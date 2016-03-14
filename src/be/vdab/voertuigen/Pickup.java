/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;

/**
 *
 * @author arne.simons
 */
public class Pickup extends Personenwagen implements Laadbaar {
    private Volume laadvolume;

    public Pickup(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Volume laadvolume, Mens bestuurder, Mens... inzittenden) {
        super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, kleur, bestuurder, inzittenden);
        setLaadvolume(laadvolume);
    }

    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    @Override
    public final void setLaadvolume(Volume laadvolume) {
        this.laadvolume = laadvolume;
    }

    @Override
    public String toString() {
        return super.toString() + " " + laadvolume;
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
         return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE, Rijbewijs.C, Rijbewijs.CE, Rijbewijs.D, Rijbewijs.DE};
    }
}
