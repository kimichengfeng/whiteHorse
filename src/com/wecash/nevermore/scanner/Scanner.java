package com.wecash.nevermore.scanner;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by sunhui on 16/12/5.
 */
@Slf4j
public class Scanner {
    static {
        try {
            Map<String, String> map = PackageWalker.loadLocalConfig();
            log.info("开始扫描包……:");
            //todo 预留一个接口？
        } catch (Exception e) {
            log.info("scanner初始化失败:", e);
        }
    }

}
