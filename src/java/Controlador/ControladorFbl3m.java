/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloFbl3m;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorFbl3m {

    public boolean Insert(ModeloFbl3m modelo) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
//                SQL = con.prepareStatement("INSERT INTO `fbl3m`("
//                        + "`Document_Number`,"
//                        + "`Document_type`,"
//                        + "`Document_Date`,"
//                        + "`Posting_Date`,"
//                        + "`Cost_Center`,"
//                        + "`Profit_Center`,"
//                        + "`YearMonth`,"
//                        + "`Account`,"
//                        + "`Plant`,"
//                        + "`Material`,"
//                        + "`Quantity`,"
//                        + "`Amount_in_local_currency`,"
//                        + "`Local_Currency`,"
//                        + "`Purchasing_Document`,"
//                        + "`Reference`,"
//                        + "`Document_currency`,"
//                        + "`Offsetting_acct_no`,"
//                        + "`Base_Unit_of_Measure`,"
//                        + "`Alternative_Account_No`,"
//                        + "`Transaction_Code`,"
//                        + "`Text`,"
//                        + "`Assignment`,"
//                        + "`Clasificacion`) "
//                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                SQL = con.prepareStatement("INSERT INTO fbl3m ("
                        + "Document_Number,"
                        + "Document_type,"
                        + "Document_Date,"
                        + "Posting_Date,"
                        + "Cost_Center,"
                        + "Profit_Center,"
                        + "YearMonth,"
                        + "Account,"
                        + "Plant,"
                        + "Material,"
                        + "Quantity,"
                        + "Amount_in_local_currency,"
                        + "Local_Currency,"
                        + "Purchasing_Document,"
                        + "Reference,"
                        + "Document_currency,"
                        + "Offsetting_acct_no,"
                        + "Base_Unit_of_Measure,"
                        + "Alternative_Account_No,"
                        + "Transaction_Code,"
                        + "Text,"
                        + "Assignment) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                SQL.setString(1, modelo.getDocument_Number());
                SQL.setString(2, modelo.getDocument_type());
                SQL.setString(3, modelo.getDocument_Date());
                SQL.setString(4, modelo.getPosting_Date());
                SQL.setString(5, modelo.getCost_Center());
                SQL.setString(6, modelo.getProfit_Center());
                SQL.setString(7, modelo.getYearMonth());
                SQL.setString(8, modelo.getAccount());
                SQL.setString(9, modelo.getPlant());
                SQL.setString(10, modelo.getMaterial());
                SQL.setString(11, modelo.getQuantity());
                SQL.setString(12, modelo.getAmount_in_local_currency());
                SQL.setString(13, modelo.getLocal_Currency());
                SQL.setString(14, modelo.getPurchasing_Document());
                SQL.setString(15, modelo.getReference());
                SQL.setString(16, modelo.getDocument_currency());
                SQL.setString(17, modelo.getOffsetting_acct_no());
                SQL.setString(18, modelo.getBase_Unit_of_Measure());
                SQL.setString(19, modelo.getAlternative_Account_No());
                SQL.setString(20, modelo.getTransaction_Code());
                SQL.setString(21, modelo.getText());
                SQL.setString(22, modelo.getAssignment());
//                SQL.setString(1, modelo.getDocument_Number());
//                SQL.setString(2, modelo.getDocument_type());
//                SQL.setString(3, modelo.getDocument_Date());
//                SQL.setString(4, modelo.getPosting_Date());
//                SQL.setString(5, modelo.getCost_Center());
//                SQL.setString(6, modelo.getProfit_Center());
//                SQL.setString(7, modelo.getYearMonth());
//                SQL.setString(8, modelo.getAccount());
//                SQL.setString(9, modelo.getPlant());
//                SQL.setString(10, modelo.getMaterial());
//                SQL.setString(11, modelo.getQuantity());
//                SQL.setString(12, modelo.getAmount_in_local_currency());
//                SQL.setString(13, modelo.getLocal_Currency());
//                SQL.setString(14, modelo.getPurchasing_Document());
//                SQL.setString(15, modelo.getReference());
//                SQL.setString(16, modelo.getDocument_currency());
//                SQL.setString(17, modelo.getOffsetting_acct_no());
//                SQL.setString(18, modelo.getBase_Unit_of_Measure());
//                SQL.setString(19, modelo.getAlternative_Account_No());
//                SQL.setString(20, modelo.getTransaction_Code());
//                SQL.setString(21, modelo.getText());
//                SQL.setString(22, modelo.getAssignment());
//                SQL.setString(23, modelo.getClasificacion());
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

    public boolean Insert(String Sql) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement(Sql);
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

    public boolean Insert(LinkedList<ModeloFbl3m> listModeloFbl3ms) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO fbl3m ("
                        + "Document_Number,"
                        + "Document_type,"
                        + "Document_Date,"
                        + "Posting_Date,"
                        + "Cost_Center,"
                        + "Profit_Center,"
                        + "YearMonth,"
                        + "Account,"
                        + "Plant,"
                        + "Material,"
                        + "Quantity,"
                        + "Amount_in_local_currency,"
                        + "Local_Currency,"
                        + "Purchasing_Document,"
                        + "Reference,"
                        + "Document_currency,"
                        + "Offsetting_acct_no,"
                        + "Base_Unit_of_Measure,"
                        + "Alternative_Account_No,"
                        + "Transaction_Code,"
                        + "Text,"
                        + "Assignment) "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ModeloFbl3m modelo : listModeloFbl3ms)
                {
                    SQL.setString(1, modelo.getDocument_Number());
                    SQL.setString(2, modelo.getDocument_type());
                    SQL.setString(3, modelo.getDocument_Date());
                    SQL.setString(4, modelo.getPosting_Date());
                    SQL.setString(5, modelo.getCost_Center());
                    SQL.setString(6, modelo.getProfit_Center());
                    SQL.setString(7, modelo.getYearMonth());
                    SQL.setString(8, modelo.getAccount());
                    SQL.setString(9, modelo.getPlant());
                    SQL.setString(10, modelo.getMaterial());
                    SQL.setString(11, modelo.getQuantity());
                    SQL.setString(12, modelo.getAmount_in_local_currency());
                    SQL.setString(13, modelo.getLocal_Currency());
                    SQL.setString(14, modelo.getPurchasing_Document());
                    SQL.setString(15, modelo.getReference());
                    SQL.setString(16, modelo.getDocument_currency());
                    SQL.setString(17, modelo.getOffsetting_acct_no());
                    SQL.setString(18, modelo.getBase_Unit_of_Measure());
                    SQL.setString(19, modelo.getAlternative_Account_No());
                    SQL.setString(20, modelo.getTransaction_Code());
                    SQL.setString(21, modelo.getText());
                    SQL.setString(22, modelo.getAssignment());
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

    public boolean Update(ModeloFbl3m modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `fbl3m` SET "
                    + "`Document_Number` = ?,"
                    + "`Document_type` = ?,"
                    + "`Document_Date` = ?,"
                    + "`Posting_Date` = ?,"
                    + "`Cost_Center` = ?,"
                    + "`Profit_Center` = ?,"
                    + "`YearMonth` = ?,"
                    + "`Account` = ?,"
                    + "`Plant` = ?,"
                    + "`Material` = ?,"
                    + "`Quantity` = ?,"
                    + "`Amount_in_local_currency` = ?,"
                    + "`Local_Currency` = ?,"
                    + "`Purchasing_Document` = ?,"
                    + "`Reference` = ?,"
                    + "`Document_currency` = ?,"
                    + "`Offsetting_acct_no` = ?,"
                    + "`Base_Unit_of_Measure` = ?,"
                    + "`Alternative_Account_No` = ?,"
                    + "`Transaction_Code` = ?,"
                    + "`Text` = ?,"
                    + "`Assignment` = ?,"
                    + "`Clasificacion` = ?"
                    + " WHERE `Id` = ?");
            SQL.setString(1, modelo.getDocument_Number());
            SQL.setString(2, modelo.getDocument_type());
            SQL.setString(3, modelo.getDocument_Date());
            SQL.setString(4, modelo.getPosting_Date());
            SQL.setString(5, modelo.getCost_Center());
            SQL.setString(6, modelo.getProfit_Center());
            SQL.setString(7, modelo.getYearMonth());
            SQL.setString(8, modelo.getAccount());
            SQL.setString(9, modelo.getPlant());
            SQL.setString(10, modelo.getMaterial());
            SQL.setString(11, modelo.getQuantity());
            SQL.setString(12, modelo.getAmount_in_local_currency());
            SQL.setString(13, modelo.getLocal_Currency());
            SQL.setString(14, modelo.getPurchasing_Document());
            SQL.setString(15, modelo.getReference());
            SQL.setString(16, modelo.getDocument_currency());
            SQL.setString(17, modelo.getOffsetting_acct_no());
            SQL.setString(18, modelo.getBase_Unit_of_Measure());
            SQL.setString(19, modelo.getAlternative_Account_No());
            SQL.setString(20, modelo.getTransaction_Code());
            SQL.setString(21, modelo.getText());
            SQL.setString(22, modelo.getAssignment());
            SQL.setString(23, modelo.getClasificacion());
            SQL.setInt(24, modelo.getId());
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

    public boolean Update(LinkedList<ModeloFbl3m> listModeloFbl3ms) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `fbl3m` SET "
                    + "`Document_Number` = ?,"
                    + "`Document_type` = ?,"
                    + "`Document_Date` = ?,"
                    + "`Posting_Date` = ?,"
                    + "`Cost_Center` = ?,"
                    + "`Profit_Center` = ?,"
                    + "`YearMonth` = ?,"
                    + "`Account` = ?,"
                    + "`Plant` = ?,"
                    + "`Material` = ?,"
                    + "`Quantity` = ?,"
                    + "`Amount_in_local_currency` = ?,"
                    + "`Local_Currency` = ?,"
                    + "`Purchasing_Document` = ?,"
                    + "`Reference` = ?,"
                    + "`Document_currency` = ?,"
                    + "`Offsetting_acct_no` = ?,"
                    + "`Base_Unit_of_Measure` = ?,"
                    + "`Alternative_Account_No` = ?,"
                    + "`Transaction_Code` = ?,"
                    + "`Text` = ?,"
                    + "`Assignment` = ?,"
                    + "`Clasificacion` = ?"
                    + " WHERE `Id` = ?");
            for (ModeloFbl3m modelo : listModeloFbl3ms)
            {
                SQL.setString(1, modelo.getDocument_Number());
                SQL.setString(2, modelo.getDocument_type());
                SQL.setString(3, modelo.getDocument_Date());
                SQL.setString(4, modelo.getPosting_Date());
                SQL.setString(5, modelo.getCost_Center());
                SQL.setString(6, modelo.getProfit_Center());
                SQL.setString(7, modelo.getYearMonth());
                SQL.setString(8, modelo.getAccount());
                SQL.setString(9, modelo.getPlant());
                SQL.setString(10, modelo.getMaterial());
                SQL.setString(11, modelo.getQuantity());
                SQL.setString(12, modelo.getAmount_in_local_currency());
                SQL.setString(13, modelo.getLocal_Currency());
                SQL.setString(14, modelo.getPurchasing_Document());
                SQL.setString(15, modelo.getReference());
                SQL.setString(16, modelo.getDocument_currency());
                SQL.setString(17, modelo.getOffsetting_acct_no());
                SQL.setString(18, modelo.getBase_Unit_of_Measure());
                SQL.setString(19, modelo.getAlternative_Account_No());
                SQL.setString(20, modelo.getTransaction_Code());
                SQL.setString(21, modelo.getText());
                SQL.setString(22, modelo.getAssignment());
                SQL.setString(23, modelo.getClasificacion());
                SQL.setInt(24, modelo.getId());
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

    public boolean Delete(ModeloFbl3m modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `fbl3m` WHERE `Id` = ?;");
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
            SQL = con.prepareStatement("DELETE FROM `fbl3m`;");
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

    public LinkedList<ModeloFbl3m> Select() {
        LinkedList<ModeloFbl3m> modeloFbl3ms = new LinkedList<ModeloFbl3m>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Document_Number`,"
                    + "`Document_type`,"
                    + "`Document_Date`,"
                    + "`Posting_Date`,"
                    + "`Cost_Center`,"
                    + "`Profit_Center`,"
                    + "`YearMonth`,"
                    + "`Account`,"
                    + "`Plant`,"
                    + "`Material`,"
                    + "`Quantity`,"
                    + "`Amount_in_local_currency`,"
                    + "`Local_Currency`,"
                    + "`Purchasing_Document`,"
                    + "`Reference`,"
                    + "`Document_currency`,"
                    + "`Offsetting_acct_no`,"
                    + "`Base_Unit_of_Measure`,"
                    + "`Alternative_Account_No`,"
                    + "`Transaction_Code`,"
                    + "`Text`,"
                    + "`Assignment`,"
                    + "`Clasificacion`"
                    + " FROM `fbl3m`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                modeloFbl3m.setId(res.getInt("id"));
                modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                modeloFbl3m.setDocument_type(res.getString("Document_type"));
                modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                modeloFbl3m.setAccount(res.getString("Account"));
                modeloFbl3m.setPlant(res.getString("Plant"));
                modeloFbl3m.setMaterial(res.getString("Material"));
                modeloFbl3m.setQuantity(res.getString("Quantity"));
                modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloFbl3m.setReference(res.getString("Reference"));
                modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                modeloFbl3m.setText(res.getString("Text"));
                modeloFbl3m.setAssignment(res.getString("Assignment"));
                modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                modeloFbl3ms.add(modeloFbl3m);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloFbl3ms;
    }

    public ModeloFbl3m Select(Integer Id) {
        ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Document_Number`,"
                    + "`Document_type`,"
                    + "`Document_Date`,"
                    + "`Posting_Date`,"
                    + "`Cost_Center`,"
                    + "`Profit_Center`,"
                    + "`YearMonth`,"
                    + "`Account`,"
                    + "`Plant`,"
                    + "`Material`,"
                    + "`Quantity`,"
                    + "`Amount_in_local_currency`,"
                    + "`Local_Currency`,"
                    + "`Purchasing_Document`,"
                    + "`Reference`,"
                    + "`Document_currency`,"
                    + "`Offsetting_acct_no`,"
                    + "`Base_Unit_of_Measure`,"
                    + "`Alternative_Account_No`,"
                    + "`Transaction_Code`,"
                    + "`Text`,"
                    + "`Assignment`,"
                    + "`Clasificacion`"
                    + " FROM `fbl3m`"
                    + " WHERE Id = ?");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloFbl3m.setId(res.getInt("id"));
                modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                modeloFbl3m.setDocument_type(res.getString("Document_type"));
                modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                modeloFbl3m.setAccount(res.getString("Account"));
                modeloFbl3m.setPlant(res.getString("Plant"));
                modeloFbl3m.setMaterial(res.getString("Material"));
                modeloFbl3m.setQuantity(res.getString("Quantity"));
                modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloFbl3m.setReference(res.getString("Reference"));
                modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                modeloFbl3m.setText(res.getString("Text"));
                modeloFbl3m.setAssignment(res.getString("Assignment"));
                modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloFbl3m;
    }

    public LinkedList<ModeloFbl3m> ListSelectSQL(String Sql) {
        LinkedList<ModeloFbl3m> modeloFbl3ms = new LinkedList<ModeloFbl3m>();
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
                ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                modeloFbl3m.setId(res.getInt("id"));
                modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                modeloFbl3m.setDocument_type(res.getString("Document_type"));
                modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                modeloFbl3m.setAccount(res.getString("Account"));
                modeloFbl3m.setPlant(res.getString("Plant"));
                modeloFbl3m.setMaterial(res.getString("Material"));
                modeloFbl3m.setQuantity(res.getString("Quantity"));
                modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloFbl3m.setReference(res.getString("Reference"));
                modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                modeloFbl3m.setText(res.getString("Text"));
                modeloFbl3m.setAssignment(res.getString("Assignment"));
                modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                modeloFbl3ms.add(modeloFbl3m);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloFbl3ms;
    }

}