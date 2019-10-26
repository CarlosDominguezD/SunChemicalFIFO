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

    public ModeloProveedor()
    {
    }

    public ModeloProveedor(Integer Id, String Vendors, String Name)
    {
        this.Id = Id;
        this.Vendors = Vendors;
        this.Name = Name;
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

}
