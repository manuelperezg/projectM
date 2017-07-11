package mes_web

class WS_PlantaController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Planta.list()
    }
}
