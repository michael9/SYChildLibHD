package com.cqvip.moblib.sychildlibHD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class BookDetailActivity extends Activity{
	
	private Book dBook;
	private TextView booktitle_tv, textView9, textView10, textView11;
	private ImageView imgview;
	private Context context;
	private LinearLayout loc_list_ll;
	private CustomProgressDialog customProgressDialog;
	private Map<String, String> gparams;
	private RequestQueue mQueue;
	private ErrorListener el;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_book);
		context = this;
		mQueue=Volley.newRequestQueue(this);
		customProgressDialog=CustomProgressDialog.createDialog(this);
		loc_list_ll = (LinearLayout) findViewById(R.id.loc_list_ll);
		imgview = (ImageView) findViewById(R.id.book_big_img);
		Bundle bundle = getIntent().getBundleExtra("detaiinfo");
		dBook = (Book) bundle.getSerializable("book");
		booktitle_tv = (TextView) findViewById(R.id.booktitle_tv);
		textView9 = (TextView) findViewById(R.id.textView9);
		textView10 = (TextView) findViewById(R.id.textView10);
		textView11 = (TextView) findViewById(R.id.textView11);
		booktitle_tv.setText(dBook.getTitle());
		textView10.setText(dBook.getAuthor());
		textView11.setText(dBook.getU_abstract());
		getLocalinfo(dBook.getRecordid());
	}
	
	private void getLocalinfo(String recordid) {
		customProgressDialog.show();
		gparams = new HashMap<String, String>();
		gparams.put("recordid", recordid);
		gparams.put("libid", GlobleData.LIBIRY_ID);
		requestVolley(GlobleData.SERVER_URL + "/library/bookquery/detail.aspx",
				back_ls, Method.POST);
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
	
	private Listener<String> back_ls = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			if (customProgressDialog != null
					&& customProgressDialog.isShowing())
				customProgressDialog.dismiss();
			try {
				final List<BookLoc> list = BookLoc.formList(response);
				new Thread(new Runnable() {

					@Override
					public void run() {
						add2gc(list);
					}
				}).start();

			} catch (Exception e) {
				onError(2);
				return;
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
	
	LinearLayout mll;

	private void add2gc(List<BookLoc> list) {
		if (list == null || list.isEmpty())
			return;
		for (BookLoc bl : list) {
			// LinearLayout mll=new LinearLayout(this);
			mll = (LinearLayout) getLayoutInflater().inflate(
					R.layout.item_location_book, loc_list_ll, false);
			// mll.inflate(this, R.layout.item_location_book, null);
			TextView barcode = (TextView) mll
					.findViewById(R.id.loc_barcode_txt);
			TextView callno = (TextView) mll.findViewById(R.id.loc_callno_txt);
			TextView location = (TextView) mll
					.findViewById(R.id.loc_location_txt);
			TextView cirtype = (TextView) mll
					.findViewById(R.id.loc_cirtype_txt);
			TextView status = (TextView) mll.findViewById(R.id.loc_status_txt);

			barcode.setText(context.getString(R.string.item_barcode)
					+ bl.getBarcode());
			callno.setText(context.getString(R.string.item_callno)
					+ bl.getCallno());
			location.setText(context.getString(R.string.item_loc)
					+ bl.getLocal());
			cirtype.setText(context.getString(R.string.item_cirtype)
					+ bl.getCirtype());
			status.setText(context.getString(R.string.item_status)
					+ bl.getStatus());
			handler.sendEmptyMessage(0);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			loc_list_ll.addView(mll, 0);
		};
	};
}
