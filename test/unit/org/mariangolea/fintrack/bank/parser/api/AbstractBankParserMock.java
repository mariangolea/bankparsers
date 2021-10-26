package org.mariangolea.fintrack.bank.parser.api;

import java.util.List;

import org.mariangolea.fintrack.bank.transaction.api.BankTransaction;

public class AbstractBankParserMock extends AbstractBankParser{
	BankTransaction toReturn;

	public AbstractBankParserMock(Bank bank) {
		this(bank, null);
	}
	
	public AbstractBankParserMock(Bank bank, BankTransaction toReturn) {
		super(bank);
		this.toReturn = toReturn;
	}

	@Override
	public BankTransaction parseTransaction(List<String> toConsume) {
		return toReturn;
	}

	@Override
	public int findNextTransactionLineIndex(List<String> toConsume) {
		return 0;
	}

}
