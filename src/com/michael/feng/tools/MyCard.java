package com.michael.feng.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import com.michael.feng.bossweather.R;

public class MyCard extends Card {

	public MyCard(String title, String desc, int image){
		super(title, desc, image);
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_picture, null);

		((TextView) view.findViewById(R.id.title)).setText(title);
		((TextView) view.findViewById(R.id.description)).setText(desc);
		((ImageView) view.findViewById(R.id.imageView1)).setImageResource(image);
		return view;
	}

}
