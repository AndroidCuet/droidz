package com.ratna.android.droidz.thread;

import com.ratna.android.droidz.view.MainGamePanel;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

	private boolean running;
	private SurfaceHolder surfaceHolder;
	private MainGamePanel mainGamePanel;
	private static final int MAX_FBS = 50;
	private static final int MAX_FRAME_SKIPS = 5;
	private static final int FRAME_PERIOD = 100 / MAX_FBS;
	private static final String TAG = MainThread.class.getSimpleName();

	public void setRunning(boolean running) {
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
		long beganTime;
		long timeDiff;
		int frameSkipped;
		int sleepTime;

		Log.d(TAG, "Starting game loop");
		while (running) {

			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					beganTime = System.currentTimeMillis();
					frameSkipped = 0;
					this.mainGamePanel.update();
					this.mainGamePanel.onDraw(canvas);
					timeDiff = System.currentTimeMillis() - beganTime;
					sleepTime = (int) (FRAME_PERIOD - timeDiff);
					if (sleepTime > 0) {
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
								e.printStackTrace();
						}
					} else if (sleepTime < 0 && frameSkipped < MAX_FRAME_SKIPS) {
						this.mainGamePanel.update();
						sleepTime += FRAME_PERIOD;
						frameSkipped++;
					}
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}

		}

	}

}
