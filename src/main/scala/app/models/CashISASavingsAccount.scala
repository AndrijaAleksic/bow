package scala.app.models

final class CashISASavingsAccount(accountNumber: String,
                                  balance : Double,
                                  private val depositThreshold : Double = 200.00
                                 )
  extends BankAccount(accountNumber, balance) {

  /**
    * Check how much is deposited,
    * if within threshold then increase, otherwise deposit the maximum
    */
  override def deposit(amount: Double): BankAccount = {
    if (amount > depositThreshold) {
      val difference = amount - depositThreshold
      println(s"You can't deposit more than £$depositThreshold, Excess: $difference")
      new CashISASavingsAccount(accountNumber, balance + depositThreshold)
    } else {
      new CashISASavingsAccount(accountNumber, balance + amount)
    }
  }

  /**
    * Cannot withdraw from CashISA, return the same balance
    */
  override def withdraw(amount: Double): BankAccount = {
    println(s"You can't withdraw from an ISA!")
    this
  }
}

