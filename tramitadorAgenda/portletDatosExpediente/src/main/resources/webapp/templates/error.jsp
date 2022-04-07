<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="es">
<head>
    <title>Error (administración)</title>
    <!-- HOJAS DE ESTILOS -->
    <link rel="stylesheet" type="text/css" href="../instalaciones/css/hojaEstiloAdmon.css"/>
</head>
<body>

    <div class="text-center">
        
        <s:iterator value="listErrores" status="error">
            <div class="error">
                <s:property value="listErrores[#error.index]"/>
            </div>
        </s:iterator>

        <div class="borde py-5">   
            <strong>
                <span class="text-danger">
                    <s:text name="ERROR.USUARIO.INCORRECTO"/>
                </span>
            </strong>
        </div>
    </div>
</body>
</html>