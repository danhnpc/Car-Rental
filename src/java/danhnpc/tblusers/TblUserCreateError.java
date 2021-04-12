/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhnpc.tblusers;

import java.io.Serializable;

/**
 *
 * @author visug
 */
public class TblUserCreateError implements Serializable{
    private String emailErr;
    private String passwordErr;
    private String confirmErr;
    private String phoneErr;
    private String nameErr;
    private String addressErr;
    private String emailDuplicateErr;

    public TblUserCreateError() {
    }

    public TblUserCreateError(String emailErr, String passwordErr, String confirmErr, String phoneErr, String nameErr, String addressErr, String emailDuplicateErr) {
        this.emailErr = emailErr;
        this.passwordErr = passwordErr;
        this.confirmErr = confirmErr;
        this.phoneErr = phoneErr;
        this.nameErr = nameErr;
        this.addressErr = addressErr;
        this.emailDuplicateErr = emailDuplicateErr;
    }

    

    /**
     * @return the emailErr
     */
    public String getEmailErr() {
        return emailErr;
    }

    /**
     * @param emailErr the emailErr to set
     */
    public void setEmailErr(String emailErr) {
        this.emailErr = emailErr;
    }

    /**
     * @return the passwordErr
     */
    public String getPasswordErr() {
        return passwordErr;
    }

    /**
     * @param passwordErr the passwordErr to set
     */
    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

    /**
     * @return the phoneErr
     */
    public String getPhoneErr() {
        return phoneErr;
    }

    /**
     * @param phoneErr the phoneErr to set
     */
    public void setPhoneErr(String phoneErr) {
        this.phoneErr = phoneErr;
    }

    /**
     * @return the nameErr
     */
    public String getNameErr() {
        return nameErr;
    }

    /**
     * @param nameErr the nameErr to set
     */
    public void setNameErr(String nameErr) {
        this.nameErr = nameErr;
    }

    /**
     * @return the addressErr
     */
    public String getAddressErr() {
        return addressErr;
    }

    /**
     * @param addressErr the addressErr to set
     */
    public void setAddressErr(String addressErr) {
        this.addressErr = addressErr;
    }

    /**
     * @return the emailDuplicateErr
     */
    public String getEmailDuplicateErr() {
        return emailDuplicateErr;
    }

    /**
     * @param emailDuplicateErr the emailDuplicateErr to set
     */
    public void setEmailDuplicateErr(String emailDuplicateErr) {
        this.emailDuplicateErr = emailDuplicateErr;
    }

    /**
     * @return the confirmErr
     */
    public String getConfirmErr() {
        return confirmErr;
    }

    /**
     * @param confirmErr the confirmErr to set
     */
    public void setConfirmErr(String confirmErr) {
        this.confirmErr = confirmErr;
    }
    
}
