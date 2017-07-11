package mes_web

class WS_MaterialController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_Material.list()
    }
}
