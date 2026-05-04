/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *  System.out.println("Telefonno: " + cl.getTelefono());
                System.out.println("email: " + cl.getEmail());
                System.out.println("Fidelidad(1-5): " + cl.getNivelFidelidad());
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println();
            }
        } else {
            throw new ErrorDatos("ERROR. No hay Clientes en la lista.");
        }

    }
 * @author Jorge
 */
import interfaces.LogicaNegocio;
import entidades.*;
import java.util.ArrayList;
import java.util.List;

public class GestorComercio implements LogicaNegocio {

    private static GestorComercio INSTANCE;

    //ATRIBUTOS DE LAS RELACIONES
    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private List<Pedido> pedidos;
    private Cliente clienteActual;
    private Pedido pedidoEnCurso;

    private GestorComercio() {
        //INICIALIZAR COLECCIONES
        clientes = new ArrayList<>();
        articulos = new ArrayList<>();
        pedidos = new ArrayList<>();

    }

    /**
     * Crea la instancia única de comercio
     *
     * @return la instancia creada
     */
    public static GestorComercio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GestorComercio();
        }
        return INSTANCE;
    }

    /**
     * Crear cliente. llama a su constructor
     *
     * @param nombre del cliente
     * @param apellidos del cliente
     * @param telefono del cliente
     * @param email del cliente
     * @param nivelFidelidad del cliente
     * @return
     * @throws ErrorDatos
     */
    @Override
    public Cliente crearCliente(String nombre, String apellidos, String telefono, String email, int nivelFidelidad) throws ErrorDatos {
        clientes.add(new Cliente(email, nivelFidelidad, nombre, apellidos, telefono));
        return clientes.getLast();
    }

    /**
     * Getter Lista de clientes
     *
     * @return lista de clientes
     */
    @Override
    public List<Cliente> listarClientes() {
        return clientes;
    }

    /**
     * Crear producto. Llama al constructor
     *
     * @param nombre producto
     * @param precioBase del producto
     * @param iva iva aplicable al producto
     * @param stock número deelementos en inventario
     * @return
     * @throws ErrorDatos
     */
    @Override
    public ProductoFisico crearProductoFisico(String nombre, double precioBase, double iva, int stock) throws ErrorDatos {
        ProductoFisico articulo = new ProductoFisico(stock, nombre, precioBase, iva);
        articulos.add(articulo);
        return articulo;
    }

    /**
     * Crear Servicio. Llama a su constructor
     *
     * @param nombre producto
     * @param precioBase del producto
     * @param iva iva aplicable al producto
     * @param minutos
     * @param urgente
     * @return
     * @throws ErrorDatos
     */
    @Override
    public Servicio crearServicio(String nombre, double precioBase, double iva, int minutos, boolean urgente) throws ErrorDatos {
        Servicio servicio = new Servicio(minutos, urgente, nombre, precioBase, iva);
        articulos.add(servicio);
        return servicio;
    }

    /**
     * Getter Lista de Articulos
     *
     * @return lista de articulos
     */
    @Override
    public List<Articulo> listarArticulos() {
        return articulos;
    }

    /**
     * Crea una instancia de pedido
     *
     * @param cliente al que
     * @throws ErrorDatos
     */
    @Override
    public void iniciarPedido(Cliente cliente) throws ErrorDatos {
        pedidoEnCurso = new Pedido(cliente);
    }

    /**
     * añadir Linea Pedido
     *
     * @param articulo de la linea
     * @param cantidad de productos
     * @throws ErrorDatos
     */
    @Override
    public void anadirLineaPedido(Articulo articulo, int cantidad) throws ErrorDatos {

        if (pedidoEnCurso != null) {
            LineaPedido linea = new LineaPedido(cantidad, articulo, this.pedidoEnCurso);
        } else {
            throw new ErrorDatos("ERROR. No hay pedido en curso");
        }
    }

    /**
     * Getter pedido en curso
     *
     * @return pedido en curso
     */
    @Override
    public Pedido obtenerPedidoEnCurso() {
        return pedidoEnCurso;
    }

    /**
     * Añade un pedido a la lista de pedidos del cliente actual
     *
     * @return
     * @throws ErrorDatos
     */
    @Override
    public Pedido confirmarPedido() throws ErrorDatos {
        if (this.obtenerPedidoEnCurso().getLista().size() != 0) {
            this.clienteActual.añadirPedido(pedidoEnCurso);
            return pedidoEnCurso;
        } else {
            throw new ErrorDatos("ERROR. El pedido en curso no se puede comfirmar, ya que no tiene lineas de pedido.");
        }
    }

    /**
     * Borra el pedido actual las lineas se borran automaticamente al perder la
     * referencia y quedar huerfanas... las lineas de pedido
     */
    @Override
    public void cancelarPedido() {
        this.pedidoEnCurso = null;
    }

    /**
     * Getter lista de pedidos
     *
     * @return lista de pedidos
     */
    @Override
    public List<Pedido> listarPedidos() {
        return this.clienteActual.listarPedidos();
    }

    public Articulo buscarArticulo(String nombre) throws ErrorDatos {
        for (Articulo a : this.listarArticulos()) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        throw new ErrorDatos("ERROR . El Articulo no se encuentra");
    }

    /**
     * Imprime datos de los articulos en la lista
     *
     * @throws ErrorDatos
     */
    public void imprimirArticulos() throws ErrorDatos {

        if (this.listarArticulos().size() != 0) {
            for (Articulo a : this.listarArticulos()) {
                System.out.println("----------------------------------------");
                System.out.println(a.getNombre());
                System.out.printf("Precio: %.2f€\n", a.getPrecioBase());
                System.out.printf("IVA aplicable: %.1f%%\n", a.getIva());
                System.out.println();

                if (a instanceof ProductoFisico pf) {
                    System.out.println("Stock: " + pf.getStock());
                } else if (a instanceof Servicio s) {
                    System.out.println("Duración: " + s.getMinutos() + " minutos");
                    if (s.isUrgente()) {
                        System.out.println("Servicio ¡¡¡URGENTE!!!");
                    }
                } else {
                    throw new ErrorDatos("ERROR. inconsistencia en los datos de articulos");
                }
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println();
            }
        } else {
            throw new ErrorDatos("ERROR. No hay articulos en la lista.");
        }

    }

    /**
     * Impprime datos de los articulos desde una lista
     * @param articulos liste de articulos a imprimir
     * @throws ErrorDatos 
     */
    public void imprimirListaArticulos(List<Articulo> articulos) throws ErrorDatos {

        if (articulos.size() != 0) {
            for (Articulo a : articulos) {
                System.out.println("----------------------------------------");
                System.out.println(a.getNombre());
                System.out.printf("Precio: %.2f€\n", a.getPrecioBase());
                System.out.printf("IVA aplicable: %.1f%%\n", a.getIva());
                System.out.println();

                if (a instanceof ProductoFisico pf) {
                    System.out.println("Stock: " + pf.getStock());
                } else if (a instanceof Servicio s) {
                    System.out.println("Duración: " + s.getMinutos() + " minutos");
                    if (s.isUrgente()) {
                        System.out.println("Servicio ¡¡¡URGENTE!!!");
                    }
                } else {
                    throw new ErrorDatos("ERROR. inconsistencia en los datos de articulos");
                }
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println();
            }
        } else {
            throw new ErrorDatos("ERROR. No hay articulos en la lista.");
        }

    }

    /**
     * Busca un cliente por nombre
     * @param nombre del cliente a buscar
     * @param apellidos del cliente a buscar
     * @return del primer cliente localizado.
     */
    public Cliente buscarCliente(String nombre, String apellidos) {
        for (Cliente cl : this.listarClientes()) {
            if (cl.getNombre().equalsIgnoreCase(nombre) && cl.getApellidos().equalsIgnoreCase(apellidos)) {
                return cl;
            }
        }
        return null;
    }

    /**
     * Crea una lista de clientes por coincidencia de nombre o apellidos.
     * @param nombre de los clientes a buscar.
     * @return lista de clientes con coincidencias.
     */
    public List<Cliente> buscarClientes(String nombre) {
        List<Cliente> result = new ArrayList<>();
        for (Cliente cl : this.listarClientes()) {
            if (cl.getNombre().equalsIgnoreCase(nombre) || cl.getApellidos().toLowerCase().contains(nombre.toLowerCase())) {
                result.add(cl);
            }
        }
        return result;
    }

    /**
     * Imprime los datos de los clientes del comercio
     * @throws ErrorDatos 
     */
    public void imprimirClientes() throws ErrorDatos {

        if (this.listarClientes().size() != 0) {
            for (Cliente cl : this.listarClientes()) {
                System.out.println("----------------------------------------");
                System.out.println("Nombre: " + cl.getNombre());
                System.out.println("Apellidos: " + cl.getApellidos());
                System.out.println("Identificador: " + cl.getId());
                System.out.println("Telefonno: " + cl.getTelefono());
                System.out.println("email: " + cl.getEmail());
                System.out.println("Fidelidad(1-5): " + cl.getNivelFidelidad());
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println();
            }
        } else {
            throw new ErrorDatos("ERROR. No hay Clientes en la lista.");
        }

    }

    /**
     * Imprime los clienytes de la lista de entrada
     * @param clientes lista de clientes a imprimir
     * @throws ErrorDatos 
     */
    public void imprimirListaClientes(List<Cliente> clientes) throws ErrorDatos {

        if (clientes.size() != 0) {
            for (Cliente cl : clientes) {
                System.out.println("----------------------------------------");
                System.out.println("Nombre: " + cl.getNombre());
                System.out.println("Apellidos: " + cl.getApellidos());
                System.out.println("Identificador: " + cl.getId());
                System.out.println("Telefonno: " + cl.getTelefono());
                System.out.println("email: " + cl.getEmail());
                System.out.println("Fidelidad(1-5): " + cl.getNivelFidelidad());
                System.out.println("----------------------------------------");
                System.out.println();
                System.out.println();
            }
        } else {
            throw new ErrorDatos("ERROR. No hay Clientes en la lista.");
        }

    }

}
