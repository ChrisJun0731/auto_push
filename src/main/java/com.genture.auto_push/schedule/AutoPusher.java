package com.genture.auto_push.schedule;

import com.genture.auto_push.model.BaseMapModel;
import com.genture.auto_push.utils.GMapInteract;
import com.genture.auto_push.utils.ImageRender;
import com.genture.auto_push.utils.ModelConfigReader;
import com.genture.device_operator.Device;
import com.genture.device_operator.DeviceOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/2/9.
 */
@Component
public class AutoPusher {

	@Autowired
	private ModelConfigReader reader;

	@Autowired
	private GMapInteract interact;

	@Autowired
	private ImageRender render;

	@Scheduled(fixedRate = 2*60*1000)
	public void autoPush(){

		System.out.println("开始执行模板推送任务");

		//读取模板配置信息
		BaseMapModel baseMapModel = reader.readConfig();

		//请求高德，返回拥堵和旅行时间信息
		//将返回信息些人BaseMapModel中
		interact.renderModel(baseMapModel);

		//根据高德返回结果渲染基图，生成播放列表
		render.setBaseMapModel(baseMapModel);
		render.drawEffectPic();

		//下发播放列表和图片
		Device device = new Device("192.168.40.217", 5000);
		DeviceOperator deviceOperator = new DeviceOperator(device);
		deviceOperator.sendFile("E:\\capture\\effectMap\\play001.lst");
		deviceOperator.sendFile("E:\\capture\\effectMap\\13-1.png");
		deviceOperator.sendFile("E:\\capture\\effectMap\\13-2.png");



		//指定播放列表进行播放
		deviceOperator.playAssignedList(1);

		System.out.println("模板推送完毕");

	}
}
