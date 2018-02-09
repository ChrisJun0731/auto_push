package com.genture.auto_push.utils;


/**
 * Created by Administrator on 2018/2/8.
 */
public class GMapInteractTest {

	public static void main(String[] args) {
		GMapInteract interact = new GMapInteract();
		String result = interact.queryRoadCondition("12140d4e08171a150d8863061801200128a80130ae1012130df6b9171a15d9b762061801200128c1023000");
		System.out.println(result);

//		String resultTravel = interact.queryTravleTime("121.588263,29.773341", "121.614613,29.743201");
//		System.out.println(resultTravel);
	}
}
