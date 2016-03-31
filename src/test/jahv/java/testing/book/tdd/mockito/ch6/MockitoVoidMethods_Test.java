package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.stock.Stock;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockBroker;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockBrokerImpl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * Testing void methods in mockito
 * 
 * @author jose.hernandez
 *
 */
public class MockitoVoidMethods_Test {

	/**
	 * Testing Mockito.doAnswer 
	 */
	@Test
	public void doAnswer_void_methods() {
		final StockBroker broker = Mockito.mock(StockBroker.class);
		final Stock stock = new Stock("ID", 100.0);

		Mockito.doAnswer(new Answer<Double>() {

			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				final Object[] args = invocation.getArguments();
				final Stock stock = (Stock) args[0];
				stock.setLastValue(50.0);
				return null;
			}
		}).when(broker).buy(stock, 10);

		Assertions.assertThat(stock.getLastValue()).isEqualTo(100.0);
		broker.buy(stock, 10);
		Assertions.assertThat(stock.getLastValue()).isEqualTo(50.0);

	}

	/**
	 * Testing consecutive calls for void methods
	 */
	@Test(expected = RuntimeException.class)
	public void consecutive_calls_void_methods() {
		final StockBroker broker = Mockito.mock(StockBrokerImpl.class);
		final Stock stock = new Stock("ID", 100.0);

		Mockito
				// First call
				.doNothing()
				// Second call
				.doAnswer(new Answer<Double>() {

					@Override
					public Double answer(InvocationOnMock invocation) throws Throwable {
						final Object[] args = invocation.getArguments();
						final Stock stock = (Stock) args[0];
						stock.setLastValue(50.0);
						return null;
					}
				})
				// Third call
				.doCallRealMethod()
				// Fourth call
				.doThrow(new RuntimeException()).when(broker).buy(stock, 10);

		// First call
		broker.buy(stock, 10);
		Assertions.assertThat(stock.getLastValue()).isEqualTo(100.0);
		// Second call
		broker.buy(stock, 10);
		Assertions.assertThat(stock.getLastValue()).isEqualTo(50.0);
		// Third call - see console
		broker.buy(stock, 10);
		// Fourth call - throws exception
		broker.buy(stock, 10);

	}
}
