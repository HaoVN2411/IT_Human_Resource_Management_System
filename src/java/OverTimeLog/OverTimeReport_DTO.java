/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OverTimeLog;

import java.sql.Date;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Hào Cute
 */
public class OverTimeReport_DTO {

    private int overTimeId;
    private Date dateOT;
    private String dateType;
    private float coSalary;
    private float otHours;
    private String dateName;
    private String reason;
    private Boolean isStatus;
    private String employeeId;
    private String reasonReject;

    public OverTimeReport_DTO() {
    }

    public OverTimeReport_DTO(Date dateOT, String dateType, float coSalary, float otHours, String dateName, String reason, String employeeId) {
        this.dateOT = dateOT;
        this.dateType = dateType;
        this.coSalary = coSalary;
        this.otHours = otHours;
        this.dateName = dateName;
        this.reason = reason;
        this.employeeId = employeeId;
    }

    public OverTimeReport_DTO(int overTimeId, Date dateOT, String dateType, float coSalary, float otHours, String dateName, String reason, Boolean isStatus, String employeeId, String reasonReject) {
        this.overTimeId = overTimeId;
        this.dateOT = dateOT;
        this.dateType = dateType;
        this.coSalary = coSalary;
        this.otHours = otHours;
        this.dateName = dateName;
        this.reason = reason;
        this.isStatus = isStatus;
        this.employeeId = employeeId;
        this.reasonReject = reasonReject;
    }

    public OverTimeReport_DTO(int overTimeId, Date dateOT) {
        this.overTimeId = overTimeId;
        this.dateOT = dateOT;
    }



    
    
    public int getOverTimeId() {
        return overTimeId;
    }
    
    public Date getDateOT() {
        return dateOT;
    }

    public String getDateType() {
        return dateType;
    }

    public float getCoSalary() {
        return coSalary;
    }

    public float getOtHours() {
        return otHours;
    }

    public String getDateName() {
        return dateName;
    }

    public String getReason() {
        return reason;
    }

    public Boolean isIsStatus() {
        return isStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getReasonReject() {
        return reasonReject;
    }

    public void setOverTimeId(int overTimeId) {
        this.overTimeId = overTimeId;
    }
    
    public void setDateOT(Date dateOT) {
        this.dateOT = dateOT;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public void setCoSalary(float coSalary) {
        this.coSalary = coSalary;
    }

    public void setOtHours(float otHours) {
        this.otHours = otHours;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setIsStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setReasonReject(String reasonReject) {
        this.reasonReject = reasonReject;
    }

    
    
}
