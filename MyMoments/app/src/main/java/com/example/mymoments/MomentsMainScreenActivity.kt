package com.example.mymoments

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoments.activities.AddNewMoment
import com.example.mymoments.adapters.MomentAdapter
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_DATE
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_MOMENT
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_TITLE
import com.example.mymoments.data.DatabaseManager.MomentEntry.TABLE_NAME
import com.example.mymoments.data.DatabaseManager.MomentEntry._ID
import com.example.mymoments.data.Moment
import com.example.mymoments.data.MomentDBHelper
import kotlinx.android.synthetic.main.activity_moment.*


class MomentsMainScreenActivity : AppCompatActivity() {

    private lateinit var momentDBHelper: MomentDBHelper

    private var momentList : ArrayList<Moment> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter : MomentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moment)

        momentDBHelper = MomentDBHelper(this)

        displayDataInfo()



    }

    private fun displayDataInfo()
    {
        val dataBase = momentDBHelper.readableDatabase

        val dataBaseProjection  = arrayOf(_ID,COLUMN_DATE,COLUMN_TITLE,COLUMN_MOMENT)

        val cursor: Cursor = dataBase.query(TABLE_NAME,dataBaseProjection,null,null,null,null,null)

        val idColumnIndex = cursor.getColumnIndexOrThrow(_ID)

        val idColumnDate = cursor.getColumnIndexOrThrow(COLUMN_DATE)

        val idColumnTitle = cursor.getColumnIndexOrThrow(COLUMN_TITLE)

        val idColumnMoment = cursor.getColumnIndexOrThrow(COLUMN_MOMENT)

        while(cursor.moveToNext())
        {
            val currentId = cursor.getInt(idColumnIndex)
            val currentDate = cursor.getString(idColumnDate)
            val currentTitle = cursor.getString(idColumnTitle)
            val currentMoment = cursor.getString(idColumnMoment)

            momentList.add(Moment(currentId ,currentDate,currentTitle,currentMoment))


        }
        cursor.close()

        linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager
        adapter = MomentAdapter(momentList)

        recycler_view.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        momentList.clear()
        displayDataInfo()
    }

    fun createNewMoment(view: View){

        val intent = Intent(this,AddNewMoment::class.java)
        startActivity(intent)
    }
}