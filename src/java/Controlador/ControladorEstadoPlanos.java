/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloEstadoPlanos;
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
public class ControladorEstadoPlanos {

    public boolean Insert(ModeloEstadoPlanos modelo) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `estadoplanos`("
                        + "`Actividad`,"
                        + "`NombrePlano`,"
                        + "`FechaCarga`,"
                        + "`Estado`)"
                        + " VALUE (?,?,?,?);");
                SQL.setString(1, modelo.getActividad());
                SQL.setString(2, modelo.getNombrePlano());
                SQL.setString(3, modelo.getFechaCarga());
                SQL.setString(4, modelo.getEstado());
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

    public boolean Insert(LinkedList<ModeloEstadoPlanos> listModeloEstadoPlanoss) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `estadoplanos`("
                        + "`Actividad`,"
                        + "`NombrePlano`,"
                        + "`FechaCarga`,"
                        + "`Estado`)"
                        + " VALUE (?,?,?,?);");
                for (ModeloEstadoPlanos modelo : listModeloEstadoPlanoss)
                {
                    SQL.setString(1, modelo.getActividad());
                    SQL.setString(2, modelo.getNombrePlano());
                    SQL.setString(3, modelo.getFechaCarga());
                    SQL.setString(4, modelo.getEstado());
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

    public boolean Update(ModeloEstadoPlanos modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `estadoplanos` SET "
                    + "`Actividad` = ?,"
                    + "`NombrePlano` = ?,"
                    + "`FechaCarga` = ?,"
                    + "`Estado` = ? "
                    + "WHERE `Id` = ?;");
            SQL.setString(1, modelo.getActividad());
            SQL.setString(2, modelo.getNombrePlano());
            SQL.setString(3, modelo.getFechaCarga());
            SQL.setString(4, modelo.getEstado());
            SQL.setInt(5, modelo.getId());
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

    public boolean Update(LinkedList<ModeloEstadoPlanos> listModeloEstadoPlanoss) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `estadoplanos` SET "
                    + "`Actividad` = ?,"
                    + "`NombrePlano` = ?,"
                    + "`FechaCarga` = ?,"
                    + "`Estado` = ? "
                    + "WHERE `Id` = ?;");
            for (ModeloEstadoPlanos modelo : listModeloEstadoPlanoss)
            {
                SQL.setString(1, modelo.getActividad());
                SQL.setString(2, modelo.getNombrePlano());
                SQL.setString(3, modelo.getFechaCarga());
                SQL.setString(4, modelo.getEstado());
                SQL.setInt(5, modelo.getId());
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

    public boolean Delete(ModeloEstadoPlanos modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `estadoplanos` WHERE `Id` = ?;");
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
            SQL = con.prepareStatement("DELETE FROM `estadoplanos`;");
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

    public LinkedList<ModeloEstadoPlanos> Select() {
        LinkedList<ModeloEstadoPlanos> modeloEstadoPlanoss = new LinkedList<ModeloEstadoPlanos>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Actividad`,"
                    + "`NombrePlano`,"
                    + "`FechaCarga`,"
                    + "`Estado` "
                    + "FROM `estadoplanos`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
                modeloEstadoPlanos.setId(res.getInt("id"));
                modeloEstadoPlanos.setActividad(res.getString("Actividad"));
                modeloEstadoPlanos.setNombrePlano(res.getString("NombrePlano"));
                modeloEstadoPlanos.setFechaCarga(res.getString("FechaCarga"));
                modeloEstadoPlanos.setEstado(res.getString("Estado"));
                modeloEstadoPlanoss.add(modeloEstadoPlanos);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloEstadoPlanoss;
    }

    public LinkedList<ModeloEstadoPlanos> Select( String Actividad) {
        LinkedList<ModeloEstadoPlanos> modeloEstadoPlanoss = new LinkedList<ModeloEstadoPlanos>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Actividad`,"
                    + "`NombrePlano`,"
                    + "`FechaCarga`,"
                    + "`Estado` "
                    + "FROM `estadoplanos`"
                    + "WHERE Actividad = ?;");
            SQL.setString(1, Actividad);
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
                modeloEstadoPlanos.setId(res.getInt("id"));
                modeloEstadoPlanos.setActividad(res.getString("Actividad"));
                modeloEstadoPlanos.setNombrePlano(res.getString("NombrePlano"));
                modeloEstadoPlanos.setFechaCarga(res.getString("FechaCarga"));
                modeloEstadoPlanos.setEstado(res.getString("Estado"));
                modeloEstadoPlanoss.add(modeloEstadoPlanos);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloEstadoPlanoss;
    }

    public ModeloEstadoPlanos Select(Integer Id) {
        ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Actividad`,"
                    + "`NombrePlano`,"
                    + "`FechaCarga`,"
                    + "`Estado` "
                    + "FROM `estadoplanos`"
                    + "WHERE Id = ?;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloEstadoPlanos.setId(res.getInt("id"));
                modeloEstadoPlanos.setActividad(res.getString("Actividad"));
                modeloEstadoPlanos.setNombrePlano(res.getString("NombrePlano"));
                modeloEstadoPlanos.setFechaCarga(res.getString("FechaCarga"));
                modeloEstadoPlanos.setEstado(res.getString("Estado"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloEstadoPlanos;
    }

    public String ValidarArchivos(HttpServletRequest request, HttpServletResponse response) {
        String out = null;
        try
        {
            LinkedList<ModeloEstadoPlanos> listModelosestadoPlanos;
            listModelosestadoPlanos = Select(request.getParameter("actividad"));
            response.setContentType("text/html;charset=UTF-8");
            out = "";
            out += "<thead>";
            out += "<tr>";
            out += "<th>Nombre Plano</th>";
            out += "<th>Fecha Carga</th>";
            out += "<th>Estado</th>";            
            out += "</tr>";
            out += "</thead>";
            out += "<tbody>";
            for (ModeloEstadoPlanos modeloEstadoPlanos : listModelosestadoPlanos)
            {
                out += "<tr>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloEstadoPlanos.getNombrePlano()+ "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloEstadoPlanos.getFechaCarga()+ "</td>";
                out += "<td WIDTH = \"0\" HEIGHT=\"0\">" + modeloEstadoPlanos.getEstado() + "</td>";                
                out += "</tr>";
            }
            out += "</tbody>";
//            PrintWriter pw = response.getWriter();
//            pw.write(out);
//            System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
        } catch (Exception e)
        {
            System.out.println("Error en el kproceso de la tabla " + e.getMessage());
        }
//        String frm = request.getParameter("frm");
//        System.out.println(frm);
//        processRequest(request, response);
        return out;
    }
}
