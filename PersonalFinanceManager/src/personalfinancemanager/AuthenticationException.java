/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinancemanager;

import java.io.IOException;

/**
 *
 * @author omt
 */
public class AuthenticationException extends IOException {
    public AuthenticationException(String message) {
        super(message);
    }
}
