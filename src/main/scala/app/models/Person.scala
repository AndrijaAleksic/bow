package scala.app.models

class Person(name : String, val age: Int, private val accounts: Seq[BankAccount] = Nil) {

  private val excludedNames : List[String] = List("adam", "daniel", "john", "michael")

  def this(name : String, age : Int) = this(
    name = name,
    age = age,
    accounts = List(new SavingsAccount("123", 0.00))
  )

  def this(name : String) = this(
    name = name,
    age = 0,
    accounts = List(new SavingsAccount("1234", 0.00))
  )

  def this(firstName : String,
           lastName : String) = this(
    name = s"$firstName $lastName",
    age = 0,
    accounts = List(new SavingsAccount("153434", 0.00))
  )

  private val years : String = if(age == 1) "year" else "years"

  def speak() : String = {
    if (excludedNames.contains(name.toLowerCase)) {
      "You don't get a hello."
    } else {
      s"Hello $name, you are $age $years old. \n You have account: $accounts"
    }
  }

  ///


}

