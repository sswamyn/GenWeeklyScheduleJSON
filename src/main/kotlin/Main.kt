import Data.*
import java.io.File
import mu.KotlinLogging

//var currentLine = 0
var excelSheet = mutableListOf<ExcelRowData>()
var idxOfWeeks = mutableListOf<ExcelRowData>()
var fileHasHeader = true

val fileNameStr = "src/main/resources/python/Training Schedule Definitions.csv"
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
    var fullScheduleData: Array<aWeekScheduleData> = emptyArray()
    //var weekIdx: Int = 1
    var weekIdx: Int = 0

    do {
        if (weekIdx <= idxOfWeeks.size) {
            //val rowsForThisWeek: Array<ExcelRowData> = excelSheet[idxOfWeeks[weekIdx] .. idxOfWeeks[(weekIdx + 1)]].toTypedArray()
            val startExcelSheetIdx = idxOfWeeks[weekIdx].lineId
            val endExcelSheetIdx = idxOfWeeks[weekIdx + 1].lineId
            logger.debug { " startExcelSheetIdx: $startExcelSheetIdx \t endExcelSheetIdx : ${endExcelSheetIdx} \t \t idxOfWeeks.size: ${idxOfWeeks.size} \t idxOfWeeks[weekIdx]: ${idxOfWeeks[weekIdx]}" }
            val rowsForThisWeek: Array<ExcelRowData> = excelSheet.subList(startExcelSheetIdx, endExcelSheetIdx).toTypedArray()

            logger.debug { " rowsForThisWeek.size: ${rowsForThisWeek.size} \t $weekIdx : ${weekIdx + 1}" }

            rowsForThisWeek.forEach {   println(it) }

            var oneWeek: aWeekScheduleData =
                aWeekScheduleData( rowsForThisWeek[0].lineId, rowsForThisWeek[0].weekToken, emptyArray() )
//

            // When the iterator is passed as a parameter to a function, the iterator starts from the beginning!
            oneWeek.dayArray = createAWeekScheduleData(rowsForThisWeek[0].weekToken, rowsForThisWeek).toTypedArray()
            fullScheduleData += oneWeek
        }
        weekIdx++
    } while (weekIdx < idxOfWeeks.size)


}

fun createAWeekScheduleData(weekToken: String, rowsForThisWeek: Array<ExcelRowData>): MutableList<aDayScheculeData> {
    var rtnListOfDaySchedule: MutableList<aDayScheculeData> = mutableListOf<aDayScheculeData>()
    var dayIndex: MutableList<Int> = mutableListOf<Int>()
    var daySchedulesForTheWeek: MutableList<aDayScheculeData> = mutableListOf<aDayScheculeData>()
    var sectionSchedulesForTheDay: MutableList<aSectionScheculeData> = mutableListOf<aSectionScheculeData>()
    var activitySchedulesForTheSecion: MutableList<aActivityData> = mutableListOf<aActivityData>()

    for (i in 0.. rowsForThisWeek.size -1) {
        var oneDay: aDayScheculeData
        if (rowsForThisWeek[i].dayToken != "") {
            aDayScheculeData =
                aDayScheculeData(rowsForThisWeek[i].lineId, rowsForThisWeek[i].weekToken, rowsForThisWeek[i].dayToken,
                    rowsForThisWeek[i].descriptionToken, emptyArray()
                )
            daySchedulesForTheWeek += oneDay
        }
    }

    return rtnListOfDaySchedule
}

fun readFile() {
    val file = File(fileNameStr)
    var lineIdx = 0
    for (line in file.readLines()) {
        if(fileHasHeader && lineIdx == 0) {
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