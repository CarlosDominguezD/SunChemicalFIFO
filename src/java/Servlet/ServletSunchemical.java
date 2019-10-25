/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorCargaPlanos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Carlos A Dominguez D
 */
@MultipartConfig
public class ServletSunchemical extends HttpServlet
{

    String res = "false";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            if ("true".equals(res))
            {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<h1> Archivo Cargado Exitosamente </h1>");
                out.println("<head>");
                out.println("<title>Plano Cargado</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h3> Plano Cargado</h3>");
                out.println("<li><a href=../../../SunChemicalFIFO/Cargaplanos.jsp>Volver Cargar Planos</a></li>");
                out.println("</body>");
                out.println("</html>");
            } else
            {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<h1> Errores en la carga del plano </h1>");
                out.println("<head>");
                out.println("<title>Mensaje Error Plano</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h3>" + res + "</h3>");
                out.println("<li><a href=../../../SunChemicalFIFO/Cargaplanos.jsp>Volver Cargar Planos</a></li>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
//        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String Accion = request.getParameter("Accion");
        String res = "";
        ControladorCargaPlanos controladorCargaPlanos = new ControladorCargaPlanos();
        switch (Accion)
        {
            case "Planos":
                res = controladorCargaPlanos.Upload(request, response);
                request.setAttribute("espuesta", res);
                processRequest(request, response);
//            response.setCharacterEncoding("UT
                break;
        }

//        String respuesta = herramienta.GetDescrpCode(res);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/plain");
//        response.getWriter().write(respuesta);
//        long tamanorequies = request.getPart("archivo").getSize();
//        System.out.println(tamanorequies);
//
//        Part arch = request.getPart("archivo");
//        String nombre = arch.getName();
//        String formato = request.getParameter("Formato");
//        InputStream is = arch.getInputStream();
//        File detino = new File("SunChemical\\" + formato);
//        if (detino.exists() != true) {
//            detino.mkdirs();
//        }
//        String RutaDispo = "SunChemical\\" + formato + "\\" + formato + " " + ObtenerFecha() + ".txt";
//        File f = new File(RutaDispo);
//        FileOutputStream ous = new FileOutputStream(f);
//        int dato = is.read();
//        while (dato != -1) {
//            ous.write(dato);
//            dato = is.read();
//        }
//        ous.close();
//        is.close();
//
//        String total = "0";
//        String totalTrue = "0";
//        String totalFalse = "0";
        //processRequest(request, response);
    }

    public static String ObtenerFecha()
    {
        Calendar fecHor = Calendar.getInstance();
        String dia = Integer.toString(fecHor.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(fecHor.get(Calendar.MONDAY) + 1);
        String año = Integer.toString(fecHor.get(Calendar.YEAR));
        String fh = (dia + "-" + mes + "-" + año);
        return fh;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
