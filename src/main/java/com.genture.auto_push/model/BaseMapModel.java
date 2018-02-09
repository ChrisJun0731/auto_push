package com.genture.auto_push.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 * 模板，由多个BaseMapInfo组成
 */
@Component
public class BaseMapModel {

	private List<BaseMap> baseMaps = new ArrayList<>();

	public List<BaseMap> getBaseMaps() {
		return baseMaps;
	}

	public void setBaseMaps(List<BaseMap> baseMaps) {
		this.baseMaps = baseMaps;
	}
}
