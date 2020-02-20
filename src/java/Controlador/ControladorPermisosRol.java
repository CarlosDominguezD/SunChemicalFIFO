/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloItemMenu;
import Modelos.ModeloPermisosRol;
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
public class ControladorPermisosRol
{

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    public String Insert (HttpServletRequest request)
    {
        if ("".equals (request.getParameter ("id")))
        {
            ModeloPermisosRol modelo = new ModeloPermisosRol (
                    0,
                    Integer.parseInt (request.getParameter ("idrol")),
                    Integer.parseInt (request.getParameter ("iditemmenu"))
            );
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO "
                            + "`permisosrol`("
                            + "`IdRol`,"
                            + "`IdTiemMenu`) "
                            + "VALUE (?,?);");
                    SQL.setInt (1, modelo.getIdRol ());
                    SQL.setInt (2, modelo.getIdItemMenu ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a insertado un permiso por rol, Id " + modelo.getIdRol () + " - " + modelo.getIdItemMenu () + " en el sistema.");
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
            ModeloPermisosRol modelo = new ModeloPermisosRol (
                    Integer.parseInt (request.getParameter ("id")),
                    Integer.parseInt (request.getParameter ("idrol")),
                    Integer.parseInt (request.getParameter ("iditemmenu"))
            );
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("UPDATE `permisosrol`  SET "
                            + "`IdRol` = ?,"
                            + "`IdTiemMenu` = ? "
                            + "WHERE "
                            + "`Id` = ?;");
                    SQL.setInt (1, modelo.getIdRol ());
                    SQL.setInt (2, modelo.getIdItemMenu ());
                    SQL.setInt (3, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a actualizado un permiso por rol, Id " + modelo.getId ()+ " en el sistema.");
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
            ModeloPermisosRol modelo = new ModeloPermisosRol ();
            modelo.setId (Integer.parseInt (request.getParameter ("id")));

            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("DELETE FROM `permisosrol` WHERE `Id` = ?;");
                    SQL.setInt (1, modelo.getId ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "2";
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
                        modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
                        modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a eliminado un permiso por rol, Id " + modelo.getId ()+" en el sistema.");
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

    public LinkedList<ModeloPermisosRol> Read ()
    {
        LinkedList<ModeloPermisosRol> modeloPermisosRols = new LinkedList<ModeloPermisosRol> ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`IdRol`,"
                    + "`IdTiemMenu` "
                    + "FROM `permisosrol`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloPermisosRol modelo = new ModeloPermisosRol ();
                modelo.setId (res.getInt ("id"));
                modelo.setIdRol (res.getInt ("IdRol"));
                modelo.setIdItemMenu (res.getInt ("IdTiemMenu"));
                modeloPermisosRols.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return modeloPermisosRols;
    }

    public String ReadRoles (HttpServletRequest request, HttpServletResponse response)
    {
        String out = null;
        try
        {
            LinkedList<ModeloPermisosRol> listmodelo;
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
            for (ModeloPermisosRol modelo : listmodelo)
            {
                ControladorRoles controladorRoles = new ControladorRoles ();
                ControladorItemMenu controladorItemMenu = new ControladorItemMenu ();
                ModeloRoles modeloRoles = controladorRoles.Select (modelo.getIdRol ());
                ModeloItemMenu modeloItemMenu = controladorItemMenu.Select (modelo.getIdItemMenu ());
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getId () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloRoles.getNombre () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloItemMenu.getNombre () + "</td>";
                out += "<td WIDTH = \"10\" HEIGHT=\"0\" class=\"text-center\">";
                // Boton Editar
                out += "<button class=\"SetFormulario btn btn-warning btn-xs\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-idrol=\"" + modelo.getIdRol () + "\"";
                out += "data-iditemmenu=\"" + modelo.getIdItemMenu () + "\"";
                out += "type=\"button\"><i id=\"IdModificar\" name=\"Modificar\" class=\"fa fa-edit\"></i> Editar</button>";
                //Boton Eliminar
                out += "<button class=\"SetEliminar btn btn-dark btn-xs\"";
                out += "data-id=\"" + modelo.getId () + "\"";
                out += "data-idrol=\"" + modelo.getIdRol () + "\"";
                out += "data-iditemmenu=\"" + modelo.getIdItemMenu () + "\"";
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

    LinkedList<ModeloItemMenu> Select (int idRol)
    {
        LinkedList<ModeloItemMenu> listModeloItemMenu = new LinkedList<ModeloItemMenu> ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        ControladorItemMenu controladorItemMenu = null;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`IdRol`,"
                    + "`IdTiemMenu` "
                    + "FROM `permisosrol`"
                    + "WHERE `IdRol` = ?");
            SQL.setInt (1, idRol);
            ResultSet res = SQL.executeQuery ();
            controladorItemMenu = new ControladorItemMenu ();
            while (res.next ())
            {
                ModeloItemMenu modeloItemMenu = new ModeloItemMenu ();
                modeloItemMenu = controladorItemMenu.Select (res.getInt ("IdTiemMenu"));
                listModeloItemMenu.add (modeloItemMenu);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return listModeloItemMenu;
    }
}
