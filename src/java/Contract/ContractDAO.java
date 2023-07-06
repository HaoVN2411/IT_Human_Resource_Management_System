/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contract;

import Candidate.CandidateDTO;
import Candidate.Helper;
import sample.Utils.DBUtils;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flami
 */
public class ContractDAO {

    public boolean insertContractCandidate(TemporaryContractDTO contract) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO TemporaryContract "
                        + "(contractId, candidateID, startDate, salary, allowance,"
                        + " description, creatorID, approverID, status)"
                        + " VALUES (?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, contract.getContractID());
                stm.setString(2, contract.getCandidateID());
                java.sql.Date startDate = java.sql.Date.valueOf(contract.getStartDate());
                stm.setDate(3, startDate);
                stm.setFloat(4, contract.getSalary());
                stm.setFloat(5, contract.getAllowance());
                stm.setString(6, contract.getDescription());
                stm.setString(7, contract.getCreatorID());
                stm.setString(8, contract.getApproverID());
                stm.setString(9, contract.getStatus());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at Insert: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    private String getNextID(String id, int beginNumber) {
        String nextID = id.substring(beginNumber);
        int number = Integer.parseInt(nextID);
        if (number == 9999) {
            return null;
        }
        number++;
        nextID = id.substring(0, beginNumber) + String.valueOf(number);
        return nextID;
    }

    public String getNewIDTemporaryContract(String CONTRACT_ID_FORMAT) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String nextID = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 1 contractID FROM TemporaryContract "
                        + "ORDER BY contractID DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String previousID = rs.getString("contractID").trim();
                    String tempID = getNextID(previousID, 2);
                    if (tempID == null) {
                        throw new IllegalArgumentException("Value cannot exceed 9999");
                    }
                    nextID = tempID;
                } else {
                    nextID = CONTRACT_ID_FORMAT;
                }
            }
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return nextID;
    }

    public List<CandidateContractDTO> getListCandidateContract(String search, String status,
            String userLoginID, String roleName) throws SQLException {
        List<CandidateContractDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        status = status.toUpperCase();
        if (search.isEmpty() || search == null) {
            search = " ";
        }
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status.equals("ALL")) {
                    String sql = "SELECT b.contractId,b.candidateID, b.startDate, b.salary, b.allowance,"
                            + " b.description, b.creatorID, b.approverID, b.status, b.reason,"
                            + " a.fullName, a.gender,a.DateOfBirth, a.phoneNumber, a.email,"
                            + " a.address, a.humanID, a.nationality,a.notation,a.image,a.creatorID "
                            + " FROM Candidate As a, TemporaryContract AS b"
                            + " WHERE a.candidateID=b.candidateID AND fullName LIKE ? AND a.isActive='true' "
                            + " AND b.status IS NOT NULL";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + search + "%");
                } else {
                    String sql = "SELECT b.contractId, b.startDate,b.candidateID, b.salary, b.allowance,"
                            + "b.description, b.creatorID, b.approverID, b.status, b.reason,"
                            + "a.fullName, a.gender,a.DateOfBirth, a.phoneNumber, a.email, "
                            + "a.address, a.humanID, a.nationality,a.notation,a.image,a.creatorID "
                            + "FROM Candidate As a, TemporaryContract AS b "
                            + "WHERE a.candidateID=b.candidateID AND b.status=? AND a.fullName LIKE ? "
                            + "AND a.isActive='true'";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, status);
                    stm.setString(2, "%" + search + "%");
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    String creatorID = rs.getString("creatorID");
                    if (userLoginID.equals(creatorID) || roleName.equals("HRM")) {
                        String idCon = rs.getString("contractID");
                        Date date = rs.getDate("dateOfBirth");
                        // chuyển đổi kiểu dữ liệu java.util.Date thành LocalDate
                        LocalDate startDate = date.toLocalDate();
                        float salary = rs.getFloat("salary");
                        float allowance = rs.getFloat("allowance");
                        String description = rs.getString("description");

                        String approverID = rs.getString("approverID");
                        String candidateID = rs.getString("candidateID");
                        String statusCon = rs.getString("status");
                        String reason = rs.getString("reason");

                        TemporaryContractDTO tempContract = new TemporaryContractDTO(
                                idCon, startDate, salary, allowance,
                                approverID, creatorID, description,
                                candidateID, statusCon, reason);

                        String id = rs.getString("candidateID");
                        String fullName = rs.getString("fullName");
                        Date dateOfBirth = rs.getDate("dateOfBirth");
                        // chuyển đổi kiểu dữ liệu java.util.Date thành LocalDate
                        LocalDate dob = dateOfBirth.toLocalDate();
                        String gender = rs.getString("gender");
                        String phoneNumber = rs.getString("phoneNumber");
                        String email = rs.getString("email");
                        String address = rs.getString("address");
                        String humanID = rs.getString("humanID");
                        String nationality = rs.getString("nationality");
                        String notation = rs.getString("notation");
                        String image = rs.getString("image");

                        CandidateDTO candidate = new CandidateDTO(id, fullName, gender, dob,
                                phoneNumber, email, address, humanID, nationality,
                                notation, image, creatorID, true);
                        list.add(new CandidateContractDTO(tempContract, candidate));
                    }
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
            if (stm != null) {
                stm.close();
            }
        }
        return list;
    }

    public List<CandidateDTO> getListCandidate(String search, String status) throws SQLException {
        List<CandidateDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT candidateID, fullName, gender,DateOfBirth,"
                        + " phoneNumber, email, address, humanID, nationality, notation,image "
                        + "FROM Candidate "
                        + "WHERE fullName like ? AND isActive=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("candidateID");
                    String fullName = rs.getString("fullName");
                    Date date = rs.getDate("dateOfBirth");
                    // chuyển đổi kiểu dữ liệu java.util.Date thành LocalDate
                    LocalDate dob = date.toLocalDate();
                    String gender = rs.getString("gender");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String humanID = rs.getString("humanID");
                    String nationality = rs.getString("nationality");
                    String notation = rs.getString("notation");
                    String image = rs.getString("image");
                    String creatorID = rs.getString("creatorID");
                    Boolean isActive = rs.getBoolean("isActive");

                    list.add(new CandidateDTO(id, fullName, gender, dob,
                            phoneNumber, email, address, humanID,
                            nationality, notation, image, creatorID, isActive));
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
            if (stm != null) {
                stm.close();
            }
        }
        return list;
    }

    public boolean updateTemporaryContract(TemporaryContractDTO tempContract) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE TemporaryContract "
                        + "SET candidateID=?, startDate=?, salary=?, allowance=?,"
                        + " description=?, creatorID=?, approverID=?, status=?, reason=?"
                        + " WHERE contractID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, tempContract.getCandidateID());
                java.sql.Date startDate = java.sql.Date.valueOf(tempContract.getStartDate());
                stm.setDate(2, startDate);
                stm.setFloat(3, tempContract.getSalary());
                stm.setFloat(4, tempContract.getAllowance());
                stm.setString(5, tempContract.getDescription());
                stm.setString(6, tempContract.getCreatorID());
                stm.setString(7, tempContract.getApproverID());
                stm.setString(8, tempContract.getStatus());
                stm.setString(9, tempContract.getReason());
                stm.setString(10, tempContract.getContractID());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at Insert: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public CandidateDTO getACandidate(String candidateID) throws SQLException {
        CandidateDTO candidate = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT candidateID, fullName, gender,DateOfBirth,"
                        + " phoneNumber, email, address, humanID, nationality, notation,image, creatorID, isActive "
                        + "FROM Candidate "
                        + "WHERE candidateID = ? AND isActive = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, candidateID);
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("candidateID");
                    String fullName = rs.getString("fullName");
                    Date date = rs.getDate("dateOfBirth");
                    // chuyển đổi kiểu dữ liệu java.util.Date thành LocalDate
                    LocalDate dob = date.toLocalDate();
                    String gender = rs.getString("gender");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String humanID = rs.getString("humanID");
                    String nationality = rs.getString("nationality");
                    String notation = rs.getString("notation");
                    String image = rs.getString("image");
                    String creatorID = rs.getString("creatorID");
                    Boolean isActive = rs.getBoolean("isActive");

                    candidate = new CandidateDTO(id, fullName, gender, dob,
                            phoneNumber, email, address, humanID,
                            nationality, notation, image, creatorID, isActive);
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
            if (stm != null) {
                stm.close();
            }
        }
        return candidate;
    }

    public TemporaryContractDTO getTemporaryContract(String contractID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TemporaryContractDTO tempContract = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT candidateID, startDate, salary, allowance,"
                        + " description, creatorID, approverID, status, reason FROM TemporaryContract "
                        + "WHERE contractID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, contractID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date date = rs.getDate("startDate");
                    // chuyển đổi kiểu dữ liệu java.util.Date thành LocalDate
                    LocalDate startDate = date.toLocalDate();
                    float salary = rs.getFloat("salary");
                    float allowance = rs.getFloat("allowance");
                    String description = rs.getString("description");
                    String creatorID = rs.getString("creatorID");
                    String approverID = rs.getString("approverID");
                    String candidateID = rs.getString("candidateID");
                    String statusCon = rs.getString("status");
                    String reason = rs.getString("reason");

                    tempContract = new TemporaryContractDTO(
                            contractID, startDate, salary, allowance,
                            approverID, creatorID, description,
                            candidateID, statusCon, reason);
                }
            }
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return tempContract;
    }

    public String getNewStaffID(String CONTRACT_ID_FORMAT) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String nextID = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 1 employeeID FROM EmployeeInformation "
                        + "WHERE employeeID LIKE '%SS%' ORDER BY employeeID DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String previousID = rs.getString("employeeID").trim();
                    String tempID = getNextID(previousID, 2);
                    if (tempID == null) {
                        throw new IllegalArgumentException("Value cannot exceed 9999");
                    }
                    nextID = tempID;
                } else {
                    nextID = CONTRACT_ID_FORMAT;
                }
            }
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return nextID;
    }

    public String getNewEmployeeContractID(String CONTRACT_ID_FORMAT) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String nextID = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 1 contractID FROM EmployeeContract"
                        + " ORDER BY contractID DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String previousID = rs.getString("contractID").trim();
                    String tempID = getNextID(previousID, 2);
                    if (tempID == null) {
                        throw new IllegalArgumentException("Value cannot exceed 9999");
                    }
                    nextID = tempID;
                } else {
                    nextID = CONTRACT_ID_FORMAT;
                }
            }
        } catch (IllegalArgumentException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return nextID;
    }

    public boolean insertEmployeeContract(TemporaryContractDTO contract) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO EmployeeContract (contractId, employeeID, startDate, salary, allowance, "
                        + "pathImage, sizeImage, creatorID, approverID, isActive) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, contract.getContractID());
                stm.setString(2, contract.getCandidateID());
                java.sql.Date startDate = java.sql.Date.valueOf(contract.getStartDate());
                stm.setDate(3, startDate);
                stm.setFloat(4, contract.getSalary());
                stm.setFloat(5, contract.getAllowance());
                stm.setString(6, contract.getPathImage());
                stm.setInt(7, contract.getSizeImage());
                stm.setString(8, contract.getCreatorID());
                stm.setString(9, contract.getApproverID());
                stm.setInt(10, 1);

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at Insert: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insertEmployee(CandidateDTO candidate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO EmployeeInformation (employeeID, fullName, gender,dateOfBirth, phoneNumber, email"
                        + ", address, humanID, nationality,image, manageBy, isActive) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, candidate.getId());
                stm.setString(2, candidate.getFullName());
                stm.setString(3, candidate.getGender());
                java.sql.Date dateOfBirth = java.sql.Date.valueOf(candidate.getDateOfBrith());
                stm.setDate(4, dateOfBirth);
                stm.setString(5, candidate.getPhoneNumber());
                stm.setString(6, candidate.getEmail());
                stm.setString(7, candidate.getAddress());
                stm.setString(8, candidate.getHumanId());
                stm.setString(9, candidate.getNationality());
                stm.setString(10, candidate.getImage());
                stm.setString(11, candidate.getCreatorID());
                stm.setInt(12, 1);

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at Insert: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    private final String password = "123@123a"; 
    public boolean insertStaffToUserLogin(CandidateDTO candidate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        String userID = Helper.generateString(candidate.getFullName())+candidate.getId();
        userID = userID.toLowerCase();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO UserLogin (userID, password, isActive,"
                        + " roleID, employeeID) VALUES (?,?,1,'1',?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                stm.setString(3, candidate.getId());

                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at Insert: " + e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
