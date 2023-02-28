package com.semanticsquare.concurrency;
// in this type of code Volatile variable is better than synchronization keyword
import java.util.concurrent.TimeUnit;

public class StopThread {
    private static volatile boolean stop;  // volatile variable

    public static void main(String[] args) throws InterruptedException {
    	 new Thread(new Runnable() {
              public void run() {
                   while(!stop) { System.out.println("In while ..."); }
              }
          }).start();

          TimeUnit.SECONDS.sleep(1);
          stop = true; 
    }
}
