package org.mariangolea.fintrack.bank.parser.api;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankParserUtilsTest {

	private Bank bank;
	private BankParserUtils test;
	
	@BeforeEach
	public void beforeTest() {
		bank = new MockBank(Locale.CANADA, "relevant", "label", DateFormat.getDateInstance(),
				DateFormat.getDateInstance(), NumberFormat.getInstance(Locale.CANADA), new int[] { 1, 2 }, 0);
		test = new BankParserUtils(bank);
	}
	
	@Test
	public void testConstrcutor() {
		assertNotNull(test);

		try {
			test = new BankParserUtils(null);
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testParseCompletedDate() {
		assertNull(test.parseCompletedDate(null));
		assertNull(test.parseCompletedDate(""));

		String expected = bank.completedDateFormat.format(new Date());
		String parsed = bank.completedDateFormat
				.format(test.parseCompletedDate(bank.completedDateFormat.format(new Date())));
		assertEquals(expected, parsed);
	}

	@Test
	public void testParseStartDate() {
		assertNull(test.parseStartDate(null));
		assertNull(test.parseStartDate(""));

		String expected = bank.completedDateFormat.format(new Date());
		String parsed = bank.completedDateFormat
				.format(test.parseStartDate(bank.completedDateFormat.format(new Date())));
		assertEquals(expected, parsed);
	}

	@Test
	public void testParseAmount() {
		assertEquals(BigDecimal.ZERO, test.parseAmount(null));
		assertEquals(BigDecimal.ZERO, test.parseAmount(""));
		assertEquals(BigDecimal.ZERO, test.parseAmount("notanumber"));

		String expected = bank.numberFormat.format(123);
		String parsed = bank.numberFormat.format(test.parseAmount(bank.numberFormat.format(123)));
		assertEquals(expected, parsed);
	}

	@Test
	public void testSearchTransactionsNumber() {
		BigDecimal response = test.searchTransactionsNumber(2, Arrays.asList("aloha", "label,1", "plus"));
		assertEquals(BigDecimal.ONE, response);
	}

	@Test
	public void testGetTransactionsIndex() {
		int response = test.getTransactionsIndex(Arrays.asList("aloha", "relevant", "plus"));
		assertEquals(1, response);
	}
}
