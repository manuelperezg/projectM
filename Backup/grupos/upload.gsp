<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'grupos.label', default: 'Grupo')}" />
        <g:set var="entitiesName" value="${message(code: 'grupos.label', default: 'Grupos')}" />
        <title><g:message code="default.create.label" args="[entitiesName]" /></title>
        <style>
            #nombre {width:50%}
        </style>
        <script language="javascript">
            function importar(){
                if ($("#file").val() == ""){
                    swal({
                        title: '${message(code: 'default.import-empty.info.message', default: '¡Advertencia!')}',
                        text: '${message(code: 'default.import-empty.ask.message', default: 'Se requiere de un archivo para continuar')}',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#5CB85C',
                        confirmButtonText: 'Aceptar',
                        confirmButtonClass: 'btn btn-success',
                        buttonsStyling: true
                    });
                    return false;
                }else{
                    swal({
                        title: '${message(code: 'default.import.info.message', default: 'Reemplazará TODOS los datos')}',
                        text: '${message(code: 'default.import.ask.message', default: 'Esta Usted seguro?')}',
                        type: 'question',
                        showCancelButton: true,
                        confirmButtonColor: '#5CB85C',
                        confirmButtonText: 'Si, importar!',
                        cancelButtonText: 'No',
                        confirmButtonClass: 'btn btn-success',
                        cancelButtonClass: 'btn btn-danger',
                        buttonsStyling: true
                    }).then(function() {
                         $( "#import_form" ).submit();
                    });
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#create-materias" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label_a" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="upload-data" class="content scaffold-create" role="main">
            <div class="content scaffold-create" role="main">
                <h1><g:message code="default.import.label" args="[entitiesName]" /></h1>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:uploadForm action="process" name="import_form">
                    <fieldset class="form" style="align:center">
                        <input type="file" name="file" id="file" />
                    </fieldset>
                    <fieldset class="buttons">
                        <input class="import" type="submit" value="${message(code: 'default.button.import.label', default: 'Importar')}" onclick="return importar()" />
                    </fieldset>
                </g:uploadForm>
            </div>
        </div>
    </body>
</html>
