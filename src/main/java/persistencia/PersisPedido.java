/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Articulo;
import entidades.Cliente;
import entidades.ErrorDatos;
import entidades.LineaPedido;
import entidades.Pedido;
import entidades.ProductoFisico;
import interfaces.InPedido;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author macky
 */
public class PersisPedido extends ConexionBase implements InPedido {

    private static final String INSERT_PEDIDO = "INSERT INTO Pedido (fecha, precio_total, cliente_id) VALUES (?, ?, ?)";
    private static final String INSERT_LINEA = "INSERT INTO LineasPedido (pedido_id, numero_linea, articulo_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PEDIDOS_CLIENTE = "SELECT * FROM Pedido WHERE cliente_id = ?";
    private static final String SELECT_LINEAS = "SELECT * FROM LineasPedido WHERE pedido_id = ?";

    /**
     * Persiste Pedidos y lineas de pedido
     *
     * @param p
     * @return 
     * 
     * true si se ha completrado la inserción
     */
    @Override
    public boolean persistirPedido(Pedido p) {
        try (Connection conn = conexionDB()) {
            conn.setAutoCommit(false); // Todo o nada

            int idPedidoGenerado = -1;

            try (PreparedStatement psP = conn.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS)) {
                psP.setTimestamp(1, Timestamp.valueOf(p.getFechaCreacion()));

                try {
                    psP.setDouble(2, p.calcularTotal());
                } catch (ErrorDatos ex) {
                    psP.setDouble(2, 0.0);
                }

                psP.setInt(3, p.getCliente().getId());
                psP.executeUpdate();

                try (ResultSet rs = psP.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPedidoGenerado = rs.getInt(1);
                    }
                }
            }

            if (idPedidoGenerado == -1) {
                throw new SQLException("ID de pedido no generado.");
            }


            try (PreparedStatement psL = conn.prepareStatement(INSERT_LINEA)) {
                int numLinea = 1;
                for (LineaPedido lp : p.getLista()) {
                    psL.setInt(1, idPedidoGenerado);
                    psL.setInt(2, numLinea++);
                    psL.setInt(3, lp.getArticulo().getId());
                    psL.setInt(4, lp.getCantidad());
                    psL.setDouble(5, lp.getPrecioUnitarioFinal());
                    psL.executeUpdate();
                }
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al persistir pedido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera pedidos y Lineas de Pedido
     *
     * @param cliente
     */
    @Override
    public void recuperarPedidos(Cliente cliente, List<Articulo> ar) {
        try (Connection conn = conexionDB(); PreparedStatement psP = conn.prepareStatement(SELECT_PEDIDOS_CLIENTE)) {

            psP.setInt(1, cliente.getId());
            try (ResultSet rsP = psP.executeQuery()) {
                while (rsP.next()) {
                    int idPedBD = rsP.getInt("id");
                    LocalDateTime fecha = rsP.getTimestamp("fecha").toLocalDateTime();
                    double precioBD = rsP.getDouble("precio_total");
                    try {

                        Pedido pedido = new Pedido(cliente);
                        pedido.setFechaCreacion(fecha);
                        pedido.setTotal(precioBD);
                        cargarLineas(idPedBD, pedido, ar, conn);

                        cliente.listarPedidos().add(pedido);
                    } catch (ErrorDatos ex) {
                        System.err.println("Error reconstruyendo pedido: " + ex.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarLineas(int idPedBD, Pedido pedido, List<Articulo> articulos, Connection conn) throws SQLException {
        try (PreparedStatement psL = conn.prepareStatement(SELECT_LINEAS)) {
            psL.setInt(1, idPedBD);
            try (ResultSet rsL = psL.executeQuery()) {
                while (rsL.next()) {
                    int idArt = rsL.getInt("articulo_id");
                    int cant = rsL.getInt("cantidad");

                    Articulo art = articulos.stream()
                            .filter(a -> a.getId() == idArt)
                            .findFirst().orElse(null);

                    if (art != null) {
                        try {

                            LineaPedido lp = new LineaPedido(cant, art, pedido);
                            pedido.añadirLinea(lp);
                        } catch (ErrorDatos ex) {
                            System.err.println("Línea omitida: " + ex.getMessage());
                        }
                    }
                }
            }
        }
    }

}
