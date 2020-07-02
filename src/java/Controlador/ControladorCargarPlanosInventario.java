/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Herramienta.Herramienta;
import Modelos.ModeloAuditoria;
import Modelos.ModeloEstadoPlanos;
import Modelos.ModeloInforme_Produccion;
import Modelos.ModeloInventarioInicial;
import Modelos.ModeloMb51;
import Modelos.ModeloMcbr;
import Modelos.ModeloUsuario;
import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ControladorCargarPlanosInventario {

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
            switch (request.getParameter("NombrePlano")) {
                case "InventarioInicial":
                    resultado = CargarCSV_InventarioInicial_INFILE(RutaDispo, request);
                    break;
                case "MCBR":
                    resultado = CargarCSV_MCBR_INFILE(RutaDispo, request);
                    break;
                case "Valuacion":
                    resultado = CargarCSV_Valuacion_INFILE(RutaDispo, request);
                    if ("true".equals(resultado)) {
                        Herramienta herramientas = new Herramienta();
                        ControladorFechas controladorFechas = new ControladorFechas();
                        ModeloEstadoPlanos modeloEstadoPlanos = new ModeloEstadoPlanos();
                        modeloEstadoPlanos.setNombrePlano("MCBR");
                        modeloEstadoPlanos.setFechaCarga(herramientas.sDate());
                        modeloEstadoPlanos.setEstado("Activo");
                        modeloEstadoPlanos.setIdFechas(controladorFechas.getIdFecha(request));
                        ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos();
                        controladorEstadoPlanos.Insert(modeloEstadoPlanos);
                        //Selecionamos el ultimo Id del la tabal estado plano
                        modeloEstadoPlanos.setId(controladorEstadoPlanos.getIdEstadoPlanos());
                        // Update Mb51
                        controladorEstadoPlanos.UpdateMB51(modeloEstadoPlanos, "MCBR");
                    }
                    break;

            }
        } catch (IOException | ServletException e) {
            System.out.println("Error en la carga del plano " + formato + "  " + e);
        }
        return resultado;
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
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE InventarioInicial"
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

    public String CargarCSV_MCBR_INFILE(String Ruta, HttpServletRequest request) throws IOException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MCBR"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Descripcion,Plant,Batch,Month,Profit_center,Material_Type,Status,Val_stock,Val_stock_Med,ValStckVal,ValStck_Val_Mon)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from MCBR WHERE IdArchivo is null";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano MCBR en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

        }

        return Realizado;
    }

    public String CargarCSV_Valuacion_INFILE(String Ruta, HttpServletRequest request) throws IOException, SQLException {
        /*
         * Variables de Año y Mes
         */
        String ano = request.getParameter("Mes");
        String mes = request.getParameter("Ano");
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MCBR"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Descripcion,Plant,Batch,Month,Profit_center,Material_Type,Status,Val_stock,Val_stock_Med,ValStckVal,ValStck_Val_Mon)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        //String Sqlborrar = "delete from MCBR WHERE IdArchivo is null";
        //if (controladorMrpdata.Insert(Sqlborrar)) {
        //Realizado = "true";
        //}
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {

            //Auditoria
            ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
            modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
            modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a cargado el plano MCBR en el sistema.");
            ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
            controladorAuditoria.Insert(modeloAuditoria);

            ControladorMcbr controladorMcbr = new ControladorMcbr();
            LinkedList<ModeloMcbr> Lista_ModeloMcbr = new LinkedList<ModeloMcbr>();
            System.out.println("INICIA CARGA AL LISTADO CON TODOS LOS REGISTROS DE MCBR: " + new Date());
            Lista_ModeloMcbr = controladorMcbr.Select();
            System.out.println("FINALIZA CARGA AL LISTADO CON TODOS LOS REGISTROS DE MCBR: " + new Date());
            LinkedList<ModeloMcbr> Lista_ModeloMcbr_Upd = new LinkedList<ModeloMcbr>();

            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            Informe_Produccion();

            System.out.println("INICIA RECORRIDO DEL LISTADO CON TODOS LOS REGISTROS DE MCBR: " + new Date());
            for (ModeloMcbr modeloMcbr : Lista_ModeloMcbr) {                
                modeloMcbr = LlenarModeloMcbr(modeloMcbr, con);
                Lista_ModeloMcbr_Upd.add(modeloMcbr);
            }
            System.out.println("FINALIZA RECORRIDO DEL LISTADO CON TODOS LOS REGISTROS DE MCBR: " + new Date());
            System.out.println("INICIA ACTUALIZACION DEL LISTADO CON TODOS LOS REGISTROS DE MCBR: " + new Date());
            controladorMcbr.UpdateList(Lista_ModeloMcbr_Upd , con);
            System.out.println("FINALIZA ACTUALIZACION DEL LISTADO CON TODOS LOS REGISTROS DE MCBR: " + new Date());
            con.close();
            
            Realizado = "true";
        }

        return Realizado;
    }
    
    public boolean Informe_Produccion()
    {
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        controladorMrpdata.Insert("Delete from informe_produccion");

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
                    + "from kob1 group by Finish_Good_sku)";

            controladorMrpdata.Insert(SqlInformeProduccionPiso);

            String ActualizacionInformePiso = "update "
                    + "informe_produccion "
                    + "set Costo_unitario_FIFO = (Nuevo_Valor_Orden / Cantidad)";

            return controladorMrpdata.Insert(ActualizacionInformePiso);
    }

    public ModeloMcbr LlenarModeloMcbr(ModeloMcbr modeloMcbr, Connection con) throws SQLException {
        
        
        Double Val_Stck = 0.0;
        if(modeloMcbr.getVal_stock() != null)
        {
            Val_Stck = Double.valueOf(modeloMcbr.getVal_stock());
        }
        Double Val_Stck_Val = 0.0;
        if(modeloMcbr.getValStckVal()!= null)
        {
            Val_Stck_Val = Double.valueOf(modeloMcbr.getValStckVal());
        }
        Double Cost_Unit_Estandar = 0.0;
        if(Val_Stck != 0.0)
        {
            Cost_Unit_Estandar = Val_Stck_Val / Val_Stck;
        }
        //LLENAMOS COLUMNA Cost Unit Estándar
        modeloMcbr.setCost_Unit_Estandar(String.format("%.5f", Cost_Unit_Estandar).replace(",", "."));
        
        LinkedList<ModeloInventarioInicial> Lista_ModeloInventarioInicial = new LinkedList<ModeloInventarioInicial>();
        
        ControladorInventarioInicial controladorInventarioInicial = new ControladorInventarioInicial();
        Lista_ModeloInventarioInicial = controladorInventarioInicial.SelectListSql("SELECT * FROM inventarioinicial WHERE Link_Material_Batch = '" + modeloMcbr.getMaterial() + modeloMcbr.getBatch() + "'", con);
        
        Double InventarioInicial_FIFO = 0.0;
        
        for(ModeloInventarioInicial modeloInventarioInicial : Lista_ModeloInventarioInicial)
        {
            InventarioInicial_FIFO = Double.valueOf(modeloInventarioInicial.getFIFO_Cost_UNIT());
        }
        //LLENAMOS COLUMNA InventarioInicial_FIFO
        modeloMcbr.setInventarioInicial_FIFO(String.format("%.5f", InventarioInicial_FIFO).replace(",", "."));
        
        
        ControladorMb51 controladorMb51 = new ControladorMb51();
        LinkedList<ModeloMb51> Lista_modeloMb51s = new LinkedList<ModeloMb51>();
        
        String Sql1 = "SELECT * FROM mb51 where Material = '" + modeloMcbr.getMaterial() + "' and Batch = '" + modeloMcbr.getBatch() + "'";
        
        Double Cost_Unit_Purchase = 0.0;
        
        
        
        Lista_modeloMb51s = controladorMb51.SelectSql(Sql1 , con);
        if (Lista_modeloMb51s.size() > 0) {
            for (ModeloMb51 modeloMb51 : Lista_modeloMb51s) {
                Cost_Unit_Purchase = Double.valueOf(modeloMb51.getUnitario_final_FIFO());
            }
        
        } 
        //LLENAMOS LA Cost_Unit_Purchase
        modeloMcbr.setCost_Unit_Purchase(String.format("%.5f", Cost_Unit_Purchase).replace(",", "."));
        
        
        //Double Cost_Unit_KOB1_Piso = 0.0;
        
        
        
        //LLENAMOS COLUMNA Cost_Unit_KOB1_Piso
        //modeloMcbr.setCost_Unit_KOB1_Piso(String.format("%.5f", Cost_Unit_KOB1_Piso).replace(",", "."));        
        
        
        Double Cost_Unit_KOB1_Final = 0.0;
        
        modeloMcbr = Produccion(modeloMcbr, con);
        
        //LLENAMOS COLUMNA Cost_Unit_KOB1_Final
        modeloMcbr.setCost_Unit_KOB1_Final(String.format("%.5f", Cost_Unit_KOB1_Final).replace(",", "."));        
        
        
        Double FIFO_Cost_Unit = InventarioInicial_FIFO + Cost_Unit_Purchase + Cost_Unit_KOB1_Final;
        
        //LLENAMOS COLUMNA FIFO Cost Unit
        modeloMcbr.setFIFO_Cost_Unit(String.format("%.5f", FIFO_Cost_Unit).replace(",", "."));  
        
        Double Inventario_Valorado_a_FIFO = FIFO_Cost_Unit * Val_Stck;
        
        //LLENAMOS COLUMNA Inventario_Valorado_a_FIFO
        modeloMcbr.setInventario_Valorado_a_FIFO(String.format("%.5f", Inventario_Valorado_a_FIFO).replace(",", "."));  
        
        Double ValStckVal = Double.valueOf(modeloMcbr.getValStckVal());
                
        Double Variacion_FIFO_vs_Estandar = Inventario_Valorado_a_FIFO - ValStckVal;
        
        //LLENAMOS LA COLUMNA Variacion_FIFO_vs_Estandar
        modeloMcbr.setVariacion_FIFO_vs_Estandar(String.format("%.5f", Variacion_FIFO_vs_Estandar).replace(",", "."));  
        
        
        
        
        return modeloMcbr;
    }
    
    public ModeloMcbr Produccion(ModeloMcbr modeloMcbr, Connection con) throws SQLException {

        ControladorInformeProduccion controladorInformeProduccion = new ControladorInformeProduccion();
        String Sql = "SELECT * FROM informe_produccion where Link_Terminado_Batch = '" + modeloMcbr.getMaterial() + modeloMcbr.getBatch() + "'";
        LinkedList<ModeloInforme_Produccion> LstModeloInforme_Produccion = controladorInformeProduccion.SelectSql(Sql, con);

        if (LstModeloInforme_Produccion.size() > 0) {
            for (ModeloInforme_Produccion modeloInforme_Produccion : LstModeloInforme_Produccion) {
                Double Cantidad = 0.0;
                Double Total_Raw_Material = 0.0;
                Double Manufact = 0.0;
                Double Packaging = 0.0;
                Double Conversion = 0.0;
                Double Nuevo_Valor_Orden = 0.0;
                
                                
                if (modeloInforme_Produccion.getCantidad()!= null) {
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
                    modeloMcbr.setCost_Unit_KOB1_Final(String.format("%.5f", Costo_unitario_FIFO).replace(",", "."));
                //} else {
                //    modeloKob1.setCost_Unit_Fifo_R_Mat_Pack("0.0");
                //}

            }
        } else {
            modeloMcbr.setCost_Unit_KOB1_Final("0.0");
        }
        return modeloMcbr;
    }

}
