$(function ()
{
    $(document).ready(function () {
        LoadTabla();
    });
//    $('#IdGenerarPlano').click(function (e)
//    {
//        var Frm = "ConprasJSP";
//        var Accion = "GenerarArchivoCompras";
//        var Actividad = "Compras";
//        var data = {
//            frm: Frm,
//            Accion: Accion,
//            actividad: Actividad
//        };
//        enableGif();
//        $.ajax({
//            type: "POST",
//            url: "ServletSunchemical",
//            data: data,
//            success: function (resul, textStatus, jqXHR)
//            {
//                disableGif();
//                alert(resul);                 
//                window.open(resul, 'Download');
//            },
//            error: function (jqXHR, textStatus, errorThrown) {
//                disableGif();
//                if (jqXHR.status === 0) {
//                    alert('Not connect: Verify Network.');
//                } else if (jqXHR.status === 404) {
//                    alert('Requested page not found [404]');
//                } else if (jqXHR.status === 500) {
//                    alert('Internal Server Error [500].');
//                } else if (textStatus === 'parsererror') {
//                    alert('Requested JSON parse failed.');
//                } else if (textStatus === 'timeout') {
//                    alert('Time out error.');
//                } else if (textStatus === 'abort') {
//                    alert('Ajax request aborted.');
//                } else {
//                    alert('Uncaught Error: ' + jqXHR.responseText);
//                }
//            }
//        });
//    });

    function LoadTabla()
    {
        var Frm = "ConprasJSP";
        var Accion = "GetArchivosCompras";
        var Actividad = "Compras";
        var data = {
            frm: Frm,
            Accion: Accion,
            actividad: Actividad
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

