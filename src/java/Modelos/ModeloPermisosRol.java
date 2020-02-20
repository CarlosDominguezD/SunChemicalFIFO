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
public class ModeloPermisosRol
{

    int Id;
    int IdRol;
    int IdItemMenu;

    public ModeloPermisosRol ()
    {
    }

    public ModeloPermisosRol (int Id, int IdRol, int IdItemMenu)
    {
        this.Id = Id;
        this.IdRol = IdRol;
        this.IdItemMenu = IdItemMenu;
    }

    public int getId ()
    {
        return Id;
    }

    public void setId (int Id)
    {
        this.Id = Id;
    }

    public int getIdRol ()
    {
        return IdRol;
    }

    public void setIdRol (int IdRol)
    {
        this.IdRol = IdRol;
    }

    public int getIdItemMenu ()
    {
        return IdItemMenu;
    }

    public void setIdItemMenu (int IdItemMenu)
    {
        this.IdItemMenu = IdItemMenu;
    }    
    
}
