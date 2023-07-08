/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeaveLog;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import sample.Utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class CreateLeaveLogDAO {

    private static String LIST_HOLIDAYS = "SELECT note FROM Holiday WHERE date = ?";

    private static String CHECK_EXIST_DATE = "select leaveLogID from LeaveLog where dateLeave = ? AND employeeId = ?";

    private static String CREATE_LEAVELOG_APPLICATION = "INSERT INTO LeaveLog(dateLeave, reason, employeeId)"
            + "values (?, ?, ?)";

    public static String CHECK_ALL_LEAVE_LOG_IN_MONTH = " Select leaveLogID "
            + "	from LeaveLog "
            + "	where MONTH(dateleave) = ? AND employeeId = ? AND isStatus = 1";

    public static String CHECK_ALL_LEAVE_LOG_IN_YEAR = " Select leaveLogID "
            + "from LeaveLog "
            + "where Year(dateleave) = ? AND employeeId = ? AND isStatus = 1";

    boolean checkExistDateOfEmployee(Date dateLeave, String employeeId) throws SQLException {
        boolean checkExistDate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(CHECK_EXIST_DATE);
            ptm.setDate(1, dateLeave);
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

    boolean CreateLeaveLogApplication(LeaveLog_DTO LeaveLogApply) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CREATE_LEAVELOG_APPLICATION);
            ptm.setDate(1, LeaveLogApply.getDateLeave());
            ptm.setString(2, LeaveLogApply.getReason());
            ptm.setString(3, LeaveLogApply.getEmployeeId());
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

    public int totalLeaveLogInMonth(int MonthLeaveLog, String employeeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CHECK_ALL_LEAVE_LOG_IN_MONTH);
            ptm.setInt(1, MonthLeaveLog);
            ptm.setString(2, employeeId);
            rs = ptm.executeQuery();
            while (rs.next()) {
                count += 1;
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
        return count;
    }

    public int totalLeaveLogInYear(int YearLeaveLog, String employeeId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CHECK_ALL_LEAVE_LOG_IN_YEAR);
            ptm.setInt(1, YearLeaveLog);
            ptm.setString(2, employeeId);
            rs = ptm.executeQuery();
            while (rs.next()) {
                count += 1;
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
        return count;
    }
}
