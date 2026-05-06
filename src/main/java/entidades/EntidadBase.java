/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import interfaces.Identificable;

/**
 *
 * @author javsimoli
 */
public abstract class EntidadBase implements Identificable {
    
    private int id;

    /**
     * Constructor vacío para recuperacion de datos persistidos
     */
    public EntidadBase() {
    }
    
    /**
     * Constructor cin identificador como parrametro
     * @param id identificador
     * @throws ErrorDatos mensaje dedicado
     */
    public EntidadBase(int id) throws ErrorDatos {
        
        if (Utils.numeroPositivo(id, "Error. El identificador no puede ser negativo."))
            this.id = id;
        
    }

    /**
     * Getter ID
     * @return identificador
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Setter identificador
     * @param identificador (int)
     * @throws ErrorDatos 
     */
    @Override
    public void setId(int identificador) throws ErrorDatos {
        if (Utils.numeroPositivo(id, "Error. El identificador no puede ser negativo.")){
            this.id = identificador;
            if (identificador >= Persona.getPuntero())
                Persona.setPuntero(identificador + 1);
        }
    }
    
}
