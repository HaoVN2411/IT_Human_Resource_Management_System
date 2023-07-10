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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class ViewManagedEmployeeOverTimeReportDAO {

    private static String VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT = "select overTimeId, dateOT, dateType, co_salary, otHours, dateName, reason, isStatus, o.employeeId, reasonReject "
            + "from OverTimeLog o, EmployeeInformation e "
            + "where o.employeeId = e.employeeID AND o.employeeId like ? AND e.manageBy like ? "
            + "order by isStatus asc, dateOT desc";

    private static String VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT_BY_DATE = "select overTimeId, dateOT, dateType, co_salary, otHours, dateName, reason, isStatus, o.employeeId, reasonReject "
            + "from OverTimeLog o, EmployeeInformation e "
            + "where o.employeeId = e.employeeID AND o.employeeId like ? AND e.manageBy like ? AND o.dateOT = ? "
            + "order by isStatus asc, dateOT desc";

    List<OverTimeReport_DTO> listManagedEmployeeOverTimeReport(String searchPersonReportById, String userLoginId) throws SQLException {
        List<OverTimeReport_DTO> listManagedEmployeeOverTimeReport = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT);
            ptm.setString(1, "%" + searchPersonReportById + "%");
            ptm.setString(2, userLoginId);
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
                String employeeId = rs.getString("employeeId");
                String reasonReject = rs.getString("reasonReject");
                listManagedEmployeeOverTimeReport.add(new OverTimeReport_DTO(overTimeId, dateOT, dateType, coSalary, otHours, dateName, reason, isStatus, employeeId, reasonReject));
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
        return listManagedEmployeeOverTimeReport;
    }

    List<OverTimeReport_DTO> listManagedEmployeeOverTimeReport(String searchOverTimeReportById, Date searchOverTimeReportByDate, String userLoginId) throws SQLException {
        List<OverTimeReport_DTO> listManagedEmployeeOverTimeReport = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT_BY_DATE);
            ptm.setString(1, "%" + searchOverTimeReportById + "%");
            ptm.setString(2, userLoginId);
            ptm.setDate(3, searchOverTimeReportByDate);
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
                String employeeId = rs.getString("employeeId");
                String reasonReject = rs.getString("reasonReject");
                listManagedEmployeeOverTimeReport.add(new OverTimeReport_DTO(overTimeId, dateOT, dateType, coSalary, otHours, dateName, reason, isStatus, employeeId, reasonReject));
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
        return listManagedEmployeeOverTimeReport;
    }
}
