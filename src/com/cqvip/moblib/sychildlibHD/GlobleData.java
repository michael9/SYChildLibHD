package com.cqvip.moblib.sychildlibHD;

import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

public class GlobleData {


	public final static int    defaultDBVersion = 1;

	public final static String dbName = "moblelib.db";

	public final static String MODEL_PACKAGE = "com.cqvip.moblelib.entity";

	public final static   String SERVER_URL = "http://mobile.cqvip.com";
//	public final static   String EPUB_HOME_URL = "http://222.180.195.154:2300/";
//	public final static   String EPUB_HOME_URL = "http://113.31.19.26:9093/";
	//public final static   String SERVER_URL = "http://192.168.20.61:8080";

	//public final static   String SERVER_URL_ZJY = "http://192.168.20.61:8080";

	public final static   String LIBIRY_ID = "3";

	public final static   String SZLG_LIB_ID = "044120";

	public final static int BOOK_ZK_TYPE = 4;

	//public final static int BOOK_SZ_TYPE = 5;
	public final static int BOOK_SZ_TYPE = 6;

	public final static String QUERY_KEY = "subject";

	public final static String QUERY_ISBN = "isbn";

	public final static String QUERY_ALL = "all";
//	public final static String QUERY_ALL = "title";

	public final static String QUERY_TITLE = "title";

	public final static String QUERY_AUTHOR = "author";

	public final static String QUERY_CALSSNO = "classno";

	public final static String QUERY_CALLNO = "callno";

	public final static String QUERY_PUBLISHER = "publisher";

	public final static String QUERY_TABLE = "bibliosm";
	//
	public final static String IMAGE_CACHE_DIR = "bookimg";
	
	public static final int ANNAOUCETYPE_HOTBOOK = 3;
	public static final int ANNAOUCETYPE_NEWBOOK = 4;
	public static final int ANNAOUCETYPE_NEWS = 2 ;
	public static final int ANNAOUCETYPE_FREESPEECH = 1;
	public static final int FAQ_QUESTION = 5;
	public static final int PROBLEM_ANNAOUCE = 6;
	
	public static Map<String, Object> datas = new HashMap<String, Object>();
	
	public static final String SERVER = "service";
	
	public static String userid;
	public static String readerid;
	public static String cqvipid;
	public static String readername;
	
	

	public static final int ANNO_NEWS = 7;
	public static final int ANNO_MESS = 8;
	public static final int ANNO_SUBJECT = 9;
	public static final int ANNO_PROFESSOR= 10;
	public static final int SUG_NEWBOOK= 11;
	
	

	public static boolean islogin = false;

	public static final int MEDIAL_TYPEID = 1;
	public static final int ENGINE_TYPEID = 63;
	public static final int SOCIAL_TYPEID = 67;
	public static final int NATURE_TYPEID = 64;
	public static final int FORESTS_TYPEID = 66;
	
	public static final int BIG_PERPAGE = 500;
	
	
	public static final String FBReaderJapk=Environment.getExternalStorageDirectory()+"/fbreader.apk";
	
	
	
}
