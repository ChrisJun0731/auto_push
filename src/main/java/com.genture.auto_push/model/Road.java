package com.genture.auto_push.model;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/2/8.
 */
@Component
public class Road {

	private String roadCode;
	private int roadCon;
	private String up_left_point;
	private String bottom_right_point;

	public int getRoadCon() {
		return roadCon;
	}

	public void setRoadCon(int roadCon) {
		this.roadCon = roadCon;
	}

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getUp_left_point() {
		return up_left_point;
	}

	public void setUp_left_point(String up_left_point) {
		this.up_left_point = up_left_point;
	}

	public String getBottom_right_point() {
		return bottom_right_point;
	}

	public void setBottom_right_point(String bottom_right_point) {
		this.bottom_right_point = bottom_right_point;
	}
}
