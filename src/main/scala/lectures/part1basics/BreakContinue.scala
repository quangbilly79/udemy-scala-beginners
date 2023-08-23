package lectures.part1basics
// Sử dụng method breakable trong util.control.Breaks
import util.control.Breaks._

object BreakContinue {
  def main(args: Array[String]): Unit = {
    // Break
    // Thêm breakable ở bên ngoài vòng lặp
    breakable {
      for (i <- 1 to 10) {
        if (i > 5) {
          break
        }
        println(i) //1 2 3 4 5
      }
    }

    // Continue
    // Thêm breakable ở bên trong vòng lặp
    for (i <- 1 to 10) {
      breakable {
        if (i > 5) {
          break // nếu i>5 thì break "breakable", continue đến vòng lặp tiếp theo
        } else {
          println(i)
        }
      }
    }
  }
}
