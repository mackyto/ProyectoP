/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author 29160712r
 */
public class Horas {
    
    private int año, mes, cantidad;

    public Horas(int cantidad) throws ErrorDatos {
        this.año = LocalDate.now().getYear();
        this.mes = LocalDate.now().getMonthValue();
        this.maxCantidad(cantidad);
    }
    
    public Horas(int mesAnterior, int cantidad) throws ErrorDatos {
        
        this.año = LocalDate.now().minusMonths(mesAnterior).getYear();
        this.mes = LocalDate.now().minusMonths(mesAnterior).getMonthValue();
        this.maxCantidad(cantidad);
    }

    public Horas(int año, int mes, int cantidad) {
        this.año = año;
        this.mes = mes;
        this.cantidad = cantidad;
    }

    
    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws ErrorDatos {
        this.maxCantidad(cantidad);
    }
    
    public void maxCantidad(int cantidad) throws ErrorDatos{
        if (cantidad > 0 && cantidad <= 80)
            this.cantidad = cantidad;
        else
            throw new ErrorDatos("Las horas extras no pueden exceder de ochwenta al año ni ser igual o inferior a cero");
    }
    
}
