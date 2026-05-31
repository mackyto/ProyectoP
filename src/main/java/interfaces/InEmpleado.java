/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Empleado;
import entidades.ErrorDatos;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public interface InEmpleado {

    public boolean persistirEmpleado(Empleado e);
    public List<Empleado> recuperarTodos() throws ErrorDatos;
    public boolean modificarEmpleado(Empleado e);
    public boolean eliminarEmpleado(int id);
    
}
