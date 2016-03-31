package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.stock.Stock;
import jahv.java.testing.book.tdd.mockito.ch6.stock.StockBroker;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class MockitoArgumentCaptor_Test {

	@Test
	public void argument_captor() throws Exception {
		final StockBroker broker = Mockito.mock(StockBroker.class);
		// Creating a captor for Stock class
		ArgumentCaptor<Stock> argument = ArgumentCaptor.forClass(Stock.class);
		// calling a method on mock object
		broker.getQoute(new Stock("A", 5.00));
		// Passing argument captor to verify to collect the argument
		Mockito.verify(broker).getQoute(argument.capture());
		// confirm that "A" was passed
		Assertions.assertThat("A").isEqualTo(argument.getValue().getId());
	}
}
