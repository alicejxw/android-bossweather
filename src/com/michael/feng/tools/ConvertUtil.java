package com.michael.feng.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.michael.feng.bossweather.R;

public class ConvertUtil {
	
	public static String dateToLocal() {
		
//		SimpleDateFormat sdfCST = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm a z");
		SimpleDateFormat sdfDST = new SimpleDateFormat("EEE， MMM.dd.yyyy");
		String destStr = "";
		destStr = sdfDST.format(new Date());
		
		// Convert Day to Chinese
		destStr = destStr.replace("Mon", "星期一");
		destStr = destStr.replace("Tue", "星期二");
		destStr = destStr.replace("Wed", "星期三");
		destStr = destStr.replace("Thu", "星期四");
		destStr = destStr.replace("Fri", "星期五");
		destStr = destStr.replace("Sat", "星期六");
		destStr = destStr.replace("Sun", "星期日");
		
		// Convert Month to number
		destStr = destStr.replace("Jan", "1");
		destStr = destStr.replace("Feb", "2");
		destStr = destStr.replace("Mar", "3");
		destStr = destStr.replace("Apr", "4");
		destStr = destStr.replace("May", "5");
		destStr = destStr.replace("Jun", "6");
		destStr = destStr.replace("Jul", "7");
		destStr = destStr.replace("Aug", "8");
		destStr = destStr.replace("Sep", "9");
		destStr = destStr.replace("Oct", "10");
		destStr = destStr.replace("Nov", "11");
		destStr = destStr.replace("Dec", "12");

		return destStr;
	}
	
	public static String weatherToLocal(String weather) {
		weather = weather.toLowerCase();
		weather = weather.replace("tornado", "龙卷风");
		weather = weather.replace("tropical storm", "热带风暴");
		weather = weather.replace("hurricane", "飓风");
		
		// Thunderstorm
		weather = weather.replace("severe thunderstorms", "强雷暴");
		weather = weather.replace("thunderstorms", "雷暴");
		
		// Rain and snow
		weather = weather.replace("mixed rain and snow",  "雨夹雪");
		weather = weather.replace("mixed rain and sleet", "雨夹雪");
		weather = weather.replace("mixed snow and sleet", "雨夹雪");
		weather = weather.replace("freezing drizzle", "冻雨");
		weather = weather.replace("drizzle", "小雨");
		weather = weather.replace("freezing rain", "冰雨");
		weather = weather.replace("showers", "阵雨");
		weather = weather.replace("snow flurries", "飘雪");
		weather = weather.replace("light snow showers", "阵雪");
		weather = weather.replace("blowing snow", "雪");
		weather = weather.replace("snow", "雪");
		weather = weather.replace("hail", "冰雹");
		weather = weather.replace("sleet", "雨夹雪");
		weather = weather.replace("dust", "尘土");
		weather = weather.replace("foggy", "雾");
		weather = weather.replace("haze", "阴霾");
		weather = weather.replace("smoky", "雾霾");
		weather = weather.replace("blustery", "阴");
		weather = weather.replace("windy", "多风");
		
		// Cold
		weather = weather.replace("cold", "冷");
		
		// Cloud
		weather = weather.replace("cloudy", "多云");
		weather = weather.replace("partly cloudy", "多云");
		weather = weather.replace("mostly cloudy (night)", "晴到多云");
		weather = weather.replace("mostly cloudy (day)", "晴到多云");
		weather = weather.replace("partly cloudy (night)", "晴到多云");
		
		// Sunny
		weather = weather.replace("partly cloudy (day)", "晴");
		weather = weather.replace("clear", "晴");
		weather = weather.replace("clear (night)", "晴");
		weather = weather.replace("sunny", "晴");
		weather = weather.replace("fair", "晴");
		weather = weather.replace("fair (night)", "晴");
		weather = weather.replace("fair (day)", "晴");
		weather = weather.replace("mixed rain and hail", "雨夹冰雹");
		weather = weather.replace("hot", "晴");
		
		// Thunderstorm
		weather = weather.replace("isolated thunderstorms", "局部雷暴");
		weather = weather.replace("scattered thunderstorms", "局部雷暴");
		weather = weather.replace("scattered showers", "零星阵雨");
		weather = weather.replace("heavy snow", "大雪");
		weather = weather.replace("scattered snow showers", "零星阵雪");
		weather = weather.replace("partly cloudy", "多云");
		weather = weather.replace("thundershowers", "雷阵雨");
		weather = weather.replace("snow showers", "阵雪");
		weather = weather.replace("isolated thundershowers", "雷阵雨");
		weather = weather.replace("not available", "未知");
		weather = weather.replace("mostly", "");
		weather = weather.replace("isolated", "");
		weather = weather.replace("partly", "局部");
		weather = weather.replace("mist", "雾");
		weather = weather.replace("rain shower", "细雨");
		weather = weather.replace("scattered", "局部");
		weather = weather.replace(" ", "");

		return weather;
	}
	
	public static int getIconByWeather(String weather) {
		int icon = R.drawable.sunny;
		if(weather.equals("晴到多云")) {
			icon = R.drawable.partly_sunny;
		} else if(weather.equals("雾") || weather.equals("阴霾") || weather.equals("雾霾")) {
			icon = R.drawable.foggy;
		} else if(weather.equals("多云") || weather.equals("阴") || weather.equals("局部多云")) {
			icon = R.drawable.cloud;
		} else if(weather.equals("冷") || weather.equals("多风")) {
			icon = R.drawable.cold;
		} else if(weather.equals("雷阵雨") || weather.equals("雷暴") || weather.equals("强雷暴") || weather.equals("局部雷暴")) {
			icon = R.drawable.thunderstorm;
		} else if(weather.equals("雪") || weather.equals("冰雹") || weather.equals("大雪")) {
			icon = R.drawable.snow;
		} else if(weather.equals("阵雪") || weather.equals("零星阵雪") || weather.equals("飘雪")) {
			icon = R.drawable.small_snow;
		} else if(weather.equals("细雨") || weather.equals("零星阵雨")) {
			icon = R.drawable.little_rain;
		} else if(weather.equals("小雨") || weather.equals("阵雨") || weather.equals("冻雨") || weather.equals("冰雨")) {
			icon = R.drawable.rain;
		} else if(weather.equals("雨夹雪") || weather.equals("雨夹冰雹")) {
			icon = R.drawable.rain_and_snow;
		}
		return icon;
	}
	
}
