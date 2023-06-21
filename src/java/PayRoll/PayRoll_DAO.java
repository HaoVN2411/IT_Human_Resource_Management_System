/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayRoll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sample.Utils.DBUtils;

/**
 *
 * @author CAO-KIEN-QUOC
 */
public class PayRoll_DAO {

    private static final String OFFHOURS = "SELECT a.officeHours "
            + "FROM Attendance a FULL OUTER JOIN UserLogin u ON u.employeeId = a.employeeID "
            + "WHERE a.employeeID = u.employeeId AND a.employeeID = ? ";
    private static final String OTHOURS = "SELECT o.otHours, o.totalHours "
            + "FROM UserLogin u "
            + "FULL OUTER JOIN OverTimeLog o ON u.employeeId = o.employeeId "
            + "WHERE u.employeeId = o.employeeId AND o.isStatus like '1' AND o.employeeId = ? ";

    private static final String ALLOWANCE = "SELECT e.allowance, e.salary FROM UserLogin u "
            + "FUll OUTER JOIN EmployeeContract e ON u.employeeId = e.employeeId "
            + "WHERE u.employeeId = e.employeeID AND e.employeeID = ? ";

    private static final String INSERT = "INSERT INTO PayRoll(fullName, employeeId, paidDate, officeHours, otHours, "
            + "ot_income, standard_income, "
            + "BHXH, BHTN, TNCN, allowance, total) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    public PayRoll_DTO getOfficeHours(String employeeId) throws SQLException {
        PayRoll_DTO payRoll = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        float ofHours = 0;
        float ot = 0;
        float total = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Office_Hours
                ptm = conn.prepareStatement(OFFHOURS);
                ptm.setString(1, employeeId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float officeHours = Float.parseFloat(rs.getString("officeHours"));
                    ofHours += officeHours;
                }
                //Ot_Hours And totalHours
                ptm = conn.prepareStatement(OTHOURS);
                ptm.setString(1, employeeId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float otHours = Float.parseFloat(rs.getString("otHours"));
                    float totalHours = Float.parseFloat(rs.getString("totalHours"));
                    ot += otHours;
                    total += totalHours;
                }
                //Allowance And Salary
                ptm = conn.prepareStatement(ALLOWANCE);
                ptm.setString(1, employeeId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    float allowance = Float.parseFloat(rs.getString("allowance"));
                    float salary = Float.parseFloat(rs.getString("salary"));
                    payRoll = new PayRoll_DTO(0, null, employeeId, null, ofHours, ot, 0, 0, 0, 0, 0, allowance, 0, salary, total);
                }
                System.out.println(payRoll);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return payRoll;
    }

    public boolean InsertDTB(PayRoll_DTO payRoll) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
//                ptm.setInt(1, payRoll.getPayID());
                ptm.setString(1, payRoll.getFullName());
                ptm.setString(2, payRoll.getEmployeeID());
                ptm.setDate(3, (java.sql.Date) payRoll.getPaidDate());
                ptm.setFloat(4, payRoll.getOfficeHours());
                ptm.setFloat(5, payRoll.getOtHours());
                ptm.setFloat(6, payRoll.getOt_income());
                ptm.setFloat(7, payRoll.getStand_income());
                ptm.setFloat(8, payRoll.getBHXH());
                ptm.setFloat(9, payRoll.getBHTN());
                ptm.setFloat(10, payRoll.getTNCN());
                ptm.setFloat(11, payRoll.getAllowance());
                ptm.setFloat(12, payRoll.getTotal());
                result=ptm.executeUpdate()>0?true:false;
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
}
