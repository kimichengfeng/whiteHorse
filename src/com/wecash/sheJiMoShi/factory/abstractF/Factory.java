package com.wecash.sheJiMoShi.factory.abstractF;

import com.wecash.sheJiMoShi.factory.easy.Product;

/**
 * 声明Product类工厂和Gift类工厂的工厂接口
 */
public interface  Factory {
    public Product createProduct();
    public Gift createGift();
}
