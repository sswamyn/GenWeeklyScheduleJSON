import Data.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import mu.KotlinLogging
import java.io.File


//var currentLine = 0
var excelSheet = mutableListOf<ExcelRowData>()
var idxOfWeeks = mutableListOf<ExcelRowData>()
var fileHasHeader = true

//val fileNameStr = "src/main/resources/python/Training Schedule Definitions.csv"
val fileNameStr = "src/main/resources/python/TrainingScheduleDefinitionscopy3.csv"
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    println("Hello World!")
    // Add logging to the application
    logger.info { "Program started" }

    // Call readFile()  to read the file and popuplate the mutable ArrayList
    readFile()

    idxOfWeeks.forEach() { println(it) }

    //var fullScheduleData is the main output in the form of a Java object;
    //    Next Step(Final): Convert this to a JSON object
    var fullScheduleData: Array<aWeekScheduleData> = emptyArray<aWeekScheduleData>()
    //var weekIdx: Int = 1
    var weekIdx: Int = 0

    //do {
    //  if (weekIdx <= idxOfWeeks.size) {
    //val rowsForThisWeek: Array<ExcelRowData> = excelSheet[idxOfWeeks[weekIdx] .. idxOfWeeks[(weekIdx + 1)]].toTypedArray()
    val startExcelSheetIdx = idxOfWeeks[weekIdx].lineId
    val endExcelSheetIdx = idxOfWeeks[weekIdx + 1].lineId
    logger.debug { " startExcelSheetIdx: $startExcelSheetIdx \t endExcelSheetIdx : ${endExcelSheetIdx} \t \t idxOfWeeks.size: ${idxOfWeeks.size} \t idxOfWeeks[weekIdx]: ${idxOfWeeks[weekIdx]}" }
    val rowsForThisWeek: Array<ExcelRowData> = excelSheet.subList(startExcelSheetIdx, endExcelSheetIdx).toTypedArray()

    logger.debug { " rowsForThisWeek.size: ${rowsForThisWeek.size} \t $weekIdx : ${weekIdx + 1}" }
    //rowsForThisWeek.forEach {   println(it) }

    val oneWeek: aWeekScheduleData =
        aWeekScheduleData(
            rowsForThisWeek[0].lineId, rowsForThisWeek[0].weekToken, //emptyArray<aDayScheculeData>()
            createAWeekScheduleData(
                rowsForThisWeek[0].lineId,
                rowsForThisWeek[0].weekToken,
                rowsForThisWeek
            ).toTypedArray()
        )

    // Convert oneWeek to JSON using GSON library
    val gson1 = Gson()
    val jsonData = gson1.toJson(oneWeek)

    val jsonElement: JsonElement = JsonParser().parse(
        jsonData
    )

    val gson: Gson = GsonBuilder().setPrettyPrinting().create();
    val json = gson.toJson(jsonElement)

    File("src/main/resources/python/TrainingScheduleDefinitionscopy3.json").writeText(json)
    //println(" JSON Version :\n " + json)
    //logger.debug { " JSON Version :\n ${json}" }

    // Print the JSON to file for testing
    //File("src/main/resources/python/TrainingScheduleDefinitionscopy2.json").writeText(json)


    // When the iterator is passed as a parameter to a function, the iterator starts from the beginning!
    //oneWeek.dayArray = createAWeekScheduleData(rowsForThisWeek[0].weekToken)     //rowsForThisWeek[0].weekToken, rowsForThisWeek)
//    logger.debug { " oneWeek: $oneWeek" }

    fullScheduleData += oneWeek //createAWeekScheduleData(rowsForThisWeek[0].weekToken)     //rowsForThisWeek[0].weekToken, rowsForThisWeek)
    //}
    //weekIdx++
    //} while (weekIdx < idxOfWeeks.size)


}

fun createAWeekScheduleData(
    lineId: Int,
    weekToken: String,
    rowsForThisWeek: Array<ExcelRowData>
): MutableList<aDayScheculeData> {

    var rtnListOfDaySchedule: MutableList<aDayScheculeData> = mutableListOf<aDayScheculeData>()
    var dayIndex: MutableList<Int> = mutableListOf<Int>()
    var sectionSchedulesForTheDay: MutableList<aSectionScheculeData> = mutableListOf<aSectionScheculeData>()
    var activitySchedulesForTheSecion: MutableList<aActivityData> = mutableListOf<aActivityData>()

    // Since we have the data for one week, let us create the WeekScheduleData object
    var oneDaySchedule: aDayScheculeData = aDayScheculeData()


    for (i in 0..rowsForThisWeek.size - 1) {
        var newDay = false
        var newSection = false
        var newActivity = false


        logger.debug("i: $i \t rowsForThisWeek[i].dayToken: ${rowsForThisWeek[i].dayToken}")
        if (rowsForThisWeek[i].dayToken != "") {
            newDay = true
            oneDaySchedule = aDayScheculeData(
                rowsForThisWeek[i].lineId,
                "", // Since this is day object, we will get WeekToken only for "Day 1", to keep it consistent  rowsForThisWeek[i].weekToken,
                rowsForThisWeek[i].dayToken,
                rowsForThisWeek[i].descriptionToken,
                sectionSchedulesForTheDay.toTypedArray() //emptyArray()
            )

            oneDaySchedule = aDayScheculeData()
            var oneSectionSchedule = aSectionScheculeData()
            var oneActivitySchedule = aActivityData()
            if (rowsForThisWeek[i].dayToken != "") {
                newDay = true
                oneDaySchedule = aDayScheculeData(
                    rowsForThisWeek[i].lineId, rowsForThisWeek[i].weekToken, rowsForThisWeek[i].dayToken,
                    rowsForThisWeek[i].descriptionToken, sectionSchedulesForTheDay.toTypedArray() //emptyArray()
                )
                dayIndex.add(i)
                daySchedulesForTheWeek.add(oneDaySchedule)
            } else {
                newDay = false
            }
            if (rowsForThisWeek[i].sectionToken != "") {
                newSection = true
                activitySchedulesForTheSecion.removeAll(activitySchedulesForTheSecion)
                sectionSchedulesForTheDay.removeAll(sectionSchedulesForTheDay)
                oneSectionSchedule = aSectionScheculeData(
                    rowsForThisWeek[i].lineId, rowsForThisWeek[i].weekToken, rowsForThisWeek[i].dayToken,
                    rowsForThisWeek[i].sectionToken, activitySchedulesForTheSecion.toTypedArray()
                )
                sectionSchedulesForTheDay.add(oneSectionSchedule)
                daySchedulesForTheWeek.last().sectionArray += sectionSchedulesForTheDay //[i] = oneSectionSchedule
            } else {
                newSection = false
            }

            if (rowsForThisWeek[i].activityToken != "") {
                oneActivitySchedule = aActivityData(
                    rowsForThisWeek[i].lineId, rowsForThisWeek[i].weekToken, rowsForThisWeek[i].dayToken,
                    rowsForThisWeek[i].sectionToken, rowsForThisWeek[i].activityToken, rowsForThisWeek[i].setsToken,
                    rowsForThisWeek[i].repsToken
                )
                //activitySchedulesForTheSecion.add(oneActivitySchedule)
                //daySchedulesForTheWeek.last().sectionArray.last().activityArray += activitySchedulesForTheSecion //[i] = oneActivitySchedule
                daySchedulesForTheWeek.last().sectionArray.last().activityArray += oneActivitySchedule //[i] = oneActivitySchedule
            }
            if (newDay) {
                rtnListOfDaySchedule.add(oneDaySchedule)
            } else if (newSection) {
                rtnListOfDaySchedule.removeLast()
                rtnListOfDaySchedule.add(daySchedulesForTheWeek.last())
            } else {
                //rtnListOfDaySchedule.removeLast()
                rtnListOfDaySchedule.add(daySchedulesForTheWeek.last())
            }

        }
        //rtnListOfDaySchedule. //add(daySchedulesForTheWeek[i])

        return rtnListOfDaySchedule
    }

    fun readFile() {
        val file = File(fileNameStr)
        var lineIdx = 0
        for (line in file.readLines()) {
            if (fileHasHeader && lineIdx == 0) {
                fileHasHeader = false
                continue
            }
            logger.debug { "Line read : $line" }
            val excelData = createExcelRowData(lineIdx, line)
            excelSheet.add(excelData)
            lineIdx = excelSheet.indexOf(excelData)
            excelData.lineId = lineIdx
            excelSheet[lineIdx].lineId = lineIdx   //update(lineIdx, excelData)
            // While at it let us also create a indexes of weeks
            if (excelData.weekToken != "") {
                idxOfWeeks.add(excelData)
            }
            lineIdx++
        }
        //excelSheet.removeAt(0)
        //idxOfWeeks.removeAt(0)
        logger.debug { "Numbers of lines read from $fileNameStr in to the list is ${excelSheet.size}" }
    }

    /**
     * Create an ExcelRowData object from a line of text
     * @param aLine
     * @return ExcelRowData
     */
    fun createExcelRowData(lineIdx: Int, aLine: String): ExcelRowData {
        // Parse the comma separated values and use them to create Excel row data
        // convert a String to an Array using comma as a delimiter
        val aLineArray = aLine.toString().split(",").toTypedArray()
        //println(aLineArray[0])
        var excelRowReturnvalue = ExcelRowData(
            lineIdx,
            aLineArray[0],
            aLineArray[1],
            aLineArray[2],
            aLineArray[3],
            aLineArray[4],
            aLineArray[5],
            aLineArray[6],
            aLineArray[7]
        )

        return excelRowReturnvalue
    }