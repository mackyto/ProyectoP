/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2526;

import interfaces.LogicaNegocio;
import logica.GestorComercio;
import entidades.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jorge
 */
public class Main {

    public static Scanner kl = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //GeneradorArticulos generador = GeneradorArticulos.getInstancia();
        //GeneradorClientes generarCli = GeneradorClientes.getInstancia();
        LogicaNegocio comercio = GestorComercio.getInstance();
        GestorComercio gestor = (GestorComercio) comercio;

        String opcion = "";

        do {

            try {

                System.out.println("Menú");
                System.out.println("1 - Crear cliente.");
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Crear Articulo");
                System.out.println("4 - Listar Articulos");
                System.out.println("5 -");
                System.out.println("6 - ");
                System.out.println("7 - ");
                System.out.println("Q - Salir.");

                opcion = kl.nextLine();

                switch (opcion) {

                    case "1":
                        crearCliente(gestor);
                        break;
                    case "2":
                        gestor.imprimirClientes();
                        break;
                    case "3":
                        crearArticulo(gestor);
                        break;
                    case "4":
                        gestor.imprimirArticulos();
//                        generador.mostrarEstadisticas();
                        break;
                    case "5": ;
                        break;
                    case "6": ;
                        break;
                    case "7": ;
                        break;

                }

//                generador.generarArticulos(comercio, 100, 70);
//                generador.mostrarEstadisticas();
//                generarCli.crearClientes(comercio, 80);
                gestor.imprimirListaClientes(gestor.buscarClientes("javier"));

                //gestor.imprimirClientes();
                //gestor.imprimirArticulos();
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());

            } catch (ErrorDatos er) {
                System.out.println(er.getMessage());
            }

        } while (!opcion.equalsIgnoreCase("q"));

    }

    /**
     * Imprime cantidad de articulos generados.
     *
     * @param generador instancia uníca generadora de articulos.
     * @throws entidades.ErrorDatos
     */
    public static void listarArticulos(GeneradorArticulos generador) throws ErrorDatos {

        // Obtener listas específicas
        List<ProductoFisico> productos = generador.getProductosFisicos();
        List<Servicio> servicios = generador.getServicios();

        System.out.println("\nProductos físicos generados: " + productos.size());
        System.out.println("Servicios generados: " + servicios.size());

    }

    /**
     * Solicita datos de cliente
     *
     * @param gestor
     * @return
     */
    public static boolean crearCliente(GestorComercio gestor) throws SQLException {

        try {

            System.out.print("Nombre: ");
            String nombre = kl.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = kl.nextLine();
            System.out.print("telefono: ");
            String telefono = kl.nextLine();
            System.out.print("Fidelidad (1-5): ");
            int fidelidad = Integer.parseInt(kl.nextLine());
            System.out.print("email: ");
            String email = kl.nextLine();

            gestor.crearCliente(nombre, apellidos, telefono, email, fidelidad);

        } catch (ErrorDatos ed) {
            ed.printStackTrace();
        }

        return true;

    }

    public static boolean crearArticulo(GestorComercio gestor) throws SQLException {

        try {

            System.out.println("Tipo de Articulo");
            System.out.println("-------------------------");
            System.out.println("P - Producto.");
            System.out.println("S - Servicio.");
            System.out.println("Q - Volver al Menú.");
            System.out.println("-------------------------");
            char opcion = kl.nextLine().trim().toLowerCase().charAt(0);
            if (opcion == 's') {
                System.out.println("Servicio");
            } else if (opcion == 'p'){
                System.out.println("Producto Físico");
            } else {
                return false;
            }
            System.out.println("-------------------------");
            System.out.print("Nombre: ");
            String nombre = kl.nextLine();
            System.out.print("Precio Base: ");
            double precio = Double.parseDouble(kl.nextLine());
            System.out.print("IVA aplicable ");
            double iva = Double.parseDouble(kl.nextLine());

            switch (opcion) {

                case 'p': {
                    System.out.print("Stock de Producto: ");
                    int stock = Integer.parseInt(kl.nextLine());

                    gestor.crearProductoFisico(nombre, precio, iva, stock);
                }
                
                break;
                case 's': {
                    System.out.print("Tiempo de ejecución (minutos): ");
                    int minutos = Integer.parseInt(kl.nextLine());
                    System.out.print("Es un Sericio Urgente (Y/N): ");
                    char urgente = kl.nextLine().trim().toLowerCase().charAt(0);
                    if (urgente == 'y')
                        gestor.crearServicio(nombre, precio, iva, minutos, true);
                    else 
                        gestor.crearServicio(nombre, precio, iva, minutos, false);
                }
                
                break;

            }

        } catch (ErrorDatos ed) {
            ed.printStackTrace();
        }

        return true;

    }

}
