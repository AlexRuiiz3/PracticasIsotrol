<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<s:form action="/tareaEntregaVerCoches">
	<s:submit value="Action ver coches" />
</s:form>
<s:form action="/tareaEntregaGuardarCoche">
	<s:submit value="Action guardar coches"/>
</s:form>
</body>
</html>