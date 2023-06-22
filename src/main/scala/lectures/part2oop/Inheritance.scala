package lectures.part2oop

object Inheritance extends App {

  // single class inheritance: Chỉ kế thừa 1 class 1 lần
  class Animal {
    private val privateVar = "private"
    val creatureType = "wild"
    protected def eatPrivate = println("eatPrivate")
    def eat = println("nomnom")
  }
  // Class Cat (SubClass) kế thừa class Animal (SuperClass)
  // Kế thừa field creatureType và method eat (Public)
  // Private method, field chỉ truy cập đc trong phần khai báo ttin Class, instance cx k đc
  // Protected method, field chỉ truy cập đc trong phần khai báo ttin Class và SubClass
  class Cat extends Animal {
    def crunch = {
      this.eat // hoặc eat không
      this.eatPrivate // vẫn đc
      println("crunch crunch")
      //this.privateVar // error
    }
  }
  val cat = new Cat
  cat.crunch // nomnom crunch crunch
  cat.eat // nomnom
  println(cat.creatureType) // wild
  //println(cat.privateVar) // Error

  val animal = new Animal
  //println(animal.eatPrivate) // Error


  // constructors
  // Khi kế thừa, sẽ gọi constructor của SuperClass trc (Class Person() trc)
  // Nên Person() k tồn tại => lỗi, phải là Person(name, age)
  // Nếu define 1 constructor khác kiểu this(name) thì Person(name) cũng được
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding: thay thế cho method/field từ class cha
  class Dog(override val creatureType: String) extends Animal {
    // Field có thể override ở cả constructor và cả bên dưới
    // override val creatureType = "domestic"
    // override a protected method
    override def eat = {
      // super.method: gọi method từ class cha
      super.eat // println("nomnom")
      // super.field: lấy gia trị field từ class cha, dùng trong TH override field.
      // Tốt nhất k nên dùng Rất hay lỗi!!
      //println(super.creatureType)
      println("crunch, crunch")
    }
  }
  // override field trên constructor tương đương vs bên dưới
//  class Dog(dogType: String) extends Animal {
//    override val creatureType = dogType
//  }
  println("-----------------")
  val dog = new Dog("K9")
  dog.eat // nomnom crunch crunch
  println(dog.creatureType) //K9

  class test() {
    def test() = {
      println("abc")
    }
  }
  println("-----------------")
  // type substitution (broad: polymorphism)
  // Type vẫn là sub-class. Lúc gọi method sẽ vẫn ưu tiên method override cuối cùng
  // Vế trái phải là super-class, vế phải là sub-class
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat //crunch, crunch => overwrite sub class method
  println(unknownAnimal.getClass) // class lectures.part2oop.Inheritance$Dog

  // overRIDING (method/field từ subclass override method/field từ superclass, có thể cùng tên/tham số)
  // vs overLOADING (method, field trong cùng 1 class overload nhau, cùng tên, phải khác tham số)


  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on the entire class
  // 3 - seal the class = extend classes in THIS FILE, prevent extension in other files
}
