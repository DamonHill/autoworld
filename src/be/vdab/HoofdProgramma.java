/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import static be.vdab.voertuigen.Voertuig.getAankoopprijsComparator;
import static be.vdab.voertuigen.Voertuig.getMerkComparator;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author arne.simons
 */
public class HoofdProgramma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatumException, VolumeException {
        SortedSet<Voertuig> ssv = new TreeSet<>();
        Datum datum = new Datum(1, 3, 2016);
        Mens chauffeur1 = new Mens("Arne", Rijbewijs.B);
        Mens chauffeur2 = new Mens("Robby", Rijbewijs.C, Rijbewijs.DE);
        Mens inzittende1 = new Mens("Geert");
        Mens inzittende2 = new Mens("Georges", Rijbewijs.BE);
        Volume vol10 = new Volume(10, 10, 10, Maat.decimeter);
        Volume vol30 = new Volume(30, 30, 30, Maat.meter);
            
        Personenwagen pw1 = new Personenwagen("BMW", datum, 50000, 5, Color.BLACK, chauffeur1, inzittende1);
        Personenwagen pw2 = new Personenwagen("Dodge", datum, 35000, 7, Color.DARK_GRAY, chauffeur1, inzittende1, inzittende2);
        Pickup p1 = new Pickup("Dodge", datum, 17500, 2, Color.RED, vol10, chauffeur2);
        Pickup p2 = new Pickup("Chevrolet", datum, 15750, 2, Color.GRAY, vol10, chauffeur1, inzittende2);
        Vrachtwagen vw1 = new Vrachtwagen("Scania", datum, 99000, 3, vol30, 30000, 2, chauffeur2, chauffeur1);
        Vrachtwagen vw2 = new Vrachtwagen("Scania", datum, 85000, 3, vol30, 30000, 2, chauffeur2);
        
        ssv.add(pw1);
        ssv.add(pw2);
        ssv.add(p1);
        ssv.add(p2);
        ssv.add(vw1);
        ssv.add(vw2);
             
        System.out.println("Ongesorteerd =============================");
        for (Voertuig v: ssv) {
            System.out.println(v.toString());
        }
    
        System.out.println("Gesorteerd op prijs =============================");
        Comparator c_prijs = getAankoopprijsComparator();
        SortedSet<Voertuig> ssv_opPrijs = new TreeSet<>(c_prijs);
        ssv_opPrijs.addAll(ssv);
        for (Voertuig v: ssv_opPrijs) {
            System.out.println(v.toString());
        }
        
        System.out.println("Gesorteerd op merk =============================");
        Comparator c_merk = getMerkComparator();
        SortedSet<Voertuig> ssv_opMerk = new TreeSet<>(c_merk);
        ssv_opMerk.addAll(ssv);
        for (Voertuig v: ssv_opMerk) {
            System.out.println(v.toString());
        }
        
        try (FileOutputStream fos =  new FileOutputStream("D:/JPF/wagenpark.ser");    
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {  
            oos.writeObject(ssv_opMerk); 
        } 
        catch (IOException e) {
            System.out.println("Lijst kan niet worden weggeschreven");
        }
        
        SortedSet<Voertuig> ssv_ingelezen = new TreeSet<>();
        try (FileInputStream fis =  new FileInputStream("D:/JPF/wagenpark.ser"); 
             ObjectInputStream ois = new ObjectInputStream(fis);) {  
            ssv_ingelezen = (TreeSet<Voertuig>)ois.readObject();              
        }  
        catch (ClassNotFoundException e) { 
            System.out.println("Klasse niet gevonden"); 
        }
        catch (IOException e) { 
            System.out.println("Kan lijst niet vinden");
        }
        
        System.out.println("Na inlezen =============================");
        for (Voertuig v: ssv_ingelezen) {
            System.out.println(v.toString());
        }
    }
    
}
