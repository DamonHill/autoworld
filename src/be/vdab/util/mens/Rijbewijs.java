/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

/**
 *
 * @author arne.simons
 */
public enum Rijbewijs {
    A, B, BE, C, CE, D, DE;

    @Override
    public String toString() {
        String rijbewijs = super.toString();
        if (rijbewijs.length() == 2) {
            return rijbewijs.charAt(0) + "+" + rijbewijs.charAt(1);
        }
        return rijbewijs;
    }
}