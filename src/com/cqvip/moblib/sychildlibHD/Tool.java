package com.cqvip.moblib.sychildlibHD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;



public class Tool {

	



	public static void ShowMessages(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ShowMessagel(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}


	public static boolean checkNetWork(Context context) {
		boolean netWorkIsOK = false;
		ConnectivityManager connectManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			netWorkIsOK = true;
		} else {
			Toast.makeText(context, "联网失败，请检查网络！", Toast.LENGTH_LONG).show();
		}
		return netWorkIsOK;
	}


	public static String md5Add(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}


	public static boolean isbnMatch(String str) {
		Pattern pattern = Pattern
				.compile("[0-9]{3}\\-?[0-9]{1}\\-?[0-9]{4}\\-?[0-9]{4}\\-?[0-9]{1}");
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	public static String formSZbookID(String callno,String recordid){
		String result = callno+","+recordid;
		return result;
	}

	/**
	 * 返回 时间样式 ：几小时前
	 * @param date
	 * @return
	 */
	public static String GetTime(Date date){
		long   lSubTime = 0;
		String strTime = "";
		Date curTime = new Date();
		lSubTime = curTime.getTime() - date.getTime();
		lSubTime = lSubTime/(60 * 1000);
		if(lSubTime < 1){
			lSubTime = 1;
		}
		if(lSubTime < 60){
			strTime = "" + lSubTime + "分钟前";
		}else if(lSubTime >= 60 && lSubTime < (24*60)){
			strTime = "" + lSubTime/60 + "小时前";
		}else{
			try {
				SimpleDateFormat df = (SimpleDateFormat) new SimpleDateFormat(
						"MM-dd hh:mm");
				strTime = df.format(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strTime;
	}


	 public static String getBigImg(String strr){
		   int i = strr.indexOf("spic",0);
		  return strr.substring(0,i) +"l"+ strr.substring(i+1);
	   }

	 public static boolean hasSDcard(Context context){

			String sdStatus = Environment.getExternalStorageState();
			 if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
				Tool.ShowMessages(context, "没有找到SD卡");
				 return false;
			 }
			 return true;
	 }
	 public static int getCachSize(){
		 int size = (int) Math.round(0.125 * Runtime.getRuntime().maxMemory());
		 return size;
	 }
	 
}
