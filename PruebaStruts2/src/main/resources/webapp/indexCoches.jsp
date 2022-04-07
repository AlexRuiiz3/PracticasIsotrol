<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href = "./css/styles.css">
<s:include value="/viewInclude.jsp"/>
<title>Insert title here</title>
</head>
<body>

<h3>Bienvenido</h3>
<p><s:property value="nombreCoche"/></p> <h5>
   <s:text name="texto.titulo" />
 </h5>
 </body>
</html>