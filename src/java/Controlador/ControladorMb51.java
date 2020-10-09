/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Herramienta.Herramienta;
import Modelos.ModeloMb51;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorMb51 {

    Herramienta herramienta = new Herramienta();

    public boolean Insert(ModeloMb51 modeloMb51) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO mb51("
                        + "Plant,"
                        + "Purchase_order,"
                        + "Material,"
                        + "Material_Description,"
                        + "Batch,"
                        + "Movement_type,"
                        + "Movement_Type_Text,"
                        + "Item,"
                        + "Quantity,"
                        + "Qty_in_unit_of_entry,"
                        + "Unit_of_Entry,"
                        + "Amt_in_loc_cur,"
                        + "Currency,"
                        + "Storage_Location,"
                        + "Posting_Date,"
                        + "Document_Date,"
                        + "Material_Document,"
                        + "User_Name,"
                        + "Vendor,"
                        + "Vendor_Name,"
                        + "Vendor_Type,"
                        + "Month,"
                        + "Period,"
                        + "Cost_Unit_SAP_en_KG,"
                        + "Material_Type,"
                        + "Profit_Center,"
                        + "link1_Material_Batch,"
                        + "link2_PO_position,"
                        + "Referencia_vendor,"
                        + "TotalQ_ME80FN,"
                        + "O_Unit_ME80FN,"
                        + "TotalQ_Porcentaje,"
                        + "TOTAL_INVOICE_VALUE,"
                        + "Factura_Value_Unit,"
                        + "PIR_Porcentaje_del_Costo,"
                        + "Precio_Unit_moneda_compra,"
                        + "Moneda,"
                        + "link3_PO_Item,"
                        + "Freight,"
                        + "Dutys,"
                        + "Arancel,"
                        + "Total_Costos_Adicionales,"
                        + "Participac_Adicionales,"
                        + "Adicionales_al_CTO_Estandar,"
                        + "Variance,"
                        + "Total_Costos,"
                        + "Unitario_Real,"
                        + "Unitario_Real_adicional_estandar,"
                        + "Unitario_estandar_SAP,"
                        + "Unitario_final_FIFO,"
                        + "Porcentaje_Real_Vs_Estandar,"
                        + "Porcentaje_fifo_final_vs_Estandar,"
                        + "Compra_valorada_a_Unit_FIFO,"
                        + "Variacion_FIFO_vs_Estandar,"
                        + "NovedadIco)"
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                SQL.setString(1, modeloMb51.getPlant());
                SQL.setString(2, modeloMb51.getPurchase_order());
                SQL.setString(3, modeloMb51.getMaterial());
                SQL.setString(4, modeloMb51.getMaterial_Description());
                SQL.setString(5, modeloMb51.getBatch());
                SQL.setString(6, modeloMb51.getMovement_type());
                SQL.setString(7, modeloMb51.getMovement_Type_Text());
                SQL.setString(8, modeloMb51.getItem());
                SQL.setString(9, modeloMb51.getQuantity());
                SQL.setString(10, modeloMb51.getQty_in_unit_of_entry());
                SQL.setString(11, modeloMb51.getUnit_of_Entry());
                SQL.setString(12, modeloMb51.getAmt_in_loc_cur());
                SQL.setString(13, modeloMb51.getCurrency());
                SQL.setString(14, modeloMb51.getStorage_Location());
                SQL.setString(15, modeloMb51.getPosting_Date());
                SQL.setString(16, modeloMb51.getDocument_Date());
                SQL.setString(17, modeloMb51.getMaterial_Document());
                SQL.setString(18, modeloMb51.getUser_Name());
                SQL.setString(19, modeloMb51.getVendor());
                SQL.setString(20, modeloMb51.getVendor_Name());
                SQL.setString(21, modeloMb51.getVendor_Type());
                SQL.setString(22, modeloMb51.getMonth());
                SQL.setString(23, modeloMb51.getPeriod());
                SQL.setString(24, modeloMb51.getCost_Unit_SAP_en_KG());
                SQL.setString(25, modeloMb51.getMaterial_Type());
                SQL.setString(26, modeloMb51.getProfit_Center());
                SQL.setString(27, modeloMb51.getLink1_Material_Batch());
                SQL.setString(28, modeloMb51.getLink2_PO_position());
                SQL.setString(29, modeloMb51.getReferencia_vendor());
                SQL.setString(30, modeloMb51.getTotalQ_ME80FN());
                SQL.setString(31, modeloMb51.getO_Unit_ME80FN());
                SQL.setString(32, modeloMb51.getTotalQ_Porcentaje());
                SQL.setString(33, modeloMb51.getTOTAL_INVOICE_VALUE());
                SQL.setString(34, modeloMb51.getFactura_Value_Unit());
                SQL.setString(35, modeloMb51.getPIR_Porcentaje_del_Costo());
                SQL.setString(36, modeloMb51.getPrecio_Unit_moneda_compra());
                SQL.setString(37, modeloMb51.getMoneda());
                SQL.setString(38, modeloMb51.getLink3_PO_Item());
                SQL.setString(39, modeloMb51.getFreight());
                SQL.setString(40, modeloMb51.getDutys());
                SQL.setString(41, modeloMb51.getArancel());
                SQL.setString(42, modeloMb51.getTotal_Costos_Adicionales());
                SQL.setString(43, modeloMb51.getParticipac_Adicionales());
                SQL.setString(44, modeloMb51.getAdicionales_al_CTO_Estandar());
                SQL.setString(45, modeloMb51.getVariance());
                SQL.setString(46, modeloMb51.getTotal_Costos());
                SQL.setString(47, modeloMb51.getUnitario_Real());
                SQL.setString(48, modeloMb51.getUnitario_Real_adicional_estandar());
                SQL.setString(49, modeloMb51.getUnitario_estandar_SAP());
                SQL.setString(50, modeloMb51.getUnitario_final_FIFO());
                SQL.setString(51, modeloMb51.getPorcentaje_Real_Vs_Estandar());
                SQL.setString(52, modeloMb51.getPorcentaje_fifo_final_vs_Estandar());
                SQL.setString(53, modeloMb51.getCompra_valorada_a_Unit_FIFO());
                SQL.setString(54, modeloMb51.getVariacion_FIFO_vs_Estandar());
                SQL.setString(55, modeloMb51.getNovedadIco());

                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert " + e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean InsertList(LinkedList<ModeloMb51> listModeloMb51) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO mb51 ("
                        + "Plant,"
                        + "Purchase_order,"
                        + "Material,"
                        + "Material_Description,"
                        + "Batch,"
                        + "Movement_type,"
                        + "Movement_Type_Text,"
                        + "Item,"
                        + "Quantity,"
                        + "Qty_in_unit_of_entry,"
                        + "Unit_of_Entry,"
                        + "Amt_in_loc_cur,"
                        + "Currency,"
                        + "Storage_Location,"
                        + "Posting_Date,"
                        + "Document_Date,"
                        + "Material_Document,"
                        + "User_Name,"
                        + "Vendor,"
                        + "Vendor_Name,"
                        + "Vendor_Type,"
                        + "Month,"
                        + "Period,"
                        + "Cost_Unit_SAP_en_KG,"
                        + "Material_Type,"
                        + "Profit_Center,"
                        + "link1_Material_Batch,"
                        + "link2_PO_position,"
                        + "Referencia_vendor,"
                        + "TotalQ_ME80FN,"
                        + "O_Unit_ME80FN,"
                        + "TotalQ_Porcentaje,"
                        + "TOTAL_INVOICE_VALUE,"
                        + "Factura_Value_Unit,"
                        + "PIR_Porcentaje_del_Costo,"
                        + "Precio_Unit_moneda_compra,"
                        + "Moneda,"
                        + "link3_PO_Item,"
                        + "Freight,"
                        + "Dutys,"
                        + "Arancel,"
                        + "Total_Costos_Adicionales,"
                        + "Participac_Adicionales,"
                        + "Adicionales_al_CTO_Estandar,"
                        + "Variance,"
                        + "Total_Costos,"
                        + "Unitario_Real,"
                        + "Unitario_Real_adicional_estandar,"
                        + "Unitario_estandar_SAP,"
                        + "Unitario_final_FIFO,"
                        + "Porcentaje_Real_Vs_Estandar,"
                        + "Porcentaje_fifo_final_vs_Estandar,"
                        + "Compra_valorada_a_Unit_FIFO,"
                        + "Variacion_FIFO_vs_Estandar,"
                        + "NovedadIco,"
                        + "IdArchivo) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                int Porcentaje1 = Integer.valueOf(70);
                int VUeltas = listModeloMb51.size() / 28;
                int sumador = 1;

                for (ModeloMb51 modeloMb51 : listModeloMb51) {

                    if (sumador == VUeltas) {
                        herramienta.setEventoProcesado("Progreso " + Porcentaje1 + "%");
                        System.err.println("Progreso " + Porcentaje1 + "%");
                        Porcentaje1++;
                        sumador = 1;
                    }
                    sumador++;

                    SQL.setString(1, modeloMb51.getPlant());
                    SQL.setString(2, modeloMb51.getPurchase_order());
                    SQL.setString(3, modeloMb51.getMaterial());
                    SQL.setString(4, modeloMb51.getMaterial_Description());
                    SQL.setString(5, modeloMb51.getBatch());
                    SQL.setString(6, modeloMb51.getMovement_type());
                    SQL.setString(7, modeloMb51.getMovement_Type_Text());
                    SQL.setString(8, modeloMb51.getItem());
                    SQL.setString(9, modeloMb51.getQuantity());
                    SQL.setString(10, modeloMb51.getQty_in_unit_of_entry());
                    SQL.setString(11, modeloMb51.getUnit_of_Entry());
                    SQL.setString(12, modeloMb51.getAmt_in_loc_cur());
                    SQL.setString(13, modeloMb51.getCurrency());
                    SQL.setString(14, modeloMb51.getStorage_Location());
                    SQL.setString(15, modeloMb51.getPosting_Date());
                    SQL.setString(16, modeloMb51.getDocument_Date());
                    SQL.setString(17, modeloMb51.getMaterial_Document());
                    SQL.setString(18, modeloMb51.getUser_Name());
                    SQL.setString(19, modeloMb51.getVendor());
                    SQL.setString(20, modeloMb51.getVendor_Name());
                    SQL.setString(21, modeloMb51.getVendor_Type());
                    SQL.setString(22, modeloMb51.getMonth());
                    SQL.setString(23, modeloMb51.getPeriod());
                    SQL.setString(24, modeloMb51.getCost_Unit_SAP_en_KG());
                    SQL.setString(25, modeloMb51.getMaterial_Type());
                    SQL.setString(26, modeloMb51.getProfit_Center());
                    SQL.setString(27, modeloMb51.getLink1_Material_Batch());
                    SQL.setString(28, modeloMb51.getLink2_PO_position());
                    SQL.setString(29, modeloMb51.getReferencia_vendor());
                    SQL.setString(30, modeloMb51.getTotalQ_ME80FN());
                    SQL.setString(31, modeloMb51.getO_Unit_ME80FN());
                    SQL.setString(32, modeloMb51.getTotalQ_Porcentaje());
                    SQL.setString(33, modeloMb51.getTOTAL_INVOICE_VALUE());
                    SQL.setString(34, modeloMb51.getFactura_Value_Unit());
                    SQL.setString(35, modeloMb51.getPIR_Porcentaje_del_Costo());
                    SQL.setString(36, modeloMb51.getPrecio_Unit_moneda_compra());
                    SQL.setString(37, modeloMb51.getMoneda());
                    SQL.setString(38, modeloMb51.getLink3_PO_Item());
                    SQL.setString(39, modeloMb51.getFreight());
                    SQL.setString(40, modeloMb51.getDutys());
                    SQL.setString(41, modeloMb51.getArancel());
                    SQL.setString(42, modeloMb51.getTotal_Costos_Adicionales());
                    SQL.setString(43, modeloMb51.getParticipac_Adicionales());
                    SQL.setString(44, modeloMb51.getAdicionales_al_CTO_Estandar());
                    SQL.setString(45, modeloMb51.getVariance());
                    SQL.setString(46, modeloMb51.getTotal_Costos());
                    SQL.setString(47, modeloMb51.getUnitario_Real());
                    SQL.setString(48, modeloMb51.getUnitario_Real_adicional_estandar());
                    SQL.setString(49, modeloMb51.getUnitario_estandar_SAP());
                    SQL.setString(50, modeloMb51.getUnitario_final_FIFO());
                    SQL.setString(51, modeloMb51.getPorcentaje_Real_Vs_Estandar());
                    SQL.setString(52, modeloMb51.getPorcentaje_fifo_final_vs_Estandar());
                    SQL.setString(53, modeloMb51.getCompra_valorada_a_Unit_FIFO());
                    SQL.setString(54, modeloMb51.getVariacion_FIFO_vs_Estandar());
                    SQL.setString(55, modeloMb51.getNovedadIco());
                    SQL.setInt(56, modeloMb51.getIdArchivo());

                    if (SQL.executeUpdate() > 0) {
                        resul = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert " + e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }
    
     public boolean InsertList_Tmp(LinkedList<ModeloMb51> listModeloMb51) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO mb51_tmp ("
                        + "Plant,"
                        + "Purchase_order,"
                        + "Material,"
                        + "Material_Description,"
                        + "Batch,"
                        + "Movement_type,"
                        + "Movement_Type_Text,"
                        + "Item,"
                        + "Quantity,"
                        + "Qty_in_unit_of_entry,"
                        + "Unit_of_Entry,"
                        + "Amt_in_loc_cur,"
                        + "Currency,"
                        + "Storage_Location,"
                        + "Posting_Date,"
                        + "Document_Date,"
                        + "Material_Document,"
                        + "User_Name,"
                        + "Vendor,"
                        + "Vendor_Name,"
                        + "Vendor_Type,"
                        + "Month,"
                        + "Period,"
                        + "Cost_Unit_SAP_en_KG,"
                        + "Material_Type,"
                        + "Profit_Center,"
                        + "link1_Material_Batch,"
                        + "link2_PO_position,"
                        + "Referencia_vendor,"
                        + "TotalQ_ME80FN,"
                        + "O_Unit_ME80FN,"
                        + "TotalQ_Porcentaje,"
                        + "TOTAL_INVOICE_VALUE,"
                        + "Factura_Value_Unit,"
                        + "PIR_Porcentaje_del_Costo,"
                        + "Precio_Unit_moneda_compra,"
                        + "Moneda,"
                        + "link3_PO_Item,"
                        + "Freight,"
                        + "Dutys,"
                        + "Arancel,"
                        + "Total_Costos_Adicionales,"
                        + "Participac_Adicionales,"
                        + "Adicionales_al_CTO_Estandar,"
                        + "Variance,"
                        + "Total_Costos,"
                        + "Unitario_Real,"
                        + "Unitario_Real_adicional_estandar,"
                        + "Unitario_estandar_SAP,"
                        + "Unitario_final_FIFO,"
                        + "Porcentaje_Real_Vs_Estandar,"
                        + "Porcentaje_fifo_final_vs_Estandar,"
                        + "Compra_valorada_a_Unit_FIFO,"
                        + "Variacion_FIFO_vs_Estandar,"
                        + "NovedadIco,"
                        + "IdArchivo) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                int Porcentaje1 = Integer.valueOf(70);
                int VUeltas = listModeloMb51.size() / 28;
                int sumador = 1;

                for (ModeloMb51 modeloMb51 : listModeloMb51) {

                    if (sumador == VUeltas) {
                        herramienta.setEventoProcesado("Progreso " + Porcentaje1 + "%");
                        System.err.println("Progreso " + Porcentaje1 + "%");
                        Porcentaje1++;
                        sumador = 1;
                    }
                    sumador++;

                    SQL.setString(1, modeloMb51.getPlant());
                    SQL.setString(2, modeloMb51.getPurchase_order());
                    SQL.setString(3, modeloMb51.getMaterial());
                    SQL.setString(4, modeloMb51.getMaterial_Description());
                    SQL.setString(5, modeloMb51.getBatch());
                    SQL.setString(6, modeloMb51.getMovement_type());
                    SQL.setString(7, modeloMb51.getMovement_Type_Text());
                    SQL.setString(8, modeloMb51.getItem());
                    SQL.setString(9, modeloMb51.getQuantity());
                    SQL.setString(10, modeloMb51.getQty_in_unit_of_entry());
                    SQL.setString(11, modeloMb51.getUnit_of_Entry());
                    SQL.setString(12, modeloMb51.getAmt_in_loc_cur());
                    SQL.setString(13, modeloMb51.getCurrency());
                    SQL.setString(14, modeloMb51.getStorage_Location());
                    SQL.setString(15, modeloMb51.getPosting_Date());
                    SQL.setString(16, modeloMb51.getDocument_Date());
                    SQL.setString(17, modeloMb51.getMaterial_Document());
                    SQL.setString(18, modeloMb51.getUser_Name());
                    SQL.setString(19, modeloMb51.getVendor());
                    SQL.setString(20, modeloMb51.getVendor_Name());
                    SQL.setString(21, modeloMb51.getVendor_Type());
                    SQL.setString(22, modeloMb51.getMonth());
                    SQL.setString(23, modeloMb51.getPeriod());
                    SQL.setString(24, modeloMb51.getCost_Unit_SAP_en_KG());
                    SQL.setString(25, modeloMb51.getMaterial_Type());
                    SQL.setString(26, modeloMb51.getProfit_Center());
                    SQL.setString(27, modeloMb51.getLink1_Material_Batch());
                    SQL.setString(28, modeloMb51.getLink2_PO_position());
                    SQL.setString(29, modeloMb51.getReferencia_vendor());
                    SQL.setString(30, modeloMb51.getTotalQ_ME80FN());
                    SQL.setString(31, modeloMb51.getO_Unit_ME80FN());
                    SQL.setString(32, modeloMb51.getTotalQ_Porcentaje());
                    SQL.setString(33, modeloMb51.getTOTAL_INVOICE_VALUE());
                    SQL.setString(34, modeloMb51.getFactura_Value_Unit());
                    SQL.setString(35, modeloMb51.getPIR_Porcentaje_del_Costo());
                    SQL.setString(36, modeloMb51.getPrecio_Unit_moneda_compra());
                    SQL.setString(37, modeloMb51.getMoneda());
                    SQL.setString(38, modeloMb51.getLink3_PO_Item());
                    SQL.setString(39, modeloMb51.getFreight());
                    SQL.setString(40, modeloMb51.getDutys());
                    SQL.setString(41, modeloMb51.getArancel());
                    SQL.setString(42, modeloMb51.getTotal_Costos_Adicionales());
                    SQL.setString(43, modeloMb51.getParticipac_Adicionales());
                    SQL.setString(44, modeloMb51.getAdicionales_al_CTO_Estandar());
                    SQL.setString(45, modeloMb51.getVariance());
                    SQL.setString(46, modeloMb51.getTotal_Costos());
                    SQL.setString(47, modeloMb51.getUnitario_Real());
                    SQL.setString(48, modeloMb51.getUnitario_Real_adicional_estandar());
                    SQL.setString(49, modeloMb51.getUnitario_estandar_SAP());
                    SQL.setString(50, modeloMb51.getUnitario_final_FIFO());
                    SQL.setString(51, modeloMb51.getPorcentaje_Real_Vs_Estandar());
                    SQL.setString(52, modeloMb51.getPorcentaje_fifo_final_vs_Estandar());
                    SQL.setString(53, modeloMb51.getCompra_valorada_a_Unit_FIFO());
                    SQL.setString(54, modeloMb51.getVariacion_FIFO_vs_Estandar());
                    SQL.setString(55, modeloMb51.getNovedadIco());
                    SQL.setInt(56, modeloMb51.getIdArchivo());

                    if (SQL.executeUpdate() > 0) {
                        resul = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert " + e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }


    public boolean Delete(ModeloMb51 modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("DELETE FROM `mb51` WHERE `Id` = ?;");
            SQL.setInt(1, modelo.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete " + e);
        }
        return resul;
    }

    public boolean Delete() {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("DELETE FROM `mb51`;");
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete " + e);
        }
        return resul;
    }

    public LinkedList<ModeloMb51> Select() {
        LinkedList<ModeloMb51> modeloMb51s = new LinkedList<ModeloMb51>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
//        String Sql = "SELECT "
//                    + "Id,"
//                    + "Plant,"
//                    + "Purchase_order,"
//                    + "Material,"
//                    + "Material_Description,"
//                    + "Batch,"
//                    + "Movement_type,"
//                    + "Movement_Type_Text,"
//                    + "Item,"
//                    + "SUM(Quantity) AS 'Quantity',"
//                    + "SUM(Qty_in_unit_of_entry) AS 'Qty_in_unit_of_entry',"
//                    + "Unit_of_Entry,"
//                    + "SUM(Amt_in_loc_cur) AS 'Amt_in_loc_cur',"
//                    + "Currency,"
//                    + "Storage_Location,"
//                    + "Posting_Date,"
//                    + "Document_Date,"
//                    + "Material_Document,"
//                    + "User_Name,"
//                    + "Vendor,"
//                    + "Vendor_Name,"
//                    + "Vendor_Type,"
//                    + "Month,"
//                    + "Period,"
//                    + "Cost_Unit_SAP_en_KG,"
//                    + "Material_Type,"
//                    + "Profit_Center,"
//                    + "link1_Material_Batch,"
//                    + "link2_PO_position,"
//                    + "Referencia_vendor,"
//                    + "TotalQ_ME80FN,"
//                    + "O_Unit_ME80FN,"
//                    + "TotalQ_Porcentaje,"
//                    + "TOTAL_INVOICE_VALUE,"
//                    + "Factura_Value_Unit,"
//                    + "PIR_Porcentaje_del_Costo,"
//                    + "Precio_Unit_moneda_compra,"
//                    + "Moneda,"
//                    + "link3_PO_Item,"
//                    + "Freight,"
//                    + "Dutys,"
//                    + "Arancel,"
//                    + "Total_Costos_Adicionales,"
//                    + "Participac_Adicionales,"
//                    + "Adicionales_al_CTO_Estandar,"
//                    + "Variance,"
//                    + "Total_Costos,"
//                    + "Unitario_Real,"
//                    + "Unitario_Real_adicional_estandar,"
//                    + "Unitario_estandar_SAP,"
//                    + "Unitario_final_FIFO,"
//                    + "Porcentaje_Real_Vs_Estandar,"
//                    + "Porcentaje_fifo_final_vs_Estandar,"
//                    + "Compra_valorada_a_Unit_FIFO,"
//                    + "Variacion_FIFO_vs_Estandar,"
//                    + "NovedadIco"
//                    + " FROM mb51 WHERE IdArchivo is null group by  Material, Batch order by Batch, Material";
        String Sql = "SELECT "
                + "Id,"
                + "Plant,"
                + "Purchase_order,"
                + "Material,"
                + "Material_Description,"
                + "Batch,"
                + "Movement_type,"
                + "Movement_Type_Text,"
                + "Item,"
                + "SUM(Quantity) AS 'Quantity',"
                + "SUM(Qty_in_unit_of_entry) AS 'Qty_in_unit_of_entry',"
                + "Unit_of_Entry,"
                + "SUM(Amt_in_loc_cur) AS 'Amt_in_loc_cur',"
                + "Currency,"
                + "Storage_Location,"
                + "Posting_Date,"
                + "Document_Date,"
                + "Material_Document,"
                + "User_Name,"
                + "Vendor,"
                + "Vendor_Name,"
                + "Vendor_Type,"
                + "Month,"
                + "Period,"
                + "Cost_Unit_SAP_en_KG,"
                + "Material_Type,"
                + "Profit_Center,"
                + "link1_Material_Batch,"
                + "link2_PO_position,"
                + "Referencia_vendor,"
                + "TotalQ_ME80FN,"
                + "O_Unit_ME80FN,"
                + "TotalQ_Porcentaje,"
                + "TOTAL_INVOICE_VALUE,"
                + "Factura_Value_Unit,"
                + "PIR_Porcentaje_del_Costo,"
                + "Precio_Unit_moneda_compra,"
                + "Moneda,"
                + "link3_PO_Item,"
                + "Freight,"
                + "Dutys,"
                + "Arancel,"
                + "Total_Costos_Adicionales,"
                + "Participac_Adicionales,"
                + "Adicionales_al_CTO_Estandar,"
                + "Variance,"
                + "Total_Costos,"
                + "Unitario_Real,"
                + "Unitario_Real_adicional_estandar,"
                + "Unitario_estandar_SAP,"
                + "Unitario_final_FIFO,"
                + "Porcentaje_Real_Vs_Estandar,"
                + "Porcentaje_fifo_final_vs_Estandar,"
                + "Compra_valorada_a_Unit_FIFO,"
                + "Variacion_FIFO_vs_Estandar,"
                + "NovedadIco"
                //+ " FROM mb51 WHERE IdArchivo is null group by  Purchase_order, Material, Batch order by Batch, Material";        
                + " FROM MB51_planos group by Purchase_order, Material, Batch order by Batch, Material";

        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMb51 modeloMb51 = new ModeloMb51();
                modeloMb51.setId(res.getInt("id"));
                modeloMb51.setPlant(res.getString("Plant"));
                modeloMb51.setPurchase_order(res.getString("Purchase_order"));
                modeloMb51.setMaterial(res.getString("Material"));
                modeloMb51.setMaterial_Description(res.getString("Material_Description"));
                modeloMb51.setBatch(res.getString("Batch"));
                modeloMb51.setMovement_type(res.getString("Movement_type"));
                modeloMb51.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloMb51.setItem(res.getString("Item"));
                modeloMb51.setQuantity(res.getString("Quantity"));
                modeloMb51.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloMb51.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51.setCurrency(res.getString("Currency"));
                modeloMb51.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51.setDocument_Date(res.getString("Document_Date"));
                modeloMb51.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51.setUser_Name(res.getString("User_Name"));
                modeloMb51.setVendor(res.getString("Vendor"));
                modeloMb51.setVendor_Name(res.getString("Vendor_Name"));
                modeloMb51.setVendor_Type(res.getString("Vendor_Type"));
                modeloMb51.setMonth(res.getString("Month"));
                modeloMb51.setPeriod(res.getString("Period"));
                modeloMb51.setCost_Unit_SAP_en_KG(res.getString("Cost_Unit_SAP_en_KG"));
                modeloMb51.setMaterial_Type(res.getString("Material_Type"));
                modeloMb51.setProfit_Center(res.getString("Profit_Center"));
                modeloMb51.setLink1_Material_Batch(res.getString("link1_Material_Batch"));
                modeloMb51.setLink2_PO_position(res.getString("link2_PO_position"));
                modeloMb51.setReferencia_vendor(res.getString("Referencia_vendor"));
                modeloMb51.setTotalQ_ME80FN(res.getString("TotalQ_ME80FN"));
                modeloMb51.setO_Unit_ME80FN(res.getString("O_Unit_ME80FN"));
                modeloMb51.setTotalQ_Porcentaje(res.getString("TotalQ_Porcentaje"));
                modeloMb51.setTOTAL_INVOICE_VALUE(res.getString("TOTAL_INVOICE_VALUE"));
                modeloMb51.setFactura_Value_Unit(res.getString("Factura_Value_Unit"));
                modeloMb51.setPIR_Porcentaje_del_Costo(res.getString("PIR_Porcentaje_del_Costo"));
                modeloMb51.setPrecio_Unit_moneda_compra(res.getString("Precio_Unit_moneda_compra"));
                modeloMb51.setMoneda(res.getString("Moneda"));
                modeloMb51.setLink3_PO_Item(res.getString("link3_PO_Item"));
                modeloMb51.setFreight(res.getString("Freight"));
                modeloMb51.setDutys(res.getString("Dutys"));
                modeloMb51.setArancel(res.getString("Arancel"));
                modeloMb51.setTotal_Costos_Adicionales(res.getString("Total_Costos_Adicionales"));
                modeloMb51.setParticipac_Adicionales(res.getString("Participac_Adicionales"));
                modeloMb51.setAdicionales_al_CTO_Estandar(res.getString("Adicionales_al_CTO_Estandar"));
                modeloMb51.setVariance(res.getString("Variance"));
                modeloMb51.setTotal_Costos(res.getString("Total_Costos"));
                modeloMb51.setUnitario_Real(res.getString("Unitario_Real"));
                modeloMb51.setUnitario_Real_adicional_estandar(res.getString("Unitario_Real_adicional_estandar"));
                modeloMb51.setUnitario_estandar_SAP(res.getString("Unitario_estandar_SAP"));
                modeloMb51.setUnitario_final_FIFO(res.getString("Unitario_final_FIFO"));
                modeloMb51.setPorcentaje_Real_Vs_Estandar(res.getString("Porcentaje_Real_Vs_Estandar"));
                modeloMb51.setPorcentaje_fifo_final_vs_Estandar(res.getString("Porcentaje_fifo_final_vs_Estandar"));
                modeloMb51.setCompra_valorada_a_Unit_FIFO(res.getString("Compra_valorada_a_Unit_FIFO"));
                modeloMb51.setVariacion_FIFO_vs_Estandar(res.getString("Variacion_FIFO_vs_Estandar"));
                modeloMb51.setNovedadIco(res.getString("NovedadIco"));
                modeloMb51s.add(modeloMb51);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMb51s;
    }
    
        public LinkedList<ModeloMb51> Select_Todos_x_Archivo() {
        LinkedList<ModeloMb51> modeloMb51s = new LinkedList<ModeloMb51>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;

        String Sql = "SELECT "
                + "Id,"
                + "Plant,"
                + "Purchase_order,"
                + "Material,"
                + "Material_Description,"
                + "Batch,"
                + "Movement_type,"
                + "Movement_Type_Text,"
                + "Item,"
                + "SUM(Quantity) AS 'Quantity',"
                + "SUM(Qty_in_unit_of_entry) AS 'Qty_in_unit_of_entry',"
                + "Unit_of_Entry,"
                + "SUM(Amt_in_loc_cur) AS 'Amt_in_loc_cur',"
                + "Currency,"
                + "Storage_Location,"
                + "Posting_Date,"
                + "Document_Date,"
                + "Material_Document,"
                + "User_Name,"
                + "Vendor,"
                + "Vendor_Name,"
                + "Vendor_Type,"
                + "Month,"
                + "Period,"
                + "Cost_Unit_SAP_en_KG,"
                + "Material_Type,"
                + "Profit_Center,"
                + "link1_Material_Batch,"
                + "link2_PO_position,"
                + "Referencia_vendor,"
                + "TotalQ_ME80FN,"
                + "O_Unit_ME80FN,"
                + "TotalQ_Porcentaje,"
                + "TOTAL_INVOICE_VALUE,"
                + "Factura_Value_Unit,"
                + "PIR_Porcentaje_del_Costo,"
                + "Precio_Unit_moneda_compra,"
                + "Moneda,"
                + "link3_PO_Item,"
                + "Freight,"
                + "Dutys,"
                + "Arancel,"
                + "Total_Costos_Adicionales,"
                + "Participac_Adicionales,"
                + "Adicionales_al_CTO_Estandar,"
                + "Variance,"
                + "Total_Costos,"
                + "Unitario_Real,"
                + "Unitario_Real_adicional_estandar,"
                + "Unitario_estandar_SAP,"
                + "Unitario_final_FIFO,"
                + "Porcentaje_Real_Vs_Estandar,"
                + "Porcentaje_fifo_final_vs_Estandar,"
                + "Compra_valorada_a_Unit_FIFO,"
                + "Variacion_FIFO_vs_Estandar,"
                + "NovedadIco"
                //+ " FROM mb51 WHERE IdArchivo is null group by  Purchase_order, Material, Batch order by Batch, Material";        
                + " FROM MB51_tmp group by Purchase_order, Material, Batch order by Batch, Material";

        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMb51 modeloMb51 = new ModeloMb51();
                modeloMb51.setId(res.getInt("id"));
                modeloMb51.setPlant(res.getString("Plant"));
                modeloMb51.setPurchase_order(res.getString("Purchase_order"));
                modeloMb51.setMaterial(res.getString("Material"));
                modeloMb51.setMaterial_Description(res.getString("Material_Description"));
                modeloMb51.setBatch(res.getString("Batch"));
                modeloMb51.setMovement_type(res.getString("Movement_type"));
                modeloMb51.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloMb51.setItem(res.getString("Item"));
                modeloMb51.setQuantity(res.getString("Quantity"));
                modeloMb51.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloMb51.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51.setCurrency(res.getString("Currency"));
                modeloMb51.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51.setDocument_Date(res.getString("Document_Date"));
                modeloMb51.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51.setUser_Name(res.getString("User_Name"));
                modeloMb51.setVendor(res.getString("Vendor"));
                modeloMb51.setVendor_Name(res.getString("Vendor_Name"));
                modeloMb51.setVendor_Type(res.getString("Vendor_Type"));
                modeloMb51.setMonth(res.getString("Month"));
                modeloMb51.setPeriod(res.getString("Period"));
                modeloMb51.setCost_Unit_SAP_en_KG(res.getString("Cost_Unit_SAP_en_KG"));
                modeloMb51.setMaterial_Type(res.getString("Material_Type"));
                modeloMb51.setProfit_Center(res.getString("Profit_Center"));
                modeloMb51.setLink1_Material_Batch(res.getString("link1_Material_Batch"));
                modeloMb51.setLink2_PO_position(res.getString("link2_PO_position"));
                modeloMb51.setReferencia_vendor(res.getString("Referencia_vendor"));
                modeloMb51.setTotalQ_ME80FN(res.getString("TotalQ_ME80FN"));
                modeloMb51.setO_Unit_ME80FN(res.getString("O_Unit_ME80FN"));
                modeloMb51.setTotalQ_Porcentaje(res.getString("TotalQ_Porcentaje"));
                modeloMb51.setTOTAL_INVOICE_VALUE(res.getString("TOTAL_INVOICE_VALUE"));
                modeloMb51.setFactura_Value_Unit(res.getString("Factura_Value_Unit"));
                modeloMb51.setPIR_Porcentaje_del_Costo(res.getString("PIR_Porcentaje_del_Costo"));
                modeloMb51.setPrecio_Unit_moneda_compra(res.getString("Precio_Unit_moneda_compra"));
                modeloMb51.setMoneda(res.getString("Moneda"));
                modeloMb51.setLink3_PO_Item(res.getString("link3_PO_Item"));
                modeloMb51.setFreight(res.getString("Freight"));
                modeloMb51.setDutys(res.getString("Dutys"));
                modeloMb51.setArancel(res.getString("Arancel"));
                modeloMb51.setTotal_Costos_Adicionales(res.getString("Total_Costos_Adicionales"));
                modeloMb51.setParticipac_Adicionales(res.getString("Participac_Adicionales"));
                modeloMb51.setAdicionales_al_CTO_Estandar(res.getString("Adicionales_al_CTO_Estandar"));
                modeloMb51.setVariance(res.getString("Variance"));
                modeloMb51.setTotal_Costos(res.getString("Total_Costos"));
                modeloMb51.setUnitario_Real(res.getString("Unitario_Real"));
                modeloMb51.setUnitario_Real_adicional_estandar(res.getString("Unitario_Real_adicional_estandar"));
                modeloMb51.setUnitario_estandar_SAP(res.getString("Unitario_estandar_SAP"));
                modeloMb51.setUnitario_final_FIFO(res.getString("Unitario_final_FIFO"));
                modeloMb51.setPorcentaje_Real_Vs_Estandar(res.getString("Porcentaje_Real_Vs_Estandar"));
                modeloMb51.setPorcentaje_fifo_final_vs_Estandar(res.getString("Porcentaje_fifo_final_vs_Estandar"));
                modeloMb51.setCompra_valorada_a_Unit_FIFO(res.getString("Compra_valorada_a_Unit_FIFO"));
                modeloMb51.setVariacion_FIFO_vs_Estandar(res.getString("Variacion_FIFO_vs_Estandar"));
                modeloMb51.setNovedadIco(res.getString("NovedadIco"));
                modeloMb51s.add(modeloMb51);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMb51s;
    }

    public boolean Update(ModeloMb51 modeloMb51) throws SQLException {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE mb51 SET "
                    + "Plant = ?, "
                    + "Purchase_order = ?, "
                    + "Material = ?, "
                    + "Material_Description = ?, "
                    + "Batch = ?, "
                    + "Movement_type = ?, "
                    + "Movement_Type_Text = ?, "
                    + "Item = ?, "
                    + "Quantity = ?, "
                    + "Qty_in_unit_of_entry = ?, "
                    + "Unit_of_Entry = ?, "
                    + "Amt_in_loc_cur = ?, "
                    + "Currency = ?, "
                    + "Storage_Location = ?, "
                    + "Posting_Date = ?, "
                    + "Document_Date = ?, "
                    + "Material_Document = ?, "
                    + "User_Name = ?, "
                    + "Vendor = ?, "
                    + "Vendor_Name = ?, "
                    + "Vendor_Type = ?, "
                    + "Month = ?, "
                    + "Period = ?, "
                    + "Cost_Unit_SAP_en_KG = ?, "
                    + "Material_Type = ?, "
                    + "Profit_Center = ?, "
                    + "link1_Material_Batch = ?, "
                    + "link2_PO_position = ?, "
                    + "Referencia_vendor = ?, "
                    + "TotalQ_ME80FN = ?, "
                    + "O_Unit_ME80FN = ?, "
                    + "TotalQ_Porcentaje = ?, "
                    + "TOTAL_INVOICE_VALUE = ?, "
                    + "Factura_Value_Unit = ?, "
                    + "PIR_Porcentaje_del_Costo = ?, "
                    + "Precio_Unit_moneda_compra = ?, "
                    + "Moneda = ?, "
                    + "link3_PO_Item = ?, "
                    + "Freight = ?, "
                    + "Dutys = ?, "
                    + "Arancel = ?, "
                    + "Total_Costos_Adicionales = ?, "
                    + "Participac_Adicionales = ?, "
                    + "Adicionales_al_CTO_Estandar = ?, "
                    + "Variance = ?, "
                    + "Total_Costos = ?, "
                    + "Unitario_Real = ?, "
                    + "Unitario_Real_adicional_estandar = ?, "
                    + "Unitario_estandar_SAP = ?, "
                    + "Unitario_final_FIFO = ?, "
                    + "Porcentaje_Real_Vs_Estandar = ?, "
                    + "Porcentaje_fifo_final_vs_Estandar = ?, "
                    + "Compra_valorada_a_Unit_FIFO = ?, "
                    + "Variacion_FIFO_vs_Estandar = ?, "
                    + "NovedadIco = ? "
                    + " WHERE Id = ? ");
            SQL.setString(1, modeloMb51.getPlant());
            SQL.setString(2, modeloMb51.getPurchase_order());
            SQL.setString(3, modeloMb51.getMaterial());
            SQL.setString(4, modeloMb51.getMaterial_Description());
            SQL.setString(5, modeloMb51.getBatch());
            SQL.setString(6, modeloMb51.getMovement_type());
            SQL.setString(7, modeloMb51.getMovement_Type_Text());
            SQL.setString(8, modeloMb51.getItem());
            SQL.setString(9, modeloMb51.getQuantity());
            SQL.setString(10, modeloMb51.getQty_in_unit_of_entry());
            SQL.setString(11, modeloMb51.getUnit_of_Entry());
            SQL.setString(12, modeloMb51.getAmt_in_loc_cur());
            SQL.setString(13, modeloMb51.getCurrency());
            SQL.setString(14, modeloMb51.getStorage_Location());
            SQL.setString(15, modeloMb51.getPosting_Date());
            SQL.setString(16, modeloMb51.getDocument_Date());
            SQL.setString(17, modeloMb51.getMaterial_Document());
            SQL.setString(18, modeloMb51.getUser_Name());
            SQL.setString(19, modeloMb51.getVendor());
            SQL.setString(20, modeloMb51.getVendor_Name());
            SQL.setString(21, modeloMb51.getVendor_Type());
            SQL.setString(22, modeloMb51.getMonth());
            SQL.setString(23, modeloMb51.getPeriod());
            SQL.setString(24, modeloMb51.getCost_Unit_SAP_en_KG());
            SQL.setString(25, modeloMb51.getMaterial_Type());
            SQL.setString(26, modeloMb51.getProfit_Center());
            SQL.setString(27, modeloMb51.getLink1_Material_Batch());
            SQL.setString(28, modeloMb51.getLink2_PO_position());
            SQL.setString(29, modeloMb51.getReferencia_vendor());
            SQL.setString(30, modeloMb51.getTotalQ_ME80FN());
            SQL.setString(31, modeloMb51.getO_Unit_ME80FN());
            SQL.setString(32, modeloMb51.getTotalQ_Porcentaje());
            SQL.setString(33, modeloMb51.getTOTAL_INVOICE_VALUE());
            SQL.setString(34, modeloMb51.getFactura_Value_Unit());
            SQL.setString(35, modeloMb51.getPIR_Porcentaje_del_Costo());
            SQL.setString(36, modeloMb51.getPrecio_Unit_moneda_compra());
            SQL.setString(37, modeloMb51.getMoneda());
            SQL.setString(38, modeloMb51.getLink3_PO_Item());
            SQL.setString(39, modeloMb51.getFreight());
            SQL.setString(40, modeloMb51.getDutys());
            SQL.setString(41, modeloMb51.getArancel());
            SQL.setString(42, modeloMb51.getTotal_Costos_Adicionales());
            SQL.setString(43, modeloMb51.getParticipac_Adicionales());
            SQL.setString(44, modeloMb51.getAdicionales_al_CTO_Estandar());
            SQL.setString(45, modeloMb51.getVariance());
            SQL.setString(46, modeloMb51.getTotal_Costos());
            SQL.setString(47, modeloMb51.getUnitario_Real());
            SQL.setString(48, modeloMb51.getUnitario_Real_adicional_estandar());
            SQL.setString(49, modeloMb51.getUnitario_estandar_SAP());
            SQL.setString(50, modeloMb51.getUnitario_final_FIFO());
            SQL.setString(51, modeloMb51.getPorcentaje_Real_Vs_Estandar());
            SQL.setString(52, modeloMb51.getPorcentaje_fifo_final_vs_Estandar());
            SQL.setString(53, modeloMb51.getCompra_valorada_a_Unit_FIFO());
            SQL.setString(54, modeloMb51.getVariacion_FIFO_vs_Estandar());
            SQL.setString(55, modeloMb51.getNovedadIco());
            SQL.setInt(56, modeloMb51.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
            SQL.close();
            con.close();
        }
        return resul;
    }

    public boolean UpdateList(LinkedList<ModeloMb51> listModeloMb51) throws SQLException {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE mb51 SET "
                    + "Plant = ?, "
                    + "Purchase_order = ?, "
                    + "Material = ?, "
                    + "Material_Description = ?, "
                    + "Batch = ?, "
                    + "Movement_type = ?, "
                    + "Movement_Type_Text = ?, "
                    + "Item = ?, "
                    + "Quantity = ?, "
                    + "Qty_in_unit_of_entry = ?, "
                    + "Unit_of_Entry = ?, "
                    + "Amt_in_loc_cur = ?, "
                    + "Currency = ?, "
                    + "Storage_Location = ?, "
                    + "Posting_Date = ?, "
                    + "Document_Date = ?, "
                    + "Material_Document = ?, "
                    + "User_Name = ?, "
                    + "Vendor = ?, "
                    + "Vendor_Name = ?, "
                    + "Vendor_Type = ?, "
                    + "Month = ?, "
                    + "Period = ?, "
                    + "Cost_Unit_SAP_en_KG = ?, "
                    + "Material_Type = ?, "
                    + "Profit_Center = ?, "
                    + "link1_Material_Batch = ?, "
                    + "link2_PO_position = ?, "
                    + "Referencia_vendor = ?, "
                    + "TotalQ_ME80FN = ?, "
                    + "O_Unit_ME80FN = ?, "
                    + "TotalQ_Porcentaje = ?, "
                    + "TOTAL_INVOICE_VALUE = ?, "
                    + "Factura_Value_Unit = ?, "
                    + "PIR_Porcentaje_del_Costo = ?, "
                    + "Precio_Unit_moneda_compra = ?, "
                    + "Moneda = ?, "
                    + "link3_PO_Item = ?, "
                    + "Freight = ?, "
                    + "Dutys = ?, "
                    + "Arancel = ?, "
                    + "Total_Costos_Adicionales = ?, "
                    + "Participac_Adicionales = ?, "
                    + "Adicionales_al_CTO_Estandar = ?, "
                    + "Variance = ?, "
                    + "Total_Costos = ?, "
                    + "Unitario_Real = ?, "
                    + "Unitario_Real_adicional_estandar = ?, "
                    + "Unitario_estandar_SAP = ?, "
                    + "Unitario_final_FIFO = ?, "
                    + "Porcentaje_Real_Vs_Estandar = ?, "
                    + "Porcentaje_fifo_final_vs_Estandar = ?, "
                    + "Compra_valorada_a_Unit_FIFO = ?, "
                    + "Variacion_FIFO_vs_Estandar = ?, "
                    + "NovedadIco = ? "
                    + " WHERE Id = ? ");
            for (ModeloMb51 modeloMb51 : listModeloMb51) {
                SQL.setString(1, modeloMb51.getPlant());
                SQL.setString(2, modeloMb51.getPurchase_order());
                SQL.setString(3, modeloMb51.getMaterial());
                SQL.setString(4, modeloMb51.getMaterial_Description());
                SQL.setString(5, modeloMb51.getBatch());
                SQL.setString(6, modeloMb51.getMovement_type());
                SQL.setString(7, modeloMb51.getMovement_Type_Text());
                SQL.setString(8, modeloMb51.getItem());
                SQL.setString(9, modeloMb51.getQuantity());
                SQL.setString(10, modeloMb51.getQty_in_unit_of_entry());
                SQL.setString(11, modeloMb51.getUnit_of_Entry());
                SQL.setString(12, modeloMb51.getAmt_in_loc_cur());
                SQL.setString(13, modeloMb51.getCurrency());
                SQL.setString(14, modeloMb51.getStorage_Location());
                SQL.setString(15, modeloMb51.getPosting_Date());
                SQL.setString(16, modeloMb51.getDocument_Date());
                SQL.setString(17, modeloMb51.getMaterial_Document());
                SQL.setString(18, modeloMb51.getUser_Name());
                SQL.setString(19, modeloMb51.getVendor());
                SQL.setString(20, modeloMb51.getVendor_Name());
                SQL.setString(21, modeloMb51.getVendor_Type());
                SQL.setString(22, modeloMb51.getMonth());
                SQL.setString(23, modeloMb51.getPeriod());
                SQL.setString(24, modeloMb51.getCost_Unit_SAP_en_KG());
                SQL.setString(25, modeloMb51.getMaterial_Type());
                SQL.setString(26, modeloMb51.getProfit_Center());
                SQL.setString(27, modeloMb51.getLink1_Material_Batch());
                SQL.setString(28, modeloMb51.getLink2_PO_position());
                SQL.setString(29, modeloMb51.getReferencia_vendor());
                SQL.setString(30, modeloMb51.getTotalQ_ME80FN());
                SQL.setString(31, modeloMb51.getO_Unit_ME80FN());
                SQL.setString(32, modeloMb51.getTotalQ_Porcentaje());
                SQL.setString(33, modeloMb51.getTOTAL_INVOICE_VALUE());
                SQL.setString(34, modeloMb51.getFactura_Value_Unit());
                SQL.setString(35, modeloMb51.getPIR_Porcentaje_del_Costo());
                SQL.setString(36, modeloMb51.getPrecio_Unit_moneda_compra());
                SQL.setString(37, modeloMb51.getMoneda());
                SQL.setString(38, modeloMb51.getLink3_PO_Item());
                SQL.setString(39, modeloMb51.getFreight());
                SQL.setString(40, modeloMb51.getDutys());
                SQL.setString(41, modeloMb51.getArancel());
                SQL.setString(42, modeloMb51.getTotal_Costos_Adicionales());
                SQL.setString(43, modeloMb51.getParticipac_Adicionales());
                SQL.setString(44, modeloMb51.getAdicionales_al_CTO_Estandar());
                SQL.setString(45, modeloMb51.getVariance());
                SQL.setString(46, modeloMb51.getTotal_Costos());
                SQL.setString(47, modeloMb51.getUnitario_Real());
                SQL.setString(48, modeloMb51.getUnitario_Real_adicional_estandar());
                SQL.setString(49, modeloMb51.getUnitario_estandar_SAP());
                SQL.setString(50, modeloMb51.getUnitario_final_FIFO());
                SQL.setString(51, modeloMb51.getPorcentaje_Real_Vs_Estandar());
                SQL.setString(52, modeloMb51.getPorcentaje_fifo_final_vs_Estandar());
                SQL.setString(53, modeloMb51.getCompra_valorada_a_Unit_FIFO());
                SQL.setString(54, modeloMb51.getVariacion_FIFO_vs_Estandar());
                SQL.setString(55, modeloMb51.getNovedadIco());
                SQL.setInt(56, modeloMb51.getId());
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
            SQL.close();
            con.close();
        }
        return resul;
    }

    LinkedList<ModeloMb51> modeloMb51s = new LinkedList<ModeloMb51>();

    public LinkedList<ModeloMb51> SelectSql(String Sql, Connection con) {
        //    LinkedList<ModeloMb51> modeloMb51s = new LinkedList<ModeloMb51>();
        modeloMb51s.clear();
        //ConexionBDMySql conexion = new ConexionBDMySql();
        //Connection con;
        //con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMb51 modeloMb51 = new ModeloMb51();
                modeloMb51.setId(res.getInt("id"));
                modeloMb51.setPlant(res.getString("Plant"));
                modeloMb51.setPurchase_order(res.getString("Purchase_order"));
                modeloMb51.setMaterial(res.getString("Material"));
                modeloMb51.setMaterial_Description(res.getString("Material_Description"));
                modeloMb51.setBatch(res.getString("Batch"));
                modeloMb51.setMovement_type(res.getString("Movement_type"));
                modeloMb51.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloMb51.setItem(res.getString("Item"));
                modeloMb51.setQuantity(res.getString("Quantity"));
                modeloMb51.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloMb51.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51.setCurrency(res.getString("Currency"));
                modeloMb51.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51.setDocument_Date(res.getString("Document_Date"));
                modeloMb51.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51.setUser_Name(res.getString("User_Name"));
                modeloMb51.setVendor(res.getString("Vendor"));
                modeloMb51.setVendor_Name(res.getString("Vendor_Name"));
                modeloMb51.setVendor_Type(res.getString("Vendor_Type"));
                modeloMb51.setMonth(res.getString("Month"));
                modeloMb51.setPeriod(res.getString("Period"));
                modeloMb51.setCost_Unit_SAP_en_KG(res.getString("Cost_Unit_SAP_en_KG"));
                modeloMb51.setMaterial_Type(res.getString("Material_Type"));
                modeloMb51.setProfit_Center(res.getString("Profit_Center"));
                modeloMb51.setLink1_Material_Batch(res.getString("link1_Material_Batch"));
                modeloMb51.setLink2_PO_position(res.getString("link2_PO_position"));
                modeloMb51.setReferencia_vendor(res.getString("Referencia_vendor"));
                modeloMb51.setTotalQ_ME80FN(res.getString("TotalQ_ME80FN"));
                modeloMb51.setO_Unit_ME80FN(res.getString("O_Unit_ME80FN"));
                modeloMb51.setTotalQ_Porcentaje(res.getString("TotalQ_Porcentaje"));
                modeloMb51.setTOTAL_INVOICE_VALUE(res.getString("TOTAL_INVOICE_VALUE"));
                modeloMb51.setFactura_Value_Unit(res.getString("Factura_Value_Unit"));
                modeloMb51.setPIR_Porcentaje_del_Costo(res.getString("PIR_Porcentaje_del_Costo"));
                modeloMb51.setPrecio_Unit_moneda_compra(res.getString("Precio_Unit_moneda_compra"));
                modeloMb51.setMoneda(res.getString("Moneda"));
                modeloMb51.setLink3_PO_Item(res.getString("link3_PO_Item"));
                modeloMb51.setFreight(res.getString("Freight"));
                modeloMb51.setDutys(res.getString("Dutys"));
                modeloMb51.setArancel(res.getString("Arancel"));
                modeloMb51.setTotal_Costos_Adicionales(res.getString("Total_Costos_Adicionales"));
                modeloMb51.setParticipac_Adicionales(res.getString("Participac_Adicionales"));
                modeloMb51.setAdicionales_al_CTO_Estandar(res.getString("Adicionales_al_CTO_Estandar"));
                modeloMb51.setVariance(res.getString("Variance"));
                modeloMb51.setTotal_Costos(res.getString("Total_Costos"));
                modeloMb51.setUnitario_Real(res.getString("Unitario_Real"));
                modeloMb51.setUnitario_Real_adicional_estandar(res.getString("Unitario_Real_adicional_estandar"));
                modeloMb51.setUnitario_estandar_SAP(res.getString("Unitario_estandar_SAP"));
                modeloMb51.setUnitario_final_FIFO(res.getString("Unitario_final_FIFO"));
                modeloMb51.setPorcentaje_Real_Vs_Estandar(res.getString("Porcentaje_Real_Vs_Estandar"));
                modeloMb51.setPorcentaje_fifo_final_vs_Estandar(res.getString("Porcentaje_fifo_final_vs_Estandar"));
                modeloMb51.setCompra_valorada_a_Unit_FIFO(res.getString("Compra_valorada_a_Unit_FIFO"));
                modeloMb51.setVariacion_FIFO_vs_Estandar(res.getString("Variacion_FIFO_vs_Estandar"));
                modeloMb51.setNovedadIco(res.getString("NovedadIco"));
                modeloMb51s.add(modeloMb51);
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMb51s;
    }

}
