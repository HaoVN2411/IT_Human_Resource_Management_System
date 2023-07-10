/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import utils.DBUtils;

public class Attendance_DAO {

    private static final String INSERT_STARTHOUR = "INSERT INTO Attendance(startHour, employeeID) VALUES(?,?)";

    private static final String INSERT_ENDHOUR = "UPDATE Attendance SET endHour = ? WHERE employeeID = ? AND CONVERT(DATE, startHour) = ?";

    private static final String CHECK_EXIST = "SELECT * FROM Attendance WHERE employeeID = ? AND CONVERT(DATE, startHour) = ?";

    private static final String GET_OFFHOUR = "SELECT FORMAT(startHour, 'HH:mm') AS startHourTime, FORMAT(endHour, 'HH:mm') AS endHourTime FROM Attendance WHERE employeeID = ? AND CONVERT(DATE, startHour) = ?";

    private static final String INSERT = "UPDATE Attendance SET officeHours = ? , totalHours = ? WHERE employeeID = ? AND CONVERT(DATE, startHour) = ? ";
    
    private static String LIST_HOLIDAYS = "SELECT note FROM Holiday WHERE date = ?";
    
    public boolean checkAttendanceExists(String employeeID, LocalDate date) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST);
                ptm.setString(1, employeeID);
                ptm.setDate(2, java.sql.Date.valueOf(date));
                rs = ptm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }

        return result;
    }

    public boolean getStartHours(LocalDateTime startHour, String employeeID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        Attendance_DTO attendance = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_STARTHOUR);
                startHour = LocalDateTime.now();
                ptm.setTimestamp(1, Timestamp.valueOf(startHour));
                ptm.setString(2, employeeID);

                result = ptm.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }

        return result;
    }

    public boolean updateAttendance(LocalDateTime endHour, String employeeID, LocalDate date) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ENDHOUR);
                endHour = LocalDateTime.now();
                ptm.setTimestamp(1, Timestamp.valueOf(endHour));
                ptm.setString(2, employeeID);
                ptm.setDate(3, Date.valueOf(date));
                result = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }
        return result;
    }

    public Attendance_DTO getOffHour(String employeeID, LocalDate Date) throws SQLException {
        Attendance_DTO attendance = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        float startHour = 0.0f;
        float endHour = 0.0f;
        NumberFormat format = NumberFormat.getInstance();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_OFFHOUR);
                ptm.setString(1, employeeID);
                ptm.setDate(2, java.sql.Date.valueOf(Date));
                rs = ptm.executeQuery();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                if (rs.next()) {
                    String startHourTime = rs.getString("startHourTime");
                    String endHourTime = rs.getString("endHourTime");

                    // Chuyển đổi từ chuỗi thành LocalTime
                    LocalTime startTime = LocalTime.parse(startHourTime, formatter);
                    LocalTime endTime = LocalTime.parse(endHourTime, formatter);

                    // Chuyển đổi từ LocalTime sang Float (nếu cần)
                    startHour = startTime.getHour() + (startTime.getMinute() / 60.0f);
                    endHour = endTime.getHour() + (endTime.getMinute() / 60.0f);

                }
                attendance = new Attendance_DTO(0, startHour, endHour, 0, 0, employeeID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }

        return attendance;
    }

    public void insertOffHour(float offHour, float totalHour, String employeeId, LocalDate b) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setFloat(1, offHour);
                ptm.setFloat(2, totalHour);
                ptm.setString(3, employeeId);
                ptm.setDate(4, java.sql.Date.valueOf(b));
                result = ptm.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }

    }
    
    public String holidayType(Date date) {
        String holidayName = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(LIST_HOLIDAYS);
            ptm.setDate(1, date);
            rs = ptm.executeQuery();
            while (rs.next()) {
                holidayName = rs.getString("note");
            }
        } catch (Exception e) {
        }
        return holidayName;
    }
}
