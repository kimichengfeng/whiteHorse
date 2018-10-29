package com.wecash.nevermore.scanner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wecash.nevermore.constant.MyConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by sunhui on 16/12/6.
 */
@Slf4j
public class PackageWalker {

    /**
     * 扫描并获取一个包名下的所有Class对象
     *
     * @param path
     * @return
     */
    public static List<Class> findClass(String path) throws Exception {
        ClassVisitor visitor = ClassVisitor.create();
        Files.walkFileTree(Paths.get(Thread.currentThread().getContextClassLoader().getResource(path.replace(".", File.separator)).toURI()), visitor);
        return Lists.newArrayList(visitor.getClassBimap().values());
    }

    /**
     * 扫描并获取一个包名下的所有Class对象
     *
     * @param path
     * @return
     */
    public static List<Class> findClass(String path, Predicate<Class> predicate) throws Exception {
        ClassVisitor visitor = ClassVisitor.create();
        Files.walkFileTree(Paths.get(Thread.currentThread().getContextClassLoader().getResource(path.replace(".", File.separator)).toURI()), visitor);
        return Lists.newArrayList(visitor.getClassBimap().values()).stream().filter(predicate).collect(Collectors.toList());
    }

    public static List<String> findConfigFilePath(String path) throws Exception {
        ClassVisitor visitor = ClassVisitor.create();
        Files.walkFileTree(Paths.get(Thread.currentThread().getContextClassLoader().getResource(path.replace(".", File.separator)).toURI()), visitor);
        return visitor.getResourceFile();
    }

    public static Map<String, String> loadLocalConfig() throws Exception {
        List<String> filePaths = findConfigFilePath("");
        Map<String, String> map = Maps.newHashMap();
        filePaths.forEach(filePath -> {
            try {
                List<String> lines = com.google.common.io.Files.readLines(new File(filePath), Charset.defaultCharset());
                lines.forEach(line -> {
                    if(line.startsWith("#")){
                        log.info("当前注释行");
                    }else if(line.trim().length()!=0) {
                        String key = MyConstant.EQUAL_SPLITTER.splitToList(line).get(0);
                        String value = MyConstant.EQUAL_SPLITTER.splitToList(line).get(1);
                        if (StringUtils.isNotEmpty(map.get(key))) {
                            log.error("已经有了该配置，请确认是否覆盖");
                            throw new IllegalArgumentException("相同的key冲突了:" + key + "，旧值:" + map.get(key) + "新值放不进去:" + value);
                        }
                        map.put(key, value);
                    }

                });
            } catch (IOException e) {
                log.info("初始化配置出异常了:", e);
            }
        });
        log.info("配置初始化完成:{}", map);
        return map;
    }

}
