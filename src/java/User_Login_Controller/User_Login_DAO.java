/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login_Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.Utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class User_Login_DAO {

    private static final String USER_LOGIN = "select u.employeeId, u.isActive, r.roleName from User_Login  u, RoleInfo r where u.roleId = r.RoleID AND userID = ? AND password = ? ";
    private static final String CHANGE = "UPDATE User_Login SET password=? where userID=?";

    public User_Login_DTO LoginUser(String userID, String password) throws SQLException {
        User_Login_DTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(USER_LOGIN);
            ptm.setString(1, userID);
            ptm.setString(2, password);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String employeeId = rs.getString("employeeId");
                boolean isActive = rs.getBoolean("isActive");
                String roleName = rs.getString("roleName");
                user = new User_Login_DTO(userID, password, isActive, employeeId, roleName);
            }
        } catch (Exception e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return user;
    }

    public boolean change(User_Login_DTO user) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHANGE);
                ptm.setString(1, user.getPassword());
                ptm.setString(2, user.getUserID());
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
}
