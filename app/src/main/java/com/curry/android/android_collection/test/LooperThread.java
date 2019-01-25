package com.curry.android.android_collection.test;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class LooperThread extends Thread {
    Handler handler;

    @Override
    public void run() {
        // 将当前线程初始化为Looper线程
        Looper.prepare();

        // ...其他处理，如实例化handler
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    System.out.println(msg.obj.toString());
                }
            }
        };

        Message message = Message.obtain(handler, 0, "test");
        Bundle bundle = new Bundle();
        message.setData(bundle);
        handler.sendMessageDelayed(message, 1000);

        // 开始循环处理消息队列
        Looper.loop();
    }

    public static void main(String[] args) {
        LooperThread looperThread = new LooperThread();
        looperThread.start();
    }

}


