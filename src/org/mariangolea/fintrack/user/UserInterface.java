package org.mariangolea.fintrack.user;

public interface UserInterface {
	public String getName();

	public void setName(String name);

	public String getPassword();

	public void setPassword(String password);

	public UserPreferencesInterface getPreferences();

	public void setPreferences(UserPreferencesInterface preferences);
}
