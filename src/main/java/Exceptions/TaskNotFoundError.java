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
public class TaskNotFoundError extends Exception {

    /**
     * Creates a new instance of <code>TaskNotFound</code> without detail
     * message.
     */
    public TaskNotFoundError() {
    }

    /**
     * Constructs an instance of <code>TaskNotFound</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public TaskNotFoundError(String msg) {
        super(msg);
    }
}
