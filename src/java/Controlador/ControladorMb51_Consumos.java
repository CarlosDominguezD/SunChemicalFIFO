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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public String ReadFormulario(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloMb51_Consumos> listmodelo;
            listmodelo = Read(request.getParameter("Whent"));
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Opcion</th>";
            out += "<th>Plant</th>";
            out += "<th>Purchase_order</th>";
            out += "<th>Material</th>";
            out += "<th>Material_Description</th>";
            out += "<th>Batch</th>";
            out += "<th>Movement_type</th>";
            out += "<th>Movement_Type_Text</th>";
            out += "<th>Item</th>";
            out += "<th>Quantity</th>";
            out += "<th>Qty_in_unit_of_entry</th>";
            out += "<th>Unit_of_Entry</th>";
            out += "<th>Amt_in_loc_cur</th>";
            out += "<th>Currency</th>";
            out += "<th>Storage_Location</th>";
            out += "<th>Posting_Date</th>";
            out += "<th>Document_Date</th>";
            out += "<th>Material_Document</th>";
            out += "<th>User_Name</th>";
            out += "<th>Vendor</th>";
            out += "<th>Order_</th>";
            //out += "<th>fecha</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            int d = 0;
            for (ModeloMb51_Consumos modelo : listmodelo) {
                d++;
                System.out.println("Controlador.ControladorMb51_Consumos.ReadFormulario()" + d);

                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                //out += "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\".bd-example-modal-lg\">Large modal</button>";
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "data-plant=\"" + modelo.getPlant() + "\"";
                out += "data-purchase_order=\"" + modelo.getPurchase_order() + "\"";
                out += "data-material=\"" + modelo.getMaterial() + "\"";
                out += "data-material_description=\"" + modelo.getMaterial_Description() + "\"";
                out += "data-batch=\"" + modelo.getBatch() + "\"";
                out += "data-movement_type=\"" + modelo.getMovement_type() + "\"";
                out += "data-movement_type_text=\"" + modelo.getMovement_Type_Text() + "\"";
                out += "data-item=\"" + modelo.getItem() + "\"";
                out += "data-quantity=\"" + modelo.getQuantity() + "\"";
                out += "data-qty_in_unit_of_entry=\"" + modelo.getQty_in_unit_of_entry() + "\"";
                out += "data-unit_of_entry=\"" + modelo.getUnit_of_Entry() + "\"";
                out += "data-amt_in_loc_cur=\"" + modelo.getAmt_in_loc_cur() + "\"";
                out += "data-currency=\"" + modelo.getCurrency() + "\"";
                out += "data-storage_location=\"" + modelo.getStorage_Location() + "\"";
                out += "data-posting_date=\"" + modelo.getPosting_Date() + "\"";
                out += "data-document_date=\"" + modelo.getDocument_Date() + "\"";
                out += "data-material_document=\"" + modelo.getMaterial_Document() + "\"";
                out += "data-user_name=\"" + modelo.getUser_Name() + "\"";
                out += "data-vendor=\"" + modelo.getVendor() + "\"";
                out += "data-order_=\"" + modelo.getOrder_() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> Eliminar</button>";
                out += "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getPlant() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getPurchase_order() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getMaterial() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getMaterial_Description() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getBatch() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getMovement_type() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getMovement_Type_Text() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getItem() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getQuantity() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getQty_in_unit_of_entry() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getUnit_of_Entry() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getAmt_in_loc_cur() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getCurrency() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getStorage_Location() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getPosting_Date() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getDocument_Date() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getMaterial_Document() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getUser_Name() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getVendor() + "</td>";
                out += "<td WIDTH=\"0\" HEIGHT=\"0\">" + modelo.getOrder_() + "</td>";
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

    private LinkedList<ModeloMb51_Consumos> Read(String Whent) {
        LinkedList<ModeloMb51_Consumos> modeloMb51_Consumoses = new LinkedList<ModeloMb51_Consumos>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT \n"
                    + "  `Id`,\n"
                    + "  `Plant`,\n"
                    + "  `Purchase_order`,\n"
                    + "  `Material`,\n"
                    + "  `Material_Description`,\n"
                    + "  `Batch`,\n"
                    + "  `Movement_type`,\n"
                    + "  `Movement_Type_Text`,\n"
                    + "  `Item`,\n"
                    + "  `Quantity`,\n"
                    + "  `Qty_in_unit_of_entry`,\n"
                    + "  `Unit_of_Entry`,\n"
                    + "  `Amt_in_loc_cur`,\n"
                    + "  `Currency`,\n"
                    + "  `Storage_Location`,\n"
                    + "  `Posting_Date`,\n"
                    + "  `Document_Date`,\n"
                    + "  `Material_Document`,\n"
                    + "  `User_Name`,\n"
                    + "  `Vendor`,\n"
                    + "  `Order_`,\n"
                    + "  `fecha`\n"
                    + "FROM \n"
                    + "  `mb51_consumos`"
                    + " WHERE " + Whent + ";");

            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMb51_Consumos modelo = new ModeloMb51_Consumos();
                modelo.setId(res.getInt("id"));
                modelo.setPlant(res.getString("Plant"));
                modelo.setPurchase_order(res.getString("Purchase_order"));
                modelo.setMaterial(res.getString("Material"));
                modelo.setMaterial_Description(res.getString("Material_Description"));
                modelo.setBatch(res.getString("Batch"));
                modelo.setMovement_type(res.getString("Movement_type"));
                modelo.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modelo.setItem(res.getString("Item"));
                modelo.setQuantity(res.getString("Quantity"));
                modelo.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modelo.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modelo.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modelo.setCurrency(res.getString("Currency"));
                modelo.setStorage_Location(res.getString("Storage_Location"));
                modelo.setPosting_Date(res.getString("Posting_Date"));
                modelo.setDocument_Date(res.getString("Document_Date"));
                modelo.setMaterial_Document(res.getString("Material_Document"));
                modelo.setUser_Name(res.getString("User_Name"));
                modelo.setVendor(res.getString("Vendor"));
                modelo.setOrder_(res.getString("Order_"));
                modeloMb51_Consumoses.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMb51_Consumoses;
    }

    public String Insert(HttpServletRequest request, HttpServletResponse response) {
        boolean r = false;
        String retorno = "";
        ModeloMb51_Consumos modeloMb51_Consumos = new ModeloMb51_Consumos();
        modeloMb51_Consumos.setId(Integer.parseInt(request.getParameter("Id")));
        modeloMb51_Consumos.setPlant(request.getParameter("Plant"));
        modeloMb51_Consumos.setPurchase_order(request.getParameter("Purchase_order"));
        modeloMb51_Consumos.setMaterial(request.getParameter("Material"));
        modeloMb51_Consumos.setMaterial_Description(request.getParameter("Material_Description"));
        modeloMb51_Consumos.setBatch(request.getParameter("Batch"));
        modeloMb51_Consumos.setMovement_type(request.getParameter("Movement_type"));
        modeloMb51_Consumos.setMovement_Type_Text(request.getParameter("Movement_Type_Text"));
        modeloMb51_Consumos.setItem(request.getParameter("Item"));
        modeloMb51_Consumos.setQuantity(request.getParameter("Quantity"));
        modeloMb51_Consumos.setQty_in_unit_of_entry(request.getParameter("Qty_in_unit_of_entry"));
        modeloMb51_Consumos.setUnit_of_Entry(request.getParameter("Unit_of_Entry"));
        modeloMb51_Consumos.setAmt_in_loc_cur(request.getParameter("Amt_in_loc_cur"));
        modeloMb51_Consumos.setCurrency(request.getParameter("Currency"));
        modeloMb51_Consumos.setStorage_Location(request.getParameter("Storage_Location"));
        modeloMb51_Consumos.setPosting_Date(request.getParameter("Posting_Date"));
        modeloMb51_Consumos.setDocument_Date(request.getParameter("Document_Date"));
        modeloMb51_Consumos.setMaterial_Document(request.getParameter("Material_Document"));
        modeloMb51_Consumos.setUser_Name(request.getParameter("User_Name"));
        modeloMb51_Consumos.setVendor(request.getParameter("Vendor"));
        modeloMb51_Consumos.setOrder_(request.getParameter("Order_"));
        if (modeloMb51_Consumos.getId() != null) {
            r = Update(modeloMb51_Consumos);
            if (r == true) {
                retorno = "1";
            }
        }
        return retorno;
    }

    private boolean Update(ModeloMb51_Consumos modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE `mb51_consumos` SET \n"
                    + "  `Plant` = ?,\n"
                    + "  `Purchase_order` = ?,\n"
                    + "  `Material` = ?,\n"
                    + "  `Material_Description` = ?,\n"
                    + "  `Batch` = ?,\n"
                    + "  `Movement_type` = ?,\n"
                    + "  `Movement_Type_Text` = ?,\n"
                    + "  `Item` = ?,\n"
                    + "  `Quantity` = ?,\n"
                    + "  `Qty_in_unit_of_entry` = ?,\n"
                    + "  `Unit_of_Entry` = ?,\n"
                    + "  `Amt_in_loc_cur` = ?,\n"
                    + "  `Currency` = ?,\n"
                    + "  `Storage_Location` = ?,\n"
                    + "  `Posting_Date` = ?,\n"
                    + "  `Document_Date` = ?,\n"
                    + "  `Material_Document` = ?,\n"
                    + "  `User_Name` = ?,\n"
                    + "  `Vendor` = ?,\n"
                    + "  `Order_` = ?"
                    + "WHERE \n"
                    + "  `Id` = ?;");
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
            SQL.setString(20, modelo.getOrder_());
            SQL.setInt(21, modelo.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Controlador.ControladorMb51_Consumos.Update() ERROR ==> " + e);
        }
        return resul;
    }

    public String Delete(HttpServletRequest request, HttpServletResponse response) {
        boolean r = false;
        String retorno = "";
        ModeloMb51_Consumos modeloMb51_Consumos = new ModeloMb51_Consumos();
        modeloMb51_Consumos.setId(Integer.parseInt(request.getParameter("Id")));
        if (modeloMb51_Consumos.getId() != null) {
            r = Delete(modeloMb51_Consumos);
            if (r == true) {
                retorno = "2";
            }
        }
        return retorno;
    }

    private boolean Delete(ModeloMb51_Consumos modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("DELETE FROM `mb51_consumos` WHERE `Id` = ?;");
            SQL.setInt(1, modelo.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Controlador.ControladorMb51_Consumos.Delete() ERROR ==> " + e);
        }
        return resul;
    }

}
