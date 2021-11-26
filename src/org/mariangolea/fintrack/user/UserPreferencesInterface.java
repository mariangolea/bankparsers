package org.mariangolea.fintrack.user;

public interface UserPreferencesInterface {
	public Integer getTimeFrameInterval();

	public void setTimeFrameInterval(Integer timeFrameInterval);

	public String getInputFolder();

	public void setInputFolder(String inputFolder);

	public int getPageSize();

	public void setPageSize(Integer pageSize);
}
