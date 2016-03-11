/*
* @Autor Frank
* Dit is een oefening.
 */

package be.vdab.voertuigen.div;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author frank.roelants
 */
public class Nummerplaat implements Serializable, Comparable<Nummerplaat> {
    public static final long serialVersionUID = 1L;
    private final String plaat;

    Nummerplaat(String plaat) {
        this.plaat = plaat;
    }

    public String getPlaat() {
        return plaat;
    }

    @Override
    public int compareTo(Nummerplaat o) {
        if (o == null) {
            throw new NullPointerException();
        }
        else {
            if (this.equals(o)) {
                return 0;
            }
            else {
                return plaat.compareTo(o.getPlaat());
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.plaat);
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
        final Nummerplaat other = (Nummerplaat) obj;
        if (!Objects.equals(this.plaat, other.plaat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return plaat;
    }
    
}
