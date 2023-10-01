package Data

data class aDayScheculeData (
    var lineId: Int = 0,
    var weekId: String = "",
    var dayId: String = "",
    var dayDescription: String  ,

    //var sectionArray: ArrayList<aSectionScheculeData> = ArrayList<aSectionScheculeData>()
    var sectionArray: Array<aSectionScheculeData> = emptyArray()
)
