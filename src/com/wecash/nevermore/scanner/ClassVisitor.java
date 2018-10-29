package com.wecash.nevermore.scanner;

import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author hui.sun
 * @email hui.sun@qunar.com
 * @date 2015-09-13
 */
@Slf4j
public class ClassVisitor extends SimpleFileVisitor<Path> {

    private static Splitter classSplitter = Splitter.on("classes"+ File.separator);

    private BiMap<String, Class> classBimap = HashBiMap.create();  //存储类名和反射路径

    private List<String> resourceFile = Lists.newArrayList();

    public static ClassVisitor create() {
        return new ClassVisitor();
    }

    public List<String> getResourceFile() {
        return resourceFile;
    }

    private ClassVisitor() {
    }

    public BiMap<String, Class> getClassBimap() {
        return classBimap;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.getFileName().toString().endsWith(".class")) {
            try {
                String clazz = getClassNameFromPath(file);
                classBimap.put(clazz, Class.forName(clazz));
            } catch (ClassNotFoundException e) {
                log.info("class not found :{}", file.getFileName(), e);
            }
        } else if (file.getFileName().toString().endsWith(".properties") || file.getFileName().toString().endsWith(".conf")) {
            resourceFile.add(getFileRealPath(file));
        }
        return super.visitFile(file, attrs);
    }


    private String getFileRealPath(Path file) {
        return file.getParent().toString() + File.separator + file.getFileName();
    }

    private String getClassNameFromPath(Path file) {
        return classSplitter.splitToList
                (file.getParent().toString()).get(1).replace(File.separator, ".")
                + "." + file.getFileName().toString().replace(".class", "");
    }


    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }

}
