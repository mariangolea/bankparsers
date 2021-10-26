package org.mariangolea.fintrack.bank.parser.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mariangolea.fintrack.bank.transaction.api.BankTransaction;

/**
 * Abstract transactions parser based on a given bank object.
 */
public abstract class AbstractBankParser {

	public static final Locale ROMANIAN_LOCALE = new Locale.Builder().setLanguage("ro").setRegion("RO")
			.setLanguageTag("ro-RO").build();

	public static final String UNRECOGNIZED_TRANSACTION = "Unrecognized Transaction";

	public final Bank bank;
	private BankParserUtils utils;
	
	/**
	 * Construct a bank parser based on an actual bank description object.
	 * 
	 * @param bank
	 */
	public AbstractBankParser(final Bank bank) {
		this.bank = Objects.requireNonNull(bank);
		utils = new BankParserUtils(bank);;
	}

	/**
	 * Parse a transaction object from a list of strings representing the
	 * transaction description.
	 * 
	 * @param toConsume list of strings describing full details of the transaction
	 * @return constructed transaction object
	 */
	public abstract BankTransaction parseTransaction(List<String> toConsume);

	/**
	 * Look ahead for the index of next transaction. <br>
	 * Usually, a file, be it csv or pdf, contains irellevant text in between
	 * transactions.
	 * 
	 * @param toConsume remaining list of strings in a file
	 * @return index of the next transaction
	 */
	public abstract int findNextTransactionLineIndex(List<String> toConsume);

	/**
	 * Parse all transactions from the given file.
	 * @param file file
	 * @return transactions response object
	 */
	public ParseResponse parseTransactions(final File file) {
        ParseResponse fileResponse = null;
        if (file == null) {
            return fileResponse;
        }
        List<String> lines = loadFile(file);

        fileResponse = parseResponse(lines, file);

        return fileResponse;
    }
	
	protected List<String> loadFile(final File file) {
        final List<String> response = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempLine;
            while ((tempLine = reader.readLine()) != null) {
                response.add(tempLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(AbstractBankParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }
	
	protected ParseResponse parseResponse(List<String> content, File file) {
		List<BankTransaction> result = new ArrayList<>();

		List<String> toConsume = new ArrayList<>(content);
		int transactionsIndex = utils.getTransactionsIndex(toConsume);
		if (transactionsIndex < 0) {
			return null;
		}

		// find out transactions number if possible.
		final BigDecimal expectedTransactions = utils.searchTransactionsNumber(transactionsIndex, toConsume);

		// remove header lines up to first transaction
		toConsume = toConsume.subList(transactionsIndex + 1, toConsume.size());

		List<String> unrecognizedStrings = new ArrayList<>();
		BankTransaction transaction;
		int consumedLines;
		int foundTransactionsNumber = 0;
		while (!toConsume.isEmpty()) {
			int nextTransactionIndex = findNextTransactionLineIndex(toConsume);
			List<String> transactionLines = toConsume;
			if (nextTransactionIndex > 0) {
				transactionLines = toConsume.subList(0, nextTransactionIndex);
			}
			transaction = parseTransaction(transactionLines);
			if (transaction == null) {
				for (String transactionString : transactionLines) {
					if (transactionString != null && !transactionString.isEmpty()) {
						unrecognizedStrings.addAll(transactionLines);
					}
				}
				consumedLines = transactionLines.size();
			} else {
				foundTransactionsNumber++;
				result.add(transaction);
				consumedLines = transaction.getContentLines();
			}
			toConsume = toConsume.subList(consumedLines, toConsume.size());
		}

		return new ParseResponse(this, expectedTransactions.intValue(), foundTransactionsNumber, file, result,
				unrecognizedStrings);
	}
	
}
