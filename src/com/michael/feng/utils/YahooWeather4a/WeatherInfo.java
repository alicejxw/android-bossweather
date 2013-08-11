/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2012 Zhenghong Wang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.michael.feng.utils.YahooWeather4a;

public class WeatherInfo {
	
	String mTitle;
	String mDescription;
	String mLanguage;
	String mLastBuildDate;
	String mLocationCity;
	String mLocationRegion; // region may be null
	String mLocationCountry;
	
	String mWindChill;
	String mWindDirection;
	String mWindSpeed;
	
	String mAtmosphereHumidity;
	String mAtmosphereVisibility;
	String mAtmospherePressure;
	String mAtmosphereRising;
	
	String mAstronomySunrise;
	String mAstronomySunset;
	
	String mConditionTitle;
	String mConditionLat;
	String mConditionLon;

	// Weather Current day
	int mCurrentCode;
	String mCurrentText;
	int mCurrentTempC;
	int mCurrentTempF;
	String mCurrentConditionIconURL;
	String mCurrentConditionDate;

	// Weather Forecast day 1
	String mForecast1Day;
	String mForecast1Date;
	int mForecast1Code;
	String mForecast1Text;
	int mForecast1TempHighC;
	int mForecast1TempLowC;
	int mForecast1TempHighF;
	int mForecast1TempLowF;
	String mForecast1ConditionIconURL;
	
	// Weather Forecast day 2
	String mForecast2Day;
	String mForecast2Date;
	int mForecast2Code;
	String mForecast2Text;
	int mForecast2TempHighC;
	int mForecast2TempLowC;
	int mForecast2TempHighF;
	int mForecast2TempLowF;
	String mForecast2ConditionIconURL;
	
	// Weather detail
	public String getTitle() {
		return mTitle;
	}

	void setTitle(String title) {
		mTitle = title;
	}

	public String getDescription() {
		return mDescription;
	}

	void setDescription(String description) {
		mDescription = description;
	}
	
	public String getLanguage() {
		return mLanguage;
	}

	void setLanguage(String language) {
		mLanguage = language;
	}

	public String getLastBuildDate() {
		return mLastBuildDate;
	}

	void setLastBuildDate(String lastBuildDate) {
		mLastBuildDate = lastBuildDate;
	}

	public String getLocationCity() {
		return mLocationCity;
	}

	void setLocationCity(String locationCity) {
		mLocationCity = locationCity;
	}

	public String getLocationRegion() {
		return mLocationRegion;
	}

	void setLocationRegion(String locationRegion) {
		mLocationRegion = locationRegion;
	}

	public String getLocationCountry() {
		return mLocationCountry;
	}

	void setLocationCountry(String locationCountry) {
		mLocationCountry = locationCountry;
	}
	
	// Wind detail of weather 
	public String getWindChill() {
		return mWindChill;
	}

	void setWindChill(String windChill) {
		mWindChill = windChill;
	}

	public String getWindDirection() {
		return mWindDirection;
	}

	void setWindDirection(String windDirection) {
		mWindDirection = windDirection;
	}

	public String getWindSpeed() {
		return mWindSpeed;
	}

	void setWindSpeed(String windSpeed) {
		mWindSpeed = windSpeed;
	}
	
	// Atmosphere detail of weather
	public String getAtmosphereHumidity() {
		return mAtmosphereHumidity;
	}

	void setAtmosphereHumidity(String atmosphereHumidity) {
		mAtmosphereHumidity = atmosphereHumidity;
	}

	public String getAtmosphereVisibility() {
		return mAtmosphereVisibility;
	}

	void setAtmosphereVisibility(String atmosphereVisibility) {
		mAtmosphereVisibility = atmosphereVisibility;
	}

	public String getAtmospherePressure() {
		return mAtmospherePressure;
	}

	void setAtmospherePressure(String atmospherePressure) {
		mAtmospherePressure = atmospherePressure;
	}

	public String getAtmosphereRising() {
		return mAtmosphereRising;
	}

	void setAtmosphereRising(String atmosphereRising) {
		mAtmosphereRising = atmosphereRising;
	}

	public String getAstronomySunrise() {
		return mAstronomySunrise;
	}

	void setAstronomySunrise(String astronomySunrise) {
		mAstronomySunrise = astronomySunrise;
	}

	public String getAstronomySunset() {
		return mAstronomySunset;
	}

	void setAstronomySunset(String astronomySunset) {
		mAstronomySunset = astronomySunset;
	}

	public String getConditionTitle() {
		return mConditionTitle;
	}

	void setConditionTitle(String conditionTitle) {
		mConditionTitle = conditionTitle;
	}

	public String getConditionLat() {
		return mConditionLat;
	}

	void setConditionLat(String conditionLat) {
		mConditionLat = conditionLat;
	}

	public String getConditionLon() {
		return mConditionLon;
	}

	void setConditionLon(String conditionLon) {
		mConditionLon = conditionLon;
	}
	
	// Weather detail of current
	public int getCurrentCode() {
		return mCurrentCode;
	}

	void setCurrentCode(int currentCode) {
		mCurrentCode = currentCode;
		mCurrentConditionIconURL = "http://l.yimg.com/a/i/us/we/52/" + currentCode + ".gif";
	}
	
	public String getCurrentText() {
		return mCurrentText;
	}

	void setCurrentText(String currentText) {
		mCurrentText = currentText;
	}
	
	public int getCurrentTempC() {
		return mCurrentTempC;
	}

	void setCurrentTempC(int currentTempC) {
		mCurrentTempC = currentTempC;
	}
	
	public int getCurrentTempF() {
		return mCurrentTempF;
	}

	void setCurrentTempF(int currentTempF) {
		mCurrentTempF = currentTempF;
		mCurrentTempC = this.turnFtoC(currentTempF);
	}
	
	public String getCurrentConditionIconURL() {
		return mCurrentConditionIconURL;
	}

	void setCurrentConditionIconURL(String currentConditionIconURL) {
		mCurrentConditionIconURL = currentConditionIconURL;
	}

	public String getCurrentConditionDate() {
		return mCurrentConditionDate;
	}
	
	void setCurrentConditionDate(String currentConditionDate) {
		mCurrentConditionDate = currentConditionDate;
	}
	
	private int turnFtoC(int tempF) {
		return (tempF - 32) * 5 / 9;
	}

	public String getForecast1Day() {
		return mForecast1Day;
	}

	void setForecast1Day(String forecast1Day) {
		mForecast1Day = forecast1Day;
	}
	
	public String getForecast1Date() {
		return mForecast1Date;
	}

	void setForecast1Date(String forecast1Date) {
		mForecast1Date = forecast1Date;
	}

	public int getForecast1Code() {
		return mForecast1Code;
	}

	void setForecast1Code(int forecast1Code) {
		mForecast1Code = forecast1Code;
		mForecast1ConditionIconURL = "http://l.yimg.com/a/i/us/we/52/" + forecast1Code + ".gif";
	}

	public String getForecast1Text() {
		return mForecast1Text;
	}

	void setForecast1Text(String forecast1Text) {
		mForecast1Text = forecast1Text;
	}
	
	void setForecast1TempHighF(int forecast1TempHighF) {
		mForecast1TempHighF = forecast1TempHighF;
		mForecast1TempHighC = this.turnFtoC(forecast1TempHighF);
	}

	public int getForecast1TempHighF() {
		return mForecast1TempHighF;
	}

	public int getForecast1TempHighC() {
		return mForecast1TempHighC;
	}

	void setForecast1TempLowF(int forecast1TempLowF) {
		mForecast1TempLowF = forecast1TempLowF;
		mForecast1TempLowC = this.turnFtoC(forecast1TempLowF);
	}
	
	public int getForecast1TempLowC() {
		return mForecast1TempLowC;
	}

	public int getForecast1TempLowF() {
		return mForecast1TempLowF;
	}

	public String getForecast1ConditionIconURL() {
		return mForecast1ConditionIconURL;
	}
	
	public String getForecast2Day() {
		return mForecast2Day;
	}

	void setForecast2Day(String forecast2Day) {
		mForecast2Day = forecast2Day;
	}
	
	public String getForecast2Date() {
		return mForecast2Date;
	}

	void setForecast2Date(String forecast2Date) {
		mForecast2Date = forecast2Date;
	}

	public int getForecast2Code() {
		return mForecast2Code;
	}

	void setForecast2Code(int forecast2Code) {
		mForecast2Code = forecast2Code;
		mForecast2ConditionIconURL = "http://l.yimg.com/a/i/us/we/52/" + forecast2Code + ".gif";
	}
	
	public String getForecast2Text() {
		return mForecast2Text;
	}

	void setForecast2Text(String forecast2Text) {
		mForecast2Text = forecast2Text;
	}

	void setForecast2TempHighF(int forecast2TempHighF) {
		mForecast2TempHighF = forecast2TempHighF;
		mForecast2TempHighC = this.turnFtoC(forecast2TempHighF);
	}

	public int getForecast2TempLowC() {
		return mForecast2TempLowC;
	}
	
	public int getForecast2TempHighC() {
		return mForecast2TempHighC;
	}
	
	void setForecast2TempLowF(int forecast2TempLowF) {
		mForecast2TempLowF = forecast2TempLowF;
		mForecast2TempLowC = this.turnFtoC(forecast2TempLowF);
	}
	
	public int getForecast2TempHighF() {
		return mForecast2TempHighF;
	}

	public int getForecast2TempLowF() {
		return mForecast2TempLowF;
	}

	public String getForecast2ConditionIconURL() {
		return mForecast2ConditionIconURL;
	}

}
