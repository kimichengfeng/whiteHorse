package com.wecash.fanShe;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by chengtong on 2017/12/29.
 */
public class InvokeTester {
    public int add(int param1, int param2) {
        return param1 + param2;
    }
    public void testMethod(List<Customer> customerList){return;}
    public String echo(String msg) {
        return "echo: " + msg;
    }

    public static void main(String[] args) throws Exception {
        Class<?> classType = InvokeTester.class;
        Object invokeTester = classType.newInstance();

//         Object invokeTester = classType.getConstructor(new
//         Class[]{}).newInstance(new Object[]{});


        //获取InvokeTester类的add()方法
        Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});

        Method[] testMethod = classType.getMethods();

        for(Method method : testMethod) {
            Type[] genericParamTypes = method.getGenericParameterTypes();
//            System.out.println(genericParamTypes[0].toString());
        }

        //调用invokeTester对象上的add()方法
        Object result = addMethod.invoke(invokeTester, new Object[]{new Integer(100), new Integer(200)});
        System.out.println((Integer) result);


        //获取InvokeTester类的echo()方法
        Method echoMethod = classType.getMethod("echo", new Class[]{String.class});
        //调用invokeTester对象的echo()方法
        result = echoMethod.invoke(invokeTester, new Object[]{"Hello"});
        System.out.println((String) result);
        InvokeTester invoke = new InvokeTester();
        Method[] methods = invoke.getClass().getMethods();
        for(Method method: methods){
            if(method.getName().equals("echo")){
                method.invoke(invoke,new App().getPort());
            }
        }
    }
   static class App{
        int port=0;

       public int getPort() {
           return port;
       }

       public void setPort(int port) {
           this.port = port;
       }
   }
}
