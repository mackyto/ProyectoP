/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Nominas;

import java.util.ArrayList;
//import nominas.Empleado;

/**
 *
 * Orden de generación de los Objetos
 * 
 * Nombre,Apellidos;DNI
 * 
 * 
 * @author macky
 */
public class Todos {
               
    private ArrayList<Empleado> personal = new ArrayList<>();
    
    public Todos() {
    
        personal = new ArrayList<>();
        inicializarEmpleados();
    
    }
    
    private void  inicializarEmpleados(){
        
        Empleado cain = new Empleado( "Cain", "Jefe Primus", "12345678Z", "46/01234567/89", "Del bosque", 25, "Manises", "Valencia", 46104, 5, 1, 1, 4, "CEO"); 
        Empleado sporticus = new Empleado("Javier","Simarro Olivares","29160712R","46/01234568/89","Colon", 9, "Puzol", "Valencia", 46560, 3, 2, 1, 4, "IT"); 
        Empleado other = new Empleado("Other", "Can Throw","12345689J","46/01234567/89","Jarafuel", 25, "Manises", "Valencia", 46104, 5, 5, 1, 4, "Auxiliar");
        Empleado kathobodua = new Empleado ("Kathobodua", "Gargaro", "45789547U", "46/69855219/89", "Solidario", 14, "Cataroja", "Valencia", 46470, 4, 1, 1, 4, "RRSS_y_Marqueting");
        Empleado sebas = new Empleado("Sebas", "El rey","94623734W","46/01234567/89","La Eliana", 6, "Mislata", "Valencia", 46920, 5, 2, 1, 4, "Nutricion y Entrenamiento");
   
        personal.add(cain);
        personal.add(sporticus);
        personal.add(other);
        personal.add(kathobodua);
        personal.add(sebas);

    //    System.out.println("Se han añadido " + personal.size() + " empleados");
        
    } 
    
    public ArrayList<Empleado> getPersonal() {
    
        return personal;
    
    }
    
}
