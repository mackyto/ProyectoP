/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 29160712r
 */
public abstract class ConexionBase {

//    protected static final String URL = "jdbc:mysql://gamvers.xyz:3306/ProyectoP?useSSL=FALSE...";
    protected static final String URL = "jdbc:mysql://gamvers.xyz:3306/ProyectoP?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    protected static final String USER = "javier";
    protected static final String PASS = "hqxjt8";

    protected Connection conexionDB() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
