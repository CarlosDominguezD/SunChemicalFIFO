/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloFechas;
import Modelos.ModeloUsuario;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorFechas {

    PreparedStatement SQL = null;
    String resultado = "";
    Connection con;

    Integer getIdFecha(HttpServletRequest request) {
        Integer idFecha = null;
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");

        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL;

            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`, "
                    + "`estadoCompras`, "
                    + "`estadoProduccion`, "
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? ;");
            SQL.setString(1, ano);
            SQL.setString(2, mes);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                idFecha = res.getInt("id");
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return idFecha;
    }

    public String GetModelo(HttpServletRequest request, HttpServletResponse response) {
        ModeloFechas modeloFechas = new ModeloFechas();
        String Resultado;
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String fase = request.getParameter("Fase");
        String estado = request.getParameter("Estado");
        String sql = null;
        if ("Compras".equals(fase)) {
            sql = "SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`,"
                    + "`estadoCompras`,"
                    + "`estadoProduccion`,"
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? "
                    + "AND "
                    + "`estadoCompras` = ? ;";
        }
        if ("Produccion".equals(fase)) {
            sql = "SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`,"
                    + "`estadoCompras`,"
                    + "`estadoProduccion`,"
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? "
                    + "AND "
                    + "`estadoProduccion` = ? ;";
        }
        if ("Inventario".equals(fase)) {
            sql = "SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`,"
                    + "`estadoCompras`,"
                    + "`estadoProduccion`,"
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? "
                    + "AND "
                    + "`estadoInventario` = ? ;";
        }

        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL;

            SQL = con.prepareStatement(sql);
            SQL.setString(1, ano);
            SQL.setString(2, mes);
            SQL.setString(3, estado);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloFechas.setId(res.getInt("id"));
                modeloFechas.setMes(res.getString("mes"));
                modeloFechas.setAno(res.getInt("ano"));
                modeloFechas.setEstadoCompras(res.getString("estadoCompras"));
                modeloFechas.setEstadoProduccion(res.getString("estadoProduccion"));
                modeloFechas.setEstadoInventario(res.getString("estadoInventario"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        Resultado = new Gson().toJson(modeloFechas);
        return Resultado;
    }
    
    
    public ModeloFechas GetModeloFechas (String mes, String ano, String fase)
    {
        ModeloFechas modeloFechas = null;
        String sql = null;
        if ("Compras".equals(fase)) {
            sql = "SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`,"
                    + "`estadoCompras`,"
                    + "`estadoProduccion`,"
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? "
                    + "AND "
                    + "`estadoCompras` = ? ;";
        }
        if ("Produccion".equals(fase)) {
            sql = "SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`,"
                    + "`estadoCompras`,"
                    + "`estadoProduccion`,"
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? "
                    + "AND "
                    + "`estadoProduccion` = ? ;";
        }
        if ("Inventario".equals(fase)) {
            sql = "SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano`,"
                    + "`estadoCompras`,"
                    + "`estadoProduccion`,"
                    + "`estadoInventario` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? "
                    + "AND "
                    + "`estadoInventario` = ? ;";
        }

        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL;

            SQL = con.prepareStatement(sql);
            SQL.setString(1, ano);
            SQL.setString(2, mes);
            SQL.setString(3, "Abierto");
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloFechas = new ModeloFechas();
                modeloFechas.setId(res.getInt("id"));
                modeloFechas.setMes(res.getString("mes"));
                modeloFechas.setAno(res.getInt("ano"));
                modeloFechas.setEstadoCompras(res.getString("estadoCompras"));
                modeloFechas.setEstadoProduccion(res.getString("estadoProduccion"));
                modeloFechas.setEstadoInventario(res.getString("estadoInventario"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloFechas;
    }
    
    

    public String GetAbrirFecha(HttpServletRequest request, HttpServletResponse response) {

        if (!"".equals(request.getParameter("Id"))) {
            String Fase = request.getParameter("Fase");
            try {
                ConexionBDMySql conexion = new ConexionBDMySql();
                Connection con;
                con = conexion.abrirConexion();
                try {
                    if ("Compras".equals(request.getParameter("Fase"))) {
                        SQL = con.prepareStatement("UPDATE `fechas`  SET "
                                + "`estadoCompras` = ? "
                                + "WHERE `Id` = ?");
                        SQL.setString(1, "Abierto");
                        SQL.setString(2, request.getParameter("Id"));
                    }
                    if ("Produccion".equals(request.getParameter("Fase"))) {
                        SQL = con.prepareStatement("UPDATE `fechas`  SET "
                                + "`estadoProduccion` = ? "
                                + "WHERE `Id` = ?;");
                        SQL.setString(1, "Abierto");
                        SQL.setString(2, request.getParameter("Id"));
                    }
                    if ("Inventario".equals(request.getParameter("Fase"))) {
                        SQL = con.prepareStatement("UPDATE `fechas`  SET "
                                + "`estadoInventario` = ? "
                                + "WHERE `Id` = ?;");
                        SQL.setString(1, "Abierto");
                        SQL.setString(2, request.getParameter("Id"));
                    }

                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                        modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                        modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a modificado una fecha , Id " + request.getParameter("Id") + " en el sistema.");
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

    public String GetCerrarFecha(HttpServletRequest request, HttpServletResponse response) {
        if (!"".equals(request.getParameter("Id"))) {
            String Fase = request.getParameter("Fase");
            try {
                ConexionBDMySql conexion = new ConexionBDMySql();
                Connection con;
                con = conexion.abrirConexion();
                try {
                    if ("Compras".equals(request.getParameter("Fase"))) {
                        SQL = con.prepareStatement("UPDATE `fechas`  SET "
                                + "`estadoCompras` = ? "
                                + "WHERE `Id` = ?");
                        SQL.setString(1, "Cerrado");
                        SQL.setString(2, request.getParameter("Id"));
                    }
                    if ("Produccion".equals(request.getParameter("Fase"))) {
                        SQL = con.prepareStatement("UPDATE `fechas`  SET "
                                + "`estadoProduccion` = ? "
                                + "WHERE `Id` = ?;");
                        SQL.setString(1, "Cerrado");
                        SQL.setString(2, request.getParameter("Id"));
                    }
                    if ("Inventario".equals(request.getParameter("Fase"))) {
                        SQL = con.prepareStatement("UPDATE `fechas`  SET "
                                + "`estadoInventario` = ? "
                                + "WHERE `Id` = ?;");
                        SQL.setString(1, "Cerrado");
                        SQL.setString(2, request.getParameter("Id"));
                    }

                    if (SQL.executeUpdate() > 0) {
                        resultado = "1";
                        SQL.close();
                        con.close();
                        //Auditoria
                        ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                        modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                        modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a modificado una fecha , Id " + request.getParameter("Id") + " en el sistema.");
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
}
