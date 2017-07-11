package mes_web

class WS_PuntoController {
    static responseFormats = ['json', 'xml']
    
    def index() {
        respond WS_Punto.list()
    }
}
