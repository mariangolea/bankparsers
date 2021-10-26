package org.mariangolea.fintrack.bank.transaction.api;

import java.io.Serializable;
import java.util.Objects;

/**
 * Original bank transaction text in the file it was parsed from.
 */
public class BankTransactionText implements Serializable {

	private static final long serialVersionUID = 4424491335719995607L;

	private Long id;

    private String originalContent;
    
    public BankTransactionText() {
    	this(null);
    }

    public BankTransactionText(String originalContent) {
        this.originalContent = originalContent;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, originalContent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankTransactionText other = (BankTransactionText) obj;
		return Objects.equals(id, other.id) && Objects.equals(originalContent, other.originalContent);
	}

    @Override
    public String toString() {
        return "BankTransactionText{" + "id=" + id + ", originalContent=" + originalContent + '}';
    }

    
}
