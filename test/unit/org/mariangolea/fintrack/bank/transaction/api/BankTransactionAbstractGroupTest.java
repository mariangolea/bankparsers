package org.mariangolea.fintrack.bank.transaction.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class BankTransactionAbstractGroupTest {

	@Mock
	BankTransactionAbstractGroup group;

	@Test
	public void testConstructor() {
		BankTransactionAbstractGroupMock test;

		try {
			test = new BankTransactionAbstractGroupMock(null, null);
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}

		test = new BankTransactionAbstractGroupMock("category", null);
		assertEquals("category", test.getCategoryName());
		assertEquals(0, test.getTransactionsNumber());
		assertEquals(0, test.getGroupsNumber());
	}

	@Test
	void testEquals() {
		BankTransactionAbstractGroupMock first = new BankTransactionAbstractGroupMock("one", null);

		assertEquals(first, first);
		assertNotEquals(first, null);
		assertNotEquals(first, "Hey");

		BankTransactionAbstractGroupMock second = new BankTransactionAbstractGroupMock("one", null);
		assertEquals(first, second);

		first = new BankTransactionAbstractGroupMock("two", null);
		assertNotEquals(first, second);
	}

	@Test
	void testHash() {
		BankTransactionAbstractGroupMock first = new BankTransactionAbstractGroupMock("one", null);
		int hashCode = first.hashCode();
		assertEquals(hashCode, new BankTransactionAbstractGroupMock("one", null).hashCode());
	}

	@Test
	void testCompare() {
		BankTransactionAbstractGroupMock first = new BankTransactionAbstractGroupMock("one", null);
		BankTransactionAbstractGroupMock second = new BankTransactionAbstractGroupMock("one", null);
		assertEquals(0, first.compareTo(second));

		second = new BankTransactionAbstractGroupMock("two", null);
		assertTrue(first.compareTo(second) < 0);

		first = new BankTransactionAbstractGroupMock("zzz", null);
		assertTrue(first.compareTo(second) > 0);
	}
	
	@Test
	public void testToString() {
		BankTransactionAbstractGroupMock test = new BankTransactionAbstractGroupMock("one", null);
		String ret = test.toString();
		assertFalse(ret.contains("null"));
		assertTrue(ret.contains("one"));
		assertTrue(ret.contains("0"));
		
		test = new BankTransactionAbstractGroupMock("one", BigDecimal.TEN);
		assertTrue(test.toString().contains(BigDecimal.TEN.toString()));
	}

	private class BankTransactionAbstractGroupMock extends BankTransactionAbstractGroup {
		BigDecimal amount;

		public BankTransactionAbstractGroupMock(String categoryName, BigDecimal totalAmount) {
			super(categoryName);
			this.amount = totalAmount;
		}

		@Override
		public Collection<BankTransactionGroupInterface> getContainedGroups() {
			return null;
		}

		@Override
		public Collection<BankTransactionInterface> getContainedTransactions() {
			return null;
		}

		@Override
		public BigDecimal getTotalAmount() {
			return amount;
		}

	}

}
