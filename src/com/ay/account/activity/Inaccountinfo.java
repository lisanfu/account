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
	private static final String FLAG="id";//���峣��������
	ListView lvInfo;//����ListView����
	String strType="" ;//�����ַ���,��¼��������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		lvInfo=(ListView)findViewById(R.id.lvinacccountinfo);
		ShowInfo(R.id.btnininfo);//�Զ���ķ�����ʾ������Ϣ
		lvInfo.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String strInfo=String.valueOf(((TextView)view).getText());//��¼�������Ϣ
				String strid=strInfo.substring(0,strInfo.indexOf('|'));//��ȡ������
				Intent intent=new Intent(Inaccountinfo.this,InfoManage.class);//����Intent����
				intent.putExtra(FLAG, new String[]{strid,strType});//���ô�������
				startActivity(intent);//ִ��Intent����	
			}
		});
	}
	 private void ShowInfo(int intType)//�������߹��������ͣ���ʾ��Ӧ����Ϣ
	 {
		 String[] strInfos=null;//�����ַ������������洢������Ϣ
		 ArrayAdapter<String> arrayAdapter=null;//����ArrayAdapter����
		 strType="btnininfo";
		 InaccountDAO inaccountinfo=new InaccountDAO(Inaccountinfo.this);//����InaccountDAO����
		 List<Tb_inaccount> listinfos=inaccountinfo.getScrollData(0, (int)inaccountinfo.getCount());
		 strInfos=new String[listinfos.size()];//�����ַ����ĳ���
		 int m=0;//��һ����ʼ��ǩ
		 for(Tb_inaccount tb_inaccount:listinfos)
		 {
			 strInfos[m]=tb_inaccount.get_id()+"|"
					 +tb_inaccount.getType()
					 +""+String.valueOf(tb_inaccount.getMoney())+"Ԫ    "
					 +tb_inaccount.getTime();
					 m++;//��ʶ����1
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
		ShowInfo(R.id.btnininfo);//��ʵ����
	}
}
