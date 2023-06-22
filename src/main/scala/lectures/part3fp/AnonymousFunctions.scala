package lectures.part3fp

import lectures.part3fp.WhatsAFunction.MyFunction

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int = (x: Int) => x*2
  //val doubler = (x:Int) => x*2
  // Tương đương với 2 đoạn code đầy đủ dưới đây
  val doubler1 = new Function1[Int, Int] {
    override def apply(x: Int): Int = {
      x*2
    }
  }
  def doubler2(x: Int): Int = {return x*2}
  // Hoặc tương đương vs python lambda
  // y = lambda x: x*2; print(y(2));

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  // Tương đương với python lambda
  // y = lambda a,b: a+b; print(y(3,4)) # 7


  // no params: Lambda func k tham số
  val justDoSomething: () => Int = () => 3
  // careful: Nhớ gọi với ()
  println(justDoSomething) // function itself: lectures.part3fp.AnonymousFunctions$$$Lambda$16/0x00000008000c1040@4a87761d
  println(justDoSomething()) // call: 3

  // curly braces with lambdas: 1 syntax khác
  val stringToInt = { (str: String) =>
    str.toInt
  }
  // Tương đương với
  val stringToInt1: (String => Int) = (str: String) => str.toInt

  // MOAR syntactic sugar: Syntax dạng short-hand hơn nữa
  // _ thay thế cho tham số đầu vào
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */
  val superAdder: (Int => Function1[Int, Int]) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

  val test = (name: String, age: Int) => "name" + name + "is" + age
  println(test("A", 3)) // nameAis3
}
