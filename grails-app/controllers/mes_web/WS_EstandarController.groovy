package mes_web

class WS_EstandarController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Estandar.list()
    }
}
