import java.util.Date;

public class ClickLog extends Log {

	private Date date;
	private long id; 
	private float clickCost;

	//TODO javadoc
	
	public ClickLog (Date date, long id, float clickCost) {
		setDate(date);
		setId(id);
		setClickCost(clickCost);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
