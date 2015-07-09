package com.cqvip.moblib.sychildlibHD;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;

public class HttpUtils {


	public static RetryPolicy setTimeout(){
		RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		return retryPolicy;
	}
}
