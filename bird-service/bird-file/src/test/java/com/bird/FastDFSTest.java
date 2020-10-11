package com.bird;

import com.bird.utils.FastDFSUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Author lipu
 * @Date 2020/10/11 19:56
 * @Description
 */
@SpringBootTest(classes = FileApp.class)
@RunWith(SpringRunner.class)
public class FastDFSTest {
    /**
     * @Author lipu
     * @Date 2020/10/11 20:20
     * @Description 获取tracker服务器信息
     */
    @Test
    public void getTracker() throws IOException {
        String tracker = FastDFSUtils.getTracker();
        System.out.println(tracker);
    }
}
