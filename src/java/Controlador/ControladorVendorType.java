/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloUsuario;
import Modelos.ModeloVendorType;
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
public class ControladorVendorType {

    String resultado = "";
    Connection con;
    PreparedStatement SQL = null;
    ConexionBDMySql conexion = new ConexionBDMySql();

    public ModeloVendorType Select(String Sql) throws SQLException {
        ModeloVendorType modeloVendorType = null;
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloVendorType = new ModeloVendorType();
                modeloVendorType.setId(res.getInt("id"));
                modeloVendorType.setMaterial(res.getString("Material"));
                modeloVendorType.setVendor_Type(res.getString("Vendor_Type"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            SQL.close();
            con.close();
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloVendorType;
    }

    public String Insert(HttpServletRequest request) {
        if ("".equals(request.getParameter("id"))) {
            ModeloVendorType modelo = new ModeloVendorType(
                    0,
                    request.getParameter("Material"),
                    request.getParameter("Vendor_Type")
            );
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("INSERT INTO `vendortype`("
                            + "`Material`,"
                            + "`Vendor_Type`)"
                            + " VALUE ("
                            + "?,"
                            + "?);");
                    SQL.setString(1, modelo.getMaterial());
                    SQL.setString(2, modelo.getVendor_Type());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario ()+ " a insertado un VendorType, nombre "+ modelo.getVendor_Type () + " en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                        controladorAuditoria.Insert (modeloAuditoria);
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        } else {
            ModeloVendorType modelo = new ModeloVendorType(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("Material"),
                    request.getParameter("Vendor_Type")
            );
            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `vendortype`  SET "
                            + "`Material` = ?,"
                            + "`Vendor_Type` = ? "
                            + "WHERE "
                            + "`Id` = ?;");
                    SQL.setString(1, modelo.getMaterial());
                    SQL.setString(2, modelo.getVendor_Type());
                    SQL.setInt(3, modelo.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario ()+ " a modificado un VendorType, Id "+ modelo.getId ()+ " en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                        controladorAuditoria.Insert (modeloAuditoria);
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                    SQL.close();
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Delete(HttpServletRequest request) {
        if (!"".equals(request.getParameter("id"))) {
            String idtmp = request.getParameter("id");
            ModeloVendorType modelo = new ModeloVendorType();
            modelo.setId(Integer.parseInt(request.getParameter("id")));

            try {
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("DELETE FROM `vendortype` "
                            + "WHERE `Id` = ?;");
                    SQL.setInt(1, modelo.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "2";
                                                //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario ()+ " a eliminado un VendorType, Id "+ modelo.getId ()+ " en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                        controladorAuditoria.Insert (modeloAuditoria);

                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    resultado = "-2";
                }
                SQL.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String ReadVendorType(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloVendorType> listmodelo;
            listmodelo = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Internal Code</th>";
            out += "<th>Material</th>";
            out += "<th>Vendor_Type</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloVendorType modeloVendorType : listmodelo) {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloVendorType.getId() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloVendorType.getMaterial() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloVendorType.getVendor_Type() + "</td>";
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modeloVendorType.getId() + "\"";
                out += "data-material=\"" + modeloVendorType.getMaterial() + "\"";
                out += "data-vendortype=\"" + modeloVendorType.getVendor_Type() + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
                out += "data-id=\"" + modeloVendorType.getId() + "\"";
                out += "data-material=\"" + modeloVendorType.getMaterial() + "\"";
                out += "data-vendortype=\"" + modeloVendorType.getVendor_Type() + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> Eliminar</button>";
                out += "</td>";
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

    private LinkedList<ModeloVendorType> Read() {
        LinkedList<ModeloVendorType> listModeloVendorTypes = new LinkedList<ModeloVendorType>();
        con = conexion.abrirConexion();
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Material`,"
                    + "`Vendor_Type` "
                    + "FROM `vendortype`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloVendorType modelo = new ModeloVendorType();
                modelo.setId(res.getInt("Id"));
                modelo.setMaterial(res.getString("Material"));
                modelo.setVendor_Type(res.getString("Vendor_Type"));
                listModeloVendorTypes.add(modelo);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listModeloVendorTypes;
    }
}
