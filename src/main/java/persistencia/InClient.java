/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import entidades.*;
import java.util.List;

/**
 *
 * @author macky
 */
public interface InClient {
    
    public boolean persistirCliente (Cliente c);
    public List<Integer> listarIDClientes ();
    public Cliente recuperarCliennteByID (int id);
    
    
}
