/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javsimoli
 */
public class Cliente extends Persona {

    private String email;
    private int nivelFidelidad;
    private List<Pedido> listaPedidos;

    public Cliente() throws ErrorDatos {
        super();
    }

    
    

    /**
     * Constructor de cliente inicializando la lista de pedidos con id
     * automático
     *
     * @param email del cliente
     * @param nivelFidelidad del 1 al 5 sobre el nivel de transacciones del
     * cliente
     * @param nombre Clase super Persona nombre de persona
     * @param apellidos Clase super Persona apellidos de persona
     * @param telefono Clase super Persona telefono de persona
     * @param id Clase super identificador nombre de persona
     * @throws ErrorDatos
     */
    public Cliente(String email, int nivelFidelidad, String nombre, String apellidos, String telefono) throws ErrorDatos {

        super(nombre, apellidos, telefono);

        if (Utils.isEmail(email)) {
            this.email = email;
        }

        if (Utils.rangoFidelidad(nivelFidelidad)) {
            this.nivelFidelidad = nivelFidelidad;
        }

        listaPedidos = new ArrayList<>();

    }

    /**
     * Constructor de cliente inicializando la lista de pedidos
     *
     * @param email del cliente
     * @param nivelFidelidad del 1 al 5 sobre el nivel de transacciones del
     * cliente
     * @param nombre Clase super Persona nombre de persona
     * @param apellidos Clase super Persona apellidos de persona
     * @param telefono Clase super Persona telefono de persona
     * @param id Clase super identificador nombre de persona
     * @throws ErrorDatos
     */
    public Cliente(String email, int nivelFidelidad, String nombre, String apellidos, String telefono, int id) throws ErrorDatos {

        super(nombre, apellidos, telefono, id);

        if (Utils.isEmail(email)) {
            this.email = email;
        }

        if (Utils.rangoFidelidad(nivelFidelidad)) {
            this.nivelFidelidad = nivelFidelidad;
        }

        listaPedidos = new ArrayList<>();

    }

    /**
     * Constructor de cliente con todos los parametros
     *
     * @param email del cliente
     * @param nivelFidelidad del 1 al 5 sobre el nivel de transacciones del
     * cliente
     * @param listaPedidos la lista de los pedidos del cliente, que puede estar
     * vacía.
     * @param nombre Clase super Persona nombre de persona
     * @param apellidos Clase super Persona apellidos de persona
     * @param telefono Clase super Persona telefono de persona
     * @param id Clase super identificador nombre de persona
     * @throws ErrorDatos
     */
    public Cliente(String email, int nivelFidelidad, List<Pedido> listaPedidos, String nombre, String apellidos, String telefono, int id) throws ErrorDatos {

        super(nombre, apellidos, telefono, id);

        if (Utils.isEmail(email)) {
            this.email = email;
        }

        if (Utils.rangoFidelidad(nivelFidelidad)) {
            this.nivelFidelidad = nivelFidelidad;
        }

        this.listaPedidos = listaPedidos;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ErrorDatos {
        if (Utils.isEmail(email)) {
            this.email = email;
        }
    }

    public int getNivelFidelidad() {
        return nivelFidelidad;
    }

    public void setNivelFidelidad(int nivelFidelidad) throws ErrorDatos {
        if (Utils.rangoFidelidad(nivelFidelidad)) {
            this.nivelFidelidad = nivelFidelidad;
        }
    }

    /**
     * Método para añadir un pedido a la lista del cliente.
     *
     * @param pedido el pedido a añadir
     * @throws ErrorDatos
     */
    public void añadirPedido(Pedido pedido) throws ErrorDatos {

        if (pedido != null) {
            this.listaPedidos.add(pedido);
        } else {
            throw new ErrorDatos("ERROR. No se ha definido pedido que añadir al cliente.");
        }

    }

    /**
     * Método para borrar un pedido de lista del cliente.
     *
     * @param pedido el pedido a borrar
     * @throws ErrorDatos
     */
    public void eliminarPedido(Pedido pedido) throws ErrorDatos {

        if (pedido != null) {
            this.listaPedidos.remove(pedido);
        } else {
            throw new ErrorDatos("ERROR. No se ha definido pedido que borrar del cliente.");
        }

    }

    /**
     * Método para borrar un pedido de lista del cliente usando el indice.
     *
     * @param indice del pedido a borrar
     * @throws ErrorDatos
     */
    public void eliminarPedido(int indice) throws ErrorDatos {

        if (Utils.numeroPositivo(indice, "ERROR indice de lista a borrar negativo") && indice < this.listaPedidos.size()) {
            this.listaPedidos.remove(indice);
        } else {
            throw new ErrorDatos("ERROR. No se ha definido un pedido que borrar del cliente.");
        }

    }

    /**
     * Getter lista de pedidos
     *
     * @return listta de pedidos
     */
    public List<Pedido> listarPedidos() {
        return this.listaPedidos;
    }

    /**
     * Cuenta los pedidos del cliente
     *
     * @return numero de pedidos (int)
     */
    public int numeroPedidos() {
        return this.listarPedidos().size();
    }

    /**
     * Imprime las cabezeras de toods pedidos del cliente
     *
     * @throws ErrorDatos
     */
    public void imprimirPedidos() throws ErrorDatos {

        for (Pedido p : this.listarPedidos()) {
            p.toString();
        }

    }

    /**
     * Busca un pedido por el total a pagar
     *
     * @param total suma totalk del pedido a buscar
     * @return pedido, el pedido si lo hubiera que coincide con el parametroi de
     * busqueda
     * @throws ErrorDatos
     */
    public Pedido buscarPedido(double total) throws ErrorDatos {

        for (Pedido p : this.listarPedidos()) {

            if (p.getTotal() == total) {
                return p;
            }

        }

        throw new ErrorDatos("ERROR. No hay pedidos con ese valor total");

    }

    /**
     * Lista en una linea todos los datos de un cliente.
     *
     * @return
     */
    @Override
    public String toString() {
        String linea = String.format("%5d\t%-30s %-50s\t%-10s\t%-40s\t%3d", super.getId(), super.getNombre(), super.getApellidos(), super.getTelefono(), this.email, this.nivelFidelidad);
        return linea;
    }

}
