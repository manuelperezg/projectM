package mes_web

class WS_PuntoRespuestaController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_PuntoRespuesta.list()
    }
}
