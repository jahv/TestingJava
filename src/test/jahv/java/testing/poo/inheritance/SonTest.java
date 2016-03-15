package jahv.java.testing.poo.inheritance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for {@link Son}
 * 
 * @author jose.hernandez
 * @since 15/03/2016
 *
 */
public class SonTest {

	/**
	 * Creates an STUB to overrides parent dependency on getResource method
	 * @author jose.hernandez
	 *
	 */
	private static class FakeSon extends Son {

		/**
		 * Overrides getResource method from son which has it because of the parent
		 */
		public String getResource() {
			return "FAKE PARENT";
		};
	}

	private FakeSon fakeSon;

	@Before
	public void setUp() {
		fakeSon = new FakeSon();
	}

	/**
	 * Testing inheritance on son, overriding the parents method
	 */
	@Test
	public void test_get_info() {
		final String getInfo = fakeSon.getInfo();

		Assert.assertNotNull(getInfo);
		Assert.assertTrue(getInfo.contains("FAKE"));
	}
}
