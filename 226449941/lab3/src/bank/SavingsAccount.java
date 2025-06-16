package bank;

/**
 * Behavior variations for a SavingsAccount withdraw() method: Savings accounts allow for 6 penalty-free withdrawal transactions per month.
 * Savings accounts allow for an unlimited number of deposits per month.
 * If the number of withdrawals for the month is greater than 6, a transaction penalty of $14 is deducted from the account when monthly maintenance is performed
 */
public class SavingsAccount extends Account {
    private static final int LIMIT_WITHDRAWALS = 6;
    private static final double FEE = 14.0;
    public SavingsAccount(double starterAmount){
        super(starterAmount);
    }

    /**
     * Rules: If the amount specified for the withdrawal is negative, the operation fails.
     * @param amount double
     * @return true if the withdrawal is successful, false otherwise
     */
    @Override
    public boolean withdraw(double amount){
        if(amount < 0){
            return false;
        }
        return super.withdraw(amount);
    }

    /**
     * Performs monthly maintenance for the savings account.
     * If the number of withdrawals exceeds the limit, a transaction fee is deducted.
     * Resets the transaction count to zero.
     */
    @Override
    public void performMonthlyMaintenance(){
        if (this.transactionNum > LIMIT_WITHDRAWALS){
            this.balance -= FEE;
        }
        this.transactionNum = 0;
    }
}
