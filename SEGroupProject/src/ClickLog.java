
public class ClickLog extends Log {

	private DateC date;
	private long id; 
	private float clickCost;

	//TODO javadoc
	
	public ClickLog (DateC date, long id, float clickCost) {
		setDate(date);
		setId(id);
		setClickCost(clickCost);
	}

	public DateC getDate() {
		return date;
	}

	public void setDate(DateC date) {
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
