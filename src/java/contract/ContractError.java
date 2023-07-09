/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contract;

import candidate.CandidateError;

/**
 *
 * @author flami
 */
public class ContractError {

    private String salaryError;
    private String notationError;
    private String otherError;
    private CandidateError candidateErr;

    public ContractError(String otherError, String salaryError, String notationError) {
        this.salaryError = salaryError;
        this.notationError = notationError;
        this.otherError = otherError;
    }

    public CandidateError getCandidateErr() {
        return candidateErr;
    }

    public void setCandidateErr(CandidateError candidateErr) {
        this.candidateErr = candidateErr;
    }

    public ContractError(String otherError, String salaryError, String notationError, CandidateError candidateErr) {
        this.salaryError = salaryError;
        this.notationError = notationError;
        this.otherError = otherError;
        this.candidateErr = candidateErr;
    }

    public ContractError() {
        salaryError = "";
        notationError = "";
        otherError = "";
    }

    public String getOtherError() {
        return otherError;
    }

    public void setOtherError(String otherError) {
        this.otherError = otherError;
    }

    public String getSalaryError() {
        return salaryError;
    }

    public void setSalaryError(String salaryError) {
        this.salaryError = salaryError;
    }

    public String getNotationError() {
        return notationError;
    }

    public void setNotationError(String notationError) {
        this.notationError = notationError;
    }

}
