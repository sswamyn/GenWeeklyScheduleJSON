import Data.ExcelRowData
import java.io.File


var sampleFileContent = mutableListOf<String>()
var currentLine = 0
fun main() {

    readThisFile("src/main/resources/WeekSchedule.csv")
    println(" sampleFileContent.size: ${sampleFileContent.size} ")
    // Test for passing Iterator to a function

    val excelSheetIterator = sampleFileContent.listIterator()
    for ((index, aLine) in excelSheetIterator.withIndex()) {
        //println("CurrentLine is: $currentLine \t Index: $index \t\t Value is: $aLine")
        if (index != 0) {
            currentLine++
            println("CurrentLine is: $currentLine \t Index: $index \t\t Value is: $aLine")
            val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
            val rowsForThisWeek: Array<ExcelRowData> =
                getExcelRowDataForThisWeek(oneExcelRow.weekToken, excelSheetIterator)
        }
    }

}

fun readThisFile(fileName: String) {
    val file = File(fileName)
    for (line in file.readLines()) {
        sampleFileContent.add(line)
    }
}

fun getExcelRowDataForThisWeek(weekToken: String, excelSheetIterator: Iterator<String>): Array<ExcelRowData> {
    //logger.info { "START getExcelRowDataForThisWeek \t weekToken: $weekToken"}
    var excelRowDataForThisWeek: Array<ExcelRowData> = emptyArray()
    //file.forEachLine {aLine
    for ((index, aLine) in excelSheetIterator.withIndex()) {
        //println(it)
        currentLine++
        //logger.info { " Current line: $currentLine \t aLine: $aLine"}

        val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
        if (oneExcelRow.weekToken == "") {
            excelRowDataForThisWeek += oneExcelRow
        } else {
            //line counter back one
            currentLine--
            break
        }
    }
    return excelRowDataForThisWeek
}

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
