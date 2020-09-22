/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controlador.ControladorAuditoria;
import Controlador.ControladorCargaPlanos;
import Controlador.ControladorCargaPlanosProduccion;
import Controlador.ControladorCargarPlanosInventario;
import Controlador.ControladorConversiones;
import Controlador.ControladorEstadoPlanos;
import Controlador.ControladorExcel;
import Controlador.ControladorFechas;
import Controlador.ControladorMb51;
import Controlador.ControladorPermisosRol;
import Controlador.ControladorRoles;
import Controlador.ControladorUsuarios;
import Controlador.ControladorVendorType;
import Herramienta.Herramienta;
import Modelos.ModeloUsuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos A Dominguez D
 */
@MultipartConfig
public class ServletSunchemical extends HttpServlet {

    String respuesta = "false";
    String jsp = "false";

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
        try (PrintWriter out = response.getWriter()) {
            /*
             * TODO output your page here. You may use following sample code.
             */
            if ("true".equals(request.getAttribute("respuesta"))) {
                if ("CargaplanosCompras".equals(request.getAttribute("jsp"))) {
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
                    out.println("<p>Para retornar al formulario ==> <a href=CargaplanosCompras.jsp>Principal</a>");
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
                if ("CargaplanosProduccion".equals(request.getAttribute("jsp"))) {
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
                    out.println("<p>Para retornar al formulario ==> <a href=CargaplanosProduccion.jsp>Principal</a>");
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
                if ("CargaplanosInventario".equals(request.getAttribute("jsp"))) {
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
                    out.println("<p>Para retornar al formulario ==> <a href=CargaplanosInventario.jsp>Principal</a>");
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
            } else {
                if ("CargaplanosCompras".equals(request.getAttribute("jsp"))) {
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
                    out.println("<p>Para retornar al formulario ==> <a href=CargaplanosCompras.jsp>Principal</a>");
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
                if ("CargaplanosProduccion".equals(request.getAttribute("jsp"))) {
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
                    out.println("<p>Para retornar al formulario ==> <a href=CargaplanosProduccion.jsp>Principal</a>");
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
                if ("CargaplanosInventario".equals(request.getAttribute("jsp"))) {
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
                    out.println("<p>Para retornar al formulario ==> <a href=CargaplanosInventario.jsp>Principal</a>");
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
        String Resultado = "";
        ControladorMb51 controladorMb51 = new ControladorMb51();
        Herramienta herramienta = new Herramienta();
        String evento;
        ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos();
        ControladorExcel controladorExcel = new ControladorExcel();
        switch (Accion) {
            case "GetSolicitudEvento":
                Resultado = herramienta.getEventoProcesado();
                response.getWriter().write(Resultado);
                break;
            case "CargarPlanosCompras":
                ControladorCargaPlanos controladorCargaPlanos = new ControladorCargaPlanos();
                 {
                    try {
                        res = controladorCargaPlanos.Upload(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                respuesta = res;
                request.setAttribute("respuesta", res);
                request.setAttribute("jsp", "CargaplanosCompras");
                processRequest(request, response);
                break;
            case "CargarPlanosProduccion":
                ControladorCargaPlanosProduccion controladorCargaPlanosProduccion = new ControladorCargaPlanosProduccion();
                 {
                    try {
                        System.err.println("Ingresa en Servlet");
                        res = controladorCargaPlanosProduccion.Upload(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                respuesta = res;
                request.setAttribute("respuesta", res);
                request.setAttribute("jsp", "CargaplanosProduccion");
                processRequest(request, response);
                break;
            case "CargarPlanosInventario":
                ControladorCargarPlanosInventario controladorCargaPlanosInventario = new ControladorCargarPlanosInventario();
                 {
                    try {
                        res = controladorCargaPlanosInventario.Upload(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletSunchemical.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                respuesta = res;
                request.setAttribute("respuesta", res);
                request.setAttribute("jsp", "CargaplanosInventario");
                processRequest(request, response);
                break;
            case "ConprasJSP":
                //ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos ();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Update":
                        Resultado = controladorEstadoPlanos.UpdateIncativoEstadoPlanos(request);
                        break;
                    case "Read":
                        Resultado = controladorEstadoPlanos.ValidarArchivos(request, response, "MB51");
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "ProduccionJSP":
                //ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos ();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Update":
                        Resultado = controladorEstadoPlanos.UpdateIncativoEstadoPlanos(request);
                        break;
                    case "Read":
                        Resultado = controladorEstadoPlanos.ValidarArchivos(request, response, "KOB1");
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "InventarioJSP":
                //ControladorEstadoPlanos controladorEstadoPlanos = new ControladorEstadoPlanos ();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Update":
                        Resultado = controladorEstadoPlanos.UpdateIncativoEstadoPlanos(request);
                        break;
                    case "Read":
                        Resultado = controladorEstadoPlanos.ValidarArchivos(request, response, "MCBR");
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "GenerarArchivoCompras":
                //ControladorExcel controladorExcel = new ControladorExcel ();
                controladorExcel.Select("GenerarArchivoCompras", request, response);
                break;
            case "GenerarArchivoProduccion":
                //ControladorExcel controladorExcel = new ControladorExcel ();
                controladorExcel.Select("GenerarArchivoProduccion", request, response);
                break;
            case "GenerarArchivoInventario":
                //ControladorExcel controladorExcel = new ControladorExcel ();
                controladorExcel.Select("GenerarArchivoInventario", request, response);
                break;
            case "VendorTypeJSP":
                ControladorVendorType controladorVendorType = new ControladorVendorType();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Upload":
                        Resultado = controladorVendorType.Insert(request);
                        break;
                    case "Delete":
                        Resultado = controladorVendorType.Delete(request);
                        break;
                    case "Read":
                        Resultado = controladorVendorType.ReadVendorType(request, response);
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "RolesJSP":
                ControladorRoles controladorRoles = new ControladorRoles();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Upload":
                        Resultado = controladorRoles.Insert(request);
                        break;
                    case "Delete":
                        Resultado = controladorRoles.Delete(request);
                        break;
                    case "Read":
                        Resultado = controladorRoles.ReadRoles(request, response);
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "ConversionesJSP":
                ControladorConversiones controladorConversiones = new ControladorConversiones();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Upload":
                        Resultado = controladorConversiones.Insert(request);
                        break;
//                    case "Delete":
//                        Resultado = controladorConversiones.Delete(request);
//                        break;
                    case "Read":
                        Resultado = controladorConversiones.Read(request, response);
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "PermisosRolJSP":
                ControladorPermisosRol controladorPermisosRol = new ControladorPermisosRol();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Upload":
                        Resultado = controladorPermisosRol.Insert(request);
                        break;
                    case "Delete":
                        Resultado = controladorPermisosRol.Delete(request);
                        break;
                    case "Read":
                        Resultado = controladorPermisosRol.ReadRoles(request, response);
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "UsuarioJSP":
                ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Upload":
                        Resultado = controladorUsuarios.Insert(request);
                        break;
                    case "Delete":
                        Resultado = controladorUsuarios.Delete(request);
                        break;
                    case "Read":
                        Resultado = controladorUsuarios.ReadRoles(request, response);
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "LoginJSP":
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Ingresar":
                        ModeloUsuario modeUsuario = new ModeloUsuario();
                        ControladorUsuarios controladorUsuario = new ControladorUsuarios();
                        modeUsuario = controladorUsuario.Select(request);
                        //request.setAttribute("modeloUsuario", modeUsuario);
                        if (modeUsuario.getId() != 0) {
                            HttpSession session = request.getSession();
                            session.setAttribute("user", modeUsuario);
                            Resultado = "3";

                        } else {
                            Resultado = "-4";
                        }
                        break;
                }
                break;
            case "AuditoriaJSP":
                ControladorAuditoria controladorAuditoria = new ControladorAuditoria();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Read":
                        Resultado = controladorAuditoria.ReadAuditoria(request, response);
                        PrintWriter pwr = response.getWriter();
                        pwr.write(Resultado);
                        System.out.println(pwr.checkError() ? "Error al cargar la lista" : "Tabla Cargada");
                        break;
                }
                break;
            case "FechasJSP":
                ControladorFechas controladorFechas = new ControladorFechas();
                evento = request.getParameter("evento");
                switch (evento) {
                    case "Select":
                        Resultado = controladorFechas.GetModelo(request, response);
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write(Resultado);
                        Accion = "JSON";
                        respuesta = Resultado;
                        break;
                    case "AbrirFecha":
                        Resultado = controladorFechas.GetAbrirFecha(request, response);
                        //respuesta = Resultado;
                        break;
                    case "CerrarFecha":
                        Resultado = controladorFechas.GetCerrarFecha(request, response);
                        //respuesta = Resultado;
                        break;
                }
                break;
        }
        if ((!"JSON".equals(Accion)) & (!"GenerarArchivoCompras".equals(Accion)) & (!"GetSolicitudEvento".equals(Accion))) {
            String respuesta = herramienta.GetDescrpCode(Resultado);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(respuesta);
        }
    }

    public static String ObtenerFecha() {
        Calendar fecHor = Calendar.getInstance();
        String dia = Integer.toString(fecHor.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(fecHor.get(Calendar.MONDAY) + 1);
        String ano = Integer.toString(fecHor.get(Calendar.YEAR));
        String hh = Integer.toString(fecHor.get(Calendar.HOUR));
        String mm = Integer.toString(fecHor.get(Calendar.MINUTE));
        String ss = Integer.toString(fecHor.get(Calendar.SECOND));
        String fh = (dia + "-" + mes + "-" + ano + "_" + hh + mm + ss);
        return fh;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
