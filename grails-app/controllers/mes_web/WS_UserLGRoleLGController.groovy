package mes_web

class WS_UserLGRoleLGController {
    static responseFormats = ['json', 'xml']
	
    def index() {
        respond WS_UserLGRoleLG.list()
    }
}
