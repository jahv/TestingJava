package jahv.java.testing.java.se;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Created by ahernandez on 1/10/17.
 */
public class Assignments {

    @Test
    public void test() {


        Map<String, Integer> map = new HashMap<>();
        map.put("xValue", 1);
        Integer x;

        if((x = map.get("xValue")) == null ) {
            x=100;
            System.out.println("Was null");
        }

        System.out.println(x);
    }
}
