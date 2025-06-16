package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {
    CheckingAccount account;
    @BeforeEach
    void setUp(){
        account = new CheckingAccount(100.0);
    }



    @Test
    void testPerformMonthlyMaintenance() {
        account.withdraw(10.0);
        account.performMonthlyMaintenance();
        assertEquals(85.0, account.getBalance(), "after withdraw 10, balance is 100-10-5=85");
    }
}