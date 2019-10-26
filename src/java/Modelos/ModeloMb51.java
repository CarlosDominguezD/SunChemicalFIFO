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
public class ModeloMb51
{

    Integer Id;
    String Plant;
    String Purchase_order;
    String Material;
    String Material_Description;
    String Batch;
    String Movement_type;
    String Movement_Type_Text;
    String Item;
    String Quantity;
    String Qty_in_unit_of_entry;
    String Unit_of_Entry;
    String Amt_in_loc_cur;
    String Currency;
    String Storage_Location;
    String Posting_Date;
    String Document_Date;
    String Material_Document;
    String User_Name;
    String Vendor;
    String Order;
    String Vendor_Name;
    String Vendor_Type;
    String Month;
    String Period;
    String Material_Type;
    String Profit_Center;
    String link1_PO_Mas_Material;
    String link2_PO_Mas_position;
    String Referencia_Y_vendor;
    String TotalQ_ME80FN;
    String TotalQ_Porcentaje;
    String TOTAL_INVOICE_VALUE;
    String Factura_Value_Unit;
    String PIR_Porcentaje;
    String Moneda;
    String FreightString;
    String Dutys;
    String Arancel;
    String Ajuste_PIR;
    String Otros;
    String Total_Costos_Adicionales;
    String Participac_Adicionales;
    String Total_Costos;
    String Unitario_final_FIFO;
    String Unitario_estandar;
    String Porcentaje_Real_Vs_Estándar;

    public ModeloMb51()
    {
    }

    public ModeloMb51(Integer Id, String Plant, String Purchase_order, String Material, String Material_Description, String Batch, String Movement_type, String Movement_Type_Text, String Item, String Quantity, String Qty_in_unit_of_entry, String Unit_of_Entry, String Amt_in_loc_cur, String Currency, String Storage_Location, String Posting_Date, String Document_Date, String Material_Document, String User_Name, String Vendor, String Order, String Vendor_Name, String Vendor_Type, String Month, String Period, String Material_Type, String Profit_Center, String link1_PO_Mas_Material, String link2_PO_Mas_position, String Referencia_Y_vendor, String TotalQ_ME80FN, String TotalQ_Porcentaje, String TOTAL_INVOICE_VALUE, String Factura_Value_Unit, String PIR_Porcentaje, String Moneda, String FreightString, String Dutys, String Arancel, String Ajuste_PIR, String Otros, String Total_Costos_Adicionales, String Participac_Adicionales, String Total_Costos, String Unitario_final_FIFO, String Unitario_estandar, String Porcentaje_Real_Vs_Estándar)
    {
        this.Id = Id;
        this.Plant = Plant;
        this.Purchase_order = Purchase_order;
        this.Material = Material;
        this.Material_Description = Material_Description;
        this.Batch = Batch;
        this.Movement_type = Movement_type;
        this.Movement_Type_Text = Movement_Type_Text;
        this.Item = Item;
        this.Quantity = Quantity;
        this.Qty_in_unit_of_entry = Qty_in_unit_of_entry;
        this.Unit_of_Entry = Unit_of_Entry;
        this.Amt_in_loc_cur = Amt_in_loc_cur;
        this.Currency = Currency;
        this.Storage_Location = Storage_Location;
        this.Posting_Date = Posting_Date;
        this.Document_Date = Document_Date;
        this.Material_Document = Material_Document;
        this.User_Name = User_Name;
        this.Vendor = Vendor;
        this.Order = Order;
        this.Vendor_Name = Vendor_Name;
        this.Vendor_Type = Vendor_Type;
        this.Month = Month;
        this.Period = Period;
        this.Material_Type = Material_Type;
        this.Profit_Center = Profit_Center;
        this.link1_PO_Mas_Material = link1_PO_Mas_Material;
        this.link2_PO_Mas_position = link2_PO_Mas_position;
        this.Referencia_Y_vendor = Referencia_Y_vendor;
        this.TotalQ_ME80FN = TotalQ_ME80FN;
        this.TotalQ_Porcentaje = TotalQ_Porcentaje;
        this.TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE;
        this.Factura_Value_Unit = Factura_Value_Unit;
        this.PIR_Porcentaje = PIR_Porcentaje;
        this.Moneda = Moneda;
        this.FreightString = FreightString;
        this.Dutys = Dutys;
        this.Arancel = Arancel;
        this.Ajuste_PIR = Ajuste_PIR;
        this.Otros = Otros;
        this.Total_Costos_Adicionales = Total_Costos_Adicionales;
        this.Participac_Adicionales = Participac_Adicionales;
        this.Total_Costos = Total_Costos;
        this.Unitario_final_FIFO = Unitario_final_FIFO;
        this.Unitario_estandar = Unitario_estandar;
        this.Porcentaje_Real_Vs_Estándar = Porcentaje_Real_Vs_Estándar;
    }

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }

    public String getPlant()
    {
        return Plant;
    }

    public void setPlant(String Plant)
    {
        this.Plant = Plant;
    }

    public String getPurchase_order()
    {
        return Purchase_order;
    }

    public void setPurchase_order(String Purchase_order)
    {
        this.Purchase_order = Purchase_order;
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

    public String getBatch()
    {
        return Batch;
    }

    public void setBatch(String Batch)
    {
        this.Batch = Batch;
    }

    public String getMovement_type()
    {
        return Movement_type;
    }

    public void setMovement_type(String Movement_type)
    {
        this.Movement_type = Movement_type;
    }

    public String getMovement_Type_Text()
    {
        return Movement_Type_Text;
    }

    public void setMovement_Type_Text(String Movement_Type_Text)
    {
        this.Movement_Type_Text = Movement_Type_Text;
    }

    public String getItem()
    {
        return Item;
    }

    public void setItem(String Item)
    {
        this.Item = Item;
    }

    public String getQuantity()
    {
        return Quantity;
    }

    public void setQuantity(String Quantity)
    {
        this.Quantity = Quantity;
    }

    public String getQty_in_unit_of_entry()
    {
        return Qty_in_unit_of_entry;
    }

    public void setQty_in_unit_of_entry(String Qty_in_unit_of_entry)
    {
        this.Qty_in_unit_of_entry = Qty_in_unit_of_entry;
    }

    public String getUnit_of_Entry()
    {
        return Unit_of_Entry;
    }

    public void setUnit_of_Entry(String Unit_of_Entry)
    {
        this.Unit_of_Entry = Unit_of_Entry;
    }

    public String getAmt_in_loc_cur()
    {
        return Amt_in_loc_cur;
    }

    public void setAmt_in_loc_cur(String Amt_in_loc_cur)
    {
        this.Amt_in_loc_cur = Amt_in_loc_cur;
    }

    public String getCurrency()
    {
        return Currency;
    }

    public void setCurrency(String Currency)
    {
        this.Currency = Currency;
    }

    public String getStorage_Location()
    {
        return Storage_Location;
    }

    public void setStorage_Location(String Storage_Location)
    {
        this.Storage_Location = Storage_Location;
    }

    public String getPosting_Date()
    {
        return Posting_Date;
    }

    public void setPosting_Date(String Posting_Date)
    {
        this.Posting_Date = Posting_Date;
    }

    public String getDocument_Date()
    {
        return Document_Date;
    }

    public void setDocument_Date(String Document_Date)
    {
        this.Document_Date = Document_Date;
    }

    public String getMaterial_Document()
    {
        return Material_Document;
    }

    public void setMaterial_Document(String Material_Document)
    {
        this.Material_Document = Material_Document;
    }

    public String getUser_Name()
    {
        return User_Name;
    }

    public void setUser_Name(String User_Name)
    {
        this.User_Name = User_Name;
    }

    public String getVendor()
    {
        return Vendor;
    }

    public void setVendor(String Vendor)
    {
        this.Vendor = Vendor;
    }

    public String getOrder()
    {
        return Order;
    }

    public void setOrder(String Order)
    {
        this.Order = Order;
    }

    public String getVendor_Name()
    {
        return Vendor_Name;
    }

    public void setVendor_Name(String Vendor_Name)
    {
        this.Vendor_Name = Vendor_Name;
    }

    public String getVendor_Type()
    {
        return Vendor_Type;
    }

    public void setVendor_Type(String Vendor_Type)
    {
        this.Vendor_Type = Vendor_Type;
    }

    public String getMonth()
    {
        return Month;
    }

    public void setMonth(String Month)
    {
        this.Month = Month;
    }

    public String getPeriod()
    {
        return Period;
    }

    public void setPeriod(String Period)
    {
        this.Period = Period;
    }

    public String getMaterial_Type()
    {
        return Material_Type;
    }

    public void setMaterial_Type(String Material_Type)
    {
        this.Material_Type = Material_Type;
    }

    public String getProfit_Center()
    {
        return Profit_Center;
    }

    public void setProfit_Center(String Profit_Center)
    {
        this.Profit_Center = Profit_Center;
    }

    public String getLink1_PO_Mas_Material()
    {
        return link1_PO_Mas_Material;
    }

    public void setLink1_PO_Mas_Material(String link1_PO_Mas_Material)
    {
        this.link1_PO_Mas_Material = link1_PO_Mas_Material;
    }

    public String getLink2_PO_Mas_position()
    {
        return link2_PO_Mas_position;
    }

    public void setLink2_PO_Mas_position(String link2_PO_Mas_position)
    {
        this.link2_PO_Mas_position = link2_PO_Mas_position;
    }

    public String getReferencia_Y_vendor()
    {
        return Referencia_Y_vendor;
    }

    public void setReferencia_Y_vendor(String Referencia_Y_vendor)
    {
        this.Referencia_Y_vendor = Referencia_Y_vendor;
    }

    public String getTotalQ_ME80FN()
    {
        return TotalQ_ME80FN;
    }

    public void setTotalQ_ME80FN(String TotalQ_ME80FN)
    {
        this.TotalQ_ME80FN = TotalQ_ME80FN;
    }

    public String getTotalQ_Porcentaje()
    {
        return TotalQ_Porcentaje;
    }

    public void setTotalQ_Porcentaje(String TotalQ_Porcentaje)
    {
        this.TotalQ_Porcentaje = TotalQ_Porcentaje;
    }

    public String getTOTAL_INVOICE_VALUE()
    {
        return TOTAL_INVOICE_VALUE;
    }

    public void setTOTAL_INVOICE_VALUE(String TOTAL_INVOICE_VALUE)
    {
        this.TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE;
    }

    public String getFactura_Value_Unit()
    {
        return Factura_Value_Unit;
    }

    public void setFactura_Value_Unit(String Factura_Value_Unit)
    {
        this.Factura_Value_Unit = Factura_Value_Unit;
    }

    public String getPIR_Porcentaje()
    {
        return PIR_Porcentaje;
    }

    public void setPIR_Porcentaje(String PIR_Porcentaje)
    {
        this.PIR_Porcentaje = PIR_Porcentaje;
    }

    public String getMoneda()
    {
        return Moneda;
    }

    public void setMoneda(String Moneda)
    {
        this.Moneda = Moneda;
    }

    public String getFreightString()
    {
        return FreightString;
    }

    public void setFreightString(String FreightString)
    {
        this.FreightString = FreightString;
    }

    public String getDutys()
    {
        return Dutys;
    }

    public void setDutys(String Dutys)
    {
        this.Dutys = Dutys;
    }

    public String getArancel()
    {
        return Arancel;
    }

    public void setArancel(String Arancel)
    {
        this.Arancel = Arancel;
    }

    public String getAjuste_PIR()
    {
        return Ajuste_PIR;
    }

    public void setAjuste_PIR(String Ajuste_PIR)
    {
        this.Ajuste_PIR = Ajuste_PIR;
    }

    public String getOtros()
    {
        return Otros;
    }

    public void setOtros(String Otros)
    {
        this.Otros = Otros;
    }

    public String getTotal_Costos_Adicionales()
    {
        return Total_Costos_Adicionales;
    }

    public void setTotal_Costos_Adicionales(String Total_Costos_Adicionales)
    {
        this.Total_Costos_Adicionales = Total_Costos_Adicionales;
    }

    public String getParticipac_Adicionales()
    {
        return Participac_Adicionales;
    }

    public void setParticipac_Adicionales(String Participac_Adicionales)
    {
        this.Participac_Adicionales = Participac_Adicionales;
    }

    public String getTotal_Costos()
    {
        return Total_Costos;
    }

    public void setTotal_Costos(String Total_Costos)
    {
        this.Total_Costos = Total_Costos;
    }

    public String getUnitario_final_FIFO()
    {
        return Unitario_final_FIFO;
    }

    public void setUnitario_final_FIFO(String Unitario_final_FIFO)
    {
        this.Unitario_final_FIFO = Unitario_final_FIFO;
    }

    public String getUnitario_estandar()
    {
        return Unitario_estandar;
    }

    public void setUnitario_estandar(String Unitario_estandar)
    {
        this.Unitario_estandar = Unitario_estandar;
    }

    public String getPorcentaje_Real_Vs_Estándar()
    {
        return Porcentaje_Real_Vs_Estándar;
    }

    public void setPorcentaje_Real_Vs_Estándar(String Porcentaje_Real_Vs_Estándar)
    {
        this.Porcentaje_Real_Vs_Estándar = Porcentaje_Real_Vs_Estándar;
    }

}
