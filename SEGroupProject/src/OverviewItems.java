import java.util.ArrayList;

/**
 * Holds all essential calculated campaign overview information.
 *
 */
public class OverviewItems {

	private int impressions;
	private int clicks;
	private int uniques;
	private int bounces;
	private int conversions;
	private float totalCost;
	private float CTR;
	private float CPA;
	private float CPC;
	private float CPM;
	private float bounceRate;
	private ArrayList<ArrayList<Object>> impressionsOverTime;
	private ArrayList<ArrayList<Object>> clicksOverTime;
	private ArrayList<ArrayList<Object>> uniquesOverTime;
	
	private ArrayList<ArrayList<Object>> bouncesOverTime;
	private ArrayList<ArrayList<Object>> conversionsOverTime;
	private ArrayList<ArrayList<Object>> totalCostOverTime;
	private ArrayList<ArrayList<Object>> CTROverTime;
	private ArrayList<ArrayList<Object>> CPCOverTime;
	private ArrayList<ArrayList<Object>> CPAOverTime;
	private ArrayList<ArrayList<Object>> CPMOverTime;
	private ArrayList<ArrayList<Object>> bounceRateOverTime;
	
	public ArrayList<ArrayList<Object>> getImpressionsOverTime(){
		return impressionsOverTime;
	}
	
	public void setImpressionOverTime(ArrayList<ArrayList<Object>> impressionsOverTime) {
		this.impressionsOverTime = impressionsOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getClicksOverTime(){
		return clicksOverTime;
	}
	
	public void setClicksOverTime(ArrayList<ArrayList<Object>> clicksOverTime) {
		this.clicksOverTime = clicksOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getUniquesOverTime(){
		return uniquesOverTime;
	}
	
	public void setUniquesOverTime(ArrayList<ArrayList<Object>> uniquesOverTime) {
		this.uniquesOverTime = uniquesOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getBouncesOverTime(){
		return bouncesOverTime;
	}
	
	public void setBouncesOverTime(ArrayList<ArrayList<Object>> bouncesOverTime) {
		this.bouncesOverTime = bouncesOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getConversionsOverTime(){
		return conversionsOverTime;
	}
	
	public void setConversionsOverTime(ArrayList<ArrayList<Object>> conversionsOverTime) {
		this.conversionsOverTime = conversionsOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getTotalCostOverTime(){
		return totalCostOverTime;
	}
	
	public void setTotalCostOverTime(ArrayList<ArrayList<Object>> totalCostOverTime) {
		this.totalCostOverTime = totalCostOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getCTROverTime(){
		return CTROverTime;
	}
	
	public void setCTROverTime(ArrayList<ArrayList<Object>> CTROverTime) {
		this.CTROverTime = CTROverTime;
	}
	
	public ArrayList<ArrayList<Object>> getCPCOverTime(){
		return CPCOverTime;
	}
	
	public void setCPCOverTime(ArrayList<ArrayList<Object>> CPCOverTime) {
		this.CPCOverTime = CPCOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getCPAOverTime(){
		return CPAOverTime;
	}
	
	public void setCPAOverTime(ArrayList<ArrayList<Object>> CPAOverTime) {
		this.CPAOverTime = CPAOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getCPMOverTime(){
		return CPMOverTime;
	}
	
	public void setCPMOverTime(ArrayList<ArrayList<Object>> CPMOverTime) {
		this.CPMOverTime = CPMOverTime;
	}
	
	public ArrayList<ArrayList<Object>> getBounceRateOverTime(){
		return bounceRateOverTime;
	}
	
	public void setBounceRateOverTime(ArrayList<ArrayList<Object>> bounceRateOverTime) {
		this.bounceRateOverTime = bounceRateOverTime;
	}
	
	public int getImpressions() {
		return impressions;
	}
	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getUniques() {
		return uniques;
	}
	public void setUniques(int uniques) {
		this.uniques = uniques;
	}
	public int getBounces() {
		return bounces;
	}
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}
	public int getConversions() {
		return conversions;
	}
	public void setConversions(int conversions) {
		this.conversions = conversions;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public float getCTR() {
		return CTR;
	}
	public void setCTR(float cTR) {
		CTR = cTR;
	}
	public float getCPA() {
		return CPA;
	}
	public void setCPA(float cPA) {
		CPA = cPA;
	}
	public float getCPC() {
		return CPC;
	}
	public void setCPC(float cPC) {
		CPC = cPC;
	}
	public float getCPM() {
		return CPM;
	}
	public void setCPM(float cPM) {
		CPM = cPM;
	}
	public float getBounceRate() {
		return bounceRate;
	}
	public void setBounceRate(float bounceRate) {
		this.bounceRate = bounceRate;
	}
}
