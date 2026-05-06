/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;
import entidades.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author macky
 */
public interface InClient {
    
    public boolean persistirCliente (Cliente c) throws SQLException;
    public List<Integer> listarIDClientes () throws SQLException;
    public Cliente recuperarCliennteByID (int id) throws SQLException;
    public List<Cliente> recuperarTodos();
    
}
