$(function ()
{
    $(document).ready(function () {
        LoadTabla();
    });

    $(document).on('click', '.SetFormulario', function () {
        $('#Id').val($(this).data('id'));
        $('#IdCodigo').val($(this).data('codigo'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdUsuario').val($(this).data('usuario'));
        $('#IdClave').val($(this).data('clave'));
    });

    function ValidaCampo()
    {
        var res = false;
        if ($('#IdCodigo').val() !== "")
        {
            if ($('#IdNombre').val() !== "")
            {
                if ($('#IdUsuario').val() !== "")
                {
                    if ($('#IdClave').val() !== "")
                    {
                        res = true;
                    }
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
        $('#IdUsuario').val('');
        $('#IdClave').val('');
    }

    $('#IdAgregar').click(function (e)
    {
        LimpiarCampos();
    });

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            var Frm = "UsuarioJSP";
            var Id = $('#Id').val();
            var codigo = $('#IdCodigo').val();
            var nombre = $('#IdNombre').val();
            var usuario = $('#IdUsuario').val();
            var clave = $('#IdClave').val();
            var idrol = $('#IdRol').val();            
            var Accion = "Upload";
            var data = {
                Accion: Frm,
                id: Id,
                codigo: codigo,
                nombre: nombre,
                usuario: usuario,
                clave: clave,
                idrol: idrol,
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
        var Frm = "UsuarioJSP";
        var Id = $(this).data('id');
        var Nombre = $(this).data('nombre');
        var Accion = "Delete";
        var data = {
            Accion: Frm,
            id: Id,
            nombre: Nombre,
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

//    $("#IdEliminar").click(function (e) {
//        if (ValidaCampo() === true)
//        {
//            var Frm = "RolesJSP";
//            var Id = $('#Id').val();
//            var Codigo = $('#IdCodigo').val();
//            var Nombre = $('#IdNombre').val();
//            var Accion = "Delete";
//            var data = {
//                frm: Frm,
//                id: Id,
//                codigo: Codigo,
//                nombre: Nombre,
//                accion: Accion
//            };
//            enableGif();
//            $.ajax({
//                type: "POST",
//                url: "ServletSunchemical",
//                data: data,
//                success: function (resul, textStatus, jqXHR)
//                {
//                    disableGif();
//                    alert(resul);
//                    LimpiarCampos();
//                    LoadTabla();
//                },
//                error: function (jqXHR, textStatus, errorThrown) {
//                    disableGif();
//                    if (jqXHR.status === 0) {
//                        alert('Not connect: Verify Network.');
//                    } else if (jqXHR.status === 404) {
//                        alert('Requested page not found [404]');
//                    } else if (jqXHR.status === 500) {
//                        alert('Internal Server Error [500].');
//                    } else if (textStatus === 'parsererror') {
//                        alert('Requested JSON parse failed.');
//                    } else if (textStatus === 'timeout') {
//                        alert('Time out error.');
//                    } else if (textStatus === 'abort') {
//                        alert('Ajax request aborted.');
//                    } else {
//                        alert('Uncaught Error: ' + jqXHR.responseText);
//                    }
//                }
//            });
//        } else
//        {
//            alert("Favor de completar todos los campos");
//        }
//
//    });

    function LoadTabla()
    {
        var Frm = "UsuarioJSP";
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
