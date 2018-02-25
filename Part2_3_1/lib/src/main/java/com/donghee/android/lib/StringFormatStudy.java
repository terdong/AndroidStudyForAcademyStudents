package com.donghee.android.lib;

/**
 * Created by Administrator on 2018-02-25.
 */

public class StringFormatStudy {
    public static void main(String[] args) {

        int number = 20;

        String str1 = "안녕하세요?";

        String str2 = "동희님";

        String result = str1 + str2;

        System.out.println("안녕?" + " 나는 동희야");

        System.out.println("안녕?" + " 나는 동희야" + "나는 " + number + " 살이야");

        result = String.format("안녕? %s %s %d %s", "나는 동희야", "나는 ", number, " 살이야");
        System.out.println(String.format("안녕? %s %s %d %s", "나는 동희야", "나는 ", number, " 살이야"));


    }
}
