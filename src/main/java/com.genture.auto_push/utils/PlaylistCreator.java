package com.genture.auto_push.utils;

import com.genture.auto_push.model.Playlist;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2018/2/7.
 * 根据自动推送对象，生成播放列表
 */
@Component
public class PlaylistCreator {

	private String filename = "E:\\capture\\effectMap\\play001.lst";


	/**
	 * 将playlist对象对应的json文本，写入到文件中
	 * @param playlist
	 */
	public void writePlayList(Playlist playlist){
		writeToFile(playlist.toString());
	}

	/**
	 * 覆盖写
	 * 向文件中写入内容，并覆盖之前的内容
	 * @param json
	 */
	public void writeToFile(String json){

		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
