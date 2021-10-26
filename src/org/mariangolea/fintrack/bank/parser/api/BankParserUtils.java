package org.mariangolea.fintrack.bank.parser.api;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Utilities related to bank transaction parsing.
 */
public final class BankParserUtils {
	private final Map<String, Date> parsedCompletedDates = new HashMap<>();
	private final Map<String, Date> parsedStartedDates = new HashMap<>();
	private final Map<String, BigDecimal> parsedAmounts = new HashMap<>();

	private final Bank bank;

	public BankParserUtils(Bank bank) {
		this.bank = Objects.requireNonNull(bank);
	}

	/**
	 * Parse a completed date from the given string. <br>
	 * Started date and completed date may have different date formats.
	 * 
	 * @param dateString
	 * @return
	 */
	public Date parseCompletedDate(final String dateString) {
		return parseDate(dateString, parsedCompletedDates, bank.completedDateFormat);
	}

	/**
	 * Parse a started date from the given string. <br>
	 * Started date and completed date may have different date formats.
	 * 
	 * @param dateString
	 * @return
	 */
	public Date parseStartDate(final String dateString) {
		return parseDate(dateString, parsedStartedDates, bank.startDateFormat);
	}

	/**
	 * Parse a BigDecimal amount from the given string.
	 * 
	 * @param amountString string representation
	 * @return
	 */
	public BigDecimal parseAmount(final String amountString) {
		if (amountString == null || amountString.trim().isEmpty()) {
			return BigDecimal.ZERO;
		}

		final String preparedString = amountString.trim();
		BigDecimal amount = parsedAmounts.get(preparedString);
		if (amount != null) {
			return amount;
		}

		try {
			Number attempt = bank.numberFormat.parse(preparedString);
			if (null != attempt) {
				amount = new BigDecimal(attempt.toString());
			}
		} catch (ParseException ex) {
		}

		if (amount == null) {
			amount = BigDecimal.ZERO;
		}
		parsedAmounts.put(preparedString, amount);
		return amount;
	}

	Date parseDate(final String dateString, final Map<String, Date> cache, final DateFormat dateFormat) {
		if (dateString == null || dateString.isEmpty()) {
			return null;
		}

		final String prepared = dateString.trim();
		Date startedDate = cache.get(prepared);
		if (startedDate != null) {
			return startedDate;
		}
		try {
			startedDate = dateFormat.parse(prepared);
		} catch (ParseException ex) {
		}

		if (startedDate != null) {
			cache.put(prepared, startedDate);
		}
		return startedDate;
	}

	BigDecimal searchTransactionsNumber(int transactionsIndex, List<String> toConsume) {
		BigDecimal expectedTransactions = BigDecimal.ZERO;
		if (!bank.transactionsNumberLabel.isEmpty()) {
			for (int i = 0; i < transactionsIndex; i++) {
				if (toConsume.get(i).contains(bank.transactionsNumberLabel)) {
					String[] toParse = toConsume.get(i).split(",");
					if (toParse != null && toParse.length == 2) {
						String transactionsString = toParse[1];
						if (transactionsString.contains(" ")) {
							transactionsString = transactionsString.substring(0, transactionsString.indexOf(" "));
						}
						expectedTransactions = parseAmount(transactionsString);
						break;
					}
				}
			}
		}

		return expectedTransactions;
	}

	int getTransactionsIndex(final List<String> search) {
		for (int i = 0; i < search.size(); i++) {
			if (search.get(i).contains(bank.relevantContentHeaderLine)) {
				return i;
			}
		}

		return -1;
	}
}
