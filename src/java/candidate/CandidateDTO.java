/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package candidate;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author flami
 */
public class CandidateDTO {

    private String id;
    private String fullName;
    private String gender;
    private LocalDate dateOfBrith;
    private String phoneNumber;
    private String email;
    private String address;
    private String humanId;
    private String nationality;
    private String notation;
    private String image;
    private String creatorID;
    private boolean isActive;

    public CandidateDTO(String id, String fullName, String gender, LocalDate dateOfBrith,
            String phoneNumber, String email, String address, String humanId, String nationality,
            String notation, String image, String creatorID, boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBrith = dateOfBrith;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.humanId = humanId;
        this.nationality = nationality;
        this.notation = notation;
        this.image = image;
        this.creatorID = creatorID;
        this.isActive = isActive;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CandidateDTO() {
        fullName = "";
        gender = "";
//        dateOfBrith = "";
        phoneNumber = "";
        email = "";
        address = "";
        humanId = "";
        nationality = "";
        notation = "";
        creatorID = "";
        isActive = true;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(LocalDate dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHumanId() {
        return humanId;
    }

    public void setHumanId(String humanId) {
        this.humanId = humanId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }
}
