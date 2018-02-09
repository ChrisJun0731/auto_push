package com.genture.auto_push.model;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/2/8.
 */
@Component
public class Img {

	private int x;
	private int y;
	private String filename;
	private int blink;
	private int width;
	private int height;
	private int stayTime = 50;

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

	public int getBlink() {
		return blink;
	}

	public void setBlink(int blink) {
		this.blink = blink;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getStayTime() {
		return stayTime;
	}

	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
