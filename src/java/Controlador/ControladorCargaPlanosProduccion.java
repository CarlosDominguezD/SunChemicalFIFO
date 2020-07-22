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
import Modelos.ModeloEstadoPlanos;
import Modelos.ModeloInforme_Produccion;
import Modelos.ModeloInventario;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
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
    

    String MES = "";
    String ANO = "";

    public String procesarCarga(LinkedList<ModeloArchivos> listModeloArchivoses, HttpServletRequest request, HttpServletResponse response) {
        String resultado = "false";
        for (ModeloArchivos listModeloArchivose : listModeloArchivoses) {
            try {
                String RutaDispo = listModeloArchivose.getRuta();
                String nombre = listModeloArchivose.getNombre();
                StringTokenizer st = new StringTokenizer(nombre, ".");
                nombre = st.nextToken();

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

                    case "MB51_CONSUMOS":
                        resultado = CargarCSV_MB51_CONSUMOS_INFILE(RutaDispo, request);
                        break;
                    case "POVR":
                        resultado = CargarCSV_POVR_INFILE(RutaDispo, request);
                        break;
                    case "KOB1":
                        resultado = CargarCSV_KOB1_INFILE(RutaDispo, request);
                        if ("true".equals(resultado)) {
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
                            // Update Mb51
                            Progreso("Actualizando Archivo Cargado", "95");
                            controladorEstadoPlanos.UpdateMB51(modeloEstadoPlanos, "KOB1");
                            Progreso("Procesamiento de Archivo de Produccin Finalizado", "100");
                        }
                        break;
                }
            } catch (IOException | SQLException ex) {
                System.out.println("Error en la carga del plano " + "  " + ex);
            }

        }

        return resultado;

    }

    public String Upload(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String resultado = "false";
        String formato = "";

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
                case "MB51_CONSUMOS":
                    resultado = CargarCSV_MB51_CONSUMOS_INFILE(RutaDispo, request);
                    break;
                case "POVR":
                    resultado = CargarCSV_POVR_INFILE(RutaDispo, request);
                    break;
                case "KOB1":
                    resultado = CargarCSV_KOB1_INFILE(RutaDispo, request);
                    if ("true".equals(resultado)) {
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
                        // Update Mb51
                        Progreso("Actualizando Archivo Cargado", "95");
                        controladorEstadoPlanos.UpdateMB51(modeloEstadoPlanos, "KOB1");
                        Progreso("Procesamiento de Archivo de Produccin Finalizado", "100");
                    }
                    break;
                case "INVENTARIO":
                    resultado = CargarCSV_INVENTARIO_INFILE(RutaDispo, request);
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
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51_CONSUMOS"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order_)";

        String Sqlborrar = "delete from MB51_CONSUMOS";

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

    public String CargarCSV_KOB1_INFILE(String Ruta, HttpServletRequest request) throws IOException, SQLException {

        mes(request);


        CONVERSION_LABOR = Double.parseDouble(request.getParameter("ConversionLabor"));
        CONVERSION_MACHINE = Double.parseDouble(request.getParameter("ConversionMachine"));
        CONVERSION_OVHDS = Double.parseDouble(request.getParameter("ConversionOvhds"));

        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE KOB1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Functional_Area,Company_Code,Order_,CO_object_name,Cost_Element,Cost_element_name,Material,Material_Description,Plant,Period,Fiscal_Year,Dr_Cr_indicator,Total_Quantity,Unit_of_Measure,Value_TranCurr,Transaction_Currency,Value_in_Obj_Crcy,Object_Currency,Document_Number)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        
        String Sqlborrar = "delete from KOB1 WHERE IdArchivo is null";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            
        }
        Progreso("Cargando Archivo ", "1");
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {

            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();

            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano KOB1 en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);


//            System.out.println("INICIA CARGA AL LISTADO CON TODOS LOS REGISTROS DE KOB1: " + new Date());
            LinkedList<ModeloKob1> lstModeloKob1 = new LinkedList<ModeloKob1>();

            Progreso("LLenando Listado de Produccion ", "5");
            lstModeloKob1 = Select("SELECT * from KOB1 WHERE IdArchivo is null", con);
//            System.out.println("FINALIZA CARGA AL LISTADO CON TODOS LOS REGISTROS DE KOB1: " + new Date());
            LinkedList<ModeloKob1> lstModeloKob1Upd = new LinkedList<ModeloKob1>();

//            System.out.println("INICIA RECORRIDO Y LLENADO DEL MODELO LISTADO NUMERO 1: " + new Date());
//            int Contador = 1;
//            int cantidad = 1;
//
//            StringBuilder Cadena = new StringBuilder();
//
//            for (ModeloKob1 modeloKob1 : lstModeloKob1) {
//                modeloKob1 = LlenarMrpdata_Original(modeloKob1, con);
//
//                lstModeloKob1Upd.add(modeloKob1);
//                Contador++;
//
//                if (Contador == 1000) {
//                    System.out.println("Vuelta de Procesados Fase 1: " + cantidad + " - " + new Date());
//                    cantidad++;
//                    Contador = 1;
//                }
//                Cadena.delete(0, Cadena.length());
//            }
//            System.out.println("FINALIZA RECORRIDO Y LLENADO DEL MODELO LISTADO NUMERO 1: " + new Date());
//            System.out.println("INICIA ACTUALIZACION DEL LISTADO NUMERO 1: " + new Date());
            Progreso("Actualizacion Listado Para Produccion Piso", "10");
            controladorKob1.UpdateList_Carlos(lstModeloKob1, con, "Actualizacion Listado Para Produccion Piso", "10");
            lstModeloKob1Upd = null;
            lstModeloKob1Upd = new LinkedList<ModeloKob1>();
//            System.out.println("FINALIZA ACTUALIZACION DEL LISTADO NUMERO 1: " + new Date());

            //BUSCO ORDENES QUE NO CONTENGAN E
//            System.out.println("INICIA DEPURACION DE REGISTROS CON ORDENES NO PISO: " + new Date());
            Progreso("Depurando registros con ordenes No Piso", "20");
            lstModeloKob1 = controladorKob1.Select("SELECT * from kob1 where Procur_Type = 'E' and IdArchivo is null GROUP by Order_");
            String SqlConOrdenes = "SELECT * from kob1 where Procur_Type is not null and IdArchivo is null";
            for (ModeloKob1 modeloKob1 : lstModeloKob1) {
                SqlConOrdenes = SqlConOrdenes + " AND Order_ <> '" + modeloKob1.getOrder_() + "'";
            }
            lstModeloKob1 = controladorKob1.Select(SqlConOrdenes);
//            System.out.println("FINALIZA DEPURACION DE REGISTROS CON ORDENES NO PISO: " + new Date());
//            System.out.println("INICIA PROCESO DE LLENADO DE REGISTROS PARA PRODUCCION PISO: " + new Date());
            Progreso("Llenado de registros Produccion Piso", "30");
            for (ModeloKob1 modeloKob1 : lstModeloKob1) {

                //BUSCO EN COMPRAS
                modeloKob1 = CostoCompras(modeloKob1, con);
                //CALCULO Total Raw Material
                modeloKob1 = TotalRawValue(modeloKob1, con);
                //CALCULO Packaging_Materials
                if (modeloKob1.getCost_Element().contentEquals("50430")) {
                    modeloKob1 = Packaging_Materials(modeloKob1, con);
                }

                //UpdateModeloKob1(modeloKob1, con);
                lstModeloKob1Upd.add(modeloKob1);

            }

//            System.out.println("FINALIZA PROCESO DE LLENADO DE REGISTROS PARA PRODUCCION PISO: " + new Date());
//            System.out.println("INICIA ACTUALIZACION DE REGISTROS DE PRODUCCION PISO: " + new Date());
            Progreso("Actualizacio registros Produccion Piso", "40");
            controladorKob1.UpdateList_Carlos(lstModeloKob1Upd, con, "Actualizacio registros Produccion Piso", "40");
//            System.out.println("FINALIZA ACTUALIZACION DE REGISTROS DE PRODUCCION PISO: " + new Date());

//            System.out.println("INICIA CONSULTAS SQL : " + new Date());


//            System.out.println(" ---------------- INICIA SUMA DE VALORES SQL : " + new Date());
            //controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where Cost_Element <> '50440' and Cost_Element <> '50400' and IdArchivo is null");
            Progreso("Calculando Valor Produccion Piso", "60");
            controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where IdArchivo is null");

//            System.out.println(" ---------------- FINALIZA SUMA DE VALORES SQL : " + new Date());
            controladorMrpdata.Insert("Delete from informe_produccion");

//            System.out.println(" ---------------- INICIA INSERSION DE INFORME DE PRODUCCION PISO : " + new Date());

            String SqlInformeProduccionPiso = "INSERT INTO "
                    + "informe_produccion "
                    + "(Finish_Good_sku, "
                    + "CO_object_name, "
                    + "Batch_Finish_goods, "
                    + "Link_Terminado_Batch, "
                    + "Cantidad, "
                    + "Total_Raw_Material, "
                    + "Manufact, "
                    + "Packaging, "
                    + "Conversion, "
                    + "Nuevo_Valor_Orden) "
                    + "(select "
                    + "Finish_Good_sku, "
                    + "CO_object_name, "
                    + "Batch_Finish_goods, "
                    + "Link_Terminado_Batch, "
                    + "Cantidad_Terminada, "
                    + "sum(Total_Raw_Material) as Total_Raw_Material_, "
                    + "sum(Manufact_Materials) as Manufact_Materials_, "
                    + "sum(Packaging_Materials) as Packaging_Materials_, "
                    + "sum(Conversion_Cost) as Conversion_Cost_, "
                    + "sum(Nuevo_Valor_Orden) as Nuevo_Valor_Orden_ "
                    + "from kob1 where IdArchivo is null "
                    + "group by Link_Terminado_Batch)";

            Progreso("Generando Informe de Produccion Piso", "70");
            controladorMrpdata.Insert(SqlInformeProduccionPiso);

//            System.out.println(" ---------------- FINALIZA INSERSION DE INFORME DE PRODUCCION PISO : " + new Date());

//            System.out.println(" ---------------- INICIA CALCULO EN INFORME DE PRODUCCION PISO : " + new Date());
            String ActualizacionInformePiso = "update "
                    + "informe_produccion "
                    + "set Costo_unitario_FIFO = (Nuevo_Valor_Orden / Cantidad)";

            Progreso("Generando Informe de Produccion Piso", "73");
            controladorMrpdata.Insert(ActualizacionInformePiso);

//            System.out.println(" ---------------- FINALIZA CALCULO EN INFORME DE PRODUCCION PISO : " + new Date());

            String ActualizacionProcur_Type = "update "
                    + "kob1 "
                    + "set Procur_Type = '' where Procur_Type is null and IdArchivo is null";

            controladorMrpdata.Insert(ActualizacionProcur_Type);

//            System.out.println("FINALIZA CONSULTAS SQL : " + new Date());

//            System.out.println("INICIA LLENADO DEL MODELO LISTADO NUMERO 2: " + new Date());
            lstModeloKob1 = null;
            Progreso("Inicia Llenado de Listado General", "75");
            lstModeloKob1 = controladorKob1.Select("SELECT * from KOB1 where IdArchivo is null");
//            System.out.println("FINALIZA LLENADO DEL MODELO LISTADO NUMERO 2: " + new Date());
            lstModeloKob1Upd = null;
            lstModeloKob1Upd = new LinkedList<ModeloKob1>();
//            System.out.println("INICIA RECORRIDO DEL LISTADO NUMERO 2: " + new Date());
            Progreso("Inicia Recorrido de Listado General", "80");
            for (ModeloKob1 modeloKob1 : lstModeloKob1) {

                if (!modeloKob1.getMaterial().contentEquals("")) {
                    //LLENAMOS COLUMNA Cost Unit Fifo R.Mat y Pack
                    if (modeloKob1.getProcur_Type().contentEquals("F")) {
                        modeloKob1 = CostoCompras(modeloKob1, con);
                        modeloKob1 = TotalRawValue(modeloKob1, con);
                    } else {
                        modeloKob1 = ProduccionPiso(modeloKob1, con);
                        modeloKob1 = ManufactMaterials(modeloKob1, con);
                    }
                    if (modeloKob1.getCost_Element().contentEquals("50430")) {
                        modeloKob1 = Packaging_Materials(modeloKob1, con);
                    }
                }

                lstModeloKob1Upd.add(modeloKob1);
            }

            controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where Cost_Element <> '50440' and Cost_Element <> '50400' and IdArchivo is null");

//            System.out.println("FINALIZA RECORRIDO DEL LISTADO NUMERO 2: " + new Date());
//            System.out.println("INICIA ACTUALIZACION DEL LISTADO NUMERO 2: " + new Date());
            Progreso("Inicia Actualizacion de Listado General", "85");
            controladorKob1.UpdateList_Carlos(lstModeloKob1Upd, con, "Inicia Actualizacion de Listado General", "85");
//            System.out.println("FINALIZA ACTUALIZACION DEL LISTADO NUMERO 2: " + new Date());

//            System.out.println(" ---------------- INICIA SUMA DE VALORES SQL : " + new Date());
            Progreso("Actualizando Calculo Final", "95");
            controladorMrpdata.Insert("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where IdArchivo is null");
//            System.out.println(" ---------------- FINALIZA SUMA DE VALORES SQL : " + new Date());

            con.close();
        }
        return Realizado;

    }

    public String CargarCSV_POVR_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE POVR"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Material,Material_Description,Process_Order_Number,Process_Order_Type,Material_Group_Packaging,Profit_Center,UOM,Material_type,Existing_Material_Costing_Lot_Size,MRP_Planned_Quantity,Consumption_Qty_Actual_I_P,Delivered_Qty_Actual_O_P,Yield_Variance_Qty,Yield_Variance_Porcentaje,Actual_Material_Cost,Labor_Cost_Actual,Machine_Cost_Actual,Overhead_Cost_Actual,Total_Cost_Actual_Output,Physical_Inventory_Yield_Loss_cost_Actu,Standard_Price,Calculated_Production_Variance,Conversion_Cost_Per_Unit,Display_Currency,Batch_Number,Product_Hierarchy,Actual_Start_Date,Actual_Finish_Date)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        //ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        String Sqlborrar = "delete from POVR";

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

    public String CargarCSV_INVENTARIO_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE INVENTARIO"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Material_Description,Batch,Link_Material_Batch,FIFO_Cost_UNIT)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        //ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        String Sqlborrar = "delete from INVENTARIO";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano INVENTARIO en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }
        return Realizado;
    }

    public ModeloKob1 LlenarMrpdata(ModeloKob1 modeloKob1, Connection con, StringBuilder Cadena) throws SQLException {

        Cadena.delete(0, Cadena.length());
        Cadena.append("SELECT * FROM mb51_Consumos where Material = '").append(modeloKob1.getMaterial()).append("' and Order_ = '").append(modeloKob1.getOrder_()).append("'");
        LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = controladorMb51_Consumos.SelectSQL(Cadena + "", con);

        Cadena.delete(0, Cadena.length());
        Cadena.append("SELECT * FROM mrpdata WHERE Material = '").append(modeloKob1.getMaterial()).append("'");
        ModeloMrpData modeloMrpData = controladorMrpdata.SelectSQL(Cadena + "", con);

        Cadena.delete(0, Cadena.length());
        Cadena.append("SELECT * FROM povr where Process_Order_Number = '").append(modeloKob1.getOrder_()).append("'");
        LinkedList<ModeloPovr> LstModeloPovr = controladorPovr.SelectSQL(Cadena + "", con);

        modeloKob1 = CostoInventario_Copia(modeloKob1, con, Cadena);

        return modeloKob1;
    }

    public ModeloKob1 CostoInventario_Copia(ModeloKob1 modeloKob1, Connection con, StringBuilder Cadena) throws SQLException {

        Cadena.delete(0, Cadena.length());
        Cadena.append("SELECT * FROM INVENTARIO where Link_Material_Batch = '").append(modeloKob1.getLink_Material_Batch()).append("'");
        LinkedList<ModeloInventario> LstModeloInventario = controladorInventario.SelectSQL(Cadena + "", con);

        return modeloKob1;
    }

    public ModeloKob1 LlenarMrpdata_Copia(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        if (!modeloKob1.getMaterial().contentEquals("")) {
            //LLENAMOS CULUMNA Link ( Plant & Material)
            modeloKob1.setLink_Plant_Material(modeloKob1.getPlant() + modeloKob1.getMaterial());
            //LLENAMOS COLUMNA Link ( Material & orden)
            modeloKob1.setLink_Material_orden(modeloKob1.getMaterial() + modeloKob1.getOrder_());
            LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = controladorMb51_Consumos.SelectSQL("SELECT * FROM mb51_Consumos where Material = '" + modeloKob1.getMaterial() + "' and Order_ = '" + modeloKob1.getOrder_() + "'", con);
            for (ModeloMb51_Consumos modeloMb51_Consumos : LstModeloMb51_Consumos) {
                //LLENAMOS COLUMNA Batch consumo
                if (modeloMb51_Consumos.getBatch() != null) {
                    modeloKob1.setBatch_consumo(modeloMb51_Consumos.getBatch());
                }
            }
            if (modeloKob1.getBatch_consumo() == null) {
                //LLENAMOS COLUMNA Batch consumo
                modeloKob1.setBatch_consumo("");
            }
            //LLENAMOS COLUMNA Link (Material & Batch)
            modeloKob1.setLink_Material_Batch(modeloKob1.getMaterial() + modeloKob1.getBatch_consumo());

            ModeloMrpData modeloMrpData = controladorMrpdata.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloKob1.getMaterial() + "'", con);

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
        modeloKob1 = CostoInventario(modeloKob1, con);

        return modeloKob1;
    }

    public ModeloKob1 LlenarMrpdata_Original(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        if (!modeloKob1.getMaterial().contentEquals("")) {
            //LLENAMOS CULUMNA Link ( Plant & Material)
            modeloKob1.setLink_Plant_Material(modeloKob1.getPlant() + modeloKob1.getMaterial());
            //LLENAMOS COLUMNA Link ( Material & orden)
            modeloKob1.setLink_Material_orden(modeloKob1.getMaterial() + modeloKob1.getOrder_());
            LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = controladorMb51_Consumos.SelectSQL("SELECT * FROM mb51_Consumos where Material = '" + modeloKob1.getMaterial() + "' and Order_ = '" + modeloKob1.getOrder_() + "'", con);
            for (ModeloMb51_Consumos modeloMb51_Consumos : LstModeloMb51_Consumos) {
                //LLENAMOS COLUMNA Batch consumo
                if (modeloMb51_Consumos.getBatch() != null) {
                    modeloKob1.setBatch_consumo(modeloMb51_Consumos.getBatch());
                }
            }
            if (modeloKob1.getBatch_consumo() == null) {
                //LLENAMOS COLUMNA Batch consumo
                modeloKob1.setBatch_consumo("");
            }
            //LLENAMOS COLUMNA Link (Material & Batch)
            modeloKob1.setLink_Material_Batch(modeloKob1.getMaterial() + modeloKob1.getBatch_consumo());

            ModeloMrpData modeloMrpData = controladorMrpdata.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloKob1.getMaterial() + "'", con);

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
        modeloKob1 = CostoInventario(modeloKob1, con);

        return modeloKob1;
    }

    public ModeloKob1 CostoInventario(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        String Sql = "SELECT * FROM INVENTARIO where Link_Material_Batch = '" + modeloKob1.getLink_Material_Batch() + "'";
        LinkedList<ModeloInventario> LstModeloInventario = controladorInventario.SelectSQL(Sql, con);
        if (LstModeloInventario.size() > 0) {
            for (ModeloInventario modeloInventario : LstModeloInventario) {
                modeloKob1.setCost_Unit_Fifo_Old(modeloInventario.getFIFO_Cost_UNIT());
            }
        } else {
            modeloKob1.setCost_Unit_Fifo_Old("0.0");
        }
        return modeloKob1;
    }

    public ModeloKob1 CostoCompras(ModeloKob1 modeloKob1, Connection con) throws SQLException {

//        ControladorMb51 controladorMb51 = new ControladorMb51();
//        String Sql = "SELECT * FROM mb51 where link1_Material_Batch = '" + modeloKob1.getLink_Material_Batch() + "'";
        String Sql = "SELECT * FROM mb51 where link1_Material_Batch = '" + modeloKob1.getLink_Material_Batch() + "' AND Posting_Date like '%" + MES + "%'";

        String Sql1 = "SELECT * FROM mb51 where Material = '" + modeloKob1.getMaterial() + "' and Batch = '" + modeloKob1.getBatch_consumo() + "'";

        LinkedList<ModeloMb51> LstModeloMb51 = controladorMb51.SelectSql(Sql, con);
        if (LstModeloMb51.size() > 0) {
            for (ModeloMb51 modeloMb51 : LstModeloMb51) {
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack(modeloMb51.getUnitario_final_FIFO());
            }
        } else {
            modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
        }
        return modeloKob1;
    }

    public ModeloKob1 ProduccionPiso(ModeloKob1 modeloKob1, Connection con) throws SQLException {

//        ControladorInformeProduccion controladorInformeProduccion = new ControladorInformeProduccion();
        String Sql = "SELECT * FROM informe_produccion where Link_Terminado_Batch = '" + modeloKob1.getLink_Material_Batch() + "'";
        LinkedList<ModeloInforme_Produccion> LstModeloInforme_Produccion = controladorInformeProduccion.SelectSql(Sql, con);

        if (LstModeloInforme_Produccion.size() > 0) {
            for (ModeloInforme_Produccion modeloInforme_Produccion : LstModeloInforme_Produccion) {
                Double Cantidad = 0.0;
                Double Total_Raw_Material = 0.0;
                Double Manufact = 0.0;
                Double Packaging = 0.0;
                Double Conversion = 0.0;
                Double Nuevo_Valor_Orden = 0.0;

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
                Double Costo_unitario_FIFO = Nuevo_Valor_Orden / Cantidad;

                //if (modeloInforme_Produccion.getCosto_unitario_FIFO() != null) {
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack(String.format("%.5f", Costo_unitario_FIFO).replace(",", "."));
                //} else {
                //    modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
                //}

            }
        } else {
            modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
        }
        return modeloKob1;
    }

    public ModeloKob1 TotalRawValue(ModeloKob1 modeloKob1, Connection con) throws SQLException {

        Double Total_Quantity = Double.valueOf(modeloKob1.getTotal_Quantity());
        Double Total_Raw_Material = 0.0;

        if (modeloKob1.getCost_Unit_Fifo_Old() == null) {
            modeloKob1.setCost_Unit_Fifo_Old("0.0");
        }

        if (modeloKob1.getCost_Unit_Fifo_Old().contentEquals("0.0")) {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf(modeloKob1.getCost_Unit_Fifo_R_Mat_Pack());
            Total_Raw_Material = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        } else {
            Double Cost_Unit_Fifo_Old = Double.valueOf(modeloKob1.getCost_Unit_Fifo_Old());
            Total_Raw_Material = Cost_Unit_Fifo_Old * Total_Quantity;
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
    public LinkedList<ModeloKob1> Select(String Sql, Connection con) {
        LinkedList<ModeloKob1> lstModeloKob1 = new LinkedList<ModeloKob1>();

        PreparedStatement SQL;
        try {
            SQL = con.prepareStatement(Sql);
            ResultSet res = SQL.executeQuery();
            while (res.next()) {
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
                    modeloKob1.setTotal_Raw_Material("");
                }
                if (modeloKob1.getManufact_Materials() == null) {
                    modeloKob1.setManufact_Materials("");
                }
                if (modeloKob1.getPackaging_Materials() == null) {
                    modeloKob1.setPackaging_Materials("");
                }
                if (modeloKob1.getConversion_Cost() == null) {
                    modeloKob1.setConversion_Cost("");
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

                lstModeloKob1.add(modeloKob1);
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

        switch (mes) {
            case "Enero":
                MES = "/01/" + ano;
                break;
            case "Febrero":
                MES = "/02/" + ano;
                break;
            case "Marzo":
                MES = "/03/" + ano;
                break;
            case "Abril":
                MES = "/04/" + ano;
                break;
            case "Mayo":
                MES = "/05/" + ano;
                break;
            case "Junio":
                MES = "/06/" + ano;
                break;
            case "Julio":
                MES = "/07/" + ano;
                break;
            case "Agosto":
                MES = "/08/" + ano;
                break;
            case "Septiembre":
                MES = "/09/" + ano;
                break;
            case "Octubre":
                MES = "/10/" + ano;
                break;
            case "Noviembre":
                MES = "/11/" + ano;
                break;
            case "Diciembre":
                MES = "/12/" + ano;
                break;

        }

    }
    
    public void Progreso(String Proceso, String Porcentaje)
    {
        System.out.println("Porcentaje " + Porcentaje + "% Proceso: " + Proceso);
        
    }

}
