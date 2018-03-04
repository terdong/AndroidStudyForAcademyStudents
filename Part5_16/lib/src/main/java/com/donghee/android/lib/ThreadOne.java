package com.donghee.android.lib;

/**
 * Created by Administrator on 2018-03-04.
 */

public class ThreadOne implements Runnable{
    @Override
    public void run() {

        for(int i=0; i < 100; ++i){
            System.out.println(String.format("threadOne count = %d", i));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
