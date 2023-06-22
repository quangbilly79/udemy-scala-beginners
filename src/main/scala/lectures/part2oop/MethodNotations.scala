package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    // def __add__ trong Python
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    //def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)

//  1. Overload the + operator mary + "the rockstar" => new person "Mary (the rockstar)"
    def +(nickname: String): Person = {
      new Person(name = s"${this.name} (the $nickname)", favoriteMovie = this.favoriteMovie);
    }

    def unary_! : String = s"$name, what the heck?!"

    //2.  Add an age to the Person class
    //        Add a unary + operator => new person with the age + 1
    //        +mary => mary with the age incrementer
    def unary_+ : Person = {
      new Person(name = this.name, favoriteMovie = this.favoriteMovie, age = this.age+1);
    }

    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    //4.  Overload the apply method
    //        mary.apply(2) => "Mary watched Inception 2 times"
    def apply(timeWatch: Int): String = {
      return s"${this.name} watched ${this.favoriteMovie} $timeWatch times"
    }


    //3.  Add a "learns" method in the Person class => "Mary learns Scala"
    //        Add a learnsScala method, calls learns method with "Scala".
    //        Use it in postfix notation.
    def learns(subject: String): String = {
      s"${this.name} learns $subject";
    }
    def learnsScala(): String = {
      this.learns("scala");
      //learns("scala); // call như bên dưới cx đc
    }


  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom) //Mary is hanging out with Tom
  println(mary.+(tom)) //Mary is hanging out with Tom

  val maryWithNickName: Person = mary + "rockstar" //
  println(maryWithNickName()) // Hi, my name is Mary (the rockstar) and I like Inception

  println(maryWithNickName(3)) // Mary (the rockstar) watched Inception 3 times

  val maryWithAdditionalAge: Person = +mary // age 0
  println(maryWithAdditionalAge.age) // age 1



  println(1 + 2) //3
  println(1.+(2)) //3

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?

  // prefix notation
  val x = -1  // equivalent with 1.unary_- -1
  val y = 1.unary_- // -1
  // unary_ prefix only works with - + ~ !

  println(!mary) //Mary, what the heck?!
  println(mary.unary_!) //Mary, what the heck?!

  // postfix notation
  println(mary.isAlive) //true
  println(mary isAlive) // only available with the scala.language.postfixOps import - discouraged

  // apply
  println(mary.apply()) //Hi, my name is Mary and I like Inception
  println(mary()) // Hi, my name is Mary and I like Inception

  // learn
  println(mary.learns("java")) // Mary learns java
  println(mary.learnsScala()) // Mary learns scala





}
