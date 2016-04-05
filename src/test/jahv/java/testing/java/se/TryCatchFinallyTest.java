package jahv.java.testing.java.se;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link TryCatchFinally}
 * 
 * @author jose.hernandez
 *
 */
public class TryCatchFinallyTest {

	private TryCatchFinally tryCatchFinally;

	@Before
	public void setUp() {
		tryCatchFinally = new TryCatchFinally();
	}

	@Test
	public void tryCatchFinallyTest() {
		tryCatchFinally.tryCatchFinally(1, 0, 1, 0);
		System.out.println("####");

	}
}
