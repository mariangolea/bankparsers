package org.mariangolea.fintrack.bank.transaction.group;

import java.math.BigDecimal;
import java.util.Collection;

import org.mariangolea.fintrack.bank.transaction.BankTransactionInterface;

/**
 * Transactions group interface. 
 */
public interface BankTransactionGroupInterface extends Comparable<BankTransactionGroupInterface>{

    public int getTransactionsNumber();

    public int getGroupsNumber();

    public String getCategoryName();

    public Collection<BankTransactionGroupInterface> getContainedGroups();

    public Collection<BankTransactionInterface> getContainedTransactions();

    public BigDecimal getTotalAmount();
}
