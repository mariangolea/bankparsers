package org.mariangolea.fintrack.company;

import java.util.Collection;

public interface CompanyInterface {
	public String getName();

	public void setName(String name);

	public Collection<CompanyIdentifierInterface> getIdentifiers();

	public void setIdentifiers(Collection<CompanyIdentifierInterface> identifiers);
}
