import scala.io.Source
//import scala.app.models.Person

val names = List("adam", "daniel", "david", "david")

for (name <- names) {
  println(name)
}

for (
  name <- names if name != "daniel"
) {
  println(name)
}

val filteredNames = for (
  name <- names if name != "daniel"
) yield name


val ages = List(1, 25, 34, 18)

val nested = for (
  name <- names if name != "daniel";
  age <- ages
) yield s"name: $name, age: $age"


class BankAccount(num : String) {
  override def toString: String = s"accountNumber: $num"
}

val accounts = for (num <- 1 to 10) yield new BankAccount(s"$num")

val personsWithAccounts = List(
  ("adam",
    List(
      accounts(0),
      accounts(1)
    )
  ),
  ("daniel", List(accounts(1))),
  ("michael", List(accounts(2))),
  ("chris", List(accounts(3))),
  ("connor", List(accounts(9)))
)

for (
p <- personsWithAccounts;
accounts <- p._2
) yield s"${p._1} $accounts"

/**
  * Tuples
  */
val allInfoOnAdam = ("Adam", 21, new BankAccount("12233455"))

allInfoOnAdam._3












val ints = List(
  1, 3, 5, 6, 8, 2 ,1)

/**
  * All the below do the same
  */

ints.foldLeft(0)((acc, num) => acc + num)

ints.foldLeft(0)(_+_)

def plus(l : Int, r : Int) = l + r
ints.foldLeft(0)(plus)


class Quiz(val title: String, val name : String, val score : Int) {

  override def toString: String = s"Name: $name"

}

val quizzes = List(
  new Quiz("part 1", "michael", 8),
  new Quiz("part 1", "pan", 1),
  new Quiz("part 1", "andrew", 9),
  new Quiz("part 1", "shannon", 7),
  new Quiz("part 1", "connor", 0),
  new Quiz("part 2", "michael", 6),
  new Quiz("part 2", "connor", 9)
)

val connorsResults = quizzes.filter(q => q.name == "connor")

val total = (total : Int, quiz: Quiz) => total + quiz.score


connorsResults.foldLeft(0)(total)

val double = (n : Int) => n * 2

val plusOne = (n : Int) => n + 1

val add = (acc : Int, n : Int) => acc + n

val nums = List(1, 2, 3, 6, 8, 12, 45, 68)
nums.map(double).foldLeft(0)(add)

val doublePlusOne = double andThen plusOne
nums.map(doublePlusOne)


val name : Option[String] = Some("Adam")

name.map(c => c.toUpperCase)

if(name.isDefined) {
  name.get
}












val numbers : List[Int] = List(1, 2, 3, 5, 8, 13)

def sumAndMultipleBy(list : List[Int],
                     f: Int => Int) = numbers.map(n => f(n)).sum

val timesTwo = (n : Int) => n * 2
val timesThree = (n : Int) => n * 3

val timesTwoThenTimesThree = timesTwo andThen timesThree

sumAndMultipleBy(numbers, timesTwo)
sumAndMultipleBy(numbers, timesThree)
sumAndMultipleBy(numbers, timesTwoThenTimesThree)



class Person(val name : String)

val p1 = new Person("adam")
val b1 = new BankAccount("12345")
val q1 = new Quiz("quiz", "quizzie", 0)

val t : (Person, BankAccount, Quiz) = (p1, b1, q1)

t._1
t._2
t._3



trait Pet {
  val name : String
}

trait Walking {

  def walk : String
}

trait DogWalking extends Walking {
  override def walk = "trot"
}

trait CatWalking extends Walking {
  override def walk = "swag"
}

class Dog(override val name : String) extends Pet
class Cat(override val name: String) extends Pet

val pets : List[Pet with Walking] = List(
  new Dog("lassie") with DogWalking,
  new Cat("anna") with CatWalking
)

pets.foreach(p => println(p.walk))











trait Logging { def log : Boolean }

trait LoggingToFile extends Logging {
  override def log: Boolean = {
    val file = Source.fromFile("").getLines()
    file.nonEmpty
  }
}
abstract class Bank
/** Without traits we would have to achieve this by defining pointless abstract classes to share behaviours. We overcome this using traits to mix-in behaviours
  */
//abstract class BankWithLogging extends Bank
//abstract class BankWithLoggingToFile extends BankWithLogging
class SAccount extends Bank with LoggingToFile

trait LoggingToFileEncrypted extends LoggingToFile {
  override def log = ???
}

val rb = new SAccount() with LoggingToFileEncrypted
val rh = new SAccount() with LoggingToFileEncrypted

val acc2 = new SAccount()
acc2.log
rb.log

