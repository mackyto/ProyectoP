/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;

/**
 * @author javsimoli
 */
public class LineaPedido {

    private int cantidad;
    private double precioUnitarioFinal;
    private Articulo articulo;
    private Pedido pedido;

    /**
     * Constructor básico.
     * @param cantidad cantidad incluida en el pedido
     * @param articulo articulo incluido
     * @param pedido pedido al que pertenece
     * @throws ErrorDatos 
     */
    public LineaPedido(int cantidad, Articulo articulo, Pedido pedido) throws ErrorDatos {

        this.articulo = articulo;

        this.pedido = pedido;

        if (Utils.numeroPositivo(cantidad, "ERROR. La cantidad en Linea de pedido no puede ser negativa.")) {
            if (articulo instanceof ProductoFisico pf && pf.getStock() < cantidad) {
                throw new ErrorDatos("ERROR. Se solicita mas cantidad de productos que las existencias de almacen");
            }
            this.cantidad = cantidad;
        }

            this.precioUnitarioFinal = this.getArticulo().precioUnitarioFinal();


    }

    
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Setter cantidad con comprobación de Stockage y numero negativo 
     * @param cantidad cantidad solicitada
     * @throws ErrorDatos 
     */
    public void setCantidad(int cantidad) throws ErrorDatos {
        if (Utils.numeroPositivo(cantidad, "ERROR. La cantidad en Linea de pedido no puede ser negativa.")) {
            if (articulo instanceof ProductoFisico pf && pf.getStock() < cantidad) {
                throw new ErrorDatos("ERROR. Se solicita mas cantidad de productos que las existencias de almacen");
            }
            this.cantidad = cantidad;
        }
    }

    
    public double getPrecioUnitarioFinal() {
        return precioUnitarioFinal;
    }

    public void setPrecioUnitarioFinal(double precioUnitarioFinal) throws ErrorDatos {
        this.precioUnitarioFinal = this.getArticulo().precioUnitarioFinal();
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    @Override
    public String toString () {
        
        String mensaje = String.format("%6d    %-50s   %8.2f   %8.2f", this.getCantidad(), this.getArticulo().getNombre(), this.getArticulo().getPrecioBase(), this.precioUnitarioFinal);
        return mensaje;
        
    }
    
    

}
