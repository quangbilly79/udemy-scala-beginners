package lectures.part2oop

// Generic: Nhận Type (kdl) làm tham số
object Generics extends App {

  class MyList[+A] {
    // B tương tự Animal, A tương tự Dog/Cat. Dog/Cat kế thừa từ Animal
    // Thêm 1 Animal vào list Dog/Cat thì phải trả về list Animal
    // B >: A: Class B phải là cha của Class A, Animal là cha của Dog/Cat
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }


  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  // Cat, Dog extend Animal
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  // Thêm dấu + trc Type +A
  class CovariantList[+A]
  // Tương tự như polymorph
  val animal: Animal = new Cat
  // Nhưng với List của Animal và List của Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return a list of Animals

  // 2. NO = INVARIANCE. List Cat KHÔNG extend List Animal
  // Không có dấu +
  class InvariantList[A]
  //val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]

  // 3. Hell, no! CONTRAVARIANCE
  // Nếu nói thay thế List Cat = List Animal thì sẽ thấy sai, vì Animal có cả Dog
  class contravarianceAnimalList[-A]
  val contravarianceAnimalList: contravarianceAnimalList[Cat]
  = new contravarianceAnimalList[Animal]
  // Nhưng nếu nói thay thế Trainer cho Cat = Trainer cho Animal thì ổn
  // Vì Trainer cho đng vật nói chung cx có thể train cho Cat
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // Chỉ chấp nhận Type là Class con của 1 Type khác
  // Dog là Class con của Class Animal nên ok
  class Cage[A <: Animal](animal: A)
  val cageDog = new Cage(new Dog)
  val cageCat = new Cage(new Cat)
  // Car không là Class con của Class Animal nên lỗi
  class Car
  //val newCage = new Cage(new Car)


  // expand MyList to be generic

}
