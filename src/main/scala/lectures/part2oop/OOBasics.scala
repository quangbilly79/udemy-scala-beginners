package lectures.part2oop

import scala.annotation.tailrec

object OOBasics extends App {

  // constructor
  class Person(val name: String, val age: Int = 0) {
    // body
    val x = 2

    println(1 + 3)

    // method
    def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
    //John says: Hi, Daniel => this.name => Class param
    // overloading
    def greet(): Unit = println(s"Hi, I am $name")

    // multiple constructors
    def this(name: String) = this(name, 0)
    def this() = this("John Doe")
  }

  val person = new Person("John", 26)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val person1 = new Person()
  person1.greet() //Hi, I am John Doe
  println(person1.age) //0

  class Writer(val firstName: String, val surName: String, val year: Int) {
    def fullName(): String = {
      //println(this.firstName + this.surName)
      return this.firstName + " " +  this.surName
    }
  }
  class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
    def authorAge(): Int = {
      return this.yearOfRelease - this.author.year
    }
    def isWrittenBy(author: Writer): Boolean = {
      if (author.fullName() == this.author.fullName()) {
        println("Same author")
        return true
      }
      else {
        println("Different author")
        return false
      }
    }
    def copy(newYearOfRelease: Int): Novel = {
      return new Novel(this.name, newYearOfRelease, this.author)
    }
  }

  val author1 = new Writer("Charles", "Dickens", 1812)
  val author2 = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author1)
  println("author 1 full name: " + author1.fullName()) //Charles Dickens
  println("novel.authorAge(): " + novel.authorAge()) // 1861-1812 = 49
  println("novel.isWrittenBy(author2): " + novel.isWrittenBy(author2)) //true
  val novel1 = novel.copy(2022)
  println("novel1.authorAge(): " + novel1.authorAge()) //2022-1812 = 210

  class Counter(var i: Int = 2) {
    def receive(): String = {
      return f"current count is ${this.i}"
    }
    def increment(): Counter = {
      println("Incrementing")
      return new Counter(this.i + 1)
    }
    def decrement(): Counter = {
      println("Decrementing")
      return new Counter(this.i - 1)
    }
    def increment(amount: Int): Counter = {
      println(f"Increment $amount")
      return new Counter(this.i + amount)
    }
    def decrement(amount: Int): Counter = {
      println(f"Increment $amount")
      return new Counter(this.i - amount)
    }
  }

  val Counter2 = new Counter()
  println("Counter2.receive(): " + Counter2.receive()) // 2
  val Counter1 = Counter2.decrement()
  println("Counter1.receive(): " + Counter1.receive()) // 2-1=1
  val Counterm1 = Counter1.decrement().decrement()
  println("Counterm1.receive(): " + Counterm1.receive()) // 1-1-1=-1
  val Counter5 = Counter2.increment(3)
  println("Counter5.receive(): " + Counter5.receive()) // 0+5=5
  val Counter10 = Counter5.increment(5)
  println("Counter10.receive(): " + Counter10.receive()) // 5+5=10

//  val counter = new Counter
//  counter.inc.print
//  counter.inc.inc.inc.print
//  counter.inc(10000).print
}

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */
//class Writer(firstName: String, surname: String, val year: Int) {
//  def fullName: String = firstName + " " + surname
//}
//
//class Novel(name: String, year: Int, author: Writer) {
//  def authorAge = year - author.year
//  def isWrittenBy(author: Writer) = author == this.author
//  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
//}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */
//class Counter(val count: Int = 0) {
//  def inc = {
//    println("incrementing")
//    new Counter(count + 1)  // immutability
//  }
//
//  def dec = {
//    println("decrementing")
//    new Counter(count - 1)
//  }
//
//  def inc(n: Int): Counter = {
//    if (n <= 0) this
//    else inc.inc(n-1)
//  }
//
//  def dec(n: Int): Counter =
//    if (n <= 0) this
//    else dec.dec(n-1)
//
//  def print = println(count)
//}

// class parameters are NOT FIELDS