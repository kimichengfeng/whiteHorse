package com.wecash.sheJiMoShi.chain.test2;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2018-11-08
 * Time: 15:09
 */
public class SensitiveFilter implements Filter {

    @Override
    public String doFilter(String content) {
        return content
                .replace("法伦功", "flg")
                .replace("政府", "zf");
    }
}
