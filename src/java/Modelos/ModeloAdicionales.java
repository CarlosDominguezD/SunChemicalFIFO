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
public class ModeloAdicionales {
    Integer id;
    String Adicional;
    String Valores;

    public ModeloAdicionales() {
    }

    public ModeloAdicionales(Integer id, String Adicional, String Valores) {
        this.id = id;
        this.Adicional = Adicional;
        this.Valores = Valores;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdicional() {
        return Adicional;
    }

    public void setAdicional(String Adicional) {
        this.Adicional = Adicional;
    }

    public String getValores() {
        return Valores;
    }

    public void setValores(String Valores) {
        this.Valores = Valores;
    }
    
    
}
