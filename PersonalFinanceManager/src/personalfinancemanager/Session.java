/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

/**
 *
 * @author zach
 */
public class Session {
    private long uid;
    private boolean isAuthd;
    private User currentUser;
    static Session sharedSession = new Session();
    
    public Session () {
        this.uid = 0;
        this.isAuthd = false;
        this.currentUser = new User();
    }
    
    public void setUID(long uid) {
        this.uid = uid;
    }
    
    public void setUser(User u) {
        this.currentUser = u;
    }
}
