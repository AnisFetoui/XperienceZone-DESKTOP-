/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;





public class Traitement {
    private int idT;
    private int idU;
    private int idrec;
    private int refobj;
    private Date dateR;
    private int typeR;
    private String resume;
    private String stat ;
    


    public int getIdT() {
        return idT;
    }

    public int getIdrec() {
        return idrec;
    }

    public int getRefobj() {
        return refobj;
    }

    public Date getDateR() {
        return dateR;
    }

    public int getIdU() {
        return idU;
    }

    public int getTypeR() {
        return typeR;
    }

    public String getResume() {
        return resume;
    }

    public String getStat() {
        return stat;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public void setRefobj(int refobj) {
        this.refobj = refobj;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }



    public void setTypeR(int typeR) {
        this.typeR = typeR;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Traitement() {
    }

    public Traitement(int idT, int idU, int idrec, int refobj, Date dateR, int typeR, String resume, String stat) {
        this.idT = idT;
        this.idU = idU;
        this.idrec = idrec;
        this.refobj = refobj;
        this.dateR = dateR;
        this.typeR = typeR;
        this.resume = resume;
        this.stat = stat;
    }

    public Traitement(int idU, int idrec, int refobj, Date dateR, int typeR, String resume, String stat) {
        this.idU = idU;
        this.idrec = idrec;
        this.refobj = refobj;
        this.dateR = dateR;
        this.typeR = typeR;
        this.resume = resume;
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "Traitement{" + "idT=" + idT + ", idU=" + idU + ", idrec=" + idrec + ", refobj=" + refobj + ", dateR=" + dateR + ", typeR=" + typeR + ", resume=" + resume + ", stat=" + stat + '}';
    }








    
}
