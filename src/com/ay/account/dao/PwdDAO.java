package com.ay.account.dao;

import com.ay.account.model.Tb_pwd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PwdDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public PwdDAO(Context context)
	{
		helper=new DBOpenHelper(context);
	}
	/*�������
	 * @param Tb_pwd
	 * 
	 * 
	 * */
	public void add(Tb_pwd tb_pwd)
	{
		db=helper.getWritableDatabase();
		db.execSQL("insert into tb_pwd(password) values(?)",
				new Object[]{tb_pwd.getPassword()});
		
	}
	/*����������Ϣ
	 * @param tb_pwd
	 * 
	 * 
	 * 
	 * */
	public void update(Tb_pwd tb_pwd)
	{
		db=helper.getWritableDatabase();
		db.execSQL("update tb_pwd set password=?",
				new Object[]{tb_pwd.getPassword()});
	}
	/*
	 * ������Ϣ
	 * @return
	 * */
	public Tb_pwd find()
	{
		db=helper.getWritableDatabase();//��ʼ��SQLiteDatabase����
		Cursor cursor=db.rawQuery("select password from tb_pwd", null);
		if(cursor.moveToNext())
		{
			return new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
		}
		return null;
	}
	/*
	 * ��ü�¼����
	 * @return
	 * 
	 * */
	public long getCount()
	{
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select count(password) from tb_pwd", null);
		if(cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}
}
