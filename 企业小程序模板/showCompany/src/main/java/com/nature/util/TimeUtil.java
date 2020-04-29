package com.nature.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public static final long ONE_DAY_TIMESTAMP = 24 * 60 * 60 * 1000L;
	public static void main(String[] args) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		System.out.println(getEndOfDay(new Date()));
		System.out.println(System.currentTimeMillis());
		//System.out.println(getEndOfDay(System.currentTimeMillis()));
	}
	
	//时间转化
    public static String getTime(Date date) {  
        String todySDF = "HH:mm";  
        String yesterDaySDF = "昨天";  
        String otherSDF = "M-d";  
        SimpleDateFormat sfd = null;  
        String time = "";  
        Calendar dateCalendar = Calendar.getInstance();  
        dateCalendar.setTime(date);  
        Date now = new Date();  
        Calendar targetCalendar = Calendar.getInstance();  
        targetCalendar.setTime(now);  
        targetCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        targetCalendar.set(Calendar.MINUTE, 0);  
        if (dateCalendar.after(targetCalendar)) {  
            sfd = new SimpleDateFormat(todySDF);  
            time = sfd.format(date);  
            return time;  
        } else {  
            targetCalendar.add(Calendar.DATE, -1);  
            if (dateCalendar.after(targetCalendar)) {  
                sfd = new SimpleDateFormat(yesterDaySDF);  
                time = sfd.format(date);  
                return time;  
            }  
        }  
        sfd = new SimpleDateFormat(otherSDF);  
        time = sfd.format(date);  
       
        return time;  
    }  
    
    
	/**
	 * 获取星期第一天
	 * 
	 * @param now
	 * @return
	 */
	public static Date getBeginOfWeek(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取单天时间开始时间
	 * 
	 * @param now
	 * @return
	 */
	public static Date getBeginOfDay(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取now那天时间第一秒
	 * @param now
	 * @return
	 */
	public static long getBeginOfDay(long now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(now));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}
	public static long getBeginOfDayLong(Date now) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}
	/**
	 * 获取单天时间第一秒
	 * @param now
	 * @return
	 */
	public static long getBeginOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取单天最后一秒
	 */
	public static long getEndOfDay(long now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(now));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return calendar.getTimeInMillis();
	}
	public static long getEndOfDay(Date now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return calendar.getTime().getTime();
	}
	public static long getEndOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return calendar.getTimeInMillis();
	}

	public static long getBeginOfYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTimeInMillis();
	}
	
	public static long getBeginOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTimeInMillis();
	}
	
	public static long getPreThreeMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MONTH, 1);
		return calendar.getTimeInMillis();
	}

	public static long getDayZeroToMillis(int specialDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, specialDay);
		return calendar.getTimeInMillis();
	}

	/**
	 * return login_time>now_month �ϴε�¼ʱ����ڱ���1�ŵ��賿��Ϊͬһ����true��
	 * 
	 * @param time
	 * @return
	 */
	public static Boolean isTheSameMonth(long time) {
		long login_time = time;
		long now_month = TimeUtil.getDayZeroToMillis(1);
		return login_time > now_month;
	}

	/**
	 * add by ace ��ǰ��δ��ĳһʱ����ʣ�µ�ʱ��(��ʽ01:34:50)
	 * 
	 * @param time
	 * @return
	 */
	public static String desTimeCh(long time) {
		String res = "";
		long now = System.currentTimeMillis();
		long desTime = time - now;
		if (desTime < 0L)
			return "0:00:00";
		int t = (int) (desTime / 1000);
		int h = t / (60 * 60);
		int m = t % (60 * 60) / 60;
		int s = t % (60 * 60) % 60;
		res = h + ":";
		if (m < 10)
			res = res + "0" + m + ":";
		else
			res = res + m + ":";
		if (s < 10)
			res = res + "0" + s;
		else
			res = res + s;
		return res;
	}

	/**
	 * add by ace ��ǰ��δ��ĳһʱ����ʣ�µ�ʱ��
	 * 
	 * @param time
	 * @return
	 */
	public static String desTime(long time) {
		String res = "ʣ";
		long now = System.currentTimeMillis();
		long desTime = time - now;
		if (desTime < 0L)
			return "0:00:00";
		int t = (int) (desTime / 1000);
		int d = t / (60 * 60 * 24);
		int h = (t - d * 60 * 60 * 24) / (60 * 60);
		int m = (t - d * 60 * 60 * 24) % (60 * 60) / 60;
		int s = (t - d * 60 * 60 * 24) % (60 * 60) % 60;
		if (d != 0) {
			res += d + "��";
			if (h != 0)
				res += h + "Сʱ";
		} else {
			if (h != 0) {
				res += h + "Сʱ";
				if (m != 0)
					res += m + "��";
			} else {
				if (m != 0) {
					res += m + "��";
					if (s != 0)
						res += s + "��";
				} else {
					if (s != 0)
						res += s + "��";
				}
			}
		}
		return res;
	}

	/**
	 * add by ace ������ת�����ַ�(��ʽ1Сʱ45����)
	 * 
	 * @param time
	 * @return
	 */
	public static String chTime(long time) {
		String res = "";
		long now = System.currentTimeMillis();
		long desTime = time - now;
		if (desTime < 0L)
			return "0:00:00";
		int t = (int) (desTime / 1000);
		int h = t / (60 * 60);
		int m = t % (60 * 60) / 60;
		if (h > 0) {
			res = h + "ʱ";
		}
		if (m > 0) {
			res += m + "��";
		}
		return res;
	}

	/**
	 * ���㵱ǰ��δ��ĳһʱ��ʣ��ķ���(����һ���Ӱ�һ������)
	 * 
	 * @param desTime
	 * @return
	 */
	public static int getRemainMinute(long time) {
		long now = new Date().getTime();
		long desTime = time - now;
		if (desTime < 0L)
			return 0;
		int m = (int) (desTime / (60 * 1000));
		if (desTime % (60 * 1000) > 0)
			m++;
		return m;
	}

	public static String localDateTime(long time) {
		Date date = new Date(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);// ��ȡ���
		int month = cal.get(Calendar.MONTH) + 1;// ��ȡ�·�
		int day = cal.get(Calendar.DAY_OF_MONTH);// ��ȡ��
		return year + "-" + month + "-" + day;
	}

	public static long getMillisecondOfHour(int hour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}
	
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 获取当前日期 yyMMddHHmmssSSS
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	/**
	 * 时间转换成字符串
	 * @param date 时间
	 * @param formatType 格式化类型
	 * @return String
	 */
	public static String formatDate(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}
	
	/**
	 * 将毫秒数转换成时分秒
	 * @param ms
	 * @return 05小时05分钟05秒005毫秒
	 */
	public static String transformTime(long ms) {  
        
        int ss = 1000;  
        int mi = ss * 60;  
        int hh = mi * 60;  
        int dd = hh * 24;  

        long day = ms / dd;  
        long hour = (ms - day * dd) / hh;  
        long minute = (ms - day * dd - hour * hh) / mi;  
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  

        String strDay = day < 10 ? "0" + day : "" + day; //天  
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时  
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟  
        String strSecond = second < 10 ? "0" + second : "" + second;//秒  
        String strMilliSecond = milliSecond < 10 ? "00" + milliSecond : "" + milliSecond;//毫秒  
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;  
         
        return strHour + "小时" + strMinute + "分钟 " + strSecond + "秒";  
}  
	
}
