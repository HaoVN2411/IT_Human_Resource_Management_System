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
public class User_Login_DTO {

    private String userID;
    private String password;
    private boolean isActive;
    private String employeeId;
    private String roleName;
    private String employeeName;

    public User_Login_DTO() {
    }

    public User_Login_DTO(String userID, String password, boolean isActive, String employeeId, String roleName) {
        this.userID = userID;
        this.password = password;
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.roleName = roleName;
    }

    
    
    public User_Login_DTO(String userID, String password, boolean isActive, String employeeId, String roleName, String employeeName) {
        this.userID = userID;
        this.password = password;
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.roleName = roleName;
        this.employeeName = employeeName;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    
}
