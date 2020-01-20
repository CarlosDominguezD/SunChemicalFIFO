/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloEine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorEine {

    public LinkedList<ModeloEine> ListSelectSQL(String Sql) {
        LinkedList<ModeloEine> ListModeloEine = new LinkedList<ModeloEine>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloEine modeloEine = new ModeloEine();
                modeloEine.setId(res.getInt("id"));
                modeloEine.setPurchasing_info_rec(res.getString("Purchasing_info_rec"));
                modeloEine.setPlant(res.getString("Plant"));
                modeloEine.setCreated_On(res.getString("Created_On"));
                modeloEine.setPurchasing_Group(res.getString("Purchasing_Group"));
                modeloEine.setCurrency(res.getString("Currency"));
                modeloEine.setStandard_PO_Quantity(res.getString("Standard_PO_Quantity"));
                modeloEine.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                modeloEine.setNet_Price(res.getString("Net_Price"));
                modeloEine.setPrice_unit(res.getString("Price_unit"));
                modeloEine.setOrder_Price_Unit(res.getString("Order_Price_Unit"));
                modeloEine.setValid_to(res.getString("Valid_to"));
                modeloEine.setQuantity_Conversion_1(res.getString("Quantity_Conversion_1"));
                modeloEine.setQuantity_Conversion_2(res.getString("Quantity_Conversion_2"));
                modeloEine.setEffective_Price(res.getString("Effective_Price"));
                modeloEine.setAcknowledgment_Reqd(res.getString("Acknowledgment_Reqd"));
                modeloEine.setConfirmation_Control(res.getString("Confirmation_Control"));
                modeloEine.setIncoterms_1(res.getString("Incoterms_1"));
                modeloEine.setIncoterms_2(res.getString("Incoterms_2"));
                modeloEine.setMaterial(res.getString("Material"));
                modeloEine.setVendor(res.getString("Vendor"));
                modeloEine.setLink_Material_vendor(res.getString("Link_Material_vendor"));
                modeloEine.setPorcentaje_Additional(res.getString("Porcentaje_Additional"));
                ListModeloEine.add(modeloEine);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return ListModeloEine;
    }

    public ModeloEine SelectSQL(String Sql) throws SQLException {
        ModeloEine modeloEine = null;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloEine = new ModeloEine();
                modeloEine.setId(res.getInt("id"));
                modeloEine.setPurchasing_info_rec(res.getString("Purchasing_info_rec"));
                modeloEine.setPlant(res.getString("Plant"));
                modeloEine.setCreated_On(res.getString("Created_On"));
                modeloEine.setPurchasing_Group(res.getString("Purchasing_Group"));
                modeloEine.setCurrency(res.getString("Currency"));
                modeloEine.setStandard_PO_Quantity(res.getString("Standard_PO_Quantity"));
                modeloEine.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                modeloEine.setNet_Price(res.getString("Net_Price"));
                modeloEine.setPrice_unit(res.getString("Price_unit"));
                modeloEine.setOrder_Price_Unit(res.getString("Order_Price_Unit"));
                modeloEine.setValid_to(res.getString("Valid_to"));
                modeloEine.setQuantity_Conversion_1(res.getString("Quantity_Conversion_1"));
                modeloEine.setQuantity_Conversion_2(res.getString("Quantity_Conversion_2"));
                modeloEine.setEffective_Price(res.getString("Effective_Price"));
                modeloEine.setAcknowledgment_Reqd(res.getString("Acknowledgment_Reqd"));
                modeloEine.setConfirmation_Control(res.getString("Confirmation_Control"));
                modeloEine.setIncoterms_1(res.getString("Incoterms_1"));
                modeloEine.setIncoterms_2(res.getString("Incoterms_2"));
                modeloEine.setMaterial(res.getString("Material"));
                modeloEine.setVendor(res.getString("Vendor"));
                modeloEine.setLink_Material_vendor(res.getString("Link_Material_vendor"));
                modeloEine.setPorcentaje_Additional(res.getString("Porcentaje_Additional"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
            SQL.close();
            con.close();
        }
        return modeloEine;
    }

}
