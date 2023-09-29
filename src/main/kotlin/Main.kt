import Data.ExcelRowData
import Data.aDayScheculeData
import Data.aWeekScheduleData
import java.io.File
import mu.KotlinLogging

var currentLine = 0
var excelSheet = mutableListOf<String>()
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    println("Hello World!")
    // Add logging to the application
    logger.info { "Program started" }

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val fileNameStr = "src/main/resources/python/TrainingScheduleDefinitionscopy.csv"
    //val fileName = fileNameStr.toPath()
    val file = File(fileNameStr)
    for (line in file.readLines()) {
        logger.debug { "Line read : $line"  }
        excelSheet.add(line)
    }
    logger.debug { "Numbers of lines read from $fileNameStr in to the list is ${excelSheet.size}" }


    var fullScheduleData: Array<aWeekScheduleData> = emptyArray()

    // read file line by line
    //file.forEachLine {
    //for (aLine in excelSheet) {
    val excelSheetIterator = excelSheet.listIterator()
    for ((index, aLine) in excelSheetIterator.withIndex()) {
        currentLine++
        logger.debug { "CurrentLine is: $currentLine \t Index: $index \t\t Value is: $aLine" }

        logger.info { "Read the first line; \t Current line: $currentLine" }
        val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
        logger.debug { " Index of list (excelSheet) is $index " }
        logger.info { "Got the first row tokenized \t oneExcelRow: $oneExcelRow" }
        val rowsForThisWeek: Array<ExcelRowData> = getExcelRowDataForThisWeek(oneExcelRow.weekToken, excelSheetIterator)

        var oneWeek: aWeekScheduleData = aWeekScheduleData(oneExcelRow.lineId, oneExcelRow.weekToken, emptyArray())
        oneWeek.dayArray = createAWeekScheduleData(oneExcelRow.weekToken, rowsForThisWeek)

        //oneWeek.dayArray = getDayArrayForThisWeek(oneExcelRow.weekToken, oneExcelRow.dayToken, file)
        fullScheduleData += oneWeek
    }
}

fun createAWeekScheduleData(weekToken: String, rowForThisWeek: Array<ExcelRowData>): Array<aDayScheculeData> {
    var aDayArray: Array<aDayScheculeData> = emptyArray()
    var aSectionArray: Array<aSectionScheculeData> = emptyArray()
    var aActivityArray: Array<aActivityData> = emptyArray()
    var aDay: aDayScheculeData = aDayScheculeData(currentLine, weekToken,  "", "", "", emptyArray())
    var aSection: aSectionScheculeData = aSectionScheculeData(0, "", "", "", emptyArray())
    var aActivity: aActivityData = aActivityData(0, "", "", "", "", "", "", "")
    var aWeekScheduleData: aWeekScheduleData = aWeekScheduleData(0, "", emptyArray())

    var currentDayToken = ""
    var currentSectionToken = ""
    var currentActivityToken = ""

    rowForThisWeek.forEach {
        aRow
        println(aRow)
        currentLine++

        if (aRow.dayToken != "") {
            currentDayToken = aRow.dayToken
            aDay = aDayScheculeData(
                aRow.lineId,
                aRow.weekToken,
                aRow.dayToken,
                aRow.descriptionToken,
                emptyArray()
            )
            aDayArray += aDay
        } else {
            if (aRow.sectionToken != "") {
                currentSectionToken = aRow.sectionToken
                aSection = aSectionScheculeData(
                    aRow.lineId,
                    aRow.weekToken,
                    aRow.dayToken,
                    aRow.sectionToken,
                    emptyArray()
                )
                aSectionArray += aSection
            } else {
                if (aRow.activityToken != "") {
                    currentActivityToken = aRow.activityToken
                    aActivity = aActivityData(
                        aRow.lineId,
                        aRow.weekToken,
                        aRow.dayToken,
                        aRow.sectionToken,
                        aRow.activityToken,
                        aRow.setsToken,
                        aRow.repsToken,
                        aRow.descriptionToken
                    )
                    aActivityArray += aActivity
                }
            }
        }
    }
    return aDayArray
}

// DONE: 2021-08-22 - Create a function that returns an array of ExcelRowData for this week
fun getExcelRowDataForThisWeek(weekToken: String, excelSheetIterator:  ): Array<ExcelRowData> {
    logger.info { "START getExcelRowDataForThisWeek \t weekToken: $weekToken"}
    var excelRowDataForThisWeek: Array<ExcelRowData> = emptyArray()
    file.forEachLine {aLine
        //println(it)
        currentLine++
        logger.info { " Current line: $currentLine \t aLine: $aLine"}

        val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
        if (oneExcelRow.weekToken == "") {
            excelRowDataForThisWeek += oneExcelRow
        } else {
            //line counter back one
            currentLine--
        }
    }
    return excelRowDataForThisWeek
}

/**
 * Create an ExcelRowData object from a line of text
 * @param aLine
 * @return ExcelRowData
 */
fun createExcelRowData(aLine: String): ExcelRowData {
    // Parse the comma separated values and use them to create Excel row data
    // convert a String to an Array using comma as a delimiter
    val aLineArray = aLine.toString().split(",").toTypedArray()
    //println(aLineArray[0])
    var excelRowReturnvalue = ExcelRowData(
        currentLine,
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


// ******************
// Parking lot; Can be removed later
// ******************

//fun getDayArrayForThisWeek(weekToken: String, dayToken: String, file: File): Array<aDayScheculeData> {
//    var dayArray: Array<aDayScheculeData> = emptyArray()
//    file.forEachLine {
//        aLine
//        //println(it)
//        println(aLine)
//        currentLine++
//
//        val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
//        if (oneExcelRow.weekToken == "" && (oneExcelRow.dayToken == "" || oneExcelRow.dayToken == dayToken)) {
//            var oneDay: aDayScheculeData = aDayScheculeData(
//                oneExcelRow.lineId,
//                oneExcelRow.weekToken,
//                oneExcelRow.dayToken,
//                oneExcelRow.descriptionToken,
//                emptyArray()
//            )
//            dayArray += oneDay
//        } else {
//            //line counter back one
//            currentLine--
//        }
//    }
//    return dayArray
//}
