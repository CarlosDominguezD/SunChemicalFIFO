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
public class ModeloKob1 {

    Integer Id;
    String Functional_Area;
    String Company_Code;
    String Order_;
    String CO_object_name;
    String Cost_Element;
    String Cost_element_name;
    String Material;
    String Material_Description;
    String Plant;
    String Period;
    String Fiscal_Year;
    String Dr_Cr_indicator;
    String Total_Quantity;
    String Unit_of_Measure;
    String Value_TranCurr;
    String Transaction_Currency;
    String Value_in_Obj_Crcy;
    String Object_Currency;
    String Document_Number;
    String Link_Plant_Material;
    String Link_Material_orden;
    String Batch_consumo;
    String Link_Material_Batch;
    String Material_Type_Components;
    String Procur_Type;
    String Level_1;
    String Finish_Good_sku;
    String Mat_Type_Unfinish_Goods;
    String Batch_Finish_goods;
    String Link_Terminado_Batch;
    String Cost_Unit_Estandar;
    String Cantidad_Terminada;
    String Cost_Unit_Fifo_Old;
    String Cost_Unit_Fifo_R_Mat_Pack;
    String x;
    String Total_Raw_Material;
    String Manufact_Materials;
    String Packaging_Materials;
    String Conversion_Cost;
    String Nuevo_Valor_Orden;
    String Month;
    Integer IdArchivo;
    String Fecha;
    String Produccion;
    String Inventario;

    public ModeloKob1(Integer Id, String Functional_Area, String Company_Code, String Order_, String CO_object_name, String Cost_Element, String Cost_element_name, String Material, String Material_Description, String Plant, String Period, String Fiscal_Year, String Dr_Cr_indicator, String Total_Quantity, String Unit_of_Measure, String Value_TranCurr, String Transaction_Currency, String Value_in_Obj_Crcy, String Object_Currency, String Document_Number, String Link_Plant_Material, String Link_Material_orden, String Batch_consumo, String Link_Material_Batch, String Material_Type_Components, String Procur_Type, String Level_1, String Finish_Good_sku, String Mat_Type_Unfinish_Goods, String Batch_Finish_goods, String Link_Terminado_Batch, String Cost_Unit_Estandar, String Cantidad_Terminada, String Cost_Unit_Fifo_Old, String Cost_Unit_Fifo_R_Mat_Pack, String x, String Total_Raw_Material, String Manufact_Materials, String Packaging_Materials, String Conversion_Cost, String Nuevo_Valor_Orden, String Month, Integer IdArchivo, String Fecha, String Produccion, String Inventario) {
        this.Id = Id;
        this.Functional_Area = Functional_Area;
        this.Company_Code = Company_Code;
        this.Order_ = Order_;
        this.CO_object_name = CO_object_name;
        this.Cost_Element = Cost_Element;
        this.Cost_element_name = Cost_element_name;
        this.Material = Material;
        this.Material_Description = Material_Description;
        this.Plant = Plant;
        this.Period = Period;
        this.Fiscal_Year = Fiscal_Year;
        this.Dr_Cr_indicator = Dr_Cr_indicator;
        this.Total_Quantity = Total_Quantity;
        this.Unit_of_Measure = Unit_of_Measure;
        this.Value_TranCurr = Value_TranCurr;
        this.Transaction_Currency = Transaction_Currency;
        this.Value_in_Obj_Crcy = Value_in_Obj_Crcy;
        this.Object_Currency = Object_Currency;
        this.Document_Number = Document_Number;
        this.Link_Plant_Material = Link_Plant_Material;
        this.Link_Material_orden = Link_Material_orden;
        this.Batch_consumo = Batch_consumo;
        this.Link_Material_Batch = Link_Material_Batch;
        this.Material_Type_Components = Material_Type_Components;
        this.Procur_Type = Procur_Type;
        this.Level_1 = Level_1;
        this.Finish_Good_sku = Finish_Good_sku;
        this.Mat_Type_Unfinish_Goods = Mat_Type_Unfinish_Goods;
        this.Batch_Finish_goods = Batch_Finish_goods;
        this.Link_Terminado_Batch = Link_Terminado_Batch;
        this.Cost_Unit_Estandar = Cost_Unit_Estandar;
        this.Cantidad_Terminada = Cantidad_Terminada;
        this.Cost_Unit_Fifo_Old = Cost_Unit_Fifo_Old;
        this.Cost_Unit_Fifo_R_Mat_Pack = Cost_Unit_Fifo_R_Mat_Pack;
        this.x = x;
        this.Total_Raw_Material = Total_Raw_Material;
        this.Manufact_Materials = Manufact_Materials;
        this.Packaging_Materials = Packaging_Materials;
        this.Conversion_Cost = Conversion_Cost;
        this.Nuevo_Valor_Orden = Nuevo_Valor_Orden;
        this.Month = Month;
        this.IdArchivo = IdArchivo;
        this.Fecha = Fecha;
        this.Produccion = Produccion;
        this.Inventario = Inventario;
    }

    public ModeloKob1() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFunctional_Area() {
        return Functional_Area;
    }

    public void setFunctional_Area(String Functional_Area) {
        this.Functional_Area = Functional_Area;
    }

    public String getCompany_Code() {
        return Company_Code;
    }

    public void setCompany_Code(String Company_Code) {
        this.Company_Code = Company_Code;
    }

    public String getOrder_() {
        return Order_;
    }

    public void setOrder_(String Order_) {
        this.Order_ = Order_;
    }

    public String getCO_object_name() {
        return CO_object_name;
    }

    public void setCO_object_name(String CO_object_name) {
        this.CO_object_name = CO_object_name;
    }

    public String getCost_Element() {
        return Cost_Element;
    }

    public void setCost_Element(String Cost_Element) {
        this.Cost_Element = Cost_Element;
    }

    public String getCost_element_name() {
        return Cost_element_name;
    }

    public void setCost_element_name(String Cost_element_name) {
        this.Cost_element_name = Cost_element_name;
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

    public String getPlant() {
        return Plant;
    }

    public void setPlant(String Plant) {
        this.Plant = Plant;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String Period) {
        this.Period = Period;
    }

    public String getFiscal_Year() {
        return Fiscal_Year;
    }

    public void setFiscal_Year(String Fiscal_Year) {
        this.Fiscal_Year = Fiscal_Year;
    }

    public String getDr_Cr_indicator() {
        return Dr_Cr_indicator;
    }

    public void setDr_Cr_indicator(String Dr_Cr_indicator) {
        this.Dr_Cr_indicator = Dr_Cr_indicator;
    }

    public String getTotal_Quantity() {
        return Total_Quantity;
    }

    public void setTotal_Quantity(String Total_Quantity) {
        this.Total_Quantity = Total_Quantity;
    }

    public String getUnit_of_Measure() {
        return Unit_of_Measure;
    }

    public void setUnit_of_Measure(String Unit_of_Measure) {
        this.Unit_of_Measure = Unit_of_Measure;
    }

    public String getValue_TranCurr() {
        return Value_TranCurr;
    }

    public void setValue_TranCurr(String Value_TranCurr) {
        this.Value_TranCurr = Value_TranCurr;
    }

    public String getTransaction_Currency() {
        return Transaction_Currency;
    }

    public void setTransaction_Currency(String Transaction_Currency) {
        this.Transaction_Currency = Transaction_Currency;
    }

    public String getValue_in_Obj_Crcy() {
        return Value_in_Obj_Crcy;
    }

    public void setValue_in_Obj_Crcy(String Value_in_Obj_Crcy) {
        this.Value_in_Obj_Crcy = Value_in_Obj_Crcy;
    }

    public String getObject_Currency() {
        return Object_Currency;
    }

    public void setObject_Currency(String Object_Currency) {
        this.Object_Currency = Object_Currency;
    }

    public String getDocument_Number() {
        return Document_Number;
    }

    public void setDocument_Number(String Document_Number) {
        this.Document_Number = Document_Number;
    }

    public String getLink_Plant_Material() {
        return Link_Plant_Material;
    }

    public void setLink_Plant_Material(String Link_Plant_Material) {
        this.Link_Plant_Material = Link_Plant_Material;
    }

    public String getLink_Material_orden() {
        return Link_Material_orden;
    }

    public void setLink_Material_orden(String Link_Material_orden) {
        this.Link_Material_orden = Link_Material_orden;
    }

    public String getBatch_consumo() {
        return Batch_consumo;
    }

    public void setBatch_consumo(String Batch_consumo) {
        this.Batch_consumo = Batch_consumo;
    }

    public String getLink_Material_Batch() {
        return Link_Material_Batch;
    }

    public void setLink_Material_Batch(String Link_Material_Batch) {
        this.Link_Material_Batch = Link_Material_Batch;
    }

    public String getMaterial_Type_Components() {
        return Material_Type_Components;
    }

    public void setMaterial_Type_Components(String Material_Type_Components) {
        this.Material_Type_Components = Material_Type_Components;
    }

    public String getProcur_Type() {
        return Procur_Type;
    }

    public void setProcur_Type(String Procur_Type) {
        this.Procur_Type = Procur_Type;
    }

    public String getLevel_1() {
        return Level_1;
    }

    public void setLevel_1(String Level_1) {
        this.Level_1 = Level_1;
    }

    public String getFinish_Good_sku() {
        return Finish_Good_sku;
    }

    public void setFinish_Good_sku(String Finish_Good_sku) {
        this.Finish_Good_sku = Finish_Good_sku;
    }

    public String getMat_Type_Unfinish_Goods() {
        return Mat_Type_Unfinish_Goods;
    }

    public void setMat_Type_Unfinish_Goods(String Mat_Type_Unfinish_Goods) {
        this.Mat_Type_Unfinish_Goods = Mat_Type_Unfinish_Goods;
    }

    public String getBatch_Finish_goods() {
        return Batch_Finish_goods;
    }

    public void setBatch_Finish_goods(String Batch_Finish_goods) {
        this.Batch_Finish_goods = Batch_Finish_goods;
    }

    public String getLink_Terminado_Batch() {
        return Link_Terminado_Batch;
    }

    public void setLink_Terminado_Batch(String Link_Terminado_Batch) {
        this.Link_Terminado_Batch = Link_Terminado_Batch;
    }

    public String getCost_Unit_Estandar() {
        return Cost_Unit_Estandar;
    }

    public void setCost_Unit_Estandar(String Cost_Unit_Estandar) {
        this.Cost_Unit_Estandar = Cost_Unit_Estandar;
    }

    public String getCantidad_Terminada() {
        return Cantidad_Terminada;
    }

    public void setCantidad_Terminada(String Cantidad_Terminada) {
        this.Cantidad_Terminada = Cantidad_Terminada;
    }

    public String getCost_Unit_Fifo_Old() {
        return Cost_Unit_Fifo_Old;
    }

    public void setCost_Unit_Fifo_Old(String Cost_Unit_Fifo_Old) {
        this.Cost_Unit_Fifo_Old = Cost_Unit_Fifo_Old;
    }

    public String getCost_Unit_Fifo_R_Mat_Pack() {
        return Cost_Unit_Fifo_R_Mat_Pack;
    }

    public void setCost_Unit_Fifo_R_Mat_Pack(String Cost_Unit_Fifo_R_Mat_Pack) {
        this.Cost_Unit_Fifo_R_Mat_Pack = Cost_Unit_Fifo_R_Mat_Pack;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getTotal_Raw_Material() {
        return Total_Raw_Material;
    }

    public void setTotal_Raw_Material(String Total_Raw_Material) {
        this.Total_Raw_Material = Total_Raw_Material;
    }

    public String getManufact_Materials() {
        return Manufact_Materials;
    }

    public void setManufact_Materials(String Manufact_Materials) {
        this.Manufact_Materials = Manufact_Materials;
    }

    public String getPackaging_Materials() {
        return Packaging_Materials;
    }

    public void setPackaging_Materials(String Packaging_Materials) {
        this.Packaging_Materials = Packaging_Materials;
    }

    public String getConversion_Cost() {
        return Conversion_Cost;
    }

    public void setConversion_Cost(String Conversion_Cost) {
        this.Conversion_Cost = Conversion_Cost;
    }

    public String getNuevo_Valor_Orden() {
        return Nuevo_Valor_Orden;
    }

    public void setNuevo_Valor_Orden(String Nuevo_Valor_Orden) {
        this.Nuevo_Valor_Orden = Nuevo_Valor_Orden;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public Integer getIdArchivo() {
        return IdArchivo;
    }

    public void setIdArchivo(Integer IdArchivo) {
        this.IdArchivo = IdArchivo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }        

    public String getProduccion() {
        return Produccion;
    }

    public void setProduccion(String Produccion) {
        this.Produccion = Produccion;
    }

    public String getInventario() {
        return Inventario;
    }

    public void setInventario(String Inventario) {
        this.Inventario = Inventario;
    }
    
    

    
    
}
