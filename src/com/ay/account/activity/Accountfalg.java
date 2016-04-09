package com.ay.account.activity;

import com.ay.account.dao.FlagDAO;
import com.ay.account.model.Tb_flag;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Accountfalg extends Activity {
	EditText txtFlag;
	Button btnflagSaveButton;
	Button btnflagCancelButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountflag);
		txtFlag=(EditText)findViewById(R.id.txtFlag);
		btnflagCancelButton=(Button)findViewById(R.id.btnflagCancel);
		btnflagSaveButton=(Button)findViewById(R.id.btnflagSave);
		btnflagSaveButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strFlag=txtFlag.getText().toString();
				if(!strFlag.isEmpty())
				{
					FlagDAO flagDAO=new FlagDAO(Accountfalg.this);
					Tb_flag tb_flag=new Tb_flag(flagDAO.getMAxId(),strFlag);
					flagDAO.add(tb_flag);
					Toast.makeText(Accountfalg.this,"【新增便签】数据添加成功", Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					Toast.makeText(Accountfalg.this,"请输入便签", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		btnflagCancelButton.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtFlag.setText("");
			}
			
		});
	}

}
