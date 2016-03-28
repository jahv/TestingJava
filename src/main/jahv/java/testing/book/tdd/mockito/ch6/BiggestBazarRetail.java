package jahv.java.testing.book.tdd.mockito.ch6;

import java.util.List;

/**
 * Bazar class
 * 
 * @author jose.hernandez
 *
 */
public class BiggestBazarRetail {

	private Inventory inventory;
	private PublicAddressSystem publicAddressSystem;

	/**
	 * Constructor
	 * 
	 * @param inventory
	 * @param publicAddressSystem
	 */
	public BiggestBazarRetail(Inventory inventory,
			PublicAddressSystem publicAddressSystem) {
		this.inventory = inventory;
		this.publicAddressSystem = publicAddressSystem;
	}

	/**
	 * Apply discount for items which expire in 30 days
	 * 
	 * @param discountRate
	 * @return
	 */
	public int issueDiscountForItemsExpireIn30Days(double discountRate) {
		final List<Item> headingExpiryItems = inventory.getItemsExpireInAMonth();
		for (Item anItem : headingExpiryItems) {
			final double newPrice = anItem.getCost() - anItem.getCost() * discountRate;
			if (newPrice > anItem.getBasePrice()) {
				inventory.update(anItem, newPrice);
				publicAddressSystem.announce(new Offer(anItem, newPrice));
			}
		}
		return inventory.itemsUpdated();
	}

}
