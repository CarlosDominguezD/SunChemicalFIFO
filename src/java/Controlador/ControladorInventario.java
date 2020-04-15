/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.ModeloInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorInventario {

    public LinkedList<ModeloInventario> SelectSQL(String Sql, Connection con) throws SQLException {
        LinkedList<ModeloInventario> LstModeloInventario = new LinkedList<ModeloInventario>();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloInventario modeloInventario = new ModeloInventario();
                modeloInventario.setId(res.getInt("Id"));
                modeloInventario.setMaterial(res.getString("Material"));
                modeloInventario.setMaterial_Description(res.getString("Material_Description"));
                modeloInventario.setBatch(res.getString("Batch"));
                modeloInventario.setLink_Material_Batch(res.getString("Link_Material_Batch"));
                modeloInventario.setFIFO_Cost_UNIT(res.getString("FIFO_Cost_UNIT"));

                LstModeloInventario.add(modeloInventario);
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
            //res.close();
            //SQL.close();
            //con.close();
        }

        return LstModeloInventario;
    }

}
