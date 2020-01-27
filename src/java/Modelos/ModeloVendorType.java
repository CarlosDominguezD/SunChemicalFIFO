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
public class ModeloVendorType {
    Integer id;
    String Material;
    String Vendor_Type;

    public ModeloVendorType() {
    }

    public ModeloVendorType(Integer id, String Material, String Vendor_Type) {
        this.id = id;
        this.Material = Material;
        this.Vendor_Type = Vendor_Type;
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

    public String getVendor_Type() {
        return Vendor_Type;
    }

    public void setVendor_Type(String Vendor_Type) {
        this.Vendor_Type = Vendor_Type;
    }
    
    
}
