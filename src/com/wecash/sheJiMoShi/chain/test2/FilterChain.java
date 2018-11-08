package com.wecash.sheJiMoShi.chain.test2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description: 过滤链（拦截链）
 * User: tong.cheng
 * Date: 2018-11-08
 * Time: 15:07
 */
public class FilterChain  implements Filter{
    public List<Filter> mFilters = new ArrayList<>();

    public FilterChain addFilter(Filter filter){
        mFilters.add(filter);
        return this;
    }
    @Override
    public String doFilter(String content) {
        for(Filter filter : mFilters){
            content = filter.doFilter(content);
        }
        return content;
    }
}
