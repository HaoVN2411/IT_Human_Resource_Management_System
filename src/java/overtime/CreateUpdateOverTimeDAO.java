/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtime;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class CreateUpdateOverTimeDAO {

    private static String DATETYPE_HODIDAY = "holiday";
    private static String DATETYPE_WEEKEND = "weekend";
    private static String DATETYPE_NORMAL = "normal";

    private static String CHECK_EXIST_DATE = "select overTimeID from OverTimeLog where dateOT = ? AND employeeId = ?";

    private static String LIST_HOLIDAYS = "SELECT note FROM Holiday WHERE date = ?";

    private static String CREATE_REPORT_OVERTIME = "INSERT INTO OverTimeLog(dateOT, dateType, co_salary, otHours, dateName, reason, employeeId, total)"
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static String CONFIRM_OVERTIME = "UPDATE OverTimeLog "
            + "SET reasonReject = ?, isStatus = ? "
            + "WHERE overTimeID = ? ";

    private static String GET_MANAGER = "select manageBy "
            + "from EmployeeInformation "
            + "where employeeID = ? ";

    private static String EDIT_OVERTIME = "UPDATE OverTimeLog "
            + " SET dateOT = ?, reason = ?, otHours = ?, isStatus = NULL, dateType = ?, dateName = ?, co_salary = ?, total = ?  "
            + " WHERE employeeId = ? AND overTimeId = ? AND (isStatus IS NULL OR isStatus = 0) ";

    boolean checkExistDateOfEmployee(Date date, String employeeId) throws SQLException {
        boolean checkExistDate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(CHECK_EXIST_DATE);
            ptm.setDate(1, date);
            ptm.setString(2, employeeId);
            rs = ptm.executeQuery();
            if (rs.next()) {
                checkExistDate = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkExistDate;
    }

    public String holidayType(Date date) throws SQLException {
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
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return holidayName;
    }

    public String checkWeekend(Date date) {
        String dayOfWeek;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.getDefault());
        dayOfWeek = formatter.format(cal.getTime());
        if (dayOfWeek.contains("Sat") || dayOfWeek.contains("Sun")) {
            return dayOfWeek;
        }
        return null;
    }

    OverTimeReport_DTO returnReportObject(Date dateOT, float otHours, String reason, String employeeId) throws SQLException {
        OverTimeReport_DTO report = null;
        //check co phai Holiday khong
        String dateName = holidayType(dateOT);
        if (dateName != null) {
            report = new OverTimeReport_DTO(dateOT, DATETYPE_HODIDAY, (float) 3, otHours, dateName, reason, employeeId);
        } else {
            //check co phai la cuoi tuan khong
            dateName = checkWeekend(dateOT);
            if (dateName != null) {
                report = new OverTimeReport_DTO(dateOT, DATETYPE_WEEKEND, (float) 2, otHours, dateName, reason, employeeId);
            } else {
                report = new OverTimeReport_DTO(dateOT, DATETYPE_NORMAL, (float) 1.5, otHours, "", reason, employeeId);
            }
        }
        return report;
    }

    boolean createReport(OverTimeReport_DTO report) throws SQLException {
        float total = (float) report.getCoSalary() * report.getOtHours();
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CREATE_REPORT_OVERTIME);
            ptm.setDate(1, report.getDateOT());
            ptm.setString(2, report.getDateType());
            ptm.setFloat(3, report.getCoSalary());
            ptm.setFloat(4, report.getOtHours());
            ptm.setString(5, report.getDateName());
            ptm.setString(6, report.getReason());
            ptm.setString(7, report.getEmployeeId());
            ptm.setFloat(8, total);
            check = ptm.executeUpdate() > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    boolean confirmOverTime(String overTimeID, String reasonReject, boolean status) throws SQLException {
        boolean checkConfirm = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CONFIRM_OVERTIME);
            ptm.setString(1, reasonReject);
            ptm.setBoolean(2, status);
            ptm.setString(3, overTimeID);
            checkConfirm = ptm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkConfirm;
    }

    public boolean isEmployeeManagedByUserLogin(String userLogin, String employeeId) throws SQLException {
        boolean isEmployeeManagedByUserLogin = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(GET_MANAGER);
            ptm.setString(1, employeeId);
            rs = ptm.executeQuery();
            if (rs.next()) {
                if (rs.getString("manageBy").equals(userLogin)) {
                    isEmployeeManagedByUserLogin = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isEmployeeManagedByUserLogin;
    }

    boolean editOverTimeReport(int overTimeId, OverTimeReport_DTO reportAfterEdited) throws SQLException {
        float total = (float) reportAfterEdited.getCoSalary() * reportAfterEdited.getOtHours();
        boolean checkEditOverTime = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(EDIT_OVERTIME);
            ptm.setDate(1, reportAfterEdited.getDateOT());
            ptm.setString(2, reportAfterEdited.getReason());
            ptm.setFloat(3, reportAfterEdited.getOtHours());
            ptm.setString(4, reportAfterEdited.getDateType());
            ptm.setString(5, reportAfterEdited.getDateName());
            ptm.setFloat(6, reportAfterEdited.getCoSalary());
            ptm.setFloat(7, total);
            ptm.setString(8, reportAfterEdited.getEmployeeId());
            ptm.setInt(9, overTimeId);
            checkEditOverTime = ptm.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkEditOverTime;
    }

}
