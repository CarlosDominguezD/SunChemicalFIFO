/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloRoles;
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
 * @author Carlos A Dominguez D
 */
public class ControladorRoles
{

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    public String Insert (HttpServletRequest request)
    {
        if ("".equals (request.getParameter ("id")))
        {
            ModeloRoles modelo = new ModeloRoles (
                    0,
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre")
            );
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `roles` ("
                            + "`nombre`,"
                            + "`codigo`) "
                            + "VALUE (?,?);");
                    SQL.setString (1, modelo.getNombre ());
                    SQL.setString (2, modelo.getCodigo ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario ()+ " a insertado un rol en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                        controladorAuditoria.Insert (modeloAuditoria);
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-2";
                    SQL.close ();
                    con.close ();
                }
            } catch (SQLException e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        else
        {
            ModeloRoles modelo = new ModeloRoles (
                    Integer.parseInt (request.getParameter ("id")),
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre")
            );
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `roles`  SET "
                            + "`nombre` = ?,"
                            + "`codigo` = ? "
                            + "WHERE "
                            + "`id` = ?;");
                    SQL.setString (1, modelo.getNombre ());
                    SQL.setString (2, modelo.getCodigo ());
                    SQL.setInt (3, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario ()+ " a actulizado un rol Id "+ modelo.getId ()+ " en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                        controladorAuditoria.Insert (modeloAuditoria);
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-2";
                    SQL.close ();
                    con.close ();
                }
            } catch (SQLException e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public String Delete (HttpServletRequest request)
    {
        if (!"".equals (request.getParameter ("id")))
        {
            String idtmp = request.getParameter ("id");
            ModeloRoles modelo = new ModeloRoles ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `roles` WHERE `id` = ?;");
                    SQL.setInt (1, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "2";
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario ()+ " a eliminado un rol Id "+ modelo.getId ()+ " en el sistema.");
                        ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                        controladorAuditoria.Insert (modeloAuditoria);
                    }
                } catch (SQLException e)
                {
                    System.out.println (e);
                    resultado = "-2";
                }
                SQL.close ();
                con.close ();
            } catch (SQLException e)
            {
                System.out.println (e);
                resultado = "-3";
            }
        }
        return resultado;
    }

    public LinkedList<ModeloRoles> Read ()
    {
        LinkedList<ModeloRoles> modeloRoleses = new LinkedList<ModeloRoles> ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`codigo`"
                    + "FROM `roles`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloRoles modelo = new ModeloRoles ();
                modelo.setId (res.getInt ("id"));
                modelo.setNombre (res.getString ("nombre"));
                modelo.setCodigo (res.getString ("codigo"));
                modeloRoleses.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return modeloRoleses;
    }

    public String ReadRoles (HttpServletRequest request, HttpServletResponse response)
    {
        String out = null;
        try
        {
            LinkedList<ModeloRoles> listmodelo;
            listmodelo = Read ();
            response.setContentType ("text/html;charset=UTF-8");

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
            for (ModeloRoles modelo : listmodelo)
            {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getId () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getCodigo () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNombre () + "</td>";
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-codigo=\"" + modelo.getCodigo () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-codigo=\"" + modelo.getCodigo () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "type=\"button\"><i id=\"IdEliminar\" name=\"Eliminar\" class=\"fa fa-trash\"></i> Eliminar</button>";
                out += "</td>";
                out += "</tr>";
            }
            out += "</tbody>";
//            PrintWriter pw = response.getWriter();
//            pw.write(out);
//            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
        } catch (Exception e)
        {
            System.out.println ("Error en el kproceso de la tabla " + e.getMessage ());
        }
//        String frm = request.getParameter("frm");
//        System.out.println(frm);
//        processRequest(request, response);
        return out;
    }

    public ModeloRoles Select (int idRol)
    {
        ModeloRoles modelo = new ModeloRoles ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`id`,"
                    + "`nombre`,"
                    + "`codigo`"
                    + "FROM `roles`"
                    + "WHERE `id` = " + idRol + ";");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modelo.setId (res.getInt ("id"));
                modelo.setNombre (res.getString ("nombre"));
                modelo.setCodigo (res.getString ("codigo"));
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return modelo;
    }
}
