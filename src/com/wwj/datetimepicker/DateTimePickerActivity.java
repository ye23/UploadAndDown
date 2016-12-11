package com.wwj.datetimepicker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 时间拾取器界面
 * 
 * @author wwj_748
 * 
 */
public class DateTimePickerActivity extends Activity {
	/** Called when the activity is first created. */
	private EditText startDateTime;//时间输入框
	private Button chongfuzhouqi,gengduoshezhi,off;//按钮，重复周期、更多设置、开/关

	private String initStartDateTime = "14:44"; // 初始化开始时间
	private String initEndDateTime = "2016年12月4日  23:26"; // 初始化结束时间
	private Intent SetActivity;
	public static SharedPreferences.Editor SCbaocun;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final SharedPreferences SharedClock= getSharedPreferences("Clock", Activity.MODE_PRIVATE);//实例化SharedPreferences，数据设置为私有，文件名为Clock 
		SCbaocun = SharedClock.edit(); 
	
		startDateTime = (EditText) findViewById(R.id.inputDate);// 时间输入框
		chongfuzhouqi = (Button) findViewById(R.id.chongfuzhouqi);
		gengduoshezhi = (Button) findViewById(R.id.gengduoshezhi);
		off = (Button) findViewById(R.id.off);
		
		SetActivity = new Intent(this, SetActivity.class);
		
		startDateTime.setText(initStartDateTime);

		startDateTime.setOnClickListener(new OnClickListener() {//时间
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						DateTimePickerActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);
			}
		});
		
		chongfuzhouqi.setOnClickListener(new OnClickListener() {//重复周期
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						DateTimePickerActivity.this, initEndDateTime);
				dateTimePicKDialog.zhouqiDialog(chongfuzhouqi); 
			}
		});
		
		gengduoshezhi.setOnClickListener(new OnClickListener() {//更多设置
			public void onClick(View v) {
				startActivity (SetActivity);
			}
		});

		off.setOnClickListener(new OnClickListener() {//开关
			public void onClick(View v) {
				if(off.getText().equals("已开启")){
					off.setText("已关闭");
				}else{
					off.setText("已开启");
					Log.e("test", "闹钟："
							+SharedClock.getString("chongfuzhouqi", "0")+","
							+SharedClock.getString("time", "0")+","
							+SharedClock.getString("gengduoshezhi_xianglingfangshi", "0")+","
							+SharedClock.getString("gengduoshezhi_lingsheng", "0")+","
							+SharedClock.getString("gengduoshezhi_jiange", "0")+","
							+SharedClock.getString("gengduoshezhi_guanbifangshi", "0")+","
							+SharedClock.getString("off", "0")
							);	
				}
				
				String value=off.getText().toString();
				SCbaocun.putString("off", value);
				SCbaocun.commit(); 
			}
		});
		
		if(SharedClock.getString("chongfuzhouqi", "0").equals("0")){
		}else{
			chongfuzhouqi.setText(SharedClock.getString("chongfuzhouqi", "0"));
		}
		
		if(SharedClock.getString("time", "0").equals("0")){
		}else{
			startDateTime.setText(SharedClock.getString("time", "0"));
		}
		
		if(SharedClock.getString("gengduoshezhi", "0").equals("0")){
		}else{
			gengduoshezhi.setText(SharedClock.getString("gengduoshezhi", "0"));
		}
		
		if(SharedClock.getString("off", "0").equals("0")){
		}else{
			off.setText(SharedClock.getString("off", "0"));
		}
		
	}
}
