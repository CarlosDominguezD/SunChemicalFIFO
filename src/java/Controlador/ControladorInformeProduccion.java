/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.ModeloInforme_Produccion;
import Modelos.ModeloMb51;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorInformeProduccion {

    LinkedList<ModeloInforme_Produccion> LstModeloInforme_Produccion = new LinkedList<ModeloInforme_Produccion>();
    public LinkedList<ModeloInforme_Produccion> SelectSql(String Sql, Connection con) {
        //LinkedList<ModeloInforme_Produccion> LstModeloInforme_Produccion = new LinkedList<ModeloInforme_Produccion>();
        LstModeloInforme_Produccion.clear();        
        //ConexionBDMySql conexion = new ConexionBDMySql();
        //Connection con;
        //con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloInforme_Produccion modeloInforme_Produccion = new ModeloInforme_Produccion();
                modeloInforme_Produccion.setId(res.getInt("Id"));
                modeloInforme_Produccion.setFinish_Good_sku(res.getString("Finish_Good_sku"));
                modeloInforme_Produccion.setCO_object_name(res.getString("CO_object_name"));
                modeloInforme_Produccion.setBatch_Finish_goods(res.getString("Batch_Finish_goods"));
                modeloInforme_Produccion.setLink_Terminado_Batch(res.getString("Link_Terminado_Batch"));
                modeloInforme_Produccion.setCantidad(res.getString("Cantidad"));
                modeloInforme_Produccion.setTotal_Raw_Material(res.getString("Total_Raw_Material"));
                modeloInforme_Produccion.setManufact(res.getString("Manufact"));
                modeloInforme_Produccion.setPackaging(res.getString("Packaging"));
                modeloInforme_Produccion.setConversion(res.getString("Conversion"));
                modeloInforme_Produccion.setNuevo_Valor_Orden(res.getString("Nuevo_Valor_Orden"));
                modeloInforme_Produccion.setCosto_unitario_FIFO(res.getString("Costo_unitario_FIFO"));
                modeloInforme_Produccion.setCosto_unitario_Estandar(res.getString("Costo_unitario_Estandar"));

                LstModeloInforme_Produccion.add(modeloInforme_Produccion);
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return LstModeloInforme_Produccion;
    }
}
