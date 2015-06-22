package com.mydistributedsystem.faulttolerance;

public class Timer extends Thread{
	public static int ts = 0;
	
	
	public void run(){
		try {
			Thread.sleep(1000);
			ts++;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
	}
	public void startTimer(){
		this.start();
	}
}
