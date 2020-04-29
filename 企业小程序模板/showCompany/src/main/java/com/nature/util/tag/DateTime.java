package com.nature.util.tag;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	public static String getDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd，HH:mm:ss").format(date);
	}
	public static String getDay(Date date){
		return new SimpleDateFormat("yyyy.M.d").format(date);
	}
	public static String getDisplayTime(Date date) {
		Date nowDate = new Date();
		Calendar oldCalendar = Calendar.getInstance();
		Calendar nowCalendar = Calendar.getInstance();
		oldCalendar.setTime(date);
		nowCalendar.setTime(nowDate);
		int day = getDaysBetween(oldCalendar, nowCalendar);
		Long nowTime = nowDate.getTime();
		Long oldTime = date.getTime();
		Long time = nowTime - oldTime;

		/***
		 * reserver for further usage*** if(time>60*60*1000){ if(day==0){ return
		 * new SimpleDateFormat("HH:mm").format(date); }else if(day==1){ return
		 * "昨天"+new SimpleDateFormat("HH:mm").format(date); }else if(day==2){
		 * return "前天"+new SimpleDateFormat("HH:mm").format(date); }else if(day
		 * < 180){ return new SimpleDateFormat("MM-dd HH:mm").format(date);
		 * }else{ return new SimpleDateFormat("yyyy-MM-dd").format(date); }
		 * }else if(time<60*1000){ return "刚刚"; }else{ return
		 * (long)time/(60*1000)+"分钟前"; }
		 */

		if (time != 0) {
			if (day == 0) {
				return new SimpleDateFormat("HH:mm").format(date);
			} else if (day == 1) {
				return "昨天" + new SimpleDateFormat("HH:mm").format(date);
			} else if (day == 2) {
				return "前天" + new SimpleDateFormat("HH:mm").format(date);
			} else if (day < 180) {
				return new SimpleDateFormat("M.d HH:mm").format(date);
			} else {
				return new SimpleDateFormat("yyyy.M.d").format(date);
			}
		} else {
			return "刚刚";
		}

	}

	public static int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}

		int days = d2.get(java.util.Calendar.DAY_OF_YEAR) - d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	public static String formatTimeInterval(Date oldDate, Date newDate) {
		String result = "剩";
		long timeInSeconds = (newDate.getTime() - oldDate.getTime()) / 1000;

		int days = (int) (timeInSeconds / (60 * 60 * 24));
		timeInSeconds = timeInSeconds - (days * 60 * 60 * 24);
		int hours = (int) (timeInSeconds / (60 * 60));
		timeInSeconds = timeInSeconds - (hours * 60 * 60);
		int minutes = (int) (timeInSeconds / 60);
		timeInSeconds = timeInSeconds - (minutes * 60);
		int seconds = (int) timeInSeconds;

		if (days > 0) {
			result += days + "天";
		}
		if (hours > 0) {
			result += hours + "时";
		}
		if (minutes > 0) {
			result += minutes + "分";
		}
		if (seconds > 0) {
			result += seconds + "秒";
		}
		if("剩".equals(result)) {
			return "";
		}
		return result;
	}

	// 副将特训页面显示特训时间
	public static String formatTimeInterval(long millisecond) {

		int days = (int) (millisecond / (24 * 60 * 60 * 1000L));
		millisecond = millisecond - (days * 24 * 60 * 60 * 1000L);
		int hours = (int) (millisecond / (60 * 60 * 1000L));
		millisecond = millisecond - (hours * 60 * 60 * 1000L);
		int minutes = (int) (millisecond / (60 * 1000L));
		millisecond = millisecond - (minutes * 60 * 1000L);
		int seconds = (int) (millisecond / 1000L);
		String result = "";
		if (days > 0) {
			result += days + "天";
		}
		if (hours > 0) {
			result += hours + "小时";
		}
		if (minutes > 0) {
			result += minutes + "分";
		}
		if (seconds > 0) {
			result += seconds + "秒";
		}
		return result;
	}

	public static String formatTimeIntervalFromNow(Date date) {
		return formatTimeInterval(new Date(), date);
	}

	public static String formatTimeIntervalFromNow(long date) {
		return formatTimeInterval(new Date(), new Date(date));
	}

	public static String formatNoWordTimeLeft(long date) {
		long timeInSeconds = date / 1000;

		String result = "";

		// int days = (int)(timeInSeconds/(60*60*24));
		// timeInSeconds = timeInSeconds-(days*60*60*24);
		int hours = (int) (timeInSeconds / (60 * 60));
		timeInSeconds = timeInSeconds - (hours * 60 * 60);
		int minutes = (int) (timeInSeconds / 60);
		timeInSeconds = timeInSeconds - (minutes * 60);
		int seconds = (int) timeInSeconds;

		// if(days > 0)
		// {
		// result += days + ":";
		// }
		if (hours > 0) {
			result += hours + ":";
			if (minutes >= 0) {
				if (minutes / 10 >= 1) {
					result += minutes + ":";
				} else {
					result += "0" + minutes + ":";
				}
			}
			if (seconds >= 0) {
				if (seconds / 10 >= 1) {
					result += seconds;
				} else {
					result += "0" + seconds;
				}
			}
		} else {
			if (minutes > 0) {
				if (minutes / 10 >= 1) {
					result += minutes + ":";
				} else {
					result += "0" + minutes + ":";
				}
				if (seconds >= 0) {
					if (seconds / 10 >= 1) {
						result += seconds;
					} else {
						result += "0" + seconds;
					}
				}
			} else {
				if (seconds / 10 >= 1) {
					result += "00:" + seconds;
				} else {
					result += "00:0" + seconds;
				}
			}
		}

		return result;
	}

	public static String formatTimeLeft(long date) {
		long timeInSeconds = date / 1000;

		String result = "";

		int days = (int) (timeInSeconds / (60 * 60 * 24));
		timeInSeconds = timeInSeconds - (days * 60 * 60 * 24);
		int hours = (int) (timeInSeconds / (60 * 60));
		timeInSeconds = timeInSeconds - (hours * 60 * 60);
		int minutes = (int) (timeInSeconds / 60);
		timeInSeconds = timeInSeconds - (minutes * 60);
		int seconds = (int) timeInSeconds;

		if (days > 0) {
			result += days + "天";
		}
		if (hours > 0) {
			result += hours + "小时";
		}
		if (minutes > 0) {
			result += minutes + "分钟";
		}
		if ((seconds > 0 && !(hours > 0 || days > 0)) || (seconds == 0 && days == 0 && hours == 0 & minutes == 0)) {
			result += seconds + "秒";
		}

		return result;
	}

	// 格式->15天5时||5时3分6秒
	public static String newFormatTimeLeft(long date) {
		date -= System.currentTimeMillis();
		if (date < 0) {
			return "已过期";
		}
		long timeInSeconds = date / 1000;
		String result = "";
		int days = (int) (timeInSeconds / (60 * 60 * 24));
		timeInSeconds = timeInSeconds - (days * 60 * 60 * 24);
		int hours = (int) (timeInSeconds / (60 * 60));
		timeInSeconds = timeInSeconds - (hours * 60 * 60);
		int minutes = (int) (timeInSeconds / 60);
		timeInSeconds = timeInSeconds - (minutes * 60);
		int seconds = (int) timeInSeconds;

		if (days > 0) {
			result += days + "天";
			if (hours > 0) {
				result += hours + "时";
			}
		} else {
			if (hours > 0) {
				result += hours + "时";
			}
			if (minutes > 0) {
				result += minutes + "分";
			}
			if (seconds > 0 && !(hours > 0 || days > 0) || (seconds == 0 && days == 0 && hours == 0 & minutes == 0)) {
				result += seconds + "秒";
			}
		}
		return result;
	}

	public static String doubleCardLeftTime(long datetime) {
		long timeInSeconds = (datetime - System.currentTimeMillis()) / 1000;
		if (timeInSeconds <= 0)
			return "无";
		// int hours = (int)(timeInSeconds / 3600);
		int minutes = (int) (timeInSeconds / 60);
		int seconds = (int) (timeInSeconds % 60);
		if (minutes > 0) {
			return "剩" + minutes + "分" + seconds + "秒";
		} else
			return "剩" + seconds + "秒";

	}
}
