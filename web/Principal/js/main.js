/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
$(function()
{

    
    function ValidaFieldsIndex(nit)
    {
        alert(nit)
        if (nit.length > 1)
        {
            return true;
        }
        else
        {
            alert("El campo NIT se encuentra Vacido");
            return false;
        }
        return false;
    }
    function validarFields(nit,nombre){
        if (nombre.length > 1 && nombre.length<=100){
            if (nit.length>1 && nit.length<=100){
                return true;                
            }
            else{
                alert("El formulario no cuenta con el Nit del Contratista");
            }
        }
        else{
            alert("El formulario no cuenta con el Nombre del contratista");
        }
        return false;
    }
    
    $('#IdAplicarAccion').click(function (e)
    {        
        var nit = $('#IdNit').val();        
        if (ValidaFieldsIndex(nit))        
        {
            var data = $('#IdAlohaHerramientasJSP').serialize();
            alert(data);
            console.log(data);
            
            $.post("InsertarHerramienta", data, function(res, est, jqXHR)
            {
                alert();
            });
        }       
    });
    
    $('#IdAplicar').click(function (e)
    {           
        var nombre = $('#IdNombre').val();
        var nit = $('#IdNit').val();
        if (validarFields(nit,nombre))        
        {
            var opcion = confirm("Desea Insertar la herramienta");            
            if (opcion)
            {
                var data = $('#IdAlohaHerramientasJSP').serialize();
                alert(data);
                console.log(data);
   
                $.post("InsertarHerramienta", data, function(res, est, jqXHR)
                {
                    alert();
                });
            } 
            else
            {
                alert(opcion)
            }
        }
        else
        {
            alert("Error")
        }
    });
    
    $('#IdSalHerramien').click(function (e)
    {           
        var nombre = $('#IdNombre').val();
        var nit = $('#IdNit').val();
        if (validarFields(nit,nombre))        
        {
            var opcion = confirm("Esta seguro de retirar la herramienta");            
            if (opcion)
            {
                var data = $('#IdAlohaHerramientasJSP').serialize();
                alert(data);
                console.log(data);
   
                $.post("InsertarHerramienta", data, function(res, est, jqXHR)
                {
                    alert();
                });
            } 
            else
            {
                alert(opcion)
            }
        }
        else
        {
            alert("Error")
        }
    });
});
//                var data = new FormData($('#IdAlohaHerramientasJSP')[0]);
//                console.log(data);
//                $.ajax
//                ({
//                    url: "InsertarHerramienta",
//                    type: "post",                    
//                    data: data,
//                    contentType:false,
//                    processData:false,
//                    success: function(data) 
//                    {
//                        alert(data)
//                    }
//                });
