/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_ViewInformation_Controller;

import User_Login_Controller.User_Login_DAO;
import User_Login_Controller.User_Login_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import sample.Utils.DBUtils;

/**
 *
 * @author CAO-KIEN-QUOC
 */
public class Employee_Information_DAO {
//    private static final String SEARCH="SELECT fullName, dateOfBirth, phoneNumber, email, address, humanId, nationality, employeeID, contractId "
//            + "FROM Employee_Information "
//            + "WHERE fullName like ? ";
//     private static final String SEARCH="SELECT e.fullName, e.dateOfBirth, e.phoneNumber, e.email, e.address, e.humanId, e.nationality, e.employeeID, e.contractId, u.roleId, u.employeeId "
//            + "FROM Employee_Information e FULL OUTER JOIN User_Login u ON e.employeeID = u.employeeId "
//            + "WHERE e.employeeID = 'SE124' ";

    private static final String SEARCH = "SELECT e.fullName, e.dateOfBirth, e.phoneNumber, e.email, e.address, e.humanId, e.nationality, e.employeeID, e.contractId, u.employeeId "
            + "FROM Employee_Information e "
            + "FULL OUTER JOIN User_Login u ON e.employeeID = u.employeeId "
            + "WHERE e.employeeID = u.employeeId AND e.employeeID = ? ";

    public List<Employee_Information_DTO> getListUser(String employeeId) throws SQLException {
        List<Employee_Information_DTO> listUser = new ArrayList<>();
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
                    listUser.add(new Employee_Information_DTO(fullName, dateOfBirth, phoneNumber, email, address, humanId, nationality, employeeID, contractId));
                }
                listUser.get(0).getEmployeeID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
             if(conn!=null) conn.close();
            if(ptm!=null) ptm.close();
        }      
        return listUser;
    }
}
