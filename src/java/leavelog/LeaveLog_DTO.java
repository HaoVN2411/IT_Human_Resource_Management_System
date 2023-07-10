/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leavelog;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Hào Cute
 */
public class LeaveLog_DTO implements Serializable{

    private int leaveLogID;
    private Date dateLeave;
    private String reason;
    private Boolean isStatus;
    private String employeeId;

    public LeaveLog_DTO() {
    }

    public LeaveLog_DTO(int leaveLogID, Date dateLeave, String reason, Boolean isStatus, String employeeId) {
        this.leaveLogID = leaveLogID;
        this.dateLeave = dateLeave;
        this.reason = reason;
        this.isStatus = isStatus;
        this.employeeId = employeeId;
    }

    public LeaveLog_DTO(Date dateLeave, String reason, String employeeId) {
        this.dateLeave = dateLeave;
        this.reason = reason;
        this.employeeId = employeeId;
    }


    public LeaveLog_DTO(int leaveLogID, Date dateLeave) {
        this.leaveLogID = leaveLogID;
        this.dateLeave = dateLeave;
    }
    
    

    public int getLeaveLogID() {
        return leaveLogID;
    }

    public Date getDateLeave() {
        return dateLeave;
    }

    public String getReason() {
        return reason;
    }

    public Boolean getIsStatus() {
        return isStatus;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setLeaveLogID(int leaveLogID) {
        this.leaveLogID = leaveLogID;
    }

    public void setDateLeave(Date dateLeave) {
        this.dateLeave = dateLeave;
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

}
