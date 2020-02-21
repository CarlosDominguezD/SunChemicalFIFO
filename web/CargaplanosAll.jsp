<%-- 
    Document   : CargarPlanos
    Created on : 04-jul-2019, 19:50:06
    Author     : Carlos A Dominguez D
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
                            <h2>Carga Planos</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />                            
                            <form method="post" action="UploadServlet" enctype="multipart/form-data">
                                <fieldset>
                                    <div align="center">
                                        <input type="file" autofocus class="btn btn-lg btn-primary" onchange="nombre(this.value)" style=""name="files" id="files" multiple/>                                                                            
                                    </div>
                                </fieldset>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>
                                <br>                         
                                <div align="center" id="botonCargar">
                                    <input type="submit" value="Cargar" class="btn btn-lg btn-primary" onclick = "enableGif()">       
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
    <%@include file="Principal/Validaciones.html" %>        
</html>