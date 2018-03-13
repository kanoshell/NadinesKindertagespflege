package com.daheim.nadineskindertagespflege;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeSet;

public class Kind {
    private int uniqueId;
    private int alter;
    private int vertragslaufzeit;
    private boolean isActive;
    private String vorname;
    private String nachname;
    private GregorianCalendar geburtstag;
    private GregorianCalendar eintrittsdatum;
    private GregorianCalendar austrittsdatum;
    private TreeSet<String> allergien;
    private HashMap<String,String> adresse;
    //public TreeSet<HashMap<String,String>> erziehungsberechtigte;

    public Kind(Integer uniqueId,
                String vorname,
                String nachname,
                Integer alter,
                Integer vertragslaufzeit,
                boolean isActive,
                GregorianCalendar geburtstag,
                GregorianCalendar eintrittsdatum,
                GregorianCalendar austrittsdatum,
                TreeSet<String> allergien,
                HashMap<String,String> adresse){
        if (uniqueId==null||vorname==null||nachname==null){
            throw new NullPointerException("Kind MUSS uniqueId,Vorname,Nachname enthalten");
        }else{
            this.uniqueId=uniqueId;
            this.vorname=vorname;
            this.nachname=nachname;
        }
        this.alter=alter;
        this.vertragslaufzeit=vertragslaufzeit;
        this.isActive=isActive;
        this.geburtstag=geburtstag;
        this.eintrittsdatum=eintrittsdatum;
        this.austrittsdatum=austrittsdatum;
        this.allergien=allergien;
        this.adresse=adresse;
    }
    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getVertragslaufzeit() {
        return vertragslaufzeit;
    }

    public void setVertragslaufzeit(int vertragslaufzeit) {
        this.vertragslaufzeit = vertragslaufzeit;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public GregorianCalendar getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(GregorianCalendar geburtstag) {
        this.geburtstag = geburtstag;
    }

    public GregorianCalendar getEintrittsdatum() {
        return eintrittsdatum;
    }

    public void setEintrittsdatum(GregorianCalendar eintrittsdatum) {
        this.eintrittsdatum = eintrittsdatum;
    }

    public GregorianCalendar getAustrittsdatum() {
        return austrittsdatum;
    }

    public void setAustrittsdatum(GregorianCalendar austrittsdatum) {
        this.austrittsdatum = austrittsdatum;
    }

    public TreeSet<String> getAllergien() {
        return allergien;
    }

    public void setAllergien(TreeSet<String> allergien) {
        this.allergien = allergien;
    }

    public HashMap<String, String> getAdresse() {
        return adresse;
    }

    public void setAdresse(HashMap<String, String> adresse) {
        this.adresse = adresse;
    }
}
