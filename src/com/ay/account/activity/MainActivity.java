package com.ay.account.activity;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
					intent=new Intent(MainActivity.this,Outaccountinfo.class);
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
			private LayoutInflater inflater;
			private List<Picture> pictures;
			public PictureAdapter(String[] titles,int[] images,Context context)
			{
				super();
				pictures=new ArrayList<Picture>();//初始化泛型集合
				inflater=LayoutInflater.from(context);//初始化LayoutFlater对象
				for(int i=0;i<images.length;i++)//遍历图像
					{
						Picture picture=new Picture(titles[i],images[i]);//使用标题和图像生成Picture对象
						pictures.add(picture);
					}
				
			}
		@Override
		public int getCount() {//获取泛型集合的长度
			// TODO Auto-generated method stub
			if(null!=pictures)//如果泛型集合不为空
			{
				return pictures.size();
			}
			return 0;//返回0
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return pictures.get(position);//获取反省指定索引处的项
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;//返回泛型集合索引
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;//创建ViewHolder对象
			if(convertView==null)
			{
				convertView=inflater.inflate(R.layout.gvitem, null);//设置图像表示
				viewHolder =new ViewHolder();//初始化ViewHolder对象
				viewHolder.title=(TextView)convertView.findViewById(R.id.ItemTitle);//设置图像标题
				viewHolder.image=(ImageView)convertView.findViewById(R.id.ItemImage);//设置图的二进制值
				convertView.setTag(viewHolder);//设置提示
			}
			else
			{
				viewHolder=(ViewHolder)convertView.getTag();//设置提示
			}
			viewHolder.title.setText(pictures.get(position).getTitle());// 设置图像标题
			viewHolder.image.setImageResource(pictures.get(position).getImageId());// 设置图像的二进制值
			return convertView;// 返回图像标识
		}
		
	}
	class ViewHolder//创建ViewHolder类
	{
		public TextView title;//创建TextView对象
		public ImageView image;//创建ImageView对象
		
	}
	class Picture//创建Pictrue对象
	{
		private String title;//定义字符串便是图片标题
		private int imageId;//表示图片的二进制值
		public Picture(){
			super();
		}
		public Picture(String title,int imageId)
		{
			super();
			this.title=title;
			this.imageId=imageId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getImageId() {
			return imageId;
		}
		public void setImageId(int imageId) {
			this.imageId = imageId;
		}
		
	}
	
	
}
