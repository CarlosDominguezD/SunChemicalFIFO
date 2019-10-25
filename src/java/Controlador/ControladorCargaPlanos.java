/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorCargaPlanos
{

    public String Upload(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
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

    private String ProcesarArchivoMRP_DATA(String RutaFinal, String formato)
    {
        try (FileInputStream file = new FileInputStream(new File(RutaFinal)))
        {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
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

}
