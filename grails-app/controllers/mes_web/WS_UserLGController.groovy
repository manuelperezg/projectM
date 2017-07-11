package mes_web

class WS_UserLGController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_UserLG.list()
    }
}
