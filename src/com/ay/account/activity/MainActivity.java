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
	String[] titles=new String[]{"����֧��", "��������", "�ҵ�֧��", "�ҵ�����", "���ݹ���",
			"ϵͳ����", "��֧��ǩ", "�˳�"};
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
		PictureAdapter adapter = new PictureAdapter(titles, images, this);// ����pictureAdapter����
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
				pictures=new ArrayList<Picture>();//��ʼ�����ͼ���
				inflater=LayoutInflater.from(context);//��ʼ��LayoutFlater����
				for(int i=0;i<images.length;i++)//����ͼ��
					{
						Picture picture=new Picture(titles[i],images[i]);//ʹ�ñ����ͼ������Picture����
						pictures.add(picture);
					}
				
			}
		@Override
		public int getCount() {//��ȡ���ͼ��ϵĳ���
			// TODO Auto-generated method stub
			if(null!=pictures)//������ͼ��ϲ�Ϊ��
			{
				return pictures.size();
			}
			return 0;//����0
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return pictures.get(position);//��ȡ��ʡָ������������
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;//���ط��ͼ�������
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;//����ViewHolder����
			if(convertView==null)
			{
				convertView=inflater.inflate(R.layout.gvitem, null);//����ͼ���ʾ
				viewHolder =new ViewHolder();//��ʼ��ViewHolder����
				viewHolder.title=(TextView)convertView.findViewById(R.id.ItemTitle);//����ͼ�����
				viewHolder.image=(ImageView)convertView.findViewById(R.id.ItemImage);//����ͼ�Ķ�����ֵ
				convertView.setTag(viewHolder);//������ʾ
			}
			else
			{
				viewHolder=(ViewHolder)convertView.getTag();//������ʾ
			}
			viewHolder.title.setText(pictures.get(position).getTitle());// ����ͼ�����
			viewHolder.image.setImageResource(pictures.get(position).getImageId());// ����ͼ��Ķ�����ֵ
			return convertView;// ����ͼ���ʶ
		}
		
	}
	class ViewHolder//����ViewHolder��
	{
		public TextView title;//����TextView����
		public ImageView image;//����ImageView����
		
	}
	class Picture//����Pictrue����
	{
		private String title;//�����ַ�������ͼƬ����
		private int imageId;//��ʾͼƬ�Ķ�����ֵ
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
