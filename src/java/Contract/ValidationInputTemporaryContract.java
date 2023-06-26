/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contract;

/**
 *
 * @author flami
 * This class handle input from user
 */
public class ValidationInputTemporaryContract {
    private String messageError;

    private void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

    public boolean isNumber(String number) {
        try {
            Float.parseFloat(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isSalary(String stringSalary) {
        if (!isNumber(stringSalary)) {
            setMessageError("Just input a float!");
            return false;
        }
        float number = Float.parseFloat(stringSalary);
        if (number <= 0) {
            setMessageError("Salary is a positive number!");
            return false;
        }
        return true;
    }
    
    public boolean isDescription(String notation){
        if(notation.length()>150){
            setMessageError("Can not enter more than 150 words!");
            return false;            
        }
        return true;
    }    
}
