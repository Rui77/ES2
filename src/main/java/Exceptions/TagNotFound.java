/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Rui
 */
public class TagNotFound extends Exception {

    /**
     * Creates a new instance of <code>TagNotFound</code> without detail
     * message.
     */
    public TagNotFound() {
    }

    /**
     * Constructs an instance of <code>TagNotFound</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TagNotFound(String msg) {
        super(msg);
    }
}
