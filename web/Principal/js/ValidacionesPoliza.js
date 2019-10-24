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
        $('#IdCodigo').val($(this).data('codigo'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdUsuario').val($(this).data('usuario'));
        $('#IdPassword').val($(this).data('password'));
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
            var data = $('#IdPolizasJSP').serialize();
            $.post("PolizaServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Poliza Guardada");
                    location.href = "Polizas.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar la poliza");
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
            var resConfir = confirm("Desea eliminar la poliza");
            if (resConfir)
            {
                $('#IdAccion').val('Eliminar');
                var data = $('#IdPolizasJSP').serialize();
                console.log(data);
                $.post("PolizaServlet", data, function(res, est, jqHXR)
                {
                    var dt = res;
                    if (dt === "true")
                    {
                        alert("Poliza eliminada");
                        location.href = "Polizas.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Error al Eliminar la Poliza");
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
        $('#IdCodigo').val('');
        $('#IdNombre').val('');
    }


    function  ValidaDatos()
    {
        var res = "False";
        if ($('#IdCodigo').val() !== "")
        {
            if ($('#IdNombre').val() !== "")
            {
               res = "True";
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
});

