/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package candidate;

/**
 *
 * @author flami
 */
public class CandidateError {

    private String fullNameError;
    private String phoneNumberError;
    private String emailError;
    private String addressError;
    private String humanIdEror;
    private String nationalityError;
    private String notationError;
    private String imageError;
    private String messageError;

    public CandidateError(String fullNameError, String phoneNumberError, 
            String emailError, String addressError, String humanIdEror, 
            String nationalityError, String notationError, String imageError,
            String messageError) {
        this.fullNameError = fullNameError;
        this.phoneNumberError = phoneNumberError;
        this.emailError = emailError;
        this.addressError = addressError;
        this.humanIdEror = humanIdEror;
        this.nationalityError = nationalityError;
        this.notationError = notationError;
        this.imageError = imageError;
        this.messageError = messageError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public CandidateError() {
        fullNameError = "";
        phoneNumberError = "";
        emailError = "";
        addressError = "";
        humanIdEror = "";
        nationalityError = "";
        notationError = "";
        imageError="";
        messageError = "";
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getHumanIdEror() {
        return humanIdEror;
    }

    public void setHumanIdEror(String humanIdEror) {
        this.humanIdEror = humanIdEror;
    }

    public String getNationalityError() {
        return nationalityError;
    }

    public void setNationalityError(String nationalityError) {
        this.nationalityError = nationalityError;
    }

    public String getNotationError() {
        return notationError;
    }

    public void setNotationError(String notationError) {
        this.notationError = notationError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
