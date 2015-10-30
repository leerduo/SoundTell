/*************************************************************************************************
 * 版权所有 (C)2015,  成都市商联汇通技术有限公司
 * 
 * 文件名称：UserData.java
 * 内容摘要：内部
 * 当前版本： TODO
 * 作        者： 李加蒙
 * 完成日期：2015-10-30 上午11:46:33
 * 修改记录：
 * 修改日期：2015-10-30 上午11:46:33
 * 版   本  号：
 * 修   改  人：
 * 修改内容：
 ************************************************************************************************/

package com.xh.soundtell.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 文件名称：SqliteData.java
 */
public class UserData extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "user.db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "user_table";
	public final static String USER_ID = "user_id";
	public final static String USER_NAME = "user_name";
	public final static String USER_PASSWORD = "user_password";

	public UserData(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 创建table
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + USER_ID
				+ " INTEGER primary key autoincrement, " + USER_NAME
				+ " text, " + USER_PASSWORD + " text);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}

	// 增加操作
	public long insert(String USERname, String author) {
		SQLiteDatabase db = this.getWritableDatabase();
		/* ContentValues */
		ContentValues cv = new ContentValues();
		cv.put(USER_NAME, USERname);
		cv.put(USER_PASSWORD, author);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}

	// 删除操作
	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = USER_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	// 修改操作
	public void update(int id, String USERname, String author) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = USER_ID + " = ?";
		String[] whereValue = { Integer.toString(id) };

		ContentValues cv = new ContentValues();
		cv.put(USER_NAME, USERname);
		cv.put(USER_PASSWORD, author);
		db.update(TABLE_NAME, cv, where, whereValue);
	}
}
