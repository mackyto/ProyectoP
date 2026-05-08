/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 * System.out.println("Telefonno: " + cl.getTelefono());
 * System.out.println("email: " + cl.getEmail());
 * System.out.println("Fidelidad(1-5): " + cl.getNivelFidelidad());
 * System.out.println("----------------------------------------");
 * System.out.println(); System.out.println(); } } else { throw new
 * ErrorDatos("ERROR. No hay Clientes en la lista."); }
 *
 * }
 *
 * @author Jorge
 */
import interfaces.LogicaNegocio;
import entidades.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.PersisArticulo;
import persistencia.PersisClient;

public class GestorComercio implements LogicaNegocio {

    private static GestorComercio INSTANCE;

    //ATRIBUTOS DE LAS RELACIONES
    private List<Cliente> clientes;
    private List<Articulo> articulos;
    private List<Pedido> pedidos;
    private Cliente clienteActual;
    private Pedido pedidoEnCurso;
    private PersisClient pClient;
    private PersisArticulo pArticul;

    private GestorComercio() {

        pClient = new PersisClient();
        pArticul = new PersisArticulo();
        
        this.clientes = (ArrayList<Cliente>) pClient.recuperarTodos();
        
        this.articulos = (ArrayList<Articulo>) pArticul.recuperarTodo();
        pedidos = new ArrayList<>();



        // Ajuste puntero clase Cliente para evitar colisiones
        int maxId = 0;
        for (Cliente c : clientes) {
            if (c.getId() > maxId) {
                maxId = c.getId();
            }
        }
        // Ajuste puntero clase Articulo para evitar colisiones        
        maxId = 0;
        for (Articulo a : articulos) {
            if (a.getId() > maxId) {
                maxId = a.getId();
            }
        }

        Persona.setPuntero(maxId + 1);

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
     * @param Cliente.getPuntero() variable estática, puntero de indexación de
     * clientes
     * @return Cliente Objeto creado e incorporado a la lista this.clientes
     * @throws java.sql.SQLException
     * @throws ErrorDatos
     */
    @Override
    public Cliente crearCliente(String nombre, String apellidos, String telefono, String email, int nivelFidelidad) throws SQLException, ErrorDatos {

        int id = Cliente.getPuntero();
        Cliente cl = new Cliente(email, nivelFidelidad, nombre, apellidos, telefono, id);
        if (!pClient.persistirCliente(cl))
            throw new SQLException("Error de Integridad de Datos");
        clientes.add(cl);
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
    public ProductoFisico crearProductoFisico(String nombre, double precioBase, double iva, int stock) throws SQLException, ErrorDatos {
        ProductoFisico articulo = new ProductoFisico(stock, nombre, precioBase, iva);
        if (!pArticul.persistirProducto(articulo))
            throw new SQLException("Error de Integridad de Datos");
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
    public Servicio crearServicio(String nombre, double precioBase, double iva, int minutos, boolean urgente) throws SQLException, ErrorDatos {

        Servicio servicio = new Servicio(minutos, urgente, nombre, precioBase, iva);
        if (!pArticul.persistirServicio(servicio))
            throw new SQLException("Error de Integridad de Datos");
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
    
    
    public void imprimirArticulos() throws ErrorDatos {
        for (Articulo a: this.listarArticulos())
            System.out.println(a.toString());
    } 
    
    /**
     * Imprime datos de los articulos en la lista
     *
     * @throws ErrorDatos
     */
    public void imprimirArticulosPlus() throws ErrorDatos {

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
    
    public void imprimirListaArticulos(List<Articulo> articulos) throws ErrorDatos {    
        for (Articulo a: articulos)
        System.out.println(a.toString());
    } 
    
    /**
     * Impprime datos de los articulos desde una lista
     *
     * @param articulos liste de articulos a imprimir
     * @throws ErrorDatos
     */
    public void imprimirListaArticulosPlus(List<Articulo> articulos) throws ErrorDatos {

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
     *
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
     *
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

    public Cliente selecionarCliente (int id) throws Exception {
        
        for (Cliente cl : this.listarClientes()){
            if (cl.getId() == id)
                return cl;
        }
        throw new Exception("El id del Cliente no exixte");
        
    }
    
    
    /**
     * Crea una lista de clientes por coincidencia de nombre o apellidos.
     *
     * @param nombre de los clientes a buscar.
     * @return lista de clientes con coincidencias.
     */
    public List<Articulo> buscarArticulos(String nombre) {
        List<Articulo> result = new ArrayList<>();
        for (Articulo ar : this.listarArticulos()) {
            if (ar.getNombre().equalsIgnoreCase(nombre) || ar.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                result.add(ar);
            }
        }
        return result;
    }
    
    public Articulo selecionarArticulo (int id) throws Exception {
        
        for (Articulo ar : this.listarArticulos()){
            if (ar.getId() == id)
                return ar;
        }
        throw new Exception("El id del Articulo no exixte");
        
    }
    
    
    
    
    public void imprimirClientes() throws ErrorDatos {
        for (Cliente cl: this.listarClientes())
            System.out.println(cl.toString());    
   
    }    
    
    
    /**
     * Imprime los datos de los clientes del comercio
     *
     * @throws ErrorDatos
     */
    public void imprimirClientesPlus() throws ErrorDatos {

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
     *
     * @param clientes
     */
    public void imprimirListaClientes (List<Cliente> clientes) {
        if (clientes.size() != 0) {
            for (Cliente cl : clientes) 
                System.out.println(cl.toString());
        }
    }

    /**
     * Imprime los clienytes de la lista de entrada
     *
     * @param clientes lista de clientes a imprimir
     * @throws ErrorDatos
     */
    public void imprimirListaClientesPlus (List<Cliente> clientes) throws ErrorDatos {

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
