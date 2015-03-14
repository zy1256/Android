package com.itwill.sqlite;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void openOrCreateDB() {
		db=SQLiteDatabase.openDatabase(
				"/data/data/com.itwill.sqlite/databases/friends.db",
				null, SQLiteDatabase.CREATE_IF_NECESSARY);
		setTitle("Open DB");
	}
	private void closeDB() {
		db.close();
		setTitle("Close DB");
	}
	private void createTable() {
		String createTableSQL=
				"create table friend(_id integer primary key autoincrement," +
				"name text," +
				"phone text," +
				"address text)";
		db.execSQL(createTableSQL);
		/*
		db.insert();
		db.update();
		db.delete();
		db.query()
		db.rawQuery()
		*/
		setTitle("create table");
	}
	
	private void dropTable() {
		String dropTableSQL="drop table friend";
		db.execSQL(dropTableSQL);
		setTitle("Drop table");
	}
	private void insertRow() {
		/***CASE1****/
		String insertSQL=
		"insert into friend(name,phone,address) values(?,?,?)";
		String[] param={"KIM","000-1111-2222","서울시"};
		db.execSQL(insertSQL,param);
		
		/***CASE2****/
		ContentValues values=new ContentValues();
		values.put("name", "KIM");
		values.put("phone", "010-5254-8577");
		values.put("address", "경기도");
		long rowId=db.insert("friend", null, values);
		setTitle("rowId:"+rowId+" 행이 insert");
		
	}
	private void updateById() {
		/**********case1**********/
		String updateSQL="update friend set phone=? ,address=? where _id= ?";
		String[] bindArgs={"000-000-0000","LA","1"};
		db.execSQL(updateSQL, bindArgs);
		
		/**********case2***********/
		ContentValues values=new ContentValues();
		values.put("phone", "888-888-8888");
		values.put("address", "CA");
		String whereClause="_id=?";
		String[] whereArgs={"3"};
		int rows=db.update("friend", values, whereClause, whereArgs);
		setTitle(rows+" rows update");
		
	}
	private void updateByIdRange() {
		/*
		 * case1
		 */
		String updateSQL=
				"update friend set name=? where _id>= ? and _id <= ?";
		String[] args={"KIMTAEHEE","5","10"};
		db.execSQL(updateSQL,args);
		/*
		 * case2
		 */
		ContentValues values=new ContentValues();
		values.put("name", "LEEHYOLEE");
		String whereClause="_id>=? and _id<=?";
		String[] whereArgs={"1","3"};
		int rows=db.update("friend", values, whereClause, whereArgs);
		setTitle(rows+ "행 update..");
		
	}
	private void deleteByIdRange() {
		/*
		 * case1
		 */
		String deleteSQL="delete from friend where _id > ?";
		String[] args={"15"};
		db.execSQL(deleteSQL,args);
		/*
		 * case2
		 */
		int rows = db.delete("friend", "_id>?", new String[]{"10"});
		setTitle(rows+" 행 delete");
	}
	private void selectByCondition() {
		
		String selectSQL="select _id,name,phone,address from friend " +
						 "where _id > ? and name=? " +
						 "order by name desc";
		//String[] selectionArgs={"10","KIM"};
		//Cursor cursor = db.rawQuery(selectSQL, selectionArgs);
		//displayFriendCursorAdapter(cursor);
		String[] columns={"_id","name","phone","address"};
		String selection="_id>? and name=?";
		String[] selectionArgs={"10","KIM"};
		String orderBy="name desc";
		
		Cursor cursor=db.query(true, "friend", columns,
				selection, selectionArgs, null,
				null, orderBy, "5");
		displayFriendCursorAdapter(cursor);
		
	}
	private void selectAll() {
		/*
		 * case 1
		 */
		/*
		String selectSQL="select _id,name,phone,address from friend";
		Cursor cursor=db.rawQuery(selectSQL, null);
		displayFriend(cursor);
		*/
		/*
		 * case 2
		 */
		Cursor cursor=db.query(true, "friend", 
				new String[]{"_id","name","phone","address"}, null,
				null, null,
				null, "_id desc", 
				null);
		//displayFriend(cursor);
		//displayFriendCursorAdapter(cursor);
		displayFriendCursorAdapterAndroidLayout(cursor);
	}
	
	private void displayFriendCursorAdapterAndroidLayout(Cursor cursor){
		
		String[] from = {"name","address"};
		int[] to={android.R.id.text1,android.R.id.text2};
		SimpleCursorAdapter cursorAdapter=
				new SimpleCursorAdapter(
						getApplicationContext(), 
						android.R.layout.simple_list_item_2,
						cursor,
						from,
						to,
						1);
		ListView listview=(ListView)findViewById(R.id.listView1);
		listview.setAdapter(cursorAdapter);
		
	}
	
	
	private void displayFriendCursorAdapter(Cursor cursor){
		
		String[] from = {"_id","name","phone","address"};
		int[] to={R.id.idTV,R.id.nameTV,R.id.phoneTV,R.id.addressTV};
		SimpleCursorAdapter cursorAdapter=
				new SimpleCursorAdapter(
						getApplicationContext(), 
						R.layout.list_child,
						cursor,
						from,
						to,
						1);
		ListView listview=(ListView)findViewById(R.id.listView1);
		listview.setAdapter(cursorAdapter);
		
	}
	private void displayFriend(Cursor cursor){
		
		ArrayList<Friend> friendList=new ArrayList<Friend>();
		while(cursor.moveToNext()){
			int _id=cursor.getInt(0);
			String name=cursor.getString(1);
			String phone=cursor.getString(2);
			String address=cursor.getString(3);
			Log.e("name", name);
			friendList.add(new Friend(_id, name, phone, address));
		}
		cursor.close();
		
		ListView listview=(ListView)findViewById(R.id.listView1);
		
		ArrayAdapter<Friend> adapter=
				new ArrayAdapter<Friend>(getApplicationContext(),0,friendList){
			@Override
			public View getView(int position, View convertView,
					ViewGroup parent) {
				View view=getLayoutInflater().inflate(R.layout.list_child, null);
				Friend friend=(Friend)getItem(position);
				TextView idTV=(TextView)view.findViewById(R.id.idTV);
				TextView nameTV=(TextView)view.findViewById(R.id.nameTV);
				TextView phoneTV=(TextView)view.findViewById(R.id.phoneTV);
				TextView addressTV=(TextView)view.findViewById(R.id.addressTV);
				idTV.setText(friend.get_id()+"");
				nameTV.setText(friend.getName());
				phoneTV.setText(friend.getPhone());
				addressTV.setText(friend.getAddress());
				
				return view;
			}
			
		};
		
		
		listview.setAdapter(adapter);
		
	}
	
	public void xxx(View v) {
		int bid = v.getId();
		switch (bid) {
		case R.id.selectAllB:
			selectAll();
			break;
		case R.id.openB:
			openOrCreateDB();
			break;
		case R.id.closeB:
			closeDB();
			break;
		case R.id.createTableB:
			createTable();
			break;
		case R.id.dropTableB:
			dropTable();
			break;
		case R.id.insertB:
			insertRow();
			break;
		case R.id.updateOneB:
			updateById();
			break;
		case R.id.updateConditionB:
			updateByIdRange();
			break;
		case R.id.deleteB:
			deleteByIdRange();
			break;
		case R.id.selectByConditionB:
			selectByCondition();
			break;
		}
	}
}
