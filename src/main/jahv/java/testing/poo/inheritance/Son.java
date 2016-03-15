package jahv.java.testing.poo.inheritance;

/**
 * Son class
 * 
 * @author jose.hernandez
 * @since 15/03/2016
 *
 */
public class Son extends Parent {

	private String info = "Son";

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info + " invoking Parent resource: " + getResource();
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

}
