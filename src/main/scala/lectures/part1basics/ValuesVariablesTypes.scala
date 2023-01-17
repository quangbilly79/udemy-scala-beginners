package lectures.part1basics

object ValuesVariablesTypes extends App {
  // extends App: turn an object into main object without def main

  val x: Int = 42
  println(x)

  // VALS ARE IMMUTABLE
  // COMPILER can infer types
  val aString: String = "hello"
  val anotherString = "goodbye"
  val aBoolean: Boolean = false //true
  val aChar: Char = 'a' //Single Quote, String la Double Quote
  val anInt: Int = x //-2^31 -> 2^31 - 1 ~ 4 bytes
  val aShort: Short = 4613 //-2^15 -> 2^15 - 1 ~ 2 bytes
  val aLong: Long = 5273985273895237L //-2^63 -> 2^63 - 1 ~ 8 bytes
  val aFloat: Float = 2.0f // ~ 4 bytes
  val aDouble: Double = 3.14 // ~ 8 bytes
  println(f"I like the number ${aDouble}%.3f")
  var aBigInt = BigInt(1234567890) //Math.BigInt, no maximum value, hold any value
  var aBigDecimal = BigDecimal(123456789.1345)
  // variables
  var aVariable: Int = 4
  var list1: List[Any] = List(1,2,3,4)
  aVariable = 5 // side effects
}
