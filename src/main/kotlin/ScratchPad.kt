//import Data.ExcelRowData
//import java.io.File
//
//
//var sampleFileContent = mutableListOf<String>()
//var currentLineTemporal = 0
//fun main() {
//
//    readThisFile("src/main/resources/WeekSchedule.csv")
//    println(" sampleFileContent.size: ${sampleFileContent.size} ")
//    // Test for passing Iterator to a function
//
//    val excelSheetIterator = sampleFileContent.listIterator()
//    for ((index, aLine) in excelSheetIterator.withIndex()) {
//        //println("CurrentLine is: $currentLine \t Index: $index \t\t Value is: $aLine")
//        if (index != 0) {
//            currentLineTemporal++
//            println("CurrentLine is: $currentLineTemporal \t Index: $index \t\t Value is: $aLine")
//            val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
//            val rowsForThisWeek: Array<ExcelRowData> =
//                getExcelRowDataForThisWeekTEMP(index, oneExcelRow.weekToken) // , excelSheetIterator)
//        }
//    }
//
//}
//
//fun getExcelRowDataForThisWeekTEMP(weekStartIndex: Int, weekToken: String): Array<ExcelRowData> {
//    //logger.info { "START getExcelRowDataForThisWeek \t weekToken: $weekToken"}
//    var excelRowDataForThisWeek: Array<ExcelRowData> = emptyArray()
//
//    println("getExcelRowDataForThisWeekTEMP \t weekStartIndex: $weekStartIndex \t weekToken: $weekToken")
//    println("\t ")
//    var firstRound = true
//    for ( idx in weekStartIndex until sampleFileContent.size) {
//        println("idx: $idx \t sampleFileContent[idx]: ${sampleFileContent[idx]}")
//        val oneExcelRow: ExcelRowData = createExcelRowData(sampleFileContent[idx])
//        if ((oneExcelRow.weekToken == "") || (firstRound == true)) {
//            firstRound = false
//            excelRowDataForThisWeek += oneExcelRow
//        } else {
//            //line counter back one
//            currentLineTemporal--
//            break
//        }
//    }
////    for ((index, aLine) in sampleFileContent.withIndex()) {
////        //println(it)
////        currentLineTemporal++
////        //logger.info { " Current line: $currentLine \t aLine: $aLine"}
////
////        val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
////        if (oneExcelRow.weekToken == "") {
////            excelRowDataForThisWeek += oneExcelRow
////        } else {
////            //line counter back one
////            currentLineTemporal--
////            break
////        }
////    }
//    println("excelRowDataForThisWeek.size: ${excelRowDataForThisWeek.size}")
//    return excelRowDataForThisWeek
//}
//
//fun readThisFile(fileName: String) {
//    val file = File(fileName)
//    for (line in file.readLines()) {
//        sampleFileContent.add(line)
//    }
//}
//
////fun getExcelRowDataForThisWeek(weekToken: String, excelSheetIterator: Iterator<String>): Array<ExcelRowData> {
////    //logger.info { "START getExcelRowDataForThisWeek \t weekToken: $weekToken"}
////    var excelRowDataForThisWeek: Array<ExcelRowData> = emptyArray()
////    //file.forEachLine {aLine
////    for ((index, aLine) in excelSheetIterator.withIndex()) {
////        //println(it)
////        currentLineTemporal++
////        //logger.info { " Current line: $currentLine \t aLine: $aLine"}
////
////        val oneExcelRow: ExcelRowData = createExcelRowData(aLine)
////        if (oneExcelRow.weekToken == "") {
////            excelRowDataForThisWeek += oneExcelRow
////        } else {
////            //line counter back one
////            currentLineTemporal--
////            break
////        }
////    }
////    return excelRowDataForThisWeek
////}
////
//////fun createExcelRowData(aLine: String): ExcelRowData {
//////    // Parse the comma separated values and use them to create Excel row data
//////    // convert a String to an Array using comma as a delimiter
//////    val aLineArray = aLine.toString().split(",").toTypedArray()
//////    //println(aLineArray[0])
//////    var excelRowReturnvalue = ExcelRowData(
//////        currentLineTemporal,
//////        aLineArray[0],
//////        aLineArray[1],
//////        aLineArray[2],
//////        aLineArray[3],
//////        aLineArray[4],
//////        aLineArray[5],
//////        aLineArray[6],
//////        aLineArray[7]
//////    )
//////
//////    return excelRowReturnvalue
//////}
