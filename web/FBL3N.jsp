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
        <script type="text/javascript" src="Principal/js/jsfifo/ValidacionesFbl3nFormulario.js" ></script> 
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
                                                    <option value="Purchasing_Document">Purchasing_Document</option>
                                                    <option value="Material">Material</option>                                                    
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
                                                    <th>Document_Number</th>
                                                    <th>Document_type</th>
                                                    <th>Document_Date</th>
                                                    <th>Posting_Date</th>
                                                    <th>Cost_Center</th>
                                                    <th>Profit_Center</th>
                                                    <th>YearMonth</th>
                                                    <th>Account</th>
                                                    <th>Plant</th>
                                                    <th>Material</th>
                                                    <th>Quantity</th>
                                                    <th>Amount_in_local_currency</th>
                                                    <th>Local_Currency</th>
                                                    <th>Purchasing_Document</th>
                                                    <th>Reference</th>
                                                    <th>Document_currency</th>
                                                    <th>Offsetting_acct_no</th>
                                                    <th>Base_Unit_of_Measure</th>
                                                    <th>Alternative_Account_No</th>
                                                    <th>Transaction_Code</th>
                                                    <th>Text</th>
                                                    <th>Assignment</th>
                                                    <th>Clasificacion</th> 
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
                                                    <label for="Document_Number">Document_Number</label>
                                                    <input type="text" class="form-control" id="IdDocument_Number" name="Document_Number" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Document_type">Document_type</label>
                                                    <input type="text" class="form-control" id="IdDocument_type" name="Document_type" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Document_Date">Document_Date</label>
                                                    <input type="text" class="form-control" id="IdDocument_Date" name="Document_Date" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Posting_Date">Posting_Date</label>
                                                    <input type="text" class="form-control" id="IdPosting_Date" name="Posting_Date" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Cost_Center">Cost_Center</label>
                                                    <input type="text" class="form-control" id="IdCost_Center" name="Cost_Center" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Profit_Center">Profit_Center</label>
                                                    <input type="text" class="form-control" id="IdProfit_Center" name="Profit_Center" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="YearMonth">YearMonth</label>
                                                    <input type="text" class="form-control" id="IdYearMonth" name="YearMonth" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Account">Account</label>
                                                    <input type="text" class="form-control" id="IdAccount" name="Account" required="required">
                                                </div>        
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Tipodoc">Tipodoc</label>
                                                    <input type="text" class="form-control" id="IdTipodoc" name="Tipodoc" required="required">
                                                </div> 
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Plant">Plant</label>
                                                    <input type="text" class="form-control" id="IdPlant" name="Plant" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Material">Material</label>
                                                    <input type="text" class="form-control" id="IdMaterial" name="Material" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Quantity">Quantity</label>
                                                    <input type="text" class="form-control" id="IdQuantity" name="Quantity" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Amount_in_local_currency">Amount_in_local_currency</label>
                                                    <input type="text" class="form-control" id="IdAmount_in_local_currency" name="Amount_in_local_currency" required="required">
                                                </div>                                                                                                                                   
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Local_Currency">Local_Currency</label>
                                                    <input type="text" class="form-control" id="IdLocal_Currency" name="Local_Currency" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Purchasing_Document">Purchasing_Document</label>
                                                    <input type="text" class="form-control" id="IdPurchasing_Document" name="Purchasing_Document" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Reference">Reference</label>
                                                    <input type="text" class="form-control" id="IdReference" name="Reference" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Document_currency">Document_currency</label>
                                                    <input type="text" class="form-control" id="IdDocument_currency" name="Document_currency" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Offsetting_acct_no">Offsetting_acct_no</label>
                                                    <input type="text" class="form-control" id="IdOffsetting_acct_no" name="Offsetting_acct_no" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Base_Unit_of_Measure">Base_Unit_of_Measure</label>
                                                    <input type="text" class="form-control" id="IdBase_Unit_of_Measure" name="Base_Unit_of_Measure" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Alternative_Account_No">Alternative_Account_No</label>
                                                    <input type="text" class="form-control" id="IdAlternative_Account_No" name="Alternative_Account_No" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Transaction_Code">Transaction_Code</label>
                                                    <input type="text" class="form-control" id="IdTransaction_Code" name="Transaction_Code" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Text">Text</label>
                                                    <input type="text" class="form-control" id="IdText" name="Text" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Assignment">Assignment</label>
                                                    <input type="text" class="form-control" id="IdAssignment" name="Assignment" required="required">
                                                </div>                                            
                                                <div class="col-md-3 col-sm-12 col-xs-12 form-group">
                                                    <label for="Clasificacion">Clasificacion</label>
                                                    <input type="text" class="form-control" id="IdClasificacion" name="Clasificacion" required="required">
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
