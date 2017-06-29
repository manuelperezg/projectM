package mes_web

class Evento {
    String titulo
    
    
    Date startTime
    Date endTime

    // Recurring Options
    boolean isRecurring
    EventRecurType recurType
    Integer recurInterval = 1
    Date recurUntil
    Integer recurCount
	
	
    static hasMany = [recurDaysOfWeek: Integer, excludeDays: Date]
    static transients = ['durationMinutes']

    static constraints = {
        titulo(nullable: false, blank: false)
        
        
        startTime(required: true, nullable: false)
        endTime(required: true, nullable: false, validator: {val, obj -> val > obj.startTime} )
        isRecurring(nullable:false)
        
        recurType(nullable: true)
        recurInterval(nullable: true)
        recurUntil(nullable: true)
        recurCount(nullable: true)
        startTime(nullable: false)
        excludeDays(nullable: true)
        recurDaysOfWeek(validator: {val, obj -> 
            if (obj.recurType == EventRecurType.SEMANAL && !val) {return 'null'}
        })
    }

    public int getDurationMinutes() {
        return ((startTime.time - endTime.time) / 1000 / 60)
    }
	
    private void updateRecurringValues() {
        if (!isRecurring) {
            recurType = null
            recurCount = null
            recurInterval = null
            recurUntil = null
            excludeDays?.clear()
            recurDaysOfWeek?.clear()
        }

        // Set recurUntil date based on the recurCount value
        if (recurCount && !recurUntil) {
           Date recurCountDate = startTime

           // extra instance if startTime day is not in recurDaysOfWeek
           def extraInstance = this.recurType == EventRecurType.SEMANAL && !(eventService.isOnRecurringDay(this, this.startTime)) ? 1 : 0

           for (int i in 1..(recurCount - 1 + extraInstance)) {
               recurCountDate = eventService.findNextOccurrence(this, new Date(recurCountDate.time + 60000))
           }

           recurUntil = new Date(recurCountDate.time + 60000)
        }
        
    }

    def beforeUpdate() {
        updateRecurringValues()
    }
    
    def beforeInsert() {
        updateRecurringValues()
    }

}

public enum EventRecurType {
    DIARIO('Diario'),
    SEMANAL('Semanal'),
    MENSUAL('Mensual'),
    ANUAL('Anual')

    String name

    EventRecurType(String name) {
        this.name = name
    }
}

public enum EventRecurActionType {
    OCCURRENCE, FOLLOWING, ALL
}
