/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leavelog;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class ViewManagedEmployeeLeaveLogApplicationDAO {

    private static String VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLY = "select leaveLogID, dateLeave, reason, isStatus, o.employeeId  "
            + "from LeaveLog o, EmployeeInformation e "
            + "where o.employeeId = e.employeeID AND o.employeeId like ? AND e.manageBy like ? "
            + "order by isStatus asc, dateLeave desc ";

    private static String VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLY_BY_DATE = "select leaveLogID, dateLeave, reason, isStatus, o.employeeId "
            + "from LeaveLog o, EmployeeInformation e "
            + "where o.employeeId = e.employeeID AND o.employeeId like ? AND e.manageBy like ? AND o.dateLeave = ? "
            + "order by isStatus asc, dateLeave desc";

    List<LeaveLog_DTO> listManagedEmployeeLeaveLogApply(String searchPersonLeaveLogApplyById, String userLoginId) throws SQLException {
        List<LeaveLog_DTO> listManagedEmployeeLeaveLogApply = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLY);
            ptm.setString(1, "%" + searchPersonLeaveLogApplyById + "%");
            ptm.setString(2, userLoginId);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int leaveLogID = rs.getInt("leaveLogID");
                Date dateLeave = rs.getDate("dateLeave");
                String reason = rs.getString("reason");
                String status = rs.getString("isStatus");
                Boolean isStatus;
                if (status == null) {
                    isStatus = null;
                } else {
                    isStatus = rs.getBoolean("isStatus");
                }
                String employeeId = rs.getString("employeeId");
                listManagedEmployeeLeaveLogApply.add(new LeaveLog_DTO(leaveLogID, dateLeave, reason, isStatus, employeeId));
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
        return listManagedEmployeeLeaveLogApply;
    }

    List<LeaveLog_DTO> listManagedEmployeeLeaveLogApply(String searchLeaveLogApplyById, Date searchLeaveLogApplyByDate, String userLoginId) throws SQLException {
        List<LeaveLog_DTO> listManagedEmployeeLeaveLogApply = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLY_BY_DATE);
            ptm.setString(1, "%" + searchLeaveLogApplyById + "%");
            ptm.setString(2, userLoginId);
            ptm.setDate(3, searchLeaveLogApplyByDate);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int leaveLogID = rs.getInt("leaveLogID");
                Date dateLeave = rs.getDate("dateLeave");
                String reason = rs.getString("reason");
                String status = rs.getString("isStatus");
                Boolean isStatus;
                if (status == null) {
                    isStatus = null;
                } else {
                    isStatus = rs.getBoolean("isStatus");
                }
                String employeeId = rs.getString("employeeId");
                listManagedEmployeeLeaveLogApply.add(new LeaveLog_DTO(leaveLogID, dateLeave, reason, isStatus, employeeId));
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
        return listManagedEmployeeLeaveLogApply;
    }
}
