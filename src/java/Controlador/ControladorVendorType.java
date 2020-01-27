/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloVendorType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorVendorType {

    public ModeloVendorType Select(String Sql) {
        ModeloVendorType modeloVendorType = null;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloVendorType = new ModeloVendorType();
                modeloVendorType.setId(res.getInt("id"));
                modeloVendorType.setMaterial(res.getString("Material"));
                modeloVendorType.setVendor_Type(res.getString("Vendor_Type"));
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        return modeloVendorType;
    }
}
