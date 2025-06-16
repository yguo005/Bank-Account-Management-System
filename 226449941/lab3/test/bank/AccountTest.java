package bank;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class AccountTest {
    //Create a concrete subclass of Account for testing purposes
    public class TestAccount extends Account {
        public TestAccount(double starterAmount) {
            super(starterAmount);
        }
        //provide a concrete implementation for the abstract method
        @Override
        public void performMonthlyMaintenance(){
        }

        @Override
        public String toString(){
            return super.toString();
        }
    }

    TestAccount account;

    @BeforeEach
    void setUp() {
        account = new TestAccount(150.0);
    }
    @Test
    void testDeposit() {
        account.deposit(50.0);
        assertEquals(200.0, account.getBalance(),"deposit 50, balance is 200");
    }

    @Test
    void testWithdraw() {
        assertTrue(account.withdraw(50.0));
        assertEquals(100.0, account.getBalance(), "withdraw 50 balance is 100");
    }

    @Test
    void testGetBalance() {
        assertEquals(150, account.getBalance(),"initial balance is starter amount");
    }


    @Test
    void testToString() {
        assertEquals("$150.00", account.toString(), "toString return balance in dollar format");
    }
}