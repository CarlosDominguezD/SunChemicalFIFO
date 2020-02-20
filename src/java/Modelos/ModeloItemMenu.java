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
public class ModeloItemMenu
{

    int Id;
    String Nombre;

    public ModeloItemMenu ()
    {
    }

    public ModeloItemMenu (int Id, String Nombre)
    {
        this.Id = Id;
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

    public String getNombre ()
    {
        return Nombre;
    }

    public void setNombre (String Nombre)
    {
        this.Nombre = Nombre;
    }

}
