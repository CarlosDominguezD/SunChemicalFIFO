/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.ModeloPovr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorPovr {

    public LinkedList<ModeloPovr> SelectSQL(String Sql, Connection con) throws SQLException {
        LinkedList<ModeloPovr> LstModeloPovr = new LinkedList<ModeloPovr>();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloPovr modeloPovr = new ModeloPovr();
                modeloPovr.setId(res.getInt("Id"));
                modeloPovr.setPlant(res.getString("Plant"));
                modeloPovr.setMaterial(res.getString("Material"));
                modeloPovr.setMaterial_Description(res.getString("Material_Description"));
                modeloPovr.setProcess_Order_Number(res.getString("Process_Order_Number"));
                modeloPovr.setProcess_Order_Type(res.getString("Process_Order_Type"));
                modeloPovr.setMaterial_Group_Packaging(res.getString("Material_Group_Packaging"));
                modeloPovr.setProfit_Center(res.getString("Profit_Center"));
                modeloPovr.setUOM(res.getString("UOM"));
                modeloPovr.setMaterial_type(res.getString("Material_type"));
                modeloPovr.setExisting_Material_Costing_Lot_Size(res.getString("Existing_Material_Costing_Lot_Size"));
                modeloPovr.setMRP_Planned_Quantity(res.getString("MRP_Planned_Quantity"));
                modeloPovr.setConsumption_Qty_Actual_I_P(res.getString("Consumption_Qty_Actual_I_P"));
                modeloPovr.setDelivered_Qty_Actual_O_P(res.getString("Delivered_Qty_Actual_O_P"));
                modeloPovr.setYield_Variance_Qty(res.getString("Yield_Variance_Qty"));
                modeloPovr.setYield_Variance_Porcentaje(res.getString("Yield_Variance_Porcentaje"));
                modeloPovr.setActual_Material_Cost(res.getString("Actual_Material_Cost"));
                modeloPovr.setLabor_Cost_Actual(res.getString("Labor_Cost_Actual"));
                modeloPovr.setMachine_Cost_Actual(res.getString("Machine_Cost_Actual"));
                modeloPovr.setOverhead_Cost_Actual(res.getString("Overhead_Cost_Actual"));
                modeloPovr.setTotal_Cost_Actual_Output(res.getString("Total_Cost_Actual_Output"));
                modeloPovr.setPhysical_Inventory_Yield_Loss_cost_Actu(res.getString("Physical_Inventory_Yield_Loss_cost_Actu"));
                modeloPovr.setStandard_Price(res.getString("Standard_Price"));
                modeloPovr.setCalculated_Production_Variance(res.getString("Calculated_Production_Variance"));
                modeloPovr.setConversion_Cost_Per_Unit(res.getString("Conversion_Cost_Per_Unit"));
                modeloPovr.setDisplay_Currency(res.getString("Display_Currency"));
                modeloPovr.setBatch_Number(res.getString("Batch_Number"));
                modeloPovr.setProduct_Hierarchy(res.getString("Product_Hierarchy"));
                modeloPovr.setActual_Start_Date(res.getString("Actual_Start_Date"));
                modeloPovr.setActual_Finish_Date(res.getString("Actual_Finish_Date"));

                LstModeloPovr.add(modeloPovr);
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

        return LstModeloPovr;
    }
}
