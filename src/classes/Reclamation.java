/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;




public class Reclamation {
    private int idR;
    private int idU;
    private Date dateREC;
    private int typeRec;
    private int refObject;
    private String details;

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }



    public int getIdR() {
        return idR;
    }
   

    public Date getDateREC() {
        return dateREC;
    }

    public int getTypeRec() {
        return typeRec;
    }

    public int getRefObject() {
        return refObject;
    }

    public String getDetails() {
        return details;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public void setDateREC(Date dateREC) {
        this.dateREC = dateREC;
    }

    public void setTypeRec(int typeRec) {
        this.typeRec = typeRec;
    }

    public void setRefObject(int refObject) {
        this.refObject = refObject;
    }

    public void setDetails(String details) {
        this.details = details;
    }



    public Reclamation() {
    }

    public Reclamation(int idR, int idU, Date dateREC, int typeRec, int refObject, String details) {
        this.idR = idR;
        this.idU = idU;
        this.dateREC = dateREC;
        this.typeRec = typeRec;
        this.refObject = refObject;
        this.details = details;
    }

    public Reclamation(int idU, Date dateREC, int typeRec, int refObject, String details) {
        this.idU = idU;
        this.dateREC = dateREC;
        this.typeRec = typeRec;
        this.refObject = refObject;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idR=" + idR + ", idU=" + idU + ", dateREC=" + dateREC + ", typeRec=" + typeRec + ", refObject=" + refObject + ", details=" + details + '}';
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.idR != other.idR) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idR;
        return hash;
    }



    
 
   
    
    
}
