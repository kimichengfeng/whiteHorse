package com.wecash.nevermore.httpclient;

import com.wecash.nevermore.concurrent.ManagedThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostUtil {
    private static Logger log= LoggerFactory.getLogger(ManagedThreadPool.class);

    public static String getLocalIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("", e);
        }
        return null;
    }

    public static String getLocalHostname() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (UnknownHostException e) {
            log.error("", e);
        }
        return null;
    }
}
