import java.io.File


var sampleFileContent = mutableListOf<String>()
fun main () {

    readThisFile("src/main/resources/WeekSchedule.csv")

    println(" sampleFileContent.size: ${sampleFileContent.size} ")
}

fun readThisFile(fileName: String) {
    val file = File(fileName)
    for (line in file.readLines()) {
        sampleFileContent.add(line)
    }
}