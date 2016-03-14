/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

/**
 *
 * @author arne.simons
 */
public enum Maat {
    centimeter, decimeter, meter;

    @Override
    public String toString() {
        return this.name();
    }
    
    
}
