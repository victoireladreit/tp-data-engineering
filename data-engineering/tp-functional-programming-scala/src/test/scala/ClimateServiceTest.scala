import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
  }

  test("isClimateRelated - both") {
    assert(ClimateService.isClimateRelated("Clara Gaubil") == false)
    assert(ClimateService.isClimateRelated("global warming"))
  }

  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to access 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  test("parseRawData - negative ppm") {
    // our inputs
    val firstRecord = (2003, 1, -355.2) //help: to access 2003 of this tuple, you can do firstRecord._1
    val list1 = List(firstRecord)

    // our output of our method "parseRawData"
    val output = List(None)

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  test("getMinMax - basic") {
    val records = List(
      CO2Record(2020, 1, 400.0),
      CO2Record(2020, 2, 410.0),
      CO2Record(2020, 3, 390.0),
      CO2Record(2020, 4, 420.0),
      CO2Record(2020, 5, 380.0)
    )

    val expected = (380.0, 420.0)
    val result = ClimateService.getMinMax(records)

    assert(result == expected)
  }

  test("getMinMax - empty list should throw IllegalArgumentException") {
    assertThrows[IllegalArgumentException] {
      val list = List.empty[CO2Record]
      val result = ClimateService.getMinMax(list)
    }
  }

  test("getMinMax - list of multiple records with same ppm values") {
    val list = List(
      CO2Record(2020, 1, 400.0),
      CO2Record(2020, 2, 400.0),
      CO2Record(2020, 3, 400.0)
    ) // assuming CO2Record has a constructor that takes ppm as argument
    val result = ClimateService.getMinMax(list)
    assert(result == (400.0, 400.0))
  }

  test("getMinMax - single-record list") {
    val list = List(CO2Record(2020, 1, 400.0)) // assuming CO2Record has a constructor that takes ppm as argument
    val result = ClimateService.getMinMax(list)
    assert(result == (400.0, 400.0))
  }

  test("getMinMaxByYear - basic") {
    val records = List(
      CO2Record(2021, 1, 415.2),
      CO2Record(2021, 2, 414.9),
      CO2Record(2021, 3, 415.8),
      CO2Record(2022, 1, 410.2),
      CO2Record(2022, 2, 408.9),
      CO2Record(2022, 3, 412.8)
    )

    val (min, max) = ClimateService.getMinMaxByYear(records, 2021)
    val expected = (414.9, 415.8)

    assert((min, max) == expected)
  }

  test("getMinMaxByYear - empty list should throw IllegalArgumentException") {
    assertThrows[IllegalArgumentException] {
      val records = List.empty[CO2Record]
      val result = {
        ClimateService.getMinMaxByYear(records, 2021)
      }
    }
  }

  test("filterDecemberData") {
    val record1 = CO2Record(2020, 11, 415.2)
    val record2 = CO2Record(2021, 12, 417.5)
    val record3 = CO2Record(2022, 1, 418.9)
    val record4 = CO2Record(2022, 12, 420.1)

    val inputList = List(Some(record1), Some(record2), Some(record3), Some(record4))

    val expectedOutput = List(record1, record3) // Expected output after filtering
    val result = ClimateService.filterDecemberData(inputList)

    assert(result == expectedOutput)
  }
}
