<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Bienvenido
	<% String usuario = (String) request.getAttribute("Usuario");
		if(usuario == null){
			usuario = "default";
		}
	%>
	<label>
	<%= usuario %>
	</label>
</body>
</html>