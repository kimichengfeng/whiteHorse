package com.wecash.sheJiMoShi.chain.test2;

/**
 * Created with IntelliJ IDEA
 * Description: 粗口过滤器
 * User: tong.cheng
 * Date: 2018-11-08
 * Time: 15:08
 */
public class FuckFilter implements Filter {

    @Override
    public String doFilter(String content) {
        return content.replace("尼玛", "xx")
                .replace("你妈", "xx");
    }
}
