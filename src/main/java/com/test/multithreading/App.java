package com.test.multithreading;


public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
////		Thread first = new Thread(new MyFirstThread("1"));
////		Thread two = new Thread(new MyFirstThread("2"));
////		Thread three = new Thread(new MyFirstThread("3"));
////		Thread four = new Thread(new MyFirstThread("4"));
////		first.start();
////		two.start();
////		three.start();
////		four.start();
//		CountDownLatch countDownLatch = new CountDownLatch(5);
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//		for(int i=0;i<5;i++){
//			executor.submit(new Thread(new MyFirstThread(i+1+"",countDownLatch)));
//		}
//		executor.shutdown();
//		try {
//			countDownLatch.await();
//		} catch (InterruptedException e) {
//		}
////		System.out.println("All Task submitted");
////		try {
////			executor.awaitTermination(1, TimeUnit.DAYS);
////		} catch (InterruptedException e) {
////			
////		}
//		System.out.println("All Task completed");
		
		final Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					processor.produce();
				} catch (InterruptedException e) {
				}
			}
			
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					processor.consume();
				} catch (InterruptedException e) {
				}
			}
			
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
	
	}

}
