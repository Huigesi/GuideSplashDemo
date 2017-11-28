package com.example.splashguidedemo;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {
	private Context mContext;
	private List<Integer> mList;
	private LayoutInflater mLayoutInflater;

	public ViewPagerAdapter(Context mContext, List<Integer> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {

		return mList.size();
	}

	// 判断两个对象是否相同
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0.equals((View) arg1);
	}

	// 销毁视图
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view =(View)mLayoutInflater.inflate(R.layout.guide_item, container,false);
		RelativeLayout mLayout=(RelativeLayout) view.findViewById(R.id.picture);
		TextView textView=(TextView)view.findViewById(R.id.enter);
		mLayout.setBackgroundResource(mList.get(position));
		
		textView.setBackgroundResource(R.drawable.third);
		
		// 设置跳转按钮
		if(position==2){
			textView.setVisibility(View.VISIBLE);
			textView.setBackgroundColor(Color.GREEN);
		}else{
			textView.setVisibility(View.GONE);
		}
		
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				GuideActivity mActivity=(GuideActivity) mContext;
				mContext.startActivity(new Intent(mContext,MainActivity.class));
				mActivity.finish();
			}
		});
		((ViewPager)container).addView(view,0);
		return view;
	}
	/*class ViewHolder{
		
	}*/
	
	

}
