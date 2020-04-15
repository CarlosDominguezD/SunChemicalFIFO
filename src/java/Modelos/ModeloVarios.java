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
public class ModeloVarios {

    ModeloProveedor modeloProveedor;
    ModeloMrpData modeloMrpData;
    LinkedList<ModeloMe80fn> LstmodeloMe80fn;
    LinkedList<ModeloFbl3m> ListmodeloFbl3m_Freight;
    LinkedList<ModeloFbl3m> ListmodeloFbl3m_DUTYS;
    LinkedList<ModeloFbl3m> ListmodeloFbl3m_ARANCEL;
    ModeloEine modeloEine;
    ModeloVendorType modeloVendorType;

    public ModeloVarios(ModeloProveedor modeloProveedor, ModeloMrpData modeloMrpData, LinkedList<ModeloMe80fn> LstmodeloMe80fn, LinkedList<ModeloFbl3m> ListmodeloFbl3m_Freight, LinkedList<ModeloFbl3m> ListmodeloFbl3m_DUTYS, LinkedList<ModeloFbl3m> ListmodeloFbl3m_ARANCEL, ModeloEine modeloEine, ModeloVendorType modeloVendorType) {
        this.modeloProveedor = modeloProveedor;
        this.modeloMrpData = modeloMrpData;
        this.LstmodeloMe80fn = LstmodeloMe80fn;
        this.ListmodeloFbl3m_Freight = ListmodeloFbl3m_Freight;
        this.ListmodeloFbl3m_DUTYS = ListmodeloFbl3m_DUTYS;
        this.ListmodeloFbl3m_ARANCEL = ListmodeloFbl3m_ARANCEL;
        this.modeloEine = modeloEine;
        this.modeloVendorType = modeloVendorType;
    }

    public ModeloVarios() {
    }

    public ModeloProveedor getModeloProveedor() {
        return modeloProveedor;
    }

    public void setModeloProveedor(ModeloProveedor modeloProveedor) {
        this.modeloProveedor = modeloProveedor;
    }

    public ModeloMrpData getModeloMrpData() {
        return modeloMrpData;
    }

    public void setModeloMrpData(ModeloMrpData modeloMrpData) {
        this.modeloMrpData = modeloMrpData;
    }

    public LinkedList<ModeloMe80fn> getLstmodeloMe80fn() {
        return LstmodeloMe80fn;
    }

    public void setLstmodeloMe80fn(LinkedList<ModeloMe80fn> LstmodeloMe80fn) {
        this.LstmodeloMe80fn = LstmodeloMe80fn;
    }

    public LinkedList<ModeloFbl3m> getListmodeloFbl3m_Freight() {
        return ListmodeloFbl3m_Freight;
    }

    public void setListmodeloFbl3m_Freight(LinkedList<ModeloFbl3m> ListmodeloFbl3m_Freight) {
        this.ListmodeloFbl3m_Freight = ListmodeloFbl3m_Freight;
    }

    public LinkedList<ModeloFbl3m> getListmodeloFbl3m_DUTYS() {
        return ListmodeloFbl3m_DUTYS;
    }

    public void setListmodeloFbl3m_DUTYS(LinkedList<ModeloFbl3m> ListmodeloFbl3m_DUTYS) {
        this.ListmodeloFbl3m_DUTYS = ListmodeloFbl3m_DUTYS;
    }

    public LinkedList<ModeloFbl3m> getListmodeloFbl3m_ARANCEL() {
        return ListmodeloFbl3m_ARANCEL;
    }

    public void setListmodeloFbl3m_ARANCEL(LinkedList<ModeloFbl3m> ListmodeloFbl3m_ARANCEL) {
        this.ListmodeloFbl3m_ARANCEL = ListmodeloFbl3m_ARANCEL;
    }

    public ModeloEine getModeloEine() {
        return modeloEine;
    }

    public void setModeloEine(ModeloEine modeloEine) {
        this.modeloEine = modeloEine;
    }

    public ModeloVendorType getModeloVendorType() {
        return modeloVendorType;
    }

    public void setModeloVendorType(ModeloVendorType modeloVendorType) {
        this.modeloVendorType = modeloVendorType;
    }

   
}
