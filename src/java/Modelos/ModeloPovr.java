/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ModeloPovr {

    Integer Id;
    String Plant;
    String Material;
    String Material_Description;
    String Process_Order_Number;
    String Process_Order_Type;
    String Material_Group_Packaging;
    String Profit_Center;
    String UOM;
    String Material_type;
    String Existing_Material_Costing_Lot_Size;
    String MRP_Planned_Quantity;
    String Consumption_Qty_Actual_I_P;
    String Delivered_Qty_Actual_O_P;
    String Yield_Variance_Qty;
    String Yield_Variance_Porcentaje;
    String Actual_Material_Cost;
    String Labor_Cost_Actual;
    String Machine_Cost_Actual;
    String Overhead_Cost_Actual;
    String Total_Cost_Actual_Output;
    String Physical_Inventory_Yield_Loss_cost_Actu;
    String Standard_Price;
    String Calculated_Production_Variance;
    String Conversion_Cost_Per_Unit;
    String Display_Currency;
    String Batch_Number;
    String Product_Hierarchy;
    String Actual_Start_Date;
    String Actual_Finish_Date;

    public ModeloPovr(Integer Id, String Plant, String Material, String Material_Description, String Process_Order_Number, String Process_Order_Type, String Material_Group_Packaging, String Profit_Center, String UOM, String Material_type, String Existing_Material_Costing_Lot_Size, String MRP_Planned_Quantity, String Consumption_Qty_Actual_I_P, String Delivered_Qty_Actual_O_P, String Yield_Variance_Qty, String Yield_Variance_Porcentaje, String Actual_Material_Cost, String Labor_Cost_Actual, String Machine_Cost_Actual, String Overhead_Cost_Actual, String Total_Cost_Actual_Output, String Physical_Inventory_Yield_Loss_cost_Actu, String Standard_Price, String Calculated_Production_Variance, String Conversion_Cost_Per_Unit, String Display_Currency, String Batch_Number, String Product_Hierarchy, String Actual_Start_Date, String Actual_Finish_Date) {
        this.Id = Id;
        this.Plant = Plant;
        this.Material = Material;
        this.Material_Description = Material_Description;
        this.Process_Order_Number = Process_Order_Number;
        this.Process_Order_Type = Process_Order_Type;
        this.Material_Group_Packaging = Material_Group_Packaging;
        this.Profit_Center = Profit_Center;
        this.UOM = UOM;
        this.Material_type = Material_type;
        this.Existing_Material_Costing_Lot_Size = Existing_Material_Costing_Lot_Size;
        this.MRP_Planned_Quantity = MRP_Planned_Quantity;
        this.Consumption_Qty_Actual_I_P = Consumption_Qty_Actual_I_P;
        this.Delivered_Qty_Actual_O_P = Delivered_Qty_Actual_O_P;
        this.Yield_Variance_Qty = Yield_Variance_Qty;
        this.Yield_Variance_Porcentaje = Yield_Variance_Porcentaje;
        this.Actual_Material_Cost = Actual_Material_Cost;
        this.Labor_Cost_Actual = Labor_Cost_Actual;
        this.Machine_Cost_Actual = Machine_Cost_Actual;
        this.Overhead_Cost_Actual = Overhead_Cost_Actual;
        this.Total_Cost_Actual_Output = Total_Cost_Actual_Output;
        this.Physical_Inventory_Yield_Loss_cost_Actu = Physical_Inventory_Yield_Loss_cost_Actu;
        this.Standard_Price = Standard_Price;
        this.Calculated_Production_Variance = Calculated_Production_Variance;
        this.Conversion_Cost_Per_Unit = Conversion_Cost_Per_Unit;
        this.Display_Currency = Display_Currency;
        this.Batch_Number = Batch_Number;
        this.Product_Hierarchy = Product_Hierarchy;
        this.Actual_Start_Date = Actual_Start_Date;
        this.Actual_Finish_Date = Actual_Finish_Date;
    }

    public ModeloPovr() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getPlant() {
        return Plant;
    }

    public void setPlant(String Plant) {
        this.Plant = Plant;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }

    public String getMaterial_Description() {
        return Material_Description;
    }

    public void setMaterial_Description(String Material_Description) {
        this.Material_Description = Material_Description;
    }

    public String getProcess_Order_Number() {
        return Process_Order_Number;
    }

    public void setProcess_Order_Number(String Process_Order_Number) {
        this.Process_Order_Number = Process_Order_Number;
    }

    public String getProcess_Order_Type() {
        return Process_Order_Type;
    }

    public void setProcess_Order_Type(String Process_Order_Type) {
        this.Process_Order_Type = Process_Order_Type;
    }

    public String getMaterial_Group_Packaging() {
        return Material_Group_Packaging;
    }

    public void setMaterial_Group_Packaging(String Material_Group_Packaging) {
        this.Material_Group_Packaging = Material_Group_Packaging;
    }

    public String getProfit_Center() {
        return Profit_Center;
    }

    public void setProfit_Center(String Profit_Center) {
        this.Profit_Center = Profit_Center;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getMaterial_type() {
        return Material_type;
    }

    public void setMaterial_type(String Material_type) {
        this.Material_type = Material_type;
    }

    public String getExisting_Material_Costing_Lot_Size() {
        return Existing_Material_Costing_Lot_Size;
    }

    public void setExisting_Material_Costing_Lot_Size(String Existing_Material_Costing_Lot_Size) {
        this.Existing_Material_Costing_Lot_Size = Existing_Material_Costing_Lot_Size;
    }

    public String getMRP_Planned_Quantity() {
        return MRP_Planned_Quantity;
    }

    public void setMRP_Planned_Quantity(String MRP_Planned_Quantity) {
        this.MRP_Planned_Quantity = MRP_Planned_Quantity;
    }

    public String getConsumption_Qty_Actual_I_P() {
        return Consumption_Qty_Actual_I_P;
    }

    public void setConsumption_Qty_Actual_I_P(String Consumption_Qty_Actual_I_P) {
        this.Consumption_Qty_Actual_I_P = Consumption_Qty_Actual_I_P;
    }

    public String getDelivered_Qty_Actual_O_P() {
        return Delivered_Qty_Actual_O_P;
    }

    public void setDelivered_Qty_Actual_O_P(String Delivered_Qty_Actual_O_P) {
        this.Delivered_Qty_Actual_O_P = Delivered_Qty_Actual_O_P;
    }

    public String getYield_Variance_Qty() {
        return Yield_Variance_Qty;
    }

    public void setYield_Variance_Qty(String Yield_Variance_Qty) {
        this.Yield_Variance_Qty = Yield_Variance_Qty;
    }

    public String getYield_Variance_Porcentaje() {
        return Yield_Variance_Porcentaje;
    }

    public void setYield_Variance_Porcentaje(String Yield_Variance_Porcentaje) {
        this.Yield_Variance_Porcentaje = Yield_Variance_Porcentaje;
    }

    public String getActual_Material_Cost() {
        return Actual_Material_Cost;
    }

    public void setActual_Material_Cost(String Actual_Material_Cost) {
        this.Actual_Material_Cost = Actual_Material_Cost;
    }

    public String getLabor_Cost_Actual() {
        return Labor_Cost_Actual;
    }

    public void setLabor_Cost_Actual(String Labor_Cost_Actual) {
        this.Labor_Cost_Actual = Labor_Cost_Actual;
    }

    public String getMachine_Cost_Actual() {
        return Machine_Cost_Actual;
    }

    public void setMachine_Cost_Actual(String Machine_Cost_Actual) {
        this.Machine_Cost_Actual = Machine_Cost_Actual;
    }

    public String getOverhead_Cost_Actual() {
        return Overhead_Cost_Actual;
    }

    public void setOverhead_Cost_Actual(String Overhead_Cost_Actual) {
        this.Overhead_Cost_Actual = Overhead_Cost_Actual;
    }

    public String getTotal_Cost_Actual_Output() {
        return Total_Cost_Actual_Output;
    }

    public void setTotal_Cost_Actual_Output(String Total_Cost_Actual_Output) {
        this.Total_Cost_Actual_Output = Total_Cost_Actual_Output;
    }

    public String getPhysical_Inventory_Yield_Loss_cost_Actu() {
        return Physical_Inventory_Yield_Loss_cost_Actu;
    }

    public void setPhysical_Inventory_Yield_Loss_cost_Actu(String Physical_Inventory_Yield_Loss_cost_Actu) {
        this.Physical_Inventory_Yield_Loss_cost_Actu = Physical_Inventory_Yield_Loss_cost_Actu;
    }

    public String getStandard_Price() {
        return Standard_Price;
    }

    public void setStandard_Price(String Standard_Price) {
        this.Standard_Price = Standard_Price;
    }

    public String getCalculated_Production_Variance() {
        return Calculated_Production_Variance;
    }

    public void setCalculated_Production_Variance(String Calculated_Production_Variance) {
        this.Calculated_Production_Variance = Calculated_Production_Variance;
    }

    public String getConversion_Cost_Per_Unit() {
        return Conversion_Cost_Per_Unit;
    }

    public void setConversion_Cost_Per_Unit(String Conversion_Cost_Per_Unit) {
        this.Conversion_Cost_Per_Unit = Conversion_Cost_Per_Unit;
    }

    public String getDisplay_Currency() {
        return Display_Currency;
    }

    public void setDisplay_Currency(String Display_Currency) {
        this.Display_Currency = Display_Currency;
    }

    public String getBatch_Number() {
        return Batch_Number;
    }

    public void setBatch_Number(String Batch_Number) {
        this.Batch_Number = Batch_Number;
    }

    public String getProduct_Hierarchy() {
        return Product_Hierarchy;
    }

    public void setProduct_Hierarchy(String Product_Hierarchy) {
        this.Product_Hierarchy = Product_Hierarchy;
    }

    public String getActual_Start_Date() {
        return Actual_Start_Date;
    }

    public void setActual_Start_Date(String Actual_Start_Date) {
        this.Actual_Start_Date = Actual_Start_Date;
    }

    public String getActual_Finish_Date() {
        return Actual_Finish_Date;
    }

    public void setActual_Finish_Date(String Actual_Finish_Date) {
        this.Actual_Finish_Date = Actual_Finish_Date;
    }
    
    

}
