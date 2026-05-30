/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;

/**
 *
 * @author 29160712r
 */
public class AlertaStock {
    
    private int id;
    private String nombreArticulo;
    private int stockActual;
    private LocalDateTime fechaAlerta;

    public AlertaStock() {
    }

    public AlertaStock(int id, String nombreArticulo, int stockActual) {
        this.id = id;
        this.nombreArticulo = nombreArticulo;
        this.stockActual = stockActual;
        this.fechaAlerta = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public LocalDateTime getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(LocalDateTime fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }   
    
}
