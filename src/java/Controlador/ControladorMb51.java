/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
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

    public boolean Insert(ModeloMb51 modelo) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO mb51("
                        + "`Plant`,"
                        + "`Purchase_order`,"
                        + "`Material`,"
                        + "`Material_Description`,"
                        + "`Batch`,"
                        + "`Movement_type`,"
                        + "`Movement_Type_Text`,"
                        + "`Item`,"
                        + "`Quantity`,"
                        + "`Qty_in:unit_of_entry`,"
                        + "`Unit_of_Entry`,"
                        + "`Amt_in_loc_cur`,"
                        + "`Currency`,"
                        + "`Storage_Location`,"
                        + "`Posting_Date`,"
                        + "`Document_Date`,"
                        + "`Material_Document`,"
                        + "`User_Name`,"
                        + "`Vendor`,"
                        + "`Order`,"
                        + "`Vendor_Name`,"
                        + "`Vendor_Type`,"
                        + "`Month`,"
                        + "`Period`,"
                        + "`Material_Type`,"
                        + "`Profit_Center`,"
                        + "`link1_PO_+_Material`,"
                        + "`link2_PO_+_position`,"
                        + "`Referencia_&_vendor`,"
                        + "`TotalQ_ME80FN`,"
                        + "`TotalQ_%`,"
                        + "`TOTAL_INVOICE_VALUE`,"
                        + "`Factura_Value_Unit`,"
                        + "`PIR_%`,"
                        + "`Moneda`,"
                        + "`Freight`,"
                        + "`Dutys`,"
                        + "`Arancel`,"
                        + "`Ajuste_PIR`,"
                        + "`Otros`,"
                        + "`Total_Costos_Adicionales`,"
                        + "`Participac_Adicionales`,"
                        + "`Total_Costos`,"
                        + "`Unitario_final_FIFO`,"
                        + "`Unitario_estandar`,"
                        + "`%_Real_Vs_Estándar`)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                SQL.setString(1, modelo.getPlant());
                SQL.setString(2, modelo.getPurchase_order());
                SQL.setString(3, modelo.getMaterial());
                SQL.setString(4, modelo.getMaterial_Description());
                SQL.setString(5, modelo.getBatch());
                SQL.setString(6, modelo.getMovement_type());
                SQL.setString(7, modelo.getMovement_Type_Text());
                SQL.setString(8, modelo.getItem());
                SQL.setString(9, modelo.getQuantity());
                SQL.setString(10, modelo.getQty_in_unit_of_entry());
                SQL.setString(11, modelo.getUnit_of_Entry());
                SQL.setString(12, modelo.getAmt_in_loc_cur());
                SQL.setString(13, modelo.getCurrency());
                SQL.setString(14, modelo.getStorage_Location());
                SQL.setString(15, modelo.getPosting_Date());
                SQL.setString(16, modelo.getDocument_Date());
                SQL.setString(17, modelo.getMaterial_Document());
                SQL.setString(18, modelo.getUser_Name());
                SQL.setString(19, modelo.getVendor());
                SQL.setString(20, modelo.getOrder());
                SQL.setString(21, modelo.getVendor_Name());
                SQL.setString(22, modelo.getVendor_Type());
                SQL.setString(23, modelo.getMonth());
                SQL.setString(24, modelo.getPeriod());
                SQL.setString(25, modelo.getMaterial_Type());
                SQL.setString(26, modelo.getProfit_Center());
                SQL.setString(27, modelo.getLink1_PO_Mas_Material());
                SQL.setString(28, modelo.getLink2_PO_Mas_position());
                SQL.setString(29, modelo.getReferencia_Y_vendor());
                SQL.setString(30, modelo.getTotalQ_ME80FN());
                SQL.setString(31, modelo.getTotalQ_Porcentaje());
                SQL.setString(32, modelo.getTOTAL_INVOICE_VALUE());
                SQL.setString(33, modelo.getFactura_Value_Unit());
                SQL.setString(34, modelo.getPIR_Porcentaje());
                SQL.setString(35, modelo.getMoneda());
                SQL.setString(36, modelo.getFreightString());
                SQL.setString(37, modelo.getDutys());
                SQL.setString(38, modelo.getArancel());
                SQL.setString(39, modelo.getAjuste_PIR());
                SQL.setString(40, modelo.getOtros());
                SQL.setString(41, modelo.getTotal_Costos_Adicionales());
                SQL.setString(42, modelo.getParticipac_Adicionales());
                SQL.setString(43, modelo.getTotal_Costos());
                SQL.setString(44, modelo.getUnitario_final_FIFO());
                SQL.setString(45, modelo.getUnitario_estandar());
                SQL.setString(46, modelo.getPorcentaje_Real_Vs_Estándar());
                if (SQL.executeUpdate() > 0)
                {
                    resul = true;
                }
            } catch (SQLException e)
            {
                System.out.println("Error en la consulta SQL Insert " + e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean Insert(LinkedList<ModeloMb51> listModeloMb51s) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO mb51("
                        + "`Plant`,"
                        + "`Purchase_order`,"
                        + "`Material`,"
                        + "`Material_Description`,"
                        + "`Batch`,"
                        + "`Movement_type`,"
                        + "`Movement_Type_Text`,"
                        + "`Item`,"
                        + "`Quantity`,"
                        + "`Qty_in:unit_of_entry`,"
                        + "`Unit_of_Entry`,"
                        + "`Amt_in_loc_cur`,"
                        + "`Currency`,"
                        + "`Storage_Location`,"
                        + "`Posting_Date`,"
                        + "`Document_Date`,"
                        + "`Material_Document`,"
                        + "`User_Name`,"
                        + "`Vendor`,"
                        + "`Order`,"
                        + "`Vendor_Name`,"
                        + "`Vendor_Type`,"
                        + "`Month`,"
                        + "`Period`,"
                        + "`Material_Type`,"
                        + "`Profit_Center`,"
                        + "`link1_PO_+_Material`,"
                        + "`link2_PO_+_position`,"
                        + "`Referencia_&_vendor`,"
                        + "`TotalQ_ME80FN`,"
                        + "`TotalQ_%`,"
                        + "`TOTAL_INVOICE_VALUE`,"
                        + "`Factura_Value_Unit`,"
                        + "`PIR_%`,"
                        + "`Moneda`,"
                        + "`Freight`,"
                        + "`Dutys`,"
                        + "`Arancel`,"
                        + "`Ajuste_PIR`,"
                        + "`Otros`,"
                        + "`Total_Costos_Adicionales`,"
                        + "`Participac_Adicionales`,"
                        + "`Total_Costos`,"
                        + "`Unitario_final_FIFO`,"
                        + "`Unitario_estandar`,"
                        + "`%_Real_Vs_Estándar`)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                for (ModeloMb51 modelo : listModeloMb51s)
                {
                    SQL.setString(1, modelo.getPlant());
                    SQL.setString(2, modelo.getPurchase_order());
                    SQL.setString(3, modelo.getMaterial());
                    SQL.setString(4, modelo.getMaterial_Description());
                    SQL.setString(5, modelo.getBatch());
                    SQL.setString(6, modelo.getMovement_type());
                    SQL.setString(7, modelo.getMovement_Type_Text());
                    SQL.setString(8, modelo.getItem());
                    SQL.setString(9, modelo.getQuantity());
                    SQL.setString(10, modelo.getQty_in_unit_of_entry());
                    SQL.setString(11, modelo.getUnit_of_Entry());
                    SQL.setString(12, modelo.getAmt_in_loc_cur());
                    SQL.setString(13, modelo.getCurrency());
                    SQL.setString(14, modelo.getStorage_Location());
                    SQL.setString(15, modelo.getPosting_Date());
                    SQL.setString(16, modelo.getDocument_Date());
                    SQL.setString(17, modelo.getMaterial_Document());
                    SQL.setString(18, modelo.getUser_Name());
                    SQL.setString(19, modelo.getVendor());
                    SQL.setString(20, modelo.getOrder());
                    SQL.setString(21, modelo.getVendor_Name());
                    SQL.setString(22, modelo.getVendor_Type());
                    SQL.setString(23, modelo.getMonth());
                    SQL.setString(24, modelo.getPeriod());
                    SQL.setString(25, modelo.getMaterial_Type());
                    SQL.setString(26, modelo.getProfit_Center());
                    SQL.setString(27, modelo.getLink1_PO_Mas_Material());
                    SQL.setString(28, modelo.getLink2_PO_Mas_position());
                    SQL.setString(29, modelo.getReferencia_Y_vendor());
                    SQL.setString(30, modelo.getTotalQ_ME80FN());
                    SQL.setString(31, modelo.getTotalQ_Porcentaje());
                    SQL.setString(32, modelo.getTOTAL_INVOICE_VALUE());
                    SQL.setString(33, modelo.getFactura_Value_Unit());
                    SQL.setString(34, modelo.getPIR_Porcentaje());
                    SQL.setString(35, modelo.getMoneda());
                    SQL.setString(36, modelo.getFreightString());
                    SQL.setString(37, modelo.getDutys());
                    SQL.setString(38, modelo.getArancel());
                    SQL.setString(39, modelo.getAjuste_PIR());
                    SQL.setString(40, modelo.getOtros());
                    SQL.setString(41, modelo.getTotal_Costos_Adicionales());
                    SQL.setString(42, modelo.getParticipac_Adicionales());
                    SQL.setString(43, modelo.getTotal_Costos());
                    SQL.setString(44, modelo.getUnitario_final_FIFO());
                    SQL.setString(45, modelo.getUnitario_estandar());
                    SQL.setString(46, modelo.getPorcentaje_Real_Vs_Estándar());
                    if (SQL.executeUpdate() > 0)
                    {
                        resul = true;
                    }
                }
            } catch (SQLException e)
            {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean Update(ModeloMb51 modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE mb51 SET"
                    + "`Plant` = ?,"
                    + "`Purchase_order` = ?,"
                    + "`Material` = ?,"
                    + "`Material_Description` = ?,"
                    + "`Batch` = ?,"
                    + "`Movement_type` = ?,"
                    + "`Movement_Type_Text` = ?,"
                    + "`Item` = ?,"
                    + "`Quantity` = ?,"
                    + "`Qty_in?unit_of_entry` = ?,"
                    + "`Unit_of_Entry` = ?,"
                    + "`Amt_in_loc_cur` = ?,"
                    + "`Currency` = ?,"
                    + "`Storage_Location` = ?,"
                    + "`Posting_Date` = ?,"
                    + "`Document_Date` = ?,"
                    + "`Material_Document` = ?,"
                    + "`User_Name` = ?,"
                    + "`Vendor` = ?,"
                    + "`Order` = ?,"
                    + "`Vendor_Name` = ?,"
                    + "`Vendor_Type` = ?,"
                    + "`Month` = ?,"
                    + "`Period` = ?,"
                    + "`Material_Type` = ?,"
                    + "`Profit_Center` = ?,"
                    + "`link1_PO_+_Material` = ?,"
                    + "`link2_PO_+_position` = ?,"
                    + "`Referencia_&_vendor` = ?,"
                    + "`TotalQ_ME80FN` = ?,"
                    + "`TotalQ_%` = ?,"
                    + "`TOTAL_INVOICE_VALUE` = ?,"
                    + "`Factura_Value_Unit` = ?,"
                    + "`PIR_%` = ?,"
                    + "`Moneda` = ?,"
                    + "`Freight` = ?,"
                    + "`Dutys` = ?,"
                    + "`Arancel` = ?,"
                    + "`Ajuste_PIR` = ?,"
                    + "`Otros` = ?,"
                    + "`Total_Costos_Adicionales` = ?,"
                    + "`Participac_Adicionales` = ?,"
                    + "`Total_Costos` = ?,"
                    + "`Unitario_final_FIFO` = ?,"
                    + "`Unitario_estandar` = ?,"
                    + "`%_Real_Vs_Estándar` = ?"
                    + "WHERE Id = ?;");
            SQL.setString(1, modelo.getPlant());
            SQL.setString(2, modelo.getPurchase_order());
            SQL.setString(3, modelo.getMaterial());
            SQL.setString(4, modelo.getMaterial_Description());
            SQL.setString(5, modelo.getBatch());
            SQL.setString(6, modelo.getMovement_type());
            SQL.setString(7, modelo.getMovement_Type_Text());
            SQL.setString(8, modelo.getItem());
            SQL.setString(9, modelo.getQuantity());
            SQL.setString(10, modelo.getQty_in_unit_of_entry());
            SQL.setString(11, modelo.getUnit_of_Entry());
            SQL.setString(12, modelo.getAmt_in_loc_cur());
            SQL.setString(13, modelo.getCurrency());
            SQL.setString(14, modelo.getStorage_Location());
            SQL.setString(15, modelo.getPosting_Date());
            SQL.setString(16, modelo.getDocument_Date());
            SQL.setString(17, modelo.getMaterial_Document());
            SQL.setString(18, modelo.getUser_Name());
            SQL.setString(19, modelo.getVendor());
            SQL.setString(20, modelo.getOrder());
            SQL.setString(21, modelo.getVendor_Name());
            SQL.setString(22, modelo.getVendor_Type());
            SQL.setString(23, modelo.getMonth());
            SQL.setString(24, modelo.getPeriod());
            SQL.setString(25, modelo.getMaterial_Type());
            SQL.setString(26, modelo.getProfit_Center());
            SQL.setString(27, modelo.getLink1_PO_Mas_Material());
            SQL.setString(28, modelo.getLink2_PO_Mas_position());
            SQL.setString(29, modelo.getReferencia_Y_vendor());
            SQL.setString(30, modelo.getTotalQ_ME80FN());
            SQL.setString(31, modelo.getTotalQ_Porcentaje());
            SQL.setString(32, modelo.getTOTAL_INVOICE_VALUE());
            SQL.setString(33, modelo.getFactura_Value_Unit());
            SQL.setString(34, modelo.getPIR_Porcentaje());
            SQL.setString(35, modelo.getMoneda());
            SQL.setString(36, modelo.getFreightString());
            SQL.setString(37, modelo.getDutys());
            SQL.setString(38, modelo.getArancel());
            SQL.setString(39, modelo.getAjuste_PIR());
            SQL.setString(40, modelo.getOtros());
            SQL.setString(41, modelo.getTotal_Costos_Adicionales());
            SQL.setString(42, modelo.getParticipac_Adicionales());
            SQL.setString(43, modelo.getTotal_Costos());
            SQL.setString(44, modelo.getUnitario_final_FIFO());
            SQL.setString(45, modelo.getUnitario_estandar());
            SQL.setString(46, modelo.getPorcentaje_Real_Vs_Estándar());
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Update " + e);
        }
        return resul;
    }

    public boolean Update(LinkedList<ModeloMb51> listModeloMb51s) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE mb51 SET"
                    + "`Plant` = ?,"
                    + "`Purchase_order` = ?,"
                    + "`Material` = ?,"
                    + "`Material_Description` = ?,"
                    + "`Batch` = ?,"
                    + "`Movement_type` = ?,"
                    + "`Movement_Type_Text` = ?,"
                    + "`Item` = ?,"
                    + "`Quantity` = ?,"
                    + "`Qty_in?unit_of_entry` = ?,"
                    + "`Unit_of_Entry` = ?,"
                    + "`Amt_in_loc_cur` = ?,"
                    + "`Currency` = ?,"
                    + "`Storage_Location` = ?,"
                    + "`Posting_Date` = ?,"
                    + "`Document_Date` = ?,"
                    + "`Material_Document` = ?,"
                    + "`User_Name` = ?,"
                    + "`Vendor` = ?,"
                    + "`Order` = ?,"
                    + "`Vendor_Name` = ?,"
                    + "`Vendor_Type` = ?,"
                    + "`Month` = ?,"
                    + "`Period` = ?,"
                    + "`Material_Type` = ?,"
                    + "`Profit_Center` = ?,"
                    + "`link1_PO_+_Material` = ?,"
                    + "`link2_PO_+_position` = ?,"
                    + "`Referencia_&_vendor` = ?,"
                    + "`TotalQ_ME80FN` = ?,"
                    + "`TotalQ_%` = ?,"
                    + "`TOTAL_INVOICE_VALUE` = ?,"
                    + "`Factura_Value_Unit` = ?,"
                    + "`PIR_%` = ?,"
                    + "`Moneda` = ?,"
                    + "`Freight` = ?,"
                    + "`Dutys` = ?,"
                    + "`Arancel` = ?,"
                    + "`Ajuste_PIR` = ?,"
                    + "`Otros` = ?,"
                    + "`Total_Costos_Adicionales` = ?,"
                    + "`Participac_Adicionales` = ?,"
                    + "`Total_Costos` = ?,"
                    + "`Unitario_final_FIFO` = ?,"
                    + "`Unitario_estandar` = ?,"
                    + "`%_Real_Vs_Estándar` = ?"
                    + "WHERE Id = ?;");
            for (ModeloMb51 modelo : listModeloMb51s)
            {
                SQL.setString(1, modelo.getPlant());
                SQL.setString(2, modelo.getPurchase_order());
                SQL.setString(3, modelo.getMaterial());
                SQL.setString(4, modelo.getMaterial_Description());
                SQL.setString(5, modelo.getBatch());
                SQL.setString(6, modelo.getMovement_type());
                SQL.setString(7, modelo.getMovement_Type_Text());
                SQL.setString(8, modelo.getItem());
                SQL.setString(9, modelo.getQuantity());
                SQL.setString(10, modelo.getQty_in_unit_of_entry());
                SQL.setString(11, modelo.getUnit_of_Entry());
                SQL.setString(12, modelo.getAmt_in_loc_cur());
                SQL.setString(13, modelo.getCurrency());
                SQL.setString(14, modelo.getStorage_Location());
                SQL.setString(15, modelo.getPosting_Date());
                SQL.setString(16, modelo.getDocument_Date());
                SQL.setString(17, modelo.getMaterial_Document());
                SQL.setString(18, modelo.getUser_Name());
                SQL.setString(19, modelo.getVendor());
                SQL.setString(20, modelo.getOrder());
                SQL.setString(21, modelo.getVendor_Name());
                SQL.setString(22, modelo.getVendor_Type());
                SQL.setString(23, modelo.getMonth());
                SQL.setString(24, modelo.getPeriod());
                SQL.setString(25, modelo.getMaterial_Type());
                SQL.setString(26, modelo.getProfit_Center());
                SQL.setString(27, modelo.getLink1_PO_Mas_Material());
                SQL.setString(28, modelo.getLink2_PO_Mas_position());
                SQL.setString(29, modelo.getReferencia_Y_vendor());
                SQL.setString(30, modelo.getTotalQ_ME80FN());
                SQL.setString(31, modelo.getTotalQ_Porcentaje());
                SQL.setString(32, modelo.getTOTAL_INVOICE_VALUE());
                SQL.setString(33, modelo.getFactura_Value_Unit());
                SQL.setString(34, modelo.getPIR_Porcentaje());
                SQL.setString(35, modelo.getMoneda());
                SQL.setString(36, modelo.getFreightString());
                SQL.setString(37, modelo.getDutys());
                SQL.setString(38, modelo.getArancel());
                SQL.setString(39, modelo.getAjuste_PIR());
                SQL.setString(40, modelo.getOtros());
                SQL.setString(41, modelo.getTotal_Costos_Adicionales());
                SQL.setString(42, modelo.getParticipac_Adicionales());
                SQL.setString(43, modelo.getTotal_Costos());
                SQL.setString(44, modelo.getUnitario_final_FIFO());
                SQL.setString(45, modelo.getUnitario_estandar());
                SQL.setString(46, modelo.getPorcentaje_Real_Vs_Estándar());
                if (SQL.executeUpdate() > 0)
                {
                    resul = true;
                }
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Update " + e);
        }
        return resul;
    }

    public boolean Delete(ModeloMb51 modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `mb51` WHERE `Id` = ?;");
            SQL.setInt(1, modelo.getId());
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
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
        try
        {
            SQL = con.prepareStatement("DELETE FROM `mb51`;");
            if (SQL.executeUpdate() > 0)
            {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
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
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "  `Id`,"
                    + "  `Plant`,"
                    + "  `Purchase_order`,"
                    + "  `Material`,"
                    + "  `Material_Description`,"
                    + "  `Batch`,"
                    + "  `Movement_type`,"
                    + "  `Movement_Type_Text`,"
                    + "  `Item`,"
                    + "  `Quantity`,"
                    + "  `Qty_in_unit_of_entry`,"
                    + "  `Unit_of_Entry`,"
                    + "  `Amt_in_loc_cur`,"
                    + "  `Currency`,"
                    + "  `Storage_Location`,"
                    + "  `Posting_Date`,"
                    + "  `Document_Date`,"
                    + "  `Material_Document`,"
                    + "  `User_Name`,"
                    + "  `Vendor`,"
                    + "  `Order1`,"
                    + "  `Vendor_Name`,"
                    + "  `Vendor_Type`,"
                    + "  `Month`,"
                    + "  `Period`,"
                    + "  `Material_Type`,"
                    + "  `Profit_Center`,"
                    + "  `link1_PO_+_Material`,"
                    + "  `link2_PO_+_position`,"
                    + "  `Referencia_&_vendor`,"
                    + "  `TotalQ_ME80FN`,"
                    + "  `TotalQ_%`,"
                    + "  `TOTAL_INVOICE_VALUE`,"
                    + "  `Factura_Value_Unit`,"
                    + "  `PIR_%`,"
                    + "  `Moneda`,"
                    + "  `Freight`,"
                    + "  `Dutys`,"
                    + "  `Arancel`,"
                    + "  `Ajuste_PIR`,"
                    + "  `Otros`,"
                    + "  `Total_Costos_Adicionales`,"
                    + "  `Participac_Adicionales`,"
                    + "  `Total_Costos`,"
                    + "  `Unitario_final_FIFO`,"
                    + "  `Unitario_estandar`,"
                    + "  `%_Real_Vs_Estándar`"
                    + "FROM "
                    + "  `mb51`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
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
                modeloMb51.setOrder(res.getString("Order1"));
                modeloMb51.setVendor_Name(res.getString("Vendor_Name"));
                modeloMb51.setVendor_Type(res.getString("Vendor_Type"));
                modeloMb51.setMonth(res.getString("Month"));
                modeloMb51.setPeriod(res.getString("Period"));
                modeloMb51.setMaterial_Type(res.getString("Material_Type"));
                modeloMb51.setProfit_Center(res.getString("Profit_Center"));
                modeloMb51.setLink1_PO_Mas_Material(res.getString("link1_PO_+_Material"));
                modeloMb51.setLink2_PO_Mas_position(res.getString("link2_PO_+_position"));
                modeloMb51.setReferencia_Y_vendor(res.getString("Referencia_&_vendor"));
                modeloMb51.setTotalQ_ME80FN(res.getString("TotalQ_ME80FN"));
                modeloMb51.setTotalQ_Porcentaje(res.getString("TotalQ_%"));
                modeloMb51.setTOTAL_INVOICE_VALUE(res.getString("TOTAL_INVOICE_VALUE"));
                modeloMb51.setFactura_Value_Unit(res.getString("Factura_Value_Unit"));
                modeloMb51.setPIR_Porcentaje(res.getString("PIR_%"));
                modeloMb51.setMoneda(res.getString("Moneda"));
                modeloMb51.setFreightString(res.getString("Freight"));
                modeloMb51.setDutys(res.getString("Dutys"));
                modeloMb51.setArancel(res.getString("Arancel"));
                modeloMb51.setAjuste_PIR(res.getString("Ajuste_PIR"));
                modeloMb51.setOtros(res.getString("Otros"));
                modeloMb51.setTotal_Costos_Adicionales(res.getString("Total_Costos_Adicionales"));
                modeloMb51.setParticipac_Adicionales(res.getString("Participac_Adicionales"));
                modeloMb51.setTotal_Costos(res.getString("Total_Costos"));
                modeloMb51.setUnitario_final_FIFO(res.getString("Unitario_final_FIFO"));
                modeloMb51.setUnitario_estandar(res.getString("Unitario_estandar"));
                modeloMb51.setPorcentaje_Real_Vs_Estándar(res.getString("%_Real_Vs_Estándar"));
                modeloMb51s.add(modeloMb51);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMb51s;
    }

    public ModeloMb51 Select(Integer Id) {
        ModeloMb51 modeloMb51 = new ModeloMb51();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "  `Id`,"
                    + "  `Plant`,"
                    + "  `Purchase_order`,"
                    + "  `Material`,"
                    + "  `Material_Description`,"
                    + "  `Batch`,"
                    + "  `Movement_type`,"
                    + "  `Movement_Type_Text`,"
                    + "  `Item`,"
                    + "  `Quantity`,"
                    + "  `Qty_in:unit_of_entry`,"
                    + "  `Unit_of_Entry`,"
                    + "  `Amt_in_loc_cur`,"
                    + "  `Currency`,"
                    + "  `Storage_Location`,"
                    + "  `Posting_Date`,"
                    + "  `Document_Date`,"
                    + "  `Material_Document`,"
                    + "  `User_Name`,"
                    + "  `Vendor`,"
                    + "  `Order`,"
                    + "  `Vendor_Name`,"
                    + "  `Vendor_Type`,"
                    + "  `Month`,"
                    + "  `Period`,"
                    + "  `Material_Type`,"
                    + "  `Profit_Center`,"
                    + "  `link1_PO_+_Material`,"
                    + "  `link2_PO_+_position`,"
                    + "  `Referencia_&_vendor`,"
                    + "  `TotalQ_ME80FN`,"
                    + "  `TotalQ_%`,"
                    + "  `TOTAL_INVOICE_VALUE`,"
                    + "  `Factura_Value_Unit`,"
                    + "  `PIR_%`,"
                    + "  `Moneda`,"
                    + "  `Freight`,"
                    + "  `Dutys`,"
                    + "  `Arancel`,"
                    + "  `Ajuste_PIR`,"
                    + "  `Otros`,"
                    + "  `Total_Costos_Adicionales`,"
                    + "  `Participac_Adicionales`,"
                    + "  `Total_Costos`,"
                    + "  `Unitario_final_FIFO`,"
                    + "  `Unitario_estandar`,"
                    + "  `%_Real_Vs_Estándar`"
                    + "FROM "
                    + "  `mb51`;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
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
                modeloMb51.setQty_in_unit_of_entry(res.getString("Qty_in:unit_of_entry"));
                modeloMb51.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51.setCurrency(res.getString("Currency"));
                modeloMb51.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51.setDocument_Date(res.getString("Document_Date"));
                modeloMb51.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51.setUser_Name(res.getString("User_Name"));
                modeloMb51.setVendor(res.getString("Vendor"));
                modeloMb51.setOrder(res.getString("Order"));
                modeloMb51.setVendor_Name(res.getString("Vendor_Name"));
                modeloMb51.setVendor_Type(res.getString("Vendor_Type"));
                modeloMb51.setMonth(res.getString("Month"));
                modeloMb51.setPeriod(res.getString("Period"));
                modeloMb51.setMaterial_Type(res.getString("Material_Type"));
                modeloMb51.setProfit_Center(res.getString("Profit_Center"));
                modeloMb51.setLink1_PO_Mas_Material(res.getString("link1_PO_+_Material"));
                modeloMb51.setLink2_PO_Mas_position(res.getString("link2_PO_+_position"));
                modeloMb51.setReferencia_Y_vendor(res.getString("Referencia_&_vendor"));
                modeloMb51.setTotalQ_ME80FN(res.getString("TotalQ_ME80FN"));
                modeloMb51.setTotalQ_Porcentaje(res.getString("TotalQ_%"));
                modeloMb51.setTOTAL_INVOICE_VALUE(res.getString("TOTAL_INVOICE_VALUE"));
                modeloMb51.setFactura_Value_Unit(res.getString("Factura_Value_Unit"));
                modeloMb51.setPIR_Porcentaje(res.getString("PIR_%"));
                modeloMb51.setMoneda(res.getString("Moneda"));
                modeloMb51.setFreightString(res.getString("Freight"));
                modeloMb51.setDutys(res.getString("Dutys"));
                modeloMb51.setArancel(res.getString("Arancel"));
                modeloMb51.setAjuste_PIR(res.getString("Ajuste_PIR"));
                modeloMb51.setOtros(res.getString("Otros"));
                modeloMb51.setTotal_Costos_Adicionales(res.getString("Total_Costos_Adicionales"));
                modeloMb51.setParticipac_Adicionales(res.getString("Participac_Adicionales"));
                modeloMb51.setTotal_Costos(res.getString("Total_Costos"));
                modeloMb51.setUnitario_final_FIFO(res.getString("Unitario_final_FIFO"));
                modeloMb51.setUnitario_estandar(res.getString("Unitario_estandar"));
                modeloMb51.setPorcentaje_Real_Vs_Estándar(res.getString("%_Real_Vs_Estándar"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMb51;
    }
}
