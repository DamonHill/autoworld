/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author arne.simons
 */
public class Datum implements Comparable <Datum>, Serializable {
    public static final long serialVersionUID = 1L;
    private final int dag;
    private final int maand;
    private final int jaar;

    public Datum(int dag, int maand, int jaar) throws DatumException {
        if (checkDatum(dag, maand, jaar)) {
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        }
        else {
            throw new DatumException("Ongeldige datum");
        }
    }

    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }
    
    private boolean checkDatum(int dag, int maand, int jaar) {
        if (dag < 1 || maand < 1 || jaar < 1583 || jaar > 4099) {
            return false;
        }
        try {
            LocalDate.of(jaar, maand, dag);
        }
        catch(DateTimeException e) {
            return false;
        }
        return true;
    }
    
    @Override 
    public int compareTo(Datum d) { 
        if (d == null) {
            throw new NullPointerException();
        }
        else {
            return this.toDate().compareTo(d.toDate());
        }
    }         

    @Override
    public String toString() {
        String sDag;
        String sMaand;
        if (dag < 10) {
            sDag = "0" + dag;
        }
        else {
            sDag = "" + dag;
        }
        if (maand < 10) {
            sMaand = "0" + maand;
        }
        else {
            sMaand = "" + maand;
            }
        return sDag + "/" + sMaand + "/" + jaar;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.dag;
        hash = 43 * hash + this.maand;
        hash = 43 * hash + this.jaar;
        return hash;
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
        final Datum other = (Datum) obj;
        if (this.dag != other.dag) {
            return false;
        }
        if (this.maand != other.maand) {
            return false;
        }
        if (this.jaar != other.jaar) {
            return false;
        }
        return true;
    }
    
    public Date toDate() {
        return new GregorianCalendar(jaar, maand-1, dag).getTime();
    }
    
}
