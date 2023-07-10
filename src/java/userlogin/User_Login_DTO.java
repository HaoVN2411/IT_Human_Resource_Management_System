/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlogin;

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
    private String image;
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public User_Login_DTO() {
    }

    public User_Login_DTO(String userID, String password, boolean isActive, String employeeId, String roleId) {
        this.userID = userID;
        this.password = password;
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.roleName = roleId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User_Login_DTO(String userID, String password, boolean isActive, String employeeId, String roleId, String image, String employeeName) {
        this.userID = userID;
        this.password = password;
        this.isActive = isActive;
        this.employeeId = employeeId;
        this.roleName = roleId;
        this.image = image;
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
}
