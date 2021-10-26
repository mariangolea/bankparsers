package org.mariangolea.fintrack.bank.transaction.api;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Transactions group interface. 
 */
public interface BankTransactionGroupInterface extends Comparable<BankTransactionGroupInterface>{

    public int getTransactionsNumber();

    public int getGroupsNumber();

    public String getCategoryName();

    public Collection<BankTransactionGroupInterface> getContainedGroups();

    public Collection<BankTransaction> getContainedTransactions();

    public BigDecimal getTotalAmount();
}
