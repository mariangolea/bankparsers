package org.mariangolea.fintrack.bank.parser;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.mariangolea.fintrack.bank.transaction.BankTransactionInterface;

/**
 * Encapsulation of a bank transactions parsing result.
 *
 */
public class ParseResponse {

	public final File file;
	public final boolean allContentProcessed;
	public final int expectedTransactionsNumber;
	public final int foundTransactionsNumber;
	public final Collection<BankTransactionInterface> parsedTransactions;
	public final Collection<String> unprocessedStrings;
	public final AbstractBankParser parserUsed;

	public ParseResponse(final AbstractBankParser parserUsed, int expectedTransactionsNumber,
			int foundTransactionsNumber, final File file, final List<BankTransactionInterface> transactions,
			final List<String> unprocessedStrings) {
		this.parserUsed = Objects.requireNonNull(parserUsed);
		this.file = Objects.requireNonNull(file);
		this.unprocessedStrings = Collections.unmodifiableCollection(Objects.requireNonNull(unprocessedStrings));
		parsedTransactions = Collections.unmodifiableCollection(Objects.requireNonNull(transactions));
		this.expectedTransactionsNumber = expectedTransactionsNumber;
		this.foundTransactionsNumber = foundTransactionsNumber;
		allContentProcessed = unprocessedStrings.isEmpty();
	}
}
