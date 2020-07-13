package com.zyl.testsystem.service;

import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.vo.ImageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/25/025
 */
@Slf4j
@Service
public class ThymeleafServiceImpl implements ThymeleafService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private UserLearnTestService userLearnTestService;

    @Value("${web.destPath}")
    private String destPath;

    /**
     * @Description: 创建html页面
     * @Author: jiangtingfeng
     */
    @Override
    public void createHtml(String id,Integer type) throws IOException {
        // 上下文
        //String path = ResourceUtils.getURL("classpath:").getPath();
//      String property = System.getProperty("user.dir");
//      System.out.println(property);
//      System.out.println(path);
        File staticHtml = new File(destPath);
        if (!staticHtml.exists()) {
            staticHtml.mkdirs();
        }
        destPath = staticHtml.getAbsolutePath();
        Context context = new Context();
        String[] s = id.split("_");
        List<ImageVO> imageVOList = userLearnTestService.getGroupListByUserId(Long.parseLong(s[1]), type);
        context.setVariable(AppConstat.GROUP_OBJ_STRNING,imageVOList);
        // 输出流
        File dest = new File(destPath, id + ".html");
        if (dest.exists()) {
            dest.delete();
        }
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            // 生成html，第一个参数是thymeleaf页面下的原型名称
            if (type.intValue() == 1) {
                templateEngine.process("learnId", context, writer);
            } else {
                templateEngine.process("testId", context, writer);
            }
        } catch (Exception e) {
            log.error("[静态页服务]：生成静态页异常", e);
        }
    }

    @Override
    public void deleteHtml(String id) {
        File dest = new File(destPath, id + ".html");
        if (dest.exists()) {
            dest.delete();
        }
    }
}
