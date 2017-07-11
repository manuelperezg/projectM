package mes_web

class WS_TipoMantenimientoController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_TipoMantenimiento.list()
    }
}
