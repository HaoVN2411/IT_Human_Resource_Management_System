/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author flami
 */
public class Employee_Info_DAO {

    private static final String SEARCH = "SELECT a.fullName, a.dateOfBirth, "
            + "a.phoneNumber, a.gender, a.email, a.address, a.humanId, a.employeeID, "
            + "a.image, a.manageBy, a.isActive, a.nationality, b.contractID "
            + "FROM EmployeeInformation AS a, EmployeeContract AS b "
            + "WHERE a.employeeID = b.employeeID AND a.employeeID = ?";

    public Employee_Info_DTO getUserInformation(String employeeId) throws SQLException {
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
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    java.sql.Date date = rs.getDate("dateOfBirth");
                    // revert to java.util.Date thành LocalDate
                    LocalDate startDate = date.toLocalDate();
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String humanId = rs.getString("humanId");
                    String employeeID = rs.getString("employeeID");
                    String image = rs.getString("image");
                    String manageBy = rs.getString("manageBy");
                    Boolean isActive = rs.getBoolean("isActive");
                    String nationality = rs.getString("nationality");
                    String contractId = rs.getString("contractID");
                    if (isActive) {
                        user = new Employee_Info_DTO(fullName, startDate,
                                phoneNumber, gender, email, address, humanId, nationality,
                                employeeID, image, manageBy, isActive, contractId);
                    }
                }
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
        return user;
    }

    private static final String SEARCH_CONTRACT = "SELECT employeeID,"
            + " startDate, salary, allowance,pathImage, creatorId, approverId, "
            + "isActive, contractID, sizeImage FROM EmployeeContract WHERE employeeID = ?";

    public List<ContractDTO> getListEmployeeContract(String employeeID) throws SQLException {
        List<ContractDTO> listContract = new ArrayList<ContractDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_CONTRACT);
                ptm.setString(1, employeeID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String contractID = rs.getString("contractID");
                    java.sql.Date date = rs.getDate("startDate");
                    // chuyển đổi kiểu dữ liệu java.util.Date thành LocalDate
                    LocalDate startDate = date.toLocalDate();
                    float salary = rs.getFloat("salary");
                    float allowance = rs.getFloat("allowance");
                    String pathImage = rs.getString("pathImage");
                    String creatorID = rs.getString("creatorID");
                    String approverID = rs.getString("approverID");
                    boolean isActive = rs.getBoolean("isActive");
                    int sizeImage = rs.getInt("sizeImage");
                    listContract.add(new ContractDTO(contractID, startDate, salary,
                            allowance, approverID, creatorID, pathImage, sizeImage,
                            employeeID, isActive));

                }
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
        return listContract;
    }

    private static final String SEARCH_LIST_EMPLOYEE = "SELECT  DISTINCT fullName, * "
            + "FROM EmployeeInformation  WHERE (fullName LIKE ? OR employeeID LIKE ?)"
            + " AND manageBy = ? AND isActive = ?";
    private static final String SEARCH_ALL_EMPLOYEE = "SELECT  DISTINCT fullName, * "
            + "FROM EmployeeInformation  WHERE (fullName LIKE ? OR employeeID LIKE ?)"
            + " AND manageBy = ?";

    public List<Employee_Info_DTO> getListEmployee(String search, String statusEmployee, String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List listEmployee = new ArrayList<Employee_Info_DTO>();
        String sql;
        if (statusEmployee.equals("All")) {
            sql = SEARCH_ALL_EMPLOYEE;
        } else {
            sql = SEARCH_LIST_EMPLOYEE;
        }

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + search + "%");
                ptm.setString(2, "%" + search + "%");
                ptm.setString(3, userID);
                if (!statusEmployee.equals("All")) {
                    Boolean isActive;
                    if (statusEmployee.equals("Working")) {
                        isActive = true;
                    } else {
                        isActive = false;
                    }
                    ptm.setBoolean(4, isActive);
                }
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    java.sql.Date date = rs.getDate("dateOfBirth");
                    // revert to java.util.Date thành LocalDate
                    LocalDate dob = date.toLocalDate();
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String humanId = rs.getString("humanId");
                    String employeeID = rs.getString("employeeID");
                    String image = rs.getString("image");
                    String manageBy = rs.getString("manageBy");
                    Boolean isActive = rs.getBoolean("isActive");
                    String nationality = rs.getString("nationality");
                    listEmployee.add(new Employee_Info_DTO(fullName, dob,
                            phoneNumber, gender, email, address, humanId, nationality,
                            employeeID, image, manageBy, isActive));
                }
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
        return listEmployee;
    }

    private static final String SEARCH_EMPLOYEE_QUERRY = "SELECT fullName, dateOfBirth, "
            + "phoneNumber, gender, email, address, humanId, employeeID, "
            + "image, manageBy, isActive, nationality "
            + "FROM EmployeeInformation "
            + "WHERE employeeID = ?";

    public Employee_Info_DTO getAEmployee(String employeeId) throws SQLException {
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
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    java.sql.Date date = rs.getDate("dateOfBirth");
                    // revert to java.util.Date thành LocalDate
                    LocalDate startDate = date.toLocalDate();
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String humanId = rs.getString("humanId");
                    String employeeID = rs.getString("employeeID");
                    String image = rs.getString("image");
                    String manageBy = rs.getString("manageBy");
                    Boolean isActive = rs.getBoolean("isActive");
                    String nationality = rs.getString("nationality");
                    if (isActive) {
                        user = new Employee_Info_DTO(fullName, startDate,
                                phoneNumber, gender, email, address, humanId, nationality,
                                employeeID, image, manageBy, isActive);
                    }
                }
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
        return user;
    }
}
