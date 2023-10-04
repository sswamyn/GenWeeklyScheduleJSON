package Data

data class ExcelRowData(
    var lineId: Int,
    var weekToken: String,
    var dayToken: String,
    var descriptionToken: String,
    var sectionToken: String,
    var sectionTypeToken: String,
    var activityToken: String,
    var setsToken: String,
    var repsToken: String
    /*
    "Week"	"Day"	"Description"	                                                                "Section"	"Section Type"	"Activity"	"Sets"	"Reps"
    1	    1	    "Body Weight Circuit - 3 SETS | 1-15 REPS, Cardio - Run 30 min, PFT Training"	"Warm Up"	1	            "Warm-up"	    1	    1
     */

////    var age: Int,
//    var address: String,
//    var salary: Double

    )
