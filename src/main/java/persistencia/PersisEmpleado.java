/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Empleado;
import entidades.ErrorDatos;
import interfaces.InEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de persistencia para la entidad Empleado utilizando JDBC nativo. Maneja
 * transacciones atómicas para la inserción y actualización en cascada.
 *
 * * @author 29160712r
 */
public class PersisEmpleado extends ConexionBase implements InEmpleado {

    private static final String INSERT_PERSONA = "INSERT INTO Persona (id, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)";
    private static final String INSERT_CLIENTE = "INSERT INTO Cliente (persona_id, fidelidad, email) VALUES (?, ?, ?)";
    private static final String INSERT_EMPLEADO = "INSERT INTO Empleado (persona_id, dni, nss, puesto, calle, numero, ciudad, provincia, cp, categoria, grupo, nivel, fecha_contrato, antiguedad_anterior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PERSONA_EMP = "UPDATE Persona SET nombre = ?, apellidos = ?, telefono = ? WHERE id = ?";
    private static final String UPDATE_CLIENTE_EMP = "UPDATE Cliente SET fidelidad = ?, email = ? WHERE persona_id = ?";
    private static final String UPDATE_EMPLEADO = "UPDATE Empleado SET dni = ?, nss = ?, puesto = ?, calle = ?, numero = ?, ciudad = ?, provincia = ?, cp = ?, categoria = ?, grupo = ?, nivel = ?, fecha_contrato = ?, antiguedad_anterior = ? WHERE persona_id = ?";

    private static final String DELETE_EMPLEADO_ONLY = "DELETE FROM Empleado WHERE persona_id = ?";

    private static final String SQL_SELECT_ALL = "SELECT * FROM v_empleado";

    
        /**
     * Guarda los datos de un empleado que ya era cliente en la base de datos
     * actualizando secuencialmente las tablas Persona y Cliente y rellenando
     * la tabla Empleado.
     *
     * @param e tipo Empleado, datos del objeto a persistir.
     * @return true si completa con éxito la transacción.
     */
    @Override
    public boolean persistirSoloEmpleado(Empleado e) {

        try (Connection conn = conexionDB()) {
            conn.setAutoCommit(false); // Transacción atómica

            try (PreparedStatement pps = conn.prepareStatement(UPDATE_PERSONA_EMP); PreparedStatement cps = conn.prepareStatement(UPDATE_CLIENTE_EMP); PreparedStatement eps = conn.prepareStatement(INSERT_EMPLEADO)) {

                // 1. Tabla Persona
                pps.setInt(1, e.getId());
                pps.setString(2, e.getNombre());
                pps.setString(3, e.getApellidos());
                pps.setString(4, e.getTelefono());
                pps.executeUpdate();

                // 2. Tabla Cliente
                cps.setInt(1, e.getId());
                cps.setInt(2, e.getNivelFidelidad());
                cps.setString(3, e.getEmail());
                cps.executeUpdate();

                // 3. Tabla Empleado
                eps.setInt(1, e.getId());
                eps.setString(2, e.getDni());
                eps.setString(3, e.getNss());
                eps.setString(4, e.getPuesto());
                eps.setString(5, e.getCalle());
                eps.setString(6, e.getNumero());
                eps.setString(7, e.getCiudad());
                eps.setString(8, e.getProvincia());
                eps.setString(9, e.getCp());
                eps.setInt(10, e.getCategoria());
                eps.setInt(11, e.getGrupo());
                eps.setInt(12, e.getNivel());

                if (e.getFechaContrato() != null) {
                    eps.setObject(13, e.getFechaContrato());
                } else {
                    eps.setNull(13, Types.DATE);
                }

                // Al ser primitivo double, pasamos el valor directamente (si no tiene, pasará 0.0)
                eps.setDouble(14, e.getAntiguedadAnterior());

                eps.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException sqle) {
                conn.rollback();
                System.err.println("Error en la transacción de Empleado. Transacción revertida (Rollback).");
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            System.err.println("Error de conexión a la base de datos: " + sqle.getMessage());
        }

        return false;
    }
    
    
    /**
     * Guarda los datos de un empleado en la base de datos rellenando
     * secuencialmente las tablas Persona, Cliente y Empleado.
     *
     * @param e tipo Empleado, datos del objeto a persistir.
     * @return true si completa con éxito la transacción.
     */
    @Override
    public boolean persistirEmpleado(Empleado e) {

        try (Connection conn = conexionDB()) {
            conn.setAutoCommit(false); // Transacción atómica

            try (PreparedStatement pps = conn.prepareStatement(INSERT_PERSONA); PreparedStatement cps = conn.prepareStatement(INSERT_CLIENTE); PreparedStatement eps = conn.prepareStatement(INSERT_EMPLEADO)) {

                // 1. Tabla Persona
                pps.setInt(1, e.getId());
                pps.setString(2, e.getNombre());
                pps.setString(3, e.getApellidos());
                pps.setString(4, e.getTelefono());
                pps.executeUpdate();

                // 2. Tabla Cliente
                cps.setInt(1, e.getId());
                cps.setInt(2, e.getNivelFidelidad());
                cps.setString(3, e.getEmail());
                cps.executeUpdate();

                // 3. Tabla Empleado
                eps.setInt(1, e.getId());
                eps.setString(2, e.getDni());
                eps.setString(3, e.getNss());
                eps.setString(4, e.getPuesto());
                eps.setString(5, e.getCalle());
                eps.setString(6, e.getNumero());
                eps.setString(7, e.getCiudad());
                eps.setString(8, e.getProvincia());
                eps.setString(9, e.getCp());
                eps.setInt(10, e.getCategoria());
                eps.setInt(11, e.getGrupo());
                eps.setInt(12, e.getNivel());

                if (e.getFechaContrato() != null) {
                    eps.setObject(13, e.getFechaContrato());
                } else {
                    eps.setNull(13, Types.DATE);
                }

                // Al ser primitivo double, pasamos el valor directamente (si no tiene, pasará 0.0)
                eps.setDouble(14, e.getAntiguedadAnterior());

                eps.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException sqle) {
                conn.rollback();
                System.err.println("Error en la transacción de Empleado. Transacción revertida (Rollback).");
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            System.err.println("Error de conexión a la base de datos: " + sqle.getMessage());
        }

        return false;
    }

    /**
     * Recupera todos los empleados haciendo uso de la vista relacional
     * v_empleado.
     *
     * @return List de objetos Empleado.
     * @throws entidades.ErrorDatos
     */
    @Override
    public List<Empleado> recuperarTodos() {
        List<Empleado> lista = new ArrayList<>();

        try (Connection conn = conexionDB(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado e = new Empleado();

                // Datos de Persona (Heredados en Empleado)
                e.setId(rs.getInt("empleado_id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellidos(rs.getString("apellidos"));
                e.setTelefono(rs.getString("telefono"));

                // Datos de Cliente (Heredados en Empleado)
                e.setNivelFidelidad(rs.getInt("fidelidad"));
                e.setEmail(rs.getString("email"));

                // Datos específicos de Empleado
                e.setDni(rs.getString("dni"));
                e.setNss(rs.getString("nss"));
                e.setPuesto(rs.getString("puesto"));
                e.setCalle(rs.getString("calle"));
                e.setNumero(rs.getString("numero"));
                e.setCiudad(rs.getString("ciudad"));
                e.setProvincia(rs.getString("provincia"));
                e.setCp(rs.getString("cp"));
                e.setCategoria(rs.getInt("categoria"));
                e.setGrupo(rs.getInt("grupo"));
                e.setNivel(rs.getInt("nivel"));

                // Recuperación de la fecha de contrato (long/BIGINT)
                e.setFechaContrato(rs.getObject("fecha_contrato", java.time.LocalDate.class));

                // Al usar primitivo double, rs.getDouble ya asigna 0.0 automáticamente si es NULL en la BD
                e.setAntiguedadAnterior(rs.getDouble("antiguedad_anterior"));

                lista.add(e);
            }

        } catch (SQLException e) {
            System.err.println("Error al recuperar empleados: " + e.getMessage());
        } catch (ErrorDatos ex) {
            java.util.logging.Logger.getLogger(PersisEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return lista;
    }

    /**
     * Modifica las tres tablas correspondientes al empleado de manera segura
     * bajo control transaccional.
     *
     * * @param e tipo Empleado con las modificaciones realizadas.
     * @return true si la operación es exitosa.
     */
    @Override
    public boolean modificarEmpleado(Empleado e) {
        try (Connection conn = conexionDB()) {
            conn.setAutoCommit(false); // Transacción atómica

            try (PreparedStatement pps = conn.prepareStatement(UPDATE_PERSONA_EMP); PreparedStatement cps = conn.prepareStatement(UPDATE_CLIENTE_EMP); PreparedStatement eps = conn.prepareStatement(UPDATE_EMPLEADO)) {

                // 1. Modificar Persona
                pps.setString(1, e.getNombre());
                pps.setString(2, e.getApellidos());
                pps.setString(3, e.getTelefono());
                pps.setInt(4, e.getId());
                pps.executeUpdate();

                // 2. Modificar Cliente (Heredado por Empleado)
                cps.setInt(1, e.getNivelFidelidad());
                cps.setString(2, e.getEmail());
                cps.setInt(3, e.getId());
                cps.executeUpdate();

                // 3. Modificar Empleado
                eps.setString(1, e.getDni());
                eps.setString(2, e.getNss());
                eps.setString(3, e.getPuesto());
                eps.setString(4, e.getCalle());
                eps.setString(5, e.getNumero());
                eps.setString(6, e.getCiudad());
                eps.setString(7, e.getProvincia());
                eps.setString(8, e.getCp());
                eps.setInt(9, e.getCategoria());
                eps.setInt(10, e.getGrupo());
                eps.setInt(11, e.getNivel());

                if (e.getFechaContrato() != null) {
                    eps.setObject(12, e.getFechaContrato());
                } else {
                    eps.setNull(12, Types.DATE);
                }

                // Asignación directa del double primitivo (Parámetro 13)
                eps.setDouble(13, e.getAntiguedadAnterior());

                // Cláusula WHERE persona_id = ? (Parámetro 14)
                eps.setInt(14, e.getId());
                eps.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException sqle) {
                conn.rollback();
                System.err.println("Error al modificar empleado. Transacción revertida.");
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            System.err.println("Error de conexión: " + sqle.getMessage());
        }
        return false;
    }

    /**
     * Elimina exclusivamente el registro laboral de la tabla Empleado.
     *
     * * @param id Identificador único (persona_id) del empleado a remover.
     * @return true si el borrado se efectúa con éxito.
     */
    @Override
    public boolean eliminarEmpleado(int id) {
        try (Connection conn = conexionDB(); PreparedStatement ps = conn.prepareStatement(DELETE_EMPLEADO_ONLY)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }
}
