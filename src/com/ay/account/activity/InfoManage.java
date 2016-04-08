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
		strInfos = bundle.getStringArray(Showinfo.FLAG);// ��ȡBundle�м�¼����Ϣ
		strid=strInfos[0];
		strType=strInfos[1];
		if(strType.equals("btnoutinfo"))
		{
			tvtitle.setText("֧������");
			textView.setText("��       ��");
			Tb_outaccount tb_outaccount=outaccountDAO.find(Integer.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
			txtTime.setText(tb_outaccount.getTime());
			spType.setPrompt(tb_outaccount.getType());
			txtHA.setText(tb_outaccount.getAddress());
			txtMark.setText(tb_outaccount.getMark());
			
		}
		else if(strType.equals("btnininfo"))
		{
			tvtitle.setText("�������");// ���ñ���Ϊ���������
			textView.setText("�����");// ���á��ص�/�������ǩ�ı�Ϊ���������
			// ���ݱ�Ų���������Ϣ�����洢��Tb_outaccount������
			Tb_inaccount tb_inaccount = inaccountDAO.find(Integer
					.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));// ��ʾ���
			txtTime.setText(tb_inaccount.getTime());// ��ʾʱ��
			spType.setPrompt(tb_inaccount.getType());// ��ʾ���
			txtHA.setText(tb_inaccount.getHandler());// ��ʾ���
			txtMark.setText(tb_inaccount.getMark());// ��ʾ��ע
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
					tb_outaccount.setTime(txtTime.getText().toString());// ����ʱ��
					tb_outaccount.setType(spType.getSelectedItem().toString());// �������
					tb_outaccount.setAddress(txtHA.getText().toString());// ���õص�
					tb_outaccount.setMark(txtMark.getText().toString());// ���ñ�ע
					outaccountDAO.update(tb_outaccount);// ����֧����Ϣ	
				}
				else if(strType.equals("btnininfo"))
				{
					
					Tb_inaccount tb_inaccount = new Tb_inaccount();// ����Tb_inaccount����
					tb_inaccount.set_id(Integer.parseInt(strid));// ���ñ��
					tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText()
							.toString()));// ���ý��
					tb_inaccount.setTime(txtTime.getText().toString());// ����ʱ��
					tb_inaccount.setType(spType.getSelectedItem().toString());// �������
					tb_inaccount.setHandler(txtHA.getText().toString());// ���ø��
					tb_inaccount.setMark(txtMark.getText().toString());// ���ñ�ע
					inaccountDAO.update(tb_inaccount);// ����������Ϣ
				}
				// ������Ϣ��ʾ
				Toast.makeText(InfoManage.this, "�����ݡ��޸ĳɹ���", Toast.LENGTH_SHORT)
						.show();
			}
		});
		btnDel.setOnClickListener(new OnClickListener() {// Ϊɾ����ť���ü����¼�
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (strType.equals("btnoutinfo"))// �ж����������btnoutinfo
				{
					outaccountDAO.detele(Integer.parseInt(strid));// ���ݱ��ɾ��֧����Ϣ
				} else if (strType.equals("btnininfo"))// �ж����������btnininfo
				{
					inaccountDAO.delete(Integer.parseInt(strid));// ���ݱ��ɾ��������Ϣ
				}
				Toast.makeText(InfoManage.this, "�����ݡ�ɾ���ɹ���", Toast.LENGTH_SHORT)
						.show();
			}
		});

		final Calendar c = Calendar.getInstance();// ��ȡ��ǰϵͳ����
		mYear = c.get(Calendar.YEAR);// ��ȡ���
		mMonth = c.get(Calendar.MONTH);// ��ȡ�·�
		mDay = c.get(Calendar.DAY_OF_MONTH);// ��ȡ����
		updateDisplay();// ��ʾ��ǰϵͳʱ��
	}
	@Override
	protected Dialog onCreateDialog(int id)// ��дonCreateDialog����
	{
		switch (id) {
		case DATE_DIALOG_ID:// ��������ѡ��Ի���
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;// Ϊ��ݸ�ֵ
			mMonth = monthOfYear;// Ϊ�·ݸ�ֵ
			mDay = dayOfMonth;// Ϊ�츳ֵ
			updateDisplay();// ��ʾ���õ�����
		}
	};

	private void updateDisplay() {
		// ��ʾ���õ�ʱ��
		txtTime.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));
	}
}
