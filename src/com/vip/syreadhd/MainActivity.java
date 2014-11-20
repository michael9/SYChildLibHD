package com.vip.syreadhd;

import com.vip.syreadhd.R.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	ImageView img_rainbow,im_star,
	img_cloud_one,img_cloud_two,img_cloud_three,img_cloud_four,img_cloud_five,
	select_btn,read_btn,manage_btn,resource_btn,alounce_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		UIidfind();
		UIsetAnim();
		
		alounce_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIsetAnim();
			}
		});
		
		manage_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIsetAnim2();
			}
		});
	}
	
	private void UIidfind()
	{
		im_star=(ImageView)findViewById(R.id.img_star);
		img_rainbow=(ImageView)findViewById(R.id.img_rainbow);
		img_cloud_one=(ImageView)findViewById(R.id.img_cloud_one);
		img_cloud_two=(ImageView)findViewById(R.id.img_cloud_two);
		img_cloud_three=(ImageView)findViewById(R.id.img_cloud_three);
		img_cloud_four=(ImageView)findViewById(R.id.img_cloud_four);
		img_cloud_five=(ImageView)findViewById(R.id.img_cloud_five);
		select_btn=(ImageView)findViewById(R.id.img_select_btn);
		read_btn=(ImageView)findViewById(R.id.img_read_btn);
		manage_btn=(ImageView)findViewById(R.id.img_manage_btn);
		resource_btn=(ImageView)findViewById(R.id.img_resource_btn);
		alounce_btn=(ImageView)findViewById(R.id.img_alounce_btn);
	}
	
	private void UIsetAnim()
	{
		im_star.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.star));
		img_rainbow.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.rainbowset));
		img_cloud_one.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud_one));
		img_cloud_two.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud_two));
		img_cloud_three.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud_three));
		img_cloud_four.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud_four));
		img_cloud_five.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud_five));
		select_btn.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.select_btn));
		read_btn.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.read_btn));
		manage_btn.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.manage_btn));
		resource_btn.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.resource_btn));
		alounce_btn.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.alounce_btn));
	}
	private void UIsetAnim2()
	{
		img_cloud_one.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.cloud_one_2));
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
