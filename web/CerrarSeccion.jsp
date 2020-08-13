<%-- 
    Document   : PermisosRol
    Created on : 19-feb-2020, 14:30:41
    Author     : Carlos A Dominguez D
--%>

<%@page session="true"%>

<%
    HttpSession session1 = request.getSession();
    session1.invalidate();
%> 
<jsp:forward page="index.jsp"/>
