package com.wwj.datetimepicker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SetActivity extends Activity {

	private String initEndDateTime = "2016��12��4��  23:26"; // ��ʼ������ʱ��
	
	private Button xianglingfangshi,xiangling,chongfucishu,guanbifangshi,queding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		
		xianglingfangshi = (Button) findViewById(R.id.fangshi);//���巽ʽ 
		xianglingfangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.xianglingfangshi(xianglingfangshi);
			}
		});
		
		xiangling = (Button) findViewById(R.id.xiangling);//����
		xiangling.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.lingsheng(xiangling);
			}
		});
		
		chongfucishu = (Button) findViewById(R.id.chongfucishu);//�Ժ�������
		chongfucishu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.jiange(chongfucishu);
			}
		});
		
		guanbifangshi = (Button) findViewById(R.id.guanbifangshi);//�ر����ӵķ���
		guanbifangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.guanbifanghsi(guanbifangshi);
			}
		});
		
/*		queding = (Button) findViewById(R.id.queding);//ȷ��
		queding.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});*/
		
	}
}