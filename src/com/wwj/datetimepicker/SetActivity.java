package com.wwj.datetimepicker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SetActivity extends Activity {

	private String initEndDateTime = "2016年12月4日  23:26"; // 初始化结束时间
	
	private Button xianglingfangshi,xiangling,chongfucishu,guanbifangshi,queding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		
		xianglingfangshi = (Button) findViewById(R.id.fangshi);//响铃方式 
		xianglingfangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.xianglingfangshi(xianglingfangshi);
			}
		});
		
		xiangling = (Button) findViewById(R.id.xiangling);//铃声
		xiangling.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.lingsheng(xiangling);
			}
		});
		
		chongfucishu = (Button) findViewById(R.id.chongfucishu);//稍后再响间隔
		chongfucishu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.jiange(chongfucishu);
			}
		});
		
		guanbifangshi = (Button) findViewById(R.id.guanbifangshi);//关闭闹钟的方法
		guanbifangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.guanbifanghsi(guanbifangshi);
			}
		});
		
/*		queding = (Button) findViewById(R.id.queding);//确定
		queding.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});*/
		
	}
}