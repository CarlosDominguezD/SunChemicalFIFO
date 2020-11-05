<%-- 
    Document   : FBL3N
    Created on : 03-nov-2020, 17:46:28
    Author     : Carlos A Dominguez D
--%>

<%@page import="Modelos.ModeloItemMenu"%>
<%@page import="Controlador.ControladorItemMenu"%>
<%@page import="java.util.LinkedList"%>
<%@page import="Controlador.ControladorRoles"%>
<%@page import="Modelos.ModeloRoles"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="Principal/Head.html" %>        
        <script type="text/javascript" src="Principal/js/jsfifo/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/jsfifo/ValidacionesMB51_ConsumosFormulario.js" ></script> 
        <style>
            #contenidoTabla {
                font-size: 10px;
            }
            td {
                font-size: 10px;
                color: black;
            }
            th {
                font-size: 10px;
                color:black;
            }
        </style>
    </head>
    <body class="nav-md">
        <%
            Modelos.ModeloUsuario modeloUsuarios = (ModeloUsuario) request.getSession().getAttribute("user");
            if (modeloUsuarios != null) {
        %>            
        <%@include file="Principal/Body.jsp" %>
        <!-- Contenido -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Conversiones</h3>
                    </div>
                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search"> 
                            <!--div class="input-group">
                                <input type="text" class="form-control" placeholder="Buscar...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div-->
                        </div>
                    </div>
                </div>
                <!-- Primera Sección-->
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Configuración</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a></a>
                                    </li>
                                    <li class="dropdown">
                                        <a><i>Ocultar</i></a>
                                    </li>
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <br/>
                                <!-- Formulario Configuración -->
                                <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
                                    <div align="center" id="espera" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div id="Principal">
                                        <div class="row">
                                            <input type="hidden" id="Id" name="Id">
                                            <div class="col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="Item">Item</label>
                                                <select class="form-control" id="IdItem" name="Item">                                                                                        
                                                    <option value=""></option>
                                                    <option value="Material">Material</option>
                                                    <option value="Order_">Order_</option>                                                    
                                                </select>
                                            </div>
                                            <div class="col-md-1 col-sm-12 col-xs-12 form-group">
                                                <label for="Operador">Operador</label>
                                                <select class="form-control" id="IdOperador" name="Operqador">  
                                                    <option value==></option>
                                                    <option value="=">=</option>
                                                    <option value=">">></option>
                                                    <option value="<"><</option>
                                                    <option value=">=">>=</option>
                                                    <option value="<="><=</option>
                                                    <option value="<>"><></option>                                                                                                                                
                                                </select>
                                            </div>
                                            <div class="col-md-6 col-sm-12 col-xs-12 form-group">
                                                <label for="Valor">Valor</label>
                                                <input type="text" class="form-control" id="IdValor" name="Valor" required="required">
                                            </div>                                            
                                            <div class="col-md-1 col-sm-12 col-xs-12 form-group">
                                                <label for="Conector">Conector</label>
                                                <select class="form-control" id="IdConector" name="Conector">  
                                                    <option value=""></option>
                                                    <option value="AND">AND</option>
                                                    <option value="OR">OR</option>                                                    
                                                </select>
                                            </div>                                            
                                        </div>
                                        <br/><br/>
                                        <div class="form-group" style="text-align: center">
                                            <div class="col-md-12 col-sm-12 col-xs-12">                                                
                                                <button class="btn btn-primary btn-sm" type="button" id="IdAgregarConsulta" name="AgregarConsulta"><i class="fa fa-plus"></i> Agregar a Consulta</button>                                                
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <div class="col-md-12 col-sm-12 col-xs-12 form-group">
                                                <label for="Consulta">Consulta</label>
                                                <textarea class="form-control" id="IdConsulta" rows="3"></textarea>
                                            </div>  
                                        </div>  
                                        <!-- Formulario Configuración -->      
                                        <!-- Botones -->
                                        <div class="ln_solid"></div>
                                        <div class="form-group" style="text-align: center">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <button class="btn btn-app btn-sm" type="button" id="IdCosultarFormulario" name="Consultar"><i class="fa fa-search"></i> Consultar</button>                                                
                                                <br/><br/>                                                        
                                            </div>
                                        </div>
                                        <!-- /Botones -->
                                    </div>
                                </form>
                                <!-- /Formulario Registro -->
                            </div>
                        </div>
                        <!-- /Primera Sección-->

                        <!-- Segunda Sección-->
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Tabla</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a></a>
                                            </li>
                                            <li class="dropdown">
                                                <a><i>Ocultar</i></a>
                                            </li>
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content" id="contenidoTabla">
                                        <!-- Tabla -->
                                        <table id="datatable" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Opcion</th>
                                                    <th>Plant</th>
                                                    <th>Purchase_order</th>
                                                    <th>Material</th>
                                                    <th>Material_Description</th>
                                                    <th>Batch</th>
                                                    <th>Movement_type</th>
                                                    <th>Movement_Type_Text</th>
                                                    <th>Item</th>
                                                    <th>Quantity</th>
                                                    <th>Qty_in_unit_of_entry</th>
                                                    <th>Unit_of_Entry</th>
                                                    <th>Amt_in_loc_cur</th>
                                                    <th>Currency</th>
                                                    <th>Storage_Location</th>
                                                    <th>Posting_Date</th>
                                                    <th>Document_Date</th>
                                                    <th>Material_Document</th>
                                                    <th>User_Name</th>
                                                    <th>Vendor</th>
                                                    <th>Order_</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                
                                            </tbody>
                                        </table>
                                        <!-- /Tabla -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /Segunda Sección-->
                        <!-- Modal Edit-->
                        <!-- /Modal Edit-->
                        <div class="modal fade bd-example-modal-lg" id="IdModalEdit" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <form id="IdFormularioModal" method="POST" action="ServletSunchemical">
                                    <div align="center" id="esperaModal" style="display: none">
                                        <img src="Principal/images/loading_dash.gif">
                                    </div>
                                    <div class="modal-content" id="PrincipalModal">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Edicion</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div>
                                                <input type="hidden" id="IdModal" name="IdModal" value="">
                                                <input type="hidden" name="evento" value="Upload">
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Plant">Plant</label>
                                                    <input type="text" class="form-control" id="IdPlant" name="Plant" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Purchase_order">Purchase_order</label>
                                                    <input type="text" class="form-control" id="IdPurchase_order" name="Purchase_order" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Material">Material</label>
                                                    <input type="text" class="form-control" id="IdMaterial" name="Material" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Material_Description">Material_Description</label>
                                                    <input type="text" class="form-control" id="IdMaterial_Description" name="Material_Description" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Batch">Batch</label>
                                                    <input type="text" class="form-control" id="IdBatch" name="Batch" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Movement_type">Movement_type</label>
                                                    <input type="text" class="form-control" id="IdMovement_type" name="Movement_type" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Movement_Type_Text">Movement_Type_Text</label>
                                                    <input type="text" class="form-control" id="IdMovement_Type_Text" name="Movement_Type_Text" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="ItemFormulario">Item</label>
                                                    <input type="text" class="form-control" id="IdItemFormulario" name="ItemFormulario" required="required">
                                                </div>        
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Quantity">Quantity</label>
                                                    <input type="text" class="form-control" id="IdQuantity" name="Quantity" required="required">
                                                </div> 
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Qty_in_unit_of_entry">Qty_in_unit_of_entry</label>
                                                    <input type="text" class="form-control" id="IdQty_in_unit_of_entry" name="Qty_in_unit_of_entry" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Unit_of_Entry">Unit_of_Entry</label>
                                                    <input type="text" class="form-control" id="IdUnit_of_Entry" name="Unit_of_Entry" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Amt_in_loc_cur">Amt_in_loc_cur</label>
                                                    <input type="text" class="form-control" id="IdAmt_in_loc_cur" name="Amt_in_loc_cur" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Currency">Currency</label>
                                                    <input type="text" class="form-control" id="IdCurrency" name="Currency" required="required">
                                                </div>                                                                                                                                   
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Storage_Location">Storage_Location</label>
                                                    <input type="text" class="form-control" id="IdStorage_Location" name="Storage_Location" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Posting_Date">Posting_Date</label>
                                                    <input type="text" class="form-control" id="IdPosting_Date" name="Posting_Date" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Document_Date">Document_Date</label>
                                                    <input type="text" class="form-control" id="IdDocument_Date" name="Document_Date" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Material_Document">Material_Document</label>
                                                    <input type="text" class="form-control" id="IdMaterial_Document" name="Material_Document" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="User_Name">User_Name</label>
                                                    <input type="text" class="form-control" id="IdUser_Name" name="User_Name" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Vendor">Vendor</label>
                                                    <input type="text" class="form-control" id="IdVendor" name="Vendor" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Order_">Order_</label>
                                                    <input type="text" class="form-control" id="IdOrder_" name="Order_" required="required">
                                                </div>                                                                                            
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary" id="IdUpdate" name="Accion" value="Fbl3NFormularioJSP">Guardar</button>
                                        </div>
                                    </div>
                                </form>>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Contenido -->
        <!-- Footer -->
        <footer>
            <br/>
            <div class="clearfix"></div>
        </footer>
        <!-- Footer -->
        <%@include file="Principal/Script.html" %>  
        <%} else {
        %>
        <script src="Principal/js/jsfifo/jquery.min.js"></script>  
        <script>
            location.href = "index.jsp";
        </script>  
        <%
            }
        %>        
    </body>
</html>
