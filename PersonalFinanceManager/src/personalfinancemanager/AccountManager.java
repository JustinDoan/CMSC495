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
interface AccountManager {
    
    public String[] getAccounts();
    public void transaction(String transactionType, long sourceAccountNumber, long destinationAccountNumber, double amount);
}
