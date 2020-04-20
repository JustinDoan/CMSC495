/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

/**
 *
 * @author marcusmaibach
 */
public class Account {
    private double accountBalance;
    public long accountNumber;
    long routingNumber;
    
    public Account(double accountBalance, long accountNumber, long routingNumber){
        this.accountBalance = accountBalance;
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }
    public void deposit(double amount){
        accountBalance += amount;
    }
    public void withdraw(double amount){
        accountBalance -= amount;
    }
    public void transfer(double amount, Account accountTo){
        accountTo.deposit(amount);
        withdraw(amount);
    }
    public double checkBalance(){
        return accountBalance;
    }
    //TODO: Implement
    public String[] getAccountSummary(long accountNumber){
        
        
        return null;
    }
    
    
}
