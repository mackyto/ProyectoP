/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Nominas;

/**
 *
 * Grupo//Nivel//Sueldo_Base_Anual//Sueldo_Base_Mensual(14 Pagas)//Base_Hora//Plus_Transporte//Hora_Nocturna//Complemento_Mobilidad
 * 
 * @author macky
 */
public class Tabla {
    
    private final double[][] TABLA_2025 = {
        {1,1,19917.08,1422.65,11.37,3,1.71,213.40},
        {2,1,18257.32,1304.09,10.42,3,1.56,195.61},
        {2,2,17688.26,1263.45,10.10,3,1.51,189.52},
        {3,1,17081.85,1220.13, 9.75,3,1.46,183.02},
        {3,2,16680.87,1191.49, 9.52,3,1.43,178.72},
        {4,1,16279.89,1162.85, 9.29,3,1.39,174.43},
        {4,2,16119.49,1151.39, 9.20,3,1.38,172.71},
        {5,1,16071.12,1147.94, 9.17,3,1.38,172.19}
    };
    
    private final double ANTIGUEDAD = 33.27;

    public double getBase(int grupo, int nivel) {
        
        for (int i = 0; i < TABLA_2025.length; i++) {

            if ((int)TABLA_2025[i][0] == grupo && (int)TABLA_2025[i][1] == nivel) {
                return TABLA_2025[i][2];
            }
    
        }

        return -1; 
    
        } 

        public double getBaseMes(int grupo, int nivel) {
        
        for (int i = 0; i < TABLA_2025.length; i++) {

            if ((int)TABLA_2025[i][0] == grupo && (int)TABLA_2025[i][1] == nivel) {
                return TABLA_2025[i][3];
            }
    
        }

        return -1; 
    
        }
    
        public double getHora(int grupo, int nivel) {
        
        for (int i = 0; i < TABLA_2025.length; i++) {

            if ((int)TABLA_2025[i][0] == grupo && (int)TABLA_2025[i][1] == nivel) {
                return TABLA_2025[i][4];
            }
    
        }

        return -1; 
    
        }

        public double getPlusTransporte(int grupo, int nivel) {
        
        for (int i = 0; i < TABLA_2025.length; i++) {

            if ((int)TABLA_2025[i][0] == grupo && (int)TABLA_2025[i][1] == nivel) {
                return TABLA_2025[i][5];
            }
    
        }

        return -1; 
    
        }
    
        public double getPlusHoraNocturna(int grupo, int nivel) {
        
        for (int i = 0; i < TABLA_2025.length; i++) {

            if ((int)TABLA_2025[i][0] == grupo && (int)TABLA_2025[i][1] == nivel) {
                return TABLA_2025[i][6];
            }
    
        }

        return -1; 
    
        }    
        
        public double getComplementoMovilidad(int grupo, int nivel) {
        
        for (int i = 0; i < TABLA_2025.length; i++) {

            if ((int)TABLA_2025[i][0] == grupo && (int)TABLA_2025[i][1] == nivel) {
                return TABLA_2025[i][7];
            }
    
        }

        return -1; 
    
        }  
        
        public double getAntiguedad() { return ANTIGUEDAD;}
        
}
