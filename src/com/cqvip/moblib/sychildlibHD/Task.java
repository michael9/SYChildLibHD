package com.cqvip.moblib.sychildlibHD;

import java.util.Map;

public class Task {
	private int taskID;
	private Map taskParam;
	public static final int TASK_LOGIN = 1;
	public static final int TASK_GET_READERINFO = 2;
	public static final int TASK_QUERY_BOOK = 3;
	public static final int TASK_QUERY_MORE= 4;
	public static final int TASK_BORROW_LIST= 5;
	public static final int TASK_E_NOTICE= 6;
	public static final int TASK_E_CARDGUID= 7;
	public static final int TASK_E_TIME= 8;
	public static final int TASK_E_READER= 9;
	public static final int TASK_E_SERVICE= 10;
	public static final int TASK_BOOK_INFO= 11;
	public static final int TASK_BOOK_RENEW= 12;
	public static final int TASK_USER_PWD= 13;
	public static final int TASK_QUERY_EBOOK= 14;
	public static final int TASK_QUERY_EBOOK_MORE= 15;
	public static final int TASK_QUERY_EBOOK_DETAIL= 16;
	public static final int TASK_EBOOK_DOWN= 17;
	public static final int TASK_REFRESH= 18;
	public static final int TASK_GET_FAVOR= 19;
	public static final int TASK_LIB_FAVOR= 20;
	public static final int TASK_CANCEL_FAVOR= 21;
	public static final int TASK_EBOOK_FAVOR= 22;
    public static final int TASK_ADD_COMMENT= 23;
	public static final int TASK_COMMENT_BOOKLIST= 24;
	public static final int TASK_COMMENT_LIST= 25;
	public static final int TASK_ANNOUNCE_SPEACH= 26;
	public static final int TASK_ANNOUNCE_WELFARE= 28;
	public static final int TASK_ANNOUNCE_WELFARE_MORE= 29;
	public static final int TASK_ANNOUNCE_NEWS = 30;
	public static final int TASK_ANNOUNCE_NEWS_MORE = 31;
	public static final int TASK_ANNOUNCE_DETAIL = 32;
	public static final int TASK_E_CAUTION = 33;
	public static final int TASK_SUGGEST_HOTBOOK = 34;
	public static final int TASK_SUGGEST_NEWBOOK = 35;
	public static final int TASK_SUGGEST_HOTBOOK_MORE = 36;
	public static final int TASK_SUGGEST_NEWBOOK_MORE = 37;
	public static final int TASK_SUGGEST_DETAIL = 38;
	public static final int TAST_CENTER_QUESTION= 39;
	public static final int TASK_COMMENT_LIST_MORE= 40;
	public static final int TASK_E_CAUTION_MORE = 41;
	public static final int TASK_PERIODICAL_TYPE= 42;
	public static final int TASK_PERIODICAL_SUBTYPE= 43;
	public static final int TASK_PERIODICAL_DETAIL= 44;
	public static final int TASK_PERIODICAL_SPECIAL= 45;

	
	
	
	public Task(int id, Map param) {
		this.taskID = id;
		this.taskParam = param;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public Map getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map taskParam) {
		this.taskParam = taskParam;
	}
}