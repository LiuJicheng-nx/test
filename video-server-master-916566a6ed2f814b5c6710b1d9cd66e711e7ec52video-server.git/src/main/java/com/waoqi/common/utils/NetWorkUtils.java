package com.waoqi.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetWorkUtils {

    /**
     * 获取当前ip 内网
     * @return
     */
    public static String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }
}
