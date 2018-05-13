package logger;

import org.junit.Assert;
import org.junit.Test;

public class TestUtils {
    @Test
    public void testSleep() {
        System.out.println("123");
        Utils.sleep(3);

        final long t = System.currentTimeMillis();
        Utils.sleep(3000);
        final long elapsed = System.currentTimeMillis() - t;

        Assert.assertEquals("Nearly 3 second", elapsed, 3000, 100);


    }
}
