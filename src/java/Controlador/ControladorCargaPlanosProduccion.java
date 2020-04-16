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
import Modelos.ModeloEine;
import Modelos.ModeloEstadoPlanos;
import Modelos.ModeloFbl3m;
import Modelos.ModeloInforme_Produccion;
import Modelos.ModeloInventario;
import Modelos.ModeloKob1;
import Modelos.ModeloMb51;
import Modelos.ModeloMb51_Consumos;
import Modelos.ModeloMe80fn;
import Modelos.ModeloMrpData;
import Modelos.ModeloPovr;
import Modelos.ModeloProveedor;
import Modelos.ModeloUsuario;
import Modelos.ModeloVarios;
import Modelos.ModeloVendorType;
import Modelos.ModeloZ39;
import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.FileInputStream;
import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.util.calendar.CalendarUtils;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCargaPlanosProduccion
{

    Double CONVERSION_LABOR = 0.0;
    Double CONVERSION_MACHINE = 0.0;
    Double CONVERSION_OVHDS = 0.0;

    public String procesarCarga (LinkedList<ModeloArchivos> listModeloArchivoses, HttpServletRequest request, HttpServletResponse response)
    {
        String resultado = "false";
        for (ModeloArchivos listModeloArchivose : listModeloArchivoses)
        {
            try
            {
                String RutaDispo = listModeloArchivose.getRuta ();
                String nombre = listModeloArchivose.getNombre ();
                StringTokenizer st = new StringTokenizer (nombre, ".");
                nombre = st.nextToken ();
                switch (nombre)
                {

                    case "MB51_CONSUMOS":
                        resultado = CargarCSV_MB51_CONSUMOS_INFILE (RutaDispo, request);
                        break;
                    case "POVR":
                        resultado = CargarCSV_POVR_INFILE (RutaDispo, request);
                        break;
                    case "KOB1":
                        resultado = CargarCSV_KOB1_INFILE (RutaDispo, request);
                        if ("true".equals (resultado))
                        {
                            Herramienta herramientas = new Herramienta ();
                            ControladorFechas controladorFechas = new ControladorFechas ();
                            ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos ();
                            modeloEstadoPlanos.setNombrePlano ("KOB1");
                            modeloEstadoPlanos.setFechaCarga (herramientas.sDate ());
                            modeloEstadoPlanos.setEstado ("Activo");
                            modeloEstadoPlanos.setIdFechas (controladorFechas.getIdFecha (request));
                            ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos ();
                            controladorEstadoPlanos.Insert (modeloEstadoPlanos);
                            //Selecionamos el ultimo Id del la tabal estado plano
                            modeloEstadoPlanos.setId (controladorEstadoPlanos.getIdEstadoPlanos ());
                            // Update Mb51
                            controladorEstadoPlanos.UpdateMB51 (modeloEstadoPlanos, "KOB1");
                        }
                        break;
                }
            } catch (IOException | SQLException ex)
            {
                System.out.println ("Error en la carga del plano " + "  " + ex);
            }

        }

        return resultado;

    }

    public String Upload (HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException
    {
        String resultado = "false";
        String formato = "";

        try
        {
            long tamanorequies = request.getPart ("archivo").getSize ();
            formato = request.getParameter ("Formato");
            Part arch = request.getPart ("archivo");
            String fileName = Paths.get (arch.getSubmittedFileName ()).getFileName ().toString ();
            System.out.println (fileName);
            InputStream is = arch.getInputStream ();
            File detino = new File ("C:\\Zred\\SunChemical\\" + formato + "\\");
            if (detino.exists () != true)
            {
                detino.mkdirs ();
            }
            File file = new File (detino, formato + "_" + ObtenerFecha () + ".csv");
            try (InputStream input = arch.getInputStream ())
            {
                file.delete ();
                Files.copy (input, file.toPath ());
            }
            //String RutaDispo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Compras\\Compras_10-11-2019.csv";//
            String RutaDispo = file.getPath ();
            File RutaFinal = new File (RutaDispo);
            //File RutaFinal = new File("C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Compras\\Compras_10-11-2019.csv");
            if (tamanorequies == RutaFinal.length ())
            {
                resultado = "true";
            }
            String d = request.getParameter ("IdNombrePlano");
            System.out.println (d);
            switch (request.getParameter ("NombrePlano"))
            {
                case "MB51_CONSUMOS":
                    resultado = CargarCSV_MB51_CONSUMOS_INFILE (RutaDispo, request);
                    break;
                case "POVR":
                    resultado = CargarCSV_POVR_INFILE (RutaDispo, request);
                    break;
                case "KOB1":
                    resultado = CargarCSV_KOB1_INFILE (RutaDispo, request);
                    if ("true".equals (resultado))
                    {
                        Herramienta herramientas = new Herramienta ();
                        ControladorFechas controladorFechas = new ControladorFechas ();
                        ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos ();
                        modeloEstadoPlanos.setNombrePlano ("KOB1");
                        modeloEstadoPlanos.setFechaCarga (herramientas.sDate ());
                        modeloEstadoPlanos.setEstado ("Activo");
                        modeloEstadoPlanos.setIdFechas (controladorFechas.getIdFecha (request));
                        ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos ();
                        controladorEstadoPlanos.Insert (modeloEstadoPlanos);
                        //Selecionamos el ultimo Id del la tabal estado plano
                        modeloEstadoPlanos.setId (controladorEstadoPlanos.getIdEstadoPlanos ());
                        // Update Mb51
                        controladorEstadoPlanos.UpdateMB51 (modeloEstadoPlanos, "KOB1");
                    }
                    break;
                case "INVENTARIO":
                    resultado = CargarCSV_INVENTARIO_INFILE (RutaDispo, request);
                    break;

            }
        } catch (IOException | ServletException e)
        {
            System.out.println ("Error en la carga del plano " + formato + "  " + e);
        }
        return resultado;
    }

    //--------------------------------------------------------------------------------------
    public String CargarCSV_MB51_CONSUMOS_INFILE (String Ruta, HttpServletRequest request) throws IOException
    {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter ("Mes");
        String mes = request.getParameter ("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace ("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51_CONSUMOS"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order_)";

        System.out.println ("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata ();

        String Sqlborrar = "delete from MB51_CONSUMOS";

        if (controladorMrpdata.Insert (Sqlborrar))
        {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert (SqlInsertMasivo))
        {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
            modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
            modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a cargado el plano EINE en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
            controladorAuditoria.Insert (modeloAuditoria);

        }
        return Realizado;
    }

    public String CargarCSV_KOB1_INFILE (String Ruta, HttpServletRequest request) throws IOException, SQLException
    {
        /*
         * Variables de Año y Mes
         */

//        CONVERSION_LABOR = 1.2;
//        CONVERSION_MACHINE = 1.5;
//        CONVERSION_OVHDS = 1.18;
        CONVERSION_LABOR = Double.parseDouble (request.getParameter ("ConversionLabor"));
        CONVERSION_MACHINE = Double.parseDouble (request.getParameter ("ConversionMachine"));
        CONVERSION_OVHDS = Double.parseDouble (request.getParameter ("ConversionOvhds"));

        String ano = request.getParameter ("Mes");
        String mes = request.getParameter ("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace ("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE KOB1"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Functional_Area,Company_Code,Order_,CO_object_name,Cost_Element,Cost_element_name,Material,Material_Description,Plant,Period,Fiscal_Year,Dr_Cr_indicator,Total_Quantity,Unit_of_Measure,Value_TranCurr,Transaction_Currency,Value_in_Obj_Crcy,Object_Currency,Document_Number)";

        System.out.println ("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata ();

        String Sqlborrar = "delete from KOB1";

        if (controladorMrpdata.Insert (Sqlborrar))
        {
            //Realizado = "true";
        }
        if (controladorMrpdata.Insert (SqlInsertMasivo))
        {

            //LLENO CONVERSIONES
            String Sql_CONVERSION_LABOR = "UPDATE KOB1 set Conversion_Cost = Value_TranCurr * " + CONVERSION_LABOR + " where Cost_Element = '80004'";
            String Sql_CONVERSION_MACHINE = "UPDATE KOB1 set Conversion_Cost = Value_TranCurr * " + CONVERSION_MACHINE + " where Cost_Element = '80005'";
            String Sql_CONVERSION_OVHDS = "UPDATE KOB1 set Conversion_Cost = Value_TranCurr * " + CONVERSION_OVHDS + " where Cost_Element = '80015'";

            System.out.println ("INICIA ACTUALIZACION DE CONVERSIONES: " + new Date ());
            controladorMrpdata.Insert (Sql_CONVERSION_LABOR);
            controladorMrpdata.Insert (Sql_CONVERSION_MACHINE);
            controladorMrpdata.Insert (Sql_CONVERSION_OVHDS);
            System.out.println ("FINALIZA ACTUALIZACION DE CONVERSIONES: " + new Date ());

            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
            modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
            modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a cargado el plano KOB1 en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
            controladorAuditoria.Insert (modeloAuditoria);

            ControladorKob1 controladorKob1 = new ControladorKob1 ();
            System.out.println ("INICIA CARGA AL LISTADO CON TODOS LOS REGISTROS DE KOB1: " + new Date ());
            LinkedList<ModeloKob1> lstModeloKob1 = new LinkedList<ModeloKob1> ();
            lstModeloKob1 = controladorKob1.Select ("SELECT * from KOB1");
            System.out.println ("FINALIZA CARGA AL LISTADO CON TODOS LOS REGISTROS DE KOB1: " + new Date ());
            LinkedList<ModeloKob1> lstModeloKob1Upd = new LinkedList<ModeloKob1> ();

            System.out.println ("INICIA RECORRIDO Y LLENADO DEL MODELO LISTADO NUMERO 1: " + new Date ());
            int Contador = 1;

            ConexionBDMySql conexion = new ConexionBDMySql ();
            Connection con;
            con = conexion.abrirConexion ();

            for (ModeloKob1 modeloKob1 : lstModeloKob1)
            {
                modeloKob1 = LlenarMrpdata (modeloKob1, con);

//            //LLENO CONVERSIONES
//            if (modeloKob1.getCost_Element().contentEquals("80004") || modeloKob1.getCost_Element().contentEquals("80005") || modeloKob1.getCost_Element().contentEquals("80015")) {
//                modeloKob1 = Conversions(modeloKob1, con);
//            }
                lstModeloKob1Upd.add (modeloKob1);
                Contador++;
            }
            System.out.println ("FINALIZA RECORRIDO Y LLENADO DEL MODELO LISTADO NUMERO 1: " + new Date ());

            System.out.println ("INICIA ACTUALIZACION DEL LISTADO NUMERO 1: " + new Date ());
            controladorKob1.UpdateList (lstModeloKob1Upd, con);
            lstModeloKob1Upd = null;
            lstModeloKob1Upd = new LinkedList<ModeloKob1> ();
            System.out.println ("FINALIZA ACTUALIZACION DEL LISTADO NUMERO 1: " + new Date ());

            //BUSCO ORDENES QUE NO CONTENGAN E
            System.out.println ("INICIA DEPURACION DE REGISTROS CON ORDENES NO PISO: " + new Date ());
            lstModeloKob1 = controladorKob1.Select ("SELECT * from kob1 where Procur_Type = 'E' GROUP by Order_");
            String SqlConOrdenes = "SELECT * from kob1 where Procur_Type is not null ";
            for (ModeloKob1 modeloKob1 : lstModeloKob1)
            {
                SqlConOrdenes = SqlConOrdenes + " AND Order_ <> '" + modeloKob1.getOrder_ () + "'";
            }
            lstModeloKob1 = controladorKob1.Select (SqlConOrdenes);
            System.out.println ("FINALIZA DEPURACION DE REGISTROS CON ORDENES NO PISO: " + new Date ());
            System.out.println ("INICIA PROCESO DE LLENADO DE REGISTROS PARA PRODUCCION PISO: " + new Date ());
            for (ModeloKob1 modeloKob1 : lstModeloKob1)
            {

                //BUSCO EN COMPRAS
                modeloKob1 = CostoCompras (modeloKob1, con);
                //CALCULO Total Raw Material
                modeloKob1 = TotalRawValue (modeloKob1, con);
                //CALCULO Packaging_Materials
                if (modeloKob1.getCost_Element ().contentEquals ("50430"))
                {
                    modeloKob1 = Packaging_Materials (modeloKob1, con);
                }

//                Double Total_Raw_Material = 0.0;
//                Double Manufact_Materials = 0.0;
//                Double Packaging_Materials = 0.0;
//                Double Conversion_Cost = 0.0;
//                Double Nuevo_Valor_Orden = 0.0;
//
//                if (modeloKob1.getTotal_Raw_Material() != null) {
//                    Total_Raw_Material = Double.valueOf(modeloKob1.getTotal_Raw_Material());
//                } else {
//                    modeloKob1.setTotal_Raw_Material(String.format("%.5f", Total_Raw_Material).replace(",", "."));
//                }
//                if (modeloKob1.getManufact_Materials() != null) {
//                    Manufact_Materials = Double.valueOf(modeloKob1.getManufact_Materials());
//                } else {
//                    modeloKob1.setManufact_Materials(String.format("%.5f", Manufact_Materials).replace(",", "."));
//                }
//                if (modeloKob1.getPackaging_Materials() != null) {
//                    Packaging_Materials = Double.valueOf(modeloKob1.getPackaging_Materials());
//                } else {
//                    modeloKob1.setPackaging_Materials(String.format("%.5f", Packaging_Materials).replace(",", "."));
//                }
//                if (modeloKob1.getConversion_Cost() != null) {
//                    Conversion_Cost = Double.valueOf(modeloKob1.getConversion_Cost());
//                } else {
//                    modeloKob1.setConversion_Cost(String.format("%.5f", Conversion_Cost).replace(",", "."));
//                }
                //Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost;
                //modeloKob1.setNuevo_Valor_Orden(String.format("%.5f", Nuevo_Valor_Orden).replace(",", "."));
                lstModeloKob1Upd.add (modeloKob1);
                Contador++;
            }

            System.out.println ("FINALIZA PROCESO DE LLENADO DE REGISTROS PARA PRODUCCION PISO: " + new Date ());
            System.out.println ("INICIA ACTUALIZACION DE REGISTROS DE PRODUCCION PISO: " + new Date ());
            controladorKob1.UpdateList (lstModeloKob1Upd, con);
            System.out.println ("FINALIZA ACTUALIZACION DE REGISTROS DE PRODUCCION PISO: " + new Date ());

            System.out.println ("INICIA CONSULTAS SQL : " + new Date ());
            controladorMrpdata.Insert ("update kob1 set Total_Raw_Material = '' where Total_Raw_Material is null");
            controladorMrpdata.Insert ("update kob1 set Manufact_Materials = '' where Manufact_Materials is null");
            controladorMrpdata.Insert ("update kob1 set Packaging_Materials = '' where Packaging_Materials is null");
            controladorMrpdata.Insert ("update kob1 set Conversion_Cost = '' where Conversion_Cost is null");

            controladorMrpdata.Insert ("update kob1 set Nuevo_Valor_Orden = Total_Raw_Material + Manufact_Materials + Packaging_Materials + Conversion_Cost where Cost_Element <> '50440' and Cost_Element <> '50400'");

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
                    + "from kob1 "
                    + "group by Finish_Good_sku)";

            controladorMrpdata.Insert (SqlInformeProduccionPiso);

            String ActualizacionInformePiso = "update "
                    + "informe_produccion "
                    + "set Costo_unitario_FIFO = (Nuevo_Valor_Orden / Cantidad)";

            controladorMrpdata.Insert (ActualizacionInformePiso);

            String ActualizacionProcur_Type = "update "
                    + "kob1 "
                    + "set Procur_Type = '' where Procur_Type is null";

            controladorMrpdata.Insert (ActualizacionProcur_Type);

            System.out.println ("FINALIZA CONSULTAS SQL : " + new Date ());

            System.out.println ("INICIA LLENADO DEL MODELO LISTADO NUMERO 2: " + new Date ());
            lstModeloKob1 = null;
            lstModeloKob1 = controladorKob1.Select ("SELECT * from KOB1");
            System.out.println ("FINALIZA LLENADO DEL MODELO LISTADO NUMERO 2: " + new Date ());
            lstModeloKob1Upd = null;
            lstModeloKob1Upd = new LinkedList<ModeloKob1> ();
            System.out.println ("INICIA RECORRIDO DEL LISTADO NUMERO 2: " + new Date ());
            for (ModeloKob1 modeloKob1 : lstModeloKob1)
            {

                if (!modeloKob1.getMaterial ().contentEquals (""))
                {
                    //LLENAMOS COLUMNA Cost Unit Fifo R.Mat y Pack
                    if (modeloKob1.getProcur_Type ().contentEquals ("F"))
                    {
                        modeloKob1 = CostoCompras (modeloKob1, con);
                        modeloKob1 = TotalRawValue (modeloKob1, con);
                    }
                    else
                    {
                        modeloKob1 = ProduccionPiso (modeloKob1, con);
                        modeloKob1 = ManufactMaterials (modeloKob1, con);
                    }
                    if (modeloKob1.getCost_Element ().contentEquals ("50430"))
                    {
                        modeloKob1 = Packaging_Materials (modeloKob1, con);
                    }

                }

                lstModeloKob1Upd.add (modeloKob1);
            }

            System.out.println ("FINALIZA RECORRIDO DEL LISTADO NUMERO 2: " + new Date ());
            System.out.println ("INICIA ACTUALIZACION DEL LISTADO NUMERO 2: " + new Date ());
            controladorKob1.UpdateList (lstModeloKob1Upd, con);
            System.out.println ("FINALIZA ACTUALIZACION DEL LISTADO NUMERO 2: " + new Date ());

            con.close ();
        }
        return Realizado;

    }

    public String CargarCSV_POVR_INFILE (String Ruta, HttpServletRequest request) throws IOException
    {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter ("Mes");
        String mes = request.getParameter ("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace ("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE POVR"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Material,Material_Description,Process_Order_Number,Process_Order_Type,Material_Group_Packaging,Profit_Center,UOM,Material_type,Existing_Material_Costing_Lot_Size,MRP_Planned_Quantity,Consumption_Qty_Actual_I_P,Delivered_Qty_Actual_O_P,Yield_Variance_Qty,Yield_Variance_Porcentaje,Actual_Material_Cost,Labor_Cost_Actual,Machine_Cost_Actual,Overhead_Cost_Actual,Total_Cost_Actual_Output,Physical_Inventory_Yield_Loss_cost_Actu,Standard_Price,Calculated_Production_Variance,Conversion_Cost_Per_Unit,Display_Currency,Batch_Number,Product_Hierarchy,Actual_Start_Date,Actual_Finish_Date)";

        System.out.println ("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata ();

        String Sqlborrar = "delete from POVR";

        if (controladorMrpdata.Insert (Sqlborrar))
        {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert (SqlInsertMasivo))
        {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
            modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
            modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a cargado el plano EINE en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
            controladorAuditoria.Insert (modeloAuditoria);

        }
        return Realizado;
    }

    public String CargarCSV_INVENTARIO_INFILE (String Ruta, HttpServletRequest request) throws IOException
    {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter ("Mes");
        String mes = request.getParameter ("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace ("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE INVENTARIO"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Material_Description,Batch,Link_Material_Batch,FIFO_Cost_UNIT)";

        System.out.println ("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata ();

        String Sqlborrar = "delete from INVENTARIO";

        if (controladorMrpdata.Insert (Sqlborrar))
        {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert (SqlInsertMasivo))
        {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria ();
            modeloAuditoria.setModeloUsuario ((ModeloUsuario) request.getSession ().getAttribute ("user"));
            modeloAuditoria.setMensaje ("El Usuario " + modeloAuditoria.getModeloUsuario ().getUsuario () + " a cargado el plano INVENTARIO en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria ();
            controladorAuditoria.Insert (modeloAuditoria);

        }
        return Realizado;
    }

    public ModeloKob1 LlenarMrpdata (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata ();
        ControladorMb51_Consumos controladorMb51_Consumos = new ControladorMb51_Consumos ();
        ControladorPovr controladorPovr = new ControladorPovr ();

        if (!modeloKob1.getMaterial ().contentEquals (""))
        {
            //LLENAMOS CULUMNA Link ( Plant & Material)
            modeloKob1.setLink_Plant_Material (modeloKob1.getPlant () + modeloKob1.getMaterial ());
            //LLENAMOS COLUMNA Link ( Material & orden)
            modeloKob1.setLink_Material_orden (modeloKob1.getMaterial () + modeloKob1.getOrder_ ());
            LinkedList<ModeloMb51_Consumos> LstModeloMb51_Consumos = controladorMb51_Consumos.SelectSQL ("SELECT * FROM mb51_Consumos where Material = '" + modeloKob1.getMaterial () + "' and Order_ = '" + modeloKob1.getOrder_ () + "'", con);
            for (ModeloMb51_Consumos modeloMb51_Consumos : LstModeloMb51_Consumos)
            {
                //LLENAMOS COLUMNA Batch consumo
                modeloKob1.setBatch_consumo (modeloMb51_Consumos.getBatch ());
            }
            if (modeloKob1.getBatch_consumo () != null)
            {
                //LLENAMOS COLUMNA Batch consumo
                modeloKob1.setBatch_consumo ("");
            }
            //LLENAMOS COLUMNA Link (Material & Batch)
            modeloKob1.setLink_Material_Batch (modeloKob1.getMaterial () + modeloKob1.getBatch_consumo ());

            ModeloMrpData modeloMrpData = controladorMrpdata.SelectSQL ("SELECT * FROM mrpdata WHERE Material = '" + modeloKob1.getMaterial () + "'", con);

            //LLENAMOS COLUMNA Material Type Components
            modeloKob1.setMaterial_Type_Components (modeloMrpData.getMaterial_Type ());

            if (!modeloKob1.getCost_Element ().contentEquals ("50440"))
            {
                //LLENAMOS COLUMNA Procur Type
                modeloKob1.setProcur_Type (modeloMrpData.getProcurement_type ());
            }

            Double Value_TranCurr = Double.valueOf (modeloKob1.getValue_TranCurr ());
            Double Total_Quantity = Double.valueOf (modeloKob1.getTotal_Quantity ());
            Double Cost_Unit_Estandar = 0.0;
            if (Total_Quantity != 0.0)
            {
                Cost_Unit_Estandar = Value_TranCurr / Total_Quantity;
            }
            //LLENAMOS COLUMNA Cost Unit Estándar
            modeloKob1.setCost_Unit_Estandar (String.format ("%.5f", Cost_Unit_Estandar).replace (",", "."));

        }
        //LLENAMOS COLUMNA Level 1 es = Columna C
        modeloKob1.setLevel_1 (modeloKob1.getOrder_ ());
        LinkedList<ModeloPovr> LstModeloPovr = controladorPovr.SelectSQL ("SELECT * FROM povr where Process_Order_Number = '" + modeloKob1.getOrder_ () + "'", con);
        for (ModeloPovr modeloPovr : LstModeloPovr)
        {
            //LLENAMOS COLUMNA Finish Good sku
            modeloKob1.setFinish_Good_sku (modeloPovr.getMaterial ());

            //LLENAMOS COLUMNA Batch Finish goods
            modeloKob1.setBatch_Finish_goods (modeloPovr.getBatch_Number ());

            //LLENAMOS COLUMNA Link (Terminado & Batch)
            modeloKob1.setLink_Terminado_Batch (modeloPovr.getMaterial () + modeloPovr.getBatch_Number ());

            //LLENAMOS COLUMNA Cantidad Terminada
            modeloKob1.setCantidad_Terminada (modeloPovr.getDelivered_Qty_Actual_O_P ());
        }

        //LLENAMOS COLUMNA Cost Unit Fifo old
        modeloKob1 = CostoInventario (modeloKob1, con);

        return modeloKob1;
    }

    public ModeloKob1 CostoInventario (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {

        ControladorInventario controladorInventario = new ControladorInventario ();
        String Sql = "SELECT * FROM INVENTARIO where Link_Material_Batch = '" + modeloKob1.getLink_Material_Batch () + "'";
        LinkedList<ModeloInventario> LstModeloInventario = controladorInventario.SelectSQL (Sql, con);
        if (LstModeloInventario.size () > 0)
        {
            for (ModeloInventario modeloInventario : LstModeloInventario)
            {
                modeloKob1.setCost_Unit_Fifo_Old (modeloInventario.getFIFO_Cost_UNIT ());
            }
        }
        else
        {
            modeloKob1.setCost_Unit_Fifo_Old ("0.0");
        }
        return modeloKob1;
    }

    public ModeloKob1 CostoCompras (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {

        ControladorMb51 controladorMb51 = new ControladorMb51 ();
        String Sql = "SELECT * FROM mb51 where link1_Material_Batch = '" + modeloKob1.getLink_Material_Batch () + "'";
        LinkedList<ModeloMb51> LstModeloMb51 = controladorMb51.SelectSql (Sql, con);
        if (LstModeloMb51.size () > 0)
        {
            for (ModeloMb51 modeloMb51 : LstModeloMb51)
            {
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack (modeloMb51.getUnitario_final_FIFO ());
            }
        }
        else
        {
            modeloKob1.setCost_Unit_Fifo_R_Mat_Pack ("0.0");
        }
        return modeloKob1;
    }

    public ModeloKob1 ProduccionPiso (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {

        ControladorInformeProduccion controladorInformeProduccion = new ControladorInformeProduccion ();
        String Sql = "SELECT * FROM informe_produccion where Link_Terminado_Batch = '" + modeloKob1.getLink_Material_Batch () + "'";
        LinkedList<ModeloInforme_Produccion> LstModeloInforme_Produccion = controladorInformeProduccion.SelectSql (Sql, con);

        if (LstModeloInforme_Produccion.size () > 0)
        {
            for (ModeloInforme_Produccion modeloInforme_Produccion : LstModeloInforme_Produccion)
            {
                modeloKob1.setCost_Unit_Fifo_R_Mat_Pack (modeloInforme_Produccion.getCosto_unitario_FIFO ());
            }
        }
        else
        {
            modeloKob1.setCost_Unit_Fifo_R_Mat_Pack ("0.0");
        }
        return modeloKob1;
    }

    public ModeloKob1 TotalRawValue (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {

        Double Total_Quantity = Double.valueOf (modeloKob1.getTotal_Quantity ());
        Double Total_Raw_Material = 0.0;

        if (modeloKob1.getCost_Unit_Fifo_Old ().contentEquals ("0.0"))
        {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf (modeloKob1.getCost_Unit_Fifo_R_Mat_Pack ());
            Total_Raw_Material = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        }
        else
        {
            Double Cost_Unit_Fifo_Old = Double.valueOf (modeloKob1.getCost_Unit_Fifo_Old ());
            Total_Raw_Material = Cost_Unit_Fifo_Old * Total_Quantity;
        }

        modeloKob1.setTotal_Raw_Material (String.format ("%.5f", Total_Raw_Material).replace (",", "."));
        return modeloKob1;
    }

    public ModeloKob1 ManufactMaterials (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {

        Double Total_Quantity = Double.valueOf (modeloKob1.getTotal_Quantity ());
        Double Manufact_Materials = 0.0;

        if (modeloKob1.getCost_Unit_Fifo_Old ().contentEquals ("0.0"))
        {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf (modeloKob1.getCost_Unit_Fifo_R_Mat_Pack ());
            Manufact_Materials = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        }
        else
        {
            Double Cost_Unit_Fifo_Old = Double.valueOf (modeloKob1.getCost_Unit_Fifo_Old ());
            Manufact_Materials = Cost_Unit_Fifo_Old * Total_Quantity;
        }

        modeloKob1.setManufact_Materials (String.format ("%.5f", Manufact_Materials).replace (",", "."));
        return modeloKob1;
    }

    public ModeloKob1 Packaging_Materials (ModeloKob1 modeloKob1, Connection con) throws SQLException
    {
        Double Total_Quantity = Double.valueOf (modeloKob1.getTotal_Quantity ());
        Double Packaging_Materials = 0.0;
        if (modeloKob1.getCost_Unit_Fifo_Old ().contentEquals (""))
        {
            Double Cost_Unit_Fifo_R_Mat_Pack = Double.valueOf (modeloKob1.getCost_Unit_Fifo_R_Mat_Pack ());
            Packaging_Materials = Cost_Unit_Fifo_R_Mat_Pack * Total_Quantity;
        }
        else
        {
            Double Cost_Unit_Fifo_Old = Double.valueOf (modeloKob1.getCost_Unit_Fifo_Old ());
            Packaging_Materials = Cost_Unit_Fifo_Old * Total_Quantity;
        }
        modeloKob1.setPackaging_Materials (String.format ("%.5f", Packaging_Materials).replace (",", "."));
        return modeloKob1;
    }

    //--------------------------------------------------------------------------------------
}
