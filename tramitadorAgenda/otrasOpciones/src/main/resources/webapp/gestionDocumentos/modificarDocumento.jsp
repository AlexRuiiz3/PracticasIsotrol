<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Modificar documento</title>
    <!-- Scripts -->
    <s:include value="../templates/head_scripts.jsp"/>
    <script type="text/javascript" src="<s:url value="/modulos/otrasOpciones/js/funcionesFiledrag.js"/>"></script>
    <!-- CSS  -->
    <s:include value="../templates/head_styles.jsp"/>
    <link rel="stylesheet" type="text/css" media="all" href="<s:url value="/css/filedrag.css"/>"/>
    <s:head theme="ajax"/>

    <script type="text/javascript">
        $(function () {
            overlayExito(window.capaEspera);
        });
    </script>
</head>
<body>
<div class="imagenCabecera mb-5"></div>
<div id="divContainerModificarDocumento" class="container-fluid">
    <div class="justify-content-center row">
        <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none">
        </div>
    </div>
    <s:form action="../otrasOpciones/subirDocumento.action" id="formEnvio" method="post" theme="simple"
            enctype="multipart/form-data">
        <s:hidden name="refDoc" id="refDoc"/>
        <div class="card mb-5">
            <div class="card-header">
                <s:text name="header.descarga"/>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <button id="descargarDocumento"
                                        style="width: auto; color: white; background-color: #007934; font-size: 1.0em; padding: 0.5em;"
                                        onclick="document.getElementById('formEnvio').action='../otrasOpciones/descargarDocumento.action';
                                                document.getElementById('formEnvio').submit();">
                                    <s:text name="boton.descarga"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-5">
            <div class="card-header">
                <s:text name="header.subida"/>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <input type="file" id="fileselect" name="fileselect[]" accept=".odt"
                                       class="clase_input" label="Guardar"
                                       style="float: left !important;margin-top: -14px;padding: 0 0.5em 0 0;"/>
                                <br>
                                <br>
                                <div id="filedrag" style="margin-top: -20px;display: block;background: url('../../instalaciones/imagenes/drag_doc.png');
                                            background-repeat: no-repeat; background-position:center; background-size:100px">
                                    <s:text name="area.arrastre"/>
                                </div>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <input type="button" class="clase_input"
                                       onclick="if(compruebaDocumentosAdjuntos()){adjuntarDoc();}"
                                       value="<s:text name="boton.subida"/>" id="subir"
                                       style="width: auto; color: white; background-color: #007934; font-size: 1.0em; padding: 0.5em; opacity: 0.5; cursor: default; float: right;"
                                       disabled="true"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="messages" style="text-align: left; display: none"></div>
    </s:form>
    <input class="btn btn-danger" type="button" id="btnCerrar" value="<s:text name="CERRAR" />"
           onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"/>
</div>
<div class="fondoOverlay d-none"></div>
<script src="<s:url value="/modulos/comun/js/Filedrag.js"/>"></script>
</body>
</html>