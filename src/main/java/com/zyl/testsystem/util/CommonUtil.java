package com.zyl.testsystem.util;

/**
 * @author jiangtingfeng
 * @description 公共方法
 * @date 2020/6/21/021
 */

public class CommonUtil {

    public static String getImageName(String filePath){
        String[] split = filePath.split("/");
        return split[split.length-1];
    }


}
