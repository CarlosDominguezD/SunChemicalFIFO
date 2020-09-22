/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Herramienta.Herramienta;
import Modelos.ModeloArchivos;
import Modelos.ModeloAuditoria;
import Modelos.ModeloConversiones;
import Modelos.ModeloEstadoPlanos;
import Modelos.ModeloInforme_Produccion;
import Modelos.ModeloInventarioInicial;
import Modelos.ModeloKob1;
import Modelos.ModeloMb51;
import Modelos.ModeloMb51_Consumos;
import Modelos.ModeloMrpData;
import Modelos.ModeloPovr;
import Modelos.ModeloUsuario;
import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCargaPlanosProduccion {

    Double CONVERSION_LABOR = 0.0;
    Double CONVERSION_MACHINE = 0.0;
    Double CONVERSION_OVHDS = 0.0;

    ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
    ControladorMb51_Consumos controladorMb51_Consumos = new ControladorMb51_Consumos();
    ControladorPovr controladorPovr = new ControladorPovr();
    ControladorInventario controladorInventario = new ControladorInventario();
    ControladorKob1 controladorKob1 = new ControladorKob1();
    ControladorMb51 controladorMb51 = new ControladorMb51();
    ControladorInformeProduccion controladorInformeProduccion = new ControladorInformeProduccion();
    ControladorInventarioInicial controladorInventarioInicial = new ControladorInventarioInicial();
    Herramienta herramienta = new Herramienta();
    ControladorConversiones controladorConversiones = new ControladorConversiones();

    String MES = "";
    String ANO = "";
    String MESActual = "";
    String MesKob1 = "";

    public String procesarCarga(LinkedList<ModeloArchivos> listModeloArchivoses, HttpServletRequest request, HttpServletResponse response) {
        String resultado = "false";
//        for (ModeloArchivos listModeloArchivose : listModeloArchivoses) {
//            try {
//                String RutaDispo = listModeloArchivose.getRuta();
//                String nombre = listModeloArchivose.getNombre();
//                StringTokenizer st = new StringTokenizer(nombre, ".");
//                nombre = st.nextToken();
//
//                if (nombre.contains("MB51_Consumos")) {
//                    nombre = "MB51_CONSUMOS";
//                }
//                if (nombre.contains("POVR")) {
//                    nombre = "POVR";
//                }
//                if (nombre.contains("KOB1")) {
//                    nombre = "KOB1";
//                }
//
//                switch (nombre) {
//
//                    case "MB51_CONSUMOS":
//                        resultado = CargarCSV_MB51_CONSUMOS_INFILE(RutaDispo, request);
//                        break;
//                    case "POVR":
//                        resultado = CargarCSV_POVR_INFILE(RutaDispo, request);
//                        break;
//                    case "KOB1":
//                        resultado = CargarCSV_KOB1_INFILE(RutaDispo, request);
//                        if ("true".equals(resultado)) {
//                            Herramienta herramientas = new Herramienta();
//                            ControladorFechas controladorFechas = new ControladorFechas();
//                            ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
//                            modeloEstadoPlanos.setNombrePlano("KOB1");
//                            modeloEstadoPlanos.setFechaCarga(herramientas.sDate());
//                            modeloEstadoPlanos.setEstado("Activo");
//                            modeloEstadoPlanos.setIdFechas(controladorFechas.getIdFecha(request));
//                            ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos();
//
//                            controladorEstadoPlanos.Insert(modeloEstadoPlanos);
//
//                            //Selecionamos el ultimo Id del la tabal estado plano
//                            modeloEstadoPlanos.setId(controladorEstadoPlanos.getIdEstadoPlanos());
//                            // Update Mb51
//                            Progreso("Actualizando Archivo Cargado", "95");
//                            controladorEstadoPlanos.UpdateMB51(modeloEstadoPlanos, "KOB1");
//                            Progreso("Procesamiento de Archivo de Produccin Finalizado", "100");
//                        }
//                        break;
//                }
//            } catch (IOException | SQLException ex) {
//                System.out.println("Error en la carga del plano " + "  " + ex);
//            }
//
//        }

        return resultado;

    }

    public String Upload(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String resultado = "false";
        String formato = "";
        System.err.println("Ingresa a Upload de Produccion");

        try {
            long tamanorequies = request.getPart("archivo").getSize();
            formato = request.getParameter("Formato");
            Part arch = request.getPart("archivo");
            String fileName = Paths.get(arch.getSubmittedFileName()).getFileName().toString();
            System.out.println(fileName);
            InputStream is = arch.getInputStream();
            File detino = new File("C:\\Zred\\SunChemical\\" + formato + "\\");
            if (detino.exists() != true) {
                detino.mkdirs();
            }
            File file = new File(detino, formato + "_" + ObtenerFecha() + ".csv");
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
            System.out.println(d);

            String nombre = request.getParameter("NombrePlano");

            if (nombre.contains("MB51_Consumos")) {
                nombre = "MB51_CONSUMOS";
            }
            if (nombre.contains("POVR")) {
                nombre = "POVR";
            }
            if (nombre.contains("KOB1")) {
                nombre = "KOB1";
            }

            switch (nombre) {
//                case "MB51_CONSUMOS":
//                    resultado = CargarCSV_MB51_CONSUMOS_INFILE(RutaDispo, request);
//                    break;
//                case "POVR":
//                    resultado = CargarCSV_POVR_INFILE(RutaDispo, request);
//                    break;
                case "KOB1":
                    System.err.println("CargarCSV_KOB1_INFILE");
                    resultado = CargarCSV_KOB1_INFILE(RutaDispo, request);
                    break;
//                case "INVENTARIO":
//                    resultado = CargarCSV_InventarioInicial_INFILE(RutaDispo, request);
//                    break;
                default:
                    resultado = "false";
                    break;

            }
        } catch (IOException | ServletException e) {
            System.out.println("Error en la carga del plano " + formato + "  " + e);
        }
        return resultado;
    }

    //--------------------------------------------------------------------------------------
    public String CargarCSV_MB51_CONSUMOS_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";

        String Sqlborrar = "delete from MB51_CONSUMOS where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51_CONSUMOS CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order_)";

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

    public String CargarCSV_KOB1_INFILE(String Ruta, HttpServletRequest request) throws IOException, SQLException {

        mes(request);

//        CONVERSION_LABOR = Double.parseDouble(request.getParameter("ConversionLabor"));
//        CONVERSION_MACHINE = Double.parseDouble(request.getParameter("ConversionMachine"));
//        CONVERSION_OVHDS = Double.parseDouble(request.getParameter("ConversionOvhds"));
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String mes = request.getParameter("Mes");
        String ano = request.getParameter("Ano");
        String Realizado = "false";

        String Sqlborrar = "delete from kob1_planos where fecha = '" + mes + "-" + ano + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE kob1_planos CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Functional_Area,Company_Code,Order_,CO_object_name,Cost_Element,Cost_element_name,Material,Material_Description,Plant,Period,Fiscal_Year,Dr_Cr_indicator,Total_Quantity,Unit_of_Measure,Value_TranCurr,Transaction_Currency,Value_in_Obj_Crcy,Object_Currency,Document_Number)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        //System.out.println("Consulta: " + SqlInsertMasivo);
        String SqlUpdateFecha = "Update kob1_planos set fecha = '" + mes + "-" + ano + "' where fecha is null";

        MesKob1 = validarMesMb51(ano);

        herramienta.setEventoProcesado("Inicia Carga de Archivo 1%");
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            controladorMrpdata.Insert(SqlUpdateFecha);
            Recorrido(Ruta, request);

        }
        Realizado = "true";
        return Realizado;

    }

    public String PROCESO_PRODUCCION(Connection con, String mes, String ano) throws IOException, SQLException {
        String Realizado = "false";

        ModeloConversiones modeloConversiones = new ModeloConversiones();
        modeloConversiones = controladorConversiones.Select("select * from conversiones where ano = '" + ano + "' and mes = '" + mes + "'", con);
        CONVERSION_LABOR = Double.parseDouble(modeloConversiones.getConversion_labor());
        CONVERSION_MACHINE = Double.parseDouble(modeloConversiones.getConversion_machine());
        CONVERSION_OVHDS = Double.parseDouble(modeloConversiones.getConversion_ovhds());

        LinkedList<ModeloKob1> lstModeloKob1 = new LinkedList<ModeloKob1>();
        LinkedList<ModeloKob1> lstModeloKob1Upd = new LinkedList<ModeloKob1>();

        if (mes.contentEquals(MESActual)) {
            Progreso(mes + "-" + ano + " LLenando Listado de Produccion Paso 1", "0");
            herramienta.setEventoProcesado(mes + "-" + ano + " LLenando Listado de Produccion Paso 1 0%");
            lstModeloKob1 = Select("SELECT * from kob1_planos where fecha = '" + mes + "-" + ano + "'", con, mes + "-" + ano);

            Progreso(mes + "-" + ano + " Insertando Listado General de Produccion Paso 1", "10");
            herramienta.setEventoProcesado(mes + "-" + ano + " Insertando Listado General de Produccion Paso 1 10%");
            controladorMrpdata.Insert("delete from kob1 where fecha = '" + mes + "-" + ano + "'");
            controladorKob1.InsertList_Masivo(lstModeloKob1, con, mes + "-" + ano + " Insertando Listado General de Produccion Paso 1", mes + "-" + ano, "10");
        }

        //BUSCO ORDENES QUE NO CONTENGAN E
        Progreso(mes + "-" + ano + " Depurando registros con ordenes No Piso", "20");
        herramienta.setEventoProcesado(mes + "-" + ano + " Depurando registros con ordenes No Piso 20%");

        lstModeloKob1 = null;
        lstModeloKob1 = controladorKob1.Select("SELECT * from kob1 where Procur_Type = 'E' and fecha = '" + mes + "-" + ano + "' GROUP by Order_", mes + "-" + ano, "20", "25");
        String SqlCon = "";

        for (ModeloKob1 modeloKob1 : lstModeloKob1) {
            modeloKob1.setX("x");
            lstModeloKob1Upd.add(modeloKob1);
        }
        Progreso(mes + "-" + ano + " Actualizando Ordenes con Produccion No Piso", "25");
        herramienta.setEventoProcesado(mes + "-" + ano + " Actualizando Ordenes con Produccion No Piso 25%");

        controladorKob1.UpdateList_CampoProd(lstModeloKob1Upd, con, "Actualizando Ordenes con Produccion No Piso", mes + "-" + ano, "25", "30");

        lstModeloKob1Upd = null;
        lstModeloKob1Upd = new LinkedList<ModeloKob1>();

        Progreso(mes + "-" + ano + " Seleccionando registros con ordenes Piso", "30");
        herramienta.setEventoProcesado(mes + "-" + ano + " Seleccionando registros con ordenes Piso 30%");

        lstModeloKob1 = null;
        lstModeloKob1 = controladorKob1.Select("SELECT * from kob1 where x is null or x = '' and fecha = '" + mes + "-" + ano + "'", mes + "-" + ano, "30", "35");
//            System.out.println("FINALIZA DEPURACION DE REGISTROS CON ORDENES NO PISO: " + new Date());
//            System.out.println("INICIA PROCESO DE LLENADO DE REGISTROS PARA PRODUCCION PISO: " + new Date());
        Progreso(mes + "-" + ano + " Recorrido de registros Produccion Piso", "35");
        herramienta.setEventoProcesado(mes + "-" + ano + "Recorrido de registros Produccion Piso 35%");

        int Porcentaje = 35;
        int VUeltas = lstModeloKob1.size() / 5;
        int sumador = 1;

        for (ModeloKob1 modeloKob1 : lstModeloKob1) {

            if (sumador == VUeltas) {
                herramienta.setEventoProcesado(mes + "-" + ano + " Progreso " + Porcentaje + "%");
                System.err.println(mes + "-" + ano + " Progreso " + Porcentaje + "%");
                Porcentaje++;
                sumador = 1;
            }
            sumador++;

            if (!modeloKob1.getCost_Element().contentEquals("50440")) {
                if (!modeloKob1.getCost_Element().contentEquals("50400")) {

                    if (!modeloKob1.getCost_Element().contentEquals("80004")) {
                        if (!modeloKob1.getCost_Element().contentEquals("80005")) {
                            if (!modeloKob1.getCost_Element().contentEquals("80015")) {

                                //BUSCO EN INVETNTARIO
                                modeloKob1 = CostoInventario(modeloKob1, con);

                                //BUSCO EN COMPRAS
                                modeloKob1 = CostoCompras(modeloKob1, con);
                                //CALCULO Total Raw Material
                                modeloKob1 = TotalRawValue(modeloKob1, con);
                                //CALCULO Packaging_Materials
                                if (modeloKob1.getCost_Element().contentEquals("50430")) {
                                    modeloKob1 = Packaging_Materials(modeloKob1, con);
                                }

                            }
                        }
                    }

                    double Nuevo_Valor_Orden = 0.0;

                    Nuevo_Valor_Orden = Double.valueOf(modeloKob1.getTotal_Raw_Material())
                            + Double.valueOf(modeloKob1.getManufact_Materials())
                            + Double.valueOf(modeloKob1.getPackaging_Materials())
                            + Double.valueOf(modeloKob1.getConversion_Cost());

                    modeloKob1.setNuevo_Valor_Orden(String.format("%.5f", Nuevo_Valor_Orden).replace(",", "."));

                }
            }
            lstModeloKob1Upd.add(modeloKob1);

        }

//            System.out.println("FINALIZA PROCESO DE LLENADO DE REGISTROS PARA PRODUCCION PISO: " + new Date());
//            System.out.println("INICIA ACTUALIZACION DE REGISTROS DE PRODUCCION PISO: " + new Date());
        Progreso(mes + "-" + ano + " Actulalizando registros con Produccion Piso", "40");
        herramienta.setEventoProcesado(mes + "-" + ano + " Actulalizando registros con Produccion Piso 40%");
        controladorKob1.UpdateList_Carlos(lstModeloKob1Upd, con, "Actulalizando registros con Produccion Piso", mes + "-" + ano, "40", "50");
//            System.out.println("FINALIZA ACTUALIZACION DE REGISTROS DE PRODUCCION PISO: " + new Date());

//            System.out.println("INICIA CONSULTAS SQL : " + new Date());
//            System.out.println(" ---------------- INICIA SUMA DE VALORES SQL : " + new Date());
        //controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where Cost_Element <> '50440' and Cost_Element <> '50400' and IdArchivo is null");
        Progreso(mes + "-" + ano + " Procesando Informe Produccion Piso", "50");
        herramienta.setEventoProcesado(mes + "-" + ano + " Procesando Informe Produccion Piso 50%");
        //controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where IdArchivo is null");

//            System.out.println(" ---------------- FINALIZA SUMA DE VALORES SQL : " + new Date());
        String FechaAño = mes + "-" + ano;
        CallableStatement InformeProduccion = con.prepareCall("{CALL SUMAS_INFORME_PRODUCCION(?)}");
        CallableStatement InformeProduccion_FinMes = con.prepareCall("{CALL SUMAS_INFORME_PRODUCCION_FINMES(?)}");
        InformeProduccion_FinMes.setString(1, FechaAño);

        InformeProduccion.setString(1, FechaAño);
        InformeProduccion.execute();

        Progreso(mes + "-" + ano + " Actuzalizando  Procur_Type", "50");
        herramienta.setEventoProcesado(mes + "-" + ano + " Actuzalizando  Procur_Type 50%");

//            System.out.println(" ---------------- FINALIZA CALCULO EN INFORME DE PRODUCCION PISO : " + new Date());
        String ActualizacionProcur_Type = "update "
                + "kob1 "
                + "set Procur_Type = '' where Procur_Type is null and fecha = '" + mes + "-" + ano + "'";

        controladorMrpdata.Insert(ActualizacionProcur_Type);

//            System.out.println("FINALIZA CONSULTAS SQL : " + new Date());
//            System.out.println("INICIA LLENADO DEL MODELO LISTADO NUMERO 2: " + new Date());
        lstModeloKob1 = null;
        Progreso(mes + "-" + ano + " Llenando Listado Produccion Paso 2", "50");
        herramienta.setEventoProcesado(mes + "-" + ano + " Llenando Listado Produccion Paso 2 50%");
        //lstModeloKob1 = controladorKob1.Select("SELECT * from KOB1 where fecha = '" + mes + "-" + ano + "'", mes + "-" + ano, "50", "60");
        lstModeloKob1 = controladorKob1.Select("SELECT * from kob1 where fecha = '" + mes + "-" + ano + "' and x = 'x'", mes + "-" + ano, "50", "60");

//            System.out.println("FINALIZA LLENADO DEL MODELO LISTADO NUMERO 2: " + new Date());
        lstModeloKob1Upd = null;
        lstModeloKob1Upd = new LinkedList<ModeloKob1>();
//            System.out.println("INICIA RECORRIDO DEL LISTADO NUMERO 2: " + new Date());

        Porcentaje = 60;
        VUeltas = lstModeloKob1.size() / 10;
        sumador = 1;

        Progreso(mes + "-" + ano + " Recorrido de Listado General", "60");
        herramienta.setEventoProcesado(mes + "-" + ano + " Inicia Recorrido de Listado General 60%");
        for (ModeloKob1 modeloKob1 : lstModeloKob1) {

            if (sumador == VUeltas) {
                herramienta.setEventoProcesado(mes + "-" + ano + " Progreso " + Porcentaje + "%");
                System.err.println(mes + "-" + ano + " Progreso " + Porcentaje + "%");
                Porcentaje++;
                sumador = 1;
            }
            sumador++;

            modeloKob1.setProduccion("");
            modeloKob1.setInventario("");
            modeloKob1.setX("");

            if (!modeloKob1.getCost_Element().contentEquals("50440")) {
                if (!modeloKob1.getCost_Element().contentEquals("50400")) {

                    if (!modeloKob1.getCost_Element().contentEquals("80004")) {
                        if (!modeloKob1.getCost_Element().contentEquals("80005")) {
                            if (!modeloKob1.getCost_Element().contentEquals("80015")) {
                                //LLENAMOS COLUMNA Cost Unit Fifo R.Mat y Pack

                                //BUSCO EN INVETNTARIO
                                modeloKob1 = CostoInventario(modeloKob1, con);

                                if (modeloKob1.getProcur_Type().contentEquals("F")) {
                                    modeloKob1 = CostoCompras(modeloKob1, con);
                                    modeloKob1 = TotalRawValue(modeloKob1, con);
                                } else if (modeloKob1.getProcur_Type().contentEquals("E")) {
                                    modeloKob1 = ProduccionPiso(modeloKob1, con);
                                    modeloKob1 = ManufactMaterials(modeloKob1, con);
                                }
                                if (modeloKob1.getCost_Element().contentEquals("50430")) {
                                    modeloKob1 = Packaging_Materials(modeloKob1, con);
                                }
                            }
                        }
                    }
                    double Nuevo_Valor_Orden = 0.0;

                    Nuevo_Valor_Orden = Double.valueOf(modeloKob1.getTotal_Raw_Material())
                            + Double.valueOf(modeloKob1.getManufact_Materials())
                            + Double.valueOf(modeloKob1.getPackaging_Materials())
                            + Double.valueOf(modeloKob1.getConversion_Cost());

                    modeloKob1.setNuevo_Valor_Orden(String.format("%.5f", Nuevo_Valor_Orden).replace(",", "."));

                }
            }

            modeloKob1.setFecha(FechaAño);
            lstModeloKob1Upd.add(modeloKob1);
        }

        //controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where Cost_Element <> '50440' and Cost_Element <> '50400' and IdArchivo is null");
//            System.out.println("FINALIZA RECORRIDO DEL LISTADO NUMERO 2: " + new Date());
//            System.out.println("INICIA ACTUALIZACION DEL LISTADO NUMERO 2: " + new Date());
        Progreso(mes + "-" + ano + " Insertando Listado General de Produccion Paso 2", "70");
        herramienta.setEventoProcesado(mes + "-" + ano + " Insertando Listado General de Produccion Paso 2 70%");
        controladorMrpdata.Insert("delete from kob1 where fecha = '" + mes + "-" + ano + "' and x = 'x'");

        //controladorKob1.UpdateList_Carlos(lstModeloKob1Upd, con, "Inicia Actualizacion de Listado General", "90");
        controladorKob1.InsertList_Masivo(lstModeloKob1Upd, con, "Insertando Listado General de Produccion Paso 2", mes + "-" + ano, "70");

        lstModeloKob1 = null;
        lstModeloKob1Upd = null;
        lstModeloKob1Upd = new LinkedList<ModeloKob1>();

        SqlCon = "SELECT * from kob1 where Produccion = 'No' and Inventario = 'No' and Procur_type = 'E' and fecha = '" + mes + "-" + ano + "'";

        Progreso(mes + "-" + ano + " Buscando Registros con Produccion No piso en Cero", "80");
        herramienta.setEventoProcesado(mes + "-" + ano + " Buscando Registros con Produccion No piso en Cero 80%");
        lstModeloKob1 = controladorKob1.Select(SqlCon, FechaAño, "80", "85");
        for (ModeloKob1 modeloKob1 : lstModeloKob1) {
            modeloKob1.setX("x");
            lstModeloKob1Upd.add(modeloKob1);
        }

        Progreso(mes + "-" + ano + " Actualizando Registros con Produccion No piso en Cero Para ser Excluidos", "85");
        herramienta.setEventoProcesado(mes + "-" + ano + " Actualizando Registros con Produccion No piso en Cero Para ser Excluidos 85%");
        controladorKob1.UpdateList_CampoProd(lstModeloKob1Upd, con, "Actualizacion Listado Para Produccion Piso", FechaAño, "85", "90");

        lstModeloKob1Upd = null;
        lstModeloKob1Upd = new LinkedList<ModeloKob1>();

        Progreso(mes + "-" + ano + " Procesando Informe Produccion Piso y No Piso", "90");
        herramienta.setEventoProcesado(mes + "-" + ano + " Procesando Informe Produccion Piso y No Piso 90%");

        //InformeProduccion.execute();
        InformeProduccion_FinMes.execute();

        lstModeloKob1 = null;
        lstModeloKob1 = controladorKob1.Select("Select * from kob1 where x = 'x' and Procur_type = 'E' and fecha = '" + mes + "-" + ano + "'", FechaAño, "90", "95");
        Porcentaje = 90;
        VUeltas = lstModeloKob1.size() / 5;
        sumador = 1;

        for (ModeloKob1 modeloKob1 : lstModeloKob1) {

            modeloKob1.setProduccion("");
            //modeloKob1.setInventario("");

            if (sumador == VUeltas) {
                herramienta.setEventoProcesado(mes + "-" + ano + " Progreso " + Porcentaje + "%");
                System.err.println(mes + "-" + ano + " Progreso " + Porcentaje + "%");
                Porcentaje++;
                sumador = 1;
            }
            sumador++;

            if (!modeloKob1.getCost_Element().contentEquals("50440")) {
                if (!modeloKob1.getCost_Element().contentEquals("50400")) {

                    if (!modeloKob1.getCost_Element().contentEquals("80004")) {
                        if (!modeloKob1.getCost_Element().contentEquals("80005")) {
                            if (!modeloKob1.getCost_Element().contentEquals("80015")) {

                                modeloKob1 = ProduccionPiso(modeloKob1, con);
                                modeloKob1 = ManufactMaterials(modeloKob1, con);

                            }
                        }
                    }

                    double Nuevo_Valor_Orden = 0.0;

                    Nuevo_Valor_Orden = Double.valueOf(modeloKob1.getTotal_Raw_Material())
                            + Double.valueOf(modeloKob1.getManufact_Materials())
                            + Double.valueOf(modeloKob1.getPackaging_Materials())
                            + Double.valueOf(modeloKob1.getConversion_Cost());

                    modeloKob1.setNuevo_Valor_Orden(String.format("%.5f", Nuevo_Valor_Orden).replace(",", "."));
                }
            }
            modeloKob1.setFecha(FechaAño);
            lstModeloKob1Upd.add(modeloKob1);

            if (modeloKob1.getOrder_().contentEquals("1012978309") && modeloKob1.getMaterial().contentEquals("98133118")) {
                System.out.println("Controlador.ControladorCargaPlanosProduccion.PROCESO_PRODUCCION()");
            }
            if (modeloKob1.getOrder_().contentEquals("1012977900") && modeloKob1.getMaterial().contentEquals("91426638")) {
                System.out.println("Controlador.ControladorCargaPlanosProduccion.PROCESO_PRODUCCION()");
            }
            if (modeloKob1.getOrder_().contentEquals("1012975749") && modeloKob1.getMaterial().contentEquals("90360144")) {
                System.out.println("Controlador.ControladorCargaPlanosProduccion.PROCESO_PRODUCCION()");
            }

        }

        Progreso("Actualizacio registros Produccion Piso", "95");
        herramienta.setEventoProcesado("Actualizacio registros Produccion Piso 95%");
        controladorKob1.UpdateList_Carlos(lstModeloKob1Upd, con, "Actualizacio registros Produccion Piso", FechaAño, "95", "100");

        //InformeProduccion_FinMes.execute();
        lstModeloKob1 = null;
        lstModeloKob1Upd = null;

        Progreso("--", "100");
        herramienta.setEventoProcesado("--");

        return Realizado;

    }

    public String CargarCSV_POVR_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";

        String Sqlborrar = "delete from POVR where fecha = '" + ano + "-" + mes + "'";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE POVR CHARACTER SET LATIN1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Material,Material_Description,Process_Order_Number,Process_Order_Type,Material_Group_Packaging,Profit_Center,UOM,Material_type,Existing_Material_Costing_Lot_Size,MRP_Planned_Quantity,Consumption_Qty_Actual_I_P,Delivered_Qty_Actual_O_P,Yield_Variance_Qty,Yield_Variance_Porcentaje,Actual_Material_Cost,Labor_Cost_Actual,Machine_Cost_Actual,Overhead_Cost_Actual,Total_Cost_Actual_Output,Physical_Inventory_Yield_Loss_cost_Actu,Standard_Price,Calculated_Production_Variance,Conversion_Cost_Per_Unit,Display_Currency,Batch_Number,Product_Hierarchy,Actual_Start_Date,Actual_Finish_Date)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        //ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
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

        ANO = mes + "-" + ano;

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

    private static List<String> res = new ArrayList<>();

    public static String[] delEmpties(String[] arr) {

        res.clear();
        int len = arr.length;

        for (int i = 0; i < len; i++) {

            if (!arr[i].equals("")) {
                res.add(arr[i]);
            }
        }

        return res.toArray(new String[0]);
    }

    /**
     * Filters the array of strings and delete repeat elements
     *
     * @param arr The arr
     * @return The array
     */
    public static String[] filterRepeated(String[] arr) {

        int len = arr.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < (len - 1); j++) {

                if (i != j) {
                    if (arr[i].equals(arr[j])) {
                        arr[j] = ""; //replace value by empty string
                    }
                }
            }
        }

        return delEmpties(arr);
    }

    public ModeloKob1 LlenarMrpdata_Original(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        if (!modeloKob1.getMaterial().contentEquals("")) {
            //LLENAMOS CULUMNA Link ( Plant & Material)
            modeloKob1.setLink_Plant_Material(modeloKob1.getPlant() + modeloKob1.getMaterial());
            //LLENAMOS COLUMNA Link ( Material & orden)
            modeloKob1.setLink_Material_orden(modeloKob1.getMaterial() + modeloKob1.getOrder_());
            LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = controladorMb51_Consumos.SelectSQL("SELECT * FROM mb51_Consumos where Material = '" + modeloKob1.getMaterial() + "' and Order_ = '" + modeloKob1.getOrder_() + "'", con);
            String VariosBatch = "";

            if (LstModeloMb51_Consumos.size() > 0) {
                if (LstModeloMb51_Consumos.size() > 1) {
                    //System.out.println("Controlador.ControladorCargaPlanosProduccion.LlenarMrpdata_Original()");
                }

                for (ModeloMb51_Consumos modeloMb51_Consumos : LstModeloMb51_Consumos) {
                    //LLENAMOS COLUMNA Batch consumo
                    if (modeloMb51_Consumos.getBatch() != null) {
                        if (VariosBatch.contentEquals("")) {
                            VariosBatch = modeloMb51_Consumos.getBatch();
                        } else {
                            VariosBatch = VariosBatch + "," + modeloMb51_Consumos.getBatch();
                        }

                        //modeloKob1.setBatch_consumo(modeloMb51_Consumos.getBatch());
                    }
                }

                String[] Bbatch = filterRepeated(VariosBatch.split(","));

                int len = Bbatch.length;

                VariosBatch = "";

                for (int i = 0; i < len; i++) {
                    if (VariosBatch.contentEquals("")) {
                        VariosBatch = Bbatch[i];
                    } else {
                        VariosBatch = VariosBatch + "," + Bbatch[i];
                    }
                }

                modeloKob1.setBatch_consumo(VariosBatch);

            }
            if (modeloKob1.getBatch_consumo() == null) {
                //LLENAMOS COLUMNA Batch consumo
                modeloKob1.setBatch_consumo("");
            }
            //LLENAMOS COLUMNA Link (Material & Batch)
            modeloKob1.setLink_Material_Batch(modeloKob1.getMaterial() + ";" + modeloKob1.getBatch_consumo());

            ModeloMrpData modeloMrpData = controladorMrpdata.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloKob1.getMaterial() + "' and Plant = '" + modeloKob1.getPlant() + "'", con);

            //LLENAMOS COLUMNA Material Type Components
            modeloKob1.setMaterial_Type_Components(modeloMrpData.getMaterial_Type());

            if (!modeloKob1.getCost_Element().contentEquals("50440")) {
                //LLENAMOS COLUMNA Procur Type
                modeloKob1.setProcur_Type(modeloMrpData.getProcurement_type());
            }

            Double Value_TranCurr = Double.valueOf(modeloKob1.getValue_TranCurr());
            Double Total_Quantity = Double.valueOf(modeloKob1.getTotal_Quantity());
            Double Cost_Unit_Estandar = 0.0;
            if (Total_Quantity != 0.0) {
                Cost_Unit_Estandar = Value_TranCurr / Total_Quantity;
            }
            //LLENAMOS COLUMNA Cost Unit Estándar
            modeloKob1.setCost_Unit_Estandar(String.format("%.5f", Cost_Unit_Estandar).replace(",", "."));

        }
        //LLENAMOS COLUMNA Level 1 es = Columna C
        modeloKob1.setLevel_1(modeloKob1.getOrder_());
        LinkedList<ModeloPovr> LstModeloPovr = controladorPovr.SelectSQL("SELECT * FROM povr where Process_Order_Number = '" + modeloKob1.getOrder_() + "'", con);
        for (ModeloPovr modeloPovr : LstModeloPovr) {
            //LLENAMOS COLUMNA Finish Good sku
            modeloKob1.setFinish_Good_sku(modeloPovr.getMaterial());

            //LLENAMOS COLUMNA Batch Finish goods
            modeloKob1.setBatch_Finish_goods(modeloPovr.getBatch_Number());

            //LLENAMOS COLUMNA Link (Terminado & Batch)
            modeloKob1.setLink_Terminado_Batch(modeloPovr.getMaterial() + modeloPovr.getBatch_Number());

            //LLENAMOS COLUMNA Cantidad Terminada
            modeloKob1.setCantidad_Terminada(modeloPovr.getDelivered_Qty_Actual_O_P());
        }

        //LLENAMOS COLUMNA Cost Unit Fifo old
        //modeloKob1 = CostoInventario(modeloKob1, con);
        return modeloKob1;
    }

    public ModeloKob1 CostoInventario(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        String[] Batch = modeloKob1.getLink_Material_Batch().split(";");
        String Material = Batch[0];
        String[] Batchs = {""};
        if (Batch.length > 1) {
            Batchs = Batch[1].split(",");
        }

        for (int i = 0; i < Batchs.length; i++) {

            //String Sql = "SELECT * FROM inventarioinicial where Link_Material_Batch = '" + modeloKob1.getLink_Material_Batch() + "'";
            String Sql = "SELECT * FROM inventarioinicial where Link_Material_Batch = '" + Material + Batchs[i] + "'";

            LinkedList<ModeloInventarioInicial> LstModeloInventario = controladorInventarioInicial.SelectListSql(Sql, con);
            if (LstModeloInventario.size() > 0) {
                for (ModeloInventarioInicial modeloInventario : LstModeloInventario) {
                    modeloKob1.setCost_Unit_Fifo_Old(modeloInventario.getFIFO_Cost_UNIT());
                }
                modeloKob1.setInventario("Si");
                break;
            } else {
                modeloKob1.setCost_Unit_Fifo_Old("0.0");
                modeloKob1.setInventario("No");
            }
        }
        return modeloKob1;
    }

    public ModeloKob1 CostoCompras(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        String[] Batch = modeloKob1.getLink_Material_Batch().split(";");
        String Material = Batch[0];
        String[] Batchs = {""};
        if (Batch.length > 1) {
            Batchs = Batch[1].split(",");
        }

        for (int i = 0; i < Batchs.length; i++) {

            //String Sql = "SELECT * FROM mb51 where link1_Material_Batch = '" + modeloKob1.getLink_Material_Batch() + "' AND fecha = '" + MesKob1 + "' ORDER BY Month";
            String Sql = "SELECT * FROM mb51 where link1_Material_Batch = '" + Material + Batchs[i] + "' AND fecha = '" + MesKob1 + "' ORDER BY Month";

            LinkedList<ModeloMb51> LstModeloMb51 = controladorMb51.SelectSql(Sql, con);

            Double Cost_Unit_Fifo_R_Mat_Pack = 0.0;
            Double Quantity = 0.0;
            if (LstModeloMb51.size() > 0) {
                for (ModeloMb51 modeloMb51 : LstModeloMb51) {
                    //if (Integer.valueOf(modeloMb51.getMonth()) == Integer.valueOf(modeloKob1.getPeriod())) {
                    //    if (Double.valueOf(modeloMb51.getQuantity()) > -1) {

                    if (modeloMb51.getQuantity() != null) {
                        if (modeloMb51.getQuantity() != "") {
                            Quantity = Quantity + Double.parseDouble(modeloMb51.getQuantity());
                        }
                    }

                    if (modeloMb51.getCompra_valorada_a_Unit_FIFO() != null) {
                        if (modeloMb51.getCompra_valorada_a_Unit_FIFO() != "" || !modeloMb51.getCompra_valorada_a_Unit_FIFO().contains("NaN")) {
                            Cost_Unit_Fifo_R_Mat_Pack = Cost_Unit_Fifo_R_Mat_Pack + Double.parseDouble(modeloMb51.getCompra_valorada_a_Unit_FIFO());
                        }
                    }

                }

                Cost_Unit_Fifo_R_Mat_Pack = Cost_Unit_Fifo_R_Mat_Pack / Quantity;
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack(String.format("%.5f", Cost_Unit_Fifo_R_Mat_Pack).replace(",", "."));
                break;
            } else {
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
            }
        }
        return modeloKob1;
    }

    public ModeloKob1 ProduccionPiso(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        String[] Batch = modeloKob1.getLink_Material_Batch().split(";");
        String Material = Batch[0];
        String[] Batchs = {""};
        if (Batch.length > 1) {
            Batchs = Batch[1].split(",");
        }

        for (int i = 0; i < Batchs.length; i++) {

            //String Sql = "SELECT * FROM informe_produccion where Link_Terminado_Batch = '" + modeloKob1.getLink_Material_Batch() + "'";
            String Sql = "SELECT * FROM informe_produccion where Link_Terminado_Batch = '" + Material + Batchs[i] + "'";
            LinkedList<ModeloInforme_Produccion> LstModeloInforme_Produccion = controladorInformeProduccion.SelectSql(Sql, con);

            if (LstModeloInforme_Produccion.size() > 0) {
                for (ModeloInforme_Produccion modeloInforme_Produccion : LstModeloInforme_Produccion) {
                    Double Cantidad = 0.0;
                    Double Total_Raw_Material = 0.0;
                    Double Manufact = 0.0;
                    Double Packaging = 0.0;
                    Double Conversion = 0.0;
                    Double Nuevo_Valor_Orden = 0.0;
                    Double Costo_unitario_FIFO = 0.0;

                    if (modeloInforme_Produccion.getCantidad() != null) {
                        Cantidad = Double.valueOf(modeloInforme_Produccion.getCantidad());
                    }

                    if (modeloInforme_Produccion.getTotal_Raw_Material() != null) {
                        Total_Raw_Material = Double.valueOf(modeloInforme_Produccion.getTotal_Raw_Material());
                    }
                    if (modeloInforme_Produccion.getManufact() != null) {
                        Manufact = Double.valueOf(modeloInforme_Produccion.getManufact());
                    }
                    if (modeloInforme_Produccion.getPackaging() != null) {
                        Packaging = Double.valueOf(modeloInforme_Produccion.getPackaging());
                    }
                    if (modeloInforme_Produccion.getConversion() != null) {
                        Conversion = Double.valueOf(modeloInforme_Produccion.getConversion());
                    }

                    Nuevo_Valor_Orden = Total_Raw_Material + Manufact + Packaging + Conversion;
                    if (Cantidad != 0.0) {
                        Costo_unitario_FIFO = Nuevo_Valor_Orden / Cantidad;
                    }

                    if (modeloKob1.getLink_Material_Batch().contentEquals("903602483YUMOC0427")) {
                        System.out.println((String.format("%.5f", Costo_unitario_FIFO)));
                        System.out.println((String.format("%.5f", Costo_unitario_FIFO).replace(",", ".")));
                    }
                    modeloKob1.setCost_Unit_Fifo_R_Mat_Pack(String.format("%.5f", Costo_unitario_FIFO).replace(",", "."));

                    modeloKob1.setProduccion("Si");
                    break;
                }
            } else {
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
                modeloKob1.setProduccion("No");
            }
        }
        return modeloKob1;
    }

    public ModeloKob1 TotalRawValue(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        Double Total_Quantity = Double.valueOf(modeloKob1.getTotal_Quantity());
        Double Total_Raw_Material = 0.0;

        if (modeloKob1.getLink_Material_Batch() != null) {
            if (modeloKob1.getLink_Material_Batch().contentEquals("35001488")) {
                //   System.out.println("Controlador.ControladorCargaPlanosProduccion.CostoCompras()");
            }
        }
        if (modeloKob1.getCost_Unit_Fifo_Old() == null) {
            modeloKob1.setCost_Unit_Fifo_Old("0.0");
        }

        if (modeloKob1.getCost_Unit_Fifo_R_Mat_Pack() == null || modeloKob1.getCost_Unit_Fifo_R_Mat_Pack().contentEquals("")) {
            modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
        }

        if (modeloKob1.getCost_Unit_Fifo_Old().contentEquals("0.0")) {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack());
            Total_Raw_Material = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        } else {
            Double Cost_Unit_Fifo_Old = Double.valueOf(modeloKob1.getCost_Unit_Fifo_Old());
            Total_Raw_Material = Cost_Unit_Fifo_Old * Total_Quantity;
        }

        if (modeloKob1.getCost_Element().contentEquals("50430")) {
            if (modeloKob1.getCost_Unit_Fifo_R_Mat_Pack() != "" || !modeloKob1.getCost_Unit_Fifo_R_Mat_Pack().contains("NaN")) {
                if (Double.parseDouble(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack()) != 0) {
                    Total_Raw_Material = 0.0;
                }
            }
        }
        modeloKob1.setTotal_Raw_Material(String.format("%.5f", Total_Raw_Material).replace(",", "."));
        return modeloKob1;
    }

    public ModeloKob1 ManufactMaterials(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        Double Total_Quantity = Double.valueOf(modeloKob1.getTotal_Quantity());
        Double Manufact_Materials = 0.0;

        if (modeloKob1.getCost_Unit_Fifo_Old() == null) {
            modeloKob1.setCost_Unit_Fifo_Old("0.0");
        }

        if (modeloKob1.getCost_Unit_Fifo_Old().contentEquals("0.0")) {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack());
            Manufact_Materials = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        } else {
            Double Cost_Unit_Fifo_Old = Double.valueOf(modeloKob1.getCost_Unit_Fifo_Old());
            Manufact_Materials = Cost_Unit_Fifo_Old * Total_Quantity;
        }

        modeloKob1.setManufact_Materials(String.format("%.5f", Manufact_Materials).replace(",", "."));
        return modeloKob1;
    }

    public ModeloKob1 Packaging_Materials(ModeloKob1 modeloKob1, Connection con) throws SQLException {
        Double Total_Quantity = Double.valueOf(modeloKob1.getTotal_Quantity());
        Double Packaging_Materials = 0.0;

        if (modeloKob1.getCost_Unit_Fifo_Old() == null) {
            modeloKob1.setCost_Unit_Fifo_Old("0.0");
        }

        if (modeloKob1.getCost_Unit_Fifo_Old().contentEquals("") || modeloKob1.getCost_Unit_Fifo_Old().contentEquals("0.0")) {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack());
            Packaging_Materials = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        } else {
            Double Cost_Unit_Fifo_Old = Double.valueOf(modeloKob1.getCost_Unit_Fifo_Old());
            Packaging_Materials = Cost_Unit_Fifo_Old * Total_Quantity;
        }
        modeloKob1.setPackaging_Materials(String.format("%.5f", Packaging_Materials).replace(",", "."));
        return modeloKob1;
    }

    //--------------------------------------------------------------------------------------
    public LinkedList<ModeloKob1> Select(String Sql, Connection con, String Fecha) {
        LinkedList<ModeloKob1> lstModeloKob1 = new LinkedList<ModeloKob1>();

        int cantFilas = 0;
        PreparedStatement SQL1;
        try {
            SQL1 = con.prepareStatement("Select Count(id) as Cuenta from kob1_planos where fecha = '" + Fecha + "'");
            ResultSet res1 = SQL1.executeQuery();
            while (res1.next()) {
                cantFilas = Integer.parseInt(res1.getString("Cuenta"));
            }
            res1.close();
            SQL1.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();

            int Porcentaje = 1;
            int VUeltas = cantFilas / 10;
            int sumador = 1;

            while (res.next()) {

                if (sumador == VUeltas) {

                    Runtime garbage = Runtime.getRuntime();
                    garbage.gc();

                    herramienta.setEventoProcesado(Fecha + " Progreso " + Porcentaje + "%");
                    System.err.println(Fecha + " Progreso " + Porcentaje + "%");
                    Porcentaje++;
                    sumador = 1;
                }
                sumador++;

                ModeloKob1 modeloKob1 = new ModeloKob1();
                modeloKob1.setId(res.getInt("Id"));
                modeloKob1.setFunctional_Area(res.getString("Functional_Area"));
                modeloKob1.setCompany_Code(res.getString("Company_Code"));
                modeloKob1.setOrder_(res.getString("Order_"));
                modeloKob1.setCO_object_name(res.getString("CO_object_name"));
                modeloKob1.setCost_Element(res.getString("Cost_Element"));
                modeloKob1.setCost_element_name(res.getString("Cost_element_name"));
                modeloKob1.setMaterial(res.getString("Material"));
                modeloKob1.setMaterial_Description(res.getString("Material_Description"));
                modeloKob1.setPlant(res.getString("Plant"));
                modeloKob1.setPeriod(res.getString("Period"));
                modeloKob1.setFiscal_Year(res.getString("Fiscal_Year"));
                modeloKob1.setDr_Cr_indicator(res.getString("Dr_Cr_indicator"));
                modeloKob1.setTotal_Quantity(res.getString("Total_Quantity"));
                modeloKob1.setUnit_of_Measure(res.getString("Unit_of_Measure"));
                modeloKob1.setValue_TranCurr(res.getString("Value_TranCurr"));
                modeloKob1.setTransaction_Currency(res.getString("Transaction_Currency"));
                modeloKob1.setValue_in_Obj_Crcy(res.getString("Value_in_Obj_Crcy"));
                modeloKob1.setObject_Currency(res.getString("Object_Currency"));
                modeloKob1.setDocument_Number(res.getString("Document_Number"));
                modeloKob1.setLink_Plant_Material(res.getString("Link_Plant_Material"));
                modeloKob1.setLink_Material_orden(res.getString("Link_Material_orden"));
                modeloKob1.setBatch_consumo(res.getString("Batch_consumo"));
                modeloKob1.setLink_Material_Batch(res.getString("Link_Material_Batch"));
                modeloKob1.setMaterial_Type_Components(res.getString("Material_Type_Components"));
                modeloKob1.setProcur_Type(res.getString("Procur_Type"));
                modeloKob1.setLevel_1(res.getString("Level_1"));
                modeloKob1.setFinish_Good_sku(res.getString("Finish_Good_sku"));
                modeloKob1.setMat_Type_Unfinish_Goods(res.getString("Mat_Type_Unfinish_Goods"));
                modeloKob1.setBatch_Finish_goods(res.getString("Batch_Finish_goods"));
                modeloKob1.setLink_Terminado_Batch(res.getString("Link_Terminado_Batch"));
                modeloKob1.setCost_Unit_Estandar(res.getString("Cost_Unit_Estandar"));
                modeloKob1.setCantidad_Terminada(res.getString("Cantidad_Terminada"));
                modeloKob1.setCost_Unit_Fifo_Old(res.getString("Cost_Unit_Fifo_Old"));
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack(res.getString("Cost_Unit_Fifo_R_Mat_Pack"));
                modeloKob1.setX(res.getString("x"));
                modeloKob1.setTotal_Raw_Material(res.getString("Total_Raw_Material"));
                modeloKob1.setManufact_Materials(res.getString("Manufact_Materials"));
                modeloKob1.setPackaging_Materials(res.getString("Packaging_Materials"));
                modeloKob1.setConversion_Cost(res.getString("Conversion_Cost"));
                modeloKob1.setNuevo_Valor_Orden(res.getString("Nuevo_Valor_Orden"));
                modeloKob1.setMonth(res.getString("Month"));

                if (modeloKob1.getTotal_Raw_Material() == null) {
                    modeloKob1.setTotal_Raw_Material("0.0");
                }
                if (modeloKob1.getManufact_Materials() == null) {
                    modeloKob1.setManufact_Materials("0.0");
                }
                if (modeloKob1.getPackaging_Materials() == null) {
                    modeloKob1.setPackaging_Materials("0.0");
                }
                if (modeloKob1.getConversion_Cost() == null) {
                    modeloKob1.setConversion_Cost("0.0");
                }

                if (modeloKob1.getCost_Element().contentEquals("80004")) {
                    double Conversion_Cost = Double.valueOf(modeloKob1.getValue_TranCurr()) * CONVERSION_LABOR;
                    modeloKob1.setConversion_Cost(String.format("%.5f", Conversion_Cost).replace(",", "."));
                }
                if (modeloKob1.getCost_Element().contentEquals("80005")) {
                    double Conversion_Cost = Double.valueOf(modeloKob1.getValue_TranCurr()) * CONVERSION_MACHINE;
                    modeloKob1.setConversion_Cost(String.format("%.5f", Conversion_Cost).replace(",", "."));
                }
                if (modeloKob1.getCost_Element().contentEquals("80015")) {
                    double Conversion_Cost = Double.valueOf(modeloKob1.getValue_TranCurr()) * CONVERSION_OVHDS;
                    modeloKob1.setConversion_Cost(String.format("%.5f", Conversion_Cost).replace(",", "."));
                }

                modeloKob1 = LlenarMrpdata_Original(modeloKob1, con);
                //UpdateModeloKob1(modeloKob1, con);

                modeloKob1.setFecha(Fecha);
                if (modeloKob1.getBatch_consumo() != null) {
                    if (modeloKob1.getBatch_consumo().length() > 250) {
                        System.out.println("Varios Batch encontrados para Material " + modeloKob1.getMaterial() + " y order " + modeloKob1.getOrder_() + " ---- " + modeloKob1.getBatch_consumo());
                    }
                }

                lstModeloKob1.add(modeloKob1);

                modeloKob1 = null;
            }
            res.close();
            SQL.close();
            //con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        return lstModeloKob1;
    }

    public boolean UpdateModeloKob1(ModeloKob1 modeloKob1, Connection con) throws SQLException {
        boolean resul = false;
        PreparedStatement SQL = null;
        try {
            SQL = con.prepareStatement("UPDATE KOB1 SET "
                    + "Functional_Area = ?, "
                    + "Company_Code = ?, "
                    + "Order_ = ?, "
                    + "CO_object_name = ?, "
                    + "Cost_Element = ?, "
                    + "Cost_element_name = ?, "
                    + "Material = ?, "
                    + "Material_Description = ?, "
                    + "Plant = ?, "
                    + "Period = ?, "
                    + "Fiscal_Year = ?, "
                    + "Dr_Cr_indicator = ?, "
                    + "Total_Quantity = ?, "
                    + "Unit_of_Measure = ?, "
                    + "Value_TranCurr = ?, "
                    + "Transaction_Currency = ?, "
                    + "Value_in_Obj_Crcy = ?, "
                    + "Object_Currency = ?, "
                    + "Document_Number = ?, "
                    + "Link_Plant_Material = ?, "
                    + "Link_Material_orden = ?, "
                    + "Batch_consumo = ?, "
                    + "Link_Material_Batch = ?, "
                    + "Material_Type_Components = ?, "
                    + "Procur_Type = ?, "
                    + "Level_1 = ?, "
                    + "Finish_Good_sku = ?, "
                    + "Mat_Type_Unfinish_Goods = ?, "
                    + "Batch_Finish_goods = ?, "
                    + "Link_Terminado_Batch = ?, "
                    + "Cost_Unit_Estandar = ?, "
                    + "Cantidad_Terminada = ?, "
                    + "Cost_Unit_Fifo_Old = ?, "
                    + "Cost_Unit_Fifo_R_Mat_Pack = ?, "
                    + "x = ?, "
                    + "Total_Raw_Material = ?, "
                    + "Manufact_Materials = ?, "
                    + "Packaging_Materials = ?, "
                    + "Conversion_Cost = ?, "
                    + "Nuevo_Valor_Orden = ?, "
                    + "Month = ? "
                    + " WHERE Id = ? ");

            SQL.setString(1, modeloKob1.getFunctional_Area());
            SQL.setString(2, modeloKob1.getCompany_Code());
            SQL.setString(3, modeloKob1.getOrder_());
            SQL.setString(4, modeloKob1.getCO_object_name());
            SQL.setString(5, modeloKob1.getCost_Element());
            SQL.setString(6, modeloKob1.getCost_element_name());
            SQL.setString(7, modeloKob1.getMaterial());
            SQL.setString(8, modeloKob1.getMaterial_Description());
            SQL.setString(9, modeloKob1.getPlant());
            SQL.setString(10, modeloKob1.getPeriod());
            SQL.setString(11, modeloKob1.getFiscal_Year());
            SQL.setString(12, modeloKob1.getDr_Cr_indicator());
            SQL.setString(13, modeloKob1.getTotal_Quantity());
            SQL.setString(14, modeloKob1.getUnit_of_Measure());
            SQL.setString(15, modeloKob1.getValue_TranCurr());
            SQL.setString(16, modeloKob1.getTransaction_Currency());
            SQL.setString(17, modeloKob1.getValue_in_Obj_Crcy());
            SQL.setString(18, modeloKob1.getObject_Currency());
            SQL.setString(19, modeloKob1.getDocument_Number());
            SQL.setString(20, modeloKob1.getLink_Plant_Material());
            SQL.setString(21, modeloKob1.getLink_Material_orden());
            SQL.setString(22, modeloKob1.getBatch_consumo());
            SQL.setString(23, modeloKob1.getLink_Material_Batch());
            SQL.setString(24, modeloKob1.getMaterial_Type_Components());
            SQL.setString(25, modeloKob1.getProcur_Type());
            SQL.setString(26, modeloKob1.getLevel_1());
            SQL.setString(27, modeloKob1.getFinish_Good_sku());
            SQL.setString(28, modeloKob1.getMat_Type_Unfinish_Goods());
            SQL.setString(29, modeloKob1.getBatch_Finish_goods());
            SQL.setString(30, modeloKob1.getLink_Terminado_Batch());
            SQL.setString(31, modeloKob1.getCost_Unit_Estandar());
            SQL.setString(32, modeloKob1.getCantidad_Terminada());
            SQL.setString(33, modeloKob1.getCost_Unit_Fifo_Old());
            SQL.setString(34, modeloKob1.getCost_Unit_Fifo_R_Mat_Pack());
            SQL.setString(35, modeloKob1.getX());
            SQL.setString(36, modeloKob1.getTotal_Raw_Material());
            SQL.setString(37, modeloKob1.getManufact_Materials());
            SQL.setString(38, modeloKob1.getPackaging_Materials());
            SQL.setString(39, modeloKob1.getConversion_Cost());
            SQL.setString(40, modeloKob1.getNuevo_Valor_Orden());
            SQL.setString(41, modeloKob1.getMonth());
            SQL.setInt(42, modeloKob1.getId());
            if (SQL.executeUpdate() > 0) {
                resul = true;
            }
            SQL.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Update " + e);
            SQL.close();
        }
        return resul;
    }

    public void mes(HttpServletRequest request) {

        String mes = request.getParameter("Mes");
        String ano = request.getParameter("Ano");

        ANO = mes + "-" + ano;

        switch (mes) {
            case "Enero":
                MES = "01";
                break;
            case "Febrero":
                MES = "02";
                break;
            case "Marzo":
                MES = "03";
                break;
            case "Abril":
                MES = "04";
                break;
            case "Mayo":
                MES = "05";
                break;
            case "Junio":
                MES = "06";
                break;
            case "Julio":
                MES = "07";
                break;
            case "Agosto":
                MES = "08";
                break;
            case "Septiembre":
                MES = "09";
                break;
            case "Octubre":
                MES = "10";
                break;
            case "Noviembre":
                MES = "11";
                break;
            case "Diciembre":
                MES = "12";
                break;

        }

    }

    public void Progreso(String Proceso, String Porcentaje) {
        System.err.println("Porcentaje " + Porcentaje + "% Proceso: " + Proceso);

    }

    public ModeloEstadoPlanos Archivo(HttpServletRequest request) {
        Herramienta herramientas = new Herramienta();
        ControladorFechas controladorFechas = new ControladorFechas();
        ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
        modeloEstadoPlanos.setNombrePlano("KOB1");
        modeloEstadoPlanos.setFechaCarga(herramientas.sDate());
        modeloEstadoPlanos.setEstado("Activo");
        modeloEstadoPlanos.setIdFechas(controladorFechas.getIdFecha(request));
        ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos();
        controladorEstadoPlanos.Insert(modeloEstadoPlanos);
        //Selecionamos el ultimo Id del la tabal estado plano
        modeloEstadoPlanos.setId(controladorEstadoPlanos.getIdEstadoPlanos());

        return modeloEstadoPlanos;
    }

    public String Recorrido(String Ruta, HttpServletRequest request) throws IOException, SQLException {

        String Realizado = "false";
        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();

        String mes = request.getParameter("Mes");
        String ano = request.getParameter("Ano");

        ANO = mes + "-" + ano;
        MESActual = request.getParameter("Mes");

        int vueltas = 0;

        switch (mes) {
            case "Enero":
                vueltas = 1;
                break;
            case "Febrero":
                vueltas = 2;
                break;
            case "Marzo":
                vueltas = 3;
                break;
            case "Abril":
                vueltas = 4;
                break;
            case "Mayo":
                vueltas = 5;
                break;
            case "Junio":
                vueltas = 6;
                break;
            case "Julio":
                vueltas = 7;
                break;
            case "Agosto":
                vueltas = 8;
                break;
            case "Septiembre":
                vueltas = 9;
                break;
            case "Octubre":
                vueltas = 10;
                break;
            case "Noviembre":
                vueltas = 11;
                break;
            case "Diciembre":
                vueltas = 12;
                break;

        }

        Date PruebaInicioFecha = new Date();
        Date PruebaFinFecha = new Date();
        //Date Res = PruebaInicioFecha.getTime() - PruebaFinFecha.ge;

        Progreso("Fecha Hora Inicio Proceso Completo: " + new Date(), "0");
        herramienta.setEventoProcesado("Fecha Hora Inicio Proceso Completo:" + new Date());
        for (int x = 1; x <= vueltas; x++) {
            Progreso("Fecha Hora Inicio Mes: " + mes + " - " + new Date(), "0");
            herramienta.setEventoProcesado("Fecha Hora Inicio Proceso Completo:" + new Date());
            Realizado = PROCESO_PRODUCCION(con, DevolverMes(x), ano);
            Progreso("Fecha Hora Fin Mes : " + mes + " - " + new Date(), "0");
            herramienta.setEventoProcesado("Fecha Hora Fin Proceso Completo: " + new Date());
        }
        Progreso("Fecha Hora Fin Proceso Completo: " + new Date(), "0");
        herramienta.setEventoProcesado("Fecha Hora Fin Proceso Completo: " + new Date());
        herramienta.setEventoProcesado("--");
        con.close();

        return Realizado;

    }

    public String DevolverMes(int mes) {
        String Mes = "";

        switch (mes) {
            case 1:
                Mes = "Enero";
                break;
            case 2:
                Mes = "Febrero";
                break;
            case 3:
                Mes = "Marzo";
                break;
            case 4:
                Mes = "Abril";
                break;
            case 5:
                Mes = "Mayo";
                break;
            case 6:
                Mes = "Junio";
                break;
            case 7:
                Mes = "Julio";
                break;
            case 8:
                Mes = "Agosto";
                break;
            case 9:
                Mes = "Septiembre";
                break;
            case 10:
                Mes = "Octubre";
                break;
            case 11:
                Mes = "Noviembre";
                break;
            case 12:
                Mes = "Diciembre";
                break;

        }

        return Mes;
    }

    public String validarMesMb51(String Ano) {
        String Mes = "";

        String Enero = "";
        String Febrero = "";
        String Marzo = "";
        String Abril = "";
        String Mayo = "";
        String Junio = "";
        String Julio = "";
        String Agosto = "";
        String Septiembre = "";
        String Octubre = "";
        String Noviembre = "";
        String Diciembre = "";

        ConexionBDMySql conexion = new ConexionBDMySql();
        Connection con;
        con = conexion.abrirConexion();
        PreparedStatement SQL;
        LinkedList<Object> lista = new LinkedList<Object>();

        try {

            SQL = con.prepareStatement("select fecha from mb51 where fecha like '%" + Ano + "' group by fecha");
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
                lista.add(res.getString("fecha"));
            }
            res.close();
            SQL.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL Select " + e);
        }

        for (Object Mess : lista) {
            if (Mess.toString().contains("Enero")) {
                Enero = "Enero-" + Ano;
            }
            if (Mess.toString().contains("Febrero")) {
                Febrero = "Febrero-" + Ano;
            }
            if (Mess.toString().contains("Marzo")) {
                Marzo = "Marzo-" + Ano;
            }
            if (Mess.toString().contains("Abril")) {
                Abril = "Abril-" + Ano;
            }
            if (Mess.toString().contains("Mayo")) {
                Mayo = "Mayo-" + Ano;
            }
            if (Mess.toString().contains("Junio")) {
                Junio = "Junio-" + Ano;
            }
            if (Mess.toString().contains("Julio")) {
                Julio = "Julio-" + Ano;
            }
            if (Mess.toString().contains("Agosto")) {
                Agosto = "Agosto-" + Ano;
            }
            if (Mess.toString().contains("Septiembre")) {
                Septiembre = "Septiembre-" + Ano;
            }
            if (Mess.toString().contains("Octubre")) {
                Octubre = "Octubre-" + Ano;
            }
            if (Mess.toString().contains("Noviembre")) {
                Noviembre = "Noviembre-" + Ano;
            }
            if (Mess.toString().contains("Diciembre")) {
                Diciembre = "Diciembre-" + Ano;
            }
        }

        if (Diciembre.contentEquals("")) {
            if (Noviembre.contentEquals("")) {
                if (Octubre.contentEquals("")) {
                    if (Septiembre.contentEquals("")) {
                        if (Agosto.contentEquals("")) {
                            if (Julio.contentEquals("")) {
                                if (Junio.contentEquals("")) {
                                    if (Mayo.contentEquals("")) {
                                        if (Abril.contentEquals("")) {
                                            if (Marzo.contentEquals("")) {
                                                if (Febrero.contentEquals("")) {
                                                    if (Enero.contentEquals("")) {
                                                    } else {
                                                        return Enero;
                                                    }
                                                } else {
                                                    return Febrero;
                                                }
                                            } else {
                                                return Marzo;
                                            }
                                        } else {
                                            return Abril;
                                        }
                                    } else {
                                        return Mayo;
                                    }
                                } else {
                                    return Junio;
                                }
                            } else {
                                return Julio;
                            }
                        } else {
                            return Agosto;
                        }
                    } else {
                        return Septiembre;
                    }
                } else {
                    return Octubre;
                }
            } else {
                return Noviembre;
            }
        } else {
            return Diciembre;
        }
        return Mes;
    }

}
