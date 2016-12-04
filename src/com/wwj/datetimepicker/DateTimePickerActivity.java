package com.wwj.datetimepicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * ʱ��ʰȡ������
 * 
 * @author wwj_748
 * 
 */
public class DateTimePickerActivity extends Activity {
	/** Called when the activity is first created. */
	private EditText startDateTime;//ʱ�������
	private Button chongfuzhouqi,gengduoshezhi,off;//��ť���ظ����ڡ��������á���/��

	private String initStartDateTime = "14:44"; // ��ʼ����ʼʱ��
	private String initEndDateTime = "2016��12��4��  23:26"; // ��ʼ������ʱ��

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		startDateTime = (EditText) findViewById(R.id.inputDate);// ʱ�������
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
