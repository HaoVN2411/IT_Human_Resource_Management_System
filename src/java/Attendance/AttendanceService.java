/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance;

/**
 *
 * @author admin
 */
public class AttendanceService {
    public float CalculateTotalHour(float startHour, float endHours){
        float totalHour = endHours - startHour;
        return totalHour;
    }
    
    public float CalculateOfficeHour(float startHour, float endHours){
        float officeHour = 0;
        if(startHour <= 8.25 && endHours >= 16.75){
            startHour = 8;
            officeHour = 17 - startHour - 1;
        } else if (startHour >= 8.25 && 16.75 <= endHours){
            endHours = 17;
            officeHour = 17 - startHour - 1;
        }else if (startHour >= 8.25 && endHours <= 16.75 && endHours >= 13){  
            officeHour = endHours - startHour -1 ;
        }else if (startHour >= 8.25 && endHours < 13){
            officeHour = endHours - startHour;
        } else if (startHour <= 8.25 &&   endHours <= 16.75  && 13 <= endHours){
            startHour = 8;
            officeHour = endHours - startHour -1;
        } else if (startHour <= 8.25 && endHours < 13){
            startHour = 8;
            officeHour = endHours - startHour;
        }
        return  officeHour;
    }
}
