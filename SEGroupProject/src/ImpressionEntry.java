/**
 * Stores one row of impression log data.
 *
 */
public class ImpressionEntry extends Entry {
	
	private long date;
	private long id;
	private float impressionCost;
	private Context context;
	
	//TODO javadoc
	//Date, ID, Gender, Age, Income, Context, ImpressionCost. 
	public ImpressionEntry(long date, long id, Gender gender, Age age,
			Income income, Context context, float impressionCost) {
		super(gender, age, income);
		setContext(context);
		setDate(date);
		setId(id);
		setImpressionCost(impressionCost);
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

	public float getImpressionCost() {
		return impressionCost;
	}

	public void setImpressionCost(float impressionCost) {
		this.impressionCost = impressionCost;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
