<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alumnos.label', default: 'Grupo')}" />
        <g:set var="entitiesName" value="${message(code: 'alumnos.label', default: 'Grupos')}" />
        <title>Seleccione UCs</title>
        <script language="javascript">
			var total_disponibles = ${session.getAttribute("materias_libres").toInteger()};
            function jconfirm(pregunta,mensaje){
                var free = parseInt($("#free").val());
                if (free == 0 && total_disponibles == 0){
                    swal({
                        title: '${message(code: "default.register-no-choice.info.message", default: "¡Aviso!")}',
                        text: '${message(code: "default.register-no-choice.ask.message", args: ["unidades curriculares"], default: "Ya NO tiene unidades curriculares disponibles a registrar para continuar")}',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#5CB85C',
                        confirmButtonText: 'Aceptar',
                        confirmButtonClass: 'btn btn-success',
                        buttonsStyling: true
                    });
                }else if (free == total_disponibles){
                    swal({
						title: '${message(code: "default.register-empty.info.message", default: "¡Advertencia!")}',
                        text: '${message(code: "default.register-empty.ask.message", args: ["unidad curricular"], default: "Se requiere de seleccionar por lo menos una unidad curricular para continuar")}',
                        type: 'warning',
                        showCancelButton: false,
                        confirmButtonColor: '#5CB85C',
                        confirmButtonText: 'Aceptar',
                        confirmButtonClass: 'btn btn-success',
                        buttonsStyling: true
                    });
                }else{
                    swal({
                            title: pregunta,
                            text: mensaje,
                            type: 'question',
                            showCancelButton: true,
                            confirmButtonColor: '#5CB85C',
                            confirmButtonText: 'Si, registrar!',
                            cancelButtonText: 'No',
                            confirmButtonClass: 'btn btn-success',
                            cancelButtonClass: 'btn btn-danger',
                            buttonsStyling: true
                    }).then(function() {
                             $( "#confirm_form" ).submit();
                    });
                }
            }
            $( document ).ready(function() {
                $("input:radio").change(radioValueChanged);
                var free = parseInt($("#free").val());
                if (free == 0){
					var count = 0;
					while(true){
						if ($("input[name='grupo_"+count+"']").length){
							if ($("input[name='opc_"+count+"']").length){
								if (!$("input[name='opc_"+count+"']:checked").val()) {
									$("input[name='opc_"+count+"']").prop("disabled",true);
								}
							}
						}else{
							//No ahi mas opciones
							break;
						}
						count++;
					}
				}
            });
            function detectaDuplicados(materia_nombre){
				var duplicados = -1;
				var count = 0;
				while(true){
					if ($("input[name='materia_"+count+"']").length){
						if ($("input[name='materia_"+count+"']").val() == materia_nombre){
							if ($("input[name='opc_"+count+"']").length){
								if ($("input[name='opc_"+count+"']:checked").val()) {
									duplicados++;
								}
							}
						}
					}else{
						//No ahi mas opciones
						break;
					}
					count++;
				}
				if ($.inArray(materia_nombre, misMaterias)>=0){
					duplicados++;
				}
				return duplicados;
			}
            var misMaterias = [];
            var misGrupos = [];
            function radioValueChanged(){
                salida = true;
                radioName = $(this).attr('name');
                if ($.inArray(radioName, misGrupos)>=0){

                }else{
					//Validar que previamente no selecciono una materia similar
					materiaName = $("input[name='"+radioName.replace('opc_','materia_')+"']").val();
					if (detectaDuplicados(materiaName)){
						var mensaje = '${message(code: "default.register-no-repeat.ask.message", default: "Previamente eligi\u00f3 la unidad curricular {0}, no debe duplicarlas para continuar")}';
						swal({
							title: '${message(code: "default.register-no-repeat.info.message", default: "¡Aviso!")}',
							text: mensaje.replace('{0}','<b><font color="red">'+materiaName+'</font></b>'),
							type: 'warning',
							showCancelButton: false,
							confirmButtonColor: '#5CB85C',
							confirmButtonText: 'Aceptar',
							confirmButtonClass: 'btn btn-success',
							buttonsStyling: true
						});
						$(this).prop("checked",false);
					}else{
						misGrupos.push(radioName);
						var free = parseInt($("#free").val())-1;
						if (free >= 0){
							$("#free").val(free);
							//No ahi opciones disponibles bloquear todo vacio
							if (free == 0){
								var count = 0;
								while(true){
									if ($("input[name='grupo_"+count+"']").length){
										if ($("input[name='opc_"+count+"']").length){
											if (!$("input[name='opc_"+count+"']:checked").val()) {
												$("input[name='opc_"+count+"']").prop("disabled",true);
											}
										}
									}else{
											//No ahi mas opciones
											break;
									}
									count++;
								}
							}
						}else{
								salida = false;
						}
					}
                }
            }
            function desmarcar(radioName){
                if (!$("input[name='"+radioName+"']:checked").val()) {

                } else {
                    //Removiendo de la lista el radio marcado
                    misGrupos = jQuery.grep(radioName, function(value) {
                            return value != radioName;
                    });
                    //Recupera el valor de disponibles
                    var free = parseInt($("#free").val())+1;
                    $("#free").val(free);
                    $("input[name='"+radioName+"']").prop('checked', false);
                    //Ahi una opcion disponible desbloquear todo vacio
                    var count = 0;
                    while(true){
                        if ($("input[name='grupo_"+count+"']").length){
							if ($("input[name='opc_"+count+"']").length){
								if (!$("input[name='opc_"+count+"']:checked").val()) {
										$("input[name='opc_"+count+"']").prop("disabled",false);
								}
							}
                        }else{
                            //No ahi mas opciones
                            break;
                        }
                        count++;
                    }
                }
            }
        </script>
    </head>
    <body>
        <g:render template="/layouts/menu" />
        <a href="#list-alumnos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-alumnos" class="content scaffold-list" role="main">
            <h1><g:message code="default.register.label" args="['unidades curriculares']" />&nbsp;-&nbsp;<g:textField name="free" id="free" value="${session.getAttribute("materias_libres").toInteger()}" style="width:35px;" readonly="readonly"/>&nbsp;Disponibles</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form method="POST" action="confirmarMaterias" name="confirm_form">
				<table>
					<thead>
						<tr>
							<g:sortableColumn property="semestre" title="Semestre" />
							<g:sortableColumn property="grupo" title="Grupo" />
							<g:sortableColumn property="uc" title="Unidad curricular" />
							<g:sortableColumn property="docente" title="Docente" />
							<g:sortableColumn property="disponible" title="Disponible" />
							<g:sortableColumn property="seleccione" title="Seleccione" style="width: 25%"/>
						</tr>
					</thead>

					<tbody>
						<g:each in="${gruposList}" var="grupo" status="i">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td>${grupo.semestre}</td>
								<td>${grupo.nombre}</td>
								<td>${(grupo.getMateria())?grupo.getMateria().nombre:'No identificada'}</td>
								<td>${(grupo.getDocente())?grupo.getDocente().nombre:'No identificado'}</td>
								<td>${grupo.cupo-grupo.inscritos}</td>
								<td>
									<g:hiddenField name="grupo_${i}" value="${grupo.id}" />
									<g:hiddenField name="materia_${i}" value="${(grupo.getMateria())?grupo.getMateria().nombre:'No identificada'}" />
									<g:if test="${grupo.isInscritoMateria(session.getAttribute("username"), grupo.id) == 0}">
										<g:if test="${(grupo.cupo-grupo.inscritos) <= 0}">
											<b><font color="red">SIN CUPO</font></b>
										</g:if>
										<g:else>
											<g:radioGroup name="opc_${i}"
												  labels="${['Primer vez','Recursada']}"
												  values="${['Primer vez','Recursada']}">
											${it.radio} ${it.label}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</g:radioGroup>&nbsp;<img src="${request.contextPath}/assets/Trash.png" style="cursor:pointer" onclick="desmarcar('opc_${i}')" title="¡desmarcar!"/>
										</g:else>
									</g:if>
									<g:else>
										<script language="javascript">
											misMaterias.push("${(grupo.getMateria())?grupo.getMateria().nombre:'No identificada'}");
										</script>
										<b><font color="#0088CC">Inscrito</font></b>
									</g:else>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>

                <fieldset class="buttons">
                    <input class="save" type="button" value="${message(code: 'default.button.register.label', default: 'Registrar')}" onclick="jconfirm('${message(code: 'default.register.ask.message', default: 'Registrar su selección')}','${message(code: 'default.register.info.message', args:['Materias'], default: 'Ha indicado que desea registrar su selecci\u00f3n de Grupos+Materias, de continuar no podr\u00e1 cambiar la informaci\u00f3n.')}');" />
                </fieldset>
            </g:form>

        </div>
    </body>
</html>
