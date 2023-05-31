/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login_Controller;

/**
 *
 * @author Hào Cute
 */
public class UserError {
    
    private String passwordError;

    public UserError() {
    }

    public UserError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

}
