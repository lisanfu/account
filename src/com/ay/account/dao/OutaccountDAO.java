package com.ay.account.dao;

import java.util.ArrayList;
import java.util.List;

import com.ay.account.model.Tb_outaccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OutaccountDAO {
	private DBOpenHelper helper;//创建DBOpenHelper对象
	private SQLiteDatabase db;//创建SQLitedatabase对象
	public OutaccountDAO(Context context)
	{
		helper=new DBOpenHelper(context);//初始化DBOpenHelper对象
		
	}
	/*
	 * 添加支出信息
	 * @param tb_outaccount
	 * */
	public void add(Tb_outaccount tb_outaccount)
	{
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
		//执行添加操作
		db.execSQL("insert into tb_outaccount(_id ,money,time,type,address,mark) values(?,?,?,?,?,?)",new
				Object[]{tb_outaccount.get_id(),tb_outaccount.getMoney(),tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark()});
	}
	/*
	 * 更新支出信息
	 * @param  tb_outaccount
	 * */
	public void update(Tb_outaccount tb_outaccount)
	{
		db=helper.getWritableDatabase();//初始化SQLiteDatabase对象
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
	 * 删除支出信息
	 * @param ids
	 * 
	 * */
	public void detele(Integer...ids)
	{
		if(ids.length>0)
		{
			StringBuffer sb=new StringBuffer();//创建StringBuffer对象
			for(int i=0;i<ids.length;i++)
			{
				sb.append("?").append(',');//将删除条件添加到StringBuffer对象中
			}
			sb.deleteCharAt(sb.length()-1);//去掉最后一个“,”字符
			db=helper.getWritableDatabase();//初始化SQliteDatabase对象
			db.execSQL("delete from tb_outaccount where _id in("+sb+")",(Object[])ids);
		}
	}
	/*
	 * 获取支出信息
	 * @param start
	 *  	起始位置
	 *  @param count
	 *  		每页显示的数量
	 *  @return
	 * 
	 * */
	public List<Tb_outaccount> getScrollData(int start,int count)
	{
		List<Tb_outaccount> tb_outaccount=new ArrayList<Tb_outaccount>();//创建集合对象
		db=helper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from tb_outaccount limit ?,?", new String[]{String.valueOf(start),String.valueOf(count)});
		while(cursor.moveToNext())//遍历所有的支出信息
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
	 * 获取总记录数
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
	 * 获取支出最大标号
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
