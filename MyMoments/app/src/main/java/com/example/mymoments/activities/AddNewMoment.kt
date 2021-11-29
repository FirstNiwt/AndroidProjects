package com.example.mymoments.activities

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.mymoments.R
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_DATE
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_MOMENT
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_TITLE
import com.example.mymoments.data.DatabaseManager.MomentEntry.TABLE_NAME
import com.example.mymoments.data.DatabaseManager.MomentEntry._ID
import com.example.mymoments.data.Moment
import com.example.mymoments.data.MomentDBHelper
import kotlinx.android.synthetic.main.activity_add_new_moment.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewMoment : AppCompatActivity() {
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_moment)

        id = intent.getIntExtra("rowID", 0)

        if(id!=0){
            readMoment()

        }


        Log.d("NewMoment", "The passed ID is $id")

        val currentDate = SimpleDateFormat("EEE, d  MMM yyyy")

        add_moment_date.text = currentDate.format(Date())
    }

    private fun readMoment() {

        val momentDBHelper = MomentDBHelper(this)
        val db = momentDBHelper.readableDatabase

        val dbProjection = arrayOf(COLUMN_DATE, COLUMN_TITLE, COLUMN_MOMENT)

        val selection = "$_ID = ?"
        val selectionArgs = arrayOf("$id")

        val cursor : Cursor = db.query(TABLE_NAME,dbProjection,selection,selectionArgs,
                                null,null,null)
        val  dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE)
        val  titleColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE)
        val  momentColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_MOMENT)


        while(cursor.moveToNext())
        {
            val currentDate = cursor.getString(dateColumnIndex)
            val currentTitle = cursor.getString(titleColumnIndex)
            val currentMoment = cursor.getString(momentColumnIndex)

            add_moment_date.text = currentDate
            moment_title_edit_text.setText(currentTitle)
            moment_text.setText(currentMoment)

        }
        cursor.close()



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu,menu)
        return true
    }

    private fun insertMoment()
    {
        val dateString = add_moment_date.text.toString()
        val momentTitle: String = moment_title_edit_text.text.toString().trim(){it <= ' '}
        val momentTextDescription: String = moment_text.text.toString().trim(){it <= ' '}

        val momentDBHelper = MomentDBHelper(this)

        val db = momentDBHelper.writableDatabase

        val values = ContentValues().apply{
            put(COLUMN_DATE,dateString)
            put(COLUMN_TITLE,momentTitle)
            put(COLUMN_MOMENT, momentTextDescription)
        }

        val rowId = db.insert(TABLE_NAME, null, values)

        if(rowId.equals(-1))
        {
            Toast.makeText(this, "problem in inserting new moment", Toast.LENGTH_SHORT).show()
        }
        else{

            Toast.makeText(this, "New moment added to the list", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item?.itemId) {

            R.id.save_moment -> {

                if(id == 0)
                {
                    insertMoment()
                }else{
                    updateMoment(id)
                }


                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMoment(id: Int) {

        val momentDBHelper = MomentDBHelper(this)

        val db = momentDBHelper.writableDatabase

        val values = ContentValues().apply{
            put(COLUMN_TITLE, moment_title_edit_text.text.toString())
            put(COLUMN_MOMENT, moment_text.text.toString())
        }

        db.update(TABLE_NAME,values, "$_ID = $id",null)


    }
}