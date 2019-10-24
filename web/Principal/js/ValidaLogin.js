/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function()
{
    $('#IdIngresar').click(function (e)
    {       
        if ($('#Idusuario').val() !=="")
        {
            if ($('#Idpassword').val() !== "")
            {
                $('#IdAccion').val('Ingresar');
                var data = $('#IdIndexJsp').serialize();   
                $.post("UsuariosServlet",data,function (res, est,jqHXR)
                {   
                    var dt = res;                       
                    if(dt === "true")
                    {
                        location.href = "Dashboard.jsp";
                        evt.preventDefault();
                    }
                    else
                    {
                        alert("Por favor verifique el usuario y la contraseña");  
                    }
                });
            }
            else
            {
                alert("Ingrese el Password");       
            }
        }
        else
        {
            alert("Ingrese el Usuario");      
        }
    });
});
