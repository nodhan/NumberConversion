package com.nodhan.numconv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by nodhan on 12/11/16.
 */

class DBHelper extends SQLiteOpenHelper {

    static String TABLE_NAME = "history";

    static DBHelper createDbConnection(Context context) {
        return new DBHelper(context, TABLE_NAME, null, 1);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "('id' integer primary key, 'number' varchar(30), 'conv1' varchar(30), 'conv2' varchar(30), 'conv3' varchar(30), 'type' integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addData(String number, List<ConvertedNumberInfo> infos, int type) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", number);
        for (int i = 1; i <= infos.size(); i++) {
            contentValues.put("conv" + i, infos.get(i - 1).number);
        }
        contentValues.put("type", type);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    Cursor getData() {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    void clearData() {
        this.getWritableDatabase().execSQL("DELETE FROM " + TABLE_NAME);
    }
}
