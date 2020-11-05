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
        var Frm = "Fbl3NFormularioJSP";
        var Id = $('#IdModal').val();
        var Document_Number = $('#IdDocument_Number').val();
        var Document_type = $('#IdDocument_type').val();
        var Document_Date = $('#IdDocument_Date').val();
        var Posting_Date = $('#IdPosting_Date').val();
        var Cost_Center = $('#IdCost_Center').val();
        var Profit_Center = $('#IdProfit_Center').val();
        var YearMonth = $('#IdYearMonth').val();
        var Account = $('#IdAccount').val();
        var Tipodoc = $('#IdTipodoc').val();
        var Plant = $('#IdPlant').val();
        var Material = $('#IdMaterial').val();
        var Quantity = $('#IdQuantity').val();
        var Amount_in_local_currency = $('#IdAmount_in_local_currency').val();
        var Local_Currency = $('#IdLocal_Currency').val();
        var Purchasing_Document = $('#IdPurchasing_Document').val();
        var Reference = $('#IdReference').val();
        var Document_currency = $('#IdDocument_currency').val();
        var Offsetting_acct_no = $('#IdOffsetting_acct_no').val();
        var Base_Unit_of_Measure = $('#IdBase_Unit_of_Measure').val();
        var Alternative_Account_No = $('#IdAlternative_Account_No').val();
        var Transaction_Code = $('#IdTransaction_Code').val();
        var Text = $('#IdText').val();
        var Assignment = $('#IdAssignment').val();
        var Clasificacion = $('#IdClasificacion').val();
        var Accion = "Upload";
        var data = {
            Accion: Frm,
            Id: Id,
            Document_Number: Document_Number,
            Document_type: Document_type,
            Document_Date: Document_Date,
            Posting_Date: Posting_Date,
            Cost_Center: Cost_Center,
            Profit_Center: Profit_Center,
            YearMonth: YearMonth,
            Account: Account,
            Tipodoc: Tipodoc,
            Plant: Plant,
            Material: Material,
            Quantity: Quantity,
            Amount_in_local_currency: Amount_in_local_currency,
            Local_Currency: Local_Currency,
            Purchasing_Document: Purchasing_Document,
            Reference: Reference,
            Document_currency: Document_currency,
            Offsetting_acct_no: Offsetting_acct_no,
            Base_Unit_of_Measure: Base_Unit_of_Measure,
            Alternative_Account_No: Alternative_Account_No,
            Transaction_Code: Transaction_Code,
            Text: Text,
            Assignment: Assignment,
            Clasificacion: Clasificacion,
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

    function ReloadTable() {
        var Frm = "Fbl3NFormularioJSP";
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

    $('#IdCosultarFormulario').click(function (e) {
        if (ValidamosCampo() === true)
        {
            $("#datatable tr").remove();
            var Frm = "Fbl3NFormularioJSP";
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
        $('#IdDocument_Number').val($(this).data('document_number'));
        $('#IdDocument_type').val($(this).data('document_type'));
        $('#IdDocument_Date').val($(this).data('document_date'));
        $('#IdPosting_Date').val($(this).data('posting_date'));
        $('#IdCost_Center').val($(this).data('cost_center'));
        $('#IdProfit_Center').val($(this).data('profit_center'));
        $('#IdYearMonth').val($(this).data('yearmonth'));
        $('#IdAccount').val($(this).data('account'));
        $('#IdTipodoc').val($(this).data('tipodoc'));
        $('#IdPlant').val($(this).data('plant'));
        $('#IdMaterial').val($(this).data('material'));
        $('#IdQuantity').val($(this).data('quantity'));
        $('#IdAmount_in_local_currency').val($(this).data('amount_in_local_currency'));
        $('#IdLocal_Currency').val($(this).data('local_currency'));
        $('#IdPurchasing_Document').val($(this).data('purchasing_document'));
        $('#IdReference').val($(this).data('reference'));
        $('#IdDocument_currency').val($(this).data('document_currency'));
        $('#IdOffsetting_acct_no').val($(this).data('offsetting_acct_no'));
        $('#IdBase_Unit_of_Measure').val($(this).data('base_unit_of_measure'));
        $('#IdAlternative_Account_No').val($(this).data('alternative_account_no'));
        $('#IdTransaction_Code').val($(this).data('transaction_code'));
        $('#IdText').val($(this).data('text'));
        $('#IdAssignment').val($(this).data('assignment'));
        $('#IdClasificacion').val($(this).data('clasificacion'));
    });

    $(document).on('click', '.SetEliminar', function () {
        var Frm = "Fbl3NFormularioJSP";
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
                    disableGif();
                    alert(resul);
                    ReloadTable();
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

