package org.mariangolea.fintrack.bank.parser;

import java.util.List;

import org.mariangolea.fintrack.bank.Bank;
import org.mariangolea.fintrack.bank.transaction.BankTransactionInterface;

public class AbstractBankParserMock extends AbstractBankParser{
	BankTransactionInterface toReturn;

	public AbstractBankParserMock(Bank bank) {
		this(bank, null);
	}
	
	public AbstractBankParserMock(Bank bank, BankTransactionInterface toReturn) {
		super(bank);
		this.toReturn = toReturn;
	}

	@Override
	public BankTransactionInterface parseTransaction(List<String> toConsume) {
		return toReturn;
	}

	@Override
	public int findNextTransactionLineIndex(List<String> toConsume) {
		return 0;
	}

}
