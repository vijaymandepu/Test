package com.test.multithreading;

import java.util.LinkedList;
import java.util.Random;

public class Processing {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final  int LIMITSIZE=10;
	private Object lock = new Object();
	public void produce() throws InterruptedException{
		int value = 0;
		while(true){
			synchronized (lock){
					while(list.size()==LIMITSIZE){
						lock.wait();
					}
					list.add(value++);
					lock.notify();
			}	
		}
	
	}
	
	public void consume() throws InterruptedException{
		Random random = new Random();
		synchronized (lock) {
			while (true) {
				while(list.size()==0){
					lock.wait();
				}
				System.out.println("list size is: "+list.size());
				int v = list.removeFirst();
				System.out.println("value from the list : "+v);
				lock.notify();
				Thread.sleep(5000);
			}
			
		}
	}

}
