/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloConversiones;
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
public class ControladorConversiones {

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    public ModeloConversiones Select(String Sql, Connection con) {
        ModeloConversiones modeloConversiones = new ModeloConversiones();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                modeloConversiones.setId(res.getInt("id"));
                modeloConversiones.setAno(res.getString("ano"));
                modeloConversiones.setMes(res.getString("mes"));
                modeloConversiones.setConversion_labor(res.getString("conversion_labor"));
                modeloConversiones.setConversion_machine(res.getString("conversion_machine"));
                modeloConversiones.setConversion_ovhds(res.getString("conversion_ovhds"));
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL GetModelo en Controladorconversiones" + e);
        }
        return modeloConversiones;
    }

    public String Insert(HttpServletRequest request) {
        ModeloConversiones modeloConversiones = new ModeloConversiones();
        if ("".equals(request.getParameter("id"))) {
            modeloConversiones = getModelo(request.getParameter("ano"), request.getParameter("mes"));
            if (modeloConversiones.getId() == null) {
                modeloConversiones.setMes(request.getParameter("mes"));
                modeloConversiones.setAno(request.getParameter("ano"));
                modeloConversiones.setConversion_labor(request.getParameter("conversionlabor"));
                modeloConversiones.setConversion_machine(request.getParameter("conversionmachine"));
                modeloConversiones.setConversion_ovhds(request.getParameter("conversionovhds"));

                try {
                    ConexionBDMySql conexion = new ConexionBDMySql();
                    Connection con;
                    con = conexion.abrirConexion();
                    try {
                        SQL = con.prepareStatement("INSERT INTO `conversiones`("
                                + "`ano`,"
                                + "`mes`,"
                                + "`conversion_labor`,"
                                + "`conversion_machine`,"
                                + "`conversion_ovhds`) "
                                + "VALUE (?,?,?,?,?)");
                        SQL.setString(1, modeloConversiones.getAno());
                        SQL.setString(2, modeloConversiones.getMes());
                        SQL.setString(3, modeloConversiones.getConversion_labor());
                        SQL.setString(4, modeloConversiones.getConversion_machine());
                        SQL.setString(5, modeloConversiones.getConversion_ovhds());
                        if (SQL.executeUpdate() > 0) {
                            resultado = "1";
                            SQL.close();
                            con.close();
                            //Auditoria
                            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a insertado una nueva Conversion, " + modeloConversiones.getDatoCopleto() + " en el sistema.");
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
            modeloConversiones.setId(Integer.parseInt(request.getParameter("id")));
            modeloConversiones.setMes(request.getParameter("mes"));
            modeloConversiones.setAno(request.getParameter("ano"));
            modeloConversiones.setConversion_labor(request.getParameter("conversionlabor"));
            modeloConversiones.setConversion_machine(request.getParameter("conversionmachine"));
            modeloConversiones.setConversion_ovhds(request.getParameter("conversionovhds"));
            try {
                ConexionBDMySql conexion = new ConexionBDMySql();
                Connection con;
                con = conexion.abrirConexion();
                try {
                    SQL = con.prepareStatement("UPDATE `conversiones`  SET "
                            + "`ano` = ?, "
                            + "`mes` = ?, "
                            + "`conversion_labor` = ?, "
                            + "`conversion_machine` = ?, "
                            + "`conversion_ovhds` = ? "
                            + "WHERE `id` = ?;");
                    SQL.setString(1, modeloConversiones.getAno());
                    SQL.setString(2, modeloConversiones.getMes());
                    SQL.setString(3, modeloConversiones.getConversion_labor());
                    SQL.setString(4, modeloConversiones.getConversion_machine());
                    SQL.setString(5, modeloConversiones.getConversion_ovhds());
                    SQL.setInt(6, modeloConversiones.getId());
                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                        modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                        modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a modificado una conversion, " + modeloConversiones.getDatoCopleto() + " en el sistema.");
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

    private ModeloConversiones getModelo(String ano, String mes) {
        ModeloConversiones modeloConversiones = new ModeloConversiones();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT  "
                    + "`id`, "
                    + "`ano`, "
                    + "`mes`, "
                    + "`conversion_labor`, "
                    + "`conversion_machine`, "
                    + "`conversion_ovhds` "
                    + "FROM `conversiones`"
                    + "WHERE ano = ? AND mes = ?;");
            SQL.setString(1, ano);
            SQL.setString(2, mes);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloConversiones.setId(res.getInt("id"));
                modeloConversiones.setAno(res.getString("ano"));
                modeloConversiones.setMes(res.getString("mes"));
                modeloConversiones.setConversion_labor(res.getString("conversion_labor"));
                modeloConversiones.setConversion_machine(res.getString("conversion_machine"));
                modeloConversiones.setConversion_ovhds(res.getString("conversion_ovhds"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Controlador.ControladorConversiones.getModelo() " + e.getMessage());
        }
        return modeloConversiones;
    }

    public String Read(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try {
            LinkedList<ModeloConversiones> listmodelo;
            listmodelo = Read();
            response.setContentType("text/html;charset=UTF-8");

            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Mes</th>";
            out += "<th>Año</th>";
            out += "<th>CONVERSION_LABOR</th>";
            out += "<th>CONVERSION_MACHINE</th>";
            out += "<th>CONVERSION_OVHDS</th>";
            out += "<th>Opcion</th>";
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloConversiones modelo : listmodelo) {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getMes() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getAno() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getConversion_labor() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getConversion_machine() + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getConversion_ovhds() + "</td>";
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId() + "\"";
                out += "data-ano=\"" + modelo.getAno() + "\"";
                out += "data-mes=\"" + modelo.getMes() + "\"";
                out += "data-labor=\"" + modelo.getConversion_labor() + "\"";
                out += "data-machine=\"" + modelo.getConversion_machine() + "\"";
                out += "data-ovhds=\"" + modelo.getConversion_ovhds() + "\"";
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

    private LinkedList<ModeloConversiones> Read() {
        LinkedList<ModeloConversiones> modeloConversioneses = new LinkedList<ModeloConversiones>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT  "
                    + "`id`, "
                    + "`ano`, "
                    + "`mes`, "
                    + "`conversion_labor`, "
                    + "`conversion_machine`, "
                    + "`conversion_ovhds` "
                    + "FROM `conversiones`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloConversiones modeloConversiones = new ModeloConversiones();
                modeloConversiones.setId(res.getInt("id"));
                modeloConversiones.setAno(res.getString("ano"));
                modeloConversiones.setMes(res.getString("mes"));
                modeloConversiones.setConversion_labor(res.getString("conversion_labor"));
                modeloConversiones.setConversion_machine(res.getString("conversion_machine"));
                modeloConversiones.setConversion_ovhds(res.getString("conversion_ovhds"));
                modeloConversioneses.add(modeloConversiones);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Controlador.ControladorConversiones.getModelo() " + e.getMessage());
        }
        return modeloConversioneses;
    }
}
