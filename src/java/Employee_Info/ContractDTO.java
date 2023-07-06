/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employee_Info;

import java.time.LocalDate;

/**
 *
 * @author flami
 */
public class ContractDTO {
    private String ContractID;
    private LocalDate startDate;
    private float salary;
    private float allowance;
    private String approverID;
    private String creatorID;
    private String pathImage;
    private int sizeImage;
    private String employeeID;
    private boolean Active;

    public ContractDTO(String ContractID, LocalDate startDate, float salary, 
            float allowance, String approverID, String creatorID, String pathImage,
            int sizeImage, String employeeID, boolean isActive) {
        this.ContractID = ContractID;
        this.startDate = startDate;
        this.salary = salary;
        this.allowance = allowance;
        this.approverID = approverID;
        this.creatorID = creatorID;
        this.pathImage = pathImage;
        this.sizeImage = sizeImage;
        this.employeeID = employeeID;
        this.Active = isActive;
    }
    
    public ContractDTO(){
    }

    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getAllowance() {
        return allowance;
    }

    public void setAllowance(float allowance) {
        this.allowance = allowance;
    }

    public String getApproverID() {
        return approverID;
    }

    public void setApproverID(String approverID) {
        this.approverID = approverID;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public int getSizeImage() {
        return sizeImage;
    }

    public void setSizeImage(int sizeImage) {
        this.sizeImage = sizeImage;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean isActive) {
        this.Active = isActive;
    }
}
