<%-- 
    Document   : Body
    Created on : 19-feb-2020, 11:45:15
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelos.ModeloItemMenu"%>
<%@page import="Modelos.ModeloUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <br>
                <div class="navbar nav_title" style="border: 0;">
                    <!--a href="Dashboard.jsp" class="site_title"><span><img src="Principal/images/sunchemical-logo.png"  width="180" height="50" class="responsive-img"/></span> </a-->
                    <a href="Dashboard.jsp" class="site_title"><span><img src="Principal/images/menu_bg.png"  width="180" height="50" class="responsive-img"/></span> </a>
                </div>
                <div class="clearfix"></div>
                <br />
                <!-- sidebar menu -->                
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <%ModeloUsuario modeloUsuario = (ModeloUsuario) request.getSession().getAttribute("user");%>
                        <h3>Usuario: <%=modeloUsuario.getNombre()%></h3>
                        <ul class="nav side-menu">
                            <% try {
                                    String ItemAdministracion = "false";
                                    String ItemConfiguracion = "false";
                                    String ItemRoles = "false";
                                    String ItemPermisosRol = "false";
                                    String ItemUsuarios = "false";
                                    String ItemProveedores = "false";
                                    String ItemMateriales = "false";
                                    String ItemVendorType = "false";
                                    String ItemCargaPlanos = "false";
                                    String ItemCargaPlanosCompras = "false";
                                    String ItemCargaPlanosProduccion = "false";
                                    String ItemCargaPlanosInventario = "false";
                                    String ItemCargaPlanosAll = "false";
                                    String ItemPlanos = "false";
                                    String ItemResultados = "false";
                                    String ItemCompras = "false";
                                    String ItemProduccion = "false";
                                    String ItemInventario = "false";
                                    String ItemAuditoria = "false";
                                    String ItemCargaplanosBarra = "false";
                                    String ItemGestionCompras = "false";
                                    String ItemConversiones = "false";
                                    String ItemAbrirFecha = "false";
                                    String ItemCerrarFecha = "false";
                                    if (modeloUsuario != null) {
                                        for (ModeloItemMenu modelo : modeloUsuario.getListModeloItemMenu()) {
                                            if ("ItemAdministracion".equals(modelo.getNombre())) {
                                                ItemAdministracion = "true";
                                            }
                                            if ("ItemConfiguracion".equals(modelo.getNombre())) {
                                                ItemConfiguracion = "true";
                                            }
                                            if ("ItemRoles".equals(modelo.getNombre())) {
                                                ItemRoles = "true";
                                            }
                                            if ("ItemPermisosRol".equals(modelo.getNombre())) {
                                                ItemPermisosRol = "true";
                                            }
                                            if ("ItemUsuarios".equals(modelo.getNombre())) {
                                                ItemUsuarios = "true";
                                            }
                                            if ("ItemProveedores".equals(modelo.getNombre())) {
                                                ItemProveedores = "true";
                                            }
                                            if ("ItemMateriales".equals(modelo.getNombre())) {
                                                ItemMateriales = "true";
                                            }
                                            if ("ItemVendorType".equals(modelo.getNombre())) {
                                                ItemVendorType = "true";
                                            }
                                            if ("ItemCargaPlanos".equals(modelo.getNombre())) {
                                                ItemCargaPlanos = "true";
                                            }
                                            if ("ItemCargaPlanosCompras".equals(modelo.getNombre())) {
                                                ItemCargaPlanosCompras = "true";
                                            }
                                            if ("ItemCargaPlanosProduccion".equals(modelo.getNombre())) {
                                                ItemCargaPlanosProduccion = "true";
                                            }
                                            if ("ItemCargaPlanosInventario".equals(modelo.getNombre())) {
                                                ItemCargaPlanosInventario = "true";
                                            }
                                            if ("ItemPlanos".equals(modelo.getNombre())) {
                                                ItemPlanos = "true";
                                            }
                                            if ("ItemResultados".equals(modelo.getNombre())) {
                                                ItemResultados = "true";
                                            }
                                            if ("ItemCompras".equals(modelo.getNombre())) {
                                                ItemCompras = "true";
                                            }
                                            if ("ItemProduccion".equals(modelo.getNombre())) {
                                                ItemProduccion = "true";
                                            }
                                            if ("ItemInventario".equals(modelo.getNombre())) {
                                                ItemInventario = "true";
                                            }
                                            if ("ItemAuditoria".equals(modelo.getNombre())) {
                                                ItemAuditoria = "true";
                                            }
                                            if ("ItemCargaPlanosAll".equals(modelo.getNombre())) {
                                                ItemCargaPlanosAll = "true";
                                            }
                                            if ("ItemCargaplanosBarra".equals(modelo.getNombre())) {
                                                ItemCargaplanosBarra = "true";
                                            }
                                            if ("ItemGestionCompras".equals(modelo.getNombre())) {
                                                ItemGestionCompras = "true";
                                            }
                                            if ("ItemConversiones".equals(modelo.getNombre())) {
                                                ItemConversiones = "true";
                                            }
                                            if ("ItemAbrirFecha".equals(modelo.getNombre())) {
                                                ItemAbrirFecha = "true";
                                            }
                                            if ("ItemCerrarFecha".equals(modelo.getNombre())) {
                                                ItemCerrarFecha = "true";
                                            }
                                        }
                                        if ("true".equals(ItemAdministracion)) {
                            %>
                            <li id="Administracion"><a><i class="fa fa-cog"></i>Administracion<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <%
                                        if ("true".equals(ItemConfiguracion)) {
                                    %>
                                    <li><a>Configuracion<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">                                            
                                            <%      }

                                                if ("true".equals(ItemRoles)) {
                                            %>
                                            <li class="sub_menu"><a href="Roles.jsp">Roles</a>
                                            </li>                                           
                                            <%      }

                                                if ("true".equals(ItemPermisosRol)) {
                                            %>
                                            <li><a href="PermisosRol.jsp">Permisos Rol</a>
                                            </li>                                          
                                            <%      }

                                                if ("true".equals(ItemUsuarios)) {
                                            %>
                                            <li><a href="Usuarios.jsp#">Usuarios</a>
                                            </li>                                          
                                            <%      }
                                                if ("true".equals(ItemGestionCompras)) {
                                            %>
                                            <li><a href="GestionCompras.jsp#">Gestion Compras</a>
                                            </li>                                          
                                            <%      }
                                                if ("true".equals(ItemAuditoria)) {
                                            %>
                                            <li><a href="Auditoria.jsp#">Auditoria</a>
                                            </li>                                          
                                            <%      }

                                                if ("true".equals(ItemAbrirFecha)) {
                                            %>
                                            <li><a href="AbrirFechas.jsp#">Abrir Fechas</a>
                                            </li>                                          
                                            <%      }
                                                if ("true".equals(ItemCerrarFecha)) {
                                            %>
                                            <li><a href="CerrarFechas.jsp#">Cerrar Fechas</a>
                                            </li>                                          
                                            <%      }

                                                if ("true".equals(ItemConfiguracion)) {
                                            %>

                                        </ul> 
                                    </li>
                                    <%      }

                                        if ("true".equals(ItemConversiones)) {
                                    %>
                                    <li><a href="Conversiones.jsp">Conversiones</a></li>
                                        <%      }
                                            if ("true".equals(ItemVendorType)) {
                                        %>
                                    <li><a href="Vendortype.jsp">Vendortype</a></li>
                                        <%      }

                                        %>
                                </ul>
                            </li>
                            <%      }

                                if ("true".equals(ItemCargaPlanos)) {
                            %>
                            <li><a><i class="fa fa-upload"></i>Cargar Planos<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">

                                    <%
                                        if ("true".equals(ItemCargaPlanosCompras)) {
                                    %>
                                    <li> <a href = "CargaplanosCompras.jsp" > Carga Planos Compras </a></li>

                                    <%      }
                                        if ("true".equals(ItemCargaPlanosProduccion)) {
                                    %>
                                    <li> <a href = "CargaplanosProduccion.jsp" > Carga Planos Produccion </a></li>

                                    <%      }
                                        if ("true".equals(ItemCargaPlanosInventario)) {
                                    %>
                                    <li> <a href = "CargaplanosInventario.jsp" > Carga Planos Inventario </a></li>

                                    <%      }
                                        if ("true".equals(ItemCargaPlanosAll)) {
                                    %>
                                    <li> <a href = "CargaplanosAll.jsp" > Carga Masiva Planos</a></li>

                                    <%      }
                                        if ("true".equals(ItemCargaplanosBarra)) {
                                    %>
                                    <li> <a href = "CargaplanosBarra.jsp" > Carga Planos barra</a></li>

                                    <%      }
                                    %>
                                </ul>
                            </li>
                            <%      }
                                if ("true".equals(ItemResultados)) {
                            %>
                            <li><a><i class="fa fa-download"></i>Resultados<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <%
                                        if ("true".equals(ItemCompras)) {
                                    %>
                                    <li><a href="Compras.jsp">Compras</a></li>
                                        <%      }
                                            if ("true".equals(ItemProduccion)) {
                                        %>
                                    <li><a href="Produccion.jsp">Produccion</a></li>
                                        <%      }
                                            if ("true".equals(ItemInventario)) {
                                        %>
                                    <li><a href="Inventario.jsp">Inventario</a></li>
                                        <%      }
                                        %>
                                </ul>
                            </li>
                            <%      }

                                    }//if

                                } catch (Exception e) {
                                    System.out.println("error className.methodName()" + e);
                                }

                            %>                            
                        </ul>
                    </div>
                </div>            
            </div>
        </div>
        <!-- Submenu superior. -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>                                
                    </div>   
                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a id="idusera" href="#" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img id="idimg" src="Principal/images/user.png" alt="">Administración
                                <span class="fa fa-angle-double-down"></span>
                            </a>
                            <input type="hidden" id="idUsAct" name="UsAct">
                            <ul class="dropdown-menu dropdown-usermenu pull-right">                                
                                <li><a id="idcambiarpw" href="CambiarClave.jsp"><i class="fa fa-key pull-right"></i>Cambiar clave</a></li>                                
                                <li><a id="idcambiarpw" href=CerrarSeccion.jsp><i class="fa fa-close pull-right"></i>Salir</a></li>                                
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>      
        <!-- /Submenu superior. -->        
