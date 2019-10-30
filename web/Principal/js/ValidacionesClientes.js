$(function()
{
    $(document).ready(function() {
        LoadTabla();
    });

    $(document).on('click', '.SetFormulario', function() {
        $('#Id').val($(this).data('id'));
        $('#IdCodigo').val($(this).data('codigo'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdDireccion').val($(this).data('direccion'));
        $('#IdTelefono').val($(this).data('telefono'));
        $('#IdWeb').val($(this).data('web'));
        $('#IdComercial').val($(this).data('idcomercial'));
        $('#IdNotas').val($(this).data('notas'));
    });

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdCodigo').val() !== "")
        {
            if ($('#IdNombre').val() !== "")
            {
                if ($('#IdComercial').val() !== "0")
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
        $('#IdCodigo').val('');
        $('#IdNombre').val('');
        $('#IdDireccion').val('');
        $('#IdTelefono').val('');
        $('#IdWeb').val('');
        $('#IdComercial').val('');
        $('#IdNotas').val('');
    }

    $('#IdNuevo').click(function(e)
    {
        LimpiarCampos();
    });


    $('#IdCancelar').click(function(e)
    {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function(e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "ClienteJSP";
            var Id = $('#Id').val();
            var Codigo = $('#IdCodigo').val();
            var Nombre = $('#IdNombre').val();
            var Direccion = $('#IdDireccion').val();
            var Telefono = $('#IdTelefono').val();
            var Web = $('#IdWeb').val();
            var IdComercial = $('#IdComercial').val();
            var Notas = $('#IdNotas').val();
            var Accion = "Upload";
            var data = {
                frm: Frm,
                id: Id,
                codigo: Codigo,
                nombre: Nombre,
                direccion: Direccion,
                telefono: Telefono,
                web: Web,
                idComercial: IdComercial,
                notas: Notas,
                accion: Accion
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletAdminClientesTC",
                data: data,
                success: function(resul, textStatus, jqXHR)
                {
                    disableGif();
                    alert(resul);
                    LimpiarCampos();
                    LoadTabla();
                },
                error: function(jqXHR, textStatus, errorThrown) {
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
        else
        {
            alert("Favor de completar todos los campos");
        }
    });

    $("#IdEliminar").click(function(e) {
        if (ValidaCampo() === true)
        {
            var Frm = "ClienteJSP";
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
                url: "ServletAdminClientesTC",
                data: data,
                success: function(resul, textStatus, jqXHR)
                {
                    disableGif();
                    alert(resul);
                    LimpiarCampos();
                    LoadTabla();
                },
                error: function(jqXHR, textStatus, errorThrown) {
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
        else
        {
            alert("Favor de completar todos los campos");
        }
    });

    function LoadTabla()
    {
        var Frm = "ClienteJSP";
        var Accion = "Read";
        var data = {
            frm: Frm,
            accion: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "Pruebas",
            dataType: 'html',
            data: data,
            success: function(resul, textStatus, jqXHR)
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
            error: function(jqXHR, textStatus, errorThrown) {
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