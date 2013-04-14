package com.ratna.android.droidz.thread;

public class MainThread extends Thread{
private boolean running;

		public void setRunning(boolean running){
		this.running = running;
	}
		@Override
		public void run() {
		super.run();
		}

}
