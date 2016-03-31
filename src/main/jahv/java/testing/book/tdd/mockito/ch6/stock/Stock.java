package jahv.java.testing.book.tdd.mockito.ch6.stock;

/**
 * Stock class
 * 
 * @author jose.hernandez
 *
 */
public class Stock {

	private String id;
	private Double lastValue;

	public Stock(String id, Double lastValue) {
		this.id = id;
		this.lastValue = lastValue;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the lastValue
	 */
	public Double getLastValue() {
		return lastValue;
	}

	/**
	 * @param lastValue the lastValue to set
	 */
	public void setLastValue(Double lastValue) {
		this.lastValue = lastValue;
	}

}
