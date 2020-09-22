$(function ()
{
    $(document).ready(function () {
        //LoadTabla();
    });

    $(document).on('click', '.SetFormulario', function () {
        $('#IdPlano').val($(this).data('id'));
        $('#IdNombrePlano').val($(this).data('nombreplano'));
        $('#IdFechaPlano').val($(this).data('fechacarga'));
    });

    function LimpiarCampos()
    {
        $('#IdAbrirFecha').val("");
        $('#IdMesAbrir').val("");
        $('#IdAnoAbrir').val("");
        $('#IdEstadoAbrir').val("");
        $('#IdFaseAbrir').val("");
    }

    function LimpiarCamposCerrar()
    {
        $('#IdFechaCerrar').val("");
        $('#IdMesCerrar').val("");
        $('#IdAnoCerrar').val("");
        $('#IdEstadoCerrar').val("");
        $('#IdFaseCerrar').val("");
    }

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdMes').val() !== "")
        {
            if ($('#IdAno').val() !== "")
            {
                if ($('#IdFase').val() !== "")
                {
                    res = true;
                }
            }
        }
        return res;
    }

    function ValidaCampoAbrir()
    {
        var res = false;
        if ($('#IdMesAbrir').val() !== "")
        {
            if ($('#IdAnoAbrir').val() !== "")
            {
                if ($('#IdFaseAbrir').val() !== "")
                {
                    if ($('#IdFechaAbrir').val() !== "")
                    {
                        if ($('#IdEstadoAbrir').val() === "Cerrado")
                        {
                            res = true;
                        }
                    }
                }
            }
        }
        return res;
    }
    function ValidaCampoCerrar()
    {
        var res = false;
        if ($('#IdMesCerrar').val() !== "")
        {
            if ($('#IdAnoCerrar').val() !== "")
            {
                if ($('#IdFaseCerrar').val() !== "")
                {
                    if ($('#IdFechaCerrar').val() !== "")
                    {
                        if ($('#IdEstadoCerrar').val() === "Abierto")
                        {
                            res = true;
                        }
                    }
                }
            }
        }
        return res;
    }

    $('#IdFiltrar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            LimpiarCampos();
            var Frm = "FechasJSP";
            var Accion = "Select";
            var Actividad = "Fechas";
            var Mes = $('#IdMes').val();
            var Ano = $('#IdAno').val();
            var Fase = $('#IdFase').val();
            var Estado = "Cerrado";
            var data = {
                Accion: Frm,
                evento: Accion,
                actividad: Actividad,
                Mes: Mes,
                Ano: Ano,
                Fase: Fase,
                Estado: Estado
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletSunchemical",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    //{\"Id\":25,\"Mes\":\"Enero\",\"Ano\":2020,\"EstadoCompras\":\"Cerrado
                    if (Object.keys(resul).length !== 0) {
                        $('#IdFechaAbrir').val(resul.Id);
                        $('#IdMesAbrir').val(resul.Mes);
                        $('#IdAnoAbrir').val(resul.Ano);
                        if (Fase === 'Compras') {
                            $('#IdFaseAbrir').val(Fase);
                            $('#IdEstadoAbrir').val(resul.EstadoCompras);
                        }
                        if (Fase === 'Produccion') {
                            $('#IdFaseAbrir').val(Fase);
                            $('#IdEstadoAbrir').val(resul.EstadoProduccion);
                        }
                        if (Fase === 'Inventario') {
                            $('#IdFaseAbrir').val(Fase);
                            $('#IdEstadoAbrir').val(resul.EstadoInventario);
                        }
                    } else
                    {
                        alert("No se encontraron resultados")
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    disableGif();
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

    $('#IdFiltrarCerrar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            LimpiarCampos();
            var Frm = "FechasJSP";
            var Accion = "Select";
            var Actividad = "Fechas";
            var Mes = $('#IdMes').val();
            var Ano = $('#IdAno').val();
            var Fase = $('#IdFase').val();
            var Estado = "Abierto";
            var data = {
                Accion: Frm,
                evento: Accion,
                actividad: Actividad,
                Mes: Mes,
                Ano: Ano,
                Fase: Fase,
                Estado: Estado
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletSunchemical",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    //{\"Id\":25,\"Mes\":\"Enero\",\"Ano\":2020,\"EstadoCompras\":\"Cerrado
                    if (Object.keys(resul).length !== 0) {
                        $('#IdFechaCerrar').val(resul.Id);
                        $('#IdMesCerrar').val(resul.Mes);
                        $('#IdAnoCerrar').val(resul.Ano);
                        if (Fase === 'Compras') {
                            $('#IdFaseCerrar').val(Fase);
                            $('#IdEstadoCerrar').val(resul.EstadoCompras);
                        }
                        if (Fase === 'Produccion') {
                            $('#IdFaseCerrar').val(Fase);
                            $('#IdEstadoCerrar').val(resul.EstadoProduccion);
                        }
                        if (Fase === 'Inventario') {
                            $('#IdFaseCerrar').val(Fase);
                            $('#IdEstadoCerrar').val(resul.EstadoInventario);
                        }
                    } else
                    {
                        alert("No se encontraron resultados")
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    disableGif();
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

    $('#IdAbrirFecha').click(function (e)
    {
        if (ValidaCampoAbrir() === true)
        {
            var Frm = "FechasJSP";
            var Accion = "AbrirFecha";
            var Actividad = "Fechas";
            var Id = $('#IdFechaAbrir').val();
            var Fase = $('#IdFaseAbrir').val();
            var data = {
                Accion: Frm,
                evento: Accion,
                actividad: Actividad,
                Id: Id,
                Fase: Fase
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletSunchemical",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    alert(resul);
                    LimpiarCampos();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    disableGif();
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

    $('#IdCerrarFecha').click(function (e)
    {
        if (ValidaCampoCerrar() === true)
        {
            var Frm = "FechasJSP";
            var Accion = "CerrarFecha";
            var Actividad = "Fechas";
            var Id = $('#IdFechaCerrar').val();
            var Fase = $('#IdFaseCerrar').val();
            var data = {
                Accion: Frm,
                evento: Accion,
                actividad: Actividad,
                Id: Id,
                Fase: Fase
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletSunchemical",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    alert(resul);
                    LimpiarCamposCerrar()();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    disableGif();
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

    function enableGif()
    {
        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none"
    }
    function disableGif()
    {
        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }
});




