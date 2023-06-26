/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contract;

import Candidate.Candidate;

/**
 *
 * @author flami
 */
public class CandidateContract {
    private TemporaryContract tempCont;
    private Candidate candidate;

    public CandidateContract(TemporaryContract tempCont, Candidate candidate) {
        this.tempCont = tempCont;
        this.candidate = candidate;
    }
    
    public TemporaryContract getTempCont() {
        return tempCont;
    }

    public void setTempCont(TemporaryContract tempCont) {
        this.tempCont = tempCont;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    
}
