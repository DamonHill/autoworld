/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author arne.simons
 */
public abstract class Voertuig implements Comparable<Voertuig>, Serializable {
    public static final long serialVersionUID = 1L;
    private final Nummerplaat nummerplaat;
    private String merk;
    private Datum datumEersteIngebruikname;
    private int aankoopprijs;
    private final int MAX_ZITPLAATSEN;
    private LinkedList<Mens> inzittenden;
    private Mens bestuurder;

    public Voertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens... inzittenden) {
        this.nummerplaat = DIV.INSTANCE.getNummerplaat();
        setMerk(merk);
        setDatumEersteIngebruikname(datumEersteIngebruikname);
        setAankoopprijs(aankoopprijs);
        if (zitplaatsen <= 0) {
            throw new MensException("Aantal inzittenden is nul!");
        }
        else {
            MAX_ZITPLAATSEN = zitplaatsen;
        }
        setInzittenden(bestuurder, inzittenden);
        setBestuurder(bestuurder);
    }

    public final void setBestuurder(Mens bestuurder) {     
        if (heeftGeldigRijbewijs(bestuurder)) {
            if (inzittenden.contains(bestuurder)) {
                inzittenden.remove(bestuurder);
                inzittenden.addFirst(bestuurder);
                this.bestuurder = bestuurder;
            }
            else {
                if (heeftGenoegZitplaatsen(inzittenden.size()+1)) {
                    inzittenden.addFirst(bestuurder);
                    this.bestuurder = bestuurder;
                } 
                else {
                    throw new MensException("Te weinig plaats in de auto!");
                }
            }
        }
        else {
            throw new MensException("Bestuurder heeft geen geldig rijbewijs!");
        }
    }
    
    public final void setDatumEersteIngebruikname(Datum datumEersteIngebruikname) {
        this.datumEersteIngebruikname = datumEersteIngebruikname;
    }

    public final void setAankoopprijs(int aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public final void setInzittenden(Mens bestuurder, Mens... inzittenden) {
        this.inzittenden = new LinkedList<>();
        this.inzittenden.addFirst(bestuurder);
        if (inzittenden != null) {
            for (int i=0; i<inzittenden.length; i++) {
                addIngezetene(inzittenden[i]);
            }   
        } 
    }

    public final void setMerk(String merk) {
        if (merk != null && !merk.isEmpty()) {
            this.merk = merk;
        }
    }
    
    public void addIngezetene(Mens m) {
        if (!isIngezetene(m)) {
            if (heeftGenoegZitplaatsen(inzittenden.size()+1)) {
                inzittenden.add(m);
            }
            else {
                throw new MensException("Te weinig plaats in de auto!");
            }
        }
    }
    
    public boolean isIngezetene(Mens m) {
        if (inzittenden.contains(m)) {
            return true;
        }
        return false;
    }

    public LinkedList getIngezeteneExclusiefBestuurder() {
        LinkedList<Mens> zonderBestuurder = (LinkedList<Mens>)inzittenden.clone();
        zonderBestuurder.removeFirst();
        return sorteerMensOpNaam(zonderBestuurder);
    }
    
    public LinkedList getIngezetenen() {
        LinkedList<Mens> metBestuurder = (LinkedList<Mens>)inzittenden.clone();
        return sorteerMensOpNaam(metBestuurder);
    }
    
    public LinkedList<Mens> sorteerMensOpNaam(LinkedList<Mens> mensen) {
        Collections.sort(mensen, (Mens m1, Mens m2) -> m1.getNaam().compareTo(m2.getNaam()));
        return mensen;
    }
    
        
    public boolean heeftGeldigRijbewijs(Mens bestuurder) {
        Set<Rijbewijs> rbSetHave = new TreeSet<>(Arrays.asList(bestuurder.getRijbewijs()));
        Set<Rijbewijs> rbSetAllowed = new TreeSet<>(Arrays.asList(this.getToegestaneRijbewijzen()));
        if (Collections.disjoint(rbSetHave, rbSetAllowed)) {
           return false;
        }
        return true;
    }
    
    public boolean heeftGenoegZitplaatsen(int aantalInzittenden) {
        if (MAX_ZITPLAATSEN < aantalInzittenden) {
            return false;
        }
        return true;
    }

    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }

    public String getMerk() {
        return merk;
    }

    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikname;
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }

    public Mens getBestuurder() {
        return bestuurder;
    }
    
    public static MerkComparator getMerkComparator() {
        return new MerkComparator();
    }
    
    public static AankoopprijsComparator getAankoopprijsComparator() {
        return new AankoopprijsComparator();
    }
    
    protected abstract Rijbewijs[] getToegestaneRijbewijzen(); 
        
    protected int getZitplaatsen() {
        return MAX_ZITPLAATSEN;
    }

    protected int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nummerplaat);
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
        final Voertuig other = (Voertuig) obj;
        if (!Objects.equals(this.nummerplaat, other.nummerplaat)) {
            return false;
        }
        return true;
    }
    
    @Override 
    public int compareTo(Voertuig v) { 
        if (v == null) {
            throw new NullPointerException();
        }
        else {
            return this.nummerplaat.compareTo(v.getNummerplaat());
        }
    }         

    @Override
    public String toString() {
        String passagier = "";
        if (inzittenden.size() > 1) {
            for (int i=1; i<inzittenden.size(); i++) {
                passagier += inzittenden.get(i).getNaam() + ", ";
            }
            passagier = passagier.substring(0,(passagier.length()-2));
            passagier = String.format(" [%s]", passagier);
        }
        return String.format("%s %s %s %s %s%s", nummerplaat, merk, datumEersteIngebruikname.toString(), aankoopprijs, inzittenden.getFirst(), passagier);
    }
    
    static class MerkComparator implements Comparator<Voertuig>, Serializable {
        @Override
        public int compare(Voertuig v1, Voertuig v2) {
            return v1.getMerk().compareTo(v2.getMerk());
        }
    }
    
    static class AankoopprijsComparator implements Comparator<Voertuig>, Serializable {
        @Override
        public int compare(Voertuig v1, Voertuig v2) {
            if (v1.getAankoopprijs() == v2.getAankoopprijs()) {
                return 0;
            }
            if (v1.getAankoopprijs() > v2.getAankoopprijs()) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
