package com.example.mymoments.activities

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.mymoments.R
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_DATE
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_MOMENT
import com.example.mymoments.data.DatabaseManager.MomentEntry.COLUMN_TITLE
import com.example.mymoments.data.DatabaseManager.MomentEntry.TABLE_NAME
import com.example.mymoments.data.Moment
import com.example.mymoments.data.MomentDBHelper
import kotlinx.android.synthetic.main.activity_add_new_moment.*
import java.text.SimpleDateFormat
import java.util.*

class AddNewMoment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_moment)

        val currentDate = SimpleDateFormat("EEE, d  MMM yyyy")

        add_moment_date.text = currentDate.format(Date())
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

            Toast.makeText(this, "New moment added to the list $rowId", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item?.itemId) {

            R.id.save_moment -> {

                insertMoment()
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}