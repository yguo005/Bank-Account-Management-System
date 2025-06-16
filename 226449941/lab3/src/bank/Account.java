package bank;
public abstract class Account implements IAccount {
    protected double balance;
    protected int transactionNum;

    /**
     * Create a new account by specifying a “starter” amount of money to open it with.
     * The starter amount must be greater than or equal to one cent.
     *
     * @param starterAmount double
     *                      If the amount specified is negative OR the amount is less than one cent ($0.01), throw an IllegalArgumentException
     */

    public Account(double starterAmount) {
        if (starterAmount < 0.01) {
            throw new IllegalArgumentException("starter amount less than 1 cent");
        }
        this.balance = starterAmount;
        this.transactionNum = 0;
    }

    /**
     * Deposit into their account.
     * Do this: Create a method called deposit that takes a single parameter (of type double) that represents the amount deposited into the account.
     * If the amount specified is negative, throw an IllegalArgumentException
     *
     * @param amount double
     */
    @Override
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("deposit amount less than 0");
        }
        this.balance += amount;
    }

    /**
     * Withdraw from their account. If the amount specified is greater than the balance available, this operation fails and returns false.
     * Do this: Create a method called withdraw that reduces the account balance by the amount specified.
     * Return true if the transaction is successful, false otherwise.
     *
     * @param amount double
     * @return
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        this.transactionNum++;
        return true;
    }

    /**
     * Check their balance.
     * Do this: Create a method getBalance that returns a double (the current account balance)
     *
     * @return balance double
     */
    @Override
    public double getBalance() {
        return this.balance;
    }

    /**
     * Bank administrators can perform monthly maintenance to assess monthly fees and give a “clean slate” for the subsequent month.
     * Do this: Create a performMonthlyMaintenance method to charge any fees and then reset transaction counters to zero.
     */
    @Override
    public abstract void performMonthlyMaintenance();

    /**
     * Do this: Create a toString method that prints the account balance in dollars/cents format (e.g: $10.00).
     * String.format method for this part, or use the DecimalFormat class if you wish.
     *
     * @return string representation of the account balance
     */
    @Override
    public String toString() {
        return String.format("$%.2f", this.balance);
    }
}

