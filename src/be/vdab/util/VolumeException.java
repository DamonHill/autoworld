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
public class VolumeException extends Exception {

    public VolumeException() {
    }

    public VolumeException(String message) {
        super(message);
    }

    public VolumeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VolumeException(Throwable cause) {
        super(cause);
    }
    
}
