$(function ()
{
    function ValidaCampo()
    {
        var res = false;
        if ($('#IdClaveActual').val() !== "")
        {
            if ($('#IdClaveNueva').val() !== "")
            {
                if ($('#IdClaveConfirma').val() !== "")
                {
                    res = true;
                }
            }
        }
        return res;
    }

    function ConfirmaCleve()
    {
        var res = false;
        if ($('#IdClave').val() === $('#IdClaveActual').val())
        {
            if ($('#IdClaveNueva').val() === $('#IdClaveConfirma').val())
            {
                res = true;
            }
        }
        return res;
    }

    function  LimpiarCampos()
    {
        $('#IdClaveConfirma').val('');
        $('#IdClaveNueva').val('');
        $('#IdClaveActual').val('');
    }

    $('#IdGuardar').click(function (e)
    {
        if (ValidaCampo() === true)
        {
            if (ConfirmaCleve() === true)
            {
                var Frm = "UsuarioJSP";
                var Id = $('#Id').val();
                var codigo = $('#IdCodigo').val();
                var nombre = $('#IdNombre').val();
                var usuario = $('#IdUsuario').val();
                var clave = $('#IdClaveNueva').val();
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
                $.ajax({
                    type: "POST",
                    url: "ServletSunchemical",
                    data: data,
                    success: function (resul, textStatus, jqXHR)
                    {
                        alert(resul);                        
                        LimpiarCampos();
                        location.href = "index.jsp";

                    },
                    error: function (jqXHR, textStatus, errorThrown) {
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
                alert("Favor Verifique las claves estas no coinciden");
            }
        } else
        {
            alert("Favor de completar todos los campos");
        }
    });
});