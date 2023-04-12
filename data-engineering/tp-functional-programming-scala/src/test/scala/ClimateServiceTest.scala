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

  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
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

  //@TODO
  test("filterDecemberData") {
    assert(true == false)
  }
}
