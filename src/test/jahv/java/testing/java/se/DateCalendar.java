package jahv.java.testing.java.se;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Created by ahernandez on 2/3/17.
 */
public class DateCalendar {

    @Test
    public void testGetDateInfo() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        System.out.println(day + "/" + month + "/" + year);
    }

    @Test
    public void dayOfWeek() {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        for(int i=0; i<30; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            System.out.println(calendar.getTime() + " - " + dayOfWeek);
            calendar.add(Calendar.DATE, 1);
        }

    }

    @Test
    public void consecutiveDates() {
        List<Date> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);

        //--- 1
        calendar.set(Calendar.DAY_OF_MONTH, 12);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 13);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 14);
        dates.add(calendar.getTime());

        //--- 2
        calendar.set(Calendar.DAY_OF_MONTH, 16);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 17);
        dates.add(calendar.getTime());

        //--- 3
        calendar.set(Calendar.DAY_OF_MONTH, 19);
        dates.add(calendar.getTime());

        //--- 4
        calendar.set(Calendar.DAY_OF_MONTH, 21);
        dates.add(calendar.getTime());

        //---

        calendar.set(Calendar.DAY_OF_MONTH, 23);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 24);
        dates.add(calendar.getTime());

        //--- 5
        calendar.set(Calendar.DAY_OF_MONTH, 27);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 28);
        dates.add(calendar.getTime());

        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 2);
        dates.add(calendar.getTime());

        //--- 6
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 11);
        dates.add(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 12);
        dates.add(calendar.getTime());
//

        //Begin
        List<Map<String, Date>> chunks = new ArrayList<>();
        int i = 0;
        while(i < dates.size()) {
            Map<String, Date> chunk = new HashMap<>();
            Date start = dates.get(i);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(start);

            System.out.println("Start: " + start);
            chunk.put("start", start);
            boolean consecutive = true;

            while (consecutive) {
                calendar1.add(Calendar.DATE, 1);

                if(i == (dates.size()-1)) {
                    System.out.println("Error");
                    chunk.put("end", dates.get(dates.size()-1));
                    System.out.println("End: " + dates.get(dates.size()-1));
                    //chunks.add(chunk);
                    break;
                }

                Date following = dates.get(i + 1);
                if (following.compareTo(calendar1.getTime()) == 0) {
                    i++;
                    System.out.println("Index: " + i);
                } else {
                    calendar1.add(Calendar.DATE, -1);
                    following = calendar1.getTime();
                    System.out.println("End: " + following);
                    chunk.put("end", following);
                    consecutive = false;
                }
            }
            chunks.add(chunk);
            i++;
            System.out.println("Index: " + i);
        }

        System.out.println(chunks);
    }
}
