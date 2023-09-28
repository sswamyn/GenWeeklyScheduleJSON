package Data

import java.util.Arrays

data class aSectionScheculeData(
    var lineId: Int,
    var weekId: String,
    var dayId: String,
    var sectionDescription: String,
    //var activityArray: emptyArray<aActivityData>()
    var activityArray: Array<aActivityData> = emptyArray()
)
