/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leavelog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author Hào Cute
 */
public class EditLeaveLogDAO {

    private static String EDIT_LEAVELOG = "UPDATE LeaveLog "
            + " SET dateLeave = ?, reason = ?, isStatus = NULL "
            + " WHERE employeeId = ? AND leaveLogID = ? AND (isStatus IS NULL OR isStatus = 0) ";

    boolean editLeaveLogApply(int leaveLogID, LeaveLog_DTO applicationAfterEdited) throws SQLException {
        boolean checkEditOverTime = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(EDIT_LEAVELOG);
            ptm.setDate(1, applicationAfterEdited.getDateLeave());
            ptm.setString(2, applicationAfterEdited.getReason());
            ptm.setString(3, applicationAfterEdited.getEmployeeId());
            ptm.setInt(4, leaveLogID);
            checkEditOverTime = ptm.executeUpdate() > 0 ? true : false;
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
        return checkEditOverTime;
    }

}
