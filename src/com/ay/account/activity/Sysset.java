package com.ay.account.activity;

import com.ay.account.dao.PwdDAO;
import com.ay.account.model.Tb_pwd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sysset extends Activity {
	EditText txtPwd;
	Button btnSet;
	Button btnSetCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stsset);
		txtPwd=(EditText)findViewById(R.id.txtPwd);
		btnSet=(Button)findViewById(R.id.btnSet);
		btnSetCancel=(Button)findViewById(R.id.btnsetCancel);
		btnSet.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PwdDAO pwdDAO=new PwdDAO(Sysset.this);
				Tb_pwd tb_pwd=new Tb_pwd(txtPwd.getText().toString());
				if(pwdDAO.getCount()==0)
				{
					pwdDAO.add(tb_pwd);
				}
				else
				{
					pwdDAO.update(tb_pwd);
				}
				
				Toast.makeText(Sysset.this, "°æ√‹¬Î°ø…Ë÷√≥…π¶", Toast.LENGTH_SHORT).show();
			}
			
		});
		btnSetCancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtPwd.setText("");
				txtPwd.setHint("«Î ‰»Î√‹¬Î");
			}
			
		});
	}
	
}
