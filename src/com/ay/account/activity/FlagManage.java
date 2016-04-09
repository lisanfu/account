package com.ay.account.activity;

import com.ay.account.dao.FlagDAO;
import com.ay.account.model.Tb_flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FlagManage extends Activity {
	EditText txtFlag;
	Button btnEdit;
	Button btnDel;
	String strid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flaganage);
		btnEdit = (Button) findViewById(R.id.btnFlagManageEdit);// 获取修改按钮
		btnDel = (Button) findViewById(R.id.btnFlagManageDelete);// 获取删除按钮
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		strid=bundle.getString(Showinfo.FLAG);
		final FlagDAO flagDAO=new FlagDAO(FlagManage.this);
		txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());
		btnEdit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_flag tb_falg=new Tb_flag();
				tb_falg.set_id(Integer.parseInt(strid));
				tb_falg.setFlag(txtFlag.getText().toString());
				flagDAO.update(tb_falg);
				Toast.makeText(FlagManage.this,"【便签数据】修改成功", Toast.LENGTH_SHORT).show();
			}
			
		});
		btnDel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flagDAO.delete(Integer.parseInt(strid));
				Toast.makeText(FlagManage.this, "【便签数据】删除成功", Toast.LENGTH_SHORT).show();
			}
			
		});
	}
	
}
