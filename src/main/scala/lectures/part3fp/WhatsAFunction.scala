package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop
  // Tạo 1 trait giống Function Type
  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  // Tạo nhanh 1 anynomous class/instance từ trait bên trên, overwrite apply method
  // Có thể khai báo đầy đủ type của val doubler là MyFuction trait
  val doubler /*: MyFunction[Int, Int]*/ = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler.getClass) // class lectures.part3fp.WhatsAFunction$$anon$1
  println(doubler(2)) // 4


  // function types = Function1[A, B]
  // Scala có sẵn các trait có tên dạng "Functionx" (x là số param/type cần dùng, tối đa 21)
  // Có x-1 là số param đầu vào của func, còn lại là đầu ra (return)
  // Chỉ việc tạo 1 anonymous class/instace mới từ các trait này, override apply method với nội dung func
  // Function1: 1 tham số đầu vào (String) và 1 đầu ra (Int)
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4) // 7

  // Function2: 2 tham số đầu vào (Int, Int) và 1 đầu ra (Int)
  // ((Int, Int) => Int) có thể coi là 1 "function" type của variable adder
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  // Function types Function2[A, B, R] === (A,B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1.  a function which takes 2 strings and concatenates them
    2.  transform the MyPredicate and MyTransformer into function types
    3.  define a function which takes an int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */


  //1.  a function which takes 2 strings and concatenates them
  def concatenator1: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }
  println(concatenator1("Hello ", "Scala")) // Hello Scala

  // Test lồng function với các khai báo bình thường
  def concatenator2(a: String, b: String): String = {return a + b}
  def nested(a: String, b: String, f: Function2[String, String, String]): String = {
    return f(a,b)
  }
  println(nested("ab","cd",concatenator2)) //abcd

  //3.  define a function which takes an int and returns another function which takes an int and returns an int
  //        - what's the type of this function
  //        - how to do it
  // type/class la` Function1[Int, Function1[Int, Int]] hoặc (Int => Function1[Int,Int])
  val superAdder: (Int => Function1[Int,Int]) = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3) // return a Function1[Int, Int], x = 3
  println(adder3(4)) // return Int, y = 4 => 7

  // curried function: 2 apply method liên tiếp, param là 1 func và return 1 func
  println(superAdder(3)(4))  // 7

}

