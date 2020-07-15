/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloKob1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorKob1 {

    public LinkedList<ModeloKob1> Select(String Sql) {
        LinkedList<ModeloKob1> lstModeloKob1 = new LinkedList<ModeloKob1>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloKob1 modeloKob1 = new ModeloKob1();
                modeloKob1.setId(res.getInt("Id"));
                modeloKob1.setFunctional_Area(res.getString("Functional_Area"));
                modeloKob1.setCompany_Code(res.getString("Company_Code"));
                modeloKob1.setOrder_(res.getString("Order_"));
                modeloKob1.setCO_object_name(res.getString("CO_object_name"));
                modeloKob1.setCost_Element(res.getString("Cost_Element"));
                modeloKob1.setCost_element_name(res.getString("Cost_element_name"));
                modeloKob1.setMaterial(res.getString("Material"));
                modeloKob1.setMaterial_Description(res.getString("Material_Description"));
                modeloKob1.setPlant(res.getString("Plant"));
                modeloKob1.setPeriod(res.getString("Period"));
                modeloKob1.setFiscal_Year(res.getString("Fiscal_Year"));
                modeloKob1.setDr_Cr_indicator(res.getString("Dr_Cr_indicator"));
                modeloKob1.setTotal_Quantity(res.getString("Total_Quantity"));
                modeloKob1.setUnit_of_Measure(res.getString("Unit_of_Measure"));
                modeloKob1.setValue_TranCurr(res.getString("Value_TranCurr"));
                modeloKob1.setTransaction_Currency(res.getString("Transaction_Currency"));
                modeloKob1.setValue_in_Obj_Crcy(res.getString("Value_in_Obj_Crcy"));
                modeloKob1.setObject_Currency(res.getString("Object_Currency"));
                modeloKob1.setDocument_Number(res.getString("Document_Number"));
                modeloKob1.setLink_Plant_Material(res.getString("Link_Plant_Material"));
                modeloKob1.setLink_Material_orden(res.getString("Link_Material_orden"));
                modeloKob1.setBatch_consumo(res.getString("Batch_consumo"));
                modeloKob1.setLink_Material_Batch(res.getString("Link_Material_Batch"));
                modeloKob1.setMaterial_Type_Components(res.getString("Material_Type_Components"));
                modeloKob1.setProcur_Type(res.getString("Procur_Type"));
                modeloKob1.setLevel_1(res.getString("Level_1"));
                modeloKob1.setFinish_Good_sku(res.getString("Finish_Good_sku"));
                modeloKob1.setMat_Type_Unfinish_Goods(res.getString("Mat_Type_Unfinish_Goods"));
                modeloKob1.setBatch_Finish_goods(res.getString("Batch_Finish_goods"));
                modeloKob1.setLink_Terminado_Batch(res.getString("Link_Terminado_Batch"));
                modeloKob1.setCost_Unit_Estandar(res.getString("Cost_Unit_Estandar"));
                modeloKob1.setCantidad_Terminada(res.getString("Cantidad_Terminada"));
                modeloKob1.setCost_Unit_Fifo_Old(res.getString("Cost_Unit_Fifo_Old"));
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack(res.getString("Cost_Unit_Fifo_R_Mat_Pack"));
                modeloKob1.setX(res.getString("x"));
                modeloKob1.setTotal_Raw_Material(res.getString("Total_Raw_Material"));
                modeloKob1.setManufact_Materials(res.getString("Manufact_Materials"));
                modeloKob1.setPackaging_Materials(res.getString("Packaging_Materials"));
                modeloKob1.setConversion_Cost(res.getString("Conversion_Cost"));
                modeloKob1.setNuevo_Valor_Orden(res.getString("Nuevo_Valor_Orden"));
                modeloKob1.setMonth(res.getString("Month"));

                lstModeloKob1.add(modeloKob1);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        return lstModeloKob1;
    }

    public boolean UpdateList(LinkedList<ModeloKob1> listModeloKob1, Connection con) throws SQLException {
        boolean resul = false;
//        ConexionBDMySql conexion = new ConexionBDMySql();
//        Connection con;
//        con = conexion.abrirConexion();

        int Contador = 0;
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE KOB1 SET "
                    + "Functional_Area = ?, "
                    + "Company_Code = ?, "
                    + "Order_ = ?, "
                    + "CO_object_name = ?, "
                    + "Cost_Element = ?, "
                    + "Cost_element_name = ?, "
                    + "Material = ?, "
                    + "Material_Description = ?, "
                    + "Plant = ?, "
                    + "Period = ?, "
                    + "Fiscal_Year = ?, "
                    + "Dr_Cr_indicator = ?, "
                    + "Total_Quantity = ?, "
                    + "Unit_of_Measure = ?, "
                    + "Value_TranCurr = ?, "
                    + "Transaction_Currency = ?, "
                    + "Value_in_Obj_Crcy = ?, "
                    + "Object_Currency = ?, "
                    + "Document_Number = ?, "
                    + "Link_Plant_Material = ?, "
                    + "Link_Material_orden = ?, "
                    + "Batch_consumo = ?, "
                    + "Link_Material_Batch = ?, "
                    + "Material_Type_Components = ?, "
                    + "Procur_Type = ?, "
                    + "Level_1 = ?, "
                    + "Finish_Good_sku = ?, "
                    + "Mat_Type_Unfinish_Goods = ?, "
                    + "Batch_Finish_goods = ?, "
                    + "Link_Terminado_Batch = ?, "
                    + "Cost_Unit_Estandar = ?, "
                    + "Cantidad_Terminada = ?, "
                    + "Cost_Unit_Fifo_Old = ?, "
                    + "Cost_Unit_Fifo_R_Mat_Pack = ?, "
                    + "x = ?, "
                    + "Total_Raw_Material = ?, "
                    + "Manufact_Materials = ?, "
                    + "Packaging_Materials = ?, "
                    + "Conversion_Cost = ?, "
                    + "Nuevo_Valor_Orden = ?, "
                    + "Month = ? "
                    + " WHERE Id = ? ");
            for (ModeloKob1 modeloKob1 : listModeloKob1) {
                SQL.setString(1, modeloKob1.getFunctional_Area());
                SQL.setString(2, modeloKob1.getCompany_Code());
                SQL.setString(3, modeloKob1.getOrder_());
                SQL.setString(4, modeloKob1.getCO_object_name());
                SQL.setString(5, modeloKob1.getCost_Element());
                SQL.setString(6, modeloKob1.getCost_element_name());
                SQL.setString(7, modeloKob1.getMaterial());
                SQL.setString(8, modeloKob1.getMaterial_Description());
                SQL.setString(9, modeloKob1.getPlant());
                SQL.setString(10, modeloKob1.getPeriod());
                SQL.setString(11, modeloKob1.getFiscal_Year());
                SQL.setString(12, modeloKob1.getDr_Cr_indicator());
                SQL.setString(13, modeloKob1.getTotal_Quantity());
                SQL.setString(14, modeloKob1.getUnit_of_Measure());
                SQL.setString(15, modeloKob1.getValue_TranCurr());
                SQL.setString(16, modeloKob1.getTransaction_Currency());
                SQL.setString(17, modeloKob1.getValue_in_Obj_Crcy());
                SQL.setString(18, modeloKob1.getObject_Currency());
                SQL.setString(19, modeloKob1.getDocument_Number());
                SQL.setString(20, modeloKob1.getLink_Plant_Material());
                SQL.setString(21, modeloKob1.getLink_Material_orden());
                SQL.setString(22, modeloKob1.getBatch_consumo());
                SQL.setString(23, modeloKob1.getLink_Material_Batch());
                SQL.setString(24, modeloKob1.getMaterial_Type_Components());
                SQL.setString(25, modeloKob1.getProcur_Type());
                SQL.setString(26, modeloKob1.getLevel_1());
                SQL.setString(27, modeloKob1.getFinish_Good_sku());
                SQL.setString(28, modeloKob1.getMat_Type_Unfinish_Goods());
                SQL.setString(29, modeloKob1.getBatch_Finish_goods());
                SQL.setString(30, modeloKob1.getLink_Terminado_Batch());
                SQL.setString(31, modeloKob1.getCost_Unit_Estandar());
                SQL.setString(32, modeloKob1.getCantidad_Terminada());
                SQL.setString(33, modeloKob1.getCost_Unit_Fifo_Old());
                SQL.setString(34, modeloKob1.getCost_Unit_Fifo_R_Mat_Pack());
                SQL.setString(35, modeloKob1.getX());
                SQL.setString(36, modeloKob1.getTotal_Raw_Material());
                SQL.setString(37, modeloKob1.getManufact_Materials());
                SQL.setString(38, modeloKob1.getPackaging_Materials());
                SQL.setString(39, modeloKob1.getConversion_Cost());
                SQL.setString(40, modeloKob1.getNuevo_Valor_Orden());
                SQL.setString(41, modeloKob1.getMonth());
                SQL.setInt(42, modeloKob1.getId());
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }

                Contador++;
                if (Contador == 1000) {

                    System.out.println("Vuelta " + Contador + " - " + new Date());

                    Contador = 0;
                }

            }
            SQL.close();
//            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
            SQL.close();
//            con.close();
        }
        return resul;
    }

    public boolean UpdateList_Masivo(LinkedList<ModeloKob1> listModeloKob1, Connection con) throws SQLException {
        boolean resul = false;

        StringBuilder Cadena = new StringBuilder();

        int Contador = 0;
        PreparedStatement SQL = null;
        try {
            for (ModeloKob1 modeloKob1 : listModeloKob1) {
                Cadena
                        .append("UPDATE KOB1 ")
                        .append("SET Functional_Area = ").append(Valor(modeloKob1.getFunctional_Area())).append(", ")
                        .append("Company_Code = ").append(Valor(modeloKob1.getCompany_Code())).append(", ")
                        .append("Order_ = ").append(Valor(modeloKob1.getOrder_())).append(", ")
                        .append("CO_object_name = ").append(Valor(modeloKob1.getCO_object_name())).append(", ")
                        .append("Cost_Element = ").append(Valor(modeloKob1.getCost_Element())).append(", ")
                        .append("Cost_element_name = ").append(Valor(modeloKob1.getCost_element_name())).append(", ")
                        .append("Material = ").append(Valor(modeloKob1.getMaterial())).append(", ")
                        .append("Material_Description = ").append(Valor(modeloKob1.getMaterial_Description())).append(", ")
                        .append("Plant = ").append(Valor(modeloKob1.getPlant())).append(", ")
                        .append("Period = ").append(Valor(modeloKob1.getPeriod())).append(", ")
                        .append("Fiscal_Year = ").append(Valor(modeloKob1.getFiscal_Year())).append(", ")
                        .append("Dr_Cr_indicator = ").append(Valor(modeloKob1.getDr_Cr_indicator())).append(", ")
                        .append("Total_Quantity = ").append(Valor(modeloKob1.getTotal_Quantity())).append(", ")
                        .append("Unit_of_Measure = ").append(Valor(modeloKob1.getUnit_of_Measure())).append(", ")
                        .append("Value_TranCurr = ").append(Valor(modeloKob1.getValue_TranCurr())).append(", ")
                        .append("Transaction_Currency = ").append(Valor(modeloKob1.getTransaction_Currency())).append(", ")
                        .append("Value_in_Obj_Crcy = ").append(Valor(modeloKob1.getValue_in_Obj_Crcy())).append(", ")
                        .append("Object_Currency = ").append(Valor(modeloKob1.getObject_Currency())).append(", ")
                        .append("Document_Number = ").append(Valor(modeloKob1.getDocument_Number())).append(", ")
                        .append("Link_Plant_Material = ").append(Valor(modeloKob1.getLink_Plant_Material())).append(", ")
                        .append("Link_Material_orden = ").append(Valor(modeloKob1.getLink_Material_orden())).append(", ")
                        .append("Batch_consumo = ").append(Valor(modeloKob1.getBatch_consumo())).append(", ")
                        .append("Link_Material_Batch = ").append(Valor(modeloKob1.getLink_Material_Batch())).append(", ")
                        .append("Material_Type_Components = ").append(Valor(modeloKob1.getMaterial_Type_Components())).append(", ")
                        .append("Procur_Type = ").append(Valor(modeloKob1.getProcur_Type())).append(", ")
                        .append("Level_1 = ").append(Valor(modeloKob1.getLevel_1())).append(", ")
                        .append("Finish_Good_sku = ").append(Valor(modeloKob1.getFinish_Good_sku())).append(", ")
                        .append("Mat_Type_Unfinish_Goods = ").append(Valor(modeloKob1.getMat_Type_Unfinish_Goods())).append(", ")
                        .append("Batch_Finish_goods = ").append(Valor(modeloKob1.getBatch_Finish_goods())).append(", ")
                        .append("Link_Terminado_Batch = ").append(Valor(modeloKob1.getLink_Terminado_Batch())).append(", ")
                        .append("Cost_Unit_Estandar = ").append(Valor(modeloKob1.getCost_Unit_Estandar())).append(", ")
                        .append("Cantidad_Terminada = ").append(Valor(modeloKob1.getCantidad_Terminada())).append(", ")
                        .append("Cost_Unit_Fifo_Old = ").append(Valor(modeloKob1.getCost_Unit_Fifo_Old())).append(", ")
                        .append("Cost_Unit_Fifo_R_Mat_Pack = ").append(Valor(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack())).append(", ")
                        .append("X = ").append(Valor(modeloKob1.getX())).append(", ")
                        .append("Total_Raw_Material = ").append(Valor(modeloKob1.getTotal_Raw_Material())).append(", ")
                        .append("Manufact_Materials = ").append(Valor(modeloKob1.getManufact_Materials())).append(", ")
                        .append("Packaging_Materials = ").append(Valor(modeloKob1.getPackaging_Materials())).append(", ")
                        .append("Conversion_Cost = ").append(Valor(modeloKob1.getConversion_Cost())).append(", ")
                        .append("Nuevo_Valor_Orden = ").append(Valor(modeloKob1.getNuevo_Valor_Orden())).append(", ")
                        .append("Month = ").append(Valor(modeloKob1.getMonth())).append(" ")
                        .append(" WHERE Id = ").append(modeloKob1.getId()).append("; ");

                Contador++;
                if (Contador == 100) {

                    System.out.println("Vuelta " + Contador + " - " + new Date());

                    Contador = 0;

                    SQL = con.prepareStatement(Cadena + "");

                    if (SQL.execute()) {
                        resul = true;
                        Cadena.delete(0, Cadena.length());
                    }

                }

            }
            SQL.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
            SQL.close();
        }

        return resul;
    }

    public StringBuilder Valor(String Valor) {
        StringBuilder Cadena = new StringBuilder();

        if (Valor == null) {
            Cadena.append(Valor);

        } else {
            Cadena.append("'").append(Valor).append("'");
        }

        return Cadena;
    }

}
