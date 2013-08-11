package com.michael.feng.bossweather;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.fima.cardsui.views.CardUI;

public class EditActivity extends SherlockActivity {

	private ActionBar ab; 
	private List cityNames;
	private ListView cityListView;
	private ListViewAdapter listViewAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        
        cityNames = new ArrayList();
        cityNames.add("Shanghai");
        cityNames.add("Hangzhou");
        cityNames.add("Beijing");
        cityNames.add("Chongqin");
        
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
			return cityNames.size();
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
			String cityName = (String) cityNames.get(position);
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
			if("0".equals(optStatus.getText())) {
				Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				Bitmap rotated = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
				optImg.setImageBitmap(rotated);
				optStatus.setText("1");
				deleteImg.setVisibility(View.VISIBLE);
			} else {
				Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
				Matrix matrix = new Matrix();
				matrix.postRotate(180);
				Bitmap rotated = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
				optImg.setImageBitmap(rotated);
				optStatus.setText("0");
				deleteImg.setVisibility(View.GONE);
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
			cityNames.remove(position);
			listViewAdapter.notifyDataSetChanged();
		}
	};
	
}

