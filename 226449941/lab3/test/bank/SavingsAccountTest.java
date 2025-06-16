package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {
    SavingsAccount account;

    @BeforeEach
    void setUp(){
        account = new SavingsAccount(100.0);
    }

    @Test
    void testWithdraw() {
        assertTrue(account.withdraw(10.0), "withdraw less than balance");
        assertEquals(90.0, account.getBalance(),"balance is 100-10=90");

         assertFalse(account.withdraw(-10.0), "withdraw a negative amount");

    }

    @Test
    void testPerformMonthlyMaintenance() {
        for (int i = 0; i <7; i++){
            account.withdraw(10.0);
        }
        account.performMonthlyMaintenance();
        assertEquals(16.0, account.getBalance(), "after monthly fee, balance is 16 (100-7*10-14)");
    }
}