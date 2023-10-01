package Data

data class aWeekScheduleData(
    var lineId: Int = 0,
    var weekId: String = "",
    //var dayArray: ArrayList<aDayScheculeData> = ArrayList<aDayScheculeData>()
    var dayArray: Array<aDayScheculeData> = emptyArray()

//    var dayToken: String,
//    var descriptionToken: String,
//    var sectionToken: String,
//    var sectionTypeToken: String,
//    var activityToken: String,
//    var setsToken: String,
//    var repsToken: String,
    )
