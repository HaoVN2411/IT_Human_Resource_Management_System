/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Hào Cute
 */
public class Attendance_DTO {

    private LocalDateTime startHours;
    private LocalDateTime endHours;
    private float officeHours;
    private float otHours;
    private int attendanceId;
    private String employeeId;
    private Date date;

    public Attendance_DTO() {
    }

    public LocalDateTime getStartHours() {
        return startHours;
    }

    public LocalDateTime getEndHours() {
        return endHours;
    }

    public float getOfficeHours() {
        return officeHours;
    }

    public float getOtHours() {
        return otHours;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setStartHours(LocalDateTime startHours) {
        this.startHours = startHours;
    }

    public void setEndHours(LocalDateTime endHours) {
        this.endHours = endHours;
    }

    public void setOfficeHours(float officeHours) {
        this.officeHours = officeHours;
    }

    public void setOtHours(float otHours) {
        this.otHours = otHours;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
