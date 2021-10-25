package org.mariangolea.fintrack.bank.transaction.api;

import java.io.Serializable;
import java.util.Objects;

public class BankTransactionText implements Serializable {

	private static final long serialVersionUID = 4424491335719995607L;

	private Long id;

    private String originalContent;
    
    public BankTransactionText() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankTransactionText other = (BankTransactionText) obj;
        if (!Objects.equals(this.originalContent, other.originalContent)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BankTransactionText{" + "id=" + id + ", originalContent=" + originalContent + '}';
    }

    
}
