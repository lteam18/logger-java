package logger;

import org.junit.Assert;
import org.junit.Test;

public class TestUtils {
    @Test
    public void testSleep() {
        System.out.println("123");
        try {
            Utils.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final long t = System.currentTimeMillis();
        try {
            Utils.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final long elapsed = System.currentTimeMillis() - t;

        Assert.assertEquals("Nearly 3 second", elapsed, 3000, 100);


    }
}
