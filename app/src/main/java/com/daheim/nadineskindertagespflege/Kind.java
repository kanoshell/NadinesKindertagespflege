package com.daheim.nadineskindertagespflege;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeSet;

public class Kind {
    public int uniqueId;
    public int alter;
    public int vertragslaufzeit;
    public boolean isActive;
    public String vorname;
    public String nachname;
    public GregorianCalendar geburtstag;
    public GregorianCalendar eintrittsdatum;
    public GregorianCalendar austrittsdatum;
    public TreeSet<String> allergien;
    //public TreeSet<HashMap<String,String>> erziehungsberechtigte;
    public HashMap<String,String> adresse;

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
        }


    }
}
