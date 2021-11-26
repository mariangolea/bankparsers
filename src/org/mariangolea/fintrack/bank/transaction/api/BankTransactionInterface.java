package org.mariangolea.fintrack.bank.transaction.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Container of parsed and raw data for any transaction. <br>
 * Instances of this class will always contain data read directly from files,
 * making no further changes to them.
 */
public interface BankTransactionInterface extends Serializable, Comparable<BankTransactionInterface>{

	public String getOriginalContent();

	public int getContentLines();

	public Date getStartDate();

	public Date getCompletedDate();

	public BigDecimal getCreditAmount();

	public BigDecimal getDebitAmount();

	public String getDescription();

	public void setStartDate(Date startDate);

	public void setCompletedDate(Date completedDate);

	public void setCreditAmount(BigDecimal creditAmount);

	public void setDebitAmount(BigDecimal debitAmount);

	public void setDescription(String description);

	public void setOriginalContent(String originalContent);

	public void setContentLines(int contentLines);
}
