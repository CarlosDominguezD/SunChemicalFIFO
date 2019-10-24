/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    LimpiarCampos();

    selPersona = function(id, nombe) {
        alert("ddd");
        $('#IdCodigoBuscar').val(nombe);
    }

    $('#exampleModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var recipient = button.data('id')
        var id = button.data('id')
        var valor = button.data('valor')
        var fechaini = button.data('fechaini')
        var fechafin = button.data('fechafin')
        var observacion = button.data('observacion')
        var estado = button.data('estado')
        var persona = button.data('persona')
        var poliza = button.data('poliza')
        var modal = $(this)
        //modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body #IdModal').val(id)
        modal.find('.modal-body #IdModalNombre').val(persona)
        modal.find('.modal-body #IdModalPoliza').val(poliza)
        modal.find('.modal-body #IdModalValorPoliza').val(valor)
        modal.find('.modal-body #IdModalFechaInicio').val(fechaini)
        modal.find('.modal-body #IdModalFechaFin').val(fechafin)
        modal.find('.modal-body #IdModalEstado').val(estado)
        modal.find('.modal-body #IdModalObservacion').val(observacion)
    });

    $(document).on('click', '.massshow-modal', function() {
        $('#Id').val($(this).data('id'));
        $('#IdPersona').val($(this).data('idpersona'));
        $('#IdNombrePersona').val($(this).data('persona'));
        $('#IdPoliza').val($(this).data('idpoliza'));
        $('#IdNombrePoliza').val($(this).data('poliza'));
        $('#IdValorPoliza').val($(this).data('valor'));
        $('#IdEstado').val($(this).data('estado'));
        $('#IdObservacion').val($(this).data('observacion'));        
    });

    $('#IdNuevo').click(function(e)
    {
        LimpiarCampos();
    });

    $('#IdCancelar').click(function(e)
    {
        LimpiarCampos();
    });

    $('#IdUpdate').click(function(e)
    {
        var InfoOK = ValidaDatosModal();
        if (InfoOK === "True")
        {
            $('#IdAccion').val('Update');
            var data = $('#IdPersonaPolizaJSP').serialize();
            $.post("PersonaPolizaServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Registro guardado");
                    location.href = "PersonaPoliza.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar el registro");
                }
            });
        }
        else
        {
            alert("Favor completar todos los campos")
        }

    });

    $('#IdGuardar').click(function(e)
    {
        var InfoOK = ValidaDatos();
        if (InfoOK === "True")
        {
            $('#IdAccion').val('Guardar');
            var data = $('#IdPersonaPolizaJSP').serialize();
            $.post("PersonaPolizaServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Registro guardado");
                    location.href = "PersonaPoliza.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar el registro");
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
        var InfoOK = ValidaDatosModalId();

        if (InfoOK === "True")
        {
            var resConfir = confirm("Desea eliminar el Usuario");
            if (resConfir)
            {
                $('#IdAccion').val('Eliminar');
                var data = $('#IdPersonaPolizaJSP').serialize();
                console.log(data);
                $.post("PersonaPolizaServlet", data, function(res, est, jqHXR)
                {
                    var dt = res;
                    if (dt === "true")
                    {
                        alert("Registro eliminado");
                        location.href = "PersonaPoliza.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Error al Eliminar el registro");
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
        $('#IdPersona').val('');
        $('#IdPoliza').val('');
        $('#IdValorPoliza').val('');
        $('#IdFechaInicio').val('');
        $('#IdFechaFin').val('');
        $('#IdObservacion').val('');
        $('#IdEstado').val('');

    }

    function  ValidaDatosModal()
    {
        var res = "False";

        if ($('#IdModal').val() !== "")
        {
            if ($('#IdModalNombre').val() !== "")
            {
                if ($('#IdModalPoliza').val() !== "")
                {
                    if ($('#IdModalValorPoliza').val() !== "")
                    {
                        if ($('#IdModalFechaInicio').val() !== "")
                        {
                            if ($('#IdModalFechaFin').val() !== "")
                            {
                                if ($('#IdModalEstado').val() !== "")
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


    function  ValidaDatosModalId()
    {
        var res = "False";

        if ($('#IdModal').val() !== "")
        {
            res = "True";
        }
        else
        {
            var res = "False";
        }
        return res;
    }
    function  ValidaDatos()
    {
        var res = "False";

        if ($('#IdPersona').val() !== "")
        {
            if ($('#IdPoliza').val() !== "")
            {
                if ($('#IdValorPoliza').val() !== "")
                {
                    if ($('#IdFechaInicio').val() !== "")
                    {
                        if ($('#IdFechaFin').val() !== "")
                        {
                            if ($('#IdEstado').val() !== "")
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

