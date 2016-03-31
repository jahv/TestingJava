package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.bazar.BiggestBazarRetail;
import jahv.java.testing.book.tdd.mockito.ch6.bazar.Inventory;
import jahv.java.testing.book.tdd.mockito.ch6.bazar.Item;
import jahv.java.testing.book.tdd.mockito.ch6.bazar.Offer;
import jahv.java.testing.book.tdd.mockito.ch6.bazar.PublicAddressSystem;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

/**
 * Testing class for {@link BiggestBazarRetail}
 * 
 * Using Mockito.verify with mocks and with real objects
 * 
 * @author jose.hernandez
 *
 */
public class BiggestBazarRetail_MockitoVerify_Test {

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
	 * Testing discount - specific item and value
	 */
	@Test
	public void test_issues_discount_specific() {
		Item soap = new Item("123", "Luxury Soap", 100.0, 50.0);
		expiredItems.add(soap);

		Mockito.when(inventory.getItemsExpireInAMonth()).thenReturn(expiredItems);
		Mockito.when(inventory.itemsUpdated()).thenReturn(expiredItems.size());

		bazar.issueDiscountForItemsExpireIn30Days(0.30);

		Mockito.verify(inventory, new Times(1)).update(soap, 70.0);
		Mockito.verify(publicAddressSystem).announce(Mockito.isA(Offer.class));
	}

	/**
	 * Testing discount - using matchers
	 */
	@Test
	public void test_issues_discount_matchers() {
		Item soap = new Item("123", "Luxury Soap", 100.0, 50.0);
		expiredItems.add(soap);

		Mockito.when(inventory.getItemsExpireInAMonth()).thenReturn(expiredItems);
		Mockito.when(inventory.itemsUpdated()).thenReturn(expiredItems.size());

		bazar.issueDiscountForItemsExpireIn30Days(0.30);

		Mockito.verify(inventory, new Times(1)).update(Mockito.isA(Item.class), Mockito.anyDouble());
		Mockito.verify(publicAddressSystem).announce(Mockito.isA(Offer.class));
	}
}
