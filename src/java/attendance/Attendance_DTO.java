/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;
import java.time.LocalDateTime;
/**
 *
 * @author admin
 */
public class Attendance_DTO {
    private int attendanceId;
    private float  startHours;
    private float  endHours;
    private float officeHours;
    private float totalHours;
    private String employeeID;

    public Attendance_DTO() {
    }

    public Attendance_DTO(int attendanceId, float startHours, float endHours, float officeHours, float totalHours, String employeeID) {
        this.attendanceId = attendanceId;
        this.startHours = startHours;
        this.endHours = endHours;
        this.officeHours = officeHours;
        this.totalHours = totalHours;
        this.employeeID = employeeID;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public float getStartHours() {
        return startHours;
    }

    public void setStartHours(float startHours) {
        this.startHours = startHours;
    }

    public float getEndHours() {
        return endHours;
    }

    public void setEndHours(float endHours) {
        this.endHours = endHours;
    }

    public float getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(float officeHours) {
        this.officeHours = officeHours;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    
    
    
}
