package com.genture.auto_push.utils;

import com.genture.auto_push.model.BaseMap;
import com.genture.auto_push.model.BaseMapModel;
import com.genture.auto_push.model.Road;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2018/2/7.
 * 与高德进行交互，发起请求，获取返回结果。
 */
@Component
public class GMapInteract {

	/**
	 * 根据BaseMapModel中的参数，请求高德接口
	 * 并将结果返回给BaseMapModel
	 *
	 * @param baseMapModel
	 * @return
	 */
	public BaseMapModel renderModel(BaseMapModel baseMapModel) {


		for (BaseMap baseMap : baseMapModel.getBaseMaps()) {

			//计算旅行时间
			{
				String travleTimeResult = queryTravleTime(baseMap.getStartPos(), baseMap.getEndPos());
				JSONObject timeObj = JSONObject.fromObject(travleTimeResult);
				JSONObject status = (JSONObject) timeObj.get("status");
				String msg = (String) status.get("msg");
				int travelTime = 0;
				if (msg.equals("success")) {
					JSONObject data = (JSONObject) timeObj.get("data");
					JSONObject route = (JSONObject) data.get("route");
					JSONArray paths = (JSONArray) route.get("paths");
					JSONArray steps = (JSONArray) ((JSONObject) paths.get(0)).get("steps");
					for (Object step : steps) {
						String duration = (String) ((JSONObject) step).get("duration");
						travelTime += Integer.parseInt(duration);
					}
					travelTime = (int) Math.ceil((float) travelTime / 60);
					baseMap.setTravelTime(travelTime);
				} else {
					System.out.println("向高德请求旅行时间接口报错！");
				}
			}

			//计算每段道路的颜色
			for (Road road : baseMap.getRoads()) {
				String roadConResult = queryRoadCondition(road.getRoadCode());
				JSONObject roadConObj = JSONObject.fromObject(roadConResult);
				JSONObject status = (JSONObject) roadConObj.get("status");
				String msg = (String) status.get("msg");
				int roadCon = 1;
				if (msg.equals("success")) {
					JSONArray data = (JSONArray) roadConObj.get("data");
					for (Object d : data) {
						int sta = (int) ((JSONObject) d).get("status");
						if (sta > roadCon) {
							roadCon = sta;
						}
					}
					road.setRoadCon(roadCon);
				} else {
					System.out.println("向高德请求路况接口报错！");
				}
			}
		}


		return baseMapModel;
	}

	/**
	 * 访问高德，查询路况拥堵信息
	 *
	 * @param roadCode
	 * @return
	 */
	public String queryRoadCondition(String roadCode) {

		URL url = createRoadConUrl(roadCode);

		return getRequest(url);
	}

	/**
	 * 访问高德，查询旅行时间
	 *
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public String queryTravleTime(String startPos, String endPos) {

		URL url = createTravelTimeUrl(startPos, endPos);

		return getRequest(url);
	}

	/**
	 * 发起get请求，获得请求体
	 *
	 * @param url
	 * @return
	 */
	public String getRequest(URL url) {

		String responseBody = "";

		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(false);
			conn.setDoInput(true);
			conn.connect();
			InputStream stream = conn.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = bufReader.readLine()) != null) {
				responseBody += line;
			}
			conn.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseBody;
	}

	/**
	 * 创建请求路况信息的url
	 *
	 * @param roadCode
	 * @return
	 */
	public URL createRoadConUrl(String roadCode) {

		URL url = null;
		String urlStr = "https://tp-restapi.amap.com/gate?sid=30012&reqData={" +
				"'city':'330200'," +
				"'roadId':" + roadCode +
				"}&serviceKey=BD243DECB2718615BE7730DD08277A88";

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return url;
	}

	/**
	 * 创建查询旅行时间的url
	 *
	 * @param startPos
	 * @param endPos
	 * @return
	 */
	public URL createTravelTimeUrl(String startPos, String endPos) {
		URL url = null;
		String urlStr = "https://tp-restapi.amap.com/gate?sid=30011&reqData={\"city\":\"330200\",\"startpos\":\"" + startPos +
				"\",\"endpos\":\"" + endPos + "\",\"strategy\":\"0\"}&serviceKey=BD243DECB2718615BE7730DD08277A88";

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return url;
	}
}


