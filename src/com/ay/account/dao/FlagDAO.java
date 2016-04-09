package com.ay.account.dao;

import java.util.ArrayList;
import java.util.List;

import com.ay.account.model.Tb_flag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FlagDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public FlagDAO(Context context)
	{
		helper=new DBOpenHelper(context);
	}
	/*��ӱ�ǩ��Ϣ
	 * @param tb_flag
	 * 
	 * 
	 * */
	public void add(Tb_flag tb_flag)
	{
		db=helper.getWritableDatabase();
		db.execSQL("insert into tb_flag(_id,flag ) values(?,?)",
				new Object[]{tb_flag.get_id(),tb_flag.getFlag()});
		
	}
	/*���±�ǩ��Ϣ
	 * @param tb_flag
	 * 
	 * 
	 * */
	public void update(Tb_flag tb_falg)
	{
		db=helper.getWritableDatabase();
		db.execSQL("upate tb_falg set flag=�� where _id=?",
				new Object[]{tb_falg.getFlag(),tb_falg.get_id()});
	}
	/*
	 * ���ұ�ǩ��Ϣ
	 * @param _id
	 * 
	 * @return
	 * */
	public Tb_flag find(int id)
	{
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select _id ,flag from "
				+ "tb_falg where _id=?",new String[]
						{String.valueOf(id)} );
		if(cursor.moveToNext())
		{
			return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("flag")));
			
		}
		return null;
	}
	/*ɾ����ǩ��Ϣ
	 * @param ids
	 * 
	 * 
	 * */
	public void delete(Integer...ids)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<ids.length;i++)
		{
			sb.append('?').append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		db=helper.getWritableDatabase();
		db.execSQL("delete from tb_flag where _id in("+sb+")",(Object[])ids);
		
	}
	/*��ȡ��ǩ��Ϣ
	 * @param start
	 * 		��ʼλ��
	 * @param count
	 * 		ÿҳ��ʾ������
	 * @return
	 * 
	 * */
	public List<Tb_flag> getScrollData(int start,int count)
	{
		List<Tb_flag> listTb_flags = new ArrayList<Tb_flag>();// �������϶���
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from tb_flag limit ?,?",
		 new String[]{String.valueOf(start),String.valueOf(count)});
		while(cursor.moveToNext())
		{
			listTb_flags.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getString(cursor.getColumnIndex("flag"))));
		}
		return listTb_flags;
	}
	/*
	 * ��ȡ�ܼ�¼��
	 * @return
	 * 
	 * */
	public long getCount()
	{
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select count(_id) from tb_flag", null);
		if(cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}
	/*
	 * 	��ȡ������ı�ǩ
	 * @return
	 * 
	 * */
	public int getMAxId()
	{
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select max(_id) from tb_flag", null);
		while(cursor.moveToNext())
		{
			return cursor.getInt(0);
		}
		return 0;
	}
	
}
