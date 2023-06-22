package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  // Object Scala ~ Class trong Java mà dùng static method.
  // Tương tự Class Scala, ta có thể khai báo các field, các hàm vợi Object
  // Object Scala và Class Scala khác nhau ở chỗ: Object k nhận tham số đầu vào
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  println(Person.N_EYES) //2
  println(Person.canFly) //false

  // Object Scala có thể coi là 1 Instance độc lập của 1 Class Scala
  // gồm type và 1 instance duy nhất
  val a = Person;
  val b = Person;
  println(a == b); // true

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

    // Scala object = SINGLETON INSTANCE
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john) // false


    val bobbie = Person(mary, john)
    
  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
}
