package com.wecash.sheJiMoShi.impl.factory.method;

import com.wecash.sheJiMoShi.impl.factory.easy.Product;
import com.wecash.sheJiMoShi.impl.factory.easy.ProductA;

public class FactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
