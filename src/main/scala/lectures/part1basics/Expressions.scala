package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // EXPRESSION
  println(x) // 3
  println(2 + 3 * 4) // 14
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x) // false
  // == != > >= < <=

  println(!(1 == x)) // true
  // ! && || ~ khác, và, hoặc

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ..... side effects
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE)
  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  aVariable = 7
  if (aVariable == 5) println("zzzz") else println ("yyyy") // cú pháp rút gọn, yyyy
  if (aVariable == 5) { // cú pháp đầy đủ, xxxxxxx
    println("zzzz")
  }
  else if (aVariable == 6) {
    println("yyyy")
  }
  else {
    println("xxxxxxxx")
  }

  var i = 0
  val aWhile = while (i < 10) {
    //println(i)
    i += 1
  }
  println("aWhile" + aWhile) // ()
  println("aWhileClass" + aWhile.getClass) // Unit === void

  for ( x <- 4 until 10 by 1) {
    print(x) // to: 45678910; until: 456789
  }
  for (x <- "abcde") {
    print(x+"Z") //aZbZcZdZeZ
  }
  // NEVER WRITE THIS AGAIN.
  // EVERYTHING in Scala is an Expression!

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue) // ()

  // side effects: println(), whiles, reassigning (return Void)

  // Code blocks

  val aCodeBlock = { //kdl trả về là kq trả về của expression cuối cùng
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye" // Kdl của Block la String
    3 // Kdl của Block la Int
  }
  println("aCodeBlock " + aCodeBlock) //3
  // 1. difference between "hello world" vs println("hello world")? return String va return Void
  // 2.

  val someValue = {2 < 3}
  println(someValue) // true

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue) // 42

}
