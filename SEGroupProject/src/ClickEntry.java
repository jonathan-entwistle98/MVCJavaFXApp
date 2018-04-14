import java.util.Date;

/**
 * Stores one row of click log data.
 *
 */
public class ClickEntry extends Entry {

	private long date;
	private long id; 
	private float clickCost;
	private int secondsViewed;
	private int pagesViewed;
	private boolean converted;
	
	/**
	 * 
	 * @param date
	 * @param id
	 * @param clickCost
	 */
	public ClickEntry (long date, long id, float clickCost,
			Gender gender, Age age, Income income,
			int secondsViewed, int pagesViewed, boolean converted) {
		super(gender, age, income);
		setDate(date);
		setId(id);
		setClickCost(clickCost);
		setSecondsViewed(secondsViewed);
		setPagesViewed(pagesViewed);
		setConverted(converted);
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getClickCost() {
		return clickCost;
	}

	public void setClickCost(float clickCost) {
		this.clickCost = clickCost;
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

	public int getSecondsViewed() {
		return secondsViewed;
	}

	public void setSecondsViewed(int secondsViewed) {
		this.secondsViewed = secondsViewed;
	}
}
