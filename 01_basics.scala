
/*
* Functions
*/
val add = (x: Int, y: Int) => x + y
println(add(2,3))

/*
* Methods
* Methods are defined with the def keywords
* Return type is declared after the parameter list
*/
def add (x: Int, y: Int): Int = x + y
println(add(2,3))

// Method can take multiple parameter lists
def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier
println(addThenMultiply(1, 2)(3)) // 9

/*
* Objects
* Objects are single instance of their own definitions
* Singletons of their own class
*/
object IdFactory {
  private var counter = 0
  def create(): Int = {
    counter += 1
    counter
  }
}
// Access objects by refering to its name
val newId: Int = IdFactory.create()
println(newId) // 1
val newerId: Int = IdFactory.create()
println(newerId) // 2


// Main Method
// Required by the JVM
object HelloWorld {
	def main(args: Array[String]): Unit = {
		println("Hello, world!")
	}
}

// Your object can extend the App trait
// The App trait already has it's own main method
// Trait is similar to abstract class in Java
// Command-line arguments are automatically made available to you in a variable named args
// You determine the number of elements in args with args.size
object HelloYou extends App {
    if (args.size == 0)
        println("Hello, you")
    else
        println("Hello, " + args(0))
}


/*
*  Variables
*  val is immutable
*  var is mutable
*/
val p = new Person("Candy")           // preferred
val p: Person = new Person("Candy")   // unnecessarily verbose

val b: Byte = 1
val x: Int = 1
val l: Long = 1
val s: Short = 1
val d: Double = 2.0
val f: Float = 3.0
var b = BigInt(1234567890)
var b = BigDecimal(123456.789)

// Enclose strings in double-quotes and a character in single-quotes
val name: String = "Bill"
val c: Char = 'a'

// string interpolation
val firstName = "John"
val mi = 'C'
val lastName = "Doe"
println(s"Name: $firstName $mi $lastName")

// Put expressions inside braces
println(s"1+1 = ${1+1}")

// Multiline strings
val speech = """Four score and
               |seven years ago
               |our fathers ...""".stripMargin

// Misc
print("Hello without newline")
System.err.println("yikes, an error happened")

/*
*  For loops
*/

// iterating over elements in a collection
val nums = Seq(1,2,3)
for (n <- nums) println(n)

// using foreach to iterate
// foreach is used for side-effects, such as printing information
// foreach returns the type Unit... whcih means it returns nothing...
nums.foreach(println)
nums.filter(_ < 4).foreach(println)

// Using for and foreach with maps
val ratings = Map (
  "Rise Of Starwalker" -> 1.0,
  "The Last Jedi" -> 5.0,
  "The Force Awakens" -> 3.0
)

for ((k,v) <- ratings) println(s"The ratings for $k is $v.")

ratings.foreach{
  case(name,rating) => println(s"The ratings for $name is $rating.")
}

/*
*  For expression
*  While a for-loop is used for side effects (such as printing output), 
*  a for-expression is used to create new collections from existing collections.
*/

// Example 1: Double each value in an array
val nums = Seq(1,2,3)
val doublednums = for (n <- nums) yield n*2
print(s"$doublednums")

// Example 2: Capitalize a list of string
val names = List("_adam", "_david", "_frank")
val names: List[String] = List("Joel", "Chris", "Ed") // Manual
val capNames = for (name <- names) yield name.drop(1).capitalize

val capNames = for (name <- names) yield {
    val nameWithoutUnderscore = name.drop(1)
    val capName = nameWithoutUnderscore.capitalize
    capName
}

/*
*  Match expression
*  _ is the catch all default case
*/

val monthName = i match {
    case 1  => "January"
    case 2  => "February"
    case 3  => "March"
    case 4  => "April"
    case 5  => "May"
    case 6  => "June"
    case 7  => "July"
    case 8  => "August"
    case 9  => "September"
    case 10 => "October"
    case 11 => "November"
    case 12 => "December"
    case _  => "Invalid month"
}

// Using match expression as the body of a method
// Example 1
def convertBooleanToStringMessage(bool: Boolean): String = bool match {
    case true => "you said true"
    case false => "you said false"
}

val result = convertBooleanToStringMessage(true)
println(result)

// Example 2
// Any is the root of all Scala classes, like object in Java
def isTrue(a: Any) = a match {
    case 0 | "" => false
    case _ => true
}
isTrue(1.1F)

// Example 3
// Using if expressions in case statements
count match {
    case 1 => println("one, a lonely number")
    case x if (x == 2 || x == 3) => println("two's company, three's a crowd")
    case x if (x > 3) => println("4+, that's a party")
    case _ => println("i'm guessing your number is zero or less")
}

i match {
  case a if 0 to 9 contains a => println("0-9 range: " + a)
  case b if 10 to 19 contains b => println("10-19 range: " + b)
  case c if 20 to 29 contains c => println("20-29 range: " + c)
  case _ => println("Hmmm...")
}

/*
*  Try/Catch/Finally expression
*  Scala uses case statements to match the different possible exceptions
*/
var text = ""
try {
    text = openAndReadAFile(filename)
} catch {
    case e: FileNotFoundException => println("Couldn't find that file.")
    case e: IOException => println("Had an IOException trying to read that file")
    case _: Throwable => println("Got some other kind of Throwable exception")
}

/*
*  Scala Classes
*  Functional or OOP - you decide....
*/

// Define a class constructor
// Return type of the method greet is Unit, similar to void in Java
class Greeter(prefix: String, suffix: String) {
  def greet(name: String): Unit =
    println(prefix + name + suffix)
}

// Create an instance of the class
val greeter = new Greeter("Hello, ", "!")
greeter.greet("Scala developer") // Hello, Scala developer!

// Example
class Person(var firstName: String, var lastName: String) {

    println("the constructor begins")

    // 'public' access by default
    var age = 0

    // some class fields
    private val HOME = System.getProperty("user.home")

    // some methods
    override def toString(): String = s"$firstName $lastName is $age years old"

    def printHome(): Unit = println(s"HOME = $HOME")    
    def printFullName(): Unit = println(this) 

    printHome()
    printFullName()
    println("you've reached the end of the constructor")

}

val p = new Person("Kim", "Carnes")
p.age
p.age = 36
p
p.printHome
p.printFullName

// Supplying default values for constructor
class Socket(var timeout: Int = 2000, var linger: Int = 3000) {
    override def toString = s"timeout: $timeout, linger: $linger"
}

val s = new Socket(timeout=2000, linger=3000)

/*
*  Case Class
*  Case class are immutable and they are compared by value unlike classes which are compared by reference
*  Useful for pattern matching
*/
case class Point(x: Int, y: Int)

// Create an instance of case class
val point = Point(1,2)
val anotherpoint = Point(3,2)

if (point == anotherpoint) {
	println(point.toString() +" and "+ anotherpoint.toString() +" are the same.")
} else {
	println(point.toString() +" and "+ anotherpoint.toString() +" are different.")
}

/*
*  Using Traits as Interfaces
*/

// Define trait
trait Speaker {
    def speak(): String
}

trait TailWagger {
    def startTail(): Unit
    def stopTail(): Unit
}

trait Runner {
    def startRunning(): Unit
    def stopRunning(): Unit
}

// Create a class from multiple traits
// Use extends to extend the first trait
// Use with to extend subsequent traits
class Dog extends Speaker with TailWagger with Runner {

    // Speaker
    def speak(): String = "Woof!"

    // TailWagger
    def startTail(): Unit = println("tail is wagging")
    def stopTail(): Unit = println("tail is stopped")

    // Runner
    def startRunning(): Unit = println("I'm running")
    def stopRunning(): Unit = println("Stopped running")

}

val d = new Dog
d.startTail
d.stopTail

/*
*  Using Traits as Abstract Classes (mixins)
*/
trait Pet {
    def speak = println("Yo")     // concrete implementation of a speak method
    def comeToMaster(): Unit      // abstract
}

// When a class extends a trait, each defined method must be implemented
class Dog(name: String) extends Pet {
    def comeToMaster(): Unit = println("Woo-hoo, I'm coming!")
}

val d = new Dog("Zeus")
d.speak
d.comeToMaster

// A class can also override a method that’s defined in a trait
class Cat extends Pet {
    // override 'speak'
    override def speak(): Unit = println("meow")
    def comeToMaster(): Unit = println("That's not gonna happen.")
}
val c = new Cat
c.speak
c.comeToMaster

// Mix traits that have CONCRETE methods into classes on the fly
trait TailWagger {
    def startTail(): Unit = println("tail is wagging")
    def stopTail(): Unit = println("tail is stopped")
}

trait Runner {
    def startRunning(): Unit = println("I'm running")
    def stopRunning(): Unit = println("Stopped running")
}

class Dog(name: String)

// WOAH!!....you can create a Dog instance that mixes in traits that you want
val d = new Dog("Fido") with TailWagger with Runner

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

/*
*  ArrayBuffer
*  Indexed, mutable sequence
*  Buffers allow not only updates of existing elements but also element additions, insertions and removals
*/
import scala.collection.mutable.ArrayBuffer
val ints = ArrayBuffer[Int]()
val strings = ArrayBuffer[String]()

nums += 4                           // add one element
nums += 5 += 6                      // add multiple elements
nums ++= List(7, 8, 9)              // add multiple elements from another collection
nums --= Array(7, 8, 9)             // remove multiple elements using another collection

val a = ArrayBuffer(1, 2, 3)         // ArrayBuffer(1, 2, 3)
a.append(4)                          // ArrayBuffer(1, 2, 3, 4)
a.append(5, 6)                       // ArrayBuffer(1, 2, 3, 4, 5, 6)
a.appendAll(Seq(7,8))                // ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8)
a.clear                              // ArrayBuffer()

val a = ArrayBuffer(9, 10)           // ArrayBuffer(9, 10)
a.insert(0, 8)                       // ArrayBuffer(8, 9, 10)
a.insertAll(0, Vector(4, 5, 6, 7))   // ArrayBuffer(4, 5, 6, 7, 8, 9, 10)
a.prepend(3)                         // ArrayBuffer(3, 4, 5, 6, 7, 8, 9, 10)
a.prepend(1, 2)                      // ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
a.prependAll(Array(0))               // ArrayBuffer(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

val a = ArrayBuffer.range('a', 'h')  // ArrayBuffer(a, b, c, d, e, f, g)
a.remove(0)                          // ArrayBuffer(b, c, d, e, f, g)
a.remove(2, 3)                       // ArrayBuffer(b, c, g)

val a = ArrayBuffer.range('a', 'h')  // ArrayBuffer(a, b, c, d, e, f, g)
a.trimStart(2)                       // ArrayBuffer(c, d, e, f, g)
a.trimEnd(2)                         // ArrayBuffer(c, d, e)

// append, appendAll, clear, insert, insertAll, prepend, prependAll, remove, trimStart, trimEnd

/*
*  List
*  Linked list, immutable sequence
*/

// Prepend elements to a List
// : character represents the side that the sequence is on
val b = 0 +: a
val b = List(-1, 0) ++: a

/*
*  Vector
*  Indexed, immutable sequence
*/
val a = Vector(1,2,3)
// Append elements
val b = a :+ 4
val b = a ++: Vector(4, 5)

/*
*  Mutable Map
*/
import scala.collection.mutable.Map
val states = collection.mutable.Map("AK" -> "Alaska")
// Add elements to map
states += ("AR" -> "Arkansas", "AZ" -> "Arizona")
states ++= Map("CA" -> "California", "CO" -> "Colorado")
// Remove elements from map
states -= "AR"
states -= ("AL", "AZ")
states --= List("AL", "AZ")
// Update elements in map
states("AK") = "Alaska, A Really Big State"
// Retain elements by supplying a function that operates on the keys and/or values
states.retain((k,v) => k == "AK")

/*
*  Immutable Map
*/
val m = Map(
    1 -> "a", 
    2 -> "b", 
    3 -> "c",
    4 -> "d"
)
// Iterate over Map elements
for ((k,v) <- m) printf("key: %s, value: %s\n", k, v)

// Get the keys from a Map
val keys = m.keys

// Get the values from a Map
val values = m.values

// Test if a Map contains a value
val contains3 = m.contains(3)

// Transform Map values
val ucMap = m.transform((k,v) => v.toUpperCase)

// Filter a Map by its keys
val twoAndThree = m.view.filterKeys(Set(2,3)).toMap

// Take the first two elements from a Map
val firstTwoElements = m.take(2)

// https://docs.scala-lang.org/overviews/collections-2.13/maps.html

/*
*  Set
*/
val set = scala.collection.mutable.Set[Int]()
// add elements to set
set += 1
set += 2 += 3
set ++= Vector(4, 5)
// add method returns true if element is successfully added
scala> set.add(6)
res4: Boolean = true
// remove elements from a set
set -= 1
set -= (2, 3)
set --= Array(4,5)
set.clear()
set.remove(2)

// SortedSet, LinkedHashSet
// https://docs.scala-lang.org/overviews/collections-2.13/sets.html

/*
*  Anonymous Functions
*/

// Use to apply an operation to a collection
val ints = List.range(1, 10)
val doubledInts = ints.map(_ * 2)
// same as...
val doubledInts = for (i <- ints) yield i * 2

// Can also use with filter
val x = ints.filter(_ % 2 == 0)

// Applying a function to map
def double(i: Int): Int = i * 2   //a method that doubles an Int
val doubledInts = ints.map(double)

// Applying a function to filter
// Filter expects boolean return value
def lessThanFive(i: Int): Boolean = (i < 5)
val y = ints.filter(lessThanFive)

/*
*  Collection methods comes with default sequence methods
*  Applicable to Array, ArrayBuffer, List, Vector, List
*/

val nums = (1 to 10).toList
val names = List("joel", "ed", "chris", "maurice")

// take/drop allows you to take/drop elements out of a list to create a new list
names.take(2)
names.takeWhile(_.length < 5)
names.drop(2)
names.dropWhile(_ != "chris")

// reduce takes a function (or anonymous function) and applies it to successive elements in the list

// Example: Reduces the list a into a single value (sum of the integers in the list)
def add(x: Int, y: Int): Int = {
    val theSum = x + y
    println(s"received $x and $y, their sum is $theSum")
    theSum
}
val a = List(1,2,3,4)

scala> a.reduce(add)
received 1 and 2, their sum is 3
received 3 and 3, their sum is 6
received 6 and 4, their sum is 10
res0: Int = 10

// Below does the same!
scala> a.reduce(_ + _)
res0: Int = 10

/*
*  Tuples
*  Tuples allow you to store heterogeneous (different) items in the same container
*  Scala tuples can contain between two and 22 items
*/
class Person(var name: String)
val t = (3, "Three", new Person("Jason"))

// Access tuple elements
scala> t._1
res1: Int = 3

scala> t._2
res2: java.lang.String = Three

scala> t._3
res3: Person = Person(David)

// Assign tuple elements to variables
val(x, y, z) = (3, "Three", new Person("David"))

// Returning a tuple from a method
 def getStockInfo = {
    // other code here ...
    ("NFLX", 100.00, 101.00)  // this is a Tuple3
}
val (symbol, currentPrice, bidPrice) = getStockInfo

/*
*  SCALA BUILD TOOL (SBT)
*  https://docs.scala-lang.org/overviews/scala-book/scala-build-tool-sbt.html
*
*  TDD vs BDD - are you testing the behavior of the application or the actual implementation?
*  BDD - What happens to the program under a certain condition
*  http://www.scalatest.org/getting_started_with_fun_suite
*
*  Refer to scala_project for examples
*/

/*
*  Functional Programming
*  
*  Pure function
*  1. Output only depends on its input variables
*  2. Does not produce any side effects
*  
*  Any method that returns Unit is going to be an impure function 
*  (e.g. foreach, datetime related functions - because they depend on hidden inputs)
*/

// Functional programming allows you to pass functions as parameters to other functions
def toUpper(s: String): String = s.toUpperCase
List("foo", "bar").map(toUpper)

def double(i: Int): Int = i * 2
val x = ints.map(double)

/*
*  Companion Objects
*  An object that’s declared in the same file as a class, and has the same name as the class
*  A companion object and its class can access each other’s private members (fields and methods)
*/

// printFilename method will work because it can access the HiddenFilename field in its companion object
class SomeClass {
    def printFilename() = {
        println(SomeClass.HiddenFilename)
    }
}

object SomeClass {
    private val HiddenFilename = "/tmp/foo.bar"
}

// Creating new instances without the new keyword
// The apply method in the companion object acts as a Factory Method
class Person {
    var name = ""
}

object Person {
    def apply(name: String): Person = {
        var p = new Person
        p.name = name
        p
    }
}

val zenMasters = List(
    Person("Nansen"),
    Person("Joshu")
)

val p = Person("Fred Flinstone")

// You can create multiple apply methods in a companion object to provide multiple constructors
class Person {
    var name: Option[String] = None
    var age: Option[Int] = None
    override def toString = s"$name, $age"
}

object Person {

    // a one-arg constructor
    def apply(name: Option[String]): Person = {
        var p = new Person
        p.name = name
        p
    }

    // a two-arg constructor
    def apply(name: Option[String], age: Option[Int]): Person = {
        var p = new Person
        p.name = name
        p.age = age
        p
    }

}

val p1 = Person(Some("Fred"))
val p2 = Person(None)

val p3 = Person(Some("Wilma"), Some(33))
val p4 = Person(Some("Wilma"), None)

// apply method in a companion object lets you construct new object instances
// adding an unapply lets you de-construct object instances
// when you put an unapply method in a companion object, it’s said that you’ve created an extractor method
// because you’ve created a way to extract the fields out of the object.
class Person(var name: String, var age: Int)

object Person {
    def unapply(p: Person): String = s"${p.name}, ${p.age}"
}
val p = new Person("Lori", 29)
val result = Person.unapply(p)

// unapply can return different types
class Person(var name: String, var age: Int)

object Person {
    def unapply(p: Person): Tuple2[String, Int] = (p.name, p.age)
}

scala> val (name, age) = Person.unapply(p)
name: String = Lori
age: Int = 29

// Notes
// 1. Unapply enable a convenient form of pattern-matching in match expressions
// 2. You get apply and unapply methods for free when you create your classes as case classes










































































