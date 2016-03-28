package jahv.java.testing.book.tdd.mockito.ch6;

/**
 * StockBroker interface
 * 
 * @author jose.hernandez
 *
 */
public interface StockBroker {

	double getQoute(Stock stock);

	void buy(Stock stock, int i);

	void sell(Stock stock, int i);

}
