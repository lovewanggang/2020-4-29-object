package com.nature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @description
 * @author len
 * @createDate 2018年3月20日下午4:44:54
 */
public class GetLatAndLngUtil {

	/**
	 * 
	 * @param addr
	 *            地址名称
	 * @return Map（经度lng、纬度lat）
	 */
	public static Map<String, BigDecimal> getLatAndLngByAddress(String addr) {
		String address = "";
		String lat = "";
		String lng = "";
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format(
				"http://api.map.baidu.com/geocoder/v2/?" + "ak=mgeyWAM2ePZHuox93ZjcnRyn8FjDbqxP&output=json&address=%s",
				address);
		URL myURL = null;
		URLConnection httpsConn = null;
		// 进行转码
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {

		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					lat = data.substring(data.indexOf("\"lat\":") + ("\"lat\":").length(),
							data.indexOf("},\"precise\""));
					lng = data.substring(data.indexOf("\"lng\":") + ("\"lng\":").length(), data.indexOf(",\"lat\""));
				}
				insr.close();
			}
		} catch (IOException e) {

		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lat", new BigDecimal(lat));
		map.put("lng", new BigDecimal(lng));
		return map;
	}

	public static Map<String, BigDecimal> getLatAndLngByAddressFTX(String addr) {
		String address = "";
		String lat = "";
		String lng = "";
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// https://apis.map.qq.com/ws/geocoder/v1/?address=北京市海淀区彩和坊路海淀西大街74号&key=HKKBZ-T7MWW-PDIR5-OGVED-YYWZJ-T3FHP
		String url = String.format(
				"https://apis.map.qq.com/ws/geocoder/v1/?address=%s&key=HKKBZ-T7MWW-PDIR5-OGVED-YYWZJ-T3FHP", address);
		URL myURL = null;
		URLConnection httpsConn = null;
		// 进行转码
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {

		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				StringBuffer result = new StringBuffer();
				while ((data = br.readLine()) != null) {
					data = data.replace(" ", "");
					result.append(data);
				}
				lat = result.substring(result.indexOf("\"lat\":") + ("\"lat\":").length(),
						result.indexOf("},\"address"));
				lng = result.substring(result.indexOf("\"lng\":") + ("\"lng\":").length(), result.indexOf(",\"lat\""));

				insr.close();
			}
		} catch (IOException e) {

		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lat", new BigDecimal(lat));
		map.put("lng", new BigDecimal(lng));
		return map;
	}

}
