/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;
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
     * @param cliente Objeto clase Cliente propietario del pedido.
     * @throws ErrorDatos mensaje personalizado de errores de construcción.
     */
    public Pedido(Cliente cliente) throws ErrorDatos {

        if (lista.size() > 0){
        this.fechaCreacion = LocalDateTime.now();
        this.total = this.calcularTotal();
        this.lista = lista;
        this.cliente = cliente;
        
        }else{
            throw new ErrorDatos ("ERROR. Pedido inválido no tiene lineas.");
        }
        
    }


    /**
     * Constructor. Con fecha de entrada para gestión diferida.
     * @param Cliente objeto proietario de la relacción
     * @param fechaCreacion fecha distinta de hoy, ahora
     * @throws ErrorDatos mensaje personalizado de errores de construcción.
     */
    public Pedido(Cliente cliente, LocalDateTime fechaCreacion) throws ErrorDatos {

        if (lista.size() > 0){
        this.fechaCreacion = fechaCreacion;
        this.total = this.calcularTotal();
        this.lista = lista;
        this.cliente = cliente;
        
        }else{
            throw new ErrorDatos ("ERROR. Pedido inválido no tiene lineas.");
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
     * @return resultado de sumar todas las lineas de pedido con cantidad por precio unitario final
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
            if (lp.getArticulo() instanceof ProductoFisico pf) {
                System.out.printf("%s\t%s\t\t\t\t%s\t%s\t\t%s\t%s\n",
                        this.tabuladorNumeros(lp.getCantidad()),
                        pf.getNombre(),
                        this.tabuladorNumeros(pf.getIva()),
                        this.tabuladorNumeros(pf.getPrecioBase()),
                        this.tabuladorNumeros(lp.getPrecioUnitarioFinal()),
                        this.tabuladorNumeros(lp.getPrecioUnitarioFinal() * lp.getCantidad()));
            }else if (lp.getArticulo() instanceof Servicio sr){
                
                String urge = "";
                
                if (sr.isUrgente()){
                    urge = "Urgente";
                }else{urge = "Normal";}
                
                System.out.printf("%s\t%s\t\t\t%s\t%s\t%s\t\t%s\t%s\n",
                        this.tabuladorNumeros(sr.getMinutos()),
                        sr.getNombre(),
                        urge,
                        this.tabuladorNumeros(sr.getIva()),
                        this.tabuladorNumeros(sr.getPrecioBase()),
                        this.tabuladorNumeros(lp.getPrecioUnitarioFinal()),
                        this.tabuladorNumeros(lp.getPrecioUnitarioFinal() * lp.getCantidad()));
                
            }
        }
        System.out.println("______________________________________________________________________________");
        System.out.printf("\t\t\t\t\t\t\t\t\t\t%s\n\n", this.tabuladorNumeros(this.calcularTotal()));
    }

    /**
     * Imprime solo las cabeceras de pedído
     * @param pedido pedido de entrada que puede ser distinto de this
     * @throws ErrorDatos 
     */
    public void imprimirCabezeraPedido (Pedido pedido) throws ErrorDatos {
        System.out.printf("Pedido Cliente:%s %s \t\tfecha: %s\t\t%s\n",
            pedido.getCliente().getNombre(),
            pedido.getCliente().getApellidos(), 
            pedido.getFechaCreacion(),
            this.tabuladorNumeros(pedido.calcularTotal()));    
    }
    
    
    /**
     * Tabula números para imprimir
     * @param numero version para numero entero
     * @return cadena de texto con el valor de un numero siempre con la misma cantidad de caracteres
     */
    private String tabuladorNumeros(int numero) {

        String resul = String.format("%.2f", numero);
        if (numero < 10) {
            resul = " " + resul;
        }
        if (numero < 100) {
            resul = " " + resul;
        }
        if (numero < 1000) {
            resul = " " + resul;
        }
        if (numero < 10000) {
            resul = " " + resul;
        }
        if (numero < 100000) {
            resul = " " + resul;
        }
        if (numero < 1000000) {
            resul = " " + resul;
        }
        if (numero < 10000000) {
            resul = " " + resul;
        }

        return resul;

    }

     /**
     * Tabula números para imprimir
     * @param numero version para numero entero
     * @return cadena de texto con el valor de un numero siempre con la misma cantidad de caracteres
     */
    private String tabuladorNumeros(double numero) {

        String resul = String.format("%.2f", numero);
        if (numero < 10) {
            resul = " " + resul;
        }
        if (numero < 100) {
            resul = " " + resul;
        }
        if (numero < 1000) {
            resul = " " + resul;
        }
        if (numero < 10000) {
            resul = " " + resul;
        }
        if (numero < 100000) {
            resul = " " + resul;
        }
        if (numero < 1000000) {
            resul = " " + resul;
        }
        if (numero < 10000000) {
            resul = " " + resul;
        }

        return resul;

    }

}
