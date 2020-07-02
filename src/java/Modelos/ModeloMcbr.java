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
public class ModeloMcbr {

    Integer id;
    String Material;
    String Descripcion;
    String Plant;
    String Batch;
    String Month;
    String Profit_center;
    String Material_Type;
    String Status;
    String Val_stock;
    String Val_stock_Med;
    String ValStckVal;
    String ValStck_Val_Mon;
    String Cost_Unit_Estandar;
    String InventarioInicial_FIFO;
    String Cost_Unit_Purchase;
    String Cost_Unit_KOB1_Piso;
    String Cost_Unit_KOB1_Final;
    String FIFO_Cost_Unit;
    String Inventario_Valorado_a_FIFO;
    String Variacion_FIFO_vs_Estandar;

    public ModeloMcbr(Integer id, String Material, String Descripcion, String Plant, String Batch, String Month, String Profit_center, String Material_Type, String Status, String Val_stock, String Val_stock_Med, String ValStckVal, String ValStck_Val_Mon, String Cost_Unit_Estandar, String InventarioInicial_FIFO, String Cost_Unit_Purchase, String Cost_Unit_KOB1_Piso, String Cost_Unit_KOB1_Final, String FIFO_Cost_Unit, String Inventario_Valorado_a_FIFO, String Variacion_FIFO_vs_Estandar) {
        this.id = id;
        this.Material = Material;
        this.Descripcion = Descripcion;
        this.Plant = Plant;
        this.Batch = Batch;
        this.Month = Month;
        this.Profit_center = Profit_center;
        this.Material_Type = Material_Type;
        this.Status = Status;
        this.Val_stock = Val_stock;
        this.Val_stock_Med = Val_stock_Med;
        this.ValStckVal = ValStckVal;
        this.ValStck_Val_Mon = ValStck_Val_Mon;
        this.Cost_Unit_Estandar = Cost_Unit_Estandar;
        this.InventarioInicial_FIFO = InventarioInicial_FIFO;
        this.Cost_Unit_Purchase = Cost_Unit_Purchase;
        this.Cost_Unit_KOB1_Piso = Cost_Unit_KOB1_Piso;
        this.Cost_Unit_KOB1_Final = Cost_Unit_KOB1_Final;
        this.FIFO_Cost_Unit = FIFO_Cost_Unit;
        this.Inventario_Valorado_a_FIFO = Inventario_Valorado_a_FIFO;
        this.Variacion_FIFO_vs_Estandar = Variacion_FIFO_vs_Estandar;
    }

    public ModeloMcbr() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPlant() {
        return Plant;
    }

    public void setPlant(String Plant) {
        this.Plant = Plant;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String Batch) {
        this.Batch = Batch;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public String getProfit_center() {
        return Profit_center;
    }

    public void setProfit_center(String Profit_center) {
        this.Profit_center = Profit_center;
    }

    public String getMaterial_Type() {
        return Material_Type;
    }

    public void setMaterial_Type(String Material_Type) {
        this.Material_Type = Material_Type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getVal_stock() {
        return Val_stock;
    }

    public void setVal_stock(String Val_stock) {
        this.Val_stock = Val_stock;
    }

    public String getVal_stock_Med() {
        return Val_stock_Med;
    }

    public void setVal_stock_Med(String Val_stock_Med) {
        this.Val_stock_Med = Val_stock_Med;
    }

    public String getValStckVal() {
        return ValStckVal;
    }

    public void setValStckVal(String ValStckVal) {
        this.ValStckVal = ValStckVal;
    }

    public String getValStck_Val_Mon() {
        return ValStck_Val_Mon;
    }

    public void setValStck_Val_Mon(String ValStck_Val_Mon) {
        this.ValStck_Val_Mon = ValStck_Val_Mon;
    }

    public String getCost_Unit_Estandar() {
        return Cost_Unit_Estandar;
    }

    public void setCost_Unit_Estandar(String Cost_Unit_Estandar) {
        this.Cost_Unit_Estandar = Cost_Unit_Estandar;
    }

    public String getInventarioInicial_FIFO() {
        return InventarioInicial_FIFO;
    }

    public void setInventarioInicial_FIFO(String InventarioInicial_FIFO) {
        this.InventarioInicial_FIFO = InventarioInicial_FIFO;
    }

    public String getCost_Unit_Purchase() {
        return Cost_Unit_Purchase;
    }

    public void setCost_Unit_Purchase(String Cost_Unit_Purchase) {
        this.Cost_Unit_Purchase = Cost_Unit_Purchase;
    }

    public String getCost_Unit_KOB1_Piso() {
        return Cost_Unit_KOB1_Piso;
    }

    public void setCost_Unit_KOB1_Piso(String Cost_Unit_KOB1_Piso) {
        this.Cost_Unit_KOB1_Piso = Cost_Unit_KOB1_Piso;
    }

    public String getCost_Unit_KOB1_Final() {
        return Cost_Unit_KOB1_Final;
    }

    public void setCost_Unit_KOB1_Final(String Cost_Unit_KOB1_Final) {
        this.Cost_Unit_KOB1_Final = Cost_Unit_KOB1_Final;
    }

    public String getFIFO_Cost_Unit() {
        return FIFO_Cost_Unit;
    }

    public void setFIFO_Cost_Unit(String FIFO_Cost_Unit) {
        this.FIFO_Cost_Unit = FIFO_Cost_Unit;
    }

    public String getInventario_Valorado_a_FIFO() {
        return Inventario_Valorado_a_FIFO;
    }

    public void setInventario_Valorado_a_FIFO(String Inventario_Valorado_a_FIFO) {
        this.Inventario_Valorado_a_FIFO = Inventario_Valorado_a_FIFO;
    }

    public String getVariacion_FIFO_vs_Estandar() {
        return Variacion_FIFO_vs_Estandar;
    }

    public void setVariacion_FIFO_vs_Estandar(String Variacion_FIFO_vs_Estandar) {
        this.Variacion_FIFO_vs_Estandar = Variacion_FIFO_vs_Estandar;
    }

    
    
}
