package jahv.java.testing.book.tdd.mockito.ch6;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Testing Mockito doReturn
 * 
 * @author jose.hernandez
 *
 */
public class MockitoDoReturn_Test {

	/**
	 * Testing the side effects of using spy and stub
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void do_return_test_side_effects() {
		final List<String> list = new ArrayList<String>();
		final List<String> spy = Mockito.spy(list);

		Mockito.when(spy.get(Mockito.anyInt())).thenReturn("value");

		spy.get(0);
	}

	/**
	 * Testing do return with no side effects
	 */
	@Test
	public void do_return_test_avoid_side_effects() {
		final List<String> list = new ArrayList<String>();
		final List<String> spy = Mockito.spy(list);

		Mockito.doReturn("value").when(spy).get(Mockito.anyInt());
		spy.get(0);
	}
}
