package org.mariangolea.fintrack.bank.parser.api;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mariangolea.fintrack.bank.transaction.api.BankTransaction;
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
	
	private class AbstractBankParserMock extends AbstractBankParser{

		public AbstractBankParserMock(Bank bank) {
			super(bank);
		}

		@Override
		public BankTransaction parseTransaction(List<String> toConsume) {
			return null;
		}

		@Override
		public int findNextTransactionLineIndex(List<String> toConsume) {
			return 0;
		}
	
	}
}
