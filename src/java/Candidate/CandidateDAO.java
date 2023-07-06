/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Candidate;

import Candidate.CandidateDTO;
import sample.Utils.DBUtils;
import Contract.TemporaryContractDTO;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author flami
 */
public class CandidateDAO {

    public boolean insertCandidate(CandidateDTO candidate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Candidate "
                        + "(candidateID, fullName, gender,DateOfBirth, phoneNumber, email"
                        + ", address, humanID, nationality, notation,image, creatorID, isActive)"
                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                stm.setString(10, candidate.getNotation());
                stm.setString(11, candidate.getImage());
                stm.setString(12, candidate.getCreatorID());
                stm.setBoolean(13, candidate.isIsActive());

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

    public TemporaryContractDTO getTemporaryContractByCandidateID(String candidateID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        TemporaryContractDTO tempContract = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT status FROM TemporaryContract "
                        + "WHERE candidateID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, candidateID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String statusContract = rs.getString("status");
                    tempContract = new TemporaryContractDTO();
                    tempContract.setStatus(statusContract);
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

    public boolean updateCandidate(CandidateDTO candidate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE Candidate SET fullName = ?, gender = ?,"
                        + "DateOfBirth = ?, phoneNumber = ?, email = ?, address = ?, "
                        + "humanID = ?, nationality = ?, notation = ?,image = ?, "
                        + "isActive = ? WHERE candidateID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, candidate.getFullName());
                stm.setString(2, candidate.getGender());
                stm.setString(3, String.valueOf(candidate.getDateOfBrith()));
                stm.setString(4, candidate.getPhoneNumber());
                stm.setString(5, candidate.getEmail());
                stm.setString(6, candidate.getAddress());
                stm.setString(7, candidate.getHumanId());
                stm.setString(8, candidate.getNationality());
                stm.setString(9, candidate.getNotation());
                stm.setString(10, candidate.getImage());
                int isActive = (candidate.isIsActive()==true)?1:0;
                stm.setInt(11, isActive);
                stm.setString(12, candidate.getId());

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
                        + " phoneNumber, email, address, humanID, nationality, "
                        + "notation,image, creatorID, isActive "
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

    public List<CandidateDTO> getListCandidate(String search, String creatorID) throws SQLException {
        List<CandidateDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT candidateID, fullName, gender,DateOfBirth,"
                        + " phoneNumber, email, address, humanID, nationality, notation,image, creatorID "
                        + "FROM Candidate "
                        + "WHERE fullName like ? AND isActive=? AND CreatorID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setString(2, "true");
                stm.setString(3, creatorID);
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
                    Boolean isActive = true;

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

    private final static String CANDIDATE_ID_FORMAT = "C1111";

    public String getNewIDCandidate() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String nextID = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP 1 candidateID FROM Candidate "
                        + "ORDER BY candidateID DESC";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String previousID = rs.getString("candidateID").trim();
                    String tempID = getNextID(previousID, 1);
                    if (tempID == null) {
                        throw new IllegalArgumentException("Value cannot exceed 9999");
                    }
                    nextID = tempID;
                } else {
                    nextID = CANDIDATE_ID_FORMAT;
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
}
