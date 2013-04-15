package com.ratna.android.droidz.view;

import com.ratna.android.droidz.R;
import com.ratna.android.droidz.model.Droidz;
import com.ratna.android.droidz.thread.MainThread;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements
	SurfaceHolder.Callback {
	
	private MainThread thread;
	private Droidz droid;
	
	private static final String TAG = MainGamePanel.class.getSimpleName();

	public MainGamePanel(Context context) {
		super(context);
		
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		droid = new Droidz(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50);
		
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
			droid.handleActionDown((int)event.getX(), (int)event.getY());
			if(event.getY()>getHeight() - 50){
				thread.setRunning(false);
				((Activity)getContext()).finish();
			}else{
				Log.d(TAG, "Co-ordinate x:" +event.getX()+" Co-ordinate y:" +event.getY());
			}
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			if(droid.isTouched()){
				droid.setX((int)event.getX());
				droid.setY((int)event.getY());
			}
		}
		if(event.getAction() == MotionEvent.ACTION_UP){
			if(droid.isTouched()){
				droid.setTouched(false);
			}
		}
	return true;
	
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		droid.draw(canvas);
	}

}
