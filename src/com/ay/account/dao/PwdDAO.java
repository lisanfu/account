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
	/*
	 * 查找信息
	 * @return
	 * */
	public Tb_pwd find()
	{
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		Cursor cursor=db.rawQuery("select password from tb_pwd", null);
		if(cursor.moveToNext())
		{
			return new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
		}
		return null;
	}
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
