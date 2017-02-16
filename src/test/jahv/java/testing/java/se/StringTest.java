package jahv.java.testing.java.se;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ahernandez on 1/30/17.
 */
public class StringTest {

    @Test
    public void StringToList() {
        String dow = "NYYNYYY";

        List<String> listDow2 = new ArrayList<>();
        for(char c : dow.toCharArray()) {
            listDow2.add(String.valueOf(c));
        }

        List<String> expected = new ArrayList<>(Arrays.asList("N","Y","Y","N","Y","Y","Y"));

        List<String> listDow = new ArrayList<>(Arrays.asList(dow.split("(?!^)")));

        System.out.println(listDow);
        System.out.println(listDow2);

        Assert.assertEquals(expected, listDow);
        Assert.assertEquals(expected, listDow2);
    }
}
