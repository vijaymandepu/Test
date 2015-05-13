package com.test.multithreading;

import java.util.concurrent.CountDownLatch;

public class MyFirstThread implements Runnable {
	private String name;
	CountDownLatch countDownLatch ;
	public MyFirstThread(String name,CountDownLatch countDownLatch ) {
		this.name=name;
		this.countDownLatch =countDownLatch;
	}

	@Override
	public void run() {
		System.out.println("You are inside thread --"+name);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished the thread --"+name);
		countDownLatch.countDown();
	}

}
