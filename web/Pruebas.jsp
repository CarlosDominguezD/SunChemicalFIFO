<%-- 
    Document   : Pruebas
    Created on : 24-oct-2019, 16:26:41
    Author     : Carlos A Dominguez D
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script>
        <link rel="stylesheet" href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Change /upload-target to your upload address -->
        <form action="ProcesarFIFO" method="post" enctype="multipart/form-data">
            <input type="file" name="file" multiple id="archivos"/>
            <input type="submit"/>
        </form>
    </body>
</html>
