package com.genture.auto_push.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
@Component
public class BaseMap {

	private String picPath;
	private String startPos;
	private String endPos;
	private String textPoint;
	private int travelTime;

	private List<Road> roads = new ArrayList<>();

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}

	public String getStartPos() {
		return startPos;
	}

	public void setStartPos(String startPos) {
		this.startPos = startPos;
	}

	public String getEndPos() {
		return endPos;
	}

	public void setEndPos(String endPos) {
		this.endPos = endPos;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public String getTextPoint() {
		return textPoint;
	}

	public void setTextPoint(String textPoint) {
		this.textPoint = textPoint;
	}
}
