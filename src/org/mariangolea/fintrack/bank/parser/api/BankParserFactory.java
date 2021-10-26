package org.mariangolea.fintrack.bank.parser.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Abstract bank parser factory. <br>
 * Initialised with a collection of known (supported) banks, will recognise
 * needed parser per given transactions file.
 *
 */
public class BankParserFactory {
	private final Map<Bank, AbstractBankParser> parsers = new HashMap<>();
	
	/**
	 * Construct a factory instance based on a collection of supported parsers.
	 * @param knownParsers known parsers
	 */
	public BankParserFactory(final Collection<AbstractBankParser> knownParsers) {
		Objects.requireNonNull(knownParsers).forEach(parser -> {
			parsers.put(parser.bank, parser);
		});
	}

	/**
	 * Retrieve the first parser which is able to work with given content. 
	 * @param fileLines list of strings in a given transactions file.
	 * @return matching parser, if any.
	 */
	public AbstractBankParser getParser(final List<String> fileLines) {
		for (Bank bank : parsers.keySet()) {
			if (matchesBank(bank, fileLines)) {
				return parsers.get(bank);
			}
		}
		return null;
	}

	private boolean matchesBank(final Bank bank, final List<String> fileLines) {
		return Objects.requireNonNull(fileLines).stream()
				.anyMatch((line) -> (Objects.requireNonNull(line).contains(bank.relevantContentHeaderLine)));
	}
}
