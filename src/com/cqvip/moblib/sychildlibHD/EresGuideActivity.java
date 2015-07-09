package com.cqvip.moblib.sychildlibHD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

public class EresGuideActivity extends Activity {
	View eres_guide_btn01,eres_guide_btn02,eres_guide_btn03;
	AnimationSet am01,am02,am03;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eres_guide);
		eres_guide_btn01=(View)findViewById(R.id.eres_guide_btn01);
		eres_guide_btn02=(View)findViewById(R.id.eres_guide_btn02);
		eres_guide_btn03=(View)findViewById(R.id.eres_guide_btn03);

		am01= new AnimationSet(true);
//		AlphaAnimation a=new AlphaAnimation(0,1);
		ScaleAnimation sa1=new ScaleAnimation(0f,1f,0f,1f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa1.setInterpolator(new DecelerateInterpolator());
//		am01.addAnimation(a);
		am01.addAnimation(sa1);
		am01.setDuration(500);
		am01.setStartOffset(100);

		am02=new AnimationSet(true);
		ScaleAnimation sa2=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa2.setInterpolator(new DecelerateInterpolator());
		am02.addAnimation(sa2);
		am02.setDuration(500);
		am02.setStartOffset(800);

		am03=new AnimationSet(true);
		ScaleAnimation sa3=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		sa3.setInterpolator(new DecelerateInterpolator());
		am03.addAnimation(sa3);
		am03.setDuration(500);
		am03.setStartOffset(1300);

		eres_guide_btn01.setOnClickListener(btnclick);
		eres_guide_btn02.setOnClickListener(btnclick);
		eres_guide_btn03.setOnClickListener(btnclick);
	}

	@Override
	protected void onResume() {
		super.onResume();
		eres_guide_btn01.setAnimation(am01);
		eres_guide_btn02.setAnimation(am02);
		eres_guide_btn03.setAnimation(am03);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.scale_s_in,R.anim.scale_s_out);
		}
		return super.onKeyDown(keyCode, event);
	}

	OnClickListener btnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(EresGuideActivity.this,WebActivity.class);
			
			switch (v.getId()) {
			case R.id.eres_guide_btn01:
				intent.putExtra("urlstr", "http://shaoer.wz.waplexiang.net");
				EresGuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
			case R.id.eres_guide_btn02:
				intent.putExtra("urlstr", "http://r.apabi.com/r2k/wx/b/cl/swhy");
				EresGuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
			case R.id.eres_guide_btn03:
				intent.putExtra("urlstr", "http://r.apabi.com/r2k/wx/p/pl/swhy");
				EresGuideActivity.this.startActivity(intent);
				overridePendingTransition(R.anim.scale_b_in,R.anim.scale_b_out);
				break;
			

			default:
				break;
			}
		}
	};
}
