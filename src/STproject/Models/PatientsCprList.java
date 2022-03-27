/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Models;

public class PatientsCprList {

    private String cprNumber;

    public PatientsCprList(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public PatientsCprList() {
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

}
