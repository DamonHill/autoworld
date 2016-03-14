/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author arne.simons
 */
public class Volume implements Comparable<Volume>, Serializable {
    public static final long serialVersionUID = 1L;
    private final int hoogte;
    private final int breedte;
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if (hoogte < 0 || breedte < 0 || diepte < 0) {
            throw new VolumeException("Het volume zal negatief zijn!");
        }
        else {
            this.hoogte = hoogte;
            this.breedte = breedte;
            this.diepte = diepte;
            this.maat = maat;
        }
    }

    public int getHoogte() {
        return hoogte;
    }

    public int getBreedte() {
        return breedte;
    }

    public int getDiepte() {
        return diepte;
    }
        
    public long getVolume() {
        return hoogte * breedte * diepte;
    }

    public Maat getMaat() {
        return maat;
    }

    @Override
    public int compareTo(Volume v) {
        if (v == null) {
            throw new NullPointerException();
        }
        else {
            long v1 = this.getVolume();
            v1 = convertToCm(v1, maat);
            long v2 = v.getVolume();
            v2 = convertToCm(v2, v.getMaat());
            if (v1 == v2) {
                return 0;
            }
            else {
                if (v1 > v2) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
        }
    }
    
    public long convertToCm(long volume, Maat maat) {
        if (maat == Maat.meter) {
            volume *= 1000000;
        }
        else {
            if (maat == Maat.decimeter) {
                volume *= 1000;
            }
        }
        return volume;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.hoogte;
        hash = 79 * hash + this.breedte;
        hash = 79 * hash + this.diepte;
        hash = 79 * hash + Objects.hashCode(this.maat);
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
        final Volume other = (Volume) obj;
        if (this.hoogte != other.hoogte) {
            return false;
        }
        if (this.breedte != other.breedte) {
            return false;
        }
        if (this.diepte != other.diepte) {
            return false;
        }
        if (this.maat != other.maat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return hoogte + "(h)x" + breedte + "(b)x" + diepte + "(d) " + maat;
    }
         
}
