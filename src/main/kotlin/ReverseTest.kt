import Data.aWeekScheduleData
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.File


fun main () {

    println("Hello World")

    // Read a JSON file using Jackson
    val mapper = ObjectMapper()
    val jsonNode: JsonNode = mapper.readTree(File("src/main/resources/python/TrainingScheduleDefinitionscopy2.json"))
    println("jsonNode: $jsonNode")

    // Convert JSON to CSV using Jackson
    val csvSchema: CsvSchema = CsvSchema.builder().setUseHeader(true).build().withHeader()
    //val csv: String = mapper.writerFor(JsonNode::class.java).with(csvSchema).writeValueAsString(jsonNode)
//    val csv: String = mapper.writerFor(aWeekScheduleData::class.java).with(csvSchema).writeValueAsString(jsonNode)
    val csv: String = mapper.writerFor(aWeekScheduleData::class.java).writeValueAsString(jsonNode)// writeValueAsString(jsonNode)

    //println("csv: $csv")
    // Save to file using Jackson
    File("src/main/resources/python/TrainingScheduleDefinitionscopy2ReverseJACKSON.csv").writeText(csv)


//    // Read a JSON file
//    val gson = Gson()
//    val jsonFileContent: String = File("src/main/resources/python/TrainingScheduleDefinitionscopy2.json").readText(Charsets.UTF_8)
//
//    // Convert JSON to Java Object
//    val aWeekScheduleData: aWeekScheduleData = gson.fromJson(jsonFileContent, aWeekScheduleData::class.java)
//    //println("aWeekScheduleData: $aWeekScheduleData")
//
//    // Convert Java Object to CSV file
//    val csvFileContent: String = gson.toJson(aWeekScheduleData)
//    println("csvFileContent: $csvFileContent")
//    // Save CSV file
//    File("src/main/resources/python/TrainingScheduleDefinitionscopy2Reverse.csv").writeText(csvFileContent)


//// Attempt #1 ******* START *******
//    // Convert JSON to spreadsheet data
//
//    var jsonTree : JsonNode =  ObjectMapper().readTree(File("src/main/resources/python/TrainingScheduleDefinitionscopy2.json"))
//    var csvSchemaBuilder : CsvSchema.Builder = CsvSchema.builder()
//    var columnNames : JsonNode = jsonTree.elements().next()
//    for (jsonNode : JsonNode in columnNames) {
//        csvSchemaBuilder.addColumn(jsonNode.asText())
//    }
//    var csvSchema : CsvSchema = csvSchemaBuilder.build().withHeader()
//
//    // CsvMapper
//    var CsvMapper = ObjectMapper()
//    //var csv : String = CsvMapper.writerFor(JsonNode::class.java).with(csvSchema).writeValueAsString(jsonTree)
//    var csv : String = CsvMapper.writerFor(aWeekScheduleData::class.java).with(csvSchema).writeValueAsString(jsonTree)
//
//
//    println("csv: $csv")
//    //********* END *********

}