/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Articulo;
import entidades.ProductoFisico;
import entidades.Servicio;
import interfaces.InArticulo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public class PersisArticulo implements InArticulo {

    private static final String INSERTAR_ARTICULO = "INSERT INTO Articulo (id, nombre, precio_base, iva) VALUES (?, ?, ?, ?)";
    private static final String INSERTAR_SERVICIO = "INSERT INTO Servicio (articulo_id, minutos, urgente) VALUES (?, ?, ?)";
    private static final String INSERTAR_PFISICO = "INSERT INTO ProductoFisico (articulo_id, stock) VALUES (?, ?)";
    private static final String SQL_SELECT_SERVICIOS = "SELECT * FROM `v_artiservicio`";
    private static final String SQL_SELECT_PFISICOS = "SELECT * FROM `v_artifisico`";

    /**
     *
     * @param a
     * @return
     */
    @Override
    public boolean persistirArticulo(Articulo a) {

        insertarArticulo(a);

        if (a instanceof ProductoFisico) {
            return insertarFisico((ProductoFisico) a);
        } else if (a instanceof Servicio) {
            return insertarServicio((Servicio) a);
        }
        return false;
    }

    private void insertarArticulo(Articulo a) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://gamvers.xyz:3306/ProyectoP", "javier", "hqxjt8")) {

            PreparedStatement aps = conn.prepareStatement(INSERT_ARTICULO);

            aps.setInt(1, c.getId());
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

    private boolean insertarFisico(ProductoFisico productoFisico) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean insertarServicio(Servicio servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Articulo> recuperarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
