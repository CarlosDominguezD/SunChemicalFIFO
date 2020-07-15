/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Conexiones.Pool;
import Modelos.ModeloMb51_Consumos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorMb51_Consumos {

    LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = new LinkedList<ModeloMb51_Consumos>();

    public LinkedList<ModeloMb51_Consumos> SelectSQL(String Sql, Connection con) throws SQLException {

        LstModeloMb51_Consumos.clear();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMb51_Consumos modeloMb51_Consumos = new ModeloMb51_Consumos();
                modeloMb51_Consumos.setId(res.getInt("Id"));
                modeloMb51_Consumos.setPlant(res.getString("Plant"));
                modeloMb51_Consumos.setPurchase_order(res.getString("Purchase_order"));
                modeloMb51_Consumos.setMaterial(res.getString("Material"));
                modeloMb51_Consumos.setMaterial_Description(res.getString("Material_Description"));
                modeloMb51_Consumos.setBatch(res.getString("Batch"));
                modeloMb51_Consumos.setMovement_type(res.getString("Movement_type"));
                modeloMb51_Consumos.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloMb51_Consumos.setItem(res.getString("Item"));
                modeloMb51_Consumos.setQuantity(res.getString("Quantity"));
                modeloMb51_Consumos.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloMb51_Consumos.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51_Consumos.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51_Consumos.setCurrency(res.getString("Currency"));
                modeloMb51_Consumos.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51_Consumos.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51_Consumos.setDocument_Date(res.getString("Document_Date"));
                modeloMb51_Consumos.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51_Consumos.setUser_Name(res.getString("User_Name"));
                modeloMb51_Consumos.setVendor(res.getString("Vendor"));
                modeloMb51_Consumos.setOrder_(res.getString("Order_"));
                LstModeloMb51_Consumos.add(modeloMb51_Consumos);
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
        return LstModeloMb51_Consumos;
    }

    

}
