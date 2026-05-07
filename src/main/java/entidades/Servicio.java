/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author javsimoli
 */
public class Servicio extends Articulo {
    
    private int minutos;
    private boolean urgente; 

    public Servicio() {
    }
    
    /**
     * Constructor de servicio
     * @param minutos
     * @param urgente
     * @param nombre
     * @param precioBase
     * @param iva
     * @throws ErrorDatos 
     */
    public Servicio(int minutos, boolean urgente, String nombre, double precioBase, double iva) throws ErrorDatos {
        super(nombre, precioBase, iva);
        this.minutos = minutos;
        this.urgente = urgente;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    
    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }    
    
    
    /**
     * Getter precio unitario con descuento.
     * @return precio unitario final (double)
     */
    @Override
    public double precioUnitarioFinal () {
        
        double precio = 0.00;
    
        precio = this.getPrecioBase() * (Math.ceil(this.getMinutos()) / 30) * (1 +  this.getIva() / 100);

        if (this.isUrgente())
            precio = precio * 1.1;

        return precio;
    
    }
    
    @Override
    public String toString(){
        
        String mensaje = String.format("%5d Servicio: %-50s Precio: %8.2f€ IVA: %5.2f Estimación %6d minutos   %s",
                super.getId(), 
                super.getNombre(), 
                super.getPrecioBase(), 
                super.getIva(), 
                this.getMinutos(), 
                this.isUrgente() ? "URGENTE" : "");
        
        return mensaje;
    }
    
}
