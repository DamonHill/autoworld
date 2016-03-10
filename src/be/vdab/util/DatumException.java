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
public class DatumException extends Exception {

    public DatumException() {
    }

    public DatumException(String message) {
        super(message);
    }

    public DatumException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatumException(Throwable cause) {
        super(cause);
    }

    public DatumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
