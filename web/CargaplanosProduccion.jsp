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
        <%
            Modelos.ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            if (modeloUsuarios != null) {
        %>        
        <%@include file="Principal/Body.jsp" %>
        <script type="text/javascript" src="Principal/js/jquery.min.js" ></script>
        <style>
            textarea {
                overflow-y: scroll;
                height: 500px;
                resize: vertical; /* Remove this if you want the user to resize the textarea */
            }
        </style>
        <div class="right_col" role="main">
            <div class="">            
                <div class="clearfix"></div>
                <div class="row">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Carga Planos Produccion</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <br />
                            <form action="ServletSunchemical" method="POST" enctype="multipart/form-data" 
                                  id="IdCargarPlanoServlet" name="cargaplanosproduccion" onsubmit="return checkSubmit();">
                                <input type="hidden" id="IdAccion">     
                                <input type="hidden" id="IdFechaHoraEnvio" name="FechaHoraEnvio" value="">     
                                <input type="hidden" id="IdNombrePlano" name="NombrePlano" value=""> 
                                <input type="hidden" id="Reprocesar" name="Reprocesar" value="false">
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
                                <!--input type="checkbox" class="form-check-input" id="exampleCheck1" name="exampleCheck1"-->
                                <div align="center" id="botonCargar">
                                    <input type="submit" class="btn btn-lg btn-primary" onclick = "GetEventos()" name="Accion" value="CargarPlanosProduccion">
                                <!--button type="button" class="btn btn-danger" id="IdCargarProduccion" name="Accion" value="CargarProduccion" >Cargar Plano</button-->
                                    
                                </div>
                                <div align="center" id="espera" style="display: none">
                                    <img src="Principal/images/loading.gif">
                                </div>
                                <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" align = "center">
                                    <label for="ListaEventos">Lista de Eventos</label>
                                    <textarea class="form-control" id="IdlistaEventos" name="listaEventos" rows="10"></textarea>
                                </div>
                                <div class="col-lg-7" style="text-align: right">                                        
                                    <button type="button" class="btn btn-dark" id="IdConsultarEstado" name="Accion" value="Consultar" >Consultar proceso</button>                                        
                                </div>
                                <div class="col-lg-5" style="text-align: right">                                        
                                    <button type="button" class="btn btn-danger" id="IdReprocesarProduccion" name="Accion" value="ReprocesarProduccion" >Reprocesar Mes</button>                                        
                                </div>
                            </form>
                            <script type="text/javascript">

                                $(document).ready(function () {
                                    //Consultamos si el sistema esta cargand algo 
                                    var Accion = "GetSolicitudEvento";
                                    var data = {
                                        Accion: Accion
                                    };
                                    $.ajax({
                                        type: "POST",
                                        url: "ServletSunchemical",
                                        data: data,
                                        success: function (resul, textStatus, jqXHR) {
                                            if (dato !== resul)
                                            {
                                                if (resul !== "--") {
                                                    GetEventos()
                                                }
                                            }
                                        },
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            if (jqXHR.status === 0) {
                                                alert('Not connect: Verify Network.');
                                            } else if (jqXHR.status === 404) {
                                                alert('Requested page not found [404]');
                                            } else if (jqXHR.status === 500) {
                                                alert('Internal Server Error [500].');
                                            } else if (textStatus === 'parsererror') {
                                                alert('Requested JSON parse failed.');
                                            } else if (textStatus === 'timeout') {
                                                alert('Time out error.');
                                            } else if (textStatus === 'abort') {
                                                alert('Ajax request aborted.');
                                            } else {
                                                alert('Uncaught Error: ' + jqXHR.responseText);
                                            }
                                        }
                                    });
                                });
                                
                                
                                
                                
                                
                                $('#IdCargarProduccion').click(function (e) {
                                    
                                    //IdFechaHoraEnvio
                                    //IdNombrePlano
                                    //Reprocesar
                                    //Idarchivo
                                    
                                    var Accion = "CargarPlanosProduccion";
                                    var Mes = $('#IdMes').val();
                                    var Ano = $('#IdAno').val();
                                    var isChecked = document.getElementById('exampleCheck1').checked;
                                    //alert(isChecked);                                                                        
                                    $('#exampleCheck1').prop('checked', false);
                                    //isChecked = document.getElementById('exampleCheck1').checked;
                                    //alert(isChecked);

                                    if (Mes === "0" || Ano === "0")
                                    {
                                        alert("Debe seleccionar Mes y Año");
                                    } else
                                    {
                                        GetEventos();
                                        var data = {
                                            Accion: Accion,
                                            Mes: Mes,
                                            Ano: Ano,
                                            Check : isChecked
                                        };
                                        $.ajax({
                                            type: "POST",
                                            url: "ServletSunchemical",
                                            data: data,
                                            success: function (resul, textStatus, jqXHR) {
                                                if (dato !== resul)
                                                {
                                                    if (resul !== "--") {
                                                        GetEventos();
                                                    }
                                                }
                                            },
                                            error: function (jqXHR, textStatus, errorThrown) {
                                                if (jqXHR.status === 0) {
                                                    alert('Not connect: Verify Network.');
                                                } else if (jqXHR.status === 404) {
                                                    alert('Requested page not found [404]');
                                                } else if (jqXHR.status === 500) {
                                                    alert('Internal Server Error [500].');
                                                } else if (textStatus === 'parsererror') {
                                                    alert('Requested JSON parse failed.');
                                                } else if (textStatus === 'timeout') {
                                                    alert('Time out error.');
                                                } else if (textStatus === 'abort') {
                                                    alert('Ajax request aborted.');
                                                } else {
                                                    alert('Uncaught Error: ' + jqXHR.responseText);
                                                }
                                            }
                                        });
                                    }
                                });
                                
                                
                                

                                $('#IdReprocesarProduccion').click(function (e) {
                                    var Accion = "ReprocesarProduccion";
                                    var Mes = $('#IdMes').val();
                                    var Ano = $('#IdAno').val();
                                    var Reprocesar = true;

                                    if (Mes === "0" || Ano === "0")
                                    {
                                        alert("Debe seleccionar Mes y Año");
                                    } else
                                    {
                                        GetEventos();
                                        var data = {
                                            Accion: Accion,
                                            Mes: Mes,
                                            Ano: Ano,
                                            Reprocesar: Reprocesar
                                        };
                                        $.ajax({
                                            type: "POST",
                                            url: "ServletSunchemical",
                                            data: data,
                                            success: function (resul, textStatus, jqXHR) {
                                                if (dato !== resul)
                                                {
                                                    if (resul !== "--") {
                                                        GetEventos();
                                                    }
                                                }
                                            },
                                            error: function (jqXHR, textStatus, errorThrown) {
                                                if (jqXHR.status === 0) {
                                                    alert('Not connect: Verify Network.');
                                                } else if (jqXHR.status === 404) {
                                                    alert('Requested page not found [404]');
                                                } else if (jqXHR.status === 500) {
                                                    alert('Internal Server Error [500].');
                                                } else if (textStatus === 'parsererror') {
                                                    alert('Requested JSON parse failed.');
                                                } else if (textStatus === 'timeout') {
                                                    alert('Time out error.');
                                                } else if (textStatus === 'abort') {
                                                    alert('Ajax request aborted.');
                                                } else {
                                                    alert('Uncaught Error: ' + jqXHR.responseText);
                                                }
                                            }
                                        });
                                    }
                                });

                                var statSend = false;
                                function checkSubmit() {
                                    if (!statSend) {
                                        statSend = true;
                                        return true;
                                    } else {
                                        alert("El archivo ya se está procesando...");
                                        return false;
                                    }
                                }

                                var dato = 'Ininciando';
                                function GetEventos() {
                                    setInterval(StartSolicitudEvento, 100);
                                }

                                $('#IdConsultarEstado').click(function (e)
                                {
                                    GetEventos();
                                });

                                function StartSolicitudEvento()
                                {
                                    document.cargaplanosproduccion.Accion.disabled = true;
                                    var Accion = "GetSolicitudEvento";
                                    var data = {
                                        Accion: Accion
                                    };
                                    $.ajax({
                                        type: "POST",
                                        url: "ServletSunchemical",
                                        data: data,
                                        success: function (resul, textStatus, jqXHR) {
                                            if (dato !== resul)
                                            {
                                                document.cargaplanosproduccion.listaEventos.value += resul + '\n';
                                                dato = resul;
                                            }
                                        },
                                        error: function (jqXHR, textStatus, errorThrown) {
                                            if (jqXHR.status === 0) {
                                                alert('Not connect: Verify Network.');
                                            } else if (jqXHR.status === 404) {
                                                alert('Requested page not found [404]');
                                            } else if (jqXHR.status === 500) {
                                                alert('Internal Server Error [500].');
                                            } else if (textStatus === 'parsererror') {
                                                alert('Requested JSON parse failed.');
                                            } else if (textStatus === 'timeout') {
                                                alert('Time out error.');
                                            } else if (textStatus === 'abort') {
                                                alert('Ajax request aborted.');
                                            } else {
                                                alert('Uncaught Error: ' + jqXHR.responseText);
                                            }
                                        }
                                    });

                                }
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

                                    var hoy = new Date();
                                    var fechahora = hoy.getDate() + '' + (hoy.getMonth() + 1) + '' + hoy.getFullYear() + '' + hoy.getHours() + '' + hoy.getMinutes() + '' + hoy.getSeconds();
                                    $('#IdFechaHoraEnvio').val(fechahora);
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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