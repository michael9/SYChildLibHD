package com.cqvip.moblib.sychildlibHD;

import org.json.JSONException;
import org.json.JSONObject;


public class User extends Result{
	
	private String cardno;
	private String name;
	private String dept;
	private String cardtype;
	private String email;
	private String username;
	private String userid;
	private String readerno;
	private String phone;
	private String mobile;
	private String updatedate;
	private int vipuserid;
	
	
	
	
	public int getVipuserid() {
		return vipuserid;
	}

	public User(String result) throws BookException{
		super(result);
		try {
			JSONObject js = new JSONObject(result);
			JSONObject json = js.getJSONObject("userinfo");
			cardno = json.getString("cardno");
			name = json.getString("name");
			cardtype = json.getString("cardtypeid");
			userid = json.getString("userid");
			readerno = json.getString("readerno");
			vipuserid = getInt("vipuserid",json);
		} catch (JSONException e) {
			throw new BookException(e);
		}
		
	}
	   protected static int getInt(String key, JSONObject json) throws JSONException {
	        String str = json.getString(key);
	        if(null == str || "".equals(str)||"null".equals(str)){
	            return 0;
	        }
	        return Integer.parseInt(str);
	    }
	    
	public String getCardno() {
		return cardno;
	}
	public String getName() {
		return name;
	}
	public String getDept() {
		return dept;
	}
	public String getCardtype() {
		return cardtype;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getUserid() {
		return userid;
	}
	public String getReaderno() {
		return readerno;
	}
	public String getPhone() {
		return phone;
	}
	public String getMobile() {
		return mobile;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	
	

}
