package com.wwj.datetimepicker;

import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Function {
	
	/**上传文件
	 * in：地址如：Environment.getExternalStorageDirectory() + "/AndroidInformation/007.jpg"
	 * out：URL(上传的文件的完整地址)*/
	public static VivoCallBack mCallBack;
	public static void uploadFile(final Activity mActivity,String address,VivoCallBack callback) {
		
		String picPath =address;//文件详细地址
		mCallBack = callback;
		final BmobFile bmobFile = new BmobFile(new File(picPath));
		
			bmobFile.uploadblock(new UploadFileListener() {
			    @Override
			    public void done(BmobException e) {
			        if(e==null){
			            //bmobFile.getFileUrl()--返回的上传文件的完整地址
			        	Log.e("yy",bmobFile.getFileUrl());
			        	mCallBack.Success(bmobFile.getFileUrl());
			        	Toast.makeText(mActivity, bmobFile.getFileUrl(), Toast.LENGTH_SHORT).show();
			        }else{
			        	mCallBack.Faid();
			        	Toast.makeText(mActivity, "上传文件失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
			        }

			    }

			    @Override
			    public void onProgress(Integer value) {
			        // 返回的上传进度（百分比）
			    	//Log.e("yy", value+"\n");
			    	mCallBack.Progress(value);
			    }
			});
	}
	
    public interface VivoCallBack{
    	public void Success(String url);
    	public void Faid();
    	public void Progress(int value);
    }
	
	/**下载文件
	 * in：BmobFile*/
	public static void downloadFile(final Activity mActivity,BmobFile file){
	    //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
	    File saveFile = new File(MApplication.Downaddress, file.getFilename());
	    file.download(saveFile, new DownloadFileListener() {

	        @Override
	        public void onStart() {
	        	Toast.makeText(mActivity, "开始下载...", Toast.LENGTH_SHORT).show();
	        }

	        @Override
	        public void done(String savePath,BmobException e) {
	            if(e==null){
	            	Toast.makeText(mActivity, "下载成功,保存路径:"+savePath, Toast.LENGTH_SHORT).show();
	            }else{
	            	Toast.makeText(mActivity, "下载失败："+e.getErrorCode()+","+e.getMessage(), Toast.LENGTH_SHORT).show();
	            }
	        }

	        @Override
	        public void onProgress(Integer value, long newworkSpeed) {
	            Log.i("bmob","下载进度："+value+","+newworkSpeed);
	        }
	    });
	}
	

}
