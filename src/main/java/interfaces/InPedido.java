/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;
import entidades.Articulo;
import entidades.Cliente;
import entidades.Pedido;
import java.util.List;
/**
 * Define las operaciones de persistencia para la gestión de Pedidos.
 * @author macky
 */

public interface InPedido {


    boolean persistirPedido(Pedido p);

    /**
     *
     * @param cliente
     * @param ar
     */
    void recuperarPedidos(Cliente cliente, List<Articulo> ar);

}

