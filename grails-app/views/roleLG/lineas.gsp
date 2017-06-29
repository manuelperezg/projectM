<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'roleLG.label', default: 'rol')}" />
        <g:set var="entitiesName" value="${message(code: 'roleLG.label', default: 'roles')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <script language="javascript">
            $( document ).ready(function() {
                $('#lineas').multiSelect({
                    selectableHeader: "<div style='height:35px;position: relative;'><span style='position:absolute; bottom:0; font-weight:bold'><g:message code='roleLg.lineas_disponibles.label' default='Available lines'/></span><span id='select-all' style='float:right' class='operations_icon green' title='<g:message code='roleLG.button.add_all.label' default='Add all lines'/>'><i class='fa fa-chevron-circle-right'></i></span></div>",
                    selectionHeader: "<div style='height:35px;position: relative;'><span id='deselect-all' style='float:left' class='operations_icon red' title='<g:message code='roleLG.button.remove_all.label' default='Remove all lines'/>'><i class='fa fa-chevron-circle-left'></i></span><span style='position:absolute; right:0; bottom:0; font-weight:bold'><g:message code='roleLg.lineas_asignadas.label' default='Chosen lines'/></span></div>"
                });

                $('#select-all').click(function(){
                    $('#lineas').multiSelect('select_all');
                    return false;
                });
                $('#deselect-all').click(function(){
                    $('#lineas').multiSelect('deselect_all');
                    return false;
                });
            });

            function jconfirm(pregunta,mensaje){
                swal({
                    title: pregunta,
                    text: mensaje,
                    type: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#DA291C',
                    confirmButtonText: 'Si, eliminar!',
                    cancelButtonText: 'No',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger',
                    buttonsStyling: true
                }).then(function() {
                     $( "#delete_form" ).submit();
                });
            }

            function go(id_tag){
				$('[name="footer-'+id_tag+'"]')[0].click();
			}

        </script>
    </head>
    <body>
		<g:render template="/layouts/menu" />
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li><g:link action="index"><g:message code="default.list.label" args="[entitiesName]" /></g:link></li>
			  <li class="active"><g:message code="roleLg.lineas.label" args="[roleLG.nombre]"/></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#show-roleLG" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
			<ul>
				<li><a href="javascript:go('save')"		class="save"><g:message code="default.button.save.label" default="Edit" /></a></li>
				<li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="show-roleLG" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form resource="${this.roleLG}" action="save_lineas">
            <div class="row">
                <div class="col-xs-12">
                    <input type="hidden" name="roleLG" value="${roleLG.id}">
                    <g:select optionKey="id" optionValue="${{ linea -> "${linea.planta} - ${linea}" }}" name="lineas" value="${roleLG?.lineas*.id}" from="${lineas}" multiple="true" />
                </div>
            </div>
            <g:submitButton name="footer-save" style="visibility:hidden;position:absolute;top:0px;width:0px;"/>
            <fieldset class="buttons">
                <g:link class="save" url="javascript:go('save')"><g:message code="default.button.save.label" default="Save" /></g:link>
                <g:link name="footer-close" class="close" action="index"><g:message code="default.button.close.label" default="Close" /></g:link>
            </fieldset>
            </g:form>
        </div>
    </body>
</html>
