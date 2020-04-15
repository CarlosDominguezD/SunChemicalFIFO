/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.LinkedList;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ModeloVariosProduccion {

    LinkedList<ModeloPovr> lstModeloPovr;
    LinkedList<ModeloMb51_Consumos> lstModeloMb51_Consumos;
    LinkedList<ModeloMrpData> lstModeloMrpData;
    LinkedList<ModeloMb51> lstModeloMb51;
    LinkedList<ModeloKob1> lstModeloKob1;

    public ModeloVariosProduccion(LinkedList<ModeloPovr> lstModeloPovr, LinkedList<ModeloMb51_Consumos> lstModeloMb51_Consumos, LinkedList<ModeloMrpData> lstModeloMrpData, LinkedList<ModeloMb51> lstModeloMb51, LinkedList<ModeloKob1> lstModeloKob1) {
        this.lstModeloPovr = lstModeloPovr;
        this.lstModeloMb51_Consumos = lstModeloMb51_Consumos;
        this.lstModeloMrpData = lstModeloMrpData;
        this.lstModeloMb51 = lstModeloMb51;
        this.lstModeloKob1 = lstModeloKob1;
    }

    public ModeloVariosProduccion() {
    }

    public LinkedList<ModeloPovr> getLstModeloPovr() {
        return lstModeloPovr;
    }

    public void setLstModeloPovr(LinkedList<ModeloPovr> lstModeloPovr) {
        this.lstModeloPovr = lstModeloPovr;
    }

    public LinkedList<ModeloMb51_Consumos> getLstModeloMb51_Consumos() {
        return lstModeloMb51_Consumos;
    }

    public void setLstModeloMb51_Consumos(LinkedList<ModeloMb51_Consumos> lstModeloMb51_Consumos) {
        this.lstModeloMb51_Consumos = lstModeloMb51_Consumos;
    }

    public LinkedList<ModeloMrpData> getLstModeloMrpData() {
        return lstModeloMrpData;
    }

    public void setLstModeloMrpData(LinkedList<ModeloMrpData> lstModeloMrpData) {
        this.lstModeloMrpData = lstModeloMrpData;
    }

    public LinkedList<ModeloMb51> getLstModeloMb51() {
        return lstModeloMb51;
    }

    public void setLstModeloMb51(LinkedList<ModeloMb51> lstModeloMb51) {
        this.lstModeloMb51 = lstModeloMb51;
    }

    public LinkedList<ModeloKob1> getLstModeloKob1() {
        return lstModeloKob1;
    }

    public void setLstModeloKob1(LinkedList<ModeloKob1> lstModeloKob1) {
        this.lstModeloKob1 = lstModeloKob1;
    }

    
    
}
