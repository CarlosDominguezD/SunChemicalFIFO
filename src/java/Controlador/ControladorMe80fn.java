/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloMe80fn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorMe80fn {

    public boolean Insert(ModeloMe80fn modelo) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `me80fn`("
                        + "`Index`,"
                        + "`Index2`,"
                        + "`Purchasing_Document`,"
                        + "`Material_Doc_Year`,"
                        + "`Material_Document`,"
                        + "`Document_Date`,"
                        + "`Material`,"
                        + "`Short_Text`,"
                        + "`Batch`,"
                        + "`Item`,"
                        + "`Movement_type`,"
                        + "`Posting_Date`,"
                        + "`Delivery_Completed`,"
                        + "`Plant`,"
                        + "`Quantity`,"
                        + "`Amt_in_loc_cur`,"
                        + "`Amount`,"
                        + "`Currency`,"
                        + "`Valuation_Type`,"
                        + "`Entry_Date`,"
                        + "`Local_currency`,"
                        + "`Reference_Doc_Item`,"
                        + "`Invoice_Value`,"
                        + "`Invoice_Value_in_FC`)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                SQL.setString(1, modelo.getIndex());
                SQL.setString(2, modelo.getIndex2());
                SQL.setString(3, modelo.getPurchasing_Document());
                SQL.setString(4, modelo.getMaterial_Doc_Year());
                SQL.setString(5, modelo.getMaterial_Document());
                SQL.setString(6, modelo.getDocument_Date());
                SQL.setString(7, modelo.getMaterial());
                SQL.setString(8, modelo.getShort_Text());
                SQL.setString(9, modelo.getBatch());
                SQL.setString(10, modelo.getItem());
                SQL.setString(11, modelo.getMovement_type());
                SQL.setString(12, modelo.getPosting_Date());
                SQL.setString(13, modelo.getDelivery_Completed());
                SQL.setString(14, modelo.getPlant());
                SQL.setString(15, modelo.getQuantity());
                SQL.setString(16, modelo.getAmt_in_loc_cur());
                SQL.setString(17, modelo.getAmount());
                SQL.setString(18, modelo.getCurrency());
                SQL.setString(19, modelo.getValuation_Type());
                SQL.setString(20, modelo.getEntry_Date());
                SQL.setString(21, modelo.getLocal_currency());
                SQL.setString(22, modelo.getReference_Doc_Item());
                SQL.setString(23, modelo.getInvoice_Value());
                SQL.setString(24, modelo.getInvoice_Value_in_FC());
                if (SQL.executeUpdate() > 0)
                {
                    resul = true;
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

    public boolean Insert(LinkedList<ModeloMe80fn> listModeloMe80fns) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `me80fn`("
                        + "`Index`,"
                        + "`Index2`,"
                        + "`Purchasing_Document`,"
                        + "`Material_Doc_Year`,"
                        + "`Material_Document`,"
                        + "`Document_Date`,"
                        + "`Material`,"
                        + "`Short_Text`,"
                        + "`Batch`,"
                        + "`Item`,"
                        + "`Movement_type`,"
                        + "`Posting_Date`,"
                        + "`Delivery_Completed`,"
                        + "`Plant`,"
                        + "`Quantity`,"
                        + "`Amt_in_loc_cur`,"
                        + "`Amount`,"
                        + "`Currency`,"
                        + "`Valuation_Type`,"
                        + "`Entry_Date`,"
                        + "`Local_currency`,"
                        + "`Reference_Doc_Item`,"
                        + "`Invoice_Value`,"
                        + "`Invoice_Value_in_FC`)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ModeloMe80fn modelo : listModeloMe80fns)
                {
                    SQL.setString(1, modelo.getIndex());
                    SQL.setString(2, modelo.getIndex2());
                    SQL.setString(3, modelo.getPurchasing_Document());
                    SQL.setString(4, modelo.getMaterial_Doc_Year());
                    SQL.setString(5, modelo.getMaterial_Document());
                    SQL.setString(6, modelo.getDocument_Date());
                    SQL.setString(7, modelo.getMaterial());
                    SQL.setString(8, modelo.getShort_Text());
                    SQL.setString(9, modelo.getBatch());
                    SQL.setString(10, modelo.getItem());
                    SQL.setString(11, modelo.getMovement_type());
                    SQL.setString(12, modelo.getPosting_Date());
                    SQL.setString(13, modelo.getDelivery_Completed());
                    SQL.setString(14, modelo.getPlant());
                    SQL.setString(15, modelo.getQuantity());
                    SQL.setString(16, modelo.getAmt_in_loc_cur());
                    SQL.setString(17, modelo.getAmount());
                    SQL.setString(18, modelo.getCurrency());
                    SQL.setString(19, modelo.getValuation_Type());
                    SQL.setString(20, modelo.getEntry_Date());
                    SQL.setString(21, modelo.getLocal_currency());
                    SQL.setString(22, modelo.getReference_Doc_Item());
                    SQL.setString(23, modelo.getInvoice_Value());
                    SQL.setString(24, modelo.getInvoice_Value_in_FC());
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
            System.out.println("Error en la consulta SQL Insert " + e);;
        }
        return resul;
    }

    public boolean Update(ModeloMe80fn modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `me80fn` SET "
                    + "`Index` = ?,"
                    + "`Index2` = ?,"
                    + "`Purchasing_Document` = ?,"
                    + "`Material_Doc_Year` = ?,"
                    + "`Material_Document` = ?,"
                    + "`Document_Date` = ?,"
                    + "`Material` = ?,"
                    + "`Short_Text` = ?,"
                    + "`Batch` = ?,"
                    + "`Item` = ?,"
                    + "`Movement_type` = ?,"
                    + "`Posting_Date` = ?,"
                    + "`Delivery_Completed` = ?,"
                    + "`Plant` = ?,"
                    + "`Quantity` = ?,"
                    + "`Amt_in_loc_cur` = ?,"
                    + "`Amount` = ?,"
                    + "`Currency` = ?,"
                    + "`Valuation_Type` = ?,"
                    + "`Entry_Date` = ?,"
                    + "`Local_currency` = ?,"
                    + "`Reference_Doc_Item` = ?,"
                    + "`Invoice_Value` = ?,"
                    + "`Invoice_Value_in_FC` = ?"
                    + " WHERE `Id` = ?;");
            SQL.setString(1, modelo.getIndex());
            SQL.setString(2, modelo.getIndex2());
            SQL.setString(3, modelo.getPurchasing_Document());
            SQL.setString(4, modelo.getMaterial_Doc_Year());
            SQL.setString(5, modelo.getMaterial_Document());
            SQL.setString(6, modelo.getDocument_Date());
            SQL.setString(7, modelo.getMaterial());
            SQL.setString(8, modelo.getShort_Text());
            SQL.setString(9, modelo.getBatch());
            SQL.setString(10, modelo.getItem());
            SQL.setString(11, modelo.getMovement_type());
            SQL.setString(12, modelo.getPosting_Date());
            SQL.setString(13, modelo.getDelivery_Completed());
            SQL.setString(14, modelo.getPlant());
            SQL.setString(15, modelo.getQuantity());
            SQL.setString(16, modelo.getAmt_in_loc_cur());
            SQL.setString(17, modelo.getAmount());
            SQL.setString(18, modelo.getCurrency());
            SQL.setString(19, modelo.getValuation_Type());
            SQL.setString(20, modelo.getEntry_Date());
            SQL.setString(21, modelo.getLocal_currency());
            SQL.setString(22, modelo.getReference_Doc_Item());
            SQL.setString(23, modelo.getInvoice_Value());
            SQL.setString(24, modelo.getInvoice_Value_in_FC());
            SQL.setInt(25, modelo.getId());
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

    public boolean Update(LinkedList<ModeloMe80fn> listModeloMe80fns) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `me80fn` SET "
                    + "`Index` = ?,"
                    + "`Index2` = ?,"
                    + "`Purchasing_Document` = ?,"
                    + "`Material_Doc_Year` = ?,"
                    + "`Material_Document` = ?,"
                    + "`Document_Date` = ?,"
                    + "`Material` = ?,"
                    + "`Short_Text` = ?,"
                    + "`Batch` = ?,"
                    + "`Item` = ?,"
                    + "`Movement_type` = ?,"
                    + "`Posting_Date` = ?,"
                    + "`Delivery_Completed` = ?,"
                    + "`Plant` = ?,"
                    + "`Quantity` = ?,"
                    + "`Amt_in_loc_cur` = ?,"
                    + "`Amount` = ?,"
                    + "`Currency` = ?,"
                    + "`Valuation_Type` = ?,"
                    + "`Entry_Date` = ?,"
                    + "`Local_currency` = ?,"
                    + "`Reference_Doc_Item` = ?,"
                    + "`Invoice_Value` = ?,"
                    + "`Invoice_Value_in_FC` = ?"
                    + " WHERE `Id` = ?;");
            for (ModeloMe80fn modelo : listModeloMe80fns)
            {
                SQL.setString(1, modelo.getIndex());
                SQL.setString(2, modelo.getIndex2());
                SQL.setString(3, modelo.getPurchasing_Document());
                SQL.setString(4, modelo.getMaterial_Doc_Year());
                SQL.setString(5, modelo.getMaterial_Document());
                SQL.setString(6, modelo.getDocument_Date());
                SQL.setString(7, modelo.getMaterial());
                SQL.setString(8, modelo.getShort_Text());
                SQL.setString(9, modelo.getBatch());
                SQL.setString(10, modelo.getItem());
                SQL.setString(11, modelo.getMovement_type());
                SQL.setString(12, modelo.getPosting_Date());
                SQL.setString(13, modelo.getDelivery_Completed());
                SQL.setString(14, modelo.getPlant());
                SQL.setString(15, modelo.getQuantity());
                SQL.setString(16, modelo.getAmt_in_loc_cur());
                SQL.setString(17, modelo.getAmount());
                SQL.setString(18, modelo.getCurrency());
                SQL.setString(19, modelo.getValuation_Type());
                SQL.setString(20, modelo.getEntry_Date());
                SQL.setString(21, modelo.getLocal_currency());
                SQL.setString(22, modelo.getReference_Doc_Item());
                SQL.setString(23, modelo.getInvoice_Value());
                SQL.setString(24, modelo.getInvoice_Value_in_FC());
                SQL.setInt(25, modelo.getId());
                SQL.setInt(25, modelo.getId());
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

    public boolean Delete(ModeloMe80fn modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `me80fn` WHERE `Id` = ?;");
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
            SQL = con.prepareStatement("DELETE FROM `me80fn`;");
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

    public LinkedList<ModeloMe80fn> Select() {
        LinkedList<ModeloMe80fn> modeloMe80fns = new LinkedList<ModeloMe80fn>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Index`,"
                    + "`Index2`,"
                    + "`Purchasing_Document`,"
                    + "`Material_Doc_Year`,"
                    + "`Material_Document`,"
                    + "`Document_Date`,"
                    + "`Material`,"
                    + "`Short_Text`,"
                    + "`Batch`,"
                    + "`Item`,"
                    + "`Movement_type`,"
                    + "`Posting_Date`,"
                    + "`Delivery_Completed`,"
                    + "`Plant`,"
                    + "`Quantity`,"
                    + "`Amt_in_loc_cur`,"
                    + "`Amount`,"
                    + "`Currency`,"
                    + "`Valuation_Type`,"
                    + "`Entry_Date`,"
                    + "`Local_currency`,"
                    + "`Reference_Doc_Item`,"
                    + "`Invoice_Value`,"
                    + "`Invoice_Value_in_FC` "
                    + "FROM `me80fn`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloMe80fn modeloMe80fn = new ModeloMe80fn();
                modeloMe80fn.setId(res.getInt("id"));
                modeloMe80fn.setIndex(res.getString("Index"));
                modeloMe80fn.setIndex2(res.getString("Index2"));
                modeloMe80fn.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloMe80fn.setMaterial_Doc_Year(res.getString("Material_Doc_Year"));
                modeloMe80fn.setMaterial_Document(res.getString("Material_Document"));
                modeloMe80fn.setDocument_Date(res.getString("Document_Date"));
                modeloMe80fn.setMaterial(res.getString("Material"));
                modeloMe80fn.setShort_Text(res.getString("Short_Text"));
                modeloMe80fn.setBatch(res.getString("Batch"));
                modeloMe80fn.setItem(res.getString("Item"));
                modeloMe80fn.setMovement_type(res.getString("Movement_type"));
                modeloMe80fn.setPosting_Date(res.getString("Posting_Date"));
                modeloMe80fn.setDelivery_Completed(res.getString("Delivery_Completed"));
                modeloMe80fn.setPlant(res.getString("Plant"));
                modeloMe80fn.setQuantity(res.getString("Quantity"));
                modeloMe80fn.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMe80fn.setAmount(res.getString("Amount"));
                modeloMe80fn.setCurrency(res.getString("Currency"));
                modeloMe80fn.setValuation_Type(res.getString("Valuation_Type"));
                modeloMe80fn.setEntry_Date(res.getString("Entry_Date"));
                modeloMe80fn.setLocal_currency(res.getString("Local_currency"));
                modeloMe80fn.setReference_Doc_Item(res.getString("Reference_Doc_Item"));
                modeloMe80fn.setInvoice_Value(res.getString("Invoice_Value"));
                modeloMe80fn.setInvoice_Value_in_FC(res.getString("Invoice_Value_in_FC"));
                modeloMe80fns.add(modeloMe80fn);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMe80fns;
    }

    public ModeloMe80fn Select(Integer Id) {
        ModeloMe80fn modeloMe80fn = new ModeloMe80fn();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Index`,"
                    + "`Index2`,"
                    + "`Purchasing_Document`,"
                    + "`Material_Doc_Year`,"
                    + "`Material_Document`,"
                    + "`Document_Date`,"
                    + "`Material`,"
                    + "`Short_Text`,"
                    + "`Batch`,"
                    + "`Item`,"
                    + "`Movement_type`,"
                    + "`Posting_Date`,"
                    + "`Delivery_Completed`,"
                    + "`Plant`,"
                    + "`Quantity`,"
                    + "`Amt_in_loc_cur`,"
                    + "`Amount`,"
                    + "`Currency`,"
                    + "`Valuation_Type`,"
                    + "`Entry_Date`,"
                    + "`Local_currency`,"
                    + "`Reference_Doc_Item`,"
                    + "`Invoice_Value`,"
                    + "`Invoice_Value_in_FC` "
                    + "FROM `me80fn`;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloMe80fn.setId(res.getInt("id"));
                modeloMe80fn.setIndex(res.getString("Index"));
                modeloMe80fn.setIndex2(res.getString("Index2"));
                modeloMe80fn.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloMe80fn.setMaterial_Doc_Year(res.getString("Material_Doc_Year"));
                modeloMe80fn.setMaterial_Document(res.getString("Material_Document"));
                modeloMe80fn.setDocument_Date(res.getString("Document_Date"));
                modeloMe80fn.setMaterial(res.getString("Material"));
                modeloMe80fn.setShort_Text(res.getString("Short_Text"));
                modeloMe80fn.setBatch(res.getString("Batch"));
                modeloMe80fn.setItem(res.getString("Item"));
                modeloMe80fn.setMovement_type(res.getString("Movement_type"));
                modeloMe80fn.setPosting_Date(res.getString("Posting_Date"));
                modeloMe80fn.setDelivery_Completed(res.getString("Delivery_Completed"));
                modeloMe80fn.setPlant(res.getString("Plant"));
                modeloMe80fn.setQuantity(res.getString("Quantity"));
                modeloMe80fn.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMe80fn.setAmount(res.getString("Amount"));
                modeloMe80fn.setCurrency(res.getString("Currency"));
                modeloMe80fn.setValuation_Type(res.getString("Valuation_Type"));
                modeloMe80fn.setEntry_Date(res.getString("Entry_Date"));
                modeloMe80fn.setLocal_currency(res.getString("Local_currency"));
                modeloMe80fn.setReference_Doc_Item(res.getString("Reference_Doc_Item"));
                modeloMe80fn.setInvoice_Value(res.getString("Invoice_Value"));
                modeloMe80fn.setInvoice_Value_in_FC(res.getString("Invoice_Value_in_FC"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMe80fn;
    }
    
    public ModeloMe80fn SelectSQL(String Sql) {
        ModeloMe80fn modeloMe80fn = new ModeloMe80fn();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloMe80fn.setId(res.getInt("id"));
                modeloMe80fn.setIndex(res.getString("Index"));
                modeloMe80fn.setIndex2(res.getString("Index2"));
                modeloMe80fn.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloMe80fn.setMaterial_Doc_Year(res.getString("Material_Doc_Year"));
                modeloMe80fn.setMaterial_Document(res.getString("Material_Document"));
                modeloMe80fn.setDocument_Date(res.getString("Document_Date"));
                modeloMe80fn.setMaterial(res.getString("Material"));
                modeloMe80fn.setShort_Text(res.getString("Short_Text"));
                modeloMe80fn.setBatch(res.getString("Batch"));
                modeloMe80fn.setItem(res.getString("Item"));
                modeloMe80fn.setMovement_type(res.getString("Movement_type"));
                modeloMe80fn.setPosting_Date(res.getString("Posting_Date"));
                modeloMe80fn.setDelivery_Completed(res.getString("Delivery_Completed"));
                modeloMe80fn.setPlant(res.getString("Plant"));
                modeloMe80fn.setQuantity(res.getString("Quantity"));
                modeloMe80fn.setOrder_Unit(res.getString("Order_Unit"));
                modeloMe80fn.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMe80fn.setAmount(res.getString("Amount"));
                modeloMe80fn.setCurrency(res.getString("Currency"));
                modeloMe80fn.setValuation_Type(res.getString("Valuation_Type"));
                modeloMe80fn.setEntry_Date(res.getString("Entry_Date"));
                modeloMe80fn.setLocal_currency(res.getString("Local_currency"));
                modeloMe80fn.setReference_Doc_Item(res.getString("Reference_Doc_Item"));
                modeloMe80fn.setInvoice_Value(res.getString("Invoice_Value"));
                modeloMe80fn.setInvoice_Value_in_FC(res.getString("Invoice_Value_in_FC"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMe80fn;
    }
    
    public LinkedList<ModeloMe80fn> ListSelectSQL(String Sql) {
        LinkedList<ModeloMe80fn> modeloMe80fns = new LinkedList<ModeloMe80fn>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloMe80fn modeloMe80fn = new ModeloMe80fn();
                modeloMe80fn.setId(res.getInt("id"));
                modeloMe80fn.setIndex(res.getString("Index"));
                modeloMe80fn.setIndex2(res.getString("Index2"));
                modeloMe80fn.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloMe80fn.setMaterial_Doc_Year(res.getString("Material_Doc_Year"));
                modeloMe80fn.setMaterial_Document(res.getString("Material_Document"));
                modeloMe80fn.setDocument_Date(res.getString("Document_Date"));
                modeloMe80fn.setMaterial(res.getString("Material"));
                modeloMe80fn.setShort_Text(res.getString("Short_Text"));
                modeloMe80fn.setBatch(res.getString("Batch"));
                modeloMe80fn.setItem(res.getString("Item"));
                modeloMe80fn.setMovement_type(res.getString("Movement_type"));
                modeloMe80fn.setPosting_Date(res.getString("Posting_Date"));
                modeloMe80fn.setDelivery_Completed(res.getString("Delivery_Completed"));
                modeloMe80fn.setPlant(res.getString("Plant"));
                modeloMe80fn.setQuantity(res.getString("Quantity"));
                modeloMe80fn.setOrder_Unit(res.getString("Order_Unit"));
                modeloMe80fn.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMe80fn.setAmount(res.getString("Amount"));
                modeloMe80fn.setCurrency(res.getString("Currency"));
                modeloMe80fn.setValuation_Type(res.getString("Valuation_Type"));
                modeloMe80fn.setEntry_Date(res.getString("Entry_Date"));
                modeloMe80fn.setLocal_currency(res.getString("Local_currency"));
                modeloMe80fn.setReference_Doc_Item(res.getString("Reference_Doc_Item"));
                modeloMe80fn.setInvoice_Value(res.getString("Invoice_Value"));
                modeloMe80fn.setInvoice_Value_in_FC(res.getString("Invoice_Value_in_FC"));
                modeloMe80fns.add(modeloMe80fn);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMe80fns;
    }

}
