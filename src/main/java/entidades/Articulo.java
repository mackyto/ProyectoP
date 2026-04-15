/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import interfaces.Vendible;

/**
 *
 * @author javsimoli
 */
public abstract class Articulo extends EntidadBase implements Vendible {
    
    private String nombre;
    private double precioBase;
    private double iva;

    private static int puntero = 0;

    /**
     * Constructor de super clase con id autoḿatico
     * @param nombre - El nombre que describe o representa un articulo.
     * @param precioBase Precio del articulo sin inpuetos
     * @param iva Inpuesto aplicable con un límite del 100%
     * @param id identificador del artículo.
     * @throws ErrorDatos 
     */
    public Articulo (String nombre, double precioBase, double iva) throws ErrorDatos {
        
        super(++puntero);
        
        if (Utils.stringNoNulo(nombre, "ERROR El nombre del Artículo es nulo o vacío."))
            this.nombre = nombre;
        
        if (Utils.numeroPositivo(precioBase, "ERROR El precio base de un articulo no puede ser negativo."))
            this.precioBase = precioBase;        
 
        if (Utils.numeroPositivo(iva))
            this.iva = iva;
            
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ErrorDatos {
        if (Utils.stringNoNulo(nombre, "ERROR El nombre del Artículo es nulo o vacío."))
            this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) throws ErrorDatos {
        if (Utils.numeroPositivo(precioBase, "ERROR El precio base de un articulo no puede ser negativo."))        
            this.precioBase = precioBase;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) throws ErrorDatos {
        if (Utils.numeroPositivo(iva))
            this.iva = iva;
    }

    /**
     * getter del precio unitario
     * @return double
     */
    @Override
    public double precioUnitario() {
        return this.precioBase;
    }


}
