/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Herramienta.Herramienta;
import Modelos.ModeloMcbr;
import java.sql.CallableStatement;
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

    Herramienta herramienta = new Herramienta();

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

    public boolean UpdateList_Masivo(LinkedList<ModeloMcbr> listModeloMcbr, Connection con, String Porcentaje) throws SQLException {
        boolean resul = false;

        CallableStatement UpdateKOB1Masivo = con.prepareCall("{call UPDATE_KOB1_MASIVO(?)}");

        StringBuilder Material = new StringBuilder();
        StringBuilder Descripcion = new StringBuilder();
        StringBuilder Plant = new StringBuilder();
        StringBuilder Batch = new StringBuilder();
        StringBuilder Month = new StringBuilder();
        StringBuilder Profit_center = new StringBuilder();
        StringBuilder Material_Type = new StringBuilder();
        StringBuilder Status = new StringBuilder();
        StringBuilder Val_stock = new StringBuilder();
        StringBuilder Val_stock_Med = new StringBuilder();
        StringBuilder ValStckVal = new StringBuilder();
        StringBuilder ValStck_Val_Mon = new StringBuilder();
        StringBuilder Cost_Unit_Estandar = new StringBuilder();
        StringBuilder InventarioInicial_FIFO = new StringBuilder();
        StringBuilder Cost_Unit_Purchase = new StringBuilder();
        StringBuilder Cost_Unit_KOB1_Piso = new StringBuilder();
        StringBuilder Cost_Unit_KOB1_Final = new StringBuilder();
        StringBuilder FIFO_Cost_Unit = new StringBuilder();
        StringBuilder Inventario_Valorado_a_FIFO = new StringBuilder();
        StringBuilder Variacion_FIFO_vs_Estandar = new StringBuilder();
        StringBuilder IdArchivo = new StringBuilder();
        StringBuilder Id = new StringBuilder();

        Material.append("(CASE id ");
        Descripcion.append("(CASE id ");
        Plant.append("(CASE id ");
        Batch.append("(CASE id ");
        Month.append("(CASE id ");
        Profit_center.append("(CASE id ");
        Material_Type.append("(CASE id ");
        Status.append("(CASE id ");
        Val_stock.append("(CASE id ");
        Val_stock_Med.append("(CASE id ");
        ValStckVal.append("(CASE id ");
        ValStck_Val_Mon.append("(CASE id ");
        Cost_Unit_Estandar.append("(CASE id ");
        InventarioInicial_FIFO.append("(CASE id ");
        Cost_Unit_Purchase.append("(CASE id ");
        Cost_Unit_KOB1_Piso.append("(CASE id ");
        Cost_Unit_KOB1_Final.append("(CASE id ");
        FIFO_Cost_Unit.append("(CASE id ");
        Inventario_Valorado_a_FIFO.append("(CASE id ");
        Variacion_FIFO_vs_Estandar.append("(CASE id ");
        IdArchivo.append("(CASE id ");
        //Id.append("(CASE id ");
        int Contador = 0;
        PreparedStatement SQL = null;
        //String SQLl = null;
        StringBuilder SQLl = new StringBuilder();
        try {
            //Recorreo el modelo e inserto los datos en las variables
            int c = 1, contt = 0;
            int v = 1;

            int Porcentaje1 = Integer.valueOf(Porcentaje);
            int VUeltas = listModeloMcbr.size() / 30;
            int sumador = 1;

            for (ModeloMcbr modeloMcbr : listModeloMcbr) {

                if (sumador == VUeltas) {
                    herramienta.setEventoProcesado("Progreso " + Porcentaje1 + "%");
                    System.err.println("Progreso " + Porcentaje1 + "%");
                    Porcentaje1++;
                    sumador = 1;
                }
                sumador++;

                contt++;
                if (c == 2) {
                    Id.append(",");
                }

                Material.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getMaterial())).append("' ");
                Descripcion.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getDescripcion())).append("' ");
                Plant.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getPlant())).append("' ");
                Batch.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getBatch())).append("' ");
                Month.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getMonth())).append("' ");
                Profit_center.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getProfit_center())).append("' ");
                Material_Type.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getMaterial_Type())).append("' ");
                Status.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getStatus())).append("' ");
                Val_stock.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getVal_stock())).append("' ");
                Val_stock_Med.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getVal_stock_Med())).append("' ");
                ValStckVal.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getValStckVal())).append("' ");
                ValStck_Val_Mon.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getValStck_Val_Mon())).append("' ");
                Cost_Unit_Estandar.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getCost_Unit_Estandar())).append("' ");
                InventarioInicial_FIFO.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getInventarioInicial_FIFO())).append("' ");
                Cost_Unit_Purchase.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getCost_Unit_Purchase())).append("' ");
                Cost_Unit_KOB1_Piso.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getCost_Unit_KOB1_Piso())).append("' ");
                Cost_Unit_KOB1_Final.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getCost_Unit_KOB1_Final())).append("' ");
                FIFO_Cost_Unit.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getFIFO_Cost_Unit())).append("' ");
                Inventario_Valorado_a_FIFO.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getInventario_Valorado_a_FIFO())).append("' ");
                Variacion_FIFO_vs_Estandar.append("WHEN ").append(modeloMcbr.getId()).append(" THEN '").append(Valor(modeloMcbr.getVariacion_FIFO_vs_Estandar())).append("' ");
                IdArchivo.append("WHEN ").append(modeloMcbr.getId()).append(" THEN ").append(modeloMcbr.getIdArchivo()).append(" ");
                Id.append(modeloMcbr.getId());
                c = 2;
                if (contt == 1000) {
                    Material.append("END)");
                    Descripcion.append("END)");
                    Plant.append("END)");
                    Batch.append("END)");
                    Month.append("END)");
                    Profit_center.append("END)");
                    Material_Type.append("END)");
                    Status.append("END)");
                    Val_stock.append("END)");
                    Val_stock_Med.append("END)");
                    ValStckVal.append("END)");
                    ValStck_Val_Mon.append("END)");
                    Cost_Unit_Estandar.append("END)");
                    InventarioInicial_FIFO.append("END)");
                    Cost_Unit_Purchase.append("END)");
                    Cost_Unit_KOB1_Piso.append("END)");
                    Cost_Unit_KOB1_Final.append("END)");
                    FIFO_Cost_Unit.append("END)");
                    Inventario_Valorado_a_FIFO.append("END)");
                    Variacion_FIFO_vs_Estandar.append("END)");
                    IdArchivo.append("END)");

                    SQLl.append("UPDATE mcbr SET ")
                            .append("Material = ").append(Material).append(", ")
                            .append("Descripcion = ").append(Descripcion).append(", ")
                            .append("Plant = ").append(Plant).append(", ")
                            .append("Batch = ").append(Batch).append(", ")
                            .append("Month = ").append(Month).append(", ")
                            .append("Profit_center = ").append(Profit_center).append(", ")
                            .append("Material_Type = ").append(Material_Type).append(", ")
                            .append("Status = ").append(Status).append(", ")
                            .append("Val_stock = ").append(Val_stock).append(", ")
                            .append("Val_stock_Med = ").append(Val_stock_Med).append(", ")
                            .append("ValStckVal = ").append(ValStckVal).append(", ")
                            .append("ValStck_Val_Mon = ").append(ValStck_Val_Mon).append(", ")
                            .append("Cost_Unit_Estandar = ").append(Cost_Unit_Estandar).append(", ")
                            .append("InventarioInicial_FIFO = ").append(InventarioInicial_FIFO).append(", ")
                            .append("Cost_Unit_Purchase = ").append(Cost_Unit_Purchase).append(", ")
                            .append("Cost_Unit_KOB1_Piso = ").append(Cost_Unit_KOB1_Piso).append(", ")
                            .append("Cost_Unit_KOB1_Final = ").append(Cost_Unit_KOB1_Final).append(", ")
                            .append("FIFO_Cost_Unit = ").append(FIFO_Cost_Unit).append(", ")
                            .append("Inventario_Valorado_a_FIFO = ").append(Inventario_Valorado_a_FIFO).append(", ")
                            .append("Variacion_FIFO_vs_Estandar = ").append(Variacion_FIFO_vs_Estandar).append(", ")
                            .append("IdArchivo = ").append(IdArchivo).append("")
                            .append(" WHERE Id IN (").append(Id).append(") ");
                    //Paso el Sql al statement
                    //SQL = con.prepareStatement(SQLl + "");
                    //ejecuto el SQL

                    UpdateKOB1Masivo.setString(1, SQLl.toString());

                    if (!UpdateKOB1Masivo.execute()) {

                        //System.out.println("Vuelta registros " + v);
                        v++;

                        resul = true;
                        // limpiamos los datos
                        SQLl.delete(0, SQLl.length());
                        Material.delete(0, Material.length());
                        Descripcion.delete(0, Descripcion.length());
                        Plant.delete(0, Plant.length());
                        Batch.delete(0, Batch.length());
                        Month.delete(0, Month.length());
                        Profit_center.delete(0, Profit_center.length());
                        Material_Type.delete(0, Material_Type.length());
                        Status.delete(0, Status.length());
                        Val_stock.delete(0, Val_stock.length());
                        Val_stock_Med.delete(0, Val_stock_Med.length());
                        ValStckVal.delete(0, ValStckVal.length());
                        ValStck_Val_Mon.delete(0, ValStck_Val_Mon.length());
                        Cost_Unit_Estandar.delete(0, Cost_Unit_Estandar.length());
                        InventarioInicial_FIFO.delete(0, InventarioInicial_FIFO.length());
                        Cost_Unit_Purchase.delete(0, Cost_Unit_Purchase.length());
                        Cost_Unit_KOB1_Piso.delete(0, Cost_Unit_KOB1_Piso.length());
                        Cost_Unit_KOB1_Final.delete(0, Cost_Unit_KOB1_Final.length());
                        FIFO_Cost_Unit.delete(0, FIFO_Cost_Unit.length());
                        Inventario_Valorado_a_FIFO.delete(0, Inventario_Valorado_a_FIFO.length());
                        Variacion_FIFO_vs_Estandar.delete(0, Variacion_FIFO_vs_Estandar.length());
                        IdArchivo.delete(0, IdArchivo.length());
                        Id.delete(0, Id.length());

                        Material.append("(CASE id ");
                        Descripcion.append("(CASE id ");
                        Plant.append("(CASE id ");
                        Batch.append("(CASE id ");
                        Month.append("(CASE id ");
                        Profit_center.append("(CASE id ");
                        Material_Type.append("(CASE id ");
                        Status.append("(CASE id ");
                        Val_stock.append("(CASE id ");
                        Val_stock_Med.append("(CASE id ");
                        ValStckVal.append("(CASE id ");
                        ValStck_Val_Mon.append("(CASE id ");
                        Cost_Unit_Estandar.append("(CASE id ");
                        InventarioInicial_FIFO.append("(CASE id ");
                        Cost_Unit_Purchase.append("(CASE id ");
                        Cost_Unit_KOB1_Piso.append("(CASE id ");
                        Cost_Unit_KOB1_Final.append("(CASE id ");
                        FIFO_Cost_Unit.append("(CASE id ");
                        Inventario_Valorado_a_FIFO.append("(CASE id ");
                        Variacion_FIFO_vs_Estandar.append("(CASE id ");
                        IdArchivo.append("(CASE id ");
                        //Id.append("(CASE id ");

                    }
                    c = 1;
                    contt = 0;
                }
            }
            //finalizo el formato del SQL
            Material.append("END)");
            Descripcion.append("END)");
            Plant.append("END)");
            Batch.append("END)");
            Month.append("END)");
            Profit_center.append("END)");
            Material_Type.append("END)");
            Status.append("END)");
            Val_stock.append("END)");
            Val_stock_Med.append("END)");
            ValStckVal.append("END)");
            ValStck_Val_Mon.append("END)");
            Cost_Unit_Estandar.append("END)");
            InventarioInicial_FIFO.append("END)");
            Cost_Unit_Purchase.append("END)");
            Cost_Unit_KOB1_Piso.append("END)");
            Cost_Unit_KOB1_Final.append("END)");
            FIFO_Cost_Unit.append("END)");
            Inventario_Valorado_a_FIFO.append("END)");
            Variacion_FIFO_vs_Estandar.append("END)");
            IdArchivo.append("END)");
            //Id.append("END)");
            //Armo el Sql 
            SQLl.delete(0, SQLl.length());
            SQLl.append("UPDATE mcbr SET ")
                    .append("Material = ").append(Material).append(", ")
                    .append("Descripcion = ").append(Descripcion).append(", ")
                    .append("Plant = ").append(Plant).append(", ")
                    .append("Batch = ").append(Batch).append(", ")
                    .append("Month = ").append(Month).append(", ")
                    .append("Profit_center = ").append(Profit_center).append(", ")
                    .append("Material_Type = ").append(Material_Type).append(", ")
                    .append("Status = ").append(Status).append(", ")
                    .append("Val_stock = ").append(Val_stock).append(", ")
                    .append("Val_stock_Med = ").append(Val_stock_Med).append(", ")
                    .append("ValStckVal = ").append(ValStckVal).append(", ")
                    .append("ValStck_Val_Mon = ").append(ValStck_Val_Mon).append(", ")
                    .append("Cost_Unit_Estandar = ").append(Cost_Unit_Estandar).append(", ")
                    .append("InventarioInicial_FIFO = ").append(InventarioInicial_FIFO).append(", ")
                    .append("Cost_Unit_Purchase = ").append(Cost_Unit_Purchase).append(", ")
                    .append("Cost_Unit_KOB1_Piso = ").append(Cost_Unit_KOB1_Piso).append(", ")
                    .append("Cost_Unit_KOB1_Final = ").append(Cost_Unit_KOB1_Final).append(", ")
                    .append("FIFO_Cost_Unit = ").append(FIFO_Cost_Unit).append(", ")
                    .append("Inventario_Valorado_a_FIFO = ").append(Inventario_Valorado_a_FIFO).append(", ")
                    .append("Variacion_FIFO_vs_Estandar = ").append(Variacion_FIFO_vs_Estandar).append(", ")
                    .append("IdArchivo = ").append(IdArchivo).append("")
                    .append(" WHERE Id IN (").append(Id).append(") ");
            //Paso el Sql al statement
            UpdateKOB1Masivo.setString(1, SQLl.toString());

            if (!UpdateKOB1Masivo.execute()) {
                resul = true;
            }
            //SQL.close();
//            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e.getMessage());
            //SQL.close();

        }
        return resul;

    }

    public StringBuilder Valor(String Valor) {
        StringBuilder Cadena = new StringBuilder();

        if (Valor == null) {
            //Cadena.append(Valor);
            Cadena.append("");

        } else {
            //Cadena.append("'").append(Valor).append("'");
            Cadena.append(Valor);

        }

        return Cadena;
    }

}
