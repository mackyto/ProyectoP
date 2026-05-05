/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2526;

import interfaces.LogicaNegocio;
import logica.GestorComercio;
import entidades.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jorge
 */
public class Main {

    public static Scanner kl = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneradorArticulos generador = GeneradorArticulos.getInstancia();
        //    GeneradorClientes generarCli = GeneradorClientes.getInstancia();
        LogicaNegocio comercio = GestorComercio.getInstance();
        GestorComercio gestor = (GestorComercio) comercio;

        String opcion = "";
        
        do{
            
        try {
            
            System.out.println("Menú");
            System.out.println("1 - Crear cliente.");
            System.out.println("2 -");
            System.out.println("3 - ");
            System.out.println("4 - ");
            System.out.println("5 -");
            System.out.println("6 - ");
            System.out.println("7 - ");

            opcion = kl.nextLine();
           
            switch (opcion) {
                
                case "1": crearCliente(); break;                
                case "2": ; break;                
                case "3": ; break;                
                case "4": ; break;                
                case "5": ; break;                
                case "6": ; break;                
                case "7": ; break;
                
                
                
                
                
            }
            
            
            
            
            
            
            

            generador.generarArticulos(comercio, 100, 70);
            generador.mostrarEstadisticas();

            //    generarCli.crearClientes(comercio, 80);
            gestor.imprimirListaClientes(gestor.buscarClientes("José"));

            //gestor.imprimirClientes();
            //gestor.imprimirArticulos();
        } catch (ErrorDatos er) {

            System.out.println(er.getMessage());

        }

        }while (!opcion.equalsIgnoreCase("q"));
        
    }

    /**
     * Imprime cantidad de articulos generados.
     *
     * @param generador instancia uníca generadora de articulos.
     * @throws entidades.ErrorDatos
     */
    public static void listarArticulos(GeneradorArticulos generador) throws ErrorDatos {

        // Obtener listas específicas
        List<ProductoFisico> productos = generador.getProductosFisicos();
        List<Servicio> servicios = generador.getServicios();

        System.out.println("\nProductos físicos generados: " + productos.size());
        System.out.println("Servicios generados: " + servicios.size());

    }

    public static Cliente crearCliente() {

        Cliente cl = null;

        try {

            System.out.println("Nombre: ");
            String nombre = kl.nextLine();
            System.out.println("Apellidos: ");
            String apellidos = kl.nextLine();
            System.out.println("telefono: ");
            String telefono = kl.nextLine();
            System.out.println("Fidelidad: ");
            int fidelidad = Integer.parseInt(kl.nextLine());
            System.out.println("email: ");
            String email = kl.nextLine();

            cl = new Cliente(email, fidelidad, nombre, apellidos, telefono);

        } catch (ErrorDatos ed) {
            ed.printStackTrace();
        }

        return cl;

    }

}
