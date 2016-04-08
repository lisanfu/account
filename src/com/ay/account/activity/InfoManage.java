package com.ay.account.activity;

import java.util.Calendar;

import com.ay.account.dao.InaccountDAO;
import com.ay.account.dao.OutaccountDAO;
import com.ay.account.model.Tb_inaccount;
import com.ay.account.model.Tb_outaccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class InfoManage extends Activity 
{
	protected static final int DATE_DIALOG_ID=0;
	TextView tvtitle;
	TextView textView;
	EditText txtMoney;
	EditText txtTime;
	EditText txtHA;
	EditText txtMark;
	Spinner spType;
	Button btnEdit;
	Button btnDel;
	String[] strInfos;
	String strid;
	String strType;
	private int mYear;
	private int mMonth;
	private int mDay;
	OutaccountDAO outaccountDAO=new OutaccountDAO(InfoManage.this);
	InaccountDAO inaccountDAO=new InaccountDAO(InfoManage.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tvtitle=(TextView)findViewById(R.id.inouttitle);
		textView=(EditText)findViewById(R.id.txtInOut);
		txtMoney=(EditText)findViewById(R.id.txtInOutMoney);
		txtTime=(EditText)findViewById(R.id.txtInOutTime);
		spType=(Spinner)findViewById(R.id.spInOutType);
		txtHA=(EditText)findViewById(R.id.tvInOut);
		txtMark=(EditText)findViewById(R.id.txtInOutMark);
		btnEdit=(Button)findViewById(R.id.btnInOutEdit);
		btnDel=(Button)findViewById(R.id.btnInOutDelete);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		strInfos = bundle.getStringArray(Showinfo.FLAG);// 获取Bundle中记录的信息
		strid=strInfos[0];
		strType=strInfos[1];
		if(strType.equals("btnoutinfo"))
		{
			tvtitle.setText("支出管理");
			textView.setText("地       点");
			Tb_outaccount tb_outaccount=outaccountDAO.find(Integer.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
			txtTime.setText(tb_outaccount.getTime());
			spType.setPrompt(tb_outaccount.getType());
			txtHA.setText(tb_outaccount.getAddress());
			txtMark.setText(tb_outaccount.getMark());
			
		}
		else if(strType.equals("btnininfo"))
		{
			tvtitle.setText("收入管理");// 设置标题为“收入管理”
			textView.setText("付款方：");// 设置“地点/付款方”标签文本为“付款方：”
			// 根据编号查找收入信息，并存储到Tb_outaccount对象中
			Tb_inaccount tb_inaccount = inaccountDAO.find(Integer
					.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));// 显示金额
			txtTime.setText(tb_inaccount.getTime());// 显示时间
			spType.setPrompt(tb_inaccount.getType());// 显示类别
			txtHA.setText(tb_inaccount.getHandler());// 显示付款方
			txtMark.setText(tb_inaccount.getMark());// 显示备注
		}
		txtTime.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
			
		});
		btnEdit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(strType.equals("btnoutinfo"))
				{
					Tb_outaccount tb_outaccount=new Tb_outaccount();
					tb_outaccount.set_id(Integer.parseInt(strid));
					tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString()));
					tb_outaccount.setTime(txtTime.getText().toString());// 设置时间
					tb_outaccount.setType(spType.getSelectedItem().toString());// 设置类别
					tb_outaccount.setAddress(txtHA.getText().toString());// 设置地点
					tb_outaccount.setMark(txtMark.getText().toString());// 设置备注
					outaccountDAO.update(tb_outaccount);// 更新支出信息	
				}
				else if(strType.equals("btnininfo"))
				{
					
					Tb_inaccount tb_inaccount = new Tb_inaccount();// 创建Tb_inaccount对象
					tb_inaccount.set_id(Integer.parseInt(strid));// 设置编号
					tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText()
							.toString()));// 设置金额
					tb_inaccount.setTime(txtTime.getText().toString());// 设置时间
					tb_inaccount.setType(spType.getSelectedItem().toString());// 设置类别
					tb_inaccount.setHandler(txtHA.getText().toString());// 设置付款方
					tb_inaccount.setMark(txtMark.getText().toString());// 设置备注
					inaccountDAO.update(tb_inaccount);// 更新收入信息
				}
				// 弹出信息提示
				Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		btnDel.setOnClickListener(new OnClickListener() {// 为删除按钮设置监听事件
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (strType.equals("btnoutinfo"))// 判断类型如果是btnoutinfo
				{
					outaccountDAO.detele(Integer.parseInt(strid));// 根据编号删除支出信息
				} else if (strType.equals("btnininfo"))// 判断类型如果是btnininfo
				{
					inaccountDAO.delete(Integer.parseInt(strid));// 根据编号删除收入信息
				}
				Toast.makeText(InfoManage.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT)
						.show();
			}
		});

		final Calendar c = Calendar.getInstance();// 获取当前系统日期
		mYear = c.get(Calendar.YEAR);// 获取年份
		mMonth = c.get(Calendar.MONTH);// 获取月份
		mDay = c.get(Calendar.DAY_OF_MONTH);// 获取天数
		updateDisplay();// 显示当前系统时间
	}
	@Override
	protected Dialog onCreateDialog(int id)// 重写onCreateDialog方法
	{
		switch (id) {
		case DATE_DIALOG_ID:// 弹出日期选择对话框
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;// 为年份赋值
			mMonth = monthOfYear;// 为月份赋值
			mDay = dayOfMonth;// 为天赋值
			updateDisplay();// 显示设置的日期
		}
	};

	private void updateDisplay() {
		// 显示设置的时间
		txtTime.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));
	}
}
