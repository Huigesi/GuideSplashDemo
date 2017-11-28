package com.example.splashguidedemo;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class SplashActivity extends BaseActivity{
	private static final int ENTER_HOME=0x00;
	private static final int ENTER_SPLASH=0x01;
	private int delayMillis=3 * 1000;
	
	private SharedPreferences mPreferences;
	private Handler mHandler;
	private TextView mTextView; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		getPreferences();
		handlerMessage();
		initView();
		setText();
		into();
		
	}

	private void into() {
		if(isFirstRun()){
			mHandler.sendEmptyMessageDelayed(ENTER_SPLASH, delayMillis);
			
		}else {
			//mHandler.sendEmptyMessageDelayed(ENTER_SPLASH, delayMillis);
			mHandler.sendEmptyMessageDelayed(ENTER_HOME,delayMillis);
		}
	}

	private boolean isFirstRun() {
		// TODO Auto-generated method stub
		return mPreferences.getBoolean("isFirst", true);
	}

	private void setText() {
		mTextView.setText("当前版本号："+getVersion());
	}

	private String getVersion() {
		// TODO Auto-generated method stub
		String versionString="";
		PackageManager manager=getPackageManager();
		try {
			PackageInfo info=manager.getPackageInfo(getPackageName(), 0);
			versionString=info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionString;
	}

	private void initView() {
		mTextView=(TextView)findViewById(R.id.version);
	}

	private void handlerMessage() {
		mHandler=new Handler(new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what==ENTER_HOME){
					Intent mHomeIntent=new Intent(SplashActivity.this,MainActivity.class);
					startActivity(mHomeIntent);
				}else{
					Editor editor=mPreferences.edit();
					//疑点
					editor.putBoolean("isFirst", false);
					editor.commit();
					Intent mSplashIntent=new Intent(SplashActivity.this,GuideActivity.class);
					startActivity(mSplashIntent);
				}
				finish();
				return false;
			}
		});
		
	}

	private void getPreferences() {
		// TODO Auto-generated method stub
		mPreferences=getSharedPreferences("first", Context.MODE_PRIVATE);
	}
	

}
