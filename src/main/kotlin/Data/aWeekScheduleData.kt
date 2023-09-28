package Data

data class aWeekScheduleData(
    var lineId: Int,
    var weekId: String,
    var dayArray: Array<aDayScheculeData> = emptyArray()

//    var dayToken: String,
//    var descriptionToken: String,
//    var sectionToken: String,
//    var sectionTypeToken: String,
//    var activityToken: String,
//    var setsToken: String,
//    var repsToken: String,
    )
