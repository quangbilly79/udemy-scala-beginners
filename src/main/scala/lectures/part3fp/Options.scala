package lectures.part3fp

import java.util.Random

object Options extends App {

  // Khai báo kdl Option dạng Option[kdl] = Some(value) hoặc None
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption) // Some(4)
  println(noOption) // None

  // 1 hàm trả về null hoặc 1 giá trị nào đó
  def unsafeMethod(): String = null
  // Dùng Option(param) (~.apply(param)), trả về kdl Option
  // trả về Some(value) nếu param k null, và None nếu param null
  val result = Option(unsafeMethod()) // Some or None
  println(result) // None

  // chained methods. Dùng Option.orElse(value) để trả về value khác trong TH Option rỗng (None)
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs. Tương tự như trên, đổi syntax
  // Function trả về kdl Option, giá trị là None thay vì trả về kdl String, giá trị null
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  // isEmpty: check xem Option rỗng hay không
  println(myFirstOption.isEmpty) // false
  // get: lấy value trong Some(value). Nếu rỗng sẽ lỗi Exception
  println(myFirstOption.get)  // USAFE - DO NOT USE THIS // 4

  // map, flatMap, filter. Vì Option cx extend Iterable nên có mấy cái func này
  println(myFirstOption.map(_ * 2)) // Some(8) ~ x => x*2
  println(myFirstOption.filter(x => x > 10)) // None
  // Khác map 1 chút là giá trị trả về của hàm là Option, tức là có thể trả về None
  println(myFirstOption.flatMap(x => Option(x * 10))) // Some(40)

  // for-comprehensions
  /*
    Exercise.
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print (Some(connectionstatus.get))
  println(connectionStatus)
  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  
  forConnectionStatus.foreach(println)
}
