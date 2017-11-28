package com.example.splashguidedemo;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends BaseActivity implements OnPageChangeListener{
	private List<Integer> mPictureList;
	private LinearLayout mPointLinearLayout;
	private ViewPager mViewPager;
	private ViewPagerAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_page);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		initData();
		initView();
		initPoint();
		setViewPager();
		
	}

	private void setViewPager() {
		mAdapter=new ViewPagerAdapter(GuideActivity.this, mPictureList);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(0);
	}

	private void initPoint() {
		mPointLinearLayout.removeAllViews();
		for(int i=0;i<mPictureList.size();i++){
			ImageView mImageView=new ImageView(this);
			if(i==0){
				mImageView.setBackgroundResource(R.drawable.page_indicator_focused);
			}else{
				mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
			//import android.widget.FrameLayout.LayoutParams;
			mImageView.setLayoutParams(new LayoutParams(20, 20,Gravity.CENTER));
			LayoutParams mParams=new LayoutParams(new ViewGroup.LayoutParams(20,20));
			mParams.leftMargin=50;
			mParams.rightMargin=50;
			mPointLinearLayout.addView(mImageView,mParams);
		}
	
	}

	private void initView() {
		mPointLinearLayout=(LinearLayout)findViewById(R.id.point);
		mViewPager=(ViewPager)findViewById(R.id.viewpager);
	}

	private void initData() {
		mPictureList=new ArrayList<Integer>();
		mPictureList.add(R.drawable.first);
		mPictureList.add(R.drawable.tow);
		mPictureList.add(R.drawable.third);
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		selectPointShow(arg0);
		
	}

	private void selectPointShow(int index) {
		for(int i=0;i<mPointLinearLayout.getChildCount();i++){
			ImageView mImageView=(ImageView)mPointLinearLayout.getChildAt(i);
			if (i==index) {
				mImageView.setBackgroundResource(R.drawable.page_indicator_focused);
				
			}else{
				mImageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

}
