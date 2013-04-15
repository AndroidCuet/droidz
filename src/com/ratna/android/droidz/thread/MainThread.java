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

public void setRunning(boolean running){
	this.running = running;
}
public MainThread(SurfaceHolder surfaceHolder, MainGamePanel mainGamePanel) {
	super();
	this.surfaceHolder = surfaceHolder;
	this.mainGamePanel = mainGamePanel;
}

	@Override
	public void run() {
	super.run();
		
		Canvas canvas = null;
		Log.d(TAG ,"Starting game loop");
		while(running){
			
				try{
					canvas = this.surfaceHolder.lockCanvas();
					synchronized (surfaceHolder) {
						this.mainGamePanel.onDraw(canvas);
					}
				}finally{
					if(canvas != null){
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			
			
		}
		
		}

}
