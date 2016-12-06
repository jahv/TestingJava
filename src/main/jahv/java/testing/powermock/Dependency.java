package jahv.java.testing.powermock;

/**
 * Created by ahernandez on 12/6/16.
 */
public class Dependency {

    private String data;

    public Dependency() {
        data = "Is brand new";
    }

    public String sayHello(String name) {
        return  "Hello " + name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
