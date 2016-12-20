package com.wwj.datetimepicker;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

/**
 * 日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 *           dateTimePicKDialog=new
 *           DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 *           dateTimePicKDialog.dateTimePicKDialog(inputDate);
 * 
 *           } });
 * 
 * @author
 */

public class DateTimePickDialogUtil extends Activity implements OnDateChangedListener,
		OnTimeChangedListener {
	private DatePicker datePicker;
	private TimePicker timePicker;
	private AlertDialog ad;
	private String dateTime;
	private String initDateTime;
	private Activity activity;

	/**
	 * 日期时间弹出选择框构造函数
	 * 
	 * @param activity
	 *            ：调用的父activity
	 * @param initDateTime
	 *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
	 */
	public DateTimePickDialogUtil(Activity activity, String initDateTime) {
		this.activity = activity;
		this.initDateTime = initDateTime;

	}

	public void init(DatePicker datePicker, TimePicker timePicker) {
		Calendar calendar = Calendar.getInstance();
		if (!(null == initDateTime || "".equals(initDateTime))) {
			calendar = this.getCalendarByInintData(initDateTime);
		} else {
			initDateTime = calendar.get(Calendar.YEAR) + "年"
					+ calendar.get(Calendar.MONTH) + "月"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "日 "
					+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
					+ calendar.get(Calendar.MINUTE);
		}

		datePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}

	/** 时间选择框*/
	public AlertDialog dateTimePicKDialog(final EditText inputDate,final int num) {
		LinearLayout dateTimeLayout = (LinearLayout) activity
				.getLayoutInflater().inflate(R.layout.common_datetime, null);
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		ad = new AlertDialog.Builder(activity)
				//.setTitle(initDateTime.substring(11,initDateTime.length()))
				.setView(dateTimeLayout)
				.setPositiveButton("设置", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						String SFTime=dateTime.substring(11,dateTime.length());
						inputDate.setText(SFTime);
						Log.e("test", dateTime.indexOf("日")+"+"+dateTime.length()+"+"+SFTime);
						DateTimePickerActivity.SCbaocun.putString("time"+num, SFTime);
						DateTimePickerActivity.SCbaocun.commit(); 
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}
	
	/**日期选择框*/
	public AlertDialog datePicKDialog(final Button inputDate,final int num) {
		LinearLayout dateTimeLayout = (LinearLayout) activity
				.getLayoutInflater().inflate(R.layout.common_date, null);
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		ad = new AlertDialog.Builder(activity)
				//.setTitle(initDateTime.substring(11,initDateTime.length()))
				.setView(dateTimeLayout)
				.setPositiveButton("设置", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						String SFTime=dateTime.substring(0,11);
						inputDate.setText(SFTime);
						Log.e("test", dateTime.indexOf("日")+"+"+dateTime.length()+"+"+SFTime);
						DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, SFTime);
						DateTimePickerActivity.SCbaocun.commit();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}
	
	/**周期选择框*/
	public AlertDialog zhouqiDialog(final Button inputDate,final int num) {
		LinearLayout dateTimeLayout = (LinearLayout) activity
				.getLayoutInflater().inflate(R.layout.common_zhouqi, null);
				
		ad = new AlertDialog.Builder(activity)
				.setTitle("请选择重复的周期")
				.setView(dateTimeLayout)
				.show();
		
		Button meitian,meizhou,meiyue,ziding,teding;
		meitian = (Button) dateTimeLayout.findViewById(R.id.meitian);
		meizhou = (Button) dateTimeLayout.findViewById(R.id.meizhou);
		meiyue = (Button) dateTimeLayout.findViewById(R.id.meiyue);
		ziding = (Button) dateTimeLayout.findViewById(R.id.ziding);
		teding = (Button) dateTimeLayout.findViewById(R.id.teding);
		
		meitian.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("每天");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "每天");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();
			}
		});
		meizhou.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("每周");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "每周");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();	
			}
		});
		meiyue.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("每月");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "每月");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();
			}
		});
		ziding.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("自定");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "自定");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();
			}
		});
		teding.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ad.dismiss();
				datePicKDialog(inputDate,num);
			}
		});
		
		return ad;
	}
	
	/**响铃方式选择框*/
	public AlertDialog xianglingfangshi(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "录音", "铃声", "闪烁屏幕","铃声+闪烁屏幕","铃声(强行把音量开到最大)","铃声(最大音量)+闪烁屏幕(疯狂)"};   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("响铃方式：")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("响铃方式：录音");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "响铃方式：录音");
	            				DateTimePickerActivity.SCbaocun.commit();  
	            				break;
	            			case 1: 
	            				inputDate.setText("响铃方式：铃声");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "响铃方式：铃声");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("响铃方式：闪屏");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "响铃方式：闪屏");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("响铃方式：铃声+闪屏");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "响铃方式：铃声+闪屏");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("响铃方式：最大音量铃声");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "最大音量铃声");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 5: 
	            				inputDate.setText("响铃方式：大铃声，疯闪屏");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "大铃声和疯闪屏");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}
	
	/**铃声选择框*/
	public AlertDialog lingsheng(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "录音1","录音2", "铃声1","铃声2", "特殊" };   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("铃声：")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("铃声：录音1");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "铃声：录音1");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 1: 
	            				inputDate.setText("铃声：录音2");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "铃声：录音2");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("铃声：铃声1");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "铃声：铃声1");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("铃声：铃声2");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "铃声：铃声2");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("铃声：特殊");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "铃声：特殊");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}
	
	/**响铃间隔选择框*/
	public AlertDialog jiange(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "关闭（只响一次）","间隔1分钟", "间隔3分钟","间隔5分钟", "无间隔一直响一直响" };   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("响铃间隔：")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("响铃间隔：关闭（只响一次）");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "响铃间隔：关闭（只响一次）");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 1: 
	            				inputDate.setText("响铃间隔：间隔1分钟");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "响铃间隔：间隔1分钟");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("响铃间隔：间隔3分钟");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "响铃间隔：间隔3分钟");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("响铃间隔：间隔5分钟");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "响铃间隔：间隔5分钟");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("响铃间隔：无间隔一直响一直响");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "响铃间隔：无间隔一直响一直响");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}
	
	/**关闭方式选择框*/
	public AlertDialog guanbifanghsi(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "点击屏幕按键关闭闹钟","摇晃手机3下关闭闹钟", "疯狂摇晃手机30下后关闭闹钟","做题，答对后关闭闹钟", "设置题目（用于上面的答题）" };   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("响铃间隔：")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("关闭方式：点击屏幕");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "关闭方式：点击屏幕");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 1: 
	            				inputDate.setText("关闭方式：摇晃手机");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "关闭方式：摇晃手机");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("关闭方式：疯狂摇晃");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "关闭方式：疯狂摇晃");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("关闭方式：回答问题");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "关闭方式：回答问题1");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("关闭方式：回答问题");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "关闭方式：回答问题2");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}

	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}

	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// 获得日历实例
		Calendar calendar = Calendar.getInstance();

		calendar.set(datePicker.getYear(), datePicker.getMonth(),
				datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
				timePicker.getCurrentMinute());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

		dateTime = sdf.format(calendar.getTime());
		ad.setTitle(dateTime);
	}

	/**
	 * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
	 * 
	 * @param initDateTime
	 *            初始日期时间值 字符串型
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();

		// 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
		String date = spliteString(initDateTime, "日", "index", "front"); // 日期
		String time = spliteString(initDateTime, "日", "index", "back"); // 时间

		String yearStr = spliteString(date, "年", "index", "front"); // 年份
		String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

		String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
		String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

		String hourStr = spliteString(time, ":", "index", "front"); // 时
		String minuteStr = spliteString(time, ":", "index", "back"); // 分

		int currentYear = Integer.valueOf(yearStr.trim()).intValue();
		int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
		int currentDay = Integer.valueOf(dayStr.trim()).intValue();
		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

		calendar.set(currentYear, currentMonth, currentDay, currentHour,
				currentMinute);
		return calendar;
	}

	/**
	 * 截取子串
	 * 
	 * @param srcStr
	 *            源串
	 * @param pattern
	 *            匹配模式
	 * @param indexOrLast
	 * @param frontOrBack
	 * @return
	 */
	public static String spliteString(String srcStr, String pattern,
			String indexOrLast, String frontOrBack) {
		String result = "";
		int loc = -1;
		if (indexOrLast.equalsIgnoreCase("index")) {
			loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
		} else {
			loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
		}
		if (frontOrBack.equalsIgnoreCase("front")) {
			if (loc != -1)
				result = srcStr.substring(0, loc); // 截取子串
		} else {
			if (loc != -1)
				result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
		}
		return result;
	}
}
