package com.wwj.datetimepicker;

import com.ed.CLOCK_SDK;

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
	
	private String TAG = "Clock_Activity";
	private EditText startDateTime[]=new EditText[10];//时间输入框
	private Button chongfuzhouqi[]=new Button[10];//重复周期
	private Button gengduoshezhi[]=new Button[10];//更多设置
	private Button off[]=new Button[10];//开关

	private String initStartDateTime = "14:44"; // 初始化开始时间
	private String initEndDateTime = "2016年12月4日  23:26"; // 初始化结束时间
	private Intent SetActivity;
	public static SharedPreferences.Editor SCbaocun;
	public static int isoff=0;
	public static  SharedPreferences SharedClock;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock_main);
		
		SharedClock= getSharedPreferences("Clock", Activity.MODE_PRIVATE);//实例化SharedPreferences，数据设置为私有，文件名为Clock 
		SCbaocun = SharedClock.edit();
		
		Integer[] RinputDate ={R.id.inputDate1,R.id.inputDate2,R.id.inputDate3,R.id.inputDate4,R.id.inputDate5};
		Integer[] Rchongfuzhouqi ={R.id.chongfuzhouqi1,R.id.chongfuzhouqi2,R.id.chongfuzhouqi3,R.id.chongfuzhouqi4,R.id.chongfuzhouqi5};
		Integer[] Rgengduoshezhi ={R.id.gengduoshezhi1,R.id.gengduoshezhi2,R.id.gengduoshezhi3,R.id.gengduoshezhi4,R.id.gengduoshezhi5};
		Integer[] Roff ={R.id.off1,R.id.off2,R.id.off3,R.id.off4,R.id.off5};
		for(int i=1;i<=5;i++){
			startDateTime[i] = (EditText) findViewById(RinputDate[i-1]);// 时间输入框
			chongfuzhouqi[i] = (Button) findViewById(Rchongfuzhouqi[i-1]);
			gengduoshezhi[i] = (Button) findViewById(Rgengduoshezhi[i-1]);
			off[i] = (Button) findViewById(Roff[i-1]);
			startDateTime[i].setText(initStartDateTime);
		}

		SetActivity = new Intent(this, SetActivity.class);
		

		DuquAnjian();
		Anjian();
		
			
	}
	
	private void Anjian(){
		for(int i=1;i<=5;i++){
			final int ii=i;
			startDateTime[i].setOnClickListener(new OnClickListener() {//时间
				public void onClick(View v) {
					DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
							DateTimePickerActivity.this, initEndDateTime);
					dateTimePicKDialog.dateTimePicKDialog(startDateTime[ii],ii);
				}
			});
			
			chongfuzhouqi[i].setOnClickListener(new OnClickListener() {//重复周期
				public void onClick(View v) {
					DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
							DateTimePickerActivity.this, initEndDateTime);
					dateTimePicKDialog.zhouqiDialog(chongfuzhouqi[ii],ii); 
				}
			});
			
			gengduoshezhi[i].setOnClickListener(new OnClickListener() {//更多设置
				public void onClick(View v) {
				    Bundle bundle_i=new Bundle();
				    bundle_i.putInt("num", ii);
				    SetActivity.putExtras(bundle_i);
					startActivity (SetActivity);
				}
			});
			
			off[i].setOnClickListener(new OnClickListener() {//开关
				public void onClick(View v) {
					switch (off[ii].getText().toString()) {
					case "已开启":
						off[ii].setText("已关闭");
						
						String value_off=off[ii].getText().toString();
						SCbaocun.putString("off"+ii, value_off);
						SCbaocun.commit(); 
						
						CLOCK_SDK.mCallBack.Faid(ii);
						break;	
					case "已关闭":
						off[ii].setText("已开启");
						
						String value_on=off[ii].getText().toString();
						SCbaocun.putString("off"+ii, value_on);
						SCbaocun.commit(); 
						
						Log.e(TAG, "闹钟："
								+SharedClock.getString("chongfuzhouqi"+ii, "0")+","
								+SharedClock.getString("time"+ii, "0")+","
								+SharedClock.getString("gengduoshezhi_xianglingfangshi"+ii, "0")+","
								+SharedClock.getString("gengduoshezhi_lingsheng"+ii, "0")+","
								+SharedClock.getString("gengduoshezhi_jiange"+ii, "0")+","
								+SharedClock.getString("gengduoshezhi_guanbifangshi"+ii, "0")+","
								+SharedClock.getString("off"+ii, "0")
								);	
						
						CLOCK_SDK.mCallBack.Success(ii,SharedClock.getString("chongfuzhouqi"+ii, "0"),
								SharedClock.getString("time"+ii, "0"),SharedClock.getString("gengduoshezhi_xianglingfangshi"+ii, "0")
								,SharedClock.getString("gengduoshezhi_lingsheng"+ii, "0"),SharedClock.getString("gengduoshezhi_jiange"+ii, "0")
								,SharedClock.getString("gengduoshezhi_guanbifangshi"+ii, "0"));
						break;
					default:
						break;
					}
				}
			});
		}
	}
	
	private void DuquAnjian(){
		for(int i=1;i<=5;i++){
			if(SharedClock.getString("off"+i, "0").equals("0")){
			}else{
				off[i].setText(SharedClock.getString("off"+i, "0"));
			}
			
			if(SharedClock.getString("chongfuzhouqi"+i, "0").equals("0")){
			}else{
				chongfuzhouqi[i].setText(SharedClock.getString("chongfuzhouqi", "0"));
			}
			
			if(SharedClock.getString("time"+i, "0").equals("0")){
			}else{
				startDateTime[i].setText(SharedClock.getString("time"+i, "0"));
			}
			
			if(SharedClock.getString("gengduoshezhi"+i, "0").equals("0")){
			}else{
				//gengduoshezhi[i].setText(SharedClock.getString("gengduoshezhi"+i, "0"));
			}
		}
	}
	
}
