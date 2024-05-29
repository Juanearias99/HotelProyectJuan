/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Lenovo
 */
public class DateDuplicateException extends Exception{

    public DateDuplicateException() {
        
        super("La fecha ya esta registrada");
    }
    
}
