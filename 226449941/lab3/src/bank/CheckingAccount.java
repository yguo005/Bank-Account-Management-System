package bank;

/**
 * If the checking balance falls below $100 at ANY time during the month (before maintenance is performed)
 * an account maintenance fee of $5 is charged when the monthly maintenance is performed.
 */
public class CheckingAccount extends Account{
    private static final double MIN_BALANCE = 100.0;
    private static final double MAINTENANCE_FEE = 5.0;
    private boolean belowMinBalance;
    public CheckingAccount(double starterAmount){
        super(starterAmount);
        this.belowMinBalance = starterAmount < MIN_BALANCE;
    }
    @Override
    public void deposit(double amount){
        super.deposit(amount);
        if (this.balance >= MIN_BALANCE){
            this.belowMinBalance = false;
        }
    }
    @Override
    public boolean withdraw(double amount){
        boolean result = super.withdraw(amount);
        if (this.balance < MIN_BALANCE){
            this.belowMinBalance = true;
        }
        return result;
    }
    @Override
    public void performMonthlyMaintenance(){
        if(this.belowMinBalance){
            this.balance -= MAINTENANCE_FEE;
        }
        this.transactionNum = 0;
    }
}
