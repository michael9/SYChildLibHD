package com.cqvip.moblib.sychildlibHD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BorrowActivity extends Activity {
	
	private ListView listview;
	private Map<String, String> gparams;
	private RequestQueue mQueue;
	private ErrorListener el;
	private CustomProgressDialog customProgressDialog;
	private RelativeLayout noborrow_rl;
	private List<BorrowBook>  lists;
	private BorrowBookAdapter adapter;
	private TextView borrow_readername;
	private Button borrow_logout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrow);
		mQueue=Volley.newRequestQueue(this);
		customProgressDialog=CustomProgressDialog.createDialog(this);
		listview = (ListView)findViewById(R.id.borrow_list);
		noborrow_rl = (RelativeLayout) findViewById(R.id.noborrow_rl);
		borrow_readername=(TextView)findViewById(R.id.borrow_readername);
		borrow_readername.setText("读者:"+GlobleData.readername);
		borrow_logout=(Button)findViewById(R.id.borrow_logout);
		borrow_logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences localUsers= getSharedPreferences("mobliereader", MODE_PRIVATE);
				Editor editor=localUsers.edit();
				editor.putBoolean("islogin",false);
				editor.commit();
				GlobleData.islogin=false;
				finish();
			}
		});
		getlist();
	}
	
	 private void getlist() {
		  customProgressDialog.show();
		  gparams=new HashMap<String, String>();
		  gparams.put("libid", GlobleData.LIBIRY_ID);	  
		  gparams.put("userid", GlobleData.userid);	  
			requestVolley(GlobleData.SERVER_URL
					+ "/library/user/borrowlist.aspx", borrowlist_ls,
					Method.POST);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.scale_s_in,R.anim.scale_s_out);
		}
		return super.onKeyDown(keyCode, event);
	}


 	  private Response.Listener<String> cl_renew = new Response.Listener<String>() {
	          @Override
	          public void onResponse(String response) {
	        	  if(customProgressDialog!=null&&customProgressDialog.isShowing())
	        		customProgressDialog.dismiss();
	    			try {
	    				ShortBook result = new ShortBook(Task.TASK_BOOK_RENEW,response);
	    				if(result!=null){
	    				if(result.getSucesss().equals("true")){
	    				for(int i=0;i<lists.size();i++){
	    					if(result.getId().equals(lists.get(i).getBarcode())){
	    						lists.get(i).setRenew(1);
	    						lists.get(i).setReturndate(result.getDate()+getResources().getString(R.string.alreadyrenew));
	    						adapter.notifyDataSetChanged();
	    						break;
	    					}
	    				  }
	    				}
	    				Tool.ShowMessages(BorrowActivity.this, result.getMessage());
	    			}
	    			} catch (Exception e) {
	    				onError(2);
	    				return;
	    			}

	          }
	      };
	 
	 private Listener<String> borrowlist_ls = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				if(customProgressDialog!=null&&customProgressDialog.isShowing())
				customProgressDialog.dismiss();
				try {
//					List<BorrowBook>lists = BorrowBook.formList(response);
					lists = BorrowBook.formList(response);
					if(lists==null||lists.isEmpty()){
						listview.setVisibility(View.GONE);
						noborrow_rl.setVisibility(View.VISIBLE);
					}else {
						listview.setVisibility(View.VISIBLE);
						noborrow_rl.setVisibility(View.GONE);
						adapter = new BorrowBookAdapter(BorrowActivity.this,lists,mQueue,cl_renew,el);
						listview.setAdapter(adapter);
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
				mys.setRetryPolicy(HttpUtils.setTimeout());mQueue.add(mys);
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
