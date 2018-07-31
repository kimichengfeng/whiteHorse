package com.wecash.sheJiMoShi.impl.factory.method;

import com.wecash.sheJiMoShi.impl.factory.easy.Product;
import com.wecash.sheJiMoShi.impl.factory.easy.ProductB;

public class FactoryB implements Factory {
    @Override
    public Product createProduct() {
        //实现工厂类的方法生成产品类B
        return new ProductB();
    }
}
