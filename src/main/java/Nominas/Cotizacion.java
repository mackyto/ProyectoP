/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Nominas;

/**
 * 
 * Mínimo Tramo
 * Máximo Tramo.
 * 
 * Base Mínima.
 * Base Máxima.
 * 
 * @author macky
 */
public class Cotizacion {
    
    private final double[][] TABLA = {
        
        {   0.00, 670.00, 900.00,1166.70,1300.00,1500.00,1700.00,1850.00,2030.00,2330.00,2760.00,3190.00,3620.00,4050.00,6000.00},        
        { 670.00, 900.00,1166.70,1300.00,1500.00,1700.00,1850.00,2030.00,2330.00,2760.00,3190.00,3620.00,4050.00,6000.00,9999999},
        { 653.59, 718.95, 849.67, 950.98, 960.78, 960.78,1143.79,1209.15,1274.51,1356.21,1437.71,1519.61,1601.31,1732.03,1928.10},
        { 718.94, 900.00,1166.70,1300.00,1500.00,1700.00,1850.00,2030.00,2330.00,2760.00,3190.00,3620.00,4050.00,4909.50,4909.50}
            
    };
    
    public double getTramoMinimo(int tramo){
        return TABLA[0][tramo];
    }
    
    public double getTramoMaximo(int tramo){
        return TABLA[1][tramo];
    }
    
    public double getBaseMinima(int tramo){
        return TABLA[2][tramo];
    }
    
    public double getBaseMaxima(int tramo){
        return TABLA[3][tramo];
    }
    
    public int getTramo(double sueldo){
        
        for (int i = 0; i < TABLA[0].length; i++){
            
            if (TABLA[0][i] < sueldo && TABLA[1][i] > sueldo ){

                return i;
                
            }
            
        }
        
        return -1;
        
    }
    
}
