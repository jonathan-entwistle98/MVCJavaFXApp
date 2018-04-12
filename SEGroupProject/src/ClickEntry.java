import java.util.Date;

/**
 * Stores one row of click log data.
 *
 */
public class ClickEntry extends Entry {

	private long date;
	private long id; 
	private float clickCost;
	
	/**
	 * 
	 * @param date
	 * @param id
	 * @param clickCost
	 */
	public ClickEntry (long date, long id, float clickCost,
			Gender gender, Age age, Income income) {
		super(gender, age, income);
		setDate(date);
		setId(id);
		setClickCost(clickCost);
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
}
