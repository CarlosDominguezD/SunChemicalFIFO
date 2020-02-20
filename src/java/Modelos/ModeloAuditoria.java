/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloAuditoria
{
    int Id;
    ModeloUsuario modeloUsuario;
    String Fecha;
    String Mensaje;

    public ModeloAuditoria ()
    {
    }

    public ModeloAuditoria (int Id, ModeloUsuario modeloUsuario, String Fecha, String Mensaje)
    {
        this.Id = Id;
        this.modeloUsuario = modeloUsuario;
        this.Fecha = Fecha;
        this.Mensaje = Mensaje;
    }

    public int getId ()
    {
        return Id;
    }

    public void setId (int Id)
    {
        this.Id = Id;
    }

    public ModeloUsuario getModeloUsuario ()
    {
        return modeloUsuario;
    }

    public void setModeloUsuario (ModeloUsuario modeloUsuario)
    {
        this.modeloUsuario = modeloUsuario;
    }

    public String getFecha ()
    {
        return Fecha;
    }

    public void setFecha (String Fecha)
    {
        this.Fecha = Fecha;
    }

    public String getMensaje ()
    {
        return Mensaje;
    }

    public void setMensaje (String Mensaje)
    {
        this.Mensaje = Mensaje;
    }
    
}
