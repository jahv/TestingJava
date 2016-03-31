package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.stock.Stock;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Testing class for {@link Stock}
 * 
 * Using Mockito spy
 */
public class Stock_MockitoSpy_Test {

	@Test
	public void spyTest() {
		final Stock realStock = new Stock("ID", 100.0);
		final Stock spy = Mockito.spy(realStock);

		// Since spy executes original methods, must to be equals
		Assertions.assertThat("ID").isEqualTo(spy.getId());
		Assertions.assertThat(100.0).isEqualTo(spy.getLastValue());

		spy.setLastValue(30.0);
		Assertions.assertThat(30.0).isEqualTo(spy.getLastValue());

		// Stubbing the spy
		Mockito.when(spy.getLastValue()).thenReturn(50.0);
		spy.setLastValue(20.0);
		Assertions.assertThat(20.0).isNotEqualTo(spy.getLastValue());
		Assertions.assertThat(50.0).isEqualTo(spy.getLastValue());

		// We haven´t stubbed the setId mehtod
		spy.setId("NEW ID");
		Assertions.assertThat("NEW ID").isEqualTo(spy.getId());

		Mockito.doNothing().when(spy).setId(Mockito.anyString());
		spy.setId("NEW ID 2");
		Assertions.assertThat("NEW ID 2").isNotEqualTo(spy.getId());
	}
}
