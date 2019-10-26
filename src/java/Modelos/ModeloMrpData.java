/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloMrpData
{

    Integer Id;
    String Material;
    String Material_Description;
    String Material_Group;
    String Profit_Center;
    String Product_Hierarchy;
    String Product_Hierarchy_Description;
    String Material_Type;
    String Material_type_descr;
    String Plant;
    String Plant_Name;
    String Ext_Material_Group;
    String Matl_Grp_Pack_Matls;
    String MGPM_Description;
    String MRP_profile;
    String MRP_Profile_Description;
    String Lot_size;
    String Procurement_type;
    String MRP_Controller;
    String MRP_Type;
    String Purchasing_Group;
    String Plant_sp_matl_status;
    String Mat_Status_Description;
    String Do_Not_Cost;
    String Storage_loc_for_EP;
    String Prod_stor_location;
    String Production_unit;
    String Created_On;
    String Availability_check;
    String Loading_Group;
    String Tot_repl_lead_time;
    String Underdely_tolerance;
    String Unltd_Overdelivery;
    String Overdely_tolerance;
    String In_house_production;
    String Prodn_Supervisor;
    String Backflush;
    String Individual_coll;
    String Rounding_value;
    String Base_Unit_of_Measure_1;
    String Reorder_Point;
    String Base_Unit_of_Measure_2;
    String Assembly_scrap_porcentaje;
    String GR_processing_time;
    String Unit_of_issue;
    String Valid_from;
    String Batch_management_1;
    String Valuation_Category;
    String DF_at_plant_level;
    String DF_at_client_level;
    String Old_material_number;
    String Order_Unit;
    String Lab_Office;
    String Lab_description;
    String Gross_Weight;
    String Weight_Unit_1;
    String Net_Weight;
    String Weight_Unit_2;
    String Container_reqmts;
    String Storage_conditions;
    String Temp_conditions;
    String Transportation_Group;
    String Batch_management_2;
    String Packaging_mat_type;
    String X_plant_matl_status;
    String Min_Rem_Shelf_Life;
    String Total_shelf_life;
    String Language_Key;
    String Created_By;
    String Safety_stock;
    String Base_Unit_of_Measure_3;
    String Fixed_lot_size;
    String Base_Unit_of_Measure_4;
    String Maximum_Lot_Size_1;
    String Base_Unit_of_Measure_5;
    String Minimum_Lot_Size_2;
    String Base_Unit_of_Measure_6;
    String Costing_Lot_Size;
    String Base_Unit_of_Measure_7;
    String Prod_Sched_Profile;
    String Prod_Sched_Profile_Desc;
    String Planned_Deliv_Time;
    String Special_procurement;
    String SP_Key_Description;
    String ABC_Indicator;
    String STime_period_profile;
    String Comm_imp_code_no;
    String Country_of_origin;
    String SpecProcurem_Costing;
    String Overhead_Group;
    String TDS;
    String Period_Indicator;

    public ModeloMrpData()
    {
    }

    public ModeloMrpData(Integer Id, String Material, String Material_Description, String Material_Group, String Profit_Center, String Product_Hierarchy, String Product_Hierarchy_Description, String Material_Type, String Material_type_descr, String Plant, String Plant_Name, String Ext_Material_Group, String Matl_Grp_Pack_Matls, String MGPM_Description, String MRP_profile, String MRP_Profile_Description, String Lot_size, String Procurement_type, String MRP_Controller, String MRP_Type, String Purchasing_Group, String Plant_sp_matl_status, String Mat_Status_Description, String Do_Not_Cost, String Storage_loc_for_EP, String Prod_stor_location, String Production_unit, String Created_On, String Availability_check, String Loading_Group, String Tot_repl_lead_time, String Underdely_tolerance, String Unltd_Overdelivery, String Overdely_tolerance, String In_house_production, String Prodn_Supervisor, String Backflush, String Individual_coll, String Rounding_value, String Base_Unit_of_Measure_1, String Reorder_Point, String Base_Unit_of_Measure_2, String Assembly_scrap_porcentaje, String GR_processing_time, String Unit_of_issue, String Valid_from, String Batch_management_1, String Valuation_Category, String DF_at_plant_level, String DF_at_client_level, String Old_material_number, String Order_Unit, String Lab_Office, String Lab_description, String Gross_Weight, String Weight_Unit_1, String Net_Weight, String Weight_Unit_2, String Container_reqmts, String Storage_conditions, String Temp_conditions, String Transportation_Group, String Batch_management_2, String Packaging_mat_type, String X_plant_matl_status, String Min_Rem_Shelf_Life, String Total_shelf_life, String Language_Key, String Created_By, String Safety_stock, String Base_Unit_of_Measure_3, String Fixed_lot_size, String Base_Unit_of_Measure_4, String Maximum_Lot_Size_1, String Base_Unit_of_Measure_5, String Minimum_Lot_Size_2, String Base_Unit_of_Measure_6, String Costing_Lot_Size, String Base_Unit_of_Measure_7, String Prod_Sched_Profile, String Prod_Sched_Profile_Desc, String Planned_Deliv_Time, String Special_procurement, String SP_Key_Description, String ABC_Indicator, String STime_period_profile, String Comm_imp_code_no, String Country_of_origin, String SpecProcurem_Costing, String Overhead_Group, String TDS, String Period_Indicator)
    {
        this.Id = Id;
        this.Material = Material;
        this.Material_Description = Material_Description;
        this.Material_Group = Material_Group;
        this.Profit_Center = Profit_Center;
        this.Product_Hierarchy = Product_Hierarchy;
        this.Product_Hierarchy_Description = Product_Hierarchy_Description;
        this.Material_Type = Material_Type;
        this.Material_type_descr = Material_type_descr;
        this.Plant = Plant;
        this.Plant_Name = Plant_Name;
        this.Ext_Material_Group = Ext_Material_Group;
        this.Matl_Grp_Pack_Matls = Matl_Grp_Pack_Matls;
        this.MGPM_Description = MGPM_Description;
        this.MRP_profile = MRP_profile;
        this.MRP_Profile_Description = MRP_Profile_Description;
        this.Lot_size = Lot_size;
        this.Procurement_type = Procurement_type;
        this.MRP_Controller = MRP_Controller;
        this.MRP_Type = MRP_Type;
        this.Purchasing_Group = Purchasing_Group;
        this.Plant_sp_matl_status = Plant_sp_matl_status;
        this.Mat_Status_Description = Mat_Status_Description;
        this.Do_Not_Cost = Do_Not_Cost;
        this.Storage_loc_for_EP = Storage_loc_for_EP;
        this.Prod_stor_location = Prod_stor_location;
        this.Production_unit = Production_unit;
        this.Created_On = Created_On;
        this.Availability_check = Availability_check;
        this.Loading_Group = Loading_Group;
        this.Tot_repl_lead_time = Tot_repl_lead_time;
        this.Underdely_tolerance = Underdely_tolerance;
        this.Unltd_Overdelivery = Unltd_Overdelivery;
        this.Overdely_tolerance = Overdely_tolerance;
        this.In_house_production = In_house_production;
        this.Prodn_Supervisor = Prodn_Supervisor;
        this.Backflush = Backflush;
        this.Individual_coll = Individual_coll;
        this.Rounding_value = Rounding_value;
        this.Base_Unit_of_Measure_1 = Base_Unit_of_Measure_1;
        this.Reorder_Point = Reorder_Point;
        this.Base_Unit_of_Measure_2 = Base_Unit_of_Measure_2;
        this.Assembly_scrap_porcentaje = Assembly_scrap_porcentaje;
        this.GR_processing_time = GR_processing_time;
        this.Unit_of_issue = Unit_of_issue;
        this.Valid_from = Valid_from;
        this.Batch_management_1 = Batch_management_1;
        this.Valuation_Category = Valuation_Category;
        this.DF_at_plant_level = DF_at_plant_level;
        this.DF_at_client_level = DF_at_client_level;
        this.Old_material_number = Old_material_number;
        this.Order_Unit = Order_Unit;
        this.Lab_Office = Lab_Office;
        this.Lab_description = Lab_description;
        this.Gross_Weight = Gross_Weight;
        this.Weight_Unit_1 = Weight_Unit_1;
        this.Net_Weight = Net_Weight;
        this.Weight_Unit_2 = Weight_Unit_2;
        this.Container_reqmts = Container_reqmts;
        this.Storage_conditions = Storage_conditions;
        this.Temp_conditions = Temp_conditions;
        this.Transportation_Group = Transportation_Group;
        this.Batch_management_2 = Batch_management_2;
        this.Packaging_mat_type = Packaging_mat_type;
        this.X_plant_matl_status = X_plant_matl_status;
        this.Min_Rem_Shelf_Life = Min_Rem_Shelf_Life;
        this.Total_shelf_life = Total_shelf_life;
        this.Language_Key = Language_Key;
        this.Created_By = Created_By;
        this.Safety_stock = Safety_stock;
        this.Base_Unit_of_Measure_3 = Base_Unit_of_Measure_3;
        this.Fixed_lot_size = Fixed_lot_size;
        this.Base_Unit_of_Measure_4 = Base_Unit_of_Measure_4;
        this.Maximum_Lot_Size_1 = Maximum_Lot_Size_1;
        this.Base_Unit_of_Measure_5 = Base_Unit_of_Measure_5;
        this.Minimum_Lot_Size_2 = Minimum_Lot_Size_2;
        this.Base_Unit_of_Measure_6 = Base_Unit_of_Measure_6;
        this.Costing_Lot_Size = Costing_Lot_Size;
        this.Base_Unit_of_Measure_7 = Base_Unit_of_Measure_7;
        this.Prod_Sched_Profile = Prod_Sched_Profile;
        this.Prod_Sched_Profile_Desc = Prod_Sched_Profile_Desc;
        this.Planned_Deliv_Time = Planned_Deliv_Time;
        this.Special_procurement = Special_procurement;
        this.SP_Key_Description = SP_Key_Description;
        this.ABC_Indicator = ABC_Indicator;
        this.STime_period_profile = STime_period_profile;
        this.Comm_imp_code_no = Comm_imp_code_no;
        this.Country_of_origin = Country_of_origin;
        this.SpecProcurem_Costing = SpecProcurem_Costing;
        this.Overhead_Group = Overhead_Group;
        this.TDS = TDS;
        this.Period_Indicator = Period_Indicator;
    }

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }

    public String getMaterial()
    {
        return Material;
    }

    public void setMaterial(String Material)
    {
        this.Material = Material;
    }

    public String getMaterial_Description()
    {
        return Material_Description;
    }

    public void setMaterial_Description(String Material_Description)
    {
        this.Material_Description = Material_Description;
    }

    public String getMaterial_Group()
    {
        return Material_Group;
    }

    public void setMaterial_Group(String Material_Group)
    {
        this.Material_Group = Material_Group;
    }

    public String getProfit_Center()
    {
        return Profit_Center;
    }

    public void setProfit_Center(String Profit_Center)
    {
        this.Profit_Center = Profit_Center;
    }

    public String getProduct_Hierarchy()
    {
        return Product_Hierarchy;
    }

    public void setProduct_Hierarchy(String Product_Hierarchy)
    {
        this.Product_Hierarchy = Product_Hierarchy;
    }

    public String getProduct_Hierarchy_Description()
    {
        return Product_Hierarchy_Description;
    }

    public void setProduct_Hierarchy_Description(String Product_Hierarchy_Description)
    {
        this.Product_Hierarchy_Description = Product_Hierarchy_Description;
    }

    public String getMaterial_Type()
    {
        return Material_Type;
    }

    public void setMaterial_Type(String Material_Type)
    {
        this.Material_Type = Material_Type;
    }

    public String getMaterial_type_descr()
    {
        return Material_type_descr;
    }

    public void setMaterial_type_descr(String Material_type_descr)
    {
        this.Material_type_descr = Material_type_descr;
    }

    public String getPlant()
    {
        return Plant;
    }

    public void setPlant(String Plant)
    {
        this.Plant = Plant;
    }

    public String getPlant_Name()
    {
        return Plant_Name;
    }

    public void setPlant_Name(String Plant_Name)
    {
        this.Plant_Name = Plant_Name;
    }

    public String getExt_Material_Group()
    {
        return Ext_Material_Group;
    }

    public void setExt_Material_Group(String Ext_Material_Group)
    {
        this.Ext_Material_Group = Ext_Material_Group;
    }

    public String getMatl_Grp_Pack_Matls()
    {
        return Matl_Grp_Pack_Matls;
    }

    public void setMatl_Grp_Pack_Matls(String Matl_Grp_Pack_Matls)
    {
        this.Matl_Grp_Pack_Matls = Matl_Grp_Pack_Matls;
    }

    public String getMGPM_Description()
    {
        return MGPM_Description;
    }

    public void setMGPM_Description(String MGPM_Description)
    {
        this.MGPM_Description = MGPM_Description;
    }

    public String getMRP_profile()
    {
        return MRP_profile;
    }

    public void setMRP_profile(String MRP_profile)
    {
        this.MRP_profile = MRP_profile;
    }

    public String getMRP_Profile_Description()
    {
        return MRP_Profile_Description;
    }

    public void setMRP_Profile_Description(String MRP_Profile_Description)
    {
        this.MRP_Profile_Description = MRP_Profile_Description;
    }

    public String getLot_size()
    {
        return Lot_size;
    }

    public void setLot_size(String Lot_size)
    {
        this.Lot_size = Lot_size;
    }

    public String getProcurement_type()
    {
        return Procurement_type;
    }

    public void setProcurement_type(String Procurement_type)
    {
        this.Procurement_type = Procurement_type;
    }

    public String getMRP_Controller()
    {
        return MRP_Controller;
    }

    public void setMRP_Controller(String MRP_Controller)
    {
        this.MRP_Controller = MRP_Controller;
    }

    public String getMRP_Type()
    {
        return MRP_Type;
    }

    public void setMRP_Type(String MRP_Type)
    {
        this.MRP_Type = MRP_Type;
    }

    public String getPurchasing_Group()
    {
        return Purchasing_Group;
    }

    public void setPurchasing_Group(String Purchasing_Group)
    {
        this.Purchasing_Group = Purchasing_Group;
    }

    public String getPlant_sp_matl_status()
    {
        return Plant_sp_matl_status;
    }

    public void setPlant_sp_matl_status(String Plant_sp_matl_status)
    {
        this.Plant_sp_matl_status = Plant_sp_matl_status;
    }

    public String getMat_Status_Description()
    {
        return Mat_Status_Description;
    }

    public void setMat_Status_Description(String Mat_Status_Description)
    {
        this.Mat_Status_Description = Mat_Status_Description;
    }

    public String getDo_Not_Cost()
    {
        return Do_Not_Cost;
    }

    public void setDo_Not_Cost(String Do_Not_Cost)
    {
        this.Do_Not_Cost = Do_Not_Cost;
    }

    public String getStorage_loc_for_EP()
    {
        return Storage_loc_for_EP;
    }

    public void setStorage_loc_for_EP(String Storage_loc_for_EP)
    {
        this.Storage_loc_for_EP = Storage_loc_for_EP;
    }

    public String getProd_stor_location()
    {
        return Prod_stor_location;
    }

    public void setProd_stor_location(String Prod_stor_location)
    {
        this.Prod_stor_location = Prod_stor_location;
    }

    public String getProduction_unit()
    {
        return Production_unit;
    }

    public void setProduction_unit(String Production_unit)
    {
        this.Production_unit = Production_unit;
    }

    public String getCreated_On()
    {
        return Created_On;
    }

    public void setCreated_On(String Created_On)
    {
        this.Created_On = Created_On;
    }

    public String getAvailability_check()
    {
        return Availability_check;
    }

    public void setAvailability_check(String Availability_check)
    {
        this.Availability_check = Availability_check;
    }

    public String getLoading_Group()
    {
        return Loading_Group;
    }

    public void setLoading_Group(String Loading_Group)
    {
        this.Loading_Group = Loading_Group;
    }

    public String getTot_repl_lead_time()
    {
        return Tot_repl_lead_time;
    }

    public void setTot_repl_lead_time(String Tot_repl_lead_time)
    {
        this.Tot_repl_lead_time = Tot_repl_lead_time;
    }

    public String getUnderdely_tolerance()
    {
        return Underdely_tolerance;
    }

    public void setUnderdely_tolerance(String Underdely_tolerance)
    {
        this.Underdely_tolerance = Underdely_tolerance;
    }

    public String getUnltd_Overdelivery()
    {
        return Unltd_Overdelivery;
    }

    public void setUnltd_Overdelivery(String Unltd_Overdelivery)
    {
        this.Unltd_Overdelivery = Unltd_Overdelivery;
    }

    public String getOverdely_tolerance()
    {
        return Overdely_tolerance;
    }

    public void setOverdely_tolerance(String Overdely_tolerance)
    {
        this.Overdely_tolerance = Overdely_tolerance;
    }

    public String getIn_house_production()
    {
        return In_house_production;
    }

    public void setIn_house_production(String In_house_production)
    {
        this.In_house_production = In_house_production;
    }

    public String getProdn_Supervisor()
    {
        return Prodn_Supervisor;
    }

    public void setProdn_Supervisor(String Prodn_Supervisor)
    {
        this.Prodn_Supervisor = Prodn_Supervisor;
    }

    public String getBackflush()
    {
        return Backflush;
    }

    public void setBackflush(String Backflush)
    {
        this.Backflush = Backflush;
    }

    public String getIndividual_coll()
    {
        return Individual_coll;
    }

    public void setIndividual_coll(String Individual_coll)
    {
        this.Individual_coll = Individual_coll;
    }

    public String getRounding_value()
    {
        return Rounding_value;
    }

    public void setRounding_value(String Rounding_value)
    {
        this.Rounding_value = Rounding_value;
    }

    public String getBase_Unit_of_Measure_1()
    {
        return Base_Unit_of_Measure_1;
    }

    public void setBase_Unit_of_Measure_1(String Base_Unit_of_Measure_1)
    {
        this.Base_Unit_of_Measure_1 = Base_Unit_of_Measure_1;
    }

    public String getReorder_Point()
    {
        return Reorder_Point;
    }

    public void setReorder_Point(String Reorder_Point)
    {
        this.Reorder_Point = Reorder_Point;
    }

    public String getBase_Unit_of_Measure_2()
    {
        return Base_Unit_of_Measure_2;
    }

    public void setBase_Unit_of_Measure_2(String Base_Unit_of_Measure_2)
    {
        this.Base_Unit_of_Measure_2 = Base_Unit_of_Measure_2;
    }

    public String getAssembly_scrap_porcentaje()
    {
        return Assembly_scrap_porcentaje;
    }

    public void setAssembly_scrap_porcentaje(String Assembly_scrap_porcentaje)
    {
        this.Assembly_scrap_porcentaje = Assembly_scrap_porcentaje;
    }

    public String getGR_processing_time()
    {
        return GR_processing_time;
    }

    public void setGR_processing_time(String GR_processing_time)
    {
        this.GR_processing_time = GR_processing_time;
    }

    public String getUnit_of_issue()
    {
        return Unit_of_issue;
    }

    public void setUnit_of_issue(String Unit_of_issue)
    {
        this.Unit_of_issue = Unit_of_issue;
    }

    public String getValid_from()
    {
        return Valid_from;
    }

    public void setValid_from(String Valid_from)
    {
        this.Valid_from = Valid_from;
    }

    public String getBatch_management_1()
    {
        return Batch_management_1;
    }

    public void setBatch_management_1(String Batch_management_1)
    {
        this.Batch_management_1 = Batch_management_1;
    }

    public String getValuation_Category()
    {
        return Valuation_Category;
    }

    public void setValuation_Category(String Valuation_Category)
    {
        this.Valuation_Category = Valuation_Category;
    }

    public String getDF_at_plant_level()
    {
        return DF_at_plant_level;
    }

    public void setDF_at_plant_level(String DF_at_plant_level)
    {
        this.DF_at_plant_level = DF_at_plant_level;
    }

    public String getDF_at_client_level()
    {
        return DF_at_client_level;
    }

    public void setDF_at_client_level(String DF_at_client_level)
    {
        this.DF_at_client_level = DF_at_client_level;
    }

    public String getOld_material_number()
    {
        return Old_material_number;
    }

    public void setOld_material_number(String Old_material_number)
    {
        this.Old_material_number = Old_material_number;
    }

    public String getOrder_Unit()
    {
        return Order_Unit;
    }

    public void setOrder_Unit(String Order_Unit)
    {
        this.Order_Unit = Order_Unit;
    }

    public String getLab_Office()
    {
        return Lab_Office;
    }

    public void setLab_Office(String Lab_Office)
    {
        this.Lab_Office = Lab_Office;
    }

    public String getLab_description()
    {
        return Lab_description;
    }

    public void setLab_description(String Lab_description)
    {
        this.Lab_description = Lab_description;
    }

    public String getGross_Weight()
    {
        return Gross_Weight;
    }

    public void setGross_Weight(String Gross_Weight)
    {
        this.Gross_Weight = Gross_Weight;
    }

    public String getWeight_Unit_1()
    {
        return Weight_Unit_1;
    }

    public void setWeight_Unit_1(String Weight_Unit_1)
    {
        this.Weight_Unit_1 = Weight_Unit_1;
    }

    public String getNet_Weight()
    {
        return Net_Weight;
    }

    public void setNet_Weight(String Net_Weight)
    {
        this.Net_Weight = Net_Weight;
    }

    public String getWeight_Unit_2()
    {
        return Weight_Unit_2;
    }

    public void setWeight_Unit_2(String Weight_Unit_2)
    {
        this.Weight_Unit_2 = Weight_Unit_2;
    }

    public String getContainer_reqmts()
    {
        return Container_reqmts;
    }

    public void setContainer_reqmts(String Container_reqmts)
    {
        this.Container_reqmts = Container_reqmts;
    }

    public String getStorage_conditions()
    {
        return Storage_conditions;
    }

    public void setStorage_conditions(String Storage_conditions)
    {
        this.Storage_conditions = Storage_conditions;
    }

    public String getTemp_conditions()
    {
        return Temp_conditions;
    }

    public void setTemp_conditions(String Temp_conditions)
    {
        this.Temp_conditions = Temp_conditions;
    }

    public String getTransportation_Group()
    {
        return Transportation_Group;
    }

    public void setTransportation_Group(String Transportation_Group)
    {
        this.Transportation_Group = Transportation_Group;
    }

    public String getBatch_management_2()
    {
        return Batch_management_2;
    }

    public void setBatch_management_2(String Batch_management_2)
    {
        this.Batch_management_2 = Batch_management_2;
    }

    public String getPackaging_mat_type()
    {
        return Packaging_mat_type;
    }

    public void setPackaging_mat_type(String Packaging_mat_type)
    {
        this.Packaging_mat_type = Packaging_mat_type;
    }

    public String getX_plant_matl_status()
    {
        return X_plant_matl_status;
    }

    public void setX_plant_matl_status(String X_plant_matl_status)
    {
        this.X_plant_matl_status = X_plant_matl_status;
    }

    public String getMin_Rem_Shelf_Life()
    {
        return Min_Rem_Shelf_Life;
    }

    public void setMin_Rem_Shelf_Life(String Min_Rem_Shelf_Life)
    {
        this.Min_Rem_Shelf_Life = Min_Rem_Shelf_Life;
    }

    public String getTotal_shelf_life()
    {
        return Total_shelf_life;
    }

    public void setTotal_shelf_life(String Total_shelf_life)
    {
        this.Total_shelf_life = Total_shelf_life;
    }

    public String getLanguage_Key()
    {
        return Language_Key;
    }

    public void setLanguage_Key(String Language_Key)
    {
        this.Language_Key = Language_Key;
    }

    public String getCreated_By()
    {
        return Created_By;
    }

    public void setCreated_By(String Created_By)
    {
        this.Created_By = Created_By;
    }

    public String getSafety_stock()
    {
        return Safety_stock;
    }

    public void setSafety_stock(String Safety_stock)
    {
        this.Safety_stock = Safety_stock;
    }

    public String getBase_Unit_of_Measure_3()
    {
        return Base_Unit_of_Measure_3;
    }

    public void setBase_Unit_of_Measure_3(String Base_Unit_of_Measure_3)
    {
        this.Base_Unit_of_Measure_3 = Base_Unit_of_Measure_3;
    }

    public String getFixed_lot_size()
    {
        return Fixed_lot_size;
    }

    public void setFixed_lot_size(String Fixed_lot_size)
    {
        this.Fixed_lot_size = Fixed_lot_size;
    }

    public String getBase_Unit_of_Measure_4()
    {
        return Base_Unit_of_Measure_4;
    }

    public void setBase_Unit_of_Measure_4(String Base_Unit_of_Measure_4)
    {
        this.Base_Unit_of_Measure_4 = Base_Unit_of_Measure_4;
    }

    public String getMaximum_Lot_Size_1()
    {
        return Maximum_Lot_Size_1;
    }

    public void setMaximum_Lot_Size_1(String Maximum_Lot_Size_1)
    {
        this.Maximum_Lot_Size_1 = Maximum_Lot_Size_1;
    }

    public String getBase_Unit_of_Measure_5()
    {
        return Base_Unit_of_Measure_5;
    }

    public void setBase_Unit_of_Measure_5(String Base_Unit_of_Measure_5)
    {
        this.Base_Unit_of_Measure_5 = Base_Unit_of_Measure_5;
    }

    public String getMinimum_Lot_Size_2()
    {
        return Minimum_Lot_Size_2;
    }

    public void setMinimum_Lot_Size_2(String Minimum_Lot_Size_2)
    {
        this.Minimum_Lot_Size_2 = Minimum_Lot_Size_2;
    }

    public String getBase_Unit_of_Measure_6()
    {
        return Base_Unit_of_Measure_6;
    }

    public void setBase_Unit_of_Measure_6(String Base_Unit_of_Measure_6)
    {
        this.Base_Unit_of_Measure_6 = Base_Unit_of_Measure_6;
    }

    public String getCosting_Lot_Size()
    {
        return Costing_Lot_Size;
    }

    public void setCosting_Lot_Size(String Costing_Lot_Size)
    {
        this.Costing_Lot_Size = Costing_Lot_Size;
    }

    public String getBase_Unit_of_Measure_7()
    {
        return Base_Unit_of_Measure_7;
    }

    public void setBase_Unit_of_Measure_7(String Base_Unit_of_Measure_7)
    {
        this.Base_Unit_of_Measure_7 = Base_Unit_of_Measure_7;
    }

    public String getProd_Sched_Profile()
    {
        return Prod_Sched_Profile;
    }

    public void setProd_Sched_Profile(String Prod_Sched_Profile)
    {
        this.Prod_Sched_Profile = Prod_Sched_Profile;
    }

    public String getProd_Sched_Profile_Desc()
    {
        return Prod_Sched_Profile_Desc;
    }

    public void setProd_Sched_Profile_Desc(String Prod_Sched_Profile_Desc)
    {
        this.Prod_Sched_Profile_Desc = Prod_Sched_Profile_Desc;
    }

    public String getPlanned_Deliv_Time()
    {
        return Planned_Deliv_Time;
    }

    public void setPlanned_Deliv_Time(String Planned_Deliv_Time)
    {
        this.Planned_Deliv_Time = Planned_Deliv_Time;
    }

    public String getSpecial_procurement()
    {
        return Special_procurement;
    }

    public void setSpecial_procurement(String Special_procurement)
    {
        this.Special_procurement = Special_procurement;
    }

    public String getSP_Key_Description()
    {
        return SP_Key_Description;
    }

    public void setSP_Key_Description(String SP_Key_Description)
    {
        this.SP_Key_Description = SP_Key_Description;
    }

    public String getABC_Indicator()
    {
        return ABC_Indicator;
    }

    public void setABC_Indicator(String ABC_Indicator)
    {
        this.ABC_Indicator = ABC_Indicator;
    }

    public String getSTime_period_profile()
    {
        return STime_period_profile;
    }

    public void setSTime_period_profile(String STime_period_profile)
    {
        this.STime_period_profile = STime_period_profile;
    }

    public String getComm_imp_code_no()
    {
        return Comm_imp_code_no;
    }

    public void setComm_imp_code_no(String Comm_imp_code_no)
    {
        this.Comm_imp_code_no = Comm_imp_code_no;
    }

    public String getCountry_of_origin()
    {
        return Country_of_origin;
    }

    public void setCountry_of_origin(String Country_of_origin)
    {
        this.Country_of_origin = Country_of_origin;
    }

    public String getSpecProcurem_Costing()
    {
        return SpecProcurem_Costing;
    }

    public void setSpecProcurem_Costing(String SpecProcurem_Costing)
    {
        this.SpecProcurem_Costing = SpecProcurem_Costing;
    }

    public String getOverhead_Group()
    {
        return Overhead_Group;
    }

    public void setOverhead_Group(String Overhead_Group)
    {
        this.Overhead_Group = Overhead_Group;
    }

    public String getTDS()
    {
        return TDS;
    }

    public void setTDS(String TDS)
    {
        this.TDS = TDS;
    }

    public String getPeriod_Indicator()
    {
        return Period_Indicator;
    }

    public void setPeriod_Indicator(String Period_Indicator)
    {
        this.Period_Indicator = Period_Indicator;
    }
}
