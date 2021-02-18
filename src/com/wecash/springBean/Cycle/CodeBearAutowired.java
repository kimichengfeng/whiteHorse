package com.wecash.springBean.Cycle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA
 * Description:自定义一个注解，字段上打上这个注解的，说明需要被Autowired
 * User: tong.cheng
 * Date: 2020-10-13
 * Time: 11:53
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeBearAutowired {

}
