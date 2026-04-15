/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.util.ArrayList;
import java.util.List;
import interfaces.LogicaNegocio;
import logica.GestorComercio;
import entidades.Utils;
/**
 *
 * @author macky
 */
public class GeneradorArticulos {
    /**
     * Instancia única (singleton)
     */
    private static GeneradorArticulos instancia;
    
    /**
     * Lista para almacenar los artículos generados
     */
    private List<Articulo> articulos;
    
    /**
     * Constructor privado para prevenir instanciación externa
     */
    private GeneradorArticulos() {
        articulos = new ArrayList<>();
    }
    
    /**
     * Método estático para obtener la única instancia
     * @return instancia de generador 
     */
    public static GeneradorArticulos getInstancia() {
        if (instancia == null) {
            instancia = new GeneradorArticulos();
        }
        return instancia;
    }
    
    /**
     * Método para generar una proporción específica de productos y servicios 
     * @param comercio Instancia única de comercio
     * @param cantidad cantidad de articulos a generar
     * @param porcentaje
     * @throws ErrorDatos 
     */
    public void generarArticulos(LogicaNegocio comercio, int cantidad, int porcentaje) throws ErrorDatos {
        
        //articulos.clear();
        if (cantidad > 1000)
            throw new ErrorDatos ("ERROR. La cantidad solicitada de Articulos a generar, supera el millar");
        
        Utils.numeroPositivo(cantidad, "ERROR. La cantidad de articulos a generar, es un número negativo.");
        
        if (porcentaje > 100)
            throw new ErrorDatos ("ERROR. El porcentaje solicitado de los Articulos, Producto/servicio, a generar, supera no puede ser mayor de cien.");
        
        Utils.numeroPositivo(porcentaje, "ERROR. El porcentaje solicitado de los Articulos, producto/Servicio, a generar, es un número negativo.");        
        
        int cantidadProductos = (int) (cantidad * porcentaje / 100);
        int cantidadServicios = cantidad - cantidadProductos;
        
        // Generar productos físicos
        for (int i = 0; i < cantidadProductos; i++) {
            Articulo producto = comercio.crearProductoFisico("Producto " + (i + 1), 10 + Math.random() * 100, 21, (int) (Math.random() * 100));
            articulos.add(producto);
        }
        
        // Generar servicios
        for (int i = 0; i < cantidadServicios; i++) {
            Articulo servicio = comercio.crearServicio("Servicio " + (i + 1), 20 + Math.random() * 200, 21, 30 + (int) (Math.random() * 180), Math.random() > 0.7);
            articulos.add(servicio);
        }
    }
    
    /**
     * Getter Lista de articulos
     * @return Lista de articulos
     */
    public List<Articulo> getArticulos() {
        return new ArrayList<>(articulos); // Devolver copia para proteger la lista interna
    }
    
    /**
     * Cuenta la cantidad de articulos en una lista
     * @return numero de articulos en la lista (int)
     */
    public int getCantidadArticulos() {
        return articulos.size();
    }
    
    /**
     * Borra la lista anterior
     */
    public void limpiarArticulos() {
        articulos.clear();
    }
    
    /**
     * Getter para obtener una lista de artículos del tipo Producto
     * @return Lista de productos
     */
    public List<ProductoFisico> getProductosFisicos() {
        List<ProductoFisico> productos = new ArrayList<>();
        for (Articulo articulo : articulos) {
            if (articulo instanceof ProductoFisico) {
                productos.add((ProductoFisico) articulo);
            }
        }
        return productos;
    }
    
    /**
     * Getter para obtener una lista de artículos del tipo Servicio
     * @return Lista de servicios
     */    
    public List<Servicio> getServicios() {
        List<Servicio> servicios = new ArrayList<>();
        for (Articulo articulo : articulos) {
            if (articulo instanceof Servicio) {
                servicios.add((Servicio) articulo);
            }
        }
        return servicios;
    }
    
    /**
     * Método para obtener estadísticas de los artículos generados
     * numero generado de productos vs servicios y cuantos son Urgentes.
     */
    public void mostrarEstadisticas() {
        int totalProductos = getProductosFisicos().size();
        int totalServicios = getServicios().size();
        int serviciosUrgentes = 0;
        
        for (Servicio servicio : getServicios()) {
            if (servicio.isUrgente()) {
                serviciosUrgentes++;
            }
        }
        
        System.out.println("=== ESTADÍSTICAS DE ARTÍCULOS ===");
        System.out.println("Total artículos: " + getCantidadArticulos());
        System.out.println("Productos físicos: " + totalProductos);
        System.out.println("Servicios: " + totalServicios);
        System.out.println("Servicios urgentes: " + serviciosUrgentes + " (" + (totalServicios > 0 ? (serviciosUrgentes * 100 / totalServicios) : 0) + "%)");
    }


}
