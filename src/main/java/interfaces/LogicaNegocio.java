/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author Jorge
 */

import java.util.List;
import entidades.*;
import java.sql.SQLException;


public interface LogicaNegocio {

    // CLIENTES
    Cliente crearCliente(String nombre, String apellidos, String telefono, String email, int nivelFidelidad) throws SQLException, ErrorDatos ;
    List<Cliente> listarClientes();

    // ARTÍCULOS
    ProductoFisico crearProductoFisico(String nombre, double precioBase, double iva, int stock) throws SQLException, ErrorDatos;
    Servicio crearServicio(String nombre, double precioBase, double iva, int minutos, boolean urgente) throws SQLException, ErrorDatos;
    List<Articulo> listarArticulos();

    // PEDIDOS
    void iniciarPedido(Cliente cliente) throws ErrorDatos;
    void anadirLineaPedido(Articulo articulo, int cantidad) throws ErrorDatos;
    Pedido obtenerPedidoEnCurso();
    Pedido confirmarPedido() throws ErrorDatos;     // registra en BBDD mediante la lógica
    void cancelarPedido();

    // CONSULTA
    List<Pedido> listarPedidos();
}
