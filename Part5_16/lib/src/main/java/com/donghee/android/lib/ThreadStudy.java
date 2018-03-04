package com.donghee.android.lib;

import java.util.Scanner;

public class ThreadStudy {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread Study");

 /*       Thread thread = new Thread(new ThreadOne());
        thread.start();*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i) {
                    System.out.println(String.format("threadOne count = %d", i));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            String result = scanner.next();
            System.out.println(String.format("result = %s", result));

            if (result.equals("/exit")) {
                break;
            }
        }
    }
}
