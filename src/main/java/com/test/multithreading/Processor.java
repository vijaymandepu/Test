package com.test.multithreading;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException{
		synchronized(this){
			System.out.println("Producer thread is running...");
			wait();
			System.out.println("Proudcer Thread Resumed....");
		}
		
	}
	public void consume() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(3000);
		synchronized (this){
			System.out.println("Waiting for key board hit.. Press any key...");
			scanner.nextLine();
			System.out.println("Key pressed.. about to return...");
			notify();
			Thread.sleep(2000);
		}
		
	}

}
