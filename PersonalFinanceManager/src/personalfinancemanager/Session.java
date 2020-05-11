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
    private int uid;
    private boolean isAuthd;
    private User currentUser;
    static Session shared = new Session();
    
    public Session () {
        this.uid = 0;
        this.isAuthd = false;
        this.currentUser = new User();
    }
    
    public User getCurrentUser(){
        return this.currentUser;
    }
    public void setUID(int uid) {
        this.uid = uid;
    }
    public int getUID() {
        return this.uid;
    }
    public void setUser(User u) {
        this.currentUser = u;
    }
}
