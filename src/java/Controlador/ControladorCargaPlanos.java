/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.ModeloFbl3m;
import Modelos.ModeloMb51;
import Modelos.ModeloMe80fn;
import Modelos.ModeloMrpData;
import Modelos.ModeloProveedor;
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
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
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

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCargaPlanos {

    public String Upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                case "MB51":
                    resultado = CargarCSV_MB51_INFILE(RutaDispo);
                    break;
                case "FBL3N":
                    resultado = CargarCSV_FBL3N_INFILE(RutaDispo);
                    break;
                case "ME80FN":
                    resultado = CargarCSV_ME80FN_INFILE(RutaDispo);
                    break;
                case "MRPDATA":
                    resultado = CargarCSV_MRPDATA_INFILE(RutaDispo);
                    break;
            }
        } catch (IOException | ServletException e) {
            System.out.println("Error en la carga del plano " + formato + "  " + e);
        }
        return resultado;
    }

    public String CargarCSV_MB51_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order1)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            ControladorMb51 controladorMb51 = new ControladorMb51();
            LinkedList<ModeloMb51> LinkModeloMb51s;
            LinkModeloMb51s = controladorMb51.Select();
            for (ModeloMb51 modeloMb51 : LinkModeloMb51s) {
                FinalComprasMB51_CargaCSV(modeloMb51);
            }

            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_FBL3N_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE FBL3M"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Document_Number,Document_type,Document_Date,Posting_Date,Cost_Center,Profit_Center,YearMonth,Account,Plant,Material,Quantity,Amount_in_local_currency,Local_Currency,Purchasing_Document,Reference,Document_currency,Offsetting_acct_no,Base_Unit_of_Measure,Alternative_Account_No,Transaction_Code,Text,Assignment)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_ME80FN_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE ME80FN"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Index2,Purchasing_Document,Material_Doc_Year,Material_Document,Document_Date,Material,Short_Text,Batch,Item,Movement_type,Posting_Date,Delivery_Completed,Plant,Quantity,Amt_in_loc_cur,Amount,Currency,Valuation_Type,Entry_Date,Local_currency,Reference_Doc_Item,Invoice_Value,Invoice_Value_in_FC)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_MRPDATA_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MRPDATA"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Material,Material_Description,Profit_Center,Material_Type,Plant,Procurement_type,Base_Unit_of_Measure_1,Plant_sp_matl_status,Overhead_Group,Special_procurement)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();
        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public void FinalComprasMB51(ModeloMb51 modeloMb51) {
        //List<ModeloMb51> listModeloMb51 = new ArrayList<ModeloMb51>();
        //LinkedList<ModeloMb51> listModeloMb51 = null;

        //for (ModeloMb51 modeloMb51 : listModeloMb51) {
        //BUSCAMOS EL PROVEEDOR
        ControladorProveedor controladorProveedores = new ControladorProveedor();
        ModeloProveedor modeloProveedor = controladorProveedores.SelectSQL("SELECT Id, Vendors, Name, Vendor_Type FROM proveedor WHERE Vendors = '" + modeloMb51.getVendor() + "'");
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

        //BUSCAMOS EL MATERIAL
        ControladorMrpdata controladorMrpData = new ControladorMrpdata();
        ModeloMrpData modeloMrpData = controladorMrpData.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
        //LLENAMOS COLUMNA Y
        modeloMb51.setMaterial_Type(modeloMrpData.getMaterial_Type());
        //LLENAMOS COLUMNA Z
        modeloMb51.setProfit_Center(modeloMrpData.getProfit_Center());
        //LLENAMOS COLUMNA AA
        modeloMb51.setLink1_PO_Mas_Material(modeloMb51.getPurchase_order() + modeloMb51.getMaterial());
        //LLENAMOS COLUMNA AB
        modeloMb51.setLink2_PO_Mas_position(modeloMb51.getPurchase_order() + modeloMb51.getItem());
        //LLENAMOS COLUMNA AC
        modeloMb51.setReferencia_Y_vendor(modeloMb51.getMaterial() + modeloMb51.getVendor());

        //BUSCAMOS EL TotalQ_ME80FN
        ControladorMe80fn controladorMe80fn = new ControladorMe80fn();
        LinkedList<ModeloMe80fn> LstmodeloMe80fn = controladorMe80fn.ListSelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double Me80fnQuantity = 0.0;

        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            Me80fnQuantity = Me80fnQuantity + Double.valueOf((modeloMe80fn.getQuantity().replace(".", "")).replace(",", "."));
        }
        //LLENAMOS COLUMNA AD
        modeloMb51.setTotalQ_ME80FN(Me80fnQuantity + "");
        //CALCULAMOS TotalQ_Porcentaje   
        Double Qty_in_unit_of_entry = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
        Double TotalQ_ME80FN = Double.valueOf(modeloMb51.getTotalQ_ME80FN());
        Double TotalQ_Porcentaje = null;
        try {
            if (TotalQ_ME80FN == 0.0) {
                TotalQ_Porcentaje = 0.0;
            } else {
                TotalQ_Porcentaje = Qty_in_unit_of_entry / TotalQ_ME80FN;
            }

        } catch (Exception e) {
            TotalQ_Porcentaje = 0.0;
        }
        //LLENAMOS COLUMNA AE
        modeloMb51.setTotalQ_Porcentaje(TotalQ_Porcentaje + "");

        //BUSCAMOS TOTAL_INVOICE_VALUE
        //modeloMe80fn = controladorMe80fn.SelectSQL("SELECT *, SUM(Amt_in_loc_cur) AS 'SUM_Amt_in_loc_cur' FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double TOTAL_INVOICE_VALUE = 0.0;
        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE + Double.valueOf((modeloMe80fn.getAmt_in_loc_cur().replace(".", "")).replace(",", "."));
        }
        //LLENAMOS COLUMNA AF
        String TOTAL_INVOICE_VALUE_String = String.format("%.5f", TOTAL_INVOICE_VALUE);
        modeloMb51.setTOTAL_INVOICE_VALUE((TOTAL_INVOICE_VALUE_String.replace(".", "")).replace(",", "."));

        Double Factura_Value_Unit = TOTAL_INVOICE_VALUE / Qty_in_unit_of_entry * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AG
        modeloMb51.setFactura_Value_Unit(Factura_Value_Unit + "");

        Double Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        Double PIR = Factura_Value_Unit / Unitario_estandar;
        //LLENAMOS COLUMNA AH
        modeloMb51.setPIR_Porcentaje(PIR + "");

        //BUSCAMOS MONEDA
        ModeloMe80fn modeloMe80fn = controladorMe80fn.SelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Movement_type <> ''");
        //LLENAMOS COLUMNA AI
        modeloMb51.setMoneda(modeloMe80fn.getCurrency());

        //BUSCAMOS Freight
        ControladorFbl3m controladorFbl3m = new ControladorFbl3m();
        LinkedList<ModeloFbl3m> ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no LIKE '3%')");

        Double Freight = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Freight = Freight + Double.valueOf((modeloFbl3m.getAmount_in_local_currency().replace(".", "")).replace(",", "."));
            }
        }
        Freight = Freight * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AJ
        modeloMb51.setFreightString(Freight + "");

        //BUSCAMOS DUTYS
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20014'");
        Double Dutys = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Dutys = Dutys + Double.valueOf((modeloFbl3m.getAmount_in_local_currency().replace(".", "")).replace(",", "."));
            }
        }
        Dutys = Dutys * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AK
        modeloMb51.setDutys(Dutys + "");

        // BUSCAMOS ARANCEL
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20319'");
        Double Arancel = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Arancel = Arancel + Double.valueOf((modeloFbl3m.getAmount_in_local_currency().replace(".", "")).replace(",", "."));
            }
        }
        Arancel = Arancel * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AL
        modeloMb51.setArancel(Arancel + "");

        //buscamos Ajuste PIR
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20010'");
        Double Ajuste_PIR = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Ajuste_PIR = Ajuste_PIR + Double.valueOf((modeloFbl3m.getAmount_in_local_currency().replace(".", "")).replace(",", "."));
            }
        }
        //LLENAMOS COLUMNA AM            
        Ajuste_PIR = Ajuste_PIR * TotalQ_Porcentaje;
        modeloMb51.setAjuste_PIR(Ajuste_PIR + "");

        //BUSCAMOS OTROS
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '10500'");
        Double Otros = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Otros = Otros + Double.valueOf((modeloFbl3m.getAmount_in_local_currency().replace(".", "")).replace(",", "."));
            }
        }
        Otros = Otros * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AN
        modeloMb51.setOtros(Otros + "");

        Double Total_Costos_Adicionales = Freight + Dutys + Arancel + Ajuste_PIR + Otros;
        String Total_Costos_Adicionales_String = String.format("%.5f", Total_Costos_Adicionales);
        //LLENAMOS COLUMNA AO
        modeloMb51.setTotal_Costos_Adicionales((Total_Costos_Adicionales_String.replace(".", "")).replace(",", "."));

        Double Participac_Adicionales = Total_Costos_Adicionales / TOTAL_INVOICE_VALUE;
        //LLENAMOS COLUMNA AP
        modeloMb51.setParticipac_Adicionales(Participac_Adicionales + "");

        Double Total_Costos = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;
        String Total_Costos_String = String.format("%.5f", Total_Costos);
        //LLENAMOS COLUMNA AQ
        modeloMb51.setTotal_Costos((Total_Costos_String.replace(".", "")).replace(",", "."));

        Double Unitario_final_FIFO = Total_Costos / Double.valueOf(modeloMb51.getQuantity());
        //LLENAMOS COLUMNA AR
        modeloMb51.setUnitario_final_FIFO(Unitario_final_FIFO + "");

        //Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        //LLENAMOS COLUMNA AS
        modeloMb51.setUnitario_estandar(Unitario_estandar + "");

        Double Real_Vs_Estándar = (Unitario_final_FIFO / Unitario_estandar) - 1;
        modeloMb51.setPorcentaje_Real_Vs_Estándar(Real_Vs_Estándar + "");

        ControladorMb51 controladorMb51 = new ControladorMb51();
        controladorMb51.Insert(modeloMb51);

        //}
    }

    public String CargarCSV_Compras_MB51(String Ruta) throws IOException {

        String Realizado = "false";
        CSVReader csvReader = null;
        try {
            System.out.println(Ruta);
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(Ruta), "UTF-8"));
            String[] fila = null;
            System.out.println(new Date());
            //AQUI VUELVO A RECORRER EL ARCHIVO, 
            ModeloMb51 modeloMb51 = null;
            Integer Row = 0;
            while ((fila = csvReader.readNext()) != null) {
                modeloMb51 = new ModeloMb51();
                modeloMb51.setPlant(fila[0]);
                modeloMb51.setPurchase_order(fila[1]);
                modeloMb51.setMaterial(fila[2]);
                modeloMb51.setMaterial_Description(fila[3]);
                modeloMb51.setBatch(fila[4]);
                modeloMb51.setMovement_type(fila[5]);
                modeloMb51.setMovement_Type_Text(fila[6]);
                modeloMb51.setItem(fila[7]);
                modeloMb51.setQuantity(fila[8].replace(",", ""));
                modeloMb51.setQty_in_unit_of_entry(fila[9].replace(",", ""));
                modeloMb51.setUnit_of_Entry(fila[10]);
                modeloMb51.setAmt_in_loc_cur(fila[11].replace(",", ""));
                modeloMb51.setCurrency(fila[12]);
                modeloMb51.setStorage_Location(fila[13]);
                modeloMb51.setPosting_Date(fila[14]);
                modeloMb51.setDocument_Date(fila[15]);
                modeloMb51.setMaterial_Document(fila[16]);
                modeloMb51.setUser_Name(fila[17]);
                modeloMb51.setVendor(fila[18]);
                modeloMb51.setOrder(fila[19]);
                if (Row != 0) {
                    FinalComprasMB51_CargaCSV(modeloMb51);
                }
                Row = 1;

                modeloMb51 = null;
            }
            System.out.println(new Date());
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error en la carga del plano FBl3M " + e);
            csvReader.close();
        } catch (IOException e) {
            System.out.println("Error en la carga del plano FBl3M " + e);
            csvReader.close();
        }
        return Realizado;
    }

    public void FinalComprasMB51_CargaCSV(ModeloMb51 modeloMb51) {
        //List<ModeloMb51> listModeloMb51 = new ArrayList<ModeloMb51>();
        //LinkedList<ModeloMb51> listModeloMb51 = null;

        //for (ModeloMb51 modeloMb51 : listModeloMb51) {
        //BUSCAMOS EL PROVEEDOR
        ControladorProveedor controladorProveedores = new ControladorProveedor();
        ModeloProveedor modeloProveedor = controladorProveedores.SelectSQL("SELECT Id, Vendors, Name, Vendor_Type FROM proveedor WHERE Vendors = '" + modeloMb51.getVendor() + "'");
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

        //BUSCAMOS EL MATERIAL
        ControladorMrpdata controladorMrpData = new ControladorMrpdata();
        ModeloMrpData modeloMrpData = controladorMrpData.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
        //LLENAMOS COLUMNA Y
        modeloMb51.setMaterial_Type(modeloMrpData.getMaterial_Type());
        //LLENAMOS COLUMNA Z
        modeloMb51.setProfit_Center(modeloMrpData.getProfit_Center());
        //LLENAMOS COLUMNA AA
        modeloMb51.setLink1_PO_Mas_Material(modeloMb51.getPurchase_order() + modeloMb51.getMaterial());
        //LLENAMOS COLUMNA AB
        modeloMb51.setLink2_PO_Mas_position(modeloMb51.getPurchase_order() + modeloMb51.getItem());
        //LLENAMOS COLUMNA AC
        modeloMb51.setReferencia_Y_vendor(modeloMb51.getMaterial() + modeloMb51.getVendor());

        //BUSCAMOS EL TotalQ_ME80FN
        ControladorMe80fn controladorMe80fn = new ControladorMe80fn();
        LinkedList<ModeloMe80fn> LstmodeloMe80fn = controladorMe80fn.ListSelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double Me80fnQuantity = 0.0;

        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            Me80fnQuantity = Me80fnQuantity + Double.valueOf(modeloMe80fn.getQuantity());
        }
        //LLENAMOS COLUMNA AD
        modeloMb51.setTotalQ_ME80FN(Me80fnQuantity + "");
        //CALCULAMOS TotalQ_Porcentaje   
        Double Qty_in_unit_of_entry = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
        Double TotalQ_ME80FN = Double.valueOf(modeloMb51.getTotalQ_ME80FN());
        Double TotalQ_Porcentaje = null;
        try {
            if (TotalQ_ME80FN == 0.0) {
                TotalQ_Porcentaje = 0.0;
            } else {
                TotalQ_Porcentaje = Qty_in_unit_of_entry / TotalQ_ME80FN;
            }

        } catch (Exception e) {
            TotalQ_Porcentaje = 0.0;
        }
        //LLENAMOS COLUMNA AE
        modeloMb51.setTotalQ_Porcentaje(String.format("%.5f", TotalQ_Porcentaje));

        //BUSCAMOS TOTAL_INVOICE_VALUE
        //modeloMe80fn = controladorMe80fn.SelectSQL("SELECT *, SUM(Amt_in_loc_cur) AS 'SUM_Amt_in_loc_cur' FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double TOTAL_INVOICE_VALUE = 0.0;
        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE + Double.valueOf(modeloMe80fn.getAmt_in_loc_cur());
        }
        //LLENAMOS COLUMNA AF

        modeloMb51.setTOTAL_INVOICE_VALUE(String.format("%.5f", TOTAL_INVOICE_VALUE));

        Double Factura_Value_Unit = TOTAL_INVOICE_VALUE / Qty_in_unit_of_entry * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AG
        modeloMb51.setFactura_Value_Unit(String.format("%.5f", Factura_Value_Unit));

        Double Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        Double PIR = Factura_Value_Unit / Unitario_estandar;
        //LLENAMOS COLUMNA AH
        modeloMb51.setPIR_Porcentaje(String.format("%.5f", PIR));

        //BUSCAMOS MONEDA
        ModeloMe80fn modeloMe80fn = controladorMe80fn.SelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Movement_type <> ''");
        //LLENAMOS COLUMNA AI
        modeloMb51.setMoneda(modeloMe80fn.getCurrency());

        //BUSCAMOS Freight
        ControladorFbl3m controladorFbl3m = new ControladorFbl3m();
        LinkedList<ModeloFbl3m> ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no LIKE '3%')");

        Double Freight = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Freight = Freight + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Freight = Freight * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AJ
        modeloMb51.setFreightString(String.format("%.5f", Freight));

        //BUSCAMOS DUTYS
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20014'");
        Double Dutys = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Dutys = Dutys + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Dutys = Dutys * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AK
        modeloMb51.setDutys(String.format("%.5f", Dutys));

        // BUSCAMOS ARANCEL
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20319'");
        Double Arancel = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Arancel = Arancel + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Arancel = Arancel * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AL
        modeloMb51.setArancel(String.format("%.5f", Arancel));

        //buscamos Ajuste PIR
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20010'");
        Double Ajuste_PIR = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Ajuste_PIR = Ajuste_PIR + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        //LLENAMOS COLUMNA AM            
        Ajuste_PIR = Ajuste_PIR * TotalQ_Porcentaje;
        modeloMb51.setAjuste_PIR(String.format("%.5f", Ajuste_PIR));

        //BUSCAMOS OTROS
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '10500'");
        Double Otros = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Otros = Otros + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Otros = Otros * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AN
        modeloMb51.setOtros(String.format("%.5f", Otros));

        Double Total_Costos_Adicionales = Freight + Dutys + Arancel + Ajuste_PIR + Otros;
        //LLENAMOS COLUMNA AO
        modeloMb51.setTotal_Costos_Adicionales(String.format("%.5f", Total_Costos_Adicionales));

        Double Participac_Adicionales = Total_Costos_Adicionales / TOTAL_INVOICE_VALUE;
        //LLENAMOS COLUMNA AP
        modeloMb51.setParticipac_Adicionales(String.format("%.5f", Participac_Adicionales));

        Double Total_Costos = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AQ
        modeloMb51.setTotal_Costos(String.format("%.5f", Total_Costos));

        Double Unitario_final_FIFO = Total_Costos / Double.valueOf(modeloMb51.getQuantity());
        //LLENAMOS COLUMNA AR
        modeloMb51.setUnitario_final_FIFO(String.format("%.5f", Unitario_final_FIFO));

        //Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        //LLENAMOS COLUMNA AS
        modeloMb51.setUnitario_estandar(String.format("%.5f", Unitario_estandar));

        Double Real_Vs_Estándar = (Unitario_final_FIFO / Unitario_estandar) - 1;
        modeloMb51.setPorcentaje_Real_Vs_Estándar(String.format("%.5f", Real_Vs_Estándar));

        ControladorMb51 controladorMb51 = new ControladorMb51();
        controladorMb51.Insert(modeloMb51);

        //}
    }

    public void FinalComprasMB51_CargaCSV20(ModeloMb51 modeloMb51) {

        //BUSCAMOS EL PROVEEDOR
        //ControladorProveedor controladorProveedores = new ControladorProveedor();
        //ModeloProveedor modeloProveedor = controladorProveedores.SelectSQL("SELECT Id, Vendors, Name, Vendor_Type FROM proveedor WHERE Vendors = '" + modeloMb51.getVendor() + "'");
        //LLENAMOS COLUMNA U
        //modeloMb51.setVendor_Name(modeloProveedor.getName());
        //LLENAMOS COLUMNA V
        //modeloMb51.setVendor_Type(modeloProveedor.getVendor_Type());
        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        //String Month = modeloMb51.getPosting_Date().split("/")[1];
        //LLENAMOS COLUMNA WUPDATE mb51 left OUTER JOIN proveedor ON (mb51.Vendor = proveedor.Vendors) SET mb51.Vendor_Name = proveedor.Name, mb51.Vendor_Type = proveedor.Vendor_Type
        //modeloMb51.setMonth(Month);
        //String Period = modeloMb51.getPosting_Date().split("/")[2];
        //LLENAMOS COLUMNA X
        //modeloMb51.setPeriod(Period);
        //BUSCAMOS EL MATERIAL
        //ControladorMrpdata controladorMrpData = new ControladorMrpdata();
        //ModeloMrpData modeloMrpData = controladorMrpData.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
        //LLENAMOS COLUMNA Y
        //modeloMb51.setMaterial_Type(modeloMrpData.getMaterial_Type());
        //LLENAMOS COLUMNA Z
        //modeloMb51.setProfit_Center(modeloMrpData.getProfit_Center());
        //LLENAMOS COLUMNA AA
        //modeloMb51.setLink1_PO_Mas_Material(modeloMb51.getPurchase_order() + modeloMb51.getMaterial());
        //LLENAMOS COLUMNA AB
        //modeloMb51.setLink2_PO_Mas_position(modeloMb51.getPurchase_order() + modeloMb51.getItem());
        //LLENAMOS COLUMNA AC
        //modeloMb51.setReferencia_Y_vendor(modeloMb51.getMaterial() + modeloMb51.getVendor());        
        String SqlUpdateMasivo = "UPDATE mb51 left "
                + "OUTER JOIN proveedor ON (mb51.Vendor = proveedor.Vendors) "
                + "SET mb51.Vendor_Name = proveedor.Name, "
                + "mb51.Vendor_Type = proveedor.Vendor_Type, "
                + "month = SUBSTRING_INDEX(SUBSTRING_INDEX(Posting_Date, '/', 2), '/', -1), "
                + "Period = SUBSTRING_INDEX(SUBSTRING_INDEX(Posting_Date, '/', 3), '/', -1)";

        controladorMrpdata.Insert(SqlUpdateMasivo);

        //BUSCAMOS EL TotalQ_ME80FN
        ControladorMe80fn controladorMe80fn = new ControladorMe80fn();
        LinkedList<ModeloMe80fn> LstmodeloMe80fn = controladorMe80fn.ListSelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double Me80fnQuantity = 0.0;

        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            Me80fnQuantity = Me80fnQuantity + Double.valueOf(modeloMe80fn.getQuantity());
        }
        //LLENAMOS COLUMNA AD
        modeloMb51.setTotalQ_ME80FN(Me80fnQuantity + "");
        //CALCULAMOS TotalQ_Porcentaje   
        Double Qty_in_unit_of_entry = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
        Double TotalQ_ME80FN = Double.valueOf(modeloMb51.getTotalQ_ME80FN());
        Double TotalQ_Porcentaje = null;
        try {
            if (TotalQ_ME80FN == 0.0) {
                TotalQ_Porcentaje = 0.0;
            } else {
                TotalQ_Porcentaje = Qty_in_unit_of_entry / TotalQ_ME80FN;
            }

        } catch (Exception e) {
            TotalQ_Porcentaje = 0.0;
        }
        //LLENAMOS COLUMNA AE
        modeloMb51.setTotalQ_Porcentaje(TotalQ_Porcentaje + "");

        //BUSCAMOS TOTAL_INVOICE_VALUE
        //modeloMe80fn = controladorMe80fn.SelectSQL("SELECT *, SUM(Amt_in_loc_cur) AS 'SUM_Amt_in_loc_cur' FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double TOTAL_INVOICE_VALUE = 0.0;
        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE + Double.valueOf(modeloMe80fn.getAmt_in_loc_cur());
        }
        //LLENAMOS COLUMNA AF

        modeloMb51.setTOTAL_INVOICE_VALUE(TOTAL_INVOICE_VALUE + "");

        Double Factura_Value_Unit = TOTAL_INVOICE_VALUE / Qty_in_unit_of_entry * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AG
        modeloMb51.setFactura_Value_Unit(Factura_Value_Unit + "");

        Double Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        Double PIR = Factura_Value_Unit / Unitario_estandar;
        //LLENAMOS COLUMNA AH
        modeloMb51.setPIR_Porcentaje(PIR + "");

        //BUSCAMOS MONEDA
        ModeloMe80fn modeloMe80fn = controladorMe80fn.SelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Movement_type <> ''");
        //LLENAMOS COLUMNA AI
        modeloMb51.setMoneda(modeloMe80fn.getCurrency());

        //BUSCAMOS Freight
        ControladorFbl3m controladorFbl3m = new ControladorFbl3m();
        LinkedList<ModeloFbl3m> ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no LIKE '3%')");

        Double Freight = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Freight = Freight + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Freight = Freight * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AJ
        modeloMb51.setFreightString(Freight + "");

        //BUSCAMOS DUTYS
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20014'");
        Double Dutys = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Dutys = Dutys + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Dutys = Dutys * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AK
        modeloMb51.setDutys(Dutys + "");

        // BUSCAMOS ARANCEL
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20319'");
        Double Arancel = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Arancel = Arancel + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Arancel = Arancel * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AL
        modeloMb51.setArancel(Arancel + "");

        //buscamos Ajuste PIR
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '20010'");
        Double Ajuste_PIR = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Ajuste_PIR = Ajuste_PIR + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        //LLENAMOS COLUMNA AM            
        Ajuste_PIR = Ajuste_PIR * TotalQ_Porcentaje;
        modeloMb51.setAjuste_PIR(Ajuste_PIR + "");

        //BUSCAMOS OTROS
        ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Account = '50320' AND Offsetting_acct_no = '10500'");
        Double Otros = 0.0;
        for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
            if (modeloFbl3m.getAmount_in_local_currency() != null) {
                Otros = Otros + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
            }
        }
        Otros = Otros * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AN
        modeloMb51.setOtros(Otros + "");

        Double Total_Costos_Adicionales = Freight + Dutys + Arancel + Ajuste_PIR + Otros;
        //LLENAMOS COLUMNA AO
        modeloMb51.setTotal_Costos_Adicionales(Total_Costos_Adicionales + "");

        Double Participac_Adicionales = Total_Costos_Adicionales / TOTAL_INVOICE_VALUE;
        //LLENAMOS COLUMNA AP
        modeloMb51.setParticipac_Adicionales(Participac_Adicionales + "");

        Double Total_Costos = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;
        //LLENAMOS COLUMNA AQ
        modeloMb51.setTotal_Costos(Total_Costos + "");

        Double Unitario_final_FIFO = Total_Costos / Double.valueOf(modeloMb51.getQuantity());
        //LLENAMOS COLUMNA AR
        modeloMb51.setUnitario_final_FIFO(Unitario_final_FIFO + "");

        //Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        //LLENAMOS COLUMNA AS
        modeloMb51.setUnitario_estandar(Unitario_estandar + "");

        Double Real_Vs_Estándar = (Unitario_final_FIFO / Unitario_estandar) - 1;
        modeloMb51.setPorcentaje_Real_Vs_Estándar(Real_Vs_Estándar + "");

        ControladorMb51 controladorMb51 = new ControladorMb51();
        controladorMb51.Insert(modeloMb51);

        //}
    }
}
