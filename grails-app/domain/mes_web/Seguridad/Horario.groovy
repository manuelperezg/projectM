package mes_web

import javax.persistence.CascadeType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull

class Horario {

    //Atributos de la tabla
    String	id 
    String	matricula
    String  fecha_Inicio
    String  fecha_Final
    Date	dateCreated
    Date	lastUpdated
    UserLG	id_Autor
	
    //Validaciones
    static constraints = {
        id				(nullable:true)
        matricula		(nullable:false)
        fecha_Inicio	(nullable:false)
        fecha_Final		(nullable:false)
        dateCreated		(widget:'display')
        lastUpdated		(widget:'display')
        id_Autor		(nullable:true, widget:'display')
    }

    //Vinculación con la tabla de la BD
    static mapping = {
        table 'HORARIOS'
        version false
        //columns {
            id column: "ID", generator : 'uuid'
            matricula column:'matricula'
            fecha_Inicio column:'fecha_inicio'
            fecha_Final column:'fecha_final'
            
            id_Autor column:'id_autor'
        //}
    }
}
