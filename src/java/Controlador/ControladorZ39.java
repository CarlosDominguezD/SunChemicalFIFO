/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloZ39;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorZ39 {
        public LinkedList<ModeloZ39> Select(String Sql, Connection con) {
        LinkedList<ModeloZ39> modeloZ39s = new LinkedList<ModeloZ39>();
        //ConexionBDMySql conexion = new ConexionBDMySql();
        //Connection con;
        //con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloZ39 modeloZ39 = new ModeloZ39();
                modeloZ39.setId(res.getInt("id"));
                modeloZ39.setPlant(res.getString("Plant"));
                modeloZ39.setPurchase_order(res.getString("Purchase_order"));
                modeloZ39.setMaterial(res.getString("Material"));
                modeloZ39.setMaterial_Description(res.getString("Material_Description"));
                modeloZ39.setBatch(res.getString("Batch"));
                modeloZ39.setMovement_type(res.getString("Movement_type"));
                modeloZ39.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloZ39.setItem(res.getString("Item"));
                modeloZ39.setQuantity(res.getString("Quantity"));
                modeloZ39.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloZ39.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloZ39.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloZ39.setCurrency(res.getString("Currency"));
                modeloZ39.setStorage_Location(res.getString("Storage_Location"));
                modeloZ39.setPosting_Date(res.getString("Posting_Date"));
                modeloZ39.setDocument_Date(res.getString("Document_Date"));
                modeloZ39.setMaterial_Document(res.getString("Material_Document"));
                modeloZ39.setUser_Name(res.getString("User_Name"));
                modeloZ39.setVendor(res.getString("Vendor"));
                modeloZ39.setOrder1(res.getString("Order1"));                
                modeloZ39s.add(modeloZ39);
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloZ39s;
    }
}
