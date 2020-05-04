

package personalfinancemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class DAO {
    
    Connection conn = null;

    public void connect() {
        String url = "jdbc:sqlite:./PFM.db";

        try {  
            conn = DriverManager.getConnection(url);
            createNewTables();
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
     private void createNewTables() {    
        String[] tables = new String[6];
         
        tables[0] = "CREATE TABLE IF NOT EXISTS accounts (\n"  
                + " _id integer PRIMARY KEY AUTOINCREMENT,\n"  
                + " account_num integer NOT NULL UNIQUE,\n" 
                + " routing_num integer NOT NULL,\n"
                + " balance real\n"  
                + ");";  
        
        tables[1] = "CREATE TABLE IF NOT EXISTS cards (\n"
                + " _id integer PRIMARY KEY AUTOINCREMENT, \n"
                + " account_id integer, \n"
                + " account_num integer NOT NULL, \n"
                + " card_num integer NOT NULL UNIQUE, \n"
                + " balance real,\n"
                + " FOREIGN KEY (account_id) REFERENCES accounts (_id)\n"
                + ");";
        
        tables[2] = "CREATE TABLE IF NOT EXISTS receipts (\n"
                + " _id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " card_id integer,\n"
                + " card_num integer,\n"
                + " sub_total real,\n"
                + " sales_tax real,\n"
                + " total real, \n"
                + " discount real, \n"
                + " cash_paid real, \n"
                + " dat_purchase text\n"
                + ");";
        
        tables[3] = "CREATE TABLE IF NOT EXISTS users (\n"
                + " _id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " last_update dateTime,\n"
                + " user_name text NOT NULL UNIQUE,\n"
                + " password text NOT NULL,\n"
                + " email_address text,\n"
                + " phone_number integer\n"
                + ");";
        
        tables[4] = "CREATE TABLE IF NOT EXISTS users_to_accounts (\n"
                + " user_id integer NOT NULL,\n"
                + " account_id integer NOT NULL,\n"
                + " FOREIGN KEY (user_id) REFERENCES users (_id),\n"
                + " FOREIGN KEY (account_id) REFERENCES accounts (_id)\n"
                + ");";
        
        tables[5] = "CREATE TABLE IF NOT EXISTS transactions (\n"
                + " source_account_id integer NOT NULL,\n"
                + " dest_account_id integer NOT NULL,\n"
                + " FOREIGN KEY (source_account_id) REFERENCES accounts (_id),\n"
                + " FOREIGN KEY (dest_account_id) REFERENCES accounts (_id)\n"
                + ");";
        
        try{   
            Statement stmt = conn.createStatement();
            for(int i = 0; i < 6; i++){
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
            double discount, double cash, LocalDateTime time) {
        String sql = "INSERT INTO receipts(card_num, sub_total, sales_tax, "
                + "total, discount, cash_paid) VALUES(?,?,?,?,?,?)";
        
        try{   
            PreparedStatement pstmt = conn.prepareStatement(sql);    
            pstmt.setLong(1, card_num);
            pstmt.setDouble(2, subTotal);
            pstmt.setDouble(3, tax);
            pstmt.setDouble(4, total);
            pstmt.setDouble(5, discount);
            pstmt.setDouble(6, cash);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
}
