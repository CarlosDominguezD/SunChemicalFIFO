/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorCargaPlanos;
import Modelos.ModeloArchivos;
import static Servlet.ServletSunchemical.ObtenerFecha;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
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
public class UploadServlet extends HttpServlet
{

    String respuesta = "false";

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try (PrintWriter out = response.getWriter ())
        {
            /*
             * TODO output your page here. You may use following sample code.
             */
            if ("true".equals (respuesta))
            {
                out.println ("<!DOCTYPE html>");
                out.println ("<html lang=en>");
                out.println ("<head>");
                out.println ("<link href=Principal/vendors/bootstrap/dist/css/bootstrap.min.css rel=stylesheet>");
                out.println ("<link href=Principal/vendors/font-awesome/css/font-awesome.min.css rel=stylesheet>");
                out.println ("<link href=Principal/vendors/nprogress/nprogress.css rel=stylesheet>");
                out.println ("<link href=Principal/build/css/custom.min.css rel=stylesheet>");
                out.println ("</head>");
                out.println ("<body class=nav-md>");
                out.println ("<div class=right_col role=main>");
                out.println ("<div class=container body>");
                out.println ("<div class=main_container>");
                out.println ("<div class=col-md-12>");
                out.println ("<div class=col-middle>");
                out.println ("<div class=text-center text-center>");
                out.println ("<h1 class=error-number>OK</h1>");
                out.println ("<br/>");
                out.println ("<p");
                out.println ("<h2>Archivo Cargado Correctamente</h2>");
                out.println ("<br/>");
                out.println ("<br/>");
                out.println ("<p>Para retornar al formulario ==> <a href=CargaplanosAll.jsp>Principal</a>");
                out.println ("</p>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</body>");
                out.println ("</html>");
            }
            else
            {
                out.println ("<!DOCTYPE html>");
                out.println ("<html lang=en>");
                out.println ("<head>");
                out.println ("<link href=Principal/vendors/bootstrap/dist/css/bootstrap.min.css rel=stylesheet>");
                out.println ("<link href=Principal/vendors/font-awesome/css/font-awesome.min.css rel=stylesheet>");
                out.println ("<link href=Principal/vendors/nprogress/nprogress.css rel=stylesheet>");
                out.println ("<link href=Principal/build/css/custom.min.css rel=stylesheet>");
                out.println ("</head>");
                out.println ("<body class=nav-md>");
                out.println ("<div class=right_col role=main>");
                out.println ("<div class=container body>");
                out.println ("<div class=main_container>");
                out.println ("<div class=col-md-12>");
                out.println ("<div class=col-middle>");
                out.println ("<div class=text-center text-center>");
                out.println ("<h1 class=error-number>Error</h1>");
                out.println ("<br/>");
                out.println ("<p");
                out.println ("<h2>Error en la carga del plano</h2>");
                out.println ("<br/>");
                out.println ("<br/>");
                out.println ("<p>Para retornar al formulario ==> <a href=CargaplanosAll.jsp>Principal</a>");
                out.println ("</p>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</div>");
                out.println ("</body>");
                out.println ("</html>");
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
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest (request, response);
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
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType ("text/html;charset=UTF-8");
        LinkedList<ModeloArchivos> listModeloArchivoses = new LinkedList<> ();
        ControladorCargaPlanos controladorCargaPlanos = new ControladorCargaPlanos ();
        String resultado = "false";
        Collection<Part> parts = request.getParts ();
        for (Part part : parts)
        {
            ModeloArchivos modeloArchivos = new ModeloArchivos ();
            String name = getFileName (part);
            if (!"null".equals (name))
            {
                modeloArchivos.setNombre (name);
                long tamanorequies = part.getSize ();
                String fileName = Paths.get (part.getSubmittedFileName ()).getFileName ().toString ();
                System.out.println (fileName);
                InputStream is = part.getInputStream ();
                File detino = new File ("C:\\Zred\\SunChemical\\Upload\\");
                if (detino.exists () != true)
                {
                    detino.mkdirs ();
                }
                File file = new File (detino, name + "_" + ObtenerFecha () + ".csv");
                try (InputStream input = part.getInputStream ())
                {
                    file.delete ();
                    Files.copy (input, file.toPath ());
                    modeloArchivos.setRuta (file.getAbsolutePath ());
                }
                //String RutaDispo = "C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Compras\\Compras_10-11-2019.csv";//
                String RutaDispo = file.getPath ();
                File RutaFinal = new File (RutaDispo);
                //File RutaFinal = new File("C:\\Users\\Carlos A Dominguez D\\GlasFish\\glassfish\\domains\\GlassFish\\config\\SunChemical\\Compras\\Compras_10-11-2019.csv");
                if (tamanorequies == RutaFinal.length ())
                {
                    resultado = "true";
                }
                listModeloArchivoses.add (modeloArchivos);
            }
            //part.write (getFileName (part));
        }
        respuesta = controladorCargaPlanos.procesarCarga (listModeloArchivoses, request, response);
        request.setAttribute ("respuesta", respuesta);
        processRequest (request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

    public String getFileName (Part part)
    {
        String contentHeader = part.getHeader ("content-disposition");
        String[] subHeaders = contentHeader.split (";");
        for (String current : subHeaders)
        {
            if (current.trim ().startsWith ("filename"))
            {
                int pos = current.indexOf ('=');
                String fileName = current.substring (pos + 1);
                return fileName.replace ("\"", "");
            }
        }
        return "null";
    }

}
