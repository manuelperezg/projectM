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

class Sesion {

    //Atributos de la tabla
    String	id 
    String	id_User
    String	sesion_No
    String	fecha_Ingreso

    //Validaciones
    static constraints = {
        id				(nullable:true)
        id_User			(nullable:false)
        sesion_No		(nullable:false)
        fecha_Ingreso	(nullable:false)
    }

    //Vinculación con la tabla de la BD
    static mapping = {
        table 'SESION'
        version false
        //columns {
            id column: "ID", generator : 'uuid'
            id_User column:'id_user'
            sesion_No column:'sesion_no'
            fecha_Ingreso column:'fecha_ingreso'
        //}
    }
}
