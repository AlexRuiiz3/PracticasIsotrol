<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<s:url value="pruebaJS.js"/>"></script>
<link rel="stylesheet" href = "./css/styles.css">
Contenido en otro jsp mediante include -> <s:include value="/viewInclude.jsp"/>
<title>Insert title here</title>
</head>
<body>

<h3>Bienvenido</h3>
<p>s:Property -> <s:property value="nombreCoche"/></p> <h5>
   s:text -> <s:text name="texto.titulo" />
 </h5>
 </body>
</html>