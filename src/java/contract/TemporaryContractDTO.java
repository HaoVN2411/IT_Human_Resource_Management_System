/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contract;

import candidate.CandidateDTO;
import java.time.LocalDate;

/**
 *
 * @author flami
 */
public class TemporaryContractDTO {

    private String ContractID;
    private LocalDate startDate;
    private float salary;
    private float allowance;
    private String approverID;
    private String creatorID;
    private String description;
    private int sizeImage;
    private String status;
    private String CandidateID;
    private String reason;
    
    private String pathImage;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TemporaryContractDTO(String ContractID, LocalDate startDate, float salary,
            float allowance, String approverID, String creatorID, String description,
            String CandidateID, String status, String reason) {
        this.ContractID = ContractID;
        this.startDate = startDate;
        this.salary = salary;
        this.allowance = allowance;
        this.approverID = approverID;
        this.creatorID = creatorID;
        this.description = description;
        this.status = status;
        this.CandidateID = CandidateID;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TemporaryContractDTO() {
        this.description = "";
        this.startDate = LocalDate.now();
        this.salary = 0;
        this.ContractID = "";
        this.allowance = 0;
        this.approverID = "";
        this.creatorID = "";
        this.status = status;
        this.CandidateID = "";
        this.reason = "";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCandidateID() {
        return CandidateID;
    }

    public void setCandidateID(String CandidateID) {
        this.CandidateID = CandidateID;
    }

}
