/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

/**
 *
 * @author arne.simons
 */
public enum DIV {
    INSTANCE;
    private Nummerplaat nummerplaat;
    private int teller = 0;

    public Nummerplaat getNummerplaat() {
        StringBuilder sb = new StringBuilder("AAA");
        ++teller;
        if (teller == 1000) {
            teller = 1;
        }
        if (teller < 10) {
            return new Nummerplaat(sb.append("00").append(teller).toString());
        } else {
            if (teller < 100) {
                return new Nummerplaat(sb.append("0").append(teller).toString());
            }
            else {
                return new Nummerplaat(sb.append(teller).toString());
            }
        } 
    }  
    
}
