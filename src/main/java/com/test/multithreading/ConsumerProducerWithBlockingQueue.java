package com.test.multithreading;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ConsumerProducerWithBlockingQueue {
	private static BlockingQueue<Integer> blockingQueue = new java.util.concurrent.ArrayBlockingQueue<Integer>(10);
	public static void main (String s[]){
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				produce();
			}			
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				consume();
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
	protected static void consume() {
		Random random = new Random();
		while(true){
			try {
				blockingQueue.put(random.nextInt(100));
			} catch (InterruptedException e) {
			}
		}
		
		
	}
	protected static void produce() {
		Random random = new Random();
		try {
			while (true){
				Thread.sleep(100);
				if(random.nextInt(10)==0){
					Integer n = blockingQueue.take();
					System.out.println("Picked value from queue"+n + " Queue Size:"+blockingQueue.size());
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
