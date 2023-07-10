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
public class ViewPersonLeaveLogApplicationDAO {

    private static String VIEW_PERSON_LEAVELOG_APPLICATION_BY_DATE = "select leaveLogID, dateLeave, reason, isStatus "
            + "from LeaveLog o "
            + "where o.employeeId like ? AND o.dateLeave = ? "
            + "order by isStatus asc, dateLeave desc";

    private static String VIEW_PERSON_LEAVELOG_APPLICATION = "select leaveLogID, dateLeave, reason, isStatus "
            + "from LeaveLog o "
            + "where o.employeeId like ? "
            + "order by isStatus asc, dateLeave desc";

    List<LeaveLog_DTO> listPersonLeaveLogApply(Date searchLeaveLogApplyByDate, String employeeId) throws SQLException {
        List<LeaveLog_DTO> listPersonLeaveLogApply = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_PERSON_LEAVELOG_APPLICATION_BY_DATE);
            ptm.setString(1, employeeId);
            ptm.setDate(2, searchLeaveLogApplyByDate);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int leaveLogID = rs.getInt("leaveLogID");
                Date dateLeaveLog = rs.getDate("dateLeave");
                String reason = rs.getString("reason");
                String status = rs.getString("isStatus");
                Boolean isStatus;
                if (status == null) {
                    isStatus = null;
                } else {
                    isStatus = rs.getBoolean("isStatus");
                }
                listPersonLeaveLogApply.add(new LeaveLog_DTO(leaveLogID, dateLeaveLog, reason, isStatus, employeeId));
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
        return listPersonLeaveLogApply;
    }

    List<LeaveLog_DTO> listPersonLeaveLogApply(String employeeId) throws SQLException {
        List<LeaveLog_DTO> listPersonLeaveLogApply = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_PERSON_LEAVELOG_APPLICATION);
            ptm.setString(1, employeeId);
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
                listPersonLeaveLogApply.add(new LeaveLog_DTO(leaveLogID, dateLeave, reason, isStatus, employeeId));
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
        return listPersonLeaveLogApply;
    }
}
