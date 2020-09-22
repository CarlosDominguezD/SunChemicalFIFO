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
public class ModeloFechas {

    Integer Id;
    String Mes;
    Integer Ano;
    String EstadoCompras;
    String EstadoProduccion;
    String EstadoInventario;

    public ModeloFechas() {
    }

    public ModeloFechas(Integer Id, String Mes, Integer Ano, String EstadoCompras, String EstadoProduccion, String EstadoInventario) {
        this.Id = Id;
        this.Mes = Mes;
        this.Ano = Ano;
        this.EstadoCompras = EstadoCompras;
        this.EstadoProduccion = EstadoProduccion;
        this.EstadoInventario = EstadoInventario;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String Mes) {
        this.Mes = Mes;
    }

    public Integer getAno() {
        return Ano;
    }

    public void setAno(Integer Ano) {
        this.Ano = Ano;
    }

    public String getEstadoCompras() {
        return EstadoCompras;
    }

    public void setEstadoCompras(String EstadoCompras) {
        this.EstadoCompras = EstadoCompras;
    }

    public String getEstadoProduccion() {
        return EstadoProduccion;
    }

    public void setEstadoProduccion(String EstadoProduccion) {
        this.EstadoProduccion = EstadoProduccion;
    }

    public String getEstadoInventario() {
        return EstadoInventario;
    }

    public void setEstadoInventario(String EstadoInventario) {
        this.EstadoInventario = EstadoInventario;
    }
    
}
