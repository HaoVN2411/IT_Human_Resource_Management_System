/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee_Info;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author flami
 */
public class Employee_Info_DTO {

    private String fullName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String gender;
    private String email;
    private String address;
    private String humanId;
    private String nationality;
    private String employeeID;
    private String image;
    private String manageBy;
    private boolean isActive;
    private String contractID;

    public Employee_Info_DTO(String fullName, LocalDate dateOfBirth, String phoneNumber,
            String gender, String email, String address, String humanId, String nationality,
            String employeeID, String image, String manageBy, boolean isActive, String contractID) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.humanId = humanId;
        this.nationality = nationality;
        this.employeeID = employeeID;
        this.image = image;
        this.manageBy = manageBy;
        this.isActive = isActive;
        this.contractID = contractID;
    }
    
        public Employee_Info_DTO(String fullName, LocalDate dateOfBirth, String phoneNumber,
            String gender, String email, String address, String humanId, String nationality,
            String employeeID, String image, String manageBy, boolean isActive) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.humanId = humanId;
        this.nationality = nationality;
        this.employeeID = employeeID;
        this.image = image;
        this.manageBy = manageBy;
        this.isActive = isActive;
    }

    public Employee_Info_DTO(){
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHumanId() {
        return humanId;
    }

    public void setHumanId(String humanId) {
        this.humanId = humanId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getManageBy() {
        return manageBy;
    }

    public void setManageBy(String manageBy) {
        this.manageBy = manageBy;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

}

