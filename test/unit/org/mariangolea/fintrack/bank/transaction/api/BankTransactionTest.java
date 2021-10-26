package org.mariangolea.fintrack.bank.transaction.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;

class BankTransactionTest {

	@Test
	void testEmptyConstructor() {
		BankTransaction empty = new BankTransaction();
		testEmptyConstructorFields(empty, 0);

		assertNull(empty.getOriginalContent());
	}

	@Test
	void testSetters() {
		BankTransaction empty = new BankTransaction();
		Date date = new Date();
		empty.setCompletedDate(date);
		assertEquals(date, empty.getCompletedDate());

		date = new Date();
		empty.setStartDate(date);
		assertEquals(date, empty.getStartDate());

		BigDecimal amount = BigDecimal.ONE;
		empty.setCreditAmount(amount);
		assertEquals(amount, empty.getCreditAmount());

		empty.setDebitAmount(amount);
		assertEquals(amount, empty.getDebitAmount());

		empty.setDescription("Aloha");
		assertEquals("Aloha", empty.getDescription());

		empty.setContentLines(3);
		assertEquals(3, empty.getContentLines());
		
		Long test = 12L;
		empty.setId(test);
		assertEquals(test, empty.getId());

		BankTransactionText text = new BankTransactionText();
		empty.setOriginalContent(text);
		assertEquals(text, empty.getOriginalContent());
	}

	@Test
	void testConstructorOriginalContent() {
		BankTransactionText text = new BankTransactionText("Aloha");
		BankTransaction empty = new BankTransaction(null, null, null, null, null, text);
		testEmptyConstructorFields(empty, 0);

		assertEquals(text, empty.getOriginalContent());
	}

	@Test
	void testConstructorContentLines() {
		Collection<String> fileContent = new ArrayList<>();
		fileContent.add("One");
		fileContent.add("Two");
		String assumedFileContentTransformed = "One" + BankTransaction.LINE_DELIMITER + "Two";

		BankTransaction empty = new BankTransaction(null, null, null, null, null, fileContent);
		testEmptyConstructorFields(empty, 2);

		assertEquals(new BankTransactionText(assumedFileContentTransformed), empty.getOriginalContent());
	}

	@Test
	void testEquals() {
		BankTransaction first = new BankTransaction();
		
		assertEquals(first, first);
		assertNotEquals(first, null);
		assertNotEquals(first, new BankTransactionText());
		
		BankTransaction second = new BankTransaction();
		assertEquals(first, second);
		
		first.setDescription("");
		assertNotEquals(first, second);
	}

	@Test
	void testHash() {
		BankTransaction first = new BankTransaction();
		int hashCode = first.hashCode();
		assertEquals(hashCode, new BankTransaction().hashCode());
	}

	@Test
	void testCompare() {
		BankTransaction first = new BankTransaction();
		BankTransaction second = new BankTransaction();
		
		assertEquals(0, first.compareTo(second));
		
		second.setCompletedDate(new Date());
		assertTrue(first.compareTo(second) < 0);
		
		first.setCompletedDate(Date.from(Instant.now()));
		assertTrue(first.compareTo(second) > 0);
		
		second.setCompletedDate(null);
		assertTrue(first.compareTo(second) > 0);

		first.setCompletedDate(null);
		second.setCompletedDate(null);
		
		second.setDescription("");
		assertTrue(first.compareTo(second) < 0);
		
		first.setDescription("");
		assertEquals(0, first.compareTo(second));
		
		second.setDescription(null);
		assertTrue(first.compareTo(second) > 0);
		
		second.setDescription("a");
		assertTrue(first.compareTo(second) < 0);
		
		first.setDescription("c");
		assertTrue(first.compareTo(second) > 0);
	}
	
	@Test
	public void testToString() {
		BankTransaction test = new BankTransaction();
		String expected = "" + BankTransaction.LINE_DELIMITER + BankTransaction.DEFAULT_AMOUNT.toString();
		assertEquals(expected, test.toString());
		
		test.setDebitAmount(BigDecimal.ONE);
		expected = "" + BankTransaction.LINE_DELIMITER + BigDecimal.ONE.toString();
		assertEquals(expected, test.toString());
		
		test.setCreditAmount(BigDecimal.TEN);
		expected = "" + BankTransaction.LINE_DELIMITER + BigDecimal.TEN.toString();
		assertEquals(expected, test.toString());
		
		test.setDescription("ShortOf20Characters");
		expected = "ShortOf20Characters" + BankTransaction.LINE_DELIMITER + BigDecimal.TEN.toString();
		assertEquals(expected, test.toString());
		
		test.setDescription("20CharactersPlusOne 1");
		expected = "20CharactersPlusOne " + BankTransaction.LINE_DELIMITER + BigDecimal.TEN.toString();
		assertEquals(expected, test.toString());
	}
	
	private void testEmptyConstructorFields(BankTransaction transaction, int lines) {
		assertNull(transaction.getStartDate());
		assertNull(transaction.getCompletedDate());
		assertEquals(BigDecimal.ZERO, transaction.getCreditAmount());
		assertEquals(BigDecimal.ZERO, transaction.getDebitAmount());
		assertNull(transaction.getDescription());
		assertEquals(lines, transaction.getContentLines());
		assertNull(transaction.getId());
	}

}
