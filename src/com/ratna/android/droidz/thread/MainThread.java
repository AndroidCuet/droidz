package com.ratna.android.droidz.thread;

import com.ratna.android.droidz.view.MainGamePanel;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
	
private boolean running;
private SurfaceHolder surfaceHolder;
private MainGamePanel mainGamePanel;
private static final String TAG = MainThread.class.getSimpleName();

public MainThread(SurfaceHolder surfaceHolder, MainGamePanel mainGamePanel) {
	super();
	this.surfaceHolder = surfaceHolder;
	this.mainGamePanel = mainGamePanel;
}
		public void setRunning(boolean running){
		this.running = running;
	}
		@Override
		public void run() {
		super.run();
		long tickCount = 0L;
		Canvas canvas = null;
		Log.d(TAG ,"Starting game loop");
		while(running){
			if(canvas == null){
				try{
					canvas = this.surfaceHolder.lockCanvas();
					synchronized (surfaceHolder) {
						this.mainGamePanel.onDraw(canvas);
					}
				}finally{
					if(canvas == null){
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}
			tickCount++;
		}
		Log.d(TAG, "GameLoopExecute" + tickCount + "times");
		}

}
