package com.wecash.sheJiMoShi.impl.factory.method;

import com.wecash.sheJiMoShi.impl.factory.easy.Product;

public interface Factory {
    //声明产生产品类的方法
    public Product createProduct();
}
