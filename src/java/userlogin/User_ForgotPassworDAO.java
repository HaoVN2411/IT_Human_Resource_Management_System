/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class User_ForgotPassworDAO {


    public String getEmail(String userID) throws ClassNotFoundException, SQLException  {
    String email = null;
    String userId = null;
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ITHumanResourceManagementSystem";
        Connection conn = DriverManager.getConnection(url, "sa", "12345");
        String query = "SELECT EmployeeInformation.email, UserLogin.userId FROM EmployeeInformation "
                + "JOIN UserLogin ON EmployeeInformation.employeeID = UserLogin.employeeId "
                + "WHERE UserLogin.userId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, userID);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            email = resultSet.getString("email");
            
        }
        
        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return email;
}
    public List<String> getUserID() throws ClassNotFoundException, SQLException  {
        List<String> listUserID = new ArrayList<>();
        String id = null;
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ITHumanResourceManagementSystem";
        Connection conn = DriverManager.getConnection(url, "sa", "12345");
        String query = "SELECT userId FROM UserLogin ";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            id = resultSet.getString("userId");
            listUserID.add(id);
        }
        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } 
        

    return listUserID;
}

}
