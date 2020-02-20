/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloPermisosRol;
import Modelos.ModeloRoles;
import Modelos.ModeloUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorUsuarios
{

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    public String Insert (HttpServletRequest request)
    {
        if ("".equals (request.getParameter ("id")))
        {
            ModeloUsuario modelo = new ModeloUsuario (
                    0,
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre"),
                    request.getParameter ("usuario"),
                    request.getParameter ("clave"),
                    Integer.parseInt (request.getParameter ("idrol"))
            );
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `usuarios`("
                            + "`Cedula`,"
                            + "`Nombre`,"
                            + "`User`,"
                            + "`Clave`,"
                            + "`IdRol`) "
                            + "VALUE (?,?,?,?,?);");
                    SQL.setString (1, modelo.getCedula ());
                    SQL.setString (2, modelo.getNombre ());
                    SQL.setString (3, modelo.getUsuario ());
                    SQL.setString (4, modelo.getClave ());
                    SQL.setInt (5, modelo.getIdRol ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a insertado un usuario, " + modelo.getUsuario () + " en el sistema.");
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
            ModeloUsuario modelo = new ModeloUsuario (
                    Integer.parseInt (request.getParameter ("id")),
                    request.getParameter ("codigo"),
                    request.getParameter ("nombre"),
                    request.getParameter ("usuario"),
                    request.getParameter ("clave"),
                    Integer.parseInt (request.getParameter ("idrol"))
            );
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `usuarios`  SET "
                            + "`Cedula` = ?,"
                            + "`Nombre` = ?,"
                            + "`User` = ?,"
                            + "`Clave` = ?,"
                            + "`IdRol` = ? "
                            + "WHERE "
                            + "`Id` = ?;");
                    SQL.setString (1, modelo.getCedula ());
                    SQL.setString (2, modelo.getNombre ());
                    SQL.setString (3, modelo.getUsuario ());
                    SQL.setString (4, modelo.getClave ());
                    SQL.setInt (5, modelo.getIdRol ());
                    SQL.setInt (6, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a modificado un usuario, Id " + modelo.getId () + " en el sistema.");
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
            ModeloUsuario modelo = new ModeloUsuario ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `usuarios` WHERE `id` = ?;");
                    SQL.setInt (1, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "2";
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a eliminado un usuario, Id " + modelo.getId () + " en el sistema.");
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

    public LinkedList<ModeloUsuario> Read ()
    {
        LinkedList<ModeloUsuario> modeloUsuarios = new LinkedList<ModeloUsuario> ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`Cedula`,"
                    + "`Nombre`,"
                    + "`User`,"
                    + "`Clave`,"
                    + "`IdRol` "
                    + "FROM `usuarios`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloUsuario modelo = new ModeloUsuario ();
                modelo.setId (res.getInt ("id"));
                modelo.setCedula (res.getString ("Cedula"));
                modelo.setNombre (res.getString ("Nombre"));
                modelo.setUsuario (res.getString ("User"));
                modelo.setClave (res.getString ("Clave"));
                modelo.setIdRol (res.getInt ("IdRol"));
                modeloUsuarios.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return modeloUsuarios;
    }

    public String ReadRoles (HttpServletRequest request, HttpServletResponse response)
    {
        String out = null;
        try
        {
            LinkedList<ModeloUsuario> listmodelo;
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
            for (ModeloUsuario modelo : listmodelo)
            {
                ControladorRoles controladorRoles = new ControladorRoles ();
                ModeloRoles modeloRoles = controladorRoles.Select (modelo.getIdRol ());
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getId () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getCedula () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getNombre () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getUsuario () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloRoles.getNombre () + "</td>";
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-codigo=\"" + modelo.getCedula () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "data-usuario=\"" + modelo.getUsuario () + "\"";
                out += "data-clave=\"" + modelo.getClave () + "\"";
                out += "data-idrol=\"" + modelo.getIdRol () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-codigo=\"" + modelo.getCedula () + "\"";
                out += "data-nombre=\"" + modelo.getNombre () + "\"";
                out += "data-usuario=\"" + modelo.getUsuario () + "\"";
                out += "data-clave=\"" + modelo.getClave () + "\"";
                out += "data-idrol=\"" + modelo.getIdRol () + "\"";
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

    public ModeloUsuario Select (HttpServletRequest request)
    {
        ModeloUsuario modelo = new ModeloUsuario ();
        ModeloUsuario modeloUsuario = new ModeloUsuario ();
        modelo.setUsuario (request.getParameter ("user"));
        modelo.setClave (request.getParameter ("clave"));
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`Cedula`,"
                    + "`Nombre`,"
                    + "`User`,"
                    + "`Clave`,"
                    + "`IdRol` "
                    + "FROM `usuarios`"
                    + " WHERE User = ?");
            SQL.setString (1, modelo.getUsuario ());
            ResultSet res = SQL.executeQuery ();
            if (res.next ())
            {
                if (modelo.getClave () == null ? res.getString ("Clave") == null : modelo.getClave ().equals (res.getString ("Clave")))
                {
                    modeloUsuario.setId (res.getInt ("id"));
                    modeloUsuario.setCedula (res.getString ("Cedula"));
                    modeloUsuario.setNombre (res.getString ("Nombre"));
                    modeloUsuario.setUsuario (res.getString ("User"));
                    modeloUsuario.setClave (res.getString ("Clave"));
                    modeloUsuario.setIdRol (res.getInt ("IdRol"));
                    ControladorPermisosRol controladorPermisosRol = new ControladorPermisosRol ();
                    modeloUsuario.setListModeloItemMenu (controladorPermisosRol.Select (modeloUsuario.getIdRol ()));
                    //Auditoria
                    ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                    modeloAuditoria.setModeloUsuario (modeloUsuario);
                    modeloAuditoria.setMensaje ("El Usuario " + modeloUsuario.getUsuario () + " a iniciado seccion en el sistema.");
                    ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
                    controladorAuditoria.Insert (modeloAuditoria);
                }
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en el controlados de ingreso " + e);
        }
        return modeloUsuario;
    }

    ModeloUsuario SelectS (String user)
    {
        ModeloUsuario modelo = new ModeloUsuario ();
        ModeloUsuario modeloUsuario = new ModeloUsuario ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`Cedula`,"
                    + "`Nombre`,"
                    + "`User`,"
                    + "`Clave`,"
                    + "`IdRol` "
                    + "FROM `usuarios`"
                    + " WHERE User = ?");
            SQL.setString (1, user);
            ResultSet res = SQL.executeQuery ();
            if (res.next ())
            {
                modeloUsuario.setId (res.getInt ("id"));
                modeloUsuario.setCedula (res.getString ("Cedula"));
                modeloUsuario.setNombre (res.getString ("Nombre"));
                modeloUsuario.setUsuario (res.getString ("User"));
                modeloUsuario.setClave (res.getString ("Clave"));
                modeloUsuario.setIdRol (res.getInt ("IdRol"));
                ControladorPermisosRol controladorPermisosRol = new ControladorPermisosRol ();
                modeloUsuario.setListModeloItemMenu (controladorPermisosRol.Select (modeloUsuario.getIdRol ()));
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en el controlados de ingreso " + e);
        }
        return modeloUsuario;
    }
}
