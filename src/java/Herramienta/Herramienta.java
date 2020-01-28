/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramienta;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Carlos A Dominguez D
 */
public class Herramienta {

    public Date getDate() {
        Date utilDate = new Date(); //fecha actual
        long lnMilisegundos = utilDate.getTime();
        Date sqlDate = new Date(lnMilisegundos);
        return sqlDate;
    }

    public String GetDescrpCode(String cod) {
        String resp = "Error";
        switch (cod)
        {
            case "0":
                resp = "Falla general del sistema contacte su proveedro";
                break;
            case "1":
                resp = "Registro Guardado";
                break;
            case "2":
                resp = "Registro Eliminado";
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
        }
        return resp;
    }

    public Timestamp getDateTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());        
        return timestamp;
    }
}
