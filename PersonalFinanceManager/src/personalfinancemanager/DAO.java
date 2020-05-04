

package personalfinancemanager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement; 


public class DAO {
    
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
                + " account_num integer UNIQUE, \n" 
                + " routing_num integer, \n"
                + " balance REAL\n"  
                + ");";  
        
        tables[1] = "CREATE TABLE IF NOT EXISTS cards (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " account_id NUMBER NOT NULL, \n"
                + " card_num NUMBER UNIQUE, \n"
                + " balance REAL, \n"
                + " FOREIGN KEY (account_id) REFERENCES accounts (id)\n"
                + ");";
        
        tables[2] = "CREATE TABLE IF NOT EXISTS receipts (\n"
                + " _id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " card_num NUMBER, \n"
                + " sub_total REAL, \n"
                + " sales_tax REAL, \n"
                + " total REAL, \n"
                + " discount REAL, \n"
                + " cash_paid REAL, \n"
                + " dat_purchase DATE, \n"
                + " FOREIGN KEY (card_id) REFERENCES cards (id)\n"
                + ");";
        
        tables[3] = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " last_update DATETIME NOT NULL, \n"
                + " user_name TEXT NOT NULL UNIQUE, \n"
                + " password TEXT NOT NULL\n"
                + ");";
        
        tables[4] = "CREATE TABLE IF NOT EXISTS users_to_accounts (\n"
                + " user_id NUMBER NOT NULL, \n"
                + " account_id NUMBER NOT NULL, \n"
                + " FOREIGN KEY (user_id) REFERENCES users (id), \n"
                + " FOREIGN KEY (account_id) REFERENCES accounts (id)\n"
                + ");";
        
        tables[5] = "CREATE TABLE IF NOT EXISTS deposits (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " source_account_id NUMBER NOT NULL, \n"
                + " dest_account_id NUMBER NOT NULL, \n"
                + " amount REAL, \n"
                + " FOREIGN KEY (source_account_id) REFERENCES accounts (id), \n"
                + " FOREIGN KEY (dest_account_id) REFERENCES accounts (id)\n"
                + ");";
        
        tables[6] = "CREATE TABLE IF NOT EXISTS withdrawls (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " source_account_id NUMBER NOT NULL, \n"
                + " dest_account_id NUMBER NOT NULL, \n"
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
    
    public void insertAccount(long account_num, long routing_num, double balance) {  
        String sql = "INSERT INTO accounts(account_num, routing_num, balance) VALUES(?,?,?)";  
   
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setLong(1, account_num);  
            pstmt.setLong(2, routing_num);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insertCards( long account_num, long card_num, double balance) {
        String sql = "INSERT INTO cards(account_num, card_num, balance) VALUES(?,?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setLong(1, account_num);
            pstmt.setLong(2, card_num);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
    
    public void insertReceipts( long card_num, double subTotal, double total, double tax,
            double discount, double cash, Date date) {
        String sql = "INSERT INTO receipts( card_num, sub_total, sales_tax,"
                + " total, discount, cash_paid, change_due) VALUES(?,?,?,?,?,?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setLong(1, card_num);
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
}
