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
	private Intent SetActivity;
	public static SharedPreferences.Editor SCbaocun;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final SharedPreferences SharedClock= getSharedPreferences("Clock", Activity.MODE_PRIVATE);//ʵ����SharedPreferences����������Ϊ˽�У��ļ���ΪClock 
		SCbaocun = SharedClock.edit(); 
	
		startDateTime = (EditText) findViewById(R.id.inputDate);// ʱ�������
		chongfuzhouqi = (Button) findViewById(R.id.chongfuzhouqi);
		gengduoshezhi = (Button) findViewById(R.id.gengduoshezhi);
		off = (Button) findViewById(R.id.off);
		
		SetActivity = new Intent(this, SetActivity.class);
		
		startDateTime.setText(initStartDateTime);

		startDateTime.setOnClickListener(new OnClickListener() {//ʱ��
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						DateTimePickerActivity.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);
			}
		});
		
		chongfuzhouqi.setOnClickListener(new OnClickListener() {//�ظ�����
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						DateTimePickerActivity.this, initEndDateTime);
				dateTimePicKDialog.zhouqiDialog(chongfuzhouqi); 
			}
		});
		
		gengduoshezhi.setOnClickListener(new OnClickListener() {//��������
			public void onClick(View v) {
				startActivity (SetActivity);
			}
		});

		off.setOnClickListener(new OnClickListener() {//����
			public void onClick(View v) {
				if(off.getText().equals("�ѿ���")){
					off.setText("�ѹر�");
				}else{
					off.setText("�ѿ���");
					Log.e("test", "���ӣ�"
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
