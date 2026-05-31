/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Cliente;
import entidades.ErrorDatos;
import interfaces.InClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public class PersisClient extends ConexionBase implements InClient {

    private static final String INSERT_PERSONA = "INSERT INTO Persona (id, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)";
    private static final String INSERT_CLIENTE = "INSERT INTO Cliente (persona_id, fidelidad, email) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM `v_cliente_only`";
        
    /**
     * Guarda los datos de un cliente en la base de datos
     *
     * @param c tipo Cliente, datos del objeto a persistir.
     * @return true si completa la transacción.
     */
    @Override
    public boolean persistirCliente(Cliente c) {

        try (Connection conn = conexionDB()) {

            PreparedStatement pps = conn.prepareStatement(INSERT_PERSONA);
            PreparedStatement cps = conn.prepareStatement(INSERT_CLIENTE);

            pps.setInt(1, c.getId());
            pps.setString(2, c.getNombre());
            pps.setString(3, c.getApellidos());
            pps.setString(4, c.getTelefono());
            pps.executeUpdate();
            cps.setInt(1, c.getId());
            cps.setInt(2, c.getNivelFidelidad());
            cps.setString(3, c.getEmail());
            cps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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

    @Override
    public List<Cliente> recuperarTodos() {
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = conexionDB(); 
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("cliente_id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setNivelFidelidad(rs.getInt("fidelidad"));
                c.setEmail(rs.getString("email"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error al recuperar clientes: " + e.getMessage());
        } catch (ErrorDatos ex) {
            System.getLogger(PersisClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return lista;
    }

}
