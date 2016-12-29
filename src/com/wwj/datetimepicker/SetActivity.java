package com.wwj.datetimepicker;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.bmob.v3.datatype.BmobFile;


public class SetActivity extends Activity {
	private Button xianglingfangshi,xiangling,chongfucishu,guanbifangshi,queding;
	public static String test1="0";
	
	private static Activity mActivity;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private File tempFile;//用于临时存储照片
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		mActivity = SetActivity.this;
		
		final Intent intent = new Intent(this,MainActivity.class);
		
		xianglingfangshi = (Button) findViewById(R.id.fangshi);//1
		xianglingfangshi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
		        // 激活系统图库，选择一张图片
		        Intent intent = new Intent(Intent.ACTION_PICK);//获取手机上的数据（图片，联系人，电影、音乐等）
		        intent.setType("image/*");//获取手机上的图片
		        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
		        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
			}
		});	
		
		xiangling = (Button) findViewById(R.id.xiangling);//2
		xiangling.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//确保文件夹是存在的
		        File dirFile = new File(MApplication.Downaddress);
		        if(!dirFile.exists()){ //判断 文件夹 是否存在
		        	dirFile.mkdir(); //新建 文件夹
		        }
				
		        // 激活相机
		        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		        tempFile = new File(MApplication.Downaddress,"XXXZP.jpg");//存储地址，和文件名
		        Uri uri = Uri.fromFile(tempFile);// 从文件中创建uri
		        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);   
		        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
		        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
			}
		});
		
		chongfucishu = (Button) findViewById(R.id.chongfucishu);//3
		chongfucishu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				BmobFile bmobfile =new BmobFile("xxx.jpg","",test1);
				Function.downloadFile(mActivity,bmobfile);
			}
		});
		
	}


	/**Activity的返回值*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {// 从相册返回的数据
            if (data != null) {
                Uri uri = data.getData();// 得到图片数据的全路径()
                Log.e("yy", "uri="+uri);
                
                String path="0";
                if(data.getDataString().contains("content")){
                	 //把intent转换成url地址
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(uri, proj, null, null, null); 
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); //按我个人理解 这个是获得用户选择的图片的索引值
                    cursor.moveToFirst();//将光标移至开头 ，这个很重要，不小心很容易引起越界
                    path = cursor.getString(column_index);//最后根据索引值获取图片路径
                    Log.e("yy", path);
                }else {
                    path = data.getDataString().replace("file://", "");
                    Log.e("yy", path);
                }
 
                Function.uploadFile(mActivity,path,new Function.VivoCallBack() {
    				@Override
    				public void Success(String url) {

    				}
    				@Override
    				public void Faid() {

    				}
    				@Override
    				public void Progress(int value) {
    					Log.e("yy", value+"\n");
    				}
    			});//上传选择的图片
           }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
        	
            	Uri uri = Uri.fromFile(tempFile);// 从相机返回的数据
            	
            	Log.e("yy", "uri="+uri);
            	
            	String path="0";//不同的系统可能返回的方式不一样，目前知道有2种content和file
                if(uri.toString().contains("content")){//检测字符串中是否 包含某个 字符串
               	 //把intent转换成url地址
                   String[] proj = {MediaStore.Images.Media.DATA};
                   Cursor cursor = managedQuery(uri, proj, null, null, null); 
                   int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); //按我个人理解 这个是获得用户选择的图片的索引值
                   cursor.moveToFirst();//将光标移至开头 ，这个很重要，不小心很容易引起越界
                   path = cursor.getString(column_index);//最后根据索引值获取图片路径
                   Log.e("yy", path);
               }else {
                   path = uri.toString().replace("file://", "");//字符串中 使用“XX” 替代“XXX”
                   Log.e("yy", path);
               }
                
                Function.uploadFile(mActivity,path,new Function.VivoCallBack() {
    				@Override
    				public void Success(String url) {

    				}
    				@Override
    				public void Faid() {

    				}
    				@Override
    				public void Progress(int value) {
    					Log.e("yy", value+"\n");
    				}
    			});//上传选择的图片
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
	
	
}