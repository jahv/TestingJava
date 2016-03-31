package jahv.java.testing.book.tdd.mockito.ch6.bazar;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventory class
 * 
 * @author jose.hernandez
 *
 */
public class Inventory {

	/**
	 * Get item which expire in a month
	 * 
	 * @return
	 */
	public List<Item> getItemsExpireInAMonth() {
		return new ArrayList<Item>();
	}

	/**
	 * Update price
	 * 
	 * @param anItem
	 * @param newPrice
	 */
	public void update(Item anItem, double newPrice) {
	}

	/**
	 * Return numbers updated
	 * 
	 * @return
	 */
	public int itemsUpdated() {
		return 0;
	}

}
