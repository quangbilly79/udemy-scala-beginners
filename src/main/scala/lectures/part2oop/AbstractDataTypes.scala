package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract: 1 Class chứa Field và Method không define chi tiết (nội dung blank)
  // Chi tiết sẽ đc define trong sub-class sau
  // Không thể tạo instance từ abstract class, phải đc extend từ 1 sub-class
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }
  // Phải override tất cả các field và method từ abstract class
  class Dog extends Animal {
    override val creatureType: String = "Canine"
    // K cần từ khóa override lúc define method, Scala tự hiểu
    /*override*/ def eat: Unit = println("crunch crunch")
  }

  // traits: 1 Object chứa Field và Method đã đc define chi tiết và
  // có thể đc sử dụng ở nhiều Class khác
  // Không thể thêm tham số đầu vào
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "abc"
  }

  trait ColdBlooded
  // 1 Class có thể extend (kế thừa) 1 abstract class, with (với) nhiều trait
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    // override method trong abstract class
    def eat: Unit = println("nomnomnom")
    // override method trong trait
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog) // I'm a croc and I'm eating Canine

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behavior (Carnivore - ăn thịt), abstract class = "thing" (Animal)

  val list1: List[Int] = List(1,2,3,4)
}
