package com.ay.account.dao;

import java.util.ArrayList;
import java.util.List;

import com.ay.account.model.Tb_outaccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OutaccountDAO {
	private DBOpenHelper helper;//����DBOpenHelper����
	private SQLiteDatabase db;//����SQLitedatabase����
	public OutaccountDAO(Context context)
	{
		helper=new DBOpenHelper(context);//��ʼ��DBOpenHelper����
		
	}
	/*
	 * ���֧����Ϣ
	 * @param tb_outaccount
	 * */
	public void add(Tb_outaccount tb_outaccount)
	{
		db=helper.getWritableDatabase();//��ʼ��SQLiteDatabase����
		//ִ����Ӳ���
		db.execSQL("insert into tb_outaccount(_id ,money,time,type,address,mark) values(?,?,?,?,?,?)",new
				Object[]{tb_outaccount.get_id(),tb_outaccount.getMoney(),tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark()});
	}
	/*
	 * ����֧����Ϣ
	 * @param  tb_outaccount
	 * */
	public void update(Tb_outaccount tb_outaccount)
	{
		db=helper.getWritableDatabase();//��ʼ��SQLiteDatabase����
		db.execSQL("update tb_outaccount set money=?,"
				+ "time=?,"
				+ "type=?,address=?,"
				+ "mark=?,"
				+ "where _id=?",
				new Object[]
						{
								tb_outaccount.getMoney(),
								tb_outaccount.getTime(),
								tb_outaccount.getMark(),
								tb_outaccount.get_id()
						});
	}
	/*
	 * ɾ��֧����Ϣ
	 * @param ids
	 * 
	 * */
	public void detele(Integer...ids)
	{
		if(ids.length>0)
		{
			StringBuffer sb=new StringBuffer();//����StringBuffer����
			for(int i=0;i<ids.length;i++)
			{
				sb.append("?").append(',');//��ɾ��������ӵ�StringBuffer������
			}
			sb.deleteCharAt(sb.length()-1);//ȥ�����һ����,���ַ�
			db=helper.getWritableDatabase();//��ʼ��SQliteDatabase����
			db.execSQL("delete from tb_outaccount where _id in("+sb+")",(Object[])ids);
		}
	}
	/*
	 * ��ȡ֧����Ϣ
	 * @param start
	 *  	��ʼλ��
	 *  @param count
	 *  		ÿҳ��ʾ������
	 *  @return
	 * 
	 * */
	public List<Tb_outaccount> getScrollData(int start,int count)
	{
		List<Tb_outaccount> tb_outaccount=new ArrayList<Tb_outaccount>();//�������϶���
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from tb_outaccount limit ?,?", new String[]{String.valueOf(start),String.valueOf(count)});
		while(cursor.moveToNext())//�������е�֧����Ϣ
		{
			tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("address")),
					cursor.getString(cursor.getColumnIndex("mark"))));
		}
		return tb_outaccount;
	}
	/*
	 * ��ȡ�ܼ�¼��
	 * @return
	 * */
	public long getCount()
	{
			db=helper.getWritableDatabase();
			Cursor cursor=db.rawQuery("select count(_id) from tb_outaccount",null);
			if(cursor.moveToNext())
			{
				return cursor.getLong(0);
			}
			return  0;
	}
	/*
	 * ��ȡ֧�������
	 * @return
	 * 
	 * */
	public int getMaxId()
	{
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select max(_id) from tb_outaccount", null);
		while(cursor.moveToNext())
		{
			return cursor.getInt(0);
		}
		return 0;
	}
	
}
