package com.wwj.datetimepicker;

import android.app.Activity;
import android.os.Bundle;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		startDateTime = (EditText) findViewById(R.id.inputDate);// 时间输入框
		chongfuzhouqi = (Button) findViewById(R.id.chongfuzhouqi);
		gengduoshezhi = (Button) findViewById(R.id.gengduoshezhi);
		off = (Button) findViewById(R.id.off);
		
		startDateTime.setText(initStartDateTime);

		startDateTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						DateTimePickerActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);

			}
		});
		
		chongfuzhouqi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						DateTimePickerActivity.this, initEndDateTime);
				dateTimePicKDialog.datePicKDialog(chongfuzhouqi);

			}
		});

	}
}
