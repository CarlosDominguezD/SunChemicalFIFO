/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorCargaPlanos;
import Controlador.ControladorEstadoPlanos;
import Controlador.ControladorExcel;
import Controlador.ControladorMb51;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos A Dominguez D
 */
@MultipartConfig
public class ServletSunchemical extends HttpServlet {

    String respuesta = "false";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            if ("true".equals(respuesta))
            {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=en>");
                out.println("<head>");
                out.println("<link href=Principal/vendors/bootstrap/dist/css/bootstrap.min.css rel=stylesheet>");
                out.println("<link href=Principal/vendors/font-awesome/css/font-awesome.min.css rel=stylesheet>");
                out.println("<link href=Principal/vendors/nprogress/nprogress.css rel=stylesheet>");
                out.println("<link href=Principal/build/css/custom.min.css rel=stylesheet>");
                out.println("</head>");
                out.println("<body class=nav-md>");
                out.println("<div class=right_col role=main>");
                out.println("<div class=container body>");
                out.println("<div class=main_container>");
                out.println("<div class=col-md-12>");
                out.println("<div class=col-middle>");
                out.println("<div class=text-center text-center>");
                out.println("<h1 class=error-number>OK</h1>");
                out.println("<br/>");
                out.println("<p");
                out.println("<h2>Archivo Cargado Correctamente</h2>");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<p>Para retornar al formulario ==> <a href=Cargaplanos.jsp>Principal</a>");
                out.println("</p>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else
            {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=en>");
                out.println("<head>");
                out.println("<link href=Principal/vendors/bootstrap/dist/css/bootstrap.min.css rel=stylesheet>");
                out.println("<link href=Principal/vendors/font-awesome/css/font-awesome.min.css rel=stylesheet>");
                out.println("<link href=Principal/vendors/nprogress/nprogress.css rel=stylesheet>");
                out.println("<link href=Principal/build/css/custom.min.css rel=stylesheet>");
                out.println("</head>");
                out.println("<body class=nav-md>");
                out.println("<div class=right_col role=main>");
                out.println("<div class=container body>");
                out.println("<div class=main_container>");
                out.println("<div class=col-md-12>");
                out.println("<div class=col-middle>");
                out.println("<div class=text-center text-center>");
                out.println("<h1 class=error-number>Error</h1>");
                out.println("<br/>");
                out.println("<p");
                out.println("<h2>Error en la carga del plano</h2>");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<p>Para retornar al formulario ==> <a href=Cargaplanos.jsp>Principal</a>");
                out.println("</p>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
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
            throws ServletException, IOException {
        //processRequest(request, response);
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Accion = request.getParameter("Accion");
        String res = "";
        ControladorMb51 controladorMb51 = new ControladorMb51();
        switch (Accion)
        {
            case "Planos":
                ControladorCargaPlanos controladorCargaPlanos = new ControladorCargaPlanos();
                res = controladorCargaPlanos.Upload(request, response);
                respuesta = res;
                request.setAttribute("respuesta", res);
                processRequest(request, response);
//            response.setCharacterEncoding("UT
                break;
            case "GetArchivosCompras":
                ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos();
                res = controladorEstadoPlanos.ValidarArchivos(request, response);
                PrintWriter pw = response.getWriter();
                pw.write(res);
                System.out.println(pw.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                break;
            case "GenerarArchivoCompras":
                ControladorExcel controladorExcel = new ControladorExcel();
                controladorExcel.Select("GenerarArchivoCompras", request, response);
                break;
        }
    }

    public static String ObtenerFecha() {
        Calendar fecHor = Calendar.getInstance();
        String dia = Integer.toString(fecHor.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(fecHor.get(Calendar.MONDAY) + 1);
        String año = Integer.toString(fecHor.get(Calendar.YEAR));
        String fh = (dia + "-" + mes + "-" + año);
        return fh;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
