package utils;

import model.BaseMapInfo;
import model.Model;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 * 用来读取模板的配置属性
 */
public class ModelConfigReader {

	public Model readConfig(String filename){

		Model model = new Model();

		try {
			File configFile = ResourceUtils.getFile("classpath:model_config.json");
			BufferedReader bufReader = new BufferedReader(new FileReader(configFile));
			String json = "";
			String line;
			List<BaseMapInfo> baseMapInfos = new ArrayList<>();

			while((line=bufReader.readLine()) != null){
				json += line;
			}
			JSONArray configArr = JSONArray.fromObject(json);
			for(Object obj: configArr){
				BaseMapInfo baseMapInfo = new BaseMapInfo();
				JSONObject baseMap = (JSONObject)obj;
				String picPath = (String)baseMap.get("picPaht");
				String startPos1 = (String)baseMap.get("startPos1");
				String endPos1 = (String)baseMap.get("endPos1");
				String startPos2 = (String)baseMap.get("startPos2");
				String endPos2 = (String)baseMap.get("endPos2");
				String startPos3 = (String)baseMap.get("startPos3");
				String endPos3 = (String)baseMap.get("endPos3");
				String up_left_point1 = (String)baseMap.get("up_left_point1");
				String bottom_right_point1 = (String)baseMap.get("bottom_right_point1");
				String up_left_point2 = (String)baseMap.get("up_left_point2");
				String bottom_right_point2 = (String)baseMap.get("bottom_right_point2");
				String up_left_point3 = (String)baseMap.get("up_left_point3");
				String bottom_right_point3 = (String)baseMap.get("bottom_right_point3");
				String startPos = (String)baseMap.get("startPos");
				String endPos = (String)baseMap.get("endPos");

				baseMapInfo.setPicPath(picPath);
				baseMapInfo.setStartPos1(startPos1);
				baseMapInfo.setEndPos1(endPos1);
				baseMapInfo.setStartPos2(startPos2);
				baseMapInfo.setEndPos2(endPos2);
				baseMapInfo.setStartPos3(startPos3);
				baseMapInfo.setEndPos3(endPos3);
				baseMapInfo.setUp_left_point1(up_left_point1);
				baseMapInfo.setBottom_right_point1(bottom_right_point1);
				baseMapInfo.setUp_left_point2(up_left_point2);
				baseMapInfo.setBottom_right_point2(bottom_right_point2);
				baseMapInfo.setUp_left_point3(up_left_point3);
				baseMapInfo.setBottom_right_point3(bottom_right_point3);
				baseMapInfo.setStartPos(startPos);
				baseMapInfo.setEndPos(endPos);

				baseMapInfos.add(baseMapInfo);
			}
			model.setBaseMapInfos(baseMapInfos);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}
}
