/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2526;

import interfaces.LogicaNegocio;
import logica.GestorComercio;
import entidades.*;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneradorArticulos generador = GeneradorArticulos.getInstancia();
        GeneradorClientes generarCli = GeneradorClientes.getInstancia();
        LogicaNegocio comercio = GestorComercio.getInstance();
        GestorComercio gestor = (GestorComercio) comercio;
        
        try{
        

            generador.generarArticulos(comercio, 100, 70);
            generador.mostrarEstadisticas();
            
            generarCli.crearClientes(comercio, 80);
            
            gestor.imprimirListaClientes(gestor.buscarClientes("José"));
            
            //gestor.imprimirClientes();
            //gestor.imprimirArticulos();
            
            
        }catch(ErrorDatos er){
            
            System.out.println(er.getMessage());
            
        }
    
        
    }
    /**
     * Imprime cantidad de articulos generados. 
     * @param generador instancia uníca generadora de articulos.
     * @throws entidades.ErrorDatos
     */
    public static void listarArticulos(GeneradorArticulos generador) throws ErrorDatos{
            
            // Obtener listas específicas
            List<ProductoFisico> productos = generador.getProductosFisicos();
            List<Servicio> servicios = generador.getServicios();
        
            System.out.println("\nProductos físicos generados: " + productos.size());
            System.out.println("Servicios generados: " + servicios.size());        
        
    }
 
    
    
    
    
    
    
}
