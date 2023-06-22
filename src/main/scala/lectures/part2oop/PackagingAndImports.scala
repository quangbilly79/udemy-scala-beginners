package lectures.part2oop

import playground.{PrinceCharming, Cinderella => Princess}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // Class/Object trong cùng 1 package thì k cần import
  val writer = new OOBasics.Writer("Daniel", "RockTheJVM", 2018)

  // Nếu khác package thì cần import
  // import playground.{PrinceCharming, Cinderella => Princess}
  // Hoac dùng "." kiểu playground.Cinderella = fully qualified name
  val princess = new Princess

  // packages are in hierarchy
  // matching folder structure.

  // package object:
  // 1 object rieng biệt, tất cả các hàm, biến bên trong đều có thể đc gọi ở mọi nơi trong phạn vi Package cha
  // Mỗi Packace chỉ có thể có 1 Package Object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use FQ names
  val date = new Date
  val sqlDate = new SqlDate(2018, 5, 4)
  // 2. use aliasing

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
