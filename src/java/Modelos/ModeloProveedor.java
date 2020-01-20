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
public class ModeloProveedor
{

    Integer Id;
    String Vendors;
    String Name;
    String Vendor_Type;
    String Moneda;

    public ModeloProveedor()
    {
    }

    public ModeloProveedor(Integer Id, String Vendors, String Name, String Vendor_Type, String Moneda)
    {
        this.Id = Id;
        this.Vendors = Vendors;
        this.Name = Name;
        this.Vendor_Type = Vendor_Type;
        this.Moneda = Moneda;
    }

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }

    public String getVendors()
    {
        return Vendors;
    }

    public void setVendors(String Vendors)
    {
        this.Vendors = Vendors;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getVendor_Type() {
        return Vendor_Type;
    }

    public void setVendor_Type(String Vendor_Type) {
        this.Vendor_Type = Vendor_Type;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

}
