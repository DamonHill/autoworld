/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author arne.simons
 */
public class Boekentas implements Laadbaar, Serializable {
    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur, Volume laadvolume) {
        setKleur(kleur);
        setLaadvolume(laadvolume);
    }

    public Volume getLaadvolume() {
        return laadvolume;
    }
    
    public String getKleur() {
        return kleur;
    }

    public final void setLaadvolume(Volume laadvolume) {
        if (laadvolume == null) {
            throw new IllegalArgumentException("Het laadvolume moet verplicht worden ingevuld!");
        } 
        else {
            this.laadvolume = laadvolume;
        }
    }
    
    public final void setKleur(String kleur) {
        if (kleur == null || kleur.isEmpty()) {
            throw new IllegalArgumentException("De kleur moet verplicht worden ingevuld!");
        }
        else {
            this.kleur = kleur;
        }
    }

    @Override
    public String toString() {
        return "boekentas " + kleur + " " + laadvolume.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.laadvolume);
        hash = 73 * hash + Objects.hashCode(this.kleur);
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
        final Boekentas other = (Boekentas) obj;
        if (!Objects.equals(this.kleur, other.kleur)) {
            return false;
        }
        if (!Objects.equals(this.laadvolume, other.laadvolume)) {
            return false;
        }
        return true;
    }
    
    
}
