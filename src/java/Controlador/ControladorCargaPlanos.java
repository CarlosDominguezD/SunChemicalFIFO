/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
            System.out.println(tamanorequies);

            Part arch = request.getPart("archivo");
            String nombre = arch.getName();
            formato = request.getParameter("Formato");
            InputStream is = arch.getInputStream();
            File detino = new File("SunChemical\\" + formato);
            if (detino.exists() != true)
            {
                detino.mkdirs();
            }
            String RutaDispo = "SunChemical\\" + formato + "\\" + formato + " " + ObtenerFecha() + ".txt";
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
            File fina = new File(RutaDispo);
            if (tamanorequies == fina.length())
            {
                resultado = "true";
            }

            String total = "0";
            String totalTrue = "0";
            String totalFalse = "0";
        } catch (IOException | ServletException e)
        {
            System.out.println("Error en la carga del plano "+formato+"  "+e);
        }
        return resultado;
    }

}
