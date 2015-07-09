package com.cqvip.moblib.sychildlibHD;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cqvip.moblib.sychildlibHD.R;

public class WebActivity extends Activity{
	
	WebView web01;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		web01=(WebView)findViewById(R.id.web01);
//		web01.setWebViewClient(new WebViewClient(){
//       	  @Override
//             public void onPageFinished(WebView view,String url)
//             {
////       		  if(customProgressDialog!=null&&customProgressDialog.isShowing()){
////       		  customProgressDialog.dismiss();
//       		  }
//             }
//            });
       	
		web01.loadUrl(getIntent().getStringExtra("urlstr"));
      WebSettings webSettings = web01.getSettings();
      webSettings.setJavaScriptEnabled(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.scale_s_in,R.anim.scale_s_out);
		}
		return super.onKeyDown(keyCode, event);
	}

}
