package com.cqvip.moblib.sychildlibHD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cqvip.moblib.sychildlibHD.DropDownListView;
import com.cqvip.moblib.sychildlibHD.BookAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BookSreachActivity extends Activity {
	private static BitmapCache cache;
	private EditText edit;
	private BookAdapter adapter;
	private Context context;
	private int page = 1;
	private DropDownListView listview;
    private ImageView btn_search;
    private RelativeLayout searchbar;
    private String key;
    private Map<String, String> gparams;
    private final int GETFIRSTPAGE = 1;
	private final int GETNEXTPAGE = 2;
	private RequestQueue mQueue;
	private ErrorListener el;
	private CustomProgressDialog customProgressDialog;
	private static final int DEFAULT_COUNT=6;
	private String search_condition;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_sreach);
		context = this;
		mQueue=Volley.newRequestQueue(this);
		customProgressDialog=CustomProgressDialog.createDialog(this);
		listview = (DropDownListView) findViewById(R.id.search_res_lv);
		searchbar=(RelativeLayout)findViewById(R.id.searchbar);
		edit=(EditText)searchbar.findViewById(R.id.et_search);
		btn_search=(ImageView)searchbar.findViewById(R.id.im_seach_icon);
		btn_search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				hideKeybord();
				key = edit.getText().toString().trim();
				if (!Tool.checkNetWork(context)) {
					return;
				}
//				if (TextUtils.isEmpty(key)) {
//					Tool.ShowMessages(context,
//							getResources()
//									.getString(R.string.tips_nosearch_key));
//					return;
//				}

				customProgressDialog.show();
				page = 1;
				getHomePage(key, GETFIRSTPAGE, DEFAULT_COUNT, GETFIRSTPAGE,
						GlobleData.QUERY_TITLE);

			}

		});
		edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				 if (edit.getText().toString().isEmpty()) {
				 return true;
				 }
				 key = edit.getText().toString().trim();

				 hideKeybord();

				 if (!Tool.checkNetWork(context)) {
				 return false;
				 }

				 customProgressDialog.show();
				 page = 1;
				 getHomePage(key, page, DEFAULT_COUNT, GETFIRSTPAGE,
							GlobleData.QUERY_TITLE);
				 return true;
				 }
		});

		listview.setOnBottomListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				page = page + 1;
				getHomePage(key, page, DEFAULT_COUNT, GETFIRSTPAGE,
							GlobleData.QUERY_TITLE);
			}
		});
		
	
	
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Book book = adapter.getLists().get(arg2);
				Intent intent=new Intent(BookSreachActivity.this,BookDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("book", book);
				intent.putExtra("detaiinfo", bundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			finish();
			overridePendingTransition(R.anim.scale_s_in,R.anim.scale_s_out);
		}
		return super.onKeyDown(keyCode, event);
	}

	private void hideKeybord() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
		}
	}

	private void getHomePage(String key, int page, int count, int type,
			String field) {
		gparams = new HashMap<String, String>();
		gparams.put("keyword", key);
		gparams.put("libid", GlobleData.LIBIRY_ID);
		gparams.put("tables", GlobleData.QUERY_TABLE);
		gparams.put("curpage", "" + page);
		gparams.put("perpage", "" + count);
		gparams.put("field", field);

		if (page == GETFIRSTPAGE) {
			if (listview.getFooterViewsCount() == 0) {
				listview.addfootview();
			}
			requestVolley(GlobleData.SERVER_URL
					+ "/library/bookquery/search.aspx", backlistener,
					Method.POST);
		} else {
			requestVolley(GlobleData.SERVER_URL
					+ "/library/bookquery/search.aspx", backlistenermore,
					Method.POST);
		}
	}
	
	Listener<String> backlistener = new Listener<String>() {
		@Override
		public void onResponse(String response) {
			if (customProgressDialog != null
					&& customProgressDialog.isShowing())
				customProgressDialog.dismiss();
			hideKeybord();
			try {

				int count = Book.bookCount(response);
//				if (count > 0) {
//					ll_total_esearch.setVisibility(View.VISIBLE);

//				} else {
//					ll_total_esearch.setVisibility(View.GONE);
//				}
//				if (BuildConfig.DEBUG) {
//					Log.i(TAG, "count" + count);
//					Log.i(TAG, "response" + response);
//				}
				// JSONObject mj=new JSONObject(response);
				List<Book> lists = Book.formList(response);
				if (lists != null && !lists.isEmpty()) {
					listview.setVisibility(View.VISIBLE);
//					noResult_rl.setVisibility(View.GONE);
					cache = new BitmapCache(Tool.getCachSize());
					adapter = new BookAdapter(context, lists, new ImageLoader(
							mQueue, cache));
					if (lists.size() < DEFAULT_COUNT) {
						listview.setHasMore(false);
						listview.setAdapter(adapter);
						listview.onBottomComplete();
					} else {
						listview.setHasMore(true);
						listview.setAdapter(adapter);
					}
				} else {
					listview.setVisibility(View.GONE);
//					noResult_rl.setVisibility(View.VISIBLE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				onError(2);
			}

		}
	};
	
	Listener<String> backlistenermore = new Listener<String>() {
		@Override
		public void onResponse(String response) {

			if (customProgressDialog != null
					&& customProgressDialog.isShowing())
				customProgressDialog.dismiss();
			// moreprocess.setVisibility(View.GONE);
			try {
				// JSONObject mj=new JSONObject(response);
				List<Book> lists = Book.formList(response);
				if (lists != null && !lists.isEmpty()
						&& lists.size() == DEFAULT_COUNT) {
					adapter.addMoreData(lists);
					listview.onBottomComplete();
					
					
				} else if (lists != null && lists.size() > 0) {
					adapter.addMoreData(lists);
					listview.setHasMore(false);
					listview.onBottomComplete();
				} else {
					listview.setHasMore(false);
					listview.onBottomComplete();
				}
			} catch (Exception e) {
				onError(2);
			}

		}
	};
	
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
			e.printStackTrace();
		}
	}

	
}
