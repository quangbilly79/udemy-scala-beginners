package other

import java.io.File
import scala.sys.process.*

object TestSubprocess extends App {
  val data_date_key = Seq(20230101,20230102,20230103,20230104)
  var logMessageAll = ""
  for (date <- data_date_key) {
    val logMessage = s"date key is ${date.toString}"
    logMessageAll += (logMessage + "\\n")
    // Tạo 1 ProcessBuilder từ 1 câu lệnh và execute với method "!"
    //Process(s"mkdir -p /home/quang/Documents/TestLog/${date}").!
    // Tg tự như trên, nhưng output ra 1 File (java.io.File)
    // với method #> (overwrite) hoặc #>> (append), kq trả về là 1 ProcessBuilder
    // excute với method "!"
    //(Process(s"""echo "date key is ${date.toString}"""") #>
      //new File(s"/home/quang/Documents/TestLog/${date}/$date.txt")).!
  }
  println(logMessageAll)
  //val command = Seq("bash", "-c", s"""echo '${logMessageAll.trim}' > /home/quang/Documents/TestLog/testlog.txt""")
  //command.!
  (Process(s"""echo -e "$logMessageAll" """) #>
  new File(s"/home/quang/Documents/TestLog/testlog.txt")).!
}
