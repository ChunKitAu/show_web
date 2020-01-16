package com.chunkit.show_web.util;

import com.chunkit.show_web.auth.util.SHA256Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther ChunKitAu
 * @create 2020-01-16 16
 */
@RunWith(SpringRunner.class)
public class SHA256UtilTest {
    @Test
    public void test() {
        String s= SHA256Util.sha256("root1234","admin");
        System.out.println(s);
    }

}
