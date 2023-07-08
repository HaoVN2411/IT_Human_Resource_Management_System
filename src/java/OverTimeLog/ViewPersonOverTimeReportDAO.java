/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OverTimeLog;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.Utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class ViewPersonOverTimeReportDAO {

    private static String VIEW_PERSON_OVERTIME_REPORT_BY_DATE = "select overTimeId, dateOT, dateType, co_salary, otHours, dateName, reason, isStatus, reasonReject "
            + "from OverTimeLog o "
            + "where o.employeeId like ? AND o.dateOT = ? "
            + "order by isStatus asc, dateOT desc";

    private static String VIEW_PERSON_OVERTIME_REPORT = "select overTimeId, dateOT, dateType, co_salary, otHours, dateName, reason, isStatus, reasonReject "
            + "from OverTimeLog o "
            + "where o.employeeId like ? "
            + "order by isStatus asc, dateOT desc";

    List<OverTimeReport_DTO> listPersonOverTimeReport(Date searchOverTimeReportByDate, String employeeId) throws SQLException {
        List<OverTimeReport_DTO> listPersonOverTimeReport = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_PERSON_OVERTIME_REPORT_BY_DATE);
            ptm.setString(1, employeeId);
            ptm.setDate(2, searchOverTimeReportByDate);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int overTimeId = rs.getInt("overTimeId");
                Date dateOT = rs.getDate("dateOT");
                String dateType = rs.getString("dateType");
                float coSalary = rs.getFloat("co_salary");
                float otHours = rs.getFloat("otHours");
                String dateName = rs.getString("dateName");
                String reason = rs.getString("reason");
                String status = rs.getString("isStatus");
                Boolean isStatus;
                if (status == null) {
                    isStatus = null;
                } else {
                    isStatus = rs.getBoolean("isStatus");
                }
                String reasonReject = rs.getString("reasonReject");
                listPersonOverTimeReport.add(new OverTimeReport_DTO(overTimeId, dateOT, dateType, coSalary, otHours, dateName, reason, isStatus, employeeId, reasonReject));
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
        return listPersonOverTimeReport;
    }

    List<OverTimeReport_DTO> listPersonOverTimeReport(String employeeId) throws SQLException {
        List<OverTimeReport_DTO> listPersonOverTimeReport = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_PERSON_OVERTIME_REPORT);
            ptm.setString(1, employeeId);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int overTimeId = rs.getInt("overTimeId");
                Date dateOT = rs.getDate("dateOT");
                String dateType = rs.getString("dateType");
                float coSalary = rs.getFloat("co_salary");
                float otHours = rs.getFloat("otHours");
                String dateName = rs.getString("dateName");
                String reason = rs.getString("reason");
                String status = rs.getString("isStatus");
                Boolean isStatus;
                if (status == null) {
                    isStatus = null;
                } else {
                    isStatus = rs.getBoolean("isStatus");
                }
                String reasonReject = rs.getString("reasonReject");
                listPersonOverTimeReport.add(new OverTimeReport_DTO(overTimeId, dateOT, dateType, coSalary, otHours, dateName, reason, isStatus, employeeId, reasonReject));
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
        return listPersonOverTimeReport;
    }
}
