/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    LimpiarCampos();

    selPersona = function(id, nombe) {
        $('#IdCodigoBuscar').val(nombe);
    }


    $('#exampleModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget) // Button that triggered the modal

        var id = button.data('id');
        var idPersona = button.data('idpersona');
        //var idPersonaPoliza = button.data('idpersonapoliza');
        var idMesFil = button.data('idmes');
        var idAnoFil = button.data('idano');
        var nombrePersona = button.data('nombrepersona');
        var cedula = button.data('cedula');
        var idPoliza = button.data('idpoliza');
        var codigoPoliza = button.data('codigopoliza')
        var nombrePoliza = button.data('nombrepoliza');
        var deducion = button.data('deducion');
        var pago = button.data('pago');
        var diferencia = button.data('diferencia');
        var debaja = button.data('debaja');
        var modal = $(this)
        //modal.find('.modal-title').text('New message to ' + recipient)
        modal.find('.modal-body #IdModal').val(id);
        modal.find('.modal-body #IdModalIdPersona').val(idPersona);
        //modal.find('.modal-body #IdModalPersonaPoliza').val(idPersonaPoliza);
        modal.find('.modal-body #IdModalNombrePersona').val(nombrePersona);
        modal.find('.modal-body #IdModalCedula').val(cedula);
        modal.find('.modal-body #IdModalIdPoliza').val(idPoliza);
        modal.find('.modal-body #IdModalCodigoPoliza').val(codigoPoliza);
        modal.find('.modal-body #IdModalNombrePoliza').val(nombrePoliza);
        modal.find('.modal-body #IdModalDeduccion').val(deducion);
        modal.find('.modal-body #IdModalPago').val(pago);
        modal.find('.modal-body #IdModalDiferencia').val(diferencia);
        modal.find('.modal-body #IdModalDeBaja').val(debaja);
        modal.find('.modal-body #IdModalMesFil').val(idMesFil);
        modal.find('.modal-body #IdModalAnoFil').val(idAnoFil);
    });

    $(document).on('click', '.massshow-modal', function() {
        $('#Id').val($(this).data('id'));
        $('#IdPersona').val($(this).data('idPersona'));

        $('#IdPersonaH1').val($(this).data('Si se puede'));

        $('#IdPoliza').val($(this).data('idPoliza'));
        $('#IdValorPoliza').val($(this).data('valor'));
        $('#IdFechaInicio').val($(this).data('fechaini'));
        $('#IdFechaFin').val($(this).data('fechafin'));
        $('#IdObservacion').val($(this).data('observacion'));
        $('#IdEstado').val($(this).data('estado'));
    });

    $('#IdBaja').click(function(e)
    {
        $('#datatable input[type=checkbox]:not(:checked').parents('tr').remove();
        var resConfir = confirm("Esta seguro de dar de baja los items seleccioados");
        if (resConfir)
        {
            $('#IdAccion').val('Baja');
            $('#IdFormato').val('Facturacion');
            var data = $('#IdResultadosJSP').serialize();
            //alert(data)
            $.post("ResultadosServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Los item se dieron de baja correctamente");
                    location.href = "Resultados.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error al dar de baja los item");
                }
            });
        }
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
            $('#IdFormato').val('Facturacion');
            var data = $('#IdResultadosJSP').serialize();
            //alert(data)
            $.post("CargarPlanoServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Registro Actualizado");
//                    location.href = "Resultados.jsp";
//                    evt.preventDefault();
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

//    $('#IdGuardar').click(function(e)
//    {
//        var InfoOK = ValidaDatos();
//        if (InfoOK === "True")
//        {
//            $('#IdAccion').val('Guardar');
//            var data = $('#IdPersonaPolizaJSP').serialize();
//            $.post("PersonaPolizaServlet", data, function(res, est, jqHXR)
//            {
//                var dt = res;
//                if (dt === "true")
//                {
//                    alert("Registro guardado");
//                    location.href = "PersonaPoliza.jsp";
//                    evt.preventDefault();
//                }
//                else
//                {
//                    alert("Error Guardar el registro");
//                }
//            });
//        }
//        else
//        {
//            alert("Favor completar todos los campos")
//        }

//    });

//    $('#IdEliminar').click(function(e)
//    {
//        var InfoOK = ValidaDatosModalId();
//
//        if (InfoOK === "True")
//        {
//            var resConfir = confirm("Desea eliminar el Usuario");
//            if (resConfir)
//            {
//                $('#IdAccion').val('Eliminar');
//                var data = $('#IdPersonaPolizaJSP').serialize();
//                console.log(data);
//                $.post("PersonaPolizaServlet", data, function(res, est, jqHXR)
//                {
//                    var dt = res;
//                    if (dt === "true")
//                    {
//                        alert("Registro eliminado");
//                        location.href = "PersonaPoliza.jsp";
//                        evt.preventDefault();
//                    }
//                    else
//                    {
//                        alert("Error al Eliminar el registro");
//                    }
//                });
//            }
//        }
//        else
//        {
//            alert("Favor completar todos los campos")
//        }
//
//    });

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


    function  ValidaDatosModalId()
    {
        var res = "False";

        if ($('#IdModal').val() !== "")
        {
            res = "True";
        }
        else
        {
            res = "False";
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

    function  ListarUser()
    {
        alert("hola carlos");
    }
});

