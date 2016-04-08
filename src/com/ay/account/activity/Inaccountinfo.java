package com.ay.account.activity;

import java.util.List;

import com.ay.account.dao.InaccountDAO;
import com.ay.account.model.Tb_inaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Inaccountinfo extends Activity 
{
	private static final String FLAG="id";//定义常量请求码
	ListView lvInfo;//创建ListView对象
	String strType="" ;//创建字符串,记录管理类型
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		lvInfo=(ListView)findViewById(R.id.lvinacccountinfo);
		ShowInfo(R.id.btnininfo);//自定义的方法显示收入信息
		lvInfo.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String strInfo=String.valueOf(((TextView)view).getText());//记录收入的信息
				String strid=strInfo.substring(0,strInfo.indexOf('|'));//截取收入编号
				Intent intent=new Intent(Inaccountinfo.this,InfoManage.class);//创建Intent对象
				intent.putExtra(FLAG, new String[]{strid,strType});//设置传递数据
				startActivity(intent);//执行Intent操作	
			}
		});
	}
	 private void ShowInfo(int intType)//用来更具管理者类型，显示相应的信息
	 {
		 String[] strInfos=null;//定义字符串对象，用来存储收入信息
		 ArrayAdapter<String> arrayAdapter=null;//创建ArrayAdapter对象
		 strType="btnininfo";
		 InaccountDAO inaccountinfo=new InaccountDAO(Inaccountinfo.this);//创建InaccountDAO对象
		 List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0, (int)inaccountinfo.getCount());
		 strInfos=new String[listinfos.size()];//设置字符串的长度
		 int m=0;//订一个开始标签
		 for(Tb_inaccount tb_inaccount:listinfos)
		 {
			 strInfos[m]=tb_inaccount.get_id()+"|"
					 +tb_inaccount.getType()
					 +""+String.valueOf(tb_inaccount.getMoney())+"元    "
					 +tb_inaccount.getTime();
					 m++;//标识符加1
		 }
		 arrayAdapter=new ArrayAdapter<String>(this,
				 android.R.layout.simple_list_item_1,
				 strInfos);
		 lvInfo.setAdapter(arrayAdapter);
	 }
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		ShowInfo(R.id.btnininfo);//现实收入
	}
}
