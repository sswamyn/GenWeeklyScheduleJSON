import Data.ExcelRowData
import Data.aDayScheculeData
import Data.aWeekScheduleData
import java.io.File
import mu.KotlinLogging

//var currentLine = 0
var excelSheet = mutableListOf<ExcelRowData>()
var idxOfWeeks = mutableListOf<ExcelRowData>()

val fileNameStr = "src/main/resources/python/Training Schedule Definitions.csv"
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    println("Hello World!")
    // Add logging to the application
    logger.info { "Program started" }

    // Call readFile()  to read the file and popuplate the mutable ArrayList
    readFile()

    //var fullScheduleData is the main output in the form of a Java object;
    //    Next Step(Final): Convert this to a JSON object
    var fullScheduleData: Array<aWeekScheduleData> = emptyArray()
    var weekIdx: Int = 1
    do {
        if (weekIdx <= idxOfWeeks.size) {
            //val rowsForThisWeek: Array<ExcelRowData> = excelSheet[idxOfWeeks[weekIdx] .. idxOfWeeks[(weekIdx + 1)]].toTypedArray()
            logger.debug { " weekIdx: $weekIdx \t weekIdx+1: ${weekIdx + 1} \t \t idxOfWeeks.size: ${idxOfWeeks.size} \t idxOfWeeks[weekIdx]: ${idxOfWeeks[weekIdx]}" }
            val rowsForThisWeek: Array<ExcelRowData> = excelSheet.subList(weekIdx, weekIdx+1).toTypedArray()
            logger.debug { " rowsForThisWeek.size: ${rowsForThisWeek.size} \t $weekIdx : ${weekIdx+1}" }

        }
        weekIdx++
    } while (weekIdx < idxOfWeeks.size )



}

fun readFile() {
    val file = File(fileNameStr)
    var lineIdx = 0
    for (line in file.readLines()) {
        logger.debug { "Line read : $line" }
        val excelData = createExcelRowData(lineIdx, line)
        excelSheet.add(excelData)
        // While at it let us also create a indexes of weeks
        if (excelData.weekToken != "") {
            idxOfWeeks.add(excelData)
        }
        lineIdx++
    }
    logger.debug { "Numbers of lines read from $fileNameStr in to the list is ${excelSheet.size}" }
}

/**
 * Create an ExcelRowData object from a line of text
 * @param aLine
 * @return ExcelRowData
 */
fun createExcelRowData(lineIdx : Int, aLine: String): ExcelRowData {
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