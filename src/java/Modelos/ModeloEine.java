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
public class ModeloEine {

    Integer Id;
    String Purchasing_info_rec;
    String Plant;
    String Created_On;
    String Purchasing_Group;
    String Currency;
    String Standard_PO_Quantity;
    String Planned_Deliv_Time;
    String Net_Price;
    String Price_unit;
    String Order_Price_Unit;
    String Valid_to;
    String Quantity_Conversion_1;
    String Quantity_Conversion_2;
    String Effective_Price;
    String Acknowledgment_Reqd;
    String Confirmation_Control;
    String Incoterms_1;
    String Incoterms_2;
    String Material;
    String Vendor;
    String Link_Material_vendor;
    String Porcentaje_Additional;

    public ModeloEine() {
    }

    
    
    public ModeloEine(Integer Id, String Purchasing_info_rec, String Plant, String Created_On, String Purchasing_Group, String Currency, String Standard_PO_Quantity, String Planned_Deliv_Time, String Net_Price, String Price_unit, String Order_Price_Unit, String Valid_to, String Quantity_Conversion_1, String Quantity_Conversion_2, String Effective_Price, String Acknowledgment_Reqd, String Confirmation_Control, String Incoterms_1, String Incoterms_2, String Material, String Vendor, String Link_Material_vendor, String Porcentaje_Additional) {
        this.Id = Id;
        this.Purchasing_info_rec = Purchasing_info_rec;
        this.Plant = Plant;
        this.Created_On = Created_On;
        this.Purchasing_Group = Purchasing_Group;
        this.Currency = Currency;
        this.Standard_PO_Quantity = Standard_PO_Quantity;
        this.Planned_Deliv_Time = Planned_Deliv_Time;
        this.Net_Price = Net_Price;
        this.Price_unit = Price_unit;
        this.Order_Price_Unit = Order_Price_Unit;
        this.Valid_to = Valid_to;
        this.Quantity_Conversion_1 = Quantity_Conversion_1;
        this.Quantity_Conversion_2 = Quantity_Conversion_2;
        this.Effective_Price = Effective_Price;
        this.Acknowledgment_Reqd = Acknowledgment_Reqd;
        this.Confirmation_Control = Confirmation_Control;
        this.Incoterms_1 = Incoterms_1;
        this.Incoterms_2 = Incoterms_2;
        this.Material = Material;
        this.Vendor = Vendor;
        this.Link_Material_vendor = Link_Material_vendor;
        this.Porcentaje_Additional = Porcentaje_Additional;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getPurchasing_info_rec() {
        return Purchasing_info_rec;
    }

    public void setPurchasing_info_rec(String Purchasing_info_rec) {
        this.Purchasing_info_rec = Purchasing_info_rec;
    }

    public String getPlant() {
        return Plant;
    }

    public void setPlant(String Plant) {
        this.Plant = Plant;
    }

    public String getCreated_On() {
        return Created_On;
    }

    public void setCreated_On(String Created_On) {
        this.Created_On = Created_On;
    }

    public String getPurchasing_Group() {
        return Purchasing_Group;
    }

    public void setPurchasing_Group(String Purchasing_Group) {
        this.Purchasing_Group = Purchasing_Group;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getStandard_PO_Quantity() {
        return Standard_PO_Quantity;
    }

    public void setStandard_PO_Quantity(String Standard_PO_Quantity) {
        this.Standard_PO_Quantity = Standard_PO_Quantity;
    }

    public String getPlanned_Deliv_Time() {
        return Planned_Deliv_Time;
    }

    public void setPlanned_Deliv_Time(String Planned_Deliv_Time) {
        this.Planned_Deliv_Time = Planned_Deliv_Time;
    }

    public String getNet_Price() {
        return Net_Price;
    }

    public void setNet_Price(String Net_Price) {
        this.Net_Price = Net_Price;
    }

    public String getPrice_unit() {
        return Price_unit;
    }

    public void setPrice_unit(String Price_unit) {
        this.Price_unit = Price_unit;
    }

    public String getOrder_Price_Unit() {
        return Order_Price_Unit;
    }

    public void setOrder_Price_Unit(String Order_Price_Unit) {
        this.Order_Price_Unit = Order_Price_Unit;
    }

    public String getValid_to() {
        return Valid_to;
    }

    public void setValid_to(String Valid_to) {
        this.Valid_to = Valid_to;
    }

    public String getQuantity_Conversion_1() {
        return Quantity_Conversion_1;
    }

    public void setQuantity_Conversion_1(String Quantity_Conversion_1) {
        this.Quantity_Conversion_1 = Quantity_Conversion_1;
    }

    public String getQuantity_Conversion_2() {
        return Quantity_Conversion_2;
    }

    public void setQuantity_Conversion_2(String Quantity_Conversion_2) {
        this.Quantity_Conversion_2 = Quantity_Conversion_2;
    }

    public String getEffective_Price() {
        return Effective_Price;
    }

    public void setEffective_Price(String Effective_Price) {
        this.Effective_Price = Effective_Price;
    }

    public String getAcknowledgment_Reqd() {
        return Acknowledgment_Reqd;
    }

    public void setAcknowledgment_Reqd(String Acknowledgment_Reqd) {
        this.Acknowledgment_Reqd = Acknowledgment_Reqd;
    }

    public String getConfirmation_Control() {
        return Confirmation_Control;
    }

    public void setConfirmation_Control(String Confirmation_Control) {
        this.Confirmation_Control = Confirmation_Control;
    }

    public String getIncoterms_1() {
        return Incoterms_1;
    }

    public void setIncoterms_1(String Incoterms_1) {
        this.Incoterms_1 = Incoterms_1;
    }

    public String getIncoterms_2() {
        return Incoterms_2;
    }

    public void setIncoterms_2(String Incoterms_2) {
        this.Incoterms_2 = Incoterms_2;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String Vendor) {
        this.Vendor = Vendor;
    }

    public String getLink_Material_vendor() {
        return Link_Material_vendor;
    }

    public void setLink_Material_vendor(String Link_Material_vendor) {
        this.Link_Material_vendor = Link_Material_vendor;
    }

    public String getPorcentaje_Additional() {
        return Porcentaje_Additional;
    }

    public void setPorcentaje_Additional(String Porcentaje_Additional) {
        this.Porcentaje_Additional = Porcentaje_Additional;
    }
    
    
}
