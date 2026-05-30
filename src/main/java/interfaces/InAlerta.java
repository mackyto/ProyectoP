/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.AlertaStock;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 29160712r
 */
public interface InAlerta {
    
    public boolean persistirAlerta(AlertaStock a) throws SQLException;
    public List<AlertaStock> recuperarTodas() throws SQLException;
    public AlertaStock recuperarAlertaByID(int id) throws SQLException;
    
}
