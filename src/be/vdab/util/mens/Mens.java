/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author arne.simons
 */
public class Mens implements Comparable<Mens> {
    private String naam;
    private Rijbewijs[] rijbewijs;

    public Mens(String naam) {
        setNaam(naam);
    }

    
    public Mens(String naam, Rijbewijs... rijbewijs) {
        this.rijbewijs = rijbewijs;
        for (int i=0; i<rijbewijs.length; i++) {
            setRijbewijs(rijbewijs[i],i);
        }
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
    
    public final void setRijbewijs(Rijbewijs rb, int index) {
        if (rb.ordinal() >= 0 && !Arrays.asList(this.rijbewijs).contains(rb)) {
            this.rijbewijs[index] = rb;
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
        if (rijbewijs.length == 0) {
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