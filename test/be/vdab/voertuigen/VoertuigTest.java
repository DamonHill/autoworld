/*
* @Autor Frank
* Dit is een oefening.
 */
package be.vdab.voertuigen;

import be.vdab.util.mens.Mens;
import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.mens.Rijbewijs;
import static be.vdab.voertuigen.MensFactorInTest.BESTUURDER_BBECCE;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author frank.roelants
 */
public class VoertuigTest implements Serializable, MensFactorInTest {

    private final Datum datum;
    private final Datum datum2;
    private final String datumString;
    private static final int AANTAL_INZITTENDEN = 3;
    private static final int MAX_ZITPLAATSEN = 3; /// zelf toegevoegd

    public VoertuigTest() throws DatumException {
        datum = new Datum(1, 2, 3456);
        datumString = "01/02/3456";
        datum2 = new Datum(1, 2, 2134);
    }

    private class TestVoertuig extends Voertuig {
        public TestVoertuig(String merk, Datum datumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens... ingezetenen) {
            super(merk, datumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, ingezetenen);
        }

        @Override
        protected Rijbewijs[] getToegestaneRijbewijzen() {
            return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
        }
        @Override
        protected int getMAX_ZITPLAATSEN() {            /// zelf toegevoegd
            return MAX_ZITPLAATSEN;
        }
    }

    
    @Test
    public void test_not_null() {
        assertNotNull(new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE));
    }

    @Test
    public void test_getNummerplaat() {
        assertNotNull(new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE).getNummerplaat());
    }

    @Test
    public void test_getMerk() {
        assertEquals(new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE).getMerk(), "a");
    }

    @Test
    public void test_getDatum() {
        assertEquals(new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE).getDatumEersteIngebruikname(), datum);
    }

    @Test
    public void test_getAankoopprijs() {
        assertEquals(new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE).getAankoopprijs(), 100);
    }

    @Test
    public void test_setMerk() {
        TestVoertuig tv = new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        tv.setMerk("b");
        assertFalse(tv.getMerk().equals("a"));
        assertTrue(tv.getMerk().equals("b"));
    }

    @Test
    public void test_setDatum() throws DatumException {
        TestVoertuig tv = new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        tv.setDatumEersteIngebruikname(datum2);
        assertFalse(tv.getDatumEersteIngebruikname().equals(datum));
        assertTrue(tv.getDatumEersteIngebruikname().equals(datum2));
    }

    @Test
    public void test_setAankoopprijs() {
        TestVoertuig tv = new TestVoertuig("a", datum, 100, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        tv.setAankoopprijs(200);
        assertFalse(tv.getAankoopprijs() == 100);
        assertTrue(tv.getAankoopprijs() == 200);
    }

    @Test
    public void test_equals_zichzelf() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertTrue(v1.equals(v1));
    }

    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void test_not_equals_null() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertFalse(v1.equals(null));
    }

    @Test
    public void test_equals_Voertuig() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
    }

    @Test
    public void test_not_equals_Voertuig() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("otto", datum2, 14300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
        assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
    }

    @Test
    public void test_equals_Symmetrie() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
        assertEquals(v2.equals(v1), v2.getNummerplaat().equals(v1.getNummerplaat()));
    }

    @Test
    public void test_equals_trasitief() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v3 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertEquals(v1.equals(v2), v1.getNummerplaat().equals(v2.getNummerplaat()));
        assertEquals(v2.equals(v3), v2.getNummerplaat().equals(v3.getNummerplaat()));
        assertEquals(v1.equals(v3), v1.getNummerplaat().equals(v3.getNummerplaat()));
    }

    @Test
    public void test_toString() {
        TestVoertuig tv = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        String nummerplaatString = tv.getNummerplaat().getPlaat();
        String merkString = tv.getMerk();
        String aankoopprijsString = Integer.toString(tv.getAankoopprijs());
        String toString = String.format("%s %s %s %s %s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)");
        assertEquals(toString, tv.toString());
    }

    @Test
    public void test_toString_Met_Een_Inzittende() {
        TestVoertuig tv = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE, INGEZETENE_A);
        String nummerplaatString = tv.getNummerplaat().getPlaat();
        String merkString = tv.getMerk();
        String aankoopprijsString = Integer.toString(tv.getAankoopprijs());
        String toString = String.format("%s %s %s %s %s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)" + " " + "[Anita]");
        assertEquals(toString, tv.toString());
    }

    @Test
    public void test_toString_Met_Twee_Inzittende() {
        TestVoertuig tv = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE, INGEZETENE_A, INGEZETENE_B);
        String nummerplaatString = tv.getNummerplaat().getPlaat();
        String merkString = tv.getMerk();
        String aankoopprijsString = Integer.toString(tv.getAankoopprijs());
        String toString = String.format("%s %s %s %s %s", nummerplaatString, merkString, datumString, aankoopprijsString, "Ammelie(B, B+E, C, C+E)" + " " + "[Anita, Bert]");
        assertEquals(toString, tv.toString());
    }

    @Test
    public void test_hashCode_equals() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertEquals((v1.hashCode() == v2.hashCode()), v1.equals(v2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_0_Zitplaatsen() {
        assertNotNull(new TestVoertuig("a", datum, 18300, 0, BESTUURDER_BBECCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Min_1_Zitplaatsen() {
        assertNotNull(new TestVoertuig("a", datum, 18300, -1, BESTUURDER_BBECCE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_MININT_Zitplaatsen() {
        assertNotNull(new TestVoertuig("a", datum, 18300, Integer.MIN_VALUE, BESTUURDER_BBECCE));
    }

    @Test
    public void test_compareTo() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("otto", datum2, 14300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertEquals(v1.compareTo(v2) < 0, v1.getNummerplaat().compareTo(v2.getNummerplaat()) < 0);
        assertEquals(v1.compareTo(v1) == 0, v1.getNummerplaat().compareTo(v1.getNummerplaat()) == 0);
        assertEquals(v2.compareTo(v1) > 0, v2.getNummerplaat().compareTo(v1.getNummerplaat()) > 0);
    }

    @Test
    public void test_MerkComparator() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("bus", datum2, 14300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertNotNull(TestVoertuig.getMerkComparator());
        assertEquals(TestVoertuig.getMerkComparator().compare(v1, v2) < 0, v1.getMerk().compareTo(v2.getMerk()) < 0);
        assertEquals(TestVoertuig.getMerkComparator().compare(v1, v1) == 0, v1.getMerk().compareTo(v1.getMerk()) == 0);
        assertEquals(TestVoertuig.getMerkComparator().compare(v2, v1) > 0, v2.getMerk().compareTo(v1.getMerk()) > 0);
    }

    @Test
    public void test_AankoopComparator() throws DatumException {
        TestVoertuig v1 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        TestVoertuig v2 = new TestVoertuig("bus", datum2, 14300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        assertNotNull(TestVoertuig.getAankoopprijsComparator());
        assertEquals(TestVoertuig.getAankoopprijsComparator().compare(v1, v2) < 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v2.getAankoopprijs())) < 0);
        assertEquals(TestVoertuig.getAankoopprijsComparator().compare(v1, v1) == 0, new Integer(v1.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) == 0);
        assertEquals(TestVoertuig.getAankoopprijsComparator().compare(v2, v1) > 0, new Integer(v2.getAankoopprijs()).compareTo(new Integer(v1.getAankoopprijs())) > 0);
    }

    @Test
    public void test_Voertuig_is_it_Serializable() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Voertuig tv = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBECCE); 
        oos.writeObject(tv);
        oos.close();
        assertTrue(out.toByteArray().length > 0);
    }

    @Test
    public void test_Voertuig_Serializable_and_equals_to_the_original() throws IOException, ClassNotFoundException {
        final String MERK = "auto";
        final int PRIJS = 2400;
        final Voertuig origineel = new TestVoertuig(MERK, datum, PRIJS, AANTAL_INZITTENDEN, BESTUURDER_BBECCE);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(origineel);
        oos.close();
        byte[] pickled = out.toByteArray();
        InputStream in = new ByteArrayInputStream(pickled);
        ObjectInputStream ois = new ObjectInputStream(in);
        Object o = ois.readObject();
        Voertuig copy = (Voertuig) o;
        assertEquals(origineel, copy);
        assertTrue(copy.getMerk().equals(MERK));
        assertTrue(copy.getAankoopprijs() == PRIJS);
        assertEquals(datum, copy.getDatumEersteIngebruikname());
        assertEquals(BESTUURDER_BBECCE, copy.getBestuurder());
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_constructor_zonder_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:---
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, INGEZETENE_A);
    }

    @Test
    public void test_constructor_met_correct_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:B
        //VOERTUIG B,BE//bestuurder:BE
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        Voertuig voertui2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BE);
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_constructor_met_incorrect_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:A
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_A);
    }

    @Test
    public void test_constructor_met_Meerdere_correcte_Rijbewijzen() {
        //VOERTUIG B,BE//bestuurder:B,BE
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BBE);
    }

    @Test
    public void test_constructor_met_Slechts_een_correct_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:A,B
        //VOERTUIG B,BE//bestuurder:B,C
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_AB);
        Voertuig voertui2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BC);
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_setBestuurder_zonder_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:---
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(INGEZETENE_A);
    }

        @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_setBestuurder_wissel_fout_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:---
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, BESTUURDER_A);
        voertuig.setBestuurder(BESTUURDER_A);
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_setBestuurder_wissel_zonder_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:---
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.setBestuurder(INGEZETENE_A);
    }    
    @Test
    public void test_setBestuurder_met_correct_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:B
        //VOERTUIG B,BE//bestuurder:BE
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_BE);
        Voertuig voertui2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_BE);
        voertui2.setBestuurder(BESTUURDER_B);
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_setBestuurder_met_incorrect_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:A
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_A);
    }

    @Test
    public void test_setBestuurder_met_Meerdere_correcte_Rijbewijzen() {
        //VOERTUIG B,BE//bestuurder:B,BE
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_BBE);
    }

    @Test
    public void test_setBestuurder_met_Slechts_een_correct_Rijbewijs() {
        //VOERTUIG B,BE//bestuurder:A,B
        //VOERTUIG B,BE//bestuurder:B,C
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_AB);
        Voertuig voertui2 = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertui2.setBestuurder(BESTUURDER_BC);
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_constructor_teveel_ingezetenen() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_C, INGEZETENE_D);

    }

    @Test
    public void test_constructor_niet_teveel_ingezetenen() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, BESTUURDER_B, INGEZETENE_C, INGEZETENE_D);

    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_setBestuurder_nieuweBestuurder_teveel_ingezetenen() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_C);
        voertuig.setBestuurder(BESTUURDER_BE);
    }

    @Test
    public void test_setBestuurder_nieuweBestuurder_niet_teveel_ingezetenen() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.setBestuurder(BESTUURDER_BE);
    }

    @Test
    public void test_setBestuurder_wissel_Bestuurder_met_ingezetenen() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, BESTUURDER_BBE, INGEZETENE_C);
        voertuig.setBestuurder(BESTUURDER_BBE);
    }

    @Test
    public void test_getBestuurder() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        assertEquals(BESTUURDER_B, voertuig.getBestuurder());
    }

    @Test
    public void test_getBestuurder_nieuwe_bestuurder() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_BE);
        assertEquals(BESTUURDER_BE, voertuig.getBestuurder());
    }

    @Test
    public void test_getBestuurder_bestaande_bestuurder() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_BE);
        voertuig.setBestuurder(BESTUURDER_B);
        assertEquals(BESTUURDER_B, voertuig.getBestuurder());
    }

    @Test
    public void test_getBestuurder_huidige_bestuurder() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B);
        voertuig.setBestuurder(BESTUURDER_B);
        assertEquals(BESTUURDER_B, voertuig.getBestuurder());
    }

    @Test
    public void test_addIngezetene_voldoende_plaats() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.addIngezetene(INGEZETENE_B);
    }

    @Test(expected = be.vdab.util.mens.MensException.class)
    public void test_addIngezetene_onvoldoende_plaats() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
        voertuig.addIngezetene(INGEZETENE_C);
    }

    @Test
    public void test_addIngezetene_reeds_ingezetene_voldoende_plaats() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.addIngezetene(INGEZETENE_A);
    }

    @Test
    public void test_addIngezetene_bestuurder_voldoende_plaats() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.addIngezetene(BESTUURDER_B);
    }

    @Test
    public void test_addIngezetene_reeds_ingezetene_volzet_voertuig() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
        voertuig.addIngezetene(INGEZETENE_A);
    }

    @Test//
    public void test_addIngezetene_bestuurder_volzet_voertuig() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
        voertuig.addIngezetene(BESTUURDER_B);
    }

    @Test
    public void test_isIngezetene_bestuurder_na_constructor() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
        assertTrue(voertuig.isIngezetene(BESTUURDER_B));
    }

    @Test
    public void test_isIngezetene_ingezetene_na_constructor() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
        assertTrue(voertuig.isIngezetene(INGEZETENE_A));
    }

    @Test
    public void test_isIngezetene_na_addIngezetene_nieuwe_inzittende() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.addIngezetene(INGEZETENE_B);
        assertTrue(voertuig.isIngezetene(INGEZETENE_B));
    }

    @Test
    public void test_isIngezetene_na_addIngezetene_bestaande_inzittende() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.addIngezetene(INGEZETENE_A);
        assertTrue(voertuig.isIngezetene(INGEZETENE_A));
    }

    @Test
    public void test_isIngezetene_na_setBestuurder_nieuwe_bestuurder() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
        voertuig.setBestuurder(BESTUURDER_BBE);
        assertTrue(voertuig.isIngezetene(BESTUURDER_BBE));
        assertTrue(voertuig.isIngezetene(BESTUURDER_B));
    }

    @Test
    public void test_isIngezetene_na_setBestuurder_bestaande_inzittende() {
        Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, BESTUURDER_BBE);
        voertuig.setBestuurder(BESTUURDER_BBE);
        assertTrue(voertuig.isIngezetene(BESTUURDER_BBE));
        assertTrue(voertuig.isIngezetene(BESTUURDER_B));
    }

    @Test
    public void getIngezetenen_na_constructor() {
        for (int i = 0; i < 65536; i++) {
            Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
            Mens[] actual = new Mens[voertuig.getIngezetenen().size()];
            Mens[] expected = new Mens[]{INGEZETENE_A, BESTUURDER_B, INGEZETENE_B};
            assertArrayEquals(expected, voertuig.getIngezetenen().toArray(actual));
        }
    }

    @Test
    public void getIngezetenen_na_addInzittende() {
        for (int i = 0; i < 65536; i++) {
            Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
            voertuig.addIngezetene(INGEZETENE_B);
            Mens[] actual = new Mens[voertuig.getIngezetenen().size()];
            Mens[] expected = new Mens[]{INGEZETENE_A, BESTUURDER_B, INGEZETENE_B};
            assertArrayEquals(expected, voertuig.getIngezetenen().toArray(actual));
        }
    }

    @Test
    public void getIngezetenen_na_setBestuurder() {
        for (int i = 0; i < 65536; i++) {
            Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
            voertuig.setBestuurder(BESTUURDER_BBE);
            Mens[] actual = new Mens[voertuig.getIngezetenen().size()];
            voertuig.getIngezetenen().toArray(actual);
            Mens[] expected = new Mens[]{INGEZETENE_A, BESTUURDER_BBE, BESTUURDER_B};
            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void getIngezetenenExclusiefBestuurder_na_constructor() {
        for (int i = 0; i < 65536; i++) {
            Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A, INGEZETENE_B);
            Mens[] actual = new Mens[voertuig.getIngezeteneExclusiefBestuurder().size()];
            Mens[] expected = new Mens[]{INGEZETENE_A, INGEZETENE_B};
            assertArrayEquals(expected, voertuig.getIngezeteneExclusiefBestuurder().toArray(actual));
        }
    }

    @Test
    public void getIngezetenenExclusiefBestuurder_na_addInzittende() {
        for (int i = 0; i < 65536; i++) {
            Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
            voertuig.addIngezetene(INGEZETENE_B);
            Mens[] actual = new Mens[voertuig.getIngezeteneExclusiefBestuurder().size()];
            Mens[] expected = new Mens[]{INGEZETENE_A, INGEZETENE_B};
            assertArrayEquals(expected, voertuig.getIngezeteneExclusiefBestuurder().toArray(actual));
        }
    }

    @Test
    public void getIngezetenenExclusiefBestuurder_na_setBestuurder() {
        for (int i = 0; i < 65536; i++) {
            Voertuig voertuig = new TestVoertuig("auto", datum, 18300, AANTAL_INZITTENDEN, BESTUURDER_B, INGEZETENE_A);
            voertuig.setBestuurder(BESTUURDER_BBE);
            Mens[] actual = new Mens[voertuig.getIngezeteneExclusiefBestuurder().size()];
            voertuig.getIngezeteneExclusiefBestuurder().toArray(actual);
            Mens[] expected = new Mens[]{INGEZETENE_A, BESTUURDER_B};
            assertArrayEquals(expected, actual);
        }
    }
}
