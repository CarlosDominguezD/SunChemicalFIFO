/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    LimpiarCampos();

    $(document).on('click', '.massshow-modal', function() {
        $('#Id').val($(this).data('id'));
//        $('#IdPoliza').val($(this).data('idpoliza'));
//        $('#IdOption').val($(this).data('nombrepoliza'));
        $('#IdPoliza').val($(this).data('idpoliza'));
        //$('#IdOption').val($(this).data('nombrepoliza'));
        $('#IdDescripcion').val($(this).data('descripcion'));
        $('#IdCodigoNomina').val($(this).data('codigonomina'));
        $('#IdCodigoReintegro').val($(this).data('codigoreintegro'));
        $('#IdCodigoCorredor').val($(this).data('codigocorredor'));
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
            var data = $('#IdGrupoPolizaJSP').serialize();
            $.post("GrupoPolizaServlet", data, function(res, est, jqHXR)
            {
                var dt = res;
                if (dt === "true")
                {
                    alert("Grupo Poliza Guardado");
                    location.href = "GrupoPoliza.jsp";
                    evt.preventDefault();
                }
                else
                {
                    alert("Error Guardar el grupo poliza");
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
                var data = $('#IdGrupoPolizaJSP').serialize();
                $.post("GrupoPolizaServlet", data, function(res, est, jqHXR)
                {
                    var dt = res;
                    if (dt === "true")
                    {
                        alert("Poliza eliminada");
                        location.href = "GrupoPoliza.jsp";
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

    $('#IdUpdate').click(function(e)
    {
        var InfoOK = ValidaDatosModal();

        if (InfoOK === "True")
        {
            var resConfir = confirm("Desea actualizar el Grupo Poliza");
            if (resConfir)
            {
                $('#IdAccion').val('Update');
                var data = $('#IdGrupoPolizaJSP').serialize();
                $.post("GrupoPolizaServlet", data, function(res, est, jqHXR)
                {
                    var dt = res;
                    if (dt === "true")
                    {
                        alert("Poliza Actualizada");
                        location.href = "GrupoPoliza.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Error al Actualizar la Poliza");
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
        $('#IdPoliza').val('');
        $('#IdOption').val('');
        $('#IdDescripcion').val('');
        $('#IdCodigoNomina').val('');
        $('#IdCodigoReintegro').val('');
        $('#IdCodigoCorredor').val('');
    }


    function  ValidaDatos()
    {
        var res = "False";
        if ($('#IdDescripcion').val() !== "")
        {
            if ($('#IdCodigoNomina').val() !== "")
            {
                if ($('#IdCodigoReintegro').val() !== "")
                {
                    if ($('#IdCodigoCorredor').val() !== "")
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

