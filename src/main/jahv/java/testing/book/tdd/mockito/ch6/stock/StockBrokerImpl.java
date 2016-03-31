package jahv.java.testing.book.tdd.mockito.ch6.stock;

public class StockBrokerImpl implements StockBroker {

	@Override
	public double getQoute(Stock stock) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void buy(Stock stock, int i) {
		System.out.println("Real Method");
	}

	@Override
	public void sell(Stock stock, int i) {
		// TODO Auto-generated method stub

	}

}
