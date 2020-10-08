<%-- 
    Document   : index
    Created on : 03-jul-2019, 22:39:44
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sun Chemical</title>
        <link href="Principal/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="Principal/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="Principal/vendors/nprogress/nprogress.css" rel="stylesheet">
        <link href="Principal/vendors/animate.css/animate.min.css" rel="stylesheet">
        <link href="Principal/build/css/custom.min.css" rel="stylesheet">
        <script type="text/javascript" src="Principal/js/jsfifo/jquery.min.js" ></script>
        <script type="text/javascript" src="Principal/js/jsfifo/ValidaLogin.js"></script> 
    </head>
    <body class="login">
        <div class="login_box">
            <br>
            <br>
            <br>
            <div class="login-logo" style="text-align: center">                
                <img src="Principal/images/menu_bg.png"  width="210" height="80" class="responsive-img"/>
            </div>
            <div class="clearfix"></div>
            <br />
            <a class="hiddenanchor" id="signup"></a>
            <a class="hiddenanchor" id="signin"></a>
            <div class="login_wrapper">
                <div class="animate form login_form">
                    <section class="login_content">
                        <form action="UsuariosServlet" method="POST" name="IndexJsp" enctype="multipart/form-data" id="IdIndexJsp" >
                            <div>
                                <input type="hidden" id="IdAccion" name="Accion" >                                    
                            </div>
                            <h1>Login</h1>
                            <div>
                                <input type="text" class="form-control" placeholder="Usuario" name="Usuario" id="Idusuario" required="" />
                            </div>
                            <div>
                                <input type="password" class="form-control" placeholder="Clave" name="Password" id="Idpassword" required="" /></div>
                            <div>
                                <button type="button" id="IdIngresar" name="Ingresar" value="Ingresar" class="btn btn-default">Ingresar</button>                                       
                            </div>
                            <div class="clearfix"></div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>

