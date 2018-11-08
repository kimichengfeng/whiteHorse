package com.wecash.sheJiMoShi.observer;


/**
 * 布告板
 * 具体的观察者
 * @author boce
 *
 */
public class MobilePhone implements Observer, DisplayElement {
	private float temperature;
	private float image;
	private float dampness;
	private Subject familyData;
	
	/**
	 * 构造函数
     * @param familyData
     */
    public MobilePhone(FamilyData familyData) {
    	this.familyData = familyData;
    	familyData.registerObserver(this);
	}
    /**
     * 当update被调用时，把温度图像和湿度保存起来，然后调用display，展示到家庭成员的手机上
     */
	@Override
	public void update(float temp, float image, float dampness) {
		this.temperature = temp;
		this.image = image;
		this.dampness = dampness;
		display();
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("观察者-Current conditions:"+temperature);

	}
    
	

}
