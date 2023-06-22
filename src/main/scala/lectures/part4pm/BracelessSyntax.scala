package lectures.part4pm

object BracelessSyntax extends App {
  // Dạng đầy đủ.
  val anIfExpressionBrace: String = {
    if (2>3) {
      "bigger"
    } else {
      "smaller"
    }
  }
  // Dạng ngắn gọn không {} (brace)
  // ~ anIfExpression = "bigger" if (2>3) else "smaller" trong Python
  val anIfExpressionNoBrace = if (2>3) "bigger" else "smaller"
  println(anIfExpressionNoBrace) //smaller

  // Hoặc có thể viết thành nhiều dòng ntn cho dễ nhìn (cần thêm "then")
  val anIfExpressionv4 =
    if 2>3 then
      "bigger"
    else
      "smaller"
  // Có thể viết 1 cách phức tạp hơn với val và return, gần như tạo thành 1 function
  val anIfExpressionv5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result


  println(anIfExpressionv5)
}
