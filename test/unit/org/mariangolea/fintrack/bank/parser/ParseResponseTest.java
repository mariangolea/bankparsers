package org.mariangolea.fintrack.bank.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ParseResponseTest {
	@Mock
	AbstractBankParser parser;

	@Mock
	File file;

	@Test
	public void testConstructor() {
		ParseResponse response = new ParseResponse(parser, 0, 1, file, new ArrayList<>(), new ArrayList<>());

		assertEquals(parser, response.parserUsed);
		assertEquals(0, response.expectedTransactionsNumber);
		assertEquals(1, response.foundTransactionsNumber);
		assertEquals(file, response.file);
		assertTrue(response.parsedTransactions.isEmpty());
		assertTrue(response.unprocessedStrings.isEmpty());
		assertTrue(response.allContentProcessed);
		
		response = new ParseResponse(parser, 0, 1, file, new ArrayList<>(), Arrays.asList("aloha"));
		assertFalse(response.allContentProcessed);
	}
}
