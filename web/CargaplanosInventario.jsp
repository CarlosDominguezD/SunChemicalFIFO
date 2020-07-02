<%-- 
    Document   : CargaplanosInventario
    Created on : 1/07/2020, 11:41:17 AM
    Author     : Diego Fdo Guzman B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
    </head>
    <body class="nav-md">        
        <%@include file="Principal/Body.jsp" %>
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/ValidacionesPlano.js" ></script> 
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Carga Planos Inventario</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form action="ServletSunchemical" method="POST" enctype="multipart/form-data" id="IdCargarPlanoServlet" name="cargaplanoscompres">
                                <input type="hidden" id="IdAccion">     
                                <input type="hidden" id="IdNombrePlano" name="NombrePlano" value=""> 
                                <div class="row">
                                    <input type="hidden" id="Id" name="Id">
                                    <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                        <select class="form-control" id="IdMes" name="Mes">                                                                                        
                                            <option value=0></option>
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
                                        <select class="form-control" id="IdAno" name="Ano">  
                                            <option value=0></option>
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
                                <br></br>
                                <div  class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" align = "center">
                                    <input type="file" name="archivo" id="Idarchivo" accept="aplication/txt" autofocus class="btn btn-lg btn-primary" onchange="nombre(this.value)">
                                </div>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>
                                <div align="center" id="botonCargar">
                                    <input type="submit" class="btn btn-lg btn-primary" onclick = "enableGif()" name="Accion" value="CargarPlanosInventario">       
                                </div>
                                <div align="center" id="espera" style="display: none">
                                    <img src="Principal/images/loading.gif">
                                </div>
                            </form>
                            <script type="text/javascript">
                                function enableGif()
                                {
                                    window.onload = document.getElementById("espera").style = "display: block";
                                    window.onload = document.getElementById("botonCargar").style = "display: none"
                                    window.onload = document.getElementById("file").style = "display: none"
                                    window.onload = document.getElementById("tituloh3").style = "display: none"
                                }

                                function nombre(fic)
                                {
                                    fic = fic.split('\\');
                                    fic = fic[fic.length - 1].replace(".", ",")
                                    var separador = ","; // un espacio en blanco
                                    var limite = 1;
                                    var fic = fic.split(separador, limite);
                                    //alert(fic[fic.length - 1].replace(".", ","));
                                    $('#IdNombrePlano').val(fic);
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Principal/Script.html" %>
    </body>
</html>