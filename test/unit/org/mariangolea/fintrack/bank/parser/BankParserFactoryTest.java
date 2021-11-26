package org.mariangolea.fintrack.bank.parser;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mariangolea.fintrack.bank.MockBank;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BankParserFactoryTest {

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
		AbstractBankParserMock parserMock = new AbstractBankParserMock(bank);

		MockBank notMatching = new MockBank(
				Locale.CANADA, 
				"suprise", 
				"label", 
				DateFormat.getDateInstance(),
				DateFormat.getDateInstance(),
				NumberFormat.getInstance(Locale.CANADA), 
				new int[] { 1, 2 }, 
				0);
		AbstractBankParserMock parserMockTwo = new AbstractBankParserMock(notMatching);
		
		BankParserFactory response = new BankParserFactory(Arrays.asList(parserMockTwo, parserMock));
		AbstractBankParser match = response.getParser(Arrays.asList("Moama", "relevant"));
		assertEquals(parserMock, match);
		
		match = response.getParser(Arrays.asList("Moama", "Toooama"));
		assertNull(match);
	}
}
