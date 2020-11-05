$(function () {
    $('#IdAgregarConsulta').click(function (e) {
        var item = $('#IdItem').val();
        var Operador = $('#IdOperador').val();
        var Valor = $('#IdValor').val();
        var Conector = $('#IdConector').val();
        var Consulta = $('#IdConsulta').val();
        Consulta += item + " " + Operador + " " + Valor + " " + Conector + " ";
        $('#IdConsulta').val(Consulta);
    });

    $('#IdUpdate').click(function (e) {
        var Frm = "MB51_ConsumosFormularioJSP";
        var Id = $('#IdModal').val();
        var Plant = $('#IdPlant').val();
        var Purchase_order = $('#IdPurchase_order').val();
        var Material = $('#IdMaterial').val();
        var Material_Description = $('#IdMaterial_Description').val();
        var Batch = $('#IdBatch').val();
        var Movement_type = $('#IdMovement_type').val();
        var Movement_Type_Text = $('#IdMovement_Type_Text').val();
        var Item = $('#IdItemFormulario').val();
        var Quantity = $('#IdQuantity').val();
        var Qty_in_unit_of_entry = $('#IdQty_in_unit_of_entry').val();
        var Unit_of_Entry = $('#IdUnit_of_Entry').val();
        var Amt_in_loc_cur = $('#IdAmt_in_loc_cur').val();
        var Currency = $('#IdCurrency').val();
        var Storage_Location = $('#IdStorage_Location').val();
        var Posting_Date = $('#IdPosting_Date').val();
        var Document_Date = $('#IdDocument_Date').val();
        var Material_Document = $('#IdMaterial_Document').val();
        var User_Name = $('#IdUser_Name').val();
        var Vendor = $('#IdVendor').val();
        var Order_ = $('#IdOrder_').val();
        var Accion = "Upload";
        var data = {
            Accion: Frm,
            Id: Id,
            Plant: Plant,
            Purchase_order: Purchase_order,
            Material: Material,
            Material_Description: Material_Description,
            Batch: Batch,
            Movement_type: Movement_type,
            Movement_Type_Text: Movement_Type_Text,
            Item: Item,
            Quantity: Quantity,
            Qty_in_unit_of_entry: Qty_in_unit_of_entry,
            Unit_of_Entry: Unit_of_Entry,
            Amt_in_loc_cur: Amt_in_loc_cur,
            Currency: Currency,
            Storage_Location: Storage_Location,
            Posting_Date: Posting_Date,
            Document_Date: Document_Date,
            Material_Document: Material_Document,
            User_Name: User_Name,
            Vendor: Vendor,
            Order_: Order_,
            evento: Accion
        };
        enableGifModal();
        $.ajax({
            type: "POST",
            url: "ServletSunchemical",
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGifModal();
                alert(resul);
                ReloadTable();
                $('#IdModalEdit').modal('hide');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGifModal();
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
    });

    $('#IdCosultarFormulario').click(function (e) {
        if (ValidamosCampo() === true)
        {
            $("#datatable tr").remove();
            var Frm = "MB51_ConsumosFormularioJSP";
            var Whent = $('#IdConsulta').val();
            var Accion = "Read";
            var data = {
                Accion: Frm,
                Whent: Whent,
                evento: Accion
            };
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletSunchemical",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    disableGif();
                    $('#datatable').html(resul);
                    $('#datatable').dataTable({
                        responsive: true,
                        language: {
                            "decimal": "",
                            "emptyTable": "No hay información",
                            "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                            "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                            "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                            "infoPostFix": "",
                            "thousands": ",",
                            "lengthMenu": "Mostrar _MENU_ Entradas",
                            "loadingRecords": "Cargando...",
                            "processing": "Procesando...",
                            "search": "Buscar:",
                            "zeroRecords": "Sin resultados encontrados",
                            "paginate": {
                                "first": "Primero",
                                "last": "Ultimo",
                                "next": "Siguiente",
                                "previous": "Anterior"
                            }
                        }
                        , "autoWidth": false
                        , "destroy": true
                        , "info": true
                        , "JQueryUI": true
                        , "ordering": true
                        , "paging": true
                        , "scrollY": "500px"
                        , "scrollX": true
                        , "scrollCollapse": true

                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    disableGif();
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
        }
    });

    $(document).on('click', '.SetFormulario', function () {
        $('#IdModalEdit').modal('show'); // abrir        
        $('#IdModal').val($(this).data('id'));
        $('#IdPlant').val($(this).data('plant'));
        $('#IdPurchase_order').val($(this).data('purchase_order'));
        $('#IdMaterial').val($(this).data('material'));
        $('#IdMaterial_Description').val($(this).data('material_description'));
        $('#IdBatch').val($(this).data('batch'));
        $('#IdMovement_type').val($(this).data('movement_type'));
        $('#IdMovement_Type_Text').val($(this).data('movement_type_text'));
        $('#IdItemFormulario').val($(this).data('item'));
        $('#IdQuantity').val($(this).data('quantity'));
        $('#IdQty_in_unit_of_entry').val($(this).data('qty_in_unit_of_entry'));
        $('#IdUnit_of_Entry').val($(this).data('unit_of_entry'));
        $('#IdAmt_in_loc_cur').val($(this).data('amt_in_loc_cur'));
        $('#IdCurrency').val($(this).data('currency'));
        $('#IdStorage_Location').val($(this).data('storage_location'));
        $('#IdPosting_Date').val($(this).data('posting_date'));
        $('#IdDocument_Date').val($(this).data('document_date'));
        $('#IdMaterial_Document').val($(this).data('material_document'));
        $('#IdUser_Name').val($(this).data('user_name'));
        $('#IdVendor').val($(this).data('vendor'));
        $('#IdOrder_').val($(this).data('order_'));
    });

    $(document).on('click', '.SetEliminar', function () {
        var Frm = "MB51_ConsumosFormularioJSP";
        $('#IdModal').val($(this).data('id'));
        var Accion = "Delete";
        var data = {
            Accion: Frm,
            Id: $('#IdModal').val(),
            evento: Accion
        };
        var opcion = confirm("Desea eliminar el item ");
        if (opcion === true) {
            enableGif();
            $.ajax({
                type: "POST",
                url: "ServletSunchemical",
                data: data,
                success: function (resul, textStatus, jqXHR)
                {
                    ReloadTable();
                    disableGif();
                    alert(resul);
                    //LimpiarCampos();
                    LoadTabla();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    disableGif();
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
        }
    });

    function ReloadTable() {
        var Frm = "MB51_ConsumosFormularioJSP";
        var Whent = $('#IdConsulta').val();
        var Accion = "Read";
        var data = {
            Accion: Frm,
            Whent: Whent,
            evento: Accion
        };
        enableGif();
        $.ajax({
            type: "POST",
            url: "ServletSunchemical",
            data: data,
            success: function (resul, textStatus, jqXHR)
            {
                disableGif();
                $('#datatable').html(resul);
                $('#datatable').dataTable({
                    responsive: true,
                    language: {
                        "decimal": "",
                        "emptyTable": "No hay información",
                        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
                        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
                        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
                        "infoPostFix": "",
                        "thousands": ",",
                        "lengthMenu": "Mostrar _MENU_ Entradas",
                        "loadingRecords": "Cargando...",
                        "processing": "Procesando...",
                        "search": "Buscar:",
                        "zeroRecords": "Sin resultados encontrados",
                        "paginate": {
                            "first": "Primero",
                            "last": "Ultimo",
                            "next": "Siguiente",
                            "previous": "Anterior"
                        }
                    }
                    , "autoWidth": false
                    , "destroy": true
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": true
                    , "scrollY": "500px"
                    , "scrollX": true
                    , "scrollCollapse": true

                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                disableGif();
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
    }
    function ValidamosCampo() {
        var res = false;
        if ($('#IdConsulta').val() !== "")
        {
            res = true;
        }
        return res;
    }

    function enableGif()
    {
        window.onload = document.getElementById("espera").style = "display: block";
        window.onload = document.getElementById("Principal").style = "display: none"
    }
    function disableGif()
    {
        window.onload = document.getElementById("espera").style = "display: none";
        window.onload = document.getElementById("Principal").style = "display: enable"
    }

    function enableGifModal()
    {
        window.onload = document.getElementById("esperaModal").style = "display: block";
        window.onload = document.getElementById("PrincipalModal").style = "display: none"
    }
    function disableGifModal()
    {
        window.onload = document.getElementById("esperaModal").style = "display: none";
        window.onload = document.getElementById("PrincipalModal").style = "display: enable"
    }
});

