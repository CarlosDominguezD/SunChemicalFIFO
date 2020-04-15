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
public class ModeloInforme_Produccion {

    Integer Id;
    String Finish_Good_sku;
    String CO_object_name;
    String Batch_Finish_goods;
    String Link_Terminado_Batch;
    String Cantidad;
    String Total_Raw_Material;
    String Manufact;
    String Packaging;
    String Conversion;
    String Nuevo_Valor_Orden;
    String Costo_unitario_FIFO;
    String Costo_unitario_Estandar;

    public ModeloInforme_Produccion(Integer Id, String Finish_Good_sku, String CO_object_name, String Batch_Finish_goods, String Link_Terminado_Batch, String Cantidad, String Total_Raw_Material, String Manufact, String Packaging, String Conversion, String Nuevo_Valor_Orden, String Costo_unitario_FIFO, String Costo_unitario_Estandar) {
        this.Id = Id;
        this.Finish_Good_sku = Finish_Good_sku;
        this.CO_object_name = CO_object_name;
        this.Batch_Finish_goods = Batch_Finish_goods;
        this.Link_Terminado_Batch = Link_Terminado_Batch;
        this.Cantidad = Cantidad;
        this.Total_Raw_Material = Total_Raw_Material;
        this.Manufact = Manufact;
        this.Packaging = Packaging;
        this.Conversion = Conversion;
        this.Nuevo_Valor_Orden = Nuevo_Valor_Orden;
        this.Costo_unitario_FIFO = Costo_unitario_FIFO;
        this.Costo_unitario_Estandar = Costo_unitario_Estandar;
    }

    public ModeloInforme_Produccion() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFinish_Good_sku() {
        return Finish_Good_sku;
    }

    public void setFinish_Good_sku(String Finish_Good_sku) {
        this.Finish_Good_sku = Finish_Good_sku;
    }

    public String getCO_object_name() {
        return CO_object_name;
    }

    public void setCO_object_name(String CO_object_name) {
        this.CO_object_name = CO_object_name;
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

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getTotal_Raw_Material() {
        return Total_Raw_Material;
    }

    public void setTotal_Raw_Material(String Total_Raw_Material) {
        this.Total_Raw_Material = Total_Raw_Material;
    }

    public String getManufact() {
        return Manufact;
    }

    public void setManufact(String Manufact) {
        this.Manufact = Manufact;
    }

    public String getPackaging() {
        return Packaging;
    }

    public void setPackaging(String Packaging) {
        this.Packaging = Packaging;
    }

    public String getConversion() {
        return Conversion;
    }

    public void setConversion(String Conversion) {
        this.Conversion = Conversion;
    }

    public String getNuevo_Valor_Orden() {
        return Nuevo_Valor_Orden;
    }

    public void setNuevo_Valor_Orden(String Nuevo_Valor_Orden) {
        this.Nuevo_Valor_Orden = Nuevo_Valor_Orden;
    }

    public String getCosto_unitario_FIFO() {
        return Costo_unitario_FIFO;
    }

    public void setCosto_unitario_FIFO(String Costo_unitario_FIFO) {
        this.Costo_unitario_FIFO = Costo_unitario_FIFO;
    }

    public String getCosto_unitario_Estandar() {
        return Costo_unitario_Estandar;
    }

    public void setCosto_unitario_Estandar(String Costo_unitario_Estandar) {
        this.Costo_unitario_Estandar = Costo_unitario_Estandar;
    }
    
    

}
