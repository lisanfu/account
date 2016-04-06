package com.ay.account.activity;

import com.xiaoke.accountsoft.activity.Accountflag;
import com.xiaoke.accountsoft.activity.AddInaccount;
import com.xiaoke.accountsoft.activity.Inaccountinfo;
import com.xiaoke.accountsoft.activity.MainActivity;
import com.xiaoke.accountsoft.activity.Outaccountinfo;
import com.xiaoke.accountsoft.activity.Showinfo;
import com.xiaoke.accountsoft.activity.Sysset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class MainActivity extends Activity 
{
	private GridView  gvInfo;
	String[] titles=new String[]{"新增支出", "新增收入", "我的支出", "我的收入", "数据管理",
			"系统设置", "收支便签", "退出"};
	int[] images = new int[] { R.drawable.addoutaccount,
			R.drawable.addinaccount, R.drawable.outaccountinfo,
			R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
			R.drawable.accountflag, R.drawable.exit };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gvInfo=(GridView)findViewById(R.id.gvInfo);
		PictureAdapter adapter = new PictureAdapter(titles, images, this);// 创建pictureAdapter对象
		gvInfo.setAdapter(adapter);
		gvInfo.setOnItemClickListener(new OnItemClickListener() 
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=null;
				switch(position)
				{
				case 0:
					intent=new Intent(MainActivity.this,addOutaccount.class);
					startActivity(intent);
					break;
				case 1:
					finish();//
				}
			}
			
		});
	}
	class PictureAdapter extends BaseAdapter
	{
			public PictureAdapter(String[] titles,int[] images,Context context)
			{
				
			}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
}
