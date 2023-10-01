package Data

import java.util.Arrays

data class aSectionScheculeData(
    var lineId: Int = 0,
    var weekId: String = "",
    var dayId: String = "",
    var sectionDescription: String = "",
    //var activityArray: emptyArray<aActivityData>()
    //var activityArray: ArrayList<aActivityData> = ArrayList<aActivityData>()
    var activityArray: Array<aActivityData> = emptyArray()
)
