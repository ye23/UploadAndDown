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
 * ����ʱ��ѡ��ؼ� ʹ�÷����� private EditText inputDate;//��Ҫ���õ�����ʱ���ı��༭�� private String
 * initDateTime="2012��9��3�� 14:44",//��ʼ����ʱ��ֵ �ڵ���¼���ʹ�ã�
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
	 * ����ʱ�䵯��ѡ����캯��
	 * 
	 * @param activity
	 *            �����õĸ�activity
	 * @param initDateTime
	 *            ��ʼ����ʱ��ֵ����Ϊ�������ڵı��������ʱ���ʼֵ
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
			initDateTime = calendar.get(Calendar.YEAR) + "��"
					+ calendar.get(Calendar.MONTH) + "��"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "�� "
					+ calendar.get(Calendar.HOUR_OF_DAY) + ":"
					+ calendar.get(Calendar.MINUTE);
		}

		datePicker.init(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}

	/** ʱ��ѡ���*/
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
				.setPositiveButton("����", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						String SFTime=dateTime.substring(11,dateTime.length());
						inputDate.setText(SFTime);
						Log.e("test", dateTime.indexOf("��")+"+"+dateTime.length()+"+"+SFTime);
						DateTimePickerActivity.SCbaocun.putString("time"+num, SFTime);
						DateTimePickerActivity.SCbaocun.commit(); 
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}
	
	/**����ѡ���*/
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
				.setPositiveButton("����", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						String SFTime=dateTime.substring(0,11);
						inputDate.setText(SFTime);
						Log.e("test", dateTime.indexOf("��")+"+"+dateTime.length()+"+"+SFTime);
						DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, SFTime);
						DateTimePickerActivity.SCbaocun.commit();
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}
	
	/**����ѡ���*/
	public AlertDialog zhouqiDialog(final Button inputDate,final int num) {
		LinearLayout dateTimeLayout = (LinearLayout) activity
				.getLayoutInflater().inflate(R.layout.common_zhouqi, null);
				
		ad = new AlertDialog.Builder(activity)
				.setTitle("��ѡ���ظ�������")
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
				inputDate.setText("ÿ��");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "ÿ��");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();
			}
		});
		meizhou.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("ÿ��");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "ÿ��");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();	
			}
		});
		meiyue.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("ÿ��");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "ÿ��");
				DateTimePickerActivity.SCbaocun.commit(); 
				ad.dismiss();
			}
		});
		ziding.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				inputDate.setText("�Զ�");
				DateTimePickerActivity.SCbaocun.putString("chongfuzhouqi"+num, "�Զ�");
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
	
	/**���巽ʽѡ���*/
	public AlertDialog xianglingfangshi(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "¼��", "����", "��˸��Ļ","����+��˸��Ļ","����(ǿ�а������������)","����(�������)+��˸��Ļ(���)"};   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("���巽ʽ��")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("���巽ʽ��¼��");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "���巽ʽ��¼��");
	            				DateTimePickerActivity.SCbaocun.commit();  
	            				break;
	            			case 1: 
	            				inputDate.setText("���巽ʽ������");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "���巽ʽ������");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("���巽ʽ������");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "���巽ʽ������");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("���巽ʽ������+����");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "���巽ʽ������+����");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("���巽ʽ�������������");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "�����������");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 5: 
	            				inputDate.setText("���巽ʽ����������������");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_xianglingfangshi"+num, "�������ͷ�����");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}
	
	/**����ѡ���*/
	public AlertDialog lingsheng(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "¼��1","¼��2", "����1","����2", "����" };   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("������")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("������¼��1");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "������¼��1");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 1: 
	            				inputDate.setText("������¼��2");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "������¼��2");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("����������1");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "����������1");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("����������2");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "����������2");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("����������");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_lingsheng"+num, "����������");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}
	
	/**������ѡ���*/
	public AlertDialog jiange(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "�رգ�ֻ��һ�Σ�","���1����", "���3����","���5����", "�޼��һֱ��һֱ��" };   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("��������")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("���������رգ�ֻ��һ�Σ�");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "���������رգ�ֻ��һ�Σ�");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 1: 
	            				inputDate.setText("�����������1����");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "�����������1����");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("�����������3����");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "�����������3����");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("�����������5����");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "�����������5����");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("���������޼��һֱ��һֱ��");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_jiange"+num, "���������޼��һֱ��һֱ��");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			default:
	            				break;
	            		}
                    } 
                }). show(); 
		return ad;
	}
	
	/**�رշ�ʽѡ���*/
	public AlertDialog guanbifanghsi(final Button inputDate,final int num) {
        final String[] xuanxiang = new String[] { "�����Ļ�����ر�����","ҡ���ֻ�3�¹ر�����", "���ҡ���ֻ�30�º�ر�����","���⣬��Ժ�ر�����", "������Ŀ����������Ĵ��⣩" };   
        ad = new AlertDialog.Builder(activity) 
                .setTitle("��������")
                .setItems(xuanxiang, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
	            		switch (which) {
	            			case 0: 
	            				inputDate.setText("�رշ�ʽ�������Ļ");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "�رշ�ʽ�������Ļ");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 1: 
	            				inputDate.setText("�رշ�ʽ��ҡ���ֻ�");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "�رշ�ʽ��ҡ���ֻ�");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 2: 
	            				inputDate.setText("�رշ�ʽ�����ҡ��");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "�رշ�ʽ�����ҡ��");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 3: 
	            				inputDate.setText("�رշ�ʽ���ش�����");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "�رշ�ʽ���ش�����1");
	            				DateTimePickerActivity.SCbaocun.commit();
	            				break;
	            			case 4: 
	            				inputDate.setText("�رշ�ʽ���ش�����");
	            				DateTimePickerActivity.SCbaocun.putString("gengduoshezhi_guanbifangshi"+num, "�رշ�ʽ���ش�����2");
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
		// �������ʵ��
		Calendar calendar = Calendar.getInstance();

		calendar.set(datePicker.getYear(), datePicker.getMonth(),
				datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
				timePicker.getCurrentMinute());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH:mm");

		dateTime = sdf.format(calendar.getTime());
		ad.setTitle(dateTime);
	}

	/**
	 * ʵ�ֽ���ʼ����ʱ��2012��07��02�� 16:45 ��ֳ��� �� �� ʱ �� ��,����ֵ��calendar
	 * 
	 * @param initDateTime
	 *            ��ʼ����ʱ��ֵ �ַ�����
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();

		// ����ʼ����ʱ��2012��07��02�� 16:45 ��ֳ��� �� �� ʱ �� ��
		String date = spliteString(initDateTime, "��", "index", "front"); // ����
		String time = spliteString(initDateTime, "��", "index", "back"); // ʱ��

		String yearStr = spliteString(date, "��", "index", "front"); // ���
		String monthAndDay = spliteString(date, "��", "index", "back"); // ����

		String monthStr = spliteString(monthAndDay, "��", "index", "front"); // ��
		String dayStr = spliteString(monthAndDay, "��", "index", "back"); // ��

		String hourStr = spliteString(time, ":", "index", "front"); // ʱ
		String minuteStr = spliteString(time, ":", "index", "back"); // ��

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
	 * ��ȡ�Ӵ�
	 * 
	 * @param srcStr
	 *            Դ��
	 * @param pattern
	 *            ƥ��ģʽ
	 * @param indexOrLast
	 * @param frontOrBack
	 * @return
	 */
	public static String spliteString(String srcStr, String pattern,
			String indexOrLast, String frontOrBack) {
		String result = "";
		int loc = -1;
		if (indexOrLast.equalsIgnoreCase("index")) {
			loc = srcStr.indexOf(pattern); // ȡ���ַ�����һ�γ��ֵ�λ��
		} else {
			loc = srcStr.lastIndexOf(pattern); // ���һ��ƥ�䴮��λ��
		}
		if (frontOrBack.equalsIgnoreCase("front")) {
			if (loc != -1)
				result = srcStr.substring(0, loc); // ��ȡ�Ӵ�
		} else {
			if (loc != -1)
				result = srcStr.substring(loc + 1, srcStr.length()); // ��ȡ�Ӵ�
		}
		return result;
	}
}
