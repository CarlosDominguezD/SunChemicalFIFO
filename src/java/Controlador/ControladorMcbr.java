/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloMcbr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorMcbr {

    public boolean InsertList(LinkedList<ModeloMcbr> listModeloMcbr) throws SQLException {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO mcbr("
                        + "Material, "
                        + "Descripcion, "
                        + "Plant, "
                        + "Batch, "
                        + "Month, "
                        + "Profit_center, "
                        + "Material_Type, "
                        + "Status, "
                        + "Val_stock, "
                        + "Val_stock_Med, "
                        + "ValStckVal, "
                        + "ValStck_Val_Mon, "
                        + "Cost_Unit_Estandar, "
                        + "InventarioInicial_FIFO, "
                        + "Cost_Unit_Purchase, "
                        + "Cost_Unit_KOB1_Piso, "
                        + "Cost_Unit_KOB1_Final, "
                        + "FIFO_Cost_Unit, "
                        + "Inventario_Valorado_a_FIFO, "
                        + "Variacion_FIFO_vs_Estandar)"
                        + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                for (ModeloMcbr modeloMcbr : listModeloMcbr) {
                    SQL.setString(1, modeloMcbr.getMaterial());
                    SQL.setString(2, modeloMcbr.getDescripcion());
                    SQL.setString(3, modeloMcbr.getPlant());
                    SQL.setString(4, modeloMcbr.getBatch());
                    SQL.setString(5, modeloMcbr.getMonth());
                    SQL.setString(6, modeloMcbr.getProfit_center());
                    SQL.setString(7, modeloMcbr.getMaterial_Type());
                    SQL.setString(8, modeloMcbr.getStatus());
                    SQL.setString(9, modeloMcbr.getVal_stock());
                    SQL.setString(10, modeloMcbr.getVal_stock_Med());
                    SQL.setString(11, modeloMcbr.getValStckVal());
                    SQL.setString(12, modeloMcbr.getValStck_Val_Mon());
                    SQL.setString(13, modeloMcbr.getCost_Unit_Estandar());
                    SQL.setString(14, modeloMcbr.getInventarioInicial_FIFO());
                    SQL.setString(15, modeloMcbr.getCost_Unit_Purchase());
                    SQL.setString(16, modeloMcbr.getCost_Unit_KOB1_Piso());
                    SQL.setString(17, modeloMcbr.getCost_Unit_KOB1_Final());
                    SQL.setString(18, modeloMcbr.getFIFO_Cost_Unit());
                    SQL.setString(19, modeloMcbr.getInventario_Valorado_a_FIFO());
                    SQL.setString(20, modeloMcbr.getVariacion_FIFO_vs_Estandar());

                    if (SQL.executeUpdate() > 0) {
                        resul = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error en la consulta SQL Insert " + e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public LinkedList<ModeloMcbr> Select() {
        LinkedList<ModeloMcbr> Lista_ModeloMcbr = new LinkedList<ModeloMcbr>();

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT "
                    + "id,"
                    + "Material,"
                    + "Descripcion,"
                    + "Plant,"
                    + "Batch,"
                    + "Month,"
                    + "Profit_center,"
                    + "Material_Type,"
                    + "Status,"
                    + "Val_stock,"
                    + "Val_stock_Med,"
                    + "ValStckVal,"
                    + "ValStck_Val_Mon,"
                    + "Cost_Unit_Estandar,"
                    + "InventarioInicial_FIFO,"
                    + "Cost_Unit_Purchase,"
                    + "Cost_Unit_KOB1_Piso,"
                    + "Cost_Unit_KOB1_Final,"
                    + "FIFO_Cost_Unit,"
                    + "Inventario_Valorado_a_FIFO,"
                    + "Variacion_FIFO_vs_Estandar "
                    + " FROM MCBR WHERE IdArchivo is null");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMcbr modeloMcbr = new ModeloMcbr();
                modeloMcbr.setId(res.getInt("id"));
                modeloMcbr.setMaterial(res.getString("Material"));
                modeloMcbr.setDescripcion(res.getString("Descripcion"));
                modeloMcbr.setPlant(res.getString("Plant"));
                modeloMcbr.setBatch(res.getString("Batch"));
                modeloMcbr.setMonth(res.getString("Month"));
                modeloMcbr.setProfit_center(res.getString("Profit_center"));
                modeloMcbr.setMaterial_Type(res.getString("Material_Type"));
                modeloMcbr.setStatus(res.getString("Status"));
                modeloMcbr.setVal_stock(res.getString("Val_stock"));
                modeloMcbr.setVal_stock_Med(res.getString("Val_stock_Med"));
                modeloMcbr.setValStckVal(res.getString("ValStckVal"));
                modeloMcbr.setValStck_Val_Mon(res.getString("ValStck_Val_Mon"));
                modeloMcbr.setCost_Unit_Estandar(res.getString("Cost_Unit_Estandar"));
                modeloMcbr.setInventarioInicial_FIFO(res.getString("InventarioInicial_FIFO"));
                modeloMcbr.setCost_Unit_Purchase(res.getString("Cost_Unit_Purchase"));
                modeloMcbr.setCost_Unit_KOB1_Piso(res.getString("Cost_Unit_KOB1_Piso"));
                modeloMcbr.setCost_Unit_KOB1_Final(res.getString("Cost_Unit_KOB1_Final"));
                modeloMcbr.setFIFO_Cost_Unit(res.getString("FIFO_Cost_Unit"));
                modeloMcbr.setInventario_Valorado_a_FIFO(res.getString("Inventario_Valorado_a_FIFO"));
                modeloMcbr.setVariacion_FIFO_vs_Estandar(res.getString("Variacion_FIFO_vs_Estandar"));
                Lista_ModeloMcbr.add(modeloMcbr);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        return Lista_ModeloMcbr;
    }

    public boolean UpdateList(LinkedList<ModeloMcbr> listModeloMcbr, Connection con) throws SQLException {
        boolean resul = false;
        try {
            //ConexionBDMySql conexion = new ConexionBDMySql();
            //Connection con;
            //con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("UPDATE mcbr SET "
                        + "Material = ?, "
                        + "Descripcion = ?, "
                        + "Plant = ?, "
                        + "Batch = ?, "
                        + "Month = ?, "
                        + "Profit_center = ?, "
                        + "Material_Type = ?, "
                        + "Status = ?, "
                        + "Val_stock = ?, "
                        + "Val_stock_Med = ?, "
                        + "ValStckVal = ?, "
                        + "ValStck_Val_Mon = ?, "
                        + "Cost_Unit_Estandar = ?, "
                        + "InventarioInicial_FIFO = ?, "
                        + "Cost_Unit_Purchase = ?, "
                        + "Cost_Unit_KOB1_Piso = ?, "
                        + "Cost_Unit_KOB1_Final = ?, "
                        + "FIFO_Cost_Unit = ?, "
                        + "Inventario_Valorado_a_FIFO = ?, "
                        + "Variacion_FIFO_vs_Estandar = ? "
                        + " WHERE Id = ? ");
                for (ModeloMcbr modeloMcbr : listModeloMcbr) {
                    SQL.setString(1, modeloMcbr.getMaterial());
                    SQL.setString(2, modeloMcbr.getDescripcion());
                    SQL.setString(3, modeloMcbr.getPlant());
                    SQL.setString(4, modeloMcbr.getBatch());
                    SQL.setString(5, modeloMcbr.getMonth());
                    SQL.setString(6, modeloMcbr.getProfit_center());
                    SQL.setString(7, modeloMcbr.getMaterial_Type());
                    SQL.setString(8, modeloMcbr.getStatus());
                    SQL.setString(9, modeloMcbr.getVal_stock());
                    SQL.setString(10, modeloMcbr.getVal_stock_Med());
                    SQL.setString(11, modeloMcbr.getValStckVal());
                    SQL.setString(12, modeloMcbr.getValStck_Val_Mon());
                    SQL.setString(13, modeloMcbr.getCost_Unit_Estandar());
                    SQL.setString(14, modeloMcbr.getInventarioInicial_FIFO());
                    SQL.setString(15, modeloMcbr.getCost_Unit_Purchase());
                    SQL.setString(16, modeloMcbr.getCost_Unit_KOB1_Piso());
                    SQL.setString(17, modeloMcbr.getCost_Unit_KOB1_Final());
                    SQL.setString(18, modeloMcbr.getFIFO_Cost_Unit());
                    SQL.setString(19, modeloMcbr.getInventario_Valorado_a_FIFO());
                    SQL.setString(20, modeloMcbr.getVariacion_FIFO_vs_Estandar());
                    SQL.setInt(21, modeloMcbr.getId());

                    if (SQL.executeUpdate() > 0) {
                        resul = true;
                    }
                }
            } catch (SQLException e) {
                SQL.close();
                System.out.println("Error en la consulta SQL Update " + e);
            }
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
        }
        return resul;
    }

}
