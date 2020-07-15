/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Conexiones.Pool;
import Modelos.ModeloMrpData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorMrpdata {

    public boolean Insert(ModeloMrpData modelo) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO `mrpdata`("
                        + "`Material`,"
                        + "`Material_Description`,"
                        + "`Material_Group`,"
                        + "`Profit_Center`,"
                        + "`Product_Hierarchy`,"
                        + "`Product_Hierarchy_Description`,"
                        + "`Material_Type`,"
                        + "`Material_type_descr`,"
                        + "`Plant`,"
                        + "`Plant_Name`,"
                        + "`Ext_Material_Group`,"
                        + "`Matl_Grp_Pack_Matls`,"
                        + "`MGPM_Description`,"
                        + "`MRP_profile`,"
                        + "`MRP_Profile_Description`,"
                        + "`Lot_size`,"
                        + "`Procurement_type`,"
                        + "`MRP_Controller`,"
                        + "`MRP_Type`,"
                        + "`Purchasing_Group`,"
                        + "`Plant_sp_matl_status`,"
                        + "`Mat_Status_Description`,"
                        + "`Do_Not_Cost`,"
                        + "`Storage_loc_for_EP`,"
                        + "`Prod_stor_location`,"
                        + "`Production_unit`,"
                        + "`Created_On`,"
                        + "`Availability_check`,"
                        + "`Loading_Group`,"
                        + "`Tot_repl_lead_time`,"
                        + "`Underdely_tolerance`,"
                        + "`Unltd_Overdelivery`,"
                        + "`Overdely_tolerance`,"
                        + "`In_house_production`,"
                        + "`Prodn_Supervisor`,"
                        + "`Backflush`,"
                        + "`Individual_coll`,"
                        + "`Rounding_value`,"
                        + "`Base_Unit_of_Measure_1`,"
                        + "`Reorder_Point`,"
                        + "`Base_Unit_of_Measure_2`,"
                        + "`Assembly_scrap_%`,"
                        + "`GR_processing_time`,"
                        + "`Unit_of_issue`,"
                        + "`Valid_from`,"
                        + "`Batch_management_1`,"
                        + "`Valuation_Category`,"
                        + "`DF_at_plant_level`,"
                        + "`DF_at_client_level`,"
                        + "`Old_material_number`,"
                        + "`Order_Unit`,"
                        + "`Lab_Office`,"
                        + "`Lab_description`,"
                        + "`Gross_Weight`,"
                        + "`Weight_Unit_1`,"
                        + "`Net_Weight`,"
                        + "`Weight_Unit_2`,"
                        + "`Container_reqmts`,"
                        + "`Storage_conditions`,"
                        + "`Temp_conditions`,"
                        + "`Transportation_Group`,"
                        + "`Batch_management_2`,"
                        + "`Packaging_mat_type`,"
                        + "`X_plant_matl_status`,"
                        + "`Min_Rem_Shelf_Life`,"
                        + "`Total_shelf_life`,"
                        + "`Language_Key`,"
                        + "`Created_By`,"
                        + "`Safety_stock`,"
                        + "`Base_Unit_of_Measure_3`,"
                        + "`Fixed_lot_size`,"
                        + "`Base_Unit_of_Measure_4`,"
                        + "`Maximum_Lot_Size_1`,"
                        + "`Base_Unit_of_Measure_5`,"
                        + "`Minimum_Lot_Size_2`,"
                        + "`Base_Unit_of_Measure_6`,"
                        + "`Costing_Lot_Size`,"
                        + "`Base_Unit_of_Measure_7`,"
                        + "`Prod_Sched_Profile`,"
                        + "`Prod_Sched_Profile_Desc`,"
                        + "`Planned_Deliv_Time`,"
                        + "`Special_procurement`,"
                        + "`SP_Key_Description`,"
                        + "`ABC_Indicator`,"
                        + "`STime_period_profile`,"
                        + "`Comm_imp_code_no`,"
                        + "`Country_of_origin`,"
                        + "`SpecProcurem_Costing`,"
                        + "`Overhead_Group`,"
                        + "`TDS`,"
                        + "`Period_Indicator`"
                        + ")VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                SQL.setString(1, modelo.getMaterial());
                SQL.setString(2, modelo.getMaterial_Description());
                SQL.setString(3, modelo.getMaterial_Group());
                SQL.setString(4, modelo.getProfit_Center());
                SQL.setString(5, modelo.getProduct_Hierarchy());
                SQL.setString(6, modelo.getProduct_Hierarchy_Description());
                SQL.setString(7, modelo.getMaterial_Type());
                SQL.setString(8, modelo.getMaterial_type_descr());
                SQL.setString(9, modelo.getPlant());
                SQL.setString(10, modelo.getPlant_Name());
                SQL.setString(11, modelo.getExt_Material_Group());
                SQL.setString(12, modelo.getMatl_Grp_Pack_Matls());
                SQL.setString(13, modelo.getMGPM_Description());
                SQL.setString(14, modelo.getMRP_profile());
                SQL.setString(15, modelo.getMRP_Profile_Description());
                SQL.setString(16, modelo.getLot_size());
                SQL.setString(17, modelo.getProcurement_type());
                SQL.setString(18, modelo.getMRP_Controller());
                SQL.setString(19, modelo.getMRP_Type());
                SQL.setString(20, modelo.getPurchasing_Group());
                SQL.setString(21, modelo.getPlant_sp_matl_status());
                SQL.setString(22, modelo.getMat_Status_Description());
                SQL.setString(23, modelo.getDo_Not_Cost());
                SQL.setString(24, modelo.getStorage_loc_for_EP());
                SQL.setString(25, modelo.getProd_stor_location());
                SQL.setString(26, modelo.getProduction_unit());
                SQL.setString(27, modelo.getCreated_On());
                SQL.setString(28, modelo.getAvailability_check());
                SQL.setString(29, modelo.getLoading_Group());
                SQL.setString(30, modelo.getTot_repl_lead_time());
                SQL.setString(31, modelo.getUnderdely_tolerance());
                SQL.setString(32, modelo.getUnltd_Overdelivery());
                SQL.setString(33, modelo.getOverdely_tolerance());
                SQL.setString(34, modelo.getIn_house_production());
                SQL.setString(35, modelo.getProdn_Supervisor());
                SQL.setString(36, modelo.getBackflush());
                SQL.setString(37, modelo.getIndividual_coll());
                SQL.setString(38, modelo.getRounding_value());
                SQL.setString(39, modelo.getBase_Unit_of_Measure_1());
                SQL.setString(40, modelo.getReorder_Point());
                SQL.setString(41, modelo.getBase_Unit_of_Measure_2());
                SQL.setString(42, modelo.getAssembly_scrap_porcentaje());
                SQL.setString(43, modelo.getGR_processing_time());
                SQL.setString(44, modelo.getUnit_of_issue());
                SQL.setString(45, modelo.getValid_from());
                SQL.setString(46, modelo.getBatch_management_1());
                SQL.setString(47, modelo.getValuation_Category());
                SQL.setString(48, modelo.getDF_at_plant_level());
                SQL.setString(49, modelo.getDF_at_client_level());
                SQL.setString(50, modelo.getOld_material_number());
                SQL.setString(51, modelo.getOrder_Unit());
                SQL.setString(52, modelo.getLab_Office());
                SQL.setString(53, modelo.getLab_description());
                SQL.setString(54, modelo.getGross_Weight());
                SQL.setString(55, modelo.getWeight_Unit_1());
                SQL.setString(56, modelo.getNet_Weight());
                SQL.setString(57, modelo.getWeight_Unit_2());
                SQL.setString(58, modelo.getContainer_reqmts());
                SQL.setString(59, modelo.getStorage_conditions());
                SQL.setString(60, modelo.getTemp_conditions());
                SQL.setString(61, modelo.getTransportation_Group());
                SQL.setString(62, modelo.getBatch_management_2());
                SQL.setString(63, modelo.getPackaging_mat_type());
                SQL.setString(64, modelo.getX_plant_matl_status());
                SQL.setString(65, modelo.getMin_Rem_Shelf_Life());
                SQL.setString(66, modelo.getTotal_shelf_life());
                SQL.setString(67, modelo.getLanguage_Key());
                SQL.setString(68, modelo.getCreated_By());
                SQL.setString(69, modelo.getSafety_stock());
                SQL.setString(70, modelo.getBase_Unit_of_Measure_3());
                SQL.setString(71, modelo.getFixed_lot_size());
                SQL.setString(72, modelo.getBase_Unit_of_Measure_4());
                SQL.setString(73, modelo.getMaximum_Lot_Size_1());
                SQL.setString(74, modelo.getBase_Unit_of_Measure_5());
                SQL.setString(75, modelo.getMinimum_Lot_Size_2());
                SQL.setString(76, modelo.getBase_Unit_of_Measure_6());
                SQL.setString(77, modelo.getCosting_Lot_Size());
                SQL.setString(78, modelo.getBase_Unit_of_Measure_7());
                SQL.setString(79, modelo.getProd_Sched_Profile());
                SQL.setString(80, modelo.getProd_Sched_Profile_Desc());
                SQL.setString(81, modelo.getPlanned_Deliv_Time());
                SQL.setString(82, modelo.getSpecial_procurement());
                SQL.setString(83, modelo.getSP_Key_Description());
                SQL.setString(84, modelo.getABC_Indicator());
                SQL.setString(85, modelo.getSTime_period_profile());
                SQL.setString(86, modelo.getComm_imp_code_no());
                SQL.setString(87, modelo.getCountry_of_origin());
                SQL.setString(88, modelo.getSpecProcurem_Costing());
                SQL.setString(89, modelo.getOverhead_Group());
                SQL.setString(90, modelo.getTDS());
                SQL.setString(91, modelo.getPeriod_Indicator());
                if (SQL.executeUpdate() > 0) {
                    resul = true;
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

    public boolean Insert(LinkedList<ModeloMrpData> listModeloMrpDatas) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement("INSERT INTO `mrpdata`("
                        + "`Material`,"
                        + "`Material_Description`,"
                        + "`Material_Group`,"
                        + "`Profit_Center`,"
                        + "`Product_Hierarchy`,"
                        + "`Product_Hierarchy_Description`,"
                        + "`Material_Type`,"
                        + "`Material_type_descr`,"
                        + "`Plant`,"
                        + "`Plant_Name`,"
                        + "`Ext_Material_Group`,"
                        + "`Matl_Grp_Pack_Matls`,"
                        + "`MGPM_Description`,"
                        + "`MRP_profile`,"
                        + "`MRP_Profile_Description`,"
                        + "`Lot_size`,"
                        + "`Procurement_type`,"
                        + "`MRP_Controller`,"
                        + "`MRP_Type`,"
                        + "`Purchasing_Group`,"
                        + "`Plant_sp_matl_status`,"
                        + "`Mat_Status_Description`,"
                        + "`Do_Not_Cost`,"
                        + "`Storage_loc_for_EP`,"
                        + "`Prod_stor_location`,"
                        + "`Production_unit`,"
                        + "`Created_On`,"
                        + "`Availability_check`,"
                        + "`Loading_Group`,"
                        + "`Tot_repl_lead_time`,"
                        + "`Underdely_tolerance`,"
                        + "`Unltd_Overdelivery`,"
                        + "`Overdely_tolerance`,"
                        + "`In_house_production`,"
                        + "`Prodn_Supervisor`,"
                        + "`Backflush`,"
                        + "`Individual_coll`,"
                        + "`Rounding_value`,"
                        + "`Base_Unit_of_Measure_1`,"
                        + "`Reorder_Point`,"
                        + "`Base_Unit_of_Measure_2`,"
                        + "`Assembly_scrap_%`,"
                        + "`GR_processing_time`,"
                        + "`Unit_of_issue`,"
                        + "`Valid_from`,"
                        + "`Batch_management_1`,"
                        + "`Valuation_Category`,"
                        + "`DF_at_plant_level`,"
                        + "`DF_at_client_level`,"
                        + "`Old_material_number`,"
                        + "`Order_Unit`,"
                        + "`Lab_Office`,"
                        + "`Lab_description`,"
                        + "`Gross_Weight`,"
                        + "`Weight_Unit_1`,"
                        + "`Net_Weight`,"
                        + "`Weight_Unit_2`,"
                        + "`Container_reqmts`,"
                        + "`Storage_conditions`,"
                        + "`Temp_conditions`,"
                        + "`Transportation_Group`,"
                        + "`Batch_management_2`,"
                        + "`Packaging_mat_type`,"
                        + "`X_plant_matl_status`,"
                        + "`Min_Rem_Shelf_Life`,"
                        + "`Total_shelf_life`,"
                        + "`Language_Key`,"
                        + "`Created_By`,"
                        + "`Safety_stock`,"
                        + "`Base_Unit_of_Measure_3`,"
                        + "`Fixed_lot_size`,"
                        + "`Base_Unit_of_Measure_4`,"
                        + "`Maximum_Lot_Size_1`,"
                        + "`Base_Unit_of_Measure_5`,"
                        + "`Minimum_Lot_Size_2`,"
                        + "`Base_Unit_of_Measure_6`,"
                        + "`Costing_Lot_Size`,"
                        + "`Base_Unit_of_Measure_7`,"
                        + "`Prod_Sched_Profile`,"
                        + "`Prod_Sched_Profile_Desc`,"
                        + "`Planned_Deliv_Time`,"
                        + "`Special_procurement`,"
                        + "`SP_Key_Description`,"
                        + "`ABC_Indicator`,"
                        + "`STime_period_profile`,"
                        + "`Comm_imp_code_no`,"
                        + "`Country_of_origin`,"
                        + "`SpecProcurem_Costing`,"
                        + "`Overhead_Group`,"
                        + "`TDS`,"
                        + "`Period_Indicator`"
                        + ")VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                for (ModeloMrpData modelo : listModeloMrpDatas) {
                    SQL.setString(1, modelo.getMaterial());
                    SQL.setString(2, modelo.getMaterial_Description());
                    SQL.setString(3, modelo.getMaterial_Group());
                    SQL.setString(4, modelo.getProfit_Center());
                    SQL.setString(5, modelo.getProduct_Hierarchy());
                    SQL.setString(6, modelo.getProduct_Hierarchy_Description());
                    SQL.setString(7, modelo.getMaterial_Type());
                    SQL.setString(8, modelo.getMaterial_type_descr());
                    SQL.setString(9, modelo.getPlant());
                    SQL.setString(10, modelo.getPlant_Name());
                    SQL.setString(11, modelo.getExt_Material_Group());
                    SQL.setString(12, modelo.getMatl_Grp_Pack_Matls());
                    SQL.setString(13, modelo.getMGPM_Description());
                    SQL.setString(14, modelo.getMRP_profile());
                    SQL.setString(15, modelo.getMRP_Profile_Description());
                    SQL.setString(16, modelo.getLot_size());
                    SQL.setString(17, modelo.getProcurement_type());
                    SQL.setString(18, modelo.getMRP_Controller());
                    SQL.setString(19, modelo.getMRP_Type());
                    SQL.setString(20, modelo.getPurchasing_Group());
                    SQL.setString(21, modelo.getPlant_sp_matl_status());
                    SQL.setString(22, modelo.getMat_Status_Description());
                    SQL.setString(23, modelo.getDo_Not_Cost());
                    SQL.setString(24, modelo.getStorage_loc_for_EP());
                    SQL.setString(25, modelo.getProd_stor_location());
                    SQL.setString(26, modelo.getProduction_unit());
                    SQL.setString(27, modelo.getCreated_On());
                    SQL.setString(28, modelo.getAvailability_check());
                    SQL.setString(29, modelo.getLoading_Group());
                    SQL.setString(30, modelo.getTot_repl_lead_time());
                    SQL.setString(31, modelo.getUnderdely_tolerance());
                    SQL.setString(32, modelo.getUnltd_Overdelivery());
                    SQL.setString(33, modelo.getOverdely_tolerance());
                    SQL.setString(34, modelo.getIn_house_production());
                    SQL.setString(35, modelo.getProdn_Supervisor());
                    SQL.setString(36, modelo.getBackflush());
                    SQL.setString(37, modelo.getIndividual_coll());
                    SQL.setString(38, modelo.getRounding_value());
                    SQL.setString(39, modelo.getBase_Unit_of_Measure_1());
                    SQL.setString(40, modelo.getReorder_Point());
                    SQL.setString(41, modelo.getBase_Unit_of_Measure_2());
                    SQL.setString(42, modelo.getAssembly_scrap_porcentaje());
                    SQL.setString(43, modelo.getGR_processing_time());
                    SQL.setString(44, modelo.getUnit_of_issue());
                    SQL.setString(45, modelo.getValid_from());
                    SQL.setString(46, modelo.getBatch_management_1());
                    SQL.setString(47, modelo.getValuation_Category());
                    SQL.setString(48, modelo.getDF_at_plant_level());
                    SQL.setString(49, modelo.getDF_at_client_level());
                    SQL.setString(50, modelo.getOld_material_number());
                    SQL.setString(51, modelo.getOrder_Unit());
                    SQL.setString(52, modelo.getLab_Office());
                    SQL.setString(53, modelo.getLab_description());
                    SQL.setString(54, modelo.getGross_Weight());
                    SQL.setString(55, modelo.getWeight_Unit_1());
                    SQL.setString(56, modelo.getNet_Weight());
                    SQL.setString(57, modelo.getWeight_Unit_2());
                    SQL.setString(58, modelo.getContainer_reqmts());
                    SQL.setString(59, modelo.getStorage_conditions());
                    SQL.setString(60, modelo.getTemp_conditions());
                    SQL.setString(61, modelo.getTransportation_Group());
                    SQL.setString(62, modelo.getBatch_management_2());
                    SQL.setString(63, modelo.getPackaging_mat_type());
                    SQL.setString(64, modelo.getX_plant_matl_status());
                    SQL.setString(65, modelo.getMin_Rem_Shelf_Life());
                    SQL.setString(66, modelo.getTotal_shelf_life());
                    SQL.setString(67, modelo.getLanguage_Key());
                    SQL.setString(68, modelo.getCreated_By());
                    SQL.setString(69, modelo.getSafety_stock());
                    SQL.setString(70, modelo.getBase_Unit_of_Measure_3());
                    SQL.setString(71, modelo.getFixed_lot_size());
                    SQL.setString(72, modelo.getBase_Unit_of_Measure_4());
                    SQL.setString(73, modelo.getMaximum_Lot_Size_1());
                    SQL.setString(74, modelo.getBase_Unit_of_Measure_5());
                    SQL.setString(75, modelo.getMinimum_Lot_Size_2());
                    SQL.setString(76, modelo.getBase_Unit_of_Measure_6());
                    SQL.setString(77, modelo.getCosting_Lot_Size());
                    SQL.setString(78, modelo.getBase_Unit_of_Measure_7());
                    SQL.setString(79, modelo.getProd_Sched_Profile());
                    SQL.setString(80, modelo.getProd_Sched_Profile_Desc());
                    SQL.setString(81, modelo.getPlanned_Deliv_Time());
                    SQL.setString(82, modelo.getSpecial_procurement());
                    SQL.setString(83, modelo.getSP_Key_Description());
                    SQL.setString(84, modelo.getABC_Indicator());
                    SQL.setString(85, modelo.getSTime_period_profile());
                    SQL.setString(86, modelo.getComm_imp_code_no());
                    SQL.setString(87, modelo.getCountry_of_origin());
                    SQL.setString(88, modelo.getSpecProcurem_Costing());
                    SQL.setString(89, modelo.getOverhead_Group());
                    SQL.setString(90, modelo.getTDS());
                    SQL.setString(91, modelo.getPeriod_Indicator());
                    if (SQL.executeUpdate() > 0) {
                        resul = true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean Insert(String Sql) {
        boolean resul = false;
        try {
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            PreparedStatement SQL = null;
            try {
                SQL = con.prepareStatement(Sql);
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Insert " + e);
        }
        return resul;
    }

    public boolean Update(ModeloMrpData modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE mrpdata SE"
                    + "Material = ?,"
                    + "Material_Description = ?,"
                    + "Material_Group = ?,"
                    + "Profit_Center = ?,"
                    + "Product_Hierarchy = ?,"
                    + "Product_Hierarchy_Description = ?,"
                    + "Material_Type = ?,"
                    + "Material_type_descr = ?,"
                    + "Plant = ?,"
                    + "Plant_Name = ?,"
                    + "Ext_Material_Group = ?,"
                    + "Matl_Grp_Pack_Matls = ?,"
                    + "MGPM_Description = ?,"
                    + "MRP_profile = ?,"
                    + "MRP_Profile_Description = ?,"
                    + "Lot_size = ?,"
                    + "Procurement_type = ?,"
                    + "MRP_Controller = ?,"
                    + "MRP_Type = ?,"
                    + "Purchasing_Group = ?,"
                    + "Plant_sp_matl_status = ?,"
                    + "Mat_Status_Description = ?,"
                    + "Do_Not_Cost = ?,"
                    + "Storage_loc_for_EP = ?,"
                    + "Prod_stor_location = ?,"
                    + "Production_unit = ?,"
                    + "Created_On = ?,"
                    + "Availability_check = ?,"
                    + "Loading_Group = ?,"
                    + "Tot_repl_lead_time = ?,"
                    + "Underdely_tolerance = ?,"
                    + "Unltd_Overdelivery = ?,"
                    + "Overdely_tolerance = ?,"
                    + "In_house_production = ?,"
                    + "Prodn_Supervisor = ?,"
                    + "Backflush = ?,"
                    + "Individual_coll = ?,"
                    + "Rounding_value = ?,"
                    + "Base_Unit_of_Measure_1 = ?,"
                    + "Reorder_Point = ?,"
                    + "Base_Unit_of_Measure_2 = ?,"
                    + "Assembly_scrap_% = ?,"
                    + "GR_processing_time = ?,"
                    + "Unit_of_issue = ?,"
                    + "Valid_from = ?,"
                    + "Batch_management_1 = ?,"
                    + "Valuation_Category = ?,"
                    + "DF_at_plant_level = ?,"
                    + "DF_at_client_level = ?,"
                    + "Old_material_number = ?,"
                    + "Order_Unit = ?,"
                    + "Lab_Office = ?,"
                    + "Lab_description = ?,"
                    + "Gross_Weight = ?,"
                    + "Weight_Unit_1 = ?,"
                    + "Net_Weight = ?,"
                    + "Weight_Unit_2 = ?,"
                    + "Container_reqmts = ?,"
                    + "Storage_conditions = ?,"
                    + "Temp_conditions = ?,"
                    + "Transportation_Group = ?,"
                    + "Batch_management_2 = ?,"
                    + "Packaging_mat_type = ?,"
                    + "X_plant_matl_status = ?,"
                    + "Min_Rem_Shelf_Life = ?,"
                    + "Total_shelf_life = ?,"
                    + "Language_Key = ?,"
                    + "Created_By = ?,"
                    + "Safety_stock = ?,"
                    + "Base_Unit_of_Measure_3 = ?,"
                    + "Fixed_lot_size = ?,"
                    + "Base_Unit_of_Measure_4 = ?,"
                    + "Maximum_Lot_Size_1 = ?,"
                    + "Base_Unit_of_Measure_5 = ?,"
                    + "Minimum_Lot_Size_2 = ?,"
                    + "Base_Unit_of_Measure_6 = ?,"
                    + "Costing_Lot_Size = ?,"
                    + "Base_Unit_of_Measure_7 = ?,"
                    + "Prod_Sched_Profile = ?,"
                    + "Prod_Sched_Profile_Desc = ?,"
                    + "Planned_Deliv_Time = ?,"
                    + "Special_procurement = ?,"
                    + "SP_Key_Description = ?,"
                    + "ABC_Indicator = ?,"
                    + "STime_period_profile = ?,"
                    + "Comm_imp_code_no = ?,"
                    + "Country_of_origin = ?,"
                    + "SpecProcurem_Costing = ?,"
                    + "Overhead_Group = ?,"
                    + "TDS = ?,"
                    + "Period_Indicator = ?,"
                    + "WHERE "
                    + "Id = ?");
            SQL.setString(1, modelo.getMaterial());
            SQL.setString(2, modelo.getMaterial_Description());
            SQL.setString(3, modelo.getMaterial_Group());
            SQL.setString(4, modelo.getProfit_Center());
            SQL.setString(5, modelo.getProduct_Hierarchy());
            SQL.setString(6, modelo.getProduct_Hierarchy_Description());
            SQL.setString(7, modelo.getMaterial_Type());
            SQL.setString(8, modelo.getMaterial_type_descr());
            SQL.setString(9, modelo.getPlant());
            SQL.setString(10, modelo.getPlant_Name());
            SQL.setString(11, modelo.getExt_Material_Group());
            SQL.setString(12, modelo.getMatl_Grp_Pack_Matls());
            SQL.setString(13, modelo.getMGPM_Description());
            SQL.setString(14, modelo.getMRP_profile());
            SQL.setString(15, modelo.getMRP_Profile_Description());
            SQL.setString(16, modelo.getLot_size());
            SQL.setString(17, modelo.getProcurement_type());
            SQL.setString(18, modelo.getMRP_Controller());
            SQL.setString(19, modelo.getMRP_Type());
            SQL.setString(20, modelo.getPurchasing_Group());
            SQL.setString(21, modelo.getPlant_sp_matl_status());
            SQL.setString(22, modelo.getMat_Status_Description());
            SQL.setString(23, modelo.getDo_Not_Cost());
            SQL.setString(24, modelo.getStorage_loc_for_EP());
            SQL.setString(25, modelo.getProd_stor_location());
            SQL.setString(26, modelo.getProduction_unit());
            SQL.setString(27, modelo.getCreated_On());
            SQL.setString(28, modelo.getAvailability_check());
            SQL.setString(29, modelo.getLoading_Group());
            SQL.setString(30, modelo.getTot_repl_lead_time());
            SQL.setString(31, modelo.getUnderdely_tolerance());
            SQL.setString(32, modelo.getUnltd_Overdelivery());
            SQL.setString(33, modelo.getOverdely_tolerance());
            SQL.setString(34, modelo.getIn_house_production());
            SQL.setString(35, modelo.getProdn_Supervisor());
            SQL.setString(36, modelo.getBackflush());
            SQL.setString(37, modelo.getIndividual_coll());
            SQL.setString(38, modelo.getRounding_value());
            SQL.setString(39, modelo.getBase_Unit_of_Measure_1());
            SQL.setString(40, modelo.getReorder_Point());
            SQL.setString(41, modelo.getBase_Unit_of_Measure_2());
            SQL.setString(42, modelo.getAssembly_scrap_porcentaje());
            SQL.setString(43, modelo.getGR_processing_time());
            SQL.setString(44, modelo.getUnit_of_issue());
            SQL.setString(45, modelo.getValid_from());
            SQL.setString(46, modelo.getBatch_management_1());
            SQL.setString(47, modelo.getValuation_Category());
            SQL.setString(48, modelo.getDF_at_plant_level());
            SQL.setString(49, modelo.getDF_at_client_level());
            SQL.setString(50, modelo.getOld_material_number());
            SQL.setString(51, modelo.getOrder_Unit());
            SQL.setString(52, modelo.getLab_Office());
            SQL.setString(53, modelo.getLab_description());
            SQL.setString(54, modelo.getGross_Weight());
            SQL.setString(55, modelo.getWeight_Unit_1());
            SQL.setString(56, modelo.getNet_Weight());
            SQL.setString(57, modelo.getWeight_Unit_2());
            SQL.setString(58, modelo.getContainer_reqmts());
            SQL.setString(59, modelo.getStorage_conditions());
            SQL.setString(60, modelo.getTemp_conditions());
            SQL.setString(61, modelo.getTransportation_Group());
            SQL.setString(62, modelo.getBatch_management_2());
            SQL.setString(63, modelo.getPackaging_mat_type());
            SQL.setString(64, modelo.getX_plant_matl_status());
            SQL.setString(65, modelo.getMin_Rem_Shelf_Life());
            SQL.setString(66, modelo.getTotal_shelf_life());
            SQL.setString(67, modelo.getLanguage_Key());
            SQL.setString(68, modelo.getCreated_By());
            SQL.setString(69, modelo.getSafety_stock());
            SQL.setString(70, modelo.getBase_Unit_of_Measure_3());
            SQL.setString(71, modelo.getFixed_lot_size());
            SQL.setString(72, modelo.getBase_Unit_of_Measure_4());
            SQL.setString(73, modelo.getMaximum_Lot_Size_1());
            SQL.setString(74, modelo.getBase_Unit_of_Measure_5());
            SQL.setString(75, modelo.getMinimum_Lot_Size_2());
            SQL.setString(76, modelo.getBase_Unit_of_Measure_6());
            SQL.setString(77, modelo.getCosting_Lot_Size());
            SQL.setString(78, modelo.getBase_Unit_of_Measure_7());
            SQL.setString(79, modelo.getProd_Sched_Profile());
            SQL.setString(80, modelo.getProd_Sched_Profile_Desc());
            SQL.setString(81, modelo.getPlanned_Deliv_Time());
            SQL.setString(82, modelo.getSpecial_procurement());
            SQL.setString(83, modelo.getSP_Key_Description());
            SQL.setString(84, modelo.getABC_Indicator());
            SQL.setString(85, modelo.getSTime_period_profile());
            SQL.setString(86, modelo.getComm_imp_code_no());
            SQL.setString(87, modelo.getCountry_of_origin());
            SQL.setString(88, modelo.getSpecProcurem_Costing());
            SQL.setString(89, modelo.getOverhead_Group());
            SQL.setString(90, modelo.getTDS());
            SQL.setString(91, modelo.getPeriod_Indicator());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
        }
        return resul;
    }

    public boolean Update(LinkedList<ModeloMrpData> listModeloMrpDatas) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE mrpdata SE"
                    + "Material = ?,"
                    + "Material_Description = ?,"
                    + "Material_Group = ?,"
                    + "Profit_Center = ?,"
                    + "Product_Hierarchy = ?,"
                    + "Product_Hierarchy_Description = ?,"
                    + "Material_Type = ?,"
                    + "Material_type_descr = ?,"
                    + "Plant = ?,"
                    + "Plant_Name = ?,"
                    + "Ext_Material_Group = ?,"
                    + "Matl_Grp_Pack_Matls = ?,"
                    + "MGPM_Description = ?,"
                    + "MRP_profile = ?,"
                    + "MRP_Profile_Description = ?,"
                    + "Lot_size = ?,"
                    + "Procurement_type = ?,"
                    + "MRP_Controller = ?,"
                    + "MRP_Type = ?,"
                    + "Purchasing_Group = ?,"
                    + "Plant_sp_matl_status = ?,"
                    + "Mat_Status_Description = ?,"
                    + "Do_Not_Cost = ?,"
                    + "Storage_loc_for_EP = ?,"
                    + "Prod_stor_location = ?,"
                    + "Production_unit = ?,"
                    + "Created_On = ?,"
                    + "Availability_check = ?,"
                    + "Loading_Group = ?,"
                    + "Tot_repl_lead_time = ?,"
                    + "Underdely_tolerance = ?,"
                    + "Unltd_Overdelivery = ?,"
                    + "Overdely_tolerance = ?,"
                    + "In_house_production = ?,"
                    + "Prodn_Supervisor = ?,"
                    + "Backflush = ?,"
                    + "Individual_coll = ?,"
                    + "Rounding_value = ?,"
                    + "Base_Unit_of_Measure_1 = ?,"
                    + "Reorder_Point = ?,"
                    + "Base_Unit_of_Measure_2 = ?,"
                    + "Assembly_scrap_% = ?,"
                    + "GR_processing_time = ?,"
                    + "Unit_of_issue = ?,"
                    + "Valid_from = ?,"
                    + "Batch_management_1 = ?,"
                    + "Valuation_Category = ?,"
                    + "DF_at_plant_level = ?,"
                    + "DF_at_client_level = ?,"
                    + "Old_material_number = ?,"
                    + "Order_Unit = ?,"
                    + "Lab_Office = ?,"
                    + "Lab_description = ?,"
                    + "Gross_Weight = ?,"
                    + "Weight_Unit_1 = ?,"
                    + "Net_Weight = ?,"
                    + "Weight_Unit_2 = ?,"
                    + "Container_reqmts = ?,"
                    + "Storage_conditions = ?,"
                    + "Temp_conditions = ?,"
                    + "Transportation_Group = ?,"
                    + "Batch_management_2 = ?,"
                    + "Packaging_mat_type = ?,"
                    + "X_plant_matl_status = ?,"
                    + "Min_Rem_Shelf_Life = ?,"
                    + "Total_shelf_life = ?,"
                    + "Language_Key = ?,"
                    + "Created_By = ?,"
                    + "Safety_stock = ?,"
                    + "Base_Unit_of_Measure_3 = ?,"
                    + "Fixed_lot_size = ?,"
                    + "Base_Unit_of_Measure_4 = ?,"
                    + "Maximum_Lot_Size_1 = ?,"
                    + "Base_Unit_of_Measure_5 = ?,"
                    + "Minimum_Lot_Size_2 = ?,"
                    + "Base_Unit_of_Measure_6 = ?,"
                    + "Costing_Lot_Size = ?,"
                    + "Base_Unit_of_Measure_7 = ?,"
                    + "Prod_Sched_Profile = ?,"
                    + "Prod_Sched_Profile_Desc = ?,"
                    + "Planned_Deliv_Time = ?,"
                    + "Special_procurement = ?,"
                    + "SP_Key_Description = ?,"
                    + "ABC_Indicator = ?,"
                    + "STime_period_profile = ?,"
                    + "Comm_imp_code_no = ?,"
                    + "Country_of_origin = ?,"
                    + "SpecProcurem_Costing = ?,"
                    + "Overhead_Group = ?,"
                    + "TDS = ?,"
                    + "Period_Indicator = ?,"
                    + "WHERE "
                    + "Id = ?");
            for (ModeloMrpData modelo : listModeloMrpDatas) {
                SQL.setString(1, modelo.getMaterial());
                SQL.setString(2, modelo.getMaterial_Description());
                SQL.setString(3, modelo.getMaterial_Group());
                SQL.setString(4, modelo.getProfit_Center());
                SQL.setString(5, modelo.getProduct_Hierarchy());
                SQL.setString(6, modelo.getProduct_Hierarchy_Description());
                SQL.setString(7, modelo.getMaterial_Type());
                SQL.setString(8, modelo.getMaterial_type_descr());
                SQL.setString(9, modelo.getPlant());
                SQL.setString(10, modelo.getPlant_Name());
                SQL.setString(11, modelo.getExt_Material_Group());
                SQL.setString(12, modelo.getMatl_Grp_Pack_Matls());
                SQL.setString(13, modelo.getMGPM_Description());
                SQL.setString(14, modelo.getMRP_profile());
                SQL.setString(15, modelo.getMRP_Profile_Description());
                SQL.setString(16, modelo.getLot_size());
                SQL.setString(17, modelo.getProcurement_type());
                SQL.setString(18, modelo.getMRP_Controller());
                SQL.setString(19, modelo.getMRP_Type());
                SQL.setString(20, modelo.getPurchasing_Group());
                SQL.setString(21, modelo.getPlant_sp_matl_status());
                SQL.setString(22, modelo.getMat_Status_Description());
                SQL.setString(23, modelo.getDo_Not_Cost());
                SQL.setString(24, modelo.getStorage_loc_for_EP());
                SQL.setString(25, modelo.getProd_stor_location());
                SQL.setString(26, modelo.getProduction_unit());
                SQL.setString(27, modelo.getCreated_On());
                SQL.setString(28, modelo.getAvailability_check());
                SQL.setString(29, modelo.getLoading_Group());
                SQL.setString(30, modelo.getTot_repl_lead_time());
                SQL.setString(31, modelo.getUnderdely_tolerance());
                SQL.setString(32, modelo.getUnltd_Overdelivery());
                SQL.setString(33, modelo.getOverdely_tolerance());
                SQL.setString(34, modelo.getIn_house_production());
                SQL.setString(35, modelo.getProdn_Supervisor());
                SQL.setString(36, modelo.getBackflush());
                SQL.setString(37, modelo.getIndividual_coll());
                SQL.setString(38, modelo.getRounding_value());
                SQL.setString(39, modelo.getBase_Unit_of_Measure_1());
                SQL.setString(40, modelo.getReorder_Point());
                SQL.setString(41, modelo.getBase_Unit_of_Measure_2());
                SQL.setString(42, modelo.getAssembly_scrap_porcentaje());
                SQL.setString(43, modelo.getGR_processing_time());
                SQL.setString(44, modelo.getUnit_of_issue());
                SQL.setString(45, modelo.getValid_from());
                SQL.setString(46, modelo.getBatch_management_1());
                SQL.setString(47, modelo.getValuation_Category());
                SQL.setString(48, modelo.getDF_at_plant_level());
                SQL.setString(49, modelo.getDF_at_client_level());
                SQL.setString(50, modelo.getOld_material_number());
                SQL.setString(51, modelo.getOrder_Unit());
                SQL.setString(52, modelo.getLab_Office());
                SQL.setString(53, modelo.getLab_description());
                SQL.setString(54, modelo.getGross_Weight());
                SQL.setString(55, modelo.getWeight_Unit_1());
                SQL.setString(56, modelo.getNet_Weight());
                SQL.setString(57, modelo.getWeight_Unit_2());
                SQL.setString(58, modelo.getContainer_reqmts());
                SQL.setString(59, modelo.getStorage_conditions());
                SQL.setString(60, modelo.getTemp_conditions());
                SQL.setString(61, modelo.getTransportation_Group());
                SQL.setString(62, modelo.getBatch_management_2());
                SQL.setString(63, modelo.getPackaging_mat_type());
                SQL.setString(64, modelo.getX_plant_matl_status());
                SQL.setString(65, modelo.getMin_Rem_Shelf_Life());
                SQL.setString(66, modelo.getTotal_shelf_life());
                SQL.setString(67, modelo.getLanguage_Key());
                SQL.setString(68, modelo.getCreated_By());
                SQL.setString(69, modelo.getSafety_stock());
                SQL.setString(70, modelo.getBase_Unit_of_Measure_3());
                SQL.setString(71, modelo.getFixed_lot_size());
                SQL.setString(72, modelo.getBase_Unit_of_Measure_4());
                SQL.setString(73, modelo.getMaximum_Lot_Size_1());
                SQL.setString(74, modelo.getBase_Unit_of_Measure_5());
                SQL.setString(75, modelo.getMinimum_Lot_Size_2());
                SQL.setString(76, modelo.getBase_Unit_of_Measure_6());
                SQL.setString(77, modelo.getCosting_Lot_Size());
                SQL.setString(78, modelo.getBase_Unit_of_Measure_7());
                SQL.setString(79, modelo.getProd_Sched_Profile());
                SQL.setString(80, modelo.getProd_Sched_Profile_Desc());
                SQL.setString(81, modelo.getPlanned_Deliv_Time());
                SQL.setString(82, modelo.getSpecial_procurement());
                SQL.setString(83, modelo.getSP_Key_Description());
                SQL.setString(84, modelo.getABC_Indicator());
                SQL.setString(85, modelo.getSTime_period_profile());
                SQL.setString(86, modelo.getComm_imp_code_no());
                SQL.setString(87, modelo.getCountry_of_origin());
                SQL.setString(88, modelo.getSpecProcurem_Costing());
                SQL.setString(89, modelo.getOverhead_Group());
                SQL.setString(90, modelo.getTDS());
                SQL.setString(91, modelo.getPeriod_Indicator());
                if (SQL.executeUpdate() > 0) {
                    resul = true;
                }
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
        }
        return resul;
    }

    public boolean Delete(ModeloMrpData modelo) {
        boolean resul = false;
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("DELETE FROM `mrpdata` WHERE `Id` = ?;");
            SQL.setInt(1, modelo.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
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
        try {
            SQL = con.prepareStatement("DELETE FROM `mrpdata`;");
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Delete " + e);
        }
        return resul;
    }

    public LinkedList<ModeloMrpData> Select() {
        LinkedList<ModeloMrpData> modeloMrpDatas = new LinkedList<ModeloMrpData>();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Material`,"
                    + "`Material_Description`,"
                    + "`Material_Group`,"
                    + "`Profit_Center`,"
                    + "`Product_Hierarchy`,"
                    + "`Product_Hierarchy_Description`,"
                    + "`Material_Type`,"
                    + "`Material_type_descr`,"
                    + "`Plant`,"
                    + "`Plant_Name`,"
                    + "`Ext_Material_Group`,"
                    + "`Matl_Grp_Pack_Matls`,"
                    + "`MGPM_Description`,"
                    + "`MRP_profile`,"
                    + "`MRP_Profile_Description`,"
                    + "`Lot_size`,"
                    + "`Procurement_type`,"
                    + "`MRP_Controller`,"
                    + "`MRP_Type`,"
                    + "`Purchasing_Group`,"
                    + "`Plant_sp_matl_status`,"
                    + "`Mat_Status_Description`,"
                    + "`Do_Not_Cost`,"
                    + "`Storage_loc_for_EP`,"
                    + "`Prod_stor_location`,"
                    + "`Production_unit`,"
                    + "`Created_On`,"
                    + "`Availability_check`,"
                    + "`Loading_Group`,"
                    + "`Tot_repl_lead_time`,"
                    + "`Underdely_tolerance`,"
                    + "`Unltd_Overdelivery`,"
                    + "`Overdely_tolerance`,"
                    + "`In_house_production`,"
                    + "`Prodn_Supervisor`,"
                    + "`Backflush`,"
                    + "`Individual_coll`,"
                    + "`Rounding_value`,"
                    + "`Base_Unit_of_Measure_1`,"
                    + "`Reorder_Point`,"
                    + "`Base_Unit_of_Measure_2`,"
                    + "`Assembly_scrap_%`,"
                    + "`GR_processing_time`,"
                    + "`Unit_of_issue`,"
                    + "`Valid_from`,"
                    + "`Batch_management_1`,"
                    + "`Valuation_Category`,"
                    + "`DF_at_plant_level`,"
                    + "`DF_at_client_level`,"
                    + "`Old_material_number`,"
                    + "`Order_Unit`,"
                    + "`Lab_Office`,"
                    + "`Lab_description`,"
                    + "`Gross_Weight`,"
                    + "`Weight_Unit_1`,"
                    + "`Net_Weight`,"
                    + "`Weight_Unit_2`,"
                    + "`Container_reqmts`,"
                    + "`Storage_conditions`,"
                    + "`Temp_conditions`,"
                    + "`Transportation_Group`,"
                    + "`Batch_management_2`,"
                    + "`Packaging_mat_type`,"
                    + "`X_plant_matl_status`,"
                    + "`Min_Rem_Shelf_Life`,"
                    + "`Total_shelf_life`,"
                    + "`Language_Key`,"
                    + "`Created_By`,"
                    + "`Safety_stock`,"
                    + "`Base_Unit_of_Measure_3`,"
                    + "`Fixed_lot_size`,"
                    + "`Base_Unit_of_Measure_4`,"
                    + "`Maximum_Lot_Size_1`,"
                    + "`Base_Unit_of_Measure_5`,"
                    + "`Minimum_Lot_Size_2`,"
                    + "`Base_Unit_of_Measure_6`,"
                    + "`Costing_Lot_Size`,"
                    + "`Base_Unit_of_Measure_7`,"
                    + "`Prod_Sched_Profile`,"
                    + "`Prod_Sched_Profile_Desc`,"
                    + "`Planned_Deliv_Time`,"
                    + "`Special_procurement`,"
                    + "`SP_Key_Description`,"
                    + "`ABC_Indicator`,"
                    + "`STime_period_profile`,"
                    + "`Comm_imp_code_no`,"
                    + "`Country_of_origin`,"
                    + "`SpecProcurem_Costing`,"
                    + "`Overhead_Group`,"
                    + "`TDS`,"
                    + "`Period_Indicator`"
                    + "FROM "
                    + "`mrpdata`;");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                ModeloMrpData modeloMrpData = new ModeloMrpData();
                modeloMrpData.setId(res.getInt("id"));
                modeloMrpData.setMaterial(res.getString("Material"));
                modeloMrpData.setMaterial_Description(res.getString("Material_Description"));
                modeloMrpData.setMaterial_Group(res.getString("Material_Group"));
                modeloMrpData.setProfit_Center(res.getString("Profit_Center"));
                modeloMrpData.setProduct_Hierarchy(res.getString("Product_Hierarchy"));
                modeloMrpData.setProduct_Hierarchy_Description(res.getString("Product_Hierarchy_Description"));
                modeloMrpData.setMaterial_Type(res.getString("Material_Type"));
                modeloMrpData.setMaterial_type_descr(res.getString("Material_type_descr"));
                modeloMrpData.setPlant(res.getString("Plant"));
                modeloMrpData.setPlant_Name(res.getString("Plant_Name"));
                modeloMrpData.setExt_Material_Group(res.getString("Ext_Material_Group"));
                modeloMrpData.setMatl_Grp_Pack_Matls(res.getString("Matl_Grp_Pack_Matls"));
                modeloMrpData.setMGPM_Description(res.getString("MGPM_Description"));
                modeloMrpData.setMRP_profile(res.getString("MRP_profile"));
                modeloMrpData.setMRP_Profile_Description(res.getString("MRP_Profile_Description"));
                modeloMrpData.setLot_size(res.getString("Lot_size"));
                modeloMrpData.setProcurement_type(res.getString("Procurement_type"));
                modeloMrpData.setMRP_Controller(res.getString("MRP_Controller"));
                modeloMrpData.setMRP_Type(res.getString("MRP_Type"));
                modeloMrpData.setPurchasing_Group(res.getString("Purchasing_Group"));
                modeloMrpData.setPlant_sp_matl_status(res.getString("Plant_sp_matl_status"));
                modeloMrpData.setMat_Status_Description(res.getString("Mat_Status_Description"));
                modeloMrpData.setDo_Not_Cost(res.getString("Do_Not_Cost"));
                modeloMrpData.setStorage_loc_for_EP(res.getString("Storage_loc_for_EP"));
                modeloMrpData.setProd_stor_location(res.getString("Prod_stor_location"));
                modeloMrpData.setProduction_unit(res.getString("Production_unit"));
                modeloMrpData.setCreated_On(res.getString("Created_On"));
                modeloMrpData.setAvailability_check(res.getString("Availability_check"));
                modeloMrpData.setLoading_Group(res.getString("Loading_Group"));
                modeloMrpData.setTot_repl_lead_time(res.getString("Tot_repl_lead_time"));
                modeloMrpData.setUnderdely_tolerance(res.getString("Underdely_tolerance"));
                modeloMrpData.setUnltd_Overdelivery(res.getString("Unltd_Overdelivery"));
                modeloMrpData.setOverdely_tolerance(res.getString("Overdely_tolerance"));
                modeloMrpData.setIn_house_production(res.getString("In_house_production"));
                modeloMrpData.setProdn_Supervisor(res.getString("Prodn_Supervisor"));
                modeloMrpData.setBackflush(res.getString("Backflush"));
                modeloMrpData.setIndividual_coll(res.getString("Individual_coll"));
                modeloMrpData.setRounding_value(res.getString("Rounding_value"));
                modeloMrpData.setBase_Unit_of_Measure_1(res.getString("Base_Unit_of_Measure_1"));
                modeloMrpData.setReorder_Point(res.getString("Reorder_Point"));
                modeloMrpData.setBase_Unit_of_Measure_2(res.getString("Base_Unit_of_Measure_2"));
                modeloMrpData.setAssembly_scrap_porcentaje(res.getString("Assembly_scrap_%"));
                modeloMrpData.setGR_processing_time(res.getString("GR_processing_time"));
                modeloMrpData.setUnit_of_issue(res.getString("Unit_of_issue"));
                modeloMrpData.setValid_from(res.getString("Valid_from"));
                modeloMrpData.setBatch_management_1(res.getString("Batch_management_1"));
                modeloMrpData.setValuation_Category(res.getString("Valuation_Category"));
                modeloMrpData.setDF_at_plant_level(res.getString("DF_at_plant_level"));
                modeloMrpData.setDF_at_client_level(res.getString("DF_at_client_level"));
                modeloMrpData.setOld_material_number(res.getString("Old_material_number"));
                modeloMrpData.setOrder_Unit(res.getString("Order_Unit"));
                modeloMrpData.setLab_Office(res.getString("Lab_Office"));
                modeloMrpData.setLab_description(res.getString("Lab_description"));
                modeloMrpData.setGross_Weight(res.getString("Gross_Weight"));
                modeloMrpData.setWeight_Unit_1(res.getString("Weight_Unit_1"));
                modeloMrpData.setNet_Weight(res.getString("Net_Weight"));
                modeloMrpData.setWeight_Unit_2(res.getString("Weight_Unit_2"));
                modeloMrpData.setContainer_reqmts(res.getString("Container_reqmts"));
                modeloMrpData.setStorage_conditions(res.getString("Storage_conditions"));
                modeloMrpData.setTemp_conditions(res.getString("Temp_conditions"));
                modeloMrpData.setTransportation_Group(res.getString("Transportation_Group"));
                modeloMrpData.setBatch_management_2(res.getString("Batch_management_2"));
                modeloMrpData.setPackaging_mat_type(res.getString("Packaging_mat_type"));
                modeloMrpData.setX_plant_matl_status(res.getString("X_plant_matl_status"));
                modeloMrpData.setMin_Rem_Shelf_Life(res.getString("Min_Rem_Shelf_Life"));
                modeloMrpData.setTotal_shelf_life(res.getString("Total_shelf_life"));
                modeloMrpData.setLanguage_Key(res.getString("Language_Key"));
                modeloMrpData.setCreated_By(res.getString("Created_By"));
                modeloMrpData.setSafety_stock(res.getString("Safety_stock"));
                modeloMrpData.setBase_Unit_of_Measure_3(res.getString("Base_Unit_of_Measure_3"));
                modeloMrpData.setFixed_lot_size(res.getString("Fixed_lot_size"));
                modeloMrpData.setBase_Unit_of_Measure_4(res.getString("Base_Unit_of_Measure_4"));
                modeloMrpData.setMaximum_Lot_Size_1(res.getString("Maximum_Lot_Size_1"));
                modeloMrpData.setBase_Unit_of_Measure_5(res.getString("Base_Unit_of_Measure_5"));
                modeloMrpData.setMinimum_Lot_Size_2(res.getString("Minimum_Lot_Size_2"));
                modeloMrpData.setBase_Unit_of_Measure_6(res.getString("Base_Unit_of_Measure_6"));
                modeloMrpData.setCosting_Lot_Size(res.getString("Costing_Lot_Size"));
                modeloMrpData.setBase_Unit_of_Measure_7(res.getString("Base_Unit_of_Measure_7"));
                modeloMrpData.setProd_Sched_Profile(res.getString("Prod_Sched_Profile"));
                modeloMrpData.setProd_Sched_Profile_Desc(res.getString("Prod_Sched_Profile_Desc"));
                modeloMrpData.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                modeloMrpData.setSpecial_procurement(res.getString("Special_procurement"));
                modeloMrpData.setSP_Key_Description(res.getString("SP_Key_Description"));
                modeloMrpData.setABC_Indicator(res.getString("ABC_Indicator"));
                modeloMrpData.setSTime_period_profile(res.getString("STime_period_profile"));
                modeloMrpData.setComm_imp_code_no(res.getString("Comm_imp_code_no"));
                modeloMrpData.setCountry_of_origin(res.getString("Country_of_origin"));
                modeloMrpData.setSpecProcurem_Costing(res.getString("SpecProcurem_Costing"));
                modeloMrpData.setOverhead_Group(res.getString("Overhead_Group"));
                modeloMrpData.setTDS(res.getString("TDS"));
                modeloMrpData.setPeriod_Indicator(res.getString("Period_Indicator"));
                modeloMrpDatas.add(modeloMrpData);
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMrpDatas;
    }

    public ModeloMrpData Select(Integer Id) {
        ModeloMrpData modeloMrpData = new ModeloMrpData();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement("SELECT "
                    + "`Id`,"
                    + "`Material`,"
                    + "`Material_Description`,"
                    + "`Material_Group`,"
                    + "`Profit_Center`,"
                    + "`Product_Hierarchy`,"
                    + "`Product_Hierarchy_Description`,"
                    + "`Material_Type`,"
                    + "`Material_type_descr`,"
                    + "`Plant`,"
                    + "`Plant_Name`,"
                    + "`Ext_Material_Group`,"
                    + "`Matl_Grp_Pack_Matls`,"
                    + "`MGPM_Description`,"
                    + "`MRP_profile`,"
                    + "`MRP_Profile_Description`,"
                    + "`Lot_size`,"
                    + "`Procurement_type`,"
                    + "`MRP_Controller`,"
                    + "`MRP_Type`,"
                    + "`Purchasing_Group`,"
                    + "`Plant_sp_matl_status`,"
                    + "`Mat_Status_Description`,"
                    + "`Do_Not_Cost`,"
                    + "`Storage_loc_for_EP`,"
                    + "`Prod_stor_location`,"
                    + "`Production_unit`,"
                    + "`Created_On`,"
                    + "`Availability_check`,"
                    + "`Loading_Group`,"
                    + "`Tot_repl_lead_time`,"
                    + "`Underdely_tolerance`,"
                    + "`Unltd_Overdelivery`,"
                    + "`Overdely_tolerance`,"
                    + "`In_house_production`,"
                    + "`Prodn_Supervisor`,"
                    + "`Backflush`,"
                    + "`Individual_coll`,"
                    + "`Rounding_value`,"
                    + "`Base_Unit_of_Measure_1`,"
                    + "`Reorder_Point`,"
                    + "`Base_Unit_of_Measure_2`,"
                    + "`Assembly_scrap_%`,"
                    + "`GR_processing_time`,"
                    + "`Unit_of_issue`,"
                    + "`Valid_from`,"
                    + "`Batch_management_1`,"
                    + "`Valuation_Category`,"
                    + "`DF_at_plant_level`,"
                    + "`DF_at_client_level`,"
                    + "`Old_material_number`,"
                    + "`Order_Unit`,"
                    + "`Lab_Office`,"
                    + "`Lab_description`,"
                    + "`Gross_Weight`,"
                    + "`Weight_Unit_1`,"
                    + "`Net_Weight`,"
                    + "`Weight_Unit_2`,"
                    + "`Container_reqmts`,"
                    + "`Storage_conditions`,"
                    + "`Temp_conditions`,"
                    + "`Transportation_Group`,"
                    + "`Batch_management_2`,"
                    + "`Packaging_mat_type`,"
                    + "`X_plant_matl_status`,"
                    + "`Min_Rem_Shelf_Life`,"
                    + "`Total_shelf_life`,"
                    + "`Language_Key`,"
                    + "`Created_By`,"
                    + "`Safety_stock`,"
                    + "`Base_Unit_of_Measure_3`,"
                    + "`Fixed_lot_size`,"
                    + "`Base_Unit_of_Measure_4`,"
                    + "`Maximum_Lot_Size_1`,"
                    + "`Base_Unit_of_Measure_5`,"
                    + "`Minimum_Lot_Size_2`,"
                    + "`Base_Unit_of_Measure_6`,"
                    + "`Costing_Lot_Size`,"
                    + "`Base_Unit_of_Measure_7`,"
                    + "`Prod_Sched_Profile`,"
                    + "`Prod_Sched_Profile_Desc`,"
                    + "`Planned_Deliv_Time`,"
                    + "`Special_procurement`,"
                    + "`SP_Key_Description`,"
                    + "`ABC_Indicator`,"
                    + "`STime_period_profile`,"
                    + "`Comm_imp_code_no`,"
                    + "`Country_of_origin`,"
                    + "`SpecProcurem_Costing`,"
                    + "`Overhead_Group`,"
                    + "`TDS`,"
                    + "`Period_Indicator`"
                    + "FROM `mrpdata`;");
            SQL.setInt(1, Id);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloMrpData.setId(res.getInt("id"));
                modeloMrpData.setMaterial(res.getString("Material"));
                modeloMrpData.setMaterial_Description(res.getString("Material_Description"));
                modeloMrpData.setMaterial_Group(res.getString("Material_Group"));
                modeloMrpData.setProfit_Center(res.getString("Profit_Center"));
                modeloMrpData.setProduct_Hierarchy(res.getString("Product_Hierarchy"));
                modeloMrpData.setProduct_Hierarchy_Description(res.getString("Product_Hierarchy_Description"));
                modeloMrpData.setMaterial_Type(res.getString("Material_Type"));
                modeloMrpData.setMaterial_type_descr(res.getString("Material_type_descr"));
                modeloMrpData.setPlant(res.getString("Plant"));
                modeloMrpData.setPlant_Name(res.getString("Plant_Name"));
                modeloMrpData.setExt_Material_Group(res.getString("Ext_Material_Group"));
                modeloMrpData.setMatl_Grp_Pack_Matls(res.getString("Matl_Grp_Pack_Matls"));
                modeloMrpData.setMGPM_Description(res.getString("MGPM_Description"));
                modeloMrpData.setMRP_profile(res.getString("MRP_profile"));
                modeloMrpData.setMRP_Profile_Description(res.getString("MRP_Profile_Description"));
                modeloMrpData.setLot_size(res.getString("Lot_size"));
                modeloMrpData.setProcurement_type(res.getString("Procurement_type"));
                modeloMrpData.setMRP_Controller(res.getString("MRP_Controller"));
                modeloMrpData.setMRP_Type(res.getString("MRP_Type"));
                modeloMrpData.setPurchasing_Group(res.getString("Purchasing_Group"));
                modeloMrpData.setPlant_sp_matl_status(res.getString("Plant_sp_matl_status"));
                modeloMrpData.setMat_Status_Description(res.getString("Mat_Status_Description"));
                modeloMrpData.setDo_Not_Cost(res.getString("Do_Not_Cost"));
                modeloMrpData.setStorage_loc_for_EP(res.getString("Storage_loc_for_EP"));
                modeloMrpData.setProd_stor_location(res.getString("Prod_stor_location"));
                modeloMrpData.setProduction_unit(res.getString("Production_unit"));
                modeloMrpData.setCreated_On(res.getString("Created_On"));
                modeloMrpData.setAvailability_check(res.getString("Availability_check"));
                modeloMrpData.setLoading_Group(res.getString("Loading_Group"));
                modeloMrpData.setTot_repl_lead_time(res.getString("Tot_repl_lead_time"));
                modeloMrpData.setUnderdely_tolerance(res.getString("Underdely_tolerance"));
                modeloMrpData.setUnltd_Overdelivery(res.getString("Unltd_Overdelivery"));
                modeloMrpData.setOverdely_tolerance(res.getString("Overdely_tolerance"));
                modeloMrpData.setIn_house_production(res.getString("In_house_production"));
                modeloMrpData.setProdn_Supervisor(res.getString("Prodn_Supervisor"));
                modeloMrpData.setBackflush(res.getString("Backflush"));
                modeloMrpData.setIndividual_coll(res.getString("Individual_coll"));
                modeloMrpData.setRounding_value(res.getString("Rounding_value"));
                modeloMrpData.setBase_Unit_of_Measure_1(res.getString("Base_Unit_of_Measure_1"));
                modeloMrpData.setReorder_Point(res.getString("Reorder_Point"));
                modeloMrpData.setBase_Unit_of_Measure_2(res.getString("Base_Unit_of_Measure_2"));
                modeloMrpData.setAssembly_scrap_porcentaje(res.getString("Assembly_scrap_%"));
                modeloMrpData.setGR_processing_time(res.getString("GR_processing_time"));
                modeloMrpData.setUnit_of_issue(res.getString("Unit_of_issue"));
                modeloMrpData.setValid_from(res.getString("Valid_from"));
                modeloMrpData.setBatch_management_1(res.getString("Batch_management_1"));
                modeloMrpData.setValuation_Category(res.getString("Valuation_Category"));
                modeloMrpData.setDF_at_plant_level(res.getString("DF_at_plant_level"));
                modeloMrpData.setDF_at_client_level(res.getString("DF_at_client_level"));
                modeloMrpData.setOld_material_number(res.getString("Old_material_number"));
                modeloMrpData.setOrder_Unit(res.getString("Order_Unit"));
                modeloMrpData.setLab_Office(res.getString("Lab_Office"));
                modeloMrpData.setLab_description(res.getString("Lab_description"));
                modeloMrpData.setGross_Weight(res.getString("Gross_Weight"));
                modeloMrpData.setWeight_Unit_1(res.getString("Weight_Unit_1"));
                modeloMrpData.setNet_Weight(res.getString("Net_Weight"));
                modeloMrpData.setWeight_Unit_2(res.getString("Weight_Unit_2"));
                modeloMrpData.setContainer_reqmts(res.getString("Container_reqmts"));
                modeloMrpData.setStorage_conditions(res.getString("Storage_conditions"));
                modeloMrpData.setTemp_conditions(res.getString("Temp_conditions"));
                modeloMrpData.setTransportation_Group(res.getString("Transportation_Group"));
                modeloMrpData.setBatch_management_2(res.getString("Batch_management_2"));
                modeloMrpData.setPackaging_mat_type(res.getString("Packaging_mat_type"));
                modeloMrpData.setX_plant_matl_status(res.getString("X_plant_matl_status"));
                modeloMrpData.setMin_Rem_Shelf_Life(res.getString("Min_Rem_Shelf_Life"));
                modeloMrpData.setTotal_shelf_life(res.getString("Total_shelf_life"));
                modeloMrpData.setLanguage_Key(res.getString("Language_Key"));
                modeloMrpData.setCreated_By(res.getString("Created_By"));
                modeloMrpData.setSafety_stock(res.getString("Safety_stock"));
                modeloMrpData.setBase_Unit_of_Measure_3(res.getString("Base_Unit_of_Measure_3"));
                modeloMrpData.setFixed_lot_size(res.getString("Fixed_lot_size"));
                modeloMrpData.setBase_Unit_of_Measure_4(res.getString("Base_Unit_of_Measure_4"));
                modeloMrpData.setMaximum_Lot_Size_1(res.getString("Maximum_Lot_Size_1"));
                modeloMrpData.setBase_Unit_of_Measure_5(res.getString("Base_Unit_of_Measure_5"));
                modeloMrpData.setMinimum_Lot_Size_2(res.getString("Minimum_Lot_Size_2"));
                modeloMrpData.setBase_Unit_of_Measure_6(res.getString("Base_Unit_of_Measure_6"));
                modeloMrpData.setCosting_Lot_Size(res.getString("Costing_Lot_Size"));
                modeloMrpData.setBase_Unit_of_Measure_7(res.getString("Base_Unit_of_Measure_7"));
                modeloMrpData.setProd_Sched_Profile(res.getString("Prod_Sched_Profile"));
                modeloMrpData.setProd_Sched_Profile_Desc(res.getString("Prod_Sched_Profile_Desc"));
                modeloMrpData.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                modeloMrpData.setSpecial_procurement(res.getString("Special_procurement"));
                modeloMrpData.setSP_Key_Description(res.getString("SP_Key_Description"));
                modeloMrpData.setABC_Indicator(res.getString("ABC_Indicator"));
                modeloMrpData.setSTime_period_profile(res.getString("STime_period_profile"));
                modeloMrpData.setComm_imp_code_no(res.getString("Comm_imp_code_no"));
                modeloMrpData.setCountry_of_origin(res.getString("Country_of_origin"));
                modeloMrpData.setSpecProcurem_Costing(res.getString("SpecProcurem_Costing"));
                modeloMrpData.setOverhead_Group(res.getString("Overhead_Group"));
                modeloMrpData.setTDS(res.getString("TDS"));
                modeloMrpData.setPeriod_Indicator(res.getString("Period_Indicator"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }
        return modeloMrpData;
    }

    public ModeloMrpData SelectSQL(String Sql) throws SQLException {
        ModeloMrpData modeloMrpData = new ModeloMrpData();
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            if (res.next()) {
                modeloMrpData.setId(res.getInt("id"));
                modeloMrpData.setMaterial(res.getString("Material"));
                modeloMrpData.setMaterial_Description(res.getString("Material_Description"));
                modeloMrpData.setMaterial_Group(res.getString("Material_Group"));
                modeloMrpData.setProfit_Center(res.getString("Profit_Center"));
                modeloMrpData.setProduct_Hierarchy(res.getString("Product_Hierarchy"));
                modeloMrpData.setProduct_Hierarchy_Description(res.getString("Product_Hierarchy_Description"));
                modeloMrpData.setMaterial_Type(res.getString("Material_Type"));
                modeloMrpData.setMaterial_type_descr(res.getString("Material_type_descr"));
                modeloMrpData.setPlant(res.getString("Plant"));
                modeloMrpData.setPlant_Name(res.getString("Plant_Name"));
                modeloMrpData.setExt_Material_Group(res.getString("Ext_Material_Group"));
                modeloMrpData.setMatl_Grp_Pack_Matls(res.getString("Matl_Grp_Pack_Matls"));
                modeloMrpData.setMGPM_Description(res.getString("MGPM_Description"));
                modeloMrpData.setMRP_profile(res.getString("MRP_profile"));
                modeloMrpData.setMRP_Profile_Description(res.getString("MRP_Profile_Description"));
                modeloMrpData.setLot_size(res.getString("Lot_size"));
                modeloMrpData.setProcurement_type(res.getString("Procurement_type"));
                modeloMrpData.setMRP_Controller(res.getString("MRP_Controller"));
                modeloMrpData.setMRP_Type(res.getString("MRP_Type"));
                modeloMrpData.setPurchasing_Group(res.getString("Purchasing_Group"));
                modeloMrpData.setPlant_sp_matl_status(res.getString("Plant_sp_matl_status"));
                modeloMrpData.setMat_Status_Description(res.getString("Mat_Status_Description"));
                modeloMrpData.setDo_Not_Cost(res.getString("Do_Not_Cost"));
                modeloMrpData.setStorage_loc_for_EP(res.getString("Storage_loc_for_EP"));
                modeloMrpData.setProd_stor_location(res.getString("Prod_stor_location"));
                modeloMrpData.setProduction_unit(res.getString("Production_unit"));
                modeloMrpData.setCreated_On(res.getString("Created_On"));
                modeloMrpData.setAvailability_check(res.getString("Availability_check"));
                modeloMrpData.setLoading_Group(res.getString("Loading_Group"));
                modeloMrpData.setTot_repl_lead_time(res.getString("Tot_repl_lead_time"));
                modeloMrpData.setUnderdely_tolerance(res.getString("Underdely_tolerance"));
                modeloMrpData.setUnltd_Overdelivery(res.getString("Unltd_Overdelivery"));
                modeloMrpData.setOverdely_tolerance(res.getString("Overdely_tolerance"));
                modeloMrpData.setIn_house_production(res.getString("In_house_production"));
                modeloMrpData.setProdn_Supervisor(res.getString("Prodn_Supervisor"));
                modeloMrpData.setBackflush(res.getString("Backflush"));
                modeloMrpData.setIndividual_coll(res.getString("Individual_coll"));
                modeloMrpData.setRounding_value(res.getString("Rounding_value"));
                modeloMrpData.setBase_Unit_of_Measure_1(res.getString("Base_Unit_of_Measure_1"));
                modeloMrpData.setReorder_Point(res.getString("Reorder_Point"));
                modeloMrpData.setBase_Unit_of_Measure_2(res.getString("Base_Unit_of_Measure_2"));
                modeloMrpData.setAssembly_scrap_porcentaje(res.getString("Assembly_scrap"));
                modeloMrpData.setGR_processing_time(res.getString("GR_processing_time"));
                modeloMrpData.setUnit_of_issue(res.getString("Unit_of_issue"));
                modeloMrpData.setValid_from(res.getString("Valid_from"));
                modeloMrpData.setBatch_management_1(res.getString("Batch_management_1"));
                modeloMrpData.setValuation_Category(res.getString("Valuation_Category"));
                modeloMrpData.setDF_at_plant_level(res.getString("DF_at_plant_level"));
                modeloMrpData.setDF_at_client_level(res.getString("DF_at_client_level"));
                modeloMrpData.setOld_material_number(res.getString("Old_material_number"));
                modeloMrpData.setOrder_Unit(res.getString("Order_Unit"));
                modeloMrpData.setLab_Office(res.getString("Lab_Office"));
                modeloMrpData.setLab_description(res.getString("Lab_description"));
                modeloMrpData.setGross_Weight(res.getString("Gross_Weight"));
                modeloMrpData.setWeight_Unit_1(res.getString("Weight_Unit_1"));
                modeloMrpData.setNet_Weight(res.getString("Net_Weight"));
                modeloMrpData.setWeight_Unit_2(res.getString("Weight_Unit_2"));
                modeloMrpData.setContainer_reqmts(res.getString("Container_reqmts"));
                modeloMrpData.setStorage_conditions(res.getString("Storage_conditions"));
                modeloMrpData.setTemp_conditions(res.getString("Temp_conditions"));
                modeloMrpData.setTransportation_Group(res.getString("Transportation_Group"));
                modeloMrpData.setBatch_management_2(res.getString("Batch_management_2"));
                modeloMrpData.setPackaging_mat_type(res.getString("Packaging_mat_type"));
                modeloMrpData.setX_plant_matl_status(res.getString("X_plant_matl_status"));
                modeloMrpData.setMin_Rem_Shelf_Life(res.getString("Min_Rem_Shelf_Life"));
                modeloMrpData.setTotal_shelf_life(res.getString("Total_shelf_life"));
                modeloMrpData.setLanguage_Key(res.getString("Language_Key"));
                modeloMrpData.setCreated_By(res.getString("Created_By"));
                modeloMrpData.setSafety_stock(res.getString("Safety_stock"));
                modeloMrpData.setBase_Unit_of_Measure_3(res.getString("Base_Unit_of_Measure_3"));
                modeloMrpData.setFixed_lot_size(res.getString("Fixed_lot_size"));
                modeloMrpData.setBase_Unit_of_Measure_4(res.getString("Base_Unit_of_Measure_4"));
                modeloMrpData.setMaximum_Lot_Size_1(res.getString("Maximum_Lot_Size_1"));
                modeloMrpData.setBase_Unit_of_Measure_5(res.getString("Base_Unit_of_Measure_5"));
                modeloMrpData.setMinimum_Lot_Size_2(res.getString("Minimum_Lot_Size_2"));
                modeloMrpData.setBase_Unit_of_Measure_6(res.getString("Base_Unit_of_Measure_6"));
                modeloMrpData.setCosting_Lot_Size(res.getString("Costing_Lot_Size"));
                modeloMrpData.setBase_Unit_of_Measure_7(res.getString("Base_Unit_of_Measure_7"));
                modeloMrpData.setProd_Sched_Profile(res.getString("Prod_Sched_Profile"));
                modeloMrpData.setProd_Sched_Profile_Desc(res.getString("Prod_Sched_Profile_Desc"));
                modeloMrpData.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                modeloMrpData.setSpecial_procurement(res.getString("Special_procurement"));
                modeloMrpData.setSP_Key_Description(res.getString("SP_Key_Description"));
                modeloMrpData.setABC_Indicator(res.getString("ABC_Indicator"));
                modeloMrpData.setSTime_period_profile(res.getString("STime_period_profile"));
                modeloMrpData.setComm_imp_code_no(res.getString("Comm_imp_code_no"));
                modeloMrpData.setCountry_of_origin(res.getString("Country_of_origin"));
                modeloMrpData.setSpecProcurem_Costing(res.getString("SpecProcurem_Costing"));
                modeloMrpData.setOverhead_Group(res.getString("Overhead_Group"));
                modeloMrpData.setTDS(res.getString("TDS"));
                modeloMrpData.setPeriod_Indicator(res.getString("Period_Indicator"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
            SQL.close();
            con.close();
        }
        return modeloMrpData;
    }
    ModeloMrpData modeloMrpData = new ModeloMrpData();

    public ModeloMrpData SelectSQL(String Sql, Connection con) throws SQLException {
        //ModeloMrpData modeloMrpData = new ModeloMrpData();
//        ConexionBDMySql conexion = new ConexionBDMySql();
//        Connection con;
//        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        ResultSet res = null;
        try {
            SQL = con.prepareStatement(Sql);
            res = SQL.executeQuery();
            if (res.next()) {
                modeloMrpData.setId(res.getInt("id"));
                modeloMrpData.setMaterial(res.getString("Material"));
                modeloMrpData.setMaterial_Description(res.getString("Material_Description"));
//                modeloMrpData.setMaterial_Group(res.getString("Material_Group"));
                modeloMrpData.setProfit_Center(res.getString("Profit_Center"));
//                modeloMrpData.setProduct_Hierarchy(res.getString("Product_Hierarchy"));
//                modeloMrpData.setProduct_Hierarchy_Description(res.getString("Product_Hierarchy_Description"));
                modeloMrpData.setMaterial_Type(res.getString("Material_Type"));
//                modeloMrpData.setMaterial_type_descr(res.getString("Material_type_descr"));
                modeloMrpData.setPlant(res.getString("Plant"));
//                modeloMrpData.setPlant_Name(res.getString("Plant_Name"));
//                modeloMrpData.setExt_Material_Group(res.getString("Ext_Material_Group"));
//                modeloMrpData.setMatl_Grp_Pack_Matls(res.getString("Matl_Grp_Pack_Matls"));
//                modeloMrpData.setMGPM_Description(res.getString("MGPM_Description"));
//                modeloMrpData.setMRP_profile(res.getString("MRP_profile"));
//                modeloMrpData.setMRP_Profile_Description(res.getString("MRP_Profile_Description"));
//                modeloMrpData.setLot_size(res.getString("Lot_size"));
                modeloMrpData.setProcurement_type(res.getString("Procurement_type"));
//                modeloMrpData.setMRP_Controller(res.getString("MRP_Controller"));
//                modeloMrpData.setMRP_Type(res.getString("MRP_Type"));
//                modeloMrpData.setPurchasing_Group(res.getString("Purchasing_Group"));
                modeloMrpData.setPlant_sp_matl_status(res.getString("Plant_sp_matl_status"));
//                modeloMrpData.setMat_Status_Description(res.getString("Mat_Status_Description"));
//                modeloMrpData.setDo_Not_Cost(res.getString("Do_Not_Cost"));
//                modeloMrpData.setStorage_loc_for_EP(res.getString("Storage_loc_for_EP"));
//                modeloMrpData.setProd_stor_location(res.getString("Prod_stor_location"));
//                modeloMrpData.setProduction_unit(res.getString("Production_unit"));
//                modeloMrpData.setCreated_On(res.getString("Created_On"));
//                modeloMrpData.setAvailability_check(res.getString("Availability_check"));
//                modeloMrpData.setLoading_Group(res.getString("Loading_Group"));
//                modeloMrpData.setTot_repl_lead_time(res.getString("Tot_repl_lead_time"));
//                modeloMrpData.setUnderdely_tolerance(res.getString("Underdely_tolerance"));
//                modeloMrpData.setUnltd_Overdelivery(res.getString("Unltd_Overdelivery"));
//                modeloMrpData.setOverdely_tolerance(res.getString("Overdely_tolerance"));
//                modeloMrpData.setIn_house_production(res.getString("In_house_production"));
//                modeloMrpData.setProdn_Supervisor(res.getString("Prodn_Supervisor"));
//                modeloMrpData.setBackflush(res.getString("Backflush"));
//                modeloMrpData.setIndividual_coll(res.getString("Individual_coll"));
//                modeloMrpData.setRounding_value(res.getString("Rounding_value"));
                modeloMrpData.setBase_Unit_of_Measure_1(res.getString("Base_Unit_of_Measure_1"));
//                modeloMrpData.setReorder_Point(res.getString("Reorder_Point"));
//                modeloMrpData.setBase_Unit_of_Measure_2(res.getString("Base_Unit_of_Measure_2"));
//                modeloMrpData.setAssembly_scrap_porcentaje(res.getString("Assembly_scrap"));
//                modeloMrpData.setGR_processing_time(res.getString("GR_processing_time"));
//                modeloMrpData.setUnit_of_issue(res.getString("Unit_of_issue"));
//                modeloMrpData.setValid_from(res.getString("Valid_from"));
//                modeloMrpData.setBatch_management_1(res.getString("Batch_management_1"));
//                modeloMrpData.setValuation_Category(res.getString("Valuation_Category"));
//                modeloMrpData.setDF_at_plant_level(res.getString("DF_at_plant_level"));
//                modeloMrpData.setDF_at_client_level(res.getString("DF_at_client_level"));
//                modeloMrpData.setOld_material_number(res.getString("Old_material_number"));
//                modeloMrpData.setOrder_Unit(res.getString("Order_Unit"));
//                modeloMrpData.setLab_Office(res.getString("Lab_Office"));
//                modeloMrpData.setLab_description(res.getString("Lab_description"));
//                modeloMrpData.setGross_Weight(res.getString("Gross_Weight"));
//                modeloMrpData.setWeight_Unit_1(res.getString("Weight_Unit_1"));
//                modeloMrpData.setNet_Weight(res.getString("Net_Weight"));
//                modeloMrpData.setWeight_Unit_2(res.getString("Weight_Unit_2"));
//                modeloMrpData.setContainer_reqmts(res.getString("Container_reqmts"));
//                modeloMrpData.setStorage_conditions(res.getString("Storage_conditions"));
//                modeloMrpData.setTemp_conditions(res.getString("Temp_conditions"));
//                modeloMrpData.setTransportation_Group(res.getString("Transportation_Group"));
//                modeloMrpData.setBatch_management_2(res.getString("Batch_management_2"));
//                modeloMrpData.setPackaging_mat_type(res.getString("Packaging_mat_type"));
//                modeloMrpData.setX_plant_matl_status(res.getString("X_plant_matl_status"));
//                modeloMrpData.setMin_Rem_Shelf_Life(res.getString("Min_Rem_Shelf_Life"));
//                modeloMrpData.setTotal_shelf_life(res.getString("Total_shelf_life"));
//                modeloMrpData.setLanguage_Key(res.getString("Language_Key"));
//                modeloMrpData.setCreated_By(res.getString("Created_By"));
//                modeloMrpData.setSafety_stock(res.getString("Safety_stock"));
//                modeloMrpData.setBase_Unit_of_Measure_3(res.getString("Base_Unit_of_Measure_3"));
//                modeloMrpData.setFixed_lot_size(res.getString("Fixed_lot_size"));
//                modeloMrpData.setBase_Unit_of_Measure_4(res.getString("Base_Unit_of_Measure_4"));
//                modeloMrpData.setMaximum_Lot_Size_1(res.getString("Maximum_Lot_Size_1"));
//                modeloMrpData.setBase_Unit_of_Measure_5(res.getString("Base_Unit_of_Measure_5"));
//                modeloMrpData.setMinimum_Lot_Size_2(res.getString("Minimum_Lot_Size_2"));
//                modeloMrpData.setBase_Unit_of_Measure_6(res.getString("Base_Unit_of_Measure_6"));
//                modeloMrpData.setCosting_Lot_Size(res.getString("Costing_Lot_Size"));
//                modeloMrpData.setBase_Unit_of_Measure_7(res.getString("Base_Unit_of_Measure_7"));
//                modeloMrpData.setProd_Sched_Profile(res.getString("Prod_Sched_Profile"));
//                modeloMrpData.setProd_Sched_Profile_Desc(res.getString("Prod_Sched_Profile_Desc"));
//                modeloMrpData.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                modeloMrpData.setSpecial_procurement(res.getString("Special_procurement"));
//                modeloMrpData.setSP_Key_Description(res.getString("SP_Key_Description"));
//                modeloMrpData.setABC_Indicator(res.getString("ABC_Indicator"));
//                modeloMrpData.setSTime_period_profile(res.getString("STime_period_profile"));
                //modeloMrpData.setComm_imp_code_no(res.getString("Comm_imp_code_no"));
                //modeloMrpData.setCountry_of_origin(res.getString("Country_of_origin"));
                //modeloMrpData.setSpecProcurem_Costing(res.getString("SpecProcurem_Costing"));
                modeloMrpData.setOverhead_Group(res.getString("Overhead_Group"));
                //modeloMrpData.setTDS(res.getString("TDS"));
                //modeloMrpData.setPeriod_Indicator(res.getString("Period_Indicator"));
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
            SQL.close();
            res.close();
            //con.close();
        }
        return modeloMrpData;
    }

    
}
