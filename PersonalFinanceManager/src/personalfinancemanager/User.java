package personalfinancemanager;

import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcusmaibach
 */
public class User {
    String name;
    String address;
    String phone;
    ArrayList<Account> accounts = new ArrayList<Account>();
    
    public String[] getUserInfo(){
        
        return null;
    }
    public void addAccount(Account account){
       accounts.add(account);
    }
    public Account getAccount(long accountNumber){
        for(Account a : accounts){
            if(a.accountNumber == accountNumber){
                return a;
            }
        }
        return null;
        
    }
    
}