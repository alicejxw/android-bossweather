/*
 * Copyright (C) 2013 The Android Open Source Project
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

package com.michael.feng.tools;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.michael.feng.model.City;

public class CityDAO {
	// Database fields
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns = { 
			SQLiteHelper.COL_ID, 
			SQLiteHelper.COL_NAME,
			SQLiteHelper.COL_PROVINCE,
			SQLiteHelper.COL_CODE,
			SQLiteHelper.COL_STATUS};

	public CityDAO(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public City insertCity(City city) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COL_NAME,     city.getName());
		values.put(SQLiteHelper.COL_PROVINCE, city.getProvince());
		values.put(SQLiteHelper.COL_CODE,     city.getCode());
		values.put(SQLiteHelper.COL_STATUS,   city.getStatus());
		long insertId = database.insert(SQLiteHelper.TB_NAME, null, values);
		Cursor cursor = database.query(SQLiteHelper.TB_NAME, allColumns, SQLiteHelper.COL_ID
				+ " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		City newCity = cursorToCity(cursor);
		cursor.close();
		return newCity;
	}
	
	public List<City> queryCityByCode(String code) {
		List<City> cityList = new ArrayList<City>();
		String querySql = "SELECT _id, name, province, code, status FROM cities WHERE code LIKE '%" + code + "%' ORDER BY _id DESC";
		Cursor cursor = database.rawQuery(querySql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			City city = cursorToCity(cursor);
			cityList.add(city);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return cityList;
	}
	
	public List<City> queryCityByName(String name) {
		List<City> cityList = new ArrayList<City>();
		String querySql = "SELECT _id, name, province, code, status FROM cities WHERE name LIKE '%" + name + "%' ORDER BY _id DESC";
		Cursor cursor = database.rawQuery(querySql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			City city = cursorToCity(cursor);
			cityList.add(city);
			cursor.moveToNext();
		}
		cursor.close();
		return cityList;
	}
	
	public List<City> getMyCities() {
		List<City> cityList = new ArrayList<City>();
		String querySql = "SELECT _id, name, province, code, status FROM cities WHERE status = 1 ORDER BY _id DESC";
		Cursor cursor = database.rawQuery(querySql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			City city = cursorToCity(cursor);
			cityList.add(city);
			cursor.moveToNext();
		}
		cursor.close();
		return cityList;
	}
	
	public void updateCityStatus(City city, String status) {
		ContentValues cv = new ContentValues();
		cv.put("status", status);
		database.update(SQLiteHelper.TB_NAME, cv, 
				SQLiteHelper.COL_NAME     + " = '" + city.getName() +"' and "+ 
				SQLiteHelper.COL_PROVINCE + " = '" + city.getProvince() + "'", null);
	}

	private City cursorToCity(Cursor cursor) {
		City city = new City();
		city.setId(cursor.getInt(0));
		city.setName(cursor.getString(1));
		city.setProvince(cursor.getString(2));
		city.setCode(cursor.getString(3));
		city.setStatus(cursor.getString(4));
		return city;
	}
	
}
