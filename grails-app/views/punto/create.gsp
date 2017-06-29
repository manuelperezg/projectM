<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'punto.label', default: 'Punto')}" />
        <g:set var="entitiesName" value="${message(code: 'punto.label', default: 'Puntos')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <style>
            #materiales, #conjunto, #nombre, #verificacion, #actividad, #descripcion, #indicacion_materiales {width:50%}
            #dispositivos {width:30%}
            #estado, #frecuencia, #responsable, #turno, #dia_semana, #mes, #subsistema {width:20%}
            #tiempo, #horario {width:10%}
            .hidden {
				display: none;
				position:absolute;
				top:0px;
			}
        </style>
        <script language="javascript">
			$( document ).ready(function() {
                $("#create-punto select").not("#materiales").select2({
					language: "es"
				});

                $("#materiales").select2();

                hiddeMalo();
				hiddeMateriales();
                hiddeSubsistema();
				hiddeFrecuency('${punto.frecuencia}');

				$("#requiere_malo").change(function() {
					hiddeMalo();
				});

                $("#requiere_materiales").change(function() {
					hiddeMateriales();
				});

                $("#requiere_subsistema").change(function() {
                    hiddeSubsistema();
                });

				$("#frecuencia").change(function() {
					hiddeFrecuency(this.value);
				});
			});

			function hiddeFrecuency(opcion){
				if (opcion == "") opcion = $("#frecuencia").val();
				opcion = opcion.replace('Una vez por ','');
				if (opcion == 'turno'){
					$("#turno").parent().show();
					$("label[for='turno']").removeClass("hidden");
					$("#dia_semana").parent().hide();
					$("label[for='dia_semana']").addClass("hidden");
					$("#horario").addClass("hidden");
					$("label[for='horario']").addClass("hidden");
					$("#mes").parent().hide();
					$("label[for='mes']").addClass("hidden");
				} else if (opcion == 'día'){
					$("#turno").parent().hide();
					$("label[for='turno']").addClass("hidden");
					$("#dia_semana").parent().hide();
					$("label[for='dia_semana']").addClass("hidden");
					$("#horario").removeClass("hidden");
					$("label[for='horario']").removeClass("hidden");
					$("#mes").parent().hide();
					$("label[for='mes']").addClass("hidden");
				} else if (opcion == 'semana'){
					$("#turno").parent().hide();
					$("label[for='turno']").addClass("hidden");
					$("#dia_semana").parent().show();
					$("label[for='dia_semana']").removeClass("hidden");
					$("#horario").addClass("hidden");
					$("label[for='horario']").addClass("hidden");
					$("#mes").parent().hide();
					$("label[for='mes']").addClass("hidden");
				} else if (opcion == 'mes'){
					$("#turno").parent().hide();
					$("label[for='turno']").addClass("hidden");
					$("#dia_semana").parent().hide();
					$("label[for='dia_semana']").addClass("hidden");
					$("#horario").addClass("hidden");
					$("label[for='horario']").addClass("hidden");
					$("#mes").parent().hide();
					$("label[for='mes']").addClass("hidden");
				} else {
					$("#turno").parent().hide();
					$("label[for='turno']").addClass("hidden");
					$("#dia_semana").parent().hide();
					$("label[for='dia_semana']").addClass("hidden");
					$("#horario").addClass("hidden");
					$("label[for='horario']").addClass("hidden");
					$("#mes").parent().show();
					$("label[for='mes']").removeClass("hidden");
				}
			}

			function hiddeMateriales(){
				if($("#requiere_materiales").is(':checked')) {
					$("#materiales").parent().show();
					$("label[for='materiales']").removeClass("hidden");
				} else {
					$("#materiales").parent().hide();
					$("label[for='materiales']").addClass("hidden");
				}
			}

            function hiddeSubsistema() {
                if($("#requiere_subsistema").is(':checked')) {
                    $("#subsistema").parent().show();
                    $("label[for='subsistema']").removeClass("hidden");
                } else {
                    $("#subsistema").parent().hide();
                    $("label[for='subsistema']").addClass("hidden");
                }
            }

			function hiddeMalo(){
				if($("#requiere_malo").is(':checked')) {
					$("#div-foto_malo").removeClass("hidden");
				} else {
					$("#div-foto_malo").addClass("hidden");
				}
			}

			function PreviewImage(id_upload, id_preview) {
				var oFReader = new FileReader();
				oFReader.readAsDataURL(document.getElementById(id_upload).files[0]);

				oFReader.onload = function (oFREvent) {
					document.getElementById(id_preview).src = oFREvent.target.result;
				};
			}

			function go(id_tag){
				$('[name="footer-'+id_tag+'"]')[0].click();
			}
		</script>
    </head>
    <body>
		<g:render template="/layouts/menu" />
		<!-- .menu-box -->
		<div class="breadcrumb-box">
		  <div class="container">
			<ul class="breadcrumb">
			  <li><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			  <li><g:link action="index" controller="verificacion"><g:message code="default.list.label" args="['Verificaciones']" /></g:link></li>
			  <li><g:link action="show" controller="verificacion" resource="${this.punto.verificacion}" >${this.punto.verificacion}</g:link></li>
			  <li class="active"><g:message code="default.new.label" args="[entityName]" /></li>
			</ul>
		  </div>
		</div>
		<!-- .breadcrumb-box -->
        <a href="#create-punto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav_topbar" role="navigation">
			<ul>
				<li><a href="javascript:go('save')"		class="save"><g:message code="default.button.save.label" default="Edit" /></a></li>
                <li><a href="javascript:go('close')"	class="close"><g:message code="default.button.close.label" default="close" /></a></li>
            </ul>
        </div>
        <div id="create-punto" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.punto}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.punto}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:uploadForm action="save">
                <fieldset class="form">
					%{--
                    <f:all bean="punto"/>
					--}%

                    <f:with bean="punto">
						<f:field property="verificacion"/>

						<f:field property="conjunto"/>

						<f:field property="nombre"/>
						<f:field property="foto_bueno" wrapper="icon"/>
						<f:field property="requiere_malo"/>
                        <f:field property="foto_malo" wrapper="icon"/>
						<f:field property="actividad"/>
						<f:field property="responsable"/>

						<f:field property="frecuencia"/>

						<f:field property="turno"/>
						<f:field property="dia_semana"/>
						<f:field property="horario"/>
						<f:field property="mes"/>

						<f:field property="estado"/>
						<f:field property="tiempo"/>
						<f:field property="descripcion"/>
						<f:field property="dispositivos"/>

						<f:field property="requiere_materiales"/>

						<div class="fieldcontain">
                            <label for="materiales" class="">Materiales</label>
                            <g:select multiple="true" optionKey="id" optionValue="${{ material -> "${material}" }}" name="materiales" value="${punto?.materiales*.id}" from="${materialesList}" />
                        </div>

                        <f:field property="requiere_subsistema"/>
                        <div class="fieldcontain">
                            <label for="subsistema" class="">Subsistemas</label>
                            <g:select optionKey="id" optionValue="${nombre}" name="subsistema" value="${punto?.subsistema*.id}" from="${punto.verificacion.maquina.subsistemas}" />
                        </div>

					</f:with>

                </fieldset>
                <g:submitButton name="footer-save" style="visibility:hidden;position:absolute;top:0px;width:0px;"/>
                <fieldset class="buttons">
					<g:link class="save" url="javascript:go('save')"><g:message code="default.button.save.label" default="Save" /></g:link>
                    <g:link name="footer-close" class="close" action="show" controller="verificacion" resource="${punto.verificacion}"><g:message code="default.button.close.label" default="Close" /></g:link>
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>
