package com.wecash.sheJiMoShi.factory.method;

import com.wecash.sheJiMoShi.factory.easy.Product;
import com.wecash.sheJiMoShi.factory.easy.ProductA;

public class FactoryA implements Factory {
    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
