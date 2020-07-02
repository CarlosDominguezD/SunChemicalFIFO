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
public class ModeloInventarioInicial {

    Integer id;
    String Material;
    String Material_Description;
    String Batch;
    String Link_Material_Batch;
    String FIFO_Cost_UNIT;

    public ModeloInventarioInicial(Integer id, String Material, String Material_Description, String Batch, String Link_Material_Batch, String FIFO_Cost_UNIT) {
        this.id = id;
        this.Material = Material;
        this.Material_Description = Material_Description;
        this.Batch = Batch;
        this.Link_Material_Batch = Link_Material_Batch;
        this.FIFO_Cost_UNIT = FIFO_Cost_UNIT;
    }

    public ModeloInventarioInicial() {
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

    public String getMaterial_Description() {
        return Material_Description;
    }

    public void setMaterial_Description(String Material_Description) {
        this.Material_Description = Material_Description;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String Batch) {
        this.Batch = Batch;
    }

    public String getLink_Material_Batch() {
        return Link_Material_Batch;
    }

    public void setLink_Material_Batch(String Link_Material_Batch) {
        this.Link_Material_Batch = Link_Material_Batch;
    }

    public String getFIFO_Cost_UNIT() {
        return FIFO_Cost_UNIT;
    }

    public void setFIFO_Cost_UNIT(String FIFO_Cost_UNIT) {
        this.FIFO_Cost_UNIT = FIFO_Cost_UNIT;
    }

    
    
}
