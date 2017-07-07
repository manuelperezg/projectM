package mes_web

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            
constraints {
                // apply constraints here
            }
        }
        //declarando rutas donde realizaremos las acciones CRUD
         
         //Categoria
     "/WS_Categoria/$id"(controller: "WS_Categoria") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //Estandar
     "/WS_Estandar/$id"(controller: "WS_Estandar") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //Pieza
     "/WS_Pieza/$id"(controller: "WS_Pieza") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //Punto
     "/WS_Punto/$id"(controller: "WS_Punto") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //Usuarios
     "/WS_UserLG/$id"(controller: "WS_UserLG") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
       //Planta
        "/WS_Planta/$id"(controller: "WS_Planta") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //lineas
    "/WS_Linea/$id"(controller: "WS_Linea") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //maquina
    "/WS_Maquina/$id"(controller: "WS_Maquina") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
         //Subsistema
    "/WS_Subsistema/$id"(controller: "WS_Subsistema") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }
        //material
     "/WS_Material/$id"(controller: "WS_Material") {
    action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
        }




        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}


