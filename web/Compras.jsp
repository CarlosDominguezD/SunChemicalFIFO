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
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Mes</label>
                                                <select class="form-control" id="IdMes" name="Mes">                                                                                        
                                                    <option value="Enero">Enero</option>
                                                    <option value="Febrero">Febrero</option>
                                                    <option value="Marzo">Marzo</option>
                                                    <option value="Abril">Abril</option>
                                                    <option value="Mayo">Mayo</option>
                                                    <option value="Junio">Junio</option>
                                                    <option value="Julio">Julio</option>
                                                    <option value="Agosto">Agosto</option>
                                                    <option value="Septiembre">Septiembre</option>
                                                    <option value="Octubre">Octubre</option>
                                                    <option value="Noviembre">Noviembre</option>
                                                    <option value="Diciembre">Diciembre</option>
                                                </select>
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="codigo">Año</label>
                                                <select class="form-control" id="IdAno" name="Ano">                                                                                        
                                                    <option value="2020">2020</option>
                                                    <option value="2021">2021</option>
                                                    <option value="2022">2022</option>
                                                    <option value="2023">2023</option>
                                                    <option value="2024">2024</option>
                                                    <option value="2025">2025</option>
                                                    <option value="2026">2026</option>
                                                    <option value="2027">2027</option>
                                                    <option value="2028">2028</option>
                                                    <option value="2029">2029</option>
                                                    <option value="2030">2030</option>
                                                </select>
                                            </div>
                                        </div>
                                        <br>
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