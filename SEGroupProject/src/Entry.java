
/**
 * Abstract class used as superclass for log objects
 *
 */
public abstract class Entry {
	
	private Gender gender;
	private Age age;
	private Income income;
	
	public Entry(Gender gender, Age age,
			Income income) {
		setGender(gender);
		setAge(age);
		setIncome(income);
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
	
}
