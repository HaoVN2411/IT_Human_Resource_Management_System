/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login_Controller;

/**
 *
 * @author CAO-KIEN-QUOC
 */
public class UserError {
    private String passwordError;
    private String confirmError;
    private String newPasswordError;

    public UserError(String passwordError, String confirmError, String newPasswordError) {
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.newPasswordError = newPasswordError;
    }
    
    public UserError() {
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getNewPasswordError() {
        return newPasswordError;
    }

    public void setNewPasswordError(String newPasswordError) {
        this.newPasswordError = newPasswordError;
    }

    
}
