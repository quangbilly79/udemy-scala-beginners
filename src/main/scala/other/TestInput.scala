package other

import java.io.{File, FileNotFoundException}
import java.util.Scanner; // Import the Scanner class to read text files


object TestInput extends App {
  // Tạo 1 file object từ File Class với đg dẫn
  val file = new File("/home/quang/Documents/server.txt")
  // Tạo 1 scanner object trỏ đến file bên trên
  val scanner = new Scanner(file)
  // Đọc file vs các method như nextLine, nextInt
  /*
  while (scanner.hasNext) {
    println(scanner.nextLine())
  }
  */

  // Sử dụng scala.io.StdIn với các method như
  // readLine, readInt, readBoolean,...
  print("Input something Scala: ")
  val userInputScala = scala.io.StdIn.readLine()
  println("The value of userInputScala is " + userInputScala)

  // Sử dụng java.utils.scanner như bên Java
  val scanner1 = new Scanner(System.in)
  print("Input something Java: ")
  val userInputJava = scanner1.nextLine()
  println("The value of userInputJava is " + userInputJava)

}
