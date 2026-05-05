/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Cliente;
import interfaces.InClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public class pClient implements InClient {

    /**
     * Guarda los datos de un cliente en la base de datos
     * @param c tipo Cliente, datos del objeto a persistir.
     * @return true si completa la transacción.
     */
    @Override
    public boolean persistirCliente(Cliente c) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://mysql.gamvers.xyz:3306/ProyectoP", "javier", "hqxjt8Z%")) {

            PreparedStatement cps = conn.prepareStatement("INSERT INTO clientes (persona_id, fidelidad, email) VALUES (?, ?, ?)");
            PreparedStatement pps = conn.prepareStatement("INSERT INTO clientes (id, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)");
            
            pps.setInt(1, c.getId());
            pps.setString(2, c.getNombre());
            pps.setString(3, c.getApellidos());
            pps.setString(4, c.getTelefono());
            pps.executeUpdate();
            cps.setInt(1, c.getId());
            cps.setInt(2, c.getNivelFidelidad());
            cps.setString(3, c.getEmail());
            cps.executeUpdate();
        } catch (SQLException sqlException){
            
        }
        
        return true;
        
    }

    @Override
    public List<Integer> listarIDClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente recuperarCliennteByID(int id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
