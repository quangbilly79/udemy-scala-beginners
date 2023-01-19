package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }
  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }
  calledByValue(System.nanoTime())
//  by value: 396471326958800
//  by value: 396471326958800 same
  calledByName(System.nanoTime())
//  by name: 396471403526800
//  by name: 396471404471800 diff

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //  printFirst(infinite(), 34) // stack overflow
  printFirst(34, infinite())

  def testFunc() = {
    1
  }
  def testFuncParam(func: Int) = {
    func
  }

  println("TestFunc: " + testFuncParam(testFunc()))
}
