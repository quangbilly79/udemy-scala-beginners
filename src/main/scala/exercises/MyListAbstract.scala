package exercises


abstract class MyListAbstract {

  /*
   head = first element of  the  list
   tail = remainder of the list
   isEmpty = is this list empty
   add(int) => new list with this element added (new instance since list is immutable)
   toString => a string representation of the list
 */
  def head: Int
  def tail: MyListAbstract
  def isEmpty: Boolean
  def add(i: Int): MyListAbstract
  def printElements: String
  // toString ~ __str__ trong Python, sửa kết qua đầu ra của println()
  override def toString: String = "[" + printElements + "]"
}
//??? (nothing) can be used for marking methods that remain to be implemented.
/// def a: { ??? }

object EmptyList extends MyListAbstract {
  def head: Int = {
    //throw new Exception("empty list no head")
    throw new NoSuchElementException()
  }
  def tail: MyListAbstract = {
    throw new NoSuchElementException()
  }
  def isEmpty: Boolean = {
    return true
  }
  def add(i: Int): MyListAbstract = {
    return new NonEmptyList(i, EmptyList)
  }
  def printElements: String = {
    return ""
  }
}


class NonEmptyList(h: Int, t: MyListAbstract) extends MyListAbstract {
  def head: Int = {
    return h //
  }
  def tail: MyListAbstract = {
    return t
  }
  def isEmpty: Boolean = {
    return false
  }
  def add(i: Int): MyListAbstract = {
    return new NonEmptyList(i, this)
  }
  def printElements: String = {

    if (this.t.isEmpty) {
      return s"${this.h}"
    }
    else {
      return s"${this.h}, ${this.t.printElements}"
    }
  }
}

object ListTesting extends App {
  val list1 = new NonEmptyList(1, EmptyList)
  println(list1.head) // 1
  val linkedList1 = new NonEmptyList(1, new NonEmptyList(2, new NonEmptyList(3, EmptyList)))
  println(linkedList1.tail.head) // 2
  println(linkedList1.tail.tail.tail) // exercises.EmptyList$@1b26f7b2
  println(linkedList1.add(4).head) // 4
  println(linkedList1) // [1, 2, 3]
}