/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloInventarioInicial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorInventarioInicial {

    LinkedList<ModeloInventarioInicial> Lista_ModeloInventarioInicial = new LinkedList<ModeloInventarioInicial>();
    public LinkedList<ModeloInventarioInicial> SelectListSql(String Sql, Connection con) {
        //LinkedList<ModeloInventarioInicial> Lista_ModeloInventarioInicial = new LinkedList<ModeloInventarioInicial>();
        Lista_ModeloInventarioInicial.clear();
        //ConexionBDMySql conexion = new ConexionBDMySql();
        //Connection con;
        //con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);

            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloInventarioInicial modeloInventarioInicial = new ModeloInventarioInicial();
                modeloInventarioInicial.setId(res.getInt("id"));
                modeloInventarioInicial.setMaterial(res.getString("Material"));
                modeloInventarioInicial.setMaterial_Description(res.getString("Material_Description"));
                modeloInventarioInicial.setBatch(res.getString("Batch"));
                modeloInventarioInicial.setLink_Material_Batch(res.getString("Link_Material_Batch"));
                modeloInventarioInicial.setFIFO_Cost_UNIT(res.getString("FIFO_Cost_UNIT"));

                Lista_ModeloInventarioInicial.add(modeloInventarioInicial);

            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        return Lista_ModeloInventarioInicial;

    }
    
    public LinkedList<ModeloInventarioInicial> SelectListSql_pro(String Sql, Connection con) {
        //LinkedList<ModeloInventarioInicial> Lista_ModeloInventarioInicial = new LinkedList<ModeloInventarioInicial>();
        Lista_ModeloInventarioInicial.clear();
        //ConexionBDMySql conexion = new ConexionBDMySql();
        //Connection con;
        //con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);

            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloInventarioInicial modeloInventarioInicial = new ModeloInventarioInicial();
//                modeloInventarioInicial.setId(res.getInt("id"));
//                modeloInventarioInicial.setMaterial(res.getString("Material"));
//                modeloInventarioInicial.setMaterial_Description(res.getString("Material_Description"));
//                modeloInventarioInicial.setBatch(res.getString("Batch"));
//                modeloInventarioInicial.setLink_Material_Batch(res.getString("Link_Material_Batch"));
                modeloInventarioInicial.setFIFO_Cost_UNIT(res.getString("FIFO_Cost_UNIT"));

                Lista_ModeloInventarioInicial.add(modeloInventarioInicial);

            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        return Lista_ModeloInventarioInicial;

    }

}
