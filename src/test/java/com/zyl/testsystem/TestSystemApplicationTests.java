package com.zyl.testsystem;

import com.zyl.testsystem.service.ThymeleafService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSystemApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    private ThymeleafService thymeleafService;
    @Test
    public void createHtmlTest() throws IOException {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        //thymeleafService.createHtml(AppConstat.FILE_LEARN_PREFIX+1L,1);
    }
}
