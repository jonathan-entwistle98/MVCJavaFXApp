
public class ServerLog extends Log {
	//TODO javadoc

	private DateC entryDate;
	private long id;
	private DateC exitDate;
	private int pagesViewed;
	private boolean converted;

	public ServerLog(DateC entryDate, long id, DateC exitDate, int pagesViewed, boolean converted) {
		setEntryDate(entryDate);
		setId(id);
		setExitDate(exitDate);
		setPagesViewed(pagesViewed);
		setConverted(converted);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public DateC getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(DateC entryDate) {
		this.entryDate = entryDate;
	}
	public DateC getExitDate() {
		return exitDate;
	}
	public void setExitDate(DateC exitDate) {
		this.exitDate = exitDate;
	}
	public int getPagesViewed() {
		return pagesViewed;
	}
	public void setPagesViewed(int pagesViewed) {
		this.pagesViewed = pagesViewed;
	}
	public boolean isConverted() {
		return converted;
	}
	public void setConverted(boolean converted) {
		this.converted = converted;
	}
	

}
