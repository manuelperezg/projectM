package mes_web

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            
constraints {
                // apply constraints here
            }
        }
            //declarando rutas donde realizaremos las acciones CRUD
            //Planta
        "/WS_Planta/$id"(controller: "WS_Planta") {
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


