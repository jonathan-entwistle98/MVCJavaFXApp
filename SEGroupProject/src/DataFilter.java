import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataFilter {

	private Metric metric;
	private Predicate<ImpressionEntry> imprP;
	private Predicate<Entry> clickP;
	
	public DataFilter (Metric m, List<Gender> gender, List<Age> age, List<Income> income, List<Context> context) {
		metric = m;
		imprP = Objects::nonNull;
		clickP = Objects::nonNull;
		addGender(gender);
		addAge(age);
		addIncome(income);
		addContext(context);
	}
	
	public Metric getMetric() {
		return metric;
	}
	
	public ArrayList<ImpressionEntry> filterImpressions(ArrayList<ImpressionEntry> rawImpressions) {
		return (ArrayList<ImpressionEntry>) rawImpressions.stream().filter(imprP).collect(Collectors.toList());
	}
	public ArrayList<ClickEntry> filterClicks(ArrayList<ClickEntry> rawClicks) {
		return (ArrayList<ClickEntry>) rawClicks.stream().filter(clickP).collect(Collectors.toList());
	}
	
	private void addGender(List<Gender> gender) {
		if(gender != null && !gender.isEmpty()) {
			Predicate<Entry> p = e -> false;
			for(Gender x : gender) {
				p = p.or(e -> e.getGender() == x);
			}
			imprP = imprP.and(p);
			clickP = clickP.and(p);
		}
	}
	private void addAge(List<Age> age) {
		if(age != null && !age.isEmpty()) {
			Predicate<Entry> p = e -> false;
			for(Age x : age) {
				p = p.or(e -> e.getAge() == x);
			}
			imprP = imprP.and(p);
			clickP = clickP.and(p);
		}
	}
	private void addIncome(List<Income> income) {
		if(income != null && !income.isEmpty()) {
			Predicate<Entry> p = e -> false;
			for(Income x : income) {
				p = p.or(e -> e.getIncome() == x);
			}
			imprP = imprP.and(p);
			clickP = clickP.and(p);
		}
	}
	private void addContext(List<Context> context) {
		if(context != null && !context.isEmpty()) {
			Predicate<ImpressionEntry> p = e -> false;
			for(Context x : context) {
				p = p.or(e -> e.getContext() == x);
			}
			imprP = imprP.and(p);
		}
	}
}
