package com.ratna.android.droidz.thread;

import com.ratna.android.droidz.view.MainGamePanel;

import android.view.SurfaceHolder;

public class MainThread extends Thread{
private boolean running;
private SurfaceHolder surfaceHolder;
private MainGamePanel mainGamePanel;

public SurfaceHolder getSurfaceHolder() {
	return surfaceHolder;
}
public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
	this.surfaceHolder = surfaceHolder;
}
public MainGamePanel getMainGamePanel() {
	return mainGamePanel;
}
public void setMainGamePanel(MainGamePanel mainGamePanel) {
	this.mainGamePanel = mainGamePanel;
}
		public void setRunning(boolean running){
		this.running = running;
	}
		@Override
		public void run() {
		super.run();
		}

}
