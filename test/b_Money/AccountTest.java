package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK)); //this line threw a nullpointer exception
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("test", 5,6, new Money(160, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("test"));
		testAccount.removeTimedPayment("test");
		assertFalse(testAccount.timedPaymentExists("test"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("testTimedPayment",5,0, new Money(5,SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals(9999995, (int)testAccount.getBalance().getAmount());
		assertEquals(1000005, (int)SweBank.getBalance("Alice"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.deposit(new Money(100, SEK));
		assertEquals((int)testAccount.getBalance().getAmount(), 10000100);
		testAccount.withdraw(new Money(100, SEK));
		assertEquals((int)testAccount.getBalance().getAmount(), 10000000);
	}
	
	@Test
	public void testGetBalance() {
		assertEquals((int)testAccount.getBalance().getAmount(), 10000000);
		assertEquals(testAccount.getBalance().getCurrency(), SEK);
	}
}
