package mes_web

class WS_MaquinaController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Maquina.list()
    }
}
