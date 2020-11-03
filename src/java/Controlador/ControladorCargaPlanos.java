/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Herramienta.Herramienta;
import Modelos.ModeloArchivos;
import Modelos.ModeloAuditoria;
import Modelos.ModeloEine;
import Modelos.ModeloEstadoPlanos;
import Modelos.ModeloFbl3m;
import Modelos.ModeloFechas;
import Modelos.ModeloMb51;
import Modelos.ModeloMe80fn;
import Modelos.ModeloMrpData;
import Modelos.ModeloProveedor;
import Modelos.ModeloUsuario;
import Modelos.ModeloVarios;
import Modelos.ModeloVendorType;
import Modelos.ModeloZ39;
import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCargaPlanos {

    Herramienta herramienta = new Herramienta();

    public String procesarCarga(LinkedList<ModeloArchivos> listModeloArchivoses, HttpServletRequest request, HttpServletResponse response) {
        String resultado = "false";
        for (ModeloArchivos listModeloArchivose : listModeloArchivoses) {
            try {
                String RutaDispo = listModeloArchivose.getRuta();
                String nombre = listModeloArchivose.getNombre();
                StringTokenizer st = new StringTokenizer(nombre, ".");
                nombre = st.nextToken();
                if (listModeloArchivose.getNombre().contains("EINE")) {
                    nombre = "EINE";
                }
                if (nombre.contains("MRPDATA")) {
                    nombre = "MRPDATA";
                }
                if (nombre.contains("Proveedores")) {
                    nombre = "PROVEEDORES";
                }
                if (listModeloArchivose.getNombre().contains("ICO")) {
                    nombre = "ICO";
                }
                if (nombre.contains("FBL3N")) {
                    nombre = "FBL3N";
                }
//                if (nombre.contains("MB51_Compras")) {
//                    nombre = "MB51";
//                }
                if (nombre.contains("Z39")) {
                    nombre = "Z39";
                }
                if (nombre.contains("ME80FN")) {
                    nombre = "ME80FN";
                }
                if (nombre.contains("MB51_Consumos")) {
                    nombre = "MB51_CONSUMOS";
                }
                if (nombre.contains("POVR")) {
                    nombre = "POVR";
                }
                if (nombre.contains("INVENTARIO") || nombre.contains("Inventario")) {
                    nombre = "INVENTARIO";
                }

                switch (nombre) {
//                    case "MB51":
//                        resultado = CargarCSV_MB51_INFILE_new(RutaDispo, request);
//                        if ("true".equals(resultado)) {
//
//                        }
//                        break;
                    case "FBL3N":
                        resultado = CargarCSV_FBL3N_INFILE(RutaDispo, request);
                        break;
                    case "ME80FN":
                        resultado = CargarCSV_ME80FN_INFILE(RutaDispo, request);
                        break;
                    case "MRPDATA":
                        resultado = CargarCSV_MRPDATA_INFILE(RutaDispo, request);
                        break;
                    case "PROVEEDORES":
                        resultado = CargarCSV_PROVEEDORES_INFILE(RutaDispo, request);
                        break;
                    case "EINE":
                        resultado = CargarCSV_EINE_INFILE(RutaDispo, request);
                        break;
                    case "ICO":
                        resultado = CargarCSV_ICO_INFILE(RutaDispo, request);
                        break;
                    case "Z39":
                        resultado = CargarCSV_Z39_INFILE_new(RutaDispo, request);
                        break;
                    case "MB51_CONSUMOS":
                        resultado = CargarCSV_MB51_CONSUMOS_INFILE(RutaDispo, request);
                        break;
                    case "POVR":
                        resultado = CargarCSV_POVR_INFILE(RutaDispo, request);
                        break;
                    case "INVENTARIO":
                        resultado = CargarCSV_InventarioInicial_INFILE(RutaDispo, request);
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ControladorCargaPlanos.class.getName()).log(Level.SEVERE, null, ex);
                resultado = "false";
            }
        }
        return resultado;
    }

    LinkedList<ModeloMb51> LinkModeloMb51_New = new LinkedList<ModeloMb51>();
    LinkedList<ModeloMb51> LinkModeloMb51_Upd = new LinkedList<ModeloMb51>();

    public String Upload(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String resultado = "false";
        String formato = "";

        try {
            long tamanorequies = request.getPart("archivo").getSize();
            formato = request.getParameter("Formato");
            Part arch = request.getPart("archivo");
            String fileName = Paths.get(arch.getSubmittedFileName()).getFileName().toString();
            //System.out.println(fileName);
            InputStream is = arch.getInputStream();
            File detino = new File("C:\\Zred\\SunChemical\\Upload\\");
            if (detino.exists() != true) {
                detino.mkdirs();
            }
            File file = new File(detino, fileName + "_" + ObtenerFecha() + ".csv");
            try (InputStream input = arch.getInputStream()) {
                file.delete();
                Files.copy(input, file.toPath());
            }
            //String RutaDispo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Compras\\Compras_10-11-2019.csv";//
            String RutaDispo = file.getPath();
            File RutaFinal = new File(RutaDispo);
            //File RutaFinal = new File("C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Compras\\Compras_10-11-2019.csv");
            if (tamanorequies == RutaFinal.length()) {
                resultado = "true";
            }
            String d = request.getParameter("IdNombrePlano");
            //System.out.println(d);
            String nombre = request.getParameter("NombrePlano");

            if (nombre.contains("EINE")) {
                nombre = "EINE";
            }
            if (nombre.contains("MRPDATA")) {
                nombre = "MRPDATA";
            }
            if (nombre.contains("Proveedores")) {
                nombre = "PROVEEDORES";
            }
            if (nombre.contains("ICO")) {
                nombre = "ICO";
            }
            if (nombre.contains("FBL3N")) {
                nombre = "FBL3N";
            }
            if (nombre.contains("MB51_Compras")) {
                nombre = "MB51";
            }
            if (nombre.contains("Z39")) {
                nombre = "Z39";
            }
            if (nombre.contains("ME80FN")) {
                nombre = "ME80FN";
            }
            if (nombre.contains("MB51_Consumos")) {
                nombre = "MB51_CONSUMOS";
            }
            if (nombre.contains("POVR")) {
                nombre = "POVR";
            }
            if (nombre.contains("INVENTARIO")) {
                nombre = "INVENTARIO";
            }

            switch (nombre) {
                case "MB51":
                    resultado = CargarCSV_MB51_INFILE_new(RutaDispo, request);
                    if ("true".equals(resultado)) {
                    }
                    break;
//                case "FBL3N":
//                    resultado = CargarCSV_FBL3N_INFILE(RutaDispo, request);
//                    break;
//                case "ME80FN":
//                    resultado = CargarCSV_ME80FN_INFILE(RutaDispo, request);
//                    break;
//                case "MRPDATA":
//                    resultado = CargarCSV_MRPDATA_INFILE(RutaDispo, request);
//                    break;
//                case "PROVEEDORES":
//                    resultado = CargarCSV_PROVEEDORES_INFILE(RutaDispo, request);
//                    break;
//                case "EINE":
//                    resultado = CargarCSV_EINE_INFILE(RutaDispo, request);
//                    break;
//                case "ICO":
//                    resultado = CargarCSV_EINE_INFILE(RutaDispo, request);
//                    break;
//                case "Z39":
//                    resultado = CargarCSV_Z39_INFILE_new(RutaDispo, request);
//                    break;
                default:
                    resultado = "false";
                    break;
            }
        } catch (IOException | ServletException e) {
            System.out.println("Error en la carga del plano " + formato + "  " + e);
            resultado = "false";
        }
        return resultado;
    }

    public String CargarCSV_MB51_INFILE1(String Ruta, HttpServletRequest request) throws IOException, SQLException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        //System.out.println(ano + " " + mes);
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51 CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

//        String Sqlborrar = "delete from Mb51";
//
//        if (controladorMrpdata.Insert(Sqlborrar)) {
//            //Realizado = "true";
//
//        }
        herramienta.setEventoProcesado("Inicia Carga de Archivo 1%");
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano MB51 en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

            ControladorMb51 controladorMb51 = new ControladorMb51();
            LinkedList<ModeloMb51> LinkModeloMb51s;
            LinkModeloMb51s = controladorMb51.Select();
            LinkedList<ModeloMb51> LinkModeloMb51 = new LinkedList<ModeloMb51>();
            Integer Contador = 0;
            int Porcentaje = 10;
            int VUeltas = LinkModeloMb51s.size() / 20;
            int sumador = 1;

            for (ModeloMb51 modeloMb51 : LinkModeloMb51s) {

                if (sumador == VUeltas) {
                    herramienta.setEventoProcesado("Verificacion de Cambios en Batch " + Porcentaje + "%");
                    System.err.println("Verificacion de Cambios en Batch " + Porcentaje + "%");
                    Porcentaje++;
                    sumador = 1;
                }
                sumador++;

                //BUSCAMOS EN Z39 SI HAY ALGUN CAMBIO DE BATCH
                CambiosBatch(modeloMb51);
            }
            Contador = 0;

            Porcentaje = 30;
            VUeltas = LinkModeloMb51_New.size() / 40;
            sumador = 1;

            for (ModeloMb51 modeloMb51 : LinkModeloMb51_New) {

                if (sumador == VUeltas) {
                    herramienta.setEventoProcesado("Consultas en Archivos Base " + Porcentaje + "%");
                    System.err.println("Consultas en Archivos Base " + Porcentaje + "%");
                    Porcentaje++;
                    sumador = 1;
                }
                sumador++;

                LinkModeloMb51.add(FinalComprasMB51_CargaCSV_20(modeloMb51));
            }

            //System.out.println("Termina:" + new Date());
            String Sqlborrar = "delete from Mb51 WHERE IdArchivo is null";

            if (controladorMrpdata.Insert(Sqlborrar)) {
                //Realizado = "true";

            }
            controladorMb51.InsertList(LinkModeloMb51);

            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_MB51_INFILE_new(String Ruta, HttpServletRequest request) throws IOException, SQLException {
        /*
         * Variables de Año y Mes
         */

        String Reprocesar = request.getParameter("Reprocesar");

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";

        ControladorFechas controladorFechas = new ControladorFechas();
        ModeloFechas modeloFechas = controladorFechas.GetModeloFechas(mes, ano, "Compras");

        boolean mes_abierto = false;

        if (modeloFechas != null) {
            if (modeloFechas.getEstadoCompras().contentEquals("Abierto")) {
                mes_abierto = true;
            }
        }

        if (mes_abierto) {

            if (Reprocesar.contentEquals("False")) {
                String Sqlborrar = "delete from MB51_planos where fecha = '" + ano + "-" + mes + "'";

                if (controladorMrpdata.Insert(Sqlborrar)) {
                    //Realizado = "true";
                }
                Ruta = Ruta.replace("\\", "/");
                String SqlInsertMasivo
                        = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51_planos CHARACTER SET LATIN1"
                        + " FIELDS TERMINATED BY ','"
                        + " ENCLOSED BY '\"'"
                        + " LINES TERMINATED BY '\\r\\n'"
                        + " IGNORE 1 LINES"
                        + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor)";

                //System.out.println("Consulta: " + SqlInsertMasivo);
                String SqlUpdateFecha = "Update MB51_planos set fecha = '" + ano + "-" + mes + "' where fecha is null";

                herramienta.setEventoProcesado("Inicia Carga de Archivo 1%");
                //if (controladorMrpdata.Insert(SqlInsertMasivo)) {
                controladorMrpdata.Insert(SqlInsertMasivo);
                controladorMrpdata.Insert(SqlUpdateFecha);

                //Auditoria
                ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano MB51 en el sistema.");
                ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                controladorAuditoria.Insert(modeloAuditoria);

            }

            ControladorMb51 controladorMb51 = new ControladorMb51();
            LinkedList<ModeloMb51> LinkModeloMb51s;
            LinkModeloMb51s = controladorMb51.Select();
            LinkedList<ModeloMb51> LinkModeloMb51 = new LinkedList<ModeloMb51>();
            Integer Contador = 0;
            //System.out.println("Inicio:" + new Date());
            int Porcentaje = 10;
            int VUeltas = LinkModeloMb51s.size() / 20;
            int sumador = 1;

            for (ModeloMb51 modeloMb51 : LinkModeloMb51s) {

                if (sumador == VUeltas) {
                    herramienta.setEventoProcesado("Cambios en Batch " + Porcentaje + "%");
                    System.err.println("Cambios en Batch " + Porcentaje + "%");
                    Porcentaje++;
                    sumador = 1;
                }
                sumador++;

                //BUSCAMOS EN Z39 SI HAY ALGUN CAMBIO DE BATCH
                CambiosBatch(modeloMb51);
                //System.out.println("Registros Procesados: " + Contador++);
            }

            ModeloEstadoPlanos modeloEstadoPlanos = Archivo(request);
            Contador = 0;
            Porcentaje = 30;
            VUeltas = LinkModeloMb51_New.size() / 40;
            sumador = 1;

            for (ModeloMb51 modeloMb51 : LinkModeloMb51_New) {

                if (modeloMb51.getPurchase_order().contentEquals("4502175823")) {
                    System.out.println("Controlador.ControladorCargaPlanos.CargarCSV_MB51_INFILE_new()");
                }

                if (sumador == VUeltas) {
                    herramienta.setEventoProcesado("Consultas en Archivos Base " + Porcentaje + "%");
                    System.err.println("Consultas en Archivos Base " + Porcentaje + "%");
                    Porcentaje++;
                    sumador = 1;
                }
                sumador++;
                //cambio 09-10-2020
                //modeloMb51 = FinalComprasMB51_CargaCSV_20(modeloMb51);

                modeloMb51.setIdArchivo(modeloEstadoPlanos.getId());
                LinkModeloMb51.add(modeloMb51);

            }

//Inicio cambio 09-10-2020*************************************************************************
            //System.out.println("Termina:" + new Date());
            String Sqlborrar0 = "delete from Mb51_tmp";

            if (controladorMrpdata.Insert(Sqlborrar0)) {
                //Realizado = "true";

            }
            controladorMb51.InsertList_Tmp(LinkModeloMb51);
            LinkModeloMb51 = null;
            LinkModeloMb51 = new LinkedList<ModeloMb51>();

            //modeloMb51 = FinalComprasMB51_CargaCSV_20(modeloMb51);
            //hacer otro select a la tabla con el id del archivo y consolidarlo
            LinkModeloMb51s = null;
            LinkModeloMb51s = controladorMb51.Select_Todos_x_Archivo();
            for (ModeloMb51 modeloMb51 : LinkModeloMb51s) {

                modeloMb51 = FinalComprasMB51_CargaCSV_20(modeloMb51);
                modeloMb51.setIdArchivo(modeloEstadoPlanos.getId());
                LinkModeloMb51.add(modeloMb51);

            }

//Fin cambio 09-10-2020*************************************************************************                
            //System.out.println("Termina:" + new Date());
            String Sqlborrar1 = "delete from Mb51 WHERE IdArchivo is null";

            if (controladorMrpdata.Insert(Sqlborrar1)) {
                //Realizado = "true";

            }
            herramienta.setEventoProcesado("Actualizacion Final de Compras 70%");
            System.err.println("Actualizacion de Final de Compras 70%");
            controladorMb51.InsertList(LinkModeloMb51);
            herramienta.setEventoProcesado("Actualizacion de Fecha Archivo de Compras 98%");
            System.err.println("Actualizacion de Fecha Archivo de Compras 98%");
            String SqlUpdateFecha = "Update MB51 set fecha = '" + ano + "-" + mes + "' where fecha is null";
            controladorMrpdata.Insert(SqlUpdateFecha);
            System.err.println("--");
            herramienta.setEventoProcesado("--");

            Realizado = "true";
            //}
        }
        return Realizado;
    }

    public String CargarCSV_FBL3N_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";

        String Sqlborrar = "delete from FBL3M where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE FBL3M CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Document_Number,Document_type,Document_Date,Posting_Date,Cost_Center,Profit_Center,YearMonth,Account,Plant,Material,Quantity,Amount_in_local_currency,Local_Currency,Purchasing_Document,Reference,Document_currency,Offsetting_acct_no,Base_Unit_of_Measure,Alternative_Account_No,Transaction_Code,Text,Assignment)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        String SqlUpdateFecha = "Update FBL3M set fecha = '" + ano + "-" + mes + "' where fecha is null";

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            controladorMrpdata.Insert(SqlUpdateFecha);
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano FBL3N en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);
            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_ME80FN_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";

        String Sqlborrar = "delete from ME80FN where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE ME80FN CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Purchasing_Document,Material_Doc_Year,Material_Document,Document_Date,Material,Short_Text,Batch,Item,Movement_type,Posting_Date,Delivery_Completed,Plant,Quantity,Order_Unit,Amt_in_loc_cur,Amount,Currency,Valuation_Type,Entry_Date,Local_currency,Reference_Doc_Item,Invoice_Value,Invoice_Value_in_FC)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        String SqlUpdateFecha = "Update ME80FN set fecha = '" + ano + "-" + mes + "' where fecha is null";

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            controladorMrpdata.Insert(SqlUpdateFecha);
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano ME80FN en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);
        }
        return Realizado;
    }

    public String CargarCSV_MRPDATA_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MRPDATA CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Material_Description,Profit_Center,Material_Type,Plant,Procurement_type,Base_Unit_of_Measure_1,Plant_sp_matl_status,Overhead_Group,Special_procurement)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from MRPDATA";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano MRPDATA en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);
        }
        return Realizado;
    }

    public String CargarCSV_PROVEEDORES_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE PROVEEDOR CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Vendors, Name, Vendor_Type, Moneda)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from PROVEEDOR";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano PROVEEDORES en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);
        }
        return Realizado;
    }

    public String CargarCSV_EINE_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE EINE CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Purchasing_info_rec,Plant,Created_On,Purchasing_Group,Currency,Standard_PO_Quantity,Planned_Deliv_Time,Net_Price,Price_unit,Order_Price_Unit,Valid_to,Quantity_Conversion_1,Quantity_Conversion_2,Effective_Price,Acknowledgment_Reqd,Confirmation_Control,Incoterms_1,Incoterms_2,Material,Vendor,Link_Material_vendor,Porcentaje_Additional)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from EINE";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano EINE en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }
        return Realizado;
    }

    public String CargarCSV_ICO_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE EINE CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Vendor,Porcentaje_Additional)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

//        String Sqlborrar = "delete from EINE";
//
//        if (controladorMrpdata.Insert(Sqlborrar)) {
//            //Realizado = "true";
//        }
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano EINE en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }
        return Realizado;
    }

    public String CargarCSV_Z39_INFILE_new(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";

        String Sqlborrar = "delete from Z39 where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE Z39 CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order1)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        String SqlUpdateFecha = "Update Z39 set fecha = '" + ano + "-" + mes + "' where fecha is null";

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            controladorMrpdata.Insert(SqlUpdateFecha);
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano Z39 en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);
        }
        return Realizado;
    }

    public String CargarCSV_MB51_CONSUMOS_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from MB51_CONSUMOS where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51_CONSUMOS CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order_)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        //ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        String SqlUpdateFecha = "Update MB51_CONSUMOS set fecha = '" + ano + "-" + mes + "' where fecha is null";

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            controladorMrpdata.Insert(SqlUpdateFecha);
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano EINE en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }
        return Realizado;
    }

    public String CargarCSV_POVR_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from POVR where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE POVR CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Material,Material_Description,Process_Order_Number,Process_Order_Type,Material_Group_Packaging,Profit_Center,UOM,Material_type,Existing_Material_Costing_Lot_Size,MRP_Planned_Quantity,Consumption_Qty_Actual_I_P,Delivered_Qty_Actual_O_P,Yield_Variance_Qty,Yield_Variance_Porcentaje,Actual_Material_Cost,Labor_Cost_Actual,Machine_Cost_Actual,Overhead_Cost_Actual,Total_Cost_Actual_Output,Physical_Inventory_Yield_Loss_cost_Actu,Standard_Price,Calculated_Production_Variance,Conversion_Cost_Per_Unit,Display_Currency,Batch_Number,Product_Hierarchy,Actual_Start_Date,Actual_Finish_Date)";

        //System.out.println("Consulta: " + SqlInsertMasivo);
        String SqlUpdateFecha = "Update POVR set fecha = '" + ano + "-" + mes + "' where fecha is null";

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            controladorMrpdata.Insert(SqlUpdateFecha);
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano EINE en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }
        return Realizado;
    }

    public String CargarCSV_InventarioInicial_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");

        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE InventarioInicial CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Material_Description,Batch,Link_Material_Batch,FIFO_Cost_UNIT)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from InventarioInicial";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano InventarioInicial en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }
        return Realizado;
    }

    public void CambiosBatch(ModeloMb51 modeloMb51) {

        Double Quantity_mb51_Original = Double.valueOf(modeloMb51.getQuantity());
        Double Amt_in_loc_cur_mb51_Original = Double.valueOf(modeloMb51.getAmt_in_loc_cur());

        LinkModeloMb51_New.add(modeloMb51);

        LinkedList<ModeloZ39> modeloZ39s = null;
        LinkedList<ModeloZ39> modeloZ39s_1 = null;
        //ModeloMb51 modeloMb51_Upd = modeloMb51;
        ControladorZ39 controladorZ39 = new ControladorZ39();
        ControladorMb51 controladorMb51 = new ControladorMb51();
        modeloZ39s = controladorZ39.Select("Select * from Z39 where Material = '" + modeloMb51.getMaterial() + "' AND Batch = '" + modeloMb51.getBatch() + "'");
        int Contador = 1;
        if (modeloZ39s.size() > 0) {
            for (ModeloZ39 modeloZ39 : modeloZ39s) {
//                if (Contador == 1) {
//                    Contador = 2;
                modeloZ39s_1 = controladorZ39.Select("Select * from Z39 where Material_Document = '" + modeloZ39.getMaterial_Document() + "' ORDER BY Quantity");
                if (modeloZ39s_1.size() > 0) {
                    for (ModeloZ39 modeloZ39_1 : modeloZ39s_1) {

                        Double Quantity_Z39 = Double.valueOf(modeloZ39_1.getQuantity());
//                        Double Qty_in_unit_of_entry_Z39 = Double.valueOf(modeloZ39_1.getQty_in_unit_of_entry());
//
//                        Double Quantity_mb51 = Double.valueOf(modeloMb51.getQuantity());
//                        Double Qty_in_unit_of_entry_mb51 = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
                        Double Amt_in_loc_cur_mb51 = 0.0;

                        if (Quantity_mb51_Original != 0) {
                            Amt_in_loc_cur_mb51 = Quantity_Z39 * Amt_in_loc_cur_mb51_Original / Quantity_mb51_Original;
                        }
                        ModeloMb51 modeloMb51_New = new ModeloMb51();
                        modeloMb51_New.setPlant(modeloMb51.getPlant());
                        modeloMb51_New.setPurchase_order(modeloMb51.getPurchase_order());
                        modeloMb51_New.setMaterial(modeloMb51.getMaterial());
                        modeloMb51_New.setMaterial_Description(modeloMb51.getMaterial_Description());
                        modeloMb51_New.setBatch(modeloZ39_1.getBatch());
                        modeloMb51_New.setMovement_type(modeloMb51.getMovement_type());
                        modeloMb51_New.setMovement_Type_Text(modeloMb51.getMovement_Type_Text());
                        modeloMb51_New.setItem(modeloMb51.getItem());
                        modeloMb51_New.setQuantity(modeloZ39_1.getQuantity());
                        modeloMb51_New.setQty_in_unit_of_entry(modeloZ39_1.getQty_in_unit_of_entry());
                        modeloMb51_New.setUnit_of_Entry(modeloMb51.getUnit_of_Entry());
                        modeloMb51_New.setAmt_in_loc_cur(String.format("%.5f", Amt_in_loc_cur_mb51).replace(",", ".") + "");
                        modeloMb51_New.setCurrency(modeloMb51.getCurrency());
                        modeloMb51_New.setStorage_Location(modeloMb51.getStorage_Location());
                        modeloMb51_New.setPosting_Date(modeloMb51.getPosting_Date());
                        modeloMb51_New.setDocument_Date(modeloMb51.getDocument_Date());
                        modeloMb51_New.setMaterial_Document(modeloMb51.getMaterial_Document());
                        modeloMb51_New.setUser_Name(modeloMb51.getUser_Name());
                        modeloMb51_New.setVendor(modeloMb51.getVendor());
                        modeloMb51_New.setVendor_Name(modeloMb51.getVendor_Name());
                        modeloMb51_New.setVendor_Type(modeloMb51.getVendor_Type());

                        LinkModeloMb51_New.add(modeloMb51_New);

                    }
                    break;
                }
//                }
            }
        }
    }

    public ModeloMb51 FinalComprasMB51_CargaCSV_20(ModeloMb51 modeloMb51) throws SQLException {

        if (modeloMb51.getPurchase_order().contentEquals("4502132460")) {
            System.out.println("Controlador.ControladorCargaPlanos.CargarCSV_MB51_INFILE_new()");
        }

        ControladorVarios controladorVarios = new ControladorVarios();
        ModeloVarios modeloVarios = controladorVarios.LLenarModelos(modeloMb51);

        //BUSCAMOS EL PROVEEDOR
        ModeloProveedor modeloProveedor = modeloVarios.getModeloProveedor();
        //LLENAMOS COLUMNA U
        modeloMb51.setVendor_Name(modeloProveedor.getName());
        //LLENAMOS COLUMNA V
        modeloMb51.setVendor_Type(modeloProveedor.getVendor_Type());

        String Month = modeloMb51.getPosting_Date().split("/")[1];
        //LLENAMOS COLUMNA W
        modeloMb51.setMonth(Month);

        String Period = modeloMb51.getPosting_Date().split("/")[2];
        //LLENAMOS COLUMNA X
        modeloMb51.setPeriod(Period);

        Double Quantity = 0.0;
        if (Double.valueOf(modeloMb51.getQuantity()) != null) {
            Quantity = Double.valueOf(modeloMb51.getQuantity());
        }
        Double Qty_in_unit_of_entry = 0.0;
        if (Double.valueOf(modeloMb51.getQty_in_unit_of_entry()) != null) {
            Qty_in_unit_of_entry = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
        }

        Double Cost_Unit_SAP_en_KG = 0.0;
        if (modeloMb51.getUnit_of_Entry().contentEquals("GAL") || modeloMb51.getUnit_of_Entry().contentEquals("L") || modeloMb51.getUnit_of_Entry().contentEquals("LB")) {
            if (Quantity != 0) {
                Cost_Unit_SAP_en_KG = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Quantity;
            } else {
                Cost_Unit_SAP_en_KG = 0.0;
            }
        } else {
            if (Qty_in_unit_of_entry != 0) {
                Cost_Unit_SAP_en_KG = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Qty_in_unit_of_entry;
            } else {
                Cost_Unit_SAP_en_KG = 0.0;
            }
        }
        //LLENAMOS COLUMNA Y
        modeloMb51.setCost_Unit_SAP_en_KG(String.format("%.5f", Cost_Unit_SAP_en_KG).replace(",", "."));

        //BUSCAMOS EL MATERIAL
        ModeloMrpData modeloMrpData = modeloVarios.getModeloMrpData();
        modeloMb51.setMaterial_Description(modeloMrpData.getMaterial_Description());

        //LLENAMOS COLUMNA Z
        modeloMb51.setMaterial_Type(modeloMrpData.getMaterial_Type());
        //LLENAMOS COLUMNA AA
        modeloMb51.setProfit_Center(modeloMrpData.getProfit_Center());
        //LLENAMOS COLUMNA AB
        modeloMb51.setLink1_Material_Batch(modeloMb51.getMaterial() + modeloMb51.getBatch());
        //LLENAMOS COLUMNA AC
        modeloMb51.setLink2_PO_position(modeloMb51.getPurchase_order() + modeloMb51.getItem());
        //LLENAMOS COLUMNA AD
        modeloMb51.setReferencia_vendor(modeloMb51.getMaterial() + modeloMb51.getVendor());

        //BUSCAMOS EL TotalQ_ME80FN
        LinkedList<ModeloMe80fn> LstmodeloMe80fn = modeloVarios.getLstmodeloMe80fn();
        Double Me80fnQuantity = 0.0;
        String O_Unit_ME80FN = "";
        String Moneda = "";
        Double TOTAL_INVOICE_VALUE = 0.0;
        Double Me80fnAmount = 0.0;

        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE + Double.valueOf(modeloMe80fn.getAmt_in_loc_cur());
            Me80fnAmount = Me80fnAmount + Double.valueOf(modeloMe80fn.getAmount());

            Me80fnQuantity = Me80fnQuantity + Double.valueOf(modeloMe80fn.getQuantity());
            O_Unit_ME80FN = modeloMe80fn.getOrder_Unit();
            if (!modeloMe80fn.getCurrency().contentEquals("")) {
                Moneda = modeloMe80fn.getCurrency();
            }
            //break;
        }
        //LLENAMOS COLUMNA AE
        modeloMb51.setTotalQ_ME80FN(String.format("%.5f", Me80fnQuantity).replace(",", "."));

        //LLENAMOS COLUMNA AF
        modeloMb51.setO_Unit_ME80FN(O_Unit_ME80FN);

        //CALCULAMOS TotalQ_Porcentaje   
        Double TotalQ_ME80FN = 0.0;
        if (Double.valueOf(modeloMb51.getTotalQ_ME80FN()) != null) {
            TotalQ_ME80FN = Double.valueOf(modeloMb51.getTotalQ_ME80FN());
        }

        Double TotalQ_Porcentaje = 0.0;
        try {
            if (TotalQ_ME80FN == 0.0) {
                TotalQ_Porcentaje = 0.0;
            } else {
                if (modeloMb51.getUnit_of_Entry().contentEquals(O_Unit_ME80FN)) {
                    TotalQ_Porcentaje = Qty_in_unit_of_entry / TotalQ_ME80FN;
                } else {
                    TotalQ_Porcentaje = Quantity / TotalQ_ME80FN;
                }
            }

        } catch (Exception e) {
            TotalQ_Porcentaje = 0.0;
        }
        //LLENAMOS COLUMNA AG
        //modeloMb51.setTotalQ_Porcentaje(String.format("%.5f", TotalQ_Porcentaje));
        modeloMb51.setTotalQ_Porcentaje(String.format("%.5f", TotalQ_Porcentaje).replace(",", "."));

        //LLENAMOS COLUMNA AH
        modeloMb51.setTOTAL_INVOICE_VALUE(String.format("%.5f", TOTAL_INVOICE_VALUE).replace(",", "."));

        Double Factura_Value_Unit = 0.0;

        if (modeloMb51.getUnit_of_Entry().contentEquals("GAL") || modeloMb51.getUnit_of_Entry().contentEquals("L") || modeloMb51.getUnit_of_Entry().contentEquals("LB")) {
            if (Quantity != 0 && TotalQ_Porcentaje != 0) {
                Factura_Value_Unit = TOTAL_INVOICE_VALUE / Quantity * TotalQ_Porcentaje;
            }
        } else {
            if (TotalQ_Porcentaje != 0 && Qty_in_unit_of_entry != 0) {
                Factura_Value_Unit = TOTAL_INVOICE_VALUE / Qty_in_unit_of_entry * TotalQ_Porcentaje;
            }
        }

        //LLENAMOS COLUMNA AI
        modeloMb51.setFactura_Value_Unit(String.format("%.5f", Factura_Value_Unit).replace(",", "."));

        //Double Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        Double PIR = 0.0;
        if (Cost_Unit_SAP_en_KG != 0) {
            PIR = Factura_Value_Unit / Cost_Unit_SAP_en_KG;
        }
        //LLENAMOS COLUMNA AJ
        //modeloMb51.setPIR_Porcentaje(String.format("%.5f", PIR));
        modeloMb51.setPIR_Porcentaje_del_Costo(String.format("%.5f", PIR).replace(",", "."));

        //BUSCAMOS MONEDA
        //ModeloMe80fn modeloMe80fn = controladorMe80fn.SelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Movement_type <> ''");
        //LLENAMOS COLUMNA AK
        Double Precio_Unit_moneda_compra = 0.0;
        if (Me80fnQuantity != 0) {
            Precio_Unit_moneda_compra = Me80fnAmount / Me80fnQuantity;
        }
        modeloMb51.setPrecio_Unit_moneda_compra(String.format("%.5f", Precio_Unit_moneda_compra).replace(",", "."));

        //LLENAMOS COLUMNA AL
        //modeloMb51.setMoneda(modeloMe80fn.getCurrency());
        modeloMb51.setMoneda(Moneda);

        //LLENAMOS COLUMNA AM
        modeloMb51.setLink3_PO_Item(modeloMb51.getPurchase_order() + modeloMb51.getMaterial());

        Double Freight = 0.0;
        Double Dutys = 0.0;
        Double Arancel = 0.0;

        //LinkedList<ModeloFbl3m> ListmodeloFbl3m = null;
        LinkedList<ModeloFbl3m> ListmodeloFbl3m_Freight = null;
        LinkedList<ModeloFbl3m> ListmodeloFbl3m_DUTYS = null;
        LinkedList<ModeloFbl3m> ListmodeloFbl3m_ARANCEL = null;

        if (modeloMb51.getVendor_Type().contains("Nal")) {
            Freight = 0.0;
            Dutys = 0.0;
            Arancel = 0.0;
        } else {

            //BUSCAMOS Freight
            Boolean IcoEncontrado = false;
            ListmodeloFbl3m_Freight = modeloVarios.getListmodeloFbl3m_Freight();
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m_Freight) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Freight = Freight + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                    IcoEncontrado = true;
                }
            }
            //BUSCAMOS DUTYS
            ListmodeloFbl3m_DUTYS = modeloVarios.getListmodeloFbl3m_DUTYS();
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m_DUTYS) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Dutys = Dutys + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                    IcoEncontrado = true;
                }
            }
            //BUSCAMOS ARANCEL
            ListmodeloFbl3m_ARANCEL = modeloVarios.getListmodeloFbl3m_ARANCEL();
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m_ARANCEL) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Arancel = Arancel + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                    IcoEncontrado = true;
                }
            }
            if (!IcoEncontrado) {
                modeloMb51.setNovedadIco("X");
            }

        }

        //LLENAMOS COLUMNA AN      
        modeloMb51.setFreight(String.format("%.5f", Freight).replace(",", "."));

        //LLENAMOS COLUMNA AO
        modeloMb51.setDutys(String.format("%.5f", Dutys).replace(",", "."));

        //LLENAMOS COLUMNA AP
        modeloMb51.setArancel(String.format("%.5f", Arancel).replace(",", "."));

        ModeloEine modeloEine = null;
        modeloEine = modeloVarios.getModeloEine();

        Double Porcentaje_Adicional = 0.0;
        Double Adicionales_al_CTO_Estandar = 0.0;
        if (modeloEine != null) {
            Porcentaje_Adicional = Double.valueOf(modeloEine.getPorcentaje_Additional());
            Adicionales_al_CTO_Estandar = Double.valueOf(modeloEine.getPorcentaje_Additional());
        }

        Double Total_Costos_Adicionales = 0.0;
        Double Participac_Adicionales = 0.0;
        if (modeloProveedor.getVendor_Type().contains("ICO")) {
            Total_Costos_Adicionales = TOTAL_INVOICE_VALUE * Porcentaje_Adicional;
            Participac_Adicionales = Porcentaje_Adicional;
        } else {
            Total_Costos_Adicionales = Freight + Dutys + Arancel;
            if (TOTAL_INVOICE_VALUE != 0) {
                Participac_Adicionales = Total_Costos_Adicionales / TOTAL_INVOICE_VALUE;
            }

        }

        //LLENAMOS COLUMNA AQ
        modeloMb51.setTotal_Costos_Adicionales(String.format("%.5f", Total_Costos_Adicionales).replace(",", "."));

        //LLENAMOS COLUMNA AR
        modeloMb51.setParticipac_Adicionales(String.format("%.5f", Participac_Adicionales).replace(",", "."));

        //LLENAMOS COLUMNA AS
        modeloMb51.setAdicionales_al_CTO_Estandar(String.format("%.5f", Adicionales_al_CTO_Estandar).replace(",", "."));

        Double Variance = 0.0;
        if (Participac_Adicionales != 0.0) {
            Variance = Porcentaje_Adicional - Participac_Adicionales;
        }
        //LLENAMOS COLUMNA AT
        modeloMb51.setVariance(String.format("%.5f", Variance).replace(",", "."));

        Double Total_Costos = 0.0;
        Double Amt_in_loc_cur = Double.valueOf(modeloMb51.getAmt_in_loc_cur());

        //BUSCAR SI ES NAL_SUBCONT
        ModeloVendorType modeloVendorType = modeloVarios.getModeloVendorType();
        if (modeloVendorType != null) {
            modeloMb51.setVendor_Type("Nal-Subcont");
        }

        if (modeloMb51.getVendor_Type().contentEquals("Nal-Subcont")) {
            Total_Costos = (Total_Costos_Adicionales * TotalQ_Porcentaje + Amt_in_loc_cur);
        } else {
            Total_Costos = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;
        }

        Double Unitario_Real = 0.0;
        //LLENAMOS COLUMNA AU
        modeloMb51.setTotal_Costos(String.format("%.5f", Total_Costos).replace(",", "."));

        if (modeloMb51.getUnit_of_Entry().contentEquals("GAL") || modeloMb51.getUnit_of_Entry().contentEquals("L") || modeloMb51.getUnit_of_Entry().contentEquals("LB")) {
            if (Quantity != 0) {
                Unitario_Real = Total_Costos / Quantity;
            } else {
                Unitario_Real = 0.0;
            }
        } else {
            if (Qty_in_unit_of_entry != 0) {
                Unitario_Real = Total_Costos / Qty_in_unit_of_entry;
            } else {
                Unitario_Real = 0.0;
            }
        }
        //LLENAMOS COLUMNA AU
        modeloMb51.setUnitario_Real(String.format("%.5f", Unitario_Real).replace(",", "."));

        Double Unitario_Real_adicional_estandar = 0.0;
        //Unitario_Real_adicional_estandar = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;
        Unitario_Real_adicional_estandar = (Factura_Value_Unit * (1 + Adicionales_al_CTO_Estandar));

        //LLENAMOS COLUMNA AV
        modeloMb51.setUnitario_Real_adicional_estandar(String.format("%.5f", Unitario_Real_adicional_estandar).replace(",", "."));

        Double Unitario_estandar_SAP = 0.0;
        Unitario_estandar_SAP = Cost_Unit_SAP_en_KG;

        //LLENAMOS COLUMNA AW
        modeloMb51.setUnitario_estandar_SAP(String.format("%.5f", Unitario_estandar_SAP).replace(",", "."));

        Double Unitario_final_FIFO = Unitario_Real;

        //LLENAMOS COLUMNA AX
        modeloMb51.setUnitario_final_FIFO(String.format("%.5f", Unitario_final_FIFO).replace(",", "."));

        Double Porcentaje_Real_Vs_Estanda = 0.0;
        if (Unitario_estandar_SAP != 0) {
            Porcentaje_Real_Vs_Estanda = ((Unitario_Real / Unitario_estandar_SAP) - 1);
        }
        //LLENAMOS COLUMNA AY
        modeloMb51.setPorcentaje_Real_Vs_Estandar(String.format("%.5f", Porcentaje_Real_Vs_Estanda).replace(",", "."));

        Double Porcentaje_fifo_final_vs_Estandar = 0.0;
        if (Unitario_estandar_SAP != 0) {
            Porcentaje_fifo_final_vs_Estandar = ((Unitario_final_FIFO / Unitario_estandar_SAP) - 1);
        }
        //LLENAMOS COLUMNA AZ
        modeloMb51.setPorcentaje_fifo_final_vs_Estandar(String.format("%.5f", Porcentaje_fifo_final_vs_Estandar).replace(",", "."));

        Double Compra_valorada_a_Unit_FIFO = 0.0;
        Compra_valorada_a_Unit_FIFO = Unitario_final_FIFO * Quantity;

        //LLENAMOS COLUMNA BA
        modeloMb51.setCompra_valorada_a_Unit_FIFO(String.format("%.5f", Compra_valorada_a_Unit_FIFO).replace(",", "."));

        Double Variacion_FIFO_vs_Estandar = 0.0;
        Variacion_FIFO_vs_Estandar = Compra_valorada_a_Unit_FIFO - Amt_in_loc_cur;

        //LLENAMOS COLUMNA BB
        modeloMb51.setVariacion_FIFO_vs_Estandar(String.format("%.5f", Variacion_FIFO_vs_Estandar).replace(",", ".") + "");

        return modeloMb51;

//        ControladorMb51 controladorMb51 = new ControladorMb51();
//
//        controladorMb51.Update(modeloMb51);
        //}
    }

    public ModeloEstadoPlanos Archivo(HttpServletRequest request) {
        Herramienta herramientas = new Herramienta();
        ControladorFechas controladorFechas = new ControladorFechas();
        ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
        modeloEstadoPlanos.setNombrePlano("MB51");
        modeloEstadoPlanos.setFechaCarga(herramientas.sDate());
        modeloEstadoPlanos.setEstado("Activo");
        modeloEstadoPlanos.setIdFechas(controladorFechas.getIdFecha(request));
        ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos();
        //herramienta.setEventoProcesado("Actualizacion Proceso Final 98%");
        //System.err.println("Actualizacion Proceso Final 98%");
        controladorEstadoPlanos.Insert(modeloEstadoPlanos);
        //Selecionamos el ultimo Id del la tabal estado plano
        modeloEstadoPlanos.setId(controladorEstadoPlanos.getIdEstadoPlanos());

        return modeloEstadoPlanos;
    }

}
