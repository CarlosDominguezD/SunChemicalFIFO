/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexiones.ConexionBDMySql;
import Modelos.ModeloAuditoria;
import Modelos.ModeloUsuario;
import Servlet.ServletSunchemical;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorExcel {

    public String TEMPLATE_PATH = "./reports/templates/";
    public String TEMPORAL_PATH = "./reports/temporal/";
    //public String GENERATED_PATH = "./reports/deploy/";
    public String GENERATED_PATH = "";
    public String EXCEL_SUFFIX = ".xls";

    public static final String DATA_SHEET = "datos";
    private static final String DEFAULT_TEMPLATE_FILE = "plantilla_generica.xls";
    public static Integer DEFAULT_TABLE_REPORTID;

    private boolean hideSheet = true;

    public String GenerarExcel(String UrlArchivo, String newQuery, String Acumulado) throws Exception {
        String genFileName = null;
        try {
            System.out.println("Reporte : " + newQuery);

            System.out.println("Reporte : " + newQuery);
            List<LinkedHashMap<String, Object>> records;
            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            ResultSet rs = con.createStatement().executeQuery(newQuery);
            records = resultSet2ContextList(rs);
            rs.close();
            con.close();

            genFileName = generateReport(UrlArchivo, UrlArchivo + "_" + Acumulado, records);
            System.out.println(genFileName);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return genFileName;
    }

    public String GenerarCSV(String UrlArchivo, String newQuery) throws Exception {
        String genFileName = null;
        String Fecha = new Date().getYear() + "_" + new Date().getMonth() + "_" + new Date().getDay() + "_" + new Date().getHours() + "_" + new Date().getMinutes() + "_" + new Date().getSeconds();
        String NombreArchivo = "C:/Zred/SunChemical/Rpt_Generados/KOB1_" + Fecha + ".CSV";

        //newQuery = "SELECT * FROM kob1 WHERE idarchivo is not null INTO OUTFILE '" + NombreArchivo +"' FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\r\\n';";
        newQuery = "SELECT "
                + "ifNull(Id, ''), "
                + "ifNull(Functional_Area, ''), "
                + "ifNull(Company_Code, ''), "
                + "ifNull(Order_, ''), "
                + "ifNull(CO_object_name, ''), "
                + "ifNull(Cost_Element, ''), "
                + "ifNull(Cost_element_name, ''), "
                + "ifNull(Material, ''), "
                + "ifNull(Material_Description, ''), "
                + "ifNull(Plant, ''), "
                + "ifNull(Period, ''), "
                + "ifNull(Fiscal_Year, ''), "
                + "ifNull(Dr_Cr_indicator, ''), "
                + "ifNull(Total_Quantity, ''), "
                + "ifNull(Unit_of_Measure, ''), "
                + "ifNull(Value_TranCurr, ''), "
                + "ifNull(Transaction_Currency, ''), "
                + "ifNull(Value_in_Obj_Crcy, ''), "
                + "ifNull(Object_Currency, ''), "
                + "ifNull(Document_Number, ''), "
                + "ifNull(Link_Plant_Material, ''), "
                + "ifNull(Link_Material_orden, ''), "
                + "ifNull(Batch_consumo, ''), "
                + "ifNull(Link_Material_Batch, ''), "
                + "ifNull(Material_Type_Components, ''), "
                + "ifNull(Procur_Type, ''), "
                + "ifNull(Level_1, ''), "
                + "ifNull(Finish_Good_sku, ''), "
                + "ifNull(Mat_Type_Unfinish_Goods, ''), "
                + "ifNull(Batch_Finish_goods, ''), "
                + "ifNull(Link_Terminado_Batch, ''), "
                + "ifNull(Cost_Unit_Estandar, ''), "
                + "ifNull(Cantidad_Terminada, ''), "
                + "ifNull(Cost_Unit_Fifo_Old, ''), "
                + "ifNull(Cost_Unit_Fifo_R_Mat_Pack, ''), "
                + "ifNull(x, ''), "
                + "ifNull(Total_Raw_Material, ''), "
                + "ifNull(Manufact_Materials, ''), "
                + "ifNull(Packaging_Materials, ''), "
                + "ifNull(Conversion_Cost, ''), "
                + "ifNull(Nuevo_Valor_Orden, ''), "
                + "ifNull(Month, ''), "
                + "ifNull(IdArchivo, ''), "
                + "ifNull(fecha, ''), "
                + "ifNull(Produccion, ''), "
                + "ifNull(Inventario, '')"
                + " FROM kob1 INTO OUTFILE '" + NombreArchivo + "' FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\\r\\n';";
        try {
            System.out.println("Reporte : " + newQuery);

            ConexionBDMySql conexion = new ConexionBDMySql();
            Connection con;
            con = conexion.abrirConexion();
            ResultSet rs = con.createStatement().executeQuery(newQuery);

            rs.close();
            con.close();

            genFileName = NombreArchivo;
            System.out.println(genFileName);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return genFileName;
    }

    protected void downloadFile(HttpServletResponse response, String filePath)
            throws ServletException, IOException {

        File fileToDownload = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(fileToDownload);

        ServletOutputStream out = response.getOutputStream();
        String mimeType = new MimetypesFileTypeMap().getContentType(filePath);

        response.setContentType(mimeType);
        response.setContentLength(fileInputStream.available());
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + fileToDownload.getName() + "\"");

        int c;
        while ((c = fileInputStream.read()) != -1) {
            out.write(c);
        }
        out.flush();
        out.close();
        fileInputStream.close();
    }

    public LinkedList<LinkedHashMap<String, Object>> resultSet2ContextList(ResultSet rs) throws Exception {
        LinkedList<LinkedHashMap<String, Object>> records = new LinkedList<LinkedHashMap<String, Object>>();
        while (rs.next()) {
            LinkedHashMap<String, Object> record = new LinkedHashMap<String, Object>();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int col = 1; col <= rsmd.getColumnCount(); col++) {
                record.put(rsmd.getColumnLabel(col), rs.getObject(col));
            }
            records.add(record);
        }
        return records;
    }

    private String generateReport(String plantilla, String reportName, List<LinkedHashMap<String, Object>> records) throws Exception {
        String generatedFileName = "";
        String generatedFullFileName = "";

        long started = new Date().getTime();
        File datosXLSFile;

        try {
            Workbook wb;

            byte[] dataFile = getFileData(plantilla);
            ByteArrayInputStream file = new ByteArrayInputStream(dataFile);
            File tempFile = File.createTempFile("reportes", null);
            FileOutputStream fileStreamB = new FileOutputStream(tempFile);

            int line = file.read();

            while (line != -1) {
                fileStreamB.write(line);
                line = file.read();
            }
            fileStreamB.close();
            file.close();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateWithFormat = format.format(started);

            String path = getRealPath(GENERATED_PATH) + "/";
            if (path.startsWith("null")) {
                path = "";
            }

            generatedFullFileName = path + reportName + "_" + dateWithFormat + EXCEL_SUFFIX;
            generatedFileName = GENERATED_PATH + reportName + "_" + dateWithFormat + EXCEL_SUFFIX;

            datosXLSFile = new File(generatedFullFileName);

            if (datosXLSFile.exists()) {
                datosXLSFile = new File(generatedFullFileName);
            }

            try {
                if (EXCEL_SUFFIX.endsWith("x")) {
                    wb = new XSSFWorkbook(new FileInputStream(tempFile));
                } else {
                    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(tempFile));
                    wb = new HSSFWorkbook(fs);
                }
            } catch (IOException e) {
                if (EXCEL_SUFFIX.endsWith("x")) {
                    wb = new XSSFWorkbook();
                } else {
                    wb = new HSSFWorkbook();
                }
            }
            //Se crea la hoja de datos "hoja1"
            createSheet(DATA_SHEET, records, wb);
            FileOutputStream fileOut = new FileOutputStream(datosXLSFile);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            System.err.println("Error al Tratar de Generar el Reporte. Causa: " + e.getMessage());
            throw e;
        }
        System.gc();
        return generatedFileName;
    }

    @SuppressWarnings("deprecation")
    private void createSheet(String sheetName, List<LinkedHashMap<String, Object>> records, Workbook wb) throws Exception {
        try {
            if (records.size() == 0) {
                return;
            }

            //Se crea la hoja con el nombre de hoja almacenado en la BD
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                sheet = wb.createSheet(sheetName);
            } else {
                wb.removeSheetAt(wb.getSheetIndex(sheetName));
                sheet = wb.createSheet(sheetName);
            }

            Cell celltemp;
            Set<String> columnNames = (Set<String>) records.get(0).keySet();
            int colNum = 0;
            Row row = sheet.createRow(colNum);
            for (String columnName : columnNames) {
                celltemp = row.createCell((short) (colNum++));
                celltemp.setCellValue(columnName.toUpperCase());
            }
            int rowCount = 1;
            for (HashMap<String, Object> record : records) {
                row = sheet.createRow(rowCount);
                int cellNum = 0;
                for (String columnName : columnNames) {
                    celltemp = row.createCell((short) (cellNum++));
                    Object var = record.get(columnName);
                    if (var instanceof BigDecimal) {
                        celltemp.setCellValue(((BigDecimal) var).doubleValue());
                    } else {
                        if (var instanceof Calendar) {
                            celltemp.setCellValue((Calendar) var);
                        } else {
                            if (var instanceof Date) {
                                celltemp.setCellValue((Date) var);
                            } else {
                                if (var instanceof Long) {
                                    celltemp.setCellValue((Long) var);
                                } else {
                                    if (var instanceof Integer) {
                                        celltemp.setCellValue((Integer) var);
                                    } else {
                                        if (var instanceof Double) {
                                            celltemp.setCellValue((Double) var);
                                        } else {
                                            //
                                            if (var != null) {
                                                //System.out.println(var);
                                                celltemp.setCellValue((String) var.toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                rowCount++;
            }

            if (hideSheet) {
                wb.setSheetHidden(wb.getSheetIndex(sheetName), true);
            }
        } catch (Exception e) {
            System.err.println("Error al Tratar de Generar el Reporte. Causa: " + e.getMessage());
            throw e;
        }
    }

    public static byte[] getFileData(String fileName) throws Exception {
        File file = new File(fileName);
        byte[] data = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        return data;
    }

    @SuppressWarnings("deprecation")
    public static String getRealPath(String virtualPath) throws Exception {
        HttpServletRequest req = null;
        if (req == null) {
            return "";
        }
        return req.getRealPath(virtualPath);
    }

    public void Select(String Archivo, HttpServletRequest request, HttpServletResponse response) {

        String Acumulado = request.getParameter("Mes") + "_" + request.getParameter("Ano"); 


        String SQLReporte;
        switch (Archivo) {
            case "GenerarArchivoCompras":
                SQLReporte = "SELECT "
                        + "Id,"
                        + "Plant,"
                        + "Purchase_order,"
                        + "Material,"
                        + "Material_Description,"
                        + "Batch,"
                        + "Movement_type,"
                        + "Movement_Type_Text,"
                        + "Item,"
                        + "SUM(Quantity) AS 'Quantity',"
                        + "SUM(Qty_in_unit_of_entry) AS 'Qty_in_unit_of_entry',"
                        + "Unit_of_Entry,"
                        + "SUM(Amt_in_loc_cur) AS 'Amt_in_loc_cur',"
                        + "Currency,"
                        + "Storage_Location,"
                        + "Posting_Date,"
                        + "Document_Date,"
                        + "Material_Document,"
                        + "User_Name,"
                        + "Vendor,"
                        + "Vendor_Name,"
                        + "Vendor_Type,"
                        + "Month,"
                        + "Period,"
                        + "Cost_Unit_SAP_en_KG,"
                        + "Material_Type,"
                        + "Profit_Center,"
                        + "link1_Material_Batch,"
                        + "link2_PO_position,"
                        + "Referencia_vendor,"
                        + "TotalQ_ME80FN,"
                        + "O_Unit_ME80FN,"
                        + "TotalQ_Porcentaje,"
                        + "TOTAL_INVOICE_VALUE,"
                        + "Factura_Value_Unit,"
                        + "PIR_Porcentaje_del_Costo,"
                        + "Precio_Unit_moneda_compra,"
                        + "Moneda,"
                        + "link3_PO_Item,"
                        + "Freight,"
                        + "Dutys,"
                        + "Arancel,"
                        + "Total_Costos_Adicionales,"
                        + "Participac_Adicionales,"
                        + "Adicionales_al_CTO_Estandar,"
                        + "Variance,"
                        + "Total_Costos,"
                        + "Unitario_Real,"
                        + "Unitario_Real_adicional_estandar,"
                        + "Unitario_estandar_SAP,"
                        + "Unitario_final_FIFO,"
                        + "Porcentaje_Real_Vs_Estandar,"
                        + "Porcentaje_fifo_final_vs_Estandar,"
                        + "Compra_valorada_a_Unit_FIFO,"
                        + "Variacion_FIFO_vs_Estandar,"
                        + "NovedadIco,"
                        + "IdArchivo"
                        + " FROM mb51 WHERE IdArchivo = " + request.getParameter("IdPlano") + " group by Purchase_order, Material, Batch order by Batch, Material";
//                SQLReporte = "SELECT "
//                        + "mb51.Id,"
//                        + "mb51.Plant,"
//                        + "mb51.Purchase_order,"
//                        + "mb51.Material,"
//                        + "mb51.Material_Description,"
//                        + "mb51.Batch,"
//                        + "mb51.Movement_type,"
//                        + "mb51.Movement_Type_Text,"
//                        + "mb51.Item,"
//                        + "SUM(mb51.Quantity) AS 'Quantity',"
//                        + "SUM(mb51.Qty_in_unit_of_entry) AS 'Qty_in_unit_of_entry',"
//                        + "mb51.Unit_of_Entry,"
//                        + "SUM(mb51.Amt_in_loc_cur) AS 'Amt_in_loc_cur',"
//                        + "mb51.Currency,"
//                        + "mb51.Storage_Location,"
//                        + "mb51.Posting_Date,"
//                        + "mb51.Document_Date,"
//                        + "mb51.Material_Document,"
//                        + "mb51.User_Name,"
//                        + "mb51.Vendor,"
//                        + "mb51.Vendor_Name,"
//                        + "mb51.Vendor_Type,"
//                        + "mb51.Month,"
//                        + "mb51.Period,"
//                        + "mb51.Cost_Unit_SAP_en_KG,"
//                        + "mb51.Material_Type,"
//                        + "mb51.Profit_Center,"
//                        + "mb51.link1_Material_Batch,"
//                        + "mb51.link2_PO_position,"
//                        + "mb51.Referencia_vendor,"
//                        + "mb51.TotalQ_ME80FN,"
//                        + "mb51.O_Unit_ME80FN,"
//                        + "mb51.TotalQ_Porcentaje,"
//                        + "mb51.TOTAL_INVOICE_VALUE,"
//                        + "mb51.Factura_Value_Unit,"
//                        + "mb51.PIR_Porcentaje_del_Costo,"
//                        + "mb51.Precio_Unit_moneda_compra,"
//                        + "mb51.Moneda,"
//                        + "mb51.link3_PO_Item,"
//                        + "mb51.Freight,"
//                        + "mb51.Dutys,"
//                        + "mb51.Arancel,"
//                        + "mb51.Total_Costos_Adicionales,"
//                        + "mb51.Participac_Adicionales,"
//                        + "mb51.Adicionales_al_CTO_Estandar,"
//                        + "mb51.Variance,"
//                        + "mb51.Total_Costos,"
//                        + "mb51.Unitario_Real,"
//                        + "mb51.Unitario_Real_adicional_estandar,"
//                        + "mb51.Unitario_estandar_SAP,"
//                        + "mb51.Unitario_final_FIFO,"
//                        + "mb51.Porcentaje_Real_Vs_Estandar,"
//                        + "mb51.Porcentaje_fifo_final_vs_Estandar,"
//                        + "mb51.Compra_valorada_a_Unit_FIFO,"
//                        + "mb51.Variacion_FIFO_vs_Estandar,"
//                        + "mb51.NovedadIco, "
//                        //+ "mb51.IdArchivo,"
//                        + "CONCAT(fechas.mes, '-', fechas.ano) AS 'Fecha' "
//                        + "FROM mb51 "
//                        + "LEFT OUTER join estadoplanos on (mb51.IdArchivo = estadoplanos.Id) "
//                        + "LEFT OUTER join fechas on (estadoplanos.IdFechas = fechas.Id) "
//                        + "group by mb51.IdArchivo, mb51.Purchase_order,  mb51.Material, mb51.Batch order by mb51.IdArchivo, mb51.Batch, mb51.Material";

                try {
                    //String UrlArchivo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Informe.xls";//request.getParameter("PlantillaUrl");
                    String UrlArchivo = "C:\\Zred\\SunChemical\\Rpt_Generados\\MacroMB51.xls";//request.getParameter("PlantillaUrl");                
                    //String UrlArchivo = "D:\\Zred\\SunChemical\\MacroMB51.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    String archivo = GenerarExcel(UrlArchivo, newQuery, Acumulado);
                    downloadFile(response, archivo);
                    //Auditoria
                    ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                    modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                    modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a descargado  el plano MB51 del sistema.");
                    ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                    controladorAuditoria.Insert(modeloAuditoria);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "GenerarArchivoProduccion":
                SQLReporte = "SELECT "
                        + "`Id`,"
                        + "`Functional_Area`,"
                        + "`Company_Code`,"
                        + "`Order_`,"
                        + "`CO_object_name`,"
                        + "`Cost_Element`,"
                        + "`Cost_element_name`,"
                        + "`Material`,"
                        + "`Material_Description`,"
                        + "`Plant`,"
                        + "`Period`,"
                        + "`Fiscal_Year`,"
                        + "`Dr_Cr_indicator`,"
                        + "`Total_Quantity`,"
                        + "`Unit_of_Measure`,"
                        + "`Value_TranCurr`,"
                        + "`Transaction_Currency`,"
                        + "`Value_in_Obj_Crcy`,"
                        + "`Object_Currency`,"
                        + "`Document_Number`,"
                        + "`Link_Plant_Material`,"
                        + "`Link_Material_orden`,"
                        + "`Batch_consumo`,"
                        + "`Link_Material_Batch`,"
                        + "`Material_Type_Components`,"
                        + "`Procur_Type`,"
                        + "`Level_1`,"
                        + "`Finish_Good_sku`,"
                        + "`Mat_Type_Unfinish_Goods`,"
                        + "`Batch_Finish_goods`,"
                        + "`Link_Terminado_Batch`,"
                        + "`Cost_Unit_Estandar`,"
                        + "`Cantidad_Terminada`,"
                        + "`Cost_Unit_Fifo_Old`,"
                        + "`Cost_Unit_Fifo_R_Mat_Pack`,"
                        + "`x`,"
                        + "`Total_Raw_Material`,"
                        + "`Manufact_Materials`,"
                        + "`Packaging_Materials`,"
                        + "`Conversion_Cost`,"
                        + "`Nuevo_Valor_Orden`,"
                        + "`Month`,"
                        + "`IdArchivo` "
                        + "FROM `kob1` WHERE IdArchivo = " + request.getParameter("IdPlano");

//                SQLReporte = "SELECT "
//                        + "kob1.Id,"
//                        + "kob1.Functional_Area,"
//                        + "kob1.Company_Code,"
//                        + "kob1.Order_,"
//                        + "kob1.CO_object_name,"
//                        + "kob1.Cost_Element,"
//                        + "kob1.Cost_element_name,"
//                        + "kob1.Material,"
//                        + "kob1.Material_Description,"
//                        + "kob1.Plant,"
//                        + "kob1.Period,"
//                        + "kob1.Fiscal_Year,"
//                        + "kob1.Dr_Cr_indicator,"
//                        + "kob1.Total_Quantity,"
//                        + "kob1.Unit_of_Measure,"
//                        + "kob1.Value_TranCurr,"
//                        + "kob1.Transaction_Currency,"
//                        + "kob1.Value_in_Obj_Crcy,"
//                        + "kob1.Object_Currency,"
//                        + "kob1.Document_Number,"
//                        + "kob1.Link_Plant_Material,"
//                        + "kob1.Link_Material_orden,"
//                        + "kob1.Batch_consumo,"
//                        + "kob1.Link_Material_Batch,"
//                        + "kob1.Material_Type_Components,"
//                        + "kob1.Procur_Type,"
//                        + "kob1.Level_1,"
//                        + "kob1.Finish_Good_sku,"
//                        + "kob1.Mat_Type_Unfinish_Goods,"
//                        + "kob1.Batch_Finish_goods,"
//                        + "kob1.Link_Terminado_Batch,"
//                        + "kob1.Cost_Unit_Estandar,"
//                        + "kob1.Cantidad_Terminada,"
//                        + "kob1.Cost_Unit_Fifo_Old,"
//                        + "kob1.Cost_Unit_Fifo_R_Mat_Pack,"
//                        + "kob1.x,"
//                        + "kob1.Total_Raw_Material,"
//                        + "kob1.Manufact_Materials,"
//                        + "kob1.Packaging_Materials,"
//                        + "kob1.Conversion_Cost,"
//                        + "kob1.Nuevo_Valor_Orden,"
//                        + "kob1.Month,"
//                        //+ "kob1.IdArchivo,"
//                        + "CONCAT(fechas.mes, '-', fechas.ano) AS 'Fecha' "
//                        + "FROM kob1 "
//                        + "LEFT OUTER join estadoplanos on (kob1.IdArchivo = estadoplanos.Id)"
//                        + "LEFT OUTER join fechas on (estadoplanos.IdFechas = fechas.Id)";
//                SQLReporte = "SELECT "
//                        + "kob1.Id,"
//                        + "kob1.Functional_Area,"
//                        + "kob1.Company_Code,"
//                        + "kob1.Order_,"
//                        + "kob1.CO_object_name,"
//                        + "kob1.Cost_Element,"
//                        + "kob1.Cost_element_name,"
//                        + "kob1.Material,"
//                        + "kob1.Material_Description,"
//                        + "kob1.Plant,"
//                        + "kob1.Period,"
//                        + "kob1.Fiscal_Year,"
//                        + "kob1.Dr_Cr_indicator,"
//                        + "kob1.Total_Quantity,"
//                        + "kob1.Unit_of_Measure,"
//                        + "kob1.Value_TranCurr,"
//                        + "kob1.Transaction_Currency,"
//                        + "kob1.Value_in_Obj_Crcy,"
//                        + "kob1.Object_Currency,"
//                        + "kob1.Document_Number,"
//                        + "kob1.Link_Plant_Material,"
//                        + "kob1.Link_Material_orden,"
//                        + "kob1.Batch_consumo,"
//                        + "kob1.Link_Material_Batch,"
//                        + "kob1.Material_Type_Components,"
//                        + "kob1.Procur_Type,"
//                        + "kob1.Level_1,"
//                        + "kob1.Finish_Good_sku,"
//                        + "kob1.Mat_Type_Unfinish_Goods,"
//                        + "kob1.Batch_Finish_goods,"
//                        + "kob1.Link_Terminado_Batch,"
//                        + "kob1.Cost_Unit_Estandar,"
//                        + "kob1.Cantidad_Terminada,"
//                        + "kob1.Cost_Unit_Fifo_Old,"
//                        + "kob1.Cost_Unit_Fifo_R_Mat_Pack,"
//                        + "kob1.x,"
//                        + "kob1.Total_Raw_Material,"
//                        + "kob1.Manufact_Materials,"
//                        + "kob1.Packaging_Materials,"
//                        + "kob1.Conversion_Cost,"
//                        + "kob1.Nuevo_Valor_Orden,"
//                        + "kob1.Month,"
//                        + "kob1.IdArchivo,"
//                        + "CONCAT(fechas.mes, '-', fechas.ano) AS 'Fecha' "
//                        + "FROM kob1 "
//                        + "LEFT OUTER join estadoplanos on (kob1.IdArchivo = estadoplanos.Id)"
//                        + "LEFT OUTER join fechas on (estadoplanos.IdFechas = fechas.Id) "
//                        + "WHERE IdArchivo = " + request.getParameter("IdPlano");
                try {
                    //String UrlArchivo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Informe.xls";//request.getParameter("PlantillaUrl");
                    String UrlArchivo = "C:\\Zred\\SunChemical\\Rpt_Generados\\MacroKOB1.xls";//request.getParameter("PlantillaUrl");                
                    //String UrlArchivo = "D:\\Zred\\SunChemical\\MacroMB51.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    //String archivo = GenerarExcel(UrlArchivo, newQuery);
                    String archivo = GenerarCSV(UrlArchivo, newQuery);
                    downloadFile(response, archivo);
                    //Auditoria
                    ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                    modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                    modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a descargado  el plano MB51 del sistema.");
                    ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                    controladorAuditoria.Insert(modeloAuditoria);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "GenerarArchivoInventario":
//                SQLReporte = "SELECT"
//                        + " id,"
//                        + " Material,"
//                        + " Descripcion,"
//                        + " Plant,"
//                        + " Batch,"
//                        + " Month,"
//                        + " Profit_center,"
//                        + " Material_Type,"
//                        + " Status,"
//                        + " REPLACE(Val_stock, '.', ',') as 'Val Stock',"
//                        + " Val_stock_Med,"
//                        + " REPLACE(ValStckVal, '.', ',') as 'Val Stck Val',"
//                        + " ValStck_Val_Mon,"
//                        + " REPLACE(Cost_Unit_Estandar, '.', ',') as 'Cost Unit Estandar',"
//                        + " REPLACE(InventarioInicial_FIFO, '.', ',') as 'Inventario Inicial FIFO',"
//                        + " REPLACE(Cost_Unit_Purchase, '.', ',') as 'Cost Unit Purchase',"
//                        + " REPLACE(Cost_Unit_KOB1_Final, '.', ',') as 'Cost Unit KOB1',"
//                        + " REPLACE(FIFO_Cost_Unit, '.', ',') as 'FIFO Cost Unit',"
//                        + " REPLACE(Inventario_Valorado_a_FIFO, '.', ',') as 'Inventario Valorado a FIFO',"
//                        + " REPLACE(Variacion_FIFO_vs_Estandar, '.', ',') as 'Variacion FIFO vs Estandar',"
//                        + " IdArchivo"
//                        + " FROM mcbr WHERE IdArchivo = " + request.getParameter("IdPlano");

                SQLReporte = "SELECT"
                        + " id,"
                        + " Material,"
                        + " Descripcion,"
                        + " Plant,"
                        + " Batch,"
                        + " Month,"
                        + " Profit_center,"
                        + " Material_Type,"
                        + " Status,"
                        + " Val_stock as 'Val Stock',"
                        + " Val_stock_Med,"
                        + " ValStckVal as 'Val Stck Val',"
                        + " ValStck_Val_Mon,"
                        + " Cost_Unit_Estandar as 'Cost Unit Estandar',"
                        + " InventarioInicial_FIFO as 'Inventario Inicial FIFO',"
                        + " Cost_Unit_Purchase as 'Cost Unit Purchase',"
                        + " Cost_Unit_KOB1_Final as 'Cost Unit KOB1',"
                        + " FIFO_Cost_Unit as 'FIFO Cost Unit',"
                        + " Inventario_Valorado_a_FIFO as 'Inventario Valorado a FIFO',"
                        + " Variacion_FIFO_vs_Estandar as 'Variacion FIFO vs Estandar',"
                        + " IdArchivo"
                        + " FROM mcbr WHERE IdArchivo = " + request.getParameter("IdPlano");
                
                

                try {
                    //String UrlArchivo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Informe.xls";//request.getParameter("PlantillaUrl");
                    String UrlArchivo = "C:\\Zred\\SunChemical\\Rpt_Generados\\MacroMCBR.xls";//request.getParameter("PlantillaUrl");                
                    //String UrlArchivo = "D:\\Zred\\SunChemical\\MacroMB51.xls";//request.getParameter("PlantillaUrl");                
                    String newQuery = SQLReporte;
                    //ControladorExcel controladorExcel = new ControladorExcel();
                    
                    String archivo = GenerarExcel(UrlArchivo, newQuery, Acumulado);
                    downloadFile(response, archivo);
                    //Auditoria
                    ModeloAuditoria modeloAuditoria = new ModeloAuditoria();
                    modeloAuditoria.setModeloUsuario((ModeloUsuario) request.getSession().getAttribute("user"));
                    modeloAuditoria.setMensaje("El Usuario " + modeloAuditoria.getModeloUsuario().getUsuario() + " a descargado  el plano MB51 del sistema.");
                    ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                    controladorAuditoria.Insert(modeloAuditoria);
                } catch (SQLException ex) {
                    Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }

    }
}
