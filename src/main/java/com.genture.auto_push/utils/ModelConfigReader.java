package com.genture.auto_push.utils;

import com.genture.auto_push.model.BaseMap;
import com.genture.auto_push.model.BaseMapModel;
import com.genture.auto_push.model.Road;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Administrator on 2018/2/7.
 * 用来读取模板的配置属性
 */
@Component
public class ModelConfigReader {

	public BaseMapModel readConfig(){

		BaseMapModel baseMapModel = new BaseMapModel();

		try {
			File configFile = ResourceUtils.getFile("classpath:model_config.json");
			BufferedReader bufReader = new BufferedReader(new FileReader(configFile));
			String json = "";
			String line;

			while ((line = bufReader.readLine()) != null) {
				json += line;
			}
			JSONObject configObj = JSONObject.fromObject(json);

			JSONArray baseMapsArr = (JSONArray)configObj.get("baseMaps");
			for (Object baseMapObj : baseMapsArr) {
				BaseMap baseMap = new BaseMap();
				String picPath = (String)((JSONObject)baseMapObj).get("picPath");
				String startPos = (String)((JSONObject)baseMapObj).get("startPos");
				String endPos = (String)((JSONObject)baseMapObj).get("endPos");
				String textPoint = (String) ((JSONObject) baseMapObj).get("textPoint");
				baseMap.setStartPos(startPos);
				baseMap.setEndPos(endPos);
				baseMap.setPicPath(picPath);
				baseMap.setTextPoint(textPoint);
				JSONArray roadArr = (JSONArray)((JSONObject)baseMapObj).get("roads");
				for (Object roadObj : roadArr) {
					Road road = new Road();
					String roadCode = (String)((JSONObject)roadObj).get("roadCode");
					String up_left_point = (String)((JSONObject)roadObj).get("up_left_point");
					String bottom_right_point = (String)((JSONObject)roadObj).get("bottom_right_point");
					road.setRoadCode(roadCode);
					road.setUp_left_point(up_left_point);
					road.setBottom_right_point(bottom_right_point);
					baseMap.getRoads().add(road);
				}
				baseMapModel.getBaseMaps().add(baseMap);
			}



		}catch(Exception e){
			e.printStackTrace();
		}

		return baseMapModel;
	}
}
