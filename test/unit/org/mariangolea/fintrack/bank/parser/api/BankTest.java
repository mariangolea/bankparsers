package org.mariangolea.fintrack.bank.parser.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class BankTest {

	@Test
	public void testConstructor() {
		MockBank bank = new MockBank(
				Locale.CANADA, 
				"relevant", 
				"label", 
				DateFormat.getDateInstance(),
				DateFormat.getDateInstance(),
				NumberFormat.getInstance(Locale.CANADA), 
				new int[] { 1, 2 }, 
				0);
		
		assertEquals(Locale.CANADA, bank.locale);
		assertEquals("relevant", bank.relevantContentHeaderLine);
		assertEquals("label", bank.transactionsNumberLabel);
		assertEquals(DateFormat.getDateInstance(), bank.completedDateFormat);
		assertEquals(DateFormat.getDateInstance(), bank.startDateFormat);
		assertEquals(NumberFormat.getInstance(Locale.CANADA), bank.numberFormat);
		assertEquals(2, bank.mandatoryRecordIndexes.length);
		assertEquals(1, bank.mandatoryRecordIndexes[0]);
		assertEquals(2, bank.mandatoryRecordIndexes[1]);
		assertEquals(0, bank.maxRecordSize);
	}
}
