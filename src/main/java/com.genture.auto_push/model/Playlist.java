package com.genture.auto_push.model;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */
@Component
public class Playlist {

	private String param = "50,1,0,1,0,5,1";
	private List<Img> imgs;

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("[all]\n");
		builder.append("items="+ imgs.size() + "\n");
		for(int i=0; i<imgs.size(); i++){
			builder.append("[item"+ (i+1) + "]\n");
			builder.append("param=" + param + "\n");
			Img img = imgs.get(i);
			String prop = "img" + (i+1) + "=" + img.getX() + "," + img.getY() + "," + img.getFilename() +
					"," + img.getBlink() + "," + img.getWidth() + "," + img.getHeight() + "\n" +
					"imgparam" + (i+1) + "=" + img.getStayTime() +"\n";
			builder.append(prop);
		}
		return builder.toString();
	}

	public List<Img> getImgs() {
		return imgs;
	}

	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}
}
