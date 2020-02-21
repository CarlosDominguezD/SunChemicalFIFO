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
public class ModeloArchivos
{
    String Nombre;
    String Ruta;

    public ModeloArchivos ()
    {
    }

    public ModeloArchivos (String Nombre, String Ruta)
    {
        this.Nombre = Nombre;
        this.Ruta = Ruta;
    }

    public String getNombre ()
    {
        return Nombre;
    }

    public void setNombre (String Nombre)
    {
        this.Nombre = Nombre;
    }

    public String getRuta ()
    {
        return Ruta;
    }

    public void setRuta (String Ruta)
    {
        this.Ruta = Ruta;
    }

}
