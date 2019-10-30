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
public class ModeloEstadoPlanos {

    Integer Id;
    String Actividad;
    String NombrePlano;
    String FechaCarga;
    String Estado;

    public ModeloEstadoPlanos() {
    }

    public ModeloEstadoPlanos(Integer Id, String Actividad, String NombrePlano, String FechaCarga, String Estado) {
        this.Id = Id;
        this.Actividad = Actividad;
        this.NombrePlano = NombrePlano;
        this.FechaCarga = FechaCarga;
        this.Estado = Estado;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String Actividad) {
        this.Actividad = Actividad;
    }

    public String getNombrePlano() {
        return NombrePlano;
    }

    public void setNombrePlano(String NombrePlano) {
        this.NombrePlano = NombrePlano;
    }

    public String getFechaCarga() {
        return FechaCarga;
    }

    public void setFechaCarga(String FechaCarga) {
        this.FechaCarga = FechaCarga;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}
