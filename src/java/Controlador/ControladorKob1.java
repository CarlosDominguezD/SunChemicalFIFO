/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Herramienta.Herramienta;
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

    Herramienta herramienta = new Herramienta();
    
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
            //Cadena.append(Valor);
            Cadena.append("");

        } else {
            //Cadena.append("'").append(Valor).append("'");
            Cadena.append(Valor);

        }

        return Cadena;
    }

    public boolean UpdateList_Carlos(LinkedList<ModeloKob1> listModeloKob1, Connection con, String Proceso, String Porcentaje) throws SQLException {
        boolean resul = false;
//        ConexionBDMySql conexion = new ConexionBDMySql();
//        Connection con;
//        con = conexion.abrirConexion();

        int CantidadRegistros = listModeloKob1.size();

        ControladorCargaPlanosProduccion cargaPlanosProduccion = new ControladorCargaPlanosProduccion();
// CADD Creo las variables Pendiente OJO
        StringBuilder Functional_Area = new StringBuilder();
        StringBuilder Company_Code = new StringBuilder();
        StringBuilder Order_ = new StringBuilder();
        StringBuilder CO_object_name = new StringBuilder();
        StringBuilder Cost_Element = new StringBuilder();
        StringBuilder Cost_element_name = new StringBuilder();
        StringBuilder Material = new StringBuilder();
        StringBuilder Material_Description = new StringBuilder();
        StringBuilder Plant = new StringBuilder();
        StringBuilder Period = new StringBuilder();
        StringBuilder Fiscal_Year = new StringBuilder();
        StringBuilder Dr_Cr_indicator = new StringBuilder();
        StringBuilder Total_Quantity = new StringBuilder();
        StringBuilder Unit_of_Measure = new StringBuilder();
        StringBuilder Value_TranCurr = new StringBuilder();
        StringBuilder Transaction_Currency = new StringBuilder();
        StringBuilder Value_in_Obj_Crcy = new StringBuilder();
        StringBuilder Object_Currency = new StringBuilder();
        StringBuilder Document_Number = new StringBuilder();
        StringBuilder Link_Plant_Material = new StringBuilder();
        StringBuilder Link_Material_orden = new StringBuilder();
        StringBuilder Batch_consumo = new StringBuilder();
        StringBuilder Link_Material_Batch = new StringBuilder();
        StringBuilder Material_Type_Components = new StringBuilder();
        StringBuilder Procur_Type = new StringBuilder();
        StringBuilder Level_1 = new StringBuilder();
        StringBuilder Finish_Good_sku = new StringBuilder();
        StringBuilder Mat_Type_Unfinish_Goods = new StringBuilder();
        StringBuilder Batch_Finish_goods = new StringBuilder();
        StringBuilder Link_Terminado_Batch = new StringBuilder();
        StringBuilder Cost_Unit_Estandar = new StringBuilder();
        StringBuilder Cantidad_Terminada = new StringBuilder();
        StringBuilder Cost_Unit_Fifo_Old = new StringBuilder();
        StringBuilder Cost_Unit_Fifo_R_Mat_Pack = new StringBuilder();
        StringBuilder X = new StringBuilder();
        StringBuilder Total_Raw_Material = new StringBuilder();
        StringBuilder Manufact_Materials = new StringBuilder();
        StringBuilder Packaging_Materials = new StringBuilder();
        StringBuilder Conversion_Cost = new StringBuilder();
        StringBuilder Nuevo_Valor_Orden = new StringBuilder();
        StringBuilder Month = new StringBuilder();
        StringBuilder IdArchivo = new StringBuilder();
        StringBuilder Id = new StringBuilder();
//CADD Inicno las Variables con el formato predefinido Ojo si se puede unir con el codigo de arriba 
        Functional_Area.append("(CASE id ");
        Company_Code.append("(CASE id ");
        Order_.append("(CASE id ");
        CO_object_name.append("(CASE id ");
        Cost_Element.append("(CASE id ");
        Cost_element_name.append("(CASE id ");
        Material.append("(CASE id ");
        Material_Description.append("(CASE id ");
        Plant.append("(CASE id ");
        Period.append("(CASE id ");
        Fiscal_Year.append("(CASE id ");
        Dr_Cr_indicator.append("(CASE id ");
        Total_Quantity.append("(CASE id ");
        Unit_of_Measure.append("(CASE id ");
        Value_TranCurr.append("(CASE id ");
        Transaction_Currency.append("(CASE id ");
        Value_in_Obj_Crcy.append("(CASE id ");
        Object_Currency.append("(CASE id ");
        Document_Number.append("(CASE id ");
        Link_Plant_Material.append("(CASE id ");
        Link_Material_orden.append("(CASE id ");
        Batch_consumo.append("(CASE id ");
        Link_Material_Batch.append("(CASE id ");
        Material_Type_Components.append("(CASE id ");
        Procur_Type.append("(CASE id ");
        Level_1.append("(CASE id ");
        Finish_Good_sku.append("(CASE id ");
        Mat_Type_Unfinish_Goods.append("(CASE id ");
        Batch_Finish_goods.append("(CASE id ");
        Link_Terminado_Batch.append("(CASE id ");
        Cost_Unit_Estandar.append("(CASE id ");
        Cantidad_Terminada.append("(CASE id ");
        Cost_Unit_Fifo_Old.append("(CASE id ");
        Cost_Unit_Fifo_R_Mat_Pack.append("(CASE id ");
        X.append("(CASE id ");
        Total_Raw_Material.append("(CASE id ");
        Manufact_Materials.append("(CASE id ");
        Packaging_Materials.append("(CASE id ");
        Conversion_Cost.append("(CASE id ");
        Nuevo_Valor_Orden.append("(CASE id ");
        Month.append("(CASE id ");
        IdArchivo.append("(CASE id ");
        //Id.append("(CASE id ");
        int Contador = 0;
        PreparedStatement SQL = null;
        //String SQLl = null;
        StringBuilder SQLl = new StringBuilder();
        try {
//Recorreo el modelo e inserto los datos en las variables
            int c = 1, contt = 0;
            int v = 1;

            int Porcentaje1 = Integer.valueOf(Porcentaje);
            int VUeltas = listModeloKob1.size() / 10;
            int sumador = 1;

            for (ModeloKob1 modeloKob1 : listModeloKob1) {

                if (sumador == VUeltas) {
                    herramienta.setEventoProcesado("Progreso " + Porcentaje1 + "%");
                    System.err.println("Progreso " + Porcentaje1 + "%");
                    Porcentaje1++;
                    sumador = 1;
                }
                sumador++;

                contt++;
                if (c == 2) {
                    Id.append(",");
                }
                Functional_Area.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getFunctional_Area())).append("' ");
                Company_Code.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCompany_Code())).append("' ");
                Order_.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getOrder_())).append("' ");
                CO_object_name.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCO_object_name())).append("' ");
                Cost_Element.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCost_Element())).append("' ");
                Cost_element_name.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCost_element_name())).append("' ");
                Material.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getMaterial())).append("' ");
                Material_Description.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getMaterial_Description())).append("' ");
                Plant.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getPlant())).append("' ");
                Period.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getPeriod())).append("' ");
                Fiscal_Year.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getFiscal_Year())).append("' ");
                Dr_Cr_indicator.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getDr_Cr_indicator())).append("' ");
                Total_Quantity.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getTotal_Quantity())).append("' ");
                Unit_of_Measure.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getUnit_of_Measure())).append("' ");
                Value_TranCurr.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getValue_TranCurr())).append("' ");
                Transaction_Currency.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getTransaction_Currency())).append("' ");
                Value_in_Obj_Crcy.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getValue_in_Obj_Crcy())).append("' ");
                Object_Currency.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getObject_Currency())).append("' ");
                Document_Number.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getDocument_Number())).append("' ");
                Link_Plant_Material.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getLink_Plant_Material())).append("' ");
                Link_Material_orden.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getLink_Material_orden())).append("' ");
                Batch_consumo.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getBatch_consumo())).append("' ");
                Link_Material_Batch.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getLink_Material_Batch())).append("' ");
                Material_Type_Components.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getMaterial_Type_Components())).append("' ");
                Procur_Type.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getProcur_Type())).append("' ");
                Level_1.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getLevel_1())).append("' ");
                Finish_Good_sku.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getFinish_Good_sku())).append("' ");
                Mat_Type_Unfinish_Goods.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getMat_Type_Unfinish_Goods())).append("' ");
                Batch_Finish_goods.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getBatch_Finish_goods())).append("' ");
                Link_Terminado_Batch.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getLink_Terminado_Batch())).append("' ");
                Cost_Unit_Estandar.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCost_Unit_Estandar())).append("' ");
                Cantidad_Terminada.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCantidad_Terminada())).append("' ");
                Cost_Unit_Fifo_Old.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCost_Unit_Fifo_Old())).append("' ");
                Cost_Unit_Fifo_R_Mat_Pack.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack())).append("' ");
                X.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getX())).append("' ");
                Total_Raw_Material.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getTotal_Raw_Material())).append("' ");
                Manufact_Materials.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getManufact_Materials())).append("' ");
                Packaging_Materials.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getPackaging_Materials())).append("' ");
                Conversion_Cost.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getConversion_Cost())).append("' ");
                Nuevo_Valor_Orden.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getNuevo_Valor_Orden())).append("' ");
                Month.append("WHEN ").append(modeloKob1.getId()).append(" THEN '").append(Valor(modeloKob1.getMonth())).append("' ");
                IdArchivo.append("WHEN ").append(modeloKob1.getId()).append(" THEN ").append(modeloKob1.getIdArchivo()).append(" ");
                Id.append(modeloKob1.getId());
                c = 2;



                if (contt == 1000) {

                    Functional_Area.append("END)");
                    Company_Code.append("END)");
                    Order_.append("END)");
                    CO_object_name.append("END)");
                    Cost_Element.append("END)");
                    Cost_element_name.append("END)");
                    Material.append("END)");
                    Material_Description.append("END)");
                    Plant.append("END)");
                    Period.append("END)");
                    Fiscal_Year.append("END)");
                    Dr_Cr_indicator.append("END)");
                    Total_Quantity.append("END)");
                    Unit_of_Measure.append("END)");
                    Value_TranCurr.append("END)");
                    Transaction_Currency.append("END)");
                    Value_in_Obj_Crcy.append("END)");
                    Object_Currency.append("END)");
                    Document_Number.append("END)");
                    Link_Plant_Material.append("END)");
                    Link_Material_orden.append("END)");
                    Batch_consumo.append("END)");
                    Link_Material_Batch.append("END)");
                    Material_Type_Components.append("END)");
                    Procur_Type.append("END)");
                    Level_1.append("END)");
                    Finish_Good_sku.append("END)");
                    Mat_Type_Unfinish_Goods.append("END)");
                    Batch_Finish_goods.append("END)");
                    Link_Terminado_Batch.append("END)");
                    Cost_Unit_Estandar.append("END)");
                    Cantidad_Terminada.append("END)");
                    Cost_Unit_Fifo_Old.append("END)");
                    Cost_Unit_Fifo_R_Mat_Pack.append("END)");
                    X.append("END)");
                    Total_Raw_Material.append("END)");
                    Manufact_Materials.append("END)");
                    Packaging_Materials.append("END)");
                    Conversion_Cost.append("END)");
                    Nuevo_Valor_Orden.append("END)");
                    Month.append("END)");
                    IdArchivo.append("END)");
                    //Armo el Sql 
                    SQLl.append("UPDATE KOB1 SET ")
                            .append("Functional_Area = ").append(Functional_Area).append(", ")
                            .append("Company_Code = ").append(Company_Code).append(", ")
                            .append("Order_ = ").append(Order_).append(", ")
                            .append("CO_object_name = ").append(CO_object_name).append(", ")
                            .append("Cost_Element = ").append(Cost_Element).append(", ")
                            .append("Cost_element_name = ").append(Cost_element_name).append(", ")
                            .append("Material = ").append(Material).append(", ")
                            .append("Material_Description = ").append(Material_Description).append(", ")
                            .append("Plant = ").append(Plant).append(", ")
                            .append("Period = ").append(Period).append(", ")
                            .append("Fiscal_Year = ").append(Fiscal_Year).append(", ")
                            .append("Dr_Cr_indicator = ").append(Dr_Cr_indicator).append(", ")
                            .append("Total_Quantity = ").append(Total_Quantity).append(", ")
                            .append("Unit_of_Measure = ").append(Unit_of_Measure).append(", ")
                            .append("Value_TranCurr = ").append(Value_TranCurr).append(", ")
                            .append("Transaction_Currency = ").append(Transaction_Currency).append(", ")
                            .append("Value_in_Obj_Crcy = ").append(Value_in_Obj_Crcy).append(", ")
                            .append("Object_Currency = ").append(Object_Currency).append(", ")
                            .append("Document_Number = ").append(Document_Number).append(", ")
                            .append("Link_Plant_Material = ").append(Link_Plant_Material).append(", ")
                            .append("Link_Material_orden = ").append(Link_Material_orden).append(", ")
                            .append("Batch_consumo = ").append(Batch_consumo).append(", ")
                            .append("Link_Material_Batch = ").append(Link_Material_Batch).append(", ")
                            .append("Material_Type_Components = ").append(Material_Type_Components).append(", ")
                            .append("Procur_Type = ").append(Procur_Type).append(", ")
                            .append("Level_1 = ").append(Level_1).append(", ")
                            .append("Finish_Good_sku = ").append(Finish_Good_sku).append(", ")
                            .append("Mat_Type_Unfinish_Goods = ").append(Mat_Type_Unfinish_Goods).append(", ")
                            .append("Batch_Finish_goods = ").append(Batch_Finish_goods).append(", ")
                            .append("Link_Terminado_Batch = ").append(Link_Terminado_Batch).append(", ")
                            .append("Cost_Unit_Estandar = ").append(Cost_Unit_Estandar).append(", ")
                            .append("Cantidad_Terminada = ").append(Cantidad_Terminada).append(", ")
                            .append("Cost_Unit_Fifo_Old = ").append(Cost_Unit_Fifo_Old).append(", ")
                            .append("Cost_Unit_Fifo_R_Mat_Pack = ").append(Cost_Unit_Fifo_R_Mat_Pack).append(", ")
                            .append("x = ").append(X).append(", ")
                            .append("Total_Raw_Material = ").append(Total_Raw_Material).append(", ")
                            .append("Manufact_Materials = ").append(Manufact_Materials).append(", ")
                            .append("Packaging_Materials = ").append(Packaging_Materials).append(", ")
                            .append("Conversion_Cost = ").append(Conversion_Cost).append(", ")
                            .append("Nuevo_Valor_Orden = ").append(Nuevo_Valor_Orden).append(", ")
                            .append("Month = ").append(Month).append(", ")
                            .append("IdArchivo = ").append(IdArchivo).append("")
                            .append(" WHERE Id IN (").append(Id).append(") ");
                    //Paso el Sql al statement
                    SQL = con.prepareStatement(SQLl + "");
                    //ejecuto el SQL
                    if (SQL.executeUpdate() > 0) {

                        //System.out.println("Vuelta registros " + v);
                        v++;

                        resul = true;
                        // limpiamos los datos
                        SQLl.delete(0, SQLl.length());
                        Functional_Area.delete(0, Functional_Area.length());
                        Company_Code.delete(0, Company_Code.length());
                        Order_.delete(0, Order_.length());
                        CO_object_name.delete(0, CO_object_name.length());
                        Cost_Element.delete(0, Cost_Element.length());
                        Cost_element_name.delete(0, Cost_element_name.length());
                        Material.delete(0, Material.length());
                        Material_Description.delete(0, Material_Description.length());
                        Plant.delete(0, Plant.length());
                        Period.delete(0, Period.length());
                        Fiscal_Year.delete(0, Fiscal_Year.length());
                        Dr_Cr_indicator.delete(0, Dr_Cr_indicator.length());
                        Total_Quantity.delete(0, Total_Quantity.length());
                        Unit_of_Measure.delete(0, Unit_of_Measure.length());
                        Value_TranCurr.delete(0, Value_TranCurr.length());
                        Transaction_Currency.delete(0, Transaction_Currency.length());
                        Value_in_Obj_Crcy.delete(0, Value_in_Obj_Crcy.length());
                        Object_Currency.delete(0, Object_Currency.length());
                        Document_Number.delete(0, Document_Number.length());
                        Link_Plant_Material.delete(0, Link_Plant_Material.length());
                        Link_Material_orden.delete(0, Link_Material_orden.length());
                        Batch_consumo.delete(0, Batch_consumo.length());
                        Link_Material_Batch.delete(0, Link_Material_Batch.length());
                        Material_Type_Components.delete(0, Material_Type_Components.length());
                        Procur_Type.delete(0, Procur_Type.length());
                        Level_1.delete(0, Level_1.length());
                        Finish_Good_sku.delete(0, Finish_Good_sku.length());
                        Mat_Type_Unfinish_Goods.delete(0, Mat_Type_Unfinish_Goods.length());
                        Batch_Finish_goods.delete(0, Batch_Finish_goods.length());
                        Link_Terminado_Batch.delete(0, Link_Terminado_Batch.length());
                        Cost_Unit_Estandar.delete(0, Cost_Unit_Estandar.length());
                        Cantidad_Terminada.delete(0, Cantidad_Terminada.length());
                        Cost_Unit_Fifo_Old.delete(0, Cost_Unit_Fifo_Old.length());
                        Cost_Unit_Fifo_R_Mat_Pack.delete(0, Cost_Unit_Fifo_R_Mat_Pack.length());
                        X.delete(0, X.length());
                        Total_Raw_Material.delete(0, Total_Raw_Material.length());
                        Manufact_Materials.delete(0, Manufact_Materials.length());
                        Packaging_Materials.delete(0, Packaging_Materials.length());
                        Conversion_Cost.delete(0, Conversion_Cost.length());
                        Nuevo_Valor_Orden.delete(0, Nuevo_Valor_Orden.length());
                        Month.delete(0, Month.length());
                        IdArchivo.delete(0, IdArchivo.length());
                        Id.delete(0, Id.length());
                        //----//

                        Functional_Area.append("(CASE id ");
                        Company_Code.append("(CASE id ");
                        Order_.append("(CASE id ");
                        CO_object_name.append("(CASE id ");
                        Cost_Element.append("(CASE id ");
                        Cost_element_name.append("(CASE id ");
                        Material.append("(CASE id ");
                        Material_Description.append("(CASE id ");
                        Plant.append("(CASE id ");
                        Period.append("(CASE id ");
                        Fiscal_Year.append("(CASE id ");
                        Dr_Cr_indicator.append("(CASE id ");
                        Total_Quantity.append("(CASE id ");
                        Unit_of_Measure.append("(CASE id ");
                        Value_TranCurr.append("(CASE id ");
                        Transaction_Currency.append("(CASE id ");
                        Value_in_Obj_Crcy.append("(CASE id ");
                        Object_Currency.append("(CASE id ");
                        Document_Number.append("(CASE id ");
                        Link_Plant_Material.append("(CASE id ");
                        Link_Material_orden.append("(CASE id ");
                        Batch_consumo.append("(CASE id ");
                        Link_Material_Batch.append("(CASE id ");
                        Material_Type_Components.append("(CASE id ");
                        Procur_Type.append("(CASE id ");
                        Level_1.append("(CASE id ");
                        Finish_Good_sku.append("(CASE id ");
                        Mat_Type_Unfinish_Goods.append("(CASE id ");
                        Batch_Finish_goods.append("(CASE id ");
                        Link_Terminado_Batch.append("(CASE id ");
                        Cost_Unit_Estandar.append("(CASE id ");
                        Cantidad_Terminada.append("(CASE id ");
                        Cost_Unit_Fifo_Old.append("(CASE id ");
                        Cost_Unit_Fifo_R_Mat_Pack.append("(CASE id ");
                        X.append("(CASE id ");
                        Total_Raw_Material.append("(CASE id ");
                        Manufact_Materials.append("(CASE id ");
                        Packaging_Materials.append("(CASE id ");
                        Conversion_Cost.append("(CASE id ");
                        Nuevo_Valor_Orden.append("(CASE id ");
                        Month.append("(CASE id ");
                        IdArchivo.append("(CASE id ");
                        //Id.append("(CASE id ");

                    }
                    c = 1;
                    contt = 0;
                }
            }
            //finalizo el formato del SQL
            Functional_Area.append("END)");
            Company_Code.append("END)");
            Order_.append("END)");
            CO_object_name.append("END)");
            Cost_Element.append("END)");
            Cost_element_name.append("END)");
            Material.append("END)");
            Material_Description.append("END)");
            Plant.append("END)");
            Period.append("END)");
            Fiscal_Year.append("END)");
            Dr_Cr_indicator.append("END)");
            Total_Quantity.append("END)");
            Unit_of_Measure.append("END)");
            Value_TranCurr.append("END)");
            Transaction_Currency.append("END)");
            Value_in_Obj_Crcy.append("END)");
            Object_Currency.append("END)");
            Document_Number.append("END)");
            Link_Plant_Material.append("END)");
            Link_Material_orden.append("END)");
            Batch_consumo.append("END)");
            Link_Material_Batch.append("END)");
            Material_Type_Components.append("END)");
            Procur_Type.append("END)");
            Level_1.append("END)");
            Finish_Good_sku.append("END)");
            Mat_Type_Unfinish_Goods.append("END)");
            Batch_Finish_goods.append("END)");
            Link_Terminado_Batch.append("END)");
            Cost_Unit_Estandar.append("END)");
            Cantidad_Terminada.append("END)");
            Cost_Unit_Fifo_Old.append("END)");
            Cost_Unit_Fifo_R_Mat_Pack.append("END)");
            X.append("END)");
            Total_Raw_Material.append("END)");
            Manufact_Materials.append("END)");
            Packaging_Materials.append("END)");
            Conversion_Cost.append("END)");
            Nuevo_Valor_Orden.append("END)");
            Month.append("END)");
            IdArchivo.append("END)");
            //Armo el Sql 
            SQLl.delete(0, SQLl.length());
            SQLl.append("UPDATE KOB1 SET ")
                    .append("Functional_Area = ").append(Functional_Area).append(", ")
                    .append("Company_Code = ").append(Company_Code).append(", ")
                    .append("Order_ = ").append(Order_).append(", ")
                    .append("CO_object_name = ").append(CO_object_name).append(", ")
                    .append("Cost_Element = ").append(Cost_Element).append(", ")
                    .append("Cost_element_name = ").append(Cost_element_name).append(", ")
                    .append("Material = ").append(Material).append(", ")
                    .append("Material_Description = ").append(Material_Description).append(", ")
                    .append("Plant = ").append(Plant).append(", ")
                    .append("Period = ").append(Period).append(", ")
                    .append("Fiscal_Year = ").append(Fiscal_Year).append(", ")
                    .append("Dr_Cr_indicator = ").append(Dr_Cr_indicator).append(", ")
                    .append("Total_Quantity = ").append(Total_Quantity).append(", ")
                    .append("Unit_of_Measure = ").append(Unit_of_Measure).append(", ")
                    .append("Value_TranCurr = ").append(Value_TranCurr).append(", ")
                    .append("Transaction_Currency = ").append(Transaction_Currency).append(", ")
                    .append("Value_in_Obj_Crcy = ").append(Value_in_Obj_Crcy).append(", ")
                    .append("Object_Currency = ").append(Object_Currency).append(", ")
                    .append("Document_Number = ").append(Document_Number).append(", ")
                    .append("Link_Plant_Material = ").append(Link_Plant_Material).append(", ")
                    .append("Link_Material_orden = ").append(Link_Material_orden).append(", ")
                    .append("Batch_consumo = ").append(Batch_consumo).append(", ")
                    .append("Link_Material_Batch = ").append(Link_Material_Batch).append(", ")
                    .append("Material_Type_Components = ").append(Material_Type_Components).append(", ")
                    .append("Procur_Type = ").append(Procur_Type).append(", ")
                    .append("Level_1 = ").append(Level_1).append(", ")
                    .append("Finish_Good_sku = ").append(Finish_Good_sku).append(", ")
                    .append("Mat_Type_Unfinish_Goods = ").append(Mat_Type_Unfinish_Goods).append(", ")
                    .append("Batch_Finish_goods = ").append(Batch_Finish_goods).append(", ")
                    .append("Link_Terminado_Batch = ").append(Link_Terminado_Batch).append(", ")
                    .append("Cost_Unit_Estandar = ").append(Cost_Unit_Estandar).append(", ")
                    .append("Cantidad_Terminada = ").append(Cantidad_Terminada).append(", ")
                    .append("Cost_Unit_Fifo_Old = ").append(Cost_Unit_Fifo_Old).append(", ")
                    .append("Cost_Unit_Fifo_R_Mat_Pack = ").append(Cost_Unit_Fifo_R_Mat_Pack).append(", ")
                    .append("x = ").append(X).append(", ")
                    .append("Total_Raw_Material = ").append(Total_Raw_Material).append(", ")
                    .append("Manufact_Materials = ").append(Manufact_Materials).append(", ")
                    .append("Packaging_Materials = ").append(Packaging_Materials).append(", ")
                    .append("Conversion_Cost = ").append(Conversion_Cost).append(", ")
                    .append("Nuevo_Valor_Orden = ").append(Nuevo_Valor_Orden).append(", ")
                    .append("Month = ").append(Month).append(", ")
                    .append("IdArchivo = ").append(IdArchivo).append("")
                    .append(" WHERE Id IN (").append(Id).append(") ");
            //Paso el Sql al statement
            SQL = con.prepareStatement(SQLl + "");
            //ejecuto el SQL
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
//            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e.getMessage());
            SQL.close();

        }
        return resul;

    }

}
