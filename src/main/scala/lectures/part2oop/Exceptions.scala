package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //  println(x.length)
  //  this ^^ will crash with a NPE (Null Point Exception)

  // 1. throwing exceptions
  // Mọi thứ trong Scala là expression nên có thể gán giá trị cho 1 biến
  // Return type là Nothing, là sub-class của mọi type
  // NullPointerException là 1 class, extend Throwable class, nên có thể throw
  // Exception and Error are the major Throwable subtypes

  //val aWeirdValue: String = throw new NullPointerException // also crashes



  // 2. how to catch exceptions
  // Hàm mà Nếu đầu vào là True thì throw error
  // Nếu là False thì trả về 42
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new NullPointerException ("No int for you!")
    else 42
  // Catch lỗi vs Try/Catch/Finally
  val potentialFail = try {
    // Đoạn code mà có thể bị lỗi
    getInt(true)
  } catch {
        // Nếu bị lỗi thì chạy đoạn dưới
        // Có thể thiết kế kiểu tùy loại lỗi mà chạy những lệnh khác nhau
        // cú pháp case e: ErrorType => Giá trị trả về liên quan đến pattern matching
    case e: ArrayIndexOutOfBoundsException  => 43
    case e: NoSuchElementException  => 41
      // Có thể viet thành Java code kiểu
//    catch (e: RuntimeException) {
//      return 43
//    }
  } finally {
    // Đoạn dưới này dù lỗi hay không đều chạy
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }
  // finally
  println("---------")
  println(potentialFail) //43
  println("---------")


  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //  throw exception

  /*
    1.  Crash your program with an OutOfMemoryError
    2.  Crash with SOError
    3.  PocketCalculator
        - add(x,y)
        - subtract(x,y)
        - multiply(x,y)
        - divide(x,y)

        Throw
          - OverflowException if add(x,y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
   */
  //  OOM (out of memory error)
  //  val array = Array.ofDim(Int.MaxValue)

  //  SO (stack over flow error)
  //  def infinite: Int = 1 + infinite
  //  val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

  }

  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}
