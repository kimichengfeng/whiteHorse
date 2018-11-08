package com.wecash.sheJiMoShi.chain.test2;

/**
 * Created with IntelliJ IDEA
 * Description:责任连是web比较常用的设计模式（比如在request拦截，在内容过滤...），在android和ios中的比较核心部分也用到了这个设计模式（触摸拦截）
 * 应用场景
 * 比如现在服务器收到一个请求（request）,我们要过滤请求中的一些关键的字眼。比如现在content = "<scrpit> 法伦功一定要灭掉，尼玛的，你妈的。中国政府真的太好了，呵呵，呵呵";
 * User: tong.cheng
 * Date: 2018-11-08
 * Time: 15:17
 */
public class MainTest {
    public static void main(String[] argus) {
        //要被过滤的内容
        String content = "<scrpit> 法伦功一定要灭掉，尼玛的，你妈的。中国政府真的太好了，呵呵，呵呵";
        //新建一个『过滤链条』
        FilterChain filterChain = new FilterChain();
        //在过滤链条中添加过滤规则
        filterChain.addFilter(new FuckFilter())
                .addFilter(new SensitiveFilter());
        //在一个过滤链中加上一个过滤链
        FilterChain filterChain1 = new FilterChain();
        filterChain1.addFilter(new TagFilter())
                .addFilter(filterChain);
        //过滤后的内容
        String filterContent = filterChain1.doFilter(content);
        //输出内容
        System.out.print(filterContent);
    }

}
