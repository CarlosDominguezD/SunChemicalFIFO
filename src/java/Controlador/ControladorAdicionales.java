/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAdicionales;
import Modelos.ModeloAuditoria;
//import Modelos.ModeloConversiones;
import Modelos.ModeloUsuario;
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
public class ControladorAdicionales {

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    public ModeloAdicionales Select(String Sql, Connection con) {
        ModeloAdicionales modeloAdicionales = new ModeloAdicionales();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloAdicionales.setId(res.getInt("id"));
                modeloAdicionales.setAdicional(res.getString("adicional"));
                modeloAdicionales.setValores(res.getString("valores"));
                
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorconversiones" + e);
        }
        return modeloAdicionales;
    }

    public String Insert(HttpServletRequest request) {
        ModeloAdicionales modeloAdicionales = new ModeloAdicionales();
        if ("".equals(request.getParameter("id"))) {
            modeloAdicionales = getModelo(request.getParameter("adicional"), request.getParameter("valores"));
            if (modeloAdicionales.getId() == null) {                
                modeloAdicionales.setAdicional(request.getParameter("adicional"));
                modeloAdicionales.setValores(request.getParameter("valores"));
                
                try {
                    ConexionBDMySql conexion = new ConexionBDMySql();
                    Connection con;
                    con = conexion.abrirConexion();
                    try {
                        SQL = con.prepareStatement("INSERT INTO `adicionales`("
                                + "`adicional`,"
                                + "`valores`,) "
                                + "VALUE (?,?)");
                        SQL.setString(1, modeloAdicionales.getAdicional());
                        SQL.setString(2, modeloAdicionales.getValores());
                        
                        if (SQL.executeUpdate() > 0) {
                            resultado = "1";
                            SQL.close();
                            con.close();
                            //Auditoria
                            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a insertado una nueva Conversion, " + modeloAdicionales.getValores()+ " en el sistema.");
                            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                            controladorAuditoria.Insert(modeloAuditoria);

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
                resultado = "-6";
            }

        } else {
            modeloAdicionales.setId(Integer.parseInt(request.getParameter("id")));
            modeloAdicionales.setAdicional(request.getParameter("adicional"));
            modeloAdicionales.setValores(request.getParameter("valores"));
           
            try {
                ConexionBDMySql conexion = new ConexionBDMySql();
                Connection con;
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `adicionales`  SET "
                            + "`adicional` = ?, "
                            + "`valores` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString(1, modeloAdicionales.getAdicional());
                    SQL.setString(2, modeloAdicionales.getValores());                    
                    SQL.setInt(3, modeloAdicionales.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                        modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                        modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a modificado una conversion, " + modeloAdicionales.getAdicional()+ " en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                        controladorAuditoria.Insert(modeloAuditoria);

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

    private ModeloAdicionales getModelo(String adicional, String valores) {
        ModeloAdicionales modeloAdicionales = new ModeloAdicionales();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT  "
                    + "`id`, "
                    + "`adicional`, "
                    + "`valores` "                    
                    + "FROM `adicionales`"
                    + "WHERE adicional = ? AND valores = ?;");
            SQL.setString(1, adicional);
            SQL.setString(2, valores);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloAdicionales.setId(res.getInt("id"));
                modeloAdicionales.setAdicional(res.getString("adicional"));
                modeloAdicionales.setValores(res.getString("valores"));                
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Controlador.ControladorConversiones.getModelo() " + e.getMessage());
        }
        return modeloAdicionales;
    }

    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloAdicionales> listmodelo;
            listmodelo = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Adicional</th>";
            out += "<th>Valores</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloAdicionales modelo : listmodelo) {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getAdicional()+ "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getValores()+ "</td>";                
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "data-adicional=\"" + modelo.getAdicional()+ "\"";
                out += "data-valores=\"" + modelo.getValores()+ "\"";                
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
//                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
//                out += "data-id=\"" + modelo.getId() + "\"";
//                out += "data-codigo=\"" + modelo.getCedula() + "\"";
//                out += "data-nombre=\"" + modelo.getNombre() + "\"";
//                out += "data-usuario=\"" + modelo.getUsuario() + "\"";
//                out += "data-clave=\"" + modelo.getClave() + "\"";
//                out += "data-idrol=\"" + modelo.getIdRol() + "\"";
//                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> Eliminar</button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
//            PrintWriter pw = response.getWriter();
//            pw.write(out);
//            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
        } catch (Exception e) {
            System.out.println("Controlador.ControladorConversiones.Read() " + e.getMessage());
        }
//        String frm = request.getParameter("frm");
//        System.out.println(frm);
//        processRequest(request, response);
        return out;
    }

    public LinkedList<ModeloAdicionales> Read() {
        LinkedList<ModeloAdicionales> modeloAdicionaleses = new LinkedList<ModeloAdicionales>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT  "
                    + "`id`, "
                    + "`adicional`, "
                    + "`valores` "                    
                    + "FROM `adicionales`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloAdicionales modeloAdicionales = new ModeloAdicionales();
                modeloAdicionales.setId(res.getInt("id"));
                modeloAdicionales.setAdicional(res.getString("adicional"));
                modeloAdicionales.setValores(res.getString("valores"));               
                modeloAdicionaleses.add(modeloAdicionales);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Controlador.ControladorConversiones.getModelo() " + e.getMessage());
        }
        return modeloAdicionaleses;
    }
}
