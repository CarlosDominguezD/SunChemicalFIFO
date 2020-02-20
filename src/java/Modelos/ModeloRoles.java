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
public class ModeloRoles
{
    int Id;
    String Codigo;
    String Nombre;

    public ModeloRoles ()
    {
    }

    public ModeloRoles (int Id, String Codigo, String Nombre)
    {
        this.Id = Id;
        this.Codigo = Codigo;
        this.Nombre = Nombre;
    }

    public int getId ()
    {
        return Id;
    }

    public void setId (int Id)
    {
        this.Id = Id;
    }

    public String getCodigo ()
    {
        return Codigo;
    }

    public void setCodigo (String Codigo)
    {
        this.Codigo = Codigo;
    }

    public String getNombre ()
    {
        return Nombre;
    }

    public void setNombre (String Nombre)
    {
        this.Nombre = Nombre;
    }    
}
