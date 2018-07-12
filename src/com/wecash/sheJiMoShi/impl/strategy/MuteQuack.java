package com.wecash.sheJiMoShi.impl.strategy;


import com.wecash.sheJiMoShi.inter.strategy.QuackBehavior;

public class MuteQuack implements QuackBehavior {

	@Override
	public void quack() {
		// TODO Auto-generated method stub
        System.out.println("Silence");
	}

}
