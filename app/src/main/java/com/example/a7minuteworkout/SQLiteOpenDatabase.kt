package com.example.a7minuteworkout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteOpenDatabase(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "SevenMinuteWorkOut.db"
        private val TABLE_NAME = "history"
        private val COLOUMN_ID = "_id"
        private val COLOUMN_DATE = "completed_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create = ("CREATE TABLE " + TABLE_NAME + "("
                + COLOUMN_ID + " INTEGER PRIMARY KEY,"
                + COLOUMN_DATE + " TEXT)")
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addDate(date: String) {
        val content = ContentValues()
        content.put(COLOUMN_DATE, date)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, content)
        db.close()
    }

    fun getAllHistory(): ArrayList<String> {
        val list = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        while(cursor.moveToNext()) {
            val dateValue = cursor.getString(cursor.getColumnIndex(COLOUMN_DATE))
            list.add(dateValue)
        }
        cursor.close()
        return list
    }
}