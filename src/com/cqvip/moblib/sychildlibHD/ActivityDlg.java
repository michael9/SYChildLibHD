package com.cqvip.moblib.sychildlibHD;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class ActivityDlg extends Activity  {

	private RelativeLayout login_layout;
	private LinearLayout msg_box_layout;
	private EditText log_in_passwords;
	private AutoCompleteTextView log_in_username;
	private Button login_btn, cancel_btn,ok_btn;
//	private LinearLayout login_status_ll;
//	private MUserDao dao;
	private TextView msg_box_txt;
	private Context context;
	private Map<String, String> gparams;
	private CustomProgressDialog customProgressDialog;
	private RequestQueue mQueue;
	private ErrorListener el;
	private Editor editor;
	private SharedPreferences localUsers;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dlg);
		context = this;
		mQueue=Volley.newRequestQueue(this);
		msg_box_layout=(LinearLayout)findViewById(R.id.msg_box_layout);
		login_layout = (RelativeLayout) findViewById(R.id.log_in_layout);
//		login_status_ll = (LinearLayout) findViewById(R.id.login_status);
		msg_box_layout.setVisibility(View.GONE);
		login_layout.setVisibility(View.GONE);
		customProgressDialog = CustomProgressDialog.createDialog(this);
//		login_status_ll.setVisibility(View.GONE);
	
		switch (getIntent().getIntExtra("ACTIONID", 0)) {
		
		case 0:
			showmsg();
			break;
		case 4:
		case 5:
		case 7:
		case 8:
			login() ;
			break;
		}
	}

	String name, pwd;

	private void login() {
		localUsers = getSharedPreferences("mobliereader", MODE_PRIVATE);
//		editor = localUsers.edit();
//		String uname = localUsers.getString("readercardid", "");
		login_layout.setVisibility(View.VISIBLE);
		log_in_username = (AutoCompleteTextView) findViewById(R.id.log_in_username);
		log_in_passwords = (EditText) findViewById(R.id.log_in_passwords);
//		login_status_ll = (LinearLayout) findViewById(R.id.login_status);
		login_btn = (Button)findViewById(R.id.login_ok_btn);
		cancel_btn = (Button)findViewById(R.id.login_cancel_btn);
		log_in_username.setText(localUsers.getString("userid", ""));
//		dao = new MUserDao(this);
				
		login_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!validate(log_in_username.getText().toString().trim(),getResources().getString(R.string.need_username))){
					return;
				}
				if(!validate(log_in_passwords.getText().toString().trim(),getResources().getString(R.string.need_pwd))){
					return;
				}
				name = log_in_username.getText().toString().trim();
				pwd = log_in_passwords.getText().toString();
				
				customProgressDialog.show();
				gparams=new HashMap<String, String>();			
				gparams.put("username", name);
				gparams.put("password", pwd);
				gparams.put("libid", GlobleData.LIBIRY_ID);
				requestVolley(GlobleData.SERVER_URL + "/library/user/login.aspx",
						back_ls, Method.POST);
			
//				login_layout.setVisibility(View.GONE);
//				login_status_ll.setVisibility(View.VISIBLE);
			}

			private boolean validate(String trim,String msg) {
				if(TextUtils.isEmpty(trim)){
					Tool.ShowMessages(context, msg);
					return false;
				}else{
					
					return true;
				}
			}
		});
		
		cancel_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				winexit(1);
			}
		});
	}

	private void showmsg(){
		msg_box_layout.setVisibility(View.VISIBLE);
		 cancel_btn=(Button)findViewById(R.id.dlg_cancel_btn);
		 ok_btn=(Button)findViewById(R.id.dlg_ok_btn);
		 msg_box_txt=(TextView)findViewById(R.id.msg_box_txt);
		 
		 cancel_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				winexit(1);
			}
		});
		 ok_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				winexit(0);
			}
		});
		 
		 msg_box_txt.setText(getIntent().getStringExtra("MSGBODY"));
		 if(getIntent().getIntExtra("BTN_CANCEL", 0)==0){
			 cancel_btn.setVisibility(View.GONE);			 
		 }
				 
	}
	
//	@Override
//	public void init() {
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_dropdown_item_1line, new String[] {
//						"0441200001098", "0440061012345" });
//		log_in_username.setThreshold(0);
//		log_in_username.setAdapter(adapter);

//		if (Tool.checkNetWork(this)) {
//			if (!ManagerService.isrun) {
//				ManagerService.isrun = true;
//				Intent it = new Intent(this, ManagerService.class);
//				this.startService(it);
//			}
//		}
//	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==4)
		{
			winexit(1);
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	void winexit(int flag)
	{
		ActivityDlg.this.setResult(flag);
		finish();
	}
	
	private Listener<String> back_ls = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			if(customProgressDialog!=null&&customProgressDialog.isShowing())
			customProgressDialog.dismiss();
			try {
				Result res = new Result(response);
				if (res.getSuccess()) {
					GlobleData.islogin = true;
					User user = new User(response);
					GlobleData.userid = user.getCardno();
					GlobleData.readerid = user.getReaderno();
					GlobleData.cqvipid = user.getVipuserid()+"";
					GlobleData.readername=user.getName();
					localUsers = getSharedPreferences("mobliereader", MODE_PRIVATE);
					editor = localUsers.edit();
					editor.putString("userid", user.getCardno());
					editor.putString("readerid",user.getReaderno());
					editor.putString("cqvipid",user.getVipuserid()+"");
					editor.putString("name",user.getName());
					editor.putBoolean("islogin",true);
					editor.commit();
					winexit(0);
				} else {
					GlobleData.islogin = false;
				
					Tool.ShowMessages(ActivityDlg.this, res.getMessage());
				}
			} catch (Exception e) {
			   onError(2);
				return;
			}
		}
	};

	private void requestVolley(String addr, Listener<String> bl, int method) {
		try {
			StringRequest mys = new StringRequest(method, addr, bl, el) {

				protected Map<String, String> getParams()
						throws com.android.volley.AuthFailureError {
					return gparams;
				};
			};
			mys.setRetryPolicy(HttpUtils.setTimeout());
			mQueue.add(mys);
			mQueue.start();
		} catch (Exception e) {
			onError(2);
		}
	}
	
	public void onError(int a) {
		if (customProgressDialog != null && customProgressDialog.isShowing()) {
			customProgressDialog.dismiss();
		}
		if (a == 1) {// 
			Tool.ShowMessages(this, getResources()
					.getString(R.string.loginfail));
		} else if (a == 2) {// 
			Tool.ShowMessages(this, getResources()
					.getString(R.string.loadfail));
		} else if (a == 3) {// 
			Tool.ShowMessages(this, getResources()
					.getString(R.string.renewfail));
		} else if (a == 4) {// 
			Tool.ShowMessages(this, getResources()
					.getString(R.string.modifyfail));
		}else if (a == 5) {// 
			Tool.ShowMessages(this, getResources()
					.getString(R.string.favorfail));
		}
	}
}
