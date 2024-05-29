/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Lenovo
 */
public class PhoneValidationException extends RuntimeException {
    
    public PhoneValidationException(){
        super("El numero ingresado no es valido:");
    }
}
