package com.genture.auto_push.utils;

import com.genture.auto_push.model.*;
import org.springframework.stereotype.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 * 渲染基图，并将效果图文件存放到指定目录下
 */
@org.springframework.stereotype.Component
public class ImageRender {

	private BaseMapModel baseMapModel;

	/**
	 * 将BaseMap列表中的对象，各绘制一张效果图
	 */
	public void drawEffectPic(){

		List<Img> imgs = new ArrayList<>();
		Playlist playlist = new Playlist();
		PlaylistCreator creator = new PlaylistCreator();

		for(BaseMap baseMap: baseMapModel.getBaseMaps()){

			//绘制效果图
			drawPicFromBaseMap(baseMap);

			//生成播放列表
			Img img = new Img();
			String picName = baseMap.getPicPath().substring(baseMap.getPicPath().lastIndexOf('\\')+1);
			img.setFilename(picName);
			imgs.add(img);
			playlist.setImgs(imgs);
			creator.writePlayList(playlist);

		}



	}

	/**
	 * 根据BaseMap对象，绘制一张效果图
	 * @param baseMap
	 */
	public void drawPicFromBaseMap(BaseMap baseMap){

		String picPath = baseMap.getPicPath();
		File pic = new File(picPath);

		try {
			BufferedImage image = ImageIO.read(pic);

			//渲染基图上路段颜色
			for(Road road: baseMap.getRoads()){
				String[] up_left_point = road.getUp_left_point().split(",");
				String[] bottom_right_point = road.getBottom_right_point().split(",");
				int up_left_x = Integer.parseInt(up_left_point[0]);
				int up_left_y = Integer.parseInt(up_left_point[1]);
				int bottom_right_x = Integer.parseInt(bottom_right_point[0]);
				int bottom_right_y = Integer.parseInt(bottom_right_point[1]);
				for(int x=up_left_x; x<=bottom_right_x; x++){
					for(int y=up_left_y; y<=bottom_right_y; y++){
						if(image.getRGB(x, y)==Color.WHITE.getRGB()){
							if(road.getRoadCon() == 1){
								image.setRGB(x, y, Color.GREEN.getRGB());
							}else if(road.getRoadCon() == 2){
								image.setRGB(x, y, Color.YELLOW.getRGB());
							}else{
								image.setRGB(x, y, Color.RED.getRGB());
							}
						}
					}
				}
			}

			//绘制旅行时间文字
			Graphics2D g = image.createGraphics();
			Font font = new Font("宋体", Font.PLAIN, 20);
			g.setFont(font);
			g.setColor(Color.GREEN);
			String timeText = baseMap.getTravelTime() + "分钟";
			String[] textPoint = baseMap.getTextPoint().split(",");
			g.drawString(timeText, Integer.parseInt(textPoint[0]), Integer.parseInt(textPoint[1]));
			g.dispose();

			//输出图片到指定目录
			String dir = "E:\\capture\\effectMap\\";
			String picName = baseMap.getPicPath().substring(baseMap.getPicPath().lastIndexOf('\\')+1);
			ImageIO.write(image, "png", new File(dir+picName));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BaseMapModel getBaseMapModel() {
		return baseMapModel;
	}

	public void setBaseMapModel(BaseMapModel baseMapModel) {
		this.baseMapModel = baseMapModel;
	}
}
