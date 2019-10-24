/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function ()        
{
    $('#IdIngHerramienta').click(function (e)
    {
        var resConfir = confirm("Desea Ejecuatr la accion Ingresar Herramienta");
        if (resConfir)
        {
            alert(resConfir);
            var data = $('#IdPruebasJSP').serialize();
            alert(data);
            console.log(data);
            $.post("InsertarHerramienta",data,function (res, est,jqHXR)
            {
            });
        }
        else
        {
            alert(resConfir);
        }
    });
    
    function ValidaCampo(Campo)
    {
        if (Campo != null)
        {
            return true;
        }  
        else
        {
            alert("Favor de indicar que empleado esta realizando la accion");
            return false;
        }
    }
    
    $('#IdAplicarAccion').click(function (e)
    {        
        var ValCampo = $('#IdEmpleado').val();
        if (ValidaCampo(ValCampo))
        {
            var resConfir = confirm("Desea ejecutar la accion Aplicar Accicon");
            if (resConfir)
            {
                var data = $('#IdAlohaHerramientasJSP').serialize();                
                console.log(data);
                $.post("InsertarHerramienta",data,function (res, est,jqHXR)
                {
                    console.log(res);
                    alert(res);  
                    location.href = "dashboard.jsp";
                    evt.preventDefault();
                });
            }
            else
            {
                alert(resConfir);
            }
        }        
    });
    
    $('#IdAplicarAccionRetiro').click(function (e)
    {        
        var ValCampo = $('#IdEmpleado').val();        
        if (ValidaCampo(ValCampo))
        {
            var CantidadBD;
            var CantidadCliente;
            var Descripcion;
            var Acc = true;
            for (var i = 0; i < document.getElementById('Tabla').rows.length; i++) 
            {
                Descripcion = document.getElementById('Tabla').rows[i].cells[1].childNodes[0].value;
                CantidadBD = document.getElementById('Tabla').rows[i].cells[2].childNodes[0].value;
                CantidadCliente = document.getElementById('Tabla').rows[i].cells[3].childNodes[0].value;
                
                if ((parseInt(CantidadCliente) > parseInt(CantidadBD)) || ((parseInt(CantidadCliente) <=0)) || (CantidadCliente == "") ||
                        ((parseInt(CantidadBD) <=0)) || (CantidadBD == "") || (Descripcion == ""))
                {
                    alert("Error favor de verificar la informacion del formulario Descripcion: " + Descripcion);
                    Acc = false;
                }     
            }
            if(Acc)
            {            
                var resConfir = confirm("Desea ejecutar la accion Retiro Herramienta");
                if (resConfir)
                {
                    var data = $('#IdAlohaHerramientasJSP').serialize();                
                    console.log(data);
                    $.post("InsertarHerramienta",data,function (res, est,jqHXR)
                    {
                        alert(res);  
                        console.log(res)
                        location.href = "dashboard.jsp";
                        evt.preventDefault();
                    });
                }
                else
                {
                    alert(resConfir);
                }
            }
	}      
    }); 
});

