package time;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description
 * @author len
 * @createDate 2018年5月30日
 */
public class TestTime {

	public static void main(String[] args) {
		new TestTime().test1();
	}
	
	private static void test1(){
		Calendar cl = Calendar.getInstance();
		
		cl.setTime(new Date());
		long from = cl.getTimeInMillis();
		System.out.println(">>>>>from>>>>>"+from);
		try {
			Thread.sleep(80000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		cl.setTime(new Date());
		long to = cl.getTimeInMillis();
		System.out.println(">>>>>to>>>>>"+to);
		System.out.println(">>>>>   to - from   >>>>>"+(to - from));
		int second = (int)((to-from)/(1000*60*60));
		System.out.println(second);
	}
}
