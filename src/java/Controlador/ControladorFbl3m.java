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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorFbl3m {

    String resultado = "";

    public boolean Insert(ModeloFbl3m modelo) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
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
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean Insert(String Sql) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement(Sql);
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean Insert(LinkedList<ModeloFbl3m> listModeloFbl3ms) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
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
                for (ModeloFbl3m modelo : listModeloFbl3ms) {
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
                    if (SQL.executeUpdate() > 0) {
                        resul = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
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
        try {
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
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
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
        try {
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
            for (ModeloFbl3m modelo : listModeloFbl3ms) {
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
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
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
        try {
            SQL = con.prepareStatement("DELETE FROM `fbl3m` WHERE `Id` = ?;");
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
            SQL = con.prepareStatement("DELETE FROM `fbl3m`;");
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

    public LinkedList<ModeloFbl3m> Select() {
        LinkedList<ModeloFbl3m> modeloFbl3ms = new LinkedList<ModeloFbl3m>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
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
            while (res.next()) {
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
        } catch (SQLException e) {
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
        try {
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
            if (res.next()) {
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
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloFbl3m;
    }

    public LinkedList<ModeloFbl3m> ListSelectSQL(String Sql) throws SQLException {
        LinkedList<ModeloFbl3m> modeloFbl3ms = new LinkedList<ModeloFbl3m>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
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
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
            SQL.close();
            con.close();
        }
        return modeloFbl3ms;
    }

    public String ReadFormulario(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloFbl3m> listmodelo;
            listmodelo = Read(request.getParameter("Whent"));
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Opcion</th>";
            out += "<th>Document_Number</th>";
            out += "<th>Document_type</th>";
            out += "<th>Document_Date</th>";
            out += "<th>Posting_Date</th>";
            out += "<th>Cost_Center</th>";
            out += "<th>Profit_Center</th>";
            out += "<th>YearMonth</th>";
            out += "<th>Account</th>";
            out += "<th>Plant</th>";
            out += "<th>Material</th>";
            out += "<th>Quantity</th>";
            out += "<th>Amount_in_local_currency</th>";
            out += "<th>Local_Currency</th>";
            out += "<th>Purchasing_Document</th>";
            out += "<th>Reference</th>";
            out += "<th>Document_currency</th>";
            out += "<th>Offsetting_acct_no</th>";
            out += "<th>Base_Unit_of_Measure</th>";
            out += "<th>Alternative_Account_No</th>";
            out += "<th>Transaction_Code</th>";
            out += "<th>Text</th>";
            out += "<th>Assignment</th>";
            out += "<th>Clasificacion</th>";
            //out += "<th>fecha</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloFbl3m modelo : listmodelo) {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                //out += "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\".bd-example-modal-lg\">Large modal</button>";
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "data-document_number=\"" + modelo.getDocument_Number() + "\"";
                out += "data-document_type=\"" + modelo.getDocument_type() + "\"";
                out += "data-document_date=\"" + modelo.getDocument_type() + "\"";
                out += "data-posting_date=\"" + modelo.getPosting_Date() + "\"";
                out += "data-cost_center=\"" + modelo.getCost_Center() + "\"";
                out += "data-profit_center=\"" + modelo.getProfit_Center() + "\"";
                out += "data-yearmonth=\"" + modelo.getYearMonth() + "\"";
                out += "data-account=\"" + modelo.getAccount() + "\"";
                out += "data-plant=\"" + modelo.getPlant() + "\"";
                out += "data-material=\"" + modelo.getMaterial() + "\"";
                out += "data-quantity=\"" + modelo.getQuantity() + "\"";
                out += "data-amount_in_local_currency=\"" + modelo.getAmount_in_local_currency() + "\"";
                out += "data-local_currency=\"" + modelo.getLocal_Currency() + "\"";
                out += "data-purchasing_document=\"" + modelo.getPurchasing_Document() + "\"";
                out += "data-reference=\"" + modelo.getReference() + "\"";
                out += "data-document_currency=\"" + modelo.getDocument_currency() + "\"";
                out += "data-offsetting_acct_no=\"" + modelo.getOffsetting_acct_no() + "\"";
                out += "data-base_unit_of_measure=\"" + modelo.getBase_Unit_of_Measure() + "\"";
                out += "data-alternative_account_no=\"" + modelo.getAlternative_Account_No() + "\"";
                out += "data-transaction_code=\"" + modelo.getTransaction_Code() + "\"";
                out += "data-text=\"" + modelo.getText() + "\"";
                out += "data-assignment=\"" + modelo.getAssignment() + "\"";
                out += "data-clasificacion=\"" + modelo.getClasificacion() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> Eliminar</button>";
                out += "</td>";                
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getDocument_Number() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getDocument_type() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getDocument_type() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getPosting_Date() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getCost_Center() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getProfit_Center() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getYearMonth() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getAccount() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getPlant() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getMaterial() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getQuantity() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getAmount_in_local_currency() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getLocal_Currency() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getPurchasing_Document() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getReference() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getDocument_currency() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getOffsetting_acct_no() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getBase_Unit_of_Measure() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getAlternative_Account_No() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getTransaction_Code() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getText() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getAssignment() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getClasificacion() + "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
//            PrintWriter pw = response.getWriter();
//            pw.write(out);
//            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
        } catch (Exception e) {
            System.out.println("Error en el kproceso de la tabla " + e.getMessage());
        }
//        String frm = request.getParameter("frm");
//        System.out.println(frm);
//        processRequest(request, response);
        return out;
    }

    private LinkedList<ModeloFbl3m> Read(String Whent) {
        LinkedList<ModeloFbl3m> modeloFbl3ms = new LinkedList<ModeloFbl3m>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT \n"
                    + "  `Id`,\n"
                    + "  `Document_Number`,\n"
                    + "  `Document_type`,\n"
                    + "  `Document_Date`,\n"
                    + "  `Posting_Date`,\n"
                    + "  `Cost_Center`,\n"
                    + "  `Profit_Center`,\n"
                    + "  `YearMonth`,\n"
                    + "  `Account`,\n"
                    + "  `Plant`,\n"
                    + "  `Material`,\n"
                    + "  `Quantity`,\n"
                    + "  `Amount_in_local_currency`,\n"
                    + "  `Local_Currency`,\n"
                    + "  `Purchasing_Document`,\n"
                    + "  `Reference`,\n"
                    + "  `Document_currency`,\n"
                    + "  `Offsetting_acct_no`,\n"
                    + "  `Base_Unit_of_Measure`,\n"
                    + "  `Alternative_Account_No`,\n"
                    + "  `Transaction_Code`,\n"
                    + "  `Text`,\n"
                    + "  `Assignment`,\n"
                    + "  `Clasificacion`,\n"
                    + "  `fecha`\n"
                    + "FROM \n"
                    + "  `fbl3m`"
                    + " WHERE "+Whent+";");
            
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloFbl3m modelo = new ModeloFbl3m();
                modelo.setId(res.getInt("id"));
                modelo.setDocument_Number(res.getString("Document_Number"));
                modelo.setDocument_type(res.getString("Document_type"));
                modelo.setDocument_Date(res.getString("Document_Date"));
                modelo.setPosting_Date(res.getString("Posting_Date"));
                modelo.setCost_Center(res.getString("Cost_Center"));
                modelo.setProfit_Center(res.getString("Profit_Center"));
                modelo.setYearMonth(res.getString("YearMonth"));
                modelo.setAccount(res.getString("Account"));
                modelo.setPlant(res.getString("Plant"));
                modelo.setMaterial(res.getString("Material"));
                modelo.setQuantity(res.getString("Quantity"));
                modelo.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                modelo.setLocal_Currency(res.getString("Local_Currency"));
                modelo.setPurchasing_Document(res.getString("Purchasing_Document"));
                modelo.setReference(res.getString("Reference"));
                modelo.setDocument_currency(res.getString("Document_currency"));
                modelo.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                modelo.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                modelo.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                modelo.setTransaction_Code(res.getString("Transaction_Code"));
                modelo.setText(res.getString("Text"));
                modelo.setAssignment(res.getString("Assignment"));
                modelo.setClasificacion(res.getString("Clasificacion"));                
                modeloFbl3ms.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloFbl3ms;
    }

    public String Insert(HttpServletRequest request, HttpServletResponse response) {  
        boolean r = false;
        String retorno ="";        
        ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
        modeloFbl3m.setAccount(request.getParameter("Account"));
        modeloFbl3m.setAlternative_Account_No(request.getParameter("Alternative_Account_No"));
        modeloFbl3m.setAmount_in_local_currency(request.getParameter("Amount_in_local_currency"));
        modeloFbl3m.setAssignment(request.getParameter("Assignment"));
        modeloFbl3m.setBase_Unit_of_Measure(request.getParameter("Base_Unit_of_Measure"));
        modeloFbl3m.setClasificacion(request.getParameter("Clasificacion"));
        modeloFbl3m.setCost_Center(request.getParameter("Cost_Center"));
        modeloFbl3m.setDocument_Date(request.getParameter("Document_Date"));
        modeloFbl3m.setDocument_Number(request.getParameter("Document_Number"));
        modeloFbl3m.setDocument_currency(request.getParameter("Document_currency"));
        modeloFbl3m.setDocument_type(request.getParameter("Document_type"));
        modeloFbl3m.setId(Integer.parseInt(request.getParameter("Id")));
        modeloFbl3m.setLocal_Currency(request.getParameter("Local_Currency"));
        modeloFbl3m.setMaterial(request.getParameter("Material"));
        modeloFbl3m.setOffsetting_acct_no(request.getParameter("Offsetting_acct_no"));
        modeloFbl3m.setPlant(request.getParameter("Plant"));
        modeloFbl3m.setPosting_Date(request.getParameter("Posting_Date"));
        modeloFbl3m.setProfit_Center(request.getParameter("Profit_Center"));
        modeloFbl3m.setPurchasing_Document(request.getParameter("Purchasing_Document"));
        modeloFbl3m.setQuantity(request.getParameter("Quantity"));
        modeloFbl3m.setReference(request.getParameter("Reference"));
        modeloFbl3m.setText(request.getParameter("Text"));
        modeloFbl3m.setTransaction_Code(request.getParameter("Transaction_Code"));
        modeloFbl3m.setYearMonth(request.getParameter("YearMonth"));     
        if (modeloFbl3m.getId() != null) {
            r = Update(modeloFbl3m);
            if (r == true) {
                retorno = "1";
            }
        }        
        return retorno;        
    }

    public String Delete(HttpServletRequest request, HttpServletResponse response) {
        boolean r = false;
        String retorno ="";        
        ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
        modeloFbl3m.setId(Integer.parseInt(request.getParameter("Id")));
        if (modeloFbl3m.getId() != null) {
            r = Delete(modeloFbl3m);
            if (r == true) {
                retorno = "2";
            }
        }        
        return retorno;
    }

}
