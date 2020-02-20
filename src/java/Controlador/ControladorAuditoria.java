/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Herramienta.Herramienta;
import Modelos.ModeloAuditoria;
import Modelos.ModeloRoles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorAuditoria
{

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    public String Insert (ModeloAuditoria modeloAuditoria)
    {
        if (!"".equals (modeloAuditoria))
        {
            Herramienta herrmientas = new Herramienta ();
            try
            {
                ConexionBDMySql conexion = new ConexionBDMySql ();
                Connection con;
                con = conexion.abrirConexion ();
                try
                {
                    SQL = con.prepareStatement ("INSERT INTO `auditoria`("
                            + "`Usuario`,"
                            + "`Fecha`,"
                            + "`Hora`,"
                            + "`Mensaje`)"
                            + " VALUE "
                            + "(?,?,?,?);");
                    SQL.setString (1, modeloAuditoria.getModeloUsuario ().getUsuario ());
                    SQL.setString (2, herrmientas.sDate ());
                    SQL.setString (3, herrmientas.sTime ());
                    SQL.setString (4, modeloAuditoria.getMensaje ());
                    if (SQL.executeUpdate () > 0)
                    {
                        resultado = "1";
                        SQL.close ();
                        con.close ();
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

    public String ReadAuditoria (HttpServletRequest request, HttpServletResponse response)
    {
        String out = null;
        try
        {
            LinkedList<ModeloAuditoria> listmodelo;
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
            for (ModeloAuditoria modelo : listmodelo)
            {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getId () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getModeloUsuario ().getUsuario () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getFecha () + "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modelo.getMensaje () + "</td>";
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

    private LinkedList<ModeloAuditoria> Read ()
    {
        LinkedList<ModeloAuditoria> modeloAuditorias = new LinkedList<ModeloAuditoria> ();
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`Usuario`,"
                    + "`Fecha`,"
                    + "`Hora`,"
                    + "`Mensaje`"
                    + " FROM `auditoria`;");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloAuditoria modelo = new ModeloAuditoria ();
                modelo.setId (res.getInt ("id"));
                modelo.setModeloUsuario (controladorUsuarios.SelectS (res.getString ("Usuario")));
                modelo.setFecha (res.getString ("Fecha") +" "+ res.getString ("Hora"));
                //4854275                
                modelo.setMensaje (res.getString ("Mensaje"));
                modeloAuditorias.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return modeloAuditorias;
    }
}
