/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contract;

import Candidate.CandidateDTO;

/**
 *
 * @author flami
 */
public class CandidateContractDTO {
    private TemporaryContractDTO tempCont;
    private CandidateDTO candidate;

    public CandidateContractDTO(TemporaryContractDTO tempCont, CandidateDTO candidate) {
        this.tempCont = tempCont;
        this.candidate = candidate;
    }
    
    public TemporaryContractDTO getTempCont() {
        return tempCont;
    }

    public void setTempCont(TemporaryContractDTO tempCont) {
        this.tempCont = tempCont;
    }

    public CandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }
    
}
