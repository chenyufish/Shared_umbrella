package com.fishman.mapper;

import com.fishman.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: CurrentUserMapperTest <br/>
 *
 * @author fishman
 * @date 2023/6/7 0007
 * 
 */
@SpringBootTest
public class CurrentUserMapperTest {

    @Autowired
    CurrentUserMapper currentUserMapper;

    @Test
    public void queryByTokenTest(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDQzMzkzMzY0QHFxLmNvbSIsImV4cCI6MTY4Njk3MTkwNTcwMSwiaWF0IjoxNjg2MTA3OTA1NzAxfQ.z13B4ZY5ulV-gAyI_SxJFtp8ppDvyqeITMe2w-PMOFc";
        User userByToken = currentUserMapper.getUserByToken(token);
        System.out.println(userByToken);
    }

}
