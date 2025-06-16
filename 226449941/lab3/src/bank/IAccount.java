package bank;

/**
 * This interface represents a bank account. It is the super-type for
 * any other type of traditional financial account a bank might offer
 */

public interface IAccount {
    void deposit(double amount);
    boolean withdraw(double amount);
    double getBalance();
    void performMonthlyMaintenance();
}