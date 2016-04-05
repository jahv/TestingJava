package jahv.java.testing.java.se;

public class TryCatchFinally {

	public void tryCatchFinally(final int divisor1, final int dividend1, final int divisor2,
			final int dividend2) {
		double division_1 = 0;
		try {
			System.out.println("Trying first division: " + divisor1 + "/" + dividend1);
			division_1 = divisor1 / dividend1;
			System.out.println("Passing first division.");
		}
		catch (final Exception e) {
			System.out.println("Error in first division: " + e.getMessage());
			division_1 = Double.POSITIVE_INFINITY;
			System.out.println("Trying second division: " + divisor2 + "/" + dividend2);
			division_1 = divisor2 / dividend2;
			System.out.println("Passing second division.");
		}
		finally {
			System.out.println("Finally: value for diivision1: " + division_1);
		}
	}
}
