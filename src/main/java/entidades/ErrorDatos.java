/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author javsimoli
 */
public class ErrorDatos extends Exception {
    
    /**
     * Constructos básico.
     */
    public ErrorDatos (){
         
        super ("!!!Error¡¡¡ Excepción General.");
            
    }
    
    /**
     * Constructor con mensaje especifico.
     * @param mensaje 
     */
    public ErrorDatos (String mensaje){
         
        super(mensaje);
        
    }
    
}
