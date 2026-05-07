/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import entidades.Articulo;
import entidades.ProductoFisico;
import entidades.Servicio;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public interface InArticulo {
    public boolean persistirProducto(ProductoFisico pf );
    public boolean persistirServicio(Servicio s);
    public List<Articulo> recuperarTodo();
    
}
