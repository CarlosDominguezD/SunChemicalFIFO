/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ModeloConversiones {

    Integer id;
    String ano;
    String mes;
    String conversion_labor;
    String conversion_machine;
    String conversion_ovhds;
    String datocompleto;    

    public ModeloConversiones(Integer id, String ano, String mes, String conversion_labor, String conversion_machine, String conversion_ovhds) {
        this.id = id;
        this.ano = ano;
        this.mes = mes;
        this.conversion_labor = conversion_labor;
        this.conversion_machine = conversion_machine;
        this.conversion_ovhds = conversion_ovhds;
    }

    public ModeloConversiones() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getConversion_labor() {
        return conversion_labor;
    }

    public void setConversion_labor(String conversion_labor) {
        this.conversion_labor = conversion_labor;
    }

    public String getConversion_machine() {
        return conversion_machine;
    }

    public void setConversion_machine(String conversion_machine) {
        this.conversion_machine = conversion_machine;
    }

    public String getConversion_ovhds() {
        return conversion_ovhds;
    }

    public void setConversion_ovhds(String conversion_ovhds) {
        this.conversion_ovhds = conversion_ovhds;
    }

    public String getDatoCopleto() {
        return datocompleto = "Ano " + ano + " Mes " + mes + " Conversion_Labor " + conversion_labor + " Conversion_Machine " + conversion_machine + " Conversion_Ovhds " + conversion_ovhds;
    }

}
