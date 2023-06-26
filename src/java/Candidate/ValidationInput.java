/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Candidate;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.servlet.http.Part;

/**
 *
 * @author flami
 * This class to test a valid input from user
 */
public class ValidationInput {

    private String message = "";

    public String getMessage() {
        return message;
    }

    private boolean checkAlphabet(String string) {
        for (int i = 0; i < string.length(); i++) {
            char key = string.charAt(i);
            if (key > 'z' || (key < 'a' && key > 'Z') || key < 'A') {
                return false;
            }
        }
        return true;
    }

    private boolean checkNumberExist(String string) {
        for (int i = 0; i < string.length(); i++) {
            char key = string.charAt(i);
            if (key >= '0' && key <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean checkLatinAlphabet(String name) {
        String regex = "^[\\p{L} .'-]+$";
        return name != null && name.matches(regex);
    }

    boolean checkLengthName(String string) {
        if (string.length() > 50 || string.length() < 3) {
            return false;
        }
        return true;
    }

    public String getNormalizeString(String string) {
        string = string.trim();
        string = string.replaceAll("\\s+", " ");
        string = string.toLowerCase();

        if (string.length() != 0) {
            String[] temp = string.split(" ");
            string = "";
            for (int i = 0; i < temp.length; i++) {
                string += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
                if (i == temp.length - 1) {
                    break;
                }
                string += " ";
            }
            return string;
        }

        return null;
    }

    public boolean isFullName(String fullName) {
        fullName = getNormalizeString(fullName);
        if (fullName == null) {
            message = "Full name is empty!";
            return false;
        }
        if (!checkLatinAlphabet(fullName)) {
            message = "Full name must contain only alphabet or latin!";
            return false;
        }
        if (!checkLengthName(fullName)) {
            message = "The length of full name must be in [3,50]";
            return false;
        }
        return true;
    }

    private boolean isNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < '0' || string.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean checkLengthPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 ? true : false;
    }

    public boolean isPhoneNumber(String phoneNumber) {
        if (!isNumber(phoneNumber)) {
            message = "Phone number only contain numbers!";
            return false;
        }
        if (!checkLengthPhoneNumber(phoneNumber)) {
            message = "Phone number must have 10 degit!";
            return false;
        }
        return true;
    }

    private boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public boolean isEmail(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
//        ^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$
        if (!patternMatches(email, regexPattern)) {
            message = "Email is wrong format!";
            return false;
        }
        return true;
    }

    public boolean isAddress(String address) {
//        String regexPattern = "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)";
//        if (!patternMatches(address, regexPattern)) {
//            message = "Address is wrong format!";
//            return false;
//        }
        return true;
    }

    public boolean isHumanId(String humanId) {
        String regexPattern = "^[0-9]{9,12}$";
        if (!patternMatches(humanId, regexPattern)) {
            message = "Human Id is wrong format!";
            return false;
        }
        return true;
    }

    private boolean checkNation(String nation) {
        nation = getNormalizeString(nation);
        String[] validNationalities = {"Việt Nam", "American", "French", "Japanese"};
        if (nation.isEmpty() || nation == null
                || !Arrays.asList(validNationalities).contains(nation)) {
            return false;
        }
        return true;
    }

    public boolean isNationality(String nationality) {
        if (checkNumberExist(nationality)) {
            message = "Nationality only contain alphabet!";
            return false;
        }
//        if (!checkNation(nationality)) {
//            message = "Nationality is not in list nationallity!";
//            return false;
//        }
        return true;
    }

    public boolean isNotation(String nationality) {
        return true;
    }

    private static final String[] tailImgList = {"jpeg", "jpg", "png", "gif",
        "tiff", "psd", "pdf", "eps", "ai", "indd", "raw"};

    public boolean isImageFile(Part file) {

        String fileName = file.getSubmittedFileName().trim().toLowerCase();
        int dotDes = fileName.indexOf('.');
        while (fileName.indexOf('.', dotDes + 1) != -1) {
            dotDes = fileName.indexOf('.', dotDes + 1);
        }
        String tailFileName = fileName.substring(dotDes + 1);

        if (!Arrays.asList(tailImgList).contains(tailFileName)) {
            message = "Unsupported file format!\n"
                    + "Supported file formats: jpeg, jpg, png, gif, "
                    + "tiff, psd, pdf, eps, ai, indd, raw";
            return false;
        }
        return true;
    }

    public boolean isImageFiles(Part file) {

        String fileName = file.getSubmittedFileName().trim().toLowerCase();
        int numberOfFiles = 0;
        Collection<Part> parts = (Collection<Part>) file;
        for (Part part : parts) {
            if (part.getName().startsWith("file")) {
                numberOfFiles++;
            }
        }
        int dotDes = fileName.indexOf('.');
        while (fileName.indexOf('.', dotDes + 1) != -1) {
            dotDes = fileName.indexOf('.', dotDes + 1);
        }
        String tailFileName = fileName.substring(dotDes + 1);

        if (!Arrays.asList(tailImgList).contains(tailFileName)) {
            message = "Unsupported file format!\n"
                    + "Supported file formats: jpeg, jpg, png, gif, "
                    + "tiff, psd, pdf, eps, ai, indd, raw";
            return false;
        }
        return true;
    }
}
