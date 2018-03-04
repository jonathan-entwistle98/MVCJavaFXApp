import java.util.Date;

public class ImpressionLog extends Log {
	
	private Date date;
	private long id;
	private Gender gender;
	private Age age;
	private Income income;
	private Context context;
	private float impressionCost;
	
	//TODO javadoc
	//Date, ID, Gender, Age, Income, Context, ImpressionCost. 
	public ImpressionLog(Date date, long id, Gender gender, Age age,
			Income income, Context context, float impressionCost) {
		setDate(date);
		setId(id);
		setGender(gender);
		setAge(age);
		setIncome(income);
		setContext(context);
		setImpressionCost(impressionCost);
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public float getImpressionCost() {
		return impressionCost;
	}

	public void setImpressionCost(float impressionCost) {
		this.impressionCost = impressionCost;
	}

}
