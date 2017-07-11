package mes_web

class WS_CategoriaController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Categoria.list()
    }
}
