package com.test.multithreading;

public class ConsumerProducerWithBasicLock {

	public static void main(String s[]){
		final Processing processing = new Processing();
		Thread t1= new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						processing.produce();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}});
		
		Thread t2= new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						processing.consume();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}});
		t1.start();t2.start();
		try {
			t1.join();t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
