/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author arne.simons
 */
public class Mens implements Comparable<Mens>, Serializable {
    private String naam;
    private Rijbewijs[] rijbewijs = new Rijbewijs[0]; // nodig voor init van persoon zonder rijbewijs

    public Mens(String naam) {
        setNaam(naam);
    }

    
    public Mens(String naam, Rijbewijs... rijbewijs) {
        setRijbewijs(rijbewijs);
        setNaam(naam);
    }

    public String getNaam() {
        return naam;
    }

    public final void setNaam(String naam) {
        if (naam != null && !naam.isEmpty()) {
            this.naam = naam;
        }
    }
    
    public final void setRijbewijs(Rijbewijs... rijbewijs) {
        Set<Rijbewijs> rijbewijzenSet = new TreeSet<>(Arrays.asList(rijbewijs));
        this.rijbewijs = new Rijbewijs[rijbewijzenSet.size()];
        int i = -1;
        for (Rijbewijs rb : rijbewijzenSet) {
            i++;
            this.rijbewijs[i] = rb;
        }
    }

    public Rijbewijs[] getRijbewijs() {
        return rijbewijs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.naam);
        hash = 67 * hash + Arrays.deepHashCode(this.rijbewijs);
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
        final Mens other = (Mens) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Arrays.deepEquals(this.rijbewijs, other.rijbewijs)) {
            return false;
        }
        return true;
    }
    
    @Override 
    public int compareTo(Mens m) { 
        if (m == null) {
            throw new NullPointerException();
        }
        else {
            if (this.equals(m)) {
                return 0;
            }
            else {
                return naam.compareTo(m.getNaam());
            }
        }
    }

    @Override
    public String toString() {
        if (rijbewijs == null || rijbewijs.length == 0) {
            return naam;
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<rijbewijs.length; i++) {
                if (i==rijbewijs.length-1) {
                    sb.append(rijbewijs[i].toString());
                }
                else {
                    sb.append(rijbewijs[i].toString() + ", ");
                }
            }
            return naam + "(" + sb + ")";
        }   
    }
        
}