package lectures.part2oop

trait MyTrait {
  val myField: String = "Hello, world!"
  var myVar: Int = 42
}

object MyObject extends MyTrait {
  def main(args: Array[String]): Unit = {
    println(myField) // accessing a val defined in the trait
    myVar += 1 // accessing a var defined in the trait
    println(myVar) // prints 43

    object Person { // type + its only instance
      // "static"/"class" - level functionality
      val N_EYES = 2

      def canFly: Boolean = false

      // factory method
      def apply(): Unit = {
        println(s"${myField} and ${myVar}")
      }
    }
    val person1 = Person
    person1.apply()

    // Vẫn có thể nhận tham số đầu vào như bthg
    class MyList[A] (a: String) {
      // Tham số của các method bên trong có thể define chỉ chấp nhận kdl đúng type vs generic
      def printting(x: A): Unit = {
        println(x)
      }
    }
    val list1 = new MyList[Int]("abc") //abc
    //list1.printting("c") //3
  }
}