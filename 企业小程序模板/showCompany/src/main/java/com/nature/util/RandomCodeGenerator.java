package com.nature.util;

import java.util.Random;

/**
 * 生成随机字符串
 * @author liuyuan
 *
 */
public class RandomCodeGenerator {

	private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final Random RANDOM = new Random();

	
	/**
	 * 生成length个英文字母组成的字符串
	 * @param length:字符串长度
	 * @return 
	 */
	public static String getRandomStr(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
		}
		return sb.toString();
	}

	/**
	 * 生成length个数字组成的字符串
	 * @param length:字符串长度
	 * @return
	 */
	public static String getRandomNum(int length){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(RANDOM.nextInt(10));
		}
		return sb.toString();
	}
	
	/**
	 * 生成length个英文字母和数字混合组成的字符串
	 * @param length:字符串长度
	 * @return 
	 */
	public static String getRandom(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (RANDOM.nextInt(2) == 0) {
				sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
			}else {
				sb.append(RANDOM.nextInt(10));
			}
		}
		return sb.toString();
	}
}
