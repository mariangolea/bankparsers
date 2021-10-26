package org.mariangolea.fintrack.bank.transaction.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Container of parsed and raw data for any transaction. <br>
 * Instances of this class will always contain data read directly from files,
 * making no further changes to them.
 */
public class BankTransaction implements Serializable, Comparable<BankTransaction> {

	private static final long serialVersionUID = 3257983424274311133L;

	public static final String LINE_DELIMITER = "\n";

	private Long id;

	private Date startDate;

	private Date completedDate;

	private BigDecimal creditAmount;

	private BigDecimal debitAmount;

	private String description;

	private BankTransactionText originalContent;

	private int contentLines = 0;

	public static final BigDecimal DEFAULT_AMOUNT = BigDecimal.ZERO;
	public static final int SHORT_DESCRIPTION_SIZE = 20;

	public BankTransaction() {
		this(null, null, DEFAULT_AMOUNT, DEFAULT_AMOUNT, null);
	}

	public BankTransaction(final Date startDate, final Date completedDate, BigDecimal creditAmount,
			BigDecimal debitAmount, final String description, final BankTransactionText originalContent) {
		this(startDate, completedDate, creditAmount, debitAmount, description);
		this.originalContent = originalContent;
	}

	public BankTransaction(final Date startDate, final Date completedDate, BigDecimal creditAmount,
			BigDecimal debitAmount, final String description, final Collection<String> fileContent) {
		this(startDate, completedDate, creditAmount, debitAmount, description);
		StringBuilder content = new StringBuilder();
		for (String line : fileContent) {
			content.append(line).append(LINE_DELIMITER);
			contentLines++;
		}
		BankTransactionText text = new BankTransactionText();
		text.setOriginalContent(content.substring(0, content.length() - LINE_DELIMITER.length()));
		this.originalContent = text;
	}

	private BankTransaction(final Date startDate, final Date completedDate, BigDecimal creditAmount,
			BigDecimal debitAmount, final String description) {
		super();

		this.startDate = startDate;
		this.completedDate = completedDate;
		this.creditAmount = creditAmount == null ? DEFAULT_AMOUNT : creditAmount.abs();
		this.debitAmount = debitAmount == null ? DEFAULT_AMOUNT : debitAmount.abs();
		this.description = description;

	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BankTransaction that = (BankTransaction) o;
		return Objects.equals(that.creditAmount, creditAmount) && Objects.equals(that.debitAmount, debitAmount)
				&& Objects.equals(startDate, that.startDate) && Objects.equals(completedDate, that.completedDate)
				&& Objects.equals(description, that.description);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(startDate, completedDate, creditAmount, debitAmount, description);
	}

	/**
	 * Support only for ordering purposes: by date, and then by description.
	 */
	@Override
	public final int compareTo(final BankTransaction o) {
		int result = 0;
		if (completedDate == null) {
			result = o.completedDate == null ? 0 : -1;
		} else {
			result = o.completedDate == null ? 1 : completedDate.compareTo(o.completedDate);
		}

		if (result == 0) {
			if (description == null) {
				result = o.description == null ? 0 : -1;
			} else {
				result = o.description == null ? 1 : description.compareTo(o.description);
			}
		}

		return result;
	}

	@Override
	public final String toString() {
		BigDecimal amount = creditAmount == DEFAULT_AMOUNT ? debitAmount : creditAmount;
		String descSubString = "";
		if (description != null) {
			descSubString = description.length() > SHORT_DESCRIPTION_SIZE
					? description.trim().substring(0, SHORT_DESCRIPTION_SIZE)
					: description;
		}
		return descSubString + LINE_DELIMITER + amount.toString();
	}

	public final BankTransactionText getOriginalContent() {
		return originalContent;
	}

	public final int getContentLines() {
		return contentLines;
	}

	public final Date getStartDate() {
		return startDate;
	}

	public final Date getCompletedDate() {
		return completedDate;
	}

	public final BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public final BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public final String getDescription() {
		return description;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public final void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public final void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public final void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final void setOriginalContent(BankTransactionText originalContent) {
		this.originalContent = originalContent;
	}

	public final void setContentLines(int contentLines) {
		this.contentLines = contentLines;
	}

}
