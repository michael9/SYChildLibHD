package com.vip.syreadhd;

import java.util.Timer;
import java.util.TimerTask;

import com.vip.syreadhd.R.anim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ImageView img_rainbow, im_star, img_cloud_one, img_cloud_two,
			img_cloud_three, img_cloud_four, img_cloud_five, select_btn,
			read_btn, manage_btn, resource_btn, alounce_btn, img_read_btn_back,
			img_select_btn_back, img_manage_btn_back, img_resource_btn_back,
			img_alounce_btn_back;

	TextView txt_select_btn, txt_manage_btn, txt_resource_btn, txt_read_btn,
			txt_alounce_btn;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 101:
				UIsetAnim2();
				break;
			default:
				break;
			}
		}
	};

	Timer tt = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			handler.sendEmptyMessage(101);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1001:
			if (resultCode == 0) {

				UIsetAnim();
				tt.schedule(task, 10000, 5000);
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		UIidfind();

		Intent intent = new Intent(MainActivity.this, WelcomePage.class);
		this.startActivityForResult(intent, 1001);

	}

	private void UIidfind() {
		im_star = (ImageView) findViewById(R.id.img_star);
		img_rainbow = (ImageView) findViewById(R.id.img_rainbow);
		img_cloud_one = (ImageView) findViewById(R.id.img_cloud_one);
		img_cloud_two = (ImageView) findViewById(R.id.img_cloud_two);
		img_cloud_three = (ImageView) findViewById(R.id.img_cloud_three);
		img_cloud_four = (ImageView) findViewById(R.id.img_cloud_four);
		img_cloud_five = (ImageView) findViewById(R.id.img_cloud_five);
		select_btn = (ImageView) findViewById(R.id.img_select_btn);
		read_btn = (ImageView) findViewById(R.id.img_read_btn);
		manage_btn = (ImageView) findViewById(R.id.img_manage_btn);
		resource_btn = (ImageView) findViewById(R.id.img_resource_btn);
		alounce_btn = (ImageView) findViewById(R.id.img_alounce_btn);
		txt_select_btn = (TextView) findViewById(R.id.txt_select_btn);
		txt_read_btn = (TextView) findViewById(R.id.txt_read_btn);
		txt_resource_btn = (TextView) findViewById(R.id.txt_resource_btn);
		txt_manage_btn = (TextView) findViewById(R.id.txt_manage_btn);
		txt_alounce_btn = (TextView) findViewById(R.id.txt_alounce_btn);
		img_read_btn_back = (ImageView) findViewById(R.id.img_read_btn_back);
		img_resource_btn_back = (ImageView) findViewById(R.id.img_resource_btn_back);
		img_select_btn_back = (ImageView) findViewById(R.id.img_select_btn_back);
		img_manage_btn_back = (ImageView) findViewById(R.id.img_manage_btn_back);
		img_alounce_btn_back = (ImageView) findViewById(R.id.img_alounce_btn_back);
	}

	private void UIsetAnim() {
		im_star.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.star));
		img_rainbow.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.rainbowset));
		img_cloud_one.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_one));
		img_cloud_two.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_two));
		img_cloud_three.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_three));
		img_cloud_four.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_four));
		img_cloud_five.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_five));
		select_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.select_btn));
		read_btn.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.read_btn));
		manage_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.manage_btn));
		resource_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.resource_btn));
		alounce_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.alounce_btn));
		txt_select_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		txt_read_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		txt_resource_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		txt_manage_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		txt_alounce_btn.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		img_read_btn_back.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		img_resource_btn_back.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		img_manage_btn_back.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));
		img_select_btn_back.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.textshow));

		Animation ma = AnimationUtils.loadAnimation(MainActivity.this,
				R.anim.textshow);
		ma.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				UIsetCmd();
			}
		});
		img_alounce_btn_back.startAnimation(ma);
	}

	private void UIsetAnim2() {
		img_cloud_one.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_one_2));
		img_cloud_two.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_two_2));
		img_cloud_three.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_three_2));
		img_cloud_four.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_four_2));
		img_cloud_five.startAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.cloud_five_2));
	}

	View.OnClickListener uiclickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Animation an = AnimationUtils.loadAnimation(MainActivity.this,
					R.anim.scale_out);

			switch (v.getId()) {
			case R.id.img_read_btn:
				img_read_btn_back.startAnimation(an);
				an.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								ReadPage.class);
						MainActivity.this.startActivity(intent);
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);
					}
				});
				break;
			case R.id.img_select_btn:
				img_select_btn_back.startAnimation(an);
				an.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								SelectPage.class);
						MainActivity.this.startActivity(intent);
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);
					}
				});
				break;

			case R.id.img_resource_btn:
				img_resource_btn_back.startAnimation(an);
				an.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								ResourcePage.class);
						MainActivity.this.startActivity(intent);
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);
					}
				});
				break;

			case R.id.img_manage_btn:
				img_manage_btn_back.startAnimation(an);
				an.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								ManagePage.class);
						MainActivity.this.startActivity(intent);
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);
					}
				});
				break;

			case R.id.img_alounce_btn:
				img_alounce_btn_back.startAnimation(an);
				an.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MainActivity.this,
								AlouncePage.class);
						MainActivity.this.startActivity(intent);
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);
					}
				});
				break;

			default:
				break;
			}

		}
	};

	private void UIsetCmd() {
		read_btn.setOnClickListener(uiclickListener);
		select_btn.setOnClickListener(uiclickListener);
		resource_btn.setOnClickListener(uiclickListener);
		manage_btn.setOnClickListener(uiclickListener);
		alounce_btn.setOnClickListener(uiclickListener);
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
