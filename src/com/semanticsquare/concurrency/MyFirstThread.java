package com.semanticsquare.concurrency;

import java.util.concurrent.TimeUnit;

public class MyFirstThread {
	
	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task); // NEW
		thread.start();		// once a run() method is executed completed then it goes to dead state.. so we cannot restart the same thread
		//thread.start(); // this will give error

		try {
			//Thread.sleep(3000);  //  current thread has to sleep for 3000ms
			TimeUnit.SECONDS.sleep(3);
			thread.interrupt(); // will make interrupt= True
			//TimeUnit.DAYS.sleep(5);  // can convert  to any time
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Inside main ...");
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		System.out.println("Inside run ...");
		try {

			TimeUnit.SECONDS.sleep(9);

		} catch (InterruptedException e) {
			System.out.println("Interrupted !!");
		}
		go();
	}

	private void go() {
		System.out.println("Inside go ...");
		more();
	}
	
	private void more() {
		System.out.println("Inside more ...");		
	}	
}