/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloConversiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorConversiones {

    public ModeloConversiones Select(String Sql, Connection con) {
        ModeloConversiones modeloConversiones = new ModeloConversiones();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloConversiones.setId(res.getInt("id"));
                modeloConversiones.setAno(res.getString("ano"));
                modeloConversiones.setMes(res.getString("mes"));
                modeloConversiones.setConversion_labor(res.getString("conversion_labor"));
                modeloConversiones.setConversion_machine(res.getString("conversion_machine"));
                modeloConversiones.setConversion_ovhds(res.getString("conversion_ovhds"));
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorconversiones" + e);
        }
        return modeloConversiones;
    }
}
