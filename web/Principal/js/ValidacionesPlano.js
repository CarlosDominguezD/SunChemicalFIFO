/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    $('#IdCargarPlano').click(function(e)
    {
        var InfoOK = ValidaDatos();
        if (InfoOK === "True")
        {
            $('#IdAccion').val('Guardar');
            var data = $('#IdCargarPlanoServlet').serialize();
            $.post("CargarPlanoServlet", data, function(res, est, jqHXR)
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
                    alert(dt);
                }
            });
        }
        else
        {
            alert("Favor completar todos los campos")
        }

    });

    function  ValidaDatos()
    {
        var res = "False";
        if ($('#Idarchivo').val() !== "")
        {
            res = "True";
        }
        else
        {
            var res = "False";
        }
        return res;
    }
});

