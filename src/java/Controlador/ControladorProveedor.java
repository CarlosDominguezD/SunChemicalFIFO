/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloProveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorProveedor {

    public boolean Insert(ModeloProveedor modelo) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `proveedor`("
                        + "`Vendors`,"
                        + "`Name`) "
                        + "VALUE (?,?);");
                SQL.setString(1, modelo.getVendors());
                SQL.setString(2, modelo.getName());
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

    public boolean Insert(LinkedList<ModeloProveedor> listModeloProveedors) {
        boolean resul = false;
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try
            {
                SQL = con.prepareStatement("INSERT INTO `proveedor`("
                        + "`Vendors`,"
                        + "`Name`) "
                        + "VALUE (?,?);");
                for (ModeloProveedor modelo : listModeloProveedors)
                {
                    SQL.setString(1, modelo.getVendors());
                    SQL.setString(2, modelo.getName());
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

    public boolean Update(ModeloProveedor modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `proveedor` SET "
                    + "`Vendors` = ?,"
                    + "`Name` = ? "
                    + "WHERE `Id` = ?;");
            SQL.setString(1, modelo.getVendors());
            SQL.setString(2, modelo.getName());
            SQL.setInt(3, modelo.getId());
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

    public boolean Update(LinkedList<ModeloProveedor> listModeloProveedors) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("UPDATE `proveedor` SET "
                    + "`Vendors` = ?,"
                    + "`Name` = ? "
                    + "WHERE `Id` = ?;");
            for (ModeloProveedor modelo : listModeloProveedors)
            {
                SQL.setString(1, modelo.getVendors());
                SQL.setString(2, modelo.getName());
                SQL.setInt(3, modelo.getId());
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

    public boolean Delete(ModeloProveedor modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try
        {
            SQL = con.prepareStatement("DELETE FROM `proveedor` WHERE `Id` = ?;");
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
            SQL = con.prepareStatement("DELETE FROM `proveedor`;");
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

    public LinkedList<ModeloProveedor> Select() {
        LinkedList<ModeloProveedor> modeloProveedors = new LinkedList<ModeloProveedor>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Vendors`,"
                    + "`Name` "
                    + "FROM `proveedor`;");
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloProveedor modeloProveedor = new ModeloProveedor();
                modeloProveedor.setId(res.getInt("id"));
                modeloProveedor.setVendors(res.getString("Vendors"));
                modeloProveedor.setName(res.getString("Name"));

                modeloProveedors.add(modeloProveedor);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloProveedors;
    }

    public ModeloProveedor Select(Integer Id) {
        ModeloProveedor modeloProveedor = new ModeloProveedor();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Vendors`,"
                    + "`Name` "
                    + "FROM `proveedor`;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloProveedor.setId(res.getInt("id"));
                modeloProveedor.setVendors(res.getString("Vendors"));
                modeloProveedor.setName(res.getString("Name"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e)
        {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloProveedor;
    }
    
    public ModeloProveedor SelectSQL(String Sql) throws SQLException {
        ModeloProveedor modeloProveedor = new ModeloProveedor();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;        
        try
        {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modeloProveedor.setId(res.getInt("id"));
                modeloProveedor.setVendors(res.getString("Vendors"));
                modeloProveedor.setName(res.getString("Name"));
                modeloProveedor.setVendor_Type(res.getString("Vendor_Type"));
                modeloProveedor.setMoneda(res.getString("Moneda"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
            con.close();
        }
        return modeloProveedor;
    }
    
    
}
