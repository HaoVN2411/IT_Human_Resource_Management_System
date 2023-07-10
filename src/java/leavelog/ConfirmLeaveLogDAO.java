/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leavelog;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class ConfirmLeaveLogDAO {

    private static String CONFIRM_LEAVELOG = "UPDATE LeaveLog "
            + "SET isStatus = ? "
            + "WHERE leaveLogID = ? ";

    boolean confirmLeaveLog(String leaveLogID, boolean status) throws SQLException {
        boolean checkConfirm = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CONFIRM_LEAVELOG);
            ptm.setBoolean(1, status);
            ptm.setString(2, leaveLogID);
            checkConfirm = ptm.executeUpdate() > 0 ? true : false;
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
        return checkConfirm;
    }
}
