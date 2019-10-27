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
import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFSheet;

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
        try
        {
            long tamanorequies = request.getPart("archivo").getSize();
            Part arch = request.getPart("archivo");
            formato = request.getParameter("Formato");
            InputStream is = arch.getInputStream();
            File detino = new File("SunChemical\\" + formato);
            if (detino.exists() != true)
            {
                detino.mkdirs();
            }
            String RutaDispo = "SunChemical\\" + formato + "\\" + formato + " " + ObtenerFecha() + ".xlsx";
            File f = new File(RutaDispo);
            FileOutputStream ous = new FileOutputStream(f);
            int dato = is.read();
            while (dato != -1)
            {
                ous.write(dato);
                dato = is.read();
            }
            ous.close();
            is.close();
            File RutaFinal = new File(RutaDispo);
            if (tamanorequies == RutaFinal.length())
            {
                resultado = "true";
            }
            String d = request.getParameter("NombrePlano");
            System.out.println(d);
            switch (request.getParameter("NombrePlano"))
            {
                case "MRP Data":
                    resultado = ProcesarArchivoMRP_DATA(RutaDispo, formato);
                    break;
            }
        } catch (IOException | ServletException e)
        {
            System.out.println("Error en la carga del plano " + formato + "  " + e);
        }
        return resultado;
    }

    private String ProcesarArchivoMRP_DATA(String RutaFinal, String formato) {
        try (FileInputStream file = new FileInputStream(new File(RutaFinal)))
        {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;;
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                //se obtiene las celdas por fila
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell;
                //se recorre cada celda
                while (cellIterator.hasNext())
                {
                    // se obtiene la celda en específico y se la imprime
                    cell = cellIterator.next();
                    System.out.print(cell.getStringCellValue() + " | ");
                }
                System.out.println();
            }
        } catch (Exception e)
        {
        }
        return null;
    }

    public String CargarXLS_Compras_MB51(String Ruta) {
        String Realizado = "False";
        String Errores = "";
        try
        {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Zred\\SunChemical\\DESCUENTOS.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();
            int Contador = 0;
            ModeloMb51 modeloMb51 = null;
            while (iterator.hasNext())
            {
                modeloMb51 = new ModeloMb51();
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    switch (Contador)
                    {
                        case 0:
                            modeloMb51.setPlant(contenidoCelda);
                            System.out.println("Plant: " + modeloMb51.getPlant());
                            break;
                        case 1:
                            modeloMb51.setPurchase_order(contenidoCelda);
                            System.out.println("Purchase_order: " + modeloMb51.getPurchase_order());
                            break;
                        case 2:
                            modeloMb51.setMaterial(contenidoCelda);
                            System.out.println("Material: " + modeloMb51.getMaterial());
                            break;
                        case 3:
                            modeloMb51.setMaterial_Description(contenidoCelda);
                            System.out.println("Material_Description: " + modeloMb51.getMaterial_Description());
                            break;
                        case 4:
                            modeloMb51.setBatch(contenidoCelda);
                            System.out.println("Batch: " + modeloMb51.getBatch());
                            break;
                        case 5:
                            modeloMb51.setMovement_type(contenidoCelda);
                            System.out.println("Movement_type: " + modeloMb51.getMovement_type());
                            break;
                        case 6:
                            modeloMb51.setMovement_Type_Text(contenidoCelda);
                            System.out.println("Movement_Type_Text: " + modeloMb51.getMovement_Type_Text());
                            break;
                        case 7:
                            modeloMb51.setItem(contenidoCelda);
                            System.out.println("Item: " + modeloMb51.getItem());
                            break;
                        case 8:
                            modeloMb51.setQuantity(contenidoCelda);
                            System.out.println("Quantity: " + modeloMb51.getQuantity());
                            break;
                        case 9:
                            modeloMb51.setQty_in_unit_of_entry(contenidoCelda);
                            System.out.println("Qty_in_unit_of_entry: " + modeloMb51.getQty_in_unit_of_entry());
                            break;
                        case 10:
                            modeloMb51.setUnit_of_Entry(contenidoCelda);
                            System.out.println("Unit_of_Entry: " + modeloMb51.getUnit_of_Entry());
                            break;
                        case 11:
                            modeloMb51.setAmt_in_loc_cur(contenidoCelda);
                            System.out.println("Amt_in_loc_cur: " + modeloMb51.getAmt_in_loc_cur());
                            break;
                        case 12:
                            modeloMb51.setCurrency(contenidoCelda);
                            System.out.println("Currency: " + modeloMb51.getCurrency());
                            break;
                        case 13:
                            modeloMb51.setStorage_Location(contenidoCelda);
                            System.out.println("Storage_Location: " + modeloMb51.getStorage_Location());
                            break;
                        case 14:
                            modeloMb51.setPosting_Date(contenidoCelda);
                            System.out.println("Posting_Date: " + modeloMb51.getPosting_Date());
                            break;
                        case 15:
                            modeloMb51.setDocument_Date(contenidoCelda);
                            System.out.println("Document_Date: " + modeloMb51.getDocument_Date());
                            break;
                        case 16:
                            modeloMb51.setMaterial_Document(contenidoCelda);
                            System.out.println("Material_Document: " + modeloMb51.getMaterial_Document());
                            break;
                        case 17:
                            modeloMb51.setUser_Name(contenidoCelda);
                            System.out.println("User_Name: " + modeloMb51.getUser_Name());
                            break;
                        case 18:
                            modeloMb51.setVendor(contenidoCelda);
                            System.out.println("Vendor: " + modeloMb51.getVendor());
                            break;
                        case 19:
                            modeloMb51.setOrder(contenidoCelda);
                            System.out.println("Order: " + modeloMb51.getOrder());
                            break;

                    }
                    //Guardar Modelo en la base de datos

                    Contador++;
                }
                modeloMb51 = null;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Realizado;
    }

    public String CargarXLS_Compras_ME80FN(String Ruta) {
        String Realizado = "False";
        String Errores = "";
        try
        {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Zred\\SunChemical\\DESCUENTOS.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();
            int Contador = 0;

            ModeloMe80fn modeloMe80fn = null;

            while (iterator.hasNext())
            {
                modeloMe80fn = new ModeloMe80fn();
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);

                    switch (Contador)
                    {
                        case 0:
                            modeloMe80fn.setPurchasing_Document(contenidoCelda);
                            System.out.println("Purchasing_Document: " + modeloMe80fn.getPurchasing_Document());
                            break;
                        case 1:
                            modeloMe80fn.setMaterial_Doc_Year(contenidoCelda);
                            System.out.println("Material_Doc_Year: " + modeloMe80fn.getMaterial_Doc_Year());
                            break;
                        case 2:
                            modeloMe80fn.setMaterial_Document(contenidoCelda);
                            System.out.println("Material_Document: " + modeloMe80fn.getMaterial_Document());
                            break;
                        case 3:
                            modeloMe80fn.setDocument_Date(contenidoCelda);
                            System.out.println("Document_Date: " + modeloMe80fn.getDocument_Date());
                            break;
                        case 4:
                            modeloMe80fn.setMaterial(contenidoCelda);
                            System.out.println("Material: " + modeloMe80fn.getMaterial());
                            break;
                        case 5:
                            modeloMe80fn.setShort_Text(contenidoCelda);
                            System.out.println("Short_Text: " + modeloMe80fn.getShort_Text());
                            break;
                        case 6:
                            modeloMe80fn.setBatch(contenidoCelda);
                            System.out.println("Batch: " + modeloMe80fn.getBatch());
                            break;
                        case 7:
                            modeloMe80fn.setItem(contenidoCelda);
                            System.out.println("Item: " + modeloMe80fn.getItem());
                            break;
                        case 8:
                            modeloMe80fn.setMovement_type(contenidoCelda);
                            System.out.println("Movement_type: " + modeloMe80fn.getMovement_type());
                            break;
                        case 9:
                            modeloMe80fn.setPosting_Date(contenidoCelda);
                            System.out.println("Posting_Date: " + modeloMe80fn.getPosting_Date());
                            break;
                        case 10:
                            modeloMe80fn.setDelivery_Completed(contenidoCelda);
                            System.out.println("Delivery_Completed: " + modeloMe80fn.getDelivery_Completed());
                            break;
                        case 11:
                            modeloMe80fn.setPlant(contenidoCelda);
                            System.out.println("Plant: " + modeloMe80fn.getPlant());
                            break;
                        case 12:
                            modeloMe80fn.setQuantity(contenidoCelda);
                            System.out.println("Quantity: " + modeloMe80fn.getQuantity());
                            break;
                        case 13:
                            modeloMe80fn.setAmt_in_loc_cur(contenidoCelda);
                            System.out.println("Amt_in_loc_cur: " + modeloMe80fn.getAmt_in_loc_cur());
                            break;
                        case 14:
                            modeloMe80fn.setAmount(contenidoCelda);
                            System.out.println("Amount: " + modeloMe80fn.getAmount());
                            break;
                        case 15:
                            modeloMe80fn.setCurrency(contenidoCelda);
                            System.out.println("Currency: " + modeloMe80fn.getCurrency());
                            break;
                        case 16:
                            modeloMe80fn.setValuation_Type(contenidoCelda);
                            System.out.println("Valuation_Type: " + modeloMe80fn.getValuation_Type());
                            break;
                        case 17:
                            modeloMe80fn.setEntry_Date(contenidoCelda);
                            System.out.println("Entry_Date: " + modeloMe80fn.getEntry_Date());
                            break;
                        case 18:
                            modeloMe80fn.setLocal_currency(contenidoCelda);
                            System.out.println("Local_currency: " + modeloMe80fn.getLocal_currency());
                            break;
                        case 19:
                            modeloMe80fn.setReference_Doc_Item(contenidoCelda);
                            System.out.println("Reference_Doc_Item: " + modeloMe80fn.getReference_Doc_Item());
                            break;
                        case 20:
                            modeloMe80fn.setInvoice_Value(contenidoCelda);
                            System.out.println("Invoice_Value: " + modeloMe80fn.getInvoice_Value());
                            break;
                        case 21:
                            modeloMe80fn.setInvoice_Value_in_FC(contenidoCelda);
                            System.out.println("Invoice_Value_in_FC: " + modeloMe80fn.getInvoice_Value_in_FC());
                            break;

                    }
                    //GUARDAR MODELO

                    Contador++;
                }
                modeloMe80fn = null;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Realizado;
    }

    public String CargarXLS_Compras_FBL3N(String Ruta) {
        String Realizado = "False";
        String Errores = "";
        try
        {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Zred\\SunChemical\\DESCUENTOS.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();
            int Contador = 0;

            ModeloFbl3m modeloFbl3m = null;

            while (iterator.hasNext())
            {
                modeloFbl3m = new ModeloFbl3m();
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);

                    switch (Contador)
                    {
                        case 0:
                            modeloFbl3m.setDocument_Number(contenidoCelda);
                            System.out.println("Document_Number: " + modeloFbl3m.getDocument_Number());
                            break;
                        case 1:
                            modeloFbl3m.setDocument_type(contenidoCelda);
                            System.out.println("Document_type: " + modeloFbl3m.getDocument_type());
                            break;
                        case 2:
                            modeloFbl3m.setDocument_Date(contenidoCelda);
                            System.out.println("Document_Date: " + modeloFbl3m.getDocument_Date());
                            break;
                        case 3:
                            modeloFbl3m.setPosting_Date(contenidoCelda);
                            System.out.println("Posting_Date: " + modeloFbl3m.getPosting_Date());
                            break;
                        case 4:
                            modeloFbl3m.setCost_Center(contenidoCelda);
                            System.out.println("Cost_Center: " + modeloFbl3m.getCost_Center());
                            break;
                        case 5:
                            modeloFbl3m.setProfit_Center(contenidoCelda);
                            System.out.println("Profit_Center: " + modeloFbl3m.getProfit_Center());
                            break;
                        case 6:
                            modeloFbl3m.setYear_month(contenidoCelda);
                            System.out.println("Year_month: " + modeloFbl3m.getYear_month());
                            break;
                        case 7:
                            modeloFbl3m.setAccount(contenidoCelda);
                            System.out.println("Account: " + modeloFbl3m.getAccount());
                            break;
                        case 8:
                            modeloFbl3m.setPlant(contenidoCelda);
                            System.out.println("Plant: " + modeloFbl3m.getPlant());
                            break;
                        case 9:
                            modeloFbl3m.setMaterial(contenidoCelda);
                            System.out.println("Material: " + modeloFbl3m.getMaterial());
                            break;
                        case 10:
                            modeloFbl3m.setQuantity(contenidoCelda);
                            System.out.println("Quantity: " + modeloFbl3m.getQuantity());
                            break;
                        case 11:
                            modeloFbl3m.setAmount_in_local_currency(contenidoCelda);
                            System.out.println("Amount_in_local_currency: " + modeloFbl3m.getAmount_in_local_currency());
                            break;
                        case 12:
                            modeloFbl3m.setLocal_Currency(contenidoCelda);
                            System.out.println("Local_Currency: " + modeloFbl3m.getLocal_Currency());
                            break;
                        case 13:
                            modeloFbl3m.setPurchasing_Document(contenidoCelda);
                            System.out.println("Purchasing_Document: " + modeloFbl3m.getPurchasing_Document());
                            break;
                        case 14:
                            modeloFbl3m.setReference(contenidoCelda);
                            System.out.println("Reference: " + modeloFbl3m.getReference());
                            break;
                        case 15:
                            modeloFbl3m.setDocument_currency(contenidoCelda);
                            System.out.println("Document_currency: " + modeloFbl3m.getDocument_currency());
                            break;
                        case 16:
                            modeloFbl3m.setOffsetting_acct_no(contenidoCelda);
                            System.out.println("Offsetting_acct_no: " + modeloFbl3m.getOffsetting_acct_no());
                            break;
                        case 17:
                            modeloFbl3m.setBase_Unit_of_Measure(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure: " + modeloFbl3m.getBase_Unit_of_Measure());
                            break;
                        case 18:
                            modeloFbl3m.setAlternative_Account_No(contenidoCelda);
                            System.out.println("Alternative_Account_No: " + modeloFbl3m.getAlternative_Account_No());
                            break;
                        case 19:
                            modeloFbl3m.setTransaction_Code(contenidoCelda);
                            System.out.println("Transaction_Code: " + modeloFbl3m.getTransaction_Code());
                            break;
                        case 20:
                            modeloFbl3m.setText(contenidoCelda);
                            System.out.println("Text: " + modeloFbl3m.getText());
                            break;
                        case 21:
                            modeloFbl3m.setAssignment(contenidoCelda);
                            System.out.println("Assignment: " + modeloFbl3m.getAssignment());
                            break;

                    }
                    //guardar modelo
                    Contador++;
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return Realizado;
    }

    public String CargarXLS_MRP_DATA(String Ruta) {
        String Realizado = "False";
        String Errores = "";
        try
        {
            FileInputStream inputStream = new FileInputStream(new File("C:\\Zred\\SunChemical\\DESCUENTOS.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();
            int Contador = 0;

            ModeloMrpData modeloMrpData = null;

            while (iterator.hasNext())
            {
                modeloMrpData = new ModeloMrpData();
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);

                    switch (Contador)
                    {
                        case 0:
                            modeloMrpData.setMaterial(contenidoCelda);
                            System.out.println("Material: " + modeloMrpData.getMaterial());
                            break;
                        case 1:
                            modeloMrpData.setMaterial_Description(contenidoCelda);
                            System.out.println("Material_Description: " + modeloMrpData.getMaterial_Description());
                            break;
                        case 2:
                            modeloMrpData.setMaterial_Group(contenidoCelda);
                            System.out.println("Material_Group: " + modeloMrpData.getMaterial_Group());
                            break;
                        case 3:
                            modeloMrpData.setProfit_Center(contenidoCelda);
                            System.out.println("Profit_Center: " + modeloMrpData.getProfit_Center());
                            break;
                        case 4:
                            modeloMrpData.setProduct_Hierarchy(contenidoCelda);
                            System.out.println("Product_Hierarchy: " + modeloMrpData.getProduct_Hierarchy());
                            break;
                        case 5:
                            modeloMrpData.setProduct_Hierarchy_Description(contenidoCelda);
                            System.out.println("Product_Hierarchy_Description: " + modeloMrpData.getProduct_Hierarchy_Description());
                            break;
                        case 6:
                            modeloMrpData.setMaterial_Type(contenidoCelda);
                            System.out.println("Material_Type: " + modeloMrpData.getMaterial_Type());
                            break;
                        case 7:
                            modeloMrpData.setMaterial_type_descr(contenidoCelda);
                            System.out.println("Material_type_descr: " + modeloMrpData.getMaterial_type_descr());
                            break;
                        case 8:
                            modeloMrpData.setPlant(contenidoCelda);
                            System.out.println("Plant: " + modeloMrpData.getPlant());
                            break;
                        case 9:
                            modeloMrpData.setPlant_Name(contenidoCelda);
                            System.out.println("Plant_Name: " + modeloMrpData.getPlant_Name());
                            break;
                        case 10:
                            modeloMrpData.setExt_Material_Group(contenidoCelda);
                            System.out.println("Ext_Material_Group: " + modeloMrpData.getExt_Material_Group());
                            break;
                        case 11:
                            modeloMrpData.setMatl_Grp_Pack_Matls(contenidoCelda);
                            System.out.println("Matl_Grp_Pack_Matls: " + modeloMrpData.getMatl_Grp_Pack_Matls());
                            break;
                        case 12:
                            modeloMrpData.setMGPM_Description(contenidoCelda);
                            System.out.println("MGPM_Description: " + modeloMrpData.getMGPM_Description());
                            break;
                        case 13:
                            modeloMrpData.setMRP_profile(contenidoCelda);
                            System.out.println("MRP_profile: " + modeloMrpData.getMRP_profile());
                            break;
                        case 14:
                            modeloMrpData.setMRP_Profile_Description(contenidoCelda);
                            System.out.println("MRP_Profile_Description: " + modeloMrpData.getMRP_Profile_Description());
                            break;
                        case 15:
                            modeloMrpData.setLot_size(contenidoCelda);
                            System.out.println("Lot_size: " + modeloMrpData.getLot_size());
                            break;
                        case 16:
                            modeloMrpData.setProcurement_type(contenidoCelda);
                            System.out.println("Procurement_type: " + modeloMrpData.getProcurement_type());
                            break;
                        case 17:
                            modeloMrpData.setMRP_Controller(contenidoCelda);
                            System.out.println("MRP_Controller: " + modeloMrpData.getMRP_Controller());
                            break;
                        case 18:
                            modeloMrpData.setMRP_Type(contenidoCelda);
                            System.out.println("MRP_Type: " + modeloMrpData.getMRP_Type());
                            break;
                        case 19:
                            modeloMrpData.setPurchasing_Group(contenidoCelda);
                            System.out.println("Purchasing_Group: " + modeloMrpData.getPurchasing_Group());
                            break;
                        case 20:
                            modeloMrpData.setPlant_sp_matl_status(contenidoCelda);
                            System.out.println("Plant_sp_matl_status: " + modeloMrpData.getPlant_sp_matl_status());
                            break;
                        case 21:
                            modeloMrpData.setMat_Status_Description(contenidoCelda);
                            System.out.println("Mat_Status_Description: " + modeloMrpData.getMat_Status_Description());
                            break;
                        case 22:
                            modeloMrpData.setDo_Not_Cost(contenidoCelda);
                            System.out.println("Do_Not_Cost: " + modeloMrpData.getDo_Not_Cost());
                            break;
                        case 23:
                            modeloMrpData.setStorage_loc_for_EP(contenidoCelda);
                            System.out.println("Storage_loc_for_EP: " + modeloMrpData.getStorage_loc_for_EP());
                            break;
                        case 24:
                            modeloMrpData.setProd_stor_location(contenidoCelda);
                            System.out.println("Prod_stor_location: " + modeloMrpData.getProd_stor_location());
                            break;
                        case 25:
                            modeloMrpData.setProduction_unit(contenidoCelda);
                            System.out.println("Production_unit: " + modeloMrpData.getProduction_unit());
                            break;
                        case 26:
                            modeloMrpData.setCreated_On(contenidoCelda);
                            System.out.println("Created_On: " + modeloMrpData.getCreated_On());
                            break;
                        case 27:
                            modeloMrpData.setAvailability_check(contenidoCelda);
                            System.out.println("Availability_check: " + modeloMrpData.getAvailability_check());
                            break;
                        case 28:
                            modeloMrpData.setLoading_Group(contenidoCelda);
                            System.out.println("Loading_Group: " + modeloMrpData.getLoading_Group());
                            break;
                        case 29:
                            modeloMrpData.setTot_repl_lead_time(contenidoCelda);
                            System.out.println("Tot_repl_lead_time: " + modeloMrpData.getTot_repl_lead_time());
                            break;
                        case 30:
                            modeloMrpData.setUnderdely_tolerance(contenidoCelda);
                            System.out.println("Underdely_tolerance: " + modeloMrpData.getUnderdely_tolerance());
                            break;
                        case 31:
                            modeloMrpData.setUnltd_Overdelivery(contenidoCelda);
                            System.out.println("Unltd_Overdelivery: " + modeloMrpData.getUnltd_Overdelivery());
                            break;
                        case 32:
                            modeloMrpData.setOverdely_tolerance(contenidoCelda);
                            System.out.println("Overdely_tolerance: " + modeloMrpData.getOverdely_tolerance());
                            break;
                        case 33:
                            modeloMrpData.setIn_house_production(contenidoCelda);
                            System.out.println("In_house_production: " + modeloMrpData.getIn_house_production());
                            break;
                        case 34:
                            modeloMrpData.setProdn_Supervisor(contenidoCelda);
                            System.out.println("Prodn_Supervisor: " + modeloMrpData.getProdn_Supervisor());
                            break;
                        case 35:
                            modeloMrpData.setBackflush(contenidoCelda);
                            System.out.println("Backflush: " + modeloMrpData.getBackflush());
                            break;
                        case 36:
                            modeloMrpData.setIndividual_coll(contenidoCelda);
                            System.out.println("Individual_coll: " + modeloMrpData.getIndividual_coll());
                            break;
                        case 37:
                            modeloMrpData.setRounding_value(contenidoCelda);
                            System.out.println("Rounding_value: " + modeloMrpData.getRounding_value());
                            break;
                        case 38:
                            modeloMrpData.setBase_Unit_of_Measure_1(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_1: " + modeloMrpData.getBase_Unit_of_Measure_1());
                            break;
                        case 39:
                            modeloMrpData.setReorder_Point(contenidoCelda);
                            System.out.println("Reorder_Point: " + modeloMrpData.getReorder_Point());
                            break;
                        case 40:
                            modeloMrpData.setBase_Unit_of_Measure_2(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_2: " + modeloMrpData.getBase_Unit_of_Measure_2());
                            break;
                        case 41:
                            modeloMrpData.setAssembly_scrap_porcentaje(contenidoCelda);
                            System.out.println("Assembly_scrap_porcentaje: " + modeloMrpData.getAssembly_scrap_porcentaje());
                            break;
                        case 42:
                            modeloMrpData.setGR_processing_time(contenidoCelda);
                            System.out.println("GR_processing_time: " + modeloMrpData.getGR_processing_time());
                            break;
                        case 43:
                            modeloMrpData.setUnit_of_issue(contenidoCelda);
                            System.out.println("Unit_of_issue: " + modeloMrpData.getUnit_of_issue());
                            break;
                        case 44:
                            modeloMrpData.setValid_from(contenidoCelda);
                            System.out.println("Valid_from: " + modeloMrpData.getValid_from());
                            break;
                        case 45:
                            modeloMrpData.setBatch_management_1(contenidoCelda);
                            System.out.println("Batch_management_1: " + modeloMrpData.getBatch_management_1());
                            break;
                        case 46:
                            modeloMrpData.setValuation_Category(contenidoCelda);
                            System.out.println("Valuation_Category: " + modeloMrpData.getValuation_Category());
                            break;
                        case 47:
                            modeloMrpData.setDF_at_plant_level(contenidoCelda);
                            System.out.println("DF_at_plant_level: " + modeloMrpData.getDF_at_plant_level());
                            break;
                        case 48:
                            modeloMrpData.setDF_at_client_level(contenidoCelda);
                            System.out.println("DF_at_client_level: " + modeloMrpData.getDF_at_client_level());
                            break;
                        case 49:
                            modeloMrpData.setOld_material_number(contenidoCelda);
                            System.out.println("Old_material_number: " + modeloMrpData.getOld_material_number());
                            break;
                        case 50:
                            modeloMrpData.setOrder_Unit(contenidoCelda);
                            System.out.println("Order_Unit: " + modeloMrpData.getOrder_Unit());
                            break;
                        case 51:
                            modeloMrpData.setLab_Office(contenidoCelda);
                            System.out.println("Lab_Office: " + modeloMrpData.getLab_Office());
                            break;
                        case 52:
                            modeloMrpData.setLab_description(contenidoCelda);
                            System.out.println("Lab_description: " + modeloMrpData.getLab_description());
                            break;
                        case 53:
                            modeloMrpData.setGross_Weight(contenidoCelda);
                            System.out.println("Gross_Weight: " + modeloMrpData.getGross_Weight());
                            break;
                        case 54:
                            modeloMrpData.setWeight_Unit_1(contenidoCelda);
                            System.out.println("Weight_Unit_1: " + modeloMrpData.getWeight_Unit_1());
                            break;
                        case 55:
                            modeloMrpData.setNet_Weight(contenidoCelda);
                            System.out.println("Net_Weight: " + modeloMrpData.getNet_Weight());
                            break;
                        case 56:
                            modeloMrpData.setWeight_Unit_2(contenidoCelda);
                            System.out.println("Weight_Unit_2: " + modeloMrpData.getWeight_Unit_2());
                            break;
                        case 57:
                            modeloMrpData.setContainer_reqmts(contenidoCelda);
                            System.out.println("Container_reqmts: " + modeloMrpData.getContainer_reqmts());
                            break;
                        case 58:
                            modeloMrpData.setStorage_conditions(contenidoCelda);
                            System.out.println("Storage_conditions: " + modeloMrpData.getStorage_conditions());
                            break;
                        case 59:
                            modeloMrpData.setTemp_conditions(contenidoCelda);
                            System.out.println("Temp_conditions: " + modeloMrpData.getTemp_conditions());
                            break;
                        case 60:
                            modeloMrpData.setTransportation_Group(contenidoCelda);
                            System.out.println("Transportation_Group: " + modeloMrpData.getTransportation_Group());
                            break;
                        case 61:
                            modeloMrpData.setBatch_management_2(contenidoCelda);
                            System.out.println("Batch_management_2: " + modeloMrpData.getBatch_management_2());
                            break;
                        case 62:
                            modeloMrpData.setPackaging_mat_type(contenidoCelda);
                            System.out.println("Packaging_mat_type: " + modeloMrpData.getPackaging_mat_type());
                            break;
                        case 63:
                            modeloMrpData.setX_plant_matl_status(contenidoCelda);
                            System.out.println("X_plant_matl_status: " + modeloMrpData.getX_plant_matl_status());
                            break;
                        case 64:
                            modeloMrpData.setMin_Rem_Shelf_Life(contenidoCelda);
                            System.out.println("Min_Rem_Shelf_Life: " + modeloMrpData.getMin_Rem_Shelf_Life());
                            break;
                        case 65:
                            modeloMrpData.setTotal_shelf_life(contenidoCelda);
                            System.out.println("Total_shelf_life: " + modeloMrpData.getTotal_shelf_life());
                            break;
                        case 66:
                            modeloMrpData.setLanguage_Key(contenidoCelda);
                            System.out.println("Language_Key: " + modeloMrpData.getLanguage_Key());
                            break;
                        case 67:
                            modeloMrpData.setCreated_By(contenidoCelda);
                            System.out.println("Created_By: " + modeloMrpData.getCreated_By());
                            break;
                        case 68:
                            modeloMrpData.setSafety_stock(contenidoCelda);
                            System.out.println("Safety_stock: " + modeloMrpData.getSafety_stock());
                            break;
                        case 69:
                            modeloMrpData.setBase_Unit_of_Measure_3(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_3: " + modeloMrpData.getBase_Unit_of_Measure_3());
                            break;
                        case 70:
                            modeloMrpData.setFixed_lot_size(contenidoCelda);
                            System.out.println("Fixed_lot_size: " + modeloMrpData.getFixed_lot_size());
                            break;
                        case 71:
                            modeloMrpData.setBase_Unit_of_Measure_4(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_4: " + modeloMrpData.getBase_Unit_of_Measure_4());
                            break;
                        case 72:
                            modeloMrpData.setMaximum_Lot_Size_1(contenidoCelda);
                            System.out.println("Maximum_Lot_Size_1: " + modeloMrpData.getMaximum_Lot_Size_1());
                            break;
                        case 73:
                            modeloMrpData.setBase_Unit_of_Measure_5(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_5: " + modeloMrpData.getBase_Unit_of_Measure_5());
                            break;
                        case 74:
                            modeloMrpData.setMinimum_Lot_Size_2(contenidoCelda);
                            System.out.println("Minimum_Lot_Size_2: " + modeloMrpData.getMinimum_Lot_Size_2());
                            break;
                        case 75:
                            modeloMrpData.setBase_Unit_of_Measure_6(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_6: " + modeloMrpData.getBase_Unit_of_Measure_6());
                            break;
                        case 76:
                            modeloMrpData.setCosting_Lot_Size(contenidoCelda);
                            System.out.println("Costing_Lot_Size: " + modeloMrpData.getCosting_Lot_Size());
                            break;
                        case 77:
                            modeloMrpData.setBase_Unit_of_Measure_7(contenidoCelda);
                            System.out.println("Base_Unit_of_Measure_7: " + modeloMrpData.getBase_Unit_of_Measure_7());
                            break;
                        case 78:
                            modeloMrpData.setProd_Sched_Profile(contenidoCelda);
                            System.out.println("Prod_Sched_Profile: " + modeloMrpData.getProd_Sched_Profile());
                            break;
                        case 79:
                            modeloMrpData.setProd_Sched_Profile_Desc(contenidoCelda);
                            System.out.println("Prod_Sched_Profile_Desc: " + modeloMrpData.getProd_Sched_Profile_Desc());
                            break;
                        case 80:
                            modeloMrpData.setPlanned_Deliv_Time(contenidoCelda);
                            System.out.println("Planned_Deliv_Time: " + modeloMrpData.getPlanned_Deliv_Time());
                            break;
                        case 81:
                            modeloMrpData.setSpecial_procurement(contenidoCelda);
                            System.out.println("Special_procurement: " + modeloMrpData.getSpecial_procurement());
                            break;
                        case 82:
                            modeloMrpData.setSP_Key_Description(contenidoCelda);
                            System.out.println("SP_Key_Description: " + modeloMrpData.getSP_Key_Description());
                            break;
                        case 83:
                            modeloMrpData.setABC_Indicator(contenidoCelda);
                            System.out.println("ABC_Indicator: " + modeloMrpData.getABC_Indicator());
                            break;
                        case 84:
                            modeloMrpData.setSTime_period_profile(contenidoCelda);
                            System.out.println("STime_period_profile: " + modeloMrpData.getSTime_period_profile());
                            break;
                        case 85:
                            modeloMrpData.setComm_imp_code_no(contenidoCelda);
                            System.out.println("Comm_imp_code_no: " + modeloMrpData.getComm_imp_code_no());
                            break;
                        case 86:
                            modeloMrpData.setCountry_of_origin(contenidoCelda);
                            System.out.println("Country_of_origin: " + modeloMrpData.getCountry_of_origin());
                            break;
                        case 87:
                            modeloMrpData.setSpecProcurem_Costing(contenidoCelda);
                            System.out.println("SpecProcurem_Costing: " + modeloMrpData.getSpecProcurem_Costing());
                            break;
                        case 88:
                            modeloMrpData.setOverhead_Group(contenidoCelda);
                            System.out.println("Overhead_Group: " + modeloMrpData.getOverhead_Group());
                            break;
                        case 89:
                            modeloMrpData.setTDS(contenidoCelda);
                            System.out.println("TDS: " + modeloMrpData.getTDS());
                            break;
                        case 90:
                            modeloMrpData.setPeriod_Indicator(contenidoCelda);
                            System.out.println("Period_Indicator: " + modeloMrpData.getPeriod_Indicator());
                            break;

                    }
                    //guardar modelo
                    Contador++;
                }
                modeloMrpData = null;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
