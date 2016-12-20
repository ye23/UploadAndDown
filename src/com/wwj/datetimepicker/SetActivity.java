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
	
	private int ii;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		
        Bundle bundle = this.getIntent().getExtras();
        ii = bundle.getInt("num");
		
		xianglingfangshi = (Button) findViewById(R.id.fangshi);//���巽ʽ 
		xianglingfangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.xianglingfangshi(xianglingfangshi,ii);
			}
		});
		
		xiangling = (Button) findViewById(R.id.xiangling);//����
		xiangling.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.lingsheng(xiangling,ii);
			}
		});
		
		chongfucishu = (Button) findViewById(R.id.chongfucishu);//�Ժ�������
		chongfucishu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.jiange(chongfucishu,ii);
			}
		});
		
		guanbifangshi = (Button) findViewById(R.id.guanbifangshi);//�ر����ӵķ���
		guanbifangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						SetActivity.this, initEndDateTime);
				dateTimePicKDialog.guanbifanghsi(guanbifangshi,ii);
			}
		});
		
		if(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_xianglingfangshi"+ii, "0").equals("0")){
		}else{
			xianglingfangshi.setText(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_xianglingfangshi"+ii, "0"));
		}
		
		if(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_lingsheng"+ii, "0").equals("0")){
		}else{
			xiangling.setText(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_lingsheng"+ii, "0"));
		}
		
		if(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_jiange"+ii, "0").equals("0")){
		}else{
			chongfucishu.setText(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_jiange"+ii, "0"));
		}
		
		if(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_guanbifangshi"+ii, "0").equals("0")){
		}else{
			guanbifangshi.setText(DateTimePickerActivity.SharedClock.getString("gengduoshezhi_guanbifangshi"+ii, "0"));
		}
		
/*		queding = (Button) findViewById(R.id.queding);//ȷ��
		queding.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});*/
		
	}
}