/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorFechas
{

    Integer getIdFecha (HttpServletRequest request)
    {
        Integer idFecha = null;
        String ano = request.getParameter ("Mes");
        String mes = request.getParameter ("Ano");
        
        try
        {
            ConexionBDMySql conexion = new ConexionBDMySql ();
            Connection con;
            con = conexion.abrirConexion ();
            PreparedStatement SQL;

            SQL = con.prepareStatement ("SELECT "
                    + "`Id`,"
                    + "`mes`,"
                    + "`ano` "
                    + "FROM `fechas` "
                    + "WHERE "
                    + "`mes` = ? "
                    + "AND "
                    + "`ano` = ? ;");
            SQL.setString (1, ano);
            SQL.setString (2, mes);
            ResultSet res = SQL.executeQuery ();
            if (res.next ())
            {
                idFecha = res.getInt ("id");                
            }
            res.close ();
            SQL.close ();
            con.close ();
        } catch (SQLException e)
        {
            System.out.println ("Error en la consulta SQL Select " + e);
        }
        return idFecha;
    }

}
