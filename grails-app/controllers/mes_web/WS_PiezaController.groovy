package mes_web

class WS_PiezaController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Pieza.list()
    }
}
