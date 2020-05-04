/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

/**
 *
 * @author omt
 */
public class Session {
    private int uid;
    private boolean isAuthd;
    private User currentUser;
    
    public Session () {
        this.uid = 0;
        this.isAuthd = false;
        this.currentUser = new User();
    }
}
