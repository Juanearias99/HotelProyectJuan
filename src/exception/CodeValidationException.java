/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author Lenovo
 */
public class CodeValidationException extends RuntimeException {

    public CodeValidationException() {
        super("El codigo ingresado no es valido");
    }
}