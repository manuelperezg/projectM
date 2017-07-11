package mes_web

class WS_RoleLGController {
    static responseFormats = ['json', 'xml']
    
    def index() {
        respond WS_RoleLG.list()
    }
}
