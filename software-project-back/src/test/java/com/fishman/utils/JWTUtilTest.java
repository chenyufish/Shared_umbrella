package com.fishman.utils;

import com.fishman.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: JWTUtilTest <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@SpringBootTest
public class JWTUtilTest {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    UserService userService;

    /**
     * token生成测试
     */
    @Test
    public void generateTokenTest(){
        String email = "EUzm68ki@example.com";
        String password = "9fgB2GeK";
//        User user = userService.queryUser(email,password);
        String token = jwtUtil.generateToken(email);
        System.out.println(token);
    }

    /**
     * token解析测试
     */
    @Test
    public void getUsernameFromToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJFVXptNjhraUBleGFtcGxlLmNvbSIsImV4cCI6MTY4NjkwMDQwNTQ3NywiaWF0IjoxNjg2MDM2NDA1NDc3fQ.yBS-D5AppidN6fjQzmcKxn3aFCgawBdsF0sqdFUaCj0";
        String usernameFromToken = jwtUtil.getPromptFromToken(token);
        System.out.println(usernameFromToken);
    }
}
