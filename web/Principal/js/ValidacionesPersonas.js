/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    LimpiarCampos();


//    $('#exampleModal').on('show.bs.modal', function(event) {
//        var button = $(event.relatedTarget) // Button that triggered the modal
//        var recipient = button.data('whatever') // Extract info from data-* attributes
//        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
//        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
//        var modal = $(this)
//        modal.find('.modal-title').text('Consulta Usuario ' + recipient)
//        modal.find('.modal-body input').val(recipient)
//    })

    $(document).on('click', '.massshow-modal', function() {
        $('#Id').val($(this).data('id'));
        $('#IdCodigo').val($(this).data('codigo'));
        $('#IdNombre').val($(this).data('nombre'));
        $('#IdApellido').val($(this).data('apellido'));
        $('#IdEstado').val($(this).data('estado'));
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
            var data = $('#IdPersonasJSP').serialize();
            $.post("PersonasServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Persona guardado");
                    location.href = "Personas.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar la persona");
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
            var resConfir = confirm("Desea eliminar el Usuario");
            if (resConfir)
            {
                $('#IdAccion').val('Eliminar');
                var data = $('#IdPersonasJSP').serialize();
                console.log(data);
                $.post("PersonasServlet", data, function(res, est, jqHXR)
                {
                    var dt = res;
                    if (dt === "true")
                    {
                        alert("Persona eliminada");
                        location.href = "Personas.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Error al Eliminar la persona");
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
        $('#IdApellido').val('');
        $('#IdPassword').val('');
    }


    function  ValidaDatos()
    {
        var res = "False";
        if ($('#IdCodigo').val() !== "")
        {
            if ($('#IdNombre').val() !== "")
            {
                if ($('#IdApellido').val() !== "")
                {
                    if ($('#IdEstado').val() !== "")
                    {
                        res = "True";
                    }
                    else
                    {
                        res = "False";
                    }
                }
                else
                {
                    res = "False";
                }
            }
            else
            {
                res = "False";
            }
        }
        else
        {
            res = "False";
        }
        return res;
    }
});

