package com.zyl.testsystem.util;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/15/015
 */

public class UuidConvert extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return LogUtil.getLogId();
    }

}
