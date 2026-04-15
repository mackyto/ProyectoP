package interfaces;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author macky
 */
public interface Vendible {
    
    /**
     * Getter nombre
     * @return nombre (String)
     */
    public String getNombre();
    
    /**
     * Getter precio por unidad.
     * @return precio unidad (double)
     */
    public double precioUnitario ();
    
    /**
     * Getter precio unitario con descuento.
     * @return precio unitario final (double)
     */
    public double precioUnitarioFinal ();
    
}
