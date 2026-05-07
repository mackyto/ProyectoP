/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Articulo;
import entidades.Cliente;
import entidades.ErrorDatos;
import entidades.ProductoFisico;
import entidades.Servicio;
import interfaces.InArticulo;
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
public class PersisArticulo extends ConexionBase implements InArticulo {

    private static final String INSERTAR_ARTICULO = "INSERT INTO Articulo (id, nombre, precio_base, iva) VALUES (?, ?, ?, ?)";
    private static final String INSERTAR_SERVICIO = "INSERT INTO Servicio (articulo_id, minutos, urgente) VALUES (?, ?, ?)";
    private static final String INSERTAR_PFISICO = "INSERT INTO ProductoFisico (articulo_id, stock) VALUES (?, ?)";
    private static final String SQL_SELECT_SERVICIOS = "SELECT * FROM `v_artiservicio`";
    private static final String SQL_SELECT_PFISICOS = "SELECT * FROM `v_artifisico`";


    @Override
    public boolean persistirProducto(ProductoFisico p) {
        try (Connection conn = conexionDB()) {
            conn.setAutoCommit(false);
            insertarArticuloBase(p, conn); // Paso 1

            try (PreparedStatement pps = conn.prepareStatement(INSERTAR_PFISICO)) {
                pps.setInt(1, p.getId());
                pps.setInt(2, p.getStock());
                pps.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException sqle) {
            // Si algo falla, aquí no se guarda nada
            sqle.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean persistirServicio(Servicio s) {
        try (Connection conn = conexionDB()) {
            conn.setAutoCommit(false);

            insertarArticuloBase(s, conn); // Paso 1

            try (PreparedStatement sps = conn.prepareStatement(INSERTAR_SERVICIO)) {
                sps.setInt(1, s.getId());
                sps.setInt(2, s.getMinutos());
                sps.setBoolean(3, s.isUrgente());
                sps.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

// Este es el método que ambos comparten, pero recibe la conexión abierta
    private void insertarArticuloBase(Articulo a, Connection conn) throws SQLException {
        try (PreparedStatement aps = conn.prepareStatement(INSERTAR_ARTICULO)) {
            aps.setInt(1, a.getId());
            aps.setString(2, a.getNombre());
            aps.setDouble(3, a.getPrecioBase());
            aps.setDouble(4, a.getIva());
            aps.executeUpdate();
        }
    }

    @Override
    public List<Articulo> recuperarTodo() {

        List<Articulo> lista = new ArrayList<>();

        try {
            try (Connection conn = conexionDB(); 
            PreparedStatement ps = conn.prepareStatement(SQL_SELECT_SERVICIOS); 
            ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Servicio s = new Servicio();
                    s.setId(rs.getInt("articulo_id"));
                    s.setNombre(rs.getString("nombre"));
                    s.setPrecioBase(rs.getDouble("precio_base"));
                    s.setIva(rs.getDouble("iva"));
                    s.setMinutos(rs.getInt("minutos"));
                    s.setUrgente(rs.getBoolean("urgente"));
                    lista.add(s);
                }

            }

            try (Connection conn = conexionDB(); 
            PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PFISICOS); 
            ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    ProductoFisico pf = new ProductoFisico();
                    pf.setId(rs.getInt("articulo_id"));
                    pf.setNombre(rs.getString("nombre"));
                    pf.setPrecioBase(rs.getDouble("precio_base"));
                    pf.setIva(rs.getDouble("iva"));
                    pf.setStock(rs.getInt("stock"));
                    lista.add(pf);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al recuperar clientes: " + e.getMessage());
        } catch (ErrorDatos ex) {
            System.getLogger(PersisClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return lista;
    }

}
