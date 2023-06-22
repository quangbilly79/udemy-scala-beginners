package lectures.part3fp

import scala.util.Random
import scala.collection.immutable.Range
object Sequences extends App {

  // Seq (lưu ý 1 số method là cho immutable thôi)
  val mixSeq = Seq(1, false, "c")
  println(mixSeq) // List(1, false, c)
  println(mixSeq.getClass) // class scala.collection.immutable.$colon$colon
  val aSequence = Seq(1,3,2,4)
  println(aSequence) // List(1, 3, 2, 4): vì apply method của Seq trả về List
  println(aSequence.getClass) // class scala.collection.immutable.$colon$colon
  println(aSequence.reverse) // List(4, 2, 3, 1)
  println(aSequence(2)) // 2:(index tại vị trí thứ 3 (0 index), giá trị là 2
  println(aSequence ++ Seq(7,5,6)) // List(1, 3, 2, 4, 7, 5, 6): cộng 2 Seq ~ list.extend in Python
  println(aSequence :+ Seq(7,5,6)) // List(1, 3, 2, 4, Seq(7, 5, 6)): cộng thêm ptu ~ list.append in Python
  println(aSequence.patch(2,Seq(9),0)) // List(1, 3, 9, 2, 4): Thêm ptu vào index bất kì (mutable có thể dùng inser)
  println(aSequence.sorted) // List(1, 2, 3, 4)

  // Ranges
  val aRange: Seq[Int] = 1 until 10
  println(aRange.getClass) // class scala.collection.immutable.Range$Exclusive: sub-class của Seq
  aRange.foreach(println) // 1 2 3 4 5
  println("arange exist: " + aRange.exists(i => i<0)) // false
  println(aRange.sorted(Ordering.by(x => -x))) // Vector(9, 8, 7, 6, 5, 4, 3, 2, 1)

  (1 to 10).foreach(x => println("Hello")) // Hello Hello ...
  // 2 method foreach trên và dưới khác nhau chút

  println(aRange(1)) // 2
  // lists
  val mixList = List(1, false, "c")
  println(mixList) // List(1, false, c)
  println(mixList.getClass) // class scala.collection.immutable.$colon$colon
  val aList = List(1,2,3)
  println(aList.getClass) // class scala.collection.immutable.$colon$colon
  println(aList(1)) // 2
  val prepended = 42 +: aList :+ 89 // List(42, 1, 2, 3, 89): +:, :+, ++ : Thêm vào đầu và đuôi List
  //val prepedned1 = aList ++ 89 // Loi vì ++ k phải method của Int
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5) // List(apple, apple, apple, apple, apple): fill: Lấp đầy 1 list vs n ptu bất kì
  // Seq cx có fill vì List là con của Seq
  println(aList.mkString("-|-")) // 1-|-2-|-3: mkString: Hiển thị list vs seperator định sẵn

  // arrays
  val mixArray = Array(1, true, "Abc")

  println(mixArray) // [Ljava.lang.Object;@4bb4de6a
  println(mixArray.getClass) // class [Ljava.lang.Object;
  val numbers = Array(1,2,3,4)
  println(numbers.getClass) // class [I: Do array k có toString method chuẩn. [ có thể đọc là array of
  // Tạo 1 Array rỗng vs số ptu nhất định, hoặc fill 1 array vs số ptu nhất định và ptu nhat định
  val threeElements = Array.ofDim[String](3)
  val threeElements1 = Array.fill(3)(1)
  threeElements.foreach(println) // null null null / 0 0 0
  threeElements1.foreach(println) // 1 1 1
  println(numbers(2)) // 3
  // mutation
  numbers(2) = 0  // syntax sugar for numbers.update(2, 0) ~ apply: Thay giá trị ở index 2 = 0
  println(numbers.mkString(" ")) // 1 2 0 4

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers  // implicit conversion: Convert ngầm bởi Scala
  println(numbersSeq.getClass) // class scala.collection.immutable.ArraySeq$ofInt
  println(numbersSeq) // ArraySeq(1, 2, 0, 4)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector.getClass) // class scala.collection.immutable.Vector1
  println(vector) // Vector(1, 2, 3)
  println(vector.updated(0,9)) // Vector(9, 2, 3): Updated theo index, tuy vậy k phải mutable
  println(vector) // Vector(1, 2, 3)
  println(0 +: vector :+ 4 :+ 5)  // Vector(0, 1, 2, 3, 4, 5): Các dấu prepend và append vẫn như vậy

  // vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns // Chạy vòng lặp 1000 lần (maxRuns)
    } yield {
      val currentTime = System.nanoTime() // Time bđ
      // Insert ptu vào collection ở vị trí bất kì vs giá trị bất kì
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      // Tính toán mất bao lâu để insert 1 ptu, lưu vào list times
      System.nanoTime() - currentTime
    }
    // yield: ~ list comprehension Python, iterate Range sẽ trả về Vector
    // class scala.collection.immutable.Vector2
    println(times.getClass)

    times.sum * 1.0 / maxRuns // Tính TB = tổng thời gian insert / số lần insert (1000)
  }
  val numbersList = (1 to maxCapacity).toList // Tạo 1 List
  val numbersVector = (1 to maxCapacity).toVector // Tạo 1 Vector
  // keeps reference to tail
  // updating an element in the middle takes long
  // println(getWriteTime(numbersList)) // 6234386.1 lâu
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  // println(getWriteTime(numbersVector)) // 7547.0 nhanh

}
