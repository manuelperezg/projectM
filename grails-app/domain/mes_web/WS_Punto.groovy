package mes_web

import grails.rest.*

@Resource(uri='/WS_Punto', formats=['json', 'xml'])
class WS_Punto{

    String  id
    Integer punto
    String	conjunto
    String  nombre
    String  descripcion
    String  foto_bueno
    boolean	requiere_malo = Boolean.FALSE
    String  foto_malo
    String  actividad
    String	turno
    String	dia_semana
    String	horario
    String	mes
    String  responsable
    String  frecuencia
    String  estado
    Integer tiempo
    String  dispositivos
    boolean	requiere_materiales = Boolean.FALSE
    boolean requiere_subsistema = Boolean.FALSE
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor
    Subsistema subsistema

    static  belongsTo = [verificacion:Verificacion]
	static hasMany = [materiales: Material]

    static constraints = {
		id				(nullable:true)
        verificacion    (nullable: false)
        punto           (nullable: true)
        conjunto		(nullable: false)
        nombre          (nullable: false)
        foto_bueno      (nullable: true, widget:'icon')
        requiere_malo	(nullable:false)
        foto_malo       (nullable: true, widget:'icon')
        actividad       (nullable: false, widget: 'textarea')
        responsable     (nullable: false, inList: ["Operador", "Supervisor"])
        frecuencia      (nullable: false, inList: ["Una vez por turno", "Una vez por d\u00eda", "Una vez por semana", "Una vez por mes", "Una vez por trimestre", "Una vez por año"])
        turno		    (nullable: true, inList: ["Matutino", "Vespertino", "Nocturno"])
        dia_semana		(nullable: true, inList: ["Lunes", "Martes", "Mi\u00e9rcoles", "Jueves", "Viernes", "S\u00e1bado", "Domingo"])
        horario			(nullable: true)
        mes				(nullable: true, inList: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"])
        estado          (nullable: false, inList: ["Parada", "Trabajando"])
        tiempo          (nullable: false)
        descripcion     (nullable: false, widget: 'textarea')
        dispositivos    (nullable: false, maxSize: 255, inList: ["Equipo b\u00e1sico (Lentes y guantes)", "Equipo de seguridad (Candado loto y guantes)"])
        requiere_materiales		(nullable:false)
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
        materiales		(nullable:true, display: false, widget:'hiddenField')
        requiere_materiales     (nullable:false)
        subsistema      (nullable:true, display: false, widget:'hiddenField')
    }

    static mapping = {
        table 'PUNTOS'
        version false
        id column: "ID", generator : 'uuid'
        descripcion column:'descripcion', sqlType: 'text'
        actividad column:'actividad', sqlType: 'text'
        id_Autor column:'id_autor'
        //materiales column:'id_punto', cascade:'all-delete-orphan'
    }

    String toString() {
        return nombre
    }
}
