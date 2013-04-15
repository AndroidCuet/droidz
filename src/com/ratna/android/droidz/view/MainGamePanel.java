package com.ratna.android.droidz.view;

import com.ratna.android.droidz.R;
import com.ratna.android.droidz.thread.MainThread;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements
	SurfaceHolder.Callback {
	MainThread thread;
	private static final String TAG = MainGamePanel.class.getSimpleName();

	public MainGamePanel(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		// make the GamePanel focusable so it can handle event
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {

			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(event.getY()>getHeight() - 50){
				thread.setRunning(false);
				((Activity)getContext()).finish();
			}else{
				Log.d(TAG, "Co-ordinate x:" +event.getX()+" Co-ordinate y:" +event.getY());
			}
		}
	return super.onTouchEvent(event);
	
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 10, 10,null);
	}

}
