package mes_web

class WS_TipoVerificacionController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_TipoVerificacion.list()
    }
}
