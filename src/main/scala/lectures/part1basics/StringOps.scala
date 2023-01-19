package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"
  println("default String method")
  println(str.charAt(2)) // l => 0-index
  println(str.substring(7, 11)) // I am =>7,8,9,10
  println(str.split(" ").toList) // List(Hello,, I, am, learning, Scala)
  println(str.startsWith("Hello")) // true
  println(str.replace(" ", "-")) // Hello,-I-am-learning-Scala
  println(str.toLowerCase()) // hello, i am learning scala
  println(str.length) // 26
  println("convert Data Type, special func")
  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  // +: là để concenate String vs Char, còn + là để dùng cho String vs String
  println(str.reverse) //alacS gninrael ma I ,olleH
  println(str.take(2)) //He

  println("Scala-specific: String interpolators.")
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  //Hello, my name is David and I will be turning 13 years old.
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
  //David can eat 1.20 burgers per minute
  println(anotherGreeting)

  println("F-interpolators")
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth) //David can eat 1.20 burgers per minute

  println("raw-interpolator")
  println(raw"This is a \n newline") // This is a \n newline
  val escaped = "This is a \n newline"
//  This is a
//  newline
  println(raw"$escaped")
  println("--------------")
  println("This is a \\n newline")
}
