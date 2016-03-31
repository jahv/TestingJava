package jahv.java.testing.book.tdd.mockito.ch6.stock;

/**
 * Stock Listener
 * 
 * @author jose.hernandez
 *
 */
public class StockListener {

	private StockBroker stockBroker;

	/**
	 * 
	 * @param stockBroker
	 */
	public StockListener(final StockBroker stockBroker) {
		this.stockBroker = stockBroker;
	}

	/**
	 * 
	 * @param stock
	 */
	public void takeAction(final Stock stock) {

		double currentPrice = stockBroker.getQoute(stock);
		if (currentPrice <= stock.getLastValue()) {
			stockBroker.buy(stock, 100);
		}
		else {
			stockBroker.sell(stock, 10);
		}

	}
}
