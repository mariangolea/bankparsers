package org.mariangolea.fintrack.bank.transaction.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

/**
 * Abstract support for grouping transactions.
 */
public abstract class BankTransactionAbstractGroup implements BankTransactionGroupInterface {

    private final String categoryName;

    public BankTransactionAbstractGroup(final String categoryName) {
        Objects.requireNonNull(categoryName);
        this.categoryName = categoryName;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public int getTransactionsNumber(){
    	Collection<BankTransaction> transactions = getContainedTransactions();
        return transactions == null ? 0 : transactions.size();
    }
    
    @Override
    public int getGroupsNumber(){
    	Collection<BankTransactionGroupInterface> groups = getContainedGroups();
        return groups == null ? 0 : groups.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankTransactionAbstractGroup)) {
            return false;
        }
        BankTransactionAbstractGroup that = (BankTransactionAbstractGroup) o;
        return Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }

    @Override
    public String toString() {
    	BigDecimal amount = getTotalAmount();
    	float totalAmount = amount == null ? 0 : amount.floatValue();
        return categoryName + "\n" 
                + totalAmount + "\n" 
                + getTransactionsNumber() + " transactions" + "\n" 
                + getGroupsNumber() + " groups";
    }

    /**
     * Comparison solely for ordering based on category name.
     */
    @Override
    public int compareTo(BankTransactionGroupInterface o) {
        return categoryName.compareTo(o.getCategoryName());
    }
}
