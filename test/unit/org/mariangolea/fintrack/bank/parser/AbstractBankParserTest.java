package org.mariangolea.fintrack.bank.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.mariangolea.fintrack.bank.Bank;
import org.mariangolea.fintrack.bank.MockBank;
import org.mariangolea.fintrack.bank.transaction.BankTransactionInterface;
import org.mariangolea.fintrack.bank.transaction.BankTransactionTextInterface;

public class AbstractBankParserTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private Bank bank;
	private AbstractBankParser test;

	@BeforeEach
	public void beforeTest() {
		bank = new MockBank(Locale.CANADA, "relevant", "label", DateFormat.getDateInstance(),
				DateFormat.getDateInstance(), NumberFormat.getInstance(Locale.CANADA), new int[] { 1, 2 }, 0);
		test = new AbstractBankParserMock(bank);
	}

	@Test
	public void testConstructor() {
		assertNotNull(test.bank);
	}

	@Test
	public void testLoadFile() {
		try {
			folder.create();
			Collection<String> lines = Arrays.asList("one", "two");
			File tempFile = folder.newFile();
			writeFile(tempFile, lines);
			Collection<String> parsedLines = test.loadFile(tempFile);
			assertEquals(lines, parsedLines);
		} catch (IOException e) {
			assertTrue(false);
		}
	}

	@Test
	public void testParseTransactions() {
		try {
			folder.create();
			Collection<String> lines = Arrays.asList("one", "label,1", "relevant", "FakeTransaction");
			File tempFile = folder.newFile();
			writeFile(tempFile, lines);
			ParseResponse response = test.parseTransactions(tempFile);
			assertNotNull(response);
			assertEquals(0, response.parsedTransactions.size());

			BankTransactionInterface transaction = new BankTransaction();
			transaction.setContentLines(1);
			test = new AbstractBankParserMock(bank, transaction);
			response = test.parseTransactions(tempFile);
			assertNotNull(response);
			assertEquals(1, response.parsedTransactions.size());
		} catch (IOException e) {
			assertTrue(false);
		}
	}

	public File writeFile(File file, final Collection<String> lines) {
		try (BufferedWriter printer = new BufferedWriter(new FileWriter(file))) {
			for (String line : lines) {
				printer.write(line);
				printer.newLine();
			}
		} catch (IOException ex) {
		}

		return file;
	}

	private class BankTransaction implements BankTransactionInterface {
		private static final long serialVersionUID = 1L;
		private int contentLines = 0;

		@Override
		public int compareTo(BankTransactionInterface arg0) {
			return 0;
		}

		@Override
		public BankTransactionTextInterface getOriginalContent() {
			return null;
		}

		@Override
		public int getContentLines() {
			return contentLines;
		}

		@Override
		public Date getStartDate() {
			return null;
		}

		@Override
		public Date getCompletedDate() {
			return null;
		}

		@Override
		public BigDecimal getCreditAmount() {
			return BigDecimal.ZERO;
		}

		@Override
		public BigDecimal getDebitAmount() {
			return BigDecimal.ZERO;
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public void setStartDate(Date startDate) {

		}

		@Override
		public void setCompletedDate(Date completedDate) {
		}

		@Override
		public void setCreditAmount(BigDecimal creditAmount) {
		}

		@Override
		public void setDebitAmount(BigDecimal debitAmount) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setDescription(String description) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setOriginalContent(BankTransactionTextInterface originalContent) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setContentLines(int contentLines) {
			this.contentLines = contentLines;
		}
	}
}
