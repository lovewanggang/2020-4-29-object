package controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.swing.text.Position.Bias;

/**
 * @description
 * @author len
 * @createDate 2018年7月18日
 */
public class test {

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		{
			String hi = "你好";
			byte[] b1 = hi.getBytes();
			byte[] b2 = hi.getBytes("utf-8");
			System.out.print("b1经过java的默认编码后：");
			for (byte b : b1) {
				System.out.print(b);
			}
			System.out.println();
			System.out.print("b2经过utf-8编码后：");
			for (byte b : b2) {
				System.out.print(b);
			}
			
//			//获取系统默认编码
//			System.out.println("系统默认编码：" + System.getProperty("file.encoding")); //查询结果GBK
//			//系统默认字符编码
//			System.out.println("系统默认字符编码：" + Charset.defaultCharset()); //查询结果GBK
//			//操作系统用户使用的语言
//			System.out.println("系统默认语言：" + System.getProperty("user.language")); //查询结果zh
//
//			//定义字符串包含数字和中文
//			String t = "hello, 大家好！";
//			//通过getBytes方法获取默认的编码
//			System.out.println("默认编码格式：");
//			byte[] b = t.getBytes(); //ASCII，GBK，UTF-8对数字和英文字母的编码相同，unicode的编码跟前面三项都不同
//			//打印默认编码
//			for(byte a : b)
//			System.out.print(a + ",\t");
//			System.out.println();
//			System.out.println(new String(b));
//			//打印GBK编码
//			System.out.println("GBK编码格式");
//			b = t.getBytes("GBK");
//			for(byte a : b)
//			System.out.print(a + ",\t");
//			System.out.println();
//			System.out.println(new String(b));
//			//打印UTF-8编码
//			System.out.println("UTF-8编码格式");
//			b = t.getBytes("UTF-8");
//			for(byte a : b)
//			System.out.print(a + ",\t");
//			System.out.println();
//			System.out.println(new String(b));
//			//打印ASCII编码
//			System.out.println("ASCII编码格式");
//			b = t.getBytes("ASCII");
//			for(byte a : b)
//			System.out.print(a + ",\t");
//			System.out.println();
//			System.out.println(new String(b));
//			//打印UNICODE编码
//			System.out.println("UNICODE编码格式");
//			b = t.getBytes("UNICODE");
//			for(byte a : b)
//			System.out.print(a + ",\t");
//			System.out.println();
//			System.out.println(new String(b));
//			System.out.println();
	}}
}
