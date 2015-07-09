package com.cqvip.moblib.sychildlibHD;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


/**
 * 
 * @author luojiang
 *
 */
public class ShortBook {

	private String sucesss;
	private String id;
	private String date;
	private String message;
	
	public ShortBook(int type,String result) throws BookException{
		switch(type){
		case Task.TASK_BOOK_RENEW:
			try {
				JSONObject js = new JSONObject(result);
				sucesss = js.getString("success");
				if(sucesss.equalsIgnoreCase("true")){
				JSONObject json = js.getJSONObject("renewinfo");
				id = json.getString("barcode");
				date = json.getString("eventtime");
				message = json.getString("message");
				}else{
				  message = js.getString("message");
				}
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_EBOOK_DOWN:
			try {
				JSONObject json = new JSONObject(result);
				date = json.getString("svrurl");
				message = json.getString("svrname");
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_REFRESH:
			try {
				JSONObject json = new JSONObject(result);
				sucesss = json.getString("success");
				id = json.getString("version");
				date = json.getString("url");
				message = json.getString("message");
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_GET_FAVOR:
			try {
				JSONObject json = new JSONObject(result);
				id = json.getString("favoritetypeid");
				date = json.getString("favoritekeyid");
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_SUGGEST_HOTBOOK:	
		case Task.TASK_SUGGEST_NEWBOOK:
		case Task.TASK_ANNOUNCE_NEWS:	
		case Task.TASK_ANNOUNCE_WELFARE:
			try {
				JSONObject json = new JSONObject(result);
				id = json.getString("id");
				message = json.getString("title");
				date = json.getString("imgurl_s");//СͼƬ
				sucesss = json.getString("imgurl");//��ͼƬ
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_E_CAUTION:	
		case Task.TASK_E_CAUTION_MORE:	
			try {
				JSONObject json = new JSONObject(result);
				message = json.getString("title");
				id = json.getString("contents");//СͼƬ
				sucesss = json.getString("imgurl");//��ͼƬ
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_PERIODICAL_TYPE:
			try {
				JSONObject json = new JSONObject(result);
				id = json.getString("classid");//СͼƬ
				date = json.getString("classname");//СͼƬ
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		}
		
	}
	
	public static List<ShortBook> formList(int type,String result) throws BookException{
		List<ShortBook> books = null;
		switch(type){
		case Task.TASK_GET_FAVOR:
			try {
				JSONObject json = new JSONObject(result);
				if(!json.getBoolean("success")){
					return null;
				}
				JSONArray ary = json.getJSONArray("favoritelist");
				int count = ary.length();
				if(count <=0){
					return null;
				}
				books = new ArrayList<ShortBook>(count);
				for(int i = 0;i<count;i++){
					books.add(new ShortBook(type,ary.getJSONObject(i).toString()));
				}
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_EBOOK_DOWN:
			try {
				JSONObject json = new JSONObject(result);
				if(!json.getBoolean("success")){
					return null;
				}
				JSONArray ary = json.getJSONArray("serverinfo");
				int count = ary.length();
				if(count <=0){
					return null;
				}
				books = new ArrayList<ShortBook>(count);
				for(int i = 0;i<count;i++){
					books.add(new ShortBook(type,ary.getJSONObject(i).toString()));
				}
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_SUGGEST_HOTBOOK:	
		case Task.TASK_SUGGEST_NEWBOOK:
		case Task.TASK_ANNOUNCE_NEWS:	
		case Task.TASK_ANNOUNCE_WELFARE:
		case Task.TASK_E_CAUTION:	
		case Task.TASK_E_CAUTION_MORE:	
			try {
				JSONObject json = new JSONObject(result);
				if(!json.getBoolean("success")){
					return null;
				}
				JSONArray ary = json.getJSONArray("announcelist");
				int count = ary.length();
				if(count <=0){
					return null;
				}
				books = new ArrayList<ShortBook>(count);
				for(int i = 0;i<count;i++){
					books.add(new ShortBook(type,ary.getJSONObject(i).toString()));
				}
			} catch (JSONException e) {
				throw new BookException(e);
			}
			break;
		case Task.TASK_PERIODICAL_TYPE:
			try {
				JSONObject json = new JSONObject(result);
				if(!json.getBoolean("success")){
					return null;
				}
				JSONArray ary=json.getJSONArray("classlist");
				int count = ary.length();
				if(count <=0){
					return null;
				}
				books = new ArrayList<ShortBook>(count);
				for(int i = 0;i<count;i++){
					books.add(new ShortBook(type,ary.getJSONObject(i).toString()));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				throw new BookException(e);
			}
			break;
			
			
			
		}
		    return books;
		
	}
	
	
	public String getSucesss() {
		return sucesss;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ShortBook [sucesss=" + sucesss + ", id=" + id + ", date="
				+ date + ", message=" + message + "]";
	}
	
	
	
}
