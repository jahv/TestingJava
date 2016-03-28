package jahv.java.testing.book.tdd.mockito.ch6;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Testing class for {@link BiggestBazarRetail}
 * 
 * @author jose.hernandez
 *
 */
public class BiggestBazarRetailTest {

	private BiggestBazarRetail bazar;
	private List<Item> expiredItems = new ArrayList<Item>();

	@Mock
	private Inventory inventory;

	@Mock
	private PublicAddressSystem publicAddressSystem;

	/**
	 * Initialize mocks and pass mocks to {@link BiggestBazarRetail} class
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		bazar = new BiggestBazarRetail(inventory, publicAddressSystem);
	}

	/**
	 * Assert mock are not null
	 */
	@Test
	public void test_annotation_create_mocks() {
		Assertions.assertThat(inventory).isNotNull();
		Assertions.assertThat(publicAddressSystem).isNotNull();
	}

	/**
	 * Testing discount
	 */
	@Test
	public void test_issues_discount() {
		Item soap = new Item("123", "Luxury Soap", 100.0, 50.0);
		expiredItems.add(soap);

		Mockito.when(inventory.getItemsExpireInAMonth()).thenReturn(expiredItems);
		Mockito.when(inventory.itemsUpdated()).thenReturn(expiredItems.size());

		bazar.issueDiscountForItemsExpireIn30Days(0.30);

		Mockito.verify(inventory).update(Matchers.any(Item.class), Matchers.anyDouble());
		Mockito.verify(publicAddressSystem).announce(Mockito.isA(Offer.class));
	}
}
