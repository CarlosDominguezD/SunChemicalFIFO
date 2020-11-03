/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramienta;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos A Dominguez D
 */
public class Herramienta {

    static String EventoProcesado;
    
    /**
     *BD para el control de envios en formulario de produccion 
     */
    public static Map<String, String> bdFormularioProduccion = new HashMap<String, String>();

    public String getEventoProcesado() {
        if (EventoProcesado == null) {
            EventoProcesado = "Iniciando Proceso";
        }
        return EventoProcesado;
    }

    public void setEventoProcesado(String EventoProcesado) {
        this.EventoProcesado = EventoProcesado;
    }

    public String sDate() {
        java.util.Date uDate = new java.util.Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(formatter.format(uDate));
        return formatter.format(uDate);
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public Date getDate() {
        Date utilDate = new Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        Date sqlDate = new Date(lnMilisegundos);
        return sqlDate;
    }

    public String GetDescrpCode(String cod) {
        String resp = "Error";
        switch (cod) {
            case "0":
                resp = "Falla general del sistema contacte su proveedro";
                break;
            case "1":
                resp = "Registro Guardado";
                break;
            case "2":
                resp = "Registro Eliminado";
                break;
            case "3":
                resp = "true";
                break;
            case "4":
                resp = "El plano se a momdificado con exito";
                break;
            case "-1":
                resp = "El registro ya existe";
                break;
            case "-2":
                resp = "Error al guardar el registro";
                break;
            case "-3":
                resp = "Error en el evento";
                break;
            case "-4":
                resp = "false";
                break;
            case "-5":
                resp = "Error al modificar el plano";
                break;
            case "-6":
                resp = "El Año y el Mes ya está ingresados en el sistema por favor selecciónelo y edítelo";
                break;
        }
        return resp;
    }

    public Timestamp getDateTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    public String sTime() {
        java.util.Date uDate = new java.util.Date();
        Format formatter = new SimpleDateFormat("HH:mm:ss");
        System.out.println(formatter.format(uDate));
        return formatter.format(uDate);
    }
}
