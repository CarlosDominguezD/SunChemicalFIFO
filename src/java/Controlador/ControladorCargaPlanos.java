/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.ModeloEine;
import Modelos.ModeloFbl3m;
import Modelos.ModeloMb51;
import Modelos.ModeloMe80fn;
import Modelos.ModeloMrpData;
import Modelos.ModeloProveedor;
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
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
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
                case "PROVEEDORES":
                    resultado = CargarCSV_PROVEEDORES_INFILE(RutaDispo);
                    break;
                case "EINE":
                    resultado = CargarCSV_EINE_INFILE(RutaDispo);
                    break;
                case "Z39":
                    resultado = CargarCSV_Z39_INFILE(RutaDispo);
                    break;
            }
        } catch (IOException | ServletException e) {
            System.out.println("Error en la carga del plano " + formato + "  " + e);
        }
        return resultado;
    }

    public String CargarCSV_MB51_INFILE(String Ruta) throws IOException, SQLException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE MB51"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from Mb51";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";

        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            ControladorMb51 controladorMb51 = new ControladorMb51();
            LinkedList<ModeloMb51> LinkModeloMb51s;
            LinkModeloMb51s = controladorMb51.Select();
            LinkedList<ModeloMb51> LinkModeloMb51 = new LinkedList<ModeloMb51>();
            Integer Contador = 0;
            System.out.println("Inicio:" + new Date());
            for (ModeloMb51 modeloMb51 : LinkModeloMb51s) {
                //BUSCAMOS EN Z39 SI HAY ALGUN CAMBIO DE BATCH
                CambiosBatch(modeloMb51);
                //System.out.println("Registros Procesados: " + Contador++);
            }
            Contador = 0;
            for (ModeloMb51 modeloMb51 : LinkModeloMb51_New) {
                LinkModeloMb51.add(FinalComprasMB51_CargaCSV(modeloMb51));
                //System.out.println("Registros Procesados: " + Contador++);

            }

            System.out.println("Termina:" + new Date());

            Sqlborrar = "delete from Mb51";

            if (controladorMrpdata.Insert(Sqlborrar)) {
                //Realizado = "true";
            }
            controladorMb51.InsertList(LinkModeloMb51);

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

        String Sqlborrar = "delete from FBL3M";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

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
                + " (Purchasing_Document,Material_Doc_Year,Material_Document,Document_Date,Material,Short_Text,Batch,Item,Movement_type,Posting_Date,Delivery_Completed,Plant,Quantity,Order_Unit,Amt_in_loc_cur,Amount,Currency,Valuation_Type,Entry_Date,Local_currency,Reference_Doc_Item,Invoice_Value,Invoice_Value_in_FC)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from ME80FN";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

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

        String Sqlborrar = "delete from MRPDATA";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_PROVEEDORES_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE PROVEEDOR"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Vendors, Name, Vendor_Type, Moneda)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from PROVEEDOR";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_EINE_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE EINE"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Purchasing_info_rec,Plant,Created_On,Purchasing_Group,Currency,Standard_PO_Quantity,Planned_Deliv_Time,Net_Price,Price_unit,Order_Price_Unit,Valid_to,Quantity_Conversion_1,Quantity_Conversion_2,Effective_Price,Acknowledgment_Reqd,Confirmation_Control,Incoterms_1,Incoterms_2,Material,Vendor,Link_Material_vendor,Porcentaje_Additional)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from EINE";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public String CargarCSV_Z39_INFILE(String Ruta) throws IOException {
        String Realizado = "false";
        Ruta = Ruta.replace("\\", "/");
        String SqlInsertMasivo
                = "LOAD DATA LOCAL INFILE '" + Ruta + "' INTO TABLE Z39"
                + " FIELDS TERMINATED BY ','"
                + " ENCLOSED BY '\"'"
                + " LINES TERMINATED BY '\\r\\n'"
                + " IGNORE 1 LINES"
                + " (Plant,Purchase_order,Material,Material_Description,Batch,Movement_type,Movement_Type_Text,Item,Quantity,Qty_in_unit_of_entry,Unit_of_Entry,Amt_in_loc_cur,Currency,Storage_Location,Posting_Date,Document_Date,Material_Document,User_Name,Vendor,Order1)";

        System.out.println("Consulta: " + SqlInsertMasivo);

        ControladorMrpdata controladorMrpdata = new ControladorMrpdata();

        String Sqlborrar = "delete from Z39";

        if (controladorMrpdata.Insert(Sqlborrar)) {
            //Realizado = "true";
        }

        if (controladorMrpdata.Insert(SqlInsertMasivo)) {
            Realizado = "true";
        }
        return Realizado;
    }

    public ModeloMb51 FinalComprasMB51_CargaCSV(ModeloMb51 modeloMb51) throws SQLException {
        //List<ModeloMb51> listModeloMb51 = new ArrayList<ModeloMb51>();
        //LinkedList<ModeloMb51> listModeloMb51 = null;

        //for (ModeloMb51 modeloMb51 : listModeloMb51) {
        //BUSCAMOS EL PROVEEDOR
        ControladorProveedor controladorProveedores = new ControladorProveedor();
        ModeloProveedor modeloProveedor = controladorProveedores.SelectSQL("SELECT Id, Vendors, Name, Vendor_Type, Moneda FROM proveedor WHERE Vendors = '" + modeloMb51.getVendor() + "'");
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

        Double Cost_Unit_SAP_en_KG = 0.0;
        if (modeloMb51.getUnit_of_Entry().contentEquals("GAL")) {
            Cost_Unit_SAP_en_KG = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        } else {
            Cost_Unit_SAP_en_KG = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
        }
        //LLENAMOS COLUMNA Y
        modeloMb51.setCost_Unit_SAP_en_KG(String.format("%.5f", Cost_Unit_SAP_en_KG).replace(",", "."));

        //BUSCAMOS EL MATERIAL
        ControladorMrpdata controladorMrpData = new ControladorMrpdata();
        ModeloMrpData modeloMrpData = controladorMrpData.SelectSQL("SELECT * FROM mrpdata WHERE Material = '" + modeloMb51.getMaterial() + "'");
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
        ControladorMe80fn controladorMe80fn = new ControladorMe80fn();
        LinkedList<ModeloMe80fn> LstmodeloMe80fn = controladorMe80fn.ListSelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Material <> '' AND Movement_type = ''");
        Double Me80fnQuantity = 0.0;
        String O_Unit_ME80FN = "";
        String Moneda = "";

        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
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
        Double Quantity = Double.valueOf(modeloMb51.getQuantity());
        Double Qty_in_unit_of_entry = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
        Double TotalQ_ME80FN = Double.valueOf(modeloMb51.getTotalQ_ME80FN());
        Double TotalQ_Porcentaje = null;
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

        //BUSCAMOS TOTAL_INVOICE_VALUE
        //modeloMe80fn = controladorMe80fn.SelectSQL("SELECT *, SUM(Amt_in_loc_cur) AS 'SUM_Amt_in_loc_cur' FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND Movement_type = ''");
        Double TOTAL_INVOICE_VALUE = 0.0;
        for (ModeloMe80fn modeloMe80fn : LstmodeloMe80fn) {
            TOTAL_INVOICE_VALUE = TOTAL_INVOICE_VALUE + Double.valueOf(modeloMe80fn.getAmt_in_loc_cur());
        }

        //LLENAMOS COLUMNA AH
        modeloMb51.setTOTAL_INVOICE_VALUE(String.format("%.5f", TOTAL_INVOICE_VALUE).replace(",", "."));

        Double Factura_Value_Unit = 0.0;
        if (modeloMb51.getUnit_of_Entry().contentEquals("GAL")) {
            Factura_Value_Unit = TOTAL_INVOICE_VALUE / Quantity * TotalQ_Porcentaje;
        } else {
            Factura_Value_Unit = TOTAL_INVOICE_VALUE / Qty_in_unit_of_entry * TotalQ_Porcentaje;
        }

        //LLENAMOS COLUMNA AI
        modeloMb51.setFactura_Value_Unit(String.format("%.5f", Factura_Value_Unit).replace(",", "."));

        //Double Unitario_estandar = Double.valueOf(modeloMb51.getAmt_in_loc_cur()) / Double.valueOf(modeloMb51.getQuantity());
        Double PIR = Factura_Value_Unit / Cost_Unit_SAP_en_KG;
        //LLENAMOS COLUMNA AJ
        //modeloMb51.setPIR_Porcentaje(String.format("%.5f", PIR));
        modeloMb51.setPIR_Porcentaje_del_Costo(String.format("%.5f", PIR).replace(",", "."));

        //BUSCAMOS MONEDA
        //ModeloMe80fn modeloMe80fn = controladorMe80fn.SelectSQL("SELECT * FROM me80fn WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND Movement_type <> ''");
        //LLENAMOS COLUMNA AK
        //modeloMb51.setMoneda(modeloMe80fn.getCurrency());
        modeloMb51.setMoneda(Moneda);

        //LLENAMOS COLUMNA AL
        modeloMb51.setLink3_PO_Item(modeloMb51.getPurchase_order() + modeloMb51.getMaterial());

        Double Freight = 0.0;
        Double Dutys = 0.0;
        Double Arancel = 0.0;
        ControladorFbl3m controladorFbl3m = new ControladorFbl3m();
        LinkedList<ModeloFbl3m> ListmodeloFbl3m = null;
        if (modeloMb51.getVendor_Type().contains("Nal")) {
            Freight = 0.0;
            Dutys = 0.0;
            Arancel = 0.0;
        } else if (modeloMb51.getVendor_Type().contains("ICO")) {
            //BUSCAMOS Freight
            //ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND (Account = '50320' OR Account = '20011') AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no LIKE '3%')");
            Boolean IcoEncontrado = false;
            ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Text = '" + modeloMb51.getPurchase_order() + modeloMb51.getItem() + "' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no = '50320' OR Offsetting_acct_no = '3062482' OR Offsetting_acct_no = '3072634' OR Offsetting_acct_no = '3905355' OR Offsetting_acct_no = '3905370' OR Offsetting_acct_no = '3905371')");
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Freight = Freight + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                    IcoEncontrado = true;
                }
            }
            //BUSCAMOS DUTYS
            //ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND (Account = '50320' OR Account = '20014') AND (Offsetting_acct_no = '20014' OR Offsetting_acct_no LIKE '3%')");
            ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Text = '" + modeloMb51.getPurchase_order() + modeloMb51.getItem() + "' AND (Offsetting_acct_no = '20014' OR Offsetting_acct_no = '3905312')");
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Dutys = Dutys + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                    IcoEncontrado = true;
                }
            }
            //BUSCAMOS ARANCEL
            //ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Item = '" + modeloMb51.getItem() + "' AND (Account = '50320' OR Account = '20014') AND (Offsetting_acct_no = '20319' OR Offsetting_acct_no = '3062623')");
            ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Text = '" + modeloMb51.getPurchase_order() + modeloMb51.getItem() + "' AND (Offsetting_acct_no = '3062623')");
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Arancel = Arancel + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                    IcoEncontrado = true;
                }
            }
            if (!IcoEncontrado) {
                modeloMb51.setNovedadIco("X");
            }

        } else {
            //BUSCAMOS Freight
            ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND (Offsetting_acct_no = '20011' OR Offsetting_acct_no = '50320' OR Offsetting_acct_no = '3062482' OR Offsetting_acct_no = '3072634' OR Offsetting_acct_no = '3905355' OR Offsetting_acct_no = '3905370' OR Offsetting_acct_no = '3905371')");
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Freight = Freight + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                }
            }
            //BUSCAMOS DUTYS
            ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND (Offsetting_acct_no = '20014' OR Offsetting_acct_no = '3905312')");
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Dutys = Dutys + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                }
            }
            //BUSCAMOS ARANCEL
            ListmodeloFbl3m = controladorFbl3m.ListSelectSQL("SELECT * FROM Fbl3m WHERE Purchasing_Document = '" + modeloMb51.getPurchase_order() + "' AND Material = '" + modeloMb51.getMaterial() + "' AND (Offsetting_acct_no = '3062623')");
            for (ModeloFbl3m modeloFbl3m : ListmodeloFbl3m) {
                if (modeloFbl3m.getAmount_in_local_currency() != null) {
                    Arancel = Arancel + Double.valueOf(modeloFbl3m.getAmount_in_local_currency());
                }
            }
        }

        //LLENAMOS COLUMNA AM       
        modeloMb51.setFreight(String.format("%.5f", Freight).replace(",", "."));

        //LLENAMOS COLUMNA AN
        modeloMb51.setDutys(String.format("%.5f", Dutys).replace(",", "."));

        //LLENAMOS COLUMNA AO
        modeloMb51.setArancel(String.format("%.5f", Arancel).replace(",", "."));

        Double Total_Costos_Adicionales = Freight + Dutys + Arancel;
        //LLENAMOS COLUMNA AP
        modeloMb51.setTotal_Costos_Adicionales(String.format("%.5f", Total_Costos_Adicionales).replace(",", "."));

        Double Participac_Adicionales = Total_Costos_Adicionales / TOTAL_INVOICE_VALUE;
        //LLENAMOS COLUMNA AQ
        modeloMb51.setParticipac_Adicionales(String.format("%.5f", Participac_Adicionales).replace(",", "."));

        ModeloEine modeloEine = null;
        ControladorEine controladorEine = new ControladorEine();
        if (modeloMb51.getVendor_Type().contains("ICO")) {
            modeloEine = controladorEine.SelectSQL("SELECT * FROM EINE WHERE vendor = '" + modeloMb51.getVendor() + "'");
        } else {
            modeloEine = controladorEine.SelectSQL("SELECT * FROM EINE WHERE Link_Material_vendor = '" + modeloMb51.getMaterial() + modeloMb51.getVendor() + "'");
        }
        Double Porcentaje_Adicional = 0.0;
        Double Adicionales_al_CTO_Estandar = 0.0;
        if (modeloEine != null) {
            Porcentaje_Adicional = Double.valueOf(modeloEine.getPorcentaje_Additional());
            Adicionales_al_CTO_Estandar = Double.valueOf(modeloEine.getPorcentaje_Additional());
        }

        //LLENAMOS COLUMNA AR
        modeloMb51.setAdicionales_al_CTO_Estandar(String.format("%.5f", Adicionales_al_CTO_Estandar).replace(",", "."));

        Double Variance = 0.0;
        if (Participac_Adicionales != 0.0) {
            Variance = Porcentaje_Adicional - Participac_Adicionales;
        }
        //LLENAMOS COLUMNA AS
        modeloMb51.setVariance(String.format("%.5f", Variance).replace(",", "."));

        Double Total_Costos = 0.0;
        Double Amt_in_loc_cur = Double.valueOf(modeloMb51.getAmt_in_loc_cur());

        //BUSCAR SI ES NAL_SUBCONT
        ControladorVendorType controladorVendorType = new ControladorVendorType();
        ModeloVendorType modeloVendorType = controladorVendorType.Select("SELECT * FROM VendorType WHERE Material = '" + modeloMb51.getMaterial() + "'");
        if (modeloVendorType != null) {
            modeloMb51.setVendor_Type("Nal-Subcont");
        }

        if (modeloMb51.getVendor_Type().contentEquals("Nal-Subcont")) {
            Total_Costos = (Total_Costos_Adicionales * TotalQ_Porcentaje + Amt_in_loc_cur);
        } else {
            Total_Costos = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;
        }

        Double Unitario_Real = 0.0;
        //LLENAMOS COLUMNA AT
        modeloMb51.setTotal_Costos(String.format("%.5f", Total_Costos).replace(",", "."));

        if (modeloMb51.getUnit_of_Entry().contentEquals("GAL")) {
            Unitario_Real = Total_Costos / Quantity;
        } else {
            Unitario_Real = Total_Costos / Qty_in_unit_of_entry;
        }

        //LLENAMOS COLUMNA AU
        modeloMb51.setUnitario_Real(String.format("%.5f", Unitario_Real).replace(",", "."));

        Double Unitario_Real_adicional_estandar = 0.0;
        Unitario_Real_adicional_estandar = (Total_Costos_Adicionales + TOTAL_INVOICE_VALUE) * TotalQ_Porcentaje;

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
        Porcentaje_Real_Vs_Estanda = ((Unitario_Real / Unitario_estandar_SAP) - 1);

        //LLENAMOS COLUMNA AY
        modeloMb51.setPorcentaje_Real_Vs_Estandar(String.format("%.5f", Porcentaje_Real_Vs_Estanda).replace(",", "."));

        Double Porcentaje_fifo_final_vs_Estandar = 0.0;
        Porcentaje_fifo_final_vs_Estandar = ((Unitario_final_FIFO / Unitario_estandar_SAP) - 1);

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
        if (modeloZ39s.size() > 0) {
            for (ModeloZ39 modeloZ39 : modeloZ39s) {
                modeloZ39s_1 = controladorZ39.Select("Select * from Z39 where Material_Document = '" + modeloZ39.getMaterial_Document() + "' ORDER BY Quantity");
                if (modeloZ39s_1.size() > 0) {
                    for (ModeloZ39 modeloZ39_1 : modeloZ39s_1) {

                        Double Quantity_Z39 = Double.valueOf(modeloZ39_1.getQuantity());
//                        Double Qty_in_unit_of_entry_Z39 = Double.valueOf(modeloZ39_1.getQty_in_unit_of_entry());
//
//                        Double Quantity_mb51 = Double.valueOf(modeloMb51.getQuantity());
//                        Double Qty_in_unit_of_entry_mb51 = Double.valueOf(modeloMb51.getQty_in_unit_of_entry());
                        Double Amt_in_loc_cur_mb51 = 0.0;

                        Amt_in_loc_cur_mb51 = Quantity_Z39 * Amt_in_loc_cur_mb51_Original / Quantity_mb51_Original;

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
                }
            }
        }
    }

    

}
