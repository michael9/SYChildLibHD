package com.cqvip.moblib.sychildlibHD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class GuideActivity extends Activity {

	View guide_btn01, guide_btn02, guide_btn03, guide_btn04,guide_btn05;

	AnimationSet am01,am02,am03,am04,am05;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		guide_btn01 = (View) findViewById(R.id.guide_btn01);
		guide_btn02 = (View) findViewById(R.id.guide_btn02);
		guide_btn03 = (View) findViewById(R.id.guide_btn03);
		guide_btn04 = (View) findViewById(R.id.guide_btn04);
		guide_btn05 = (View) findViewById(R.id.guide_btn05);

		am01= new AnimationSet(true);
//		AlphaAnimation a=new AlphaAnimation(0,1);
		ScaleAnimation sa1=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa1.setInterpolator(new AnticipateOvershootInterpolator());
//		am01.addAnimation(a);
		am01.addAnimation(sa1);
		am01.setDuration(500);
		am01.setStartOffset(100);

		am02=new AnimationSet(true);
		ScaleAnimation sa2=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa2.setInterpolator(new AnticipateOvershootInterpolator());
		am02.addAnimation(sa2);
		am02.setDuration(500);
		am02.setStartOffset(1800);

		am03=new AnimationSet(true);
		ScaleAnimation sa3=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa3.setInterpolator(new AnticipateOvershootInterpolator());
		am03.addAnimation(sa3);
		am03.setDuration(500);
		am03.setStartOffset(1300);

		am04=new AnimationSet(true);
		ScaleAnimation sa4=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa4.setInterpolator(new AnticipateOvershootInterpolator());
		am04.addAnimation(sa4);
		am04.setDuration(500);
		am04.setStartOffset(800);
		
		am05=new AnimationSet(true);
		ScaleAnimation sa5=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa5.setInterpolator(new AnticipateOvershootInterpolator());
		am05.addAnimation(sa5);
		am05.setDuration(500);
		am05.setStartOffset(800);

		guide_btn01.setOnClickListener(btnclick);
		guide_btn02.setOnClickListener(btnclick);
		guide_btn03.setOnClickListener(btnclick);
		guide_btn04.setOnClickListener(btnclick);
		guide_btn05.setOnClickListener(btnclick);


	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.scale_s_in,R.anim.scale_s_out);
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
		guide_btn01.startAnimation(am01);
		guide_btn02.startAnimation(am02);
		guide_btn03.startAnimation(am03);
		guide_btn04.startAnimation(am04);
		guide_btn05.startAnimation(am05);
	}

	OnClickListener btnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(GuideActivity.this,WebActivity.class);
			
			switch (v.getId()) {
			case R.id.guide_btn01:
				intent.putExtra("urlstr", "http://weixin.mobcld.com/webcld/syse/htmls/syser_bgjj.html");
				GuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
			case R.id.guide_btn02:
				intent.putExtra("urlstr", "http://weixin.mobcld.com/webcld/syse/htmls/syser_fwxm.html");
				GuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
			case R.id.guide_btn03:
				intent.putExtra("urlstr", "http://weixin.mobcld.com/webcld/syse/htmls/syser_jyxz.html");
				GuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
			case R.id.guide_btn04:
				intent.putExtra("urlstr", "http://weixin.mobcld.com/webcld/syse/htmls/syser_gcfb.html");
				GuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
				
			case R.id.guide_btn05:
				intent.putExtra("urlstr", "http://weixin.mobcld.com/webcld/syse/htmls/syser_kfsj.html");
				GuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;

			default:
				break;
			}
		}
	};

}
