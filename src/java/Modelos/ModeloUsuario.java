/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloUsuario
{

    int Id;
    String Cedula;
    String Nombre;
    String Usuario;
    String Clave;
    int IdRol;
    LinkedList<ModeloItemMenu> listModeloItemMenu;

    public ModeloUsuario ()
    {
    }

    public ModeloUsuario (int Id, String Cedula, String Nombre, String Usuario, String Clave, int IdRol)
    {
        this.Id = Id;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.IdRol = IdRol;
    }

    public int getId ()
    {
        return Id;
    }

    public void setId (int Id)
    {
        this.Id = Id;
    }

    public String getCedula ()
    {
        return Cedula;
    }

    public void setCedula (String Cedula)
    {
        this.Cedula = Cedula;
    }

    public String getNombre ()
    {
        return Nombre;
    }

    public void setNombre (String Nombre)
    {
        this.Nombre = Nombre;
    }

    public String getUsuario ()
    {
        return Usuario;
    }

    public void setUsuario (String Usuario)
    {
        this.Usuario = Usuario;
    }

    public String getClave ()
    {
        return Clave;
    }

    public void setClave (String Clave)
    {
        this.Clave = Clave;
    }

    public int getIdRol ()
    {
        return IdRol;
    }

    public void setIdRol (int IdRol)
    {
        this.IdRol = IdRol;
    }

    public LinkedList<ModeloItemMenu> getListModeloItemMenu ()
    {
        return listModeloItemMenu;
    }

    public void setListModeloItemMenu (LinkedList<ModeloItemMenu> listModeloItemMenu)
    {
        this.listModeloItemMenu = listModeloItemMenu;
    }
    
    
}
