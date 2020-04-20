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
interface ReceiptManager {
    public void createReceipt(long accountNumber, long cardNumber, double subtotal, double total, double cashPaid, 
            double salesTax, double discount, double changeDue);
            
            
}
