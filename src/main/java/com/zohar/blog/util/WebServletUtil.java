package com.zohar.blog.util;

import javax.servlet.http.HttpServletRequest;

public class WebServletUtil {

    /**
     * 从请求体中提取客户端 IP 地址，由于可能经过多层代理，如 Nginx 代理，因此需要识别多个 Header
     *
     * @param request 请求体
     * @return 客户端 IP 地址
     */
    public static String getIP(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
            }
            // 通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            return ipAddress == null ? "" : ipAddress.contains(",") ? ipAddress.split(",")[0] : ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
