/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Nominas;

/**
 *
 * @author macky
 */
public class Complementos {

    private final double DESTINO = 434.23;
    private final double[] ESPECIFICO = {0.00,532.20,628.45,789.45,856.78,940.56};
    
    public double getComplementoDestino(){
        return DESTINO;
    }
    
    public double getComplementoEspecifico(int clase){
        return ESPECIFICO[clase-1];
    }
    
    
}
