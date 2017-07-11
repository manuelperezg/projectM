package mes_web

class WS_LineaController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Linea.list()
    }
}
