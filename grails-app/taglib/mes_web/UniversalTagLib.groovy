package mes_web

class UniversalTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    
    def thisYear = {
        out << new Date().format("yyyy")
    }

    def thisDate = {
        out << new Date().format("dd/MM/yyyy")
    }

    def copyright = {attrs, body->
        def anio_actual = new Date().format("yyyy")
        
        out << "&copy; <strong>" + attrs.startYear
        out <<(attrs.startYear.toInteger() != anio_actual.toString().toInteger() ? " - " + thisYear() + "</strong> " : "</strong> ")
        out << body()
    }
    
    def createMenu = {body -> 
        out << "<content tag=\"nav\">\n"
        out << "        <li class=\"dropdown\">\n"
        out << "            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Unidades Curriculares <span class=\"caret\"></span></a>\n"
        out << "            <ul class=\"dropdown-menu\">\n"
        out << "                <li><a href=\"/materias/importar\">Importación de UC</a></li>\n"
        out << "                <li><a href=\"/materias/index\">Gestión de UC</a></li>\n"
        out << "                <li><a href=\"/materias/reporte\">Reporte de UC</a></li>\n"
        out << "                <li><a href=\"/materias/respaldo\">Backup de UC</a></li>\n"
        out << "            </ul>\n"
        out << "        </li>\n"
        /*out << "        <li class=\"dropdown\">\n"
        out << "            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Alumnos <span class=\"caret\"></span></a>\n"
        out << "            <ul class=\"dropdown-menu\">\n"
        out << "                <li><a href=\"/alumnos/importar\">Importación de alumnos</a></li>\n"
        out << "                <li><a href=\"/alumnos/index\">Gestión de alumnos</a></li>\n"
        out << "                <li><a href=\"/alumnos/reporte\">Reporte de alumnos</a></li>\n"
        out << "                <li><a href=\"/alumnos/respaldo\">Backup de alumnos</a></li>\n"
        out << "                <li><a href=\"/alumnos/materias\">Toma de materias</a></li>\n"
        out << "                <li><a href=\"/grupos/index\">Gestión de grupos</a></li>\n"
        out << "            </ul>\n"
        out << "        </li>\n"
        out << "        <li class=\"dropdown\">\n"
        out << "            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Docentes <span class=\"caret\"></span></a>\n"
        out << "            <ul class=\"dropdown-menu\">\n"
        out << "                <li><a href=\"/docentes/importar\">Importación de docentes</a></li>\n"
        out << "                <li><a href=\"/docentes/index\">Gestión de docentes</a></li>\n"
        out << "                <li><a href=\"/docentes/reporte\">Reporte de docentes</a></li>\n"
        out << "                <li><a href=\"/docentes/respaldo\">Backup de docentes</a></li>\n"
        out << "            </ul>\n"
        out << "        </li>\n"
        out << "        <li class=\"dropdown\">\n"
        out << "            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Reportes <span class=\"caret\"></span></a>\n"
        out << "            <ul class=\"dropdown-menu\">\n"
        out << "                <li><a href=\"/reportes/materias\">Inscritos en cada UC</a></li>\n"
        out << "                <li><a href=\"/reportes/exportar\">Exportar listas a Excel</a></li>\n"
        out << "                <li><a href=\"/reportes/listas\">Listas de asistencia</a></li>\n"
        out << "            </ul>\n"
        out << "        </li>\n"
        out << "        <li class=\"dropdown\">\n"
        out << "            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Administración <span class=\"caret\"></span></a>\n"
        out << "            <ul class=\"dropdown-menu\">\n"
        out << "                <li><a href=\"/usuarios/index\">Gestión de usuarios</a></li>\n"
        out << "                <li><a href=\"/roles/index\">Gestión de roles</a></li>\n"
        out << "                <li><a href=\"/usuarios/reporte\">Reporte de usuarios registrados</a></li>\n"
        out << "                <li><a href=\"/usuarios/password\">Restablecer contraseñas</a></li>\n"
        out << "            </ul>\n"
        out << "        </li>\n"*/
        out << "    </content>\n"
    }
}
