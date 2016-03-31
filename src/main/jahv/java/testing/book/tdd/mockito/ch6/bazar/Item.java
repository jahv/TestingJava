package jahv.java.testing.book.tdd.mockito.ch6.bazar;

/**
 * Item class
 * 
 * @author jose.hernandez
 *
 */
public class Item {

	private String barCode;
	private String name;
	private Double cost;
	private Double basePrice;

	public Item() {
	}

	public Item(String barCode, String name, Double cost, Double basePrice) {
		this.barCode = barCode;
		this.name = name;
		this.cost = cost;
		this.basePrice = basePrice;
	}

	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @param barCode the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

}
