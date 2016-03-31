package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.stock.Stock;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockBroker;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Testing class for {@link StockListener}
 * 
 * @author jose.hernandez
 *
 */
public class StockListener_MockitoAnswer_Tests {

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
		Mockito.when(stockBroker.getQoute(Mockito.any(Stock.class))).then(new BlueChipStockAnswer());
		stockListener.takeAction(new Stock("SBI", 500.0));
		Mockito.verify(stockBroker).sell(Mockito.isA(Stock.class), Mockito.anyInt());
		Mockito.verify(stockBroker, Mockito.times(0)).buy(Mockito.isA(Stock.class), Mockito.anyInt());
	}

}

/**
 * Answer class
 * 
 * @author jose.hernandez
 *
 */
class BlueChipStockAnswer implements Answer<Double> {

	@Override
	public Double answer(InvocationOnMock invocation) throws Throwable {
		final Stock argument = (Stock) invocation.getArguments()[0];
		System.out.println(argument.getId());
		System.out.println(argument.getLastValue());
		return 1000.0;
	}

}
