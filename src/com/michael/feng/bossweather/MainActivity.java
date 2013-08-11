package com.michael.feng.bossweather;

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
import com.michael.feng.tools.ConvertUtil;
import com.michael.feng.tools.MyCard;
import com.michael.feng.utils.YahooWeather4a.WeatherInfo;
import com.michael.feng.utils.YahooWeather4a.YahooWeatherInfoListener;
import com.michael.feng.utils.YahooWeather4a.YahooWeatherUtils;

public class MainActivity extends SherlockActivity implements YahooWeatherInfoListener {

	private String curWeather = ""; 
	private CardUI mCardView; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Display prompt info for loading
        mCardView = (CardUI) findViewById(R.id.cardsview);
        mCardView.setSwipeable(false);
        MyCard loadCard = new MyCard("Getting your location...", "Loading...", R.drawable.url1);
        mCardView.addCard(loadCard, true);

        Log.d("YWeatherGetter4a", "onCreate");
        
        YahooWeatherUtils yahooWeatherUtils = YahooWeatherUtils.getInstance();
        yahooWeatherUtils.queryYahooWeather(getApplicationContext(), "Shanghai", this);
        
    }

	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
        if(weatherInfo != null) {
        	//TextView tv = (TextView) findViewById(R.id.textview_title);
			//tv.setText(weatherInfo.getTitle() + "\n"
			//		+ weatherInfo.getLocationCity() + ", "
			//		+ weatherInfo.getLocationCountry());
			//tvWeather0 = (TextView) findViewById(R.id.textview_weather_info_0);
			curWeather += 	   ConvertUtil.dateToLocal(weatherInfo.getCurrentConditionDate()) + "\n\n" +
						       + weatherInfo.getCurrentTempC() + "℃，  " +  ConvertUtil.weatherToLocal(weatherInfo.getCurrentText()) + "\n\n" +
						       "明天: " + weatherInfo.getForecast1TempLowC()  + "~" + weatherInfo.getForecast1TempHighC() + "℃， " + ConvertUtil.weatherToLocal(weatherInfo.getForecast1Text()) + "\n"  +
						       "后天: " + weatherInfo.getForecast2TempHighC() + "~" + weatherInfo.getForecast2TempHighC() + "℃， " + ConvertUtil.weatherToLocal(weatherInfo.getForecast2Text());
			
			//ivWeather0 = (ImageView) findViewById(R.id.imageview_weather_info_0);
			
			LoadWebImagesTask task = new LoadWebImagesTask();
			task.execute(weatherInfo.getCurrentConditionIconURL());
			
			// init CardView
			mCardView.clearCards();
			
			MyCard shCard = new MyCard("Shanghai", curWeather, R.drawable.url1);
	        mCardView.addCardToLastStack(shCard);
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

	        MyCard hzCard = new MyCard("Hangzhou", "Hi Feng", R.drawable.url2);
	        mCardView.addCard(hzCard);

	        MyCard bjCard = new MyCard("Beijing", "BEIJING, BEIJING", R.drawable.url3);
	        mCardView.addCard(bjCard);
	        
	        MyCard cqCard = new MyCard("Chongqin", "PAULGEV5", R.drawable.url1);
	        mCardView.addCard(cqCard);

	        mCardView.refresh();
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
        case R.id.shareItem:
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
            break;
        case R.id.newitem:
        	Intent addIntent = new Intent();
        	addIntent.setClass(MainActivity.this, AddActivity.class);
        	startActivity(addIntent);
        	break;
          
        }    
        return super.onOptionsItemSelected(item);    
    } 
	
}


