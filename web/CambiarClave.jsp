<%-- 
    Document   : PermisosRol
    Created on : 19-feb-2020, 14:30:41
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelos.ModeloItemMenu"%>
<%@page import="Controlador.ControladorItemMenu"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorRoles"%>
<%@page import="Modelos.ModeloRoles"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
        <script type="text/javascript" src="Principal/js/jsfifo/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/jsfifo/ValidacionesCambiarClave.js" ></script> 
    </head>
    <body class="nav-md">
        <%
            Modelos.ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            if (modeloUsuarios != null) {
        %> 
        <%@include file="Principal/Body.jsp" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Cambiar Clave</h3>
                    </div>
                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> 
                            <!--div class="input-group">
                                <input type="text" class="form-control" placeholder="Buscar...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div-->
                        </div>
                    </div>
                </div>
                <!-- Primera Sección-->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Configuración</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a></a>
                                    </li>
                                    <li class="dropdown">
                                        <a><i>Ocultar</i></a>
                                    </li>
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <br/>
                                <!-- Formulario Configuración -->
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div id="Principal">
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id" value="<%=modeloUsuarios.getId()%>">                                            
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="cedula">Código</label>
                                                <input type="number" class="form-control" id="IdCodigo" name="Codigo" min="0" value="<%=modeloUsuarios.getCedula()%>" disabled>                                                
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="nombre">Nombre</label>
                                                <input type="text" class="form-control" id="IdNombre" name="Nombre" value="<%=modeloUsuarios.getNombre()%>"disabled>
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="Usuario">Usuario</label>
                                                <input type="text" class="form-control" id="IdUsuario" name="Usuario" value="<%=modeloUsuarios.getUsuario()%>" disabled>                                                
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group" hidden="">
                                                <label for="Clave">Clave</label>
                                                <input type="text" class="form-control" id="IdClave" name="Clave" value="<%=modeloUsuarios.getClave()%>" disabled>                                                
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="Clave">Clave Actual</label>
                                                <input type="password" class="form-control" id="IdClaveActual" name="Clave">                                                
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="Clave">Nueva Clave</label>
                                                <input type="password" class="form-control" id="IdClaveNueva" name="Clave">                                                
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="Clave">Confirme Clave</label>
                                                <input type="password" class="form-control" id="IdClaveConfirma" name="Clave">                                                
                                            </div>  
                                            <div class="col-md-0 col-sm-12 col-xs-12 form-group" hidden="">
                                                <select class="form-control" id="IdRol" name="Rol" disabled>                                                       
                                                    <%
                                                        ControladorRoles controladorRoles = new ControladorRoles();
                                                        LinkedList<ModeloRoles> listmodeloRoles = controladorRoles.Read();
                                                        for (ModeloRoles modeloRoles : listmodeloRoles) {
                                                    %>
                                                    <option value="<%=modeloUsuarios.getIdRol()%>"><%=modeloUsuarios.getIdRol()%></option> 
                                                    <%
                                                        }
                                                    %>  
                                                </select>
                                            </div>
                                        </div>
                                        <!-- Formulario Configuración -->      
                                        <!-- Botones -->
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-12 col-sm-12 col-xs-12 col-md-offset-5">                                                
                                                <button class="btn btn-success btn-sm" type="button" id="IdGuardar" name="Guardar"><i class="fa fa-save"></i> Guardar</button>                                                                                                
                                                <br/><br/>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <div class="col-md-8 col-sm-8 col-xs-12 col-md-offset-1">
                                                            <!--button class="btn btn-warning btn-sm" type="button"><i class="fa fa-edit"></i> Editar</button-->
                                                            <!--button class="btn btn-dark btn-sm" type="button" disabled><i class="fa fa-trash"></i> Eliminar</button-->
                                                        </div>
                                                    </div>
                                                </div>         
                                            </div>
                                        </div>
                                        <!-- /Botones -->
                                    </div>
                                </form>
                                <!-- /Formulario Registro -->
                            </div>
                        </div>
                        <!-- /Primera Sección-->
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido -->
        <!-- Footer -->
        <footer>
            <br/>
            <div class="clearfix"></div>
        </footer>
        <!-- Footer -->
        <%@include file="Principal/Script.html" %>  
        <%} else {
        %>
        <script src="Principal/js/jsfifo/jquery.min.js"></script>  
        <script>
            location.href = "index.jsp";
        </script>  
        <%
            }
        %>
    </body>
</html>
