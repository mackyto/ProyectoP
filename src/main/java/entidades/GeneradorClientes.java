/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import logica.GestorComercio;
import interfaces.LogicaNegocio;
import java.util.ArrayList;
import java.util.List;
import entidades.Utils;
import java.sql.SQLException;

/**
 *
 * @author macky
 */
public class GeneradorClientes {

    private List<Cliente> clientes;

    String[] apellidosTodos = {
        "García", "Rodríguez", "González", "Fernández", "López",
        "Martínez", "Sánchez", "Pérez", "Gómez", "Martín",
        "Jiménez", "Ruiz", "Hernández", "Díaz", "Moreno",
        "Álvarez", "Muñoz", "Romero", "Alonso", "Gutiérrez",
        "Navarro", "Torres", "Domínguez", "Vázquez", "Ramos",
        "Gil", "Ramírez", "Serrano", "Blanco", "Molina",
        "Morales", "Suárez", "Ortega", "Delgado", "Castro",
        "Ortiz", "Rubio", "Marín", "Sanz", "Iglesias",
        "Núñez", "Medina", "Garrido", "Santos", "Castillo",
        "Cortés", "Lozano", "Guerrero", "Cano", "Prieto", "Simarro"
    };

    String[] nombres = {
        "Ana", "Carlos", "María", "Juan", "Laura", "Pedro", "Sofía", "Diego",
        "Valentina", "Miguel", "Camila", "José", "Fernanda", "Andrés", "Lucía",
        "Javier", "Daniela", "Alejandro", "Paula", "Sergio", "Andrea", "Ricardo",
        "Gabriela", "Fernando", "Isabella", "Javier"};

    String[] dominiosEmail = {
        "@correofalso.com", "@mailtest.net", "@emailprueba.org", "@dominiofake.es",
        "@tempemail.io", "@testmail.co", "@falsocorreo.com", "@emaildemo.net",
        "@pruebaweb.org", "@demoemail.es", "@gamvers.xyz"};

    /**
     * Constructor instancia única.
     */
    private static GeneradorClientes instancia;

    /**
     * Generador de clientes.
     */
    private GeneradorClientes() {
        clientes = new ArrayList<>();
    }

    /**
     * Getter instancia única generador de clientes. 
     * @return 
     */
    public static GeneradorClientes getInstancia() {
        if (instancia == null) {
            instancia = new GeneradorClientes();
        }
        return instancia;
    }

    /**
     * Generador de clientes automatizado
     * @param comercio objeto al que se añadiran los clientes
     * @param cantidad cantidad de clientes a generar.
     * @throws ErrorDatos 
     */
    public void crearClientes(LogicaNegocio comercio, int cantidad) throws ErrorDatos, SQLException {

        GestorComercio gestor = (GestorComercio) comercio;
        
        Utils.numeroPositivo(cantidad, "ERROR La cantidad de Clientes a generar, no puede ser un número negativo");
        
        if (cantidad > 1000)
            throw new ErrorDatos("ERROR. La cantidad solicitada de clientes a generar, supera el millar");
        
        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[(int) (Math.random() * 25)];
            //System.out.println(nombre);
            String apellido1 = apellidosTodos[(int) (Math.random() * 50)];
            //System.out.println(apellido1);
            String apellido2 = apellidosTodos[(int) (Math.random() * 50)];
            //System.out.println(apellido2);
            String apellidos = apellido1 + " " + apellido2;
            String email = this.emailFormat(nombre, apellido1, apellido2, i);
            //System.out.println(email);
            String telefono = this.newTelefono();
            //System.out.println(telefono);
            if (gestor.buscarCliente(nombre, apellidos) == null) {
                gestor.crearCliente(nombre, apellidos, telefono, email, (int) (Math.random() * 5 + 1));
            }
        }

        gestor.crearCliente("Javier", "Simarro Olivares", "686972866", "jsimarro@gamvers.xyz", 5);
        
        System.out.println(gestor.listarClientes().size() + " Registros de clientes");
    }

    /**
     * Genera DNIs aválidos automaticamente
     * @return DNI válido (String)
     */
    public static String newDNI() {

        int numero = (int) (Math.random() * 40000000 + 1000000);
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        char letra = letras[numero % 23];
        String dni = "" + numero + letra;

        return dni;

    }

    /**
     * Genera telefonos
     * @return Teléfono válido (String)
     */
    public String newTelefono() {

        //numero fijo
        int numero = (int) (Math.random() * 90000000 + 910000000);

        // la mitad numeros de movil
        if (Math.random() > 0.5) {
            numero = (int) (Math.random() * 100000000 + 600000000);
        }

        String telefono = "" + numero;

        return telefono;

    }

    /**
     * Generador de cuentas email automáticas
     * @param nombre nombre del usuario
     * @param apellido1 primer apellido del usuario.
     * @param apellido2 segundo apellido del usuario
     * @param indice indice único para evitar repeticiones.
     * @return 
     */
    public String emailFormat(String nombre, String apellido1, String apellido2, int indice) {

        String email = nombre + apellido1.substring(0, 3) + apellido2.substring(0, 3);
        email = email
                .replace("á", "a").replace("é", "e").replace("í", "i")
                .replace("ó", "o").replace("ú", "u").replace("Á", "A")
                .replace("É", "E").replace("Í", "I").replace("Ó", "O")
                .replace("Ú", "U").replace("ñ", "n").replace("Ñ", "N");

        email = email.toLowerCase() + indice + dominiosEmail[(int) (Math.random() * 10)];
        
        return email;

    }

}
