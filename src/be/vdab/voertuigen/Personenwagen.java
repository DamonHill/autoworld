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
import java.awt.Color;

/**
 *
 * @author arne.simons
 */
public class Personenwagen extends Voertuig {
    private Color kleur;

    public Personenwagen(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder, Mens... inzittenden) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, inzittenden);
        if (zitplaatsen > 8) {
            throw new MensException("Personenwagen kan maximaal 8 zitplaatsen hebben!");
        }
        setKleur(kleur);
    }

    public Color getKleur() {
        return kleur;
    }

    public final void setKleur(Color kleur) {
        this.kleur = kleur;
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + getZitplaatsen();
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE, Rijbewijs.C, Rijbewijs.CE, Rijbewijs.D, Rijbewijs.DE};
    }
    
}