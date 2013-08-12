package com.michael.feng.bossweather;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.michael.feng.model.City;
import com.michael.feng.tools.CityDAO;

public class AddActivity extends SherlockActivity {

	private ActionBar ab; 
	private EditText inputETxt;
	private Button cancelBtn;
	private List<City> cityList;
	private ListView cityListView;
	private ListViewAdapter listViewAdapter;
	private List<City> queryResult;
	private CityDAO cityDAO;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("添加城市");
        
        cityList = new ArrayList<City>();
        cityDAO = new CityDAO(this);
        cityListView = (ListView) findViewById(android.R.id.list);
        listViewAdapter = new ListViewAdapter(this);
        cityListView.setAdapter(listViewAdapter);
        
        //cityList.add(new City("Shanghai", "Shanghai", "SH", "1"));
        //cityList.add(new City("Hangzhou", "Zhejiang", "HZ", "1"));
        //cityList.add(new City("Beijing", "Beijing", "BJ", "1"));
        //cityList.add(new City("Chongqin", "Sichuan", "CQ", "1"));
        
        cancelBtn = (Button)   findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
        });
        
        inputETxt = (EditText) findViewById(R.id.inputETxt);
        inputETxt.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable arg0) {
				if(null == inputETxt.getText() || "".equals(inputETxt.getText().toString().trim())) {
					cleanList();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int count, int after) {
				String str = s.toString();
			    Log.d("ontextchanged", str);
			    if(!"".equals(str.trim())) {
			    	refreshList(cityDAO, str);
			    }
			}
        });
        
    }

	protected void refreshList(CityDAO cityDAO, String str) {
		cityDAO.open();
		cityList = cityDAO.queryCityByCode(str);
		cityDAO.close();

		listViewAdapter.notifyDataSetChanged();
		cityListView.setAdapter(listViewAdapter);
	}
	
	protected void cleanList() {
		cityList.clear();
		listViewAdapter.notifyDataSetChanged();
        cityListView.setAdapter(listViewAdapter);
	}

	class LoadWebImagesTask extends AsyncTask<String, Void, Bitmap[]> {

		@Override
		protected Bitmap[] doInBackground(String... params) {
			Bitmap[] res = new Bitmap[3];
//			res[0] = ImageUtils.getBitmapFromWeb(params[0]);
			return res;
		}

		@Override
		protected void onPostExecute(Bitmap[] results) {
			super.onPostExecute(results);
//			ivWeather0.setImageBitmap(results[0]);
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//MenuInflater inflater = getSupportMenuInflater();
		//inflater.inflate(R.menu.mainactivity_itemlist, menu);
		return true;
	}
	
	@Override    
    public boolean onOptionsItemSelected(MenuItem item) {    
        switch(item.getItemId()){
        case android.R.id.home:  
        	finish();
            break; 
        }    
        return super.onOptionsItemSelected(item);    
    } 
	
	public class ListViewAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		private int listLayout = R.layout.listitem_add;

		public ListViewAdapter(Context con) {
			mInflater = LayoutInflater.from(con);
		}

		public void setListLayout(int layoutId) {
			listLayout = layoutId;
		}

		public int getCount() {
			return cityList.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ListContent holder;
			View view = convertView;
			if (view == null) {
				view = mInflater.inflate(listLayout, null);
				holder = new ListContent();
				holder.cityName = (TextView) view.findViewById(R.id.cityName);
				view.setTag(holder);
			} else {
				holder = (ListContent) view.getTag();
			}
			City city = cityList.get(position);
			String cityName = city.getName() + "，" + city.getProvince();
			holder.cityName.setText(cityName);
			holder.cityName.setOnClickListener(cityNameListener);
			return view;
		}
	}

	static class ListContent {
		TextView cityName;
	}

	public OnClickListener cityNameListener = new OnClickListener() {
		public void onClick(View view) {
			int position = cityListView.getPositionForView((View) view.getParent());
			City city = cityList.get(position);
			
			// Update city status to 1 - means add to my city list
			cityDAO.open();
			cityDAO.updateCityStatus(city, "1");
			cityDAO.close();
			
			finish();
			Intent intent = new Intent();
			intent.setClass(AddActivity.this, EditActivity.class);
			startActivity(intent);
		}
	};

}


