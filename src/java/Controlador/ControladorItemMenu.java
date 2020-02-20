/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloItemMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorItemMenu
{

    public LinkedList<ModeloItemMenu> Read ()
    {
        LinkedList<ModeloItemMenu> modeloItemMenu = new LinkedList<ModeloItemMenu> ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`ItemMenu`,"
                    + "`Estado` "
                    + "FROM `itemmenu`"
                    + "WHERE "
                    + "Estado = 'true';");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                ModeloItemMenu modelo = new ModeloItemMenu ();
                modelo.setId (res.getInt ("id"));
                modelo.setNombre (res.getString ("ItemMenu"));
                modeloItemMenu.add (modelo);
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return modeloItemMenu;
    }

    public ModeloItemMenu Select (int idItemMenu)
    {
        ModeloItemMenu modelo = new ModeloItemMenu ();
        ConexionBDMySql conexion = new ConexionBDMySql ();
        Connection con;
        con = conexion.abrirConexion ();
        PreparedStatement SQL;
        try
        {
            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`ItemMenu`,"
                    + "`Estado` "
                    + "FROM `itemmenu` "
                    + "WHERE `Id` = " + idItemMenu + ";");
            ResultSet res = SQL.executeQuery ();
            while (res.next ())
            {
                modelo.setId (res.getInt ("id"));
                modelo.setNombre (res.getString ("ItemMenu"));                  
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
