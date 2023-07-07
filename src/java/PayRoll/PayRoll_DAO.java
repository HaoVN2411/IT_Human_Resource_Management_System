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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import sample.Utils.DBUtils;

/**
 *
 * @author CAO-KIEN-QUOC
 */
public class PayRoll_DAO {

    private static final String GET_OFFHOURS = "SELECT officeHours "
            + "FROM Attendance  "
            + "WHERE employeeID = ? AND MONTH(startHour) = ? AND YEAR(startHour) = ? ";
    private static final String GET_OTHOURS = "SELECT otHours, totalHours "
            + "FROM OverTimeLog "
            + "WHERE isStatus like '1' AND employeeId = ? AND MONTH(dateOT) = ? AND YEAR(dateOT) = ? ";

    private static final String GET_ALLOWANCE = "SELECT allowance, salary FROM EmployeeContract "
            + "WHERE employeeID = ? ";

    private static final String INSERT = "INSERT INTO PayRoll(fullName, employeeId, paidDate, officeHours, otHours, "
            + "ot_income, standard_income, "
            + "BHXH, BHTN, TNCN, allowance, total) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

//    private static final String SEARCH = "SELECT * FROM PayRoll "
//            + " WHERE MONTH(paidDate) = ? AND YEAR(paidDate) = ?";
    private static final String SEARCH_LISTPAYROLL =   " SELECT payId, fullName, p.employeeID, paidDate, officeHours, otHours, ot_income, standard_income, BHXH, BHTN, TNCN, allowance, total " +
                                            " FROM PayRoll p FULL OUTER JOIN UserLogin u ON u.employeeId = p.employeeID " +
                                            " where u.employeeId like ? ";
    private static final String GETIDPAYROLL = "SELECT distinct employeeId from Payroll WHERE MONTH(paidDate) = ? AND YEAR(paidDate) = ? ";

    private static final String GETIDATTENDAVE = "SELECT distinct employeeID from Attendance WHERE MONTH(startHour) = ? AND YEAR(startHour) = ? ";

    public PayRoll_DTO getOfficeHours(String employeeId, int month, int year) throws SQLException {
        PayRoll_DTO payRoll = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        float ot = 0;
        float total = 0;
        float totaloffHours = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //Office_Hours
                ptm = conn.prepareStatement(GET_OFFHOURS);
                ptm.setString(1, employeeId);
                ptm.setInt(2, month);
                ptm.setInt(3, year);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float offHours = Float.parseFloat(rs.getString("officeHours"));
                    totaloffHours += offHours;
                }
                //Ot_Hours And totalHours
                ptm = conn.prepareStatement(GET_OTHOURS);
                ptm.setString(1, employeeId);
                ptm.setInt(2, month);
                ptm.setInt(3, year);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float otHours = Float.parseFloat(rs.getString("otHours"));
                    float totalHours = Float.parseFloat(rs.getString("totalHours"));
                    ot += otHours;
                    total += totalHours;
                }
                //Allowance And Salary
                ptm = conn.prepareStatement(GET_ALLOWANCE);
                ptm.setString(1, employeeId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    float allowance = Float.parseFloat(rs.getString("allowance"));
                    float salary = Float.parseFloat(rs.getString("salary"));
                    payRoll = new PayRoll_DTO(0, null, employeeId, null, totaloffHours, ot, 0, 0, 0, 0, 0, allowance, 0, salary, total);
                }
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

//    public List<PayRoll_DTO> getListPayRoll(int month, int year) throws SQLException {
//        List<PayRoll_DTO> listPayRoll = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(SEARCH);
//                ptm.setInt(1, month);
//                ptm.setInt(2, year);
//                rs = ptm.executeQuery();
//                while (rs.next()) {
//                    int payID = rs.getInt("payId");
//                    String fullName = rs.getString("fullName");
//                    String employeeID = rs.getString("employeeId");
//                    Date paidDate = rs.getDate("paidDate");
//                    float officeHours = rs.getFloat("officeHours");
//                    float otHours = rs.getFloat("otHours");
//                    float ot_income = rs.getFloat("ot_income");
//                    float standard_income = rs.getFloat("standard_income");
//                    float BHXH = rs.getFloat("BHXH");
//                    float BHTN = rs.getFloat("BHTN");
//                    float TNCN = rs.getFloat("TNCN");
//                    float allowance = rs.getFloat("allowance");
//                    float total = rs.getFloat("total");
//                    listPayRoll.add(new PayRoll_DTO(payID, fullName, employeeID, (java.sql.Date) paidDate, officeHours, otHours, ot_income, standard_income, BHXH, BHTN, TNCN, allowance, total, total, otHours));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
//        return listPayRoll;
//
//    }

    public List<String> getEmployeeID(int month, int year) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String id = null;
        List<String> listEmployeeID = new ArrayList<>();
        conn = DBUtils.getConnection();
        if (conn != null) {
            ptm = conn.prepareStatement(GETIDATTENDAVE);
            ptm.setInt(1, month);
            ptm.setInt(2, year);
            rs = ptm.executeQuery();
            while (rs.next()) {
                id = rs.getString("employeeID");
                listEmployeeID.add(id);
            }

        }
        return listEmployeeID;
    }

    public List<String> getEmployeeIDPayroll(int month, int year) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String id = null;
        List<String> listEmployeeID = new ArrayList<>();
        conn = DBUtils.getConnection();
        if (conn != null) {
            ptm = conn.prepareStatement(GETIDPAYROLL);
            ptm.setInt(1, month);
            ptm.setInt(2, year);
            rs = ptm.executeQuery();
            while (rs.next()) {
                id = rs.getString("employeeId");
                listEmployeeID.add(id);
            }
        }
        return listEmployeeID;
    }
    public List<PayRoll_DTO> getListPayRoll(String employeeID) throws SQLException {
        List<PayRoll_DTO> listPayRoll = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_LISTPAYROLL);
                ptm.setString(1, employeeID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int payID = rs.getInt("payId");
                    String fullName = rs.getString("fullName");
                    String employeeId = rs.getString("employeeId");
                    Date paidDate = rs.getDate("paidDate");
                    float officeHours = rs.getFloat("officeHours");
                    float otHours = rs.getFloat("otHours");
                    float ot_income = rs.getFloat("ot_income");
                    float standard_income = rs.getFloat("standard_income");
                    float BHXH = rs.getFloat("BHXH");
                    float BHTN = rs.getFloat("BHTN");
                    float TNCN = rs.getFloat("TNCN");
                    float allowance = rs.getFloat("allowance");
                    float total = rs.getFloat("total");
                    listPayRoll.add(new PayRoll_DTO(payID, fullName, employeeID, (java.sql.Date) paidDate, officeHours, otHours, ot_income, standard_income, BHXH, BHTN, TNCN, allowance, total, total, otHours));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return listPayRoll;

    }
    
}
