package com.donghee.android.lib.anonymous_class_study;

/**
 * Created by Administrator on 2018-02-25.
 */

public class AnonymousClassStudy {
    public static void main(String[] args) {

        HelloWorld helloWorld = new HelloWorld();
        System.out.println("helloWorld.getNumber() = " + helloWorld.getNumber());

        // 익명 클래스
        HelloWorld helloWorld1 = new HelloWorld(){
            @Override
            public int getNumber(){
                return number * 2;
            }
        };
        System.out.println("helloWorld1.getNumber() = " + helloWorld1.getNumber());

        HelloWorld helloWorld2 = new HelloWorldChild();
        System.out.println("HelloWorldChild.getNumber() = " + helloWorld2.getNumber());

        Pet dog = new Pet(){
            @Override
            public void bark(){
                System.out.println("왕왕");
            }
            @Override
            public void play(){
                System.out.println("강아지와 즐겁게 놀고 있습니다.");
            }
        };

        dog.bark();
        dog.play();

    }
}
