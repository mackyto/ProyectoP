/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author javsimoli
 */
public class ProductoFisico extends Articulo {
 
    private int stock;

    
    public ProductoFisico() {
    }
    
    /**
     * Constructor 
     * @param stock cantidad de servicio
     * @param nombre nombre de articulo
     * @param precioBase precio unitario
     * @param iva iva para el producto
     * @throws ErrorDatos 
     */
    public ProductoFisico(int stock, String nombre, double precioBase, double iva) throws ErrorDatos {
        super(nombre, precioBase, iva);
        int total;
        
        if (Utils.numeroPositivo(stock, "ERROR. El stock (número de unidades) no puede ser negativo"))
            this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws ErrorDatos {
        if (Utils.numeroPositivo(stock, "ERROR. El stock (número de unidades) no puede ser negativo"))
            this.stock = stock;
    }    
    
    /**
     * Elimina un articulo de la lista
     */
    public void restaUno() throws ErrorDatos {
        this.stock--;
    } 
    
    /**
     * Calcule el Precio Unitario Final. con impuestos
     * @return 
     */
    @Override
    public double precioUnitarioFinal(){    
        
        double precio = 0.00;

        precio = this.getPrecioBase() * (1 +  this.getIva() / 100);
        
        return precio;

    }
    
    @Override
    public String toString(){
        
        String mensaje = String.format("%5d Producto: %-50s Precio: %8.2f€ IVA: %5.2f Estimación %6d unidades",
                super.getId(), 
                super.getNombre(), 
                super.getPrecioBase(), 
                super.getIva(), 
                this.getStock());
        
        return mensaje;
    }
    
}
