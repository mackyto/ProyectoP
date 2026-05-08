/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javsimoli
 */
public class Pedido {

    private LocalDateTime fechaCreacion;
    private double total;
    private List<LineaPedido> lista;
    private Cliente cliente;

    /**
     * Constructor. básico. El valor total es calculado.
     *
     * @param cliente Objeto clase Cliente propietario del pedido.
     * @throws ErrorDatos mensaje personalizado de errores de construcción.
     */
    public Pedido(Cliente cliente) throws ErrorDatos {

        this.lista = new ArrayList<>();
        this.fechaCreacion = LocalDateTime.now();
        this.total = 0.00;
        this.cliente = cliente;

    }

    /**
     * Constructor. Con fecha de entrada para gestión diferida.
     *
     * @param Cliente objeto proietario de la relacción
     * @param fechaCreacion fecha distinta de hoy, ahora
     * @throws ErrorDatos mensaje personalizado de errores de construcción.
     */
    public Pedido(Cliente cliente, LocalDateTime fechaCreacion) throws ErrorDatos {

        if (lista.size() > 0) {
            this.fechaCreacion = fechaCreacion;
            this.total = this.calcularTotal();
            this.lista = lista;
            this.cliente = cliente;

        } else {
            throw new ErrorDatos("ERROR. Pedido inválido no tiene lineas.");
        }
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) throws ErrorDatos {
        if (Utils.numeroPositivo(total, "ERROR. El precio unitario final no puede ser negativo.")) {
            this.total = total;
        }
    }

    public List<LineaPedido> getLista() {
        return lista;
    }

    public void setLista(List<LineaPedido> lista) {
        this.lista = lista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método añade una linea de pedido al pedido.
     *
     * @param linea objeto de la clase LineaPedido a añadir.
     * @throws ErrorDatos mensaje personalizado si hay error de datos
     */
    public void añadirLinea(LineaPedido linea) throws ErrorDatos {

        if (linea != null) {
            this.lista.add(linea);
        } else {
            throw new ErrorDatos("ERROR. No se ha definido linea de pedido que añadir a la lista.");
        }

    }

    /**
     * Metodo para borrar una linea de pedido de la lista
     *
     * @param linea objeto de la clase LineaPedido a borrar
     * @throws ErrorDatos mensaje personalizado si hay error de datos
     */
    public void eliminarLinea(LineaPedido linea) throws ErrorDatos {

        if (linea != null) {
            this.lista.remove(linea);
        } else {
            throw new ErrorDatos("ERROR. No se ha definido linea de pedido que borrar a la lista.");
        }

    }

    /**
     * Metodo para borrar una linea de pedido indicando el indice de la lista de
     * lineas de pedido.
     *
     * @param indice puntero del objeto de la lista de LineaPedido a borrar
     * @throws ErrorDatos mensaje personalizado si hay error de datos
     */
    public void eliminarLinea(int indice) throws ErrorDatos {

        if (Utils.numeroPositivo(indice, "ERROR indice de lista a borrar negativo") && indice < this.lista.size()) {
            this.lista.remove(indice);
        } else {
            throw new ErrorDatos("ERROR. No se ha definido linea de pedido que borrar a la lista.");
        }

    }

    /**
     * Calcula el valor total de un pedido.
     *
     * @return resultado de sumar todas las lineas de pedido con cantidad por
     * precio unitario final
     * @throws ErrorDatos
     */
    public double calcularTotal() throws ErrorDatos {

        for (LineaPedido linea : this.getLista()) {

            this.setTotal(linea.getPrecioUnitarioFinal() * linea.getCantidad() + this.getTotal());
        }

        return total;

    }

    /**
     * Imprime toda la información de un pedido
     *
     * @param pedido el pedido que puede ser distinto del actual
     * @throws ErrorDatos
     */
    public void imprimirPedido(Pedido pedido) throws ErrorDatos {

        System.out.println();
        System.out.println();
        System.out.println("==============================================================================");
        System.out.printf("Pedido Cliente:%s %s \t\t\t\t\tfecha: %s\n\n",
                pedido.getCliente().getNombre(),
                pedido.getCliente().getApellidos(),
                pedido.getFechaCreacion());
        System.out.println("______________________________________________________________________________");
        for (LineaPedido lp : pedido.getLista()) {

        }
        System.out.println("______________________________________________________________________________");
        System.out.printf("\t\t\t\t\t\t\t\t\t\t%6s\n\n", (this.calcularTotal()));
    }

    /**
     * Imprime solo las cabeceras de pedído
     *
     * @param pedido pedido de entrada que puede ser distinto de this
     * @throws ErrorDatos
     */
    public void imprimirCabezeraPedido(Pedido pedido) throws ErrorDatos {
        System.out.printf("Pedido Cliente:%s %s \t\tfecha: %s\t\t%6s\n",
                pedido.getCliente().getNombre(),
                pedido.getCliente().getApellidos(),
                pedido.getFechaCreacion(),
                (pedido.calcularTotal()));
    }

    /**
     * Convierte los datos de cabezera del pedido en una linea imprimible
     *
     * @return String con los datos de pedido en una linea y un separador
     * @throws ErrorDatos gestion de Execpciones de calcularTotal()
     */
    @Override
    public String toString() {
        String mensaje = "";
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = this.getFechaCreacion().format(formato);

            mensaje = String.format("Pedido Cliente:%-20s %-40s \tfecha: %s Total: %8.2f\n",
                    this.getCliente().getNombre(),
                    this.getCliente().getApellidos(),
                    fechaFormateada,
                    this.calcularTotal());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mensaje;
    }

    public String toStringCabezera() {

        String mensaje = String.format("Cantidad  %-50s %10s %10s ", " Nombre", "PrecioUnid", "Precio");
        return mensaje;

    }

}
