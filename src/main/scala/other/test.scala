package other

import java.text.SimpleDateFormat
import java.util.Calendar
import scala.collection.{Seq, mutable}

object test extends App {

  val calendar = Calendar.getInstance()
  val dateFormatter = new SimpleDateFormat("yyyyMMdd")
  val date = dateFormatter.parse("20230820")
  calendar.setTime(date)
  println(calendar.get(Calendar.DAY_OF_WEEK))
  println(Calendar.MONDAY + " " + Calendar.SUNDAY + " " + Calendar.SATURDAY)
  println("1 23 4".contains("1 23"))
  var testSeq: scala.collection.mutable.Seq[String] = scala.collection.mutable.Seq("cde")
  println(testSeq)
  var testSeq1 = testSeq +: "abcc"
  println(testSeq)
  var testString = "Abc"
  testString += ("Test" + "\n")
  testString += ("Test1" + "\n")
  println(testString)
  println("+++++++++++++++")
  val seq1: Seq[Any] = Seq(150000, 1200000, "25,26,27,28,29,30,31", 150000, 2000000)
  println(seq1(1))
  def validateCountEachDayTest (thresholdMin: Int, thresholdMax: Int,
                           specialDate1: String = "", thresholdMin1: Int = 0, thresholdMax1: Int = 0): Unit = {
    println(s"$thresholdMin, $thresholdMax, $specialDate1, $thresholdMin1, $thresholdMax1")
  }

  val tableMap = Map(
    "vega_action_audit" -> Map(
      "validateCountEachDay" -> Seq(150000, 1200000, "25,26,27,28,29,30,31", 150000, 2000000),
      "validateDistinctRatioEachDay" -> Seq(1.0),
      "validateColumnEachDay" -> Seq(Seq("isdn", "null", 0.0))
    ),
    "vega_first_call" -> Map(
      "validateCountEachDay" -> Seq(0, 0, "cn", 30000000, 32000000),
      "validateDistinctRatioEachDay" -> Seq(1.0),
      "validateDistinctIsdnEachDay" -> Seq("isdn", 1.01),
      "validateColumnEachDay" -> Seq(Seq("isdn", "null", 0.0), Seq("sub_type", "null", 0.0))
    )
  )
  
  val myList: mutable.Buffer[Int] = mutable.Buffer(1, 2, 3, 4)

  // Append an element
  myList += 5

  // Insert an element at a specific index
  myList.insert(2, 6)

  // Remove an element at a specific index
  myList.remove(3)

  println(myList) // Output: ArrayBuffer(1, 2, 6, 4, 5)
  println(myList.sorted.reverse) // ArrayBuffer(6, 5, 4, 2, 1)

}
