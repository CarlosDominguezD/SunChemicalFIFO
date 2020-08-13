/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function ()
{
    $(document).ready(function () {
        LoadTabla();
    });

    $(document).on('click', '.SetFormulario', function () {
        $('#Id').val($(this).data('id'));
        $('#IdAno').val($(this).data('ano'));
        $('#IdMes').val($(this).data('mes'));
        $('#IdConversionLabor').val($(this).data('labor'));
        $('#IdConversionMachine').val($(this).data('machine'));
        $('#IdConversionOvhds').val($(this).data('ovhds'));
    });

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdConversionLabor').val() !== "")
        {
            if ($('#IdConversionMachine').val() !== "")
            {
                if ($('#IdConversionOvhds').val() !== "")
                {
                    res = true;
                }
            }
        }
        return res;
    }

    function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#IdAno').val('');
        $('#IdMes').val('');
        $('#IdConversionLabor').val('');
        $('#IdConversionMachine').val('');
        $('#IdConversionOvhds').val('');
    }

    $('#IdAgregar').click(function (e)
    {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "ConversionesJSP";
            var Id = $('#Id').val();
            var IdMes = $('#IdMes').val();
            var IdAno = $('#IdAno').val();
            var IdConversionLabor = $('#IdConversionLabor').val();
            var IdConversionMachine = $('#IdConversionMachine').val();
            var IdConversionOvhds = $('#IdConversionOvhds').val();
            var Accion = "Upload";
            var data = {
                Accion: Frm,
                id: Id,
                mes: IdMes,
                ano: IdAno,
                conversionlabor: IdConversionLabor,
                conversionmachine: IdConversionMachine,
                conversionovhds: IdConversionOvhds,
                evento: Accion
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
                    LoadTabla();
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
        } else
        {
            alert("Favor de completar todos los campos");
        }
    });



    $(document).on('click', '.SetEliminar', function () {
        var Frm = "PermisosRolJSP";
        var Id = $(this).data('id');
        var Accion = "Delete";
        var data = {
            Accion: Frm,
            id: Id,
            evento: Accion
        };
        var opcion = confirm("Desea eliminar el item ");
        if (opcion === true) {
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
                    LoadTabla();
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

    $("#IdEliminar").click(function (e) {
        if (ValidaCampo() === true)
        {
            var Frm = "PermisosRolJSP";
            var Id = $('#Id').val();
            var Accion = "Delete";
            var data = {
                frm: Frm,
                id: Id,
                accion: Accion
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
                    LoadTabla();
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
        } else
        {
            alert("Favor de completar todos los campos");
        }

    });

    function LoadTabla()
    {
        var Frm = "ConversionesJSP";
        var Accion = "Read";
        var data = {
            Accion: Frm,
            evento: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletSunchemical",
            dataType: 'html',
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGif();
                $('#datatable').html(resul);
                $('#datatable').dataTable({
                    responsive: true,
                    language: {
                        "decimal": "",
                        "emptyTable": "No hay información",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Mostrar _MENU_ Entradas",
                        "loadingRecords": "Cargando...",
                        "processing": "Procesando...",
                        "search": "Buscar:",
                        "zeroRecords": "Sin resultados encontrados",
                        "paginate": {
                            "first": "Primero",
                            "last": "Ultimo",
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    }
                    , "autoWidth": false
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
                    , "scrollCollapse": true

                });
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

