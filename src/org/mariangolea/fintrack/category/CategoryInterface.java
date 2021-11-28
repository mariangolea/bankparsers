package org.mariangolea.fintrack.category;

import java.util.Collection;

public interface CategoryInterface {

	public String getName();

	public void setName(String name);

	public void setParentCategory(CategoryInterface parent);
	
	public CategoryInterface getParent();
	
	public Collection<CategoryInterface> getChildren();
	
	public void addChildren(CategoryInterface... children);
}
