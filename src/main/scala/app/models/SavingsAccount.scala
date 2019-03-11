package scala.app.models

class SavingsAccount(accountNumber: String,
                     balance : Double) extends BankAccount(accountNumber,balance) {

  override def deposit(amount: Double): BankAccount = new SavingsAccount(accountNumber, balance + amount)

  override def withdraw(amount: Double): BankAccount = new SavingsAccount(accountNumber, balance - amount)

}

