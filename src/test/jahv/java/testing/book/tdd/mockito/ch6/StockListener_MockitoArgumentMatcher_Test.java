package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.stock.Stock;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockBroker;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Testing class for {@link StockListener}
 * 
 * Using Mockito.verify with own {@link BlueChipStockMatcher} matcher class
 * 
 * @author jose.hernandez
 *
 */
public class StockListener_MockitoArgumentMatcher_Test {

	private StockListener stockListener;

	@Mock
	private StockBroker stockBroker;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		stockListener = new StockListener(stockBroker);
	}

	/**
	 * Test with the {@link BlueChipStockMatcher} matcher class
	 */
	@Test
	public void test_sells_bluechip_stock() {
		Mockito.when(stockBroker.getQoute(Mockito.argThat(new BlueChipStockMatcher()))).thenReturn(1000.0);
		stockListener.takeAction(new Stock("SBI", 500.0));
		Mockito.verify(stockBroker).sell(Mockito.isA(Stock.class), Mockito.anyInt());
		Mockito.verify(stockBroker, Mockito.times(0)).buy(Mockito.isA(Stock.class), Mockito.anyInt());
	}

	/**
	 * Test not using the matcher class
	 */
	@Test
	public void test_buy_others() {
		Mockito.when(stockBroker.getQoute(Mockito.argThat(new BlueChipStockMatcher()))).thenReturn(1000.0);
		stockListener.takeAction(new Stock("OTHER", 500.0));
		Mockito.verify(stockBroker).buy(Mockito.isA(Stock.class), Mockito.anyInt());
		Mockito.verify(stockBroker, Mockito.times(0)).sell(Mockito.isA(Stock.class), Mockito.anyInt());

	}
}

/**
 * Own matcher class
 * 
 * @author jose.hernandez
 *
 */
class BlueChipStockMatcher extends ArgumentMatcher<Stock> {

	@Override
	public boolean matches(Object argument) {
		Stock myStock = (Stock) argument;
		return "SBI".equals(myStock.getId()) || "HDFC".equals(myStock.getId());
	}

}
