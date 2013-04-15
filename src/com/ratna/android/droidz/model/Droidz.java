package com.ratna.android.droidz.model;

import android.graphics.Bitmap;

public class Droidz {
	
private Bitmap bitmap;
private int x;
private int y;

public Droidz(Bitmap bitmap, int x, int y) {
	super();
	this.bitmap = bitmap;
	this.x = x;
	this.y = y;
}

public Bitmap getBitmap() {
	return bitmap;
}

public void setBitmap(Bitmap bitmap) {
	this.bitmap = bitmap;
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}
}
