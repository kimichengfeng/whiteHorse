package com.wecash.baseTest.doubleDispatch;
/**
* 
* @author chengTong
* @date 2018-07-10 11:09
**/
public class Keeper {
    public void say(Animal a) {
        System.out.println("Animal say");
    }

    public void say(Dog dog) {
       System.out.println("dog say");
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();
        Dog dog2 = new Dog();
        Keeper keeper = new Keeper();
        keeper.say(animal);
        keeper.say(dog);

        Animal.bark();
        Dog.bark();

        Dog.barkStaic();
    }
}
