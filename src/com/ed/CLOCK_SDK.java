package com.ed;

import com.wwj.datetimepicker.DateTimePickerActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CLOCK_SDK {
	private static String TAG = "Clock_SDK";

    public static ClockCallBack mCallBack;
    public static void order(final Context mContext,String PayCode,ClockCallBack callback){
    	Log.i(TAG, "µ÷ÓÃCLOCK_SDK");
    	mCallBack = callback;
    	Intent intent = new Intent(mContext, DateTimePickerActivity.class);
    	mContext.startActivity(intent);	
    }
    
    
    
    public interface ClockCallBack{
    	public void Success(int num,String chongfuzhouqi,String time,
    			String gengduoshezhi_xianglingfangshi,String gengduoshezhi_lingsheng,
    			String gengduoshezhi_jiange,String gengduoshezhi_guanbifangshi);
    	public void Faid(int num);
    	public void Cancel();
    }
    
}
