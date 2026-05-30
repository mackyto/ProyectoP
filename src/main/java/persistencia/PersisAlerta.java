/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.AlertaStock; // Asegúrate de que el paquete sea el correcto
import entidades.ErrorDatos;
import interfaces.InAlerta;   // Tu interfaz para las alertas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // Para manejar la conversión de LocalDateTime a SQL
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public class PersisAlerta extends ConexionBase implements InAlerta {

    
    private static final String SQL_REPLACE_ALERTA = "REPLACE INTO AlertaStock (articulo_id, nombre_articulo, stock_actual, fecha_alerta) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM AlertaStock ORDER BY fecha_alerta DESC";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM AlertaStock WHERE articulo_id = ?";

    /**
     * Guarda o actualiza una alerta en la base de datos,
     *
     * @param a tipo AlertaStock, datos del objeto a persistir.
     * @return true si completa la transacción.
     */
    @Override
    public boolean persistirAlerta(AlertaStock a) {

        try (Connection conn = conexionDB();
             PreparedStatement ps = conn.prepareStatement(SQL_REPLACE_ALERTA)) {

            ps.setInt(1, a.getId());
            ps.setString(2, a.getNombreArticulo());
            ps.setInt(3, a.getStockActual());
            // Convertimos el LocalDateTime de Java a Timestamp de SQL
            ps.setTimestamp(4, Timestamp.valueOf(a.getFechaAlerta()));
            
            ps.executeUpdate();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Recupera todas las alertas de stock de la BD ordenadas por fecha
     * * @return Lista de objetos AlertaStock
     */
    @Override
    public List<AlertaStock> recuperarTodas() {
        List<AlertaStock> lista = new ArrayList<>();

        try (Connection conn = conexionDB(); 
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Instanciamos usando el constructor vacío que exige la persistencia
                AlertaStock a = new AlertaStock();
                
                a.setId(rs.getInt("articulo_id"));
                a.setNombreArticulo(rs.getString("nombre_articulo"));
                a.setStockActual(rs.getInt("stock_actual"));
                // Convertimos el Timestamp de MySQL de vuelta a LocalDateTime de Java
                a.setFechaAlerta(rs.getTimestamp("fecha_alerta").toLocalDateTime());
                
                lista.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Error al recuperar alertas: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public AlertaStock recuperarAlertaByID(int id) {
        AlertaStock a = null;

        try (Connection conn = conexionDB();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new AlertaStock();
                    a.setId(rs.getInt("articulo_id"));
                    a.setNombreArticulo(rs.getString("nombre_articulo"));
                    a.setStockActual(rs.getInt("stock_actual"));
                    a.setFechaAlerta(rs.getTimestamp("fecha_alerta").toLocalDateTime());
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al recuperar alerta por ID: " + e.getMessage());
        }

        return a;
    }
}