package com.michael.feng.bossweather;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.fima.cardsui.objects.Card;
import com.fima.cardsui.objects.Card.OnCardSwiped;
import com.fima.cardsui.views.CardUI;
import com.michael.feng.model.City;
import com.michael.feng.tools.CityDAO;
import com.michael.feng.tools.ConvertUtil;
import com.michael.feng.tools.MyCard;
import com.michael.feng.utils.YahooWeather4a.WeatherInfo;
import com.michael.feng.utils.YahooWeather4a.YahooWeatherInfoListener;
import com.michael.feng.utils.YahooWeather4a.YahooWeatherUtils;

public class MainActivity extends SherlockActivity implements YahooWeatherInfoListener {

	private String curWeather = ""; 
	private String curCity = "";
	private CardUI mCardView; 
	private List<City> myCityList;
	private CityDAO cityDAO;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Display prompt info for loading
        mCardView = (CardUI) findViewById(R.id.cardsview);
        mCardView.setSwipeable(false);
        MyCard loadCard = new MyCard("当前还未添加任何城市", "\n加号：添加一个城市\n\n铅笔：编辑已有城市", R.drawable.sunny);
        mCardView.addCard(loadCard, true);
        
        cityDAO = new CityDAO(this);
        cityDAO.open();
        myCityList = cityDAO.getMyCities();
        cityDAO.close();
        if(myCityList.isEmpty()) {
        	// Then get location and show
        	//YahooWeatherUtils yahooWeatherUtils = YahooWeatherUtils.getInstance();
            //yahooWeatherUtils.queryYahooWeather(getApplicationContext(), "Shanghai", this);
            return;
        } 
        
        mCardView.clearCards();
        YahooWeatherUtils yahooWeatherUtils = YahooWeatherUtils.getInstance();
        for(City city: myCityList) {
        	// init CardView
            yahooWeatherUtils.queryYahooWeather(getApplicationContext(), city.getName(), this);
        }
        
    }

	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
        if(weatherInfo != null) {
        	curWeather = "";
        	String localWeatherDate  = ConvertUtil.dateToLocal();//weatherInfo.getCurrentConditionDate());
        	String localWeatherText  = ConvertUtil.weatherToLocal(weatherInfo.getCurrentText());
        	String localWeatherFore1 = ConvertUtil.weatherToLocal(weatherInfo.getForecast1Text());
        	String localWeatherFore2 = ConvertUtil.weatherToLocal(weatherInfo.getForecast2Text());
			curWeather += localWeatherDate + "\n\n" +
						  weatherInfo.getCurrentTempC() + "℃，  " +  localWeatherText + "\n\n" +
						  "明天: " + weatherInfo.getForecast1TempLowC()  + "~" + weatherInfo.getForecast1TempHighC() + "℃， " + localWeatherFore1 + "\n"  +
						  "后天: " + weatherInfo.getForecast2TempHighC() + "~" + weatherInfo.getForecast2TempHighC() + "℃， " + localWeatherFore2;
			final int curWeatherIcon = ConvertUtil.getIconByWeather(localWeatherText);
			MyCard shCard = new MyCard(weatherInfo.getLocationCity(), curWeather, curWeatherIcon);
	        mCardView.addCard(shCard);
	        shCard.setOnCardSwipedListener(new OnCardSwiped() {
				public void onCardSwiped(Card card, View layout) {
					Log.d("card swipe","swiped");
				}
	        });
	        shCard.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					Log.d("card click","click");					
				}
	        });
	        mCardView.refresh();
	        
			//LoadWebImagesTask task = new LoadWebImagesTask();
			//task.execute(weatherInfo.getCurrentConditionIconURL());
        } else {
        	Toast.makeText(getApplicationContext(), "Sorry, no result returned", Toast.LENGTH_SHORT).show();
        }
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
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.mainactivity_itemlist, menu);
		return true;
	}
	
	@Override    
    public boolean onOptionsItemSelected(MenuItem item) {    
        switch(item.getItemId()){
        case R.id.edititem:  
        	Intent intent = new Intent();
        	intent.setClass(MainActivity.this, EditActivity.class);
        	startActivity(intent);
            break; 
//        case R.id.shareItem:
//            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
//            break;
        case R.id.newitem:
        	Intent addIntent = new Intent();
        	addIntent.setClass(MainActivity.this, AddActivity.class);
        	startActivity(addIntent);
        	break;
          
        }    
        return super.onOptionsItemSelected(item);    
    } 
	
}


