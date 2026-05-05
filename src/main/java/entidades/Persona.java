/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author javsimoli
 */
public abstract class Persona extends EntidadBase {
    
    private String nombre;
    private String apellidos;
    private String telefono;
    
    private static int puntero = 0;
    
    /**
     * Constructor Persona calculando el id único
     * @param nombre de la persona
     * @param apellidos de la persona
     * @param telefono de la persona
     * @param id código único incremental.
     * @throws ErrorDatos 
     */
    public Persona(String nombre, String apellidos, String telefono) throws ErrorDatos {

        super(++puntero);
        
        if (Utils.stringNoNulo(nombre, "ERROR. El nombre es nulo no vacío."))
            this.nombre = nombre;
        
        if (Utils.stringNoNulo(apellidos, "ERROR. El apellido es nulo no vacío."))
            this.apellidos = apellidos;
        
        if (Utils.isTlfo(telefono))
            this.telefono = telefono;
        
    }

    /**
     * Constructor Persona con todos los parametros
     * @param nombre de la persona
     * @param apellidos de la persona
     * @param telefono de la persona
     * @param id código único incremental.
     * @throws ErrorDatos 
     */
    public Persona(String nombre, String apellidos, String telefono, int id) throws ErrorDatos {          
            
        super(id);
        
        if (Utils.stringNoNulo(nombre, "ERROR. El nombre es nulo no vacío."))
            this.nombre = nombre;
        
        if (Utils.stringNoNulo(nombre, "ERROR. El apellido es nulo no vacío."))
            this.apellidos = apellidos;
        
        if (Utils.isTlfo(telefono))
            this.telefono = telefono;
       
        if (puntero <= id)
            puntero = id +1;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws ErrorDatos {
        if (Utils.stringNoNulo(nombre, "ERROR. El nombre es nulo no vacío."))
            this.nombre = nombre;
    }

    public String getApellidos() {

        return apellidos;
    }

    public void setApellidos(String apellidos) throws ErrorDatos {
        if (Utils.stringNoNulo(nombre, "ERROR. El apellido es nulo no vacío."))        
            this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws ErrorDatos {
        if (Utils.isTlfo(telefono))
            this.telefono = telefono;
    }

    public static int getPuntero() {
        return puntero++;
    }
    
}
