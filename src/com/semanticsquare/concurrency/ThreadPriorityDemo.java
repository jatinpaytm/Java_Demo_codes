package com.semanticsquare.concurrency;
// setting the priority of threads and using join to suspend main thread.
public class ThreadPriorityDemo {
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread());

		Thread t1 = new Thread(new EmailCampaign());
		Thread t2 = new Thread(new DataAggregator());
		//Thread t3 = new Thread(new DataAgg());
		
		t1.setName("EmailCampaign");
		t2.setName("DataAggregator");
		//t3.setName("DataAgg");
		
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		//t3.setPriority(9);
		
		t1.start();
		//t3.start();
		t2.start();

		
		try {
			// main thread is suspended until t1 and t2 DIES
			t1.join();
			t2.join();     // this means all other thread wait for t2 to finish
//			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Inside main ... ");
	}
}

class EmailCampaign implements Runnable {
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName());
			if (i == 5) {
				// Hint to scheduler that thread is willing to 
				// yield its current use of CPU
				Thread.currentThread().yield();
			}
        }		
	}
}

class DataAggregator implements Runnable {
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName());
        }
	}
}

class DataAgg implements Runnable {
	public void run() {
		for (int i = 1; i <=5; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
}
