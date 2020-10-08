package com.bird.user;

import com.bird.SurveyApp;
import com.bird.domain.User;
import com.bird.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/8 11:26
 * @Description
 */
@SpringBootTest(classes = SurveyApp.class)
@RunWith(SpringRunner.class)
public class UserTest {
    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void findAll(){
        List<User> userList = userService.findAll();
        for (User user:userList) {
            System.out.println(user);
        }
    }

    @Test
    public void redisTest(){
        List<User> userList = userService.findAll();
        redisTemplate.opsForValue().set("userList",userList);
        List<User> result =(List<User>) redisTemplate.opsForValue().get("userList");
        System.out.println(result);
    }
}
