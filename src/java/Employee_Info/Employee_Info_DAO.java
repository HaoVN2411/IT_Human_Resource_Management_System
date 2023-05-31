/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employee_Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sample.Utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class Employee_Info_DAO {
        private static final String SEARCH = "SELECT e.fullName, e.dateOfBirth, e.phoneNumber, e.email, e.address, e.humanId, e.nationality, e.employeeID, e.contractId, u.employeeId "
            + "FROM Employee_Information e "
            + "FULL OUTER JOIN User_Login u ON e.employeeID = u.employeeId "
            + "WHERE e.employeeID = u.employeeId AND e.employeeID = ? ";

    public Employee_Info_DTO getUserInfo(String employeeId) throws SQLException {
        Employee_Info_DTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, employeeId);
                rs = ptm.executeQuery();
                if(rs.next()) {
                    String fullName = rs.getString("fullName");
                    Date dateOfBirth = rs.getDate("dateOfBirth");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String humanId = rs.getString("humanId");
                    String nationality = rs.getString("nationality");
                    String employeeID = rs.getString("employeeID");
                    String contractId = rs.getString("contractId");
                    user = new Employee_Info_DTO(fullName, dateOfBirth, phoneNumber, email, address, humanId, nationality, employeeID, contractId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
             if(conn!=null) conn.close();
            if(ptm!=null) ptm.close();
        }      
        return user;
    }
}
