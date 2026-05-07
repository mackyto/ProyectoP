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

            PreparedStatement aps = conn.prepareStatement(INSERTAR_ARTICULO);

            aps.setInt(1, a.getId());
            aps.setString(2, a.getNombre());
            aps.setDouble(3, a.getPrecioBase());
            aps.setDouble(4, a.getIva());
            aps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    private boolean insertarFisico(ProductoFisico p) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://gamvers.xyz:3306/ProyectoP", "javier", "hqxjt8")) {

            PreparedStatement pps = conn.prepareStatement(INSERTAR_PFISICO);

            pps.setInt(1, p.getId());
            pps.setDouble(2, p.precioUnitarioFinal());
            pps.setDouble(3, p.getIva());
            pps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return true;

    }

    private boolean insertarServicio(Servicio s) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://gamvers.xyz:3306/ProyectoP", "javier", "hqxjt8")) {

            PreparedStatement sps = conn.prepareStatement(INSERTAR_SERVICIO);

            sps.setInt(1, s.getId());
            sps.setInt(2, s.getMinutos());
            sps.setBoolean(3, s.isUrgente());
            sps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return true;

    }

    @Override
    public List<Articulo> recuperarTodo() {

        List<Articulo> lista = new ArrayList<>();

        try {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://gamvers.xyz:3306/ProyectoP", "javier", "hqxjt8");
                    PreparedStatement ps = conn.prepareStatement(SQL_SELECT_SERVICIOS);
                    ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Servicio s = new Servicio();
                    s.setId(rs.getInt("id"));
                    s.setNombre(rs.getString("nombre"));
                    s.setPrecioBase(rs.getDouble("precio_base"));
                    s.setIva(rs.getDouble("iva"));
                    s.setMinutos(rs.getInt("minutos"));
                    s.setUrgente(rs.getBoolean("urgente"));
                    lista.add(s);
                }

            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://gamvers.xyz:3306/ProyectoP", "javier", "hqxjt8");
                    PreparedStatement ps = conn.prepareStatement(SQL_SELECT_PFISICOS);
                    ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    ProductoFisico pf = new ProductoFisico();
                    pf.setId(rs.getInt("id"));
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
