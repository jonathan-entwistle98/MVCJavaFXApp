public class Controller {
	
	private Model model;
	
	/**
	 * Creates controller with model assigned.
	 * @param model
	 */
	public Controller(Model model) {
		this.model = model;
	}
	
	/**
	 * 
	 * @return gets campaign overview from model.
	 */
	public OverviewItems getOverviewItems() {
		return model.getOverviewItems();
	}

}
