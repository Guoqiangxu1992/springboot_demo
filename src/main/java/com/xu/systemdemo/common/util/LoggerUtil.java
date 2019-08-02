package com.xu.systemdemo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LoggerUtil {
    // 源生日志记录对象
    private static final Logger log = LoggerFactory.getLogger("custom_log");
    // 本对象的路径
    private static String thisClassUrl = LoggerUtil.class.getName();
    private LoggerUtil(){};

    private static void setSeat() {
        MDC.put("seat", getInvokInfo());
    }

    private static String getInvokInfo() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String className = null;
        String methodName = null;
        int lineNumber = 0;
        // 从栈的最上开始 往下找 找到第一个不为Log和线程的类
        for (int i = 0; i < ste.length; i++) {
            className = ste[i].getClassName();

            if ("java.lang.Thread".equals(className) || thisClassUrl.equals(className)) {
                if (i != (ste.length - 1)) {
                    continue;// 如果不是最后一个就跳过，如果是最后一个那就没办法了，返回这栈信息吧
                }
            }
            methodName = ste[i].getMethodName();
            lineNumber = ste[i].getLineNumber();
            break;
        }
        return className + "." + methodName + "()@" + lineNumber + "";
    }

    public static void debug(String arg0) {
        if (log.isDebugEnabled()) {
            setSeat();
            log.debug(arg0);
        }
    }
    public static void error(String arg0) {
        if (log.isErrorEnabled()) {
            setSeat();
            log.error(arg0);
        }
    }


    public static void info(String arg0) {
        if (log.isInfoEnabled()) {
            setSeat();
            log.info(arg0);
        }
    }
}
