package com.nature.util;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * <p>
 * BASE64编码解码工具包
 * </p>
 * <p>
 * 依赖javabase64-1.3.1.jar
 * </p>
 *
 */
public class Base64Utils {
	 /**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;
     
    /**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     *
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64){
    	byte[] bytes = Base64.decode(base64.getBytes());
        return bytes;
    }
     
    /**
     * <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes){
        return new String(Base64.encode(bytes));
    }
     
    /**
     * <p>
     * 将文件编码为BASE64字符串
     * </p>
     * <p>
     * 大文件慎用，可能会导致内存溢出
     * </p>
     *
     * @param filePath 文件绝对路径
     * @return
     * @throws Exception
     */
    public static String encodeFile(String filePath){
        byte[] bytes = null;
		try {
			bytes = fileToByte(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return encode(bytes);
    }
     
    /**
     * <p>
     * BASE64字符串转回文件
     * </p>
     *
     * @param filePath 文件绝对路径
     * @param base64 编码字符串
     * @throws Exception
     */
    public static void decodeToFile(String filePath, String base64){
        byte[] bytes = decode(base64);
        byteArrayToFile(bytes, filePath);
    }
     
    /**
     * <p>
     * 文件转换为二进制数组
     * </p>
     *
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static byte[] fileToByte(String filePath){
        byte[] data = new byte[0];
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
			try {
				in = new FileInputStream(file);
				 out = new ByteArrayOutputStream(2048);
		            byte[] cache = new byte[CACHE_SIZE];
		            int nRead = 0;
		            while ((nRead = in.read(cache)) != -1) {
		                out.write(cache, 0, nRead);
		                out.flush();
		            }
		           
					out.close();
		            in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            data = out.toByteArray();
         }
        return data;
    }
     
    /**
     * <p>
     * 二进制数据写文件
     * </p>
     *
     * @param bytes 二进制数据
     * @param filePath 文件生成目录
     */
    public static void byteArrayToFile(byte[] bytes, String filePath){
		InputStream in = new ByteArrayInputStream(bytes);
		File destFile = new File(filePath);
		if (!destFile.getParentFile().exists()) {
			destFile.getParentFile().mkdirs();
		}
		OutputStream out = null;
		try {
			destFile.createNewFile();
			out = new FileOutputStream(destFile);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
     
}
