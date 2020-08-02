package com.zohar.blog.util;

import java.util.Date;

public class TimeUtil {

    /**
     * 获取时间间隔 - 单位秒
     *
     * @param datetime1 时间 1
     * @param datetime2 时间 2
     * @return 时间间隔
     */
    public static Long msInterval(Date datetime1, Date datetime2) {
        return Math.abs(datetime1.getTime() - datetime2.getTime());
    }
}
