/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;

/**
 *
 * @author javsimoli
 */
public class Utils {
    
    
    /**
     * Verifica si un número es positivo si no da error
     * @param numero entero (int)
     * @return true si es positivo
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean numeroPositivo (int numero) throws ErrorDatos{
        
        if (numero < 0)
            throw new ErrorDatos ("ERROR. El numero ".concat(String.format("%d", numero)).concat(" no puede ser negativo"));
        
        return true;
        
    }     
    
    /**
     * Verifica si un número es positivo si no da error
     * @param numero entero (int)
     * @param mensaje Mensaje especifico para 
     * @return true si es positivo
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean numeroPositivo (int numero, String mensaje) throws ErrorDatos{
        
        if (numero < 0)
            throw new ErrorDatos (mensaje);
        
        return true;
        
    } 
    
     /**
     * Verifica si un número es positivo si no da error
     * @param numero entero (int)
     * @return true si es positivo
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean numeroPositivo (double numero) throws ErrorDatos{
        
        if (numero < 0)
            throw new ErrorDatos ("ERROR. El numero ".concat(String.format("%f", numero)).concat(" no puede ser negativo"));
        
        return true;
        
    }     
    
     /**
     * Verifica si un número es positivo si no da error
     * @param numero entero (double)
     * @param mensaje Mensaje especifico para 
     * @return true si es positivo
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean numeroPositivo (double numero, String mensaje) throws ErrorDatos{
        
        if (numero < 0)
            throw new ErrorDatos (mensaje);
        
        return true;
        
    }
    
    /**
     * Verifica que un String contiene caracteres
     * @param texto a comprobar
     * @return true si es válido
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean stringNoNulo (String texto) throws ErrorDatos{
        
        if (texto == null || texto.isEmpty())
            throw new ErrorDatos(" ERROR. La cadena de texto es nula o vacía.");
        
        return true;
        
    }
    
    /**
     * Verifica que un String contiene caracteres
     * @param texto a comprobar
     * @param mensaje Mensaje especifico para
     * @return true si es válido
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean stringNoNulo (String texto, String mensaje) throws ErrorDatos{
        
        if (texto == null || texto.isEmpty())
            throw new ErrorDatos(mensaje);
        
        return true;
        
    }
    
    /**
     * Verificación de un rango de IVA.
     * @param iva valor de iva
     * @return true si esta en rango
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean rangoIva (double iva) throws ErrorDatos{
          
        try {
            
            Utils.numeroPositivo(iva,"el IVA no puede ser negativo. ");
        
            if ( iva > 100)
                throw new ErrorDatos("el valor del IVA no puede ser mayor del 100%");
            
        } catch (ErrorDatos ed) {
            
            throw new ErrorDatos("ERROR. IVA fuera de rango: " + iva + "% " + ed.getMessage());
            
        }
        
        return true;
        
    }
    
     /**
     * Verificación de un rango de fidelidad.
     * @param fidelidad valor de fidelidad
     * @return true si esta en rango
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean rangoFidelidad (int fidelidad) throws ErrorDatos{
        
        if (fidelidad < 1 || fidelidad > 5)
            throw new ErrorDatos("ERROR. Valor de fidelidad fuera de rango: " + fidelidad + " (valores entre 1 - 5)");
        
        return true;
        
    }
    
    /**
     * Comprobación de email.
     * @param email email a comprobar
     * @return true si esta en rango
     * @throws ErrorDatos Excepción si da error
     */
    public static boolean isEmail (String email) throws ErrorDatos{

        try {
            
            Utils.stringNoNulo(email,"El email es nulo o vacío");
        
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
                throw new ErrorDatos("El correo electronico no tiene un formato reconoble");
            
        } catch (ErrorDatos ed) {
            
            throw new ErrorDatos("ERROR. email incorrecto: '" + email + "' " + ed.getMessage());
            
        }
        
        return true;
        
    }
    
    /**
     * Verificación de teléfono
     * @param tlfo tellefono a comprobar 
     * @return true si es válido
     * @throws ErrorDatos Excepción si da error
     */    
    public static boolean isTlfo (String tlfo) throws ErrorDatos{

        try {
            
            Utils.stringNoNulo(tlfo,"El telefono es nulo o vacío");
        
            if (!tlfo.matches("\\d{9}"))
                throw new ErrorDatos("El telefono no tiene un formato de nueve números.");
            
        } catch (ErrorDatos ed) {
            
            throw new ErrorDatos("ERROR. teléfono incorrecto: '" + tlfo + "' " + ed.getMessage());
            
        }
        
        return true;
        
    }
    
    /**
     * Busca una linea de una lista de pedido por nombre del articulo.
     * @param nombre
     * @param lista
     * @return
     * @throws ErrorDatos 
     */
    public static LineaPedido buscarLineaNombreArticulo (String nombre, List<LineaPedido> lista) throws ErrorDatos{
        
        for (LineaPedido linea: lista){
            
            if (linea.getArticulo().getNombre().equalsIgnoreCase(nombre))

                return linea;
                
            }        
        
        throw new ErrorDatos ("ERROR. No se ha encontrado el artículo en la lista");
        
    }
    
    
    /**
     * Método de busqueda de una linea de pedido. Por el campo del nombre del articulo que contiene. envia la primera  
     * @param nombre nombre del articulo a buscar
     * @param lista lista de las lineas de pedido donde buscar el articulo
     * @return indice de la lista para la linea con el articulo dado
     * @throws ErrorDatos 
     */
    public static int buscarIndiceNombreArticulo (String nombre, List<LineaPedido> lista) throws ErrorDatos{
        
        if (Utils.stringNoNulo(nombre, "El nombre del Articulo a buscar es nulo o vacío")){

            int contador = 0;
        
            for (LineaPedido linea: lista){
            
                if (linea.getArticulo().getNombre().equalsIgnoreCase(nombre))

                    return contador++;
                
            }        
        
            throw new ErrorDatos ("ERROR. No se ha encontrado el artículo en la lista");
            
        }
        
        throw new ErrorDatos("ERROR Los parametros de busqueda son incorrectos.");    
    
    }
    
     /**
     * Método de busqueda del indice de una linea de pedido. Por el campo del nombre del articulo que contiene. envia la primera  
     * @param nombre nombre del articulo a buscar
     * @param lista lista de las lineas de pedido donde buscar el articulo
     * @param inicio indicador de por donde empezar la busqueda 
     * @return indice de la lista para la linea con el articulo dado
     * @throws ErrorDatos 
     */
    public static int buscarIndiceNombreArticulo (String nombre, List<LineaPedido> lista, int inicio) throws ErrorDatos{
        
        if (Utils.stringNoNulo(nombre, "El nombre del Articulo a buscar es nulo o vacío") && Utils.numeroPositivo(inicio,"El indice de inicio de busqueda es negativo") && inicio < lista.size()){

        int contador = inicio;
        
        for (LineaPedido linea: lista){
            
            if (linea.getArticulo().getNombre().equalsIgnoreCase(nombre))

                return contador++;
                
            }        
        
        throw new ErrorDatos ("ERROR. No se ha encontrado el artículo en la lista");
        
        }
        
        throw new ErrorDatos("ERROR Los parametros de busqueda son incorrectos.");
        
    }
    
    public static double calcularIva(double precio, double iva) throws ErrorDatos {
        
        return precio + precio * iva /100;
        
    }
    
}
