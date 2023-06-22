package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields (k cần phải thêm val vào chỗ khai báo param nữa)
  val jim = new Person("Jim", 34)
  println(jim.name) // Jim

  // 2. sensible toString:
  // Bình thg khi print/toString instance/class sẽ ra kiểu hashcode,
  // nhưng vs case class sẽ ra ClassName(Param1,Param2) luôn
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim) // Person(Jim,34)

  // 3. equals and hashCode implemented OOTB
  // 2 instace khác nhau vẫn = nhau (trong khi class thuong thì không)
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true

  // 4. CCs have handy copy method
  // Có thể dễ dàng copy (trong khi class thường k có method copy này, chỉ có clone)
  val jim3 = jim.copy(age = 45)
  println(jim3) // Person(Jim,45)

  // 5. CCs have companion objects
  // Tự động tạo 1 object Person đi kèm Class luôn (companion)
  val thePerson = Person
  // Dùng apply method vs companion object ~ constructor new Person(...)
  val mary = Person("Mary", 23)

  // 6. CCs are serializable
  // Hữu dụng trong distributed system
  // Akka framework

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING
  // Học ở phần sau

  // Tương tự case class, nhưng k có mục 5. CCs have companion objects. Vì đã là object sẵn rồi
  // Tương tự object, cx k accept param
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyList - use case classes and case objects
   */
}
