import java.util.Date;

/**
 * Stores one row of server log data.
 *
 */

public class ServerLog extends Log {
	//TODO javadoc

	private Date entryDate;
	private long id;
	private Date exitDate;
	private int pagesViewed;
	private boolean converted;

	public ServerLog(Date entryDate, long id, Date exitDate, int pagesViewed, boolean converted) {
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
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
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
