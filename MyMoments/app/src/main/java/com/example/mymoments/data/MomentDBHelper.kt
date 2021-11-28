package com.example.mymoments.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_DATE
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_MOMENT
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_TITLE
import com.example.mymoments.data.DatabaseManager.MomentEntry.TABLE_NAME
import com.example.mymoments.data.DatabaseManager.MomentEntry._ID

class MomentDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "mymoments.db"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_MOMENTS_TABLE = "CREATE TABLE $TABLE_NAME ("+
                "$_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "$COLUMN_DATE TEXT, "+
                "$COLUMN_TITLE TEXT, "+
                "$COLUMN_MOMENT TEXT )"

        private const val  SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }


    override fun onCreate(db: SQLiteDatabase?) {

      db!!.execSQL(SQL_CREATE_MOMENTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(SQL_DELETE_ENTRIES)

    }


}