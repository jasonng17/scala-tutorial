import scala.io.StdIn.readLine

object HelloInteractive extends App {
    print("Enter your first name: ")
     val first = readLine()
     print("Enter your last namle: ")
     val last = readLine()
     print(s"Your name is $first $last")
}