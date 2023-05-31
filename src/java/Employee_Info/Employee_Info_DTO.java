/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee_Info;

import java.util.Date;

/**
 *
 * @author Hào Cute
 */
public class Employee_Info_DTO {

    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String humanId;
    private String nationality;
    private String employeeID;
    private String contractId;

    public Employee_Info_DTO() {
    }

    public Employee_Info_DTO(String fullName, Date dateOfBirth, String phoneNumber, String email, String address, String humanId, String nationality, String employeeID, String contractId) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.humanId = humanId;
        this.nationality = nationality;
        this.employeeID = employeeID;
        this.contractId = contractId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

}
