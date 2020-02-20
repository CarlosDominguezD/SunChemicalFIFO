<%-- 
    Document   : Pruebas
    Created on : 29-oct-2019, 8:16:06
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>         
        <%@include file="Principal/Head.html" %>     
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesCompras.js" ></script> 
    </head>
    <body class="nav-md" onload="">        
        <%@include file="Principal/Body.jsp" %>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Descargar Reporte Compras</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form class="form-horizontal form-label-left input_mask" action="ServletSunchemical" method="POST" name="ComprasJSP" enctype="multipart/form-data" id="IdComprasJSP" >                            
                                <div align="center" id="espera" style="display: none">
                                    <img src="Principal/images/loading.gif">
                                </div>
                                <div id="Principal">                                                                                                                                                                         
                                    <div class="x_panel">
                                        <table id="datatable" class="table table-striped table-bordered">                                  
                                            <thead>
                                                <tr>
                                                    <th>Nombre Plano</th>
                                                    <th>Fecha Carga</th>
                                                    <th>Estado</th>                                                                                                                                                       
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>                                                    
                                                    <td WIDTH = "0" HEIGHT="0"></td>                                     
                                                    <td WIDTH = "0" HEIGHT="0"></td>                                     
                                                    <td WIDTH = "0" HEIGHT="0"></td>                                                                                         
                                                </tr> 
                                            </tbody>
                                        </table>                                           
                                    </div>
                                </div>
                                <div class="text-center" id="botonCargar">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button type="submit" class="btn btn-primary" id="IdGenerarPlano" name="Accion" value="GenerarArchivoCompras">Generar Plano</button>                                        
                                    </div>                        
                                </div>
                                <script type="text/javascript">
                                    function enableGif()
                                    {
                                        window.onload = document.getElementById("espera").style = "display: block";
                                        window.onload = document.getElementById("botonCargar").style = "display: none"
                                        window.onload = document.getElementById("Principal").style = "display: none"
                                    }
                                </script>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Principal/Script.html" %>       
    </body>   
</html>