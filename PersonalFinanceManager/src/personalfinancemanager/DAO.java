

package personalfinancemanager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

//NEEDED FOR LOGIN:
import java.time.LocalDateTime;    
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;

//NEEDED FOR GETACCOUNTS:
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DAO {
    static DAO shared = new DAO();
    Connection conn = null;

    public void connect() {
        String url = "jdbc:sqlite:./PFM.db";

        try {  
            conn = DriverManager.getConnection(url);
            createNewTables();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
     private void createNewTables() {    
        String[] tables = new String[7];
         
        tables[0] = "CREATE TABLE IF NOT EXISTS accounts (\n"  
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"  
                + " account_num TEXT UNIQUE, \n" 
                + " routing_num TEXT, \n"
                + " balance REAL\n"
                + ");";  
        
        tables[1] = "CREATE TABLE IF NOT EXISTS cards (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " account_id INTEGER NOT NULL, \n"
                + " card_num TEXT UNIQUE, \n"
                + " balance REAL, \n"
                + " FOREIGN KEY (account_id) REFERENCES accounts (id)\n"
                + ");";
        
        tables[2] = "CREATE TABLE IF NOT EXISTS receipts (\n"
                + " _id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " card_num TEXT, \n"
                + " sub_total REAL, \n"
                + " sales_tax REAL, \n"
                + " total REAL, \n"
                + " discount REAL, \n"
                + " cash_paid REAL, \n"
                + " dat_purchase DATE, \n"
                + " FOREIGN KEY (card_num) REFERENCES cards (id)\n"
                + ");";
        
        tables[3] = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " last_update TEXT NOT NULL, \n"
                + " user_name TEXT NOT NULL UNIQUE, \n"
                + " password TEXT NOT NULL, \n"
                + " email_address TEXT, \n"
                + " phone_num TEXT \n"
                + ");";
        
        tables[4] = "CREATE TABLE IF NOT EXISTS users_to_accounts (\n"
                + " user_id INTEGER NOT NULL, \n"
                + " account_id INTEGER NOT NULL, \n"
                + " FOREIGN KEY (user_id) REFERENCES users (id), \n"
                + " FOREIGN KEY (account_id) REFERENCES accounts (id)\n"
                + ");";
        
        tables[5] = "CREATE TABLE IF NOT EXISTS deposits (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " source_account_id TEXT, \n"
                + " dest_account_id TEXT NOT NULL, \n"
                + " amount REAL, \n"
                + " FOREIGN KEY (source_account_id) REFERENCES accounts (id), \n"
                + " FOREIGN KEY (dest_account_id) REFERENCES accounts (id)\n"
                + ");";
        
        tables[6] = "CREATE TABLE IF NOT EXISTS withdrawals (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " source_account_id TEXT NOT NULL, \n"
                + " dest_account_id TEXT, \n"
                + " amount REAL, \n"
                + " FOREIGN KEY (source_account_id) REFERENCES accounts (id), \n"
                + " FOREIGN KEY (dest_account_id) REFERENCES accounts (id)\n"
                + ");";
        
        try{   
            Statement stmt = conn.createStatement();
            for(int i = 0; i < 7; i++){
                stmt.execute(tables[i]);
            }

        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
     
    public void closeDB() {
        try {
            if (conn != null) {
                conn.close();
            }  
        } catch (SQLException ex) {  
            System.out.println(ex.getMessage());  
        }
    }
    
    public void insertAccount(String account_num, String routing_num, double balance) {  
        String sql = "INSERT INTO accounts(account_num, routing_num, balance) VALUES(?,?,?)";  
   
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, account_num);  
            pstmt.setString(2, routing_num);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        
        sql = "INSERT INTO users_to_accounts(user_id, account_id) VALUES(?,?);";
        int uid = Session.shared.getUID();
        int aid = shared.getAccountID(account_num);
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setInt(1, uid);  
            pstmt.setInt(2, aid);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insertUser(String last_update, String user_name, String password) {  
        String sql = "INSERT INTO users(last_update, user_name, password) VALUES(?,?,?)";  
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException nsa) {
        	// handle failure
            return;
        }
        String saltedPW = password.concat(last_update.toString());
        byte[] hashbytes = digest.digest(saltedPW.getBytes(StandardCharsets.UTF_8));
        String sha_256hex = bytesToHex(hashbytes);  
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, last_update);
            pstmt.setString(2, user_name);
            pstmt.setString(3, sha_256hex);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insertCards( String account_num, String card_num, double balance) {
        String sql = "INSERT INTO cards(account_num, card_num, balance) VALUES(?,?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setString(1, account_num);
            pstmt.setString(2, card_num);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insertDeposits(String destination_account, double amount) {
        String sql = "INSERT INTO deposits(dest_account_id, amount) VALUES(?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setString(1, destination_account);
            pstmt.setDouble(2, amount);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insertWithdrawals(String source_account, double amount) {
        String sql = "INSERT INTO withdrawals(source_account_id, amount) VALUES(?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setString(1, source_account);
            pstmt.setDouble(2, -amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    //*
    public void insertTransfers(String source_account, String destination_account, double amount) {
        String sql1 = "INSERT INTO withdrawals(source_account_id, amount) VALUES(?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql1);    
            pstmt.setString(1, source_account);
            pstmt.setDouble(2, -amount);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
        
        String sql2 = "INSERT INTO deposits(dest_account_id, amount) VALUES(?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql2);    
            pstmt.setString(1, destination_account);
            pstmt.setDouble(2, amount);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } //*/
    
    public void insertReceipts( String card_num, double subTotal, double total, double tax,
            double discount, double cash, Date date) {
        String sql = "INSERT INTO receipts( card_num, sub_total, sales_tax,"
                + " total, discount, cash_paid, dat_purchase) VALUES(?,?,?,?,?,?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setString(1, card_num);
            pstmt.setDouble(2, subTotal);
            pstmt.setDouble(3, tax);
            pstmt.setDouble(4, total);
            pstmt.setDouble(5, discount);
            pstmt.setDouble(6, cash);
            pstmt.setDate(7, date);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public User login (String un, String pw) {
        User currUser = null;
        String sql = "SELECT id, user_name, last_update, password, email_address, phone_num FROM users WHERE user_name = ?;";
        int numResults = 0;
        ResultSet result;
        String sha_256hex = "";
        
        shared.connect();
        
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException nsa) {
            System.out.println(nsa.getMessage());
            //TODO
            return null;
        }
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, un);
            result = pstmt.executeQuery();
            while (result.next()) {
                numResults++;
                if(numResults > 1) {
                    //should not happen since user_name is unique
                    Main.showAlert(DialogTypes.DBERROR,null);
                    result = null;
                    break;
                }
                int userID = result.getInt("id");
                String userName = result.getString("user_name");
                String lastUpdate = result.getString("last_update");
                String password = result.getString("password");
                String emailAddr = result.getString("email_address");
                String phoneNum = result.getString("phone_num");
                
                String saltedPW = pw.concat(lastUpdate);
                byte[] hashbytes = digest.digest(saltedPW.getBytes(StandardCharsets.UTF_8));
                
                sha_256hex = bytesToHex(hashbytes);
                
                if(sha_256hex.compareTo(password) == 0) {
                    Main.showAlert(DialogTypes.SUCCESS,null);
                    currUser = new User();
                    currUser.setName(userName);
                    currUser.setID(userID);
                    shared.getAccounts(userID); //T/S
                }
                else Main.showAlert(DialogTypes.FAILURE,null);
            }
            
            if(!pw.isEmpty() && numResults<1) Main.showAlert(DialogTypes.USERNAME,null);
            //empty input is handled in the invoking function
            
        } catch (SQLException e) {  
            Logger.getLogger(WarningController.class.getName()).log(Level.SEVERE, null, e);
            Main.showAlert(DialogTypes.DBERROR,null);
        }
        
        //shared.closeDB();
        return currUser;
    }
    
    public ArrayList<String> getAccounts (int uid) {
        int numResults = 0;
        String sql = "SELECT account_id FROM users_to_accounts WHERE user_id = ";
        ResultSet result;
        ArrayList<Integer> accts = new ArrayList<Integer>();
        ArrayList<String> acctNums = new ArrayList<String>();
        
        System.out.println(Integer.toString(uid));

        shared.connect();

        try{
            Statement stmt = conn.createStatement();
            sql = sql.concat(String.format("%d;", uid));
            result = stmt.executeQuery(sql);
            /*
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            result = pstmt.executeQuery();
            */
            while (result.next()) {
                numResults++;
                accts.add(result.getInt("account_id"));
            }
        } catch (SQLException e) {
            Logger.getLogger(WarningController.class.getName()).log(Level.SEVERE, null, e);
            Main.showAlert(DialogTypes.DBERROR,null);
        }
        
        if(numResults==0) System.out.println("numResults==0");

        numResults = 0;
        sql = "SELECT account_num FROM accounts WHERE ";
        Enumeration<Integer> eAccts = Collections.enumeration(accts);
        
        while(eAccts.hasMoreElements()) {
            System.out.println("test"); //T/S
            sql = sql.concat(String.format("id=%s ", eAccts.nextElement()));
            if(eAccts.hasMoreElements()) sql = sql.concat("OR ");
        } //*/
        sql = sql.concat(";");
        
        System.out.println(sql); //T/S

        try{
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            while (result.next()) {
                numResults++;
                acctNums.add(result.getString("account_num"));
            }
        } catch (SQLException e) {
            Logger.getLogger(WarningController.class.getName()).log(Level.SEVERE, null, e);
            Main.showAlert(DialogTypes.DBERROR,null);
        }


        /* TROUBLESHOOTING
        System.out.println(sql);
        Enumeration<String> eAcctNums = Collections.enumeration(acctNums);
        while(eAcctNums.hasMoreElements()) {
            System.out.println(eAcctNums.nextElement());
        } //*/


        //shared.closeDB();
        return acctNums;
    }
    
    public ArrayList<Long> getCards (int ait) {
        ArrayList<Long> cards = new ArrayList<Long>();
        
        return cards;
    }
    
    public int getAccountID(String accountNum) {
        int aid = 0;
        int numResults = 0;
        String sql = "SELECT id FROM accounts WHERE account_num = ";
        ResultSet result;
        
        shared.connect();

        try{
            Statement stmt = conn.createStatement();
            sql = sql.concat(accountNum);
            result = stmt.executeQuery(sql);
            while (result.next()) {
                numResults++;
                if(numResults>1) {
                    //should not happen since user_name is unique
                    Main.showAlert(DialogTypes.DBERROR,null);
                    result = null;
                    break;
                }
                aid = result.getInt("id");
            }
        } catch (SQLException e) {
            Logger.getLogger(WarningController.class.getName()).log(Level.SEVERE, null, e);
            Main.showAlert(DialogTypes.DBERROR,null);
        }
        
        return aid;
    }
    
    //Source: https://www.baeldung.com/sha-256-hashing-java
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    /*
    public void warnDialog(String message, String details) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Warning.fxml"));
            Scene scene = new Scene(loader.load());
            WarningController warningController = loader.getController();
            Stage warningStage = new Stage();
            warningStage.setTitle("Warning");
            warningStage.setScene(scene);
            warningStage.initModality(Modality.APPLICATION_MODAL);
            warningStage.setAlwaysOnTop(true);
            warningStage.setResizable(false);
            //TODO hide this menu during warning
            //warningController.enclosingStage = warningStage; //DNE
            warningController.setMsg(message, details);
            warningStage.show();
        } catch (IOException ex) {
            Logger.getLogger(WarningController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void warnDialog(String details) {
        this.warnDialog("Warning",details);
    } //*/
}
