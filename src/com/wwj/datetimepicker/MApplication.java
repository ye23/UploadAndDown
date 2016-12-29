package com.wwj.datetimepicker;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;
import cn.bmob.v3.Bmob;

public class MApplication extends Application {
	/**
	 * SDK初始化也可以放到Application中
	 */
	public static String APPID ="ec4fc24eed7208790c68926a81e172ed";
	public static String Downaddress =Environment.getExternalStorageDirectory() + "/AndroidDown/";
	public static String Saveaddress =Environment.getExternalStorageDirectory() + "/AndroidInformation/";
	
	@Override
	public void onCreate() {
		super.onCreate();
		//Bmob初始化
		Bmob.initialize(this,APPID,"demo");
		Log.e("yy", "初始化");


	}

}