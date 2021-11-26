package org.mariangolea.fintrack.bank;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MockBank extends Bank {
	public MockBank(Locale locale, String relevantContentHeaderLine, String transactionsNumberLabel,
			DateFormat startDateFormat, DateFormat completedDateFormat, NumberFormat numberFormat,
			int[] mandatoryRecordIndexes, int maxRecordSize) {
		super(locale, relevantContentHeaderLine, transactionsNumberLabel, startDateFormat, completedDateFormat,
				numberFormat, mandatoryRecordIndexes, maxRecordSize);
	}

}
