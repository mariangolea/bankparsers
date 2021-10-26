package org.mariangolea.fintrack.bank.transaction.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BankTransactionTextTest {

	@Test
	void testEmptyConstructor() {
		BankTransactionText empty = new BankTransactionText();
		assertNull(empty.getOriginalContent());
		assertNull(empty.getId());
	}

	@Test
	void testSetters() {
		BankTransactionText empty = new BankTransactionText();
		empty.setOriginalContent("Mu");
		assertEquals("Mu", empty.getOriginalContent());

		Long test = 10L;
		empty.setId(test);
		assertEquals(test, empty.getId());
	}

	@Test
	void testConstructorOriginalContent() {
		BankTransactionText text = new BankTransactionText("Aloha");
		assertEquals("Aloha", text.getOriginalContent());
		assertNull(text.getId());
	}

	@Test
	void testEquals() {
		BankTransactionText first = new BankTransactionText();

		assertEquals(first, first);
		assertNotEquals(first, null);
		assertNotEquals(first, new BankTransaction());

		BankTransactionText second = new BankTransactionText();
		assertEquals(first, second);

		first.setId(10L);
		assertNotEquals(first, second);
	}

	@Test
	void testHash() {
		BankTransactionText first = new BankTransactionText();
		int hashCode = first.hashCode();
		assertEquals(hashCode, new BankTransactionText().hashCode());
	}

	@Test
	public void testToString() {
		BankTransactionText test = new BankTransactionText();
		assertTrue(test.toString().contains("null"));

		test.setId(10L);
		assertTrue(test.toString().contains("10"));

		test.setOriginalContent("Aloha");
		assertTrue(test.toString().contains("Aloha"));
	}

}
