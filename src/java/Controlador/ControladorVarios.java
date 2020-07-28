/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloEine;
import Modelos.ModeloFbl3m;
import Modelos.ModeloKob1;
import Modelos.ModeloMb51;
import Modelos.ModeloMb51_Consumos;
import Modelos.ModeloMe80fn;
import Modelos.ModeloMrpData;
import Modelos.ModeloPovr;
import Modelos.ModeloProveedor;
import Modelos.ModeloVarios;
import Modelos.ModeloVariosProduccion;
import Modelos.ModeloVendorType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorVarios {

    public ModeloVarios LLenarModelos(ModeloMb51 modeloMb51) {
        ModeloVarios modeloVarios = new ModeloVarios();
        ModeloProveedor modeloProveedor = null;
        ModeloMrpData modeloMrpData = null;
        ModeloEine modeloEine = null;
        ModeloVendorType modeloVendorType = null;

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        ResultSet res = null;
        try {
            SQL = con.prepareStatement("SELECT Id, Vendors, Name, Vendor_Type, Moneda FROM proveedor WHERE Vendors = '" + modeloMb51.getVendor() + "'");
            res = SQL.executeQuery();
            if (res.next()) {
                modeloProveedor = new ModeloProveedor();
                modeloProveedor.setId(res.getInt("id"));
                modeloProveedor.setVendors(res.getString("Vendors"));
                modeloProveedor.setName(res.getString("Name"));
                modeloProveedor.setVendor_Type(res.getString("Vendor_Type"));
                modeloProveedor.setMoneda(res.getString("Moneda"));
                modeloVarios.setModeloProveedor(modeloProveedor);
            }

            SQL = con.prepareStatement("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
            res = SQL.executeQuery();
            if (res.next()) {
                modeloMrpData = new ModeloMrpData();
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
                modeloVarios.setModeloMrpData(modeloMrpData);
            }

            LinkedList<ModeloMe80fn> modeloMe80fns = new LinkedList<ModeloMe80fn>();
            SQL = con.prepareStatement("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Material <> '' AND Movement_type = ''");
            res = SQL.executeQuery();
            while (res.next()) {
                ModeloMe80fn modeloMe80fn = new ModeloMe80fn();
                modeloMe80fn.setId(res.getInt("id"));
                modeloMe80fn.setIndex(res.getString("Index"));
                modeloMe80fn.setIndex2(res.getString("Index2"));
                modeloMe80fn.setPurchasing_Document(res.getString("Purchasing_Document"));
                modeloMe80fn.setMaterial_Doc_Year(res.getString("Material_Doc_Year"));
                modeloMe80fn.setMaterial_Document(res.getString("Material_Document"));
                modeloMe80fn.setDocument_Date(res.getString("Document_Date"));
                modeloMe80fn.setMaterial(res.getString("Material"));
                modeloMe80fn.setShort_Text(res.getString("Short_Text"));
                modeloMe80fn.setBatch(res.getString("Batch"));
                modeloMe80fn.setItem(res.getString("Item"));
                modeloMe80fn.setMovement_type(res.getString("Movement_type"));
                modeloMe80fn.setPosting_Date(res.getString("Posting_Date"));
                modeloMe80fn.setDelivery_Completed(res.getString("Delivery_Completed"));
                modeloMe80fn.setPlant(res.getString("Plant"));
                modeloMe80fn.setQuantity(res.getString("Quantity"));
                modeloMe80fn.setOrder_Unit(res.getString("Order_Unit"));
                modeloMe80fn.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMe80fn.setAmount(res.getString("Amount"));
                modeloMe80fn.setCurrency(res.getString("Currency"));
                modeloMe80fn.setValuation_Type(res.getString("Valuation_Type"));
                modeloMe80fn.setEntry_Date(res.getString("Entry_Date"));
                modeloMe80fn.setLocal_currency(res.getString("Local_currency"));
                modeloMe80fn.setReference_Doc_Item(res.getString("Reference_Doc_Item"));
                modeloMe80fn.setInvoice_Value(res.getString("Invoice_Value"));
                modeloMe80fn.setInvoice_Value_in_FC(res.getString("Invoice_Value_in_FC"));
                modeloMe80fns.add(modeloMe80fn);
            }
            modeloVarios.setLstmodeloMe80fn(modeloMe80fns);

            if (modeloMb51.getBatch().contentEquals("B19121512")) {
                System.err.println("");
            }

            if (modeloProveedor.getVendor_Type().contains("ICO")) {

                SQL = con.prepareStatement("SELECT * FROM Fbl3m WHERE Text = '" + modeloMb51.getPurchase_order() + modeloMb51.getItem() + "' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no = '50320' OR Offsetting_acct_no = '3062482' OR Offsetting_acct_no = '3072634' OR Offsetting_acct_no = '3905355' OR Offsetting_acct_no = '3905370' OR Offsetting_acct_no = '3905371')");
                res = SQL.executeQuery();
                LinkedList<ModeloFbl3m> modeloFbl3ms_Freight = new LinkedList<ModeloFbl3m>();
                while (res.next()) {
                    ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                    modeloFbl3m.setId(res.getInt("id"));
                    modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                    modeloFbl3m.setDocument_type(res.getString("Document_type"));
                    modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                    modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                    modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                    modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                    modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                    modeloFbl3m.setAccount(res.getString("Account"));
                    modeloFbl3m.setPlant(res.getString("Plant"));
                    modeloFbl3m.setMaterial(res.getString("Material"));
                    modeloFbl3m.setQuantity(res.getString("Quantity"));
                    modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                    modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                    modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                    modeloFbl3m.setReference(res.getString("Reference"));
                    modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                    modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                    modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                    modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                    modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                    modeloFbl3m.setText(res.getString("Text"));
                    modeloFbl3m.setAssignment(res.getString("Assignment"));
                    modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                    modeloFbl3ms_Freight.add(modeloFbl3m);
                }
                modeloVarios.setListmodeloFbl3m_Freight(modeloFbl3ms_Freight);

                SQL = con.prepareStatement("SELECT * FROM Fbl3m WHERE Text = '" + modeloMb51.getPurchase_order() + modeloMb51.getItem() + "' AND (Offsetting_acct_no = '20014' OR Offsetting_acct_no = '3905312')");
                res = SQL.executeQuery();
                LinkedList<ModeloFbl3m> modeloFbl3ms_DUTYS = new LinkedList<ModeloFbl3m>();
                while (res.next()) {
                    ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                    modeloFbl3m.setId(res.getInt("id"));
                    modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                    modeloFbl3m.setDocument_type(res.getString("Document_type"));
                    modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                    modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                    modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                    modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                    modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                    modeloFbl3m.setAccount(res.getString("Account"));
                    modeloFbl3m.setPlant(res.getString("Plant"));
                    modeloFbl3m.setMaterial(res.getString("Material"));
                    modeloFbl3m.setQuantity(res.getString("Quantity"));
                    modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                    modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                    modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                    modeloFbl3m.setReference(res.getString("Reference"));
                    modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                    modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                    modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                    modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                    modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                    modeloFbl3m.setText(res.getString("Text"));
                    modeloFbl3m.setAssignment(res.getString("Assignment"));
                    modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                    modeloFbl3ms_DUTYS.add(modeloFbl3m);
                }
                modeloVarios.setListmodeloFbl3m_DUTYS(modeloFbl3ms_DUTYS);

                SQL = con.prepareStatement("SELECT * FROM Fbl3m WHERE Text = '" + modeloMb51.getPurchase_order() + modeloMb51.getItem() + "' AND (Offsetting_acct_no = '3062623')");
                res = SQL.executeQuery();
                LinkedList<ModeloFbl3m> modeloFbl3ms_ARANCEL = new LinkedList<ModeloFbl3m>();
                while (res.next()) {
                    ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                    modeloFbl3m.setId(res.getInt("id"));
                    modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                    modeloFbl3m.setDocument_type(res.getString("Document_type"));
                    modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                    modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                    modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                    modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                    modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                    modeloFbl3m.setAccount(res.getString("Account"));
                    modeloFbl3m.setPlant(res.getString("Plant"));
                    modeloFbl3m.setMaterial(res.getString("Material"));
                    modeloFbl3m.setQuantity(res.getString("Quantity"));
                    modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                    modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                    modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                    modeloFbl3m.setReference(res.getString("Reference"));
                    modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                    modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                    modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                    modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                    modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                    modeloFbl3m.setText(res.getString("Text"));
                    modeloFbl3m.setAssignment(res.getString("Assignment"));
                    modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                    modeloFbl3ms_ARANCEL.add(modeloFbl3m);
                }
                modeloVarios.setListmodeloFbl3m_ARANCEL(modeloFbl3ms_ARANCEL);

            } else {
                SQL = con.prepareStatement("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no = '50320' OR Offsetting_acct_no = '3062482' OR Offsetting_acct_no = '3072634' OR Offsetting_acct_no = '3905355' OR Offsetting_acct_no = '3905370' OR Offsetting_acct_no = '3905371')");
                res = SQL.executeQuery();
                LinkedList<ModeloFbl3m> modeloFbl3ms_Freight = new LinkedList<ModeloFbl3m>();
                while (res.next()) {
                    ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                    modeloFbl3m.setId(res.getInt("id"));
                    modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                    modeloFbl3m.setDocument_type(res.getString("Document_type"));
                    modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                    modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                    modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                    modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                    modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                    modeloFbl3m.setAccount(res.getString("Account"));
                    modeloFbl3m.setPlant(res.getString("Plant"));
                    modeloFbl3m.setMaterial(res.getString("Material"));
                    modeloFbl3m.setQuantity(res.getString("Quantity"));
                    modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                    modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                    modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                    modeloFbl3m.setReference(res.getString("Reference"));
                    modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                    modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                    modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                    modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                    modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                    modeloFbl3m.setText(res.getString("Text"));
                    modeloFbl3m.setAssignment(res.getString("Assignment"));
                    modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                    modeloFbl3ms_Freight.add(modeloFbl3m);
                }
                modeloVarios.setListmodeloFbl3m_Freight(modeloFbl3ms_Freight);

                SQL = con.prepareStatement("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND (Offsetting_acct_no = '20014' OR Offsetting_acct_no = '3905312')");
                res = SQL.executeQuery();
                LinkedList<ModeloFbl3m> modeloFbl3ms_DUTYS = new LinkedList<ModeloFbl3m>();
                while (res.next()) {
                    ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                    modeloFbl3m.setId(res.getInt("id"));
                    modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                    modeloFbl3m.setDocument_type(res.getString("Document_type"));
                    modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                    modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                    modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                    modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                    modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                    modeloFbl3m.setAccount(res.getString("Account"));
                    modeloFbl3m.setPlant(res.getString("Plant"));
                    modeloFbl3m.setMaterial(res.getString("Material"));
                    modeloFbl3m.setQuantity(res.getString("Quantity"));
                    modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                    modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                    modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                    modeloFbl3m.setReference(res.getString("Reference"));
                    modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                    modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                    modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                    modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                    modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                    modeloFbl3m.setText(res.getString("Text"));
                    modeloFbl3m.setAssignment(res.getString("Assignment"));
                    modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                    modeloFbl3ms_DUTYS.add(modeloFbl3m);
                }
                modeloVarios.setListmodeloFbl3m_DUTYS(modeloFbl3ms_DUTYS);

                SQL = con.prepareStatement("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND (Offsetting_acct_no = '3062623')");
                res = SQL.executeQuery();
                LinkedList<ModeloFbl3m> modeloFbl3ms_ARANCEL = new LinkedList<ModeloFbl3m>();
                while (res.next()) {
                    ModeloFbl3m modeloFbl3m = new ModeloFbl3m();
                    modeloFbl3m.setId(res.getInt("id"));
                    modeloFbl3m.setDocument_Number(res.getString("Document_Number"));
                    modeloFbl3m.setDocument_type(res.getString("Document_type"));
                    modeloFbl3m.setDocument_Date(res.getString("Document_Date"));
                    modeloFbl3m.setPosting_Date(res.getString("Posting_Date"));
                    modeloFbl3m.setCost_Center(res.getString("Cost_Center"));
                    modeloFbl3m.setProfit_Center(res.getString("Profit_Center"));
                    modeloFbl3m.setYearMonth(res.getString("YearMonth"));
                    modeloFbl3m.setAccount(res.getString("Account"));
                    modeloFbl3m.setPlant(res.getString("Plant"));
                    modeloFbl3m.setMaterial(res.getString("Material"));
                    modeloFbl3m.setQuantity(res.getString("Quantity"));
                    modeloFbl3m.setAmount_in_local_currency(res.getString("Amount_in_local_currency"));
                    modeloFbl3m.setLocal_Currency(res.getString("Local_Currency"));
                    modeloFbl3m.setPurchasing_Document(res.getString("Purchasing_Document"));
                    modeloFbl3m.setReference(res.getString("Reference"));
                    modeloFbl3m.setDocument_currency(res.getString("Document_currency"));
                    modeloFbl3m.setOffsetting_acct_no(res.getString("Offsetting_acct_no"));
                    modeloFbl3m.setBase_Unit_of_Measure(res.getString("Base_Unit_of_Measure"));
                    modeloFbl3m.setAlternative_Account_No(res.getString("Alternative_Account_No"));
                    modeloFbl3m.setTransaction_Code(res.getString("Transaction_Code"));
                    modeloFbl3m.setText(res.getString("Text"));
                    modeloFbl3m.setAssignment(res.getString("Assignment"));
                    modeloFbl3m.setClasificacion(res.getString("Clasificacion"));
                    modeloFbl3ms_ARANCEL.add(modeloFbl3m);
                }
                modeloVarios.setListmodeloFbl3m_ARANCEL(modeloFbl3ms_ARANCEL);
            }

            if (modeloProveedor.getVendor_Type().contains("ICO")) {

                SQL = con.prepareStatement("SELECT * FROM EINE WHERE vendor = '" + modeloMb51.getVendor() + "'");
                res = SQL.executeQuery();
                while (res.next()) {

                    modeloEine = new ModeloEine();
                    modeloEine.setId(res.getInt("id"));
                    modeloEine.setPurchasing_info_rec(res.getString("Purchasing_info_rec"));
                    modeloEine.setPlant(res.getString("Plant"));
                    modeloEine.setCreated_On(res.getString("Created_On"));
                    modeloEine.setPurchasing_Group(res.getString("Purchasing_Group"));
                    modeloEine.setCurrency(res.getString("Currency"));
                    modeloEine.setStandard_PO_Quantity(res.getString("Standard_PO_Quantity"));
                    modeloEine.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                    modeloEine.setNet_Price(res.getString("Net_Price"));
                    modeloEine.setPrice_unit(res.getString("Price_unit"));
                    modeloEine.setOrder_Price_Unit(res.getString("Order_Price_Unit"));
                    modeloEine.setValid_to(res.getString("Valid_to"));
                    modeloEine.setQuantity_Conversion_1(res.getString("Quantity_Conversion_1"));
                    modeloEine.setQuantity_Conversion_2(res.getString("Quantity_Conversion_2"));
                    modeloEine.setEffective_Price(res.getString("Effective_Price"));
                    modeloEine.setAcknowledgment_Reqd(res.getString("Acknowledgment_Reqd"));
                    modeloEine.setConfirmation_Control(res.getString("Confirmation_Control"));
                    modeloEine.setIncoterms_1(res.getString("Incoterms_1"));
                    modeloEine.setIncoterms_2(res.getString("Incoterms_2"));
                    modeloEine.setMaterial(res.getString("Material"));
                    modeloEine.setVendor(res.getString("Vendor"));
                    modeloEine.setLink_Material_vendor(res.getString("Link_Material_vendor"));
                    modeloEine.setPorcentaje_Additional(res.getString("Porcentaje_Additional"));
                    modeloVarios.setModeloEine(modeloEine);
                }
            } else {
                SQL = con.prepareStatement("SELECT * FROM EINE WHERE Link_Material_vendor = '" + modeloMb51.getMaterial() + modeloMb51.getVendor() + "'");
                res = SQL.executeQuery();
                while (res.next()) {

                    modeloEine = new ModeloEine();
                    modeloEine.setId(res.getInt("id"));
                    modeloEine.setPurchasing_info_rec(res.getString("Purchasing_info_rec"));
                    modeloEine.setPlant(res.getString("Plant"));
                    modeloEine.setCreated_On(res.getString("Created_On"));
                    modeloEine.setPurchasing_Group(res.getString("Purchasing_Group"));
                    modeloEine.setCurrency(res.getString("Currency"));
                    modeloEine.setStandard_PO_Quantity(res.getString("Standard_PO_Quantity"));
                    modeloEine.setPlanned_Deliv_Time(res.getString("Planned_Deliv_Time"));
                    modeloEine.setNet_Price(res.getString("Net_Price"));
                    modeloEine.setPrice_unit(res.getString("Price_unit"));
                    modeloEine.setOrder_Price_Unit(res.getString("Order_Price_Unit"));
                    modeloEine.setValid_to(res.getString("Valid_to"));
                    modeloEine.setQuantity_Conversion_1(res.getString("Quantity_Conversion_1"));
                    modeloEine.setQuantity_Conversion_2(res.getString("Quantity_Conversion_2"));
                    modeloEine.setEffective_Price(res.getString("Effective_Price"));
                    modeloEine.setAcknowledgment_Reqd(res.getString("Acknowledgment_Reqd"));
                    modeloEine.setConfirmation_Control(res.getString("Confirmation_Control"));
                    modeloEine.setIncoterms_1(res.getString("Incoterms_1"));
                    modeloEine.setIncoterms_2(res.getString("Incoterms_2"));
                    modeloEine.setMaterial(res.getString("Material"));
                    modeloEine.setVendor(res.getString("Vendor"));
                    modeloEine.setLink_Material_vendor(res.getString("Link_Material_vendor"));
                    modeloEine.setPorcentaje_Additional(res.getString("Porcentaje_Additional"));
                    modeloVarios.setModeloEine(modeloEine);
                }
            }

            SQL = con.prepareStatement("SELECT * FROM VendorType WHERE Material = '" + modeloMb51.getMaterial() + "'");
            res = SQL.executeQuery();
            if (res.next()) {
                modeloVendorType = new ModeloVendorType();
                modeloVendorType.setId(res.getInt("id"));
                modeloVendorType.setMaterial(res.getString("Material"));
                modeloVendorType.setVendor_Type(res.getString("Vendor_Type"));
            }

            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);

        }

        return modeloVarios;
    }

    public ModeloVarios LLenarModelos(ModeloKob1 modeloKob1) {
        ModeloVariosProduccion modeloVariosProduccion = new ModeloVariosProduccion();
        ModeloMrpData modeloMrpData = null;
        ModeloMb51_Consumos modeloMb51_Consumos = null;

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL = null;
        ResultSet res = null;
        try {

            //MB51_CONSUMOS
            LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = new LinkedList<ModeloMb51_Consumos>();

            //SQL = con.prepareStatement("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
            res = SQL.executeQuery();
            while (res.next()) {

                modeloMb51_Consumos = new ModeloMb51_Consumos();
                modeloMb51_Consumos.setId(res.getInt("Id"));
                modeloMb51_Consumos.setPlant(res.getString("Plant"));
                modeloMb51_Consumos.setPurchase_order(res.getString("Purchase_order"));
                modeloMb51_Consumos.setMaterial(res.getString("Material"));
                modeloMb51_Consumos.setMaterial_Description(res.getString("Material_Description"));
                modeloMb51_Consumos.setBatch(res.getString("Batch"));
                modeloMb51_Consumos.setMovement_type(res.getString("Movement_type"));
                modeloMb51_Consumos.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloMb51_Consumos.setItem(res.getString("Item"));
                modeloMb51_Consumos.setQuantity(res.getString("Quantity"));
                modeloMb51_Consumos.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloMb51_Consumos.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51_Consumos.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51_Consumos.setCurrency(res.getString("Currency"));
                modeloMb51_Consumos.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51_Consumos.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51_Consumos.setDocument_Date(res.getString("Document_Date"));
                modeloMb51_Consumos.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51_Consumos.setUser_Name(res.getString("User_Name"));
                modeloMb51_Consumos.setVendor(res.getString("Vendor"));
                modeloMb51_Consumos.setOrder_(res.getString("Order_"));
                LstModeloMb51_Consumos.add(modeloMb51_Consumos);
            }

            modeloVariosProduccion.setLstModeloMb51_Consumos(LstModeloMb51_Consumos);

            // MPRDATA
            LinkedList<ModeloMrpData> LstModeloMrpData = new LinkedList<ModeloMrpData>();

            //SQL = con.prepareStatement("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
            res = SQL.executeQuery();
            while (res.next()) {

                modeloMrpData = new ModeloMrpData();
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
//                ModeloMrpData.add(modeloMrpData);

            }

            modeloVariosProduccion.setLstModeloMrpData(LstModeloMrpData);

            LinkedList<ModeloPovr> LstModeloPovr = new LinkedList<ModeloPovr>();

//            SQL = con.prepareStatement("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
            res = SQL.executeQuery();
            while (res.next()) {
                ModeloPovr modeloPovr = new ModeloPovr();
                modeloPovr.setId(res.getInt("Id"));
                modeloPovr.setPlant(res.getString("Plant"));
                modeloPovr.setMaterial(res.getString("Material"));
                modeloPovr.setMaterial_Description(res.getString("Material_Description"));
                modeloPovr.setProcess_Order_Number(res.getString("Process_Order_Number"));
                modeloPovr.setProcess_Order_Type(res.getString("Process_Order_Type"));
                modeloPovr.setMaterial_Group_Packaging(res.getString("Material_Group_Packaging"));
                modeloPovr.setProfit_Center(res.getString("Profit_Center"));
                modeloPovr.setUOM(res.getString("UOM"));
                modeloPovr.setMaterial_type(res.getString("Material_type"));
                modeloPovr.setExisting_Material_Costing_Lot_Size(res.getString("Existing_Material_Costing_Lot_Size"));
                modeloPovr.setMRP_Planned_Quantity(res.getString("MRP_Planned_Quantity"));
                modeloPovr.setConsumption_Qty_Actual_I_P(res.getString("Consumption_Qty_Actual_I_P"));
                modeloPovr.setDelivered_Qty_Actual_O_P(res.getString("Delivered_Qty_Actual_O_P"));
                modeloPovr.setYield_Variance_Qty(res.getString("Yield_Variance_Qty"));
                modeloPovr.setYield_Variance_Porcentaje(res.getString("Yield_Variance_Porcentaje"));
                modeloPovr.setActual_Material_Cost(res.getString("Actual_Material_Cost"));
                modeloPovr.setLabor_Cost_Actual(res.getString("Labor_Cost_Actual"));
                modeloPovr.setMachine_Cost_Actual(res.getString("Machine_Cost_Actual"));
                modeloPovr.setOverhead_Cost_Actual(res.getString("Overhead_Cost_Actual"));
                modeloPovr.setTotal_Cost_Actual_Output(res.getString("Total_Cost_Actual_Output"));
                modeloPovr.setPhysical_Inventory_Yield_Loss_cost_Actu(res.getString("Physical_Inventory_Yield_Loss_cost_Actu"));
                modeloPovr.setStandard_Price(res.getString("Standard_Price"));
                modeloPovr.setCalculated_Production_Variance(res.getString("Calculated_Production_Variance"));
                modeloPovr.setConversion_Cost_Per_Unit(res.getString("Conversion_Cost_Per_Unit"));
                modeloPovr.setDisplay_Currency(res.getString("Display_Currency"));
                modeloPovr.setBatch_Number(res.getString("Batch_Number"));
                modeloPovr.setProduct_Hierarchy(res.getString("Product_Hierarchy"));
                modeloPovr.setActual_Start_Date(res.getString("Actual_Start_Date"));
                modeloPovr.setActual_Finish_Date(res.getString("Actual_Finish_Date"));

                LstModeloPovr.add(modeloPovr);

            }
            modeloVariosProduccion.setLstModeloPovr(LstModeloPovr);

            LinkedList<ModeloMb51> LstModeloMb51 = new LinkedList<ModeloMb51>();

//            SQL = con.prepareStatement("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
            res = SQL.executeQuery();
            while (res.next()) {
                ModeloMb51 modeloMb51 = new ModeloMb51();
                modeloMb51.setId(res.getInt("Id"));
                modeloMb51.setPlant(res.getString("Plant"));
                modeloMb51.setPurchase_order(res.getString("Purchase_order"));
                modeloMb51.setMaterial(res.getString("Material"));
                modeloMb51.setMaterial_Description(res.getString("Material_Description"));
                modeloMb51.setBatch(res.getString("Batch"));
                modeloMb51.setMovement_type(res.getString("Movement_type"));
                modeloMb51.setMovement_Type_Text(res.getString("Movement_Type_Text"));
                modeloMb51.setItem(res.getString("Item"));
                modeloMb51.setQuantity(res.getString("Quantity"));
                modeloMb51.setQty_in_unit_of_entry(res.getString("Qty_in_unit_of_entry"));
                modeloMb51.setUnit_of_Entry(res.getString("Unit_of_Entry"));
                modeloMb51.setAmt_in_loc_cur(res.getString("Amt_in_loc_cur"));
                modeloMb51.setCurrency(res.getString("Currency"));
                modeloMb51.setStorage_Location(res.getString("Storage_Location"));
                modeloMb51.setPosting_Date(res.getString("Posting_Date"));
                modeloMb51.setDocument_Date(res.getString("Document_Date"));
                modeloMb51.setMaterial_Document(res.getString("Material_Document"));
                modeloMb51.setUser_Name(res.getString("User_Name"));
                modeloMb51.setVendor(res.getString("Vendor"));
                modeloMb51.setVendor_Name(res.getString("Vendor_Name"));
                modeloMb51.setVendor_Type(res.getString("Vendor_Type"));
                modeloMb51.setMonth(res.getString("Month"));
                modeloMb51.setPeriod(res.getString("Period"));
                modeloMb51.setCost_Unit_SAP_en_KG(res.getString("Cost_Unit_SAP_en_KG"));
                modeloMb51.setMaterial_Type(res.getString("Material_Type"));
                modeloMb51.setProfit_Center(res.getString("Profit_Center"));
                modeloMb51.setLink1_Material_Batch(res.getString("link1_Material_Batch"));
                modeloMb51.setLink2_PO_position(res.getString("link2_PO_position"));
                modeloMb51.setReferencia_vendor(res.getString("Referencia_vendor"));
                modeloMb51.setTotalQ_ME80FN(res.getString("TotalQ_ME80FN"));
                modeloMb51.setO_Unit_ME80FN(res.getString("O_Unit_ME80FN"));
                modeloMb51.setTotalQ_Porcentaje(res.getString("TotalQ_Porcentaje"));
                modeloMb51.setTOTAL_INVOICE_VALUE(res.getString("TOTAL_INVOICE_VALUE"));
                modeloMb51.setFactura_Value_Unit(res.getString("Factura_Value_Unit"));
                modeloMb51.setPIR_Porcentaje_del_Costo(res.getString("PIR_Porcentaje_del_Costo"));
                modeloMb51.setPrecio_Unit_moneda_compra(res.getString("Precio_Unit_moneda_compra"));
                modeloMb51.setMoneda(res.getString("Moneda"));
                modeloMb51.setLink3_PO_Item(res.getString("link3_PO_Item"));
                modeloMb51.setFreight(res.getString("Freight"));
                modeloMb51.setDutys(res.getString("Dutys"));
                modeloMb51.setArancel(res.getString("Arancel"));
                modeloMb51.setTotal_Costos_Adicionales(res.getString("Total_Costos_Adicionales"));
                modeloMb51.setParticipac_Adicionales(res.getString("Participac_Adicionales"));
                modeloMb51.setAdicionales_al_CTO_Estandar(res.getString("Adicionales_al_CTO_Estandar"));
                modeloMb51.setVariance(res.getString("Variance"));
                modeloMb51.setTotal_Costos(res.getString("Total_Costos"));
                modeloMb51.setUnitario_Real(res.getString("Unitario_Real"));
                modeloMb51.setUnitario_Real_adicional_estandar(res.getString("Unitario_Real_adicional_estandar"));
                modeloMb51.setUnitario_estandar_SAP(res.getString("Unitario_estandar_SAP"));
                modeloMb51.setUnitario_final_FIFO(res.getString("Unitario_final_FIFO"));
                modeloMb51.setPorcentaje_Real_Vs_Estandar(res.getString("Porcentaje_Real_Vs_Estandar"));
                modeloMb51.setPorcentaje_fifo_final_vs_Estandar(res.getString("Porcentaje_fifo_final_vs_Estandar"));
                modeloMb51.setCompra_valorada_a_Unit_FIFO(res.getString("Compra_valorada_a_Unit_FIFO"));
                modeloMb51.setVariacion_FIFO_vs_Estandar(res.getString("Variacion_FIFO_vs_Estandar"));
                modeloMb51.setNovedadIco(res.getString("NovedadIco"));

                LstModeloMb51.add(modeloMb51);
            }
            modeloVariosProduccion.setLstModeloMb51(LstModeloMb51);

        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);

        }
        return null;
    }

}
