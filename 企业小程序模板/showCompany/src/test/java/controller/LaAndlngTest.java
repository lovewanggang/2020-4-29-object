package controller;

import java.math.BigDecimal;
import java.util.Map;

import com.nature.util.GetLatAndLngUtil;

/**
 * @description
 * @author len
 * @createDate 2018年7月16日
 */
public class LaAndlngTest {

	
	public static void main(String[] args) {
		Map<String, BigDecimal> map = GetLatAndLngUtil.getLatAndLngByAddressFTX("广东省广州市海珠区珠江国际纺织城D区30823");
		System.out.println(map.get("lng").doubleValue());
		System.out.println(map.get("lat").doubleValue());
	}
}
