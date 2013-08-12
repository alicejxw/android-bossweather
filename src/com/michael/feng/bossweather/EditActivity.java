package com.michael.feng.bossweather;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.michael.feng.model.City;
import com.michael.feng.tools.CityDAO;

public class EditActivity extends SherlockActivity {

	private ActionBar ab; 
	private ListView cityListView;
	private ListViewAdapter listViewAdapter;
	private boolean inAction = false;
	
	private CityDAO cityDAO;
	private List<City> cityList;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("我的城市");
        
        listMyCities();
    }
    
    // Get my cities from DB where status = 1
 	public void listMyCities() {
 		cityDAO = new CityDAO(this);
 		cityDAO.open();
 		cityList = (List<City>) cityDAO.getMyCities();
 		cityDAO.close();
 		
 		listViewAdapter = new ListViewAdapter(this);
 		cityListView = (ListView) findViewById(android.R.id.list);
 		cityListView.setAdapter(listViewAdapter);
 	}

	protected void refresh(String str) {
//		outputData = insertData.queryData(str);
//		orderListListAdapter = new OrderListListAdapter(this, orderData);
//		orderlistlistView.setAdapter(orderListListAdapter);
//		orderListListAdapter.notifyDataSetChanged();
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
		//inflater.inflate(R.menu.editactivity_itemlist, menu);
		return true;
	}
	
	@Override    
    public boolean onOptionsItemSelected(MenuItem item) {    
        switch(item.getItemId()){
        case android.R.id.home:  
        	finish();
            break; 
        case R.id.newitem:
        	Intent newIntent = new Intent();
        	newIntent.setClass(EditActivity.this, AddActivity.class);
        	startActivity(newIntent);
        	finish();
        	break;
        }    
        return super.onOptionsItemSelected(item);    
    } 
	
	public class ListViewAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		private int listLayout = R.layout.listitem_edit;

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
				holder.optStatus = (TextView) view.findViewById(R.id.optStatus);
				holder.optImg = (ImageView) view.findViewById(R.id.optImg);
				holder.cityName = (TextView) view.findViewById(R.id.cityName);
				holder.deleteImg = (ImageView) view.findViewById(R.id.deleteImg);
				view.setTag(holder);
			} else {
				holder = (ListContent) view.getTag();
			}
			String cityName = cityList.get(position).getName();
			holder.cityName.setText(cityName);
			holder.optImg.setOnClickListener(operatetListener);
			holder.deleteImg.setOnClickListener(deleteImgListener);
			return view;
		}
		
	}

	static class ListContent {
		TextView optStatus;
		ImageView optImg;
		TextView cityName;
		ImageView deleteImg;
	}

	public OnClickListener operatetListener = new OnClickListener() {
		public void onClick(View view) {
			int position = cityListView.getPositionForView((View) view.getParent());
			ImageView deleteImg = (ImageView) cityListView.getChildAt(position).findViewById(R.id.deleteImg);
			ImageView optImg = (ImageView) cityListView.getChildAt(position).findViewById(R.id.optImg);
			
			TextView optStatus = (TextView) cityListView.getChildAt(position).findViewById(R.id.optStatus);
			if("0".equals(optStatus.getText()) && inAction == false) {
				inAction = true;
				Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				Bitmap rotated = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
				optImg.setImageBitmap(rotated);
				optStatus.setText("1");
				deleteImg.setVisibility(View.VISIBLE);
			} else if("1".equals(optStatus.getText()) && inAction == true){
				inAction = false;
				Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
				Matrix matrix = new Matrix();
				matrix.postRotate(180);
				Bitmap rotated = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
				optImg.setImageBitmap(rotated);
				optStatus.setText("0");
				deleteImg.setVisibility(View.GONE);
			} else {
				inAction = false;
				for(int i = 0; i<cityListView.getCount(); i++) {
					deleteImg = (ImageView) cityListView.getChildAt(i).findViewById(R.id.deleteImg);
					optImg    = (ImageView) cityListView.getChildAt(i).findViewById(R.id.optImg);
					optStatus = (TextView) cityListView.getChildAt(i).findViewById(R.id.optStatus);
					deleteImg.setVisibility(View.GONE);
					Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
					Matrix matrix = new Matrix();
					matrix.postRotate(180);
					Bitmap rotated = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
					optImg.setImageBitmap(rotated);
					optStatus.setText("0");
				}
			}
			
		}
	};

	public OnClickListener deleteImgListener = new OnClickListener() {
		public void onClick(View view) {
			int position = cityListView.getPositionForView((View) view.getParent());
			
			// Turn delete button back to invisiable
			ImageView deleteImg = (ImageView) cityListView.getChildAt(position).findViewById(R.id.deleteImg);
			deleteImg.setVisibility(View.GONE);
			
			// Turn delete icon back to normal
			ImageView optImg = (ImageView) cityListView.getChildAt(position).findViewById(R.id.optImg);
			TextView optStatus = (TextView) cityListView.getChildAt(position).findViewById(R.id.optStatus);
			Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
			Matrix matrix = new Matrix();
			matrix.postRotate(180);
			Bitmap rotated = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
			optImg.setImageBitmap(rotated);
			optStatus.setText("0");
			
			// Remove item and refresh list view
			cityList.remove(position);
			listViewAdapter.notifyDataSetChanged();
		}
	};
	
}


