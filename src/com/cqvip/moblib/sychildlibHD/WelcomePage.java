package com.cqvip.moblib.sychildlibHD;

import java.util.Timer;
import java.util.TimerTask;

import com.cqvip.moblib.sychildlibHD.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WelcomePage extends Activity {
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			 case 101:
				 WelcomePage.this.setResult(0);
				WelcomePage.this.finish();
				 overridePendingTransition( R.anim.alpha_in,R.anim.left_out);  
				 break;
				 default :
					 break;
			}
		}
	};
	
	Timer tt=new Timer();
	TimerTask task = new TimerTask() {   
		public void run() {   
			 handler.sendEmptyMessage(101);
		}   
		}; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomepage);
		tt.schedule(task, 1500);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tt.cancel();
	}
}
