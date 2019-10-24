/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{

    LimpiarCampos();


    $('#exampleModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('whatever') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-title').text('Consulta Usuario ' + recipient)
        modal.find('.modal-body input').val(recipient)
    })

    $(document).on('click', '.massshow-modal', function() {
        $('#Id').val($(this).data('id'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdPlantillaUrl').val($(this).data('plantillaurl'));
        $('#IdSql').val($(this).data('sql'));
    });

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
        var InfoOK = ValidaDatos();
        if (InfoOK === "True")
        {
            $('#IdAccion').val('Guardar');
            var data = $('#IdReportesJSP').serialize();
            $.post("ReportesServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Reporte guardado");
                    location.href = "Reportes.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar el Reporte");
                }
            });
        }
        else
        {
            alert("Favor completar todos los campos")
        }

    });

    $('#IdEliminar').click(function(e)
    {
        var InfoOK = ValidaDatos();

        if (InfoOK === "True")
        {
            var resConfir = confirm("Desea eliminar el reporte");
            if (resConfir)
            {
                $('#IdAccion').val('Eliminar');
                var data = $('#IdReportesJSP').serialize();
                console.log(data);
                $.post("ReportesServlet", data, function(res, est, jqHXR)
                {
                    var dt = res;
                    if (dt === "true")
                    {
                        alert("Reporte eliminado");
                        location.href = "Reportes.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Error al Eliminar el reporte");
                    }
                });
            }
        }
        else
        {
            alert("Favor completar todos los campos")
        }

    });

    function  LimpiarCampos()
    {
        $('#Id').val('');
        $('#IdPlantillaUrl').val('');
        $('#IdNombre').val('');
        $('#IdSql').val('');
    }


    function  ValidaDatos()
    {
        var res = "False";
        if ($('#IdNombre').val() !== "")
        {
            if ($('#IdPlantillaUrl').val() !== "")
            {
                if ($('#IdSql').val() !== "")
                {
                    res = "True"
                }
                else
                {
                    var res = "False";
                }
            }
            else
            {
                var res = "False";
            }
        }
        else
        {
            var res = "False";
        }
        return res;
    }

    function  ListarUser()
    {
        alert("hola carlos");
    }
});

