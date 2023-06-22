package lectures.part3fp

import scala.annotation.tailrec

/**
  * Created by Daniel.
  */
object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val bTuple: Tuple = (1,2,3,4,5)
  println(bTuple.getClass) // class scala.Tuple5
  val aTuple = (2, "hello, Scala")  // Tuple2[Int, String] = (Int, String)
  println(aTuple.getClass) // class scala.Tuple2
  println(aTuple._1)  // 2 (1 indexed)
  println(aTuple.copy(_2 = "goodbye Java")) // copy thành 1 tuple mới với ptu ms (giống case class)
  println(aTuple.swap)  // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map() // Blank map, Key là String, Value là Int

  // set DefaultValue xử lý TH apply(key) mà k tồn tại
  val phonebook = Map(("Jim", 555), "Daniel" -> 789, ("JIM", 9000)).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phonebook) // Map(Jim -> 555, Daniel -> 789, JIM -> 9000)

  // map ops
  println(phonebook.contains("Jim")) // true
  println(phonebook("Mary")) // Apply. Trả về -1 vì set DefaultValue bên trên, nếu k sẽ báo lỗi
  println(phonebook.get("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing // ~ dict.update trong Python,
  // note là + giữa Map và (Key,value), k phải Map + Map, vd Map(a->b) + Map(c->d) sẽ lỗi
  println(newPhonebook) // Map(Jim -> 555, Daniel -> 789, JIM -> 9000, Mary -> 678)

  // functionals on maps

  // map, flat map, filter
  // Khi map/flat map1 map trong Scala, sẽ iterate qua từng (key, value) pair trong 1 map
  // ~ dict.items trong Python, trả về 1 tuple (key, value)
  // Map(Jim -> 555, Daniel -> 789, JIM -> 9000)
  // map key
  // Như ở dưới là lowerCase các Key, Value giu nguyên
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // Map(jim -> 9000, daniel -> 789)
  // flat map: Như ở dưới đây là tách (key,value) ra thành 1 Seq các giá trị,
  // sau đó gộp và trộn các ptu của các Seq
  println(phonebook.flatMap(pair => Seq[Any](pair._1.toUpperCase, pair._1.toLowerCase,
    pair._2 + 2000))) // List(JIM, jim, 2555, DANIEL, daniel, 2789, JIM, jim, 11000)

  // filter key: Phải thêm .view trong phiên bản ms, nếu k thêm sẽ có tbao deprecated. Xong phải toMap lại từ View
  // k có view.filterValues
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap) // Map(Jim -> 555, JIM -> 9000)

  // map value: Phải thêm .view trong phiên bản ms, nếu k thêm sẽ có tbao deprecated. Xong phải toMap lại từ View
  // k có view.mapKeys
  println(phonebook.view.mapValues(number => "0245-" + number).toMap) //Map(Jim -> 0245-555, Daniel -> 0245-789, JIM -> 0245-9000)
  // filter value: Muốn filter value thì có th dùng filter thường (._2), tương tự filer key sẽ là ._1
  println(phonebook.view.filter(pair => pair._2 < 600).toMap) // Map(Jim -> 555)

  // conversions to other collections
  // Chuyển sang List((k1,v1), (k2,v2))
  println(phonebook.toList) // List((Jim,555), (Daniel,789), (JIM,9000))
  // Convert ng lại từ List sang Map. Vẫn phải đảm bảo số lg ptu và type tương ứng
  println(List(("Daniel", 555)).toMap) // Map(Daniel -> 555)

  // group by (dùng đc vs cả List và Map, cơ mà thg dùng vs List)
  val phonebook1:Map[String, Int] = Map("Jack" -> 3, "Jim" -> 4, "Ana" -> 5)
  println(phonebook1)
  println(phonebook1.groupBy(pair => pair._1.charAt(0))) // HashMap(J -> Map(Jack -> 3, Jim -> 4), A -> Map(Ana -> 5))

  // Group những ng có chữ cái đầu tên giống nhau vào 1 nhóm
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  // HashMap(J -> List(James, Jim), A -> List(Angela), M -> List(Mary), B -> List(Bob), D -> List(Daniel))
  // Group những ng có chữ cái đầu tên là J vào 1 nhóm
  println(names.groupBy(name => name.charAt(0) == 'J'))
  // HashMap(false -> List(Bob, Angela, Mary, Daniel), true -> List(James, Jim))

  /*
    1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

        => Sau khi lowercase Key thì chỉ còn Jim -> 900. K đc phép trùng Key trong Map
  */

  // Overly simplified social network based on maps: Map(name -> FriendSet())
  val testSocialNetwork: Map[String, Set[String]] = Map("bob" -> Set("lily", "julia"), "jack" -> Set())
  println(testSocialNetwork) // testSocialNetwork: Map("bob" -> Set("lily", "julia"), "jack" -> Set())

  // add a person to the network (mặc định k friend)
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set(""))
  }
  println("Add: " + add(testSocialNetwork, "lily"))
  // Map(bob -> Set(lily, julia), jack -> Set(), lily -> Set())

  // remove (remove cả ng đó trong ds bạn của ng khác nếu có)
  val network0 = Map("bob" -> Set("lily", "jack"), "jack" -> Set("bob", "bin"), "bin" -> Set("jack"))

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    var newNetwork = network
    for (key <- network.keys) {
      if (network(key).contains(person)) {
        newNetwork = unfriend(network, key, person)
      }
    }
    newNetwork = newNetwork - person
    newNetwork
  }
  println("Remove: " + remove(network0, "bob")) // Map(jack -> Set(bin), bin -> Set(jack))

  // testSocialNetwork: Map("bob" -> Set("lily", "julia"), "jack" -> Set())
  // friend (mutual)
  // check điều kiện xem person 1 hay person 2 có tồn tại hay k. Cả 2 tồn tại ms ok
  def friend(network: Map[String, Set[String]], person1: String, person2: String) = {
    if (!network.contains(person1) || !network.contains(person2)) {
      println(s"${person1} or ${person2} not exist")
      network
    }
    else {
      // Thêm ptu vào Set vs "+". Tạo 1 Update dict mới vs updated(key,newValue)
//      val newNetwork = network.updated(person1, network(person1) + person2)
//        .updated(person2, network(person2) + person1)
//      newNetwork
      // Hoặc thêm trực tiếp key, value ms vì key cũ sẽ tự động bị replace
      network + (person1 -> (network(person1) + person2)) + (person2 -> (network(person2) + person1))
    }
  }
  println("friend: " + friend(testSocialNetwork, person1 = "bob",person2 = "jack"))
  // Map(bob -> Set(lily, julia, jack), jack -> Set(bob))

  // testSocialNetwork: Map("bob" -> Set("lily", "julia"), "jack" -> Set())
  // unfriend: Tương tự bên trên nhưng dùng "-"
  def unfriend(network: Map[String, Set[String]], person1: String, person2: String) = {
    network + (person1 -> (network(person1) - person2)) + (person2 -> (network(person2) - person1))
  }
  val network1 = friend(testSocialNetwork, person1 = "bob",person2 = "jack")
  // Map(bob -> Set(lily, julia, jack), jack -> Set(bob))
  println("unfriend: " + unfriend(network1, person1 = "bob",person2 = "jack"))
  // Map(bob -> Set(lily, julia), jack -> Set())

  // testSocialNetwork: Map("bob" -> Set("lily", "julia"), "jack" -> Set())
  // number of friends of a person: Dùng size
  def numOfFriend(network: Map[String, Set[String]], person1: String) = {
    if (!network.contains(person1)) {
      -1
    }
    else {
      network(person1).size
    }
  }
  println("numOfFriend: " + numOfFriend(testSocialNetwork, "bob")) // 2
  println("numOfFriend: " + numOfFriend(testSocialNetwork, "jack")) // 0

  // testSocialNetwork: Map("bob" -> Set("lily", "julia"), "jack" -> Set())
  // person with most friends: Vòng lặp từng người trong Network, ktra và gán số bạn, tên ng có nh bạn nhất
  def mostFriend(network: Map[String, Set[String]]) = {
    var numFriend = 0
    var name = ""
    for (key <- network.keys) {
      if (network(key).size > numFriend) {
        numFriend = network(key).size
        name = key
      }
    }
    name
  }
  println(mostFriend(testSocialNetwork)) // bob

  // how many people have NO friends: lọc ra 1 map những ng k có bạn, rồi tính size của map đó
  def numOfPeopleWithNoFriend(network: Map[String, Set[String]]): Int = {
    val noFriendNetwork = network.filter(pair => pair._2.isEmpty) // hoặc .size == 0
    val noFriendNetwork1 = network.filter(_._2.isEmpty) // _: vs mỗi ptu trong Collection (pair => pair...)
    val sizeNetwork = noFriendNetwork.size
    sizeNetwork
  }
  println(numOfPeopleWithNoFriend(testSocialNetwork)) // 1


  // if there is a social connection between two people (direct or not)
  val network2 = Map("bob" -> Set("jack"), "jack" -> Set("bob", "jone"), "jone" -> Set("jack", "jin"),
    "jin" -> Set("jone"), "lily" -> Set())

  def areConnected(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    // Check if A and B are direct friends
    if (network(personA).contains(personB)) {
      println(f"direct friend. personA: ${personA}. personB: ${personB}")
      true
    } else {
      // Check if A and B have mutual friends who connect them indirectly
      val aFriends = network.getOrElse(personA, Set.empty)
      val bFriends = network.getOrElse(personB, Set.empty)
      val mutualFriends = aFriends ++ bFriends
      println(f"indirect friend. personA: ${personA}. personB: ${personB}")
      println(f"aFriends: $aFriends, bFriends: $bFriends,mutualFriends: $mutualFriends")
      println("before call function")
      mutualFriends.exists(friend => areConnected(network, personA, friend) && areConnected(network, personB, friend))
    }
  }

  println(areConnected(network2, "bob", "jin"))
  println("----------")
  //println(areConnected(network2, "bob", "lily"))
//  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
//    network + (person -> Set())
//
//  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
//    val friendsA = network(a)
//    val friendsB = network(b)
//
//    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
//  }
//
//  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
//    val friendsA = network(a)
//    val friendsB = network(b)
//
//    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
//  }
//
//  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
//    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
//      if (friends.isEmpty) networkAcc
//      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
//
//    val unfriended = removeAux(network(person), network)
//    unfriended - person
//  }
//
//  val empty: Map[String, Set[String]] = Map()
//  val network = add(add(empty, "Bob"), "Mary")
//  println(network)
//  println(friend(network, "Bob", "Mary"))
//  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
//  println(remove(friend(network, "Bob", "Mary"), "Bob"))
//
//  // Jim,Bob,Mary
//  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
//  val jimBob = friend(people, "Bob", "Jim")
//  val testNet = friend(jimBob, "Bob", "Mary")
//
//  println(testNet)
//
//  def nFriends(network: Map[String, Set[String]], person: String): Int =
//    if (!network.contains(person)) 0
//    else network(person).size
//
//  println(nFriends(testNet, "Bob"))
//
//  def mostFriends(network: Map[String, Set[String]]): String =
//    network.maxBy(pair => pair._2.size)._1

//  println(mostFriends(testNet))
//
//  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
//    network.count(_._2.isEmpty)
//
//  println(nPeopleWithNoFriends(testNet))
//
//  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
//    @tailrec
//    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
//      if (discoveredPeople.isEmpty) false
//      else {
//        val person = discoveredPeople.head
//        if (person == target) true
//        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
//        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
//      }
//    }
//
//    bfs(b, Set(), network(a) + a)
//  }
//
//  println(socialConnection(testNet, "Mary", "Jim"))
//  println(socialConnection(network, "Mary", "Bob"))

}
