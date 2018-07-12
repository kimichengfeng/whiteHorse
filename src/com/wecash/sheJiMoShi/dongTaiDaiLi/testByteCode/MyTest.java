package com.wecash.sheJiMoShi.dongTaiDaiLi.testByteCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
* Java编译器编译好Java文件之后，产生.class 文件在磁盘中。
 * 这种class文件是二进制文件，内容是只有JVM虚拟机能够识别的机器码。JVM虚拟机读取字节码文件，取出二进制数据，加载到内存中，解析.class 文件内的信息，生成对应的 Class对象
*
 * 同一个加载器无法加载相同包的的同名类，在类加载机制里面，我们说到每个类在内存中只有一份，这里的一份是指拥有相同的类加载器的情况。因此很多第三方框架都喜欢用自己的类加载器，不至于重复。
 * eg：Tomcat容器，每个WebApp有自己的ClassLoader,加载每个WebApp的ClassPath路径上的类，一旦遇到Tomcat自带的Jar包就委托给CommonClassLoader加载；
 * @author chengTong
* @date 2018-06-25 14:56
**/
public class MyTest {
    public static void main(String[] args) throws IOException {
        //读取本地的class文件内的字节码，转换成字节码数组
        File file = new File(".");
        InputStream input = new FileInputStream(file.getCanonicalPath()+"/out/production/MyTest/com/wecash/sheJiMoShi/dongTaiDaiLi/testByteCode/Programmer.class");
        byte[] result = new byte[1024];

        int count = input.read(result);
        // 使用自定义的类加载器将 byte字节码数组转换为对应的class对象
        MyClassLoader loader = new MyClassLoader();
        Class clazz = loader.defineMyClass( result, 0, count);
        //测试加载是否成功，打印class 对象的名称
        System.out.println(clazz.getCanonicalName());
        try {
        //实例化一个Programmer对象
            Object o= clazz.newInstance();
            //调用Programmer的code方法
            clazz.getMethod("code", null).invoke(o, null);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
