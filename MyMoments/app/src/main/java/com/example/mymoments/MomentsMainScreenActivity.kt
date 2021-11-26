package com.example.mymoments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoments.activities.AddNewMoment
import com.example.mymoments.adapters.MomentAdapter
import com.example.mymoments.data.Moment
import kotlinx.android.synthetic.main.activity_moment.*


class MomentsMainScreenActivity : AppCompatActivity() {

    private var momentList : ArrayList<Moment> = ArrayList() // list represeting all moments
    private lateinit var linearLayoutManager: LinearLayoutManager // our listview Manager
    private lateinit var adapter : MomentAdapter  //adapter for each moment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moment)

        momentList.add(Moment("2019","first Moment","My first moment"))
        momentList.add(Moment("2020","second Moment","My second moment"))

        linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager
        adapter = MomentAdapter(momentList)

        recycler_view.adapter = adapter
    }

    fun createNewMoment(view: View){

        val intent = Intent(this,AddNewMoment::class.java)
        startActivity(intent)
    }
}