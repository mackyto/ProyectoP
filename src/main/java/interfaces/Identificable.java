/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package interfaces;

import entidades.ErrorDatos;

/**
 *
 * @author macky
 */
public interface Identificable {
    
    /**
     * Getter identificador
     * @return identificador (int)
     */
    public int getId () throws ErrorDatos;
    
     /**
     * Setter identificador
     * @param identificador (int)
     */   
    public void setId (int identificador) throws ErrorDatos;
    
}
